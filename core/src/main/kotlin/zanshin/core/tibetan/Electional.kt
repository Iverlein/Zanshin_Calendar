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
import zanshin.core.tibetan.Trigram.KHEN
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
 * 'bras rtsis bai dkar dgongs don kun phan me long; then the print's other
 * boxes, read on its scans (docs/sources/kp-activities.md), with the same
 * rules: a qualified entry counts for neither side. Where his lists name a
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
        // The print's other boxes, read on the scans (docs/sources/kp-activities.md).
        // 2. Naming (img. 21, KP2 img. 240).
        ActivityList(
            "naming",
            good = Factors(
                mansions = m(BHARANI, MRIGASHIRAS, PUSHYA, PUNARVASU, UTTARAPHALGUNI, PURVASHADHA, UTTARASHADHA, UTTARABHADRAPADA, REVATI, ASHVINI),
            ),
            bad = Factors(
                mansions = m(PURVAPHALGUNI, CITRA, ASHLESHA, JYESHTHA, MULA),
            ),
        ),
        // 3. Cutting new clothes (img. 21, KP2 img. 240).
        ActivityList(
            "new_clothes",
            good = Factors(
                weekdays = setOf(MONDAY, WEDNESDAY, THURSDAY, FRIDAY),
                mansions = m(ROHINI, PUNARVASU, PUSHYA, HASTA, SVATI, VISHAKHA, ANURADHA, DHANISHTHA, CITRA, UTTARABHADRAPADA, UTTARASHADHA, SHRAVANA, ASHVINI),
                animals = setOf(DOG),
            ),
            bad = Factors(
                weekdays = setOf(SUNDAY, SATURDAY, TUESDAY),
                dates = setOf(8),
                mansions = m(BHARANI, KRITTIKA, MRIGASHIRAS, ARDRA, ASHLESHA, MAGHA, UTTARAPHALGUNI, JYESHTHA, MULA, PURVASHADHA, SHATABHISHAJ, REVATI, PURVABHADRAPADA, PURVAPHALGUNI),
                animals = setOf(MONKEY),
            ),
        ),
        // 5. Putting on new clothes (img. 23, KP2 img. 242).
        ActivityList(
            "first_wearing_of_new_clothes",
            good = Factors(
                weekdays = setOf(WEDNESDAY, THURSDAY, FRIDAY),
                mansions = m(HASTA, ROHINI, PUNARVASU, PUSHYA, SVATI, CITRA, VISHAKHA, SHRAVANA, ANURADHA, UTTARASHADHA, DHANISHTHA, UTTARABHADRAPADA, REVATI, ASHVINI),
            ),
            bad = Factors(
                weekdays = setOf(SUNDAY, TUESDAY, SATURDAY),
                dates = setOf(8),
                mansions = m(BHARANI, KRITTIKA, MRIGASHIRAS, ARDRA, ASHLESHA, MAGHA, PURVAPHALGUNI, JYESHTHA, MULA, PURVASHADHA, UTTARAPHALGUNI, PURVABHADRAPADA, SHATABHISHAJ),
                animals = setOf(MONKEY),
            ),
        ),
        // 7. Building walls and forts (img. 24, KP2 img. 243).
        ActivityList(
            "building_walls",
            good = Factors(
                weekdays = setOf(WEDNESDAY, FRIDAY),
                dates = setOf(3, 7, 11, 13, 15, 17, 14, 23, 25),
                mansions = m(PURVASHADHA, PUNARVASU, MRIGASHIRAS, UTTARAPHALGUNI, PURVAPHALGUNI, PURVABHADRAPADA, UTTARASHADHA, DHANISHTHA, REVATI, ROHINI),
                animals = setOf(TIGER, OX, SHEEP),
                trigrams = setOf(GIN, ZIN),
            ),
            bad = Factors(
                weekdays = setOf(TUESDAY),
                dates = setOf(10, 20, 30, 18, 22, 6, 16, 26),
                mansions = m(BHARANI, ASHLESHA, UTTARABHADRAPADA, ANURADHA, KRITTIKA, MAGHA, SHRAVANA, SHATABHISHAJ, VISHAKHA, JYESHTHA, ASHVINI, CITRA),
                animals = setOf(RABBIT, DRAGON, DOG, PIG, SNAKE),
                trigrams = setOf(KHON, KHAM, KHEN),
            ),
        ),
        // 9. Setting up hearth and pillars (img. 25, KP2 img. 244).
        ActivityList(
            "setting_up_hearth_and_pillars",
            good = Factors(
                weekdays = setOf(SUNDAY, MONDAY, THURSDAY, FRIDAY, SATURDAY),
                dates = setOf(2, 7, 8, 12, 13, 17, 18, 22, 23, 27),
                mansions = m(KRITTIKA, MRIGASHIRAS, PUSHYA, PURVAPHALGUNI, HASTA, UTTARAPHALGUNI, PURVABHADRAPADA, ASHVINI),
            ),
            bad = Factors(
                weekdays = setOf(TUESDAY, WEDNESDAY),
                mansions = m(ARDRA, MAGHA, MULA, PURVASHADHA, ASHLESHA, UTTARABHADRAPADA, DHANISHTHA, REVATI),
            ),
        ),
        // 10. Feasts, using vessels and household goods (img. 26, KP2 img. 245).
        ActivityList(
            "feasts",
            good = Factors(
                weekdays = setOf(FRIDAY, THURSDAY, SUNDAY),
                mansions = m(UTTARAPHALGUNI, MULA, REVATI, PUNARVASU, DHANISHTHA, SVATI, HASTA, MRIGASHIRAS),
                animals = setOf(DOG),
            ),
            bad = Factors(
                weekdays = setOf(TUESDAY, SATURDAY),
                dates = setOf(9, 19, 29, 1, 13, 10, 11, 20, 21, 30),
                mansions = m(ROHINI, PUSHYA, VISHAKHA, MAGHA, PURVAPHALGUNI, BHARANI, UTTARABHADRAPADA),
                animals = setOf(BIRD, RABBIT),
            ),
        ),
        // 11. Preparing food, brewing beer (img. 26, KP2 img. 245).
        ActivityList(
            "preparing_food_and_brewing",
            good = Factors(
                weekdays = setOf(MONDAY, THURSDAY, FRIDAY),
                dates = setOf(4),
                mansions = m(UTTARABHADRAPADA),
                animals = setOf(SHEEP),
            ),
            bad = Factors(
                weekdays = setOf(WEDNESDAY),
                dates = setOf(29),
            ),
        ),
        // 15. Trade and measuring grain (img. 30, KP2 img. 249).
        ActivityList(
            "trade",
            good = Factors(
                weekdays = setOf(WEDNESDAY, THURSDAY, FRIDAY),
                mansions = m(SHATABHISHAJ, ASHVINI, REVATI, MRIGASHIRAS, PUNARVASU, ROHINI, PURVABHADRAPADA, MULA, UTTARASHADHA, SVATI, SHRAVANA, ASHLESHA, PURVAPHALGUNI),
                animals = setOf(BIRD, MOUSE),
            ),
            bad = Factors(
                weekdays = setOf(TUESDAY, SATURDAY),
                dates = setOf(4, 5, 6, 7, 8, 9, 10, 11),
                mansions = m(HASTA, PUSHYA, DHANISHTHA, VISHAKHA, ARDRA),
                trigrams = setOf(GIN),
            ),
        ),
        // 15b. Giving and taking loans (img. 31, KP2 img. 250).
        ActivityList(
            "giving_and_taking_loans",
            good = Factors(
                weekdays = setOf(TUESDAY, WEDNESDAY),
                mansions = m(ROHINI, ARDRA, ASHLESHA, PUNARVASU),
                animals = setOf(SNAKE),
            ),
            bad = Factors(
                weekdays = setOf(FRIDAY),
                dates = setOf(15),
                mansions = m(SVATI, VISHAKHA, ANURADHA, MULA, SHRAVANA),
            ),
        ),
        // 16a. Receiving wealth (img. 31, KP2 img. 250).
        ActivityList(
            "receiving_wealth",
            good = Factors(
                weekdays = setOf(SUNDAY, SATURDAY, TUESDAY, WEDNESDAY),
                dates = setOf(3, 4, 5, 15, 21, 25),
                mansions = m(ROHINI, PUSHYA, MAGHA, PURVAPHALGUNI, MULA, PURVASHADHA, MRIGASHIRAS, SHATABHISHAJ, UTTARABHADRAPADA, REVATI, KRITTIKA, PUNARVASU, JYESHTHA, PURVABHADRAPADA, BHARANI),
            ),
            bad = Factors(
                weekdays = setOf(MONDAY, FRIDAY),
                dates = setOf(1, 6, 7, 8, 9, 10, 11, 12, 13, 16, 17, 18, 20, 26),
                mansions = m(HASTA, UTTARAPHALGUNI, CITRA, DHANISHTHA),
                animals = setOf(SNAKE),
            ),
        ),
        // 16b. Giving wealth away (img. 31, KP2 img. 250).
        ActivityList(
            "sending_out_wealth",
            good = Factors(
                dates = setOf(3, 4, 5, 15, 21, 25),
            ),
            bad = Factors(
                weekdays = setOf(SUNDAY, SATURDAY, TUESDAY, WEDNESDAY, MONDAY, FRIDAY),
                dates = setOf(1, 6, 7, 8, 9, 10, 11, 12, 13, 16, 17, 18, 20, 26),
                mansions = m(ROHINI, PUSHYA, MAGHA, PURVAPHALGUNI, MULA, PURVASHADHA, MRIGASHIRAS, SHATABHISHAJ, UTTARABHADRAPADA, REVATI, KRITTIKA, PUNARVASU, JYESHTHA, PURVABHADRAPADA, BHARANI, HASTA, UTTARAPHALGUNI, CITRA, DHANISHTHA),
                animals = setOf(SNAKE),
            ),
        ),
        // 17. Auspicious and virtuous rites (img. 32, KP2 img. 251).
        ActivityList(
            "auspicious_work",
            good = Factors(
                weekdays = setOf(SUNDAY, MONDAY, WEDNESDAY, FRIDAY, THURSDAY),
                mansions = m(ASHVINI, KRITTIKA, MRIGASHIRAS, UTTARAPHALGUNI, HASTA, CITRA, SVATI, JYESHTHA, PURVASHADHA, SHRAVANA, REVATI, UTTARASHADHA, SHATABHISHAJ),
            ),
            bad = Factors(
                weekdays = setOf(TUESDAY, SATURDAY),
                mansions = m(BHARANI, ARDRA, ASHLESHA, MULA),
            ),
        ),
        // 18. Planting trees and flowers (img. 33, KP2 img. 252).
        ActivityList(
            "planting_trees_and_flowers",
            good = Factors(
                weekdays = setOf(MONDAY, WEDNESDAY, THURSDAY, SATURDAY),
                mansions = m(MRIGASHIRAS, ARDRA, PUSHYA, PURVASHADHA, REVATI, SHATABHISHAJ, JYESHTHA, SVATI, ASHLESHA, UTTARASHADHA, PURVABHADRAPADA),
                animals = setOf(BIRD, MONKEY),
                trigrams = setOf(LI, ZIN),
            ),
            bad = Factors(
                weekdays = setOf(TUESDAY),
                mansions = m(BHARANI, KRITTIKA, MAGHA, HASTA, UTTARAPHALGUNI, VISHAKHA),
            ),
        ),
        // 19. Making peace between enemies (img. 33, KP2 img. 252).
        ActivityList(
            "reconciliation",
            good = Factors(
                weekdays = setOf(MONDAY, WEDNESDAY, THURSDAY),
                dates = setOf(8),
                mansions = m(SVATI, PURVABHADRAPADA, UTTARAPHALGUNI, UTTARASHADHA, SHRAVANA, HASTA, ANURADHA, ASHVINI, SHATABHISHAJ, PUSHYA),
                animals = setOf(DOG, OX),
            ),
            bad = Factors(
                weekdays = setOf(SUNDAY, TUESDAY, SATURDAY),
                dates = setOf(4),
                mansions = m(MRIGASHIRAS, ARDRA, ASHLESHA, MAGHA, CITRA, PURVASHADHA, JYESHTHA, VISHAKHA, REVATI, UTTARABHADRAPADA),
                animals = setOf(HORSE),
            ),
        ),
        // 20. Virtuous acts for the living (img. 34, KP2 img. 253).
        ActivityList(
            "virtuous_acts_for_the_living",
            good = Factors(
                weekdays = setOf(WEDNESDAY, THURSDAY, FRIDAY),
                dates = setOf(1, 18),
                mansions = m(ROHINI, MRIGASHIRAS, PUNARVASU, PUSHYA, SHATABHISHAJ, UTTARAPHALGUNI, ARDRA, HASTA, CITRA, SVATI, JYESHTHA, DHANISHTHA, PURVABHADRAPADA, PURVAPHALGUNI, REVATI, ANURADHA, MULA, MAGHA),
                animals = setOf(DRAGON),
                trigrams = setOf(KHEN),
            ),
            bad = Factors(
                weekdays = setOf(TUESDAY, SATURDAY),
                mansions = m(BHARANI, ASHLESHA, UTTARABHADRAPADA, SHRAVANA),
                animals = setOf(BIRD),
            ),
        ),
        // 21. Manuring, opening the soil, breaking in oxen (img. 34, KP2 img. 253).
        ActivityList(
            "manuring_and_breaking_in_oxen",
            good = Factors(
                weekdays = setOf(MONDAY, WEDNESDAY, FRIDAY, SATURDAY),
                dates = setOf(1, 4, 14, 19, 25),
                mansions = m(PUNARVASU, SHATABHISHAJ, ROHINI, REVATI, VISHAKHA, DHANISHTHA, PURVASHADHA, MRIGASHIRAS, HASTA, SVATI, PUSHYA),
                trigrams = setOf(GIN),
            ),
            bad = Factors(
                weekdays = setOf(SUNDAY, TUESDAY),
                dates = setOf(11, 22),
                mansions = m(ASHLESHA, BHARANI, KRITTIKA, UTTARAPHALGUNI, MAGHA, PURVAPHALGUNI, ANURADHA),
                animals = setOf(RABBIT, SNAKE, PIG),
            ),
        ),
        // 22. Sowing the fields (img. 36, KP2 img. 257).
        ActivityList(
            "sowing",
            good = Factors(
                weekdays = setOf(SATURDAY, MONDAY, WEDNESDAY, THURSDAY, FRIDAY),
                dates = setOf(2, 10, 12, 15, 16, 17, 21, 22, 23, 24, 25, 26, 27, 30),
                mansions = m(ROHINI, PUNARVASU, REVATI, MRIGASHIRAS, MAGHA, HASTA, CITRA, JYESHTHA, DHANISHTHA, SHATABHISHAJ, SVATI, ARDRA, SHRAVANA, MULA, PURVASHADHA, UTTARASHADHA, ANURADHA),
                animals = setOf(SHEEP, DOG, OX),
            ),
            bad = Factors(
                weekdays = setOf(SUNDAY, TUESDAY),
                dates = setOf(4, 8, 14, 29),
                mansions = m(BHARANI, KRITTIKA, PUSHYA, VISHAKHA, UTTARAPHALGUNI, PURVABHADRAPADA),
                animals = setOf(PIG, RABBIT, DRAGON, SNAKE),
            ),
        ),
        // 23. Giving gifts and dowries (img. 36, KP2 img. 257).
        ActivityList(
            "giving_gifts_and_dowries",
            good = Factors(
                weekdays = setOf(WEDNESDAY, THURSDAY),
                mansions = m(PURVABHADRAPADA, REVATI, UTTARAPHALGUNI, MULA, UTTARASHADHA),
            ),
            bad = Factors(
                weekdays = setOf(FRIDAY, SUNDAY, TUESDAY, SATURDAY),
                mansions = m(ASHVINI, CITRA, MRIGASHIRAS, HASTA, PUSHYA, ROHINI, DHANISHTHA, KRITTIKA, ARDRA, SVATI, VISHAKHA, MAGHA, PURVASHADHA, ASHLESHA),
                animals = setOf(RABBIT, DOG, SHEEP, SNAKE, TIGER),
                trigrams = setOf(KHON),
            ),
        ),
        // 24. Sewing tents and felt (img. 37, KP2 img. 258).
        ActivityList(
            "sewing_tents",
            good = Factors(
                weekdays = setOf(THURSDAY),
                mansions = m(SVATI, PUNARVASU, ROHINI, PURVABHADRAPADA, PURVASHADHA, UTTARAPHALGUNI),
                animals = setOf(TIGER, SNAKE),
            ),
            bad = Factors(
                weekdays = setOf(SUNDAY, SATURDAY),
                dates = setOf(29),
                mansions = m(UTTARASHADHA, PURVAPHALGUNI, MAGHA, ASHLESHA, ARDRA, REVATI, DHANISHTHA, UTTARABHADRAPADA, MULA, CITRA),
                animals = setOf(MONKEY),
            ),
        ),
        // 25. Building storehouses (img. 37, KP2 img. 258).
        ActivityList(
            "building_storehouses",
            good = Factors(
                weekdays = setOf(WEDNESDAY, SATURDAY),
                mansions = m(PUNARVASU, ROHINI, PUSHYA, SHRAVANA, MULA),
                animals = setOf(MOUSE, SHEEP, DOG),
            ),
            bad = Factors(
                weekdays = setOf(SUNDAY, TUESDAY),
                animals = setOf(PIG, SNAKE, BIRD),
            ),
        ),
        // 26. Raising victory banners and flags (img. 38, KP2 img. 259).
        ActivityList(
            "raising_banners",
            good = Factors(
                weekdays = setOf(SUNDAY, THURSDAY, SATURDAY),
                mansions = m(MRIGASHIRAS, UTTARAPHALGUNI, PURVABHADRAPADA, ASHVINI, UTTARASHADHA, PUSHYA),
            ),
            bad = Factors(
                mansions = m(KRITTIKA, ARDRA, PURVAPHALGUNI, MAGHA, MULA, BHARANI),
                animals = setOf(OX, SHEEP),
            ),
        ),
        // 27. Making springs, wells and canals (img. 38, KP2 img. 259).
        ActivityList(
            "digging_ponds_canals_and_wells",
            good = Factors(
                weekdays = setOf(MONDAY, WEDNESDAY, FRIDAY, SATURDAY),
                dates = setOf(2, 7, 12, 17, 22, 27),
                mansions = m(UTTARABHADRAPADA, ASHVINI, ARDRA, SHRAVANA, JYESHTHA, UTTARASHADHA, SVATI, ASHLESHA, UTTARAPHALGUNI),
                animals = setOf(SHEEP),
            ),
            bad = Factors(
                weekdays = setOf(SUNDAY, TUESDAY),
                dates = setOf(3, 13, 18, 23, 28, 8),
                mansions = m(KRITTIKA, MAGHA, PURVAPHALGUNI, HASTA, CITRA, SHATABHISHAJ, PURVABHADRAPADA),
                animals = setOf(RABBIT, DRAGON, SNAKE, PIG),
                trigrams = setOf(KHAM),
            ),
        ),
        // 28. Feeding up horses (img. 39, KP2 img. 260).
        ActivityList(
            "feeding_up_horses",
            good = Factors(
                weekdays = setOf(FRIDAY, TUESDAY, SUNDAY, WEDNESDAY, THURSDAY),
                mansions = m(KRITTIKA, MRIGASHIRAS, PUSHYA, PUNARVASU, HASTA, ROHINI, CITRA, SHRAVANA),
                animals = setOf(RABBIT),
                trigrams = setOf(GIN),
            ),
            bad = Factors(
                weekdays = setOf(SATURDAY),
                dates = setOf(11, 22, 29),
                mansions = m(SVATI, SHATABHISHAJ, MAGHA, PURVAPHALGUNI, PURVABHADRAPADA, PURVASHADHA),
                animals = setOf(MOUSE, HORSE),
            ),
        ),
        // 29. Treating horses, mules and donkeys (img. 39, KP2 img. 260).
        ActivityList(
            "treating_horses_mules_and_donkeys",
            good = Factors(
                weekdays = setOf(MONDAY, WEDNESDAY),
                mansions = m(PUNARVASU, UTTARASHADHA, SHATABHISHAJ, SVATI),
                animals = setOf(RABBIT),
                trigrams = setOf(GIN),
            ),
            bad = Factors(
                dates = setOf(11, 22, 29),
                animals = setOf(MOUSE, HORSE),
            ),
        ),
        // 30. Saddling (img. 39, KP2 img. 260).
        ActivityList(
            "saddling",
            good = Factors(
                weekdays = setOf(TUESDAY, WEDNESDAY, THURSDAY, FRIDAY),
                mansions = m(KRITTIKA, PUSHYA, PUNARVASU, ANURADHA, SHRAVANA, MRIGASHIRAS, ROHINI, SHATABHISHAJ, UTTARASHADHA, UTTARAPHALGUNI, HASTA, SVATI),
                animals = setOf(RABBIT),
                trigrams = setOf(GIN),
            ),
            bad = Factors(
                weekdays = setOf(SATURDAY),
                dates = setOf(11, 22, 29),
                mansions = m(ASHLESHA, MAGHA, PURVAPHALGUNI, DHANISHTHA),
                animals = setOf(MOUSE, HORSE),
            ),
        ),
        // 31. Keeping dogs (img. 40, KP2 img. 261).
        ActivityList(
            "raising_dogs",
            good = Factors(
                weekdays = setOf(SATURDAY, TUESDAY),
                dates = setOf(3, 4, 5, 15, 21, 25),
                mansions = m(ASHLESHA, MAGHA, ASHVINI),
            ),
            bad = Factors(
                dates = setOf(1, 6, 7, 8, 9, 10, 11, 12, 13, 16, 17, 18, 20, 26),
                animals = setOf(DOG),
                trigrams = setOf(KHEN),
            ),
        ),
        // 32. Calling prosperity, bon rites (img. 40, KP2 img. 261).
        ActivityList(
            "calling_prosperity_and_bon_rites",
            good = Factors(
                weekdays = setOf(SUNDAY, MONDAY, WEDNESDAY, SATURDAY),
                dates = setOf(15),
                mansions = m(ROHINI, PUNARVASU, KRITTIKA, PUSHYA, MRIGASHIRAS, UTTARAPHALGUNI, SVATI, SHATABHISHAJ, DHANISHTHA, JYESHTHA, MULA, PURVASHADHA, REVATI, UTTARASHADHA, PURVAPHALGUNI, CITRA, HASTA, ANURADHA, ASHVINI),
                animals = setOf(TIGER),
            ),
            bad = Factors(
                weekdays = setOf(TUESDAY),
                mansions = m(VISHAKHA, ASHLESHA, UTTARABHADRAPADA, BHARANI),
                animals = setOf(RABBIT, PIG),
            ),
        ),
        // 33. Learning writing and astrology (img. 40, KP2 img. 261).
        ActivityList(
            "learning_writing_and_astrology",
            good = Factors(
                weekdays = setOf(THURSDAY, WEDNESDAY, FRIDAY, SUNDAY),
                dates = setOf(18),
                mansions = m(KRITTIKA, PUNARVASU, MAGHA, HASTA, PUSHYA, UTTARABHADRAPADA, SVATI, MRIGASHIRAS, VISHAKHA, SHATABHISHAJ, PURVABHADRAPADA, ANURADHA, ARDRA),
                animals = setOf(DOG),
            ),
            bad = Factors(
                weekdays = setOf(SATURDAY, TUESDAY),
                dates = setOf(16),
                mansions = m(BHARANI, ASHLESHA),
                animals = setOf(PIG, OX),
            ),
        ),
        // 34. Compounding medicine (img. 41, KP2 img. 262).
        ActivityList(
            "preparing_medicine",
            good = Factors(
                weekdays = setOf(MONDAY, SUNDAY, THURSDAY, FRIDAY),
                mansions = m(KRITTIKA, MRIGASHIRAS, PUSHYA, HASTA, ROHINI, SVATI, SHRAVANA, DHANISHTHA, SHATABHISHAJ, PURVABHADRAPADA, UTTARABHADRAPADA, REVATI, UTTARASHADHA, CITRA, PUNARVASU, ANURADHA),
                animals = setOf(BIRD, DOG),
            ),
            bad = Factors(
                weekdays = setOf(SATURDAY, TUESDAY, WEDNESDAY),
                mansions = m(BHARANI, ASHLESHA, JYESHTHA, PURVAPHALGUNI, VISHAKHA, ASHVINI),
                animals = setOf(TIGER, SNAKE, SHEEP),
            ),
        ),
        // 35. Bloodletting, moxibustion, treatment (img. 41, KP2 img. 262).
        ActivityList(
            "bloodletting_and_moxibustion",
            good = Factors(
                weekdays = setOf(MONDAY, WEDNESDAY, THURSDAY, FRIDAY),
                dates = setOf(10, 18),
                mansions = m(ANURADHA, REVATI, SHRAVANA, MAGHA, ROHINI, PUNARVASU, PUSHYA, SVATI, JYESHTHA),
                animals = setOf(BIRD, DOG),
            ),
            bad = Factors(
                dates = setOf(9, 8, 29, 30),
                mansions = m(ARDRA, PURVAPHALGUNI, ASHVINI, BHARANI),
                animals = setOf(DRAGON, SNAKE, SHEEP),
            ),
        ),
        // 37. Bathing and washing the hair (img. 46, KP2 img. 267).
        ActivityList(
            "bathing_and_washing_the_hair",
            good = Factors(
                weekdays = setOf(MONDAY, WEDNESDAY, THURSDAY, FRIDAY),
                dates = setOf(3, 4, 5, 6, 8, 10, 11, 13, 15, 16, 18, 19, 22, 23, 26),
                mansions = m(KRITTIKA, ROHINI, MRIGASHIRAS, ARDRA, ASHVINI, PURVASHADHA, SVATI, UTTARASHADHA, UTTARABHADRAPADA, REVATI, CITRA, DHANISHTHA),
            ),
            bad = Factors(
                weekdays = setOf(TUESDAY, SATURDAY),
                dates = setOf(1, 2, 7, 9, 12, 14, 17, 20, 21, 24, 25, 27, 28, 29, 30),
                mansions = m(PUSHYA, MAGHA, VISHAKHA, PURVABHADRAPADA, PURVAPHALGUNI, BHARANI),
            ),
        ),
        // 39. Enthronement (img. 47, KP2 img. 268).
        ActivityList(
            "enthronement",
            good = Factors(
                weekdays = setOf(SUNDAY, TUESDAY, THURSDAY),
                mansions = m(ROHINI, MRIGASHIRAS, PUSHYA, ANURADHA, JYESHTHA, UTTARASHADHA, PUNARVASU, PURVASHADHA, SHATABHISHAJ, UTTARAPHALGUNI, UTTARABHADRAPADA, REVATI, KRITTIKA, SVATI, HASTA),
                animals = setOf(HORSE, DRAGON, TIGER, RABBIT),
                trigrams = setOf(KHEN),
            ),
            bad = Factors(
                weekdays = setOf(SATURDAY),
                mansions = m(BHARANI, ARDRA, ASHLESHA, MAGHA, CITRA, SHRAVANA, MULA, VISHAKHA, DHANISHTHA),
                animals = setOf(SNAKE, MONKEY),
            ),
        ),
        // 41. Taking attendants and servants (img. 48, KP2 img. 269).
        ActivityList(
            "taking_servants",
            good = Factors(
                weekdays = setOf(MONDAY, WEDNESDAY, FRIDAY),
                dates = setOf(8, 5, 14),
                mansions = m(UTTARABHADRAPADA, PUNARVASU, PURVABHADRAPADA, PUSHYA, MAGHA, UTTARAPHALGUNI, MULA, PURVASHADHA, UTTARASHADHA, BHARANI),
                animals = setOf(HORSE),
            ),
            bad = Factors(
                weekdays = setOf(TUESDAY, SATURDAY),
                dates = setOf(11, 12, 13, 4, 7, 9, 10, 18, 21, 22, 23, 25, 26, 27, 28, 30),
                mansions = m(PURVAPHALGUNI, JYESHTHA),
                animals = setOf(SNAKE),
                trigrams = setOf(KHEN),
            ),
        ),
        // 43. Putting on ornaments (img. 50, KP2 img. 271).
        ActivityList(
            "putting_on_ornaments",
            good = Factors(
                weekdays = setOf(MONDAY, FRIDAY, WEDNESDAY, THURSDAY, SUNDAY),
                mansions = m(KRITTIKA, MRIGASHIRAS, SHRAVANA, PUSHYA, PUNARVASU, MAGHA, PURVAPHALGUNI, PURVASHADHA, UTTARASHADHA, JYESHTHA, MULA, ARDRA, HASTA, DHANISHTHA, ANURADHA, UTTARABHADRAPADA, UTTARAPHALGUNI, CITRA),
                animals = setOf(HORSE, MONKEY),
                trigrams = setOf(GIN),
            ),
            bad = Factors(
                weekdays = setOf(TUESDAY, SATURDAY),
                mansions = m(BHARANI, ASHVINI, ASHLESHA, SHATABHISHAJ),
            ),
        ),
        // 44. Suppressing the sri spirits (img. 50, KP2 img. 271).
        ActivityList(
            "suppressing_sri",
            good = Factors(
                weekdays = setOf(TUESDAY, SATURDAY, SUNDAY, THURSDAY),
                mansions = m(MRIGASHIRAS, ARDRA, ASHLESHA, JYESHTHA, MULA, VISHAKHA, SVATI, UTTARABHADRAPADA, HASTA, ASHVINI, MAGHA),
                animals = setOf(TIGER, DRAGON, MONKEY),
                trigrams = setOf(ZON),
            ),
            bad = Factors(
                weekdays = setOf(MONDAY),
                dates = setOf(1),
                mansions = m(PUSHYA, KRITTIKA, DHANISHTHA, ANURADHA, SHRAVANA),
                animals = setOf(RABBIT, SNAKE, PIG, BIRD, MOUSE, OX),
                trigrams = setOf(KHON),
            ),
        ),
        // 48. Fire offerings (img. 54, KP2 img. 275).
        ActivityList(
            "fire_offerings",
            good = Factors(
                weekdays = setOf(SUNDAY, TUESDAY, THURSDAY),
                dates = setOf(3, 13, 23, 18, 28, 29),
                mansions = m(KRITTIKA, PUNARVASU, MAGHA, SVATI, PURVABHADRAPADA, PURVAPHALGUNI, PURVASHADHA, ARDRA),
            ),
            bad = Factors(
                weekdays = setOf(MONDAY, WEDNESDAY, FRIDAY),
                dates = setOf(12, 22, 2, 17, 27),
                mansions = m(ARDRA, ASHLESHA, BHARANI, UTTARABHADRAPADA, MULA),
                animals = setOf(SNAKE),
            ),
        ),
        // 49. Consecration (img. 54, KP2 img. 275).
        ActivityList(
            "consecration",
            good = Factors(
                weekdays = setOf(THURSDAY, MONDAY, WEDNESDAY, FRIDAY),
                dates = setOf(1, 10, 11, 12, 13, 15),
                mansions = m(KRITTIKA, ARDRA, PURVABHADRAPADA, ANURADHA, PUNARVASU, MRIGASHIRAS, PUSHYA, ROHINI, UTTARABHADRAPADA, UTTARAPHALGUNI, MULA, SHRAVANA, DHANISHTHA, UTTARASHADHA, PURVASHADHA, ASHLESHA),
                animals = setOf(TIGER, DRAGON),
            ),
            bad = Factors(
                weekdays = setOf(SUNDAY, TUESDAY, SATURDAY),
                dates = setOf(2, 3, 5, 6, 7, 8, 14, 16, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30),
                mansions = m(CITRA, ASHLESHA, MAGHA, PURVAPHALGUNI, SHRAVANA, BHARANI),
            ),
        ),
        // 54. Composing treatises, learning poetics and grammar (img. 57, KP2 img. 280).
        ActivityList(
            "composing_treatises_and_learning_poetics",
            good = Factors(
                weekdays = setOf(THURSDAY, WEDNESDAY, SUNDAY, MONDAY),
                dates = setOf(2, 3, 5, 10, 11, 23),
                mansions = m(KRITTIKA, ROHINI, PUSHYA, ANURADHA, HASTA, SVATI, SHATABHISHAJ, UTTARAPHALGUNI, PURVABHADRAPADA, UTTARABHADRAPADA, ASHVINI, PUNARVASU, REVATI, PURVASHADHA, MULA, ARDRA, MRIGASHIRAS),
                animals = setOf(DOG),
            ),
            bad = Factors(
                weekdays = setOf(SATURDAY, TUESDAY),
                dates = setOf(4, 7, 12, 9, 15),
                mansions = m(BHARANI, ASHLESHA, MAGHA),
                animals = setOf(OX, PIG),
            ),
        ),
        // 55. Bringing rain (img. 57, KP2 img. 280).
        ActivityList(
            "bringing_rain",
            good = Factors(
                weekdays = setOf(MONDAY, WEDNESDAY, FRIDAY),
                mansions = m(BHARANI, MRIGASHIRAS, ARDRA, ROHINI, PURVAPHALGUNI, UTTARAPHALGUNI, CITRA, SVATI, ANURADHA, PURVASHADHA, DHANISHTHA, SHRAVANA, JYESHTHA, MULA, MAGHA, REVATI, UTTARASHADHA, SHATABHISHAJ, PURVABHADRAPADA),
                animals = setOf(MOUSE, PIG, BIRD, MONKEY),
                trigrams = setOf(KHAM),
            ),
            bad = Factors(
                weekdays = setOf(THURSDAY, SUNDAY, TUESDAY),
                mansions = m(KRITTIKA, PUSHYA, HASTA, UTTARABHADRAPADA, ASHLESHA),
                animals = setOf(HORSE, DRAGON, OX, SHEEP, SNAKE, DOG),
                trigrams = setOf(LI),
            ),
        ),
        // 56. Thread-cross and torma rites (img. 58, KP2 img. 281).
        ActivityList(
            "thread_cross_and_torma_rites",
            good = Factors(
                weekdays = setOf(SUNDAY, THURSDAY, SATURDAY, TUESDAY),
                dates = setOf(18, 25, 28, 27),
                mansions = m(PURVAPHALGUNI, ARDRA, MRIGASHIRAS, MULA, SHRAVANA),
            ),
            bad = Factors(
                weekdays = setOf(FRIDAY, MONDAY, WEDNESDAY),
                dates = setOf(26, 12, 14),
                mansions = m(ROHINI, ANURADHA, SHATABHISHAJ, DHANISHTHA, REVATI),
            ),
        ),
        // 57. Honouring and petitioning (img. 59, KP2 img. 282).
        ActivityList(
            "honouring_and_petitioning",
            good = Factors(
                weekdays = setOf(FRIDAY, SUNDAY, WEDNESDAY),
                mansions = m(ASHVINI, PUNARVASU, SHATABHISHAJ, REVATI, SVATI, MRIGASHIRAS, ARDRA, PUSHYA, ASHLESHA, MAGHA, DHANISHTHA, PURVASHADHA, JYESHTHA),
                animals = setOf(DOG, RABBIT),
                trigrams = setOf(KHEN),
            ),
            bad = Factors(
                weekdays = setOf(SATURDAY, TUESDAY),
                dates = setOf(4, 8, 9, 28),
                mansions = m(UTTARASHADHA, PURVABHADRAPADA, VISHAKHA),
            ),
        ),
        // 58. Disputes and lawsuits (img. 59, KP2 img. 282).
        ActivityList(
            "lawsuits_and_disputes",
            good = Factors(
                weekdays = setOf(FRIDAY, TUESDAY),
                mansions = m(ASHVINI, UTTARASHADHA, PUNARVASU, SHATABHISHAJ, REVATI, MRIGASHIRAS, MAGHA),
            ),
            bad = Factors(
                weekdays = setOf(SATURDAY, SUNDAY, MONDAY, THURSDAY, WEDNESDAY),
                dates = setOf(4, 8, 9, 28),
                mansions = m(SVATI, ARDRA, PUSHYA, ASHLESHA, JYESHTHA, ANURADHA, MULA, CITRA),
                animals = setOf(HORSE),
            ),
        ),
        // 59. Judging cases (img. 60, KP2 img. 283).
        ActivityList(
            "judging_disputes",
            good = Factors(
                weekdays = setOf(FRIDAY, SUNDAY, TUESDAY),
                mansions = m(ASHVINI, PUNARVASU, SHATABHISHAJ, REVATI, SVATI, ARDRA, PUSHYA, UTTARASHADHA),
                animals = setOf(OX, DOG),
            ),
            bad = Factors(
                weekdays = setOf(SATURDAY, MONDAY, WEDNESDAY),
                dates = setOf(4, 8, 9, 18),
                mansions = m(MRIGASHIRAS, ASHLESHA, CITRA, JYESHTHA, PURVABHADRAPADA, MULA),
                animals = setOf(HORSE),
            ),
        ),
        // 60. Councils (img. 60, KP2 img. 283).
        ActivityList(
            "council",
            good = Factors(
                weekdays = setOf(THURSDAY, FRIDAY),
                mansions = m(ROHINI, MRIGASHIRAS, ASHLESHA, SHATABHISHAJ, HASTA, CITRA, UTTARAPHALGUNI, DHANISHTHA, PURVABHADRAPADA, UTTARABHADRAPADA, UTTARASHADHA, REVATI),
                animals = setOf(HORSE),
            ),
            bad = Factors(
                weekdays = setOf(TUESDAY, SATURDAY),
                dates = setOf(12),
                mansions = m(ANURADHA, MULA),
                animals = setOf(TIGER, MONKEY, BIRD, DOG),
            ),
        ),
        // 50. Ordination, teaching, maṇḍalas, study, empowerment, practice (KP2 only, img. 276).
        ActivityList(
            "ordination_teaching_and_empowerment",
            good = Factors(
                weekdays = setOf(SUNDAY, THURSDAY),
                dates = setOf(3, 5, 10, 13, 23, 11),
                mansions = m(SVATI, VISHAKHA, ROHINI, MRIGASHIRAS, ARDRA, PUNARVASU, PUSHYA, MAGHA, KRITTIKA, HASTA, CITRA, DHANISHTHA, SHATABHISHAJ, PURVASHADHA, UTTARABHADRAPADA),
                animals = setOf(DRAGON),
                trigrams = setOf(GIN),
            ),
            bad = Factors(
                weekdays = setOf(TUESDAY, SATURDAY),
                dates = setOf(1, 22, 15),
                animals = setOf(SHEEP, OX),
            ),
        ),
        // 51. Dikes and protection against water (KP2 only, img. 277).
        ActivityList(
            "building_flood_dikes",
            good = Factors(
                weekdays = setOf(SUNDAY, FRIDAY, TUESDAY),
                mansions = m(KRITTIKA, PUSHYA, PURVAPHALGUNI, UTTARAPHALGUNI, HASTA, SVATI, VISHAKHA, ASHLESHA, PURVABHADRAPADA, DHANISHTHA),
                animals = setOf(DOG),
            ),
            bad = Factors(
                weekdays = setOf(MONDAY, WEDNESDAY),
                mansions = m(UTTARASHADHA, UTTARABHADRAPADA, PURVASHADHA, ARDRA, MAGHA, REVATI, CITRA, JYESHTHA, MULA),
            ),
        ),
        // 52a. Cutting hair and nails (img. 55, KP2 img. 278).
        ActivityList(
            "cutting_hair_and_nails",
            good = Factors(
                weekdays = setOf(FRIDAY, MONDAY, WEDNESDAY),
                mansions = m(KRITTIKA, MRIGASHIRAS, CITRA, MAGHA, VISHAKHA, ANURADHA, UTTARABHADRAPADA, ASHVINI, ARDRA, HASTA, REVATI),
            ),
            bad = Factors(
                weekdays = setOf(SUNDAY, TUESDAY, THURSDAY, SATURDAY),
                mansions = m(ROHINI, JYESHTHA, UTTARASHADHA, UTTARAPHALGUNI, SHATABHISHAJ, BHARANI),
                animals = setOf(MONKEY, DRAGON),
            ),
        ),
        // 52b. Crafts (img. 55, KP2 img. 278).
        ActivityList(
            "crafts",
            good = Factors(
                weekdays = setOf(FRIDAY),
                mansions = m(KRITTIKA, MRIGASHIRAS, CITRA, MAGHA, VISHAKHA, ANURADHA, UTTARABHADRAPADA),
                animals = setOf(DOG, OX),
                trigrams = setOf(KHAM, LI),
            ),
            bad = Factors(
                mansions = m(ROHINI, JYESHTHA, UTTARASHADHA, UTTARAPHALGUNI, SHATABHISHAJ, BHARANI),
                animals = setOf(MONKEY, DRAGON),
            ),
        ),
        // 53a. Military training (img. 56, KP2 img. 279).
        ActivityList(
            "martial_skills",
            good = Factors(
                mansions = m(ASHVINI, MRIGASHIRAS, ARDRA, ASHLESHA, PURVASHADHA, HASTA, ANURADHA, UTTARAPHALGUNI, SHATABHISHAJ, JYESHTHA, MULA, PURVABHADRAPADA, VISHAKHA, MAGHA),
            ),
            bad = Factors(
                weekdays = setOf(SUNDAY, THURSDAY, MONDAY, WEDNESDAY),
                animals = setOf(TIGER, DRAGON, OX),
                trigrams = setOf(ZON),
            ),
        ),
        // 53b. Dice and games (img. 56, KP2 img. 279).
        ActivityList(
            "games",
            good = Factors(
                weekdays = setOf(SATURDAY, TUESDAY, FRIDAY),
                mansions = m(ASHVINI, MRIGASHIRAS, ARDRA, ASHLESHA, PURVASHADHA, HASTA, ANURADHA, UTTARAPHALGUNI, SHATABHISHAJ, JYESHTHA, MULA, PURVABHADRAPADA, VISHAKHA, MAGHA),
                animals = setOf(SNAKE, RABBIT, SHEEP, DOG, MONKEY),
                trigrams = setOf(LI),
            ),
            bad = Factors(
                weekdays = setOf(SUNDAY, THURSDAY, MONDAY, WEDNESDAY),
                animals = setOf(TIGER, DRAGON, OX),
                trigrams = setOf(ZON),
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
