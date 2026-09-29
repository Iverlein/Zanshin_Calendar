/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class EwtsTest {
    @Test
    fun `prefixes, superscripts, subscripts and suffixes`() {
        val cases = mapOf(
            "dbang po" to "དབང་པོ", // prefix d before the root b
            "bsgrub bya" to "བསྒྲུབ་བྱ", // prefix, superscript, root, subscript; ya-btags
            "'grams" to "འགྲམས", // 'a-chung prefix, two suffixes
            "rgyal" to "རྒྱལ", // superscript r over a subscribed stack
            "snron" to "སྣྲོན",
            "dwa" to "དྭ", // wa-zur, not a prefix
            "bra nye" to "བྲ་ཉེ",
            "brtan pa" to "བརྟན་པ", // prefix b before the stack rt
            "zug rngu" to "ཟུག་རྔུ",
            "tshe dang ldan pa" to "ཚེ་དང་ལྡན་པ",
            "mdza' bo" to "མཛའ་བོ", // 'a-chung suffix
            "gza’ mig dmar" to "གཟའ་མིག་དམར", // typographic apostrophe
            "g.yu" to "གཡུ",
            "gyu" to "གྱུ",
            "bkra shis" to "བཀྲ་ཤིས",
            "lha mtshams" to "ལྷ་མཚམས",
            "rlung" to "རླུང",
        )
        for ((w, t) in cases) assertEquals(t, Ewts.toTibetan(w), w)
    }

    @Test
    fun `anything that is not plain EWTS gives no script`() {
        assertNull(Ewts.toTibetan("vishti"))
        assertNull(Ewts.toTibetan("xyz"))
    }

    /** Every Tibetan term the app shows has a script, except the Sanskrit loan still to be sourced. */
    @Test
    fun `every term of the engine converts`() {
        val terms = Weekday.entries.map { it.wylie } + Mansion.entries.map { it.wylie } + Yoga.entries.map { it.wylie } +
            Karana.entries.filter { it != Karana.VISHTI }.map { it.wylie } + ElementPair.entries.map { it.wylie } +
            Trigram.entries.map { it.wylie } + IndianElement.entries.map { it.wylie } +
            (1..12).map { TibetanCalendar.monthNames(it).wylie }
        for (w in terms) assertNotNull(Ewts.toTibetan(w), w)
    }
}
