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
import zanshin.core.tibetan.PersonalDay
import zanshin.core.tibetan.SpecialDay
import zanshin.core.tibetan.TibetanFestival

/*
 * Readings shown on demand (SPEC §8). Each has a published source and a
 * licence. The default is this project's own English statement of what the
 * source says (MPL-2.0); wording adapted from Japanese Wikipedia is CC BY-SA
 * 4.0. Nothing under a non-commercial licence: F-Droid would flag it as a
 * non-free asset.
 */

enum class License(val label: String) {
    OWN("English summary of the cited source by the Zanshin Calendar authors, MPL-2.0"),
    CC_BY_SA("Adapted from Japanese Wikipedia, CC BY-SA 4.0"),
}

data class Source(val title: String, val publisher: String, val url: String)

data class Reading(
    val summary: String,
    val good: List<String> = emptyList(),
    val avoid: List<String> = emptyList(),
    val source: Source,
    val license: License = License.OWN,
    /** Further sources for parts of the summary, under the same licence. */
    val also: List<Source> = emptyList(),
)

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
    val HENNING_SYMBOLS = Source("Symbolic details of the Kālacakra calendar", "Edward Henning", "http://www.kalacakra.org/calendar/symlst.htm")
    val HENNING_ARCHIVE = Source("Phugpa Tibetan calendar list", "Edward Henning", "http://www.kalacakra.org/calendar/tiblist.htm")
    val LOTSAWA_TENTH = Source(
        "A Prayer Invoking the Benefits of the Festival of the Tenth Day, by Rigdzin Jigme Lingpa, tr. Rigpa Translations, 2013",
        "Lotsawa House",
        "https://www.lotsawahouse.org/tibetan-masters/jigme-lingpa/benefits-of-the-tenth-day",
    )
}

object Texts {

    val ROKUYO: Map<Rokuyo, Reading> = mapOf(
        Rokuyo.SENSHO to Reading(
            "Act early: the morning is lucky, the afternoon unlucky. Taken as good for urgent business and lawsuits.",
            good = listOf("the morning", "quick decisions"), avoid = listOf("the afternoon"), source = Sources.TODAN_ROKUYO,
        ),
        Rokuyo.TOMOBIKI to Reading(
            "A draw: neither side wins. Good for celebrations; lucky in the morning and evening, unlucky at noon. " +
                "Funerals are customarily avoided, a folk belief from the similar-sounding 友曳.",
            good = listOf("celebrations", "morning and evening"), avoid = listOf("noon", "funerals (custom)"), source = Sources.TODAN_ROKUYO,
        ),
        Rokuyo.SENBU to Reading(
            "Wait quietly and avoid haste: the morning is unlucky, the afternoon lucky. Disputes and urgent matters are best avoided.",
            good = listOf("the afternoon", "calm"), avoid = listOf("the morning", "haste", "disputes"), source = Sources.TODAN_ROKUYO,
        ),
        Rokuyo.BUTSUMETSU to Reading(
            "Nothing comes to fruition; the worst of the six, and weddings are avoided. Despite the name it has nothing to do " +
                "with Buddhism: the old name meant \"empty\".",
            avoid = listOf("new beginnings", "weddings"), source = Sources.TODAN_ROKUYO,
        ),
        Rokuyo.TAIAN to Reading(
            "Great peace: success in everything — travel, moving house, marriage, opening a shop.",
            good = listOf("travel", "moving house", "marriage", "opening a shop", "everything"), source = Sources.TODAN_ROKUYO,
        ),
        Rokuyo.SHAKKO to Reading(
            "Unlucky for celebrations; only the hour of the Horse, about 11:00–13:00, is lucky. Take care with fire and blades.",
            good = listOf("around noon"), avoid = listOf("celebrations", "fire", "blades"), source = Sources.TODAN_ROKUYO,
        ),
    )

    val CHOKU: Map<Choku, Reading> = mapOf(
        Choku.TATSU to Reading("Very lucky, good for everything.", good = listOf("everything"), avoid = listOf("moving earth", "boarding ships"), source = Sources.TODAN_CHOKU),
        Choku.NOZOKU to Reading("A day to ward off what is bad.", good = listOf("throwing things away", "clearing out"), source = Sources.TODAN_CHOKU),
        Choku.MITSU to Reading("A full day.", good = listOf("shrine rites", "building a house", "moving house", "weddings", "opening a shop", "sowing", "moving earth"), source = Sources.TODAN_CHOKU),
        Choku.TAIRA to Reading("Good and bad are level; very good for talks.", good = listOf("consultations", "negotiations"), source = Sources.TODAN_CHOKU),
        Choku.SADAN to Reading("A day for settling things.", good = listOf("sowing", "weddings", "digging wells", "fixing decisions"), source = Sources.TODAN_CHOKU),
        Choku.TORU to Reading("A day for taking things in.", good = listOf("harvesting", "buying", "acquiring things"), source = Sources.TODAN_CHOKU),
        Choku.YABURU to Reading("A breaking day.", avoid = listOf("promises", "consultations", "agreements"), source = Sources.TODAN_CHOKU),
        Choku.AYAUSHI to Reading("A precarious day.", avoid = listOf("setting out on journeys", "sea travel"), source = Sources.TODAN_CHOKU),
        Choku.NARU to Reading("A day of completion.", good = listOf("money talks", "opening a shop", "announcements", "raising pillars"), source = Sources.TODAN_CHOKU),
        Choku.OSAN to Reading("A day of gathering in; no obstacle to ordinary affairs.", good = listOf("storing", "collecting"), source = Sources.TODAN_CHOKU),
        Choku.HIRAKU to Reading("An opening day: what you begin finds its way.", good = listOf("starting school", "opening a business", "beginnings"), source = Sources.TODAN_CHOKU),
        Choku.TOZU to Reading("A closing day, bad for most things.", avoid = listOf("opening a shop", "beginnings"), source = Sources.TODAN_CHOKU),
    )

    val SHUKU: Map<Shuku, Reading> = mapOf(
        Shuku.KAKU to Reading("", good = listOf("weddings", "building", "digging wells", "setting out", "sewing"), avoid = listOf("funerals", "interments"), source = Sources.TODAN_SHUKU),
        Shuku.KO to Reading("", good = listOf("sowing", "betrothal gifts", "sewing", "receiving money"), avoid = listOf("building"), source = Sources.TODAN_SHUKU),
        Shuku.TEI to Reading("", good = listOf("taking a bride", "opening gates", "building stone walls", "opening a shop"), avoid = listOf("sewing", "first wearing of new clothes"), source = Sources.TODAN_SHUKU),
        Shuku.BO to Reading("Very lucky.", good = listOf("sewing", "raising the ridgepole", "weddings", "building", "setting up a branch family", "retiring", "haircuts"), source = Sources.TODAN_SHUKU),
        Shuku.SHIN to Reading("", good = listOf("shrine rites", "memorial services"), avoid = listOf("building", "weddings", "funerals", "interments"), source = Sources.TODAN_SHUKU),
        Shuku.BI to Reading("", good = listOf("starting medicine", "weddings", "building", "opening gates"), avoid = listOf("opening a shop", "sewing"), source = Sources.TODAN_SHUKU),
        Shuku.KI to Reading("Funerals and interments bring great misfortune.", good = listOf("sewing", "first wearing of new clothes"), avoid = listOf("funerals", "interments"), source = Sources.TODAN_SHUKU),
        Shuku.TO to Reading("Wells and storehouses built today fill with treasure.", good = listOf("digging wells", "building storehouses", "sewing", "building"), source = Sources.TODAN_SHUKU),
        Shuku.GYU to Reading("Good for everything.", good = listOf("everything"), source = Sources.TODAN_SHUKU),
        Shuku.JO to Reading("A bad day.", avoid = listOf("funerals", "sewing", "new clothes", "building", "moving house", "opening a shop"), source = Sources.TODAN_SHUKU),
        Shuku.KYO to Reading("Misfortune follows at once.", avoid = listOf("building", "marriage talks", "funerals", "interments"), source = Sources.TODAN_SHUKU),
        Shuku.KIH to Reading("A very bad day: these bring poverty.", avoid = listOf("weddings", "sewing", "driving nails", "moving house"), source = Sources.TODAN_SHUKU),
        Shuku.SHITSU to Reading("", good = listOf("weddings", "taking medicine", "haircuts", "raising pillars", "building", "digging wells"), avoid = listOf("funerals", "interments"), source = Sources.TODAN_SHUKU),
        Shuku.HEKI to Reading("", good = listOf("sewing", "new clothes", "building a house", "funerals", "weddings"), avoid = listOf("naming"), source = Sources.TODAN_SHUKU),
        Shuku.KEI to Reading("", good = listOf("grafting", "felling trees", "sewing"), avoid = listOf("opening a shop, gate or storehouse"), source = Sources.TODAN_SHUKU),
        Shuku.RO to Reading("Very good for weddings.", good = listOf("opening gates", "sewing", "new clothes", "building", "weddings"), source = Sources.TODAN_SHUKU),
        Shuku.I to Reading("Funerals are especially to be avoided.", good = listOf("building a house", "sewing", "money talks", "helping others"), avoid = listOf("funerals"), source = Sources.TODAN_SHUKU),
        Shuku.BO2 to Reading("", good = listOf("prayers to gods and buddhas", "shrines and altars", "memorial services", "devotion"), source = Sources.TODAN_SHUKU),
        Shuku.HITSU to Reading("", good = listOf("prayer", "building", "burial", "filling holes", "building storehouses", "turning soil"), source = Sources.TODAN_SHUKU),
        Shuku.SHI to Reading("A bad day: a wedding scatters wealth and brings illness. Be restrained.", avoid = listOf("weddings"), source = Sources.TODAN_SHUKU),
        Shuku.SHIN2 to Reading("", good = listOf("putting things in order", "making things", "setting out"), avoid = listOf("funerals"), source = Sources.TODAN_SHUKU),
        Shuku.SEI to Reading("A calm, lucky day.", avoid = listOf("funerals", "sewing"), source = Sources.TODAN_SHUKU),
        Shuku.KI2 to Reading("A very lucky day, good for everything.", good = listOf("building a house", "sewing", "digging wells", "everything"), source = Sources.TODAN_SHUKU),
        Shuku.RYU to Reading("A funeral brings seven misfortunes.", good = listOf("sowing"), avoid = listOf("funerals", "sewing", "raising pillars"), source = Sources.TODAN_SHUKU),
        Shuku.SEI2 to Reading("A very bad day for these.", avoid = listOf("negotiations", "marriage", "starting medicine", "funerals", "interments"), source = Sources.TODAN_SHUKU),
        Shuku.CHO to Reading("These lay the foundation of the family's prosperity.", good = listOf("weddings", "sewing", "new clothes", "opening a shop"), source = Sources.TODAN_SHUKU),
        Shuku.YOKU to Reading("A bad day.", good = listOf("cutting grass"), avoid = listOf("entrance exams", "weddings", "negotiations"), source = Sources.TODAN_SHUKU),
        Shuku.SHIN3 to Reading("", good = listOf("buying land", "raising the ridgepole", "funerals", "weddings"), avoid = listOf("sewing", "new clothes"), source = Sources.TODAN_SHUKU),
    )

    val KYUSEI: Map<KyuSei, Reading> = listOf(
        "Water; north; trigram 坎 (water); white.",
        "Earth; southwest; trigram 坤 (earth); black.",
        "Wood; east; trigram 震 (thunder); blue.",
        "Wood; southeast; trigram 巽 (wind); green.",
        "Earth; centre; 太極 (the supreme ultimate); yellow.",
        "Metal; northwest; trigram 乾 (heaven); white.",
        "Metal; west; trigram 兌 (lake); red.",
        "Earth; northeast; trigram 艮 (mountain); white.",
        "Fire; south; trigram 離 (fire); purple.",
    ).mapIndexed { i, s ->
        KyuSei.entries[i] to Reading(
            "Element, direction, trigram and colour: $s Used in Japanese onmyōdō to read fortunes and directions; the " +
                "stars have nothing to do with planets.",
            source = Sources.WP_KYUSEI, license = License.CC_BY_SA,
        )
    }.toMap()

    /**
     * The relation of one's birth star to the day star (九星気学). Relations from Japanese
     * Wikipedia 九星; the school from 九星気学.
     */
    val KIGAKU: Map<StarRelation, Reading> = mapOf(
        StarRelation.SOSHO to "相生: the element of one star feeds the other's, as metal feeds water. Read against a person's years, months and days, this counts as good.",
        StarRelation.HIWA to "比和: both stars have the same element. Read against a person's years, months and days, this counts as good.",
        StarRelation.SOKOKU to "相剋: the element of one star overcomes the other's, as earth overcomes water. Read against a person's years, months and days, this counts as bad.",
    ).mapValues { (_, s) ->
        Reading(
            "$s Your birth star (本命星) is the year star of your birth year, counted from 立春. This reading belongs to " +
                "九星気学, fortune-telling by the nine stars, gathered as 気学 in 1909; it is not an " +
                "annotation of the historical almanac.",
            source = Sources.WP_KYUSEI, license = License.CC_BY_SA, also = listOf(Sources.WP_KIGAKU),
        )
    }

    val SENJITSU: Map<Senjitsu, Reading> = mapOf(
        Senjitsu.TENSHA to Reading("The hundred gods rise to heaven and heaven forgives all wrongs: the best day of the year, noted as \"good for all\". Five or six times a year.", good = listOf("everything"), source = Sources.WP_KAGEDAN, license = License.CC_BY_SA),
        Senjitsu.ICHIRYU_MANBAI to Reading("A single grain grows into ten thousand: taken as good for beginnings. A Japanese selection without a classical Chinese source; its credibility is low.", good = listOf("beginnings"), source = Sources.WP_ICHIRYU, license = License.CC_BY_SA),
        Senjitsu.DAIMYO to Reading("Heaven and earth open and the sun reaches every corner: very good for all good deeds.", good = listOf("building", "moving house", "travel"), source = Sources.WP_KAGEDAN, license = License.CC_BY_SA),
        Senjitsu.TENON to Reading("A day of heaven's grace: very good for happy occasions, not to be used for sad ones.", good = listOf("celebrations"), avoid = listOf("mourning"), source = Sources.WP_KAGEDAN, license = License.CC_BY_SA),
        Senjitsu.BOSO to Reading("Heaven cherishes people as a mother her child: good for everything, marriage especially.", good = listOf("marriage", "building"), source = Sources.WP_KAGEDAN, license = License.CC_BY_SA),
        Senjitsu.SETTOKU to Reading("Good for work that touches the earth, such as extending or rebuilding a house.", good = listOf("extending a house", "earthworks"), source = Sources.WP_KAGEDAN, license = License.CC_BY_SA),
        Senjitsu.KISHUKU to Reading("The day of the lodge 鬼, which the almanac counts very lucky.", good = listOf("everything"), source = Sources.WP_KAGEDAN, license = License.CC_BY_SA),
        Senjitsu.TORA to Reading("The Tiger day of the twelve-day cycle. A tiger goes a thousand ri and comes back a thousand ri, so weddings were avoided. The festival day of Bishamonten.", avoid = listOf("weddings"), source = Sources.KB_TORA),
        Senjitsu.MI to Reading("The Snake day of the twelve-day cycle.", source = Sources.KB_MI),
        Senjitsu.TSUCHINOTO_MI to Reading("The festival day of Benzaiten, once every sixty days.", source = Sources.KB_MI),
        Senjitsu.KINOE_NE to Reading("The first of the sixty days and the festival day of Daikokuten. On 甲子待 people stayed up until the hour of the Rat and offered soybeans, black beans and forked radish for worldly fortune.", source = Sources.KB_KINOENE),
        Senjitsu.TENICHI_TENJO to Reading("For these sixteen days the direction god Ten'ichi-jin is back in heaven, so no direction is blocked and any journey is fine. Nichiyū-jin stays in the house instead: keep it clean.", good = listOf("journeys in any direction"), avoid = listOf("an unclean house"), source = Sources.WP_TENICHI, license = License.CC_BY_SA),
        Senjitsu.JUSHI to Reading("The black day, the worst of all: no other annotation need be read. An illness begun today is said to be fatal. Funerals alone are not affected.", avoid = listOf("visiting the sick", "taking medicine", "acupuncture", "travel"), source = Sources.WP_KAGEDAN, license = License.CC_BY_SA),
        Senjitsu.JISSHI to Reading("Next after the black day: bad for everything, though funerals are not affected.", avoid = listOf("everything"), source = Sources.WP_KAGEDAN, license = License.CC_BY_SA),
        Senjitsu.KIKO to Reading("The spirit of the star Tianbang stands at the door and keeps people from coming home.", avoid = listOf("long journeys", "coming home", "moving house", "bringing in a bride", "lending or borrowing money"), source = Sources.WP_KAGEDAN, license = License.CC_BY_SA),
        Senjitsu.CHIIMI to Reading("Seeing blood is unlucky.", avoid = listOf("acupuncture", "bloodshed", "hunting"), source = Sources.WP_KAGEDAN, license = License.CC_BY_SA),
        Senjitsu.TENKA to Reading("Heaven's fire is fierce: raising a roof today is said to bring fire.", avoid = listOf("raising the ridgepole", "roofing", "house repairs", "moving house"), source = Sources.WP_KAGEDAN, license = License.CC_BY_SA),
        Senjitsu.JIKA to Reading("The earth's fire is fierce.", avoid = listOf("moving earth", "laying foundations", "raising pillars", "digging wells", "sowing", "building graves", "funerals"), source = Sources.WP_KAGEDAN, license = License.CC_BY_SA),
        Senjitsu.OMO to Reading("\"Go and perish\": once the day armies did not march.", avoid = listOf("long journeys", "taking up office", "moving house", "weddings"), source = Sources.WP_KAGEDAN, license = License.CC_BY_SA),
        Senjitsu.FUJOJU to Reading("Nothing is accomplished: starting anything is unlucky.", avoid = listOf("weddings", "opening a shop", "naming a child", "moving house", "contracts", "starting lessons", "making wishes"), source = Sources.WP_FUJOJU, license = License.CC_BY_SA),
        Senjitsu.SANRINBO to Reading("Building today is said to ruin three neighbours. Some almanacs add that climbing high brings injury.", avoid = listOf("raising the ridgepole", "breaking ground", "any building work", "climbing high"), source = Sources.WP_SANRINBO, license = License.CC_BY_SA),
        Senjitsu.JIPPOGURE to Reading("Ten days when the energies of heaven and earth clash and nothing goes well: much toil, little result.", avoid = listOf("new ventures"), source = Sources.WP_JIPPO, license = License.CC_BY_SA),
        Senjitsu.HASSEN to Reading("Twelve days in which eight have stem and branch of the same element: good grows better and bad grows worse, and later almanacs stressed only the bad.", source = Sources.WP_HASSEN, license = License.CC_BY_SA),
        Senjitsu.HASSEN_MABI to Reading("One of the four days within 八専 whose stem and branch do not share an element: free of its influence.", source = Sources.WP_HASSEN, license = License.CC_BY_SA),
        Senjitsu.OTSUCHI to Reading("The earth god Dokujin is in the ground: do not disturb the soil.", avoid = listOf("digging", "wells", "sowing", "earthworks", "felling trees", "ground-breaking rites"), source = Sources.WP_TSUCHI, license = License.CC_BY_SA),
        Senjitsu.KOTSUCHI to Reading("The earth god Dokujin is in the ground: do not disturb the soil.", avoid = listOf("digging", "wells", "sowing", "earthworks", "felling trees", "ground-breaking rites"), source = Sources.WP_TSUCHI, license = License.CC_BY_SA),
        Senjitsu.TSUCHI_MABI to Reading("The day between the great and lesser earth taboos, without their restrictions.", source = Sources.WP_TSUCHI, license = License.CC_BY_SA),
        Senjitsu.SAIGEJIKI to Reading("The star spirit Tenkō comes down to eat; what people eat loses its strength. A light bad day.", avoid = listOf("eating and drinking heavily", "sowing", "opening rice bales", "planting"), source = Sources.WP_KAGEDAN, license = License.CC_BY_SA),
        Senjitsu.JUNICHI to Reading("What is done today happens again: good for good things, bad for bad ones. Weddings are avoided, as they would repeat.", avoid = listOf("weddings", "funerals"), source = Sources.WP_KAGEDAN, license = License.CC_BY_SA),
        Senjitsu.FUKUNICHI to Reading("Good deeds today are doubled and so are bad ones. Weddings are avoided, as they would repeat.", avoid = listOf("weddings"), source = Sources.WP_KAGEDAN, license = License.CC_BY_SA),
        Senjitsu.KANOE_SARU to Reading("Kōshin night: the three worms in the body rise to heaven while one sleeps and report one's misdeeds, so people stayed awake together (庚申待).", source = Sources.WP_KOSHIN, license = License.CC_BY_SA),
        Senjitsu.TAIKA to Reading("The worst of the three bad days for your birth year.", avoid = listOf("careless words", "house repairs", "building gates", "sea voyages", "funerals"), source = Sources.WP_KAGEDAN, license = License.CC_BY_SA),
        Senjitsu.ROSHAKU to Reading("One of the three bad days for your birth year: everything is said to fail.", avoid = listOf("everything"), source = Sources.WP_KAGEDAN, license = License.CC_BY_SA),
        Senjitsu.METSUMON to Reading("One of the three bad days for your birth year: said to ruin a whole house.", avoid = listOf("everything"), source = Sources.WP_KAGEDAN, license = License.CC_BY_SA),
    )

    val ZASSETSU: Map<Zassetsu, Reading> = mapOf(
        Zassetsu.SETSUBUN to Reading("The day before 立春. Once the eve of every season, now only of spring; the bean-throwing comes from the Chinese exorcism 追儺.", source = Sources.NAOJ_ZASSETSU),
        Zassetsu.HIGAN_IRI to Reading("First day of the seven days centred on the equinox.", source = Sources.NAOJ_ZASSETSU),
        Zassetsu.HIGAN to Reading("Within the seven days centred on the equinox.", source = Sources.NAOJ_ZASSETSU),
        Zassetsu.HIGAN_CHUNICHI to Reading("The equinox itself, middle day of higan.", source = Sources.NAOJ_ZASSETSU),
        Zassetsu.HIGAN_AKE to Reading("Last day of higan.", source = Sources.NAOJ_ZASSETSU),
        Zassetsu.SHANICHI to Reading("The 戊 day nearest the equinox, dedicated to the earth deity. Dropped from the official ephemeris after the war.", source = Sources.NAOJ_ZASSETSU),
        Zassetsu.HACHIJUHACHIYA to Reading("The 88th day from 立春: the end of the frost season — beware the late frost.", source = Sources.NAOJ_ZASSETSU),
        Zassetsu.NYUBAI to Reading("The sun at longitude 80°: the calendar start of the rainy season.", source = Sources.NAOJ_ZASSETSU),
        Zassetsu.HANGESHO to Reading("The sun at longitude 100°.", source = Sources.NAOJ_ZASSETSU),
        Zassetsu.DOYO_IRI to Reading("Start of the eighteen or so days before a season begins, which five-element theory gives to earth.", source = Sources.NAOJ_ZASSETSU),
        Zassetsu.DOYO to Reading("Within the earth season before a new season begins.", source = Sources.NAOJ_ZASSETSU),
        Zassetsu.DOYO_USHI to Reading("An Ox day within doyō.", source = Sources.NAOJ_ZASSETSU),
        Zassetsu.NIHYAKUTOKA to Reading("The 210th day from 立春, when rice is in flower and typhoons approach.", source = Sources.NAOJ_ZASSETSU),
        Zassetsu.NIHYAKUHATSUKA to Reading("The 220th day from 立春, still in the typhoon season.", source = Sources.NAOJ_ZASSETSU),
    )

    val EHOU: Map<Ehou, Reading> = Ehou.entries.associateWith {
        Reading(
            "Where 歳徳神, the god of the year's fortune, resides. Whatever is done facing this direction is lucky.",
            source = Sources.WP_TOSHITOKU, license = License.CC_BY_SA,
        )
    }

    /** Lunar days on which cutting one's hair brings a good result, per the same source. */
    val HAIRCUT_GOOD: Set<Int> = setOf(3, 4, 5, 8, 9, 10, 11, 13, 14, 15, 18, 19, 22, 23, 26, 27)

    /** Result of cutting one's hair on each lunar day, 1–30. */
    val HAIRCUT: List<Reading> = listOf(
        "Short life", "Many illnesses", "Wealth will come", "A good complexion", "Possessions increase",
        "A lawsuit", "Complexion fades", "Long life", "Meeting youthful people", "Great power",
        "Sharp intelligence", "Danger to life", "Good for all beings", "Wealth", "Auspicious",
        "Thirst", "Flesh turns blue", "Receiving possessions", "Meeting helpful people", "Hunger and thirst",
        "Illness", "Finding food and water", "Things go well", "Eye pain", "Contagious illness",
        "Lasting happiness", "Virtue increases", "Fights and quarrels", "One's life force wanders",
        "Meeting the dead reborn as spirits in human form",
    ).map { Reading("Cutting hair today: $it.", source = Sources.FPMT_HAIR) }

    val ELEMENT_PAIR: Map<ElementPair, Reading> = mapOf(
        ElementPair.EARTH_EARTH to Reading("Earth meets earth: power, and with power every wish is achieved.", source = Sources.RABTEN),
        ElementPair.WATER_WATER to Reading("Water meets water: nectar, which increases life's force.", source = Sources.RABTEN),
        ElementPair.EARTH_WATER to Reading("Earth meets water: youth, which brings great happiness.", source = Sources.RABTEN),
        ElementPair.FIRE_FIRE to Reading("Fire meets fire: increase of food and wealth.", source = Sources.RABTEN),
        ElementPair.WIND_WIND to Reading("Wind meets wind: perfection, and quick accomplishment of wishes.", source = Sources.RABTEN),
        ElementPair.FIRE_WIND to Reading("Fire meets wind: strength, which brings all good omens.", source = Sources.RABTEN),
        ElementPair.EARTH_WIND to Reading("Earth meets wind: incompatibility, which exhausts food and wealth.", source = Sources.RABTEN),
        ElementPair.WATER_WIND to Reading("Water meets wind: disharmony, which separates friends.", source = Sources.RABTEN),
        ElementPair.EARTH_FIRE to Reading("Earth meets fire: burning, which creates suffering.", source = Sources.RABTEN),
        ElementPair.FIRE_WATER to Reading("Fire meets water: death, which robs life away.", source = Sources.RABTEN),
    )

    val SPECIAL_DAY: Map<SpecialDay, Reading> = mapOf(
        SpecialDay.EIGHTH to Reading("A special day for any wholesome action; recommended for lay practitioners to take the eight precepts.", source = Sources.RABTEN),
        SpecialDay.TENTH to Reading(
            "Guru Rinpoche promised to come to Tibet on every tenth day, so each tenth day is a festival of his enlightened activity, " +
                "kept with tsok offerings and guru pūjā (after Jigme Lingpa's prayer on the benefits of the tenth day).",
            source = Sources.LOTSAWA_TENTH,
        ),
        SpecialDay.FULL_MOON to Reading("Full moon: a special day for any wholesome action, and a Sojong day of monastic confession.", source = Sources.RABTEN),
        SpecialDay.TWENTY_FIFTH to Reading("A day for tsok offerings, especially recommended for guru pūjā.", source = Sources.RABTEN),
        SpecialDay.NEW_MOON to Reading("New moon: a special day for any wholesome action, and a Sojong day of monastic confession.", source = Sources.RABTEN),
    )

    val TIBETAN_FESTIVAL: Map<TibetanFestival, Reading> = mapOf(
        TibetanFestival.LOSAR to Reading("The first day of the Tibetan year, celebrated in every auspicious and joyous way; the first fifteen days commemorate the Buddha's miracles.", source = Sources.RABTEN),
        TibetanFestival.CHOTRUL_DUCHEN to Reading("The day of Buddha Śākyamuni's great miracles. Positive and negative actions on such days multiply.", source = Sources.RABTEN),
        TibetanFestival.KALACAKRA to Reading("The revelation of the Kālacakra Tantra.", source = Sources.HENNING_ARCHIVE),
        TibetanFestival.BIRTH to Reading("The birth of the Buddha.", source = Sources.HENNING_ARCHIVE),
        TibetanFestival.SAGA_DAWA_DUCHEN to Reading("The full moon of Saga Dawa, the fourth month: the day on which the Buddha took birth, attained enlightenment and passed into parinirvāṇa.", source = Sources.RABTEN),
        TibetanFestival.ZAMLING_CHISANG to Reading("The universal smoke offering (sang) to all the protectors.", source = Sources.RABTEN),
        TibetanFestival.CHOKHOR_DUCHEN to Reading("Buddha Śākyamuni turns the wheel of Dharma for the first time.", source = Sources.RABTEN),
        TibetanFestival.ENTRY_INTO_WOMB to Reading("The Buddha's entry into the womb of his mother.", source = Sources.HENNING_ARCHIVE),
        TibetanFestival.LHABAB_DUCHEN to Reading("Buddha Śākyamuni returns from the realm of the gods.", source = Sources.RABTEN),
        TibetanFestival.GADEN_NGAMCHO to Reading("The parinirvāṇa of Je Tsongkhapa.", source = Sources.RABTEN),
        TibetanFestival.SANGPO_CHUZOM to Reading("The day of the ten good omens: for turning inauspicious circumstances into auspicious ones, and for merrymaking.", source = Sources.RABTEN),
        TibetanFestival.PROTECTORS to Reading("Thanksgiving to the Dharma protectors at the close of the year.", source = Sources.RABTEN),
    )

    /** Kyūreki festivals, by their kanji as in [zanshin.core.kyureki.Festival]. */
    val JAPANESE_FESTIVAL: Map<String, Reading> = mapOf(
        "旧正月" to Reading("The first day of the first month of the old lunisolar calendar.", source = Sources.NAOJ_SEKKU),
        "人日の節句" to Reading("One of the five seasonal festivals (五節句), on the 7th day of the 1st month. The five were official holidays until 1872.", source = Sources.NAOJ_SEKKU),
        "上巳の節句" to Reading("One of the five seasonal festivals, on the 3rd day of the 3rd month.", source = Sources.NAOJ_SEKKU),
        "端午の節句" to Reading("One of the five seasonal festivals, on the 5th day of the 5th month.", source = Sources.NAOJ_SEKKU),
        "七夕" to Reading("One of the five seasonal festivals, on the 7th day of the 7th month. Kept on the old-calendar date it is the \"traditional Tanabata\".", source = Sources.NAOJ_SEKKU),
        "十五夜" to Reading("The mid-autumn moon of the 15th night of the 8th month, the moon-viewing night.", source = Sources.NAOJ_JUSANYA),
        "重陽の節句" to Reading("One of the five seasonal festivals, on the 9th day of the 9th month.", source = Sources.NAOJ_SEKKU),
        "十三夜" to Reading(
            "A second moon-viewing on the 13th night of the 9th month, a custom found only in Japan, said to begin with a " +
                "moon-viewing held by the retired emperor Uda in 919. Also called the later moon, chestnut moon or bean moon.",
            source = Sources.NAOJ_JUSANYA,
        ),
    )

    val PERSONAL_DAY: Map<PersonalDay, Reading> = mapOf(
        PersonalDay.LUCK to Reading("A harmonious weekday for your birth year: suitable for starting projects and celebrating auspicious events.", source = Sources.RABTEN),
        PersonalDay.LIFE to Reading("A harmonious weekday for your birth year: suitable for starting projects and celebrating auspicious events.", source = Sources.RABTEN),
        PersonalDay.ANTI to Reading("A disharmonious weekday for your birth year: generally unsuitable for starting things or celebrations.", source = Sources.RABTEN),
    )
}
