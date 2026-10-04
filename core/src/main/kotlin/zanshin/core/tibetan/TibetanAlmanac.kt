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
 * The ten combinations of the weekday's and the mansion's element
 * (khams kyi sbyor ba, 'byung 'phrod), named after Henning's list.
 */
enum class ElementPair(val a: IndianElement, val b: IndianElement, val wylie: String, val sanskrit: String, val auspicious: Boolean) {
    EARTH_EARTH(IndianElement.EARTH, IndianElement.EARTH, "dngos grub", "siddhi", true),
    WATER_WATER(IndianElement.WATER, IndianElement.WATER, "bdud rtsi", "amṛta", true),
    EARTH_WATER(IndianElement.EARTH, IndianElement.WATER, "lang tsho", "yauvana", true),
    FIRE_FIRE(IndianElement.FIRE, IndianElement.FIRE, "'phel 'gyur", "pragati", true),
    WIND_WIND(IndianElement.WIND, IndianElement.WIND, "phun tshogs", "saṃpanna", true),
    FIRE_WIND(IndianElement.FIRE, IndianElement.WIND, "stobs ldan", "balayukta", true),
    EARTH_WIND(IndianElement.EARTH, IndianElement.WIND, "mi 'phrod", "alābha", false),
    WATER_WIND(IndianElement.WATER, IndianElement.WIND, "mi mthun", "pratikūla", false),
    EARTH_FIRE(IndianElement.EARTH, IndianElement.FIRE, "sreg pa", "dahana", false),
    FIRE_WATER(IndianElement.FIRE, IndianElement.WATER, "'chi ba", "maraṇa", false);

    val english: String get() = gloss(this)


    companion object {
        /** The pair is unordered: weekday and mansion elements in either order. */
        fun of(x: IndianElement, y: IndianElement): ElementPair =
            entries.first { (it.a == x && it.b == y) || (it.a == y && it.b == x) }
    }
}

/** The eight trigrams (spar kha) in Janson's Table 15 order. */
enum class Trigram(val wylie: String, val chinese: String) {
    LI("li", "離"), KHON("khon", "坤"), DWA("dwa", "兌"), KHEN("khen", "乾"),
    KHAM("kham", "坎"), GIN("gin", "艮"), ZIN("zin", "震"), ZON("zon", "巽");

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
