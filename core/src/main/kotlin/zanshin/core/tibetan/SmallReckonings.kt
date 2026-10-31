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

/** A check of a round (pp. 18–19): the year [step] from the year reached (inclusive), its [other] aspect against the person's [own]; [key] names its reading. */
data class RoundCheck(val step: Int, val own: Force, val other: Force, val key: String)

/**
 * The rounds by age of pp. 18–19 (ROADMAP R7, WB vol. 2, ch. 27): each starts from its [animal] in the element standing
 * as [relation] to the person's power, and is counted by the years of age, the start the first, [down] or up; a man
 * reckons the man's round ([male] true), a woman the woman's, both the rounds with [male] null. A round with [checks]
 * reads each check that cuts inward; the others read a sign the year reached shows ([SmallReckonings.roundSign]).
 */
enum class Round(val animal: Animal, val relation: Kinship, val male: Boolean?, val down: Boolean, val checks: List<RoundCheck> = emptyList()) {
    /** The wheel's full depth (འཁོར་ལོ་གཏིང་རྫོགས), the man's: from the ox of his own element, down. */
    WHEEL_MALE(
        Animal.OX, Kinship.IDENTITY, true, true,
        listOf(RoundCheck(0, Force.DESTINY, Force.DESTINY, "WHEEL.DESTINY"), RoundCheck(11, Force.BODY, Force.BODY, "WHEEL.BODY"), RoundCheck(-5, Force.VITALITY, Force.VITALITY, "WHEEL.VITALITY")),
    ),

    /** The woman's: piled up from the sheep of the enemy; "the results as for a man". */
    WHEEL_FEMALE(
        Animal.SHEEP, Kinship.ENEMY, false, false,
        listOf(RoundCheck(0, Force.DESTINY, Force.DESTINY, "WHEEL.DESTINY"), RoundCheck(5, Force.VITALITY, Force.VITALITY, "WHEEL.VITALITY"), RoundCheck(-9, Force.BODY, Force.BODY, "WHEEL.BODY")),
    ),

    /** The gods' and demons' trade, the man's: from the hare of his own friend element, down. */
    TRADE_MALE(Animal.RABBIT, Kinship.FRIEND, true, true, listOf(RoundCheck(0, Force.VITALITY, Force.VITALITY, "TRADE.VITALITY"), RoundCheck(-6, Force.BODY, Force.BODY, "TRADE.BODY"))),

    /** The woman's: from the hare of the mother, up. */
    TRADE_FEMALE(Animal.RABBIT, Kinship.MOTHER, false, false, listOf(RoundCheck(0, Force.VITALITY, Force.VITALITY, "TRADE.VITALITY"), RoundCheck(-6, Force.BODY, Force.BODY, "TRADE.BODY"))),

    /** The peacock with crossed necks, the man's: from the snake of his own element, down; the life force cut, the bird of ruin rides too. */
    PEACOCK_MALE(
        Animal.SNAKE, Kinship.IDENTITY, true, true,
        listOf(RoundCheck(0, Force.VITALITY, Force.VITALITY, "PEACOCK.VITALITY"), RoundCheck(0, Force.BODY, Force.BODY, "PEACOCK"), RoundCheck(0, Force.DESTINY, Force.DESTINY, "PEACOCK")),
    ),

    /** The woman's, "reckoned as above": cut chiefly by the power; the power cutting her life force, the bird of ruin. */
    PEACOCK_FEMALE(
        Animal.SNAKE, Kinship.IDENTITY, false, true,
        listOf(RoundCheck(0, Force.DESTINY, Force.DESTINY, "PEACOCK.FEMALE"), RoundCheck(0, Force.VITALITY, Force.DESTINY, "RUIN_BIRD.FEMALE")),
    ),

    /** The yungdrung's five rounds: from the snake of the friend, down; the year reached against the power, the 5th up against the life force. */
    YUNGDRUNG(Animal.SNAKE, Kinship.FRIEND, null, true),

    /** The yellow lord of men, the man's: from the bird of the friend, down. */
    YELLOW_LORD_MALE(Animal.BIRD, Kinship.FRIEND, true, true),

    /** The woman's: from the pig of the mother, up. */
    YELLOW_LORD_FEMALE(Animal.PIG, Kinship.MOTHER, false, false),

    /** The cock's black trail, the man's: from the ox of his own element, down by his own years. */
    BLACK_TRAIL_MALE(Animal.OX, Kinship.IDENTITY, true, true),

    /** The woman's: from the ox of her own friend element, down. */
    BLACK_TRAIL_FEMALE(Animal.OX, Kinship.FRIEND, false, true),

    /** The rhinoceros year, the outer, the man's: from the dog of his own element, down. */
    RHINO_MALE(Animal.DOG, Kinship.IDENTITY, true, true),

    /** The woman's: down from the horse of her own son. */
    RHINO_FEMALE(Animal.HORSE, Kinship.SON, false, true);

    val english: String get() = gloss(this)

    /** Whether one of [gender] reckons this round. */
    fun reckonedBy(gender: Gender): Boolean = male == null || male == (gender == Gender.MALE)
}

/** A round reckoned: its start, the year reached, the cuts of its checks, and the sign it reads (a [RoundCheck.key] or a sign key), if any. */
data class RoundReading(val round: Round, val start: Sign, val reached: Sign, val cut: List<RoundCheck>, val sign: String?)

/** The four executioners, the earth animals (the golden turtle's turtle years, WB vol. 2, p. 20). */
val EXECUTIONERS = setOf(Animal.DRAGON, Animal.DOG, Animal.OX, Animal.SHEEP)

object Rounds {
    /** Where [round] starts for one born in [birth]. */
    fun start(round: Round, birth: Sign): Sign = Sign(Circles.element(birth.forces.destiny, round.relation), round.animal)

    /**
     * [round] for one born in [birth] in the [age]th year of age: the start counted by the years of age, the start the
     * first; each check's year counted from the year reached, inclusively; the sign read off the year reached.
     */
    fun reckon(round: Round, birth: Sign, age: Int): RoundReading {
        val start = start(round, birth)
        val at = YearOfLife.sexagenary(start) + if (round.down) -(age - 1) else age - 1
        val reached = YearOfLife.signOf(at)
        val own = birth.forces
        val cut = round.checks.filter { c -> SmallReckonings.cutsInward(own[c.own], YearOfLife.signOf(at + c.step).forces[c.other]) }
        return RoundReading(round, start, reached, cut, sign(round, birth, at))
    }

    /**
     * The sign a round without checks reads (pp. 18–19), only as the text prints it: the yungdrung destroyed, a
     * person whose power and life force are both water, the year reached cutting the power and its 5th up the life
     * force, both earth; the power lost, a wood person reaching the earth ox or sheep; the black trail, an ox year or an
     * earth year reached; the rhinoceros's horn broken, an earth year reached; the woman's, a year of the four
     * executioners or of iron or water reached.
     */
    private fun sign(round: Round, birth: Sign, at: Int): String? {
        val reached = YearOfLife.signOf(at)
        val own = birth.forces
        return when (round) {
            Round.YUNGDRUNG -> "YUNGDRUNG".takeIf {
                own.destiny == Element.WATER && own.vitality == Element.WATER &&
                    reached.forces.destiny == Element.EARTH && YearOfLife.signOf(at + 4).forces.vitality == Element.EARTH
            }
            Round.YELLOW_LORD_MALE, Round.YELLOW_LORD_FEMALE -> "YELLOW_LORD".takeIf {
                birth.element == Element.WOOD && reached.element == Element.EARTH && reached.animal in setOf(Animal.OX, Animal.SHEEP)
            }
            Round.BLACK_TRAIL_MALE, Round.BLACK_TRAIL_FEMALE -> "BLACK_TRAIL".takeIf { reached.animal == Animal.OX || reached.element == Element.EARTH }
            Round.RHINO_MALE -> "RHINO".takeIf { reached.element == Element.EARTH }
            Round.RHINO_FEMALE -> "RHINO.FEMALE".takeIf { reached.animal in EXECUTIONERS || reached.element == Element.IRON || reached.element == Element.WATER }
            else -> null
        }
    }

    /** The rounds one of [gender] reckons, born in [birth], in the [age]th year of age. */
    fun all(birth: Sign, gender: Gender, age: Int): List<RoundReading> = Round.entries.filter { it.reckonedBy(gender) }.map { reckon(it, birth, age) }

    /** The rhinoceros's inner sign (p. 19), a man's: the year's trigram on the fire or the sky trigram. */
    fun rhinoInner(trigram: Trigram): Boolean = trigram == Trigram.LI || trigram == Trigram.KHEN
}
