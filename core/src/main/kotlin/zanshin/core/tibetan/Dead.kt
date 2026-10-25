/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import zanshin.core.texts.gloss

/*
 * The reckoning for the dead (ROADMAP R2n; WB vol. 2, ch. 29, the copper vessel; docs/sources/reckonings.md, RD16):
 * its first examination, whether the time of death had come (pp. 55–57), as far as the dates decide it. The dead's
 * vitality, body, destiny and luck fight the roots the notes name (vol. 2, p. 500): the year of death, the dead's
 * progressed sign, trigram and mewa of the year of age, the rise and decline on the year of death, the month and the
 * day of death and the hour of death; the planet and the mansion, fought by element, are not reckoned. Each root is a
 * place counted once by its colour, as the notes count the year of age (p. 490), and each aspect's deciding pebble
 * lies on the more. Its sixth and seventh tests read the year of age's mewa obstacles and course at the year of death.
 * The second examination reads the reason the death met (pp. 57–58), the third which demon carried it off (pp. 58–68)
 * by the dead's year animal, the day's animal, the weekday, the mansion and the hour of death; the fifth the rebirth by
 * the day's animal and the hour (pp. 70–72), the sixth help by virtue by the year of death (p. 72).
 */

/** The roots of the dead's pebbles (the notes, vol. 2, p. 500), in the notes' order, the planet and mansion left out. */
enum class DeadRoot { DEATH_YEAR, LOG_MEN, TRIGRAM, SME_BA, SECTOR, MONTH, DATE, HOUR; val english: String get() = gloss(this) }

/** The whole of the first examination (pp. 56–57): the black pebbles many, the life spent; the white many, an untimely death. */
enum class DeadWhole { SPENT, UNTIMELY; val english: String get() = gloss(this) }

/** One root's pebbles for one aspect; [other] the root's element for the aspect, null for the rise and decline. */
data class DeadPebble(val root: DeadRoot, val force: Force, val pebbles: Pebbles, val other: Element? = null)

/**
 * The second examination's cuts (WB vol. 2, p. 57): an element cut by its enemy, or met within by itself. The two
 * waters' meeting within is counted but not read (its words, «ཁང་ནག་བཤུས», not settled).
 */
enum class DeadCut(val own: Element, val inner: Boolean) {
    IRON_CUT(Element.IRON, false), FIRE_CUT(Element.FIRE, false), EARTH_CUT(Element.EARTH, false),
    WATER_CUT(Element.WATER, false), WOOD_CUT(Element.WOOD, false),
    FIRE_INNER(Element.FIRE, true), WOOD_INNER(Element.WOOD, true), WATER_INNER(Element.WATER, true),
    IRON_INNER(Element.IRON, true), EARTH_INNER(Element.EARTH, true);

    val read: Boolean get() = this != WATER_INNER
    val english: String get() = gloss(this)
}

/** The six kinds of birth the fifth examination reads (p. 71). */
enum class DeadRebirth { GOD, ASURA, HUMAN, ANIMAL, HUNGRY_GHOST, HELL; val english: String get() = gloss(this) }

/** The four times of the second examination's reason by time (p. 58): the year, month, day and hour of death. */
enum class DeadTime { YEAR, MONTH, DAY, HOUR; val english: String get() = gloss(this) }

/**
 * The dead's first examination: [year] the year of age reckoning of the dead at the year of death (its present year
 * the year of death, its hour the hour of death where it is known), [month] and [date] the signs of the month and the
 * lunar date of the death.
 */
data class DeadReckoning(val year: YearReckoning, val month: Sign, val date: Sign, val weekday: Weekday? = null, val mansion: Mansion? = null) {
    val pebbles: List<DeadPebble> = buildList {
        val roots = mapOf(
            BasicSign.PRESENT_YEAR to DeadRoot.DEATH_YEAR, BasicSign.LOG_MEN to DeadRoot.LOG_MEN, BasicSign.TRIGRAM to DeadRoot.TRIGRAM,
            BasicSign.SME_BA to DeadRoot.SME_BA, BasicSign.SECTOR to DeadRoot.SECTOR, BasicSign.HOUR to DeadRoot.HOUR,
        )
        year.pebbles.filter { it.basic != BasicSign.HOUR }.forEach { add(DeadPebble(roots.getValue(it.basic), it.force, it.pebbles, it.other)) }
        for ((root, sign) in listOf(DeadRoot.MONTH to month, DeadRoot.DATE to date)) Force.entries.forEach { f ->
            val own = year.forces[f]
            add(DeadPebble(root, f, Forces.pebbles(Forces.kinship(own, sign.forces[f]), own), sign.forces[f]))
        }
        year.pebbles.filter { it.basic == BasicSign.HOUR }.forEach { add(DeadPebble(DeadRoot.HOUR, it.force, it.pebbles, it.other)) }
    }

    /** The places of [force] counted once each: of white alone, of black alone; a place of both colours counts for neither. */
    fun places(force: Force): Pebbles = pebbles.filter { it.force == force }.map { it.pebbles }
        .let { ps -> Pebbles(ps.count { it.white > 0 && it.black == 0 }, ps.count { it.black > 0 && it.white == 0 }) }

    /** The deciding pebble (*btang gcod*) of [force]: on the more of its places, speckled when they are even. */
    fun deciding(force: Force): PebbleColour = places(force).let {
        when {
            it.white > it.black -> PebbleColour.WHITE
            it.black > it.white -> PebbleColour.BLACK
            else -> PebbleColour.SPECKLED
        }
    }

    /** The whole (pp. 56–57) on the four deciding pebbles: three or four black, the life spent; three or four white, untimely; else none. */
    val whole: DeadWhole? get() = Force.entries.map(::deciding).let { d ->
        when {
            d.count { it == PebbleColour.BLACK } >= 3 -> DeadWhole.SPENT
            d.count { it == PebbleColour.WHITE } >= 3 -> DeadWhole.UNTIMELY
            else -> null
        }
    }

    /**
     * The first examination's sixth test (p. 56): the dead's mewa of the year of age at the year of death on its four
     * small obstacles, of the house, the bed, the land and the royal gate: the time of death; none of them, untimely.
     */
    val mewaObstacles: List<SmeBaObstacle> get() = year.smeBaObstacles.filter { it in SMALL_OBSTACLES }

    /**
     * The seventh test (p. 56): the course of the years at the year of death, the obstacles of the year of age that
     * hold then of those it names: one's own year, its seventh, the triad, the fourth up and down, the progressed sign's
     * demon cut, the nine-multiples and the grave-eye; and the aspects whose rise and decline falls on a bad stage.
     */
    val course: List<HarshYear> get() = year.harsh.map { it.year }.filter { it in COURSE }.distinct()

    /** The aspects whose rise and decline stands on one of the bad six stages in the year of death. */
    val badStages: List<Force> get() = year.sectors.filter { !it.sector.good }.map { it.force }

    /**
     * The second examination's cuts (p. 57; the notes, p. 503: "iron meeting its enemy fire many times within the
     * pebbles"): over all the roots' fights, how often each element of the dead is cut by its enemy or met by itself,
     * and those found most often; none unless a deciding pebble turned black ("where the pebbles turned black").
     */
    val cuts: List<DeadCut> get() {
        if (Force.entries.none { deciding(it) == PebbleColour.BLACK }) return emptyList()
        val counts = pebbles.mapNotNull { p ->
            val own = year.forces[p.force]
            when (p.other?.let { Forces.kinship(own, it) }) {
                Kinship.ENEMY -> DeadCut.entries.first { it.own == own && !it.inner }
                Kinship.IDENTITY -> DeadCut.entries.first { it.own == own && it.inner }
                else -> null
            }
        }.groupingBy { it }.eachCount()
        val most = counts.values.maxOrNull() ?: return emptyList()
        return DeadCut.entries.filter { counts[it] == most }
    }

    /** The animals of the year, month, day and hour of death (p. 58), the hour where it is known. */
    val times: List<Pair<DeadTime, Animal>> get() = listOfNotNull(
        DeadTime.YEAR to year.present.animal, DeadTime.MONTH to month.animal, DeadTime.DAY to date.animal,
        year.hour?.let { DeadTime.HOUR to it.sign.animal },
    )

    /** The day's animal of death, where the third examination names it (p. 63): the ox, tiger, snake, horse, sheep and monkey; the other days hold no good or bad. */
    val demonDay: Animal? get() = date.animal.takeIf { it in DEMON_DAYS }

    companion object {
        private val DEMON_DAYS = setOf(Animal.OX, Animal.TIGER, Animal.SNAKE, Animal.HORSE, Animal.SHEEP, Animal.MONKEY)

        /**
         * The rebirth by the double hour of death (p. 71): the hare and tiger, gods; dragon and ox, asuras; snake and
         * mouse, humans; horse and pig, animals; sheep and dog, hungry ghosts; monkey and bird, hell.
         */
        fun rebirthByHour(animal: Animal): DeadRebirth = when (animal) {
            Animal.RABBIT, Animal.TIGER -> DeadRebirth.GOD
            Animal.DRAGON, Animal.OX -> DeadRebirth.ASURA
            Animal.SNAKE, Animal.MOUSE -> DeadRebirth.HUMAN
            Animal.HORSE, Animal.PIG -> DeadRebirth.ANIMAL
            Animal.SHEEP, Animal.DOG -> DeadRebirth.HUNGRY_GHOST
            Animal.MONKEY, Animal.BIRD -> DeadRebirth.HELL
        }

        /** The month in which the demon cut the la of one who died in [animal]'s time: its seventh (p. 58). */
        fun cutMonth(animal: Animal): Animal = Animal.entries[(animal.ordinal + 6) % 12]

        private val SMALL_OBSTACLES = setOf(SmeBaObstacle.HOUSE, SmeBaObstacle.BED, SmeBaObstacle.LAND, SmeBaObstacle.ROYAL_GATE)
        private val COURSE = setOf(
            HarshYear.OWN_YEAR, HarshYear.SEVENTH, HarshYear.TRIAD, HarshYear.FOURTH_UP, HarshYear.FOURTH_DOWN, HarshYear.LOG_MEN_ENEMY,
            HarshYear.COMBINED_NINE, HarshYear.NINE_TOMB, HarshYear.TRIGRAM_NINE, HarshYear.MEWA_NINE, HarshYear.TOMB_SIGN,
        )

        /**
         * The first examination of one of [gender] born in the Tibetan year [birthYear], who died on the Tibetan day
         * [death], in the hour [hour] where it is known; null if the death is not after the birth.
         */
        fun of(birthYear: Int, gender: Gender, death: TibetanDay, hour: HourSign?): DeadReckoning? {
            if (YearOfLife.age(birthYear, death.year) < 1) return null
            val signs = Forces.signs(death)
            val year = YearReckoning(YearOfLife.yearSign(birthYear), birthYear, signs.year, death.year, gender, hour)
            return DeadReckoning(year, signs.month, signs.date, death.weekday, death.mansion)
        }
    }
}
