/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import java.time.LocalDate
import java.time.LocalTime
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/** WB's illness reckoning (ROADMAP H2; docs/sources/corpus/wb/ch25.md). WB gives no worked example of these parts. */
class IllnessTest {
    /** Pp. 433–435: one born under 1 white (iron) against each element the descending mewa can have. */
    @Test
    fun `the spirit's kind by the birth mewa against the descending`() {
        assertEquals(IllnessSpiritKind.GOING_GOD, IllnessReckoning.spiritKind(1, 5)) // earth, the mother
        assertEquals(IllnessSpiritKind.GOING_GOD, IllnessReckoning.spiritKind(1, 6)) // iron, itself
        assertEquals(IllnessSpiritKind.GOING_GOD, IllnessReckoning.spiritKind(1, 8))
        assertEquals(IllnessSpiritKind.DEBT_DEMON, IllnessReckoning.spiritKind(1, 7)) // fire, the enemy
        assertEquals(IllnessSpiritKind.DEBT_DEMON, IllnessReckoning.spiritKind(1, 2)) // water, the son
        assertEquals(IllnessSpiritKind.RTER_BTSOG, IllnessReckoning.spiritKind(1, 4)) // wood, the friend
        assertEquals(IllnessSpiritKind.DEBT_DEMON, IllnessReckoning.spiritKind(4, 6)) // wood born, iron its enemy
        assertEquals(IllnessSpiritKind.GOING_GOD, IllnessReckoning.spiritKind(4, 3)) // water, wood's mother
    }

    /** P. 435: the element most is the illness, two sharing the most both (p. 438); p. 436 reads splits of two elements. */
    @Test
    fun `the five pebbles' illness and split`() {
        val e = Element.entries.associateBy { it.name.first() }
        fun of(s: String) = s.map { e.getValue(it) }
        assertEquals(listOf(Element.FIRE), IllnessReckoning.chief(of("FFFFE")))
        assertEquals(listOf(Element.FIRE, Element.EARTH), IllnessReckoning.chief(of("FFEEW")))
        assertEquals("FIRE4_EARTH1", IllnessReckoning.split(of("FEFFF")))
        assertEquals("WATER3_IRON2", IllnessReckoning.split(of("IWWIW")))
        assertEquals("WATER5", IllnessReckoning.split(of("WWWWW")))
        assertEquals(null, IllnessReckoning.split(of("FFEEW")))
        assertEquals(null, IllnessReckoning.split(of("FFFF")))
    }

    /**
     * The four times on a day worked by hand: 15 October 2026 at 06:45, the 5th of the 9th month, a mouse month, in a
     * fire horse year. The signs are fire horse, earth mouse, the date's earth horse and the hare hour's iron hare; their
     * life forces fire, water, fire, wood. The trigrams: li for the horse year, kham for the mouse month, the 5th counted
     * on from kham to li (as the almanac's own), the hare hour's first span li's next, khon. Fire is most, the fifth.
     */
    @Test
    fun `the four times of falling ill`() {
        val onset = IllnessReckoning.onset(LocalDate.of(2026, 10, 15), LocalTime.of(6, 45))
        assertEquals(
            listOf(Sign(Element.FIRE, Animal.HORSE), Sign(Element.EARTH, Animal.MOUSE), Sign(Element.EARTH, Animal.HORSE), Sign(Element.IRON, Animal.RABBIT)),
            onset.signs,
        )
        assertEquals(listOf(Trigram.LI, Trigram.KHAM, Trigram.LI, Trigram.KHON), onset.trigrams)
        assertEquals(onset.day.trigram, onset.trigrams[2])
        assertEquals(listOf(Element.FIRE, Element.WATER, Element.FIRE, Element.WOOD, Element.FIRE), onset.pebbles)
        assertEquals(listOf(Element.FIRE), IllnessReckoning.chief(onset.pebbles))
        assertEquals(null, IllnessReckoning.split(onset.pebbles))
        assertEquals(9, onset.mewa)
    }

    /**
     * The sixteen fought pebbles for one born a fire dragon (life force and body earth), fallen ill at the moment above:
     * the times' life forces fire, water, fire, wood and bodies water, fire, fire, wood. Earth meets fire as its mother
     * and water as its friend, white, and wood as its enemy, black; the rise and decline is earth's stage on each
     * time's animal.
     */
    @Test
    fun `the sixteen pebbles`() {
        val onset = IllnessReckoning.onset(LocalDate.of(2026, 10, 15), LocalTime.of(6, 45))
        val pebbles = IllnessReckoning.nineteen(Sign(Element.FIRE, Animal.DRAGON), onset)
        assertEquals(16, pebbles.size)
        val fought = pebbles.filter { it.aspect == IllnessAspect.LIFE || it.aspect == IllnessAspect.BODY }.map { it.white }
        assertEquals(listOf(true, true, true, true, true, true, false, false), fought)
        val animals = listOf(Animal.HORSE, Animal.MOUSE, Animal.HORSE, Animal.RABBIT)
        assertEquals(animals.map { YearOfLife.sector(Element.EARTH, it).good }, pebbles.filter { it.aspect == IllnessAspect.LIFE_RISE }.map { it.white })
    }

    /**
     * WB's one worked example (p. 442): a woman of the wood rat year, her power wood, its breath taken at the monkey;
     * at sunrise, carried onto the dragon, she falls on decline.
     */
    @Test
    fun `the twelve stages by WB's example`() {
        assertEquals(Sector.DECLINE, IllnessReckoning.stage(Sign(Element.WOOD, Animal.MOUSE), Animal.DRAGON))
    }

    /** P. 445: for an earth life force, the nurse is fire, its mother; the physician iron, its son; the messenger water, its friend. */
    @Test
    fun `the people the sick need`() {
        assertEquals(Element.FIRE, IllnessReckoning.helper(IllnessHelper.NURSE, Element.EARTH))
        assertEquals(Element.IRON, IllnessReckoning.helper(IllnessHelper.PHYSICIAN, Element.EARTH))
        assertEquals(Element.WATER, IllnessReckoning.helper(IllnessHelper.MESSENGER, Element.EARTH))
    }

    /**
     * P. 426 with the notes: a man born a fire dragon (life force earth), in 2026, his 51st year: the progressed year
     * runs from the earth tiger (fire's son, earth) on fifty places to the earth dragon, life force earth, his own.
     */
    @Test
    fun `the progressed year's reach`() {
        assertEquals(Sign(Element.EARTH, Animal.DRAGON), YearOfLife.logMen(Element.FIRE, Gender.MALE, 51))
        assertEquals(Kinship.IDENTITY, IllnessReckoning.reach(Sign(Element.FIRE, Animal.DRAGON), 1976, Gender.MALE, 2026))
        assertEquals(IllnessYearPair.HORSE_SHEEP, IllnessYearPair.of(Animal.SHEEP))
    }

    /**
     * The board for one born a fire dragon in 1976 (life force earth, body earth, power fire, luck wood), reckoned in the
     * fire horse year 2026, fifty years lived. Point 1 counts from the sky year fifty places down, forward, to the fire
     * monkey (iron, fire, fire, wood): earth meets iron as its son, speckled; earth meets fire, its mother, white; fire
     * and wood meet themselves, black. Point 2 from the water tiger (fire's enemy) to the water dragon (earth, water,
     * water, wood): white, white, black, black. The door against the fire horse (fire, water, fire, iron): white,
     * white, black, black.
     */
    @Test
    fun `the board of the fifteen starting points`() {
        val board = IllnessReckoning.board(Sign(Element.FIRE, Animal.DRAGON), 1976, 2026)
        val W = PebbleColour.WHITE; val B = PebbleColour.BLACK; val S = PebbleColour.SPECKLED
        assertEquals(15, board.points.size)
        assertEquals(Sign(Element.FIRE, Animal.MONKEY), board.points[0].reached)
        assertEquals(listOf(S, W, B, B), board.points[0].pebbles)
        assertEquals(Sign(Element.WATER, Animal.DRAGON), board.points[1].reached)
        assertEquals(listOf(W, W, B, B), board.points[1].pebbles)
        assertEquals(listOf(W, W, B, B), board.door)
        // Point 10: thirteen up from the fire horse, inclusively, the wood horse (2014), then fifty down: the wood monkey.
        assertEquals(Sign(Element.WOOD, Animal.MONKEY), board.points[9].reached)
        assertEquals(15, board.tree(Force.BODY).size)
    }

    /** The day of falling ill turns at dawn, 05:00, and the moment is counted from that dawn. */
    @Test
    fun `the moment of falling ill`() {
        val date = LocalDate.of(2026, 10, 15)
        val night = IllnessReckoning.onset(date, LocalTime.of(4, 30))
        assertEquals(TibetanCalendar.of(date.minusDays(1)), night.day)
        assertEquals(23 * 60 + 30, night.minutes)
        val morning = IllnessReckoning.onset(date, LocalTime.of(6, 45))
        assertEquals(TibetanCalendar.of(date), morning.day)
        assertEquals(105, morning.minutes)
        val day = morning.day
        assertEquals(MomentReckoning.trigram(day.monthNames.animal, day.day, 105), morning.trigram)
        assertEquals(MomentReckoning.mewa(day.monthNames.animal, day.day, 105), morning.mewa)
        assertEquals(Forces.signs(day).date.animal, morning.dayAnimal)
    }
}
