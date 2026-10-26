/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import java.time.LocalDate
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import zanshin.core.tibetan.Animal.DOG
import zanshin.core.tibetan.Animal.DRAGON
import zanshin.core.tibetan.Animal.HORSE
import zanshin.core.tibetan.Animal.MONKEY
import zanshin.core.tibetan.Animal.MOUSE
import zanshin.core.tibetan.Animal.OX
import zanshin.core.tibetan.Animal.PIG
import zanshin.core.tibetan.Animal.RABBIT
import zanshin.core.tibetan.Animal.SHEEP
import zanshin.core.tibetan.Animal.SNAKE
import zanshin.core.tibetan.Animal.TIGER
import zanshin.core.tibetan.Direction.EAST
import zanshin.core.tibetan.Direction.NORTH
import zanshin.core.tibetan.Direction.SOUTH
import zanshin.core.tibetan.Direction.WEST

/**
 * The year's and the month's earth lords (WB vol. 2, ch. 31, pp. 174–223; docs/sources/year-month-earth-lords.md, RD3,
 * RD4): the twelve month heads of WB's model almanac (vol. 1, pp. 154–171, read on the scan), the year's move at the
 * solstice (question 34) and the courses as the chapter words them.
 */
class YearMonthEarthLordsTest {
    private fun direction(lord: MonthLord, m: Animal): Direction =
        (YearMonthEarthLords.seats(lord, m).single().place as LordPlace.InDirection).direction

    private fun animal(lord: YearLord, year: Int): Animal =
        (YearMonthEarthLords.seats(lord, year).single().place as LordPlace.OnAnimal).animal

    /** The model almanac's heads from Hor month 11, the tiger month: *bum stong*, the five gods and the nine. */
    @Test
    fun modelAlmanacMonthHeads() {
        val heads = listOf(
            TIGER to Triple(EAST, EAST, WEST), RABBIT to Triple(SOUTH, SOUTH, SOUTH), DRAGON to Triple(WEST, WEST, EAST),
            SNAKE to Triple(NORTH, NORTH, NORTH), HORSE to Triple(EAST, EAST, WEST), SHEEP to Triple(SOUTH, SOUTH, SOUTH),
            MONKEY to Triple(WEST, WEST, EAST), Animal.BIRD to Triple(NORTH, NORTH, NORTH), DOG to Triple(EAST, EAST, WEST),
            PIG to Triple(SOUTH, SOUTH, SOUTH), MOUSE to Triple(WEST, WEST, EAST), OX to Triple(NORTH, NORTH, NORTH),
        )
        for ((m, h) in heads) {
            assertEquals(h.first, direction(MonthLord.EMPTY_VASE, m), "bum stong, $m")
            assertEquals(h.second, direction(MonthLord.FIVE_GODS, m), "lha lnga, $m")
            assertEquals(h.third, direction(MonthLord.NINE_GODS, m), "lha dgu, $m")
        }
    }

    @Test
    fun theSolsticeInLhasa() {
        assertEquals(LocalDate.of(2025, 12, 21), YearMonthEarthLords.winterSolstice(2025))
        assertEquals(LocalDate.of(2026, 12, 22), YearMonthEarthLords.winterSolstice(2026))
    }

    /** Question 34: each lord takes the new year's place on its own day counted from the solstice, not at Losar. */
    @Test
    fun theYearsLordsMoveAtTheSolstice() {
        val solstice = YearMonthEarthLords.winterSolstice(2025)
        assertEquals(2026 to solstice, YearMonthEarthLords.lordYear(YearLord.TESE, solstice))
        assertEquals(2025, YearMonthEarthLords.lordYear(YearLord.TESE, solstice.minusDays(1)).first)
        // Hangphen 24 days before the solstice, Kala 124 after it, past Losar.
        assertEquals(2026, YearMonthEarthLords.lordYear(YearLord.HANGPHEN, solstice.minusDays(24)).first)
        assertEquals(2025, YearMonthEarthLords.lordYear(YearLord.NB_KALA, LocalDate.of(2026, 3, 1)).first)
        assertEquals(2026, YearMonthEarthLords.lordYear(YearLord.NB_KALA, solstice.plusDays(124)).first)
    }

    @Test
    fun theYearsCourses() {
        // 2026 the fire horse: the king on the year's own animal, the queen two behind, the son on the triad's wind-horse.
        assertEquals(HORSE, YearMonthEarthLords.yearAnimal(2026))
        assertEquals(HORSE, animal(YearLord.TESE, 2026))
        assertEquals(DRAGON, animal(YearLord.TEKHYIM, 2026))
        assertEquals(MONKEY, animal(YearLord.TESO, 2026))
        // The *se ba bla mkhyen*'s outer place, the seventh; the sweepers on the downward four-slayer.
        assertEquals(MOUSE, animal(YearLord.SEBA_LAKHYEN, 2026))
        assertEquals(RABBIT, animal(YearLord.KUNGNYUL, 2026))
        // The hal khyi on the previous year's (the snake's) wind-horse, the pig, with the king's daughter.
        assertEquals(PIG, animal(YearLord.HAL_KHYI, 2026))
        assertEquals(PIG, animal(YearLord.KARSHAM, 2026))
    }

    /** The black years are those whose sme ba is the two-black, every one of them a corner year (question 40). */
    @Test
    fun theBlackYears() {
        for (year in 1900..2100) {
            val black = YearMonthEarthLords.seats(YearLord.BLACK_YEAR, year).isNotEmpty()
            assertEquals(YearOfLife.yearSmeBa(year) == 2, black, "$year")
            if (black) assertTrue(YearMonthEarthLords.yearAnimal(year) in setOf(TIGER, MONKEY, PIG, SNAKE), "$year")
        }
    }

    @Test
    fun theMonthsCourses() {
        // The month itself on its animal, Piling on the seventh, Zinphung on the upward four-slayer, the slayer the downward.
        assertEquals(LordPlace.OnAnimal(TIGER), YearMonthEarthLords.seats(MonthLord.MONTH_ITSELF, TIGER).single().place)
        assertEquals(LordPlace.OnAnimal(MONKEY), YearMonthEarthLords.seats(MonthLord.PILING, TIGER).single().place)
        assertEquals(LordPlace.OnAnimal(SNAKE), YearMonthEarthLords.seats(MonthLord.ZINPHUNG, TIGER).single().place)
        assertEquals(LordPlace.OnAnimal(PIG), YearMonthEarthLords.seats(MonthLord.SLAYER, TIGER).single().place)
        // The month's hal khyi on the wind-horse of the month before: in the horse month, the snake's triad's pig.
        assertEquals(LordPlace.OnAnimal(PIG), YearMonthEarthLords.seats(MonthLord.HAL_KHYI, HORSE).single().place)
        // Lagchen has no seat in the horse month; the guard dog sits in four months only.
        assertTrue(YearMonthEarthLords.seats(MonthLord.LAGCHEN, HORSE).isEmpty())
        assertEquals(4, Animal.entries.count { YearMonthEarthLords.seats(MonthLord.GUARD_DOG, it).isNotEmpty() })
        // The nine black ones of the month: nine members in every month.
        for (m in Animal.entries) assertEquals(9, YearMonthEarthLords.seats(MonthLord.NINE_BLACK, m).size, "$m")
    }
}
