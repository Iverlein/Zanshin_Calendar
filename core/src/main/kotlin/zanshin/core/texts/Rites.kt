/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.texts

import java.util.Locale

/**
 * The rites a Together gathers, one by one and the most general first (ROADMAP R5, the owner 2026-10-31). A remedy
 * row is split into its rites where a comma, a semicolon or an "and" opens a new imperative, never inside brackets
 * or quotation marks. The rites are ranked by how often the remedy rows of the whole catalog name what they name:
 * a rite with Tibetan terms by its rarest term, counted in the rows that bracket it; a rite without one by the rows
 * that hold all its words. The rites the sources lay most often come first, the peculiar ones last, and rites of
 * equal count keep the order they were gathered in. A language without a splitter keeps each row as one rite.
 */
object Rites {
    /** The imperatives that open a rite in English, from the catalog's remedy rows. */
    private val EN_VERBS = setOf(
        "accomplish", "apply", "ask", "avert", "avoid", "bathe", "beat", "beware", "bind", "build", "burn", "bury", "call",
        "carry", "cast", "change", "chop", "cleanse", "clear", "close", "clothe", "cover", "crush", "cure", "cut", "deck",
        "dig", "do", "draw", "dress", "drive", "entrust", "expel", "face", "fasten", "free", "fulfil", "fumigate", "gather",
        "give", "go", "hang", "have", "heal", "hide", "hoist", "hold", "honour", "hurl", "invite", "keep", "kill", "knead",
        "knock", "lay", "leave", "let", "load", "make", "mend", "move", "offer", "open", "paint", "pass", "pay", "perform",
        "pile", "place", "plant", "pour", "practise", "praise", "press", "proclaim", "protect", "purify", "put", "quickly",
        "raise", "ransom", "read", "receive", "recite", "reconcile", "release", "rely", "repair", "repay", "restore",
        "reverse", "save", "say", "scatter", "screen", "seek", "send", "separate", "set", "show", "smear", "stamp", "strike",
        "strive", "summon", "suppress", "sweep", "take", "tame", "throw", "tie", "treat", "turn", "undo", "untie", "use",
        "wash", "wave", "wear", "welcome", "worship", "wrap", "write",
    )

    /** Russian words with a verb's ending that are not verbs, among the catalog's remedy rows. */
    private val RU_NOT_VERBS = setOf(
        "двадцать", "девяти", "девять", "дети", "дичь", "пять", "шесть", "семь", "восемь", "десять", "тринадцать",
        "смерть", "смерти", "сеть", "нить", "путь", "часть", "кость", "мать", "печать", "власть", "честь",
    )

    private fun isVerb(word: String, language: String): Boolean = when (language) {
        "en" -> word in EN_VERBS
        "ru" -> word == "пусть" || (
            word !in RU_NOT_VERBS && !word.endsWith("ости") &&
                Regex("[а-яё]+(те|тесь|ть|ться|ти|чь)").matches(word)
            )
        else -> false
    }

    /** [text]'s rites in [language]; the whole text where the language has no splitter. */
    fun split(text: String, language: String): List<String> {
        val and = when (language) {
            "en" -> "and"
            "ru" -> "и"
            else -> return listOf(text)
        }
        val seps = listOf(", $and ", "; $and ", ", ", "; ", " $and ")
        // Each rite as the range of [text] it spans, from its first word to the separator after it.
        val rites = mutableListOf<IntRange>()
        var start = 0
        var depth = 0
        var quoted = false
        var i = 0
        while (i < text.length) {
            when (text[i]) {
                '(', '«' -> depth++
                ')', '»' -> depth--
                '"' -> quoted = !quoted
            }
            if (depth == 0 && !quoted) {
                seps.firstOrNull { text.startsWith(it, i) }?.let { sep ->
                    val next = WORD_AT.find(text, i + sep.length)?.takeIf { it.range.first == i + sep.length }?.value
                    // Two bare verbs joined by "and" share what follows ("wash and purify", "call back and accomplish life").
                    val bare = sep == " $and " && text.substring(start, i).trim().split(' ').let { w ->
                        w.size == 1 || (w.size == 2 && w[1] in PARTICLES)
                    }
                    if (next != null && isVerb(next, language) && !bare) {
                        rites += start until i
                        start = i + sep.length
                        i = start - 1
                    }
                }
            }
            i++
        }
        rites += start until text.length
        // A row that opens with a circumstance ("with a white dog, …, press …") keeps it on its first rite.
        if (rites.size > 1 && !isVerb(firstWord(text.substring(rites[0])), language)) {
            rites[1] = rites[0].first..rites[1].last
            rites.removeAt(0)
        }
        // A rite that refers back ("dress it in old clothes", "send them away") stays with the one before it.
        val back = BACK.getValue(language)
        var r = 1
        while (r < rites.size) {
            if (back.containsMatchIn(text.substring(rites[r]))) {
                rites[r - 1] = rites[r - 1].first..rites[r].last
                rites.removeAt(r)
            } else {
                r++
            }
        }
        return rites.map { text.substring(it).trim() }.filter { it.isNotEmpty() }
    }

    private val BACK = mapOf(
        "en" to Regex("""(?<![\p{L}'])(it|them)(?![\p{L}'])"""),
        "ru" to Regex("""(?<![\p{L}])(его|её|ее|их|ему|ей|им|него|неё|них|ним|ними)(?![\p{L}])""", RegexOption.IGNORE_CASE),
    )

    private val WORD_AT = Regex("[\\p{L}']+")

    /** The particles that make a bare verb of two words ("call back"). */
    private val PARTICLES = setOf("back", "out", "up", "down", "away", "off")

    /** A rite's first word, a negation's verb after it ("не отнимайте"). */
    private fun firstWord(rite: String): String {
        val words = WORD_AT.findAll(rite).map { it.value }.take(2).toList()
        return if (words.firstOrNull() == "не" && words.size > 1) words[1] else words.firstOrNull().orEmpty()
    }

    private val TERM = Regex("""\([^,()]+, ([^()]+)\)""")
    private val WORD = Regex("""[\p{L}']{4,}""")

    private class Counts(val terms: Map<String, Int>, val words: List<Set<String>>)

    private val counts = mutableMapOf<Locale, Counts>()

    /** Every remedy row of the catalog in [locale]: its Tibetan terms counted, and each row's words. */
    private fun counts(locale: Locale): Counts = synchronized(counts) {
        counts.getOrPut(locale) {
            val texts = Catalog.entries("").keys.filter { it.endsWith(".remedy") }.sorted().mapNotNull { Catalog.textOrNull(it, locale) }
            Counts(
                texts.flatMap { t -> TERM.findAll(t).map { it.groupValues[1] }.toSet() }.groupingBy { it }.eachCount(),
                texts.map { t -> WORD.findAll(t.lowercase()).map { it.value.take(5) }.toSet() },
            )
        }
    }

    /** How many remedy rows of the catalog name what [rite] names. */
    fun generality(rite: String, locale: Locale = Catalog.locale ?: Locale.getDefault()): Int {
        val c = counts(locale)
        val terms = TERM.findAll(rite).map { it.groupValues[1] }.toList()
        if (terms.isNotEmpty()) return terms.minOf { c.terms[it] ?: 0 }
        val words = WORD.findAll(rite.lowercase()).map { it.value.take(5) }.toSet()
        return c.words.count { it.containsAll(words) }
    }

    /** The rites of the remedy rows [texts], each once, the most general first. */
    fun list(texts: List<String>, locale: Locale = Catalog.locale ?: Locale.getDefault()): List<String> =
        texts.flatMap { split(it, locale.language) }
            .distinctBy { it.lowercase(locale) }
            .sortedByDescending { generality(it, locale) }
}
