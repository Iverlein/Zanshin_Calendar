/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import java.time.LocalDate
import java.time.LocalTime
import zanshin.core.texts.gloss

/*
 * WB's illness reckoning (ROADMAP H2; WB vol. 1, ch. 25; docs/sources/corpus/wb/ch25.md), as far as it is built:
 * the illness and spirit identified by the moment of falling ill (H2b). The moment's trigram and mewa are found as
 * the reckoning of the moment finds them (R2o, [MomentReckoning]): the date's own at dawn, run on through the day by
 * WB's rough spans. That the trigram and mewa "under which one fell ill" (p. 431) are the moment's, not the year's or
 * the month's, is a stated choice: ch. 25 says only that the time of falling ill is the year, month, day and hour
 * (p. 450), and the notes join the trigram and mewa to those four times (vol. 2, p. 497). The day animal is the
 * date's (pp. 428–429, question 360).
 */

/**
 * The kind of harmful spirit (WB vol. 1, pp. 433–435): the sick person's birth mewa fought against the mewa they fell
 * ill under, the descending mewa (བབས་སྨེ, babs sme).
 */
enum class IllnessSpiritKind { GOING_GOD, DEBT_DEMON, RTER_BTSOG; val english: String get() = gloss(this) }

/** The four times of falling ill the nineteen pebbles fight (WB vol. 1, p. 445): the sky year, the month as minister, the day as soldier, the hour as weapon. */
enum class IllnessTime { YEAR, MONTH, DAY, HOUR; val english: String get() = gloss(this) }

/** What each of the sixteen pebbles reads at a time: the life force fought, its rise and decline, the body fought, its rise and decline. */
enum class IllnessAspect { LIFE, LIFE_RISE, BODY, BODY_RISE; val english: String get() = gloss(this) }

/** One of the sixteen fought pebbles of the nineteen (p. 445), white or not. */
data class IllnessPebble(val time: IllnessTime, val aspect: IllnessAspect, val white: Boolean)

/** The pairs of year animals p. 426's table reads the sky year by: rat and ox, tiger and hare, and on. */
enum class IllnessYearPair { MOUSE_OX, TIGER_RABBIT, DRAGON_SNAKE, HORSE_SHEEP, MONKEY_BIRD, DOG_PIG;
    companion object { fun of(animal: Animal): IllnessYearPair = entries[animal.ordinal / 2] }
}

/** Three of the five people the sick need (p. 445), by their life force's relation to the sick person's. */
enum class IllnessHelper(val relation: Kinship) { NURSE(Kinship.MOTHER), PHYSICIAN(Kinship.SON), MESSENGER(Kinship.FRIEND);
    val english: String get() = gloss(this)
}

/** A plank-tree's verdict (pp. 422–423): all white it stands, all black it is broken, between them by the greater colour or even. */
enum class IllnessTreeVerdict { STANDS, MOSTLY_WHITE, SPECKLED, MOSTLY_BLACK, BROKEN }

/** One starting point of the board (pp. 417–419), its year reached and its four pebbles, life force, body, power and luck. */
data class IllnessPoint(val n: Int, val reached: Sign, val pebbles: List<PebbleColour>)

/**
 * The board of the fifteen starting points (ROADMAP H2g; WB vol. 1, ch. 25, pp. 417–425) and the pebble door, the
 * sixteenth: the points' pebbles by rows, each aspect's fifteen its plank-tree.
 */
data class IllnessBoard(val points: List<IllnessPoint>, val door: List<PebbleColour>) {
    /** The plank-tree of [force]: its fifteen pebbles, the first five the man's, the middle the woman's, the last the children's. */
    fun tree(force: Force): List<PebbleColour> = points.map { it.pebbles[force.ordinal] }

    /** The verdict of [force]'s plank-tree. */
    fun verdict(force: Force): IllnessTreeVerdict {
        val t = tree(force)
        val white = t.count { it == PebbleColour.WHITE }
        val black = t.count { it == PebbleColour.BLACK }
        return when {
            white == t.size -> IllnessTreeVerdict.STANDS
            black == t.size -> IllnessTreeVerdict.BROKEN
            white > black -> IllnessTreeVerdict.MOSTLY_WHITE
            black > white -> IllnessTreeVerdict.MOSTLY_BLACK
            else -> IllnessTreeVerdict.SPECKLED
        }
    }

    /** The black pebbles of the body's plank-tree, which name the spirit (p. 425). */
    val bodyBlack: Int get() = tree(Force.BODY).count { it == PebbleColour.BLACK }
}

/** The moment someone fell ill: the Tibetan day it falls in, the day turning at dawn, and the minutes since that dawn. */
data class IllnessOnset(val day: TibetanDay, val minutes: Int) {
    /** The trigram under which one fell ill. */
    val trigram: Trigram get() = MomentReckoning.trigram(day.monthNames.animal, day.day, minutes)

    /** The mewa under which one fell ill, the descending mewa. */
    val mewa: Int get() = MomentReckoning.mewa(day.monthNames.animal, day.day, minutes)

    /** The animal of the day of falling ill. */
    val dayAnimal: Animal get() = Forces.signs(day).date.animal

    /** The signs of the four times of falling ill: the year, the month, the day and the hour, from the hare's at dawn. */
    val signs: List<Sign> get() = Forces.signs(day).let { listOf(it.year, it.month, it.date, Forces.hours(it.date)[minutes / 120].sign) }

    /**
     * The trigrams of the four times (p. 435, «སྟག་རྟ་ཁྱི་གསུམ་ལིར་བརྩིས་པའི»): the day's counted on from its month's
     * first date, which is li in a tiger, horse or dog month (the almanac rule, vol. 1, p. 178), and the hour's on
     * from the day's ([trigram]). The month's is the one its first date begins with, and the year's is found by the
     * same triads on the year's animal, as the *Speckled Elements*' line «སྟག་རྟ་ཁྱི་གསུམ་ལོ་ལ་སྤར་ཁ་ལི» reads
     * (question 390): stated choices, no text giving the year's and the month's trigram for this count.
     */
    val trigrams: List<Trigram> get() = listOf(
        MomentReckoning.monthTrigram(day.yearAnimal),
        MomentReckoning.monthTrigram(day.monthNames.animal),
        MomentReckoning.dateTrigram(day.monthNames.animal, day.day),
        trigram,
    )

    /**
     * The five element pebbles of the illness identified (p. 435; the notes, vol. 2, p. 497): the life force of the
     * year, month, day and hour of falling ill, and the element most among the four times' trigrams; only four where
     * no one element is most among them (two and two, or four apart), on which no text rules.
     */
    val pebbles: List<Element> get() {
        val fifth = trigrams.map { YearOfLife.trigramElement(it) }.groupingBy { it }.eachCount().let { counts ->
            val most = counts.values.max()
            counts.filterValues { it == most }.keys.singleOrNull()
        }
        return signs.map { it.forces.vitality } + listOfNotNull(fifth)
    }
}

object IllnessReckoning {
    /** The Tibetan day turns at dawn, 05:00, as the moment's does. */
    private const val DAWN = 5 * 60

    /** The moment of falling ill on [date] at [time], clock time: before 05:00 it belongs to the day before. */
    fun onset(date: LocalDate, time: LocalTime): IllnessOnset {
        val clock = time.hour * 60 + time.minute
        val dayDate = if (clock < DAWN) date.minusDays(1) else date
        return IllnessOnset(TibetanCalendar.of(dayDate), Math.floorMod(clock - DAWN, 1440))
    }

    /**
     * The illness of [pebbles] (p. 435): wood is wind, fire blood, earth bile, iron phlegm, water cold, and the element
     * most is the illness; where two elements share the most, both (p. 438: "half and half, the two illnesses weighed").
     */
    fun chief(pebbles: List<Element>): List<Element> {
        val counts = pebbles.groupingBy { it }.eachCount()
        val most = counts.values.max()
        return Element.entries.filter { counts[it] == most }
    }

    /** The splits p. 435–436 names an illness for clearly; the others' names are not read (corpus/wb/ch25.md). */
    val SPLITS: Set<String> = setOf(
        "FIRE4_EARTH1", "FIRE3_EARTH2", "WOOD4_FIRE1", "EARTH3_FIRE2", "EARTH4_FIRE1", "WOOD3_FIRE2", "FIRE4_IRON1", "FIRE3_IRON2",
        "WATER4_EARTH1", "WATER3_IRON2", "IRON4_WATER1", "IRON3_WATER2", "EARTH3_WATER2", "EARTH4_WATER1", "WATER4_WOOD1", "WATER5",
        "WOOD4_WATER1",
    )

    /**
     * The split of [pebbles] as the catalog keys p. 436's illnesses (`FIRE4_EARTH1`, `WATER5`): the elements by count,
     * the larger first; null for five pebbles split among three elements or more, or only four laid, which the lists do
     * not read.
     */
    fun split(pebbles: List<Element>): String? {
        if (pebbles.size != 5) return null
        val counts = pebbles.groupingBy { it }.eachCount().entries.sortedByDescending { it.value }
        if (counts.size > 2) return null
        return counts.joinToString("_") { "${it.key.name}${it.value}" }
    }

    /**
     * The sixteen fought pebbles of the nineteen (pp. 445–447; the notes, vol. 2, p. 498) for one born in [birth] who
     * fell ill at [onset]: at each of the year, month, day and hour, the person's life force and body fought against
     * the time's, white as the circles' pebbles are (mother or friend, earth or water by itself, white; son or enemy,
     * wood, iron or fire by itself, black: [Circles.colour]), and each one's rise and decline carried onto the time's
     * animal, white on the six good stages. The other three, the date, weekday and mansion, are taken as they come.
     */
    fun nineteen(birth: Sign, onset: IllnessOnset): List<IllnessPebble> {
        val own = birth.forces
        return IllnessTime.entries.zip(onset.signs).flatMap { (time, sign) ->
            val theirs = sign.forces
            listOf(
                IllnessPebble(time, IllnessAspect.LIFE, Circles.colour(own.vitality, theirs.vitality) == PebbleColour.WHITE),
                IllnessPebble(time, IllnessAspect.LIFE_RISE, YearOfLife.sector(own.vitality, sign.animal).good),
                IllnessPebble(time, IllnessAspect.BODY, Circles.colour(own.body, theirs.body) == PebbleColour.WHITE),
                IllnessPebble(time, IllnessAspect.BODY_RISE, YearOfLife.sector(own.body, sign.animal).good),
            )
        }
    }

    /**
     * Where the progressed year (ལོག་མེན, log men) of the year of falling ill reaches (p. 426): the life force of one born
     * in [birth], of [gender], fought against the progressed year's life force (the notes, vol. 2, p. 496), in the
     * Tibetan year [year].
     */
    fun reach(birth: Sign, birthYear: Int, gender: Gender, year: Int): Kinship =
        Forces.kinship(birth.forces.vitality, YearOfLife.logMen(birth.element, gender, YearOfLife.age(birthYear, year)).forces.vitality)

    /**
     * The stage of rise and decline of the hearth-keeper's power, carried onto the hour of reckoning (pp. 440–442; the
     * notes, vol. 2, p. 498): the stage the power of one born in [birth] falls on at the hour of [hour].
     */
    fun stage(birth: Sign, hour: Animal): Sector = YearOfLife.sector(birth.element, hour)

    /** The element of the life force of the helper [h] for a sick person whose life force is [vitality] (p. 445). */
    fun helper(h: IllnessHelper, vitality: Element): Element = Element.entries.first { Forces.kinship(vitality, it) == h.relation }

    /**
     * A pebble of the board (p. 417, «རྡེལ་བོང་མ་གྲོགས་དཀར་པོ་དང་། །དགྲ་ནག་བུ་ལ་དཀར་ནག་ཟུང»): the reckoned's aspect [own]
     * meeting [other] as mother or friend white, as enemy black, as son a white and a black, laid as one speckled
     * pebble; meeting itself, earth and water white, wood, iron and fire black.
     */
    fun boardColour(own: Element, other: Element): PebbleColour = when (Forces.kinship(own, other)) {
        Kinship.MOTHER, Kinship.FRIEND -> PebbleColour.WHITE
        Kinship.ENEMY -> PebbleColour.BLACK
        Kinship.SON -> PebbleColour.SPECKLED
        Kinship.IDENTITY -> if (own == Element.EARTH || own == Element.WATER) PebbleColour.WHITE else PebbleColour.BLACK
    }

    /**
     * Where each starting point's count begins, and which way the years lived run from it (pp. 417–419): a sign in
     * an element related to the reckoned's power, or a place counted from the sky year; true for "down" (ཐུར), which
     * runs forward through the sixty, as the progressed year's does (p. 387), false for "up" (གྱེན), backward. Point 9's
     * "the mother's hare or the son's" is the mother's, the first named.
     */
    private val STARTS: List<Triple<Kinship?, Pair<Animal?, Int>, Boolean>> = listOf(
        Triple(null, null to 0, true), // 1 the la's life: from the sky year's place
        Triple(Kinship.ENEMY, Animal.TIGER to 0, true), // 2 the king's seat
        Triple(Kinship.MOTHER, Animal.TIGER to 0, true), // 3 under the felt hat's dust
        Triple(Kinship.IDENTITY, Animal.TIGER to 0, true), // 4 the row of ministers
        Triple(Kinship.FRIEND, Animal.TIGER to 0, true), // 5
        Triple(Kinship.ENEMY, Animal.RABBIT to 0, false), // 6 the demon's thief
        Triple(Kinship.FRIEND, Animal.RABBIT to 0, true), // 7 the karmic ogre
        Triple(Kinship.IDENTITY, Animal.RABBIT to 0, true), // 8 the wish-granting sapling
        Triple(Kinship.MOTHER, Animal.RABBIT to 0, true), // 9 the treasury of gold and silver
        Triple(null, null to -12, true), // 10 the rope from above: thirteen up from the sky year
        Triple(null, null to 12, false), // 11 the hanging rope: thirteen down
        Triple(Kinship.SON, Animal.TIGER to 0, true), // 12 the wind-storm
        Triple(Kinship.MOTHER, Animal.MONKEY to 0, false), // 13 the star-house
        Triple(null, null to -4, true), // 14 the pillow-wood: five up
        Triple(null, null to 4, false), // 15 the support peg: five down
    )

    /**
     * The board for one born in [birth], in the Tibetan year [birthYear], reckoned in the sky year [year], the year of
     * falling ill (question 361): from each point's start the years lived counted inclusively (D19), and the year
     * reached's four aspects fought against the reckoned's; the door the reckoned's four against the sky year's.
     * "The sky-year's earth" (གནམ་ལོའི་ས) is read as the sky year's place, as the *Moonbeams*' plank-tree counts from
     * the sky year's thirteenth and fifth above and below (claims/illness-heads-years-lived.md).
     */
    fun board(birth: Sign, birthYear: Int, year: Int): IllnessBoard {
        val own = birth.forces
        val lived = YearOfLife.age(birthYear, year) - 1
        val sky = YearOfLife.sexagenary(YearOfLife.yearSign(year))
        fun fight(sign: Sign) = Force.entries.map { boardColour(own[it], sign.forces[it]) }
        val points = STARTS.mapIndexed { i, (relation, at, down) ->
            val start = if (relation == null) sky + at.second
            else YearOfLife.sexagenary(Sign(Element.entries.first { Forces.kinship(birth.element, it) == relation }, at.first!!))
            val reached = YearOfLife.signOf(start + if (down) lived else -lived)
            IllnessPoint(i + 1, reached, fight(reached))
        }
        return IllnessBoard(points, fight(YearOfLife.yearSign(year)))
    }

    /**
     * The kind of spirit for one born under the mewa [birth] who fell ill under [descending]: the descending mewa's
     * element the mother of the birth mewa's, or the same, the going god; the enemy or the son, the debt demon; the
     * friend, the rter btsog demon.
     */
    fun spiritKind(birth: Int, descending: Int): IllnessSpiritKind =
        when (Forces.kinship(smeBaElement(birth), smeBaElement(descending))) {
            Kinship.MOTHER, Kinship.IDENTITY -> IllnessSpiritKind.GOING_GOD
            Kinship.ENEMY, Kinship.SON -> IllnessSpiritKind.DEBT_DEMON
            Kinship.FRIEND -> IllnessSpiritKind.RTER_BTSOG
        }
}
