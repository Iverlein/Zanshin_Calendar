/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDate

/** A person's own weekdays and mansion by the birth date, WB vol. 2, pp. 330, 338 and 346 (docs/sources/personal-mansions.md). */
class OwnDayTest {
    /** The weekday table of p. 346, «གཟའི་བླ་གཟའ་སོགས་ངོས་འཛིན», read cell by cell on the scan (img. 354): own, mother, friend, child, enemy. */
    private val table = mapOf(
        Element.WOOD to listOf("THURSDAY", "MONDAY WEDNESDAY", "SATURDAY", "SUNDAY TUESDAY", "FRIDAY"),
        Element.FIRE to listOf("SUNDAY TUESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "MONDAY WEDNESDAY"),
        Element.EARTH to listOf("SATURDAY", "SUNDAY TUESDAY", "MONDAY WEDNESDAY", "FRIDAY", "THURSDAY"),
        Element.IRON to listOf("FRIDAY", "SATURDAY", "THURSDAY", "MONDAY WEDNESDAY", "SUNDAY TUESDAY"),
        Element.WATER to listOf("MONDAY WEDNESDAY", "FRIDAY", "SUNDAY TUESDAY", "THURSDAY", "SATURDAY"),
    )
    private val columns = listOf(OwnDay.OWN_WEEKDAY, OwnDay.MOTHER_WEEKDAY, OwnDay.FRIEND_WEEKDAY, OwnDay.CHILD_WEEKDAY, OwnDay.ENEMY_WEEKDAY)

    @Test
    fun `the element's weekdays follow the table of p 346`() {
        for ((element, cells) in table) {
            val expected = columns.zip(cells).flatMap { (role, days) -> days.split(" ").map { Weekday.valueOf(it) to role } }.toMap()
            assertEquals(expected, Weekday.entries.associateWith { elementWeekday(element, it) }, "$element")
        }
    }

    /** The life force by animal (Gyurme Dorje's table, as `Forces.of` has it) gives each animal's gshed gza' of p. 330 among its enemy weekdays. */
    @Test
    fun `the birth animal's anti day is an enemy weekday of its life force`() {
        for (animal in Animal.entries) {
            val anti = Weekday.entries.single { personalDay(animal, it) == PersonalDay.ANTI }
            assertEquals(OwnDay.ENEMY_WEEKDAY, elementWeekday(Forces.of(Element.WOOD, animal).vitality, anti), "$animal")
        }
    }

    /**
     * The test birth date, 1 June 1976: a Tuesday of the Fire Dragon year, whose life force is
     * earth; so Saturday is the own weekday, Sunday and Tuesday the mother's, Monday and
     * Wednesday the friend's, Friday the child's, Thursday the enemy's.
     */
    @Test
    fun `the test birth date's own days`() {
        val birth = TibetanCalendar.of(LocalDate.of(1976, 6, 1))
        assertEquals(Weekday.TUESDAY, birth.weekday)
        assertEquals(Element.EARTH, ownElement(birth))
        // 4–10 October 2026, Sunday to Saturday.
        val week = (4..10).map { TibetanCalendar.of(LocalDate.of(2026, 10, it)) }
        assertEquals(
            listOf(
                listOf(OwnDay.MOTHER_WEEKDAY),
                listOf(OwnDay.FRIEND_WEEKDAY),
                listOf(OwnDay.BIRTH_WEEKDAY, OwnDay.MOTHER_WEEKDAY),
                listOf(OwnDay.FRIEND_WEEKDAY),
                listOf(OwnDay.ENEMY_WEEKDAY),
                listOf(OwnDay.CHILD_WEEKDAY),
                listOf(OwnDay.OWN_WEEKDAY),
            ),
            week.map { d -> ownDays(birth, d).filter { it.isWeekday } },
        )
        // The birth mansion, lag (Ārdrā), on the days whose mansion is the birth date's: 12 in 2026.
        val days = (0L until 366).map { TibetanCalendar.of(LocalDate.of(2026, 1, 1).plusDays(it)) }
        val returns = days.filter { OwnDay.BIRTH_MANSION in ownDays(birth, it) }
        assertEquals(days.filter { it.mansion == birth.mansion }, returns)
        assertEquals(Mansion.ARDRA, birth.mansion)
        assertEquals(12, returns.size)
    }
}
