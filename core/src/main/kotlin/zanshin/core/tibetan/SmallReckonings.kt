/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import zanshin.core.texts.gloss

/*
 * The small reckonings of ch. 27 (རྩིས་ཕྲན, rtsis phran; ROADMAP R6; WB vol. 2, pp. 16–17 and 20;
 * docs/sources/corpus/wb/ch27.md): what a year does to one person by their birth year, their trigram of the year and
 * the sky year risen, apart from the circles. The regent's replies 141 and 142 keep them apart from the essentials so as
 * not to scatter them, not to give them up (claims/ch27-small-reckonings-apart.md).
 */

/**
 * The four starting points drawn from the limbs of the secret reckoning (WB vol. 2, p. 17): each starts from its
 * [animal] in the element that stands as [relation] to the person's power («ཁམས», the power's element, as the circles
 * and the notes take it, question 479; «ཁམས་མཐུན», the agreeing element, the same as the thirty's), and is counted down
 * by the years of age.
 */
enum class StartingPoint(val animal: Animal, val relation: Kinship) {
    /** For a great spiritual friend, a great chief: "which king falls"; within ten months. */
    SPIRITUAL_FRIEND(Animal.MOUSE, Kinship.IDENTITY),

    /** For ministers and those of the lord's lineage; within nine months. */
    MINISTERS(Animal.TIGER, Kinship.FRIEND),

    /** For soldiers who hold the banner, the young men's war reckoning; within ten months. */
    SOLDIERS(Animal.DRAGON, Kinship.MOTHER),

    /** The weapons, for the chief of a household and for all. */
    WEAPONS(Animal.HORSE, Kinship.IDENTITY);

    val english: String get() = gloss(this)
}

/** One of a starting point's four checks: the year [steps] from the year reached (inclusive, so 18th is 17 on) against [force]. */
enum class StartingCheck(val steps: Int, val force: Force) {
    /** The eighteenth upward against the life force: cut inward, the rope of the sky is cut. */
    ROPE(17, Force.VITALITY),

    /** The tenth upward against the power: cut inward, the god has sold oneself. */
    SOLD(9, Force.DESTINY),

    /** The eighteenth downward against the luck: cut inward, the supporting dagger is pulled out. */
    DAGGER(-17, Force.LUCK),

    /** The tenth downward against the body: cut inward, the ogre has bought the man. */
    BOUGHT(-9, Force.BODY);

    val english: String get() = gloss(this)
}

/** A starting point reckoned: where it starts, the year the age reaches counting down, and each check's year and cut. */
data class StartingReading(val point: StartingPoint, val start: Sign, val reached: Sign, val checks: Map<StartingCheck, Sign>, val cut: Set<StartingCheck>)

/**
 * The la's wandering (WB vol. 2, p. 16, read on the scan and on the Taikhang and Zhol prints): in a sky year of one
 * triad, the la of one born in one animal of it wanders in a direction, and enemies rise from there. The snake's and
 * the horse's both wander north, the rat's and the pig's both south, as every print reads (question 363).
 */
enum class LaWander(val born: Animal, val direction: Direction) {
    MOUSE(Animal.MOUSE, Direction.SOUTH),
    SNAKE(Animal.SNAKE, Direction.NORTH),
    HORSE(Animal.HORSE, Direction.NORTH),
    PIG(Animal.PIG, Direction.SOUTH),
}

/** The la and life in decline (pp. 16–17): in a sky year of one triad, one born in [born] declines, with what to avoid. */
enum class LaDecline(val born: Animal) { TIGER(Animal.TIGER), MONKEY(Animal.MONKEY), BIRD(Animal.BIRD) }

/** The inner end of the womb (p. 20): born on a trigram, the year's trigram back on it, in the sky year of its animals. */
enum class WombEnd(val trigram: Trigram, val risen: Set<Animal>) {
    LI(Trigram.LI, setOf(Animal.HORSE, Animal.SNAKE)),
    KHON(Trigram.KHON, setOf(Animal.SHEEP)),
}

object SmallReckonings {
    /** Where [point] starts for one born in [birth]: its animal in the element its relation names against the power. */
    fun start(point: StartingPoint, birth: Sign): Sign = Sign(Circles.element(birth.forces.destiny, point.relation), point.animal)

    /** Whether [year]'s [force] cuts the person's inward: its element overcomes theirs (the moment's rule, `Moment.cutsInward`). */
    fun cutsInward(own: Element, other: Element): Boolean = Forces.kinship(own, other) == Kinship.ENEMY

    /**
     * [point] for one born in [birth] in the [age]th year of age: the start counted down through the sixty by the years
     * of age, the start the first (as the circles count up, D19), then each check counted from the year reached, the
     * year reached the first, its aspect fighting the person's like with like.
     */
    fun reckon(point: StartingPoint, birth: Sign, age: Int): StartingReading {
        val start = start(point, birth)
        val at = YearOfLife.sexagenary(start) - (age - 1)
        val reached = YearOfLife.signOf(at)
        val checks = StartingCheck.entries.associateWith { YearOfLife.signOf(at + it.steps) }
        val cut = checks.filter { (c, s) -> cutsInward(birth.forces[c.force], s.forces[c.force]) }.keys
        return StartingReading(point, start, reached, checks, cut)
    }

    /** All four starting points for one born in [birth] in the [age]th year of age. */
    fun startingPoints(birth: Sign, age: Int): List<StartingReading> = StartingPoint.entries.map { reckon(it, birth, age) }

    /** Whose la wanders in the sky year of [risen], if it is the one born in [born]'s. */
    fun laWander(born: Animal, risen: Animal): LaWander? =
        LaWander.entries[risen.ordinal % 4].takeIf { it.born == born }

    /** Whose la and life decline in the sky year of [risen], if it is the one born in [born]'s; the horse's triad names none. */
    fun laDecline(born: Animal, risen: Animal): LaDecline? = when (risen.ordinal % 4) {
        0 -> LaDecline.TIGER
        1 -> LaDecline.MONKEY
        3 -> LaDecline.BIRD
        else -> null
    }?.takeIf { it.born == born }

    /**
     * The flesh's secret reckoning (p. 16): the year's trigram on the grave of the person's element, wood on the earth
     * trigram, fire on the sky, iron on the mountain, earth and water on the wind, the same places as the year of age's
     * tomb sign (WB vol. 1, p. 415, `YearOfLife.tombTrigram`, of the vitality). The element of the grave entered, or null.
     */
    fun graveEntered(vitality: Element, trigram: Trigram): Element? = vitality.takeIf { YearOfLife.tombTrigram(it) == trigram }

    /**
     * Whether a grave of [grave] is gentle for one of [gender] (p. 16): wood gentle for a man, bad for a woman; fire
     * gentle for a woman, bad for a man; iron bad for a man, fit for a woman; earth bad for a woman, fit for a man. The
     * water grave is not named: null.
     */
    fun graveGentle(grave: Element, gender: Gender): Boolean? = when (grave) {
        Element.WOOD, Element.EARTH -> gender == Gender.MALE
        Element.FIRE, Element.IRON -> gender == Gender.FEMALE
        Element.WATER -> null
    }

    /** The end of the womb reached (p. 20), born on [natal], the year's trigram [trigram], in the sky year of [risen]; null if not. */
    fun wombEnd(natal: Trigram, trigram: Trigram, risen: Animal): WombEnd? =
        WombEnd.entries.firstOrNull { it.trigram == natal && it.trigram == trigram && risen in it.risen }

    /**
     * A birth by the sky year's power (p. 17): a son or daughter born in an iron, earth, wood or fire year. The water
     * year's son rests on a line not settled («ནོར་གྱི་ཕྱུགས་མ་མ་སྐྱེས་ན») and its daughter is not named: false for water.
     */
    fun birthPower(element: Element): Boolean = element != Element.WATER
}
