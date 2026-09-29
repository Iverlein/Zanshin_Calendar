/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

/**
 * The worked examples of THL Simplified Phonetic Transcription of Standard
 * Tibetan (Germano and Tournadre, 2003): "The General Principle", "Special
 * Rules" 1–13, "Word Boundaries" and the "List of Exceptions". THL
 * capitalises proper names; the comparison ignores case.
 */
class ThlTest {
    private val examples = listOf(
        // General principle
        "dag pa" to "dakpa", "ring po" to "ringpo", "rin chen" to "Rinchen", "lab" to "lap", "dum bu" to "dumbu",
        "dmar po" to "marpo", "ril bu" to "rilbu", "sa skya pa" to "Sakyapa", "blo bzang" to "Lozang",
        "rnying ma pa" to "Nyingmapa", "dge lugs pa" to "Gelukpa", "gzhis ka rtse" to "Zhikatsé", "mar me" to "marmé",
        "dge bshes" to "geshé",
        // Rules 1 and 2
        "bcu" to "chu", "gcig pa" to "chikpa", "nag chu" to "Nakchu",
        "'phag pa" to "pakpa", "gser thang" to "Sertang", "khang tshan" to "khangtsen",
        // Rule 3
        "bka' brgyud" to "Kagyü", "bsod nams" to "Sönam", "yul" to "yül", "dus tshod" to "dütsö", "bon po" to "Bönpo",
        // Rule 4
        "sde dge" to "Degé", "brgyad" to "gyé", "dge rgan" to "gegen", "ral pa can" to "Relpachen",
        "tshe ring" to "Tsering", "byes" to "Jé", "bstan 'dzin" to "Tendzin", "'jam dpal dbyangs" to "Jampelyang",
        // Rule 5
        "dge legs" to "Gelek", "kha btags" to "khatak", "sngags pa" to "ngakpa", "byang chub" to "jangchup",
        "thub bstan" to "Tupten", "thabs" to "tap",
        // Rule 6
        "lha sa ba" to "Lhasawa", "jo bo" to "Jowo", "dpa' bo" to "pawo", "gsal bar" to "selwar", "bar ba" to "barwa",
        // Rules 7 and 8
        "spyan ras gzigs" to "Chenrezik", "phyag" to "chak", "sbyin bdag" to "jindak",
        "smyong" to "nyong", "dmyal ba" to "nyelwa",
        // Rule 9
        "sgrol ma" to "Drölma", "grub thob" to "druptop", "sprul sku" to "trülku", "'bras spungs" to "Drepung",
        "'phrin las" to "trinlé", "srung ma" to "sungma", "srog rlung" to "soklung", "rdzun smra ba" to "dzünmawa",
        // Rules 10 to 12
        "klad pa" to "lepa", "glog" to "lok", "zla ba" to "dawa",
        "lha sa" to "Lhasa", "lho phyogs" to "lhochok", "lhun grub" to "lhündrup",
        "dbang" to "wang", "dbyar kha" to "yarkha", "dbral" to "rel",
        // Rule 13
        "bka' 'gyur" to "Kangyur", "dge 'dun" to "Gendün", "ngos 'dzin" to "ngöndzin", "rig 'dzin" to "Rindzin",
        "mkha' 'gro" to "khandro", "dkyil 'khor" to "kyinkhor", "chos 'phel" to "Chömpel", "dpal 'bar" to "Pembar",
        "sku 'bum" to "Kumbum", "dpal 'byor" to "Penjor", "rgyu 'bras" to "gyundré",
        // Word boundaries
        "lha mo skyid" to "Lhamokyi", "bsod nams rin chen" to "Sönam Rinchen", "gang byung mang byung" to "gangjung mangjung",
        // List of exceptions
        "skyabs 'gro" to "kyamdro", "rten 'brel" to "temdrel", "lam 'bras" to "lamdré", "rdo rje" to "Dorjé",
        "rgyal rtse" to "Gyantsé", "dga' ldan" to "Ganden",
    )

    @Test
    fun `THL's own examples`() {
        val wrong = examples.mapNotNull { (w, p) ->
            val got = Thl.toPhonetic(w)
            if (got == p.lowercase()) null else "$w: expected $p, got $got"
        }
        assertEquals(emptyList<String>(), wrong)
    }

    @Test
    fun `every term of the engine has a phonetic form`() {
        val terms = Weekday.entries.map { it.wylie } + Mansion.entries.map { it.wylie } + Yoga.entries.map { it.wylie } +
            Karana.entries.filter { it != Karana.VISHTI }.map { it.wylie } + ElementPair.entries.map { it.wylie } +
            Trigram.entries.map { it.wylie } + IndianElement.entries.map { it.wylie } +
            (1..12).map { TibetanCalendar.monthNames(it).wylie }
        for (w in terms) assertNotNull(Thl.toPhonetic(w), w)
    }
}
