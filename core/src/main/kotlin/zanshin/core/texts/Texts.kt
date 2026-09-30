/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.texts

import zanshin.core.kyureki.Choku
import zanshin.core.kyureki.Ehou
import zanshin.core.kyureki.KyuSei
import zanshin.core.kyureki.Rokuyo
import zanshin.core.kyureki.Senjitsu
import zanshin.core.kyureki.Shuku
import zanshin.core.kyureki.StarRelation
import zanshin.core.kyureki.Zassetsu
import zanshin.core.tibetan.ElementPair
import zanshin.core.tibetan.Kinship
import zanshin.core.tibetan.PersonalDay
import zanshin.core.tibetan.SpecialDay
import zanshin.core.tibetan.TibetanFestival

/*
 * Readings shown on demand (SPEC §8). Each has a published source and a
 * licence; its text is in the catalog (`texts/texts.properties`). The default
 * is this project's own English statement of what the source says (MPL-2.0);
 * wording adapted from Japanese Wikipedia is CC BY-SA 4.0. Nothing under a
 * non-commercial licence: F-Droid would flag it as a non-free asset.
 */

enum class License {
    OWN, CC_BY_SA;

    val label: String get() = gloss(this)
}

data class Source(val title: String, val publisher: String, val url: String)

/**
 * A sourced reading. Its text lives in the catalog (SPEC §8.2): the summary
 * under [key], and the wordings of the "good for" and "avoid" lists under
 * `wording.<key>`. The lists keep the source's wording, one catalog entry per
 * wording, so `Activities` can tell which act each one names.
 */
data class Reading(
    val goodKeys: List<String> = emptyList(),
    val avoidKeys: List<String> = emptyList(),
    val source: Source,
    val license: License = License.OWN,
    /** Further sources for parts of the summary, under the same licence. */
    val also: List<Source> = emptyList(),
    /** Catalog key of the summary, set by [keyed] for most readings; none in the catalog when the source gives only the lists. */
    val key: String = "",
    /** Catalog key of the text that fills the summary's {0}. */
    val arg: String? = null,
) {
    val summary: String
        get() = Catalog.textOrNull(key)?.let { s -> arg?.let { Catalog.fill(s, Catalog.text(it)) } ?: s } ?: ""
    val good: List<String> get() = goodKeys.map { Catalog.text("wording.$it") }
    val avoid: List<String> get() = avoidKeys.map { Catalog.text("wording.$it") }
}

/** Readings keyed by term, each summary under `reading.<Enum>.<NAME>`. */
private fun <E : Enum<E>> keyed(vararg entries: Pair<E, Reading>): Map<E, Reading> =
    entries.associate { (e, r) -> e to r.copy(key = "reading.${glossKey(e)}") }

object Sources {
    val TODAN_ROKUYO = Source("こよみ博物館「六曜」", "株式会社トーダン (Todan)", "https://www.todan.co.jp/koyomi_museum/basic/rekichu/6.html")
    val TODAN_CHOKU = Source("こよみ博物館「十二直」", "株式会社トーダン (Todan)", "https://www.todan.co.jp/koyomi_museum/basic/rekichu/12.html")
    val TODAN_SHUKU = Source("こよみ博物館「二十八宿」", "株式会社トーダン (Todan)", "https://www.todan.co.jp/koyomi_museum/basic/rekichu/28.html")
    val WP_ROKUYO = Source("六曜", "Japanese Wikipedia", "https://ja.wikipedia.org/wiki/六曜")
    val WP_KAGEDAN = Source("暦注下段", "Japanese Wikipedia", "https://ja.wikipedia.org/wiki/暦注下段")
    val WP_ICHIRYU = Source("一粒万倍日", "Japanese Wikipedia", "https://ja.wikipedia.org/wiki/一粒万倍日")
    val WP_FUJOJU = Source("不成就日", "Japanese Wikipedia", "https://ja.wikipedia.org/wiki/不成就日")
    val WP_SANRINBO = Source("三隣亡", "Japanese Wikipedia", "https://ja.wikipedia.org/wiki/三隣亡")
    val WP_TENICHI = Source("天一神", "Japanese Wikipedia", "https://ja.wikipedia.org/wiki/天一神")
    val WP_HASSEN = Source("八専", "Japanese Wikipedia", "https://ja.wikipedia.org/wiki/八専")
    val WP_JIPPO = Source("十方暮", "Japanese Wikipedia", "https://ja.wikipedia.org/wiki/十方暮")
    val WP_TSUCHI = Source("犯土", "Japanese Wikipedia", "https://ja.wikipedia.org/wiki/犯土")
    val WP_KOSHIN = Source("庚申待", "Japanese Wikipedia", "https://ja.wikipedia.org/wiki/庚申待")
    val WP_KYUSEI = Source("九星", "Japanese Wikipedia", "https://ja.wikipedia.org/wiki/九星")
    val WP_KIGAKU = Source("九星気学", "Japanese Wikipedia", "https://ja.wikipedia.org/wiki/九星気学")
    val WP_TOSHITOKU = Source("歳徳神", "Japanese Wikipedia", "https://ja.wikipedia.org/wiki/歳徳神")
    val KB_TORA = Source("「寅の日」 精選版 日本国語大辞典", "Kotobank", "https://kotobank.jp/word/寅の日")
    val KB_MI = Source("「巳の日」 精選版 日本国語大辞典", "Kotobank", "https://kotobank.jp/word/巳の日")
    val KB_KINOENE = Source("「甲子」 日本大百科全書・世界大百科事典", "Kotobank", "https://kotobank.jp/word/甲子")
    val NAOJ_SEKKU = Source("暦Wiki「節句」", "国立天文台 暦計算室 (NAOJ)", "https://eco.mtk.nao.ac.jp/koyomi/wiki/C0E1B6E7.html")
    val NAOJ_JUSANYA = Source("暦Wiki「中秋の名月とは/十三夜」", "国立天文台 暦計算室 (NAOJ)", "https://eco.mtk.nao.ac.jp/koyomi/wiki/C3E6BDA9A4CECCBEB7EEA4C8A4CF2FBDBDBBB0CCEB.html")
    val NAOJ_ZASSETSU = Source("暦Wiki「雑節とは？」", "国立天文台 暦計算室 (NAOJ)", "https://eco.mtk.nao.ac.jp/koyomi/wiki/B5A8C0E12FBBA8C0E1A4C8A4CFA1A9.html")
    val FPMT_HAIR = Source(
        "From the Sutra Chapter of Bodhisattva's Hair: Pacifying the Date of Cutting Hair, tr. Lama Zopa Rinpoche, 2008",
        "FPMT",
        "https://fpmt.org/wp-content/uploads/teachers/zopa/advice/pdf/cutting_hair_advice_lzr08.pdf",
    )
    val RABTEN = Source("Tibetan Calendar 2026, Fire-Horse Year 2153", "Edition Rabten", "https://www.rabten.eu/downloads/calendarEN.pdf")
    val WHITE_BERYL_PEBBLES = Source(
        "Vaiḍūrya dkar po (the White Beryl), Sde srid Sangs rgyas rgya mtsho, ff. 248b–254a, with Lo chen Dharmaśrī's " +
            "Moonbeams, f. 28a/b; the four aspects ff. 156a/b, 158a",
        "BDRC W1KG12714",
        "https://library.bdrc.io/show/bdr:MW1KG12714",
    )
    val HENNING_SYMBOLS = Source("Symbolic details of the Kālacakra calendar", "Edward Henning", "http://www.kalacakra.org/calendar/symlst.htm")
    val HENNING_ARCHIVE = Source("Phugpa Tibetan calendar list", "Edward Henning", "http://www.kalacakra.org/calendar/tiblist.htm")
    val LOTSAWA_TENTH = Source(
        "A Prayer Invoking the Benefits of the Festival of the Tenth Day, by Rigdzin Jigme Lingpa, tr. Rigpa Translations, 2013",
        "Lotsawa House",
        "https://www.lotsawahouse.org/tibetan-masters/jigme-lingpa/benefits-of-the-tenth-day",
    )
}

object Texts {

    val ROKUYO: Map<Rokuyo, Reading> = keyed(
        Rokuyo.SENSHO to Reading(goodKeys = listOf("the_morning", "quick_decisions"), avoidKeys = listOf("the_afternoon"), source = Sources.TODAN_ROKUYO),
        Rokuyo.TOMOBIKI to Reading(goodKeys = listOf("celebrations", "morning_and_evening"), avoidKeys = listOf("noon", "funerals_custom"), source = Sources.TODAN_ROKUYO),
        Rokuyo.SENBU to Reading(goodKeys = listOf("the_afternoon", "calm"), avoidKeys = listOf("the_morning", "haste", "disputes"), source = Sources.TODAN_ROKUYO),
        Rokuyo.BUTSUMETSU to Reading(avoidKeys = listOf("new_beginnings", "weddings"), source = Sources.TODAN_ROKUYO),
        Rokuyo.TAIAN to Reading(goodKeys = listOf("travel", "moving_house", "marriage", "opening_a_shop", "everything"), source = Sources.TODAN_ROKUYO),
        Rokuyo.SHAKKO to Reading(goodKeys = listOf("around_noon"), avoidKeys = listOf("celebrations", "fire", "blades"), source = Sources.TODAN_ROKUYO),
    )

    val CHOKU: Map<Choku, Reading> = keyed(
        Choku.TATSU to Reading(goodKeys = listOf("everything"), avoidKeys = listOf("moving_earth", "boarding_ships"), source = Sources.TODAN_CHOKU),
        Choku.NOZOKU to Reading(goodKeys = listOf("throwing_things_away", "clearing_out"), source = Sources.TODAN_CHOKU),
        Choku.MITSU to Reading(goodKeys = listOf("shrine_rites", "building_a_house", "moving_house", "weddings", "opening_a_shop", "sowing", "moving_earth"), source = Sources.TODAN_CHOKU),
        Choku.TAIRA to Reading(goodKeys = listOf("consultations", "negotiations"), source = Sources.TODAN_CHOKU),
        Choku.SADAN to Reading(goodKeys = listOf("sowing", "weddings", "digging_wells", "fixing_decisions"), source = Sources.TODAN_CHOKU),
        Choku.TORU to Reading(goodKeys = listOf("harvesting", "buying", "acquiring_things"), source = Sources.TODAN_CHOKU),
        Choku.YABURU to Reading(avoidKeys = listOf("promises", "consultations", "agreements"), source = Sources.TODAN_CHOKU),
        Choku.AYAUSHI to Reading(avoidKeys = listOf("setting_out_on_journeys", "sea_travel"), source = Sources.TODAN_CHOKU),
        Choku.NARU to Reading(goodKeys = listOf("money_talks", "opening_a_shop", "announcements", "raising_pillars"), source = Sources.TODAN_CHOKU),
        Choku.OSAN to Reading(goodKeys = listOf("storing", "collecting"), source = Sources.TODAN_CHOKU),
        Choku.HIRAKU to Reading(goodKeys = listOf("starting_school", "opening_a_business", "beginnings"), source = Sources.TODAN_CHOKU),
        Choku.TOZU to Reading(avoidKeys = listOf("opening_a_shop", "beginnings"), source = Sources.TODAN_CHOKU),
    )

    val SHUKU: Map<Shuku, Reading> = keyed(
        Shuku.KAKU to Reading(goodKeys = listOf("weddings", "building", "digging_wells", "setting_out", "sewing"), avoidKeys = listOf("funerals", "interments"), source = Sources.TODAN_SHUKU),
        Shuku.KO to Reading(goodKeys = listOf("sowing", "betrothal_gifts", "sewing", "receiving_money"), avoidKeys = listOf("building"), source = Sources.TODAN_SHUKU),
        Shuku.TEI to Reading(goodKeys = listOf("taking_a_bride", "opening_gates", "building_stone_walls", "opening_a_shop"), avoidKeys = listOf("sewing", "first_wearing_of_new_clothes"), source = Sources.TODAN_SHUKU),
        Shuku.BO to Reading(goodKeys = listOf("sewing", "raising_the_ridgepole", "weddings", "building", "setting_up_a_branch_family", "retiring", "haircuts"), source = Sources.TODAN_SHUKU),
        Shuku.SHIN to Reading(goodKeys = listOf("shrine_rites", "memorial_services"), avoidKeys = listOf("building", "weddings", "funerals", "interments"), source = Sources.TODAN_SHUKU),
        Shuku.BI to Reading(goodKeys = listOf("starting_medicine", "weddings", "building", "opening_gates"), avoidKeys = listOf("opening_a_shop", "sewing"), source = Sources.TODAN_SHUKU),
        Shuku.KI to Reading(goodKeys = listOf("sewing", "first_wearing_of_new_clothes"), avoidKeys = listOf("funerals", "interments"), source = Sources.TODAN_SHUKU),
        Shuku.TO to Reading(goodKeys = listOf("digging_wells", "building_storehouses", "sewing", "building"), source = Sources.TODAN_SHUKU),
        Shuku.GYU to Reading(goodKeys = listOf("everything"), source = Sources.TODAN_SHUKU),
        Shuku.JO to Reading(avoidKeys = listOf("funerals", "sewing", "new_clothes", "building", "moving_house", "opening_a_shop"), source = Sources.TODAN_SHUKU),
        Shuku.KYO to Reading(avoidKeys = listOf("building", "marriage_talks", "funerals", "interments"), source = Sources.TODAN_SHUKU),
        Shuku.KIH to Reading(avoidKeys = listOf("weddings", "sewing", "driving_nails", "moving_house"), source = Sources.TODAN_SHUKU),
        Shuku.SHITSU to Reading(goodKeys = listOf("weddings", "taking_medicine", "haircuts", "raising_pillars", "building", "digging_wells"), avoidKeys = listOf("funerals", "interments"), source = Sources.TODAN_SHUKU),
        Shuku.HEKI to Reading(goodKeys = listOf("sewing", "new_clothes", "building_a_house", "funerals", "weddings"), avoidKeys = listOf("naming"), source = Sources.TODAN_SHUKU),
        Shuku.KEI to Reading(goodKeys = listOf("grafting", "felling_trees", "sewing"), avoidKeys = listOf("opening_a_shop_gate_or_storehouse"), source = Sources.TODAN_SHUKU),
        Shuku.RO to Reading(goodKeys = listOf("opening_gates", "sewing", "new_clothes", "building", "weddings"), source = Sources.TODAN_SHUKU),
        Shuku.I to Reading(goodKeys = listOf("building_a_house", "sewing", "money_talks", "helping_others"), avoidKeys = listOf("funerals"), source = Sources.TODAN_SHUKU),
        Shuku.BO2 to Reading(goodKeys = listOf("prayers_to_gods_and_buddhas", "shrines_and_altars", "memorial_services", "devotion"), source = Sources.TODAN_SHUKU),
        Shuku.HITSU to Reading(goodKeys = listOf("prayer", "building", "burial", "filling_holes", "building_storehouses", "turning_soil"), source = Sources.TODAN_SHUKU),
        Shuku.SHI to Reading(avoidKeys = listOf("weddings"), source = Sources.TODAN_SHUKU),
        Shuku.SHIN2 to Reading(goodKeys = listOf("putting_things_in_order", "making_things", "setting_out"), avoidKeys = listOf("funerals"), source = Sources.TODAN_SHUKU),
        Shuku.SEI to Reading(avoidKeys = listOf("funerals", "sewing"), source = Sources.TODAN_SHUKU),
        Shuku.KI2 to Reading(goodKeys = listOf("building_a_house", "sewing", "digging_wells", "everything"), source = Sources.TODAN_SHUKU),
        Shuku.RYU to Reading(goodKeys = listOf("sowing"), avoidKeys = listOf("funerals", "sewing", "raising_pillars"), source = Sources.TODAN_SHUKU),
        Shuku.SEI2 to Reading(avoidKeys = listOf("negotiations", "marriage", "starting_medicine", "funerals", "interments"), source = Sources.TODAN_SHUKU),
        Shuku.CHO to Reading(goodKeys = listOf("weddings", "sewing", "new_clothes", "opening_a_shop"), source = Sources.TODAN_SHUKU),
        Shuku.YOKU to Reading(goodKeys = listOf("cutting_grass"), avoidKeys = listOf("entrance_exams", "weddings", "negotiations"), source = Sources.TODAN_SHUKU),
        Shuku.SHIN3 to Reading(goodKeys = listOf("buying_land", "raising_the_ridgepole", "funerals", "weddings"), avoidKeys = listOf("sewing", "new_clothes"), source = Sources.TODAN_SHUKU),
    )

    val KYUSEI: Map<KyuSei, Reading> = KyuSei.entries.associateWith {
        Reading(key = "reading.KyuSei", arg = "reading.${glossKey(it)}", source = Sources.WP_KYUSEI, license = License.CC_BY_SA)
    }

    /**
     * The relation of one's birth star to the day star (九星気学). Relations from Japanese
     * Wikipedia 九星; the school from 九星気学.
     */
    val KIGAKU: Map<StarRelation, Reading> = StarRelation.entries.associateWith {
        Reading(
            key = "reading.StarRelation", arg = "reading.${glossKey(it)}",
            source = Sources.WP_KYUSEI, license = License.CC_BY_SA, also = listOf(Sources.WP_KIGAKU),
        )
    }

    val SENJITSU: Map<Senjitsu, Reading> = keyed(
        Senjitsu.TENSHA to Reading(goodKeys = listOf("everything"), source = Sources.WP_KAGEDAN, license = License.CC_BY_SA),
        Senjitsu.ICHIRYU_MANBAI to Reading(goodKeys = listOf("beginnings"), source = Sources.WP_ICHIRYU, license = License.CC_BY_SA),
        Senjitsu.DAIMYO to Reading(goodKeys = listOf("building", "moving_house", "travel"), source = Sources.WP_KAGEDAN, license = License.CC_BY_SA),
        Senjitsu.TENON to Reading(goodKeys = listOf("celebrations"), avoidKeys = listOf("mourning"), source = Sources.WP_KAGEDAN, license = License.CC_BY_SA),
        Senjitsu.BOSO to Reading(goodKeys = listOf("marriage", "building"), source = Sources.WP_KAGEDAN, license = License.CC_BY_SA),
        Senjitsu.SETTOKU to Reading(goodKeys = listOf("extending_a_house", "earthworks"), source = Sources.WP_KAGEDAN, license = License.CC_BY_SA),
        Senjitsu.KISHUKU to Reading(goodKeys = listOf("everything"), source = Sources.WP_KAGEDAN, license = License.CC_BY_SA),
        Senjitsu.TORA to Reading(avoidKeys = listOf("weddings"), source = Sources.KB_TORA),
        Senjitsu.MI to Reading(source = Sources.KB_MI),
        Senjitsu.TSUCHINOTO_MI to Reading(source = Sources.KB_MI),
        Senjitsu.KINOE_NE to Reading(source = Sources.KB_KINOENE),
        Senjitsu.TENICHI_TENJO to Reading(goodKeys = listOf("journeys_in_any_direction"), avoidKeys = listOf("an_unclean_house"), source = Sources.WP_TENICHI, license = License.CC_BY_SA),
        Senjitsu.JUSHI to Reading(avoidKeys = listOf("visiting_the_sick", "taking_medicine", "acupuncture", "travel"), source = Sources.WP_KAGEDAN, license = License.CC_BY_SA),
        Senjitsu.JISSHI to Reading(avoidKeys = listOf("everything"), source = Sources.WP_KAGEDAN, license = License.CC_BY_SA),
        Senjitsu.KIKO to Reading(avoidKeys = listOf("long_journeys", "coming_home", "moving_house", "bringing_in_a_bride", "lending_or_borrowing_money"), source = Sources.WP_KAGEDAN, license = License.CC_BY_SA),
        Senjitsu.CHIIMI to Reading(avoidKeys = listOf("acupuncture", "bloodshed", "hunting"), source = Sources.WP_KAGEDAN, license = License.CC_BY_SA),
        Senjitsu.TENKA to Reading(avoidKeys = listOf("raising_the_ridgepole", "roofing", "house_repairs", "moving_house"), source = Sources.WP_KAGEDAN, license = License.CC_BY_SA),
        Senjitsu.JIKA to Reading(avoidKeys = listOf("moving_earth", "laying_foundations", "raising_pillars", "digging_wells", "sowing", "building_graves", "funerals"), source = Sources.WP_KAGEDAN, license = License.CC_BY_SA),
        Senjitsu.OMO to Reading(avoidKeys = listOf("long_journeys", "taking_up_office", "moving_house", "weddings"), source = Sources.WP_KAGEDAN, license = License.CC_BY_SA),
        Senjitsu.FUJOJU to Reading(avoidKeys = listOf("weddings", "opening_a_shop", "naming_a_child", "moving_house", "contracts", "starting_lessons", "making_wishes"), source = Sources.WP_FUJOJU, license = License.CC_BY_SA),
        Senjitsu.SANRINBO to Reading(avoidKeys = listOf("raising_the_ridgepole", "breaking_ground", "any_building_work", "climbing_high"), source = Sources.WP_SANRINBO, license = License.CC_BY_SA),
        Senjitsu.JIPPOGURE to Reading(avoidKeys = listOf("new_ventures"), source = Sources.WP_JIPPO, license = License.CC_BY_SA),
        Senjitsu.HASSEN to Reading(source = Sources.WP_HASSEN, license = License.CC_BY_SA),
        Senjitsu.HASSEN_MABI to Reading(source = Sources.WP_HASSEN, license = License.CC_BY_SA),
        Senjitsu.OTSUCHI to Reading(avoidKeys = listOf("digging", "wells", "sowing", "earthworks", "felling_trees", "ground_breaking_rites"), source = Sources.WP_TSUCHI, license = License.CC_BY_SA),
        Senjitsu.KOTSUCHI to Reading(avoidKeys = listOf("digging", "wells", "sowing", "earthworks", "felling_trees", "ground_breaking_rites"), source = Sources.WP_TSUCHI, license = License.CC_BY_SA),
        Senjitsu.TSUCHI_MABI to Reading(source = Sources.WP_TSUCHI, license = License.CC_BY_SA),
        Senjitsu.SAIGEJIKI to Reading(avoidKeys = listOf("eating_and_drinking_heavily", "sowing", "opening_rice_bales", "planting"), source = Sources.WP_KAGEDAN, license = License.CC_BY_SA),
        Senjitsu.JUNICHI to Reading(avoidKeys = listOf("weddings", "funerals"), source = Sources.WP_KAGEDAN, license = License.CC_BY_SA),
        Senjitsu.FUKUNICHI to Reading(avoidKeys = listOf("weddings"), source = Sources.WP_KAGEDAN, license = License.CC_BY_SA),
        Senjitsu.KANOE_SARU to Reading(source = Sources.WP_KOSHIN, license = License.CC_BY_SA),
        Senjitsu.TAIKA to Reading(avoidKeys = listOf("careless_words", "house_repairs", "building_gates", "sea_voyages", "funerals"), source = Sources.WP_KAGEDAN, license = License.CC_BY_SA),
        Senjitsu.ROSHAKU to Reading(avoidKeys = listOf("everything"), source = Sources.WP_KAGEDAN, license = License.CC_BY_SA),
        Senjitsu.METSUMON to Reading(avoidKeys = listOf("everything"), source = Sources.WP_KAGEDAN, license = License.CC_BY_SA),
    )

    val ZASSETSU: Map<Zassetsu, Reading> = keyed(
        Zassetsu.SETSUBUN to Reading(source = Sources.NAOJ_ZASSETSU),
        Zassetsu.HIGAN_IRI to Reading(source = Sources.NAOJ_ZASSETSU),
        Zassetsu.HIGAN to Reading(source = Sources.NAOJ_ZASSETSU),
        Zassetsu.HIGAN_CHUNICHI to Reading(source = Sources.NAOJ_ZASSETSU),
        Zassetsu.HIGAN_AKE to Reading(source = Sources.NAOJ_ZASSETSU),
        Zassetsu.SHANICHI to Reading(source = Sources.NAOJ_ZASSETSU),
        Zassetsu.HACHIJUHACHIYA to Reading(source = Sources.NAOJ_ZASSETSU),
        Zassetsu.NYUBAI to Reading(source = Sources.NAOJ_ZASSETSU),
        Zassetsu.HANGESHO to Reading(source = Sources.NAOJ_ZASSETSU),
        Zassetsu.DOYO_IRI to Reading(source = Sources.NAOJ_ZASSETSU),
        Zassetsu.DOYO to Reading(source = Sources.NAOJ_ZASSETSU),
        Zassetsu.DOYO_USHI to Reading(source = Sources.NAOJ_ZASSETSU),
        Zassetsu.NIHYAKUTOKA to Reading(source = Sources.NAOJ_ZASSETSU),
        Zassetsu.NIHYAKUHATSUKA to Reading(source = Sources.NAOJ_ZASSETSU),
    )

    val EHOU: Map<Ehou, Reading> = Ehou.entries.associateWith {
        Reading(key = "reading.Ehou", source = Sources.WP_TOSHITOKU, license = License.CC_BY_SA)
    }

    /** Lunar days on which cutting one's hair brings a good result, per the same source. */
    val HAIRCUT_GOOD: Set<Int> = setOf(3, 4, 5, 8, 9, 10, 11, 13, 14, 15, 18, 19, 22, 23, 26, 27)

    /** Result of cutting one's hair on each lunar day, 1–30: the result alone under `reading.Haircut.<day>`. */
    val HAIRCUT: List<Reading> = (1..30).map { Reading(key = "reading.Haircut", arg = "reading.Haircut.$it", source = Sources.FPMT_HAIR) }

    val ELEMENT_PAIR: Map<ElementPair, Reading> = keyed(
        ElementPair.EARTH_EARTH to Reading(source = Sources.RABTEN),
        ElementPair.WATER_WATER to Reading(source = Sources.RABTEN),
        ElementPair.EARTH_WATER to Reading(source = Sources.RABTEN),
        ElementPair.FIRE_FIRE to Reading(source = Sources.RABTEN),
        ElementPair.WIND_WIND to Reading(source = Sources.RABTEN),
        ElementPair.FIRE_WIND to Reading(source = Sources.RABTEN),
        ElementPair.EARTH_WIND to Reading(source = Sources.RABTEN),
        ElementPair.WATER_WIND to Reading(source = Sources.RABTEN),
        ElementPair.EARTH_FIRE to Reading(source = Sources.RABTEN),
        ElementPair.FIRE_WATER to Reading(source = Sources.RABTEN),
    )

    val SPECIAL_DAY: Map<SpecialDay, Reading> = keyed(
        SpecialDay.EIGHTH to Reading(source = Sources.RABTEN),
        SpecialDay.TENTH to Reading(source = Sources.LOTSAWA_TENTH),
        SpecialDay.FULL_MOON to Reading(source = Sources.RABTEN),
        SpecialDay.TWENTY_FIFTH to Reading(source = Sources.RABTEN),
        SpecialDay.NEW_MOON to Reading(source = Sources.RABTEN),
    )

    val TIBETAN_FESTIVAL: Map<TibetanFestival, Reading> = keyed(
        TibetanFestival.LOSAR to Reading(source = Sources.RABTEN),
        TibetanFestival.CHOTRUL_DUCHEN to Reading(source = Sources.RABTEN),
        TibetanFestival.KALACAKRA to Reading(source = Sources.HENNING_ARCHIVE),
        TibetanFestival.BIRTH to Reading(source = Sources.HENNING_ARCHIVE),
        TibetanFestival.SAGA_DAWA_DUCHEN to Reading(source = Sources.RABTEN),
        TibetanFestival.ZAMLING_CHISANG to Reading(source = Sources.RABTEN),
        TibetanFestival.CHOKHOR_DUCHEN to Reading(source = Sources.RABTEN),
        TibetanFestival.ENTRY_INTO_WOMB to Reading(source = Sources.HENNING_ARCHIVE),
        TibetanFestival.LHABAB_DUCHEN to Reading(source = Sources.RABTEN),
        TibetanFestival.GADEN_NGAMCHO to Reading(source = Sources.RABTEN),
        TibetanFestival.SANGPO_CHUZOM to Reading(source = Sources.RABTEN),
        TibetanFestival.PROTECTORS to Reading(source = Sources.RABTEN),
    )

    /** Kyūreki festivals, by their kanji as in [zanshin.core.kyureki.Festival]. */
    val JAPANESE_FESTIVAL: Map<String, Reading> = mapOf(
        "旧正月" to Reading(source = Sources.NAOJ_SEKKU),
        "人日の節句" to Reading(source = Sources.NAOJ_SEKKU),
        "上巳の節句" to Reading(source = Sources.NAOJ_SEKKU),
        "端午の節句" to Reading(source = Sources.NAOJ_SEKKU),
        "七夕" to Reading(source = Sources.NAOJ_SEKKU),
        "十五夜" to Reading(source = Sources.NAOJ_JUSANYA),
        "重陽の節句" to Reading(source = Sources.NAOJ_SEKKU),
        "十三夜" to Reading(source = Sources.NAOJ_JUSANYA),
    ).mapValues { (kanji, r) -> r.copy(key = "reading.Festival.$kanji") }

    val PERSONAL_DAY: Map<PersonalDay, Reading> = keyed(
        PersonalDay.LUCK to Reading(source = Sources.RABTEN),
        PersonalDay.LIFE to Reading(source = Sources.RABTEN),
        PersonalDay.ANTI to Reading(source = Sources.RABTEN),
    )

    /**
     * The yearly pebble reading: one aspect of the birth year against the same
     * aspect of the present year. The ranking and the predictions for the
     * vitality pebbles follow the White Beryl's chapter on obstacle years.
     */
    val PEBBLES: Map<Kinship, Reading> = Kinship.entries.associateWith {
        Reading(key = "reading.Kinship", arg = "reading.${glossKey(it)}", source = Sources.WHITE_BERYL_PEBBLES)
    }
}
