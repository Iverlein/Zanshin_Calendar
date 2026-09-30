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

/** The 27 yogas (sbyor ba), Janson (10.5), with the Tibetan names of the Phugpa almanacs. */
enum class Yoga(val wylie: String, val sanskrit: String) {
    VISHKAMBHA("rnam sel", "Viṣkambha"),
    PRITI("mdza' bo", "Prīti"),
    AYUSHMAN("tshe dang ldan pa", "Āyuṣmān"),
    SAUBHAGYA("skal bzang", "Saubhāgya"),
    SHOBHANA("dge byed", "Śobhana"),
    ATIGANDA("shin tu 'grams", "Atigaṇḍa"),
    SUKARMAN("las bzang", "Sukarman"),
    DHRITI("'dzin byed", "Dhṛti"),
    SHULA("zug rngu", "Śūla"),
    GANDA("'grams", "Gaṇḍa"),
    VRIDDHI("'phel", "Vṛddhi"),
    DHRUVA("brtan pa", "Dhruva"),
    VYAGHATA("yongs bsnun", "Vyāghāta"),
    HARSHANA("dga' ba", "Harṣaṇa"),
    VAJRA("rdo rje", "Vajra"),
    SIDDHI("dngos grub", "Siddhi"),
    VYATIPATA("phan tshun", "Vyatīpāta"),
    VARIYAS("mchog can", "Varīyas"),
    PARIGHA("yongs 'joms", "Parigha"),
    SHIVA("zhi ba", "Śiva"),
    SIDDHA("grub pa", "Siddha"),
    SADHYA("bsgrub bya", "Sādhya"),
    SHUBHA("dge ba", "Śubha"),
    SHUKLA("dkar po", "Śukla"),
    BRAHMA("tshangs pa", "Brahma"),
    INDRA("dbang po", "Indra"),
    VAIDHRITI("'khon 'dzin", "Vaidhṛti");

    val english: String get() = gloss(this)
}

/**
 * The 11 karaṇas (byed pa). Half-lunar-day number H (1–60): H = 1, 58, 59,
 * 60 are the four fixed ones, the rest cycle through the seven changing ones
 * as (H − 1) amod 7 (Janson §10 (viii)).
 */
enum class Karana(val wylie: String, val sanskrit: String) {
    VAVA("gdab pa", "Vava"),
    BALAVA("byis pa", "Bālava"),
    KAULAVA("rigs can", "Kaulava"),
    TAITILA("til brdung", "Taitila"),
    GARA("khyim skyes", "Gara"),
    VANIJA("tshong ba", "Vaṇija"),
    VISHTI("vishti", "Viṣṭi"),
    SHAKUNI("bkra shis", "Śakuni"),
    CATUSHPADA("rkang bzhi", "Catuṣpada"),
    NAGA("klu", "Nāga"),
    KIMSTUGHNA("mi sdug pa", "Kiṃstughna");

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
