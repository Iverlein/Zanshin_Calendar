/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.kyureki

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import zanshin.core.vectors
import java.time.LocalDate

class KigakuTest {
    private val byKanji = KyuSei.entries.associateBy { it.kanji }

    /** Every pair of stars against the table of Japanese Wikipedia 九星, 九星の関係. */
    @Test
    fun `relations of all 81 pairs match the published table`() {
        val rows = vectors("wikipedia-kyusei-relations.tsv")
        assertEquals(81, rows.map { it[0] to it[1] }.toSet().size)
        for (r in rows) {
            val a = Kigaku.affinity(byKanji.getValue(r[0]), byKanji.getValue(r[1]))
            assertEquals(r[2], a.relation.kanji, "${r[0]} / ${r[1]}")
            if (r.getOrElse(3) { "" }.isNotEmpty()) assertEquals(r[3], a.cycle, "${r[0]} / ${r[1]}")
        }
    }

    @Test
    fun `birth star is reckoned from 立春`() {
        // 2026 is a 一白 year, 2025 a 二黒 year (the 180-year count of Japanese Wikipedia 九星気学:
        // 2008 was 一白); 立春 2026 fell on 4 February.
        assertEquals(KyuSei.JIKOKU, Kigaku.honmeiStar(LocalDate.of(2026, 2, 3)))
        assertEquals(KyuSei.IPPAKU, Kigaku.honmeiStar(LocalDate.of(2026, 2, 4)))
        assertEquals(KyuSei.IPPAKU, Kigaku.honmeiStar(LocalDate.of(2008, 6, 1)))
    }
}
