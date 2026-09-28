/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.kyureki

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import zanshin.core.vectors
import java.time.LocalDate

/**
 * Two years of daily annotations from こよみのページ (koyomi8.com), an
 * independent computation, against ours.
 *
 * Not compared: 神吉日 (its exclusion rules are not recoverable — SPEC §7.5),
 * 五墓日 (needs the birth year's 納音), and 大禍日・狼藉日・滅門日, which koyomi8 shows for everyone
 * while the rule makes them personal. 歳下食 is not computed by koyomi8 at all.
 * koyomi8 writes the lodge 氐 with the look-alike 氏.
 */
class Koyomi8Test {
    private val rows = vectors("koyomi8-2026-2027.tsv")

    private val daily = mapOf(
        "天赦日" to Senjitsu.TENSHA, "一粒万倍日" to Senjitsu.ICHIRYU_MANBAI, "大明日" to Senjitsu.DAIMYO,
        "天恩日" to Senjitsu.TENON, "母倉日" to Senjitsu.BOSO, "鬼宿日" to Senjitsu.KISHUKU,
        "受死日" to Senjitsu.JUSHI, "十死日" to Senjitsu.JISSHI, "帰忌日" to Senjitsu.KIKO, "血忌日" to Senjitsu.CHIIMI,
        "天火日" to Senjitsu.TENKA, "地火日" to Senjitsu.JIKA, "往亡日" to Senjitsu.OMO, "不成就日" to Senjitsu.FUJOJU,
        "三隣亡" to Senjitsu.SANRINBO, "重日" to Senjitsu.JUNICHI, "復日" to Senjitsu.FUKUNICHI,
        "八専間日" to Senjitsu.HASSEN_MABI, "月徳日" to Senjitsu.SETTOKU,
    )

    /** Periods koyomi8 marks by their first and last day. */
    private val periods = mapOf(
        "十方暮" to setOf(Senjitsu.JIPPOGURE),
        "八専" to setOf(Senjitsu.HASSEN, Senjitsu.HASSEN_MABI),
        "天一天上" to setOf(Senjitsu.TENICHI_TENJO),
        "大土" to setOf(Senjitsu.OTSUCHI),
        "小土" to setOf(Senjitsu.KOTSUCHI),
    )

    @Test
    fun `annotations agree day by day for 2026-2027`() {
        val problems = mutableListOf<String>()
        for (r in rows) {
            val date = LocalDate.parse(r[0])
            val day = Rekichu.of(date)
            val kyureki = Kyureki.of(date)
            val (choku, shuku) = r[2].split('/')
            val (kyuMonth, kyuDay) = r[4].split('/')
            val (rokuyo, star) = r[5].split('/')
            fun check(what: String, expected: Any, actual: Any) {
                if (expected != actual) problems += "$date $what: koyomi8 $expected, ours $actual"
            }
            check("kanshi", r[1], day.dayKanshi.kanji)
            check("choku", choku, day.choku.kanji)
            check("28 lodge", shuku.replace('氏', '氐'), day.shuku.kanji)
            check("kyureki", "${kyuMonth.removePrefix("閏")}/$kyuDay", "${kyureki.month}/${kyureki.day}")
            check("leap", kyuMonth.startsWith("閏"), kyureki.leapMonth)
            check("rokuyo", rokuyo, kyureki.rokuyo.kanji)
            check("day star", star, day.dayStar.kanji.take(2))

            val tokens = r[3].replace("※", "/").split('/', ' ').map { it.trim() }.filter { it.isNotEmpty() }
            for ((name, s) in daily) {
                check(name, name in tokens, s in day.senjitsu)
            }
            for ((name, set) in periods) {
                val today = day.senjitsu.any { it in set }
                val yesterday = Rekichu.of(date.minusDays(1)).senjitsu.any { it in set }
                val tomorrow = Rekichu.of(date.plusDays(1)).senjitsu.any { it in set }
                check("$name start", "${name}始まり" in tokens || "${name}入り" in tokens, today && !yesterday)
                check("$name end", "${name}終わり" in tokens, today && !tomorrow)
            }
        }
        println(problems.groupingBy { it.substringAfter(' ').substringBefore(':') }.eachCount())
        problems.take(40).forEach(::println)
        assertTrue(problems.isEmpty(), "${problems.size} differences")
    }
}
