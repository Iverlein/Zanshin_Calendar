/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.texts

/**
 * What `CatalogTest` measures of a reading's shape (SPEC §8.1, *The shape of a reading*; ROADMAP X2):
 * the paragraph's length, the app's and an editor's words, and whether a Tibetan word has one English
 * name and one bracket wherever it stands (D7).
 */
object ReadingShape {
    private val TIBETAN_BRACKET = Regex("\\([^()]*[\\u0F00-\\u0FFF][^()]*\\)")
    private val WORD = Regex("\\{\\d}|[\\p{L}\\p{N}][\\p{L}\\p{N}'’-]*")

    /** The words of a paragraph, a {0} counting as one; a Tibetan bracket names a word and adds none. */
    fun words(text: String): Int = WORD.findAll(TIBETAN_BRACKET.replace(text, "")).count()

    /**
     * Rule 8's and rule 9's words: the app's mechanics ("the lists", "the day in brief", "the works
     * below") and an editor's notes ("not identified", "word for word", "the print"). "Below" counts
     * where it points at the sheet, not where a verse has a dog bite below.
     */
    val APP_OR_EDITOR = mapOf(
        "" to Regex("(?i)\\bthe lists?\\b|\\bthe day in brief\\b|\\bnot identified\\b|\\bword for word\\b|\\bthe print\\b|\\b(?:works?|listed|named|shown) below\\b|\\bbelow (?:are|is)\\b"),
        "_ru" to Regex("(?i)день вкратце|списк|перечисленн\\p{L}* ниже|(?<!\\p{L})ниже —|не опознан|дословно|в печати|оттиск"),
    )

    /** One naming of a Tibetan word: where it stands, its Wylie, and the stems of the words before its bracket. */
    data class Naming(val where: String, val wylie: String, val words: List<String>) {
        val tail: String get() = words.last()
    }

    private val BRACKET = Regex("\\(([^()]*)\\)")
    private val PAIR = Regex("([\\u0F00-\\u0FFF]+), ([^\\u0F00-\\u0FFF]+)")
    private val STOP = Regex("[.:;,()!?—–«»\"“”…]")
    private val RUSSIAN_ENDING = Regex("^(.{3,}?)(ами|ями|ыми|ими|ого|его|ому|ему|ов|ев|ей|ам|ям|ах|ях|ой|ый|ий|ая|яя|ое|ее|ые|ие|ых|их|ую|юю|ым|им|ом|ем|а|я|ы|и|у|ю|е|о|ь|й)?$")

    /** A word reduced so that its number and case do not tell two namings apart. */
    fun stem(word: String, russian: Boolean): String = word.lowercase().replace('’', '\'').split('-').joinToString("-") { part ->
        val w = part.replace(Regex("'s?$"), "")
        if (russian) {
            val once = RUSSIAN_ENDING.matchEntire(w)?.groupValues?.get(1) ?: w
            once.removeSuffix("и").removeSuffix("ь").removeSuffix("й").ifEmpty { once }
        } else when {
            w.length > 4 && listOf("sses", "ches", "shes", "xes").any { w.endsWith(it) } -> w.dropLast(2)
            w.length > 4 && w.endsWith("ies") -> w.dropLast(3) + "y"
            w.length > 3 && w.endsWith("s") && !w.endsWith("ss") -> w.dropLast(1)
            else -> w
        }
    }

    /**
     * The namings in [text]: each bracket that names one word, with the words before it back to the
     * last stop. A bracket of several words names none of them alone and is left out.
     */
    fun namings(where: String, text: String, russian: Boolean): List<Naming> = BRACKET.findAll(text).mapNotNull { b ->
        val pair = PAIR.matchEntire(b.groupValues[1]) ?: return@mapNotNull null
        val words = WORD.findAll(STOP.split(text.substring(0, b.range.first)).last()).map { stem(it.value, russian) }.toList()
        if (words.isEmpty()) null else Naming(where, pair.groupValues[2].lowercase(), words)
    }.toList()

    /**
     * D7: one English name per Wylie, and one bracket per English name. A word whose namings end in
     * different words is named two ways; the namings that differ from the most frequent are the odd
     * ones (on a tie, the one the interface or a term gives, which [plain] tells, stands). A name is
     * the words its namings share at their end; two words with one name, or a naming of one word by
     * another word's longer name ("water tormas" for the torma where the water torma has its own
     * word), are bracketed two ways ([oneBracket], English only, since a Russian genitive puts the
     * head before the name). [exceptions] are the words the sources themselves name two ways.
     */
    fun odd(namings: List<Naming>, plain: (String) -> Boolean, exceptions: Set<String>, oneBracket: Boolean): List<Pair<Naming, String>> {
        val out = mutableListOf<Pair<Naming, String>>()
        val byWylie = namings.filter { it.wylie !in exceptions }.groupBy { it.wylie }
        val names = mutableMapOf<String, List<String>>()
        for ((wylie, all) in byWylie) {
            val tails = all.groupBy { it.tail }
            if (tails.size == 1) {
                names[wylie] = commonEnd(all.map { it.words })
                continue
            }
            val standing = tails.entries.sortedWith(
                compareByDescending<Map.Entry<String, List<Naming>>> { e -> e.value.size }
                    .thenByDescending { e -> e.value.any { plain(it.where) } }.thenBy { it.key },
            ).first().key
            all.filter { it.tail != standing }.forEach { out += it to "«$wylie» named «${it.tail}», elsewhere «$standing»" }
        }
        if (!oneBracket) return out
        names.entries.filter { it.value.isNotEmpty() }.groupBy { it.value }.filter { it.value.size > 1 }.forEach { (name, same) ->
            val kept = same.maxWith(compareBy<Map.Entry<String, List<String>>> { byWylie.getValue(it.key).size }.thenByDescending { it.key }).key
            same.filter { it.key != kept }.flatMap { byWylie.getValue(it.key) }
                .forEach { out += it to "«${name.joinToString(" ")}» names «${it.wylie}» and «$kept»" }
        }
        for ((a, nameA) in names) for ((b, nameB) in names) {
            if (a == b || nameA.isEmpty() || nameB.size <= nameA.size || nameB.takeLast(nameA.size) != nameA) continue
            byWylie.getValue(a).filter { it.words.size >= nameB.size && it.words.takeLast(nameB.size) == nameB }
                .forEach { out += it to "«${nameB.joinToString(" ")}» is «$b», named here for «$a»" }
        }
        return out
    }

    /**
     * The name the namings share: their common last words, at most four, without the article or
     * preposition a phrase opens with ("the twelve links" names the links, not "the").
     */
    private fun commonEnd(all: List<List<String>>): List<String> {
        val n = all.minOf { it.size }
        val k = (1..n).takeWhile { i -> all.map { it[it.size - i] }.toSet().size == 1 }.size
        return all.first().takeLast(minOf(k, 4)).dropWhile { it in FUNCTION_WORDS }
    }

    private val FUNCTION_WORDS = setOf("the", "a", "an", "and", "or", "of", "in", "on", "at", "by", "to", "with", "for", "from", "its", "their", "his", "her", "is", "are", "as")
}
