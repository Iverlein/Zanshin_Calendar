/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.kyureki

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import zanshin.core.astro.Astro
import zanshin.core.time.SUPPORTED_RANGE
import zanshin.core.time.julianDayNumber
import zanshin.core.vectors
import java.time.Duration
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.math.abs

class RekichuTest {
    private val naoj = vectors("naoj-2026-2027.tsv")

    private fun jstInstant(date: String, time: String): Instant =
        LocalDateTime.parse("${date}T$time").toInstant(Kyureki.JST)

    @Test
    fun `solar terms and new moons match NAOJ to the minute`() {
        val from = Instant.parse("2025-12-31T00:00:00Z")
        val to = Instant.parse("2028-01-01T00:00:00Z")
        val crossings = Astro.solarLongitudeCrossings(from, to, stepDeg = 15)
        val moons = Astro.newMoonsBetween(from, to)
        for (r in naoj) {
            val expected = jstInstant(r[3], r[4].ifEmpty { continue })
            val actual = when (r[0]) {
                "term" -> crossings.first { it.first == r[2].toInt() && abs(Duration.between(it.second, expected).toDays()) < 2 }.second
                "new_moon" -> moons.minBy { abs(Duration.between(it, expected).seconds) }
                else -> continue
            }
            // NAOJ truncates to the minute.
            val seconds = Duration.between(expected, actual).seconds
            assertTrue(seconds in -60..120, "${r[1]} ${r[3]} ${r[4]}: off by ${seconds}s")
        }
    }

    @Test
    fun `zassetsu fall on the NAOJ dates`() {
        val expectedByName = mapOf(
            "節分" to Zassetsu.SETSUBUN, "彼岸" to Zassetsu.HIGAN_IRI, "土用" to Zassetsu.DOYO_IRI,
            "八十八夜" to Zassetsu.HACHIJUHACHIYA, "入梅" to Zassetsu.NYUBAI, "半夏生" to Zassetsu.HANGESHO,
            "二百十日" to Zassetsu.NIHYAKUTOKA,
        )
        for (r in naoj.filter { it[0] == "zassetsu" }) {
            val day = Rekichu.of(LocalDate.parse(r[3]))
            assertTrue(expectedByName.getValue(r[1]) in day.zassetsu, "${r[1]} on ${r[3]}: got ${day.zassetsu}")
        }
    }

    @Test
    fun `28 lodges follow the weekday and branch table every day`() {
        // Japanese Wikipedia 二十八宿: rows by branch group, columns Mon..Sun.
        val table = mapOf(
            "子辰申" to "畢翼箕奎鬼氐虚",
            "丑巳酉" to "危觜軫斗婁柳房",
            "寅午戌" to "心室参角牛胃星",
            "卯未亥" to "張尾壁井亢女昴",
        )
        var date = SUPPORTED_RANGE.start
        while (date <= SUPPORTED_RANGE.endInclusive) {
            val jd = date.julianDayNumber()
            val branch = Kanshi.ofDay(jd).branch
            val row = table.entries.first { BRANCHES[branch] in it.key }.value
            val expected = row[date.dayOfWeek.value - 1].toString()
            assertEquals(expected, Shuku.entries[Rekichu.shukuIndex(jd)].kanji, "$date")
            date = date.plusDays(1)
        }
    }

    @Test
    fun `28 lodges match the Chinese mansions in Henning's calendars`() {
        val pinyin = listOf(
            "Jiao", "Kang", "Di", "Fang", "Xin", "Wei", "Ji", "Dou", "Niu", "Nu", "Xu", "Wei", "Shi", "Bi",
            "Kui", "Lou", "Wei", "Mao", "Bi", "Zui", "Can", "Jing", "Gui", "Liu", "Xing", "Zhang", "Yi", "Zhen",
        )
        for (r in vectors("henning-phugpa.tsv")) {
            val jd = LocalDate.parse(r[0]).julianDayNumber()
            assertEquals(r[13], pinyin[Rekichu.shukuIndex(jd)], r[0])
        }
    }

    @Test
    fun `day-star leaps fall where Japanese Wikipedia lists them`() {
        // 九星 article, main list (the variant positions in parentheses are other schools').
        val expected = listOf(
            "1905冬", "1916冬", "1928夏", "1939冬", "1951夏", "1962冬", "1974夏", "1985冬", "1997夏",
            "2008冬", "2019冬", "2031冬", "2042冬", "2054夏", "2065冬", "2077夏", "2088冬", "2100夏",
        )
        val actual = Rekichu.dayStarLeaps(1900, 2101).map { switch ->
            if (switch.monthValue in 5..8) "${switch.year}夏"
            else "${if (switch.monthValue == 1) switch.year - 1 else switch.year}冬"
        }.filter { it.take(4).toInt() in 1900..2100 }
        assertEquals(expected, actual)
    }

    @Test
    fun `year stars and lucky directions`() {
        assertEquals(KyuSei.IPPAKU, Rekichu.yearStar(2008))
        assertEquals(KyuSei.JIKOKU, Rekichu.yearStar(2007))
        assertEquals(KyuSei.SANPEKI, Rekichu.yearStar(1997))
        assertEquals(Ehou.SOUTH_SOUTHEAST, Rekichu.ehouOf(2026))
        assertEquals(Ehou.NORTH_NORTHWEST, Rekichu.ehouOf(2027))
    }

    @Test
    fun `twelve stations restart on the branch of the solar month`() {
        var date = LocalDate.of(2026, 1, 1)
        while (date.year == 2026) {
            val (branch, start) = Rekichu.setsuOf(date)
            val choku = Rekichu.chokuOf(date)
            if (date == start) assertEquals(Rekichu.chokuOf(date.minusDays(1)), choku, "$date repeats")
            else if (Kanshi.ofDay(date.julianDayNumber()).branch == branch) assertEquals(Choku.TATSU, choku, "$date")
            date = date.plusDays(1)
        }
    }

    /**
     * The 選日 of Japanese Wikipedia 選日 (一覧) and koyomi8 rekicyuu_doc02; the
     * 縁日 of Japanese Wikipedia 縁日 (甲子 of Daikokuten, 己巳 of Benzaiten, 庚申
     * of Taishakuten and Shōmen Kongō, 寅の日 of Bishamonten, and 巳の日 as the
     * day of the sexagenary cycle behind 初巳); every other entry is 暦注下段
     * (Wikipedia 暦注下段, koyomi8 rekicyuu_doc03).
     */
    @Test
    fun `selected days are split between the lower band, the selected-day column and the deity days`() {
        val senjitsu = setOf(
            Senjitsu.ICHIRYU_MANBAI, Senjitsu.FUJOJU, Senjitsu.HASSEN, Senjitsu.HASSEN_MABI, Senjitsu.JIPPOGURE,
            Senjitsu.TENICHI_TENJO, Senjitsu.SANRINBO, Senjitsu.OTSUCHI, Senjitsu.KOTSUCHI, Senjitsu.TSUCHI_MABI,
        )
        val ennichi = setOf(Senjitsu.TORA, Senjitsu.MI, Senjitsu.TSUCHINOTO_MI, Senjitsu.KINOE_NE, Senjitsu.KANOE_SARU)
        Senjitsu.entries.forEach {
            val expected = when (it) {
                in senjitsu -> Band.SENJITSU
                in ennichi -> Band.ENNICHI
                else -> Band.KAGEDAN
            }
            assertEquals(expected, it.band, it.kanji)
        }
    }

    @Test
    fun `the black day and the pardon day carry their marks, the black day first`() {
        var date = LocalDate.of(2026, 1, 1)
        var black = 0
        var pardon = 0
        while (date.year == 2026) {
            val day = Rekichu.of(date)
            val expected = when {
                Senjitsu.JUSHI in day.senjitsu -> DayMark.BLACK
                Senjitsu.TENSHA in day.senjitsu -> DayMark.PARDON
                else -> null
            }
            assertEquals(expected, day.mark, "$date")
            if (day.mark == DayMark.BLACK) black++
            if (day.mark == DayMark.PARDON) pardon++
            date = date.plusDays(1)
        }
        // 天赦日 falls five or six times a year (Japanese Wikipedia 暦注下段).
        assertTrue(pardon in 5..6, "$pardon pardon days")
        assertTrue(black > 0)
    }
}
