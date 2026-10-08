/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.texts

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import zanshin.core.kyureki.Choku
import zanshin.core.kyureki.Ehou
import zanshin.core.kyureki.Gogyo
import zanshin.core.kyureki.KyuSei
import zanshin.core.kyureki.Rokuyo
import zanshin.core.kyureki.Senjitsu
import zanshin.core.kyureki.Shuku
import zanshin.core.kyureki.SolarTerm
import zanshin.core.kyureki.StarRelation
import zanshin.core.kyureki.Zassetsu
import zanshin.core.tibetan.Animal
import zanshin.core.tibetan.Element
import zanshin.core.tibetan.ElectionalFactor
import zanshin.core.tibetan.ElementPair
import zanshin.core.tibetan.Gender
import zanshin.core.tibetan.Force
import zanshin.core.tibetan.IndianElement
import zanshin.core.tibetan.Karana
import zanshin.core.tibetan.Kinship
import zanshin.core.tibetan.LunarDayClass
import zanshin.core.tibetan.Mansion
import zanshin.core.tibetan.PersonalDay
import zanshin.core.tibetan.PersonalMansion
import zanshin.core.tibetan.OwnDay
import zanshin.core.tibetan.SME_BA_COLOURS
import zanshin.core.tibetan.SpecialDay
import zanshin.core.tibetan.TibetanFestival
import zanshin.core.tibetan.CombinationDay
import zanshin.core.tibetan.GreatCombination
import zanshin.core.tibetan.Trigram
import zanshin.core.tibetan.Weekday
import zanshin.core.tibetan.Yoga
import java.io.File
import java.util.Locale

/** SPEC §8.2: every text the engines show comes from the catalog, and a translation matches it. */
class CatalogTest {
    private val english = Catalog.entries("")

    private fun present(key: String) = assertNotNull(english[key], "missing from texts.properties: $key")

    @Test
    fun `every term has its English`() {
        val terms: List<Enum<*>> = listOf(
            IndianElement.entries, Weekday.entries, Mansion.entries, Yoga.entries, Karana.entries, ElementPair.entries,
            Trigram.entries, SpecialDay.entries, TibetanFestival.entries, PersonalDay.entries, Force.entries, Kinship.entries,
            Rokuyo.entries, SolarTerm.entries, Gogyo.entries, Choku.entries, Shuku.entries, KyuSei.entries,
            StarRelation.entries, Senjitsu.entries, Zassetsu.entries, Ehou.entries, Activity.entries, ActivityFamily.entries, License.entries, ElectionalFactor.entries, DayFactor.entries, GreatCombination.entries, CombinationDay.entries,
            LunarDayClass.entries, PersonalMansion.entries, OwnDay.entries,
        ).flatten()
        terms.forEach { present(glossKey(it)) }
        Weekday.entries.forEach { present(glossKey(it, "planet")) }
        (Element.entries + Animal.entries + Gender.entries).forEach { present(glossKey(it)) }
        (Element.entries + Gender.entries).forEach { present(glossKey(it, "inText")) }
        TibetanFestival.entries.forEach { present(glossKey(it, "title")) }
        (0..9).forEach { present("Stem.$it") }
        (0..11).forEach { present("Branch.$it") }
        (1..12).forEach { present("KyurekiMonth.$it"); present("Season.$it") }
        SME_BA_COLOURS.forEach { present("Colour.$it") }
        Texts.JAPANESE_FESTIVAL.keys.forEach { present("Festival.$it") }
        listOf("Kanshi", "StarAffinity.feeds", "StarAffinity.overcomes", "StarAffinity.same").forEach(::present)
    }

    @Test
    fun `every reading's text is in the catalog`() {
        val readings = listOf(
            Texts.ROKUYO, Texts.CHOKU, Texts.SHUKU, Texts.KYUSEI, Texts.KIGAKU, Texts.SENJITSU, Texts.ZASSETSU,
            Texts.EHOU, Texts.ELEMENT_PAIR, Texts.SPECIAL_DAY, Texts.TIBETAN_FESTIVAL, Texts.JAPANESE_FESTIVAL,
            Texts.PERSONAL_DAY, Texts.PEBBLES, Texts.MANSION, Texts.ELECTIONAL_WEEKDAY, Texts.ELECTIONAL_DATE,
            Texts.ELECTIONAL_ANIMAL, Texts.ELECTIONAL_TRIGRAM, Texts.YOGA, Texts.KARANA,
            Texts.PERSONAL_MANSION, Texts.WEEKDAY, Texts.TRIGRAM, Texts.GREAT_COMBINATION, Texts.COMBINATION_DAY, Texts.GTSUG_LAG_DAY, Texts.RAHU, Texts.RAHU_GENERAL, Texts.EARTH_LORD, Texts.BLA_MKHYEN,
            Texts.OWN_DAY,
        ).flatMap { it.values } + Texts.HAIRCUT + Texts.LUNAR_DATE + Texts.BURNING_DATE
        (Texts.YOGA.values + Texts.LUNAR_DATE + Texts.PERSONAL_MANSION.values + Texts.WEEKDAY.values + Texts.TRIGRAM.values + Texts.GREAT_COMBINATION.values + Texts.ELEMENT_PAIR.values + Texts.COMBINATION_DAY.values + Texts.RAHU.values).forEach { present(it.key) }
        for (r in readings) {
            assertTrue(r.key.startsWith("reading."), "reading without a key from ${r.source.title}")
            r.arg?.let { present(r.key); present(it) }
            (r.goodKeys + r.avoidKeys).forEach { present("wording.$it") }
        }
    }

    /**
     * A translation has only English keys, and each text the same placeholders;
     * it may lack some, which then show in English. A language offered in the
     * app's menu must have them all, which the app's TranslationsTest checks.
     */
    @Test
    fun `every language has English keys and placeholders`() {
        val dir = File("src/main/resources/texts")
        val languages = dir.listFiles().orEmpty().mapNotNull { Regex("texts(_.+)\\.properties").matchEntire(it.name)?.groupValues?.get(1) }
        val placeholders = Regex("\\{\\d}")
        for (suffix in languages) {
            val t = Catalog.entries(suffix)
            assertTrue(english.keys.containsAll(t.keys), "texts$suffix.properties: keys not in English: ${t.keys - english.keys}")
            for ((k, v) in t) {
                assertEquals(placeholders.findAll(english.getValue(k)).map { it.value }.toSet(), placeholders.findAll(v).map { it.value }.toSet(), "$suffix $k")
            }
        }
    }

    /** The White Beryl (vol. 2, pp. 303–304) places the la on every date for people and for horses and other livestock. */
    @Test
    fun `every lunar date gives the la's place in a person and in livestock`() {
        val livestock = mapOf("" to "in horses and other livestock", "_ru" to "у лошадей и другого скота")
        for ((suffix, words) in livestock) {
            val t = Catalog.entries(suffix)
            for (date in 1..30) {
                val reading = t.getValue("reading.LunarDate.$date")
                assertTrue("(bla)" in reading && words in reading, "texts$suffix reading.LunarDate.$date")
            }
        }
    }

    /**
     * The names of the White Beryl's terms follow its words (ROADMAP T2.17), in every
     * language: terms it writes differently never share a name, and one word of its keeps
     * one word across the weekday-and-mansion reckonings that use it. Trigrams are left
     * out: they are named by their image (li, fire), not by a Tibetan word.
     */
    @Test
    fun `names follow the White Beryl's words`() {
        val terms: List<Pair<Enum<*>, String>> = Weekday.entries.map { it to it.wylie } +
            IndianElement.entries.map { it to it.wylie } + Mansion.entries.map { it to it.wylie } +
            Yoga.entries.map { it to it.wylie } + Karana.entries.map { it to it.wylie } +
            ElementPair.entries.map { it to it.wylie } + GreatCombination.entries.map { it to it.wylie } +
            CombinationDay.entries.map { it to it.wylie } + LunarDayClass.entries.map { it to it.wylie } +
            PersonalMansion.entries.map { it to it.wylie } + Force.entries.map { it to it.wylie } +
            OwnDay.entries.map { it to it.wylie }
        // The fire pair's 'phel 'gyur is 'phel, "growth", with 'gyur, "becoming": one word.
        val word = mapOf("'phel 'gyur" to "'phel")
        // Each WB word: its stem in English and Russian, and the names and readings that carry it.
        val oneWord = mapOf(
            "dngos grub" to Triple("attainment", "достижени", listOf("Yoga.SIDDHI", "ElementPair.EARTH_EARTH")),
            "grub" to Triple("accomplishment", "свершени", listOf("GreatCombination.GRUB", "CombinationDay.GRUB_NYI", "CombinationDay.GRUB_SBYOR")),
            "'phel" to Triple("growth", "возрастани", listOf("Yoga.VRIDDHI", "ElementPair.FIRE_FIRE", "GreatCombination.PHEL", "CombinationDay.PHEL_NYI")),
            "sreg" to Triple("burning", "сожжени", listOf("ElementPair.EARTH_FIRE", "BurningDate", "reading.CombinationDay.GTAN_SPANG")),
            "mi 'phrod" to Triple("incompatibility", "несовместимост", listOf("ElementPair.EARTH_WIND", "CombinationDay.MI_PHROD_NYI")),
            "mi mthun" to Triple("discord", "разлад", listOf("ElementPair.WATER_WIND", "CombinationDay.MI_MTHUN_NYI")),
            "bdud rtsi" to Triple("nectar", "нектар", listOf("ElementPair.WATER_WATER", "GreatCombination.BDUD_RTSI")),
            "'chi" to Triple("death", "смерт", listOf("ElementPair.FIRE_WATER", "CombinationDay.CHI_SBYOR", "GreatCombination.CHI_BDAG")),
            "dga' ba" to Triple("joy", "радост", listOf("Yoga.HARSHANA", "LunarDayClass.NANDA")),
        )
        for (suffix in listOf("", "_ru")) {
            val t = Catalog.entries(suffix)
            terms.groupBy { (e, _) -> t.getValue(glossKey(e)).lowercase() }.forEach { (name, same) ->
                assertEquals(1, same.map { word[it.second] ?: it.second }.toSet().size, "texts$suffix: «$name» names ${same.map { "${glossKey(it.first)} (${it.second})" }}")
            }
            for ((wylie, carried) in oneWord) {
                val (en, ru, keys) = carried
                val stem = if (suffix == "") en else ru
                keys.forEach { assertTrue(stem in t.getValue(it).lowercase(), "texts$suffix, $wylie: $it lacks «$stem»") }
            }
        }
    }

    /**
     * SPEC §8: a text is named by its English name first, then its Tibetan
     * script and Wylie in brackets, so that a reader can find it; several in
     * one bracket stand apart by semicolons. Every reading that names the
     * White Beryl gives its Tibetan title once.
     */
    @Test
    fun `texts are named in English with their Tibetan and Wylie`() {
        val tibetan = Regex("[\\u0F00-\\u0FFF]")
        val bracket = Regex("\\(([^()]*)\\)")
        val pair = Regex("[\\u0F00-\\u0FFF]+, [^\\u0F00-\\u0FFF]+")
        fun check(where: String, text: String) {
            val inBrackets = bracket.findAll(text).filter { tibetan.containsMatchIn(it.value) }.toList()
            for (b in inBrackets) {
                for (part in b.groupValues[1].split("; ")) assertTrue(pair.matches(part), "$where: «$part» is not «Tibetan, Wylie»")
                assertTrue(b.range.first >= 2 && text[b.range.first - 1] == ' ' && !tibetan.matches(text[b.range.first - 2].toString()), "$where: no name before ${b.value}")
            }
            val outside = inBrackets.fold(text) { t, b -> t.replace(b.value, "") }
            assertTrue(!tibetan.containsMatchIn(outside), "$where: Tibetan script outside a bracket")
            for (wylie in listOf("kun phan me long", "dbyangs 'char", "gtsug lag", "zla ba'i 'od zer", "bai DUr", "snang brgyad", "mtshan brjod", "gzungs bsdus", "tog gzungs")) {
                assertTrue(wylie !in outside, "$where: «$wylie» without its Tibetan")
            }
        }
        val dir = File("src/main/resources/texts")
        val suffixes = listOf("") + dir.listFiles().orEmpty().mapNotNull { Regex("texts(_.+)\\.properties").matchEntire(it.name)?.groupValues?.get(1) }
        for (suffix in suffixes) {
            for ((k, v) in Catalog.entries(suffix)) {
                check("texts$suffix $k", v)
                if (Regex("White Beryl|берилл").containsMatchIn(v)) assertTrue("bai DUr dkar po" in v, "texts$suffix $k: the White Beryl without its Tibetan title")
            }
        }
        val sources = Sources::class.java.declaredMethods
            .filter { it.returnType == Source::class.java && it.parameterCount == 0 }
            .map { it.isAccessible = true; it.invoke(Sources) as Source }
        assertTrue(sources.size > 30)
        for (s in sources) check("source ${s.publisher}", s.title)
    }

    @Test
    fun `a language without a catalog falls back to English`() {
        assertEquals(english.getValue("Rokuyo.TAIAN"), Catalog.text("Rokuyo.TAIAN", Locale.forLanguageTag("xx")))
        assertEquals(listOf("_zh_Hant", "_zh_TW", "_zh", ""), Catalog.candidates(Locale.forLanguageTag("zh-Hant-TW")))
    }
}
