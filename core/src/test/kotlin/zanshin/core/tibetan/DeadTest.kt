/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import java.time.LocalDate

/** The reckoning for the dead's first examination (ROADMAP R2n; docs/sources/reckonings.md, RD16). */
class DeadTest {
    /**
     * Kun dga' rig 'dzin's sheet for the dead (KD vol. 3, p. 729; docs/sources/verdicts.md, RD28): a man of the fire
     * horse year, past ninety, dead in a wood pig year on the 4th of the 9th month at the sheep hour, his deciding
     * pebbles life black, body white, destiny white, luck black. The sheet fights four places, the year, month, day
     * and hour of death; on those four the colours and counts here give its pebbles. WB's roots (pp. 45–46; the notes,
     * p. 500), eight of them here, give the same but destiny speckled, three places white and three black.
     */
    @Test
    fun `the time of death on Kun dga' rig 'dzin's sheet`() {
        var d = LocalDate.of(1995, 10, 15)
        while (TibetanCalendar.of(d).let { !(it.month == 9 && it.day == 4) }) d = d.plusDays(1)
        val day = TibetanCalendar.of(d)
        val hour = Forces.hours(Forces.signs(day).date).first { it.sign.animal == Animal.SHEEP }
        val r = DeadReckoning.of(1906, Gender.MALE, day, hour)!!
        assertEquals(90, r.year.age)
        val four = setOf(DeadRoot.DEATH_YEAR, DeadRoot.MONTH, DeadRoot.DATE, DeadRoot.HOUR)
        fun onFour(f: Force): PebbleColour = r.pebbles.filter { it.force == f && it.root in four }.map { it.pebbles }
            .let { ps -> ps.count { it.white > 0 && it.black == 0 } to ps.count { it.black > 0 && it.white == 0 } }
            .let { (w, b) -> if (w > b) PebbleColour.WHITE else if (b > w) PebbleColour.BLACK else PebbleColour.SPECKLED }
        assertEquals(listOf(PebbleColour.BLACK, PebbleColour.WHITE, PebbleColour.WHITE, PebbleColour.BLACK), Force.entries.map(::onFour))
        assertEquals(listOf(PebbleColour.BLACK, PebbleColour.WHITE, PebbleColour.SPECKLED, PebbleColour.BLACK), Force.entries.map(r::deciding))
        assertEquals(Pebbles(3, 3), r.places(Force.DESTINY))
        assertEquals(8, r.pebbles.count { it.force == Force.LUCK })
        assertNull(r.whole)
        // The sixth and seventh tests at the year of death, the 90th year of the fire horse: what the year of age gives.
        assertEquals(r.year.smeBaObstacles.filter { it.name in setOf("HOUSE", "BED", "LAND", "ROYAL_GATE") }, r.mewaObstacles)
        assert(r.course.all { h -> r.year.harsh.any { it.year == h } })
        // Without the hour of death the seven other roots are reckoned.
        assertEquals(7, DeadReckoning.of(1906, Gender.MALE, day, null)!!.pebbles.count { it.force == Force.LUCK })
        assertNull(DeadReckoning.of(1996, Gender.MALE, day, hour))
    }

    /** The second examination (WB vol. 2, pp. 57–58): the reason by the times of death and by the elements' cuts. */
    @Test
    fun `the reason the death met`() {
        // Each animal's demon cuts the la in its seventh month: the tiger in the monkey's, the ox in the sheep's (p. 58).
        assertEquals(Animal.MONKEY, DeadReckoning.cutMonth(Animal.TIGER))
        assertEquals(Animal.SHEEP, DeadReckoning.cutMonth(Animal.OX))
        assertEquals(Animal.MOUSE, DeadReckoning.cutMonth(Animal.HORSE))
        // The fifth examination's hours (p. 71): two to a realm, the hare and tiger gods, the monkey and bird hell.
        assertEquals(DeadRebirth.entries.toSet(), Animal.entries.map(DeadReckoning::rebirthByHour).toSet())
        DeadRebirth.entries.forEach { r -> assertEquals(2, Animal.entries.count { DeadReckoning.rebirthByHour(it) == r }) }
        assertEquals(DeadRebirth.GOD, DeadReckoning.rebirthByHour(Animal.TIGER))
        assertEquals(DeadRebirth.HELL, DeadReckoning.rebirthByHour(Animal.BIRD))
        var d = LocalDate.of(1995, 10, 15)
        while (TibetanCalendar.of(d).let { !(it.month == 9 && it.day == 4) }) d = d.plusDays(1)
        val day = TibetanCalendar.of(d)
        val hour = Forces.hours(Forces.signs(day).date).first { it.sign.animal == Animal.SHEEP }
        val r = DeadReckoning.of(1906, Gender.MALE, day, hour)!!
        // The sheet's man died in a wood pig year, at the sheep hour.
        assertEquals(listOf(DeadTime.YEAR to Animal.PIG, DeadTime.MONTH to Forces.signs(day).month.animal), r.times.take(2))
        assertEquals(DeadTime.HOUR to Animal.SHEEP, r.times.last())
        assertEquals(3, DeadReckoning.of(1906, Gender.MALE, day, null)!!.times.size)
        // The third examination's inputs (pp. 58–68): the weekday of death, and the day's animal only where p. 63 names it.
        assertEquals(day.weekday, r.weekday)
        assertEquals(day.mansion, r.mansion)
        // D18: the dead's trigram and mewa at death are those of the year of age at the year of death.
        assertEquals(r.year.trigram, r.trigram)
        assertEquals(r.year.currentSmeBa, r.mewa)
        // The tenth examination's strikes (pp. 87–88): the sheet's man died in his 90th year, a nine-multiple; on the 4th, no grave day.
        assertEquals(day.day, r.lunarDate)
        assertNull(r.nineEye)
        assertEquals(false, r.graveDay)
        // By the Chinese seasons the 11th month is a season's first, the 12th its middle, the 1st its last (p. 88).
        assertEquals(true, r.copy(lunarDate = 6, monthNumber = 11).graveDay)
        assertEquals(true, r.copy(lunarDate = 16, monthNumber = 12).graveDay)
        assertEquals(true, r.copy(lunarDate = 26, monthNumber = 1).graveDay)
        assertEquals(false, r.copy(lunarDate = 6, monthNumber = 12).graveDay)
        assertEquals(r.date.animal.takeIf { it in setOf(Animal.OX, Animal.TIGER, Animal.SNAKE, Animal.HORSE, Animal.SHEEP, Animal.MONKEY) }, r.demonDay)
        // His vitality's deciding pebble is black, so the cuts are read: those met most often among the fights.
        val counts = r.pebbles.mapNotNull { p -> p.other?.let { Forces.kinship(r.year.forces[p.force], it) to r.year.forces[p.force] } }
            .filter { it.first == Kinship.ENEMY || it.first == Kinship.IDENTITY }.groupingBy { it }.eachCount()
        val most = counts.values.max()
        assertEquals(counts.filterValues { it == most }.keys.map { (k, e) -> e to (k == Kinship.IDENTITY) }.toSet(), r.cuts.map { it.own to it.inner }.toSet())
    }
}
