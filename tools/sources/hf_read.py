# This Source Code Form is subject to the terms of the Mozilla Public
# License, v. 2.0. If a copy of the MPL was not distributed with this
# file, You can obtain one at https://mozilla.org/MPL/2.0/.
"""Transcribe Tibetan page images with a page-level OCR model from Hugging Face.

  hf_read.py setup VENVDIR          make the virtualenv it runs in (torch with CUDA)
  hf_read.py MODEL OUTDIR IMG... [--max-side N] [--max-tokens N]

MODEL is one of the keys of MODELS below. Output: OUTDIR/<image stem>.txt;
pages already read are skipped. Runs on the GPU with transformers >= 5.15,
torch, torchvision, accelerate and pillow — not in the host Python: run it
with the virtualenv's python (on MONOLITH ~/ai/tibetan-ocr/venv, RUNBOOK §7)
inside a memory-capped scope, e.g.

  systemd-run --user --scope -p MemoryMax=16G venv/bin/python hf_read.py yigdzin out img/*.jpg

The weights download into the Hugging Face cache on first use (Yigdzin
1.7 GB, MITRA 1.8 GB). On an 8 GB laptop GPU Yigdzin reads a page in
10–20 s, MITRA in 40–50 s (its linear-attention layers run without fused
kernels); measured 2026-10-03, with the readers' ranking, in
docs/sources/PLAN.md.

Each model is run the way its card says (prompt, greedy decoding, image
size); the notes there are the reason for every special case here.
Compare readers with ocr_bench.py.
"""
import os
import sys
import time
from pathlib import Path

MODELS = {
    # BDRC's Yigdzin 1 (Apache-2.0), PaddleOCR-VL-1.6 fine-tune, first on BDRC's leaderboard.
    # Trained with sequential image-token positions: mm_token_type_ids must be zeroed,
    # which llama.cpp cannot do (the reason a GGUF of it reads badly). DRY guards against loops.
    "yigdzin": dict(repo="BDRC/tibetan-ocr", prompt="Extract all Tibetan text. Preserve line breaks.",
                    sequential=True, dry=True, max_side=2400),
    # BDRC-MITRA, Qwen3.5-0.8B fine-tune, second on the leaderboard; best at 1500–2000 px.
    "mitra": dict(repo="buddhist-nlp/bdrc-mitra-ocr-qwen35-0.8b", prompt="Extract all text from this image",
                  max_side=2000),
    # The untuned base of Yigdzin, for comparison.
    "paddleocr-vl": dict(repo="PaddlePaddle/PaddleOCR-VL-1.6", prompt="OCR:", max_side=2400),
}


def load(spec):
    import torch
    from transformers import AutoModelForImageTextToText, AutoProcessor
    processor = AutoProcessor.from_pretrained(spec["repo"])
    model = AutoModelForImageTextToText.from_pretrained(spec["repo"], dtype=torch.bfloat16, device_map="cuda")
    dry = None
    if spec.get("dry"):
        from huggingface_hub import hf_hub_download
        sys.path.insert(0, str(Path(hf_hub_download(spec["repo"], "dry_logits_processor.py")).parent))
        from dry_logits_processor import make_hf_dry_processor
        dry = make_hf_dry_processor
    return processor, model, dry


def read(spec, processor, model, dry, image, max_tokens):
    import torch
    from transformers import LogitsProcessorList
    messages = [{"role": "user", "content": [{"type": "image", "image": image},
                                             {"type": "text", "text": spec["prompt"]}]}]
    inputs = processor.apply_chat_template(messages, add_generation_prompt=True, tokenize=True,
                                           return_dict=True, return_tensors="pt").to(model.device)
    if spec.get("sequential"):
        inputs["mm_token_type_ids"] = torch.zeros_like(inputs["input_ids"])
    n = inputs["input_ids"].shape[1]
    extra = {}
    if dry:
        extra["logits_processor"] = LogitsProcessorList([dry(prompt_len=n, multiplier=0.8, base=1.75, allowed_length=12)])
    with torch.inference_mode():
        # use_cache: Yigdzin's generation_config.json switches the KV cache off, which recomputes
        # the whole page, vision encoder included, for every token (1 token in 0.5 s instead of 40/s).
        out = model.generate(**inputs, do_sample=False, max_new_tokens=max_tokens, use_cache=True, **extra)
    return processor.decode(out[0][n:], skip_special_tokens=True).strip()


def setup(venv):
    import shutil
    import subprocess
    packages = ["torch", "torchvision", "transformers>=5.15", "accelerate", "pillow", "huggingface_hub",
                "pandas", "pyarrow"]
    index = ["--extra-index-url", "https://download.pytorch.org/whl/cu128"]
    if shutil.which("uv"):  # far faster, and reuses the wheels it has cached
        subprocess.run(["uv", "venv", "-q", "--python", "3.12", str(venv)], check=True)
        subprocess.run(["uv", "pip", "install", "-q", "--python", str(Path(venv) / "bin/python"),
                        "--index-strategy", "unsafe-best-match", *index, *packages], check=True)
    else:
        subprocess.run([sys.executable, "-m", "venv", str(venv)], check=True)
        subprocess.run([str(Path(venv) / "bin/pip"), "install", "-q", *index, *packages], check=True)
    print("ready:", venv)


def main(argv):
    if len(argv) == 2 and argv[0] == "setup":
        return setup(Path(argv[1]))
    opts = {"--max-side": None, "--max-tokens": "4096"}
    for o in opts:
        if o in argv:
            i = argv.index(o)
            opts[o] = argv[i + 1]
            argv = argv[:i] + argv[i + 2:]
    if len(argv) < 3 or argv[0] not in MODELS:
        sys.exit(__doc__)
    from PIL import Image
    spec = MODELS[argv[0]]
    out = Path(argv[1])
    out.mkdir(parents=True, exist_ok=True)
    side = int(opts["--max-side"] or spec["max_side"])
    sys.path.insert(0, str(Path(__file__).parent))
    import gpu
    gpu.exit_on_signals()
    t0 = time.time()
    processor, model, dry = load(spec)
    print(f"# {spec['repo']} loaded in {time.time() - t0:.0f} s", flush=True)
    for path in map(Path, argv[2:]):
        target = out / f"{path.stem}.txt"
        if target.exists():
            continue
        image = Image.open(path).convert("RGB")
        if max(image.size) > side:
            image.thumbnail((side, side), Image.BICUBIC)
        t = time.time()
        text = read(spec, processor, model, dry, image, int(opts["--max-tokens"]))
        target.write_text(text + "\n")
        print(f"{path.name}: {len(text)} chars, {time.time() - t:.1f} s", flush=True)
    # The model goes with this process; what else is still on the GPU?
    gpu.report(exclude={os.getpid()})


if __name__ == "__main__":
    main(sys.argv[1:])
