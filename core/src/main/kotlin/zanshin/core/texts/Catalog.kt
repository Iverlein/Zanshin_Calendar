/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.texts

import java.io.InputStreamReader
import java.util.Locale
import java.util.Properties
import java.util.concurrent.ConcurrentHashMap

/**
 * The translatable text of the engines and readings (SPEC §8.2): English glosses
 * of the terms, reading summaries and the wordings of their lists. One catalog
 * per language under `texts/`: `texts.properties` is English, the source
 * language; `texts_ru.properties`, `texts_zh_Hant.properties` and so on hold
 * translations, UTF-8. A key a language lacks falls back to English.
 *
 * Read with [Properties.load] from a UTF-8 reader rather than ResourceBundle,
 * which on older Android reads property files as ISO 8859-1. A translation may
 * be partial, as Weblate commits only reviewed strings (SPEC §8.2): an empty
 * entry counts as missing.
 */
object Catalog {
    private val loaded = ConcurrentHashMap<String, Map<String, String>>()

    /**
     * The language to show, set by the app to the language its own resources
     * resolved to, so the catalog and the interface never disagree; the
     * default locale when unset.
     */
    @Volatile
    var locale: Locale? = null

    /** The catalog file suffixes to try for [locale], most specific first, ending with English. */
    fun candidates(locale: Locale): List<String> = listOfNotNull(
        locale.script.takeIf { it.isNotEmpty() }?.let { "_${locale.language}_$it" },
        locale.country.takeIf { it.isNotEmpty() }?.let { "_${locale.language}_$it" },
        locale.language.takeIf { it.isNotEmpty() && it != "en" }?.let { "_$it" },
        "",
    ).distinct()

    /** The entries of one catalog file, empty if there is none. */
    fun entries(suffix: String): Map<String, String> = loaded.getOrPut(suffix) {
        val stream = Catalog::class.java.getResourceAsStream("/texts/texts$suffix.properties") ?: return@getOrPut emptyMap()
        val p = Properties()
        InputStreamReader(stream, Charsets.UTF_8).use { p.load(it) }
        p.stringPropertyNames().associateWith { p.getProperty(it) }.filterValues { it.isNotBlank() }
    }

    fun textOrNull(key: String, locale: Locale = this.locale ?: Locale.getDefault()): String? =
        candidates(locale).firstNotNullOfOrNull { entries(it)[key] }

    /** The text of [key]; a missing key shows as itself, and `CatalogTest` fails on it. */
    fun text(key: String, locale: Locale = this.locale ?: Locale.getDefault()): String = textOrNull(key, locale) ?: key

    /** The text of [key] with {0}, {1}… replaced by [args]. */
    fun format(key: String, vararg args: String): String = fill(text(key), *args)

    fun fill(pattern: String, vararg args: String): String =
        args.foldIndexed(pattern) { i, s, a -> s.replace("{$i}", a) }
}

/** The name of an engine term in the current language: catalog key `<Enum>.<NAME>`, or `<Enum>.<NAME>.<field>`. */
fun gloss(e: Enum<*>, field: String? = null): String =
    Catalog.text(glossKey(e, field))

fun glossKey(e: Enum<*>, field: String? = null): String =
    "${e.declaringJavaClass.simpleName}.${e.name}" + (field?.let { ".$it" } ?: "")
