#!/usr/bin/env python3
# This Source Code Form is subject to the terms of the Mozilla Public
# License, v. 2.0. If a copy of the MPL was not distributed with this
# file, You can obtain one at https://mozilla.org/MPL/2.0/.
"""Make the eclipses' test vector: every lunar and solar eclipse of 2000-2049.

Source: Jean Meeus, Astronomical Algorithms, 2nd ed. (Willmann-Bell 1998), chapter 54,
"Eclipses": the mean phases with the periodic terms of the eclipse's time, gamma and u.
A lunar eclipse is any whose penumbral magnitude is positive, a solar one any whose
|gamma| is under 1.5433 + u; the umbral magnitude is written beside each lunar one.
EclipsesTest holds WB's rule to it: every full and new moon the rule marks has one.

Usage: tools/eclipse_vectors.py OUTDIR
  writes OUTDIR/meeus-eclipses.tsv: kind (moon, sun), the UT date of greatest eclipse,
  the umbral magnitude (moon) or gamma (sun).
"""
import math
import sys
from datetime import date
from pathlib import Path

FIRST, LAST = 2451544.5, 2469807.5  # 2000-01-01 and 2050-01-01, 0h UT


def eclipse(k):
    """Meeus ch. 54 for the phase k (whole for a new moon, half for a full one), or None."""
    r = math.radians
    t = k / 1236.85
    jde = 2451550.09766 + 29.530588861 * k + 0.00015437 * t * t
    e = 1 - 0.002516 * t
    m = r(2.5534 + 29.10535670 * k)
    mp = r(201.5643 + 385.81693528 * k)
    f = r(160.7108 + 390.67050284 * k)
    om = r(124.7746 - 1.56375588 * k)
    if abs(math.sin(f)) > 0.36:
        return None
    f1 = f - r(0.02665) * math.sin(om)
    p = (0.2070 * e * math.sin(m) + 0.0024 * e * math.sin(2 * m) - 0.0392 * math.sin(mp) + 0.0116 * math.sin(2 * mp)
         - 0.0073 * e * math.sin(mp + m) + 0.0067 * e * math.sin(mp - m) + 0.0118 * math.sin(2 * f1))
    q = (5.2207 - 0.0048 * e * math.cos(m) + 0.0020 * e * math.cos(2 * m) - 0.3299 * math.cos(mp)
         - 0.0060 * e * math.cos(mp + m) + 0.0041 * e * math.cos(mp - m))
    w = abs(math.cos(f1))
    gamma = (p * math.cos(f1) + q * math.sin(f1)) * (1 - 0.0048 * w)
    u = (0.0059 + 0.0046 * e * math.cos(m) - 0.0182 * math.cos(mp) + 0.0004 * math.cos(2 * mp)
         - 0.0005 * math.cos(m + mp))
    if k % 1:
        if (1.5573 + u - abs(gamma)) / 0.5450 <= 0:
            return None
        return jde, "moon", (1.0128 - u - abs(gamma)) / 0.5450
    if abs(gamma) >= 1.5433 + u:
        return None
    return jde, "sun", gamma


def main(argv):
    if len(argv) != 1:
        sys.exit(__doc__)
    rows = []
    for i in range(-2, 13 * 51):
        for k in (i, i + 0.5):
            e = eclipse(k)
            if e and FIRST <= e[0] < LAST:
                jd, kind, value = e
                rows.append((jd, kind, date.fromordinal(math.floor(jd + 0.5) - 1721425), value))
    rows.sort()
    out = Path(argv[0]) / "meeus-eclipses.tsv"
    with out.open("w") as f:
        f.write("# Jean Meeus, Astronomical Algorithms, 2nd ed., Willmann-Bell 1998, ch. 54: every lunar (penumbral or deeper) "
                "and solar eclipse of 2000-2049, the UT date of greatest eclipse, the umbral magnitude or gamma (tools/eclipse_vectors.py).\n")
        for _, kind, d, value in rows:
            f.write(f"{kind}\t{d.isoformat()}\t{value:.2f}\n")
    print(f"{len(rows)} eclipses -> {out}")


if __name__ == "__main__":
    main(sys.argv[1:])
