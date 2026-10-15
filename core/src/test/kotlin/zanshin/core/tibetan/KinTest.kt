/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

/** The natal trigram and the obstacles that need a relative (ROADMAP P2; docs/sources/year-of-life.md, RD13–RD14). */
class KinTest {
    /** Gyurme Dorje's chart 6.2 man: born in the fire dragon year 1976, here in 2026, his 51st year. */
    private fun man(year: Int = 2026) = YearReckoning(YearOfLife.yearSign(1976), 1976, YearOfLife.yearSign(year), year, Gender.MALE, null)

    private fun obstacles(r: YearReckoning, vararg kin: Kin) = KinReckoning.obstacles(r, kin.toList()).map { it.obstacle to it.kin.role }

    /**
     * The Moonbeams, f. 9a: the mother's year less the child's, plus one, is the mother's year of age at the birth, and
     * her trigram that year, a woman's from kham towards khen (WB vol. 1, p. 257), is the child's natal trigram.
     */
    @Test
    fun `the natal trigram is the mother's trigram in the year of the birth`() {
        // A woman's circuit: kham, khen, dwa, khon, li, zon, zin, gin, then kham again in her 9th year.
        val circuit = listOf(Trigram.KHAM, Trigram.KHEN, Trigram.DWA, Trigram.KHON, Trigram.LI, Trigram.ZON, Trigram.ZIN, Trigram.GIN)
        for (age in 1..60) assertEquals(circuit[(age - 1) % 8], KinReckoning.natalTrigram(1950, 1950 + age - 1), "mother's year $age")
        // A mother born in 1950 bears a child in 1976, her 27th year: dwa.
        assertEquals(Trigram.DWA, KinReckoning.natalTrigram(1950, 1976))
        // A mother in her 21st year: li.
        assertEquals(Trigram.LI, KinReckoning.natalTrigram(1955, 1975))
        assertNull(KinReckoning.natalTrigram(1980, 1976))
    }

    @Test
    fun `the secret obstacle falls when the year's trigram comes onto the natal one`() {
        val mother = Kin(KinRole.MOTHER, birthYear = 1950, gender = Gender.FEMALE)
        // The man's trigram runs li, khon, dwa …: dwa in his 3rd, 11th … 51st year, 2026; not in 2025 (khon).
        assertEquals(Trigram.DWA, man().trigram)
        assertEquals(listOf(KinObstacle.SECRET to KinRole.MOTHER), obstacles(man(), mother))
        assertEquals(emptyList<Any>(), obstacles(man(2025), mother))
        // The natal trigram stands whether or not the mother lives; only her rites differ.
        assertEquals(listOf(KinObstacle.SECRET to KinRole.MOTHER), obstacles(man(), mother.copy(living = false, deathYear = 2000)))
        assertEquals(Trigram.DWA, KinReckoning.natalTrigram(man(), listOf(mother)))
    }

    @Test
    fun `husband and wife on one trigram of the year, and others of the house`() {
        // A wife born in 1984 is in her 43rd year in 2026, on dwa as he is.
        val wife = Kin(KinRole.SPOUSE, birthYear = 1984, gender = Gender.FEMALE)
        assertEquals(Trigram.DWA, wife.trigram(2026))
        assertEquals(listOf(KinObstacle.SPOUSES to KinRole.SPOUSE), obstacles(man(), wife))
        // No trigram without the spouse's gender, and none for a spouse who has died.
        assertEquals(emptyList<Any>(), obstacles(man(), wife.copy(gender = null)))
        assertEquals(emptyList<Any>(), obstacles(man(), wife.copy(living = false)))
        // A brother of the same year in the house is on dwa too: the bridge's end; out of the house, nothing.
        val brother = Kin(KinRole.OTHER, birthYear = 1976, gender = Gender.MALE, household = true)
        assertEquals(listOf(KinObstacle.BRIDGE_END to KinRole.OTHER), obstacles(man(), brother))
        assertEquals(emptyList<Any>(), obstacles(man(), brother.copy(household = false)))
    }

    /**
     * WB vol. 1, p. 388. The man's progressed sign starts from the earth tiger (fire's son earth) and runs forward:
     * the iron mouse in his 23rd year (chart 6.2), the earth dragon in his 51st, 2026.
     */
    @Test
    fun `the progressed sign on another's birth year`() {
        assertEquals(YearOfLife.yearSign(2020), man(1998).logMen)
        assertEquals(YearOfLife.yearSign(1988), man().logMen)
        val wife = Kin(KinRole.SPOUSE, birthYear = 1988, gender = Gender.FEMALE)
        assertTrue(KinObstacle.LOG_MEN_ON_WIFE to KinRole.SPOUSE in obstacles(man(), wife))
        assertTrue(obstacles(man(), wife.copy(living = false)).isEmpty())
        // From her side the husband's progressed sign is on her year: the same obstacle.
        val her = YearReckoning(YearOfLife.yearSign(1988), 1988, YearOfLife.yearSign(2026), 2026, Gender.FEMALE, null)
        val husband = Kin(KinRole.SPOUSE, birthYear = 1976, gender = Gender.MALE)
        assertTrue(KinObstacle.LOG_MEN_ON_WIFE to KinRole.SPOUSE in obstacles(her, husband))
        assertTrue(obstacles(her, husband.copy(gender = null)).none { it.first == KinObstacle.LOG_MEN_ON_WIFE })
        // The son's progressed sign on the father's year: a father born in 1928, an earth dragon too.
        val father = Kin(KinRole.FATHER, birthYear = 1928, gender = Gender.MALE)
        assertTrue(KinObstacle.LOG_MEN_ON_FATHER to KinRole.FATHER in obstacles(man(), father))
        // The father's progressed sign on the son's year: a son born in 1988, seen from the father.
        val son = Kin(KinRole.CHILD, birthYear = 1988, gender = Gender.MALE)
        assertTrue(KinObstacle.LOG_MEN_ON_SON to KinRole.CHILD in obstacles(man(), son))
        assertTrue(obstacles(man(), son.copy(gender = Gender.FEMALE)).none { it.first == KinObstacle.LOG_MEN_ON_SON })
        // The year a parent died: a father dead in 1988.
        val dead = father.copy(birthYear = 1940, living = false, deathYear = 1988)
        assertEquals(listOf(KinObstacle.LOG_MEN_ON_DEATH to KinRole.FATHER), obstacles(man(), dead))
        // No progressed sign without the person's own gender.
        val noGender = YearReckoning(YearOfLife.yearSign(1976), 1976, YearOfLife.yearSign(2026), 2026, null, null)
        assertEquals(emptyList<Any>(), obstacles(noGender, wife, father, son, dead))
    }

    /** WB vol. 1, p. 409: both spouses' mewa of the year on the two-black. Table 2.11: a natal two-black is two-black again in the 10th year. */
    @Test
    fun `both spouses on the two-black`() {
        assertEquals(2, YearOfLife.yearSmeBa(1980))
        val him = YearReckoning(YearOfLife.yearSign(1980), 1980, YearOfLife.yearSign(1989), 1989, Gender.MALE, null)
        assertEquals(2, him.currentSmeBa)
        val wife = Kin(KinRole.SPOUSE, birthYear = 1980, gender = Gender.FEMALE)
        assertEquals(2, wife.mewa(1989))
        assertTrue(KinObstacle.SPOUSES_TWO_BLACK to KinRole.SPOUSE in obstacles(him, wife))
        // The mewa needs no gender; a year later neither is on the two-black.
        assertTrue(KinObstacle.SPOUSES_TWO_BLACK to KinRole.SPOUSE in obstacles(him, wife.copy(gender = null)))
        val later = YearReckoning(YearOfLife.yearSign(1980), 1980, YearOfLife.yearSign(1990), 1990, Gender.MALE, null)
        assertTrue(obstacles(later, wife).none { it.first == KinObstacle.SPOUSES_TWO_BLACK })
    }
}
