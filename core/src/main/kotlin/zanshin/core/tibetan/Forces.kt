/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

/*
 * The four elemental aspects of a year and the yearly pebble reading of the
 * elemental divination ('byung rtsis). Rules from the White Beryl (f. 156a/b,
 * f. 158a; the pebbles ff. 248b–249a) with Lo-chen Dharmaśrī's Moonbeams
 * (ff. 5b–6b, f. 28a/b), as edited by Gyurme Dorje, Tibetan Elemental
 * Divination Paintings (2001), pp. 20, 64, 68–85 and 227–228.
 */

/** The element this one feeds: wood feeds fire, fire earth, earth iron, iron water, water wood. */
val Element.feeds: Element get() = Element.entries[(ordinal + 1) % 5]

/** The element this one overcomes: wood overcomes earth, earth water, water fire, fire iron, iron wood. */
val Element.overcomes: Element get() = Element.entries[(ordinal + 2) % 5]

/** The four elemental aspects of a year, in the order the charts give them. */
enum class Force(val wylie: String, val english: String) {
    VITALITY("srog", "vitality"),
    BODY("lus", "body"),
    DESTINY("dbang thang", "destiny"),
    LUCK("klung rta", "luck"),
}

/** The elements of one year's four aspects. */
data class YearForces(val vitality: Element, val body: Element, val destiny: Element, val luck: Element) {
    operator fun get(force: Force): Element = when (force) {
        Force.VITALITY -> vitality
        Force.BODY -> body
        Force.DESTINY -> destiny
        Force.LUCK -> luck
    }
}

/**
 * What another element is to one's own: its mother feeds it, its son is fed
 * by it, its friend is overcome by it, its enemy overcomes it. For a wood
 * person, water years are mother years and iron years enemy years.
 */
enum class Kinship(val english: String) {
    MOTHER("mother"),
    FRIEND("friend"),
    IDENTITY("identity"),
    SON("son"),
    ENEMY("enemy"),
}

/** Noughts (white pebbles) and crosses (black pebbles). */
data class Pebbles(val white: Int, val black: Int) {
    /** As the schematic charts write them: noughts, then crosses. */
    override fun toString(): String = "○".repeat(white) + "×".repeat(black)
}

/** One aspect of the birth year against the same aspect of the present year. */
data class ForceContrast(val force: Force, val own: Element, val year: Element) {
    val kinship: Kinship get() = Forces.kinship(own, year)
    val pebbles: Pebbles get() = Forces.pebbles(kinship, own)
}

object Forces {
    /**
     * The four aspects of the year of [element] and [animal]. Vitality is the
     * element of the animal's direction; destiny is the element of the year;
     * luck goes by the animal's triad; body comes from how the year's element
     * stands to a key element of the animal, and each male–female pair of
     * years shares one.
     */
    fun of(element: Element, animal: Animal): YearForces {
        val vitality = when (animal) {
            Animal.TIGER, Animal.RABBIT -> Element.WOOD
            Animal.SNAKE, Animal.HORSE -> Element.FIRE
            Animal.MONKEY, Animal.BIRD -> Element.IRON
            Animal.MOUSE, Animal.PIG -> Element.WATER
            Animal.OX, Animal.DRAGON, Animal.SHEEP, Animal.DOG -> Element.EARTH
        }
        val luck = when (animal) {
            Animal.TIGER, Animal.HORSE, Animal.DOG -> Element.IRON
            Animal.MOUSE, Animal.DRAGON, Animal.MONKEY -> Element.WOOD
            Animal.BIRD, Animal.OX, Animal.SNAKE -> Element.WATER
            Animal.PIG, Animal.SHEEP, Animal.RABBIT -> Element.FIRE
        }
        val key = when (animal) {
            Animal.TIGER, Animal.RABBIT, Animal.BIRD, Animal.MONKEY -> Element.WATER
            Animal.OX, Animal.SHEEP, Animal.HORSE, Animal.MOUSE -> Element.WOOD
            Animal.DOG, Animal.DRAGON, Animal.PIG, Animal.SNAKE -> Element.IRON
        }
        val body = when (key) {
            element -> Element.IRON
            element.feeds -> Element.WOOD
            element.overcomes -> Element.EARTH
            else -> if (key.feeds == element) Element.WATER else Element.FIRE
        }
        return YearForces(vitality, body, element, luck)
    }

    /** What [other] is to [own]. */
    fun kinship(own: Element, other: Element): Kinship = when (other) {
        own -> Kinship.IDENTITY
        own.feeds -> Kinship.SON
        own.overcomes -> Kinship.FRIEND
        else -> if (other.feeds == own) Kinship.MOTHER else Kinship.ENEMY
    }

    /**
     * The pebbles of a kinship: mother three white, friend two white, son one
     * white and one black, enemy two black; identity one white for earth or
     * water, one black for wood, fire or iron.
     */
    fun pebbles(kinship: Kinship, own: Element): Pebbles = when (kinship) {
        Kinship.MOTHER -> Pebbles(3, 0)
        Kinship.FRIEND -> Pebbles(2, 0)
        Kinship.SON -> Pebbles(1, 1)
        Kinship.ENEMY -> Pebbles(0, 2)
        Kinship.IDENTITY -> if (own == Element.EARTH || own == Element.WATER) Pebbles(1, 0) else Pebbles(0, 1)
    }

    /** Each aspect of the birth year against the same aspect of the present year. */
    fun contrast(birth: YearForces, year: YearForces): List<ForceContrast> =
        Force.entries.map { ForceContrast(it, birth[it], year[it]) }
}
