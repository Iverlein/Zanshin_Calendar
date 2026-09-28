/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.kyureki

import zanshin.core.time.julianDayNumber
import java.time.Instant
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.concurrent.ConcurrentHashMap
import kotlin.math.abs

/*
 * 暦注 — the traditional almanac annotations of the Japanese calendar
 * (SPEC §7.5). Rules as tabulated in Japanese Wikipedia (十二直, 二十八宿,
 * 九星, 選日, 暦注下段, after 岡田芳朗・阿久根末忠『現代こよみ読み解き事典』,
 * 1993) and, for the 雑節, the definitions of the National Astronomical
 * Observatory of Japan (暦Wiki「雑節とは？」). "節月" is the solar month that
 * begins at each odd solar term (立春 = 寅月).
 */

enum class Tone { GOOD, BAD, MIXED, NEUTRAL }

/** A named annotation: its kanji, reading, English name and whether it is good or bad. */
interface Term {
    val kanji: String
    val reading: String
    val english: String
}

const val STEMS = "甲乙丙丁戊己庚辛壬癸"
const val BRANCHES = "子丑寅卯辰巳午未申酉戌亥"
private val STEM_READINGS = listOf("kinoe", "kinoto", "hinoe", "hinoto", "tsuchinoe", "tsuchinoto", "kanoe", "kanoto", "mizunoe", "mizunoto")
private val STEM_ENGLISH = listOf("Wood yang", "Wood yin", "Fire yang", "Fire yin", "Earth yang", "Earth yin", "Metal yang", "Metal yin", "Water yang", "Water yin")
private val BRANCH_READINGS = listOf("ne", "ushi", "tora", "u", "tatsu", "mi", "uma", "hitsuji", "saru", "tori", "inu", "i")
private val BRANCH_ENGLISH = listOf("Rat", "Ox", "Tiger", "Rabbit", "Dragon", "Snake", "Horse", "Sheep", "Monkey", "Rooster", "Dog", "Boar")

/** A sexagenary combination, index 0 = 甲子. */
data class Kanshi(val index: Int) : Term {
    val stem: Int get() = index % 10
    val branch: Int get() = index % 12
    override val kanji: String get() = "${STEMS[stem]}${BRANCHES[branch]}"
    override val reading: String get() = "${STEM_READINGS[stem]}-${BRANCH_READINGS[branch]}"
    override val english: String get() = "${STEM_ENGLISH[stem]} ${BRANCH_ENGLISH[branch]}"

    companion object {
        fun ofDay(jd: Long): Kanshi = Kanshi(Math.floorMod(jd + 49, 60L).toInt())
        fun ofYear(year: Int): Kanshi = Kanshi(Math.floorMod(year - 4, 60))
        fun branchName(branch: Int): String = "${BRANCHES[branch]} (${BRANCH_ENGLISH[branch]})"
    }
}

/** 十二直, the twelve "stations" of the middle band; tones after Todan's readings. */
enum class Choku(override val kanji: String, override val reading: String, override val english: String, val tone: Tone) : Term {
    TATSU("建", "tatsu", "establish", Tone.GOOD),
    NOZOKU("除", "nozoku", "remove", Tone.GOOD),
    MITSU("満", "mitsu", "full", Tone.GOOD),
    TAIRA("平", "taira", "level", Tone.GOOD),
    SADAN("定", "sadan", "settle", Tone.GOOD),
    TORU("執", "toru", "take in hand", Tone.GOOD),
    YABURU("破", "yaburu", "break", Tone.BAD),
    AYAUSHI("危", "ayaushi", "danger", Tone.BAD),
    NARU("成", "naru", "completion", Tone.GOOD),
    OSAN("納", "osan", "receive", Tone.GOOD),
    HIRAKU("開", "hiraku", "open", Tone.GOOD),
    TOZU("閉", "tozu", "close", Tone.BAD),
}

/** 二十八宿, the 28 lunar lodges of the almanac. */
enum class Shuku(override val kanji: String, override val reading: String, override val english: String) : Term {
    KAKU("角", "kaku", "Horn"), KO("亢", "kō", "Neck"), TEI("氐", "tei", "Root"), BO("房", "bō", "Room"),
    SHIN("心", "shin", "Heart"), BI("尾", "bi", "Tail"), KI("箕", "ki", "Winnowing Basket"),
    TO("斗", "to", "Dipper"), GYU("牛", "gyū", "Ox"), JO("女", "jo", "Girl"), KYO("虚", "kyo", "Emptiness"),
    KIH("危", "ki", "Rooftop"), SHITSU("室", "shitsu", "Encampment"), HEKI("壁", "heki", "Wall"),
    KEI("奎", "kei", "Legs"), RO("婁", "rō", "Bond"), I("胃", "i", "Stomach"), BO2("昴", "bō", "Hairy Head"),
    HITSU("畢", "hitsu", "Net"), SHI("觜", "shi", "Turtle Beak"), SHIN2("参", "shin", "Three Stars"),
    SEI("井", "sei", "Well"), KI2("鬼", "ki", "Ghost"), RYU("柳", "ryū", "Willow"), SEI2("星", "sei", "Star"),
    CHO("張", "chō", "Extended Net"), YOKU("翼", "yoku", "Wings"), SHIN3("軫", "shin", "Chariot"),
}

/** 九星, the nine stars. */
enum class KyuSei(override val kanji: String, override val reading: String, override val english: String) : Term {
    IPPAKU("一白水星", "ippaku suisei", "One White, Water"),
    JIKOKU("二黒土星", "jikoku dosei", "Two Black, Earth"),
    SANPEKI("三碧木星", "sanpeki mokusei", "Three Jade, Wood"),
    SHIROKU("四緑木星", "shiroku mokusei", "Four Green, Wood"),
    GOO("五黄土星", "goō dosei", "Five Yellow, Earth"),
    ROPPAKU("六白金星", "roppaku kinsei", "Six White, Metal"),
    SHICHISEKI("七赤金星", "shichiseki kinsei", "Seven Red, Metal"),
    HAPPAKU("八白土星", "happaku dosei", "Eight White, Earth"),
    KYUSHI("九紫火星", "kyūshi kasei", "Nine Purple, Fire");

    companion object {
        fun of(number: Int): KyuSei = entries[Math.floorMod(number - 1, 9)]
    }
}

/** 選日 and 暦注下段 — selected good and bad days. */
enum class Senjitsu(override val kanji: String, override val reading: String, override val english: String, val tone: Tone) : Term {
    TENSHA("天赦日", "tenshanichi", "Heaven's pardon", Tone.GOOD),
    ICHIRYU_MANBAI("一粒万倍日", "ichiryū manbaibi", "One grain, ten thousand fold", Tone.GOOD),
    DAIMYO("大明日", "daimyōnichi", "Great brightness", Tone.GOOD),
    TENON("天恩日", "ten'onnichi", "Heaven's grace", Tone.GOOD),
    BOSO("母倉日", "bosōnichi", "Mother's storehouse", Tone.GOOD),
    SETTOKU("節徳日", "settokunichi", "Virtue of the season", Tone.GOOD),
    KISHUKU("鬼宿日", "kishukunichi", "Ghost lodge day", Tone.GOOD),
    TORA("寅の日", "tora no hi", "Tiger day", Tone.GOOD),
    MI("巳の日", "mi no hi", "Snake day", Tone.GOOD),
    TSUCHINOTO_MI("己巳の日", "tsuchinoto-mi no hi", "Earth-yin Snake day", Tone.GOOD),
    KINOE_NE("甲子", "kinoe-ne", "Wood-yang Rat, first of the sixty", Tone.GOOD),
    TENICHI_TENJO("天一天上", "ten'ichi tenjō", "Ten'ichi in heaven", Tone.GOOD),
    JUSHI("受死日", "jushinichi", "Receiving death (black day)", Tone.BAD),
    JISSHI("十死日", "jisshinichi", "Tenfold death", Tone.BAD),
    KIKO("帰忌日", "kikonichi", "Return taboo", Tone.BAD),
    CHIIMI("血忌日", "chiiminichi", "Blood taboo", Tone.BAD),
    TENKA("天火日", "tenkanichi", "Heaven's fire", Tone.BAD),
    JIKA("地火日", "jikanichi", "Earth's fire", Tone.BAD),
    OMO("往亡日", "ōmōnichi", "Going and perishing", Tone.BAD),
    FUJOJU("不成就日", "fujōjubi", "Nothing accomplished", Tone.BAD),
    SANRINBO("三隣亡", "sanrinbō", "Ruin of three neighbours", Tone.BAD),
    JIPPOGURE("十方暮", "jippōgure", "Darkness in ten directions", Tone.BAD),
    HASSEN("八専", "hassen", "Eight concentrations", Tone.BAD),
    HASSEN_MABI("八専間日", "hassen mabi", "Rest day within hassen", Tone.NEUTRAL),
    OTSUCHI("大犯土", "ōtsuchi", "Great earth taboo", Tone.BAD),
    KOTSUCHI("小犯土", "kotsuchi", "Lesser earth taboo", Tone.BAD),
    TSUCHI_MABI("犯土間日", "tsuchi mabi", "Rest day between earth taboos", Tone.NEUTRAL),
    SAIGEJIKI("歳下食", "saigejiki", "Year's descending eater", Tone.BAD),
    JUNICHI("重日", "jūnichi", "Doubling day", Tone.MIXED),
    FUKUNICHI("復日", "fukunichi", "Repeating day", Tone.MIXED),
    KANOE_SARU("庚申", "kōshin", "Metal-yang Monkey (kōshin)", Tone.MIXED),
    TAIKA("大禍日", "taikanichi", "Great calamity (personal)", Tone.BAD),
    ROSHAKU("狼藉日", "rōshakunichi", "Havoc (personal)", Tone.BAD),
    METSUMON("滅門日", "metsumonnichi", "Ruin of the house (personal)", Tone.BAD),
}

/** 雑節 — seasonal markers defined by the NAOJ. */
enum class Zassetsu(override val kanji: String, override val reading: String, override val english: String) : Term {
    SETSUBUN("節分", "setsubun", "Parting of the seasons"),
    HIGAN_IRI("彼岸入り", "higan iri", "Start of higan"),
    HIGAN("彼岸", "higan", "Higan, equinox week"),
    HIGAN_CHUNICHI("彼岸の中日", "higan no chūnichi", "Middle day of higan"),
    HIGAN_AKE("彼岸明け", "higan ake", "End of higan"),
    SHANICHI("社日", "shanichi", "Day of the earth deity"),
    HACHIJUHACHIYA("八十八夜", "hachijūhachiya", "Eighty-eighth night"),
    NYUBAI("入梅", "nyūbai", "Start of the rainy season"),
    HANGESHO("半夏生", "hangeshō", "Crow-dipper sprouts"),
    DOYO_IRI("土用入り", "doyō iri", "Start of doyō"),
    DOYO("土用", "doyō", "Doyō, earth season"),
    DOYO_USHI("土用の丑の日", "doyō no ushi no hi", "Ox day of doyō"),
    NIHYAKUTOKA("二百十日", "nihyaku tōka", "Two-hundred-tenth day"),
    NIHYAKUHATSUKA("二百二十日", "nihyaku hatsuka", "Two-hundred-twentieth day"),
}

/** 恵方, the lucky direction of the year (where 歳徳神 resides), by the year's stem. */
enum class Ehou(override val kanji: String, override val reading: String, override val english: String) : Term {
    EAST_NORTHEAST("東北東やや東", "tōhokutō", "East-northeast (75°)"),
    WEST_SOUTHWEST("西南西やや西", "seinansei", "West-southwest (255°)"),
    SOUTH_SOUTHEAST("南南東やや南", "nannantō", "South-southeast (165°)"),
    NORTH_NORTHWEST("北北西やや北", "hokuhokusei", "North-northwest (345°)"),
}

data class RekichuDay(
    val date: LocalDate,
    val dayKanshi: Kanshi,
    /** Branch (0 = 子) of the solar month the day belongs to. */
    val setsuBranch: Int,
    val setsuStart: LocalDate,
    val choku: Choku,
    val shuku: Shuku,
    val dayStar: KyuSei,
    val monthStar: KyuSei,
    val yearStar: KyuSei,
    val senjitsu: List<Senjitsu>,
    val zassetsu: List<Zassetsu>,
    val ehou: Ehou,
)

object Rekichu {
    private val dayStarCache = ConcurrentHashMap<Int, List<Pair<LocalDate, Boolean>>>()

    /**
     * The annotations of [date]. [birth] adds the personal 三箇の悪日, which
     * depend on the branch of the birth year (reckoned from 立春).
     */
    fun of(date: LocalDate, birth: LocalDate? = null): RekichuDay {
        val jd = date.julianDayNumber()
        val kanshi = Kanshi.ofDay(jd)
        val (setsuBranch, setsuStart) = setsuOf(date)
        val kyureki = Kyureki.of(date)
        val setsuYear = setsuYearOf(date)

        val senjitsu = buildList {
            addAll(selectedDays(date, kanshi, setsuBranch, setsuStart, setsuYear))
            if (Shuku.entries[shukuIndex(jd)] == Shuku.KI2) add(Senjitsu.KISHUKU)
            if (fujojuDays(kyureki.month).contains(kyureki.day)) add(Senjitsu.FUJOJU)
            if (birth != null) addAll(personalDays(Math.floorMod(setsuYearOf(birth) - 4, 12), kanshi.branch, setsuBranch))
        }

        return RekichuDay(
            date = date,
            dayKanshi = kanshi,
            setsuBranch = setsuBranch,
            setsuStart = setsuStart,
            choku = chokuOf(date),
            shuku = Shuku.entries[shukuIndex(jd)],
            dayStar = dayStar(date),
            monthStar = monthStar(setsuYear, setsuBranch),
            yearStar = yearStar(setsuYear),
            senjitsu = senjitsu.sortedBy { it.ordinal },
            zassetsu = zassetsu(date, kanshi),
            ehou = ehouOf(setsuYear),
        )
    }

    // ---- solar months ----

    /** Branch of the solar month containing [date] and the JST date it began (its 節入り). */
    fun setsuOf(date: LocalDate): Pair<Int, LocalDate> {
        val setsu = (Kyureki.termsIn(date.year - 1) + Kyureki.termsIn(date.year))
            .filter { (term, _) -> (term.longitude - 315).mod(30) == 0 }
            .last { (_, d) -> !d.isAfter(date) }
        val branch = Math.floorMod((setsu.first.longitude - 315).mod(360) / 30 + 2, 12)
        return branch to setsu.second
    }

    /** The year as reckoned from 立春: dates before 立春 belong to the previous year. */
    fun setsuYearOf(date: LocalDate): Int {
        val risshun = Kyureki.termsIn(date.year).first { it.first == SolarTerm.RISSHUN }.second
        return if (date.isBefore(risshun)) date.year - 1 else date.year
    }

    // ---- 十二直 ----

    /**
    * 建 falls on the day whose branch equals the solar month's branch; on the
    * first day of a solar month the previous day's station repeats.
     */
    fun chokuOf(date: LocalDate): Choku {
        val (branch, start) = setsuOf(date)
        if (date == start) return chokuOf(date.minusDays(1))
        val dayBranch = Kanshi.ofDay(date.julianDayNumber()).branch
        return Choku.entries[Math.floorMod(dayBranch - branch, 12)]
    }

    // ---- 二十八宿 ----

    /**
     * The lodges run in an unbroken 28-day cycle: equivalent to the table of
     * weekday × branch group, with 角 on JD ≡ 17 (mod 28).
     */
    fun shukuIndex(jd: Long): Int = Math.floorMod(jd + 11, 28L).toInt()

    // ---- 九星 ----

    fun yearStar(setsuYear: Int): KyuSei {
        var r = Math.floorMod(setsuYear, 9)
        if (r == 0) r = 9
        if (r == 1) r = 10
        return KyuSei.of(11 - r)
    }

    /** 節の九星: by the year's branch group and the solar month (寅月 = column 0). */
    fun monthStar(setsuYear: Int, setsuBranch: Int): KyuSei {
        val yearBranch = Math.floorMod(setsuYear - 4, 12)
        val startForTora = when (yearBranch % 3) {
            0 -> 8 // 子・卯・午・酉
            1 -> 5 // 丑・辰・未・戌
            else -> 2 // 寅・巳・申・亥
        }
        val column = Math.floorMod(setsuBranch - 2, 12)
        return KyuSei.of(startForTora - column)
    }

    /**
     * 日の九星: 陽遁 (counting up) from the 甲子 nearest 冬至, which is 一白;
     * 陰遁 (counting down) from the 甲子 nearest 夏至, which is 九紫. When the
     * solstice falls on 甲午 or 癸巳 the later 甲子 is taken. When one half
     * runs 240 days its last 60 are the 九星 leap: the switch happens at the
     * 甲午 in its middle, starting from 七赤 (陽遁) or 三碧 (陰遁).
     */
    fun dayStar(date: LocalDate): KyuSei {
        val switches = (dayStarSwitches(date.year - 1) + dayStarSwitches(date.year) + dayStarSwitches(date.year + 1))
        val i = switches.indexOfLast { !it.first.isAfter(date) }
        val (start, yang) = switches[i]
        val (next, nextYang) = switches[i + 1]
        val length = ChronoUnit.DAYS.between(start, next)
        val leapSwitch = next.minusDays(30)
        return if (length == 240L && !date.isBefore(leapSwitch)) {
            val n = ChronoUnit.DAYS.between(leapSwitch, date).toInt()
            if (nextYang) KyuSei.of(7 + n) else KyuSei.of(3 - n)
        } else {
            val n = ChronoUnit.DAYS.between(start, date).toInt()
            if (yang) KyuSei.of(1 + n) else KyuSei.of(9 - n)
        }
    }

    /** Positions of the day-star leap in [fromYear]..[toYear]: the switch dates that end a 240-day half. */
    fun dayStarLeaps(fromYear: Int, toYear: Int): List<LocalDate> {
        val switches = (fromYear - 1..toYear + 1).flatMap { dayStarSwitches(it) }
        return switches.zipWithNext()
            .filter { (a, b) -> ChronoUnit.DAYS.between(a.first, b.first) == 240L }
            .map { it.second.first }
            .filter { it.year in fromYear..toYear }
    }

    /** The two regular switch days of Gregorian [year]: (date, true = 陽遁 starts). */
    private fun dayStarSwitches(year: Int): List<Pair<LocalDate, Boolean>> = dayStarCache.getOrPut(year) {
        val terms = Kyureki.termsIn(year)
        val geshi = terms.first { it.first == SolarTerm.GESHI }.second
        val toji = terms.first { it.first == SolarTerm.TOJI }.second
        listOf(nearestKinoeNe(geshi) to false, nearestKinoeNe(toji) to true)
    }

    private fun nearestKinoeNe(solstice: LocalDate): LocalDate {
        val index = Kanshi.ofDay(solstice.julianDayNumber()).index
        val toNext = Math.floorMod(-index, 60)
        return if (toNext <= 31) solstice.plusDays(toNext.toLong()) else solstice.minusDays((60 - toNext).toLong())
    }

    // ---- 選日 ----

    private fun b(s: String): Set<Int> = s.map { BRANCHES.indexOf(it) }.toSet()

    private fun st(s: String): Set<Int> = s.map { STEMS.indexOf(it) }.toSet()

    /** Rows indexed by solar-month branch 0 = 子 … 11 = 亥. */
    private fun bySetsu(vararg rows: Pair<String, String>): Map<Int, Set<Int>> =
        rows.flatMap { (months, days) -> months.map { BRANCHES.indexOf(it) to b(days) } }.toMap()

    private val ICHIRYU = bySetsu("寅" to "丑午", "卯" to "寅酉", "辰" to "子卯", "巳" to "卯辰", "午" to "巳午", "未" to "午酉",
        "申" to "子未", "酉" to "卯申", "戌" to "午酉", "亥" to "酉戌", "子" to "子亥", "丑" to "子卯")
    private val BOSO = bySetsu("寅卯" to "子亥", "巳午" to "寅卯", "辰未戌丑" to "巳午", "申酉" to "丑辰未戌", "亥子" to "申酉")
    private val JUSHI = bySetsu("寅" to "戌", "卯" to "辰", "辰" to "亥", "巳" to "巳", "午" to "子", "未" to "午",
        "申" to "丑", "酉" to "未", "戌" to "寅", "亥" to "申", "子" to "卯", "丑" to "酉")
    private val JISSHI = bySetsu("寅巳申亥" to "酉", "卯午酉子" to "巳", "辰未戌丑" to "丑")
    private val KIKO = bySetsu("寅巳申亥" to "丑", "卯午酉子" to "寅", "辰未戌丑" to "子")
    private val CHIIMI = bySetsu("寅" to "丑", "卯" to "未", "辰" to "寅", "巳" to "申", "午" to "卯", "未" to "酉",
        "申" to "辰", "酉" to "戌", "戌" to "巳", "亥" to "亥", "子" to "午", "丑" to "子")
    private val TENKA = bySetsu("寅午戌" to "子", "卯未亥" to "卯", "辰申子" to "午", "巳酉丑" to "酉")
    private val JIKA = bySetsu("寅" to "巳", "卯" to "午", "辰" to "未", "巳" to "申", "午" to "酉", "未" to "戌",
        "申" to "亥", "酉" to "子", "戌" to "丑", "亥" to "寅", "子" to "卯", "丑" to "辰")
    private val SANRINBO = bySetsu("寅巳申亥" to "亥", "卯午酉子" to "寅", "辰未戌丑" to "午")
    private val SETTOKU_STEM = mapOf("寅午戌" to "丙", "卯未亥" to "甲", "辰申子" to "壬", "巳酉丑" to "庚")
        .flatMap { (months, stem) -> months.map { BRANCHES.indexOf(it) to st(stem) } }.toMap()
    private val FUKU_STEMS = mapOf("寅申" to "甲庚", "卯酉" to "乙辛", "辰未戌丑" to "戊己", "巳亥" to "丙壬", "午子" to "丁癸")
        .flatMap { (months, stems) -> months.map { BRANCHES.indexOf(it) to st(stems) } }.toMap()

    /** 往亡日: the n-th day of the solar month, counting its first day as 1. */
    private val OMO_DAY = mapOf("寅" to 7, "卯" to 14, "辰" to 21, "巳" to 8, "午" to 16, "未" to 24,
        "申" to 9, "酉" to 18, "戌" to 27, "亥" to 10, "子" to 20, "丑" to 30).mapKeys { BRANCHES.indexOf(it.key[0]) }

    /** Sexagenary positions, 1-based as the sources list them. */
    private val DAIMYO = setOf(6, 7, 8, 9, 10, 14, 16, 19, 21, 24, 29, 32, 39, 41, 42, 43, 44, 46, 47, 48, 53, 55, 56, 57, 58)
    private val TENON = setOf(1, 2, 3, 4, 5, 16, 17, 18, 19, 20, 46, 47, 48, 49, 50)

    /** 歳下食 by the branch of the year: the one sexagenary day (1-based) it falls on. */
    private val SAIGEJIKI = listOf(14, 27, 4, 29, 54, 43, 44, 57, 34, 23, 48, 37)

    private fun selectedDays(date: LocalDate, k: Kanshi, setsu: Int, setsuStart: LocalDate, setsuYear: Int): List<Senjitsu> =
        buildList {
            val n = k.index + 1 // 1-based position from 甲子
            val season = when (setsu) {
                2, 3, 4 -> 0 // 寅卯辰: spring
                5, 6, 7 -> 1
                8, 9, 10 -> 2
                else -> 3
            }
            if (k.index == listOf(14, 30, 44, 0)[season]) add(Senjitsu.TENSHA)
            if (k.branch in ICHIRYU.getValue(setsu)) add(Senjitsu.ICHIRYU_MANBAI)
            if (n in DAIMYO) add(Senjitsu.DAIMYO)
            if (n in TENON) add(Senjitsu.TENON)
            if (k.branch in BOSO.getValue(setsu)) add(Senjitsu.BOSO)
            if (k.stem in SETTOKU_STEM.getValue(setsu)) add(Senjitsu.SETTOKU)
            if (k.branch == 2) add(Senjitsu.TORA)
            if (k.branch == 5) add(Senjitsu.MI)
            if (k.index == 5) add(Senjitsu.TSUCHINOTO_MI)
            if (k.index == 0) add(Senjitsu.KINOE_NE)
            if (n in 30..45) add(Senjitsu.TENICHI_TENJO)
            if (k.branch in JUSHI.getValue(setsu)) add(Senjitsu.JUSHI)
            if (k.branch in JISSHI.getValue(setsu)) add(Senjitsu.JISSHI)
            if (k.branch in KIKO.getValue(setsu)) add(Senjitsu.KIKO)
            if (k.branch in CHIIMI.getValue(setsu)) add(Senjitsu.CHIIMI)
            if (k.branch in TENKA.getValue(setsu)) add(Senjitsu.TENKA)
            if (k.branch in JIKA.getValue(setsu)) add(Senjitsu.JIKA)
            if (ChronoUnit.DAYS.between(setsuStart, date).toInt() + 1 == OMO_DAY.getValue(setsu)) add(Senjitsu.OMO)
            if (k.branch in SANRINBO.getValue(setsu)) add(Senjitsu.SANRINBO)
            if (n in 21..30) add(Senjitsu.JIPPOGURE)
            when (n) {
                50, 53, 55, 59 -> add(Senjitsu.HASSEN_MABI)
                in 49..60 -> add(Senjitsu.HASSEN)
            }
            when (n) {
                in 7..13 -> add(Senjitsu.OTSUCHI)
                14 -> add(Senjitsu.TSUCHI_MABI)
                in 15..21 -> add(Senjitsu.KOTSUCHI)
            }
            if (n == SAIGEJIKI[Math.floorMod(setsuYear - 4, 12)]) add(Senjitsu.SAIGEJIKI)
            if (k.branch == 5 || k.branch == 11) add(Senjitsu.JUNICHI)
            if (k.stem in FUKU_STEMS.getValue(setsu)) add(Senjitsu.FUKUNICHI)
            if (k.index == 56) add(Senjitsu.KANOE_SARU)
        }

    /** 不成就日 by kyūreki month (leap months use their number). */
    private fun fujojuDays(month: Int): Set<Int> = when ((month - 1) % 6) {
        0 -> setOf(3, 11, 19, 27)
        1 -> setOf(2, 10, 18, 26)
        2 -> setOf(1, 9, 17, 25)
        3 -> setOf(4, 12, 20, 28)
        4 -> setOf(5, 13, 21, 29)
        else -> setOf(6, 14, 22, 30)
    }

    /** 三箇の悪日: only in the solar month whose branch equals the birth year's branch. */
    private fun personalDays(birthBranch: Int, dayBranch: Int, setsu: Int): List<Senjitsu> {
        if (birthBranch != setsu) return emptyList()
        val (taika, roshaku, metsumon) = listOf(
            "酉午卯", "辰酉戌", "亥子巳", "午卯子", "丑午未", "申酉寅", "卯子酉", "戌卯辰", "巳午亥", "子酉午", "未子丑", "寅卯申",
        )[birthBranch].map { BRANCHES.indexOf(it) }
        return buildList {
            if (dayBranch == taika) add(Senjitsu.TAIKA)
            if (dayBranch == roshaku) add(Senjitsu.ROSHAKU)
            if (dayBranch == metsumon) add(Senjitsu.METSUMON)
        }
    }

    // ---- 雑節 ----

    private fun zassetsu(date: LocalDate, k: Kanshi): List<Zassetsu> = buildList {
        val terms = Kyureki.termsIn(date.year)
        fun termDate(t: SolarTerm) = terms.first { it.first == t }.second
        val risshun = termDate(SolarTerm.RISSHUN)
        val sinceRisshun = ChronoUnit.DAYS.between(risshun, date).toInt() + 1

        if (date == risshun.minusDays(1)) add(Zassetsu.SETSUBUN)
        for (equinox in listOf(SolarTerm.SHUNBUN, SolarTerm.SHUBUN)) {
            val mid = termDate(equinox)
            when (ChronoUnit.DAYS.between(mid, date)) {
                -3L -> add(Zassetsu.HIGAN_IRI)
                -2L, -1L, 1L, 2L -> add(Zassetsu.HIGAN)
                0L -> add(Zassetsu.HIGAN_CHUNICHI)
                3L -> add(Zassetsu.HIGAN_AKE)
            }
            if (date == shanichi(date.year, equinox.longitude.toDouble())) add(Zassetsu.SHANICHI)
        }
        if (sinceRisshun == 88) add(Zassetsu.HACHIJUHACHIYA)
        if (sinceRisshun == 210) add(Zassetsu.NIHYAKUTOKA)
        if (sinceRisshun == 220) add(Zassetsu.NIHYAKUHATSUKA)
        if (date == jst(date.year, 80.0)) add(Zassetsu.NYUBAI)
        if (date == jst(date.year, 100.0)) add(Zassetsu.HANGESHO)

        // 土用: from longitude 297°/27°/117°/207° to the day before 立春/立夏/立秋/立冬.
        for ((deg, end) in listOf(297.0 to SolarTerm.RISSHUN, 27.0 to SolarTerm.RIKKA, 117.0 to SolarTerm.RISSHU, 207.0 to SolarTerm.RITTO)) {
            val start = jst(date.year, deg)
            val endDate = termDate(end).let { if (it.isBefore(start)) Kyureki.termsIn(date.year + 1).first { t -> t.first == end }.second else it }
            if (date == start) add(Zassetsu.DOYO_IRI)
            else if (date.isAfter(start) && date.isBefore(endDate)) add(Zassetsu.DOYO)
            if (!date.isBefore(start) && date.isBefore(endDate) && k.branch == 1) add(Zassetsu.DOYO_USHI)
        }
    }

    private fun jst(year: Int, deg: Double): LocalDate = Kyureki.jstDateOf(Kyureki.solarLongitudeIn(year, deg))

    /**
     * 社日: the 戊 day nearest the equinox. When the equinox falls on a 癸
     * day both 戊 days are five days away; the one nearer the equinox instant
     * is taken (the NAOJ notes the tie was never settled).
     */
    private fun shanichi(year: Int, longitude: Double): LocalDate {
        val instant = Kyureki.solarLongitudeIn(year, longitude)
        val day = Kyureki.jstDateOf(instant)
        val stem = Kanshi.ofDay(day.julianDayNumber()).stem
        val after = day.plusDays(Math.floorMod(4 - stem, 10).toLong())
        val before = after.minusDays(10)
        fun distance(d: LocalDate) = abs(Instant.ofEpochSecond(d.atStartOfDay(Kyureki.JST).toEpochSecond() + 43200).epochSecond - instant.epochSecond)
        return if (ChronoUnit.DAYS.between(day, after) < 5) after
        else if (ChronoUnit.DAYS.between(before, day) < 5) before
        else if (distance(after) <= distance(before)) after else before
    }

    // ---- 恵方 ----

    fun ehouOf(setsuYear: Int): Ehou = when (Math.floorMod(setsuYear - 4, 10)) {
        0, 5 -> Ehou.EAST_NORTHEAST // 甲・己
        1, 6 -> Ehou.WEST_SOUTHWEST // 乙・庚
        3, 8 -> Ehou.NORTH_NORTHWEST // 丁・壬
        else -> Ehou.SOUTH_SOUTHEAST // 丙・辛・戊・癸
    }
}
