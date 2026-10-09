/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app

import zanshin.core.tibetan.Gender
import java.time.LocalDate

/**
 * One person whose personal days the pages read: a name, a birth date and,
 * for the progressions of the elemental divination, a gender, null while it
 * is not set (SPEC §10.5).
 */
data class Person(val name: String, val birth: LocalDate, val gender: Gender? = null)

/**
 * The saved people, at most [MAX], and which of them the pages read for:
 * [active] indexes [list], null when the pages read for no one. Kept on
 * the device only.
 */
data class People(val list: List<Person> = emptyList(), val active: Int? = null) {
    init {
        require(list.size <= MAX) { "at most $MAX people" }
        require(active == null || active in list.indices) { "no person $active" }
    }

    val current: Person? get() = active?.let(list::get)
    val full: Boolean get() = list.size >= MAX

    fun choose(index: Int?): People = copy(active = index)

    /** Adds [person] and chooses them. */
    fun add(person: Person): People = if (full) this else People(list + person, list.size)

    fun replace(index: Int, person: Person): People = copy(list = list.toMutableList().also { it[index] = person })

    /** Removes the person at [index]; if they were chosen, no one is. */
    fun remove(index: Int): People = People(
        list.filterIndexed { i, _ -> i != index },
        when {
            active == null || active == index -> null
            active > index -> active - 1
            else -> active
        },
    )

    companion object {
        const val MAX = 10
        const val NAME_MAX = 40

        fun clean(name: String): String = name.replace(Regex("[\\t\\r\\n]+"), " ").trim().take(NAME_MAX).trim()

        /**
         * One line per person: the birth date's epoch day, a tab, the name and,
         * when the gender is set, a tab and "m" or "f".
         */
        fun encode(list: List<Person>): String = list.joinToString("\n") { p ->
            "${p.birth.toEpochDay()}\t${clean(p.name)}" + when (p.gender) {
                Gender.MALE -> "\tm"
                Gender.FEMALE -> "\tf"
                null -> ""
            }
        }

        /**
         * Reads [encode]'s lines; a malformed line is skipped, and no more than
         * [MAX] are kept. A line saved before the gender was has no third field
         * and reads as not set.
         */
        fun decode(text: String): List<Person> = text.lineSequence().mapNotNull { line ->
            val fields = line.split('\t')
            if (fields.size < 2) return@mapNotNull null
            val day = fields[0].toLongOrNull() ?: return@mapNotNull null
            val gender = when (fields.getOrNull(2)) {
                "m" -> Gender.MALE
                "f" -> Gender.FEMALE
                else -> null
            }
            runCatching { Person(fields[1], LocalDate.ofEpochDay(day), gender) }.getOrNull()
        }.take(MAX).toList()

        /** [list] with [active] dropped if it is out of range. */
        fun of(list: List<Person>, active: Int?): People = People(list, active?.takeIf { it in list.indices })
    }
}

/** Up to two initials of a name, for the header's switcher; empty for a blank name. */
fun initials(name: String): String = name.trim().split(Regex("\\s+")).filter { it.isNotEmpty() }.take(2)
    .joinToString("") { word -> String(Character.toChars(word.codePointAt(0))).uppercase() }
