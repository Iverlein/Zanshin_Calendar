/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.ui

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import zanshin.core.texts.ActivityFamily

/** SPEC §10.7: the summary line and the breakdown look up a glyph for every family. */
class CueGlyphsTest {
    @Test
    fun `every family has a glyph`() {
        assertEquals(ActivityFamily.entries.toSet(), CueGlyphs.FAMILY.keys)
    }
}
