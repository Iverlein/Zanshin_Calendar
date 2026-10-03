# This Source Code Form is subject to the terms of the Mozilla Public
# License, v. 2.0. If a copy of the MPL was not distributed with this
# file, You can obtain one at https://mozilla.org/MPL/2.0/.
"""Run BDRC's own Tibetan OCR (github.com/buda-base/tibetan-ocr-app, MIT) locally.

  bdrc_ocr.py APPDIR setup              clone the app, fetch its models, make its venv
  bdrc_ocr.py APPDIR run MODEL INDIR OUTDIR
                                        OCR every image in INDIR; MODEL is one of
                                        Woodblock, Woodblock-Stacks, Modern,
                                        Ume_Druma, Ume_Petsuk

CPU only, a few seconds a page. On the typeset 1996 White Beryl the
Woodblock model beats Modern (12% against 41% syllable errors, 2026-10-03),
and Woodblock-Stacks is better again (5% CER on the benchmark pages); on
woodblock tables the columns interleave and digits drop. Its use here is as
a quick second witness beside Yigdzin-1 (hf_read.py, disagree.py). Setup notes and the upstream
bug patched below: ~/knowledge/tibetan-woodblock-ocr.md on MONOLITH.
"""
import os
import subprocess
import sys
from pathlib import Path

REPO = "https://github.com/buda-base/tibetan-ocr-app.git"
MODELS = "https://github.com/buda-base/tibetan-ocr-app/releases/download/v0.1/bdrc_ocr_models_1.0.zip"
PACKAGES = ["numpy", "onnxruntime", "opencv-python-headless", "pillow", "pyctcdecode", "pyewts", "scipy",
            "thin-plate-spline", "tqdm", "pyyaml", "requests", "platformdirs", "PySide6-Essentials"]


def run(*cmd, cwd=None, env=None):
    subprocess.run(cmd, cwd=cwd, env=env, check=True)


def setup(app):
    if not app.exists():
        run("git", "clone", "--depth", "1", REPO, str(app))
    if not (app / "OCRModels").exists():
        run("curl", "-sLO", MODELS, cwd=app)
        run("unzip", "-q", "bdrc_ocr_models_1.0.zip", "-d", "OCRModels", cwd=app)
    if not (app / "venv").exists():
        run(sys.executable, "-m", "venv", "venv", cwd=app)
        run(str(app / "venv/bin/pip"), "install", "-q", *PACKAGES, cwd=app)
    cli = app / "cli.py"
    text = cli.read_text()
    # Upstream passes the models' parent directory where the model directory is meant.
    fixed = text.replace("import_local_model(os.path.dirname(model_dir))", "import_local_model(model_dir)")
    if fixed != text:
        cli.write_text(fixed)
    print("ready:", app)


def ocr(app, model, indir, outdir):
    Path(outdir).mkdir(parents=True, exist_ok=True)
    env = dict(os.environ, QT_QPA_PLATFORM="offscreen")
    # The CLI opens Models/Lines/... by relative path, so it runs from the app directory.
    run(str(app / "venv/bin/python"), "cli.py", "--model", f"OCRModels/{model}",
        "--folder", str(Path(indir).resolve()), "--output", str(Path(outdir).resolve()), cwd=app, env=env)


def main(argv):
    if len(argv) == 2 and argv[1] == "setup":
        setup(Path(argv[0]).resolve())
    elif len(argv) == 5 and argv[1] == "run":
        ocr(Path(argv[0]).resolve(), argv[2], argv[3], argv[4])
    else:
        sys.exit(__doc__)


if __name__ == "__main__":
    main(sys.argv[1:])
