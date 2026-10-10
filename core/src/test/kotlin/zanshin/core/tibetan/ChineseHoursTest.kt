/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import zanshin.core.astro.Place
import zanshin.core.astro.SunTimes
import zanshin.core.kyureki.Tone
import zanshin.core.texts.Activities
import zanshin.core.texts.Activity
import zanshin.core.texts.DayFactor
import zanshin.core.texts.DaySummary
import zanshin.core.texts.Texts
import zanshin.core.tibetan.ChineseHours.RoughTime
import java.time.LocalDate
import java.time.ZoneId

/**
 * The White Beryl's twelve hours, the hour of one's own year and the four rough times (vol. 2,
 * p. 359, read on the scan), and the *kun phan me long*'s rule 2 from p. 376, the hour above the
 * day's animal sign (docs/sources/weighing.md, *The hour of rule 2*).
 */
class ChineseHoursTest {
    private val kyoto = Place(35.0116, 135.7681, ZoneId.of("Asia/Tokyo"))
    private val lhasa = Place(29.6500, 91.1000, ZoneId.of("Asia/Shanghai"))

    @Test
    fun `each hour has the works p 359 names for it`() {
        assertEquals(Animal.entries.toSet(), Texts.HOUR_WORKS.keys)
        // In the print's order from the hare hour: what each hour is good for, as the app's acts.
        val acts = listOf(
            setOf(Activity.DAIRY, Activity.BREWING, Activity.DESTROYING), // curd and beer, the gongpo's mouths
            setOf(Activity.PROSPERITY_RITES),
            setOf(Activity.MANTRAS),
            setOf(Activity.AVERTING_RITES),
            emptySet(), // madness and fainting fits: no act the lists compare
            setOf(Activity.AVERTING_RITES),
            setOf(Activity.OFFERINGS),
            setOf(Activity.DESTROYING),
            setOf(Activity.DESTROYING),
            setOf(Activity.PROSPERITY_RITES),
            setOf(Activity.DESTROYING),
            setOf(Activity.DESTROYING),
        )
        assertEquals(acts, Forces.HOUR_ANIMALS.map { Activities.of(Texts.HOUR_WORKS.getValue(it).goodKeys) })
        assertTrue(Texts.HOUR_WORKS.values.all { it.avoidKeys.isEmpty() })
    }

    @Test
    fun `the four rough times are the sun's moments within the Tibetan day`() {
        val day = LocalDate.of(2026, 10, 10)
        val sun = SunTimes.of(day, kyoto)
        val times = ChineseHours.roughTimes(day, kyoto)
        assertEquals(listOf(RoughTime.SUNRISE, RoughTime.NOON, RoughTime.SUNSET, RoughTime.MIDNIGHT), times.map { it.kind })
        assertEquals(listOf(sun.sunrise, sun.transit, sun.sunset, sun.transit.plusHours(12)), times.map { it.at })
        // In Kyoto noon falls in the horse hour, which the print names noon, and midnight in the mouse hour.
        assertEquals(listOf(0, 3, 6, 9), times.map { ChineseHours.hourOf(day, it.at) })
        // In Lhasa, on Beijing's clock, the sun stands highest in the sheep hour: the moment, not the hour's name.
        val lhasaNoon = ChineseHours.roughTimes(day, lhasa).single { it.kind == RoughTime.NOON }
        assertEquals(Animal.SHEEP, Forces.HOUR_ANIMALS[ChineseHours.hourOf(day, lhasaNoon.at)])
    }

    @Test
    fun `a sunrise before five belongs to the day before`() {
        // Kyoto at midsummer: the sun rises before 05:00, so the day's sunrise is the next morning's, in its tiger hour.
        val day = LocalDate.of(2026, 6, 21)
        val sunrise = ChineseHours.roughTimes(day, kyoto).single { it.kind == RoughTime.SUNRISE }
        assertEquals(SunTimes.of(day.plusDays(1), kyoto).sunrise, sunrise.at)
        assertEquals(Animal.TIGER, Forces.HOUR_ANIMALS[ChineseHours.hourOf(day, sunrise.at)])
    }

    @Test
    fun `the hour of one's own year is the birth year's animal's`() {
        assertTrue(ChineseHours.ownYearHour(Animal.HORSE, Animal.HORSE))
        assertTrue(Animal.entries.filter { it != Animal.HORSE }.none { ChineseHours.ownYearHour(Animal.HORSE, it) })
    }

    @Test
    fun `the hour turns what the day's animal sign alone decided`() {
        // 5 January 2026, a horse day: consultations, and seeking friends (the horse's results, WB vol. 2,
        // p. 357), are good by the day's animal sign only; in a rough time and in the day's black hours (the
        // bird and the hare) nothing but fierce work turns out well.
        val horseDay = DaySummary.of(TibetanCalendar.of(LocalDate.of(2026, 1, 5)))
        val consult = horseDay.activities.single { it.activity == Activity.CONSULTATIONS }
        assertEquals(DayFactor.DAY_ANIMAL, consult.decider)
        assertEquals(Tone.GOOD, horseDay.sideOf(Activity.CONSULTATIONS)!!.first)
        for (hour in listOf(Texts.ROUGH_TIME, Texts.BLACK_HOUR)) {
            assertEquals(listOf(Activity.CONSULTATIONS to Tone.BAD, Activity.FRIENDSHIP to Tone.BAD), horseDay.overruledInHour(listOf(hour)).map { it.first.activity to it.second })
        }
        // 29 November 2026: fierce rites, which the day's animal sign avoids, are good in the hours named for fierce mantras.
        val day = DaySummary.of(TibetanCalendar.of(LocalDate.of(2026, 11, 29)))
        assertEquals(Tone.BAD, day.sideOf(Activity.DESTROYING)!!.first)
        for (hour in listOf(Animal.OX, Animal.TIGER)) {
            assertEquals(listOf(Activity.DESTROYING to Tone.GOOD), day.overruledInHour(listOf(Texts.HOUR_WORKS.getValue(hour))).map { it.first.activity to it.second })
        }
        // Readings that name a work both ways turn nothing: the ox hour's fierce mantras against one's own year's fierce work.
        assertTrue(day.overruledInHour(listOf(Texts.HOUR_WORKS.getValue(Animal.OX), Texts.OWN_YEAR_HOUR)).isEmpty())
    }

    @Test
    fun `only the day's animal sign is outweighed`() {
        var d = LocalDate.of(2026, 1, 1)
        var turned = 0
        while (d.year == 2026) {
            val s = DaySummary.of(TibetanCalendar.of(d))
            for (hour in Texts.HOUR_WORKS.values + Texts.ROUGH_TIME + Texts.BLACK_HOUR + Texts.OWN_YEAR_HOUR) {
                for ((note, tone) in s.overruledInHour(listOf(hour))) {
                    assertEquals(DayFactor.DAY_ANIMAL, note.decider, "$d ${note.activity}")
                    assertTrue(tone != s.sideOf(note.activity)!!.first, "$d ${note.activity}")
                    turned++
                }
            }
            d = d.plusDays(1)
        }
        assertTrue(turned > 0)
    }
}
