/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/** The White Beryl's personal mansions, p. 330, as settled in docs/sources/personal-mansions.md. */
class PersonalMansionTest {
    private fun roles(animal: Animal) = Mansion.entries.associateWith { personalMansions(animal, it) }.filterValues { it.isNotEmpty() }

    @Test
    fun `each animal has its six mansions`() {
        for (a in Animal.entries) assertEquals(PersonalMansion.entries, roles(a).values.flatten().sortedBy { it.ordinal }, "$a")
        // The verse's first line, Mouse to Pig: chu stod, lha mtshams, mgo, gre, smin, me bzhi, lha mtshams, rgyal, rgyal, nag, skag, bra nye.
        val bla = listOf(
            Mansion.PURVASHADHA, Mansion.ANURADHA, Mansion.MRIGASHIRAS, Mansion.PURVAPHALGUNI, Mansion.KRITTIKA, Mansion.HASTA,
            Mansion.ANURADHA, Mansion.PUSHYA, Mansion.PUSHYA, Mansion.CITRA, Mansion.ASHLESHA, Mansion.BHARANI,
        )
        assertEquals(bla, Animal.entries.map { a -> roles(a).entries.single { PersonalMansion.BLA in it.value }.key })
    }

    @Test
    fun `the places the three texts settle`() {
        // The editors' 23 for the Tiger's skeg and the Horse's gshed is 13 and 26 in NM and SY.
        assertEquals(listOf(PersonalMansion.SKEG), personalMansions(Animal.TIGER, Mansion.CITRA))
        assertEquals(listOf(PersonalMansion.GSHED), personalMansions(Animal.HORSE, Mansion.REVATI))
        // The Snake's dbang and gshed skar are both lag.
        assertEquals(listOf(PersonalMansion.DBANG, PersonalMansion.GSHED), personalMansions(Animal.SNAKE, Mansion.ARDRA))
    }
}
