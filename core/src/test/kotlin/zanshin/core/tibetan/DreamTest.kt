/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import zanshin.core.astro.Place
import zanshin.core.kyureki.Tone
import zanshin.core.texts.Catalog
import zanshin.core.texts.Texts

/** The dream reading (ROADMAP S2, block 73; SPEC §5.9.11). */
class DreamTest {
    private val lhasa = Place(29.65, 91.1, ZoneId.of("Asia/Shanghai"))

    @Test
    fun `the night before a summer and a winter day in Lhasa is cut in four equal quarters`() {
        for (date in listOf(LocalDate.of(2026, 6, 21), LocalDate.of(2026, 12, 21))) {
            val night = DreamNight.before(date, lhasa)!!
            assertEquals(date.minusDays(1), night.sunset.toLocalDate())
            assertEquals(date, night.sunrise.toLocalDate())
            val quarters = (0..4).map { night.quarter(it) }
            assertEquals(night.sunset, quarters.first())
            assertEquals(night.sunrise, quarters.last())
            val lengths = quarters.zipWithNext { a, b -> java.time.Duration.between(a, b).seconds }
            assertTrue(lengths.max() - lengths.min() <= 120, "$date: $lengths")
            for (n in 1..3) assertEquals(0, quarters[n].second, "an inner bound is a whole minute")
            assertEquals(DreamWatch.DUSK, night.watch(night.sunset))
            assertEquals(DreamWatch.DUSK, night.watch(quarters[1].minusMinutes(1)))
            assertEquals(DreamWatch.MIDDLE, night.watch(quarters[1]))
            assertEquals(DreamWatch.MIDDLE, night.watch(quarters[3].minusMinutes(1)))
            assertEquals(DreamWatch.DAWN, night.watch(quarters[3]))
            assertEquals(DreamWatch.DAWN, night.watch(night.sunrise.minusMinutes(1)))
            assertNull(night.watch(night.sunrise))
            assertNull(night.watch(night.sunset.minusMinutes(1)))
        }
        // A winter night is longer than a summer one.
        val summer = DreamNight.before(LocalDate.of(2026, 6, 21), lhasa)!!
        val winter = DreamNight.before(LocalDate.of(2026, 12, 21), lhasa)!!
        assertTrue(java.time.Duration.between(winter.sunset, winter.sunrise) > java.time.Duration.between(summer.sunset, summer.sunrise))
    }

    @Test
    fun `a clock time falls on the evening before midnight and on the morning after`() {
        val night = DreamNight.before(LocalDate.of(2026, 12, 21), lhasa)!!
        assertEquals(LocalDate.of(2026, 12, 20), night.at(LocalTime.of(23, 30)).toLocalDate())
        assertEquals(LocalDate.of(2026, 12, 21), night.at(LocalTime.of(3, 0)).toLocalDate())
        assertEquals(DreamWatch.MIDDLE, night.watch(night.at(LocalTime.of(0, 30))))
        assertEquals(DreamWatch.DAWN, night.watch(night.at(night.sunrise.toLocalTime().minusMinutes(20))))
        assertNull(night.watch(night.at(LocalTime.NOON)))
        // The clock time shown for a bound is the first minute of its quarter.
        for ((n, w) in listOf(1 to DreamWatch.MIDDLE, 3 to DreamWatch.DAWN)) {
            val shown = night.quarter(n).toLocalTime()
            assertEquals(w, night.watch(night.at(shown)))
            assertEquals(if (n == 1) DreamWatch.DUSK else DreamWatch.MIDDLE, night.watch(night.at(shown.minusMinutes(1))))
        }
    }

    @Test
    fun `only a dawn dream is read, and no reading gives a fruit date or span`() {
        val images = listOf(DreamImage.RIDING_UPWARD, DreamImage.PIT_FALL_CHASM, DreamImage.SUN_RISING)
        for (w in listOf(DreamWatch.DUSK, DreamWatch.MIDDLE)) {
            val r = DreamReading(w, ill = false, images)
            assertTrue(r.verdicts.isEmpty())
            assertTrue(r.remedies.isEmpty())
        }
        val dawn = DreamReading(DreamWatch.DAWN, ill = false, images)
        assertEquals("DreamNight.DAWN", dawn.lineKey)
        assertTrue(dawn.verdicts.isNotEmpty())
        // WB first, the texts in their order, and each image keeps its place within a text.
        assertEquals(DreamText.WB, dawn.verdicts.first().text)
        assertEquals(dawn.verdicts.map { it.text }.sorted(), dawn.verdicts.map { it.text })
        // No span of days, months or a year in any verdict or line (the owner, 2026-11-10).
        val span = Regex("(?i)\\bwithin\\b|\\b(?:days|months?|years?)\\b|\\bhalf a month\\b")
        val texts = DreamImage.entries.flatMap { it.verdicts }.map { it.key } + DreamWatch.entries.map { "DreamNight.${it.name}" }
        for (k in texts) assertNull(span.find(Catalog.text(k, java.util.Locale.ENGLISH)), k)
    }

    @Test
    fun `with the dreamer ill the Four Tantras' rule of the night stands and their readings join`() {
        val images = listOf(DreamImage.SUN_MOON_DARKENED, DreamImage.HEAD_BROKEN)
        val well = DreamReading(DreamWatch.DAWN, ill = false, images)
        assertTrue(well.verdicts.none { it.text == DreamText.ST })
        assertTrue(well.verdicts.none { it.image == DreamImage.HEAD_BROKEN }, "the images of the sick are not read for one who is well")
        val ill = DreamReading(DreamWatch.DAWN, ill = true, images)
        assertEquals("DreamNight.ILL", ill.lineKey)
        assertEquals("DreamNight.ILL", DreamReading(DreamWatch.DUSK, ill = true, images).lineKey)
        assertTrue(ill.verdicts.any { it.text == DreamText.ST && it.image == DreamImage.HEAD_BROKEN })
        assertTrue(ill.verdicts.any { it.text == DreamText.ST && it.image == DreamImage.SUN_MOON_DARKENED })
        assertTrue(DreamRemedy.ST in ill.remedies)
        assertTrue(DreamRemedy.ST !in well.remedies)
        assertTrue(DreamImage.offered(ill = false).keys.none { it == DreamGroup.ILL })
        assertTrue(DreamImage.offered(ill = true).containsKey(DreamGroup.ILL))
    }

    @Test
    fun `a bad image brings its texts' remedies once, a good one none`() {
        val good = DreamReading(DreamWatch.DAWN, ill = false, listOf(DreamImage.RIDING_UPWARD))
        assertTrue(good.verdicts.all { it.tone == Tone.GOOD || it.tone == Tone.NEUTRAL })
        assertTrue(good.remedies.isEmpty())
        val bad = DreamReading(DreamWatch.DAWN, ill = false, listOf(DreamImage.BEASTS_CHASING, DreamImage.RIDING_DONKEY_BARE))
        assertEquals(listOf(DreamRemedy.WB, DreamRemedy.LGD, DreamRemedy.SVO, DreamRemedy.SVI), bad.remedies)
    }

    @Test
    fun `every image and verdict has its text in English and Russian`() {
        val en = Catalog.entries("")
        val ru = Catalog.entries("_ru")
        val missing = mutableListOf<String>()
        val keys = DreamImage.entries.map { "DreamImage.${it.name}" } + DreamImage.entries.flatMap { it.verdicts }.map { it.key } +
            DreamWatch.entries.flatMap { listOf("DreamWatch.${it.name}", "DreamNight.${it.name}") } + "DreamNight.ILL" +
            DreamGroup.entries.map { "DreamGroup.${it.name}" } + DreamRemedy.entries.map { it.key }
        for (k in keys) {
            if (en[k].isNullOrBlank()) missing += "en $k"
            if (ru[k].isNullOrBlank()) missing += "ru $k"
        }
        val known = keys.toSet()
        for (k in en.keys) if ((k.startsWith("Dream") || k.startsWith("DreamVerdict")) && k !in known) missing += "unused $k"
        assertEquals("", missing.joinToString("\n"))
        // Every image is read by some text, and an image any dreamer is offered has a row for one who is well.
        for (i in DreamImage.entries) {
            assertTrue(i.verdicts.isNotEmpty(), "$i")
            if (i.group != DreamGroup.ILL) assertTrue(i.verdicts.any { !it.text.medical }, "$i")
            else assertTrue(i.verdicts.all { it.text.medical }, "$i")
        }
        assertEquals(DreamText.entries.toSet(), Texts.DREAM_SOURCES.keys)
    }
}
