/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import zanshin.core.texts.gloss

/*
 * What the Health screen reckons (SPEC §10.10, ROADMAP H1; docs/sources/health.md): the day's mansion
 * as a quarter of the person's element, the grave mansions, the la's element and its course through the
 * body by date and within the day, the round of the dates' illnesses and the four perilous days, and the
 * Four Tantras' humours and seasons. The day's illness readings themselves are rows of the date's,
 * weekday's and link's readings (`Texts`).
 */

/**
 * The quarters of the sky as an element's (WB vol. 2, p. 330, with the tables pp. 345–346): the
 * element's own six mansions (four for earth), and those of the elements that are its mother, friend,
 * child and enemy. Counted by the life force (*srog*) of the birth year, which p. 330 says applies the
 * same way as the clan's element (docs/sources/personal-mansions.md, *Whose element*).
 */
enum class ElementQuarter(val wylie: String) {
    OWN("rang skar"),
    MOTHER("ma skar"),
    FRIEND("grogs skar"),
    CHILD("bu skar"),
    ENEMY("dgra skar");

    val english: String get() = gloss(this)
}

/** The great and the small grave mansion of an element (WB p. 330 for wood, the tables pp. 345–346 for all five). */
enum class GraveMansion(val wylie: String) {
    GREAT("dur skar che ba"),
    SMALL("dur skar chung ba");

    val english: String get() = gloss(this)
}

/** The Root Tantra's three humours (ch. 3), each with the season and the two times its illness rises. */
enum class Humour(val wylie: String) {
    WIND("rlung"),
    BILE("mkhris pa"),
    PHLEGM("bad kan");

    val english: String get() = gloss(this)
}

/** The Explanatory Tantra's six seasons (ch. 14), two months each, with their food and conduct. */
enum class MedicalSeason(val wylie: String) {
    EARLY_WINTER("dgun stod"),
    LATE_WINTER("dgun smad"),
    SPRING("dpyid ka"),
    SO_GA("so ga"),
    SUMMER("dbyar"),
    AUTUMN("ston ka");

    val english: String get() = gloss(this)
}

/**
 * The la's course within any day (WB vol. 2, p. 304, the third list, "within any day"; read on the
 * scan, docs/sources/health.md): from both feet at daybreak up to the middle of the chest at noon and
 * down to both feet at sunset, spread above the navel through the night. The times are the verse's
 * own, not hours by the clock; the place of each is the catalog's `LaHour.<NAME>.place`.
 */
enum class LaHour(val wylie: String) {
    DAYBREAK("nam langs"),
    SUNRISE("nyi shar"),
    EARLY_MORNING("dros chung"),
    LATE_MORNING("dros chen"),
    NOON("nyi phyed"),
    EARLY_AFTERNOON("phyed yol"),
    LATE_AFTERNOON("myur kha"),
    TOWARDS_EVENING("myur chung"),
    SUNSET("nyi nub"),
    NIGHT("sa srod");

    val english: String get() = gloss(this)
    val place: String get() = gloss(this, "place")
}

object Health {
    /**
     * The element whose quarter [mansion] is in (WB p. 330; vol. 1, p. 232): the six eastern mansions
     * from Kṛttikā (2–7) wood, the six southern (9–14) fire, the six western (16–21) iron, the six
     * northern (22–26 and Aśvinī, 0) water, the four between (1, 8, 15 and Abhijit) earth. The tables
     * print 21 in both the western six and earth's four; vol. 1, p. 232 ends the western six with
     * Śravaṇa (*gro bzhin*) and names Abhijit (*byi bzhin*) among the four, so a day in Śravaṇa is
     * iron's, and earth's fourth, Abhijit, has no day of its own among the 27.
     */
    fun quarterElement(mansion: Mansion): Element = when (mansion.ordinal) {
        in 2..7 -> Element.WOOD
        in 9..14 -> Element.FIRE
        in 16..21 -> Element.IRON
        in 22..26, 0 -> Element.WATER
        else -> Element.EARTH
    }

    /** What [mansion]'s quarter is to [element], the life force's: own, mother, friend, child or enemy. */
    fun quarter(element: Element, mansion: Mansion): ElementQuarter = when (Forces.kinship(element, quarterElement(mansion))) {
        Kinship.IDENTITY -> ElementQuarter.OWN
        Kinship.MOTHER -> ElementQuarter.MOTHER
        Kinship.FRIEND -> ElementQuarter.FRIEND
        Kinship.SON -> ElementQuarter.CHILD
        Kinship.ENEMY -> ElementQuarter.ENEMY
    }

    /**
     * [element]'s great and small grave mansions, as the tables print them (pp. 345–346): all four
     * among earth's mansions, 21 Abhijit, which no day falls on, so it is null here.
     */
    fun graveMansions(element: Element): Map<GraveMansion, Mansion?> {
        val (great, small) = when (element) {
            Element.WOOD -> 15 to 1
            Element.FIRE -> 21 to 8
            Element.EARTH -> 8 to 21
            Element.IRON -> 1 to 15
            Element.WATER -> 8 to 21
        }
        fun mansion(n: Int) = if (n == 21) null else Mansion.entries[n]
        return mapOf(GraveMansion.GREAT to mansion(great), GraveMansion.SMALL to mansion(small))
    }

    /** Which grave mansion of [element] [mansion] is, if either. */
    fun grave(element: Element, mansion: Mansion): GraveMansion? =
        graveMansions(element).entries.firstOrNull { it.value == mansion }?.key

    /**
     * The la's element: "though the la is commonly reckoned as the mother of the body, as the mother of
     * the life force it accords with the tantras' meaning" (WB vol. 1, p. 253), so the element that
     * gives birth to the life force's: water for wood, fire for earth.
     */
    fun laElement(lifeForce: Element): Element = Element.entries.first { it.feeds == lifeForce }

    /**
     * The date whose illness reading [date] takes (WB vol. 2, pp. 297–302): dates 1–8 have lines of
     * their own and every later date is "the same as" one of them, in a round of eight, the 9th as the
     * 1st and the 30th as the 6th; the round is ch. 25's eight goddesses of the date (vol. 1, pp. 449–450).
     */
    fun illnessDate(date: Int): Int = (date - 1) % 8 + 1

    /** The four perilous days (p. 302): falling ill on the 8th, 15th, 22nd or 30th is dangerous. */
    val PERILOUS_DATES: Set<Int> = setOf(8, 15, 22, 30)
}
