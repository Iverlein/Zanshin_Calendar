/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/** The moment's trigram and mewa (ROADMAP R2o; docs/sources/reckonings.md, question 164). */
class MomentTest {
    /**
     * O16's check: counted one a date, a month of thirty ends where the next month's group begins, round the year: a
     * tiger month starting on li ends on gin and the hare month starts on zin; a first month starting on 1 ends on 3
     * and the middle month starts on 4.
     */
    @Test
    fun `the months' starts follow from one a date`() {
        val year = listOf(Animal.TIGER, Animal.RABBIT, Animal.DRAGON, Animal.SNAKE, Animal.HORSE, Animal.SHEEP, Animal.MONKEY, Animal.BIRD, Animal.DOG, Animal.PIG, Animal.MOUSE, Animal.OX)
        assertEquals(Trigram.GIN, MomentReckoning.dateTrigram(Animal.TIGER, 30))
        year.zipWithNext().forEach { (a, b) ->
            assertEquals(MomentReckoning.monthTrigram(b), Trigram.entries[(MomentReckoning.dateTrigram(a, 30).ordinal + 1) % 8], "$a $b")
            assertEquals(MomentReckoning.monthMewa(b), MomentReckoning.dateMewa(a, 30) % 9 + 1, "$a $b")
        }
    }

    /** P. 162's rough spans: eight trigrams in the twelve hours of day, nine mewa, and the night starting again on the date's. */
    @Test
    fun `the day's spans`() {
        assertEquals(Trigram.LI, MomentReckoning.trigram(Animal.TIGER, 1, 0))
        assertEquals(Trigram.KHON, MomentReckoning.trigram(Animal.TIGER, 1, 90))
        assertEquals(Trigram.ZON, MomentReckoning.trigram(Animal.TIGER, 1, 719))
        assertEquals(Trigram.LI, MomentReckoning.trigram(Animal.TIGER, 1, 720))
        assertEquals(1, MomentReckoning.mewa(Animal.TIGER, 1, 79))
        assertEquals(2, MomentReckoning.mewa(Animal.TIGER, 1, 80))
        assertEquals(9, MomentReckoning.mewa(Animal.TIGER, 1, 719))
        assertEquals(1, MomentReckoning.mewa(Animal.TIGER, 1, 1439 - 640))
    }

    /** P. 156's seven headings on the hours from the hare: dawn at 05:00, sunset in the bird's hour, none after 19:00. */
    @Test
    fun `the periods of the day`() {
        assertEquals(MomentPeriod.DAWN, MomentReckoning.period(0))
        assertEquals(MomentPeriod.NOON, MomentReckoning.period(6 * 60 + 30))
        assertEquals(MomentPeriod.SUNSET, MomentReckoning.period(12 * 60 + 59))
        assertEquals(null, MomentReckoning.period(14 * 60))
    }
}
