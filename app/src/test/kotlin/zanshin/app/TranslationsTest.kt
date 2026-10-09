/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.w3c.dom.Element
import zanshin.core.texts.TibetanNaming
import java.io.File
import java.util.Properties
import javax.xml.parsers.DocumentBuilderFactory

/**
 * The interface translations (SPEC §8.2, §10.5). Weblate commits a language
 * string by string, so a translation may be partial; a language offered in
 * the menu must be complete, in the interface and in the catalog.
 */
class TranslationsTest {
    private val res = File("src/main/res")
    private val catalog = File("../core/src/main/resources/texts")

    /** A strings.xml as name to its placeholders; plurals merge their items. Untranslatable strings left out. */
    private fun strings(file: File): Map<String, Set<String>> {
        val doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file)
        val out = mutableMapOf<String, Set<String>>()
        for (tag in listOf("string", "plurals")) {
            val nodes = doc.getElementsByTagName(tag)
            for (i in 0 until nodes.length) {
                val e = nodes.item(i) as Element
                if (e.getAttribute("translatable") == "false") continue
                out[e.getAttribute("name")] = PLACEHOLDER.findAll(e.textContent).map { it.value }.toSet()
            }
        }
        return out
    }

    private fun localeTag(file: File): String? {
        val doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file)
        val nodes = doc.getElementsByTagName("string")
        return (0 until nodes.length).map { nodes.item(it) as Element }.firstOrNull { it.getAttribute("name") == "locale_tag" }?.textContent
    }

    private val english = strings(File(res, "values/strings.xml"))

    private val translations: List<File> =
        res.listFiles().orEmpty().filter { it.name.startsWith("values-") }.map { File(it, "strings.xml") }.filter { it.exists() }

    private fun catalog(suffix: String): Map<String, String> {
        val p = Properties()
        File(catalog, "texts$suffix.properties").reader(Charsets.UTF_8).use { p.load(it) }
        return p.stringPropertyNames().associateWith { p.getProperty(it) }.filterValues { it.isNotBlank() }
    }

    @Test
    fun `the menu offers the languages of locales_config`() {
        val doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(File(res, "xml/locales_config.xml"))
        val nodes = doc.getElementsByTagName("locale")
        val listed = (0 until nodes.length).map { (nodes.item(it) as Element).getAttribute("android:name") }
        assertEquals(AppLanguage.TAGS, listed)
    }

    @Test
    fun `a translation has English strings with the same placeholders`() {
        for (file in translations) {
            val t = strings(file)
            assertTrue(english.keys.containsAll(t.keys), "${file.parentFile.name}: not in English: ${t.keys - english.keys}")
            for ((name, placeholders) in t) assertEquals(english.getValue(name), placeholders, "${file.parentFile.name} $name")
        }
    }

    /** locale_tag names the language the resources resolved to; dates and the catalog follow it (SPEC §10.1). */
    @Test
    fun `locale_tag names the folder's language`() {
        for (file in translations) {
            val tag = localeTag(file) ?: continue
            val folder = file.parentFile.name.removePrefix("values-").removePrefix("b+").split('-', '+').first()
            assertEquals(folder, tag.split('-').first(), "${file.parentFile.name}: locale_tag $tag")
        }
    }

    @Test
    fun `an offered language is complete`() {
        val englishCatalog = catalog("")
        for (tag in AppLanguage.TAGS - "en") {
            val file = translations.singleOrNull { localeTag(it) == tag }
            assertTrue(file != null, "no values folder with locale_tag $tag")
            val missing = english.keys - strings(file!!).keys
            assertTrue(missing.isEmpty(), "$tag strings.xml lacks $missing")
            val lacking = englishCatalog.keys - catalog("_" + tag.replace('-', '_')).keys
            assertTrue(lacking.isEmpty(), "texts_$tag.properties lacks $lacking")
        }
    }

    /**
     * SPEC §8.1: the interface and the store listing name every Tibetan word
     * by its English name, then its Tibetan script and Wylie in brackets, as
     * the catalog does (core's CatalogTest), and a translation names the same
     * words as English.
     */
    @Test
    fun `Tibetan words are named in English with their Tibetan and Wylie`() {
        val texts = mutableMapOf<String, String>()
        val counterpart = mutableMapOf<String, String>()
        for (file in listOf(File(res, "values/strings.xml")) + translations) {
            val nodes = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file).getElementsByTagName("string")
            for (i in 0 until nodes.length) {
                val e = nodes.item(i) as Element
                val name = e.getAttribute("name")
                val where = "${file.parentFile.name} $name"
                texts[where] = e.textContent.replace("\\'", "'")
                if (file.parentFile.name != "values") counterpart[where] = "values $name"
            }
        }
        val listing = File("../fastlane/metadata/android")
        listing.walkTopDown().filter { it.isFile && it.extension == "txt" }.forEach { f ->
            texts[f.path] = f.readText().replace(Regex("<[^>]+>"), " ")
            val english = File(listing, "en-US/" + f.relativeTo(listing).path.substringAfter('/'))
            if (!f.path.contains("/en-US/") && english.exists()) counterpart[f.path] = english.path
        }
        assertTrue(texts.keys.any { "changelogs" in it })
        val catalogs = listOf("", "_ru").map { catalog(it) }
        val known = TibetanNaming.engineWylie +
            (texts.values + catalogs.flatMap { it.values }).flatMap { TibetanNaming.pairs(it).map { p -> p.second } } - ALSO_ENGLISH
        val wrong = mutableListOf<String>()
        for ((where, text) in texts.toSortedMap()) {
            TibetanNaming.problems(text, known).forEach { wrong += "$where: $it" }
            if (Regex("White Beryl|берилл").containsMatchIn(text) && "bai DUr dkar po" !in text) wrong += "$where: the White Beryl without its Tibetan title"
            val english = counterpart[where]?.let { texts[it] } ?: continue
            val (en, here) = TibetanNaming.pairs(english).toSet() to TibetanNaming.pairs(text).toSet()
            if (en != here) wrong += "$where: names ${here - en}, English ${en - here}"
        }
        assertEquals("", wrong.joinToString("\n"))
    }

    private companion object {
        /** Wylie that is also an English word, left out of the check: the fire element's *me*. */
        val ALSO_ENGLISH = setOf("me")

        val PLACEHOLDER = Regex("%(\\d+\\$)?[-.\\d]*[sdf]")
    }
}
