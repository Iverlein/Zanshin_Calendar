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


/** The sexes of two years of one element (p. 353): both male, both female, or one of each. */
enum class YearSexes { MALE_MALE, FEMALE_FEMALE, MIXED }

/**
 * The named lists of pairs of year animals in the match's first point (pp. 352–353), each pair joined whichever is
 * the man.
 */
enum class PairList(val pairs: List<Pair<Animal, Animal>>, val good: Boolean) {
    HEART(pairs("MOUSE DRAGON", "MOUSE MONKEY", "DRAGON MONKEY"), true),
    CHILDREN(pairs("RABBIT PIG", "BIRD SNAKE", "OX SHEEP", "MONKEY DRAGON", "HORSE DOG"), true),
    WEALTH(pairs("BIRD OX", "TIGER HORSE", "RABBIT SHEEP", "DRAGON PIG", "MONKEY SNAKE"), true),
    PARTING(pairs("BIRD TIGER", "MONKEY RABBIT", "DRAGON OX", "MOUSE SNAKE", "DOG SHEEP", "TIGER PIG"), false),
    HARD_CHILDREN(pairs("OX DOG", "PIG SHEEP", "DRAGON SNAKE"), false),
    RUIN(pairs("BIRD DOG", "HORSE OX", "SNAKE TIGER"), false),
    DESTRUCTION(pairs("DOG SHEEP", "OX RABBIT", "BIRD MOUSE"), false),
    OMEN_GREAT(pairs("SHEEP SNAKE"), false),
    OMEN_MIDDLING(pairs("HORSE DRAGON", "HORSE SNAKE"), false);

    fun holds(a: Animal, b: Animal): Boolean = pairs.any { (x, y) -> x == a && y == b || x == b && y == a }
}

private fun pairs(vararg names: String): List<Pair<Animal, Animal>> = names.map { n -> n.split(" ").let { Animal.valueOf(it[0]) to Animal.valueOf(it[1]) } }

/**
 * The pairs of year animals the match's first point reads one by one (pp. 352–353). Where WB says one takes (བླངས)
 * the other, the man is [man] and the woman one of [women], as Mipham's notes read it (p. 523); otherwise the pair is
 * [ordered] false and either may be the man. [good] is null for a lot both good and bad.
 */
enum class PairLine(val man: Animal, val women: List<Animal>, val ordered: Boolean, val good: Boolean?) {
    DOG_MONKEY(Animal.DOG, listOf(Animal.MONKEY), ordered = true, good = true),
    BIRD_SNAKE(Animal.BIRD, listOf(Animal.SNAKE), ordered = true, good = true),
    PIG_MOUSE(Animal.PIG, listOf(Animal.MOUSE), ordered = true, good = false),
    TIGER_SNAKE(Animal.TIGER, listOf(Animal.SNAKE), ordered = true, good = null),
    SNAKE_TIGER(Animal.SNAKE, listOf(Animal.TIGER), ordered = true, good = false),
    SHEEP_MOUSE(Animal.SHEEP, listOf(Animal.MOUSE), ordered = true, good = false),
    MONKEY_MOUSE(Animal.MONKEY, listOf(Animal.MOUSE), ordered = true, good = false),
    RABBIT_PIG_MOUSE(Animal.RABBIT, listOf(Animal.PIG, Animal.MOUSE), ordered = true, good = true),
    DRAGON_BIRD_MONKEY(Animal.DRAGON, listOf(Animal.BIRD, Animal.MONKEY), ordered = true, good = false),
    SHEEP_OX(Animal.SHEEP, listOf(Animal.OX), ordered = true, good = false),
    MONKEY_WITH_DRAGON(Animal.MONKEY, listOf(Animal.DRAGON), ordered = false, good = true),
    DOG_WITH_HORSE(Animal.DOG, listOf(Animal.HORSE), ordered = false, good = true),
    PIG_RABBIT(Animal.PIG, listOf(Animal.RABBIT), ordered = true, good = true),
    OX_MOUSE_BIRD(Animal.OX, listOf(Animal.MOUSE, Animal.BIRD), ordered = true, good = true),
    TIGER_HORSE(Animal.TIGER, listOf(Animal.HORSE), ordered = true, good = true),
    DRAGON_WITH_MOUSE_PIG(Animal.DRAGON, listOf(Animal.MOUSE, Animal.PIG), ordered = false, good = null),
    MONKEY_SNAKE(Animal.MONKEY, listOf(Animal.SNAKE), ordered = true, good = true),
    BIRD_OX(Animal.BIRD, listOf(Animal.OX), ordered = true, good = true),
    SHEEP_SNAKE(Animal.SHEEP, listOf(Animal.SNAKE), ordered = true, good = true),
    SNAKE_SHEEP(Animal.SNAKE, listOf(Animal.SHEEP), ordered = true, good = true),
    DRAGON_OX(Animal.DRAGON, listOf(Animal.OX), ordered = true, good = true),
    SNAKE_MOUSE(Animal.SNAKE, listOf(Animal.MOUSE), ordered = true, good = false),
    HORSE_OX(Animal.HORSE, listOf(Animal.OX), ordered = true, good = true),
    OX_WITH_RABBIT(Animal.OX, listOf(Animal.RABBIT), ordered = false, good = true),
    DOG_TIGER(Animal.DOG, listOf(Animal.TIGER), ordered = true, good = true),
    MOUSE_SNAKE(Animal.MOUSE, listOf(Animal.SNAKE), ordered = true, good = false),
    OX_DRAGON(Animal.OX, listOf(Animal.DRAGON), ordered = true, good = false),
    SHEEP_DOG(Animal.SHEEP, listOf(Animal.DOG), ordered = true, good = false),
    DOG_SHEEP(Animal.DOG, listOf(Animal.SHEEP), ordered = true, good = false),
    DOG_OX(Animal.DOG, listOf(Animal.OX), ordered = true, good = false),
    OX_DOG(Animal.OX, listOf(Animal.DOG), ordered = true, good = false),
    DRAGON_SHEEP(Animal.DRAGON, listOf(Animal.SHEEP), ordered = true, good = false),
    SHEEP_DRAGON(Animal.SHEEP, listOf(Animal.DRAGON), ordered = true, good = false),
    PIG_WITH_SNAKE(Animal.PIG, listOf(Animal.SNAKE), ordered = false, good = false),
    DOG_WITH_DRAGON(Animal.DOG, listOf(Animal.DRAGON), ordered = false, good = false),
    DOG_DRAGON(Animal.DOG, listOf(Animal.DRAGON), ordered = true, good = false),
    MOUSE_WITH_HORSE(Animal.MOUSE, listOf(Animal.HORSE), ordered = false, good = false),
    HORSE_MOUSE(Animal.HORSE, listOf(Animal.MOUSE), ordered = true, good = false),
    OX_WITH_SHEEP(Animal.OX, listOf(Animal.SHEEP), ordered = false, good = false),
    TIGER_WITH_MONKEY(Animal.TIGER, listOf(Animal.MONKEY), ordered = false, good = false),
    MONKEY_TIGER(Animal.MONKEY, listOf(Animal.TIGER), ordered = true, good = false),
    SNAKE_HORSE(Animal.SNAKE, listOf(Animal.HORSE), ordered = true, good = false),
    MOUSE_WITH_SHEEP(Animal.MOUSE, listOf(Animal.SHEEP), ordered = false, good = false),
    SHEEP_PIG(Animal.SHEEP, listOf(Animal.PIG), ordered = true, good = true);

    fun holds(manAnimal: Animal, womanAnimal: Animal): Boolean =
        manAnimal == man && womanAnimal in women || !ordered && womanAnimal == man && manAnimal in women
}

/**
 * How the two birth mewas meet (p. 363): born on one, misfortune gathered when both are on the two-black, as mother
 * and son, as enemies, and the mewa's demon, the enemies' worst, with the man on the seven-red and the woman on the
 * three-blue.
 */
enum class MatchMewa { SAME, BYUR_DUS, MOTHER_SON, ENEMY, DEMON }

/** The planet husband and wife are joined in when born on the same weekday (p. 360), the weekdays WB reads together joined. */
enum class MatchPlanet { SATURDAY, SUN_TUE, THURSDAY, MON_WED, FRIDAY }

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

    /** The planet two spouses born on weekdays [a] and [b] are joined in (p. 360); null on different weekdays. */
    fun samePlanet(a: Weekday, b: Weekday): MatchPlanet? = if (a != b) null else when (a) {
        Weekday.SATURDAY -> MatchPlanet.SATURDAY
        Weekday.SUNDAY, Weekday.TUESDAY -> MatchPlanet.SUN_TUE
        Weekday.THURSDAY -> MatchPlanet.THURSDAY
        Weekday.MONDAY, Weekday.WEDNESDAY -> MatchPlanet.MON_WED
        Weekday.FRIDAY -> MatchPlanet.FRIDAY
    }

    /** The named lists of pairs the two year animals make up (pp. 352–353). */
    fun pairLists(man: Animal, woman: Animal): List<PairList> = PairList.entries.filter { it.holds(man, woman) }

    /** The pairs read one by one that hold for the man's and the woman's year animals (pp. 352–353). */
    fun pairLines(man: Animal, woman: Animal): List<PairLine> = PairLine.entries.filter { it.holds(man, woman) }

    /**
     * How the man's and the woman's birth mewas [man] and [woman] meet (p. 363): the birth mewa is "the main one"
     * («ངོ་བོ་སྐྱེས་སྨེ་གཙོ་ཆེ»); two mewas of one element that are not one meet in none of WB's ways.
     */
    fun mewaMeetings(man: Int, woman: Int): List<MatchMewa> {
        if (man == woman) return listOfNotNull(MatchMewa.SAME, MatchMewa.BYUR_DUS.takeIf { man == 2 })
        return when (Forces.kinship(smeBaElement(man), smeBaElement(woman))) {
            Kinship.MOTHER, Kinship.SON -> listOf(MatchMewa.MOTHER_SON)
            Kinship.FRIEND, Kinship.ENEMY -> listOf(if (man == 7 && woman == 3) MatchMewa.DEMON else MatchMewa.ENEMY)
            Kinship.IDENTITY -> emptyList()
        }
    }

    /** Whether the two years are of one animal (p. 353); the monkey's line («མི་བུ་བསྲེལ») is not read, so it has none. */
    fun sameAnimal(man: Sign, woman: Sign): Animal? = man.animal.takeIf { it == woman.animal && it != Animal.MONKEY }

    /** The sexes of two years of one element (p. 353); null when the elements differ. */
    fun sameElement(man: Sign, woman: Sign): YearSexes? = if (man.element != woman.element) null else when {
        man.animal.gender == Gender.MALE && woman.animal.gender == Gender.MALE -> YearSexes.MALE_MALE
        man.animal.gender == Gender.FEMALE && woman.animal.gender == Gender.FEMALE -> YearSexes.FEMALE_FEMALE
        else -> YearSexes.MIXED
    }

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
