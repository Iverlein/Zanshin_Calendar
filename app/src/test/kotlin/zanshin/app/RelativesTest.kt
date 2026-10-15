/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import zanshin.core.tibetan.Gender
import java.time.LocalDate
import java.time.LocalTime

/** SPEC §10.5, ROADMAP P1: each saved person's own tree of relatives, its storage and its rules. */
class RelativesTest {
    private val mother = Relative(0, null, Relation.MOTHER, "Dolma", birth = Moment(1950), died = true, death = Moment.of(LocalDate.of(1999, 3, 4), LocalTime.of(6, 30)))
    private val father = Relative(0, null, Relation.FATHER, birth = Moment.of(LocalDate.of(1948, 11, 2)), household = true)

    /** A tree: mother and father of the person, the father's mother under him, her sister under her, and a servant. */
    private fun tree(): Relatives {
        var t = Relatives().put(mother).put(father)
        t = t.put(Relative(0, 2, Relation.MOTHER, birth = Moment(1920), died = true, death = Moment(1980)))
        t = t.put(Relative(0, 3, Relation.SIBLING, gender = Gender.FEMALE, died = true))
        return t.put(Relative(0, null, Relation.HOUSEHOLD, "Pema", Gender.MALE, Moment(1990), household = true))
    }

    @Test
    fun `every field survives the round trip, person by person`() {
        val people = listOf(
            Person("Kyoto test", LocalDate.of(1976, 6, 1), Gender.FEMALE, tree()),
            Person("Lhasa test", LocalDate.of(1990, 2, 27)),
            Person("Third", LocalDate.of(2001, 12, 31), relatives = Relatives().put(Relative(0, null, Relation.SPOUSE, "A\tB", Gender.MALE, Moment(1999)))),
        )
        val trees = Relatives.decode(Relatives.encode(people), people.size)
        assertEquals(people.map { it.relatives.list.map { r -> r.copy(name = People.clean(r.name)) } }, trees.map { it.list })
        assertEquals("A B", trees[2].list.single().name)
    }

    @Test
    fun `a list saved before relatives reads as people without them`() {
        assertEquals(listOf(Relatives(), Relatives()), Relatives.decode("", 2))
    }

    @Test
    fun `moments are a year, a date, or a date with its hour`() {
        for (m in listOf(Moment(1950), Moment.of(LocalDate.of(1948, 11, 2)), Moment.of(LocalDate.of(1999, 3, 4), LocalTime.of(6, 30)))) {
            assertEquals(m, Moment.decode(m.encode()))
        }
        assertEquals("1999-03-04T06:30", Moment.of(LocalDate.of(1999, 3, 4), LocalTime.of(6, 30, 59)).encode())
        assertNull(Moment.decode("19x0"))
        // A day before the new year is in the year before; a year alone is the one that begins in it.
        assertEquals(1969, Moment.of(LocalDate.of(1970, 1, 1)).tibetanYear)
        assertEquals(1970, Moment.of(LocalDate.of(1970, 6, 1)).tibetanYear)
        assertEquals(1970, Moment(1970).tibetanYear)
        assertThrows<IllegalArgumentException> { Moment(1950, LocalDate.of(1951, 1, 1)) }
        assertThrows<IllegalArgumentException> { Moment(1950, null, LocalTime.NOON) }
    }

    @Test
    fun `malformed lines are skipped, and a branch whose root is lost goes with it`() {
        val good = Relatives.encode(listOf(Person("", LocalDate.of(1976, 6, 1), relatives = tree())))
        val lines = good.lines()
        // Drop the father (id 2): his mother (3) and her sister (4) no longer reach the person.
        val text = (lines.filterNot { it.split('\t')[1] == "2" } + listOf("garbage", "0\tx\t-\tMOTHER\t\t\t\t\t", "5\t9\t-\tFATHER\t\t\t1950\t\t", "0\t9\t-\tUNCLE\t\t\t1950\t\t"))
            .joinToString("\n")
        val read = Relatives.decode(text, 1).single()
        assertEquals(listOf(1, 5), read.list.map { it.id })
    }

    @Test
    fun `the tree is walked from the person out, each under the one they are entered under`() {
        val walk = tree().walk().map { (r, depth) -> r.id to depth }
        assertEquals(listOf(1 to 0, 2 to 0, 3 to 1, 4 to 2, 5 to 0), walk)
    }

    @Test
    fun `deleting a relative deletes those entered under them`() {
        val t = tree()
        assertEquals(2, t.branch(2))
        assertEquals(listOf(1, 5), t.remove(2).list.map { it.id })
        assertEquals(listOf(1, 2, 3, 5), t.remove(4).list.map { it.id })
    }

    @Test
    fun `a mother and a father once each, the household under the person only`() {
        val t = tree()
        assertEquals(listOf(Relation.SPOUSE, Relation.CHILD, Relation.SIBLING, Relation.HOUSEHOLD), t.relationsFree(null))
        assertEquals(listOf(Relation.MOTHER, Relation.SPOUSE, Relation.CHILD, Relation.SIBLING, Relation.HOUSEHOLD), t.relationsFree(null, keeping = t.byId(1)))
        assertEquals(listOf(Relation.FATHER, Relation.SPOUSE, Relation.CHILD, Relation.SIBLING), t.relationsFree(2))
        assertThrows<IllegalArgumentException> { Relative(1, 2, Relation.HOUSEHOLD, household = true) }
    }

    @Test
    fun `a new relative gets a fresh id, a changed one keeps its place, and no more than the limit are kept`() {
        val t = tree()
        assertEquals(6, t.put(Relative(0, null, Relation.CHILD)).list.last().id)
        val renamed = t.put(t.byId(3)!!.copy(name = "Grandmother"))
        assertEquals(t.list.size, renamed.list.size)
        assertEquals("Grandmother", renamed.byId(3)!!.name)
        var full = Relatives()
        repeat(Relatives.MAX + 3) { full = full.put(Relative(0, null, Relation.CHILD, birth = Moment(2000))) }
        assertEquals(Relatives.MAX, full.list.size)
        assertTrue(full.full)
    }

    @Test
    fun `a relative is named from the person out, in each language's order`() {
        val t = tree()
        val english = { rel: Relation, g: Gender?, _: Boolean ->
            when (rel) {
                Relation.SIBLING -> if (g == Gender.FEMALE) "sister" else "sibling"
                else -> rel.name.lowercase()
            }
        }
        assertEquals("father", t.label(t.byId(2)!!, english) { a, b -> "$b’s $a" })
        assertEquals("father’s mother’s sister", t.label(t.byId(4)!!, english) { a, b -> "$b’s $a" })
        val russian = { rel: Relation, g: Gender?, of: Boolean ->
            when (rel) {
                Relation.MOTHER -> if (of) "матери" else "мать"
                Relation.FATHER -> if (of) "отца" else "отец"
                Relation.SIBLING -> if (g == Gender.FEMALE) (if (of) "сестры" else "сестра") else "брат или сестра"
                else -> rel.name
            }
        }
        assertEquals("сестра матери отца", t.label(t.byId(4)!!, russian) { a, b -> "$a $b" })
    }

    @Test
    fun `a relative needs a date the readings can use, and the relation gives a parent's gender`() {
        assertTrue(mother.dated)
        assertFalse(Relative(1, null, Relation.SIBLING, died = true).dated)
        assertEquals(Gender.FEMALE, mother.sex)
        assertEquals(Gender.MALE, father.copy(gender = Gender.FEMALE).sex)
        assertThrows<IllegalArgumentException> { Relative(1, null, Relation.CHILD, death = Moment(2000)) }
    }

    @Test
    fun `removing a person keeps each tree with its own person`() {
        val people = People(
            listOf(
                Person("A", LocalDate.of(1976, 6, 1), relatives = Relatives().put(mother)),
                Person("B", LocalDate.of(1990, 2, 27), relatives = Relatives().put(father)),
            ),
            1,
        ).remove(0)
        val trees = Relatives.decode(Relatives.encode(people.list), people.list.size)
        assertEquals(listOf(Relation.FATHER), trees.single().list.map { it.relation })
    }
}
