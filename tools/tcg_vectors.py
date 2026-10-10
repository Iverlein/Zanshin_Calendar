#!/usr/bin/env python3
# This Source Code Form is subject to the terms of the Mozilla Public
# License, v. 2.0. If a copy of the MPL was not distributed with this
# file, You can obtain one at https://mozilla.org/MPL/2.0/.
"""Make the planets' and Rāhu's test vectors from Edward Henning's own software.

Source: Edward Henning, Tibetan calendar software TCG 1.06 (MIT licence),
  http://www.kalacakra.org/calendar/os_tib.htm, tcg1309.zip.
Its planet and Rāhu routines (t3.c, t2.c) are compiled unchanged with its
arithmetic (utils.c, bcd.c) and a small main for the generalised Phugpa epoch;
empty headers stand in for the DOS ones. Needs gcc.

Usage: tools/tcg_vectors.py SCRATCH OUTDIR
  writes OUTDIR/henning-tcg-planets.tsv (each day 2000-01-01 to 2047-10-13)
  and OUTDIR/henning-tcg-rahu.tsv (true months 36800-37799). PlanetsTest reads them.
"""
import subprocess
import sys
import urllib.request
import zipfile
from pathlib import Path

URL = "http://www.kalacakra.org/calendar/tcg1309.zip"
CITE = ("# Edward Henning, Tibetan calendar software TCG 1.06 (MIT licence, "
        "http://www.kalacakra.org/calendar/os_tib.htm), generalised Phugpa epoch: ")

MAIN = r'''
#include <stdio.h>
#include <stdlib.h>
#include "tc.h"
#include "tc.def"
#include "bcd.h"
FILE *repfil;
int getch(void) { fprintf(stderr, "getch\n"); exit(3); }
void do_plans(int sz);
void do_rahu(int m, int tt);
extern int marmurdag[6], jupmurdag[6], satmurdag[6], mermurdag[6], venmurdag[6], rahudong[6], rahudong30[6];
static void p(int *a) { fprintf(stderr, "\t%d;%d,%d,%d,%d", a[0], a[1], a[2], a[3], a[4]); }
int main(int argc, char **argv) {
  int i;
  epch = 0; printinprog = 0; rahupart = 93;
  maradd = 4; jupadd = 511; satadd = 2995; meradd = 2080; venadd = 277; dragkadd = 6663418;
  if (argv[1][0] == 'p')
    for (i = atoi(argv[2]); i < atoi(argv[3]); ++i) {
      do_plans(i - 1355847);
      fprintf(stderr, "%d", i); p(marmurdag); p(jupmurdag); p(satmurdag); p(mermurdag); p(venmurdag); fprintf(stderr, "\n");
    }
  else
    for (i = atoi(argv[2]); i < atoi(argv[3]); ++i) {
      do_rahu(i, 15);
      fprintf(stderr, "%d", i); p(rahudong); p(rahudong30); fprintf(stderr, "\n");
    }
  return 0;
}
'''


def main(argv):
    if len(argv) != 2:
        sys.exit(__doc__)
    scratch, out = Path(argv[0]), Path(argv[1])
    src, shim = scratch / "tcg", scratch / "shim"
    src.mkdir(parents=True, exist_ok=True)
    shim.mkdir(exist_ok=True)
    archive = scratch / "tcg1309.zip"
    if not archive.exists():
        archive.write_bytes(urllib.request.urlopen(URL, timeout=120).read())
    zipfile.ZipFile(archive).extractall(src)
    for h in ("conio.h", "dos.h", "bios.h"):
        (shim / h).touch()
    (scratch / "main.c").write_text(MAIN)
    exe = scratch / "tcg_vectors"
    subprocess.run(["gcc", "-w", "-std=gnu89", f"-I{shim}", f"-I{src}", "-o", str(exe), str(scratch / "main.c")]
                   + [str(src / f) for f in ("t2.c", "t3.c", "utils.c", "bcd.c")], check=True)

    def run(kind, first, last):
        r = subprocess.run([str(exe), kind, str(first), str(last)], capture_output=True, text=True, check=True)
        return r.stderr  # the routines' own chatter goes to stdout
    (out / "henning-tcg-planets.tsv").write_text(
        CITE + "the five planets' apparent places (myur dag) for each Julian day number, Mars, Jupiter, Saturn, "
        "Mercury, Venus, as do_plans in t3.c computes them, the source compiled unchanged (tools/tcg_vectors.py).\n"
        + run("p", 2451545, 2469000))
    (out / "henning-tcg-rahu.tsv").write_text(
        CITE + "Rāhu's head (gdong) on the 15th and the 30th of each true month count (zla dag), as do_rahu in "
        "t2.c prints it, the source compiled unchanged (tools/tcg_vectors.py).\n" + run("r", 36800, 37800))


if __name__ == "__main__":
    main(sys.argv[1:])
