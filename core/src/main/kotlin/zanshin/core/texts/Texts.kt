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
import zanshin.core.kyureki.Tone
import zanshin.core.kyureki.Zassetsu
import zanshin.core.tibetan.ZodiacSign
import zanshin.core.tibetan.Animal
import zanshin.core.tibetan.Electional
import zanshin.core.tibetan.ElementPair
import zanshin.core.tibetan.Karana
import zanshin.core.tibetan.Kinship
import zanshin.core.tibetan.LunarDayClass
import zanshin.core.tibetan.Mansion
import zanshin.core.tibetan.PersonalDay
import zanshin.core.tibetan.PersonalMansion
import zanshin.core.tibetan.OwnDay
import zanshin.core.tibetan.SpecialDay
import zanshin.core.tibetan.SeasonReckoning
import zanshin.core.tibetan.TibetanFestival
import zanshin.core.tibetan.CombinationDay
import zanshin.core.tibetan.Direction
import zanshin.core.tibetan.GreatCombination
import zanshin.core.tibetan.Trigram
import zanshin.core.tibetan.Weekday
import zanshin.core.tibetan.Yoga

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

/**
 * Sources name a text by its English name first, then its title in Tibetan
 * script and in Wylie, in brackets (SPEC §8): the Wylie as BDRC catalogues
 * it, so that the text can be found there.
 */
object Sources {
    private const val WHITE_BERYL = "The White Beryl (ཕུག་ལུགས་རྩིས་ཀྱི་ལེགས་བཤད་མཁས་པའི་མགུལ་རྒྱན་བཻཌཱུར་དཀར་པོའི་དོ་ཤལ, Phug lugs rtsis kyi legs bshad mkhas pa'i mgul rgyan bai DUr dkar po'i do shal)"
    private const val KUN_PHAN_ME_LONG_TITLE = "The Stainless All-Benefiting Mirror (འབྲས་རྩིས་བཻ་དཀར་དགོངས་དོན་དྲི་མེད་ཀུན་ཕན་མེ་ལོང, 'Bras rtsis bai dkar dgongs don dri med kun phan me long)"
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
    val WP_OBON = Source("お盆", "Japanese Wikipedia", "https://ja.wikipedia.org/wiki/お盆")
    /** The 三箇の悪日 by birth year: the classical text, read on the NDL scan, and koyomi8's table of it. */
    val FUKI_NAIDEN = Source(
        "The Hoki Naiden, Collection of the Golden Crow and the Jade Hare (三國相傳陰陽輨轄簠簋内傳金烏玉兎集, Sangoku sōden on'yō kankatsu hoki naiden kin'u gyokuto shū), vol. 1,「三箇悪日」, 田中太右衛門, 1919",
        "国立国会図書館デジタルコレクション (NDL)",
        "https://dl.ndl.go.jp/pid/1911335/1/20",
    )
    /** 歳下食 by the year's branch, a second witness to Wikipedia's table, read on the NDL scan. */
    val DOKUSEN_EKIGAKU = Source(
        "The Complete Book of Divination (独占易学全書, Dokusen ekigaku zensho), ed. 開運館,「歳下食日」, 又間精華堂, Osaka 1901, p. 54",
        "国立国会図書館デジタルコレクション (NDL)",
        "https://dl.ndl.go.jp/pid/760758/1/29",
    )
    val KOYOMI8_GEDAN = Source("暦注の説明（その３）・下段について", "こよみのページ (koyomi8.com)", "https://koyomi8.com/sub/rekicyuu_doc03.html")
    val KB_TORA = Source("「寅の日」, in the Great Dictionary of the Japanese Language, Selected Edition (精選版 日本国語大辞典, Seisenban Nihon kokugo daijiten)", "Kotobank", "https://kotobank.jp/word/寅の日")
    val KB_MI = Source("「巳の日」, in the Great Dictionary of the Japanese Language, Selected Edition (精選版 日本国語大辞典, Seisenban Nihon kokugo daijiten)", "Kotobank", "https://kotobank.jp/word/巳の日")
    val KB_KINOENE = Source("「甲子」, in the Encyclopedia Nipponica (日本大百科全書, Nihon daihyakka zensho) and the World Encyclopedia (世界大百科事典, Sekai daihyakka jiten)", "Kotobank", "https://kotobank.jp/word/甲子")
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
        "$WHITE_BERYL, Sde srid Sangs rgyas rgya mtsho, ff. 248b–254a and 295b–299a, with Lo chen Dharmaśrī's " +
            "Moonbeams (འབྱུང་རྩིས་མན་ངག་ཟླ་བའི་འོད་ཟེར, 'Byung rtsis man ngag zla ba'i 'od zer), ff. 28a/b and 31b–32a; " +
            "the four aspects ff. 156a/b, 158a",
        "BDRC W1KG12714",
        "https://library.bdrc.io/show/bdr:MW1KG12714",
    )
    /** The White Beryl, 1996 edition; [pages] names the section, the pages are the printed ones (chapter 33 in vol. 2). */
    private fun whiteBeryl(pages: String, volume: Int = 2) = Source(
        "$WHITE_BERYL, Sde srid Sangs rgyas rgya mtsho, 1685; " +
            "Krung go'i bod kyi shes rig dpe skrun khang, Beijing 1996, vol. $volume, $pages",
        "BDRC MW2CZ8040",
        "https://library.bdrc.io/show/bdr:MW2CZ8040",
    )
    val WHITE_BERYL_YOGAS = whiteBeryl("pp. 347–349")
    val WHITE_BERYL_KARANAS = whiteBeryl("pp. 349–351")
    val WHITE_BERYL_LUNAR_DATES = whiteBeryl("pp. 297–304")
    val WHITE_BERYL_PERSONAL_MANSIONS = whiteBeryl("p. 330")
    /** A person's own weekdays and mansions: the works p. 338, the own weekday's p. 312, the element's weekdays p. 330 with the table p. 346. */
    val WHITE_BERYL_OWN_DAYS = whiteBeryl("pp. 337–338, with p. 312 and the tables pp. 345–346")
    val WHITE_BERYL_WEEKDAYS = whiteBeryl("pp. 308–312")
    val WHITE_BERYL_MANSION_VERSES = whiteBeryl("pp. 313–328")
    val WHITE_BERYL_MANSION_CLASSES = whiteBeryl("pp. 328–329")
    /** Chapter 34, the important works one by one, whose lists the kun phan me long's boxes digest (SPEC §5.10). */
    val WHITE_BERYL_WORKS = whiteBeryl("pp. 378–428")
    val WHITE_BERYL_EARTH_LORDS = whiteBeryl("pp. 223–226")
    val WHITE_BERYL_BLA_MKHYEN = whiteBeryl("p. 224, with the year's bla mkhyen p. 180, the remedy's texts in full p. 189 and the day's sme ba p. 192")
    /** The five texts the bla mkhyen's remedy names (WB vol. 2, p. 180), in one print of the Collected Dhāraṇīs. */
    val GZUNGS_BSDUS = Source(
        "The Collected Dhāraṇīs (གཟུངས་བསྡུས, gzungs bsdus), also titled A Garland of Wish-Fulfilling Jewels, the Cherished Essence of the Ocean of Sūtra and Tantra " +
            "(མདོ་རྒྱུད་གསུང་རབ་རྒྱ་མཚོའི་སྙིང་པོ་གཅེས་པར་བཏུས་པ་འདོད་འབྱུང་ནོར་བུའི་ཕྲེང་བ, Mdo rgyud gsung rab rgya mtsho'i snying po gces par btus pa 'dod 'byung nor bu'i phreng ba), " +
            "Dkon mchog lha bris, Delhi 1994: with Reciting the Names of Mañjuśrī (part ka), the White Parasol (ca), the Ratnaketu Dhāraṇī (nya) " +
            "and the Eight Appearances of Heaven and Earth (po)",
        "BDRC MW1KG5988",
        "https://library.bdrc.io/show/bdr:MW1KG5988",
    )
    val SNANG_BRGYAD = Source(
        "The Sūtra of the Eight Appearances of Heaven and Earth (འཕགས་པ་གནམ་ས་སྣང་བརྒྱད་ཅེས་བྱ་བ་ཐེག་པ་ཆེན་པོའི་མདོ, " +
            "'Phags pa gnam sa snang brgyad ces bya ba theg pa chen po'i mdo), in the Collected Dhāraṇīs, Delhi 1994, part po",
        "BDRC MW1KG5988_508E3D",
        "https://library.bdrc.io/show/bdr:MW1KG5988_508E3D",
    )
    val GDUGS_DKAR = Source(
        "The Supreme Accomplishment of Invincible Averting, Sitātapatrā Born from the Uṣṇīṣa of the Tathāgata " +
            "(འཕགས་པ་དེ་བཞིན་གཤེགས་པའི་གཙུག་ཏོར་ནས་བྱུང་བའི་གདུགས་དཀར་པོ་ཅན་གཞན་གྱིས་མི་ཐུབ་པ་ཕྱིར་ཟློག་པ་ཆེན་མོ་མཆོག་ཏུ་གྲུབ་པ, " +
            "'Phags pa de bzhin gshegs pa'i gtsug tor nas byung ba'i gdugs dkar po can gzhan gyis mi thub pa phyir zlog pa chen mo mchog tu grub pa), Toh 591",
        "84000",
        "https://84000.co/translation/toh591",
    )
    val MTSHAN_BRJOD = Source(
        "Reciting the Names of Mañjuśrī (འཕགས་པ་འཇམ་དཔལ་གྱི་མཚན་ཡང་དག་པར་བརྗོད་པ, 'Phags pa 'jam dpal gyi mtshan yang dag par brjod pa), Toh 360",
        "84000",
        "https://84000.co/translation/toh360",
    )
    val TOG_GZUNGS = Source(
        "The Ratnaketu Dhāraṇī (འཕགས་པ་འདུས་པ་ཆེན་པོ་རིན་པོ་ཆེ་ཏོག་གི་གཟུངས་ཞེས་བྱ་བ་ཐེག་པ་ཆེན་པོའི་མདོ, " +
            "'Phags pa 'dus pa chen po rin po che tog gi gzungs zhes bya ba theg pa chen po'i mdo), Toh 138",
        "84000",
        "https://84000.co/translation/toh138",
    )
    /** Blo bzang sbyin pa's account of the day's sme ba, which way it counts and from which wood-mouse day (docs/sources/earth-lords.md). */
    val BLO_BZANG_SBYIN_PA_DAY_SME_BA = Source(
        "Blo bzang sbyin pa, A Flower Offered to Mañjughoṣa, an Entry to the Chinese Reckoning " +
            "(ཙི་ནའི་རྩིས་ལ་འཇུག་པའི་ཡི་གེ་འཇམ་དབྱངས་མཆོད་པའི་མེ་ཏོག, Tsi na'i rtsis la 'jug pa'i yi ge 'jam dbyangs mchod pa'i me tog), " +
            "in his Collected Works (གསུང་འབུམ, gsung 'bum), Kan su'u mi rigs dpe skrun khang, Lanzhou 2003, vol. 3",
        "BDRC MW25151_4E0A69",
        "https://library.bdrc.io/show/bdr:MW25151_4E0A69",
    )
    val MDO_KHAMS_STOD_DAY_SME_BA = Source(
        "A Garland of Nectar Drops, the Essence of the Words of the Learned and Accomplished of Upper Dokham " +
            "(ཡུལ་མདོ་ཁམས་སྟོད་ཀྱི་མཁས་གྲུབ་རྣམ་པའི་གསུང་བཅུད་བདུད་རྩིའི་ཐིགས་ཕྲེང, Yul mdo khams stod kyi mkhas grub rnam pa'i gsung bcud bdud rtsi'i thigs phreng), " +
            "vol. 10, Bod ljongs dpe rnying dpe skrun khang, Lhasa 2012",
        "BDRC MW1PD152297",
        "https://library.bdrc.io/show/bdr:MW1PD152297",
    )
    val WHITE_BERYL_GODDESSES = whiteBeryl("pp. 449–450", volume = 1)
    val WHITE_BERYL_RAHU = whiteBeryl("pp. 236–239")
    val WHITE_BERYL_DUS_SBYOR = whiteBeryl("pp. 371–376")
    val WHITE_BERYL_COMBINATION_DAYS = whiteBeryl("pp. 335–337, with the table p. 341")
    val WHITE_BERYL_GTSUG_LAG_DAYS = whiteBeryl("p. 337, with the table p. 342")
    val WHITE_BERYL_BURNING_DATES = whiteBeryl("p. 351 and chapter 34, pp. 404, 414 and 426, with the dates in vol. 1, p. 177")
    val WHITE_BERYL_COMBINATIONS = whiteBeryl("pp. 331–333, with the table in vol. 1, pp. 148–149")
    /** The birth mansion as the mansion of the birth date: ch. 3, section 7, «སྐྱེས་སྐར་ངོས་འཛིན་གྱི་རྩིས». */
    val PHUG_PA_DBYANGS_CHAR = Source(
        "Phug pa Lhun grub rgya mtsho, The Sound of Supreme Joy, an Extensive Commentary on the Glorious Svarodaya, Victorious in Battle " +
            "(དཔལ་གཡུལ་ལས་རྣམ་པར་རྒྱལ་བ་དབྱངས་འཆར་བའི་རྒྱ་ཆེར་འགྲེལ་པ་མཆོག་ཏུ་དགའ་བའི་སྒྲ་དབྱངས, " +
            "Dpal g.yul las rnam par rgyal ba dbyangs 'char ba'i rgya cher 'grel pa mchog tu dga' ba'i sgra dbyangs), ch. 3",
        "BDRC MW1NLM5184",
        "https://library.bdrc.io/show/bdr:MW1NLM5184",
    )
    val NOR_BU_ME_LONG = Source(
        "The Jewel Mirror That Makes Elemental Divination Clear (འབྲས་རྩིས་རབ་གསལ་ནོར་བུའི་མེ་ལོང, 'Bras rtsis rab gsal nor bu'i me long), " +
            "in The Scriptures of the Glorious Sakyapas (དཔལ་ལྡན་ས་སྐྱ་པའི་གསུང་རབ, Dpal ldan sa skya pa'i gsung rab), vol. 7, " +
            "Mi rigs dpe skrun khang, Beijing 2004, p. 64",
        "BDRC MW29978_8B19DD",
        "https://library.bdrc.io/show/bdr:MW29978_8B19DD",
    )
    val HENNING_SYMBOLS = Source("Symbolic details of the Kālacakra calendar", "Edward Henning", "http://www.kalacakra.org/calendar/symlst.htm")
    val HENNING_ARCHIVE = Source("Phugpa Tibetan calendar list", "Edward Henning", "http://www.kalacakra.org/calendar/tiblist.htm")
    val BERZIN_ASTROLOGY_1 = Source("Details of Tibetan Astrology 1: Philosophical Context and Horoscopes", "Alexander Berzin, Study Buddhism", "https://studybuddhism.com/en/advanced-studies/history-culture/tibetan-astrology/details-of-tibetan-astrology-1-philosophical-context-and-horoscopes")
    val HENNING_ELECTIONAL = Source("Horary and electional astrology of the five components", "Edward Henning", "http://www.kalacakra.org/calendar/tibast03.htm")
    /** The print Henning translated the activity lists from; his doubled mansions are read on it (SPEC §5.10). */
    val KUN_PHAN_ME_LONG = Source(
        "$KUN_PHAN_ME_LONG_TITLE, Mtho las dgon print, the activity tables (img. 21–65)",
        "BDRC MW4CZ65561",
        "https://library.bdrc.io/show/bdr:MW4CZ65561",
    )
    /** The same print's chart of Rāhu's general course by date, the White Beryl's grouped by direction (§7, img. 78). */
    val KUN_PHAN_ME_LONG_DUS_SBYOR = Source(
        "$KUN_PHAN_ME_LONG_TITLE, Mtho las dgon print, the sign rising in each hour by month (img. 79–80)",
        "BDRC MW4CZ65561",
        "https://library.bdrc.io/show/bdr:MW4CZ65561",
    )
    val KUN_PHAN_ME_LONG_NECTAR = Source(
        "$KUN_PHAN_ME_LONG_TITLE, Mtho las dgon print, Jupiter's nectar periods by day and by night (img. 81–82)",
        "BDRC MW4CZ65561",
        "https://library.bdrc.io/show/bdr:MW4CZ65561",
    )
    val KUN_PHAN_ME_LONG_RAHU = Source(
        "$KUN_PHAN_ME_LONG_TITLE, Mtho las dgon print, Rāhu's course by date (img. 78)",
        "BDRC MW4CZ65561",
        "https://library.bdrc.io/show/bdr:MW4CZ65561",
    )
    /** The same print's chart of the day's earth lords: where each sits, the hearth god, the witnessing earth lord (§16, img. 103). */
    val KUN_PHAN_ME_LONG_EARTH_LORDS = Source(
        "$KUN_PHAN_ME_LONG_TITLE, Mtho las dgon print, the earth lords of the day (img. 103)",
        "BDRC MW4CZ65561",
        "https://library.bdrc.io/show/bdr:MW4CZ65561",
    )
    val LOTSAWA_TENTH = Source(
        "A Prayer Invoking the Benefits of the Festival of the Tenth Day (ཚེས་བཅུའི་ཕན་ཡོན་གསོལ་འདེབས, tshes bcu'i phan yon gsol 'debs), " +
            "by Rigdzin Jigme Lingpa, tr. Rigpa Translations, 2013",
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

    /** Where the birth-year rule of the three personal bad days is stated (SPEC §7.5). */
    private val PERSONAL_BAD_DAYS = listOf(Sources.FUKI_NAIDEN, Sources.KOYOMI8_GEDAN)

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
        Senjitsu.SAIGEJIKI to Reading(avoidKeys = listOf("eating_and_drinking_heavily", "sowing", "opening_rice_bales", "planting"), source = Sources.WP_KAGEDAN, license = License.CC_BY_SA, also = listOf(Sources.DOKUSEN_EKIGAKU)),
        Senjitsu.JUNICHI to Reading(avoidKeys = listOf("weddings", "funerals"), source = Sources.WP_KAGEDAN, license = License.CC_BY_SA),
        Senjitsu.FUKUNICHI to Reading(avoidKeys = listOf("weddings"), source = Sources.WP_KAGEDAN, license = License.CC_BY_SA),
        Senjitsu.KANOE_SARU to Reading(source = Sources.WP_KOSHIN, license = License.CC_BY_SA),
        Senjitsu.TAIKA to Reading(avoidKeys = listOf("careless_words", "house_repairs", "building_gates", "sea_voyages", "funerals"), source = Sources.WP_KAGEDAN, license = License.CC_BY_SA, also = PERSONAL_BAD_DAYS),
        Senjitsu.ROSHAKU to Reading(avoidKeys = listOf("everything"), source = Sources.WP_KAGEDAN, license = License.CC_BY_SA, also = PERSONAL_BAD_DAYS),
        Senjitsu.METSUMON to Reading(avoidKeys = listOf("everything"), source = Sources.WP_KAGEDAN, license = License.CC_BY_SA, also = PERSONAL_BAD_DAYS),
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

    /**
     * The same verdict as a list of the date, good or to avoid, so that the day in brief weighs it
     * with the date's other lists and the hair-cutting row and the brief do not go separate ways
     * (SPEC §5.12).
     */
    val HAIRCUT_LIST: List<Reading> = (1..30).map {
        if (it in HAIRCUT_GOOD) Reading(goodKeys = listOf("haircuts"), source = Sources.FPMT_HAIR)
        else Reading(avoidKeys = listOf("haircuts"), source = Sources.FPMT_HAIR)
    }

    /**
     * The lunar mansion: its kind of work, nature, planet and foods, and what the
     * White Beryl's verse on it, the list of mansions and the activity lists name
     * it good or bad for, the verse first (SPEC §5.10).
     */
    val MANSION: Map<Mansion, Reading> = Mansion.entries.associateWith { m ->
        val (verseGood, verseAvoid) = MansionVerses.LISTS.getValue(m)
        Reading(
            goodKeys = (verseGood + Electional.MANSION_ACTIVITIES.getValue(m) + Electional.good { m in it.mansions }).distinct(),
            avoidKeys = (verseAvoid + Electional.bad { m in it.mansions }).distinct(),
            source = Sources.HENNING_ELECTIONAL,
            also = listOf(Sources.WHITE_BERYL_MANSION_VERSES, Sources.KUN_PHAN_ME_LONG, Sources.WHITE_BERYL_WORKS, Sources.WHITE_BERYL_MANSION_CLASSES),
            key = "reading.Mansion",
            arg = "reading.${glossKey(m)}",
        )
    }

    /**
     * The yoga: the White Beryl's short reading, the gist of its longer verse,
     * and whether its avoidance verse names it (docs/sources/yogas.md).
     */
    val YOGA: Map<Yoga, Reading> = keyed(*Yoga.entries.map { it to Reading(source = Sources.WHITE_BERYL_YOGAS) }.toTypedArray())

    /**
     * The yoga's dot: unlucky for the three the White Beryl says to avoid
     * whole and the three whose short reading names a harm, mixed for the
     * others it says to avoid in their first chu tshod (3, 5, 6 or 9) and the two it
     * calls middling, lucky for the rest. Its ranking verse is not used: it
     * counts from sel ba, but its 18th and its good line have no reading in
     * any source found (docs/sources/yogas.md, "Ranking").
     */
    val YOGA_TONE: Map<Yoga, Tone> = Yoga.entries.associateWith {
        when (it) {
            Yoga.VYATIPATA, Yoga.PARIGHA, Yoga.VAIDHRITI, Yoga.ATIGANDA, Yoga.SHULA, Yoga.GANDA -> Tone.BAD
            Yoga.VISHKAMBHA, Yoga.VYAGHATA, Yoga.VAJRA, Yoga.HARSHANA -> Tone.MIXED
            else -> Tone.GOOD
        }
    }

    /**
     * The karaṇa: what the White Beryl's verse on it names good, and for Viṣṭi
     * what to avoid (docs/sources/karanas.md).
     */
    val KARANA: Map<Karana, Reading> = keyed(
        Karana.VAVA to Reading(goodKeys = listOf("virtue", "lasting_work", "work_on_the_move", "increasing_rites", "preparing_medicine", "dharma_practice", "reciting_mantras"), source = Sources.WHITE_BERYL_KARANAS),
        Karana.BALAVA to Reading(goodKeys = listOf("serving_the_teacher", "the_brahmins_affairs", "ordination", "the_parents_affairs", "fire_offerings", "honouring_and_service", "work_with_cattle"), source = Sources.WHITE_BERYL_KARANAS),
        Karana.KAULAVA to Reading(goodKeys = listOf("gathering_merit", "making_images", "consecration", "disputes", "trade", "power_rites", "unsteady_and_moving_work", "moving_goods"), source = Sources.WHITE_BERYL_KARANAS),
        Karana.TAITILA to Reading(goodKeys = listOf("gathering_power", "prayers_for_good_fortune", "power_rites", "increasing_rites", "making_friends", "joyful_occasions"), source = Sources.WHITE_BERYL_KARANAS),
        Karana.GARA to Reading(goodKeys = listOf("field_work", "taking_a_new_home"), source = Sources.WHITE_BERYL_KARANAS),
        Karana.VANIJA to Reading(goodKeys = listOf("buying_and_trading", "lasting_work"), source = Sources.WHITE_BERYL_KARANAS),
        Karana.VISHTI to Reading(goodKeys = listOf("killing_others", "preparing_poison", "harsh_work", "fierce_rites"), avoidKeys = listOf("virtue", "empowerment", "consecration", "lawsuits", "marriage"), source = Sources.WHITE_BERYL_KARANAS),
        Karana.SHAKUNI to Reading(goodKeys = listOf("increasing_rites", "mantras", "cursing", "preparing_medicine"), source = Sources.WHITE_BERYL_KARANAS),
        Karana.CATUSHPADA to Reading(goodKeys = listOf("worship_of_brahmins", "affairs_of_state", "work_with_cattle", "weapon_tormas"), source = Sources.WHITE_BERYL_KARANAS),
        Karana.NAGA to Reading(goodKeys = listOf("lasting_work", "killing_and_robbing", "works_of_good_fortune"), source = Sources.WHITE_BERYL_KARANAS),
        Karana.KIMSTUGHNA to Reading(goodKeys = listOf("virtue", "killing_people", "robbing", "works_of_good_fortune", "wished_for_increase", "empowerment"), source = Sources.WHITE_BERYL_KARANAS),
    )

    /**
     * The karaṇa's dot: Viṣṭi unlucky, since the verse names every virtuous
     * work bad on it and good only harsh ones; the others lucky, since their
     * verses name only what they are good for.
     */
    val KARANA_TONE: Map<Karana, Tone> = Karana.entries.associateWith { if (it == Karana.VISHTI) Tone.BAD else Tone.GOOD }

    /**
     * The lunar date, 1–30: the White Beryl's good and bad activities, with its
     * place in the five-fold cycle, a birth and a death on it, the four
     * perilous dates and where the la, the life-spirit, sits in a person and in a horse (bla gnas) (docs/sources/lunar-dates.md).
     */
    val LUNAR_DATE: List<Reading> = listOf(
        Reading(goodKeys = listOf("setting_out", "lawsuits", "breaking_ground", "pacifying_rites", "war_not_east", "buying_livestock"), avoidKeys = listOf("teaching_dharma", "washing_the_hair", "funeral_rites", "marriage", "giving_anything_out", "worship_of_deities", "averting_rites", "suppressing_sri", "ordination"), source = Sources.WHITE_BERYL_LUNAR_DATES, key = "reading.LunarDate.1"),
        Reading(goodKeys = listOf("consecration", "enthronement", "building_a_hearth", "sowing", "digging_ponds_and_wells", "increasing_rites", "buying_livestock"), avoidKeys = listOf("lawsuits", "setting_out", "washing_the_hair", "building_dams", "fire_offerings"), source = Sources.WHITE_BERYL_LUNAR_DATES, key = "reading.LunarDate.2"),
        Reading(goodKeys = listOf("ordination", "consecration", "setting_out", "lawsuits", "building", "taking_a_new_home", "washing_the_hair", "fire_offerings", "increasing_rites", "power_rites", "war_not_south", "buying_livestock"), avoidKeys = listOf("retinue_and_marriage", "digging_ponds_canals_and_wells"), source = Sources.WHITE_BERYL_LUNAR_DATES, key = "reading.LunarDate.3"),
        Reading(goodKeys = listOf("washing_the_hair", "trade", "taking_servants", "felling_trees", "prostrations", "suppressing_sri", "power_rites", "fierce_rites", "buying_livestock"), avoidKeys = listOf("ordination", "consecration", "setting_out", "bloodletting_and_moxibustion", "leading_an_army", "marriage", "digging_ponds_and_wells", "sowing"), source = Sources.WHITE_BERYL_LUNAR_DATES, key = "reading.LunarDate.4"),
        Reading(goodKeys = listOf("ordination", "washing_the_hair", "setting_out", "consecration", "taking_servants", "pacifying_rites", "buying_livestock"), avoidKeys = listOf("funeral_rites", "marriage"), source = Sources.WHITE_BERYL_LUNAR_DATES, key = "reading.LunarDate.5"),
        Reading(goodKeys = listOf("washing_the_hair", "consecration", "breaking_ground", "increasing_rites"), avoidKeys = listOf("setting_out", "hearing_dharma", "ordination", "taking_servants", "buying_livestock", "enthronement", "building", "marriage"), source = Sources.WHITE_BERYL_LUNAR_DATES, key = "reading.LunarDate.6"),
        Reading(goodKeys = listOf("building", "consecration", "setting_out", "digging_ponds_canals_and_wells", "taking_a_new_home", "funeral_rites", "building_a_hearth", "lawsuits", "war_not_east", "power_rites"), avoidKeys = listOf("washing_the_hair", "taking_servants", "marriage", "ordination", "buying_livestock"), source = Sources.WHITE_BERYL_LUNAR_DATES, key = "reading.LunarDate.7"),
        Reading(goodKeys = listOf("attacking_enemies", "judging_disputes", "enthronement", "consecration", "oaths", "suppressing_sri", "washing_the_hair", "making_images", "building_a_hearth", "fierce_rites"), avoidKeys = listOf("marriage", "new_clothes", "setting_out", "bloodletting", "ordination", "sowing", "digging_ponds_canals_and_wells", "buying_livestock"), source = Sources.WHITE_BERYL_LUNAR_DATES, key = "reading.LunarDate.8"),
        Reading(goodKeys = listOf("enthronement", "consecration", "setting_out", "suppressing_sri", "pacifying_rites", "war_not_south"), avoidKeys = listOf("washing_the_hair", "taking_servants", "funeral_rites", "marriage"), source = Sources.WHITE_BERYL_LUNAR_DATES, key = "reading.LunarDate.9"),
        Reading(goodKeys = listOf("washing_the_hair", "ordination", "consecration", "enthronement", "building_temples_and_stupas", "making_images", "war_not_south", "planting", "increasing_rites"), avoidKeys = listOf("setting_out", "taking_servants", "buying_livestock"), source = Sources.WHITE_BERYL_LUNAR_DATES, key = "reading.LunarDate.10"),
        Reading(goodKeys = listOf("washing_the_hair", "bathing", "tying_on_amulets", "enthronement", "consecration", "ordination", "building", "war_not_south", "breaking_ground", "bloodletting_and_moxibustion", "setting_out", "fierce_rites"), avoidKeys = listOf("taking_servants", "buying_livestock", "breaking_in_livestock"), source = Sources.WHITE_BERYL_LUNAR_DATES, key = "reading.LunarDate.11"),
        Reading(goodKeys = listOf("consecration", "enthronement", "building_temples_and_stupas", "building_a_hearth", "making_images", "sowing", "digging_ponds_canals_and_wells", "pacifying_rites", "war_not_south_or_west"), avoidKeys = listOf("setting_out", "washing_the_hair", "taking_servants", "buying_livestock", "fire_offerings", "ordination"), source = Sources.WHITE_BERYL_LUNAR_DATES, key = "reading.LunarDate.12"),
        Reading(goodKeys = listOf("enthronement", "washing_the_hair", "setting_out", "consecration", "building", "building_a_hearth", "building_temples_and_stupas", "making_images", "fire_offerings", "building_dams", "increasing_rites"), avoidKeys = listOf("digging_ponds_and_wells", "taking_servants", "buying_livestock"), source = Sources.WHITE_BERYL_LUNAR_DATES, key = "reading.LunarDate.13"),
        Reading(goodKeys = listOf("washing_the_hair", "ordination", "consecration", "buying_livestock", "building", "setting_out", "power_rites"), avoidKeys = listOf("sowing", "teaching_dharma", "taking_servants"), source = Sources.WHITE_BERYL_LUNAR_DATES, key = "reading.LunarDate.14"),
        Reading(goodKeys = listOf("taking_a_new_home", "making_images", "building_temples_and_stupas", "consecration", "sowing", "prosperity_rites", "setting_out", "empowerment", "dharma_practice", "washing_the_hair", "gaining_wealth", "funeral_rites", "enthronement", "averting_rites", "building", "buying_livestock"), avoidKeys = listOf("oaths", "marriage", "ordination", "giving_a_child_away", "paying_debts"), source = Sources.WHITE_BERYL_LUNAR_DATES, key = "reading.LunarDate.15"),
        Reading(goodKeys = listOf("breaking_ground", "field_work", "pacifying_rites", "washing_the_hair", "buying_servants_and_goods", "buying_livestock"), avoidKeys = listOf("consecration", "setting_out", "robbery", "quarrels", "building", "enthronement"), source = Sources.WHITE_BERYL_LUNAR_DATES, key = "reading.LunarDate.16"),
        Reading(goodKeys = listOf("funeral_rites", "setting_out", "building_a_hearth", "building", "digging_ponds_and_wells", "sowing", "increasing_rites"), avoidKeys = listOf("washing_the_hair", "building_dams", "fire_offerings", "marriage", "buying_livestock"), source = Sources.WHITE_BERYL_LUNAR_DATES, key = "reading.LunarDate.17"),
        Reading(goodKeys = listOf("washing_the_hair", "virtuous_work", "making_images", "suppressing_sri", "building_a_hearth", "sowing", "building_dams", "fire_offerings", "fierce_rites", "roofing", "hanging_doors", "setting_out", "bloodletting_and_moxibustion", "funeral_rites", "enthronement"), avoidKeys = listOf("digging_ponds_canals_and_wells", "marriage", "buying_livestock"), source = Sources.WHITE_BERYL_LUNAR_DATES, key = "reading.LunarDate.18"),
        Reading(goodKeys = listOf("breaking_ground", "suppressing_sri", "setting_out", "washing_the_hair", "building", "pacifying_rites", "field_work"), avoidKeys = listOf("funeral_rites", "consecration", "enthronement", "building_temples_and_stupas", "marriage", "making_images", "buying_livestock"), source = Sources.WHITE_BERYL_LUNAR_DATES, key = "reading.LunarDate.19"),
        Reading(goodKeys = listOf("increasing_rites"), avoidKeys = listOf("washing_the_hair", "consecration", "enthronement", "setting_out", "making_images"), source = Sources.WHITE_BERYL_LUNAR_DATES, key = "reading.LunarDate.20"),
        Reading(goodKeys = listOf("planting", "setting_out", "power_rites", "buying_livestock"), avoidKeys = listOf("consecration", "taking_servants", "enthronement", "marriage", "building_temples", "making_images", "washing_the_hair"), source = Sources.WHITE_BERYL_LUNAR_DATES, key = "reading.LunarDate.21"),
        Reading(goodKeys = listOf("learning_writing_astrology_and_crafts", "building_a_hearth", "washing_the_hair", "digging_ponds_canals_and_wells", "planting", "gtad_and_sri_rites", "work_with_rock", "buying_livestock"), avoidKeys = listOf("taking_a_new_home", "consecration", "enthronement", "making_images", "council", "marriage", "burial", "setting_out", "taking_servants", "building", "buying_goods", "breaking_in_livestock", "fire_offerings", "building_flood_dikes"), source = Sources.WHITE_BERYL_LUNAR_DATES, key = "reading.LunarDate.22"),
        Reading(goodKeys = listOf("building", "building_a_hearth", "setting_out", "ordination", "washing_the_hair", "sowing", "fire_offerings", "building_dams", "pacifying_rites"), avoidKeys = listOf("digging_ponds_canals_and_wells", "buying_livestock", "buying_servants_and_goods", "consecration", "enthronement"), source = Sources.WHITE_BERYL_LUNAR_DATES, key = "reading.LunarDate.23"),
        Reading(goodKeys = listOf("taking_servants", "buying_goods", "buying_livestock", "setting_out", "sowing", "increasing_rites"), avoidKeys = listOf("enthronement", "consecration", "washing_the_hair"), source = Sources.WHITE_BERYL_LUNAR_DATES, key = "reading.LunarDate.24"),
        Reading(goodKeys = listOf("building", "buying_fields", "setting_out", "breaking_ground", "suppressing_sri", "funeral_rites", "sowing", "fierce_rites", "war_not_east", "buying_livestock"), avoidKeys = listOf("washing_the_hair", "consecration", "enthronement", "taking_servants"), source = Sources.WHITE_BERYL_LUNAR_DATES, key = "reading.LunarDate.25"),
        Reading(goodKeys = listOf("washing_the_hair", "sowing", "breaking_ground", "fierce_rites"), avoidKeys = listOf("taking_servants", "setting_out", "buying_goods", "buying_livestock", "enthronement", "consecration", "building"), source = Sources.WHITE_BERYL_LUNAR_DATES, key = "reading.LunarDate.26"),
        Reading(goodKeys = listOf("setting_out", "washing_the_hair", "funeral_rites", "sowing", "building_a_hearth", "digging_ponds_and_wells", "increasing_rites"), avoidKeys = listOf("enthronement", "consecration", "buying_servants_and_goods", "making_images", "building_temples", "marriage", "fire_offerings", "building_flood_dikes"), source = Sources.WHITE_BERYL_LUNAR_DATES, key = "reading.LunarDate.27"),
        Reading(goodKeys = listOf("fire_offerings", "building_dams", "power_rites"), avoidKeys = listOf("washing_the_hair", "setting_out", "funeral_rites", "taking_servants", "digging_ponds_and_canals", "marriage", "enthronement", "consecration", "sowing", "buying_goods", "buying_livestock", "war_south_or_west"), source = Sources.WHITE_BERYL_LUNAR_DATES, key = "reading.LunarDate.28"),
        Reading(goodKeys = listOf("teaching_dharma", "subduing_enemies", "setting_out", "suppressing_sri", "fierce_rites"), avoidKeys = listOf("sowing", "taking_servants", "breaking_in_livestock", "brewing_beer", "washing_the_hair", "consecration", "funeral_rites", "enthronement", "bloodletting_and_moxibustion", "making_images", "buying_livestock"), source = Sources.WHITE_BERYL_LUNAR_DATES, key = "reading.LunarDate.29"),
        Reading(goodKeys = listOf("power_rites", "sowing"), avoidKeys = listOf("consecration", "enthronement", "washing_the_hair", "setting_out", "bloodletting_and_moxibustion", "funeral_rites", "marriage", "buying_servants_and_goods", "buying_livestock"), source = Sources.WHITE_BERYL_LUNAR_DATES, key = "reading.LunarDate.30"),
    )

    /** The four perilous dates of every month (the White Beryl, p. 302). */
    val PERILOUS_DATES: Set<Int> = setOf(8, 15, 22, 30)

    /**
     * The lunar date's dot: lucky on the three virtuous days of the five-fold
     * cycle, unlucky on the other two; mixed for a virtuous day that is also
     * one of the perilous dates (the 8th and the 22nd).
     */
    fun lunarDateTone(date: Int): Tone = when {
        !LunarDayClass.of(date).virtuous -> Tone.BAD
        date in PERILOUS_DATES -> Tone.MIXED
        else -> Tone.GOOD
    }

    /**
     * The date's trigram as the day of one of the White Beryl's eight
     * goddesses: what an illness that comes on that day is traced to, the
     * spirits that harm, how it shows and the rites named for it
     * (docs/sources/lunar-day-signs.md). An illness reading only, so it
     * carries no lists and no tone.
     */
    val TRIGRAM: Map<Trigram, Reading> = keyed(*Trigram.entries.map { it to Reading(source = Sources.WHITE_BERYL_GODDESSES) }.toTypedArray())

    /**
     * The weekday: the White Beryl's verse on its planet, with what it names
     * good and bad, when the planet is strong, setting out, a birth and a
     * death (docs/sources/weekdays.md).
     */
    val WEEKDAY: Map<Weekday, Reading> = keyed(
        Weekday.SUNDAY to Reading(
            goodKeys = listOf("enthronement", "founding_a_palace", "affairs_of_state", "meeting_great_people", "fire_offerings", "preparing_medicine", "bathing", "empowerment", "ordination", "planting_trees", "making_weapons", "building_a_hearth", "raising_banners", "sowing", "averting_thieves", "horse_racing", "breaking_in_horses_and_livestock", "prosperity_rites", "spectacles", "naming", "building_dams", "saddling", "crafts_in_gold_wood_leather_and_bone", "work_with_fire", "smoke_offerings", "reciting_scripture", "worship_of_deities", "presenting_petitions", "prostrations", "study", "going_into_the_forest", "feasts", "making_perfumes", "auspicious_work"),
            avoidKeys = listOf("oaths", "funeral_rites", "carrying_out_the_dead", "harsh_words", "pacifying_rites", "visiting_the_sick", "bloodletting_and_moxibustion", "temple_foundations", "cutting_hair_and_nails", "first_wearing_of_new_clothes", "sewing_tents", "taking_a_new_home", "suppressing_sri", "building_towns", "planting", "digging_ponds_and_canals", "breaking_ground", "marriage", "consecration", "planting_flowers", "moving_house", "breaking_in_oxen", "building_storehouses", "sending_out_wealth", "reconciliation", "lawsuits_and_disputes", "setting_out"),
            source = Sources.WHITE_BERYL_WEEKDAYS,
        ),
        Weekday.MONDAY to Reading(
            goodKeys = listOf("preparing_medicine", "sowing", "brewing_beer", "bathing", "work_with_water", "auspicious_work", "taking_servants", "offerings_to_nagas", "building_a_hearth", "taking_a_new_home", "worship_of_deities", "digging_ponds_and_canals", "breaking_ground", "consecration", "marriage", "making_perfumes", "breaking_in_oxen", "spectacles", "reconciliation", "washing_the_hair", "cutting_hair_and_nails", "taking_elixirs", "putting_on_ornaments", "prostrations", "matchmaking", "first_wearing_of_new_clothes", "dairy_work", "breaking_in_horses", "field_work", "planting_trees", "grain_work", "bloodletting_and_moxibustion", "offerings_to_the_lama", "prosperity_rites", "pacifying_rites", "increasing_rites", "temple_foundations", "making_images"),
            avoidKeys = listOf("receiving_wealth", "sending_out_wealth", "funeral_rites", "suppressing_sri", "carrying_out_the_dead", "power_rites", "fierce_rites", "ordination", "hearing_dharma", "lawsuits", "war", "building", "making_weapons", "building_dams", "fire_offerings", "hunting", "setting_out"),
            source = Sources.WHITE_BERYL_WEEKDAYS,
        ),
        Weekday.TUESDAY to Reading(
            goodKeys = listOf("war", "martial_skills", "appointing_generals", "fierce_rites", "subduing_enemies", "enthronement", "judging_disputes", "lawsuits", "averting_rites", "suppressing_sri", "horse_racing", "fire_offerings", "hurling_zor", "directing_magic", "collecting_debts", "taking_new_lands", "raising_dogs", "moxibustion", "work_with_gold_coral_and_swords", "destroying_forts", "power_rites", "building_flood_dikes"),
            avoidKeys = listOf("ordination", "consecration", "marriage", "bathing", "putting_on_ornaments", "virtuous_work", "empowerment", "divination", "burial", "funeral_rites", "council", "taking_servants", "building", "building_temples", "making_images", "astrology", "preparing_medicine", "crafts", "cutting_hair_and_nails", "sending_out_wealth", "bloodletting", "building_towns", "gatherings", "feasts", "sowing", "worship_of_deities", "building_a_hearth", "building_storehouses", "digging_ponds_and_canals", "field_work", "breaking_ground", "planting_trees", "planting_flowers", "lasting_work", "auspicious_work", "spectacles", "reconciliation", "prosperity_rites", "trade", "first_wearing_of_new_clothes", "sewing_tents", "moving_house", "pacifying_rites", "increasing_rites", "setting_out"),
            source = Sources.WHITE_BERYL_WEEKDAYS,
        ),
        Weekday.WEDNESDAY to Reading(
            goodKeys = listOf("poetry_and_grammar", "writing_treatises", "reciting_mantras", "work_with_jewels", "earthworks", "trade", "taking_vows", "consecration", "learning_writing_and_astrology", "empowerment", "studying_the_dharma", "work_with_water", "reconciliation", "seeking_connections", "taking_a_new_home", "putting_on_ornaments", "first_wearing_of_new_clothes", "lawsuits", "prosperity_rites", "taking_a_retinue", "pacifying_rites", "washing_the_hair", "presenting_offerings", "building", "digging_ponds_canals_and_wells", "building_temples", "making_images", "building_storehouses", "moving_house", "planting", "breaking_ground", "crafts", "planting_trees", "planting_flowers", "breaking_in_livestock", "astrology_and_divination", "funeral_rites", "saddling", "bloodletting_and_moxibustion", "matchmaking", "cutting_hair_and_nails", "bathing", "magic_shows", "virtuous_work", "increasing_rites", "setting_out"),
            avoidKeys = listOf("ordination", "brewing_beer", "sending_out_wealth", "building_a_hearth", "building_dams", "hearing_dharma", "preparing_medicine", "taking_a_bride", "power_rites", "averting_rites", "disputes", "judging_disputes", "war", "robbery", "fierce_rites"),
            source = Sources.WHITE_BERYL_WEEKDAYS,
        ),
        Weekday.THURSDAY to Reading(
            goodKeys = listOf("ordination", "empowerment", "consecration", "taking_vows", "marriage", "first_wearing_of_new_clothes", "sowing", "virtuous_work", "mandala_rites", "fire_offerings", "learning_the_sciences", "teaching_and_hearing_the_dharma", "enthronement", "bloodletting_and_moxibustion", "power_rites", "preparing_medicine", "astrology_and_divination", "building", "building_a_hearth", "taking_a_new_home", "brewing_beer", "reciting_mantras", "putting_on_ornaments", "trade", "suppressing_sri", "building_temples", "making_images", "sewing_tents", "raising_banners", "woodwork", "moving_house", "breaking_in_livestock", "saddling", "taking_a_retinue", "reconciliation", "feasts", "planting_flowers", "planting_trees", "washing_the_hair", "work_with_jewels", "averting_rites", "directing_magic", "the_parents_affairs", "council", "lasting_work", "auspicious_work", "setting_out"),
            avoidKeys = listOf("roofing", "burial", "oaths", "war", "sending_out_livestock", "cutting_hair_and_nails", "hurling_mdos_torma_and_zor", "crafts", "funeral_rites"),
            source = Sources.WHITE_BERYL_WEEKDAYS,
        ),
        Weekday.FRIDAY to Reading(
            goodKeys = listOf("worship_of_deities", "teaching_dharma", "ordination", "consecration", "virtuous_work", "making_images", "brewing_beer", "seeking_friends", "sowing", "matchmaking", "marriage", "trade", "putting_on_ornaments", "making_perfumes", "farming", "building_temples", "building_new_houses", "laying_foundations", "first_wearing_of_new_clothes", "building", "digging_ponds_and_canals", "metal_and_jewel_work", "bloodletting", "increasing_rites", "moxibustion", "taking_a_new_home", "spectacles", "taking_a_retinue", "funeral_rites", "horse_racing", "games", "building_a_hearth", "feasts", "washing_the_hair", "cutting_hair_and_nails", "breaking_ground", "saddling", "council", "lasting_work", "auspicious_work", "astrology_and_divination", "learning_the_sciences", "reconciliation", "affairs_of_state", "judging_disputes", "presenting_petitions", "prostrations", "presenting_offerings", "building_dams", "consorting_with_women", "power_rites", "setting_out"),
            avoidKeys = listOf("feuds", "moving_house", "paying_debts", "hurling_mdos_torma_and_zor", "robbery", "fierce_rites", "sending_out_livestock"),
            source = Sources.WHITE_BERYL_WEEKDAYS,
        ),
        Weekday.SATURDAY to Reading(
            goodKeys = listOf("taking_a_new_home", "laying_foundations", "gaining_wealth", "long_life_and_prosperity_rites", "acquiring_goods_and_livestock", "leading_an_army", "field_work", "building_towns", "planting_flowers", "planting_trees", "ironwork", "digging_ponds_and_wells", "breaking_ground", "suppressing_sri", "raising_banners", "building_storehouses", "building", "naming", "consorting_with_women", "making_weapons", "black_rites", "raising_dogs", "astrology", "robbery", "sowing", "killing_others"),
            avoidKeys = listOf("ordination", "consecration", "restoring_vows", "virtuous_work", "council", "auspicious_work", "lasting_work", "first_wearing_of_new_clothes", "cutting_hair_and_nails", "increasing_rites", "power_rites", "bloodletting_and_moxibustion", "washing_the_hair", "preparing_medicine", "trade", "averting_rites", "marriage", "sending_out_wealth", "sewing_tents", "funeral_rites", "moving_house", "games", "building_temples", "making_images", "trading_land_and_houses", "saddling", "feasts", "reconciliation", "worship_of_deities", "putting_on_ornaments", "enthronement", "serving_the_king", "prostrations", "presenting_offerings", "affairs_of_state", "judging_disputes", "fighting_enemies", "taking_a_retinue", "spectacles", "setting_out"),
            source = Sources.WHITE_BERYL_WEEKDAYS,
        ),
    )

    /**
     * The weekday's dot: unlucky for Tuesday and Saturday, fierce planets on
     * which the chapter's closing verse says virtuous work is avoided; mixed
     * for Sunday, which that verse names with them though its own verse calls
     * it peaceful; lucky for the other four.
     */
    fun weekdayTone(w: Weekday): Tone = when (w) {
        Weekday.TUESDAY, Weekday.SATURDAY -> Tone.BAD
        Weekday.SUNDAY -> Tone.MIXED
        else -> Tone.GOOD
    }

    private fun electional(arg: String, good: List<String>, bad: List<String>) =
        Reading(
            goodKeys = good, avoidKeys = bad, source = Sources.KUN_PHAN_ME_LONG, also = listOf(Sources.WHITE_BERYL_WORKS, Sources.HENNING_ELECTIONAL),
            key = "reading.Electional", arg = "reading.Electional.$arg",
        )

    /** What the activity lists name each weekday, lunar date, day animal and trigram good or bad for. */
    val ELECTIONAL_WEEKDAY: Map<Weekday, Reading> = Weekday.entries.associateWith { w ->
        electional("weekday", Electional.good { w in it.weekdays }, Electional.bad { w in it.weekdays })
    }
    val ELECTIONAL_DATE: Map<Int, Reading> = (1..30).associateWith { d ->
        electional("date", Electional.good { d in it.dates }, Electional.bad { d in it.dates })
    }
    val ELECTIONAL_ANIMAL: Map<Animal, Reading> = Animal.entries.associateWith { a ->
        electional("animal", Electional.good { a in it.animals }, Electional.bad { a in it.animals })
    }
    /**
     * The lunar date's animal, the *nyi ma* (open question 9): where the earth
     * lord sits in the house on a day of that animal, in which part of the
     * house, where the hearth god is, and the earth lord who witnesses it,
     * all for the reckoning of the dead (WB vol. 2, pp. 223–226, and the *kun
     * phan me long*'s chart, img. 103; docs/sources/earth-lords.md), with
     * the activity lists' good and bad for a day of that animal.
     */
    val EARTH_LORD: Map<Animal, Reading> = Animal.entries.associateWith { a ->
        val lists = ELECTIONAL_ANIMAL.getValue(a)
        Reading(
            goodKeys = lists.goodKeys, avoidKeys = lists.avoidKeys, source = Sources.WHITE_BERYL_EARTH_LORDS,
            also = listOf(Sources.KUN_PHAN_ME_LONG_EARTH_LORDS, Sources.HENNING_ELECTIONAL, Sources.KUN_PHAN_ME_LONG),
            key = "reading.EarthLord", arg = "reading.EarthLord.${a.name}",
        )
    }
    /**
     * The *bla mkhyen* of the day, in the direction of the day's seven-red
     * (WB vol. 2, p. 224), with what the year's section names avoided towards
     * it, whom it harms and the remedy (p. 180); the day's sme ba by the count
     * of [zanshin.core.tibetan.DaySmeBa]. Its direction fills the summary.
     */
    val BLA_MKHYEN: Map<Direction, Reading> = Direction.entries.associateWith { d ->
        Reading(
            avoidKeys = listOf(
                "markets", "any_building_work", "seeking_land", "taking_out_a_corpse", "building_graves", "founding_a_palace",
                "funeral_rites", "taking_in_a_dog", "setting_up_a_gate", "building_a_hearth", "war", "robbery",
            ),
            source = Sources.WHITE_BERYL_BLA_MKHYEN,
            also = listOf(
                Sources.GZUNGS_BSDUS, Sources.GDUGS_DKAR, Sources.SNANG_BRGYAD, Sources.MTSHAN_BRJOD, Sources.TOG_GZUNGS,
                Sources.BLO_BZANG_SBYIN_PA_DAY_SME_BA, Sources.MDO_KHAMS_STOD_DAY_SME_BA,
            ),
            key = "reading.BlaMkhyen", arg = glossKey(d),
        )
    }
    val ELECTIONAL_TRIGRAM: Map<Trigram, Reading> = Trigram.entries.associateWith { t ->
        electional("trigram", Electional.good { t in it.trigrams }, Electional.bad { t in it.trigrams })
    }

    /**
     * The element pair of weekday and mansion, the small combination: the
     * White Beryl's verse on the ten (vol. 2, p. 333), with what each is good
     * for; the names as Rabten prints them.
     */
    val ELEMENT_PAIR: Map<ElementPair, Reading> = keyed(
        ElementPair.EARTH_EARTH to wbPair("setting_up_supports", "building", "buying_fields", "increasing_rites", "council", "lasting_work"),
        ElementPair.WATER_WATER to wbPair("trade", "field_work", "preparing_medicine", "bathing", "taking_a_bride", "long_life_and_prosperity_rites"),
        ElementPair.EARTH_WATER to wbPair("putting_on_ornaments", "games", "feasts"),
        ElementPair.FIRE_FIRE to wbPair("trade", "sowing", "giving_anything_out", "prosperity_rites", "virtuous_work"),
        ElementPair.WIND_WIND to wbPair("travel", "reconciliation", "unsteady_and_moving_work", "fierce_rites"),
        ElementPair.FIRE_WIND to wbPair("worship_of_deities", "dharma_practice", "pacifying_rites", "increasing_rites", "power_rites"),
        ElementPair.EARTH_WIND to wbPair(),
        ElementPair.WATER_WIND to wbPair(),
        ElementPair.EARTH_FIRE to wbPair("leading_an_army", "subduing_enemies", "fierce_rites", "disputes"),
        ElementPair.FIRE_WATER to wbPair("killing_others", "directing_magic", "preparing_poison"),
    )

    private fun wbPair(vararg good: String) = Reading(goodKeys = good.toList(), source = Sources.WHITE_BERYL_COMBINATIONS, also = listOf(Sources.RABTEN))

    /**
     * Rāhu's course by lunar date, the White Beryl's detailed account (vol. 2,
     * pp. 237–238; docs/sources/rahu.md): on the sixteen dates it enters a
     * direction or turns back, when and where it moves, and what that day is
     * good and bad for. Dates it does not name have no entry. This and the
     * general course both add its course over the hours of any day (p. 239).
     */
    val RAHU: Map<Int, Reading> = mapOf(
        1 to rahu(1, good = listOf("fierce_rites", "hurling_zor")),
        4 to rahu(4, good = listOf("taking_a_bride", "trade", "felling_trees", "virtuous_work", "brewing_beer"), avoid = listOf("funerals", "teaching_dharma", "reconciliation")),
        6 to rahu(6, good = listOf("fierce_rites")),
        8 to rahu(8, good = listOf("lawsuits", "attacking_enemies", "robbery", "oaths"), avoid = listOf("washing_the_hair", "sewing", "taking_a_bride", "bathing")),
        11 to rahu(11, good = listOf("killing_others", "black_rites")),
        12 to rahu(12, good = listOf("bathing", "feasts", "paying_debts", "washing_the_hair", "sowing"), avoid = listOf("taking_servants", "breaking_in_livestock")),
        14 to rahu(14, good = listOf("fierce_rites")),
        15 to rahu(15, good = listOf("worship_of_deities", "prosperity_rites", "empowerment", "dharma_practice"), avoid = listOf("sending_out_wealth", "oaths", "taking_a_bride", "giving_a_child_away")),
        17 to rahu(17, good = listOf("fierce_rites")),
        18 to rahu(18, good = listOf("ordination", "building_temples_and_stupas", "setting_out", "leading_an_army"), avoid = listOf("oaths", "prostrations", "building", "hanging_doors", "roofing")),
        21 to rahu(21, good = listOf("fierce_rites")),
        22 to rahu(22, good = listOf("crafts", "learning_writing_and_astrology", "digging_ponds_and_canals", "gtad_and_sri_rites", "council"), avoid = listOf("building_a_house", "breaking_in_livestock")),
        24 to rahu(24, good = listOf("fierce_rites")),
        25 to rahu(25, good = listOf("buying_fields", "building"), avoid = listOf("sending_out_wealth", "leading_an_army")),
        27 to rahu(27),
        29 to rahu(29, good = listOf("fierce_rites", "subduing_enemies"), avoid = listOf("dharma_practice", "sowing", "brewing_beer")),
    )

    private fun rahu(date: Int, good: List<String> = emptyList(), avoid: List<String> = emptyList()) =
        Reading(goodKeys = good, avoidKeys = avoid, source = Sources.WHITE_BERYL_RAHU, key = "reading.Rahu", arg = "reading.Rahu.$date")

    /**
     * The combination period (*dus sbyor*) of each sign, the White Beryl's
     * chapter on the twelve houses at the moment (vol. 2, pp. 371–376;
     * docs/sources/combination-period.md): what is to be done and avoided
     * while the sign rises, and whether the period is to be accomplished
     * (lucky) or avoided (unlucky). The text holds it above every factor of
     * the day, since their works are all complete in its house.
     */
    val DUS_SBYOR: Map<ZodiacSign, Pair<Tone, Reading>> = mapOf(
        ZodiacSign.ARIES to dusSbyor("ARIES", Tone.BAD, good = listOf("empowerment", "ordination", "taking_servants", "cutting_hair_and_nails", "fighting_enemies", "war", "robbery", "seeking_connections", "fire_offerings", "fierce_rites"), avoid = listOf("consecration", "building_temples_and_stupas", "making_images", "taking_a_bride", "building_walls", "bloodletting_and_moxibustion", "trade", "bringing_rain", "virtuous_acts_for_the_living", "council", "auspicious_work", "digging_ponds_canals_and_wells", "sowing", "worship_of_deities", "planting_trees_and_flowers", "pacifying_rites", "power_rites", "increasing_rites")),
        ZodiacSign.TAURUS to dusSbyor("TAURUS", Tone.GOOD, good = listOf("ordination", "fire_offerings", "power_rites", "fierce_rites", "putting_on_ornaments", "taking_a_bride", "first_wearing_of_new_clothes", "crafts", "bloodletting_and_moxibustion", "astrology_and_divination", "cutting_hair_and_nails", "worship_of_deities", "averting_rites", "suppressing_sri", "building_temples_and_stupas", "making_images", "presenting_offerings", "consecration", "building_walls", "buying_fields", "building_storehouses", "council", "auspicious_work", "virtuous_acts_for_the_living", "digging_ponds_canals_and_wells", "sowing", "sewing_tents", "raising_banners", "taking_servants", "matchmaking", "planting_trees_and_flowers", "breaking_in_horses_and_livestock", "trade", "taking_a_new_home", "spectacles"), avoid = listOf()),
        ZodiacSign.GEMINI to dusSbyor("GEMINI", Tone.GOOD, good = listOf("empowerment", "consecration", "dharma_practice", "worship_of_deities", "preparing_medicine", "building_temples", "making_images", "raising_banners", "taking_a_bride", "first_wearing_of_new_clothes", "putting_on_ornaments", "crafts", "digging_ponds_canals_and_wells", "cutting_hair_and_nails", "trade", "planting_trees_and_flowers", "spectacles", "bloodletting_and_moxibustion", "study", "naming", "increasing_rites"), avoid = listOf("power_rites", "fierce_rites", "pacifying_rites", "ordination", "building_walls")),
        ZodiacSign.CANCER to dusSbyor("CANCER", Tone.BAD, good = listOf("suppressing_sri", "digging_ponds_and_wells", "work_with_water", "increasing_rites", "bringing_rain", "leading_an_army", "unsteady_and_moving_work"), avoid = listOf("consecration", "building_walls", "first_wearing_of_new_clothes", "crafts", "auspicious_work", "council", "virtuous_acts_for_the_living", "taking_servants", "washing_the_hair", "cutting_hair_and_nails", "worship_of_deities", "making_images", "sowing", "building_storehouses", "raising_banners", "breaking_in_horses_and_livestock", "horse_racing", "preparing_medicine")),
        ZodiacSign.LEO to dusSbyor("LEO", Tone.GOOD, good = listOf("ordination", "building_temples_and_stupas", "worship_of_deities", "fire_offerings", "pacifying_rites", "power_rites", "averting_rites", "astrology_and_divination", "suppressing_sri", "putting_on_ornaments", "taking_a_bride", "enthronement", "building_walls", "building_a_hearth", "sewing_tents", "first_wearing_of_new_clothes", "crafts", "cutting_hair_and_nails", "virtuous_acts_for_the_living", "auspicious_work", "council", "honouring_and_petitioning", "empowerment", "spectacles", "breaking_in_horses_and_livestock", "horse_racing", "leading_an_army", "taking_a_retinue", "raising_banners", "trade", "reconciliation", "building_dams"), avoid = listOf()),
        ZodiacSign.VIRGO to dusSbyor("VIRGO", Tone.GOOD, good = listOf("building_temples", "making_images", "taking_a_bride", "putting_on_ornaments", "first_wearing_of_new_clothes", "crafts", "washing_the_hair", "preparing_medicine", "bloodletting_and_moxibustion", "building_walls", "reconciliation", "setting_out", "astrology_and_divination", "cutting_hair_and_nails", "auspicious_work", "virtuous_acts_for_the_living", "council", "building_dams", "trade", "pacifying_rites", "increasing_rites", "lasting_work", "unsteady_and_moving_work", "affairs_of_state"), avoid = listOf("ordination", "leading_an_army", "fierce_rites")),
        ZodiacSign.LIBRA to dusSbyor("LIBRA", Tone.BAD, good = listOf("farming", "trade", "breaking_ground", "lasting_work", "astrology_and_divination", "building_dams", "power_rites"), avoid = listOf("consecration", "making_images", "taking_a_bride", "crafts", "auspicious_work", "council", "virtuous_acts_for_the_living", "washing_the_hair", "bathing", "cutting_hair_and_nails", "building_walls", "planting_trees_and_flowers", "bringing_rain", "raising_banners", "naming", "taking_servants", "pacifying_rites", "increasing_rites")),
        ZodiacSign.SCORPIO to dusSbyor("SCORPIO", Tone.BAD, good = listOf("consecration", "empowerment", "averting_rites", "suppressing_sri", "serving_the_king", "lasting_work", "trade", "fierce_rites", "making_weapons"), avoid = listOf("building_temples", "making_images", "enthronement", "bringing_rain", "first_wearing_of_new_clothes", "building_walls", "taking_a_bride", "council", "auspicious_work", "virtuous_acts_for_the_living", "preparing_medicine", "bloodletting_and_moxibustion", "cutting_hair_and_nails", "sewing_tents", "building_storehouses", "leading_an_army", "farming", "sowing", "raising_banners", "worship_of_deities", "naming", "reconciliation", "pacifying_rites", "increasing_rites", "power_rites")),
        ZodiacSign.SAGITTARIUS to dusSbyor("SAGITTARIUS", Tone.GOOD, good = listOf("building_temples", "making_images", "suppressing_sri", "setting_out", "taking_a_bride", "putting_on_ornaments", "enthronement", "first_wearing_of_new_clothes", "preparing_medicine", "taking_a_retinue", "astrology_and_divination", "averting_rites", "sewing_tents", "building_walls", "digging_ponds_and_wells", "breaking_ground", "medical_treatment", "breaking_in_horses_and_livestock", "virtuous_acts_for_the_living", "council", "auspicious_work", "spectacles", "raising_banners", "leading_an_army", "naming", "averting_thieves", "pacifying_rites", "increasing_rites", "fierce_rites"), avoid = listOf("building_dams", "worship_of_deities", "ordination")),
        ZodiacSign.CAPRICORN to dusSbyor("CAPRICORN", Tone.BAD, good = listOf(), avoid = listOf("consecration", "building_temples", "making_images", "crafts", "auspicious_work", "virtuous_acts_for_the_living", "council", "fire_offerings", "taking_a_bride", "enthronement", "preparing_medicine", "bloodletting_and_moxibustion", "cutting_hair_and_nails", "worship_of_deities", "building_walls", "sewing_tents", "pacifying_rites", "increasing_rites", "power_rites", "reconciliation", "raising_banners")),
        ZodiacSign.AQUARIUS to dusSbyor("AQUARIUS", Tone.GOOD, good = listOf("ordination", "building_temples_and_stupas", "making_images", "bathing", "washing_the_hair", "building_walls", "taking_a_new_home", "digging_ponds_canals_and_wells", "breaking_ground", "learning_writing_and_astrology", "auspicious_work", "virtuous_acts_for_the_living", "council", "pacifying_rites", "increasing_rites", "lasting_work", "building_a_hearth", "building_storehouses", "sowing", "planting_trees_and_flowers", "sewing_tents", "naming"), avoid = listOf("bloodletting_and_moxibustion", "fierce_rites", "leading_an_army", "horse_racing", "building_dams")),
        ZodiacSign.PISCES to dusSbyor("PISCES", Tone.GOOD, good = listOf("dharma_practice", "ordination", "study", "putting_on_ornaments", "taking_a_bride", "enthronement", "first_wearing_of_new_clothes", "crafts", "cutting_hair_and_nails", "building_walls", "building_a_hearth", "preparing_medicine", "bloodletting_and_moxibustion", "astrology_and_divination", "washing_the_hair", "bathing", "breaking_ground", "farming", "work_with_cattle", "sowing", "travel", "learning_the_sciences", "setting_out", "sewing_tents", "reconciliation", "planting_trees_and_flowers", "spectacles", "naming", "pacifying_rites"), avoid = listOf("building_temples_and_stupas", "making_images", "increasing_rites", "fierce_rites", "leading_an_army", "building_dams", "fire_offerings")),
    )

    private fun dusSbyor(sign: String, tone: Tone, good: List<String>, avoid: List<String>) = tone to Reading(
        goodKeys = good, avoidKeys = avoid, source = Sources.WHITE_BERYL_DUS_SBYOR, also = listOf(Sources.KUN_PHAN_ME_LONG_DUS_SBYOR),
        key = "reading.DusSbyor", arg = "reading.DusSbyor.$sign",
    )

    /**
     * Rāhu's course by month (the White Beryl, vol. 2, pp. 238–239;
     * docs/sources/rahu.md): in the first two months of spring it moves "like a
     * messenger turning its head" through the parts of its body on given
     * dates, and in the three months of autumn it takes seven forms on given
     * dates, times and directions; on all of them fierce work is good. Keyed
     * by Tibetan month and date. Summer and winter are not given. The passage
     * is in WB's chapter 31, which counts its seasons by the Chinese reckoning
     * ([SeasonReckoning.CHINESE]: its p. 206 goes through the tiger to sheep
     * months and then "the three of autumn", and the bad days of p. 228 by
     * animal month are those of its "other way" by season-month): spring is
     * the 11th, 12th and 1st months, autumn the 5th to the 7th.
     */
    val RAHU_MONTH: Map<Pair<Int, Int>, Reading> = buildMap {
        val chinese = SeasonReckoning.CHINESE
        for (date in listOf(6, 9, 11, 13)) put(chinese.month(0) to date, rahuMonth("earlySpring.$date"))
        for (date in listOf(16, 9, 11, 13, 19)) put(chinese.month(1) to date, rahuMonth("midSpring.$date"))
        for (season in 6..8) for (date in listOf(15, 11, 8, 4, 22, 25, 29)) put(chinese.month(season) to date, rahuMonth("autumn.$date"))
    }

    private fun rahuMonth(arg: String) =
        Reading(goodKeys = listOf("fierce_rites"), source = Sources.WHITE_BERYL_RAHU, key = "reading.RahuMonth", arg = "reading.RahuMonth.$arg")

    /**
     * Jupiter's nectar periods ([zanshin.core.tibetan.nectarHours]): the rule,
     * and what the print's activity boxes name them good for (boxes 17, 19,
     * 26, 34, 49, 55, 60; docs/sources/kp-activities.md).
     */
    val NECTAR_PERIODS = Reading(
        goodKeys = listOf("auspicious_work", "reconciliation", "raising_banners", "preparing_medicine", "consecration", "bringing_rain", "council"),
        source = Sources.KUN_PHAN_ME_LONG_NECTAR, also = listOf(Sources.KUN_PHAN_ME_LONG),
        key = "reading.NectarPeriods",
    )

    /**
     * Rāhu's general course (the White Beryl, vol. 2, pp. 236–237, with the
     * rule for fierce work p. 238; the *kun phan me long*'s chart agrees
     * date by date; docs/sources/rahu.md), on the fourteen dates the detailed
     * course ([RAHU]) does not name: when and where Rāhu moves. No lists:
     * the course names no activities.
     */
    val RAHU_GENERAL: Map<Int, Reading> = (1..30).filter { it !in RAHU }.associateWith {
        Reading(
            source = Sources.WHITE_BERYL_RAHU, also = listOf(Sources.KUN_PHAN_ME_LONG_RAHU),
            key = "reading.RahuGeneral", arg = "reading.RahuGeneral.$it",
        )
    }

    /**
     * The special days of weekday and mansion (vol. 2, pp. 335–337, with the
     * table p. 341; docs/sources/combinations.md), with what each names good
     * and to avoid. In the weighing they speak as one, below the weekday and
     * the mansion, whose own results "are the main thing" (p. 337; SPEC §5.12).
     */
    val COMBINATION_DAY: Map<CombinationDay, Reading> = keyed(
        CombinationDay.GRUB_SBYOR to wbDay(good = listOf("everything")),
        CombinationDay.ZUNG_SBYOR to wbDay(good = listOf("pacifying_rites", "increasing_rites", "power_rites"), avoid = listOf("fierce_rites")),
        CombinationDay.BDUD_RGYAL to wbDay(good = listOf("fierce_rites", "subduing_enemies", "attacking_enemies")),
        CombinationDay.GRUB_NYI to wbDay(good = listOf("virtuous_work"), avoid = listOf("black_rites")),
        CombinationDay.BKRA_SHIS_NYI to wbDay(good = listOf("empowerment", "consecration", "worship_of_deities", "virtuous_work")),
        CombinationDay.PHEL_NYI to wbDay(good = listOf("virtuous_work", "learning_writing_and_astrology", "work_with_water", "digging_wells", "field_work", "sowing")),
        CombinationDay.CHUB_NYI to wbDay(good = listOf("virtuous_work")),
        CombinationDay.MTHUN_NYI to wbDay(good = listOf("reconciliation", "matchmaking", "helping_others")),
        CombinationDay.SBYOR_NYI to wbDay(good = listOf("worship_of_deities", "matchmaking", "spectacles", "virtuous_work", "increasing_rites"), avoid = listOf("black_rites")),
        CombinationDay.BDUD_NYI to wbDay(good = listOf("directing_magic"), avoid = listOf("marriage", "funerals", "setting_out")),
        CombinationDay.CHI_SBYOR to wbDay(avoid = listOf("building", "consecration", "long_life_and_prosperity_rites", "marriage", "setting_out")),
        CombinationDay.MI_PHROD_NYI to wbDay(good = listOf("subduing_enemies"), avoid = listOf("marriage", "funerals", "medical_treatment", "reconciliation")),
        CombinationDay.MI_MTHUN_NYI to wbDay(avoid = listOf("marriage", "funerals", "virtuous_work")),
        CombinationDay.JIG_NYI to wbDay(avoid = listOf("marriage", "funerals", "setting_out")),
        CombinationDay.GTAN_SPANG to wbDay(avoid = listOf("everything", "bloodletting_and_moxibustion")),
    )

    private fun wbDay(good: List<String> = emptyList(), avoid: List<String> = emptyList()) =
        Reading(goodKeys = good, avoidKeys = avoid, source = Sources.WHITE_BERYL_COMBINATION_DAYS)

    /**
     * The same kinds of special day reckoned by the *Rdo rje gtsug lag*,
     * which WB quotes after its own (p. 337, with the table p. 342;
     * docs/sources/combinations.md): each summary says so, adds what that
     * text says of the day where it says something, and gives WB's own
     * reading of the kind, whose lists it keeps.
     */
    val GTSUG_LAG_DAY: Map<CombinationDay, Reading> =
        listOf(CombinationDay.GRUB_NYI, CombinationDay.BDUD_NYI, CombinationDay.MI_PHROD_NYI, CombinationDay.MI_MTHUN_NYI, CombinationDay.JIG_NYI)
            .associateWith { c ->
                val own = COMBINATION_DAY.getValue(c)
                Reading(
                    goodKeys = own.goodKeys + listOfNotNull("dharma_practice".takeIf { c == CombinationDay.GRUB_NYI }),
                    avoidKeys = own.avoidKeys, source = Sources.WHITE_BERYL_GTSUG_LAG_DAYS,
                    key = "reading.GtsugLagDay.${c.name}", arg = "reading.CombinationDay.${c.name}",
                )
            }

    /**
     * The burning date (*bsreg tshes*, [zanshin.core.tibetan.BurningDate]): its reading after the
     * karaṇas (vol. 2, p. 351), where bloodletting, moxibustion and virtuous work fail, cremation is
     * bad and fierce work favoured; chapter 34 names it among the times to avoid for bloodletting and
     * moxa, funerals, supports and temples, and life and wealth (pp. 404, 414, 426;
     * docs/sources/burning-dates.md). It stands with the special days, as in WB's almanac (vol. 1,
     * p. 177; SPEC §5.12).
     */
    val BURNING_DATE = Reading(
        goodKeys = listOf("fierce_rites"),
        avoidKeys = listOf("bloodletting_and_moxibustion", "virtuous_work", "cremation", "funerals", "setting_up_supports", "health_and_wealth"),
        source = Sources.WHITE_BERYL_BURNING_DATES,
        key = "reading.BurningDate",
    )

    /**
     * The 28 named combinations of weekday and mansion, the great combination
     * ('phrod chen): the White Beryl's short reading of each with the gist of
     * its verse (vol. 2, pp. 331–333; docs/sources/combinations.md). No lists:
     * the verses name fortunes, not activities.
     */
    val GREAT_COMBINATION: Map<GreatCombination, Reading> =
        keyed(*GreatCombination.entries.map { it to Reading(source = Sources.WHITE_BERYL_COMBINATIONS) }.toTypedArray())


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

    /** Kyūreki festivals, and those kept by the Gregorian date, by their kanji as in [zanshin.core.kyureki.Festival]. */
    val JAPANESE_FESTIVAL: Map<String, Reading> = mapOf(
        "旧正月" to Reading(source = Sources.NAOJ_SEKKU),
        "人日の節句" to Reading(source = Sources.NAOJ_SEKKU),
        "上巳の節句" to Reading(source = Sources.NAOJ_SEKKU),
        "端午の節句" to Reading(source = Sources.NAOJ_SEKKU),
        "七夕" to Reading(source = Sources.NAOJ_SEKKU),
        "十五夜" to Reading(source = Sources.NAOJ_JUSANYA),
        "重陽の節句" to Reading(source = Sources.NAOJ_SEKKU),
        "十三夜" to Reading(source = Sources.NAOJ_JUSANYA),
        "お盆" to Reading(source = Sources.WP_OBON, license = License.CC_BY_SA),
    ).mapValues { (kanji, r) -> r.copy(key = "reading.Festival.$kanji") }

    val PERSONAL_DAY: Map<PersonalDay, Reading> = keyed(
        PersonalDay.LUCK to Reading(source = Sources.RABTEN),
        PersonalDay.LIFE to Reading(source = Sources.RABTEN),
        PersonalDay.ANTI to Reading(source = Sources.RABTEN),
    )

    /** One's personal mansions (the White Beryl, p. 330, with the Sakya manual's prose beside its table). */
    val PERSONAL_MANSION: Map<PersonalMansion, Reading> = keyed(
        *PersonalMansion.entries.map {
            it to Reading(source = Sources.WHITE_BERYL_PERSONAL_MANSIONS, also = listOf(Sources.NOR_BU_ME_LONG))
        }.toTypedArray(),
    )

    /** The works WB p. 338 names good on one's birth weekday and own weekday, as p. 312 does on the own weekday alone. */
    private val OWN_WEEKDAY_WORKS = listOf(
        "arraying_for_battle", "contests_of_skill", "pleading_a_case", "trade", "trials_of_strength", "dice", "horse_racing", "archery", "stirring_up_strife",
    )

    /**
     * One's own weekdays and mansion by the birth date (WB vol. 2, pp. 337–338): the birth and own
     * weekday's works, the mother's and friend's anything (p. 330), the child's middling, the enemy's
     * every work avoided, the birth mansion's works.
     */
    val OWN_DAY: Map<OwnDay, Reading> = keyed(
        OwnDay.BIRTH_WEEKDAY to Reading(goodKeys = OWN_WEEKDAY_WORKS, source = Sources.WHITE_BERYL_OWN_DAYS),
        OwnDay.OWN_WEEKDAY to Reading(goodKeys = OWN_WEEKDAY_WORKS, source = Sources.WHITE_BERYL_OWN_DAYS, also = listOf(Sources.WHITE_BERYL_PERSONAL_MANSIONS)),
        OwnDay.MOTHER_WEEKDAY to Reading(goodKeys = listOf("anything"), source = Sources.WHITE_BERYL_OWN_DAYS, also = listOf(Sources.WHITE_BERYL_PERSONAL_MANSIONS)),
        OwnDay.FRIEND_WEEKDAY to Reading(goodKeys = listOf("anything"), source = Sources.WHITE_BERYL_OWN_DAYS, also = listOf(Sources.WHITE_BERYL_PERSONAL_MANSIONS)),
        OwnDay.CHILD_WEEKDAY to Reading(source = Sources.WHITE_BERYL_PERSONAL_MANSIONS, also = listOf(Sources.WHITE_BERYL_OWN_DAYS)),
        OwnDay.ENEMY_WEEKDAY to Reading(avoidKeys = listOf("every_work"), source = Sources.WHITE_BERYL_OWN_DAYS),
        OwnDay.BIRTH_MANSION to Reading(
            goodKeys = listOf(
                "offerings_to_deities", "serving_the_lama", "generosity", "virtuous_works", "putting_on_ornaments_and_clothes",
                "taking_a_new_house", "planting_trees", "the_works_of_a_house",
            ),
            source = Sources.WHITE_BERYL_OWN_DAYS,
            also = listOf(Sources.PHUG_PA_DBYANGS_CHAR),
        ),
    )

    /** Lucky for the birth, own, mother and friend weekdays and the birth mansion; unlucky for the enemy weekday; none for the child's, which is middling. */
    fun ownDayTone(d: OwnDay): Tone = when (d) {
        OwnDay.ENEMY_WEEKDAY -> Tone.BAD
        OwnDay.CHILD_WEEKDAY -> Tone.NEUTRAL
        else -> Tone.GOOD
    }

    /** Lucky for the bla, srog and dbang skar, which both texts call good for anything; unlucky for the other three, which they call bad. */
    fun personalMansionTone(m: PersonalMansion): Tone = when (m) {
        PersonalMansion.BLA, PersonalMansion.SROG, PersonalMansion.DBANG -> Tone.GOOD
        else -> Tone.BAD
    }

    /**
     * The pebble readings: one aspect of the birth year against the same aspect
     * of the lunar date, the month or the year. The ranking and the predictions
     * for the vitality pebbles follow the White Beryl's chapter on obstacle
     * years; the day and month contrasts its divination of health.
     */
    val PEBBLES: Map<Kinship, Reading> = Kinship.entries.associateWith {
        Reading(key = "reading.Kinship", arg = "reading.${glossKey(it)}", source = Sources.WHITE_BERYL_PEBBLES, also = listOf(Sources.BERZIN_ASTROLOGY_1))
    }
}
