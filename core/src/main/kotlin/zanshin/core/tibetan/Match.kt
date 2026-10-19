/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import zanshin.core.texts.gloss

/*
 * The marriage match (ROADMAP R3, WB vol. 1, ch. 23, the first examination, pp. 351–364): the points that follow from
 * the two birth years. docs/sources/marriage.md quotes each rule as read on the scan.
 */

/**
 * How one aspect of one spouse meets the same aspect of the other in the single match (*rkyang pa*, pp. 355–356): as
 * mother, son or friend it agrees; as enemy it disagrees; in the same element it agrees for earth and water and is
 * middling for fire, iron and wood.
 */
enum class MatchMeeting {
    AGREE,
    MIDDLING,
    DISAGREE;

    val english: String get() = gloss(this)
}

/**
 * The bad years for marriage (pp. 353–354), each named list with its years; a spouse born in one of them is read by
 * it. The black year with fangs goes by the natal mewa, the two-black, not by the year's sign.
 */
enum class BadYear(val years: List<Sign>) {
    HARSH_RNGA_ZOR(signs("IRON DOG", "IRON PIG")),
    HARSH_RO_KHUR(signs("IRON MOUSE", "IRON OX")),
    HARSH_RUS_GCOD(signs("FIRE TIGER", "FIRE RABBIT")),
    HARSH_MDUN_BSDEBS(signs("FIRE DRAGON", "FIRE SNAKE")),
    HARSH_GRU_KHYER(signs("FIRE HORSE", "FIRE SHEEP")),
    HARSH_BYE_BRAL(signs("FIRE MONKEY", "FIRE BIRD")),
    BAD_OMEN(signs("FIRE SNAKE", "WATER PIG", "WATER BIRD", "WOOD BIRD", "IRON SHEEP", "EARTH BIRD", "IRON PIG")),
    LITTLE_WHITE(signs("IRON DOG", "IRON BIRD", "FIRE OX", "FIRE MONKEY", "EARTH DRAGON", "WATER SNAKE")),
    THAN(signs("EARTH DRAGON", "IRON SHEEP", "EARTH MONKEY", "IRON BIRD", "IRON DOG", "FIRE PIG", "FIRE OX")),
    BYA_RA(signs("FIRE HORSE", "WATER MOUSE")),
    GRAVE_MEN(signs("IRON OX", "WOOD SHEEP", "FIRE DOG", "WATER DRAGON")),
    LDING_NAG(signs("WATER TIGER", "WATER SNAKE", "IRON MONKEY")),
    SRIN_KHYER(signs("WATER MOUSE", "WATER HORSE", "WATER BIRD", "WATER RABBIT", "EARTH PIG", "EARTH SNAKE", "EARTH BIRD", "EARTH RABBIT", "FIRE DRAGON", "FIRE BIRD", "WATER OX")),
    THER_DRE(signs("FIRE HORSE", "WATER BIRD", "WOOD RABBIT", "IRON BIRD")),
    WIDOWHOOD(signs("IRON MONKEY", "IRON PIG", "FIRE DRAGON", "FIRE SNAKE", "EARTH SHEEP", "IRON DOG", "WOOD SNAKE", "EARTH BIRD")),
    AVOID(signs("WOOD TIGER", "IRON MONKEY", "WATER TIGER", "WATER MONKEY", "FIRE TIGER", "WATER SNAKE", "WATER DOG", "WATER PIG")),
    BLACK_FANGS(emptyList());

    val english: String get() = gloss(this)
}

private fun signs(vararg names: String): List<Sign> = names.map { n -> n.split(" ").let { Sign(Element.valueOf(it[0]), Animal.valueOf(it[1])) } }


/** The *ni sha* reckoning (p. 359): the man of a male year and older, the woman of a female year and younger. */
enum class NiSha { GOOD, REVERSED }

object MatchReckoning {
    /** The aspects the single match reads, in its order; the life force and luck are the man's set against the woman's, the body and destiny the woman's against the man's. */
    val ASPECTS = listOf(Force.VITALITY, Force.BODY, Force.DESTINY, Force.LUCK)

    /** Whose aspect is set against the other's: the man's for the life force and luck, the woman's for the body and destiny. */
    fun manLeads(force: Force): Boolean = force == Force.VITALITY || force == Force.LUCK

    /** How [own] meets [other] (p. 355). */
    fun meeting(own: Element, other: Element): MatchMeeting = when (Forces.kinship(own, other)) {
        Kinship.MOTHER, Kinship.SON, Kinship.FRIEND -> MatchMeeting.AGREE
        Kinship.ENEMY -> MatchMeeting.DISAGREE
        Kinship.IDENTITY -> if (own == Element.EARTH || own == Element.WATER) MatchMeeting.AGREE else MatchMeeting.MIDDLING
    }

    /** Each aspect's meeting for a man of [man]'s and a woman of [woman]'s birth years. */
    fun aspects(man: YearForces, woman: YearForces): List<Pair<Force, MatchMeeting>> = ASPECTS.map { f ->
        f to if (manLeads(f)) meeting(man[f], woman[f]) else meeting(woman[f], man[f])
    }

    /** Whether all four aspects meet as mother and son: the wife of karma (*las kyi chung ma*, p. 355). */
    fun karma(man: YearForces, woman: YearForces): Boolean =
        ASPECTS.all { Forces.kinship(man[it], woman[it]) in setOf(Kinship.MOTHER, Kinship.SON) }

    /** The bad years for marriage a spouse born in [sign] under the natal mewa [natalMewa] is of (pp. 353–354). */
    fun badYears(sign: Sign, natalMewa: Int): List<BadYear> =
        BadYear.entries.filter { sign in it.years } + if (natalMewa == 2) listOf(BadYear.BLACK_FANGS) else emptyList()

    /** The kind of year both spouses are of, by destiny against life force, when it is the same (p. 351): not to be joined. */
    fun sameKind(man: Sign, woman: Sign): PowerYear? =
        BirthReckoning.powerYear(man).takeIf { it == BirthReckoning.powerYear(woman) }

    /** The two destinies' elements as a pair, the earlier in [Element]'s order first (p. 357). */
    fun destinyPair(a: Element, b: Element): Pair<Element, Element> = if (a.ordinal <= b.ordinal) a to b else b to a

    /**
     * The *ni sha* reckoning (p. 359) for a man born in the Tibetan year [manYear] of animal [manAnimal] and a woman
     * of [womanYear] and [womanAnimal]: good when he is of a male year and older and she of a female year; reversed
     * when he is younger, or her year is male and his female; null otherwise.
     */
    fun niSha(manYear: Int, manAnimal: Animal, womanYear: Int, womanAnimal: Animal): NiSha? = when {
        manAnimal.gender == Gender.MALE && womanAnimal.gender == Gender.FEMALE && manYear < womanYear -> NiSha.GOOD
        manYear > womanYear || manAnimal.gender == Gender.FEMALE && womanAnimal.gender == Gender.MALE -> NiSha.REVERSED
        else -> null
    }
}
