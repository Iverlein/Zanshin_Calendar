/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import zanshin.core.texts.gloss
import zanshin.core.tibetan.Animal.BIRD
import zanshin.core.tibetan.Animal.DOG
import zanshin.core.tibetan.Animal.DRAGON
import zanshin.core.tibetan.Animal.HORSE
import zanshin.core.tibetan.Animal.MONKEY
import zanshin.core.tibetan.Animal.MOUSE
import zanshin.core.tibetan.Animal.OX
import zanshin.core.tibetan.Animal.PIG
import zanshin.core.tibetan.Animal.RABBIT
import zanshin.core.tibetan.Animal.SHEEP
import zanshin.core.tibetan.Animal.SNAKE
import zanshin.core.tibetan.Animal.TIGER
import zanshin.core.tibetan.Mansion.ANURADHA
import zanshin.core.tibetan.Mansion.ARDRA
import zanshin.core.tibetan.Mansion.ASHLESHA
import zanshin.core.tibetan.Mansion.ASHVINI
import zanshin.core.tibetan.Mansion.BHARANI
import zanshin.core.tibetan.Mansion.CITRA
import zanshin.core.tibetan.Mansion.DHANISHTHA
import zanshin.core.tibetan.Mansion.HASTA
import zanshin.core.tibetan.Mansion.JYESHTHA
import zanshin.core.tibetan.Mansion.KRITTIKA
import zanshin.core.tibetan.Mansion.MAGHA
import zanshin.core.tibetan.Mansion.MRIGASHIRAS
import zanshin.core.tibetan.Mansion.MULA
import zanshin.core.tibetan.Mansion.PUNARVASU
import zanshin.core.tibetan.Mansion.PURVABHADRAPADA
import zanshin.core.tibetan.Mansion.PURVAPHALGUNI
import zanshin.core.tibetan.Mansion.PURVASHADHA
import zanshin.core.tibetan.Mansion.PUSHYA
import zanshin.core.tibetan.Mansion.REVATI
import zanshin.core.tibetan.Mansion.ROHINI
import zanshin.core.tibetan.Mansion.SHATABHISHAJ
import zanshin.core.tibetan.Mansion.SHRAVANA
import zanshin.core.tibetan.Mansion.SVATI
import zanshin.core.tibetan.Mansion.UTTARABHADRAPADA
import zanshin.core.tibetan.Mansion.UTTARAPHALGUNI
import zanshin.core.tibetan.Mansion.UTTARASHADHA
import zanshin.core.tibetan.Mansion.VISHAKHA
import zanshin.core.tibetan.Trigram.GIN
import zanshin.core.tibetan.Trigram.KHAM
import zanshin.core.tibetan.Trigram.KHON
import zanshin.core.tibetan.Trigram.LI
import zanshin.core.tibetan.Trigram.ZIN
import zanshin.core.tibetan.Trigram.ZON
import zanshin.core.tibetan.Weekday.FRIDAY
import zanshin.core.tibetan.Weekday.MONDAY
import zanshin.core.tibetan.Weekday.SATURDAY
import zanshin.core.tibetan.Weekday.SUNDAY
import zanshin.core.tibetan.Weekday.THURSDAY
import zanshin.core.tibetan.Weekday.TUESDAY
import zanshin.core.tibetan.Weekday.WEDNESDAY

/** The factors of a day that the activity lists weigh, as the app shows them (SPEC §5.10). */
enum class ElectionalFactor {
    WEEKDAY,
    LUNAR_DATE,
    MANSION,
    DAY_ANIMAL,
    TRIGRAM;

    val english: String get() = gloss(this)
}

/** Weekdays, lunar dates, mansions, day animals and trigrams named for an activity. */
data class Factors(
    val weekdays: Set<Weekday> = emptySet(),
    val dates: Set<Int> = emptySet(),
    val mansions: List<Mansion> = emptyList(),
    val animals: Set<Animal> = emptySet(),
    val trigrams: Set<Trigram> = emptySet(),
)

/**
 * One activity of the lists, with what is good and bad for it. [named] holds
 * the mansions the source names only with a qualifier or in parentheses: they
 * count as good or bad for nothing, and a mansion named in two of these
 * places for one activity (good and bad, or also with a qualifier) is left
 * out (SPEC §5.10).
 */
class ActivityList(val wording: String, good: Factors, bad: Factors, named: List<Mansion> = emptyList()) {
    private val twice = (good.mansions + bad.mansions + named).groupingBy { it }.eachCount().filterValues { it > 1 }.keys
    val good = good.copy(mansions = good.mansions.distinct() - twice)
    val bad = bad.copy(mansions = bad.mansions.distinct() - twice)
}

/**
 * Electional astrology of the five components, after Edward Henning's
 * "Horary and electional astrology of the five components" (kalacakra.org):
 * the lunar mansions' natures and activities (after the White Beryl and the
 * Treasury of Jewels) and his selection from the activity lists of the
 * 'bras rtsis bai dkar dgongs don kun phan me long. Where his lists name a
 * mansion twice, the mansions follow the print he translated (BDRC
 * W4CZ65561, read box by box in docs/sources/mansions.md): its abbreviations
 * of khrums stod and khrums smad had been read as chu smad. Rising signs are
 * left out, since the app shows a day, not a moment, and so is Abhijit, which
 * the Phugpa calendar does not count among the day's mansions.
 */
object Electional {
    private fun m(vararg x: Mansion) = x.toList()

    val ACTIVITIES: List<ActivityList> = listOf(
        // 6. Offerings to deities. For the protectors and in general alike. Uttarāṣāḍhā
        // stands in both lists of the print, Uttarabhādrapadā in the good and the bad
        // half (open question 6), so it is left out.
        ActivityList(
            "offerings_to_deities",
            good = Factors(
                weekdays = setOf(MONDAY, FRIDAY, SUNDAY),
                dates = setOf(15),
                mansions = m(
                    MRIGASHIRAS, UTTARASHADHA, PUNARVASU, HASTA,
                    ROHINI, PUSHYA, SVATI, UTTARAPHALGUNI, KRITTIKA, ANURADHA, PURVASHADHA, PURVAPHALGUNI, JYESHTHA, MULA,
                    SHRAVANA, DHANISHTHA, SHATABHISHAJ, REVATI, UTTARABHADRAPADA,
                ),
                animals = setOf(SHEEP, DRAGON, HORSE),
                trigrams = setOf(KHAM, GIN, ZIN),
            ),
            bad = Factors(
                weekdays = setOf(TUESDAY, SATURDAY),
                dates = setOf(1),
                mansions = m(MAGHA, VISHAKHA, UTTARABHADRAPADA, ASHLESHA, BHARANI, CITRA),
                animals = setOf(TIGER),
            ),
        ),
        // 8. Taking a new home.
        ActivityList(
            "taking_a_new_home",
            good = Factors(
                weekdays = setOf(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY),
                dates = setOf(3, 7, 11, 12, 14, 15, 17, 19, 25),
                mansions = m(
                    ROHINI, MRIGASHIRAS, PUNARVASU, PUSHYA, MAGHA, PURVAPHALGUNI, UTTARAPHALGUNI, HASTA, CITRA, SVATI,
                    VISHAKHA, UTTARASHADHA, DHANISHTHA, SHRAVANA, SHATABHISHAJ, PURVABHADRAPADA, REVATI,
                ),
            ),
            bad = Factors(
                weekdays = setOf(SUNDAY),
                dates = setOf(10, 20, 22, 30),
                mansions = m(ASHVINI, ASHLESHA, MULA, ANURADHA, UTTARABHADRAPADA),
            ),
        ),
        // 12. Starting a journey. Every good weekday carries a direction, so none is kept.
        ActivityList(
            "setting_out_on_journeys",
            good = Factors(
                mansions = m(ASHVINI, PUSHYA, PUNARVASU, SVATI, ANURADHA, SHRAVANA, PURVASHADHA, DHANISHTHA, SHATABHISHAJ, UTTARABHADRAPADA),
                animals = setOf(BIRD),
            ),
            bad = Factors(
                weekdays = setOf(SATURDAY, TUESDAY),
                mansions = m(
                    BHARANI, KRITTIKA, ROHINI, MRIGASHIRAS, ARDRA, ASHLESHA, MAGHA, PURVAPHALGUNI, UTTARAPHALGUNI, HASTA,
                    CITRA, VISHAKHA, JYESHTHA, MULA, UTTARASHADHA, PURVABHADRAPADA, REVATI,
                ),
                animals = setOf(HORSE, DOG, MOUSE, SHEEP),
            ),
        ),
        // 38. Astrology and divination. The mouse is bad "for divination", this very activity.
        ActivityList(
            "astrology_and_divination",
            good = Factors(
                weekdays = setOf(THURSDAY, FRIDAY, SATURDAY, WEDNESDAY),
                mansions = m(
                    KRITTIKA, PURVAPHALGUNI, PUNARVASU, PUSHYA, MAGHA, UTTARAPHALGUNI, HASTA, UTTARASHADHA, MULA,
                    MRIGASHIRAS, ARDRA, SHRAVANA,
                ),
                animals = setOf(TIGER, SNAKE, DRAGON, MONKEY),
            ),
            bad = Factors(
                weekdays = setOf(TUESDAY),
                dates = setOf(9, 19, 29, 15, 30, 8),
                mansions = m(BHARANI, ASHLESHA, CITRA, JYESHTHA, VISHAKHA),
                animals = setOf(MOUSE),
            ),
        ),
        // 40. Making weapons such as arrows and lances.
        ActivityList(
            "making_weapons",
            good = Factors(
                weekdays = setOf(SUNDAY, SATURDAY),
                mansions = m(MRIGASHIRAS, ARDRA, UTTARABHADRAPADA, MULA, PURVASHADHA, CITRA, HASTA, MAGHA, BHARANI),
                animals = setOf(TIGER, DRAGON, OX),
                trigrams = setOf(KHAM),
            ),
            bad = Factors(
                weekdays = setOf(TUESDAY, THURSDAY, MONDAY),
                mansions = m(KRITTIKA, UTTARASHADHA, PURVAPHALGUNI),
                animals = setOf(HORSE, PIG, BIRD, RABBIT, MOUSE),
            ),
        ),
        // 42. Marriage.
        ActivityList(
            "marriage",
            good = Factors(
                weekdays = setOf(MONDAY, THURSDAY, FRIDAY),
                mansions = m(UTTARASHADHA, UTTARAPHALGUNI, SHRAVANA, PUNARVASU, PUSHYA, MRIGASHIRAS, VISHAKHA),
                animals = setOf(MONKEY, RABBIT, SHEEP),
            ),
            bad = Factors(
                weekdays = setOf(SUNDAY, WEDNESDAY, TUESDAY, SATURDAY),
                dates = setOf(1, 3, 4, 5, 7, 6, 8, 12, 15, 17, 19, 21, 27, 28, 30),
                mansions = m(
                    BHARANI, KRITTIKA, ROHINI, MAGHA, ANURADHA, JYESHTHA, MULA, REVATI, ARDRA, HASTA, ASHLESHA, CITRA,
                    UTTARABHADRAPADA, DHANISHTHA, SHATABHISHAJ, PURVAPHALGUNI, PURVABHADRAPADA, PURVASHADHA, ASHVINI,
                ),
                animals = setOf(BIRD, SNAKE, HORSE, MOUSE, DOG, OX),
                trigrams = setOf(LI),
            ),
            named = m(SVATI),
        ),
        // 45. Funereal activities.
        ActivityList(
            "funerals",
            good = Factors(
                weekdays = setOf(WEDNESDAY, THURSDAY, FRIDAY),
                dates = setOf(7, 17, 27, 15, 25),
                mansions = m(ASHVINI, REVATI, UTTARABHADRAPADA, PUNARVASU, SHATABHISHAJ, PUSHYA),
                animals = setOf(TIGER, MONKEY, DOG),
            ),
            bad = Factors(
                weekdays = setOf(SUNDAY, SATURDAY, TUESDAY, MONDAY),
                dates = setOf(10, 29, 20, 19, 1, 5, 23, 9, 28, 30),
                mansions = m(
                    ROHINI, ARDRA, PURVAPHALGUNI, MRIGASHIRAS, VISHAKHA, ANURADHA, PURVASHADHA, JYESHTHA, DHANISHTHA,
                    SHRAVANA, CITRA, BHARANI, MAGHA, HASTA, KRITTIKA,
                ),
                animals = setOf(RABBIT),
                trigrams = setOf(LI, KHON),
            ),
            named = m(MULA, ASHLESHA, SVATI, PURVABHADRAPADA, UTTARASHADHA),
        ),
        // 46. Setting up supports of body, speech and mind, temples and the like.
        ActivityList(
            "setting_up_supports",
            good = Factors(
                weekdays = setOf(WEDNESDAY, THURSDAY, FRIDAY, MONDAY),
                dates = setOf(1, 8, 10, 12, 13, 15, 18),
                mansions = m(
                    ROHINI, UTTARASHADHA, KRITTIKA, CITRA, MRIGASHIRAS, PUSHYA, UTTARAPHALGUNI, PURVAPHALGUNI, DHANISHTHA,
                    PUNARVASU, SHRAVANA, MULA, SVATI, SHATABHISHAJ,
                ),
                animals = setOf(SHEEP, DOG, OX, TIGER, DRAGON),
            ),
            bad = Factors(
                weekdays = setOf(SUNDAY, TUESDAY, SATURDAY),
                dates = setOf(20, 19, 21, 22, 27, 29),
                mansions = m(ASHLESHA, BHARANI, ARDRA, HASTA, JYESHTHA, VISHAKHA),
            ),
            named = m(ANURADHA),
        ),
        // 61. Destructive activity.
        ActivityList(
            "destructive_activity",
            good = Factors(
                weekdays = setOf(TUESDAY, SATURDAY),
                dates = setOf(4, 29, 11, 18, 22, 8, 15, 25, 26),
                mansions = m(
                    MRIGASHIRAS, ARDRA, ASHLESHA, MAGHA, ANURADHA, HASTA, PURVASHADHA, UTTARABHADRAPADA, MULA, UTTARAPHALGUNI,
                    ASHVINI, VISHAKHA, BHARANI, JYESHTHA,
                ),
                animals = setOf(TIGER, DRAGON),
                trigrams = setOf(ZON),
            ),
            bad = Factors(
                weekdays = setOf(MONDAY, WEDNESDAY, FRIDAY),
                mansions = m(PURVABHADRAPADA, SHRAVANA, SHATABHISHAJ, KRITTIKA, PURVAPHALGUNI),
                animals = setOf(SNAKE, RABBIT, DOG, SHEEP),
            ),
        ),
        // 62. Controlling activity. The "merely acceptable" mansions count for neither side.
        ActivityList(
            "controlling_activity",
            good = Factors(
                weekdays = setOf(FRIDAY, SUNDAY, TUESDAY, THURSDAY),
                dates = setOf(15, 4, 7, 21, 28, 30, 14, 3),
                mansions = m(
                    PUNARVASU, PUSHYA, HASTA, MAGHA, PURVASHADHA, CITRA, VISHAKHA, BHARANI, DHANISHTHA, REVATI,
                    PURVAPHALGUNI, SHATABHISHAJ, UTTARABHADRAPADA, UTTARAPHALGUNI, UTTARASHADHA, ASHVINI,
                ),
                animals = setOf(PIG),
            ),
            bad = Factors(
                weekdays = setOf(MONDAY, WEDNESDAY, SATURDAY),
                mansions = m(JYESHTHA, ASHLESHA, KRITTIKA, MRIGASHIRAS, ARDRA, SHRAVANA),
            ),
            named = m(ANURADHA, MULA, MRIGASHIRAS, PURVABHADRAPADA, SVATI),
        ),
        // 63. Pacifying activity. Five mansions are acceptable and two neutral; "all others are bad".
        ActivityList(
            "pacifying_activity",
            good = Factors(
                weekdays = setOf(WEDNESDAY, MONDAY),
                dates = setOf(1, 5, 9, 12, 16, 19, 23),
                mansions = m(ROHINI, PUNARVASU, PUSHYA, SVATI, HASTA, UTTARAPHALGUNI, UTTARASHADHA, REVATI, ASHVINI),
            ),
            bad = Factors(
                weekdays = setOf(SUNDAY, SATURDAY, TUESDAY),
                mansions = Mansion.entries - setOf(
                    ROHINI, PUNARVASU, PUSHYA, SVATI, HASTA, UTTARAPHALGUNI, UTTARASHADHA, REVATI, ASHVINI,
                    MRIGASHIRAS, MAGHA, PURVASHADHA, SHATABHISHAJ, SHRAVANA, KRITTIKA, PURVAPHALGUNI,
                ),
            ),
        ),
        // 64. Accomplishing health and wealth. Waxing dates are good but for the 6th, 7th and 9th; waning dates bad.
        ActivityList(
            "health_and_wealth",
            good = Factors(
                weekdays = setOf(SATURDAY, WEDNESDAY),
                dates = (1..15).toSet() - setOf(6, 7, 9),
                mansions = m(
                    PUNARVASU, ROHINI, PURVAPHALGUNI, UTTARAPHALGUNI, SVATI, SHRAVANA, UTTARASHADHA, PUSHYA, ARDRA,
                    JYESHTHA, ANURADHA, MULA, PURVASHADHA, PURVABHADRAPADA, CITRA,
                ),
                animals = setOf(DRAGON, BIRD, MOUSE, MONKEY),
                trigrams = setOf(GIN),
            ),
            bad = Factors(
                weekdays = setOf(SUNDAY, TUESDAY),
                dates = (16..30).toSet(),
                mansions = m(ASHVINI, BHARANI, ASHLESHA),
                animals = setOf(SHEEP),
            ),
        ),
        // 65. Increasing activity.
        ActivityList(
            "increasing_activity",
            good = Factors(
                weekdays = setOf(WEDNESDAY, MONDAY, FRIDAY),
                dates = setOf(2, 20, 17, 3, 10, 13, 27, 6),
                mansions = m(
                    PUNARVASU, PUSHYA, HASTA, MRIGASHIRAS, MAGHA, ASHVINI, PURVABHADRAPADA, ROHINI, SVATI, VISHAKHA,
                    ANURADHA, CITRA, REVATI,
                ),
            ),
            bad = Factors(
                weekdays = setOf(SATURDAY, TUESDAY),
                mansions = m(JYESHTHA, ASHLESHA, MULA, PURVASHADHA, SHRAVANA, DHANISHTHA, BHARANI, ARDRA, UTTARASHADHA, UTTARABHADRAPADA),
            ),
        ),
    )

    /**
     * What the list of lunar mansions names each one good for, after the kind
     * of work its first line gives (which stays in the mansion's reading).
     * Mūla's surgery, "on Tuesday and Saturday" only, is left out.
     */
    val MANSION_ACTIVITIES: Map<Mansion, List<String>> = mapOf(
        ASHVINI to listOf(ASTROLOGY, SOWING, MEDICINE, TRAVEL),
        BHARANI to listOf(),
        KRITTIKA to listOf(),
        ROHINI to listOf(MARRIAGE, TEMPLE, FOUNDATION, MUSIC, SOWING, MEDICINE),
        MRIGASHIRAS to listOf(HOME, MARRIAGE, TEMPLE, FOUNDATION, MEDICINE, TRAVEL),
        ARDRA to listOf(SURGERY),
        PUNARVASU to listOf(HOME, TEMPLE, ASTROLOGY, MEDICINE, TRAVEL),
        PUSHYA to listOf(TEMPLE, ASTROLOGY, MUSIC, SOWING, MEDICINE, SCRIPTURE, TRAVEL, WILL),
        ASHLESHA to listOf(HOME, SURGERY),
        MAGHA to listOf(HOME, SOWING),
        PURVAPHALGUNI to listOf(HOME, MUSIC),
        UTTARAPHALGUNI to listOf(MARRIAGE, TEMPLE, FOUNDATION, SOWING),
        HASTA to listOf(MARRIAGE, TEMPLE, FOUNDATION, ASTROLOGY, MUSIC, SOWING, MEDICINE, TRAVEL),
        CITRA to listOf(FOUNDATION, SOWING, MEDICINE),
        SVATI to listOf(MARRIAGE, TEMPLE, ASTROLOGY, SOWING, MEDICINE, SCRIPTURE),
        VISHAKHA to listOf(HOME),
        ANURADHA to listOf(MARRIAGE, MUSIC, SOWING, MEDICINE, TRAVEL),
        JYESHTHA to listOf(FOUNDATION, MUSIC, SURGERY),
        MULA to listOf(HOME, ASTROLOGY, SOWING, TRAVEL),
        PURVASHADHA to listOf(),
        UTTARASHADHA to listOf(MARRIAGE, TEMPLE, FOUNDATION, MUSIC, SOWING, MEDICINE),
        SHRAVANA to listOf(FOUNDATION, MEDICINE, SCRIPTURE, TRAVEL),
        DHANISHTHA to listOf(MUSIC, MEDICINE, STUDYING_MEDICINE, TRAVEL),
        SHATABHISHAJ to listOf(MUSIC, MEDICINE, STUDYING_MEDICINE),
        PURVABHADRAPADA to listOf(),
        UTTARABHADRAPADA to listOf(MARRIAGE, TEMPLE, MUSIC, MEDICINE),
        REVATI to listOf(HOME, MARRIAGE, ASTROLOGY, MUSIC, SOWING, MEDICINE, TRAVEL),
    )

    /** The activities whose lists name the factor good. */
    fun good(named: (Factors) -> Boolean): List<String> = ACTIVITIES.filter { named(it.good) }.map { it.wording }

    /** The activities whose lists name the factor bad. */
    fun bad(named: (Factors) -> Boolean): List<String> = ACTIVITIES.filter { named(it.bad) }.map { it.wording }
}

private const val ASTROLOGY = "learning_astrology"
private const val SOWING = "planting_and_sowing"
private const val MEDICINE = "medical_treatment"
private const val TRAVEL = "setting_out_on_journeys"
private const val MARRIAGE = "marriage"
private const val TEMPLE = "installing_a_deity"
private const val FOUNDATION = "laying_the_foundation_of_a_home"
private const val MUSIC = "learning_music_or_dance"
private const val HOME = "buying_a_home"
private const val SURGERY = "surgical_treatment"
private const val SCRIPTURE = "studying_scripture"
private const val STUDYING_MEDICINE = "studying_medicine"
private const val WILL = "making_a_will"
