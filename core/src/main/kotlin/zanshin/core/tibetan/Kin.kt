/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import zanshin.core.texts.gloss

/*
 * The year of age's readings that need another person's signs (ROADMAP P2): the natal trigram, which is the
 * mother's (WB vol. 1, ch. 22, pp. 261 and 286–288; the Moonbeams, f. 9a), and the obstacles of chapter 24 that
 * set the person's signs of the year against a spouse's, a parent's or a child's (pp. 388, 390, 409).
 * docs/sources/year-of-life.md, RD13 and RD14, quotes each rule.
 */

/** How a relative stands to the person reckoned, as far as the obstacles ask: [OTHER] is anyone else of the household, kin or not. */
enum class KinRole { MOTHER, FATHER, SPOUSE, CHILD, OTHER }

/**
 * A relative as the reckoning sees them: their [role], the Tibetan year of their birth and of their death where
 * known, their gender and whether they live in the [household]. [living] is false for one who has died, whether or
 * not the year is known.
 */
data class Kin(
    val role: KinRole,
    val birthYear: Int? = null,
    val gender: Gender? = null,
    val living: Boolean = true,
    val deathYear: Int? = null,
    /** Whether they live in the person's household. */
    val household: Boolean = false,
    /** Who they are, for the screen to name them; the reckoning does not read it. */
    val tag: Int = 0,
) {
    val birth: Sign? get() = birthYear?.let(YearOfLife::yearSign)

    /** Their trigram in the Tibetan year [year], while they live and their years and gender are known. */
    fun trigram(year: Int): Trigram? = ifAlive(year) { age, g -> YearOfLife.currentTrigram(g, age) }

    /** Their progressed sign in [year], on the same terms. */
    fun logMen(year: Int): Sign? = ifAlive(year) { age, g -> YearOfLife.logMen(birth!!.element, g, age) }

    /** The mewa of their year of age in [year], which needs no gender. */
    fun mewa(year: Int): Int? {
        val born = birthYear ?: return null
        val age = YearOfLife.age(born, year)
        if (!living || age < 1) return null
        return YearOfLife.currentSmeBa(YearOfLife.yearSmeBa(born), birth!!.animal.gender, age)
    }

    private fun <T> ifAlive(year: Int, f: (Int, Gender) -> T): T? {
        val born = birthYear ?: return null
        val g = gender ?: return null
        val age = YearOfLife.age(born, year)
        return if (living && age >= 1) f(age, g) else null
    }
}

/** The obstacles of chapter 24 that need another person's signs, in the order WB gives them. */
enum class KinObstacle(val wylie: String?) {
    /** The husband's progressed sign on the wife's birth year: "gone into the trap", bad for the wife (p. 388). */
    LOG_MEN_ON_WIFE(null),
    /** The wife's progressed sign on the husband's birth year: "wearing the demon's hat", bad for the husband. */
    LOG_MEN_ON_HUSBAND(null),
    /** The son's progressed sign on the father's birth year: "leading the ghost", bad for the father. */
    LOG_MEN_ON_FATHER(null),
    /** The father's progressed sign on the son's birth year: "the father holding the son's corpse", bad for the son. */
    LOG_MEN_ON_SON(null),
    /** The progressed sign on the year in which the father or the mother died: "gone into the existence of the dead". */
    LOG_MEN_ON_DEATH(null),
    /** Husband and wife on one trigram of the year (*bza' shug*, p. 390). */
    SPOUSES("bza' shug"),
    /** Others of one house on one trigram: no spouses' obstacle, but the fault of the bridge's end (*zam thug*, the notes, vol. 2, p. 492). */
    BRIDGE_END("zam thug"),
    /** The trigram of the year come onto one's natal trigram: the secret obstacle, "the thief" (*gsang keg*, p. 390). */
    SECRET("gsang keg"),
    /** Both spouses' mewa of the year on the two-black (p. 409). */
    SPOUSES_TWO_BLACK(null);

    val english: String get() = gloss(this)
}

/** An obstacle that holds, with the relative it comes with. */
data class KinHarsh(val obstacle: KinObstacle, val kin: Kin)

object KinReckoning {
    /**
     * The natal trigram (*skyes spar*) of a child born in the Tibetan year [birthYear] to a mother born in
     * [motherBirthYear]: "take the child's year from the mother's, add one, and turn the mother's trigram that
     * many times; the trigram the mother lands on is the trigram the child is born on" (the Moonbeams, f. 9a):
     * the mother's trigram of her year of age in the year of the birth, a woman's, from kham towards khen
     * (WB vol. 1, p. 257); "the trigram one is born on is the mother's luck" (the notes, vol. 2, p. 476).
     * Null for a mother not born before the child.
     */
    fun natalTrigram(motherBirthYear: Int, birthYear: Int): Trigram? {
        val age = YearOfLife.age(motherBirthYear, birthYear)
        return if (age >= 1) YearOfLife.currentTrigram(Gender.FEMALE, age) else null
    }

    /** The person's natal trigram from the first mother in [kin] with a birth year. */
    fun natalTrigram(r: YearReckoning, kin: List<Kin>): Trigram? =
        kin.firstOrNull { it.role == KinRole.MOTHER && it.birthYear != null }?.let { natalTrigram(it.birthYear!!, r.birthYear) }

    /** The obstacles that hold in the year of [r] for the person and their relatives [kin], in WB's order. */
    fun obstacles(r: YearReckoning, kin: List<Kin>): List<KinHarsh> = buildList {
        val year = r.year
        val male = r.gender == Gender.MALE
        val female = r.gender == Gender.FEMALE
        val spouses = kin.filter { it.role == KinRole.SPOUSE && it.living }
        // The progressed sign on another's year, from both sides (p. 388).
        fun onYear(sign: Sign?, of: Sign?) = sign != null && of != null && sign == of
        for (s in spouses) {
            val husbandOnWife = if (male) onYear(r.logMen, s.birth) else female && s.gender == Gender.MALE && onYear(s.logMen(year), r.birth)
            val wifeOnHusband = if (female) onYear(r.logMen, s.birth) else male && s.gender == Gender.FEMALE && onYear(s.logMen(year), r.birth)
            if (husbandOnWife) add(KinHarsh(KinObstacle.LOG_MEN_ON_WIFE, s))
            if (wifeOnHusband) add(KinHarsh(KinObstacle.LOG_MEN_ON_HUSBAND, s))
        }
        if (male) {
            kin.filter { it.role == KinRole.FATHER && it.living }.forEach { f ->
                if (onYear(r.logMen, f.birth)) add(KinHarsh(KinObstacle.LOG_MEN_ON_FATHER, f))
                if (onYear(f.logMen(year), r.birth)) add(KinHarsh(KinObstacle.LOG_MEN_ON_SON, f))
            }
            kin.filter { it.role == KinRole.CHILD && it.living && it.gender == Gender.MALE }.forEach { son ->
                if (onYear(son.logMen(year), r.birth)) add(KinHarsh(KinObstacle.LOG_MEN_ON_FATHER, son))
                if (onYear(r.logMen, son.birth)) add(KinHarsh(KinObstacle.LOG_MEN_ON_SON, son))
            }
        }
        kin.filter { (it.role == KinRole.FATHER || it.role == KinRole.MOTHER) && !it.living && it.deathYear != null }.forEach { p ->
            if (onYear(r.logMen, YearOfLife.yearSign(p.deathYear!!))) add(KinHarsh(KinObstacle.LOG_MEN_ON_DEATH, p))
        }
        // One trigram of the year shared in the house (p. 390, the notes p. 492).
        r.trigram?.let { t ->
            spouses.filter { it.trigram(year) == t }.forEach { add(KinHarsh(KinObstacle.SPOUSES, it)) }
            kin.filter { it.household && it.role != KinRole.SPOUSE && it.trigram(year) == t }.forEach { add(KinHarsh(KinObstacle.BRIDGE_END, it)) }
        }
        // The secret obstacle: the trigram of the year on the natal trigram (p. 390).
        val mother = kin.firstOrNull { it.role == KinRole.MOTHER && it.birthYear != null }
        if (mother != null && r.trigram != null && r.trigram == natalTrigram(mother.birthYear!!, r.birthYear)) add(KinHarsh(KinObstacle.SECRET, mother))
        // Both spouses on the two-black (p. 409).
        if (r.currentSmeBa == 2) spouses.filter { it.mewa(year) == 2 }.forEach { add(KinHarsh(KinObstacle.SPOUSES_TWO_BLACK, it)) }
    }
}
