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
 * lies on the more.
 */

/** The roots of the dead's pebbles (the notes, vol. 2, p. 500), in the notes' order, the planet and mansion left out. */
enum class DeadRoot { DEATH_YEAR, LOG_MEN, TRIGRAM, SME_BA, SECTOR, MONTH, DATE, HOUR; val english: String get() = gloss(this) }

/** The whole of the first examination (pp. 56–57): the black pebbles many, the life spent; the white many, an untimely death. */
enum class DeadWhole { SPENT, UNTIMELY; val english: String get() = gloss(this) }

/** One root's pebbles for one aspect. */
data class DeadPebble(val root: DeadRoot, val force: Force, val pebbles: Pebbles)

/**
 * The dead's first examination: [year] the year of age reckoning of the dead at the year of death (its present year
 * the year of death, its hour the hour of death where it is known), [month] and [date] the signs of the month and the
 * lunar date of the death.
 */
data class DeadReckoning(val year: YearReckoning, val month: Sign, val date: Sign) {
    val pebbles: List<DeadPebble> = buildList {
        val roots = mapOf(
            BasicSign.PRESENT_YEAR to DeadRoot.DEATH_YEAR, BasicSign.LOG_MEN to DeadRoot.LOG_MEN, BasicSign.TRIGRAM to DeadRoot.TRIGRAM,
            BasicSign.SME_BA to DeadRoot.SME_BA, BasicSign.SECTOR to DeadRoot.SECTOR, BasicSign.HOUR to DeadRoot.HOUR,
        )
        year.pebbles.filter { it.basic != BasicSign.HOUR }.forEach { add(DeadPebble(roots.getValue(it.basic), it.force, it.pebbles)) }
        for ((root, sign) in listOf(DeadRoot.MONTH to month, DeadRoot.DATE to date)) Force.entries.forEach { f ->
            val own = year.forces[f]
            add(DeadPebble(root, f, Forces.pebbles(Forces.kinship(own, sign.forces[f]), own)))
        }
        year.pebbles.filter { it.basic == BasicSign.HOUR }.forEach { add(DeadPebble(DeadRoot.HOUR, it.force, it.pebbles)) }
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

    companion object {
        /**
         * The first examination of one of [gender] born in the Tibetan year [birthYear], who died on the Tibetan day
         * [death], in the hour [hour] where it is known; null if the death is not after the birth.
         */
        fun of(birthYear: Int, gender: Gender, death: TibetanDay, hour: HourSign?): DeadReckoning? {
            if (YearOfLife.age(birthYear, death.year) < 1) return null
            val signs = Forces.signs(death)
            val year = YearReckoning(YearOfLife.yearSign(birthYear), birthYear, signs.year, death.year, gender, hour)
            return DeadReckoning(year, signs.month, signs.date)
        }
    }
}
