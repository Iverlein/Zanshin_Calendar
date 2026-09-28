/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

/**
 * Tables for the daily entries of a Phugpa almanac (Janson §10 and
 * Appendix E). Names follow Edward Henning's Phugpa calendar archive and his
 * "Symbolic details of the Kālacakra calendar" (kalacakra.org); the English
 * glosses are this project's.
 */

/** The four elements of the Indian system, used for weekdays and lunar mansions. */
enum class IndianElement(val english: String, val wylie: String) {
    EARTH("Earth", "sa"), WATER("Water", "chu"), FIRE("Fire", "me"), WIND("Wind", "rlung")
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
enum class Mansion(val wylie: String, val sanskrit: String, val english: String, val element: IndianElement) {
    ASHVINI("tha skar", "Aśvinī", "horse-woman", IndianElement.WIND),
    BHARANI("bra nye", "Bharaṇī", "bearer", IndianElement.FIRE),
    KRITTIKA("smin drug", "Kṛttikā", "the Pleiades", IndianElement.FIRE),
    ROHINI("snar ma", "Rohiṇī", "the red one", IndianElement.EARTH),
    MRIGASHIRAS("mgo", "Mṛgaśiras", "deer's head", IndianElement.WIND),
    ARDRA("lag", "Ārdrā", "the moist one", IndianElement.WATER),
    PUNARVASU("nabs so", "Punarvasu", "return of the good", IndianElement.WIND),
    PUSHYA("rgyal", "Puṣya", "the nourisher", IndianElement.FIRE),
    ASHLESHA("skag", "Āśleṣā", "the embrace", IndianElement.WATER),
    MAGHA("mchu", "Maghā", "the bountiful", IndianElement.FIRE),
    PURVAPHALGUNI("gre", "Pūrvaphalgunī", "former reddish one", IndianElement.FIRE),
    UTTARAPHALGUNI("dbo", "Uttaraphalgunī", "latter reddish one", IndianElement.WIND),
    HASTA("me bzhi", "Hasta", "the hand", IndianElement.WIND),
    CITRA("nag pa", "Citrā", "the bright one", IndianElement.WIND),
    SVATI("sa ri", "Svātī", "the independent one", IndianElement.WIND),
    VISHAKHA("sa ga", "Viśākhā", "the forked one", IndianElement.FIRE),
    ANURADHA("lha mtshams", "Anurādhā", "following Rādhā", IndianElement.EARTH),
    JYESHTHA("snron", "Jyeṣṭhā", "the eldest", IndianElement.EARTH),
    MULA("snrubs", "Mūla", "the root", IndianElement.WATER),
    PURVASHADHA("chu stod", "Pūrvāṣāḍhā", "former invincible one", IndianElement.WATER),
    UTTARASHADHA("chu smad", "Uttarāṣāḍhā", "latter invincible one", IndianElement.EARTH),
    SHRAVANA("gro bzhin", "Śravaṇa", "hearing", IndianElement.EARTH),
    DHANISHTHA("mon gre", "Dhaniṣṭhā", "the wealthiest", IndianElement.WATER),
    SHATABHISHAJ("mon gru", "Śatabhiṣaj", "hundred physicians", IndianElement.EARTH),
    PURVABHADRAPADA("khrums stod", "Pūrvabhādrapadā", "former auspicious feet", IndianElement.FIRE),
    UTTARABHADRAPADA("khrums smad", "Uttarabhādrapadā", "latter auspicious feet", IndianElement.WATER),
    REVATI("nam gru", "Revatī", "the wealthy one", IndianElement.WATER),
}

/** The 27 yogas (sbyor ba), Janson (10.5), with the Tibetan names of the Phugpa almanacs. */
enum class Yoga(val wylie: String, val sanskrit: String, val english: String) {
    VISHKAMBHA("rnam sel", "Viṣkambha", "support"),
    PRITI("mdza' bo", "Prīti", "affection"),
    AYUSHMAN("tshe dang ldan pa", "Āyuṣmān", "long life"),
    SAUBHAGYA("skal bzang", "Saubhāgya", "good fortune"),
    SHOBHANA("dge byed", "Śobhana", "splendour"),
    ATIGANDA("shin tu 'grams", "Atigaṇḍa", "great danger"),
    SUKARMAN("las bzang", "Sukarman", "good deeds"),
    DHRITI("'dzin byed", "Dhṛti", "steadfastness"),
    SHULA("zug rngu", "Śūla", "spike"),
    GANDA("'grams", "Gaṇḍa", "danger"),
    VRIDDHI("'phel", "Vṛddhi", "growth"),
    DHRUVA("brtan pa", "Dhruva", "constancy"),
    VYAGHATA("yongs bsnun", "Vyāghāta", "striking"),
    HARSHANA("dga' ba", "Harṣaṇa", "delight"),
    VAJRA("rdo rje", "Vajra", "vajra"),
    SIDDHI("dngos grub", "Siddhi", "accomplishment"),
    VYATIPATA("phan tshun", "Vyatīpāta", "calamity"),
    VARIYAS("mchog can", "Varīyas", "excellence"),
    PARIGHA("yongs 'joms", "Parigha", "obstruction"),
    SHIVA("zhi ba", "Śiva", "peace"),
    SIDDHA("grub pa", "Siddha", "the accomplished"),
    SADHYA("bsgrub bya", "Sādhya", "what is to be accomplished"),
    SHUBHA("dge ba", "Śubha", "virtue"),
    SHUKLA("dkar po", "Śukla", "white"),
    BRAHMA("tshangs pa", "Brahma", "Brahmā"),
    INDRA("dbang po", "Indra", "Indra"),
    VAIDHRITI("'khon 'dzin", "Vaidhṛti", "discord"),
}

/**
 * The 11 karaṇas (byed pa). Half-lunar-day number H (1–60): H = 1, 58, 59,
 * 60 are the four fixed ones, the rest cycle through the seven changing ones
 * as (H − 1) amod 7 (Janson §10 (viii)).
 */
enum class Karana(val wylie: String, val sanskrit: String, val english: String) {
    VAVA("gdab pa", "Vava", "setting down"),
    BALAVA("byis pa", "Bālava", "the child"),
    KAULAVA("rigs can", "Kaulava", "of good family"),
    TAITILA("til rdung", "Taitila", "sesame pounder"),
    GARA("khyim skyes", "Gara", "born in the house"),
    VANIJA("tshong ba", "Vaṇija", "the merchant"),
    VISHTI("vishti", "Viṣṭi", "Viṣṭi (Bhadrā)"),
    SHAKUNI("bkra shis", "Śakuni", "auspicious"),
    CATUSHPADA("rkang bzhi", "Catuṣpada", "four-footed"),
    NAGA("klu", "Nāga", "nāga, serpent spirit"),
    KIMSTUGHNA("mi sdug pa", "Kiṃstughna", "unpleasant");

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
enum class ElementPair(
    val a: IndianElement,
    val b: IndianElement,
    val wylie: String,
    val sanskrit: String,
    val english: String,
    val auspicious: Boolean,
) {
    EARTH_EARTH(IndianElement.EARTH, IndianElement.EARTH, "dngos grub", "siddhi", "accomplishment", true),
    WATER_WATER(IndianElement.WATER, IndianElement.WATER, "bdud rtsi", "amṛta", "nectar", true),
    EARTH_WATER(IndianElement.EARTH, IndianElement.WATER, "lang tsho", "yauvana", "youth", true),
    FIRE_FIRE(IndianElement.FIRE, IndianElement.FIRE, "'phel 'gyur", "pragati", "progress", true),
    WIND_WIND(IndianElement.WIND, IndianElement.WIND, "phun tshogs", "saṃpanna", "excellence", true),
    FIRE_WIND(IndianElement.FIRE, IndianElement.WIND, "stobs ldan", "balayukta", "strength", true),
    EARTH_WIND(IndianElement.EARTH, IndianElement.WIND, "mi phrod", "alābha", "deficiency", false),
    WATER_WIND(IndianElement.WATER, IndianElement.WIND, "mi mthun", "pratikūla", "discord", false),
    EARTH_FIRE(IndianElement.EARTH, IndianElement.FIRE, "sreg pa", "dahana", "burning", false),
    FIRE_WATER(IndianElement.FIRE, IndianElement.WATER, "'chi ba", "maraṇa", "death", false);

    companion object {
        /** The pair is unordered: weekday and mansion elements in either order. */
        fun of(x: IndianElement, y: IndianElement): ElementPair =
            entries.first { (it.a == x && it.b == y) || (it.a == y && it.b == x) }
    }
}

/** The eight trigrams (spar kha) in Janson's Table 15 order. */
enum class Trigram(val wylie: String, val chinese: String, val english: String) {
    LI("li", "離", "fire"), KHON("khon", "坤", "earth"), DWA("dwa", "兌", "lake"), KHEN("khen", "乾", "sky"),
    KHAM("kham", "坎", "water"), GIN("gin", "艮", "mountain"), ZIN("zin", "震", "thunder"), ZON("zon", "巽", "wind"),
}

/** The nine numbers (sme ba) and their colours, Janson Table 16. */
val SME_BA_COLOURS = listOf("white", "black", "blue", "green", "yellow", "white", "red", "white", "red")

/** Monthly observances by lunar day, as listed in Rabten's Tibetan calendar. */
enum class SpecialDay(val day: Int, val english: String) {
    EIGHTH(8, "Eighth day"),
    TENTH(10, "Tenth day — tsok"),
    FULL_MOON(15, "Full moon — Sojong"),
    TWENTY_FIFTH(25, "Twenty-fifth day — tsok"),
    NEW_MOON(30, "New moon — Sojong"),
}

/**
 * Festivals on fixed Tibetan dates. Dates from Henning's Phugpa archive and
 * Rabten's calendars (Zamling Chisang, Gaden Ngamchö, the Ten Good Omens,
 * Thanksgiving to the Protectors); not held in leap months except Losar.
 */
enum class TibetanFestival(val month: Int, val day: Int, val title: String, val english: String) {
    LOSAR(1, 1, "Losar", "Tibetan New Year"),
    CHOTRUL_DUCHEN(1, 15, "Chötrul Düchen", "Day of Miracles"),
    KALACAKRA(3, 15, "Kālacakra", "Revelation of the Kālacakra Tantra"),
    BIRTH(4, 7, "Birth of the Buddha", "Birth of the Buddha"),
    SAGA_DAWA_DUCHEN(4, 15, "Saga Dawa Düchen", "Enlightenment and parinirvāṇa of the Buddha"),
    ZAMLING_CHISANG(5, 15, "Zamling Chisang", "Universal smoke offering to all protectors"),
    CHOKHOR_DUCHEN(6, 4, "Chökhor Düchen", "First turning of the wheel of Dharma"),
    ENTRY_INTO_WOMB(6, 15, "Entry into the womb", "The Buddha's entry into his mother's womb"),
    LHABAB_DUCHEN(9, 22, "Lhabab Düchen", "Descent of the Buddha from the realm of the gods"),
    GADEN_NGAMCHO(10, 25, "Gaden Ngamchö", "Parinirvāṇa of Je Tsongkhapa"),
    SANGPO_CHUZOM(11, 6, "Sangpo Chuzom", "Day of the Ten Good Omens"),
    PROTECTORS(12, 29, "Thanksgiving to the Protectors", "Thanksgiving offering to the Dharma protectors"),
}

enum class PersonalDay(val english: String) { LUCK("Luck day"), LIFE("Life day"), ANTI("Anti day") }

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
