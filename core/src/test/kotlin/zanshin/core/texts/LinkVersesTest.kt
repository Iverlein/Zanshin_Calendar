/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.texts

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import zanshin.core.kyureki.Tone
import zanshin.core.tibetan.Link
import zanshin.core.tibetan.Link.*

/** The White Beryl's days of the twelve links (vol. 2, pp. 351–356; docs/sources/day-letters.md) and their join to the link's lists. */
class LinkVersesTest {
    private fun side(link: Link, wording: String): Tone? {
        val (good, avoid) = LinkVerses.LISTS.getValue(link)
        return when (wording) {
            in good -> Tone.GOOD
            in avoid -> Tone.BAD
            else -> null
        }
    }

    @Test
    fun `every verse names washing the head, washing the body and new clothes as the scan reads them`() {
        val g = Tone.GOOD
        val b = Tone.BAD
        // Head, body, clothes, read link by link on the scan (img. 359–364).
        val expected = mapOf(
            IGNORANCE to listOf(g, g, b),
            FORMATION to listOf(b, g, b),
            CONSCIOUSNESS to listOf(g, b, g),
            NAME_AND_FORM to listOf(b, b, b),
            SOURCES to listOf(g, b, g),
            CONTACT to listOf(g, b, b),
            FEELING to listOf(b, b, b),
            CRAVING to listOf(b, b, b),
            GRASPING to listOf(g, g, b),
            BECOMING to listOf(g, g, b),
            BIRTH to listOf(g, g, b),
            AGEING_AND_DEATH to listOf(g, b, b),
        )
        assertEquals(Link.entries.toSet(), LinkVerses.LISTS.keys)
        for ((link, sides) in expected) {
            assertEquals(sides, listOf("washing_the_hair", "bathing", "wearing_new_clothes").map { side(link, it) }, "$link")
        }
    }

    @Test
    fun `the verses' works stand where the scan puts them`() {
        // A work from each verse, on its side (docs/sources/day-letters.md, *WB's days of the twelve links*).
        assertEquals(Tone.GOOD, side(IGNORANCE, "stupas"))
        assertEquals(Tone.BAD, side(IGNORANCE, "meeting_great_people"))
        assertEquals(Tone.GOOD, side(FORMATION, "learning_to_write"))
        assertEquals(Tone.GOOD, side(CONSCIOUSNESS, "destroying_towns"))
        assertEquals(Tone.GOOD, side(NAME_AND_FORM, "cutting_hair_and_nails"))
        assertEquals(Tone.BAD, side(SOURCES, "prostrations"))
        assertEquals(Tone.BAD, side(CONTACT, "crafts"))
        assertEquals(Tone.GOOD, side(FEELING, "crossing_water"))
        assertEquals(Tone.BAD, side(FEELING, "shaving"))
        assertEquals(Tone.GOOD, side(CRAVING, "a_bride"))
        assertEquals(Tone.GOOD, side(GRASPING, "cutting_the_beard"))
        assertEquals(Tone.BAD, side(BECOMING, "fights_and_disputes"))
        assertEquals(Tone.GOOD, side(BIRTH, "affairs_of_state"))
        assertEquals(Tone.BAD, side(AGEING_AND_DEATH, "divination"))
    }

    @Test
    fun `no verse names a work both ways`() {
        for ((link, lists) in LinkVerses.LISTS) {
            val (good, avoid) = lists
            assertEquals(emptySet<Activity>(), Activities.of(good) intersect Activities.of(avoid), "$link")
        }
    }

    @Test
    fun `the link's lists take the verse first and drop the activity lists' entries it names the other way`() {
        for (link in Link.entries) {
            val (verseGood, verseAvoid) = LinkVerses.LISTS.getValue(link)
            val reading = Texts.TWELVE_LINK.getValue(link)
            assertEquals(verseGood, reading.goodKeys.take(verseGood.size), "$link")
            assertEquals(verseAvoid, reading.avoidKeys.take(verseAvoid.size), "$link")
            assertTrue(Activities.of(reading.goodKeys).none { it in Activities.of(verseAvoid) }, "$link")
            assertTrue(Activities.of(reading.avoidKeys).none { it in Activities.of(verseGood) }, "$link")
            assertEquals(Sources.WHITE_BERYL_LINKS, reading.source)
            // Each reading carries the directions, the remedy, the illness (block 26) and the birth as rows, in the rows' order.
            assertEquals(listOf(ReadingRow.SETTING_OUT, ReadingRow.REMEDY, ReadingRow.ILLNESS, ReadingRow.BIRTH), reading.rows.map { it.first }, "$link")
        }
        // The sense sources' day: the lists' consecration to avoid (box 49) stays, the verse naming it neither way.
        assertTrue("consecration" in Texts.TWELVE_LINK.getValue(SOURCES).avoidKeys)
    }
}
