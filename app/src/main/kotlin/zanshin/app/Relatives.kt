/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app

import zanshin.core.tibetan.Gender
import zanshin.core.tibetan.TibetanCalendar
import java.time.LocalDate
import java.time.LocalTime

/**
 * How a relative stands to the one they are entered under (SPEC §10.5, ROADMAP P1): the saved person or another
 * relative, so that a tree grows from the person ("Father's mother"). [HOUSEHOLD] is someone of the household who is
 * not kin, entered under the person only.
 */
enum class Relation { MOTHER, FATHER, SPOUSE, CHILD, SIBLING, HOUSEHOLD }

/**
 * A birth or a death as far as it is known: a [year] alone, or a whole [date], with the [time] of day when that is
 * known too. A year alone counts as the Tibetan year that begins in it.
 */
data class Moment(val year: Int, val date: LocalDate? = null, val time: LocalTime? = null) {
    init {
        require(date == null || date.year == year) { "the date is in $year" }
        require(time == null || date != null) { "a time needs its date" }
    }

    /** The Tibetan year it falls in: the date's, or, for a year alone, the one that begins in it. */
    val tibetanYear: Int get() = date?.let { TibetanCalendar.of(it).year } ?: year

    fun encode(): String = when {
        time != null -> "${date}T${time.withSecond(0).withNano(0)}"
        date != null -> "$date"
        else -> "$year"
    }

    companion object {
        fun of(date: LocalDate, time: LocalTime? = null) = Moment(date.year, date, time)

        fun decode(text: String): Moment? = runCatching {
            val (day, clock) = text.split('T').let { it[0] to it.getOrNull(1) }
            if ('-' !in day) Moment(day.toInt()) else LocalDate.parse(day).let { Moment(it.year, it, clock?.let(LocalTime::parse)) }
        }.getOrNull()
    }
}

/**
 * One relative in a person's tree: [id] unique within the tree, [of] the relative they are entered under (null for the
 * person), how they stand to them, a [name] that may be blank, a [gender] (a mother's and a father's are theirs by the
 * relation), the [birth] and, for one who has died, [died] true and the [death] as far as it is known, and whether they
 * live in the person's [household]. No clan: only some families have one (the owner, 2026-10-15).
 */
data class Relative(
    val id: Int,
    val of: Int?,
    val relation: Relation,
    val name: String = "",
    val gender: Gender? = null,
    val birth: Moment? = null,
    val died: Boolean = false,
    val death: Moment? = null,
    val household: Boolean = false,
) {
    init {
        require(died || death == null) { "a death needs died" }
        require(relation != Relation.HOUSEHOLD || (of == null && household)) { "a household member is the person's, in the household" }
    }

    /** The gender the relation gives, or the one set. */
    val sex: Gender? get() = when (relation) {
        Relation.MOTHER -> Gender.FEMALE
        Relation.FATHER -> Gender.MALE
        else -> gender
    }

    /** What the readings can use: a birth year, or a year of death. */
    val dated: Boolean get() = birth != null || death != null
}

/** A person's tree of relatives, at most [MAX], each one reachable from the person. */
data class Relatives(val list: List<Relative> = emptyList()) {
    init {
        require(list.size <= MAX) { "at most $MAX relatives" }
        require(list.map { it.id }.toSet().size == list.size) { "ids are unique" }
    }

    val full: Boolean get() = list.size >= MAX

    fun byId(id: Int): Relative? = list.firstOrNull { it.id == id }

    /** Those entered directly under [of] (null: the person), in the order entered. */
    fun under(of: Int?): List<Relative> = list.filter { it.of == of }

    /** The tree in reading order, each with its depth: the person's own first, each followed by those under them. */
    fun walk(): List<Pair<Relative, Int>> = buildList {
        fun visit(of: Int?, depth: Int) {
            for (r in under(of).sortedBy { it.relation.ordinal }) {
                add(r to depth)
                visit(r.id, depth + 1)
            }
        }
        visit(null, 0)
    }

    /**
     * What [r] is to the person, as a chain from the person out: "father's mother's sister". [word] names a relation
     * for a gender, in the form of the one another is entered under when its last argument is true (the genitive, in
     * Russian); [join] puts a relation with the one it is of.
     */
    fun label(r: Relative, word: (Relation, Gender?, Boolean) -> String, join: (String, String) -> String): String {
        fun ofForm(x: Relative): String {
            val up = x.of?.let(::byId) ?: return word(x.relation, x.sex, true)
            return join(word(x.relation, x.sex, true), ofForm(up))
        }
        val up = r.of?.let(::byId) ?: return word(r.relation, r.sex, false)
        return join(word(r.relation, r.sex, false), ofForm(up))
    }

    /** How many are entered under [id], at any depth: those [remove] takes with them. */
    fun branch(id: Int): Int = list.size - remove(id).list.size - 1

    /** The relations still open under [of]: a mother and a father once each, a household member under the person only. */
    fun relationsFree(of: Int?, keeping: Relative? = null): List<Relation> = Relation.entries.filter { r ->
        when (r) {
            Relation.MOTHER, Relation.FATHER -> under(of).none { it.relation == r && it.id != keeping?.id }
            Relation.HOUSEHOLD -> of == null
            else -> true
        }
    }

    /** [relative] added with a fresh id, or changed in place when its id is in the tree. */
    fun put(relative: Relative): Relatives =
        if (byId(relative.id) != null) Relatives(list.map { if (it.id == relative.id) relative else it })
        else if (full) this
        else Relatives(list + relative.copy(id = (list.maxOfOrNull { it.id } ?: 0) + 1))

    /** The tree without [id] and everyone entered under them, at any depth. */
    fun remove(id: Int): Relatives {
        val gone = mutableSetOf(id)
        var grew = true
        while (grew) grew = gone.addAll(list.filter { it.of in gone }.map { it.id })
        return Relatives(list.filter { it.id !in gone })
    }

    companion object {
        const val MAX = 60

        /**
         * One line per relative, fields by tabs: the person's index, the id, the one entered under ("-" for the
         * person), the relation, the name, the gender ("m", "f" or empty), the birth ([Moment.encode] or empty), the
         * death (empty while living, "d" for a death not dated, "d" and a moment as "d1999"), and "h" when in the
         * household.
         */
        fun encode(people: List<Person>): String = people.withIndex().flatMap { (i, p) ->
            p.relatives.list.map { r ->
                listOf(
                    "$i", "${r.id}", r.of?.toString() ?: "-", r.relation.name, People.clean(r.name),
                    when (r.gender) { Gender.MALE -> "m"; Gender.FEMALE -> "f"; null -> "" },
                    r.birth?.encode().orEmpty(),
                    if (r.died) "d" + (r.death?.encode().orEmpty()) else "",
                    if (r.household) "h" else "",
                ).joinToString("\t")
            }
        }.joinToString("\n")

        /**
         * Reads [encode]'s lines into each person's tree, by index; a malformed line is skipped, and so is a relative
         * whose line of descent no longer reaches the person.
         */
        fun decode(text: String, count: Int): List<Relatives> {
            val read = text.lineSequence().mapNotNull { line ->
                val f = line.split('\t')
                if (f.size < 9) return@mapNotNull null
                val owner = f[0].toIntOrNull()?.takeIf { it in 0 until count } ?: return@mapNotNull null
                runCatching {
                    owner to Relative(
                        id = f[1].toInt(),
                        of = if (f[2] == "-") null else f[2].toInt(),
                        relation = Relation.valueOf(f[3]),
                        name = f[4],
                        gender = when (f[5]) { "m" -> Gender.MALE; "f" -> Gender.FEMALE; else -> null },
                        birth = f[6].takeIf { it.isNotEmpty() }?.let { Moment.decode(it) ?: error("birth") },
                        died = f[7].startsWith("d"),
                        death = f[7].drop(1).takeIf { it.isNotEmpty() }?.let { Moment.decode(it) ?: error("death") },
                        household = f[8] == "h",
                    )
                }.getOrNull()
            }.toList()
            return (0 until count).map { owner ->
                val mine = read.filter { it.first == owner }.map { it.second }.distinctBy { it.id }
                // Keep only what hangs from the person, so that a lost line takes its branch with it.
                val kept = mutableListOf<Relative>()
                var grew = true
                while (grew) {
                    val next = mine.filter { r -> r !in kept && (r.of == null || kept.any { it.id == r.of }) }
                    grew = next.isNotEmpty()
                    kept += next
                }
                // Cut in the order found from the person out, so the cut keeps whole branches; then in the order entered.
                val ids = kept.take(MAX).map { it.id }.toSet()
                Relatives(mine.filter { it.id in ids })
            }
        }
    }
}
