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

/**
 * The bad years by mewa path (p. 354): in the first, middle and lower sixty years of the mewa's round, the years
 * that hold all five elements (bad as a bride) and the nine that hold two. [years] lists each path's signs as WB
 * names them. The five-element years are exactly those whose life force, body, power, luck and birth mewa are five
 * elements; the two-element list departs from that count in three places (marriage.md) and is kept as printed.
 */
enum class MewaPathYear(val years: List<List<Sign>>) {
    FIVE_ELEMENTS(listOf(signs("WOOD OX"), signs("WOOD DOG", "EARTH SNAKE"), signs("WATER SHEEP", "EARTH MOUSE", "WOOD SHEEP", "EARTH PIG", "IRON MOUSE"))),
    TWO_ELEMENTS(listOf(signs("FIRE RABBIT", "WATER BIRD", "IRON MONKEY"), signs("WOOD MONKEY", "WATER SNAKE"), signs("IRON TIGER", "IRON DOG", "WATER MOUSE", "WATER PIG"))),
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

/**
 * How the two natal trigrams meet (pp. 361–362), each kind with its pairs as WB lists them: three good, the four bad,
 * and one trigram joined within. The printed srog 'tsho repeats dpal bskyed's «ཁམ་ཟིན» where the scheme, each trigram
 * once in a kind, wants ཁམ་ཟོན; that pair is left out of both, and ཁམ་ཟོན meets in none (marriage.md).
 */
enum class MatchTrigramKind(val good: Boolean, val pairs: List<Pair<Trigram, Trigram>>) {
    GNAM_SMAN(true, trigramPairs("LI KHAM", "KHON KHEN", "DWA GIN", "ZIN ZON")),
    DPAL_BSKYED(true, trigramPairs("LI ZON", "KHON DWA", "KHEN GIN", "KHAM ZIN")),
    SROG_TSHO(true, trigramPairs("LI ZIN", "KHON GIN", "DWA KHEN")),
    BDUD_GCOD(false, trigramPairs("LI KHEN", "DWA ZIN", "KHON KHAM", "GIN ZON")),
    LUS_CHAD(false, trigramPairs("LI KHON", "DWA ZON", "GIN ZIN", "KHEN KHAM")),
    DRE_LNGA(false, trigramPairs("LI DWA", "KHON ZON", "GIN KHAM", "KHEN ZIN")),
    GNOD_PA(false, trigramPairs("LI GIN", "KHEN ZON", "DWA KHAM", "KHON ZIN")),
    ONE(false, emptyList());

    /** The pair of this kind that [a] and [b] make up, in WB's order, or null. */
    fun pair(a: Trigram, b: Trigram): Pair<Trigram, Trigram>? = pairs.firstOrNull { (x, y) -> x == a && y == b || x == b && y == a }
}

private fun trigramPairs(vararg names: String): List<Pair<Trigram, Trigram>> =
    names.map { n -> n.split(" ").let { Trigram.valueOf(it[0]) to Trigram.valueOf(it[1]) } }

/** The children's number by the pair of natal trigrams (p. 363), the pairs as WB names them. */
val TRIGRAM_CHILDREN: List<Pair<Trigram, Trigram>> =
    trigramPairs("LI KHON", "KHON DWA", "DWA GIN", "GIN KHAM", "GIN ZIN", "ZON ZIN", "KHAM ZON", "DWA KHEN", "LI KHEN", "LI ZON", "KHAM ZIN")

/** The eight hand-wavers (p. 362): the year animals that, born on the trigram, make one of them. */
val HAND_WAVERS: Map<Trigram, List<Animal>> = mapOf(
    Trigram.GIN to listOf(Animal.OX, Animal.TIGER),
    Trigram.ZON to listOf(Animal.DRAGON, Animal.SNAKE),
    Trigram.KHON to listOf(Animal.SHEEP, Animal.MONKEY),
    Trigram.KHEN to listOf(Animal.MOUSE, Animal.PIG),
)

/**
 * The grade of a stage of rise and decline when a spouse's life force falls on it in the match (p. 363): the body
 * complete, bathing, dressing and flourishing the best (true), taking breath, the womb, birth and working middling
 * (null), decline, illness, death and the tomb bad (false).
 */
fun matchStageGrade(s: Sector): Boolean? = when (s) {
    Sector.BODY_COMPLETE, Sector.BATHING, Sector.DRESSING, Sector.FLOURISHING -> true
    Sector.TAKING_BREATH, Sector.IN_THE_WOMB, Sector.BIRTH, Sector.WORKING -> null
    Sector.DECLINE, Sector.ILLNESS, Sector.DEATH, Sector.ENTERING_THE_TOMB -> false
}

/** The parent a spouse's stage is counted from (pp. 359–360): the fathers in point 8, the mothers in point 9. */
enum class MatchParent { FATHER, MOTHER }

/** The two ways of counting from a parent's year that WB and the notes spell out (p. 359; the notes, p. 524). */
enum class ParentWay { OUTER, SECRET }

/**
 * Births on a natal trigram read one by one (p. 362): a woman of a wood tiger or hare year on khon, a husband of an
 * iron bird or monkey year on gin, and a woman on khen with a man on zon.
 */
enum class MatchTrigramLine { WOOD_WOMAN_KHON, IRON_HUSBAND_GIN, KHEN_WOMAN_ZON_MAN }

/** The planet husband and wife are joined in when born on the same weekday (p. 360), the weekdays WB reads together joined. */
enum class MatchPlanet { SATURDAY, SUN_TUE, THURSDAY, MON_WED, FRIDAY }

/** The *ni sha* reckoning (p. 359): the man of a male year and older, the woman of a female year and younger. */
enum class NiSha { GOOD, REVERSED }

/**
 * The nine wheels the hidden sets two mewas on (pp. 357–358, each mewa by its trigram; the wheels as the notes draw
 * them, vol. 2, p. 524), in WB's order: the first three like the sun rising in a cloudless sky, the next three like
 * the sun between clouds, the last three like frost on the ground. Together they hold each pair of trigrams once, a
 * trigram with itself on the two wheels of luck. Life's fourth pair, water and wind, the natal trigrams' list leaves
 * out ([MatchTrigramKind.SROG_TSHO]); the hidden's verse names it («ཆུ(༡)སྲོག་རླུང(༤)»).
 */
enum class HiddenWheel(val good: Boolean, val pairs: List<Pair<Trigram, Trigram>>) {
    GNAM_SMAN(true, trigramPairs("LI KHAM", "KHON KHEN", "DWA GIN", "ZIN ZON")),
    SROG_TSHO(true, trigramPairs("LI ZIN", "KHON GIN", "DWA KHEN", "KHAM ZON")),
    DPAL_BSKYED(true, trigramPairs("LI ZON", "DWA KHON", "KHEN GIN", "KHAM ZIN")),
    PHYWA_LON(true, trigramPairs("LI LI", "GIN GIN", "DWA DWA", "KHEN KHEN")),
    PHYWA_MNYAM(true, trigramPairs("KHAM KHAM", "KHON KHON", "ZIN ZIN", "ZON ZON")),
    GNOD_PA(false, trigramPairs("LI GIN", "KHEN ZON", "DWA KHAM", "ZIN KHON")),
    DRE_LNGA(false, trigramPairs("DWA LI", "KHEN ZIN", "GIN KHAM", "KHON ZON")),
    BDUD_GCOD(false, trigramPairs("LI KHEN", "KHON KHAM", "DWA ZIN", "GIN ZON")),
    LUS_CHAD(false, trigramPairs("LI KHON", "ZON DWA", "GIN ZIN", "KHAM KHEN"));

    /** 0 the cloudless sun, 1 the sun between clouds, 2 the frost. */
    val tier: Int get() = ordinal / 3

    fun holds(a: Trigram, b: Trigram): Boolean = pairs.any { (x, y) -> x == a && y == b || x == b && y == a }
}

/** Point 7's comparison of the two sums (p. 359; the notes, p. 525): equal is good, and whoever has more wins. */
enum class SiblingsWin { EQUAL, MAN, WOMAN }

/** The couple's one deciding pebble over the bad years (the notes, vol. 2, pp. 523–524): black for both, speckled for one, white for neither. */
enum class BadYearPebble { BLACK, SPECKLED_WOMAN, SPECKLED_MAN, WHITE }

/**
 * The kind of spouse one aspect makes (p. 355; Sangye Gyatso's *Removing the Tarnish*, reply 113; the notes,
 * vol. 2, p. 524), in grades: of karma the best (mother and son, or earth or water meeting itself), of kin the
 * middling (friends, or fire, iron or wood meeting itself), of enmity the last (enemies).
 */
enum class SpouseKind { KARMA, KIN, ENMITY }

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

    /** How the man's and the woman's natal trigrams meet (pp. 361–362); null for ཁམ་ཟོན, which the print leaves out. */
    fun trigramKind(man: Trigram, woman: Trigram): MatchTrigramKind? =
        if (man == woman) MatchTrigramKind.ONE else MatchTrigramKind.entries.firstOrNull { it.pair(man, woman) != null }

    /** The pair of natal trigrams whose children WB numbers (p. 363), in its order, or null. */
    fun trigramChildren(a: Trigram, b: Trigram): Pair<Trigram, Trigram>? =
        TRIGRAM_CHILDREN.firstOrNull { (x, y) -> x == a && y == b || x == b && y == a }

    /** Whether one born in a year of [animal] on the natal trigram [natal] is one of the eight hand-wavers (p. 362). */
    fun handWaver(animal: Animal, natal: Trigram): Boolean = HAND_WAVERS[natal]?.contains(animal) == true

    /**
     * The root rise and decline (p. 364): the stage that one spouse's life force [vitality] falls on, carried onto
     * the other's year animal [other] («ཕོ་སྲོག་མོ་སྟེང་མོ་སྲོག་ཕོའི། །སྟེང་ཁྱེར»).
     */
    fun riseDecline(vitality: Element, other: Animal): Sector = YearOfLife.sector(vitality, other)

    /**
     * The stage a spouse born in a year of [own] falls on, counted from the parent's year [parent] (pp. 359–360):
     * the outer way takes breath on the parent's year animal, the secret way on the breath-taking of the parent's
     * life force, and both count on to the spouse's own year (the notes, p. 524: «ཕ་སྟག་བུ་ཡོས་ལ་དར་བ་བབ་པ»).
     */
    fun parentStage(way: ParentWay, parent: Sign, own: Animal): Sector = when (way) {
        ParentWay.OUTER -> Sector.entries[Math.floorMod(own.ordinal - parent.animal.ordinal, 12)]
        ParentWay.SECRET -> YearOfLife.sector(Forces.of(parent.element, parent.animal).vitality, own)
    }

    /** The mewa path of the Tibetan year [year]: 0 the first sixty years of the round (1864–1923), 1 the middle, 2 the lower. */
    fun mewaPath(year: Int): Int = Math.floorMod(year - 1864, 180) / 60

    /** The bad years by mewa path that the Tibetan year [year] is among (p. 354). */
    fun mewaPathYears(year: Int): List<MewaPathYear> =
        MewaPathYear.entries.filter { YearOfLife.yearSign(year) in it.years[mewaPath(year)] }

    /** The births on the natal trigrams [manTrigram] and [womanTrigram] that WB reads one by one (p. 362). */
    fun trigramLines(man: Sign, manTrigram: Trigram, woman: Sign, womanTrigram: Trigram): List<MatchTrigramLine> = listOfNotNull(
        MatchTrigramLine.WOOD_WOMAN_KHON.takeIf {
            womanTrigram == Trigram.KHON && woman.element == Element.WOOD && woman.animal in listOf(Animal.TIGER, Animal.RABBIT)
        },
        MatchTrigramLine.IRON_HUSBAND_GIN.takeIf {
            manTrigram == Trigram.GIN && man.element == Element.IRON && man.animal in listOf(Animal.BIRD, Animal.MONKEY)
        },
        MatchTrigramLine.KHEN_WOMAN_ZON_MAN.takeIf { womanTrigram == Trigram.KHEN && manTrigram == Trigram.ZON },
    )

    /** Whether the two years are of one animal (p. 353); the monkey's line («མི་བུ་བསྲེལ») is not read, so it has none. */
    fun sameAnimal(man: Sign, woman: Sign): Animal? = man.animal.takeIf { it == woman.animal && it != Animal.MONKEY }

    /** The sexes of two years of one element (p. 353); null when the elements differ. */
    fun sameElement(man: Sign, woman: Sign): YearSexes? = if (man.element != woman.element) null else when {
        man.animal.gender == Gender.MALE && woman.animal.gender == Gender.MALE -> YearSexes.MALE_MALE
        man.animal.gender == Gender.FEMALE && woman.animal.gender == Gender.FEMALE -> YearSexes.FEMALE_FEMALE
        else -> YearSexes.MIXED
    }

    /** The kind of spouse [own] meeting [other] makes in one aspect (p. 355; *Removing the Tarnish*, reply 113). */
    fun kind(own: Element, other: Element): SpouseKind = when (Forces.kinship(own, other)) {
        Kinship.MOTHER, Kinship.SON -> SpouseKind.KARMA
        Kinship.FRIEND -> SpouseKind.KIN
        Kinship.ENEMY -> SpouseKind.ENMITY
        Kinship.IDENTITY -> if (own == Element.EARTH || own == Element.WATER) SpouseKind.KARMA else SpouseKind.KIN
    }

    /** Each aspect's kind of spouse, set as the single match sets it ([manLeads]). */
    fun kinds(man: YearForces, woman: YearForces): List<Pair<Force, SpouseKind>> = ASPECTS.map { f ->
        f to if (manLeads(f)) kind(man[f], woman[f]) else kind(woman[f], man[f])
    }

    /**
     * The mewa of the aspect [force] of one born in the Tibetan year [year] (ch. 21, p. 255, «ཡར་བཞི་སྲོག་ལ་མར་བཞི་དབང་། །གང་སྐྱེས་ལུས་སྨེ་མཐུན་གསུམ་ཀླུང་།»;
     * marriage.md, *Open questions 161–165*): the body's is the birth mewa; the life force's "four up", the birth
     * mewa less three; destiny's "four down", plus three, round 1–9; the luck's the birth mewa of the wind-horse year
     * of the animal's triad in the same sixty years, as WB's own fire hare counts it.
     */
    fun aspectMewa(year: Int, force: Force): Int {
        val birth = YearOfLife.yearSmeBa(year)
        return when (force) {
            Force.BODY -> birth
            Force.VITALITY -> Math.floorMod(birth - 4, 9) + 1
            Force.DESTINY -> Math.floorMod(birth + 2, 9) + 1
            Force.LUCK -> YearOfLife.yearSmeBa(windHorseYear(year))
        }
    }

    /**
     * The wind-horse year of the triad the year [year]'s animal is in (ch. 21, p. 254), within the same sixty years,
     * counted from the fire hare of 1027.
     */
    fun windHorseYear(year: Int): Int {
        val start = 1027 + 60 * Math.floorDiv(year - 1027, 60)
        val horse = when (YearOfLife.yearSign(year).animal) {
            Animal.TIGER, Animal.HORSE, Animal.DOG -> Sign(Element.IRON, Animal.MONKEY)
            Animal.PIG, Animal.SHEEP, Animal.RABBIT -> Sign(Element.FIRE, Animal.SNAKE)
            Animal.MOUSE, Animal.DRAGON, Animal.MONKEY -> Sign(Element.WOOD, Animal.TIGER)
            Animal.BIRD, Animal.OX, Animal.SNAKE -> Sign(Element.WATER, Animal.PIG)
        }
        return (start until start + 60).first { YearOfLife.yearSign(it) == horse }
    }

    /**
     * The trigram a mewa [mewa] stands for on the hidden's wheels (p. 358; the notes, p. 524): the square with the 5
     * in the middle; a man's 5 counts as 2, a woman's as 7 (p. 357).
     */
    fun mewaTrigram(mewa: Int, man: Boolean): Trigram = when (mewa) {
        9 -> Trigram.LI
        2 -> Trigram.KHON
        7 -> Trigram.DWA
        6 -> Trigram.KHEN
        1 -> Trigram.KHAM
        8 -> Trigram.GIN
        3 -> Trigram.ZIN
        4 -> Trigram.ZON
        5 -> if (man) Trigram.KHON else Trigram.DWA
        else -> throw IllegalArgumentException("no mewa $mewa")
    }

    /** The wheel the two trigrams [a] and [b] fall on (pp. 357–358). */
    fun wheel(a: Trigram, b: Trigram): HiddenWheel = HiddenWheel.entries.first { it.holds(a, b) }

    /**
     * Point 4, the hidden (pp. 357–358): each aspect's mewa of the man, born in the Tibetan year [manYear], and of the
     * woman, born in [womanYear], set on the wheels. With [deeper], point 5, the deeper hidden (p. 359): the man's
     * mewa stays, the woman's is drawn, 1 and 2, 3 and 9, 4 and 8 to each other, 5, 6 and 7 not drawn.
     */
    fun hidden(manYear: Int, womanYear: Int, deeper: Boolean = false): List<Triple<Force, Pair<Int, Int>, HiddenWheel>> = ASPECTS.map { f ->
        val man = aspectMewa(manYear, f)
        val woman = aspectMewa(womanYear, f).let { if (deeper) drawn(it) else it }
        Triple(f, man to woman, wheel(mewaTrigram(man, man = true), mewaTrigram(woman, man = false)))
    }

    /** The deeper hidden's drawing of the woman's mewa (p. 359). */
    fun drawn(mewa: Int): Int = when (mewa) {
        1 -> 2; 2 -> 1; 3 -> 9; 9 -> 3; 4 -> 8; 8 -> 4
        else -> mewa
    }

    /** The pebbles an element counts in point 7 (p. 359): wood one, earth two, water three, fire four, iron five. */
    fun siblingsPebbles(e: Element): Int = when (e) {
        Element.WOOD -> 1
        Element.EARTH -> 2
        Element.WATER -> 3
        Element.FIRE -> 4
        Element.IRON -> 5
    }

    /**
     * Point 7, the siblings' number of the elements (p. 359; the notes, p. 525), of one born in the Tibetan year
     * [year]: the pebbles of the life force's, body's, destiny's and luck's elements and the birth mewa's, summed.
     */
    fun siblings(year: Int): Int {
        val forces = YearOfLife.yearSign(year).let { Forces.of(it.element, it.animal) }
        return ASPECTS.sumOf { siblingsPebbles(forces[it]) } + siblingsPebbles(smeBaElement(YearOfLife.yearSmeBa(year)))
    }

    /** Point 7's comparison of the man's sum [man] and the woman's [woman]. */
    fun siblingsWin(man: Int, woman: Int): SiblingsWin = when {
        man == woman -> SiblingsWin.EQUAL
        man > woman -> SiblingsWin.MAN
        else -> SiblingsWin.WOMAN
    }

    /** Point 7's agreement, read once on the two sums together (the notes, p. 525): even agrees. */
    fun siblingsAgree(man: Int, woman: Int): Boolean = (man + woman) % 2 == 0

    /** The couple's deciding pebble over the bad years, from whether the man and the woman are born in any (the notes, pp. 523–524). */
    fun badYearPebble(man: Boolean, woman: Boolean): BadYearPebble = when {
        man && woman -> BadYearPebble.BLACK
        woman -> BadYearPebble.SPECKLED_WOMAN
        man -> BadYearPebble.SPECKLED_MAN
        else -> BadYearPebble.WHITE
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
