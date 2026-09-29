/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.texts

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import zanshin.core.kyureki.KyuSei
import zanshin.core.kyureki.Kyureki
import zanshin.core.kyureki.Rekichu
import java.time.LocalDate

/** ROADMAP R3: the summary lists only what the readings state, and keeps both sides of a disagreement. */
class DaySummaryTest {
    @Test
    fun `every listed activity is named by the annotations it cites`() {
        var date = LocalDate.of(2026, 1, 1)
        var disputedDays = 0
        while (date.year == 2026) {
            val rk = Rekichu.of(date)
            val s = DaySummary.of(Kyureki.of(date), rk, KyuSei.IPPAKU)
            assertEquals(rk.mark, s.mark, "$date")
            for (n in s.activities) {
                assertTrue(n.good.isNotEmpty() || n.avoid.isNotEmpty(), "$date ${n.activity}")
                n.good.forEach { assertTrue(n.activity in Activities.of(it.reading!!.good), "$date ${n.activity} ${it.kanji}") }
                n.avoid.forEach { assertTrue(n.activity in Activities.of(it.reading!!.avoid), "$date ${n.activity} ${it.kanji}") }
                assertEquals(n.good.isNotEmpty() && n.avoid.isNotEmpty(), n.disputed)
            }
            val named = s.byTone.values.flatten().flatMap { e -> e.reading?.let { Activities.of(it.good) + Activities.of(it.avoid) }.orEmpty() }.toSet()
            assertEquals(named, s.activities.map { it.activity }.toSet(), "$date: activities left out")
            if (s.activities.any { it.disputed }) disputedDays++
            date = date.plusDays(1)
        }
        // Annotations disagree often enough that hiding either side would change the picture.
        assertTrue(disputedDays > 0)
    }
}
