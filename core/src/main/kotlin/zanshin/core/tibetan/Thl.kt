/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

/**
 * THL Simplified Phonetic Transcription of Standard Tibetan (David Germano and
 * Nicolas Tournadre, THL, 2003): how a Tibetan word is said, from its Wylie.
 * Rule numbers refer to the section "Special Rules" of that document; the
 * words it lists as exceptions are taken from its "List of Exceptions".
 *
 * Covered: the general principle, rules 1–13, the exceptions and the word
 * boundaries (up to three syllables make one word, four make two). Not
 * covered: vowels joined by an 'a-chung (rules 14–16), which the almanac's
 * terms do not use; such input returns null.
 */
object Thl {
    private val EXCEPTIONS = mapOf(
        "skyabs 'gro" to "kyamdro", "dga' ldan" to "ganden", "rgyal rtse" to "gyantsé", "chab mdo" to "chamdo",
        "rta mgrin" to "tamdrin", "rten 'brel" to "temdrel", "rdo rje" to "dorjé", "dpal ldan" to "penden",
        "bla brang" to "labrang", "'bras ljongs" to "drenjong", "'bri ru" to "biru", "sbra nag zhol" to "banakzhöl",
        "me mda'" to "menda", "lam 'bras" to "lamdré", "lha rje" to "lharjé", "lha bris pa" to "lhapripa",
        "a mdo" to "amdo", "u rgyan" to "urgyen", "o rgyan" to "orgyen",
    )

    /** Root letters as THL writes them (general principle, rules 1 and 2; Sanskrit retroflexes lose their dot). */
    private val ROOT = mapOf(
        "c" to "ch", "th" to "t", "ph" to "p", "tsh" to "ts", "T" to "t", "Th" to "t", "D" to "d", "N" to "n", "Sh" to "sh",
        "gh" to "g", "dh" to "d", "bh" to "b",
    )

    /** Suffixes that are pronounced, as pronounced (general principle, rule 5). */
    private val SOUNDED_SUFFIX = mapOf("g" to "k", "ng" to "ng", "n" to "n", "b" to "p", "m" to "m", "r" to "r", "l" to "l")

    /** Phonetics of [wylie], one word per THL's word-boundary rule, or null when it cannot be read. */
    fun toPhonetic(wylie: String): String? {
        val normal = wylie.trim().replace('’', '\'').split(Regex("\\s+")).joinToString(" ")
        EXCEPTIONS[normal]?.let { return it }
        val syllables = Ewts.parse(normal) ?: return null
        val words = if (syllables.size == 4) listOf(syllables.take(2), syllables.drop(2)) else listOf(syllables)
        return words.joinToString(" ") { word(it) }
    }

    private fun word(syllables: List<Syllable>): String {
        val out = StringBuilder()
        syllables.forEachIndexed { i, s ->
            val last = i == syllables.lastIndex
            if (i > 0 && s.prefix == "'") {
                // Rule 13: nasalisation before a syllable with the prefix 'a-chung.
                elideSuffix(out, syllables[i - 1])
                val labial = s.root in setOf("ph", "b") && s.subscripts.none { it == "y" || it == "r" }
                out.append(if (labial) "m" else "n")
            }
            out.append(syllable(s, last))
        }
        return out.toString()
    }

    /** Drops the sounded suffix just written, so the nasal of rule 13 replaces it. */
    private fun elideSuffix(out: StringBuilder, previous: Syllable) {
        val sounded = previous.suffixes.firstOrNull()?.let { SOUNDED_SUFFIX[it] } ?: return
        if (out.endsWith(sounded)) out.setLength(out.length - sounded.length)
    }

    private fun syllable(s: Syllable, last: Boolean): String {
        // Rule 6: ba and bo close a word as wa and wo.
        if (last && s.prefix == null && s.superscript == null && s.root == "b" && s.subscripts.isEmpty() &&
            s.vowel in setOf("a", "o") && (s.suffixes.isEmpty() || s.suffixes == listOf("r"))
        ) {
            return "w" + s.vowel + if (s.suffixes.isNotEmpty()) "r" else ""
        }
        val first = s.suffixes.firstOrNull()
        val modifying = first in setOf("d", "n", "l", "s")
        val sounded = first?.let { SOUNDED_SUFFIX[it] } ?: ""
        val baseVowel = when (s.vowel) {
            "A" -> "a"
            "I", "-i", "-I" -> "i"
            "U" -> "u"
            else -> s.vowel
        }
        var vowel = when {
            modifying && baseVowel == "o" -> "ö" // rule 3
            modifying && baseVowel == "u" -> "ü"
            modifying && baseVowel == "a" -> "e" // rule 4
            else -> baseVowel
        }
        // Rule 4: an e that is the last sound of the word takes the accent.
        if (last && vowel == "e" && sounded.isEmpty()) vowel = "é"
        return onset(s) + vowel + sounded
    }

    private fun onset(s: Syllable): String {
        val root = s.root ?: return ""
        val subs = s.subscripts.filter { it != "w" } // the wa-zur is silent
        if (root == "'") return ""
        // Rule 12: prefix d before the root b.
        if (s.prefix == "d" && root == "b" && s.superscript == null) {
            return when {
                subs.isEmpty() -> "w"
                subs == listOf("y") -> "y"
                subs == listOf("r") -> "r"
                else -> "w"
            }
        }
        // Rule 11: l over h.
        if (s.superscript == "l" && root == "h") return "lh"
        return when {
            "y" in subs && root in setOf("p", "ph") -> "ch" // rule 7
            "y" in subs && root == "b" -> "j"
            "y" in subs && root == "m" -> "ny" // rule 8
            "r" in subs && root in setOf("k", "p", "t", "kh", "ph", "th") -> "tr" // rule 9
            "r" in subs && root in setOf("g", "b", "d") -> "dr"
            "l" in subs && root == "z" -> "d" // rule 10
            "l" in subs -> "l"
            else -> (ROOT[root] ?: root) + if ("y" in subs) "y" else ""
        }
    }
}
