/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.texts

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.util.Locale

/** ROADMAP R5: a Together's rites, one by one, the most general first. */
class RitesTest {
    private val english = Locale.ENGLISH
    private val russian = Locale.forLanguageTag("ru")

    @Test
    fun `a remedy row splits where an imperative opens`() {
        assertEquals(
            listOf(
                "send the thread-crosses of the mamo (མ་མོ, ma mo), the nāga demons (ཀླུ་བདུད, klu bdud) and the gyalpo (རྒྱལ་པོ, rgyal po)",
                "beat the drewo (གྲེ་བོ, gre bo) and dremo (གྲེ་མོ, gre mo)",
            ),
            Rites.split(Catalog.text("reading.IllnessTrigram.ZON.remedy", english), "en"),
        )
        assertEquals(
            listOf("почините дорогу", "выпустите на волю живых существ", "закажите гадание и обрядовые службы (རིམ་གྲོ, rim gro)"),
            Rites.split(Catalog.text("reading.IllnessPebble.DAY.LIFE_RISE.BLACK.remedy", russian), "ru"),
        )
        // A circumstance at the head stays with the first rite.
        assertEquals(
            "with a white dog, a donkey and a black-necked goat, press the gongpo spirit (འགོང་པོ, 'gong po) and namté (གནམ་ཐེ, gnam the) under a skull",
            Rites.split(Catalog.text("reading.IllnessSpirit.1.RTER_BTSOG.remedy", english), "en").first(),
        )
        // A rite that refers back stays with the one before it; two bare verbs share what follows.
        assertEquals(
            listOf(
                "take no other's life",
                "make the sick person's figure on father, mother and son woods, dress it in old clothes, lay it in a bed and send it away",
            ),
            Rites.split(Catalog.text("reading.IllnessPebble.YEAR.LIFE_RISE.BLACK.remedy", english), "en"),
        )
        assertTrue("call back and accomplish life" in Rites.split(Catalog.text("reading.IllnessPoint.1.remedy", english), "en"))
        // Nothing is split inside quotation marks.
        assertTrue(Rites.split(Catalog.text("reading.NineMultiple.remedy", russian), "ru").none { it.startsWith("смерти") })
        assertEquals(listOf("a, b and c"), Rites.split("a, b and c", "de"))
    }

    @Test
    fun `every remedy row splits into whole rites`() {
        val keys = Catalog.entries("").keys.filter { it.endsWith(".remedy") }
        for ((locale, language) in listOf(english to "en", russian to "ru")) {
            for (k in keys) {
                val text = Catalog.text(k, locale)
                val rites = Rites.split(text, language)
                assertTrue(rites.isNotEmpty() && rites.none { it.isBlank() }, "$language $k")
                // Each rite is a stretch of the row, in order, and no bracket is cut.
                var from = 0
                for (r in rites) {
                    val at = text.indexOf(r, from)
                    assertTrue(at >= 0, "$language $k: «$r»")
                    from = at + r.length
                    assertEquals(r.count { it == '(' }, r.count { it == ')' }, "$language $k: «$r»")
                }
            }
        }
    }

    @Test
    fun `the rites the sources lay most often come first`() {
        val texts = listOf(
            Catalog.text("reading.IllnessPebble.HOUR.BODY_RISE.BLACK.remedy", english), // carry a dog's corpse …
            Catalog.text("reading.IllnessDay.MONKEY.remedy", english), // treat the earth lord …
            Catalog.text("reading.IllnessPebble.HOUR.LIFE.BLACK.remedy", english), // stamp clay votive images …
        )
        val list = Rites.list(texts, english)
        assertEquals("carry a dog's corpse to a place in a good direction", list.last())
        assertTrue(list.indexOf("treat the earth lord (ས་བདག, sa bdag)") < list.indexOf("drive out the serak (བསེ་རག, bse rag)"))
        // Each rite once.
        assertEquals(list.size, list.toSet().size)
        assertEquals(Rites.split(texts.joinToString(", "), "en").toSet().size, list.size)
    }
}
