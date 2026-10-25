/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import zanshin.core.texts.gloss

/*
 * The thirteen circles (དཔྱད་ལྡིང, dpyad lding; ROADMAP R2u; WB vol. 2, ch. 27, pp. 3–21; docs/sources/corpus/wb/ch27.md,
 * RD38, RD44, RD47): each circle starts from a year animal in an element set against the reckoned person's power
 * (the notes, vol. 2, p. 533), is counted upward by the years lived for man and woman alike (the regent's reply 139),
 * inclusively, the starting year the first (the owner, 2026-10-25, D19), and fights the year reached against the
 * person's own aspects, one pebble each. The fifth pebble, the mewa, is left out until how its two mewas are joined is
 * read (D20, open question 368).
 */

/** The thirteen circles in WB's order, each with its starting animal and the element's relation to the person's power. */
enum class Circle(val start: Animal, val relation: Kinship) {
    KING(Animal.BIRD, Kinship.ENEMY),
    SPIRITUAL_FRIENDS(Animal.RABBIT, Kinship.ENEMY),
    MANTRINS(Animal.SNAKE, Kinship.FRIEND),
    BON_PRIESTS(Animal.DRAGON, Kinship.MOTHER),
    MINISTERS(Animal.OX, Kinship.FRIEND),
    YOUNG_MEN(Animal.TIGER, Kinship.ENEMY),
    WOMEN(Animal.MOUSE, Kinship.MOTHER),
    CHILDREN(Animal.TIGER, Kinship.ENEMY),
    YOUNG_WOMEN(Animal.DOG, Kinship.MOTHER),

    /** Roughly from the mother's bird; WB counts it finely by the four nails, from the servant's own year ([Circles.servantStart]). */
    SERVANTS(Animal.BIRD, Kinship.MOTHER),
    LAND(Animal.PIG, Kinship.ENEMY),
    STORED_GOODS(Animal.PIG, Kinship.FRIEND),
    LIVESTOCK(Animal.OX, Kinship.MOTHER);

    val english: String get() = gloss(this)
}

/** One circle reckoned for one person in one year: its starting year, the year reached, and a pebble for each aspect. */
data class CircleReading(val circle: Circle, val start: Sign, val reached: Sign, val pebbles: Map<Force, PebbleColour>)

object Circles {
    /** The element standing in [relation] to [power]: the enemy the one that overcomes it, the friend the one it overcomes. */
    fun element(power: Element, relation: Kinship): Element = Element.entries.first { Forces.kinship(power, it) == relation }

    /**
     * The tenth circle's fine start by the four nails (WB vol. 2, pp. 10–11; the regent's reply 140, the servant's own
     * year): one born tiger, horse or dog from the tiger of the friend element; rat, dragon or monkey from the dragon
     * of the mother; pig, sheep or hare from the pig of the same power; bird, ox or snake from the bird of the son.
     */
    fun servantStart(birth: Sign): Sign {
        val power = birth.forces.destiny
        return when (birth.animal) {
            Animal.TIGER, Animal.HORSE, Animal.DOG -> Sign(element(power, Kinship.FRIEND), Animal.TIGER)
            Animal.MOUSE, Animal.DRAGON, Animal.MONKEY -> Sign(element(power, Kinship.MOTHER), Animal.DRAGON)
            Animal.PIG, Animal.SHEEP, Animal.RABBIT -> Sign(power, Animal.PIG)
            Animal.BIRD, Animal.OX, Animal.SNAKE -> Sign(element(power, Kinship.SON), Animal.BIRD)
        }
    }

    /** Where [circle] starts for one born in [birth]: its animal in the element its relation names against the power. */
    fun start(circle: Circle, birth: Sign): Sign =
        if (circle == Circle.SERVANTS) servantStart(birth) else Sign(element(birth.forces.destiny, circle.relation), circle.start)

    /**
     * A pebble of the circles (WB vol. 2, p. 3: «བུ་དགྲ་ཤིང་ལྕགས་མེ་གསུམ་པོ། །ནང་ཕྲད་ནག་པོ»): the year reached's aspect [other]
     * met by the person's [own] as mother or friend, or earth or water by itself, white; as son or enemy, or wood,
     * iron or fire by itself, black. No speckled pebble.
     */
    fun colour(own: Element, other: Element): PebbleColour = when (Forces.kinship(own, other)) {
        Kinship.MOTHER, Kinship.FRIEND -> PebbleColour.WHITE
        Kinship.SON, Kinship.ENEMY -> PebbleColour.BLACK
        Kinship.IDENTITY -> if (own == Element.EARTH || own == Element.WATER) PebbleColour.WHITE else PebbleColour.BLACK
    }

    /** [circle] for one born in [birth] in the [age]th year of age: the start counted upward through the sixty, inclusively (D19). */
    fun reckon(circle: Circle, birth: Sign, age: Int): CircleReading {
        val start = start(circle, birth)
        val reached = YearOfLife.signOf(YearOfLife.sexagenary(start) + age - 1)
        val own = birth.forces
        val there = reached.forces
        return CircleReading(circle, start, reached, Force.entries.associateWith { colour(own[it], there[it]) })
    }

    /** All thirteen circles for one born in [birth] in the [age]th year of age. */
    fun all(birth: Sign, age: Int): List<CircleReading> = Circle.entries.map { reckon(it, birth, age) }
}
