/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import zanshin.core.texts.gloss

/*
 * The reckoning of the birth (ROADMAP R2, the birth): the parts of WB vol. 1, ch. 22 that are read on the scan and
 * follow from the dates. The natal trigram is [KinReckoning.natalTrigram]'s; set against the birth year's life
 * force it gives the five children of p. 294, and with the father's trigram, the child's sex and both years the
 * readings of the parents' trigrams at the birth on p. 326. With the clan, which is asked, the special pebble sets
 * the birth year against the clan's element (pp. 261, 284–286). docs/sources/year-of-life.md, RD13, quotes each rule.
 */

/**
 * The five great clans (*rus chen*) by their element (p. 259): keg wood, ji fire, kungs earth, shang iron, 'u water.
 * The clan is known in the family or found by its tradition, not reckoned (the notes, vol. 2, p. 474).
 */
enum class Clan(val element: Element, val wylie: String) {
    KEG(Element.WOOD, "keg"),
    JI(Element.FIRE, "ji"),
    KUNGS(Element.EARTH, "kungs"),
    SHANG(Element.IRON, "shang"),
    U(Element.WATER, "'u");

    val english: String get() = gloss(this)
}

/**
 * The year's own destiny (*rang nyid lo yi dbang thang*, pp. 261, 270–280): the birth year's destiny, its element,
 * against its life force, by its animal, one of the two pebbles that cannot be lacking. Life force and destiny one
 * element, *kha yan*; destiny begetting the life force, *khong nong*; the life force begetting destiny, *se zhig*;
 * destiny cutting the life force, *'dun khur*; the life force cutting destiny, *kha ral* (pp. 275–280, read on the scan).
 */
enum class PowerYear(val wylie: String) {
    KHA_YAN("kha yan"),
    KHONG_NONG("khong nong"),
    SE_ZHIG("se zhig"),
    DUN_KHUR("'dun khur"),
    KHA_RAL("kha ral");

    val english: String get() = gloss(this)
}

/**
 * The first, middle and last years (*ra 'bring tha ma*, p. 283): the tiger, monkey, pig and snake the first; the mouse,
 * horse, bird and hare the middle; the ox, sheep, dog and dragon the last.
 */
enum class YearPlace(val wylie: String) {
    FIRST("ra ba"),
    MIDDLE("'bring po"),
    LAST("tha chung");

    val english: String get() = gloss(this)
}

/**
 * The cuts of several of the birth year's aspects together that close the cutting and tearing (p. 282): two that cut
 * one, three that cut one (each named a star), and three that nourish the life force. [nourish] marks the last.
 */
enum class GroupCut(val by: Set<Force>, val on: Force, val nourish: Boolean = false) {
    PAIR_BODY(setOf(Force.DESTINY, Force.VITALITY), Force.BODY),
    PAIR_DESTINY(setOf(Force.VITALITY, Force.BODY), Force.DESTINY),
    PAIR_LUCK(setOf(Force.DESTINY, Force.VITALITY), Force.LUCK),
    STAR_DESTINY(setOf(Force.VITALITY, Force.BODY, Force.LUCK), Force.DESTINY),
    STAR_VITALITY(setOf(Force.BODY, Force.DESTINY, Force.LUCK), Force.VITALITY),
    STAR_LUCK(setOf(Force.VITALITY, Force.BODY, Force.DESTINY), Force.LUCK),
    STAR_NOURISH(setOf(Force.BODY, Force.DESTINY, Force.LUCK), Force.VITALITY, nourish = true);

    val english: String get() = gloss(this)
}

/** How a birth year stands to the clan's element, the special pebble (*sgos rdel*, p. 261), in the order of its readings (pp. 284–286). */
enum class ClanYear {
    GREAT_PENALTY,
    SMALL_PENALTY,
    MOTHER,
    POWER,
    SON,
    FRIEND,
    ENEMY;

    val english: String get() = gloss(this)
}

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
 * What p. 294 sets against the natal trigram's element besides the life force: the body, destiny and the luck, each
 * one of the same five for its share; and the natal mewa's element, set against the life force the same way.
 */
enum class BirthShare(val force: Force?) {
    BODY(Force.BODY),
    DESTINY(Force.DESTINY),
    LUCK(Force.LUCK),
    MEWA(null),
}

/**
 * Glory and misfortune (*dpal byur*, pp. 295–297): one's life force and luck against the father's year's, one's body and
 * destiny against the mother's (the notes, vol. 2, p. 476): the parent's the mother of one's own, glory falls; its
 * enemy, misfortune falls; the same, on its own foot; its son, lustre lost; the one it overcomes, life taken.
 */
enum class DpalByur {
    GLORY,
    MISFORTUNE,
    OWN_FOOT,
    LUSTRE_LOST,
    LIFE_TAKEN;

    val english: String get() = gloss(this)
}

/**
 * Where a forefather's life force, counted down through its twelve stages onto one's birth year, falls in the line of
 * births (*rgya ma*, pp. 297–299, and the notes, vol. 2, p. 476): illness crowded, death empty, the seventh from the
 * tomb staying, the tomb torn.
 */
enum class LineStage {
    CROWDED,
    EMPTY,
    STAYING,
    TORN;

    val english: String get() = gloss(this)
}

/** The forefathers' years pp. 297–299 reads the crowded line for, one group each. */
enum class LineGroup(val animals: Set<Animal>) {
    HORSE_SNAKE(setOf(Animal.HORSE, Animal.SNAKE)),
    TIGER_RABBIT(setOf(Animal.TIGER, Animal.RABBIT)),
    BIRD_MONKEY(setOf(Animal.BIRD, Animal.MONKEY)),
    MOUSE_PIG(setOf(Animal.MOUSE, Animal.PIG)),
    OX(setOf(Animal.OX)),
    SHEEP(setOf(Animal.SHEEP)),
    DOG(setOf(Animal.DOG)),
    DRAGON(setOf(Animal.DRAGON));

    companion object {
        fun of(animal: Animal): LineGroup = entries.first { animal in it.animals }
    }
}

/** The four precious pebbles (*gces rdel*, p. 262): the birth's year as king, month as minister, day as soldier, hour as weapon. */
enum class PreciousPebble { YEAR, MONTH, DAY, HOUR; val english: String get() = gloss(this) }

/** A pebble's colour; speckled where white and black are even (WB vol. 2, p. 46, «ཕྱེད་མ་ཁྲ་བོར་བོར»). */
enum class PebbleColour { WHITE, BLACK, SPECKLED }

/**
 * The five unchanging lucks (*mi 'gyur klung*, p. 261), the hundred's rows from the top as Kun dga' rig 'dzin's worked
 * sheet lays them (KD vol. 3, pp. 717–718): the father's year, for a daughter the mother's; the mother's progressed
 * sign at the birth; the trigram and the mewa fallen at the birth; the birth month.
 */
enum class UnchangingLuck { PARENT_YEAR, MOTHER_LOG_MEN, TRIGRAM, MEWA, MONTH; val english: String get() = gloss(this) }

/** An aspect's five columns in the hundred, in the sheet's order: its own element, its mother, son, friend and enemy. */
enum class HundredKin { OWN, MOTHER, SON, FRIEND, ENEMY; val english: String get() = gloss(this) }

/**
 * The hundred pebbles of the birth (pp. 261, 329–331; the board, docs/sources/claims/birth-hundred-board.md): each
 * of the five unchanging lucks [lucks], by the same aspect's element of it, met by each aspect of [birth] split into
 * its own element and that element's mother, son, friend and enemy. Five rows of twenty, one pebble a cell, and a
 * deciding pebble under each column.
 */
data class Hundred(val lucks: Map<UnchangingLuck, YearForces>, val birth: YearForces) {
    /** The element of the column [kin] of the aspect [force]. */
    fun column(force: Force, kin: HundredKin): Element = birth[force].let { e ->
        when (kin) {
            HundredKin.OWN -> e
            HundredKin.MOTHER -> Element.entries.first { it.feeds == e }
            HundredKin.SON -> e.feeds
            HundredKin.FRIEND -> e.overcomes
            HundredKin.ENEMY -> Element.entries.first { it.overcomes == e }
        }
    }

    /**
     * The pebble where [luck]'s row meets the column (the notes, vol. 2, p. 475): white where the row's element is the
     * column's mother or friend, black where it is its son or enemy; the same element black for fire, iron and wood,
     * white for earth and water.
     */
    fun cell(luck: UnchangingLuck, force: Force, kin: HundredKin): PebbleColour {
        val own = column(force, kin)
        return when (Forces.kinship(own, lucks.getValue(luck)[force])) {
            Kinship.MOTHER, Kinship.FRIEND -> PebbleColour.WHITE
            Kinship.SON, Kinship.ENEMY -> PebbleColour.BLACK
            Kinship.IDENTITY -> if (own == Element.EARTH || own == Element.WATER) PebbleColour.WHITE else PebbleColour.BLACK
        }
    }

    /** The deciding pebble (*btang gcod*, p. 261) under the column: the greater of its five. */
    fun deciding(force: Force, kin: HundredKin): PebbleColour =
        if (UnchangingLuck.entries.count { cell(it, force, kin) == PebbleColour.WHITE } >= 3) PebbleColour.WHITE else PebbleColour.BLACK

    /** Whether the deciding pebble is a good one: white, but for the enemy, whose black is the good pebble (pp. 329–331). */
    fun good(force: Force, kin: HundredKin): Boolean = (deciding(force, kin) == PebbleColour.WHITE) != (kin == HundredKin.ENEMY)
}

/** Whom the birth month bears on, by the child's life force counted onto it (p. 348). */
enum class RaisingMonth { FATHER, MOTHER, OWN, GOOD; val english: String get() = gloss(this) }

/** Whether the birth day makes the child hard or easy to raise, by its element against the year animal's (p. 348). */
enum class RaisingDay { HARD, EASY; val english: String get() = gloss(this) }

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
    /**
     * The special pebble's table (p. 261, read on the scan): for each clan the year animals of its great and small
     * penalty, its power year, and its mother, son, friend and enemy years. The great penalty is the element's tomb
     * animal and the small its seventh; water's are earth's.
     */
    private val CLAN_YEARS: Map<Clan, Map<ClanYear, Set<Animal>>> = mapOf(
        Clan.KUNGS to years(Animal.DRAGON, Animal.DOG, power = Animal.OX to Animal.SHEEP, mother = Animal.HORSE to Animal.SNAKE, son = Animal.BIRD to Animal.MONKEY, friend = Animal.MOUSE to Animal.PIG, enemy = Animal.TIGER to Animal.RABBIT),
        Clan.JI to years(Animal.DOG, Animal.DRAGON, power = Animal.HORSE to Animal.SNAKE, mother = Animal.TIGER to Animal.RABBIT, son = Animal.OX to Animal.SHEEP, friend = Animal.BIRD to Animal.MONKEY, enemy = Animal.MOUSE to Animal.PIG),
        Clan.KEG to years(Animal.SHEEP, Animal.OX, power = Animal.TIGER to Animal.RABBIT, mother = Animal.MOUSE to Animal.PIG, son = Animal.HORSE to Animal.SNAKE, friend = Animal.DOG to Animal.DRAGON, enemy = Animal.BIRD to Animal.MONKEY),
        Clan.U to years(Animal.DRAGON, Animal.DOG, power = Animal.MOUSE to Animal.PIG, mother = Animal.BIRD to Animal.MONKEY, son = Animal.TIGER to Animal.RABBIT, friend = Animal.HORSE to Animal.SNAKE, enemy = Animal.OX to Animal.SHEEP),
        Clan.SHANG to years(Animal.OX, Animal.SHEEP, power = Animal.BIRD to Animal.MONKEY, mother = Animal.DOG to Animal.DRAGON, son = Animal.MOUSE to Animal.PIG, friend = Animal.TIGER to Animal.RABBIT, enemy = Animal.HORSE to Animal.SNAKE),
    )

    private fun years(great: Animal, small: Animal, power: Pair<Animal, Animal>, mother: Pair<Animal, Animal>, son: Pair<Animal, Animal>, friend: Pair<Animal, Animal>, enemy: Pair<Animal, Animal>) =
        mapOf(
            ClanYear.GREAT_PENALTY to setOf(great), ClanYear.SMALL_PENALTY to setOf(small), ClanYear.POWER to power.toList().toSet(),
            ClanYear.MOTHER to mother.toList().toSet(), ClanYear.SON to son.toList().toSet(), ClanYear.FRIEND to friend.toList().toSet(),
            ClanYear.ENEMY to enemy.toList().toSet(),
        )

    /** The kind of the birth year [birth] by its destiny against its life force (pp. 275–280). */
    fun powerYear(birth: Sign): PowerYear {
        val forces = Forces.of(birth.element, birth.animal)
        return when (Forces.kinship(forces.vitality, forces.destiny)) {
            Kinship.IDENTITY -> PowerYear.KHA_YAN
            Kinship.MOTHER -> PowerYear.KHONG_NONG
            Kinship.SON -> PowerYear.SE_ZHIG
            Kinship.ENEMY -> PowerYear.DUN_KHUR
            Kinship.FRIEND -> PowerYear.KHA_RAL
        }
    }

    /**
     * The cutting and tearing (*gcod 'dral*, pp. 281–282) among the birth year [birth]'s own four aspects: each pair
     * whose first aspect's element overcomes the second's, in WB's order, as long as WB reads it (it gives no reading
     * of the luck cutting the life force or destiny).
     */
    fun cuts(birth: Sign): List<Pair<Force, Force>> {
        val f = Forces.of(birth.element, birth.animal)
        return CUTS.filter { (a, b) -> f[a].overcomes == f[b] }
    }

    /**
     * The group cuts of p. 282 that hold for the birth year [birth]: every aspect of the group overcomes (or, for
     * [GroupCut.STAR_NOURISH], feeds) the one it acts on; a pair inside a star that holds is left to the star.
     */
    fun groupCuts(birth: Sign): List<GroupCut> {
        val f = Forces.of(birth.element, birth.animal)
        val holding = GroupCut.entries.filter { g -> g.by.all { if (g.nourish) f[it].feeds == f[g.on] else f[it].overcomes == f[g.on] } }
        return holding.filter { g -> g.by.size == 3 || holding.none { it.by.size == 3 && !it.nourish && it.on == g.on && it.by.containsAll(g.by) } }
    }

    /** The pairs p. 281–282 reads, cutter first, in its order. */
    val CUTS: List<Pair<Force, Force>> = listOf(
        Force.VITALITY to Force.BODY, Force.VITALITY to Force.DESTINY, Force.VITALITY to Force.LUCK,
        Force.BODY to Force.VITALITY, Force.BODY to Force.DESTINY, Force.BODY to Force.LUCK,
        Force.DESTINY to Force.BODY, Force.DESTINY to Force.VITALITY, Force.DESTINY to Force.LUCK,
        Force.LUCK to Force.BODY,
    )

    /**
     * The first year of the pair [birth] belongs to among the thirty paired-year elements (*kha srog*, pp. 271–275):
     * the cycle's years go in pairs, the wood mouse with the wood ox and so on, each pair one body.
     */
    fun pairFirst(birth: Sign): Sign {
        val n = (0 until 60).first { Element.entries[it % 10 / 2] == birth.element && Animal.entries[it % 12] == birth.animal }
        val first = n - n % 2
        return Sign(Element.entries[first % 10 / 2], Animal.entries[first % 12])
    }

    /** Whether a year of [animal] is a first, middle or last year (p. 283). */
    fun yearPlace(animal: Animal): YearPlace = when (animal) {
        Animal.TIGER, Animal.MONKEY, Animal.PIG, Animal.SNAKE -> YearPlace.FIRST
        Animal.MOUSE, Animal.HORSE, Animal.BIRD, Animal.RABBIT -> YearPlace.MIDDLE
        Animal.OX, Animal.SHEEP, Animal.DOG, Animal.DRAGON -> YearPlace.LAST
    }

    /** How a birth year of [animal] stands to [clan] (p. 261). */
    fun clanYear(clan: Clan, animal: Animal): ClanYear = CLAN_YEARS.getValue(clan).entries.first { animal in it.value }.key

    /** What one born in a year of life force [vitality] on the natal trigram [natal] is (p. 294). */
    fun child(vitality: Element, natal: Trigram): BirthChild = kind(vitality, YearOfLife.trigramElement(natal))

    /** Which of the five [element] is against [other] (p. 294): [other] its mother, its son, the same, the one it overcomes, its enemy. */
    fun kind(element: Element, other: Element): BirthChild = when (Forces.kinship(element, other)) {
        Kinship.MOTHER -> BirthChild.PARENTS_GOD
        Kinship.SON -> BirthChild.CHILDRENS_GLORY
        Kinship.IDENTITY -> BirthChild.LIFE
        Kinship.FRIEND -> BirthChild.SPIRIT
        Kinship.ENEMY -> BirthChild.DEMON
    }

    /** Glory and misfortune of one's aspect of element [own] against the parent's of element [parent] (vol. 2, p. 476). */
    fun dpalByur(own: Element, parent: Element): DpalByur = when (Forces.kinship(own, parent)) {
        Kinship.MOTHER -> DpalByur.GLORY
        Kinship.ENEMY -> DpalByur.MISFORTUNE
        Kinship.IDENTITY -> DpalByur.OWN_FOOT
        Kinship.SON -> DpalByur.LUSTRE_LOST
        Kinship.FRIEND -> DpalByur.LIFE_TAKEN
    }

    /** Whose year each aspect is set against in glory and misfortune: the life force and luck the father's, the body and destiny the mother's. */
    fun dpalByurParentIsFather(force: Force): Boolean = force == Force.VITALITY || force == Force.LUCK

    /**
     * Where the life force of a forefather born in a year of [forefather], counted down onto a birth year of [child],
     * falls in the line of births (pp. 297–299): null when on none of the four. The life force goes by the animal alone.
     */
    fun lineStage(forefather: Animal, child: Animal): LineStage? {
        val vitality = Forces.of(Element.WOOD, forefather).vitality
        return when (YearOfLife.sector(vitality, child)) {
            Sector.ILLNESS -> LineStage.CROWDED
            Sector.DEATH -> LineStage.EMPTY
            Sector.DRESSING -> LineStage.STAYING
            Sector.ENTERING_THE_TOMB -> LineStage.TORN
            else -> null
        }
    }

    /**
     * The key pebbles of a sign's animal (p. 260): black growing from the tiger (one) to the snake (four), white from
     * the horse (one) to the bird (four); the dog one of each, the pig two white and one black, the mouse two black and
     * one white, the ox two of each. As white and black.
     */
    fun keyPebbles(animal: Animal): Pebbles = when (animal) {
        Animal.TIGER -> Pebbles(0, 1)
        Animal.RABBIT -> Pebbles(0, 2)
        Animal.DRAGON -> Pebbles(0, 3)
        Animal.SNAKE -> Pebbles(0, 4)
        Animal.HORSE -> Pebbles(1, 0)
        Animal.SHEEP -> Pebbles(2, 0)
        Animal.MONKEY -> Pebbles(3, 0)
        Animal.BIRD -> Pebbles(4, 0)
        Animal.DOG -> Pebbles(1, 1)
        Animal.PIG -> Pebbles(2, 1)
        Animal.MOUSE -> Pebbles(1, 2)
        Animal.OX -> Pebbles(2, 2)
    }

    /** A precious pebble's colour by its animal's key pebbles: the more, white or black; even, speckled. */
    fun preciousColour(animal: Animal): PebbleColour = keyPebbles(animal).let {
        when {
            it.white > it.black -> PebbleColour.WHITE
            it.black > it.white -> PebbleColour.BLACK
            else -> PebbleColour.SPECKLED
        }
    }

    /**
     * Whom the birth month of animal [month] bears on for a child of year animal [year] (p. 348): the life force in
     * its tomb, the father; in the womb, the mother; in illness or death, the child; flourishing, good; null otherwise.
     */
    fun raisingMonth(year: Animal, month: Animal): RaisingMonth? =
        when (YearOfLife.sector(Forces.of(Element.WOOD, year).vitality, month)) {
            Sector.ENTERING_THE_TOMB -> RaisingMonth.FATHER
            Sector.IN_THE_WOMB -> RaisingMonth.MOTHER
            Sector.ILLNESS, Sector.DEATH -> RaisingMonth.OWN
            Sector.FLOURISHING -> RaisingMonth.GOOD
            else -> null
        }

    /** Whether a birth on a day of animal [day] makes a child of year animal [year] hard or easy to raise (p. 348). */
    fun raisingDay(year: Animal, day: Animal): RaisingDay? =
        when (Forces.kinship(Forces.of(Element.WOOD, year).vitality, Forces.of(Element.WOOD, day).vitality)) {
            Kinship.ENEMY -> RaisingDay.HARD
            Kinship.MOTHER -> RaisingDay.EASY
            else -> null
        }

    /**
     * The four pebbles of rise and decline (the notes, vol. 2, p. 475): the life force, body, destiny and luck of the
     * birth year, each counted onto the birth's own year, month, day and hour in turn; the hour is left out, the
     * time of birth not being kept. The stage's readings are pp. 331–333.
     */
    fun riseDecline(born: TibetanDay): List<Pair<Force, Sector>> {
        val f = Forces.of(born.yearElement, born.yearAnimal)
        return listOf(
            Force.VITALITY to YearOfLife.sector(f.vitality, born.yearAnimal),
            Force.BODY to YearOfLife.sector(f.body, born.monthNames.animal),
            Force.DESTINY to YearOfLife.sector(f.destiny, born.dayAnimal),
        )
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

    /**
     * The hundred pebbles of a child of [gender] born in the Tibetan year [birthYear] of sign [birth], in the month of
     * sign [month], to a mother born in [motherYear] and a father of year sign [father] (p. 261): the father's year
     * for a son and the mother's for a daughter, the mother's progressed sign at her year of age at the birth, the
     * natal trigram and natal mewa, the birth month. Null where a son's father is not known or the mother is not
     * born before.
     */
    fun hundred(birth: Sign, birthYear: Int, month: Sign, gender: Gender, motherYear: Int, father: Sign?): Hundred? {
        val motherAge = YearOfLife.age(motherYear, birthYear)
        if (motherAge < 1) return null
        val parent = if (gender == Gender.FEMALE) YearOfLife.yearSign(motherYear) else father ?: return null
        val one = { e: Element -> YearForces(e, e, e, e) }
        return Hundred(
            mapOf(
                UnchangingLuck.PARENT_YEAR to parent.forces,
                UnchangingLuck.MOTHER_LOG_MEN to YearOfLife.logMen(YearOfLife.yearSign(motherYear).element, Gender.FEMALE, motherAge).forces,
                UnchangingLuck.TRIGRAM to one(YearOfLife.trigramElement(YearOfLife.currentTrigram(Gender.FEMALE, motherAge))),
                UnchangingLuck.MEWA to one(smeBaElement(YearOfLife.yearSmeBa(birthYear))),
                UnchangingLuck.MONTH to month.forces,
            ),
            birth.forces,
        )
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
