/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import zanshin.core.texts.gloss

/*
 * The reckoning of the birth (ROADMAP R2, the birth): the parts of WB vol. 1, ch. 22 that are read on the scan and
 * follow from the dates. The natal trigram is [KinReckoning.natalTrigram]'s; set against the birth year's life
 * force it gives the five children of p. 294, and with the father's trigram, the child's sex and both years the
 * readings of the parents' trigrams at the birth on p. 326. docs/sources/year-of-life.md, RD13, quotes each rule.
 */

/**
 * What the one born is by the element of the natal trigram against the element of their birth year's life force
 * (*lha dpal tshe bdud*, p. 294): the trigram the life force's mother, the parents' god; its son, the children's
 * glory; the same, harmony that is life; the element it overcomes, a spirit that can be survived; the one that
 * overcomes it, a demon that cannot.
 */
enum class BirthChild(val wylie: String) {
    PARENTS_GOD("pha ma lha"),
    CHILDRENS_GLORY("bu tsha dpal"),
    LIFE("mthun pa tshe"),
    SPIRIT("thub pa 'dre"),
    DEMON("mi thub bdud");

    val english: String get() = gloss(this)
}

/**
 * The readings of the parents' trigrams at the birth (p. 326) that the dates decide, in WB's order. The trigram a
 * child is born on is the mother's (the natal trigram); the father's is his trigram of the year of the birth. WB's
 * readings that need the order of the siblings, or end "and the like", are left out.
 */
enum class BirthOmen {
    /** A son born while the father is on zin and the mother on dwa: the father soon dies. */
    FATHER_DIES,
    /** A daughter born while the father is on gin and the mother on zin: the mother soon dies. */
    MOTHER_DIES,
    /** On khen, a son of a mother of a dog or dragon year, or a child of a dog or dragon year: bad for the father. */
    SKY_BAD_FOR_FATHER,
    /** A son born on li while the father is on khen: bad for the father. */
    FATHER_SKY_SON_FIRE,
    /** On khon, a daughter of a mother of an ox or sheep year, or a daughter of an ox or sheep year: bad for the mother. */
    EARTH_BAD_FOR_MOTHER,
    /** On zon, any child of a mother of a dog or dragon year, or a son of a dog or dragon year: no sister to call one. */
    WIND_NO_SISTER,
    /** On gin, any child of a mother of an ox or sheep year: no sister to call one, as on zon. */
    MOUNTAIN_NO_SISTER,
    /** On gin, a daughter of an ox or sheep year: bad for the brothers. */
    MOUNTAIN_BAD_FOR_BROTHERS,
    /** A daughter born on one of the trigrams p. 326 lists for her year's animal ([BirthReckoning.hearths]): she sets up house again and again. */
    HEARTHS;

    val english: String get() = gloss(this)
}

object BirthReckoning {
    /** What one born in a year of life force [vitality] on the natal trigram [natal] is (p. 294). */
    fun child(vitality: Element, natal: Trigram): BirthChild = when (Forces.kinship(vitality, YearOfLife.trigramElement(natal))) {
        Kinship.MOTHER -> BirthChild.PARENTS_GOD
        Kinship.SON -> BirthChild.CHILDRENS_GLORY
        Kinship.IDENTITY -> BirthChild.LIFE
        Kinship.FRIEND -> BirthChild.SPIRIT
        Kinship.ENEMY -> BirthChild.DEMON
    }

    /**
     * The age at which p. 294 has a child of a demon die, by the life force [vitality]: wood on dwa at fifteen,
     * iron on li at twelve, fire on kham at nine, water on the four corners at six, earth on zin at three.
     */
    fun demonAge(vitality: Element): Int = when (vitality) {
        Element.WOOD -> 15
        Element.IRON -> 12
        Element.FIRE -> 9
        Element.WATER -> 6
        Element.EARTH -> 3
    }

    /** The father's trigram in the Tibetan year [birthYear] of the birth, for a father born in [fatherBirthYear]; null if not born before. */
    fun fatherTrigram(fatherBirthYear: Int, birthYear: Int): Trigram? {
        val age = YearOfLife.age(fatherBirthYear, birthYear)
        return if (age >= 1) YearOfLife.currentTrigram(Gender.MALE, age) else null
    }

    /**
     * The readings of p. 326 that hold for a child of year animal [animal] and [gender] born on [natal], to a mother
     * of year animal [mother] and a father standing on [father] (null where his year is not known); the readings
     * that ask for the child's sex are left out without it.
     */
    fun omens(natal: Trigram, animal: Animal, gender: Gender?, mother: Animal, father: Trigram?): List<BirthOmen> = buildList {
        val son = gender == Gender.MALE
        val daughter = gender == Gender.FEMALE
        fun dogDragon(a: Animal) = a == Animal.DOG || a == Animal.DRAGON
        fun oxSheep(a: Animal) = a == Animal.OX || a == Animal.SHEEP
        if (son && father == Trigram.ZIN && natal == Trigram.DWA) add(BirthOmen.FATHER_DIES)
        if (daughter && father == Trigram.GIN && natal == Trigram.ZIN) add(BirthOmen.MOTHER_DIES)
        if (natal == Trigram.KHEN && (son && dogDragon(mother) || dogDragon(animal))) add(BirthOmen.SKY_BAD_FOR_FATHER)
        if (son && father == Trigram.KHEN && natal == Trigram.LI) add(BirthOmen.FATHER_SKY_SON_FIRE)
        if (daughter && natal == Trigram.KHON && (oxSheep(mother) || oxSheep(animal))) add(BirthOmen.EARTH_BAD_FOR_MOTHER)
        if (natal == Trigram.ZON && (dogDragon(mother) || son && dogDragon(animal))) add(BirthOmen.WIND_NO_SISTER)
        if (natal == Trigram.GIN && oxSheep(mother)) add(BirthOmen.MOUNTAIN_NO_SISTER)
        if (daughter && natal == Trigram.GIN && oxSheep(animal)) add(BirthOmen.MOUNTAIN_BAD_FOR_BROTHERS)
        if (daughter && natal in hearths(animal)) add(BirthOmen.HEARTHS)
    }

    /**
     * The trigrams on which a daughter of year animal [animal] "counts hearthstones" (p. 326): the horse, snake,
     * mouse and pig on kham; the tiger, hare, bird and monkey on dwa or zin; the ox and sheep on khon or gin; the
     * dog and dragon on zon; any on khen.
     */
    fun hearths(animal: Animal): Set<Trigram> = setOf(Trigram.KHEN) + when (animal) {
        Animal.HORSE, Animal.SNAKE, Animal.MOUSE, Animal.PIG -> setOf(Trigram.KHAM)
        Animal.TIGER, Animal.RABBIT, Animal.BIRD, Animal.MONKEY -> setOf(Trigram.DWA, Trigram.ZIN)
        Animal.OX, Animal.SHEEP -> setOf(Trigram.KHON, Trigram.GIN)
        Animal.DOG, Animal.DRAGON -> setOf(Trigram.ZON)
    }
}
