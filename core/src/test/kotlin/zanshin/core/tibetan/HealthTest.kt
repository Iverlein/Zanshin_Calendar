/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import java.time.LocalDate

/** The Health screen's reckonings (SPEC §10.10; docs/sources/health.md, personal-mansions.md). */
class HealthTest {
    /**
     * The tables of pp. 345–346 (img. 353–354), «…ཁམས་པའི་བླ་སྐར་སོགས་ངོས་འཛིན», read cell by cell on the scan: the
     * mansions by number of the own, mother, friend, child and enemy columns, then the great and small grave mansion.
     * Earth's 21 is Abhijit (WB vol. 1, p. 232), which no day falls on; the western six's 21 is Śravaṇa.
     */
    private val table = mapOf(
        Element.WOOD to listOf("2 3 4 5 6 7", "22 23 24 25 26 0", "1 8 15 21", "9 10 11 12 13 14", "16 17 18 19 20 21", "15", "1"),
        Element.FIRE to listOf("9 10 11 12 13 14", "2 3 4 5 6 7", "16 17 18 19 20 21", "1 8 15 21", "22 23 24 25 26 0", "21", "8"),
        Element.EARTH to listOf("1 8 15 21", "9 10 11 12 13 14", "22 23 24 25 26 0", "16 17 18 19 20 21", "2 3 4 5 6 7", "8", "21"),
        Element.IRON to listOf("16 17 18 19 20 21", "1 8 15 21", "2 3 4 5 6 7", "22 23 24 25 26 0", "9 10 11 12 13 14", "1", "15"),
        Element.WATER to listOf("22 23 24 25 26 0", "16 17 18 19 20 21", "9 10 11 12 13 14", "2 3 4 5 6 7", "1 8 15 21", "8", "21"),
    )

    @Test
    fun `the element's quarters follow the tables of pp 345-346`() {
        for ((element, cells) in table) {
            val expected = mutableMapOf<Mansion, ElementQuarter>()
            ElementQuarter.entries.zip(cells).forEach { (quarter, cell) ->
                // Earth's four print 21 for Abhijit: Śravaṇa, 21 among the days, is the western six's.
                val earths = cell.split(" ").size == 4
                cell.split(" ").map { it.toInt() }.filter { !(earths && it == 21) }.forEach { expected[Mansion.entries[it]] = quarter }
            }
            assertEquals(Mansion.entries.toSet(), expected.keys, "$element: every day's mansion in one quarter")
            assertEquals(expected.toMap(), Mansion.entries.associateWith { Health.quarter(element, it) }, "$element")
            val grave = Health.graveMansions(element)
            listOf(GraveMansion.GREAT to cells[5], GraveMansion.SMALL to cells[6]).forEach { (g, n) ->
                assertEquals(n.toInt().takeIf { it != 21 }?.let { Mansion.entries[it] }, grave[g], "$element $g")
            }
        }
    }

    /** WB vol. 1, p. 253: the la is the mother of the life force, the element that gives birth to it. */
    @Test
    fun `the la is the mother of the life force`() {
        assertEquals(
            mapOf(Element.WOOD to Element.WATER, Element.FIRE to Element.WOOD, Element.EARTH to Element.FIRE, Element.IRON to Element.EARTH, Element.WATER to Element.IRON),
            Element.entries.associateWith(Health::laElement),
        )
    }

    /** WB vol. 2, pp. 297–302: the 9th as the 1st, the 16th as the 8th, the 17th as the 1st, the 24th as the 8th, the 30th as the 6th. */
    @Test
    fun `the dates' illness goes round in eight`() {
        assertEquals((1..8).toList(), (1..8).map(Health::illnessDate))
        assertEquals(listOf(1, 8, 1, 8, 1, 6), listOf(9, 16, 17, 24, 25, 30).map(Health::illnessDate))
        assertEquals(setOf(8, 15, 22, 30), Health.PERILOUS_DATES)
    }

    /**
     * The test birth date, 1 June 1976, Fire Dragon year: life force earth, la fire. Its own mansions are Bharaṇī,
     * Āśleṣā and Viśākhā (and Abhijit); Āśleṣā is its great grave mansion, its small one Abhijit, which no day has.
     */
    @Test
    fun `the test birth date's mansions`() {
        val birth = TibetanCalendar.of(LocalDate.of(1976, 6, 1))
        val element = ownElement(birth)
        assertEquals(Element.EARTH, element)
        assertEquals(Element.FIRE, Health.laElement(element))
        assertEquals(ElementQuarter.OWN, Health.quarter(element, Mansion.ASHLESHA))
        assertEquals(GraveMansion.GREAT, Health.grave(element, Mansion.ASHLESHA))
        assertEquals(ElementQuarter.CHILD, Health.quarter(element, Mansion.SHRAVANA))
        assertNull(Health.grave(element, Mansion.SHRAVANA))
        assertEquals(ElementQuarter.ENEMY, Health.quarter(element, Mansion.KRITTIKA))
        assertEquals(ElementQuarter.FRIEND, Health.quarter(element, Mansion.ASHVINI))
        assertEquals(ElementQuarter.MOTHER, Health.quarter(element, Mansion.MAGHA))
        // 15–24 October 2026: the days' mansions and what each is to the test person.
        val days = (15..24).map { TibetanCalendar.of(LocalDate.of(2026, 10, it)) }
        assertEquals(EXPECTED_MANSIONS, days.map { it.mansion })
        assertEquals(EXPECTED_QUARTERS, days.map { Health.quarter(element, it.mansion) })
    }

    private companion object {
        // Anurādhā to Śravaṇa (16–21) are iron's, the child of earth; Dhaniṣṭhā to Pūrvabhādrapadā (22–24) water's, its friend.
        val EXPECTED_MANSIONS = listOf(
            Mansion.ANURADHA, Mansion.JYESHTHA, Mansion.JYESHTHA, Mansion.MULA, Mansion.PURVASHADHA,
            Mansion.UTTARASHADHA, Mansion.SHRAVANA, Mansion.DHANISHTHA, Mansion.SHATABHISHAJ, Mansion.PURVABHADRAPADA,
        )
        val EXPECTED_QUARTERS = List(7) { ElementQuarter.CHILD } + List(3) { ElementQuarter.FRIEND }
    }
}
