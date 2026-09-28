/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.texts

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import zanshin.core.kyureki.Choku
import zanshin.core.kyureki.Ehou
import zanshin.core.kyureki.KyuSei
import zanshin.core.kyureki.Rokuyo
import zanshin.core.kyureki.Senjitsu
import zanshin.core.kyureki.Shuku
import zanshin.core.kyureki.Zassetsu
import zanshin.core.tibetan.ElementPair
import zanshin.core.tibetan.PersonalDay
import zanshin.core.tibetan.SpecialDay
import zanshin.core.tibetan.TibetanFestival

/** SPEC §8.1: nothing is shown without a published source. */
class TextsTest {
    @Test
    fun `every annotation has a sourced reading`() {
        fun <E : Enum<E>> complete(entries: List<E>, map: Map<E, Reading>) =
            assertEquals(entries.toSet(), map.keys, "missing readings")
        complete(Rokuyo.entries, Texts.ROKUYO)
        complete(Choku.entries, Texts.CHOKU)
        complete(Shuku.entries, Texts.SHUKU)
        complete(KyuSei.entries, Texts.KYUSEI)
        complete(Senjitsu.entries, Texts.SENJITSU)
        complete(Zassetsu.entries, Texts.ZASSETSU)
        complete(Ehou.entries, Texts.EHOU)
        complete(ElementPair.entries, Texts.ELEMENT_PAIR)
        complete(SpecialDay.entries, Texts.SPECIAL_DAY)
        complete(TibetanFestival.entries, Texts.TIBETAN_FESTIVAL)
        complete(PersonalDay.entries, Texts.PERSONAL_DAY)
        assertEquals(30, Texts.HAIRCUT.size)

        val all = listOf(
            Texts.ROKUYO, Texts.CHOKU, Texts.SHUKU, Texts.KYUSEI, Texts.SENJITSU, Texts.ZASSETSU, Texts.EHOU,
            Texts.ELEMENT_PAIR, Texts.SPECIAL_DAY, Texts.TIBETAN_FESTIVAL, Texts.PERSONAL_DAY,
        ).flatMap { it.values } + Texts.HAIRCUT
        for (r in all) {
            assertTrue(r.source.url.startsWith("http"), "source of \"${r.summary}\"")
            assertTrue(r.summary.isNotBlank() || r.good.isNotEmpty() || r.avoid.isNotEmpty(), "empty reading from ${r.source.title}")
        }
    }
}
