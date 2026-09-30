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
import zanshin.core.tibetan.ElementPair
import zanshin.core.tibetan.Gender
import zanshin.core.tibetan.Force
import zanshin.core.tibetan.IndianElement
import zanshin.core.tibetan.Karana
import zanshin.core.tibetan.Kinship
import zanshin.core.tibetan.Mansion
import zanshin.core.tibetan.PersonalDay
import zanshin.core.tibetan.SME_BA_COLOURS
import zanshin.core.tibetan.SpecialDay
import zanshin.core.tibetan.TibetanFestival
import zanshin.core.tibetan.Trigram
import zanshin.core.tibetan.Weekday
import zanshin.core.tibetan.Yoga
import java.io.File
import java.util.Locale

/** ROADMAP L1: every text the engines show comes from the catalog, and every language has all of it. */
class CatalogTest {
    private val english = Catalog.entries("")

    private fun present(key: String) = assertNotNull(english[key], "missing from texts.properties: $key")

    @Test
    fun `every term has its English`() {
        val terms: List<Enum<*>> = listOf(
            IndianElement.entries, Weekday.entries, Mansion.entries, Yoga.entries, Karana.entries, ElementPair.entries,
            Trigram.entries, SpecialDay.entries, TibetanFestival.entries, PersonalDay.entries, Force.entries, Kinship.entries,
            Rokuyo.entries, SolarTerm.entries, Gogyo.entries, Choku.entries, Shuku.entries, KyuSei.entries,
            StarRelation.entries, Senjitsu.entries, Zassetsu.entries, Ehou.entries, Activity.entries, License.entries,
        ).flatten()
        terms.forEach { present(glossKey(it)) }
        Weekday.entries.forEach { present(glossKey(it, "planet")) }
        (Element.entries + Animal.entries + Gender.entries).forEach { present(glossKey(it)) }
        (Element.entries + Gender.entries).forEach { present(glossKey(it, "inText")) }
        TibetanFestival.entries.forEach { present(glossKey(it, "title")) }
        (0..9).forEach { present("Stem.$it") }
        (0..11).forEach { present("Branch.$it") }
        (1..12).forEach { present("KyurekiMonth.$it"); present("TibetanMonth.$it.season") }
        SME_BA_COLOURS.forEach { present("Colour.$it") }
        Texts.JAPANESE_FESTIVAL.keys.forEach { present("Festival.$it") }
        listOf("Kanshi", "StarAffinity.feeds", "StarAffinity.overcomes", "StarAffinity.same").forEach(::present)
    }

    @Test
    fun `every reading's text is in the catalog`() {
        val readings = listOf(
            Texts.ROKUYO, Texts.CHOKU, Texts.SHUKU, Texts.KYUSEI, Texts.KIGAKU, Texts.SENJITSU, Texts.ZASSETSU,
            Texts.EHOU, Texts.ELEMENT_PAIR, Texts.SPECIAL_DAY, Texts.TIBETAN_FESTIVAL, Texts.JAPANESE_FESTIVAL,
            Texts.PERSONAL_DAY, Texts.PEBBLES,
        ).flatMap { it.values } + Texts.HAIRCUT
        for (r in readings) {
            assertTrue(r.key.startsWith("reading."), "reading without a key from ${r.source.title}")
            r.arg?.let { present(r.key); present(it) }
            (r.goodKeys + r.avoidKeys).forEach { present("wording.$it") }
        }
    }

    /** A translation has exactly the English keys, and each text the same placeholders. */
    @Test
    fun `every language has every key`() {
        val dir = File("src/main/resources/texts")
        val languages = dir.listFiles().orEmpty().mapNotNull { Regex("texts(_.+)\\.properties").matchEntire(it.name)?.groupValues?.get(1) }
        val placeholders = Regex("\\{\\d}")
        for (suffix in languages) {
            val t = Catalog.entries(suffix)
            assertEquals(english.keys, t.keys, "texts$suffix.properties: keys differ from English")
            for ((k, v) in t) {
                assertEquals(placeholders.findAll(english.getValue(k)).map { it.value }.toSet(), placeholders.findAll(v).map { it.value }.toSet(), "$suffix $k")
            }
        }
    }

    @Test
    fun `a language without a catalog falls back to English`() {
        assertEquals(english.getValue("Rokuyo.TAIAN"), Catalog.text("Rokuyo.TAIAN", Locale.forLanguageTag("xx")))
        assertEquals(listOf("_zh_Hant", "_zh_TW", "_zh", ""), Catalog.candidates(Locale.forLanguageTag("zh-Hant-TW")))
    }
}
