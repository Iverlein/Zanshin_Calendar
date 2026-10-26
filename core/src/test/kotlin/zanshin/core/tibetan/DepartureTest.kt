/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import java.time.LocalDate
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import zanshin.core.tibetan.Animal.BIRD
import zanshin.core.tibetan.Animal.DOG
import zanshin.core.tibetan.Animal.DRAGON
import zanshin.core.tibetan.Animal.HORSE
import zanshin.core.tibetan.Animal.MONKEY
import zanshin.core.tibetan.Animal.MOUSE
import zanshin.core.tibetan.Animal.PIG
import zanshin.core.tibetan.Animal.SHEEP
import zanshin.core.tibetan.Animal.SNAKE
import zanshin.core.tibetan.Animal.TIGER
import zanshin.core.tibetan.DepartureTone.BAD
import zanshin.core.tibetan.DepartureTone.GOOD
import zanshin.core.tibetan.DepartureTone.MIDDLING

/**
 * The bride's departure (WB vol. 1, ch. 23, pp. 365–378; docs/sources/marriage.md): Mipham's *kham yu*, the bad ages,
 * the sky dog's coupling, the hour and direction, and a month of a couple's days worked by hand from the topic file's
 * tables. The couples are invented; no birth here is anyone's.
 */
class DepartureTest {
    private fun day(year: Int, month: Int, date: Int, from: LocalDate): TibetanDay {
        return (0L until 120L).map { TibetanCalendar.of(from.plusDays(it)) }
            .first { it.year == year && it.month == month && it.day == date && !it.leapMonth }
    }

    private fun cases(d: DepartureDay, part: DeparturePart): List<String> = d.findings.filter { it.part == part }.map { it.case }

    /** A day some weeks into Tibetan year [year], for the findings that go by the year. */
    private fun inYear(year: Int): TibetanDay = TibetanCalendar.of(TibetanCalendar.losar(year).plusDays(40))

    /**
     * Mipham's worked example (vol. 2, p. 527, marriage.md, *1. Kham yu*): a horse-born groom of 25 lands on «རྟ་མཆུ་ཉི་མ་བཅུ་པ»,
     * Maghā, the tenth, good; a sheep-born bride of 24 on «རྟ་མོན་དྲེ་ཉི་མ་ལག་སོར», Dhaniṣṭhā, the finger, bad. An iron horse of 1990
     * and an iron sheep of 1991 are 25 and 24 in 2014.
     */
    @Test
    fun `Mipham's kham yu`() {
        assertEquals(HORSE, Departure.khamYuStation(HORSE, 25, groom = true))
        assertEquals(HORSE, Departure.khamYuStation(SHEEP, 24, groom = false))
        val d = Departure.of(Couple(Spouse(1990), Spouse(1991)), inYear(2014))
        val khamYu = d.findings.filter { it.part == DeparturePart.KHAM_YU }.associate { it.case to it.tone }
        assertEquals(mapOf("GROOM.HORSE" to GOOD, "BRIDE.HORSE" to BAD), khamYu)
        assertEquals("reading.Departure.KHAM_YU.BRIDE.HORSE", d.findings.first { it.case == "BRIDE.HORSE" }.reading)
    }

    /** The bride's bad ages by her year (p. 371): she dies, no son, other bad ages, for a few animals. */
    @Test
    fun `the bad ages by the bride's year`() {
        fun year(born: Int, age: Int): List<String> =
            cases(Departure.of(Couple(Spouse(born - 2), Spouse(born)), inYear(born + age - 1)), DeparturePart.YEAR)
        // A wood mouse of 1984: dies at 18 and 27, no son at 15, bad for father and brothers at 26.
        assertTrue("DIES" in year(1984, 18))
        assertTrue("DIES" in year(1984, 27))
        assertTrue("NO_SON" in year(1984, 15))
        assertTrue("OTHER_AGE" in year(1984, 26))
        // A fire tiger of 1986: dies at 17 and 22, no son at 11, 26 and 36 bad; 18 good and 27 middling, a first of the four.
        assertTrue("DIES" in year(1986, 22))
        assertTrue("NO_SON" in year(1986, 11))
        assertTrue("OTHER_AGE" in year(1986, 36))
        assertTrue("AGE_GOOD" in year(1986, 18))
        assertTrue("AGE_MIDDLING" in year(1986, 27))
        // An earth snake of 1989: barren at 20, dies at 12 and 24.
        assertTrue("NO_SON" in year(1989, 20))
        assertTrue("DIES" in year(1989, 24))
        // An iron sheep of 1991: 22, 26 and 36 bad, a last of the four, 24 best.
        assertTrue("OTHER_AGE" in year(1991, 36))
        assertTrue("AGE_GOOD" in year(1991, 24))
        // A wood pig of 1995 at 33: none.
        assertTrue(year(1995, 33).none { it in setOf("DIES", "NO_SON", "OTHER_AGE") })
        // The ages 17 to 32 by number, each its own (p. 372).
        assertTrue("AGE.17" in year(1995, 17))
        assertFalse(year(1995, 33).any { it.startsWith("AGE.") })
    }

    /**
     * The sky dogs' coupling (p. 376; vol. 2, p. 198), by the year of departure in the Chinese reckoning: a sheep year
     * on the 7th of the last autumn month (Hor month 7 of 2027), a mouse year on the 1st of the middle spring month
     * (Hor month 12 of Tibetan year 2031, the mouse year of the Chinese reckoning from the 11th month), a pig year on
     * the 8th of the first spring month (Hor month 11 of 2030).
     */
    @Test
    fun `the coupling date of the sky dog`() {
        val couple = Couple(Spouse(1992), Spouse(1995))
        val sheep = Departure.of(couple, LocalDate.of(2027, 9, 7))
        assertEquals(7, sheep.day.day)
        assertTrue("COUPLING" in cases(sheep, DeparturePart.SKY_DOG))
        assertFalse("COUPLING" in cases(Departure.of(couple, LocalDate.of(2027, 9, 6)), DeparturePart.SKY_DOG))
        val mouse = Departure.of(couple, day(2031, 12, 1, LocalDate.of(2031, 11, 15)))
        assertEquals(MOUSE, EarthLordCourses.chineseYear(mouse.day))
        assertTrue("COUPLING" in cases(mouse, DeparturePart.SKY_DOG))
        val pig = Departure.of(couple, day(2030, 11, 8, LocalDate.of(2030, 11, 1)))
        assertEquals(PIG, EarthLordCourses.chineseYear(pig.day))
        assertTrue("COUPLING" in cases(pig, DeparturePart.SKY_DOG))
        // The coupling is not the bride's: one date a year, whatever her year.
        assertTrue("COUPLING" in cases(Departure.of(Couple(Spouse(1990), Spouse(1984)), LocalDate.of(2027, 9, 7)), DeparturePart.SKY_DOG))
    }

    /**
     * The hour of a pig-year bride, as the notes work it (vol. 2, p. 531, marriage.md, *165*): the bird, the life force's
     * mother, good; the monkey, though a mother, a four-executioner; the horse, a friend, good; the snake, though a
     * friend, the seventh. Her grave by the life force, water's dragon, and its seventh the dog.
     */
    @Test
    fun `the hour of a pig-year bride`() {
        val couple = Couple(Spouse(1992), Spouse(1995))
        fun hour(h: Animal) = Departure.of(couple, LocalDate.of(2027, 9, 10), hour = h).findings.filter { it.part == DeparturePart.HOUR }.map { it.case to it.tone }
        assertEquals(listOf("MOTHER" to GOOD), hour(BIRD))
        assertEquals(listOf("FOUR_EXECUTIONER" to BAD), hour(MONKEY))
        assertEquals(listOf("FRIEND" to GOOD), hour(HORSE))
        assertEquals(listOf("SEVENTH" to BAD), hour(SNAKE))
        assertEquals(listOf("FOUR_EXECUTIONER" to BAD), hour(TIGER))
        assertEquals(listOf("GRAVE" to BAD), hour(DRAGON))
        assertEquals(listOf("GRAVE" to BAD), hour(DOG))
        assertTrue(Departure.of(couple, LocalDate.of(2027, 9, 10)).findings.none { it.part == DeparturePart.HOUR })
    }

    /**
     * The direction (pp. 374–375) for the pig-year bride of 2027, on *kham* in her 33rd year: south is *li*, the sky
     * goddess's, good; west is *dwa*, harm (*gnod pa*), with *kham*'s remedy; north is *kham* itself, named by neither.
     * The year's mewa, 2027's nine-red, in the middle puts the four-green in the south, bad.
     */
    @Test
    fun `the direction of travel`() {
        val couple = Couple(Spouse(1992), Spouse(1995))
        fun direction(d: Direction) = Departure.of(couple, LocalDate.of(2027, 9, 10), direction = d).findings.filter { it.part == DeparturePart.DIRECTION }
        assertEquals(listOf("GNAM_SMAN" to GOOD, "MEWA.4" to BAD), direction(Direction.SOUTH).map { it.case to it.tone })
        val west = direction(Direction.WEST).first()
        assertEquals("GNOD_PA" to BAD, west.case to west.tone)
        assertEquals("reading.Departure.DIRECTION.GNOD_PA.KHAM.remedy", west.remedy)
        assertEquals(listOf("MEWA.5"), direction(Direction.NORTH).map { it.case })
        // The earth lords toward the direction only, and never weighed (D4).
        val lords = Departure.of(couple, LocalDate.of(2027, 9, 10), direction = Direction.NORTH).findings.filter { it.part == DeparturePart.EARTH_LORDS }
        assertTrue(lords.none { it.weighed })
        assertTrue(lords.filter { it.scope == DepartureScope.DIRECTION }.all { (it.falls as DepartureFall).terms == listOf(listOf(Direction.NORTH)) })
    }

    /** Anurādhā on a Tuesday in a fire year, «ཤ་བ་བརྒྱ་གསོད་བག», cannot be remedied (p. 377): 2 February 2027, of the fire horse year. */
    @Test
    fun `the bride of a hundred slain deer`() {
        val d = Departure.of(Couple(Spouse(1992), Spouse(1995)), LocalDate.of(2027, 2, 2))
        assertEquals(Mansion.ANURADHA, d.day.mansion)
        assertTrue(d.noRemedy)
        assertFalse(Departure.of(Couple(Spouse(1992), Spouse(1995)), LocalDate.of(2027, 2, 1)).noRemedy)
    }

    /**
     * A couple's good and bad days for the departure over one Tibetan month, worked by hand from marriage.md's tables:
     * a groom born in 1992 (water monkey) and a bride born on 4 November 1995 (wood pig: life force water; born in the
     * mouse month, on an ox day, a Saturday, in Pūrvabhādrapadā), leaving in Hor month 7 of 2027 (fire sheep), 1 to
     * 30 September 2027, the dog month, the last month of autumn (season-month 8) in the sheep year of the Chinese
     * reckoning. In her 33rd year she goes on *kham*.
     *
     * The month (p. 372): a pig year is female, so counted back from her pig month, the dog month is the 2nd, good;
     * it is not her birth month (mouse) or its seventh (horse), nor the pig triad's grave month (sheep), nor the upper
     * or lower four-executioner (tiger, monkey). So every day's month finding is good, and a day is good unless one of
     * its own findings is bad. What makes a day bad (p. 373, pp. 375–376, p. 366): the dates 1, 3, 4, 5, 6, 8, 9, 18,
     * 19, 21, 22, 28, 30; the three 7s (the 7th the sky door of a last month); Saturday, Wednesday, Sunday, Tuesday;
     * her birth day's ox and its seventh, the sheep; the earth days ox, dragon, sheep, dog, executioners of her water;
     * water's grave, the dragon; the snake; Kṛttikā, Anurādhā, Revatī, Dhaniṣṭhā, Maghā, Rohiṇī and her birth
     * mansion; the demon, *'chi*, *mi 'phrod*, *mi mthun* and *'jig* days and the burning date; the sky dog's tiger,
     * horse and dog days, its black days and Wednesday, its coupling (the 7th of this month in a sheep year) and its
     * food in this month, the mouse, hare and monkey days; and, since she goes on *kham*, the bird and monkey days
     * (no. 20). Thursday and Friday are good, Monday middling.
     */
    @Test
    fun `a month of a couple's departure, by hand`() {
        val couple = Couple(Spouse(1992), Spouse.born(LocalDate.of(1995, 11, 4)))
        assertEquals(PIG, couple.bride.sign.animal)
        assertEquals(Animal.OX, couple.bride.born!!.lunarDayAnimal)
        val days = Departure.days(couple, LocalDate.of(2027, 9, 1), LocalDate.of(2027, 9, 30))
        val expected = mapOf(
            1 to BAD, // the 1st (father and brothers); a tiger day (the sky dog); Wednesday; Maghā
            2 to GOOD, // the month's 2nd and Thursday; the sky dog's food on a hare day is reported, not weighed (D4)
            3 to BAD, // the 3rd (the mother); a dragon day, an executioner and her grave
            4 to BAD, // the 4th; a snake day; Saturday, her birth weekday and her enemy's; 'chi sbyor
            5 to BAD, // the 5th; a horse day; Sunday
            6 to BAD, // the 6th; a sheep day, the seventh of her birth day and an executioner; a black day
            7 to BAD, // the 7th, the sky door of a last month; the coupling; a monkey day (food, and no. 20); Tuesday
            8 to BAD, // the 8th; a bird day (no. 20); Wednesday; Anurādhā
            9 to BAD, // the 9th; a dog day; Jyeṣṭhā clean, but the date and the day are not
            10 to GOOD, // the 10th, a pig day, Friday (good), Mūla: nothing bad, the month's 2nd and Friday good
            11 to BAD, // a mouse day (food); Saturday
            12 to BAD, // an ox day, her birth day's animal and an executioner; Sunday; a black day; the burning date
            13 to GOOD, // the month's 2nd; Monday middling, Śravaṇa clean; the sky dog's tiger day reported, not weighed (D4)
            14 to BAD, // a hare day (food); Tuesday
            15 to BAD, // a dragon day; Wednesday; Dhaniṣṭhā
            16 to BAD, // a snake day; mi 'phrod nyi ma
            17 to BAD, // the 17th, a sky door; a horse day; Pūrvabhādrapadā, her birth mansion; the burning date
            18 to BAD, // the 18th; Saturday; a sheep day; a black day
            19 to BAD, // the 19th; Sunday; a monkey day; Revatī
            20 to BAD, // a bird day: she goes on kham, so no. 20, separation; Monday and Aśvinī otherwise
            21 to BAD, // the 21st; a dog day; Tuesday
            22 to BAD, // the 22nd; Wednesday; Kṛttikā
            23 to BAD, // a mouse day (food); Rohiṇī; 'jig pa'i nyi ma
            24 to BAD, // an ox day, her birth day's animal; a black day
            25 to BAD, // a tiger day; Saturday
            26 to BAD, // Sunday; a hare day (food)
            27 to BAD, // the 27th, a sky door; a dragon day
            28 to BAD, // the 28th; a snake day; Tuesday; Maghā
            29 to BAD, // Wednesday; a horse day
            30 to BAD, // the 30th; a sheep day; a black day
        )
        assertEquals(30, days.size)
        assertEquals(expected, days.associate { it.day.day to it.dayVerdict })
        fun on(date: Int) = days.first { it.day.day == date }
        fun bad(d: DepartureDay) = d.findings.filter { it.weighed && it.tone == BAD && it.scope != DepartureScope.YEAR }.map { "${it.part}.${it.case}" }.toSet()
        // The days one finding makes bad.
        // The sky dog's food day and tiger day are reported, not weighed (D4): nothing else bars the 2nd and 13th.
        assertEquals(emptySet<String>(), bad(on(2)))
        assertEquals(emptySet<String>(), bad(on(13)))
        assertTrue(on(2).findings.any { it.part == DeparturePart.SKY_DOG && it.case == "FOOD" && !it.weighed })
        assertEquals(setOf("TWENTY_BRIDES.SEPARATION"), bad(on(20)))
        assertTrue(setOf("DATE.SKY_DOOR_MONTH", "TWENTY_BRIDES.SEPARATION").all { it in bad(on(7)) })
        assertTrue(setOf("DATE.OWN_DAY", "DATE.EXECUTIONER_DAY", "MANSION.BURNING").all { it in bad(on(12)) })
        assertTrue(setOf("MANSION.BIRTH_MANSION", "DATE.SKY_DOOR").all { it in bad(on(17)) })
        assertTrue("MANSION.GOOD.PURVABHADRAPADA" in on(17).findings.map { "${it.part}.${it.case}" })
        // The good day: the month's 2nd and Friday.
        val good = on(10).findings.filter { it.weighed && it.scope != DepartureScope.YEAR }.map { "${it.part}.${it.case}" to it.tone }
        assertEquals(listOf("MONTH.COUNT.2" to GOOD, "PLANET.FRIDAY" to GOOD), good)

        // The year's findings stand over every day (p. 366, pp. 368–371): kham yu, the sheep station (Śatabhiṣaj) for
        // her, the bird (Hasta) for the groom, both middling; no. 8, sorrow (on her own trigram kham in a year of her
        // triad); the year's earth life cutting her water (no. 11) and its water body cutting her fire (no. 13); kham
        // her own trigram, good, but the enemy of her fire body and wind-horse; the seven-red, bad, on Barma's white
        // lotus woman in the west, good; the progressed sign iron mouse, of iron's four and the line-cutting; her power,
        // wood, entering its tomb in a sheep year; the groom's life (iron) and power (water) in good stages.
        val year = on(10).yearFindings.map { "${it.part}.${it.case}" to it.tone }
        assertEquals(
            listOf(
                "KHAM_YU.GROOM.BIRD" to MIDDLING, "KHAM_YU.BRIDE.SHEEP" to MIDDLING,
                "TWENTY_BRIDES.SORROW" to BAD, "TWENTY_BRIDES.LADLE" to BAD, "TWENTY_BRIDES.EARTH_SACK" to BAD,
                "TRIGRAM.OWN" to GOOD, "TRIGRAM.ENEMY.BODY" to BAD, "TRIGRAM.ENEMY.LUCK" to BAD,
                "MEWA.7" to BAD, "MEWA.GOD.WEST" to GOOD,
                "YEAR.LOG_MEN_HARSH" to BAD, "YEAR.LOG_MEN.LINEAGE_CUT" to BAD, "YEAR.SECTOR.DESTINY" to BAD,
                "YEAR.FLOURISH.GROOM_VITALITY" to GOOD, "YEAR.FLOURISH.GROOM_DESTINY" to GOOD,
            ),
            year,
        )
        // So over all its findings the whole month is bad.
        assertTrue(days.all { it.verdict == BAD })
    }

    /** Every finding names its part's catalog key, a known *How it falls* rule, and only the earth lords, the sky dog (D4) and people go unweighed. */
    @Test
    fun `findings keep their keys`() {
        val couples = listOf(
            Couple(Spouse(1992), Spouse.born(LocalDate.of(1995, 11, 4))),
            Couple(Spouse(1990, motherBirthYear = 1965), Spouse(1991, motherBirthYear = 1968)),
            Couple(Spouse(1984), Spouse(2000)),
        )
        var d = LocalDate.of(2026, 1, 1)
        while (d < LocalDate.of(2027, 1, 1)) {
            for (c in couples) for (f in Departure.of(c, d, hour = Animal.entries[d.dayOfYear % 12], direction = Direction.entries[d.dayOfYear % 8]).findings) {
                if (f.part == DeparturePart.EARTH_LORDS) {
                    assertFalse(f.weighed)
                    assertTrue(f.reading.matches(Regex("reading\\.(EarthLord|YearLord|MonthLord)\\.[A-Z_0-9]+|reading\\.GreatBlackDay")), f.reading)
                    assertNotNull(f.note)
                } else {
                    assertEquals("reading.Departure.${f.part.name}.${f.case}", f.reading)
                    assertEquals(f.part !in setOf(DeparturePart.FIVE_PEOPLE, DeparturePart.RECEIVING, DeparturePart.SKY_DOG), f.weighed, f.reading)
                    assertTrue((f.falls as DepartureFall).rule in Departure.FALL_RULES, f.reading)
                }
            }
            d = d.plusDays(5)
        }
    }
}
