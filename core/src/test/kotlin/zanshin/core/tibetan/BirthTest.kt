/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

/** The birth reckoning's parts built (ROADMAP R2; docs/sources/year-of-life.md, RD13). */
class BirthTest {
    /** The key pebbles (p. 260) and the tie (vol. 2, p. 46): the tiger one black to the snake four, the horse one white to the bird four; the dog and ox speckled. */
    @Test
    fun `the precious pebbles' colours`() {
        assertEquals(Pebbles(0, 1), BirthReckoning.keyPebbles(Animal.TIGER))
        assertEquals(Pebbles(0, 4), BirthReckoning.keyPebbles(Animal.SNAKE))
        assertEquals(Pebbles(4, 0), BirthReckoning.keyPebbles(Animal.BIRD))
        assertEquals(PebbleColour.WHITE, BirthReckoning.preciousColour(Animal.PIG))
        assertEquals(PebbleColour.BLACK, BirthReckoning.preciousColour(Animal.MOUSE))
        assertEquals(PebbleColour.SPECKLED, BirthReckoning.preciousColour(Animal.DOG))
        assertEquals(PebbleColour.SPECKLED, BirthReckoning.preciousColour(Animal.OX))
    }

    /** P. 348's examples: a mouse child's months (dragon the father's, horse the mother's, tiger and hare its own) and a tiger or hare day. */
    @Test
    fun `raising the child by month and day`() {
        assertEquals(RaisingMonth.FATHER, BirthReckoning.raisingMonth(Animal.MOUSE, Animal.DRAGON))
        assertEquals(RaisingMonth.MOTHER, BirthReckoning.raisingMonth(Animal.MOUSE, Animal.HORSE))
        assertEquals(RaisingMonth.OWN, BirthReckoning.raisingMonth(Animal.MOUSE, Animal.TIGER))
        assertEquals(RaisingMonth.OWN, BirthReckoning.raisingMonth(Animal.MOUSE, Animal.RABBIT))
        for (a in listOf(Animal.OX, Animal.SHEEP, Animal.DOG, Animal.DRAGON)) assertEquals(RaisingDay.HARD, BirthReckoning.raisingDay(a, Animal.TIGER))
        for (a in listOf(Animal.HORSE, Animal.SNAKE)) assertEquals(RaisingDay.EASY, BirthReckoning.raisingDay(a, Animal.RABBIT))
    }

    /** The bad years' lists (pp. 353–354): the harsh years are iron's four and fire's eight, two to a name. */
    @Test
    fun `the bad years for marriage`() {
        val harsh = BadYear.entries.filter { it.name.startsWith("HARSH") }.flatMap { it.years }
        assertEquals(12, harsh.size)
        assertEquals(4, harsh.count { it.element == Element.IRON })
        assertEquals(8, harsh.count { it.element == Element.FIRE })
        assertEquals(listOf(7, 6, 7, 2, 4, 3, 11, 4, 8, 8), listOf(BadYear.BAD_OMEN, BadYear.LITTLE_WHITE, BadYear.THAN, BadYear.BYA_RA, BadYear.GRAVE_MEN, BadYear.LDING_NAG, BadYear.SRIN_KHYER, BadYear.THER_DRE, BadYear.WIDOWHOOD, BadYear.AVOID).map { it.years.size })
        assertEquals(listOf(BadYear.HARSH_GRU_KHYER, BadYear.BYA_RA, BadYear.THER_DRE), MatchReckoning.badYears(Sign(Element.FIRE, Animal.HORSE), 1))
        assertEquals(PowerYear.KHONG_NONG, MatchReckoning.sameKind(Sign(Element.FIRE, Animal.DRAGON), Sign(Element.FIRE, Animal.DOG)))
        // Each of the 28 pairs of two trigrams in one kind, but ཁམ་ཟོན (p. 361's printed srog 'tsho repeats ཁམ་ཟིན).
        val pairs = Trigram.entries.flatMap { a -> Trigram.entries.filter { it.ordinal > a.ordinal }.map { a to it } }
        assertEquals(listOf(Trigram.KHAM to Trigram.ZON), pairs.filter { (a, b) -> MatchReckoning.trigramKind(a, b) == null })
        assertEquals(MatchTrigramKind.BDUD_GCOD, MatchReckoning.trigramKind(Trigram.KHEN, Trigram.LI))
        // The five-element years are those whose five forces are five elements, path by path; the two-element list is WB's.
        for (start in listOf(1864, 1924, 1984)) for (y in start until start + 60) {
            val s = YearOfLife.yearSign(y)
            val f = Forces.of(s.element, s.animal)
            val five = setOf(f.vitality, f.body, f.destiny, f.luck, smeBaElement(YearOfLife.yearSmeBa(y))).size == 5
            assertEquals(five, MewaPathYear.FIVE_ELEMENTS in MatchReckoning.mewaPathYears(y), "$y")
        }
        assertEquals(9, MewaPathYear.TWO_ELEMENTS.years.sumOf { it.size })
        assertEquals(listOf(MewaPathYear.FIVE_ELEMENTS), MatchReckoning.mewaPathYears(2020))
        assertEquals(2, MatchReckoning.mewaPath(2026))
        assertEquals(
            listOf(MatchTrigramLine.KHEN_WOMAN_ZON_MAN),
            MatchReckoning.trigramLines(Sign(Element.FIRE, Animal.DRAGON), Trigram.ZON, Sign(Element.IRON, Animal.MONKEY), Trigram.KHEN),
        )
        assertEquals(
            listOf(MatchTrigramLine.WOOD_WOMAN_KHON, MatchTrigramLine.IRON_HUSBAND_GIN),
            MatchReckoning.trigramLines(Sign(Element.IRON, Animal.BIRD), Trigram.GIN, Sign(Element.WOOD, Animal.RABBIT), Trigram.KHON),
        )
        // The notes' example (p. 524): a father of a tiger year, a son of a hare year, the secret way flourishing.
        assertEquals(Sector.FLOURISHING, MatchReckoning.parentStage(ParentWay.SECRET, Sign(Element.WOOD, Animal.TIGER), Animal.RABBIT))
        assertEquals(Sector.IN_THE_WOMB, MatchReckoning.parentStage(ParentWay.OUTER, Sign(Element.WOOD, Animal.TIGER), Animal.RABBIT))
        // Fire takes breath on the pig, so a fire life force on a pig year falls on taking breath.
        assertEquals(Sector.TAKING_BREATH, MatchReckoning.riseDecline(Element.FIRE, Animal.PIG))
        assertEquals(4, Sector.entries.count { matchStageGrade(it) == true })
        assertEquals(4, Sector.entries.count { matchStageGrade(it) == false })
        assertEquals(11, TRIGRAM_CHILDREN.distinctBy { setOf(it.first, it.second) }.size)
        assertEquals(Trigram.ZON to Trigram.ZIN, MatchReckoning.trigramChildren(Trigram.ZIN, Trigram.ZON))
        assertEquals(8, HAND_WAVERS.values.sumOf { it.size })
        assertEquals(true, MatchReckoning.handWaver(Animal.PIG, Trigram.KHEN))
        assertEquals(false, MatchReckoning.handWaver(Animal.DOG, Trigram.KHEN))
        assertEquals(MatchTrigramKind.ONE, MatchReckoning.trigramKind(Trigram.GIN, Trigram.GIN))
        assertEquals(listOf(MatchMewa.SAME, MatchMewa.BYUR_DUS), MatchReckoning.mewaMeetings(2, 2))
        assertEquals(listOf(MatchMewa.SAME), MatchReckoning.mewaMeetings(5, 5))
        assertEquals(listOf(MatchMewa.DEMON), MatchReckoning.mewaMeetings(7, 3))
        assertEquals(listOf(MatchMewa.ENEMY), MatchReckoning.mewaMeetings(3, 7))
        assertEquals(listOf(MatchMewa.MOTHER_SON), MatchReckoning.mewaMeetings(1, 2))
        assertEquals(emptyList<MatchMewa>(), MatchReckoning.mewaMeetings(1, 6))
        assertEquals(listOf(PairList.HEART, PairList.CHILDREN), MatchReckoning.pairLists(Animal.DRAGON, Animal.MONKEY))
        assertEquals(listOf(PairList.PARTING, PairList.DESTRUCTION), MatchReckoning.pairLists(Animal.SHEEP, Animal.DOG))
        assertEquals(listOf(PairLine.DOG_MONKEY), MatchReckoning.pairLines(Animal.DOG, Animal.MONKEY))
        assertEquals(listOf(PairLine.DRAGON_BIRD_MONKEY, PairLine.MONKEY_WITH_DRAGON), MatchReckoning.pairLines(Animal.DRAGON, Animal.MONKEY))
        assertEquals(listOf(PairLine.MONKEY_WITH_DRAGON), MatchReckoning.pairLines(Animal.MONKEY, Animal.DRAGON))
        assertEquals(listOf(PairLine.DOG_WITH_DRAGON, PairLine.DOG_DRAGON), MatchReckoning.pairLines(Animal.DOG, Animal.DRAGON))
        assertEquals(listOf(PairLine.DOG_WITH_DRAGON), MatchReckoning.pairLines(Animal.DRAGON, Animal.DOG))
        assertEquals(Animal.PIG, MatchReckoning.sameAnimal(Sign(Element.WATER, Animal.PIG), Sign(Element.WOOD, Animal.PIG)))
        assertEquals(null, MatchReckoning.sameAnimal(Sign(Element.WATER, Animal.MONKEY), Sign(Element.WOOD, Animal.MONKEY)))
        assertEquals(YearSexes.MIXED, MatchReckoning.sameElement(Sign(Element.WATER, Animal.MOUSE), Sign(Element.WATER, Animal.OX)))
        assertEquals(YearSexes.MALE_MALE, MatchReckoning.sameElement(Sign(Element.IRON, Animal.DRAGON), Sign(Element.IRON, Animal.DOG)))
        assertEquals(null, MatchReckoning.sameElement(Sign(Element.IRON, Animal.DRAGON), Sign(Element.FIRE, Animal.DOG)))
    }

    /** The marriage match's single (p. 355): mother, son and friend agree, the enemy disagrees, the same element agrees for earth and water and is middling for fire, iron and wood; ni sha (p. 359). */
    @Test
    fun `the marriage match's single and ni sha`() {
        assertEquals(MatchMeeting.AGREE, MatchReckoning.meeting(Element.FIRE, Element.WOOD))
        assertEquals(MatchMeeting.AGREE, MatchReckoning.meeting(Element.FIRE, Element.EARTH))
        assertEquals(MatchMeeting.AGREE, MatchReckoning.meeting(Element.FIRE, Element.IRON))
        assertEquals(MatchMeeting.DISAGREE, MatchReckoning.meeting(Element.FIRE, Element.WATER))
        assertEquals(MatchMeeting.MIDDLING, MatchReckoning.meeting(Element.FIRE, Element.FIRE))
        assertEquals(MatchMeeting.AGREE, MatchReckoning.meeting(Element.WATER, Element.WATER))
        assertEquals(NiSha.GOOD, MatchReckoning.niSha(1976, Animal.DRAGON, 1979, Animal.SHEEP))
        assertEquals(NiSha.REVERSED, MatchReckoning.niSha(1979, Animal.SHEEP, 1976, Animal.DRAGON))
        assertNull(MatchReckoning.niSha(1976, Animal.DRAGON, 1978, Animal.HORSE))
    }

    /**
     * The four aspects' mewas (ch. 21, p. 255): WB's fire hare of 1687, life force 4, destiny 1, the luck 2 (the fire
     * snake of 1737), body 7; the *Moonbeams*' wood rat of 1684, body 1, life force 7, destiny 4, its wind-horse the
     * wood tiger of 1674; the notes' four down of a 5, 8 (vol. 2, p. 536).
     */
    @Test
    fun `the four aspects' mewas`() {
        assertEquals(listOf(4, 7, 1, 2), listOf(Force.VITALITY, Force.BODY, Force.DESTINY, Force.LUCK).map { MatchReckoning.aspectMewa(1687, it) })
        assertEquals(1737, MatchReckoning.windHorseYear(1687))
        assertEquals(listOf(7, 1, 4), listOf(Force.VITALITY, Force.BODY, Force.DESTINY).map { MatchReckoning.aspectMewa(1684, it) })
        assertEquals(1674, MatchReckoning.windHorseYear(1684))
        val five = (1900..1910).first { YearOfLife.yearSmeBa(it) == 5 }
        assertEquals(8, MatchReckoning.aspectMewa(five, Force.DESTINY))
        assertEquals(2, MatchReckoning.aspectMewa(five, Force.VITALITY))
    }

    /** The hidden's nine wheels (pp. 357–358; the notes, p. 524) hold each pair of trigrams once, a trigram with itself on the two of fortune. */
    @Test
    fun `the hidden's wheels hold every pair once`() {
        val pairs = Trigram.entries.flatMap { a -> Trigram.entries.filter { it.ordinal >= a.ordinal }.map { a to it } }
        assertEquals(36, pairs.size)
        for ((a, b) in pairs) assertEquals(1, HiddenWheel.entries.count { it.holds(a, b) }, "$a $b")
        assertEquals(HiddenWheel.LUS_CHAD, MatchReckoning.wheel(Trigram.LI, Trigram.KHON))
        assertEquals(HiddenWheel.SROG_TSHO, MatchReckoning.wheel(Trigram.KHAM, Trigram.ZON))
        assertEquals(HiddenWheel.PHYWA_LON, MatchReckoning.wheel(Trigram.GIN, Trigram.GIN))
        assertEquals(HiddenWheel.PHYWA_MNYAM, MatchReckoning.wheel(Trigram.ZIN, Trigram.ZIN))
        // The five: a man's as the two-black, a woman's as the seven-red (p. 357).
        assertEquals(Trigram.KHON, MatchReckoning.mewaTrigram(5, man = true))
        assertEquals(Trigram.DWA, MatchReckoning.mewaTrigram(5, man = false))
        // The deeper hidden draws 1 and 2, 3 and 9, 4 and 8; 5, 6 and 7 stay (p. 359).
        assertEquals(listOf(2, 1, 9, 8, 5, 6, 7, 4, 3), (1..9).map { MatchReckoning.drawn(it) })
    }

    /** The hidden for the fire hare man of 1687 and the wood rat woman of 1684: each aspect's two mewas on their wheel. */
    @Test
    fun `the hidden of two worked years`() {
        val h = MatchReckoning.hidden(1687, 1684)
        assertEquals(listOf(4 to 7, 7 to 1, 1 to 4), h.take(3).map { it.second })
        // Life force 4 and 7: wind and lake, the body cut; body 7 and 1: lake and water, harm; destiny 1 and 4: water and wind, sustaining life.
        assertEquals(listOf(HiddenWheel.LUS_CHAD, HiddenWheel.GNOD_PA, HiddenWheel.SROG_TSHO), h.take(3).map { it.third })
        val d = MatchReckoning.hidden(1687, 1684, deeper = true)
        // The woman's 7 stays, her 1 is drawn to 2, her 4 to 8.
        assertEquals(listOf(4 to 7, 7 to 2, 1 to 8), d.take(3).map { it.second })
    }

    /** Point 7 (p. 359; the notes, p. 525): five counts summed, the larger wins, equal is good; even together agrees. */
    @Test
    fun `the siblings' number`() {
        // 1976, the fire dragon: life force earth 2, body earth 2, destiny fire 4, luck, and the six-white mewa as iron 5.
        val f = Forces.of(Element.FIRE, Animal.DRAGON)
        assertEquals(2 + MatchReckoning.siblingsPebbles(f.body) + 4 + MatchReckoning.siblingsPebbles(f.luck) + 5, MatchReckoning.siblings(1976))
        assertEquals(Element.EARTH, f.vitality)
        assertEquals(SiblingsWin.EQUAL, MatchReckoning.siblingsWin(14, 14))
        assertEquals(SiblingsWin.WOMAN, MatchReckoning.siblingsWin(13, 14))
        assert(MatchReckoning.siblingsAgree(13, 15))
        assert(!MatchReckoning.siblingsAgree(13, 14))
    }

    /** The kinds of spouse (p. 355; reply 113) and the couple's bad-year pebble (the notes, pp. 523–524). */
    @Test
    fun `the kinds of spouse and the bad-year pebble`() {
        assertEquals(SpouseKind.KARMA, MatchReckoning.kind(Element.FIRE, Element.WOOD))
        assertEquals(SpouseKind.KARMA, MatchReckoning.kind(Element.FIRE, Element.EARTH))
        assertEquals(SpouseKind.KIN, MatchReckoning.kind(Element.FIRE, Element.IRON))
        assertEquals(SpouseKind.ENMITY, MatchReckoning.kind(Element.FIRE, Element.WATER))
        assertEquals(SpouseKind.KARMA, MatchReckoning.kind(Element.WATER, Element.WATER))
        assertEquals(SpouseKind.KIN, MatchReckoning.kind(Element.FIRE, Element.FIRE))
        assertEquals(BadYearPebble.BLACK, MatchReckoning.badYearPebble(man = true, woman = true))
        assertEquals(BadYearPebble.SPECKLED_WOMAN, MatchReckoning.badYearPebble(man = false, woman = true))
        assertEquals(BadYearPebble.SPECKLED_MAN, MatchReckoning.badYearPebble(man = true, woman = false))
        assertEquals(BadYearPebble.WHITE, MatchReckoning.badYearPebble(man = false, woman = false))
    }

    /** The line of births' examples, pp. 297–299: for each forefather's year, the child's years that crowd, empty, stay and tear the line. */
    @Test
    fun `the line of births as WB lists it`() {
        fun check(f: Animal, crowd: Animal, empty: Animal, stay: Animal, torn: Animal) {
            assertEquals(LineStage.CROWDED, BirthReckoning.lineStage(f, crowd), "$f crowded")
            assertEquals(LineStage.EMPTY, BirthReckoning.lineStage(f, empty), "$f empty")
            assertEquals(LineStage.STAYING, BirthReckoning.lineStage(f, stay), "$f staying")
            assertEquals(LineStage.TORN, BirthReckoning.lineStage(f, torn), "$f torn")
        }
        check(Animal.HORSE, Animal.MONKEY, Animal.BIRD, Animal.DRAGON, Animal.DOG)
        check(Animal.TIGER, Animal.SNAKE, Animal.HORSE, Animal.OX, Animal.SHEEP)
        check(Animal.BIRD, Animal.PIG, Animal.MOUSE, Animal.SHEEP, Animal.OX)
        check(Animal.MOUSE, Animal.TIGER, Animal.RABBIT, Animal.DOG, Animal.DRAGON)
        for (earth in listOf(Animal.OX, Animal.SHEEP, Animal.DOG, Animal.DRAGON)) check(earth, Animal.TIGER, Animal.RABBIT, Animal.DOG, Animal.DRAGON)
        assertNull(BirthReckoning.lineStage(Animal.HORSE, Animal.HORSE))
    }

    /** The notes' rule for glory and misfortune (vol. 2, p. 476): the parent's the mother of one's own, glory; its son, lustre lost; enemy, misfortune; same, own foot; friend, life taken. */
    @Test
    fun `glory and misfortune by the notes`() {
        assertEquals(DpalByur.GLORY, BirthReckoning.dpalByur(Element.FIRE, Element.WOOD))
        assertEquals(DpalByur.LUSTRE_LOST, BirthReckoning.dpalByur(Element.FIRE, Element.EARTH))
        assertEquals(DpalByur.MISFORTUNE, BirthReckoning.dpalByur(Element.FIRE, Element.WATER))
        assertEquals(DpalByur.OWN_FOOT, BirthReckoning.dpalByur(Element.FIRE, Element.FIRE))
        assertEquals(DpalByur.LIFE_TAKEN, BirthReckoning.dpalByur(Element.FIRE, Element.IRON))
    }

    /** P. 294's examples of the five by the body and by the mewa: a fire body born on zin, and the horse and snake under the four-green, are the parents' god. */
    @Test
    fun `the five by the body and by the mewa`() {
        assertEquals(BirthChild.PARENTS_GOD, BirthReckoning.kind(Element.FIRE, YearOfLife.trigramElement(Trigram.ZIN)))
        assertEquals(BirthChild.PARENTS_GOD, BirthReckoning.kind(Forces.of(Element.FIRE, Animal.HORSE).vitality, smeBaElement(4)))
        assertEquals(BirthChild.DEMON, BirthReckoning.kind(Element.WOOD, Element.IRON))
    }

    /** The thirty paired-year elements' bodies as p. 271–275 names them, pair by pair from the wood mouse. */
    @Test
    fun `the thirty pairs' bodies are the years' bodies`() {
        val bodies = "IRON FIRE WOOD EARTH IRON FIRE WATER EARTH IRON WOOD WATER EARTH FIRE WOOD WATER " +
            "IRON FIRE WOOD EARTH IRON FIRE WATER EARTH IRON WOOD WATER EARTH FIRE WOOD WATER"
        val wb = bodies.split(" ").map { Element.valueOf(it) }
        for (n in 0 until 60) {
            val sign = Sign(Element.entries[n % 10 / 2], Animal.entries[n % 12])
            assertEquals(wb[n / 2], Forces.of(sign.element, sign.animal).body, "body of $sign")
            val first = BirthReckoning.pairFirst(sign)
            assertEquals(Sign(Element.entries[(n - n % 2) % 10 / 2], Animal.entries[(n - n % 2) % 12]), first)
        }
    }

    private fun vitality(a: Animal) = Forces.of(Element.WOOD, a).vitality

    /** P. 294's own count, read on the scan: the horse and snake years on each trigram, and the five demons. */
    @Test
    fun `the five children follow WB's worked count`() {
        for (a in listOf(Animal.HORSE, Animal.SNAKE)) {
            val fire = vitality(a)
            assertEquals(Element.FIRE, fire)
            assertEquals(BirthChild.PARENTS_GOD, BirthReckoning.child(fire, Trigram.ZIN))
            for (corner in listOf(Trigram.KHON, Trigram.KHEN, Trigram.GIN, Trigram.ZON)) assertEquals(BirthChild.CHILDRENS_GLORY, BirthReckoning.child(fire, corner))
            assertEquals(BirthChild.LIFE, BirthReckoning.child(fire, Trigram.LI))
            assertEquals(BirthChild.SPIRIT, BirthReckoning.child(fire, Trigram.DWA))
            assertEquals(BirthChild.DEMON, BirthReckoning.child(fire, Trigram.KHAM))
        }
        // The wood tiger and hare on dwa die at fifteen, iron bird and monkey on li at twelve, fire horse and snake on
        // kham at nine, water mouse and pig on the four corners at six, the earth animals on zin at three.
        val demons = listOf(
            listOf(Animal.TIGER, Animal.RABBIT) to (Trigram.DWA to 15),
            listOf(Animal.BIRD, Animal.MONKEY) to (Trigram.LI to 12),
            listOf(Animal.HORSE, Animal.SNAKE) to (Trigram.KHAM to 9),
            listOf(Animal.MOUSE, Animal.PIG) to (Trigram.GIN to 6),
            listOf(Animal.OX, Animal.DRAGON, Animal.SHEEP, Animal.DOG) to (Trigram.ZIN to 3),
        )
        for ((animals, fall) in demons) for (a in animals) {
            assertEquals(BirthChild.DEMON, BirthReckoning.child(vitality(a), fall.first), "$a")
            assertEquals(fall.second, BirthReckoning.demonAge(vitality(a)), "$a")
        }
    }

    /** Each trigram's element against each life force gives each of the five once per element. */
    @Test
    fun `every life force has one trigram element of each kind`() {
        for (v in Element.entries) {
            val kinds = Trigram.entries.map { YearOfLife.trigramElement(it) }.distinct().map { BirthReckoning.child(v, Trigram.entries.first { t -> YearOfLife.trigramElement(t) == it }) }
            assertEquals(BirthChild.entries.toSet(), kinds.toSet(), "$v")
        }
    }

    /** A man's trigram runs li, khon, dwa, khen, kham, gin, zin, zon from his first year (WB vol. 1, p. 257). */
    @Test
    fun `the father's trigram is his of the year of the birth`() {
        assertEquals(Trigram.LI, BirthReckoning.fatherTrigram(1950, 1950))
        assertEquals(Trigram.ZIN, BirthReckoning.fatherTrigram(1950, 1956))
        assertEquals(Trigram.GIN, BirthReckoning.fatherTrigram(1950, 1955))
        assertEquals(Trigram.KHEN, BirthReckoning.fatherTrigram(1950, 1953))
        assertNull(BirthReckoning.fatherTrigram(1980, 1976))
    }

    /** P. 326's readings, read on the scan, each where it holds and where it does not. */
    @Test
    fun `the parents' trigrams at the birth`() {
        fun omens(natal: Trigram, animal: Animal, gender: Gender?, mother: Animal = Animal.TIGER, father: Trigram? = null) =
            BirthReckoning.omens(natal, animal, gender, mother, father)
        assertEquals(listOf(BirthOmen.FATHER_DIES), omens(Trigram.DWA, Animal.HORSE, Gender.MALE, father = Trigram.ZIN))
        assertEquals(emptyList<BirthOmen>(), omens(Trigram.DWA, Animal.HORSE, Gender.FEMALE, father = Trigram.ZIN))
        assertEquals(listOf(BirthOmen.MOTHER_DIES), omens(Trigram.ZIN, Animal.HORSE, Gender.FEMALE, father = Trigram.GIN))
        // On khen: a son of a dog or dragon mother; a child of a dog or dragon year, either sex; a daughter of a dog
        // mother is not, and on khen every daughter counts hearthstones.
        assertEquals(listOf(BirthOmen.SKY_BAD_FOR_FATHER), omens(Trigram.KHEN, Animal.HORSE, Gender.MALE, mother = Animal.DOG))
        assertEquals(listOf(BirthOmen.SKY_BAD_FOR_FATHER), omens(Trigram.KHEN, Animal.DRAGON, null))
        assertEquals(listOf(BirthOmen.HEARTHS), omens(Trigram.KHEN, Animal.HORSE, Gender.FEMALE, mother = Animal.DOG))
        assertEquals(listOf(BirthOmen.FATHER_SKY_SON_FIRE), omens(Trigram.LI, Animal.HORSE, Gender.MALE, father = Trigram.KHEN))
        // On khon: a daughter of an ox or sheep mother, or an ox or sheep daughter, who also counts hearthstones there.
        assertEquals(listOf(BirthOmen.EARTH_BAD_FOR_MOTHER), omens(Trigram.KHON, Animal.HORSE, Gender.FEMALE, mother = Animal.SHEEP))
        assertEquals(listOf(BirthOmen.EARTH_BAD_FOR_MOTHER, BirthOmen.HEARTHS), omens(Trigram.KHON, Animal.OX, Gender.FEMALE))
        assertEquals(emptyList<BirthOmen>(), omens(Trigram.KHON, Animal.OX, Gender.MALE, mother = Animal.SHEEP))
        // On zon: any child of a dog or dragon mother; a son of a dog or dragon year; a dog daughter counts hearthstones.
        assertEquals(listOf(BirthOmen.WIND_NO_SISTER), omens(Trigram.ZON, Animal.HORSE, null, mother = Animal.DRAGON))
        assertEquals(listOf(BirthOmen.WIND_NO_SISTER), omens(Trigram.ZON, Animal.DOG, Gender.MALE))
        assertEquals(listOf(BirthOmen.HEARTHS), omens(Trigram.ZON, Animal.DOG, Gender.FEMALE))
        // On gin: any child of an ox or sheep mother; an ox or sheep daughter, bad for her brothers.
        assertEquals(listOf(BirthOmen.MOUNTAIN_NO_SISTER), omens(Trigram.GIN, Animal.HORSE, Gender.MALE, mother = Animal.OX))
        assertEquals(listOf(BirthOmen.MOUNTAIN_BAD_FOR_BROTHERS, BirthOmen.HEARTHS), omens(Trigram.GIN, Animal.SHEEP, Gender.FEMALE))
        // The hearthstones by the daughter's year.
        assertEquals(listOf(BirthOmen.HEARTHS), omens(Trigram.KHAM, Animal.PIG, Gender.FEMALE))
        assertEquals(listOf(BirthOmen.HEARTHS), omens(Trigram.ZIN, Animal.BIRD, Gender.FEMALE))
        assertEquals(emptyList<BirthOmen>(), omens(Trigram.KHAM, Animal.BIRD, Gender.FEMALE))
        assertEquals(emptyList<BirthOmen>(), omens(Trigram.LI, Animal.HORSE, Gender.FEMALE))
    }

    /** P. 261's table, read on the scan: every clan's seven kinds of year cover the twelve animals once. */
    @Test
    fun `the special pebble follows the clan's table`() {
        for (c in Clan.entries) {
            val kinds = Animal.entries.groupBy { BirthReckoning.clanYear(c, it) }
            assertEquals(ClanYear.entries.toSet(), kinds.keys, "$c")
            assertEquals(1, kinds.getValue(ClanYear.GREAT_PENALTY).size)
            assertEquals(1, kinds.getValue(ClanYear.SMALL_PENALTY).size)
            // The great penalty is the element's tomb animal, the small its seventh; water's are earth's.
            val tomb = YearOfLife.tomb(YearOfLife.tombKey(c.element))
            assertEquals(listOf(tomb), kinds.getValue(ClanYear.GREAT_PENALTY), "$c")
            assertEquals(listOf(Animal.entries[(tomb.ordinal + 6) % 12]), kinds.getValue(ClanYear.SMALL_PENALTY), "$c")
            // The rest by the life force of the year against the clan's element.
            for (y in listOf(ClanYear.POWER, ClanYear.MOTHER, ClanYear.SON, ClanYear.FRIEND, ClanYear.ENEMY)) for (a in kinds.getValue(y)) {
                val kin = Forces.kinship(c.element, vitality(a))
                val expected = mapOf(Kinship.IDENTITY to ClanYear.POWER, Kinship.MOTHER to ClanYear.MOTHER, Kinship.SON to ClanYear.SON, Kinship.FRIEND to ClanYear.FRIEND, Kinship.ENEMY to ClanYear.ENEMY)
                assertEquals(expected.getValue(kin), y, "$c $a")
            }
        }
        // Rows of the table as printed.
        assertEquals(ClanYear.GREAT_PENALTY, BirthReckoning.clanYear(Clan.KEG, Animal.SHEEP))
        assertEquals(ClanYear.FRIEND, BirthReckoning.clanYear(Clan.KEG, Animal.DRAGON))
        assertEquals(ClanYear.MOTHER, BirthReckoning.clanYear(Clan.SHANG, Animal.DOG))
        assertEquals(ClanYear.ENEMY, BirthReckoning.clanYear(Clan.U, Animal.SHEEP))
        assertEquals(ClanYear.SMALL_PENALTY, BirthReckoning.clanYear(Clan.JI, Animal.DRAGON))
    }

    /** Pp. 275–280's particular readings name the years of each kind; the tiger and hare years run through all five. */
    @Test
    fun `the year's destiny against its life force`() {
        fun kind(e: Element, a: Animal) = BirthReckoning.powerYear(Sign(e, a))
        assertEquals(PowerYear.KHA_YAN, kind(Element.WOOD, Animal.TIGER))
        assertEquals(PowerYear.KHA_YAN, kind(Element.FIRE, Animal.HORSE))
        assertEquals(PowerYear.KHA_YAN, kind(Element.EARTH, Animal.DOG))
        assertEquals(PowerYear.KHONG_NONG, kind(Element.WATER, Animal.RABBIT))
        assertEquals(PowerYear.KHONG_NONG, kind(Element.IRON, Animal.PIG))
        assertEquals(PowerYear.SE_ZHIG, kind(Element.FIRE, Animal.TIGER))
        assertEquals(PowerYear.SE_ZHIG, kind(Element.WATER, Animal.MONKEY))
        assertEquals(PowerYear.DUN_KHUR, kind(Element.IRON, Animal.RABBIT))
        assertEquals(PowerYear.DUN_KHUR, kind(Element.WATER, Animal.SNAKE))
        assertEquals(PowerYear.KHA_RAL, kind(Element.EARTH, Animal.TIGER))
        assertEquals(PowerYear.KHA_RAL, kind(Element.WATER, Animal.DRAGON))
        // Each of the five falls on twelve of the sixty years.
        val counts = (0 until 60).groupingBy { BirthReckoning.powerYear(YearOfLife.signOf(it)) }.eachCount()
        assertEquals(PowerYear.entries.associateWith { 12 }, counts)
    }

    /** P. 283: four first, four middle and four last years. */
    @Test
    fun `the first, middle and last years`() {
        val places = Animal.entries.groupBy { BirthReckoning.yearPlace(it) }
        assertEquals(listOf(Animal.TIGER, Animal.SNAKE, Animal.MONKEY, Animal.PIG), places.getValue(YearPlace.FIRST))
        assertEquals(listOf(Animal.MOUSE, Animal.RABBIT, Animal.HORSE, Animal.BIRD), places.getValue(YearPlace.MIDDLE))
        assertEquals(listOf(Animal.OX, Animal.DRAGON, Animal.SHEEP, Animal.DOG), places.getValue(YearPlace.LAST))
    }

    /** Pp. 281–282: a pair is read where the cutter's element overcomes the other's; the fire dragon's destiny cuts its luck. */
    @Test
    fun `the cutting and tearing among one's own aspects`() {
        // Fire dragon: life force earth, body iron? destiny fire, luck wood.
        val f = Forces.of(Element.FIRE, Animal.DRAGON)
        val cuts = BirthReckoning.cuts(Sign(Element.FIRE, Animal.DRAGON))
        for ((a, b) in cuts) assertEquals(f[b], f[a].overcomes, "$a $b")
        for ((a, b) in BirthReckoning.CUTS - cuts.toSet()) assert(f[a].overcomes != f[b]) { "$a $b" }
        // Over the sixty years every pair WB reads falls somewhere.
        val seen = (0 until 60).flatMap { BirthReckoning.cuts(YearOfLife.signOf(it)) }.toSet()
        assertEquals(BirthReckoning.CUTS.toSet(), seen)
    }

    /** P. 282's group cuts: each holds where every aspect of the group acts on the one, and every one falls in the sixty. */
    @Test
    fun `the group cuts`() {
        val all = (0 until 60).flatMap { n ->
            val sign = YearOfLife.signOf(n)
            val f = Forces.of(sign.element, sign.animal)
            BirthReckoning.groupCuts(sign).onEach { g ->
                for (a in g.by) assertEquals(f[g.on], if (g.nourish) f[a].feeds else f[a].overcomes, "$sign $g $a")
            }
        }.toSet()
        // Over the sixty years, those that occur (a star needs three aspects of one element).
        assert(all.isNotEmpty())
        assert(all.all { it in GroupCut.entries })
    }

    /**
     * Kun dga' rig 'dzin's worked sheet (KD vol. 3, pp. 716–718, docs/sources/corpus/kd/v3-sheets.md): a son of the wood
     * mouse year 1984 born in the water monkey month, his father a wood sheep, his mother of 1958 (an earth dog,
     * whose progressed sign at 27 is the iron horse and trigram dwa), natal mewa seven. Every cell of the hundred
     * and every deciding pebble as the sheet draws them, the enemy's drawn by its meaning.
     */
    @Test
    fun `the hundred pebbles as Kun dga' rig 'dzin's sheet lays them`() {
        val h = BirthReckoning.hundred(
            Sign(Element.WOOD, Animal.MOUSE), 1984, Sign(Element.WATER, Animal.MONKEY), Gender.MALE, 1958, Sign(Element.WOOD, Animal.SHEEP),
        )!!
        assertEquals(Sign(Element.IRON, Animal.HORSE), YearOfLife.logMen(Element.EARTH, Gender.FEMALE, 27))
        // Rows top to bottom, each the four blocks' own, mother, son, friend, enemy: 1 black, 0 white.
        val sheet = listOf(
            "10010 11010 11010 10101",
            "01110 00101 10011 10011",
            "01101 11010 10011 10011",
            "01110 10011 10101 10101",
            "01101 11010 00101 11010",
        )
        UnchangingLuck.entries.forEachIndexed { row, luck ->
            val blocks = sheet[row].split(" ")
            Force.entries.forEachIndexed { b, force ->
                HundredKin.entries.forEachIndexed { k, kin ->
                    val drawn = if (blocks[b][k] == '1') PebbleColour.BLACK else PebbleColour.WHITE
                    assertEquals(drawn, h.cell(luck, force, kin), "$luck $force $kin")
                }
            }
        }
        // The sixth row, drawn white for a good pebble: the enemy's by its meaning.
        val decided = "01111 11011 10010 10010"
        Force.entries.forEachIndexed { b, force ->
            HundredKin.entries.forEachIndexed { k, kin ->
                assertEquals(decided.split(" ")[b][k] == '0', h.good(force, kin), "$force $kin")
            }
        }
        assertEquals(PebbleColour.WHITE, h.deciding(Force.VITALITY, HundredKin.ENEMY))
        // P. 338's sets on the sheet's deciding pebbles: the mothers two and two, the sons mixed, the friends all black,
        // eight white of twenty; no aspect's own and enemy part. Eight good of twenty: the birth weighs bad (p. 349).
        assertEquals(listOf(BirthSet.MOTHERS_HALF, BirthSet.MANY_BLACK, BirthSet.SONS_MIXED, BirthSet.FRIENDS_BLACK), BirthReckoning.sets(h))
        assertEquals(8, BirthReckoning.goodCount(h))
        assertEquals(BirthWhole.BAD, BirthReckoning.whole(h))
        assertNull(BirthReckoning.hundred(Sign(Element.WOOD, Animal.MOUSE), 1984, Sign(Element.WATER, Animal.MONKEY), Gender.MALE, 1958, null))
    }
}
