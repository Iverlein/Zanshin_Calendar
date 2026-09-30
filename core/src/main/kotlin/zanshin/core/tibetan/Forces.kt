/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import zanshin.core.texts.gloss

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
enum class Force(val wylie: String) {
    VITALITY("srog"),
    BODY("lus"),
    DESTINY("dbang thang"),
    LUCK("klung rta");

    val english: String get() = gloss(this)
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
enum class Kinship {
    MOTHER,
    FRIEND,
    IDENTITY,
    SON,
    ENEMY;

    val english: String get() = gloss(this)
}

/** Noughts (white pebbles) and crosses (black pebbles). */
data class Pebbles(val white: Int, val black: Int) {
    /** As the schematic charts write them: noughts, then crosses. */
    override fun toString(): String = "○".repeat(white) + "×".repeat(black)
}

/** One aspect of the birth year against the same aspect of a year, month or day. */
data class ForceContrast(val force: Force, val own: Element, val other: Element) {
    val kinship: Kinship get() = Forces.kinship(own, other)
    val pebbles: Pebbles get() = Forces.pebbles(kinship, own)
}

/** An element with an animal: the sign of a year, a month or a lunar date. */
data class Sign(val element: Element, val animal: Animal) {
    val forces: YearForces get() = Forces.of(element, animal)
}

/** The signs the elemental divination reads for one day: its year, its month and its lunar date. */
data class DaySigns(val year: Sign, val month: Sign, val date: Sign)

/**
 * One of the twelve two-hour periods of a Tibetan day: its sign, and when it
 * starts in clock time, minutes after midnight (the mouse hour's 23:00 is
 * 1380); it lasts 120 minutes.
 */
data class HourSign(val sign: Sign, val startMinute: Int)

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

    /**
     * The destiny element of a month, by its animal, from the year's destiny
     * element: tiger, hare, mouse and ox months are its son; dragon and snake
     * its friend; horse and sheep its enemy; monkey and bird its mother; dog
     * and pig the same element (Gyurme Dorje, p. 90 and Table 2.5).
     */
    fun monthElement(yearElement: Element, monthAnimal: Animal): Element = when (monthAnimal) {
        Animal.TIGER, Animal.RABBIT, Animal.MOUSE, Animal.OX -> yearElement.feeds
        Animal.DRAGON, Animal.SNAKE -> yearElement.overcomes
        Animal.HORSE, Animal.SHEEP -> Element.entries.first { it.overcomes == yearElement }
        Animal.MONKEY, Animal.BIRD -> Element.entries.first { it.feeds == yearElement }
        Animal.DOG, Animal.PIG -> yearElement
    }

    /**
     * The destiny element of lunar date [date]: the dates run through the
     * elements from the son of the month's, so the 1st and 6th are its son,
     * the 5th and 10th the month's own element (p. 90). A doubled date repeats
     * the element, a skipped one is passed over.
     */
    fun dateElement(monthElement: Element, date: Int): Element = Element.entries[(monthElement.ordinal + date) % 5]

    /** The animals of the day's two-hour periods, from daybreak: the hare hour first, the tiger hour last. */
    val HOUR_ANIMALS: List<Animal> = List(12) { Animal.entries[(Animal.RABBIT.ordinal + it) % 12] }

    /**
     * An hour's destiny element from the lunar date's, by the hour's animal
     * (White Beryl; Gyurme Dorje p. 90, Table 2.7): hare, monkey and ox hours
     * take its son; dragon, bird and tiger its friend; snake and dog its enemy;
     * horse and pig its mother; sheep and mouse the same element.
     */
    fun hourElement(dateElement: Element, hourAnimal: Animal): Element = when (hourAnimal) {
        Animal.RABBIT, Animal.MONKEY, Animal.OX -> dateElement.feeds
        Animal.DRAGON, Animal.BIRD, Animal.TIGER -> dateElement.overcomes
        Animal.SNAKE, Animal.DOG -> Element.entries.first { it.overcomes == dateElement }
        Animal.HORSE, Animal.PIG -> Element.entries.first { it.feeds == dateElement }
        Animal.SHEEP, Animal.MOUSE -> dateElement
    }

    /**
     * The twelve hours of the day whose lunar date is [date], from the hare hour
     * at 05:00 clock time to the tiger hour ending at 05:00 the next morning:
     * "the first astrological period of the day begins at dawn … standardly
     * taken as from 5 to 7 o'clock wristwatch-time" (Berzin, Details of Tibetan
     * Astrology 1).
     */
    fun hours(date: Sign): List<HourSign> = HOUR_ANIMALS.mapIndexed { i, animal ->
        HourSign(Sign(hourElement(date.element, animal), animal), (5 * 60 + i * 120) % (24 * 60))
    }

    /**
     * The year, month and lunar-date signs of [day]. The month's animal is its
     * Phugpa one (the 3rd month a horse, as in the book's chart 8.1); the
     * date's animal runs from the tiger in male months and the monkey in
     * female ones, which is [TibetanDay.lunarDayAnimal].
     */
    fun signs(day: TibetanDay): DaySigns {
        val year = Sign(day.yearElement, day.yearAnimal)
        val monthAnimal = day.monthNames.animal
        val month = Sign(monthElement(day.yearElement, monthAnimal), monthAnimal)
        return DaySigns(year, month, Sign(dateElement(month.element, day.day), day.lunarDayAnimal))
    }
}
