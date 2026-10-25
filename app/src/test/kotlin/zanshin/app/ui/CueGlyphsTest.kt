/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.ui

import androidx.compose.ui.graphics.vector.PathParser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotSame
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import zanshin.core.texts.Activity
import zanshin.core.texts.ActivityFamily
import zanshin.core.texts.family

/** SPEC §10.7: the summary line and the breakdown look up a glyph for every family, and some works have their own. */
class CueGlyphsTest {
    @Test
    fun `every family has a glyph`() {
        assertEquals(ActivityFamily.entries.toSet(), CueGlyphs.FAMILY.keys)
    }

    @Test
    fun `every work is drawn, by its own glyph where it has one`() {
        for (a in Activity.entries) {
            val own = CueGlyphs.WORK[a]
            if (own == null) assertEquals(CueGlyphs.FAMILY.getValue(a.family), CueGlyphs.of(a))
            else {
                assertNotSame(CueGlyphs.FAMILY.getValue(a.family), own, "$a")
                assertEquals(own, CueGlyphs.of(a))
            }
        }
    }

    @Test
    fun `every work glyph parses`() {
        for ((a, g) in CueGlyphs.WORK) assertTrue(PathParser().parsePathString(g.path).toNodes().isNotEmpty(), "$a")
    }
}
