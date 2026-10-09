/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.texts

import zanshin.core.tibetan.CombinationDay
import zanshin.core.tibetan.ElementPair
import zanshin.core.tibetan.Ewts
import zanshin.core.tibetan.Force
import zanshin.core.tibetan.GreatCombination
import zanshin.core.tibetan.IndianElement
import zanshin.core.tibetan.Karana
import zanshin.core.tibetan.LunarDayClass
import zanshin.core.tibetan.Mansion
import zanshin.core.tibetan.OwnDay
import zanshin.core.tibetan.PersonalMansion
import zanshin.core.tibetan.TibetanCalendar
import zanshin.core.tibetan.Trigram
import zanshin.core.tibetan.Weekday
import zanshin.core.tibetan.Yoga
import zanshin.core.tibetan.ZodiacSign

/**
 * How a text names a Tibetan word (SPEC §8.1): its name, then its script and
 * Wylie in brackets, "the la, the life-spirit (བླ, bla)"; several in one
 * bracket stand apart by semicolons. Core's CatalogTest and the app's
 * TranslationsTest check the catalog, the interface and the store listing
 * with it.
 */
object TibetanNaming {
    private val TIBETAN = Regex("[\\u0F00-\\u0FFF]")
    private val BRACKET = Regex("\\(([^()]*)\\)")
    private val PAIR = Regex("([\\u0F00-\\u0FFF]+), ([^\\u0F00-\\u0FFF]+)")

    /** The Wylie of the engines' terms, which a text never gives without its script. */
    val engineWylie: Set<String> by lazy {
        (
            Weekday.entries.flatMap { listOf(it.wylie, "gza' ${it.wylie}") } + IndianElement.entries.map { it.wylie } +
                Mansion.entries.map { it.wylie } + Yoga.entries.flatMap { listOf(it.wylie, it.whiteBeryl) } +
                Karana.entries.flatMap { listOf(it.wylie, it.whiteBeryl) } + ElementPair.entries.map { it.wylie } +
                GreatCombination.entries.map { it.wylie } + CombinationDay.entries.map { it.wylie } +
                Trigram.entries.flatMap { listOf(it.wylie, it.goddess) } + LunarDayClass.entries.map { it.wylie } +
                PersonalMansion.entries.map { it.wylie } + OwnDay.entries.map { it.wylie } + Force.entries.map { it.wylie } +
                ZodiacSign.entries.map { it.wylie } + (1..12).map { TibetanCalendar.monthNames(it).wylie }
            ).toSet()
    }

    /** The brackets of [text] that hold Tibetan script. */
    private fun tibetanBrackets(text: String) = BRACKET.findAll(text).filter { TIBETAN.containsMatchIn(it.value) }.toList()

    /** The (script, Wylie) pairs [text] names. */
    fun pairs(text: String): List<Pair<String, String>> = tibetanBrackets(text).flatMap { b ->
        b.groupValues[1].split("; ").mapNotNull { PAIR.matchEntire(it)?.let { m -> m.groupValues[1] to m.groupValues[2] } }
    }

    /**
     * What breaks §8.1 in [text]: a bracket that is not "script, Wylie", a script
     * that is not the one the Wylie converts to, a bracket without a name before
     * it, script outside a bracket, and any of the [known] Wylie standing alone.
     * Words that are also English, such as the fire element's *me*, are
     * left to the reader: [known] should not hold them.
     */
    fun problems(text: String, known: Set<String>): List<String> {
        val out = mutableListOf<String>()
        val brackets = tibetanBrackets(text)
        for (b in brackets) {
            for (part in b.groupValues[1].split("; ")) {
                val m = PAIR.matchEntire(part)
                if (m == null) {
                    out += "«$part» is not «Tibetan, Wylie»"
                    continue
                }
                val (script, wylie) = m.destructured
                // A Sanskrit loan is printed without a tsheg inside it (བཻཌཱུར, bai DUr): tshegs are not compared.
                // BDRC capitalises a title's first letter, which is no retroflex ("Dpal ldan").
                val converted = Ewts.toTibetan(wylie.replaceFirstChar { it.lowercase() })
                if (converted != null && converted.replace("་", "") != script.replace("་", "")) out += "«$part»: the Wylie writes $converted"
            }
            val before = text.substring(0, b.range.first)
            if (!before.endsWith(' ') || before.isBlank() || TIBETAN.matches(before.trimEnd().last().toString())) out += "no name before ${b.value}"
        }
        // A name spelled as its Wylie stands before its own bracket ("zor (ཟོར, zor)"): the bracket names it.
        val outside = brackets.fold(text) { t, b ->
            val named = pairs(b.value).map { it.second }
            named.fold(t) { u, w -> u.replace(Regex("(?i)" + Regex.escape(w) + " " + Regex.escape(b.value)), "") }.replace(b.value, "")
        }
        if (TIBETAN.containsMatchIn(outside)) out += "Tibetan script outside a bracket"
        for (w in known) standing(outside, w)?.let { out += "«$it» without its Tibetan" }
        return out
    }

    /**
     * [wylie] as a word of its own in [text], its first letter in either case
     * (a name at the start of a sentence): not inside a longer word, though an
     * English possessive may follow it ("the bla mkhyen's").
     */
    private fun standing(text: String, wylie: String): String? {
        fun isLetter(c: Char) = c.isLetter() || c == '\''  || c == '’'
        val variants = setOf(wylie, wylie.replaceFirstChar { it.uppercase() })
        for (v in variants) {
            var i = text.indexOf(v)
            while (i >= 0) {
                val end = i + v.length
                val beforeOk = i == 0 || !isLetter(text[i - 1])
                val afterOk = end == text.length || !isLetter(text[end]) ||
                    (text[end] in "'’" && end + 1 < text.length && text[end + 1] == 's' && (end + 2 == text.length || !text[end + 2].isLetter()))
                if (beforeOk && afterOk) return v
                i = text.indexOf(v, i + 1)
            }
        }
        return null
    }
}
