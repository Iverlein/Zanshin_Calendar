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
import zanshin.core.kyureki.StarRelation
import zanshin.core.kyureki.Zassetsu
import zanshin.core.tibetan.ElementPair
import zanshin.core.tibetan.Karana
import zanshin.core.tibetan.Kinship
import zanshin.core.tibetan.Mansion
import zanshin.core.tibetan.PersonalDay
import zanshin.core.tibetan.PersonalMansion
import zanshin.core.tibetan.SpecialDay
import zanshin.core.tibetan.TibetanFestival
import zanshin.core.tibetan.Weekday
import zanshin.core.tibetan.Yoga

/** SPEC §8.1: nothing is shown without a published source. */
class TextsTest {
    private val all = listOf(
        Texts.ROKUYO, Texts.CHOKU, Texts.SHUKU, Texts.KYUSEI, Texts.SENJITSU, Texts.ZASSETSU, Texts.EHOU,
        Texts.ELEMENT_PAIR, Texts.SPECIAL_DAY, Texts.TIBETAN_FESTIVAL, Texts.PERSONAL_DAY, Texts.KIGAKU, Texts.PEBBLES,
        Texts.MANSION, Texts.ELECTIONAL_WEEKDAY, Texts.ELECTIONAL_DATE, Texts.ELECTIONAL_ANIMAL, Texts.ELECTIONAL_TRIGRAM,
        Texts.YOGA, Texts.KARANA, Texts.PERSONAL_MANSION, Texts.WEEKDAY, Texts.TRIGRAM, Texts.GREAT_COMBINATION, Texts.COMBINATION_DAY, Texts.GTSUG_LAG_DAY, Texts.RAHU, Texts.RAHU_GENERAL, Texts.EARTH_LORD,
    ).flatMap { it.values } + Texts.HAIRCUT + Texts.LUNAR_DATE

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
        complete(StarRelation.entries, Texts.KIGAKU)
        complete(Kinship.entries, Texts.PEBBLES)
        complete(Mansion.entries, Texts.MANSION)
        complete(Yoga.entries, Texts.YOGA)
        assertEquals(Yoga.entries.toSet(), Texts.YOGA_TONE.keys)
        complete(Karana.entries, Texts.KARANA)
        complete(PersonalMansion.entries, Texts.PERSONAL_MANSION)
        complete(Weekday.entries, Texts.WEEKDAY)
        assertEquals(30, Texts.LUNAR_DATE.size)
        assertEquals(30, Texts.HAIRCUT.size)

        for (r in all) {
            for (s in listOf(r.source) + r.also) assertTrue(s.url.startsWith("http"), "source of \"${r.summary}\"")
            assertTrue(r.summary.isNotBlank() || r.good.isNotEmpty() || r.avoid.isNotEmpty(), "empty reading from ${r.source.title}")
        }
    }

    /** ROADMAP R1: every "good for" and "avoid" wording names an activity or a time of day, not both. */
    @Test
    fun `every listed wording maps to an activity or a time of day`() {
        val wordings = all.flatMap { it.goodKeys + it.avoidKeys }.toSet()
        for (w in wordings) {
            val kinds = listOfNotNull(Activities.BY_WORDING[w], Activities.TIMES[w])
            assertEquals(1, kinds.size, "\"$w\" must map to exactly one of activities or times")
        }
        assertEquals(wordings, Activities.BY_WORDING.keys + Activities.TIMES.keys, "wordings no reading uses")
        assertEquals(Activity.entries.toSet(), Activities.BY_WORDING.values.flatten().toSet(), "activities no wording names")
    }

    @Test
    fun `Rahu's course is read on every date, the detailed course where it has one`() {
        // The detailed course names sixteen dates; the general course, with no lists, the other fourteen.
        assertEquals((1..30).toSet(), Texts.RAHU.keys + Texts.RAHU_GENERAL.keys)
        assertTrue(Texts.RAHU.keys.none { it in Texts.RAHU_GENERAL })
        assertEquals(listOf(2, 3, 5, 7, 9, 10, 13, 16, 19, 20, 23, 26, 28, 30), Texts.RAHU_GENERAL.keys.sorted())
        assertTrue(Texts.RAHU_GENERAL.values.all { it.goodKeys.isEmpty() && it.avoidKeys.isEmpty() })
    }
}
