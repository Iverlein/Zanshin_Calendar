/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import zanshin.core.tibetan.Gender
import zanshin.core.tibetan.Sign
import zanshin.core.tibetan.TibetanCalendar
import zanshin.core.tibetan.YearReckoning
import java.time.LocalDate

/** SPEC §10.11: the Reckonings section's list, and the year of age it steps by Tibetan year. */
class ReckoningsTest {
    private val person = Person("", LocalDate.of(1976, 5, 20), Gender.FEMALE)

    @Test
    fun `the list needs a person`() {
        assertEquals(emptyList<Reckoning>(), Reckonings.built(null))
        assertEquals(listOf(Reckoning.YEAR_OF_AGE), Reckonings.built(person))
    }

    @Test
    fun `the year of age starts in the birth year`() {
        val born = Reckonings.birthYear(person)
        assertEquals(1976, born)
        assertNull(Reckonings.yearOfAge(person, born - 1, null))
        assertEquals(1, Reckonings.yearOfAge(person, born, null)!!.age)
        assertEquals(51, Reckonings.yearOfAge(person, 2026, null)!!.age)
    }

    @Test
    fun `a year stepped to reckons as the year's balloon does from a day in it`() {
        // Every tenth day over four years, which takes in each new year's eve and day within ten days.
        val born = TibetanCalendar.of(person.birth)
        var date = LocalDate.of(2025, 1, 1)
        while (date < LocalDate.of(2029, 1, 1)) {
            val day = TibetanCalendar.of(date)
            val fromDay = YearReckoning(Sign(born.yearElement, born.yearAnimal), born.year, Sign(day.yearElement, day.yearAnimal), day.year, person.gender, null)
            assertEquals(fromDay, Reckonings.yearOfAge(person, day.year, null), "$date")
            date = date.plusDays(10)
        }
    }
}
