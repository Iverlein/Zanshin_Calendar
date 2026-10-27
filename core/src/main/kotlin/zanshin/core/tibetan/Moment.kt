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
