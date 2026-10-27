/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

/*
 * The reckoning of the moment (ROADMAP R2o; WB vol. 2, ch. 30; docs/sources/reckonings.md, RD17 and O16, question
 * 164): the trigram and the mewa of a moment. The date's trigram and mewa stand at dawn, the hare hour; the trigram
 * of a month's first date is li for the tiger, horse and dog months, zin for the pig, sheep and hare, dwa for the
 * bird, ox and snake, kham for the mouse, dragon and monkey, and runs forward one a date; the mewa is 1 white for the
 * first month of each season, 4 green for the middle, 7 red for the last, and runs up one a date (the notes, vol. 2,
 * p. 535; the Moonbeams). Through the day they run on by WB's rough spans (p. 162): three quarters of an hour for a
 * trigram, two thirds for a mewa, eight and nine by day, and the same again from the date's own by night. The day is
 * twelve hours from dawn at 05:00, the night twelve from 17:00.
 */
/** The times of the day the moment's readings are headed by (p. 156), from dawn, one to each hour of the hare to the bird. */
enum class MomentPeriod { DAWN, SUNRISE, WARMTH, NOON, PAST_NOON, LATE, SUNSET; val english: String get() = zanshin.core.texts.gloss(this) }

object MomentReckoning {
    /** The period of the day [minutes] after dawn: the hare's hour dawn, the dragon's sunrise, on to the bird's sunset; null by night. */
    fun period(minutes: Int): MomentPeriod? = MomentPeriod.entries.getOrNull(minutes / 120)

    /**
     * The element of the hour's power in [period] (p. 156, «ཉིན་དྲུག་བུ་འདེད་ཁམས་སྦྱར»; the notes, p. 535): the son of the
     * day's power [day] at dawn, each period's the son of the one before.
     */
    fun hourElement(day: Element, period: MomentPeriod): Element = Element.entries[(day.ordinal + period.ordinal + 1) % 5]

    /** The trigram that dawns on a month's first date, by the month's animal. */
    fun monthTrigram(month: Animal): Trigram = when (month) {
        Animal.TIGER, Animal.HORSE, Animal.DOG -> Trigram.LI
        Animal.PIG, Animal.SHEEP, Animal.RABBIT -> Trigram.ZIN
        Animal.BIRD, Animal.OX, Animal.SNAKE -> Trigram.DWA
        Animal.MOUSE, Animal.DRAGON, Animal.MONKEY -> Trigram.KHAM
    }

    /** The mewa that dawns on a month's first date: 1 for a season's first month, 4 for its middle, 7 for its last. */
    fun monthMewa(month: Animal): Int = when (month) {
        Animal.TIGER, Animal.SNAKE, Animal.MONKEY, Animal.PIG -> 1
        Animal.RABBIT, Animal.HORSE, Animal.BIRD, Animal.MOUSE -> 4
        Animal.DRAGON, Animal.SHEEP, Animal.DOG, Animal.OX -> 7
    }

    /** The trigram of the lunar date [date] (1–30) in a month of [month]: forward one a date in WB's order. */
    fun dateTrigram(month: Animal, date: Int): Trigram = Trigram.entries[(monthTrigram(month).ordinal + date - 1) % 8]

    /** The mewa of the lunar date [date] in a month of [month]: up one a date, round the nine. */
    fun dateMewa(month: Animal, date: Int): Int = (monthMewa(month) + date - 2) % 9 + 1

    /** The moment's trigram, [minutes] after dawn (0–1439) on the lunar date [date] of a month of [month]. */
    fun trigram(month: Animal, date: Int, minutes: Int): Trigram =
        Trigram.entries[(dateTrigram(month, date).ordinal + (minutes % 720) / 90) % 8]

    /** The moment's mewa, [minutes] after dawn on the lunar date [date] of a month of [month]. */
    fun mewa(month: Animal, date: Int, minutes: Int): Int = (dateMewa(month, date) - 1 + (minutes % 720) / 80) % 9 + 1
}

/*
 * The moment's places (ROADMAP R2z; WB vol. 2, ch. 30, pp. 162–168, with Mipham's notes, pp. 535–536;
 * docs/sources/claims/moment-eight-places.md, moment-three-mewa.md, moment-mewa-kin.md, moment-asker-year.md). The
 * moment's sign is the hour's animal with the hour's power: the notes' moment of an iron snake has the power iron,
 * the life force fire, the body iron and the luck water, which are the aspects of the iron snake year. Its aspects
 * fight the asker's, each its own (the notes: «བརྩི་བྱ་དང་དུས་ཀྱི་སྲོག་སོགས་ནང་མཐུན་པར་འཐབ»): the house by the life
 * force, the field by the power, the grave by the luck. What the moment's aspect is to the asker's names the reading:
 * the enemy cuts inward, the friend is cut outward, the mother generates inward, the son outward (the notes' house).
 */

/** The asker's places the moment fights (pp. 166–168), each by the aspect WB reads it by. */
enum class AskerPlace(val force: Force) {
    HOUSE(Force.VITALITY),
    FIELD(Force.DESTINY),
    GRAVE(Force.LUCK);

    val english: String get() = zanshin.core.texts.gloss(this)
}

/** One of the asker's places: the asker's aspect, the moment's, and what the moment's is to the asker's. */
data class AskerMeet(val place: AskerPlace, val asker: Element, val moment: Element) {
    val kinship: Kinship get() = Forces.kinship(asker, moment)
}

/** The people of one's own place (p. 168): oneself at the moment's power, the kin at its other aspects, the enemy at its enemy. */
enum class OwnPlaceRole { ASKER, PATERNAL_KIN, MATERNAL_UNCLE, MAID, LORD, ENEMY; val english: String get() = zanshin.core.texts.gloss(this) }

/**
 * The nine squares of the three mewa joined (p. 165), in WB's count: 1 the centre, 2 the east, on round by the
 * south-east to 9 the north-east.
 */
enum class MomentSquare(val direction: Direction) {
    CENTRE(Direction.CENTRE),
    EAST(Direction.EAST),
    SOUTH_EAST(Direction.SOUTH_EAST),
    SOUTH(Direction.SOUTH),
    SOUTH_WEST(Direction.SOUTH_WEST),
    WEST(Direction.WEST),
    NORTH_WEST(Direction.NORTH_WEST),
    NORTH(Direction.NORTH),
    NORTH_EAST(Direction.NORTH_EAST);

    /** WB's number of the square, 1 for the centre. */
    val number: Int get() = ordinal + 1
}

/** What befalls a square's kin (p. 165): cut inward, it happened; for the seventh, generated inward, its luck stands. */
enum class SquareFate { CUT, CLEAR, STABLE }

/** The guardians a square's meeting shows (p. 165): a mother good guardian gods, a friend harmful demons, else neither. */
enum class SquareGuard { GODS, DEMONS, NEITHER }

/** One square of the three mewa joined: its mewa, and what that mewa is to the year's, the one reckoned. */
data class SquareMeet(val square: MomentSquare, val mewa: Int, val yearMewa: Int) {
    val kinship: Kinship get() = Forces.kinship(smeBaElement(yearMewa), smeBaElement(mewa))

    /**
     * The pebble the notes lay (p. 536): a mother white; an enemy, a friend and wood or iron meeting itself black; a son
     * and earth or water meeting itself speckled. Fire meeting itself the notes do not name; it is laid black, with
     * wood and iron, as the kinship colours lay it.
     */
    val colour: PebbleColour get() = when (kinship) {
        Kinship.MOTHER -> PebbleColour.WHITE
        Kinship.ENEMY, Kinship.FRIEND -> PebbleColour.BLACK
        Kinship.SON -> PebbleColour.SPECKLED
        Kinship.IDENTITY -> if (smeBaElement(mewa) == Element.EARTH || smeBaElement(mewa) == Element.WATER) PebbleColour.SPECKLED else PebbleColour.BLACK
    }

    val guard: SquareGuard get() = when (kinship) {
        Kinship.MOTHER -> SquareGuard.GODS
        Kinship.FRIEND -> SquareGuard.DEMONS
        else -> SquareGuard.NEITHER
    }

    /**
     * The fate of the square's kin, null for the centre, which names none: the square's mewa cutting the year's inward,
     * its enemy, the harm happened; the seventh's luck stands where its mewa generates the year's, and WB says nothing
     * of it otherwise; every other meeting the harm did not happen.
     */
    val fate: SquareFate? get() = when {
        square == MomentSquare.CENTRE -> null
        kinship == Kinship.ENEMY -> SquareFate.CUT
        square == MomentSquare.NORTH_WEST -> if (kinship == Kinship.MOTHER) SquareFate.STABLE else null
        else -> SquareFate.CLEAR
    }
}

object MomentPlaces {
    /** The animal of the hour [minutes] after dawn: the hare's from 05:00, two hours each. */
    fun hourAnimal(minutes: Int): Animal = Animal.entries[(Animal.RABBIT.ordinal + minutes / 120) % 12]

    /**
     * The moment's sign [minutes] after dawn on a day whose power is [day]: the hour's animal with the hour's power
     * ([MomentReckoning.hourElement]); null by night, when WB reckons no hour's power.
     */
    fun sign(day: Element, minutes: Int): Sign? =
        MomentReckoning.period(minutes)?.let { Sign(MomentReckoning.hourElement(day, it), hourAnimal(minutes)) }

    /** [place] of an asker born in a year of [asker]'s aspects at the moment of [moment]. */
    fun meet(place: AskerPlace, asker: YearForces, moment: Sign): AskerMeet = AskerMeet(place, asker[place.force], moment.forces[place.force])

    /**
     * The grave's reading for [m] (pp. 167–168), null where WB gives none: the lucks of one element (no earth), the
     * three pairs that cut (fire and water, iron and wood, iron and fire), a mother or son; the catalog key's last part.
     */
    fun graveKey(m: AskerMeet): String? {
        val pair = setOf(m.asker, m.moment)
        return when {
            m.kinship == Kinship.MOTHER || m.kinship == Kinship.SON -> "KIN"
            m.kinship == Kinship.IDENTITY -> if (m.asker == Element.EARTH) null else "SAME.${m.asker.name}"
            pair == setOf(Element.FIRE, Element.WATER) -> "FIRE_WATER"
            pair == setOf(Element.IRON, Element.WOOD) -> "IRON_WOOD"
            pair == setOf(Element.FIRE, Element.IRON) -> "FIRE_IRON"
            else -> null
        }
    }

    /** The stage of [element]'s rise and decline on the moment's animal (pp. 167–168: "the power's rise and decline on the moment"). */
    fun stage(element: Element, moment: Sign): Sector = YearOfLife.sector(element, moment.animal)

    /** Whether there is a field (p. 167): the asker's power on a good stage at the moment. */
    fun fieldThere(asker: YearForces, moment: Sign): Boolean = stage(asker.destiny, moment).good

    /** Whether the grave's luck declines (p. 168): the asker's luck on a bad stage at the moment. */
    fun graveDeclining(asker: YearForces, moment: Sign): Boolean = !stage(asker.luck, moment).good

    /**
     * Whether kin and enemy stand at one's own place (p. 168; the notes, p. 536): the moment's power on a good stage on
     * the moment's animal, friends and no enemy; on a bad one, enemies and no friend.
     */
    fun kinStage(moment: Sign): Sector = stage(moment.element, moment)

    /**
     * Where each of one's own place stands (the notes, p. 536): oneself at the direction of the moment's power, the
     * paternal kin at its life force's, the maternal uncle at its body's, the maid at its friend's, the lord at its
     * luck's, the enemy at its enemy's; wood the east, fire the south, iron the west, water the north. Earth the notes'
     * example does not place; it is set in the centre, where the sme ba square puts the five yellow.
     */
    fun ownPlace(moment: Sign): List<Pair<OwnPlaceRole, Direction>> {
        val f = moment.forces
        val enemy = Element.entries.first { it.overcomes == moment.element }
        return listOf(
            OwnPlaceRole.ASKER to f.destiny,
            OwnPlaceRole.PATERNAL_KIN to f.vitality,
            OwnPlaceRole.MATERNAL_UNCLE to f.body,
            OwnPlaceRole.MAID to moment.element.overcomes,
            OwnPlaceRole.LORD to f.luck,
            OwnPlaceRole.ENEMY to enemy,
        ).map { (role, e) -> role to direction(e) }
    }

    /** The direction of an element in the notes' own place. */
    fun direction(e: Element): Direction = when (e) {
        Element.WOOD -> Direction.EAST
        Element.FIRE -> Direction.SOUTH
        Element.IRON -> Direction.WEST
        Element.WATER -> Direction.NORTH
        Element.EARTH -> Direction.CENTRE
    }

    /**
     * The three mewa joined (p. 165; the notes, p. 536): the day's mewa [dayMewa] in the centre, the others laid out from
     * it in the square's order, each fought by the year's mewa [yearMewa], the one reckoned. The notes' example: the
     * day's 4 green in the centre, the year's 8 white meets it as a friend, the east's 2 black as a son, the north-east's
     * 7 red as an enemy.
     */
    fun squares(dayMewa: Int, yearMewa: Int): List<SquareMeet> =
        MomentSquare.entries.map { SquareMeet(it, DaySmeBa.at(it.direction, dayMewa), yearMewa) }

    /**
     * The mewa in the forefathers' place (pp. 163–164; the notes, p. 535): the moment's mewa [mewa] in the centre, the
     * others laid out from it, the crown of the figure lying head to the west; with 5 yellow in the centre, 7 red.
     */
    fun forefathers(mewa: Int): Int = DaySmeBa.at(Direction.WEST, mewa)
}

/**
 * The kin's places on the figure of a man lying head to the west (pp. 163–164; the notes, p. 535: the head west, the
 * right shoulder south-west, the left north-west, the father's place the right shoulder), the moment's mewa in the
 * centre: one's own household the centre, the forefathers the crown, the father the right shoulder, the maternal uncle
 * the left, the father's brothers the right side, the brothers and dependants the right foot, the servants and the
 * field the left foot, the mother and spouse the lap. The left side, «ཡམས་སྐུད», WB does not explain; it is not read.
 */
enum class KinPlace(val direction: Direction) {
    HOUSEHOLD(Direction.CENTRE),
    FOREFATHERS(Direction.WEST),
    FATHER(Direction.SOUTH_WEST),
    MATERNAL_UNCLE(Direction.NORTH_WEST),
    FATHERS_BROTHERS(Direction.SOUTH),
    BROTHERS(Direction.SOUTH_EAST),
    SERVANTS(Direction.NORTH_EAST),
    MOTHER_SPOUSE(Direction.EAST);

    val english: String get() = zanshin.core.texts.gloss(this)
}

/**
 * The thirteen gates (pp. 170–171; claims moment-thirteen-gates, moment-asker-year): the sky year is the asker's own
 * birth year (the notes, p. 536). Each gate starts from its year of the sixty, counts the asker's years of age through
 * the sixty, a man down and a woman up, the first year the start (as the thirty count, D22), and fights the year
 * reached, or the years [checks] up and down from it, against the moment; the harm comes when that year cuts the
 * moment inward. Stated choices (question 422): the "five" of gates 6, 10, 11 and 13 («ལྔ་ནས») is read as no offset,
 * as gate 5 alone gives its five as a count; gate 9's start is the own element's ox, the sheep's seventh; "eight up"
 * and "three up" count inclusively, the year reached the first.
 */
enum class MomentGate(val checks: List<Int> = listOf(0)) {
    KING(listOf(7, -7)),
    MINISTER,
    FIRE_OX(listOf(2, -2)),
    SKY,
    SWASTIKA,
    SUN_ARMY,
    DEMON_CUTTING,
    GODS_DEMONS,
    THIEF,
    PEACOCK,
    SKY_WANDERINGS,
    EARTH_WANDERINGS,
    RUIN_HORSE;

    val english: String get() = zanshin.core.texts.gloss(this)
}

/** One year a gate or a count fights against the moment: where it stands and whether it cuts the moment inward. */
data class GateYear(val offset: Int, val year: Sign, val cuts: Boolean)

/** A gate counted for the asker: the year it starts from, the year reached, and the years fought. */
data class GateReading(val gate: MomentGate, val start: Sign, val reached: Sign, val years: List<GateYear>)

/**
 * The counts after the gates, their small reckonings (pp. 171–172; claims moment-sixty-pairs,
 * moment-gates-small-reckonings): each from a fixed year, or for a man and a woman from two, the asker's years counted
 * a man down and a woman up, and the year reached cutting the moment inward, or for the four doors the year [after]
 * from it landing on one of [lands]. The ogre's and the demon's start from the moment seven up and down. Which year is
 * counted from them WB does not say (question 344); the asker's, as the gates count.
 */
enum class SixtyCount(
    val male: Sign?,
    val female: Sign? = male,
    val after: Int = 0,
    val lands: Set<Animal> = emptySet(),
) {
    OGRE(null),
    DEMON(null),
    IRON_DOG(Sign(Element.IRON, Animal.DOG)),
    EARTH_SHEEP(Sign(Element.EARTH, Animal.SHEEP)),
    IRON_BIRD(Sign(Element.IRON, Animal.BIRD)),
    EARTH_PIG(Sign(Element.EARTH, Animal.PIG)),
    EARTH_DRAGON(Sign(Element.EARTH, Animal.DRAGON)),
    EARTH_SHEEP_STONE(Sign(Element.EARTH, Animal.SHEEP)),
    IRON_DOG_CRYSTAL(Sign(Element.IRON, Animal.DOG)),
    FIRE_OX(Sign(Element.FIRE, Animal.OX)),
    WATER_SNAKE(Sign(Element.WATER, Animal.SNAKE)),
    IRON_HORSE(Sign(Element.IRON, Animal.HORSE)),
    WATER_RAT(Sign(Element.WATER, Animal.MOUSE)),
    CHANGE(null),
    WOOD(Sign(Element.WOOD, Animal.TIGER), Sign(Element.WOOD, Animal.RABBIT)),
    FIRE(Sign(Element.FIRE, Animal.HORSE), Sign(Element.FIRE, Animal.SNAKE)),
    IRON(Sign(Element.IRON, Animal.MONKEY), Sign(Element.IRON, Animal.BIRD)),
    WATER(Sign(Element.WATER, Animal.MOUSE), Sign(Element.WATER, Animal.PIG)),
    EARTH_DRAGON_UNCLE(Sign(Element.EARTH, Animal.DRAGON)),
    EARTH_SHEEP_SON(Sign(Element.EARTH, Animal.SHEEP)),
    EARTH_DOG(Sign(Element.EARTH, Animal.DOG)),
    EARTH_OX(Sign(Element.EARTH, Animal.OX)),
    EARTH(Sign(Element.EARTH, Animal.TIGER), Sign(Element.EARTH, Animal.RABBIT)),
    FIRE_GRANDMOTHERS(Sign(Element.FIRE, Animal.MOUSE), Sign(Element.FIRE, Animal.PIG)),
    IRON_ILLNESS(Sign(Element.IRON, Animal.HORSE), Sign(Element.IRON, Animal.SNAKE)),
    WATER_UNCLE(Sign(Element.WATER, Animal.DRAGON), Sign(Element.WATER, Animal.DOG)),
    WATER_SHEEP(Sign(Element.WATER, Animal.SHEEP)),
    WATER_OX(Sign(Element.WATER, Animal.OX)),
    WATER_DOG(Sign(Element.WATER, Animal.DOG)),
    SKY_DOOR(Sign(Element.IRON, Animal.DRAGON), after = 7, lands = setOf(Animal.DOG, Animal.DRAGON)),
    EARTH_DOOR(Sign(Element.WATER, Animal.PIG), after = -7, lands = setOf(Animal.PIG, Animal.SNAKE)),
    SEPARATION(Sign(Element.FIRE, Animal.OX), after = 2, lands = setOf(Animal.BIRD, Animal.MONKEY)),
    RUIN(Sign(Element.WOOD, Animal.OX), after = -2, lands = setOf(Animal.OX, Animal.SHEEP)),
}

/** A count after the gates that falls: where it started and the year reached. */
data class SixtyReading(val count: SixtyCount, val start: Sign, val reached: Sign)

object MomentGates {
    private fun at(s: Sign, steps: Int): Sign = YearOfLife.signOf(YearOfLife.sexagenary(s) + steps)

    /** The year [age] years of age from [start], the start the first, down for a man and up for a woman. */
    fun counted(start: Sign, age: Int, down: Boolean): Sign = at(start, if (down) -(age - 1) else age - 1)

    /** Whether [year] cuts the moment [moment] inward: its element overcomes the moment's. */
    fun cuts(year: Sign, moment: Sign): Boolean = year.element.overcomes == moment.element

    /** Where [gate] starts for an asker born in [sky], a man if [male]. */
    fun start(gate: MomentGate, sky: Sign, male: Boolean): Sign {
        val own = sky.element
        val son = own.feeds
        val friend = own.overcomes
        val enemy = Element.entries.first { it.overcomes == own }
        val mother = Element.entries.first { it.feeds == own }
        return when (gate) {
            MomentGate.KING -> Sign(own, Animal.MOUSE)
            MomentGate.MINISTER -> if (male) Sign(son, Animal.TIGER) else Sign(son, Animal.MONKEY)
            MomentGate.FIRE_OX -> Sign(son, Animal.entries[(sky.animal.ordinal + 2) % 12])
            MomentGate.SKY -> at(Sign(son, Animal.OX), -12)
            MomentGate.SWASTIKA -> at(Sign(son, Animal.MONKEY), if (male) -4 else 4)
            MomentGate.SUN_ARMY -> Sign(friend, Animal.RABBIT)
            MomentGate.DEMON_CUTTING -> Sign(enemy, Animal.BIRD)
            MomentGate.GODS_DEMONS -> Sign(mother, Animal.HORSE)
            MomentGate.THIEF -> Sign(own, Animal.OX)
            MomentGate.PEACOCK -> at(Sign(own, Animal.MONKEY), 12)
            MomentGate.SKY_WANDERINGS -> at(Sign(own, Animal.PIG), 24)
            MomentGate.EARTH_WANDERINGS -> at(Sign(own, Animal.PIG), -24)
            MomentGate.RUIN_HORSE -> at(Sign(own, Animal.MOUSE), -12)
        }
    }

    /**
     * The thirteen gates for an asker born in [sky], in their [age]th year of age, a man if [male], at the moment of
     * [moment]; the king's command is counted down for all («ཐུར་དུ་སྦུབ»).
     */
    fun gates(sky: Sign, age: Int, male: Boolean, moment: Sign): List<GateReading> = MomentGate.entries.map { g ->
        val start = start(g, sky, male)
        val reached = counted(start, age, g == MomentGate.KING || male)
        GateReading(g, start, reached, g.checks.map { o -> at(reached, o).let { GateYear(o, it, cuts(it, moment)) } })
    }

    /** Where [count] starts for an asker born in [sky], a man if [male], at the moment of [moment]. */
    fun start(count: SixtyCount, sky: Sign, male: Boolean, moment: Sign): Sign = when (count) {
        SixtyCount.OGRE -> at(moment, 6)
        SixtyCount.DEMON -> at(moment, -6)
        SixtyCount.CHANGE -> if (male) Sign(sky.element.feeds, Animal.TIGER) else Sign(Element.entries.first { it.feeds == sky.element }, Animal.MONKEY)
        else -> if (male) count.male!! else count.female!!
    }

    /** The counts after the gates that fall on the asker at the moment: a cut inward, or a door's landing. */
    fun sixty(sky: Sign, age: Int, male: Boolean, moment: Sign): List<SixtyReading> = SixtyCount.entries.mapNotNull { c ->
        val start = start(c, sky, male, moment)
        val reached = counted(start, age, male)
        val falls = if (c.lands.isNotEmpty()) at(reached, c.after).animal in c.lands else cuts(reached, moment)
        if (falls) SixtyReading(c, start, reached) else null
    }
}

/** The mewa standing in each kin's place, the moment's mewa [mewa] in the centre (pp. 163–164). */
fun MomentPlaces.kinPlaces(mewa: Int): List<Pair<KinPlace, Int>> = KinPlace.entries.map { it to DaySmeBa.at(it.direction, mewa) }

/** The five solid organs by their elements, whose rise and decline on the moment shows where the illness is (p. 170). */
enum class Organ(val element: Element) {
    LIVER(Element.WOOD), HEART(Element.FIRE), SPLEEN(Element.EARTH), LUNGS(Element.IRON), KIDNEYS(Element.WATER);

    val english: String get() = zanshin.core.texts.gloss(this)
}

/** Where a neighbouring house stands from one's own (p. 169; the notes, p. 536): right the enemy, left the friend, behind the mother, in front the son. */
enum class HouseSide { CENTRE, RIGHT, LEFT, BEHIND, FRONT; val english: String get() = zanshin.core.texts.gloss(this) }

/** The sick one's life by the life force's stage on the moment (p. 170): rising lives, the breath-taking and the womb hard, declining in danger. */
enum class SickLife { LIVES, HARD, DECLINE }

/** Whether beer is given (p. 169), by the moment's power against the asker's. */
enum class BeerGiven { GIVEN, NOT_GIVEN, HALF, OWN_SIDE }

/** Whether the drinking is finished (p. 169), by the moment's and the asker's years, male or female. */
enum class BeerDrunk { FINISHED, NOT_FINISHED, HALF }

/** How often food was eaten (p. 170), by the moment's power on the asker's year and the asker's on the moment. */
enum class EatenCount { TWICE, ONCE, NONE }

/**
 * The moment's small examinations of the asker (pp. 168–170; the notes, p. 536; claim moment-asker-year): each by the
 * asker's aspects set on the moment's animal, rising on the six good stages and declining on the six bad, or by the
 * meeting of the asker's aspect with the moment's. [asker] is the asker's birth year's aspects, [moment] the moment's
 * sign.
 */
object MomentSigns {
    private fun up(e: Element, moment: Sign) = MomentPlaces.stage(e, moment).good

    /** Whether the wealth is there (p. 168): the body and the power both rising, and the moment's element cutting neither. */
    fun wealthPresent(asker: YearForces, moment: Sign): Boolean =
        up(asker.body, moment) && up(asker.destiny, moment) && moment.element.overcomes != asker.body && moment.element.overcomes != asker.destiny

    /** The demon by the asker's body against the moment's power (p. 169): what the moment's power is to the body. */
    fun demonMeet(asker: YearForces, moment: Sign): Kinship = Forces.kinship(asker.body, moment.element)

    /** Whether there is beer (p. 169): the body and the power both rising. */
    fun beerThere(asker: YearForces, moment: Sign): Boolean = up(asker.body, moment) && up(asker.destiny, moment)

    /** Whether beer is given (p. 169): the moment's power generating the asker's inward, cutting it, generating outward or cut outward, or the same. */
    fun beerGiven(asker: YearForces, moment: Sign): BeerGiven = when (Forces.kinship(asker.destiny, moment.element)) {
        Kinship.MOTHER -> BeerGiven.GIVEN
        Kinship.ENEMY -> BeerGiven.NOT_GIVEN
        Kinship.SON, Kinship.FRIEND -> BeerGiven.HALF
        Kinship.IDENTITY -> BeerGiven.OWN_SIDE
    }

    /** Whether the drinking is finished (p. 169): the moment's and the asker's animals both male, both female, or one of each. */
    fun beerDrunk(asker: Sign, moment: Sign): BeerDrunk = when {
        asker.animal.gender != moment.animal.gender -> BeerDrunk.HALF
        asker.animal.gender == Gender.MALE -> BeerDrunk.FINISHED
        else -> BeerDrunk.NOT_FINISHED
    }

    /** The neighbouring houses (p. 169; the notes, p. 536): one's own sign in the centre, the moment's element's enemy right, friend left, mother behind, son in front. */
    fun houses(moment: Sign): List<Pair<HouseSide, Element>> {
        val e = moment.element
        return listOf(
            HouseSide.CENTRE to e,
            HouseSide.RIGHT to Element.entries.first { it.overcomes == e },
            HouseSide.LEFT to e.overcomes,
            HouseSide.BEHIND to Element.entries.first { it.feeds == e },
            HouseSide.FRONT to e.feeds,
        )
    }

    /** The asker as the sick one (p. 170): the life force rising lives; on the breath-taking or the womb, hard; on the other bad stages, declining. */
    fun sickLife(asker: YearForces, moment: Sign): SickLife = when (MomentPlaces.stage(asker.vitality, moment)) {
        Sector.TAKING_BREATH, Sector.IN_THE_WOMB -> SickLife.HARD
        else -> if (up(asker.vitality, moment)) SickLife.LIVES else SickLife.DECLINE
    }

    /**
     * The sick one's clothing, pillow, mat and food by the moment's element's mother, son, enemy and friend (p. 170: "a
     * sick one at a wood moment: a red pillow, a white mat, fried pastry eaten").
     */
    fun sickBed(moment: Sign): List<Pair<Kinship, Element>> {
        val e = moment.element
        return listOf(
            Kinship.MOTHER to Element.entries.first { it.feeds == e },
            Kinship.SON to e.feeds,
            Kinship.ENEMY to Element.entries.first { it.overcomes == e },
            Kinship.FRIEND to e.overcomes,
        )
    }

    /**
     * How often food was eaten (p. 170): the moment's power on the asker's year animal and the asker's year element on
     * the moment's animal, both rising twice, the asker's alone once, both declining none; the moment's alone WB does not
     * read, null.
     */
    fun eaten(asker: Sign, moment: Sign): EatenCount? {
        val momentUp = YearOfLife.sector(moment.element, asker.animal).good
        val askerUp = up(asker.element, moment)
        return when {
            momentUp && askerUp -> EatenCount.TWICE
            askerUp -> EatenCount.ONCE
            !momentUp -> EatenCount.NONE
            else -> null
        }
    }

    /** The organs declining on the moment (p. 170), where the illness is. */
    fun illnessSeat(moment: Sign): List<Organ> = Organ.entries.filter { !up(it.element, moment) }

    /** Whether trade goes well (p. 170): the asker's power rising on the moment. */
    fun trade(asker: YearForces, moment: Sign): Boolean = up(asker.destiny, moment)

    /** Whether disputes are won (p. 170): the asker's luck rising on the moment. */
    fun dispute(asker: YearForces, moment: Sign): Boolean = up(asker.luck, moment)
}

/** The first, middle and last part of a life, as the cutting on the figure reads them (p. 163). */
enum class LifePart { EARLY, MIDDLE, LATE }

/** How a part of life goes (p. 163; the notes, p. 536): a mother or friend nourishes, good; an element meeting itself rises and falls; cut or faded, bad. */
enum class LifeVerdict { GOOD, RISE_FALL, BAD }

/**
 * The kin whose parts of life the cutting reads, with the three places on the figure for their first, middle and last
 * part (p. 163, read on the scan; the figure as [KinPlace] lays it): the father and uncles the left shoulder, crown and
 * right shoulder; the father's brothers the right shoulder, right side and right foot; the mother's kin the right foot,
 * the front and the left foot; the maternal uncles the left foot, left side and left shoulder; the children the crown,
 * centre and front; oneself the right side, centre and left side.
 */
enum class KinGroup(val places: List<Direction>) {
    FATHERS(listOf(Direction.NORTH_WEST, Direction.WEST, Direction.SOUTH_WEST)),
    FATHERS_BROTHERS(listOf(Direction.SOUTH_WEST, Direction.SOUTH, Direction.SOUTH_EAST)),
    MOTHERS(listOf(Direction.SOUTH_EAST, Direction.EAST, Direction.NORTH_EAST)),
    MATERNAL_UNCLES(listOf(Direction.NORTH_EAST, Direction.NORTH, Direction.NORTH_WEST)),
    CHILDREN(listOf(Direction.WEST, Direction.CENTRE, Direction.EAST)),
    ONESELF(listOf(Direction.SOUTH, Direction.CENTRE, Direction.NORTH)),
}

/** One part of a kin's life: the mewa standing in its place and how the kin's life force meets it. */
data class LifePartReading(val part: LifePart, val mewa: Int, val kinship: Kinship, val verdict: LifeVerdict)

/** What the grain brought for the casting shows (p. 170), by the moment's life force's stage. */
enum class GrainMeasure { FULL, MIDDLING, LITTLE }

object MomentKin {
    /** How a life force meets a mewa (the notes, p. 536: «མ་གྲོགས་བཟང་དགྲ་དང་མདངས་ཤོར་ཏེ་བུ་ངན། ནང་འཕྲད་འཕར་འབུབ་ཆེ»). */
    fun verdict(k: Kinship): LifeVerdict = when (k) {
        Kinship.MOTHER, Kinship.FRIEND -> LifeVerdict.GOOD
        Kinship.IDENTITY -> LifeVerdict.RISE_FALL
        Kinship.ENEMY, Kinship.SON -> LifeVerdict.BAD
    }

    /**
     * The three parts of life of a kin of [group] whose life force is [lifeForce], the moment's mewa [mewa] in the centre
     * of the figure. The notes' example (p. 535): 5 yellow in the centre, the father and grandfather of horse and snake
     * years, fire: early life 6 white, a friend, good; middle 7 red, fire itself, rise and fall; late 2 black, an
     * enemy, bad.
     */
    fun lifeParts(group: KinGroup, lifeForce: Element, mewa: Int): List<LifePartReading> = LifePart.entries.map { part ->
        val m = DaySmeBa.at(group.places[part.ordinal], mewa)
        val k = Forces.kinship(lifeForce, smeBaElement(m))
        LifePartReading(part, m, k, verdict(k))
    }

    /** The forefathers' year against the moment (p. 168): cutting the moment, no fault in the grave; cut by it inward, a fault; else null. */
    fun forefathersYear(year: Sign, moment: Sign): Boolean? = when {
        year.element.overcomes == moment.element -> false
        moment.element.overcomes == year.element -> true
        else -> null
    }

    /** The grain of the casting (p. 170): the moment's life force rising full, on the breath-taking or the womb middling, declining little. */
    fun grain(moment: Sign): GrainMeasure {
        val stage = MomentPlaces.stage(moment.forces.vitality, moment)
        return when {
            stage == Sector.TAKING_BREATH || stage == Sector.IN_THE_WOMB -> GrainMeasure.MIDDLING
            stage.good -> GrainMeasure.FULL
            else -> GrainMeasure.LITTLE
        }
    }

    /** Where the grain is brought from (p. 170): the direction of the moment's element or of its enemy; a wood moment, east or west. */
    fun grainFrom(moment: Sign): List<Direction> =
        listOf(MomentPlaces.direction(moment.element), MomentPlaces.direction(Element.entries.first { it.overcomes == moment.element }))
}
