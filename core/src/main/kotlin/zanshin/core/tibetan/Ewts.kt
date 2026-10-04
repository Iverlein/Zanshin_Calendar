/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

/**
 * One Tibetan syllable in Extended Wylie letters: an optional prefix, a stack
 * (optional superscript, the root, subscripts), one vowel and up to two
 * suffixes. The vowel is "a" for the inherent vowel.
 */
data class Syllable(
    val prefix: String?,
    val superscript: String?,
    val root: String?,
    val subscripts: List<String>,
    val vowel: String,
    val suffixes: List<String>,
) {
    /** The stack from top to bottom, as written. */
    val stack: List<String> get() = listOfNotNull(superscript, root) + subscripts
}

/**
 * Extended Wylie (EWTS) to Tibetan script, for the plain Tibetan syllables
 * the almanac uses. Words separated by spaces become syllables separated by a
 * tsheg. A dot forces a prefix reading ("g.ya"). A Sanskrit loan is written
 * with EWTS's explicit stacking: "+" joins the letters of one stack, and the
 * word is written without a tsheg ("biSh+Ti", བིཥྚི). Other Sanskrit
 * transliteration is not handled; [toTibetan] returns null for anything it
 * cannot read, so no term is shown in a script guessed from a spelling the
 * converter does not know.
 */
object Ewts {
    private val CONSONANTS: Map<String, Int> = linkedMapOf(
        "tsh" to 0x0F5A,
        "kh" to 0x0F41, "gh" to 0x0F43, "ng" to 0x0F44, "ch" to 0x0F46, "ny" to 0x0F49,
        "Th" to 0x0F4B, "Dh" to 0x0F4D, "th" to 0x0F50, "dh" to 0x0F52, "ph" to 0x0F55,
        "bh" to 0x0F57, "ts" to 0x0F59, "dz" to 0x0F5B, "zh" to 0x0F5E, "sh" to 0x0F64, "Sh" to 0x0F65,
        "k" to 0x0F40, "g" to 0x0F42, "c" to 0x0F45, "j" to 0x0F47, "T" to 0x0F4A, "D" to 0x0F4C,
        "N" to 0x0F4E, "t" to 0x0F4F, "d" to 0x0F51, "n" to 0x0F53, "p" to 0x0F54, "b" to 0x0F56,
        "m" to 0x0F58, "w" to 0x0F5D, "z" to 0x0F5F, "'" to 0x0F60, "y" to 0x0F61, "r" to 0x0F62,
        "l" to 0x0F63, "s" to 0x0F66, "h" to 0x0F67,
    )

    /** Vowel signs; the inherent a has none. */
    private val VOWELS: Map<String, String> = linkedMapOf(
        "-I" to "ཱྀ", "-i" to "ྀ", "ai" to "ཻ", "au" to "ཽ",
        "A" to "ཱ", "I" to "ཱི", "U" to "ཱུ",
        "i" to "ི", "u" to "ུ", "e" to "ེ", "o" to "ོ", "a" to "",
    )

    private val PREFIXES = setOf("g", "d", "b", "m", "'")
    private val SUBSCRIPTS = setOf("y", "r", "l", "w")
    private const val TSHEG = '་'
    private const val SUBJOINED_OFFSET = 0x50

    /** The syllables of [wylie], or null when it is not plain EWTS. */
    fun parse(wylie: String): List<Syllable>? = runCatching {
        wylie.trim().replace('’', '\'').split(Regex("\\s+")).map { syllable(it) }
    }.getOrNull()

    fun toTibetan(wylie: String): String? = runCatching {
        wylie.trim().replace('’', '\'').split(Regex("\\s+")).joinToString(TSHEG.toString()) {
            if ('+' in it) stacked(it) else render(syllable(it))
        }
    }.getOrNull()

    /** A word written with explicit stacks ("biSh+Ti"): each stack, then its vowel, with no tsheg between. */
    private fun stacked(word: String): String {
        val out = StringBuilder()
        var i = 0
        while (i < word.length) {
            var first = true
            do {
                if (!first) i++ // the "+"
                val c = CONSONANTS.keys.firstOrNull { word.startsWith(it, i) } ?: error("not EWTS: $word")
                out.appendCodePoint(CONSONANTS.getValue(c) + if (first) 0 else SUBJOINED_OFFSET)
                i += c.length
                first = false
            } while (i < word.length && word[i] == '+')
            val v = VOWELS.keys.firstOrNull { word.startsWith(it, i) } ?: error("no vowel in $word")
            out.append(VOWELS.getValue(v))
            i += v.length
        }
        return out.toString()
    }

    private fun render(s: Syllable): String {
        val out = StringBuilder()
        s.prefix?.let { out.appendCodePoint(CONSONANTS.getValue(it)) }
        val stack = s.stack
        if (stack.isEmpty()) {
            out.append('ཨ') // a vowel alone sits on the a-chen
        } else {
            out.appendCodePoint(CONSONANTS.getValue(stack[0]))
            stack.drop(1).forEach { out.appendCodePoint(CONSONANTS.getValue(it) + SUBJOINED_OFFSET) }
        }
        out.append(VOWELS.getValue(s.vowel))
        s.suffixes.forEach { out.appendCodePoint(CONSONANTS.getValue(it)) }
        return out.toString()
    }

    private fun syllable(s: String): Syllable {
        val before = mutableListOf<String>()
        var forcedPrefix = false
        var vowel: String? = null
        val after = mutableListOf<String>()
        var i = 0
        while (i < s.length) {
            if (s[i] == '.') {
                require(vowel == null && before.size == 1) { "misplaced dot in $s" }
                forcedPrefix = true
                i++
                continue
            }
            val v = if (vowel == null) VOWELS.keys.firstOrNull { s.startsWith(it, i) } else null
            if (v != null) {
                vowel = v
                i += v.length
                continue
            }
            val c = CONSONANTS.keys.firstOrNull { s.startsWith(it, i) } ?: error("not EWTS: $s")
            if (vowel == null) before += c else after += c
            i += c.length
        }
        require(vowel != null) { "no vowel in $s" }
        require(after.size <= 2) { "too many suffixes in $s" }

        val prefix = if (isPrefix(before, forcedPrefix)) before[0] else null
        val stack = if (prefix != null) before.drop(1) else before
        val superscript = stack.getOrNull(0)?.takeIf { stack.size >= 2 && isSuperscript(stack[0], stack[1]) }
        val rest = if (superscript != null) stack.drop(1) else stack
        return Syllable(prefix, superscript, rest.firstOrNull(), rest.drop(1), vowel, after)
    }

    /**
     * The first letter is a prefix when it can be one and the rest does not
     * read as subscripts under it: "dbang" has the prefix d, "bra" and "dwa"
     * are stacks, "brtan" is b before the stack rt.
     */
    private fun isPrefix(letters: List<String>, forced: Boolean): Boolean = when {
        forced -> true
        letters.size < 2 || letters[0] !in PREFIXES -> false
        else -> !letters.drop(1).all { it in SUBSCRIPTS }
    }

    /** r always stands above what follows it; l and s do unless a subscript follows ("sla", "sra"). */
    private fun isSuperscript(top: String, next: String): Boolean =
        top == "r" || (top in setOf("l", "s") && next !in SUBSCRIPTS)
}
