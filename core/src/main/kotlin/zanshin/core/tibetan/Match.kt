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
