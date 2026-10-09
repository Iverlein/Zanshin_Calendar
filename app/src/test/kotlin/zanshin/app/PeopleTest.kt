/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.Test
import zanshin.core.tibetan.Gender
import java.time.LocalDate

/** The saved people (SPEC §10.5): their storage, the limit of ten and which one stays chosen. */
class PeopleTest {
    private val kyoto = Person("Kyoto test", LocalDate.of(1976, 6, 1))
    private val lhasa = Person("Lhasa test", LocalDate.of(1990, 2, 27))
    private val third = Person("Third", LocalDate.of(2001, 12, 31))

    @Test
    fun `names and dates survive the round trip`() {
        val list = listOf(kyoto, lhasa, Person("", LocalDate.of(1900, 1, 1)), Person("Ана · 天 ཀ", LocalDate.of(2026, 10, 9)))
        assertEquals(list, People.decode(People.encode(list)))
    }

    @Test
    fun `the gender survives the round trip, and a line saved before it reads as not set`() {
        val list = listOf(kyoto.copy(gender = Gender.MALE), lhasa.copy(gender = Gender.FEMALE), third)
        assertEquals(list, People.decode(People.encode(list)))
        assertEquals(listOf(Person("Old", LocalDate.ofEpochDay(100))), People.decode("100\tOld"))
        assertEquals(listOf(Person("Odd", LocalDate.ofEpochDay(100))), People.decode("100\tOdd\tx"))
    }

    @Test
    fun `a name is kept on one line`() {
        val messy = Person("  A\tname\nwith breaks  ", kyoto.birth)
        assertEquals(listOf(Person("A name with breaks", kyoto.birth)), People.decode(People.encode(listOf(messy))))
        assertEquals(People.NAME_MAX, People.clean("x".repeat(100)).length)
    }

    @Test
    fun `malformed lines are skipped and no more than ten are read`() {
        val text = (listOf("garbage", "12\t", "x\tname") + (1..12).map { "$it\tP$it" }).joinToString("\n")
        val read = People.decode(text)
        assertEquals(People.MAX, read.size)
        assertEquals(Person("", LocalDate.ofEpochDay(12)), read[0])
        assertEquals("P1", read[1].name)
    }

    @Test
    fun `a new person is chosen, and the eleventh is refused`() {
        var people = People().add(kyoto).add(lhasa)
        assertEquals(1, people.active)
        assertEquals(lhasa, people.current)
        repeat(8) { people = people.add(third) }
        assertEquals(People.MAX, people.list.size)
        assertSame(people, people.add(kyoto))
    }

    @Test
    fun `removing keeps the same person chosen, or no one if it was them`() {
        val people = People(listOf(kyoto, lhasa, third), 2)
        assertEquals(third, people.remove(0).current)
        assertEquals(third, people.remove(1).current)
        assertNull(people.remove(2).active)
        assertEquals(People(listOf(lhasa, third), null), People(listOf(kyoto, lhasa, third), null).remove(0))
    }

    @Test
    fun `a stored choice out of range reads as no one`() {
        assertNull(People.of(listOf(kyoto), 3).active)
        assertEquals(0, People.of(listOf(kyoto), 0).active)
    }

    @Test
    fun `initials of one or two words`() {
        assertEquals("T", initials("tenzin"))
        assertEquals("TD", initials(" Tenzin  Dolma Sherpa "))
        assertEquals("", initials("   "))
        assertEquals("天", initials("天野"))
    }
}
