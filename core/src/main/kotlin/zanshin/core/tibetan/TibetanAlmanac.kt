/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import zanshin.core.texts.gloss

/**
 * Tables for the daily entries of a Phugpa almanac (Janson §10 and
 * Appendix E). Names follow Edward Henning's Phugpa calendar archive and his
 * "Symbolic details of the Kālacakra calendar" (kalacakra.org), except where
 * the White Beryl spells them otherwise (til brdung, mi 'phrod); the English
 * glosses are this project's.
 */

/** The four elements of the Indian system, used for weekdays and lunar mansions. */
enum class IndianElement(val wylie: String) {
    EARTH("sa"), WATER("chu"), FIRE("me"), WIND("rlung");

    val english: String get() = gloss(this)
}

/** Weekday elements, Janson Table 5. */
val Weekday.element: IndianElement
    get() = when (this) {
        Weekday.SATURDAY, Weekday.FRIDAY -> IndianElement.EARTH
        Weekday.SUNDAY, Weekday.TUESDAY -> IndianElement.FIRE
        Weekday.MONDAY, Weekday.WEDNESDAY -> IndianElement.WATER
        Weekday.THURSDAY -> IndianElement.WIND
    }

/** The 27 lunar mansions (rgyu skar), numbered from 0 as in Janson (10.3). */
enum class Mansion(val wylie: String, val sanskrit: String, val element: IndianElement) {
    ASHVINI("tha skar", "Aśvinī", IndianElement.WIND),
    BHARANI("bra nye", "Bharaṇī", IndianElement.FIRE),
    KRITTIKA("smin drug", "Kṛttikā", IndianElement.FIRE),
    ROHINI("snar ma", "Rohiṇī", IndianElement.EARTH),
    MRIGASHIRAS("mgo", "Mṛgaśiras", IndianElement.WIND),
    ARDRA("lag", "Ārdrā", IndianElement.WATER),
    PUNARVASU("nabs so", "Punarvasu", IndianElement.WIND),
    PUSHYA("rgyal", "Puṣya", IndianElement.FIRE),
    ASHLESHA("skag", "Āśleṣā", IndianElement.WATER),
    MAGHA("mchu", "Maghā", IndianElement.FIRE),
    PURVAPHALGUNI("gre", "Pūrvaphalgunī", IndianElement.FIRE),
    UTTARAPHALGUNI("dbo", "Uttaraphalgunī", IndianElement.WIND),
    HASTA("me bzhi", "Hasta", IndianElement.WIND),
    CITRA("nag pa", "Citrā", IndianElement.WIND),
    SVATI("sa ri", "Svātī", IndianElement.WIND),
    VISHAKHA("sa ga", "Viśākhā", IndianElement.FIRE),
    ANURADHA("lha mtshams", "Anurādhā", IndianElement.EARTH),
    JYESHTHA("snron", "Jyeṣṭhā", IndianElement.EARTH),
    MULA("snrubs", "Mūla", IndianElement.WATER),
    PURVASHADHA("chu stod", "Pūrvāṣāḍhā", IndianElement.WATER),
    UTTARASHADHA("chu smad", "Uttarāṣāḍhā", IndianElement.EARTH),
    SHRAVANA("gro bzhin", "Śravaṇa", IndianElement.EARTH),
    DHANISHTHA("mon gre", "Dhaniṣṭhā", IndianElement.WATER),
    SHATABHISHAJ("mon gru", "Śatabhiṣaj", IndianElement.EARTH),
    PURVABHADRAPADA("khrums stod", "Pūrvabhādrapadā", IndianElement.FIRE),
    UTTARABHADRAPADA("khrums smad", "Uttarabhādrapadā", IndianElement.WATER),
    REVATI("nam gru", "Revatī", IndianElement.WATER);

    val english: String get() = gloss(this)
}

/**
 * The 27 yogas (sbyor ba), Janson (10.5), with the Tibetan names of the Phugpa
 * almanacs; [whiteBeryl] is the name the White Beryl gives the same yoga
 * (vol. 2, pp. 347–349 of the 1996 edition), which differs for most of them
 * (docs/sources/yogas.md).
 */
enum class Yoga(val wylie: String, val sanskrit: String, val whiteBeryl: String) {
    VISHKAMBHA("rnam sel", "Viṣkambha", "sel ba"),
    PRITI("mdza' bo", "Prīti", "mdza' bo"),
    AYUSHMAN("tshe dang ldan pa", "Āyuṣmān", "tshe ldan"),
    SAUBHAGYA("skal bzang", "Saubhāgya", "skal bzang"),
    SHOBHANA("dge byed", "Śobhana", "bzang po"),
    ATIGANDA("shin tu 'grams", "Atigaṇḍa", "rab stongs"),
    SUKARMAN("las bzang", "Sukarman", "las bzang"),
    DHRITI("'dzin byed", "Dhṛti", "'dzin pa"),
    SHULA("zug rngu", "Śūla", "gzer"),
    GANDA("'grams", "Gaṇḍa", "'bras"),
    VRIDDHI("'phel", "Vṛddhi", "'phel ba"),
    DHRUVA("brtan pa", "Dhruva", "nges pa"),
    VYAGHATA("yongs bsnun", "Vyāghāta", "rma chen"),
    HARSHANA("dga' ba", "Harṣaṇa", "dga' ba"),
    VAJRA("rdo rje", "Vajra", "rdo rje"),
    SIDDHI("dngos grub", "Siddhi", "dngos grub"),
    VYATIPATA("phan tshun", "Vyatīpāta", "kun brdungs"),
    VARIYAS("mchog can", "Varīyas", "dpa' bo"),
    PARIGHA("yongs 'joms", "Parigha", "yongs 'joms"),
    SHIVA("zhi ba", "Śiva", "zhi ba"),
    SIDDHA("grub pa", "Siddha", "grub pa"),
    SADHYA("bsgrub bya", "Sādhya", "grub bya"),
    SHUBHA("dge ba", "Śubha", "dge ba"),
    SHUKLA("dkar po", "Śukla", "dkar po"),
    BRAHMA("tshangs pa", "Brahma", "tshangs pa"),
    INDRA("dbang po", "Indra", "dbang po"),
    VAIDHRITI("'khon 'dzin", "Vaidhṛti", "sha 'khon");

    val english: String get() = gloss(this)
}

/**
 * The 11 karaṇas (byed pa). Half-lunar-day number H (1–60): H = 1, 58, 59,
 * 60 are the four fixed ones, the rest cycle through the seven changing ones
 * as (H − 1) amod 7 (Janson §10 (viii)). [whiteBeryl] is the name the White
 * Beryl gives it (vol. 2, pp. 349–351), where it differs from the almanacs'
 * for Bālava, Kaulava, Vaṇija and Catuṣpada; Viṣṭi is spelled as it prints it.
 */
enum class Karana(val wylie: String, val sanskrit: String, val whiteBeryl: String) {
    VAVA("gdab pa", "Vava", "gdab pa"),
    BALAVA("byis pa", "Bālava", "byis pa can"),
    KAULAVA("rigs can", "Kaulava", "dge ba"),
    TAITILA("til brdung", "Taitila", "til brdung"),
    GARA("khyim skyes", "Gara", "khyim skyes"),
    VANIJA("tshong ba", "Vaṇija", "tshong pa"),
    VISHTI("biSh+Ti", "Viṣṭi", "biSh+Ti"),
    SHAKUNI("bkra shis", "Śakuni", "bkra shis"),
    CATUSHPADA("rkang bzhi", "Catuṣpada", "bzhi mdo"),
    NAGA("klu", "Nāga", "klu"),
    KIMSTUGHNA("mi sdug pa", "Kiṃstughna", "mi sdug pa");

    val english: String get() = gloss(this)


    companion object {
        fun ofHalfDay(h: Int): Karana = when (h) {
            1 -> KIMSTUGHNA
            58 -> SHAKUNI
            59 -> CATUSHPADA
            60 -> NAGA
            else -> entries[(h - 2).mod(7)]
        }
    }
}

/**
 * The White Beryl's grades of the ten element pairs (vol. 2, p. 333), in the
 * order of its verse on them: «བཟང་གསུམ་གསོ་ཐུབ་གསུམ། །ངན་གསུམ་ཐ་ཆད་གཅིག», three
 * good, three that sustain (*gso thub*), three bad and one worst. The verses of
 * the three that sustain promise food and clothes, quick success and good
 * omens, so they are lucky with the good ones (docs/sources/combinations.md).
 */
enum class PairGrade(val lucky: Boolean) { GOOD(true), SUSTAINING(true), BAD(false), WORST(false) }

/**
 * The ten combinations of the weekday's and the mansion's element
 * (khams kyi sbyor ba, 'byung 'phrod), named after Henning's list, in the
 * order of the White Beryl's verse, with its [grade].
 */
enum class ElementPair(val a: IndianElement, val b: IndianElement, val wylie: String, val sanskrit: String, val grade: PairGrade) {
    EARTH_EARTH(IndianElement.EARTH, IndianElement.EARTH, "dngos grub", "siddhi", PairGrade.GOOD),
    WATER_WATER(IndianElement.WATER, IndianElement.WATER, "bdud rtsi", "amṛta", PairGrade.GOOD),
    EARTH_WATER(IndianElement.EARTH, IndianElement.WATER, "lang tsho", "yauvana", PairGrade.GOOD),
    FIRE_FIRE(IndianElement.FIRE, IndianElement.FIRE, "'phel 'gyur", "pragati", PairGrade.SUSTAINING),
    WIND_WIND(IndianElement.WIND, IndianElement.WIND, "phun tshogs", "saṃpanna", PairGrade.SUSTAINING),
    FIRE_WIND(IndianElement.FIRE, IndianElement.WIND, "stobs ldan", "balayukta", PairGrade.SUSTAINING),
    EARTH_WIND(IndianElement.EARTH, IndianElement.WIND, "mi 'phrod", "alābha", PairGrade.BAD),
    WATER_WIND(IndianElement.WATER, IndianElement.WIND, "mi mthun", "pratikūla", PairGrade.BAD),
    EARTH_FIRE(IndianElement.EARTH, IndianElement.FIRE, "sreg pa", "dahana", PairGrade.BAD),
    FIRE_WATER(IndianElement.FIRE, IndianElement.WATER, "'chi ba", "maraṇa", PairGrade.WORST);

    val auspicious: Boolean get() = grade.lucky

    val english: String get() = gloss(this)


    companion object {
        /** The pair is unordered: weekday and mansion elements in either order. */
        fun of(x: IndianElement, y: IndianElement): ElementPair =
            entries.first { (it.a == x && it.b == y) || (it.a == y && it.b == x) }
    }
}

/**
 * The 28 named combinations of weekday and mansion ('phrod chen), in the
 * order of the White Beryl's table (vol. 1, pp. 148–149): [lucky] as its
 * readings (vol. 2, pp. 331–333) call them. Counted over the 28 mansions in
 * the White Beryl's order, Abhijit after Śravaṇa, from Aśvinī on Sunday and
 * four mansions further on each weekday after it (docs/sources/combinations.md).
 */
enum class GreatCombination(val wylie: String, val lucky: Boolean) {
    KUN_DGA("kun dga'", true), DUS_DBYIG("dus dbyig", false), DUL("dul ba", true), SKYE_DGU("skye dgu", true),
    GZHON("gzhon", true), BYA_ROG("bya rog", false), RGYAL_MTSHAN("rgyal mtshan", true), DPAL_BEU("dpal be'u", true),
    RDO_RJE("rdo rje", false), THO_BA("tho ba", false), GDUGS("gdugs", true), GROGS("grogs", true),
    YID("yid", true), DOD("'dod", true), MGAL_ME("mgal me", false), RTSA_BTON("rtsa bton", false),
    CHI_BDAG("'chi bdag", false), MDA("mda'", false), GRUB("grub", true), MDUNG("mdung", false),
    BDUD_RTSI("bdud rtsi", true), GTUN_SHING("gtun shing", false), GLANG_PO("glang po", true), RTAG_MYOS("rtag myos", true),
    ZAD_PA("zad pa", false), GYO("g.yo", true), BRTAN("brtan", false), PHEL("'phel", true);

    val english: String get() = gloss(this)

    companion object {
        /** The mansion's place among the 28 in the White Beryl's order, Abhijit (byi bzhin) after Śravaṇa (gro bzhin). */
        fun place(m: Mansion): Int = if (m.ordinal <= Mansion.SHRAVANA.ordinal) m.ordinal else m.ordinal + 1

        /** Sunday starts at Aśvinī, each weekday after it four mansions on (Saturday at Śatabhiṣaj). */
        fun of(w: Weekday, m: Mansion): GreatCombination =
            entries[Math.floorMod(place(m) - 4 * Math.floorMod(w.ordinal - Weekday.SUNDAY.ordinal, 7), 28)]
    }
}

/**
 * The special days of weekday and mansion the White Beryl names (vol. 2,
 * pp. 335–337, its table p. 341; docs/sources/combinations.md): for each,
 * the mansions that make it on Sunday … Saturday, by WB's numbers (0 tha
 * skar … 21 gro bzhin … 26 nam gru; [ABHIJIT] for byi bzhin, never the day's
 * mansion), the second column of the table, where it has one, counted too.
 * [gtsugLag] is the other reckoning WB quotes after it from the *Rdo rje
 * gtsug lag* (p. 337, its table p. 342) for five of the kinds, other
 * mansions under the same names.
 */
enum class CombinationDay(
    val wylie: String,
    val lucky: Boolean,
    private val table: List<List<Int>>,
    private val gtsugLag: List<List<Int>>? = null,
) {
    GRUB_SBYOR("'grub sbyor", true, listOf(listOf(12), listOf(21), listOf(0), listOf(16), listOf(7), listOf(26), listOf(3))),
    ZUNG_SBYOR("zung sbyor", true, listOf(listOf(9), listOf(15), listOf(5), listOf(18), listOf(11), listOf(3), listOf(19))),
    BDUD_RGYAL("bdud rgyal", true, listOf(listOf(18), listOf(ABHIJIT), listOf(25), listOf(2), listOf(6), listOf(10), listOf(14))),
    GRUB_NYI(
        "grub nyi", true, listOf(listOf(18, 19), listOf(22, 13), listOf(25), listOf(2), listOf(7, 6), listOf(10), listOf(15, 14)),
        listOf(listOf(24, 25, 26, 20), listOf(21, 3), listOf(25, 26, 2), listOf(2, 23), listOf(6, 7), listOf(21, 0), listOf(21)),
    ),
    BKRA_SHIS_NYI("bkra shis nyi ma", true, listOf(listOf(25), listOf(8), listOf(14, 10), listOf(17), listOf(11), listOf(2), listOf(3, 14))),
    PHEL_NYI("'phel nyi", true, listOf(listOf(3), listOf(14, 19), listOf(7, 6), listOf(16), listOf(25), listOf(4), listOf(24, 22))),
    CHUB_NYI("chub nyi", true, listOf(listOf(9), listOf(15), listOf(5), listOf(18), listOf(24), listOf(3), listOf(19))),
    MTHUN_NYI("mthun nyi", true, listOf(listOf(17), listOf(25), listOf(24), listOf(1), listOf(0), listOf(9), listOf(26))),
    SBYOR_NYI("sbyor nyi", true, listOf(listOf(4), listOf(17), listOf(2), listOf(19, 1), listOf(23, 22), listOf(0, 21), listOf(12, 17))),
    BDUD_NYI(
        "bdud kyi nyi ma", false, listOf(listOf(2), listOf(11), listOf(8), listOf(19), listOf(4), listOf(7), listOf(23)),
        listOf(listOf(16, 1), listOf(7, 19, 20, 5), listOf(20, 22, 15), listOf(0, 4), listOf(4, 15), listOf(8, 26), listOf(11, 12)),
    ),
    CHI_SBYOR("'chi sbyor", false, listOf(listOf(16), listOf(2), listOf(23), listOf(0), listOf(4), listOf(3), listOf(12))),
    MI_PHROD_NYI(
        "mi 'phrod nyi ma", false, listOf(listOf(22, 21), listOf(15, 16), listOf(16), listOf(17, 18), listOf(21, 23), listOf(3), listOf(10)),
        listOf(listOf(9, 21, 22), listOf(5, 15, 16), listOf(5, 16, 18), listOf(18), listOf(21, 23, 26), listOf(3), listOf(10, 19, 20)),
    ),
    MI_MTHUN_NYI(
        "mi mthun nyi ma", false, listOf(listOf(17, 23), listOf(25), listOf(24, 0), listOf(1), listOf(16, 15), listOf(9), listOf(26, 13)),
        listOf(listOf(9, 17, 18, 23), listOf(15, ABHIJIT, 25), listOf(1, 0, 24, 5), listOf(1), listOf(15, 5, 16), listOf(9), listOf(13, 26)),
    ),
    JIG_NYI(
        "'jig pa'i nyi ma", false, listOf(listOf(15, 24), listOf(17, 3), listOf(26, 24, 23), listOf(12, 13), listOf(3), listOf(6, 10), listOf(1, 8)),
        listOf(listOf(15, 19, 24), listOf(3, 20, 23), listOf(23, 24), listOf(3, 13, 18, 26), listOf(3, 18), listOf(3, 6, 10), listOf(8, 19, 20)),
    ),
    GTAN_SPANG("gtan spang", false, listOf(listOf(9), listOf(15), listOf(5), listOf(18), listOf(22), listOf(3), listOf(19)));

    val english: String get() = gloss(this)

    /** The mansions that make this day on [w]. */
    fun mansions(w: Weekday): Set<Mansion> = on(table, w)

    /** The mansions that make this day on [w] by the *Rdo rje gtsug lag*; none for the kinds it does not reckon. */
    fun gtsugLagMansions(w: Weekday): Set<Mansion> = gtsugLag?.let { on(it, w) }.orEmpty()

    companion object {
        fun of(w: Weekday, m: Mansion): List<CombinationDay> = entries.filter { m in it.mansions(w) }

        /** The days the *Rdo rje gtsug lag* makes of [w] and [m] that WB's own table does not. */
        fun ofGtsugLag(w: Weekday, m: Mansion): List<CombinationDay> = entries.filter { m in it.gtsugLagMansions(w) && m !in it.mansions(w) }

        private fun on(t: List<List<Int>>, w: Weekday): Set<Mansion> =
            t[Math.floorMod(w.ordinal - Weekday.SUNDAY.ordinal, 7)].filter { it != ABHIJIT }.map { Mansion.entries[it] }.toSet()
    }
}

/** Abhijit in the table of [CombinationDay]: never the day's mansion in the Phugpa calendar. */
private const val ABHIJIT = -1

/**
 * The burning dates (*bsreg tshes*): a weekday meeting one of its two dates, one in each half of the
 * month, Sunday the 12th and 27th … Saturday the 7th and 22nd (the White Beryl, vol. 1, p. 177, and
 * again in number words, vol. 2, p. 351; docs/sources/burning-dates.md). The day's own date counts,
 * with each day's own weekday: a doubled date, on two weekdays, burns on one of its days at most.
 * WB also writes one, marked, where the burning date begins before nightfall on the weekday (vol. 1,
 * p. 177); that needs its length of daylight (vol. 1, ch. 8) and is not counted.
 */
object BurningDate {
    private val FIRST = mapOf(
        Weekday.SUNDAY to 12, Weekday.MONDAY to 11, Weekday.TUESDAY to 10, Weekday.WEDNESDAY to 3,
        Weekday.THURSDAY to 6, Weekday.FRIDAY to 2, Weekday.SATURDAY to 7,
    )

    /** The two dates that burn on [w]. */
    fun dates(w: Weekday): Set<Int> = FIRST.getValue(w).let { setOf(it, it + 15) }

    fun of(w: Weekday, date: Int): Boolean = date in dates(w)
}

/**
 * The eight trigrams (spar kha) in Janson's Table 15 order. [goddess] is the
 * one of the White Beryl's eight goddesses (lha mo brgyad, vol. 1,
 * pp. 449–450) whose day the date is: its "deeper" count by date and month
 * gives the date's trigram under her name.
 */
enum class Trigram(val wylie: String, val chinese: String, val goddess: String) {
    LI("li", "離", "'od 'bar ma"), KHON("khon", "坤", "bstan ma"), DWA("dwa", "兌", "dkar gsal ma"), KHEN("khen", "乾", "mdangs ldan ma"),
    KHAM("kham", "坎", "char 'bebs ma"), GIN("gin", "艮", "g.yo med ma"), ZIN("zin", "震", "'od 'chang ma"), ZON("zon", "巽", "skyob byed ma");

    val english: String get() = gloss(this)
}

/**
 * The five-fold cycle of the lunar dates (the White Beryl, pp. 302–303): dga'
 * ba on the 1st, 6th, 11th …, then bzang po, rgyal ba, stong pa and rdzogs pa.
 * The first three are virtuous, the last two not.
 */
enum class LunarDayClass(val wylie: String, val sanskrit: String, val virtuous: Boolean) {
    NANDA("dga' ba", "Nandā", true),
    BHADRA("bzang po", "Bhadrā", true),
    JAYA("rgyal ba", "Jayā", true),
    RIKTA("stong pa", "Riktā", false),
    PURNA("rdzogs pa", "Pūrṇā", false);

    val english: String get() = gloss(this)

    companion object {
        fun of(date: Int): LunarDayClass = entries[(date - 1) % 5]
    }
}

/** The nine numbers (sme ba) and their colours, Janson Table 16. */
val SME_BA_COLOURS = listOf("white", "black", "blue", "green", "yellow", "white", "red", "white", "red")

/** Monthly observances by lunar day, as listed in Rabten's Tibetan calendar. */
enum class SpecialDay(val day: Int) {
    EIGHTH(8),
    TENTH(10),
    FULL_MOON(15),
    TWENTY_FIFTH(25),
    NEW_MOON(30);

    val english: String get() = gloss(this)
}

/**
 * Festivals on fixed Tibetan dates. Dates from Henning's Phugpa archive and
 * Rabten's calendars (Zamling Chisang, Gaden Ngamchö, the Ten Good Omens,
 * Thanksgiving to the Protectors); not held in leap months except Losar.
 */
enum class TibetanFestival(val month: Int, val day: Int) {
    LOSAR(1, 1),
    CHOTRUL_DUCHEN(1, 15),
    KALACAKRA(3, 15),
    BIRTH(4, 7),
    SAGA_DAWA_DUCHEN(4, 15),
    ZAMLING_CHISANG(5, 15),
    CHOKHOR_DUCHEN(6, 4),
    ENTRY_INTO_WOMB(6, 15),
    LHABAB_DUCHEN(9, 22),
    GADEN_NGAMCHO(10, 25),
    SANGPO_CHUZOM(11, 6),
    PROTECTORS(12, 29);

    val title: String get() = gloss(this, "title")
    val english: String get() = gloss(this)
}

enum class PersonalDay {
    LUCK, LIFE, ANTI;

    val english: String get() = gloss(this)
}

/**
 * The six personal mansions of a birth-year animal (rgyu skar): bla skar,
 * srog skar, dbang skar, skeg skar, bdud skar and gshed skar.
 */
enum class PersonalMansion(val wylie: String) {
    BLA("bla skar"),
    SROG("srog skar"),
    DBANG("dbang skar"),
    SKEG("skeg skar"),
    BDUD("bdud skar"),
    GSHED("gshed skar");

    val english: String get() = gloss(this)
}

/**
 * Which of one's personal mansions the day's mansion is, by the animal of the
 * birth year: the White Beryl, vol. 2, p. 330 (1996), its slips settled by two
 * rtsis manuals that print the same table (docs/sources/personal-mansions.md).
 * A mansion can hold two roles (the Snake's dbang and gshed skar are both lag).
 */
fun personalMansions(birthAnimal: Animal, mansion: Mansion): List<PersonalMansion> {
    // bla, srog, dbang, skeg, bdud, gshed, as Mansion ordinals (0 = tha skar), Mouse to Pig.
    val row = when (birthAnimal) {
        Animal.MOUSE -> intArrayOf(19, 5, 2, 25, 9, 22)
        Animal.OX -> intArrayOf(16, 13, 11, 1, 7, 4)
        Animal.TIGER -> intArrayOf(4, 26, 8, 13, 11, 1)
        Animal.RABBIT -> intArrayOf(10, 26, 11, 25, 14, 17)
        Animal.DRAGON -> intArrayOf(2, 23, 16, 7, 8, 10)
        Animal.SNAKE -> intArrayOf(12, 11, 5, 7, 8, 5)
        Animal.HORSE -> intArrayOf(16, 11, 5, 19, 4, 26)
        Animal.SHEEP -> intArrayOf(7, 0, 1, 19, 4, 26)
        Animal.MONKEY -> intArrayOf(7, 0, 1, 8, 4, 16)
        Animal.BIRD -> intArrayOf(13, 6, 24, 2, 10, 23)
        Animal.DOG -> intArrayOf(8, 26, 4, 10, 2, 11)
        Animal.PIG -> intArrayOf(1, 7, 10, 25, 2, 11)
    }
    return PersonalMansion.entries.filter { row[it.ordinal] == mansion.ordinal }
}

/**
 * Luck, life and anti weekdays by the animal of one's birth year, from the
 * table in Rabten's Tibetan calendar ("Astrological year-signs and days").
 */
fun personalDay(birthAnimal: Animal, weekday: Weekday): PersonalDay? {
    val (luck, life, anti) = when (birthAnimal) {
        Animal.MOUSE -> Triple(Weekday.WEDNESDAY, Weekday.TUESDAY, Weekday.SATURDAY)
        Animal.OX -> Triple(Weekday.SATURDAY, Weekday.WEDNESDAY, Weekday.THURSDAY)
        Animal.TIGER -> Triple(Weekday.THURSDAY, Weekday.SATURDAY, Weekday.FRIDAY)
        Animal.RABBIT -> Triple(Weekday.THURSDAY, Weekday.SATURDAY, Weekday.FRIDAY)
        Animal.DRAGON -> Triple(Weekday.SUNDAY, Weekday.WEDNESDAY, Weekday.THURSDAY)
        Animal.SNAKE -> Triple(Weekday.TUESDAY, Weekday.FRIDAY, Weekday.WEDNESDAY)
        Animal.HORSE -> Triple(Weekday.TUESDAY, Weekday.FRIDAY, Weekday.WEDNESDAY)
        Animal.SHEEP -> Triple(Weekday.FRIDAY, Weekday.MONDAY, Weekday.THURSDAY)
        Animal.MONKEY -> Triple(Weekday.FRIDAY, Weekday.THURSDAY, Weekday.TUESDAY)
        Animal.BIRD -> Triple(Weekday.FRIDAY, Weekday.THURSDAY, Weekday.TUESDAY)
        Animal.DOG -> Triple(Weekday.MONDAY, Weekday.WEDNESDAY, Weekday.THURSDAY)
        Animal.PIG -> Triple(Weekday.WEDNESDAY, Weekday.TUESDAY, Weekday.SATURDAY)
    }
    return when (weekday) {
        luck -> PersonalDay.LUCK
        life -> PersonalDay.LIFE
        anti -> PersonalDay.ANTI
        else -> null
    }
}

/**
 * Weekday elements of the Chinese reckoning (*nag rtsis*), the White Beryl vol. 1, p. 257: Sun and
 * Mars fire, Moon and Mercury water, Jupiter wood, Venus iron, Saturn earth. Not [element], the
 * Indian four of the combinations.
 */
val Weekday.fiveElement: Element
    get() = when (this) {
        Weekday.SUNDAY, Weekday.TUESDAY -> Element.FIRE
        Weekday.MONDAY, Weekday.WEDNESDAY -> Element.WATER
        Weekday.THURSDAY -> Element.WOOD
        Weekday.FRIDAY -> Element.IRON
        Weekday.SATURDAY -> Element.EARTH
    }

/**
 * A person's own weekdays and mansion of the White Beryl, vol. 2, p. 338, that the birth date gives
 * (docs/sources/personal-mansions.md, question 14): the weekday of birth (p. 379); the weekdays
 * of one's element by the birth year's life force, which p. 330 says is reckoned as the clan's
 * element is, own, mother, friend, child and enemy by the tables of p. 346; and the mansion of the
 * birth date (Phug pa Lhun grub rgya mtsho's coarse reckoning). The own weekday is WB's *rang
 * gza'*, *bla gza'* and *dbang gza'* in one, not the birth animal's *bla gza'* of p. 330
 * ([PersonalDay.LUCK]).
 */
enum class OwnDay(val wylie: String) {
    BIRTH_WEEKDAY("skyes gza'"),
    OWN_WEEKDAY("rang gza'"),
    MOTHER_WEEKDAY("ma gza'"),
    FRIEND_WEEKDAY("grogs gza'"),
    CHILD_WEEKDAY("bu gza'"),
    ENEMY_WEEKDAY("dgra gza'"),
    BIRTH_MANSION("skyes skar");

    val english: String get() = gloss(this)
    val isWeekday: Boolean get() = this != BIRTH_MANSION
}

/** The element of one's own weekdays: the life force (*srog*) of the birth year (WB p. 330, «དེ་ལྟར་སྲོག་ལ་བརྩི་ཡང་འགྲེ»). */
fun ownElement(birth: TibetanDay): Element = Forces.of(birth.yearElement, birth.yearAnimal).vitality

/** What [weekday] is to [element] by the relations of the elements, as the table of WB p. 346 gives it: every weekday is one of the five. */
fun elementWeekday(element: Element, weekday: Weekday): OwnDay = when (Forces.kinship(element, weekday.fiveElement)) {
    Kinship.IDENTITY -> OwnDay.OWN_WEEKDAY
    Kinship.MOTHER -> OwnDay.MOTHER_WEEKDAY
    Kinship.FRIEND -> OwnDay.FRIEND_WEEKDAY
    Kinship.SON -> OwnDay.CHILD_WEEKDAY
    Kinship.ENEMY -> OwnDay.ENEMY_WEEKDAY
}

/**
 * Which of one's own days [day] is, for someone born on [birth]: the birth weekday, the weekday's
 * place among the element's five, and the birth mansion; weekdays first.
 */
fun ownDays(birth: TibetanDay, day: TibetanDay): List<OwnDay> = listOfNotNull(
    OwnDay.BIRTH_WEEKDAY.takeIf { day.weekday == birth.weekday },
    elementWeekday(ownElement(birth), day.weekday),
    OwnDay.BIRTH_MANSION.takeIf { day.mansion == birth.mansion },
)

/**
 * Jupiter's nectar periods (*bdud rtsi thun mtshams*), the *kun phan me long*
 * §10 (img. 81–82; docs/sources/nectar-periods.md). Each of the twelve double
 * hours from dawn is halved and each half has a ruling planet: by day the
 * first half of dawn is the weekday's own planet and each next one is counted
 * six on in the order of the weekdays, by night the first half after sunset
 * is the weekday's planet again and each next one is counted five on. The
 * halves Jupiter rules are the nectar periods. They come as clock hours from
 * the hare hour, the app's clock for the hours (§10.3): 0 is 05:00–06:00,
 * 12 is 17:00–18:00, 23 is 04:00–05:00.
 */
fun nectarHours(weekday: Weekday): List<Int> {
    // The tables number the planets as the weekdays: Saturn 0, Sun 1 … Venus 6, which are the ordinals.
    val jupiter = Weekday.THURSDAY.ordinal
    val day = (0 until 12).filter { (weekday.ordinal + 5 * it) % 7 == jupiter }
    val night = (0 until 12).filter { (weekday.ordinal + 4 * it) % 7 == jupiter }.map { it + 12 }
    return day + night
}

/** The twelve signs (*khyim*), Aries first, as the White Beryl and the *kun phan me long* name them. */
enum class ZodiacSign(val wylie: String) {
    ARIES("lug"), TAURUS("glang"), GEMINI("'khrig pa"), CANCER("karka Ta"), LEO("seng ge"), VIRGO("bu mo"),
    LIBRA("srang"), SCORPIO("sdig pa"), SAGITTARIUS("gzhu"), CAPRICORN("chu srin"), AQUARIUS("bum pa"), PISCES("nya");

    val english: String get() = gloss(this)
}

/**
 * The combination period (*dus sbyor*) of each of the day's twelve hours: the
 * sign rising in it, after the *kun phan me long*'s table (§9, img. 79–80;
 * docs/sources/combination-period.md). Its rows are the months, each with its
 * sign (the 3rd month Aries … the 2nd Pisces); at daybreak (ནམ་ལངས), the
 * first hour, the month's own sign rises, and each later hour the next sign.
 * [hour] counts the app's twelve hours from the hare hour at 05:00 (§10.3).
 */
fun risingSign(month: Int, hour: Int): ZodiacSign = ZodiacSign.entries[Math.floorMod(month - 3 + hour, 12)]
