/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import zanshin.core.texts.Activity
import zanshin.core.texts.DaySummary
import zanshin.core.texts.Texts

/** SPEC §5.10: Henning's activity lists, his doubled mansions read on the print, and the rules for what stays doubtful. */
class ElectionalTest {
    private fun list(wording: String) = Electional.ACTIVITIES.single { it.wording == wording }

    @Test
    fun `Henning's thirteen, the print's 50 other activities and the White Beryl's five, each without duplicates or overlap`() {
        assertEquals(63, Electional.PRINT.size)
        assertEquals(68, Electional.ACTIVITIES.size)
        assertEquals(68, Electional.ACTIVITIES.map { it.wording }.distinct().size)
        for (a in Electional.ACTIVITIES) {
            assertEquals(a.good.mansions.distinct(), a.good.mansions, a.wording)
            assertTrue(a.good.mansions.intersect(a.bad.mansions.toSet()).isEmpty(), a.wording)
            assertTrue(a.good.weekdays.intersect(a.bad.weekdays).isEmpty(), a.wording)
            assertTrue(a.good.dates.intersect(a.bad.dates).isEmpty(), a.wording)
            assertTrue(a.good.animals.intersect(a.bad.animals).isEmpty(), a.wording)
            assertTrue(a.good.trigrams.intersect(a.bad.trigrams).isEmpty(), a.wording)
            assertTrue(a.good.smeBa.intersect(a.bad.smeBa).isEmpty(), a.wording)
            assertTrue(a.good.links.intersect(a.bad.links).isEmpty(), a.wording)
        }
        assertEquals(Mansion.entries.toSet(), Electional.MANSION_ACTIVITIES.keys)
    }

    @Test
    fun `a mansion named in two places for one activity is left out`() {
        // Marriage: Svātī good only in parentheses in the print.
        val marriage = Electional.PRINT.single { it.wording == "marriage" }
        assertFalse(Mansion.SVATI in marriage.good.mansions)
        assertTrue(Mansion.PUSHYA in marriage.good.mansions)
        // Offerings: Uttarabhādrapadā stands in the good and the bad half of the print.
        val offerings = Electional.PRINT.single { it.wording == "offerings_to_deities" }
        assertFalse(Mansion.UTTARABHADRAPADA in offerings.good.mansions || Mansion.UTTARABHADRAPADA in offerings.bad.mansions)
        assertTrue(Mansion.MRIGASHIRAS in offerings.good.mansions)
        // Controlling: Mṛgaśiras is both "merely acceptable" and bad.
        assertFalse(Mansion.MRIGASHIRAS in list("controlling_activity").bad.mansions)
    }

    /** Henning's doubled chu smad, read on the print he translated (docs/sources/mansions.md, Box by box). */
    @Test
    fun `the doubled mansions follow the kun phan me long`() {
        fun good(w: String) = list(w).good.mansions
        fun bad(w: String) = list(w).bad.mansions
        val ua = Mansion.UTTARASHADHA
        val ub = Mansion.UTTARABHADRAPADA
        assertTrue(ua in good("marriage") && ub in bad("marriage"))
        assertTrue(ua in good("taking_a_new_home") && ub in bad("taking_a_new_home"))
        assertTrue(ub in good("setting_out_on_journeys") && ua in bad("setting_out_on_journeys"))
        assertTrue(Mansion.SHATABHISHAJ in good("setting_out_on_journeys"))
        assertTrue(ub in good("making_weapons") && ua in bad("making_weapons"))
        assertTrue(ub in good("funerals") && ua !in good("funerals") && ua !in bad("funerals"))
        assertTrue(ub in good("destructive_activity") && ua !in good("destructive_activity"))
        assertTrue(listOf(ua, ub, Mansion.PURVAPHALGUNI, Mansion.SHATABHISHAJ).all { it in good("controlling_activity") })
        assertTrue(ua in bad("increasing_activity") && ub in bad("increasing_activity"))
        val offerings = list("offerings_to_deities")
        assertTrue(listOf(ua, Mansion.PURVAPHALGUNI, Mansion.DHANISHTHA).all { it in offerings.good.mansions })
        assertTrue(Mansion.CITRA in offerings.bad.mansions)
    }

    @Test
    fun `rules the source states as ranges`() {
        assertEquals(11, list("pacifying_activity").bad.mansions.size)
        val health = list("health_and_wealth")
        assertTrue(5 in health.good.dates && 6 !in health.good.dates && 16 in health.bad.dates)
        assertTrue(list("setting_out_on_journeys").good.weekdays.isEmpty())
    }

    @Test
    fun `a mansion's reading keeps both lists where they disagree`() {
        // Rohiṇī: the list of mansions names marriage; the marriage list names Rohiṇī bad.
        val rohini = Texts.MANSION.getValue(Mansion.ROHINI)
        assertTrue("marriage" in rohini.goodKeys && "marriage" in rohini.avoidKeys)
    }

    @Test
    fun `the Tibetan day in brief`() {
        // 2026-09-30: Wednesday, 19th, Aśvinī, fire sheep, trigram li.
        val day = TibetanCalendar.of(java.time.LocalDate.of(2026, 9, 30))
        assertEquals(listOf(Weekday.WEDNESDAY, 19, Mansion.ASHVINI, Animal.SHEEP, Trigram.LI), listOf(day.weekday, day.day, day.mansion, day.dayAnimal, day.trigram))
        val s = DaySummary.of(day)
        val marriage = s.activities.single { it.activity == Activity.WEDDING }
        // Marriage lists Wednesday, Aśvinī, the 19th and the li trigram as bad, and the sheep, the 60-day animal,
        // as good; the lists count the lunar date's animal, here the tiger, whose results in the White Beryl
        // avoid marriage alliances (vol. 2, p. 356; open question 9). The weekday decides, the death combination
        // agrees below the mansion (SPEC §5.12), and so does the demon day the Rdo rje gtsug lag makes of
        // Wednesday with Aśvinī.
        assertEquals(listOf("Wednesday", "Aśvinī", "Death combination", "Demon day", "day 19", "Tiger", "Li"), marriage.avoid.map { it.kanji })
        assertTrue(marriage.good.isEmpty())
        assertFalse(marriage.disputed)
    }

    @Test
    fun `the print's other boxes, read on the scans`() {
        // Box 22, sowing (img. 36): the 28th stands in both halves, so it counts for neither; the doubtful good date is
        // the 19th, KP's 9 (open question 157).
        val printed = Electional.PRINT.single { it.wording == "sowing" }
        assertEquals(setOf(4, 8, 14, 29), printed.bad.dates)
        assertTrue(19 in printed.good.dates)
        assertFalse(28 in printed.good.dates || 28 in printed.bad.dates)
        val sowing = list("sowing")
        assertEquals(setOf(Weekday.SATURDAY, Weekday.MONDAY, Weekday.WEDNESDAY, Weekday.THURSDAY, Weekday.FRIDAY), sowing.good.weekdays)
        assertTrue(Animal.PIG in sowing.bad.animals)
        // Box 11, food and brewing: "otherwise as the feasts", but it names something in every kind, so its own lists stand.
        assertEquals(listOf(Mansion.UTTARABHADRAPADA), list("preparing_food_and_brewing").good.mansions)
        // Box 30, saddling: "the rest as feeding up horses", whose day animals it takes.
        assertEquals(list("feeding_up_horses").bad.animals, list("saddling").bad.animals)
        // Box 49, consecration: the 9th in both halves is left out.
        val consecration = Electional.PRINT.single { it.wording == "consecration" }
        assertFalse(9 in consecration.good.dates || 9 in consecration.bad.dates)
        // Box 52 split: the hair half keeps the weekdays the crafts half qualifies by material.
        assertEquals(setOf(Weekday.FRIDAY, Weekday.MONDAY, Weekday.WEDNESDAY), list("cutting_hair_and_nails").good.weekdays)
        assertEquals(setOf(Weekday.FRIDAY), Electional.PRINT.single { it.wording == "crafts" }.good.weekdays)
        // Box 16 split: what is good for taking in wealth is bad for giving it away.
        assertTrue(Weekday.SUNDAY in list("receiving_wealth").good.weekdays && Weekday.SUNDAY in list("sending_out_wealth").bad.weekdays)
    }

    /** The White Beryl's chapter 34, read on the scans (docs/sources/white-beryl-ch34.md), joined to the print's boxes. */
    @Test
    fun `the White Beryl's works decide what the print digests`() {
        fun good(w: String) = list(w).good
        fun bad(w: String) = list(w).bad
        // Every work it adds to is a box; its five own works are new lists.
        assertEquals(listOf("spectacles", "hunting_and_theft", "breaking_in_horses", "averting_rites", "sorcery"), WhiteBerylWorks.OWN.map { it.wording })
        // Open question 6: the print names khrums twice for offerings; the chapter, khrums stod good and khrums smad bad (p. 386).
        assertTrue(Mansion.PURVABHADRAPADA in good("offerings_to_deities").mansions && Mansion.ASHVINI in good("offerings_to_deities").mansions)
        assertTrue(Mansion.UTTARABHADRAPADA in bad("offerings_to_deities").mansions)
        // What the print names on the other side, the chapter decides: the sowing 28th bad (p. 400), Svātī good for
        // marriage (p. 412), the waxing 9th good for consecration (p. 419).
        assertTrue(28 in bad("sowing").dates && 28 !in good("sowing").dates)
        // Servants (p. 406): the box, read on the scan, has the chapter's dates; the 8th and 18th are on neither side.
        assertEquals(setOf(4, 5, 16, 24), good("taking_servants").dates)
        assertEquals(setOf(3, 6, 7, 9, 10, 11, 12, 13, 14, 21, 22, 23, 25, 26, 27, 28, 29, 30), bad("taking_servants").dates)
        assertTrue(Mansion.SVATI in good("marriage").mansions)
        assertTrue(9 in good("consecration").dates && 2 in good("consecration").dates && 2 !in bad("consecration").dates)
        // War and dice: the tiger and dragon good, the print's box reversed (p. 421).
        for (w in listOf("martial_skills", "games")) {
            assertTrue(Animal.TIGER in good(w).animals && Animal.SNAKE in bad(w).animals, w)
        }
        // Rain: the watery mansions good, Maghā, Jyeṣṭhā, Mūla and Revatī bad (p. 423).
        assertTrue(listOf(Mansion.MAGHA, Mansion.JYESHTHA, Mansion.MULA, Mansion.REVATI).all { it in bad("bringing_rain").mansions })
        // Cutting hair: khrums smad good for crafts, bad for the hair (p. 420).
        assertTrue(Mansion.UTTARABHADRAPADA in good("crafts").mansions && Mansion.UTTARABHADRAPADA in bad("cutting_hair_and_nails").mansions)
        assertEquals(setOf(Weekday.FRIDAY, Weekday.WEDNESDAY), good("crafts").weekdays)
        // What the print names and the chapter does not keeps the print's reading.
        assertTrue(12 in good("taking_a_new_home").dates && 13 in good("taking_a_new_home").dates)
        // Taming horses takes the racing box's dates and day animals, as the chapter says («ཁྱིམ་ཉི་རྒྱུག་དང་མཚུངས»).
        assertEquals(list("feeding_up_horses").bad.dates, bad("breaking_in_horses").dates)
        assertEquals(list("feeding_up_horses").bad.animals, bad("breaking_in_horses").animals)
    }

    /** ROADMAP T2.15: the good and bad dates for washing the hair, read on the scan of WB vol. 2, p. 404 (docs/sources/hair-dates.md). */
    @Test
    fun `washing the hair takes the White Beryl's dates, the 27th on neither side`() {
        // «གསུམ་བཞི་ལྔ་དྲུག་བརྒྱད་དང་བཅུ། །བཅུ་གཅིག་བཅུ་གསུམ་བཅོ་ལྔ་དང་། །བཅུ་དྲུག་བཅོ་བརྒྱད་བཅུ་དགུ་དང་། །ཉེར་གཉིས་ཉེར་གསུམ་ཉེར་དྲུག་བཟང»
        val good = setOf(3, 4, 5, 6, 8, 10, 11, 13, 15, 16, 18, 19, 22, 23, 26)
        // «གཅིག་གཉིས་བདུན་དགུ་བཅུ་གཉིས་དང་། །བཅུ་བཞི་བཅུ་བདུན་ཚེས་ཉི་ཤུ། །ཉེར་གཅིག་ཉེར་བཞི་ཉེར་ལྔ་བརྒྱད། །ཉེར་དགུ་གནམ་གང་ངན་པར་བརྗོད»
        val bad = setOf(1, 2, 7, 9, 12, 14, 17, 20, 21, 24, 25, 28, 29, 30)
        val washing = list("bathing_and_washing_the_hair")
        assertEquals(good, washing.good.dates)
        assertEquals(bad, washing.bad.dates)
        assertFalse(27 in good || 27 in bad)
    }

    @Test
    fun `the boxes' sme ba and links, as read on the scans`() {
        // docs/sources/kp-activities.md, read 2026-10-10 on KP and KP2: the number or the link and the half it stands in.
        val sme = Electional.ACTIVITIES.filter { it.good.smeBa.isNotEmpty() || it.bad.smeBa.isNotEmpty() }
            .associate { it.wording to (it.good.smeBa to it.bad.smeBa) }
        assertEquals(
            mapOf(
                "funerals" to (setOf<Int>() to setOf(2, 5, 9)),
                "setting_up_supports" to (setOf(5) to setOf()),
                "building_walls" to (setOf<Int>() to setOf(3)),
                "trade" to (setOf<Int>() to setOf(9)),
                "receiving_wealth" to (setOf(9) to setOf()),
                "auspicious_work" to (setOf(8) to setOf()),
                "giving_gifts_and_dowries" to (setOf(6) to setOf()),
                "digging_ponds_canals_and_wells" to (setOf<Int>() to setOf(3)),
                "raising_dogs" to (setOf<Int>() to setOf(5)),
                "calling_prosperity_and_bon_rites" to (setOf(6, 9) to setOf()),
                "preparing_medicine" to (setOf(4) to setOf()),
                "bloodletting_and_moxibustion" to (setOf<Int>() to setOf(2, 7)),
                "bathing_and_washing_the_hair" to (setOf<Int>() to setOf(1, 6)),
                "suppressing_sri" to (setOf(2) to setOf()),
                "consecration" to (setOf<Int>() to setOf(6)),
                "composing_treatises_and_learning_poetics" to (setOf(5) to setOf()),
                "fire_offerings" to (setOf<Int>() to setOf(5, 7, 2, 4)),
                "bringing_rain" to (setOf(3, 2, 4) to setOf(9, 7)),
                "honouring_and_petitioning" to (setOf(5) to setOf()),
                "ordination_teaching_and_empowerment" to (setOf(5) to setOf()),
            ),
            sme,
        )
        val links = Electional.ACTIVITIES.count { it.good.links.isNotEmpty() || it.bad.links.isNotEmpty() }
        assertEquals(28, links)
        with(list("bathing_and_washing_the_hair")) {
            assertEquals(setOf(Link.SOURCES, Link.BECOMING, Link.FORMATION, Link.BIRTH, Link.IGNORANCE, Link.CONSCIOUSNESS, Link.AGEING_AND_DEATH), good.links)
            assertEquals(setOf(Link.NAME_AND_FORM, Link.FEELING, Link.CRAVING), bad.links)
        }
    }

    /** ROADMAP T2.28: the links and the sme ba O1 read, and the boxes O10f read otherwise (docs/sources/kp-activities.md). */
    @Test
    fun `the boxes read again on the scans`() {
        fun printed(w: String) = Electional.PRINT.single { it.wording == w }
        // O1, *What the table should now carry*: «སྐྱེད» is the sense sources, bare «སྐྱེ» birth.
        with(printed("first_wearing_of_new_clothes")) {
            assertEquals(setOf(Link.CONSCIOUSNESS, Link.SOURCES, Link.BECOMING), good.links)
            assertTrue(Link.BIRTH in bad.links)
        }
        assertEquals(setOf(Link.FORMATION, Link.IGNORANCE, Link.BIRTH), printed("building_walls").good.links)
        assertTrue(Link.SOURCES in printed("virtuous_acts_for_the_living").good.links)
        assertEquals(setOf(Link.SOURCES, Link.CONTACT, Link.BIRTH), printed("judging_disputes").good.links)
        // Box 59's last bad date on the scan: «༢༨», as boxes 57 and 58.
        assertEquals(setOf(4, 8, 9, 28), printed("judging_disputes").bad.dates)
        assertEquals(setOf(Link.CONSCIOUSNESS, Link.FEELING), printed("ordination_teaching_and_empowerment").good.links)
        // Box 39's dates, from the Bhutan print, joined to the chapter's (p. 405): the 8th to the 10th on neither side.
        assertEquals(setOf(1, 2, 11, 12, 13, 15, 18), list("enthronement").good.dates)
        assertEquals(setOf(6, 7, 16) + (19..30), list("enthronement").bad.dates)
        // Box 50's dates, from the Bhutan print: the 14th good, not the 13th.
        assertEquals(setOf(3, 5, 10, 14, 23, 11), printed("ordination_teaching_and_empowerment").good.dates)
        assertEquals(setOf(1, 4, 6, 7, 8, 22, 15), printed("ordination_teaching_and_empowerment").bad.dates)
        // Work 6: the three-blue bad for forts, where the box carves its 9.
        assertEquals(setOf(3), list("building_walls").bad.smeBa)
        // Box 44's good dates, read in the Bhutan print: the White Beryl's.
        assertEquals(setOf(4, 8, 9, 18, 19, 22, 25, 29), printed("suppressing_sri").good.dates)
        // Box 33's second good date, read in the Bhutan print: the 22nd.
        assertEquals(setOf(18, 22), printed("learning_writing_and_astrology").good.dates)
        // Box 15's dates are good for buying and bad for selling, or the reverse: neither side.
        assertTrue(printed("trade").good.dates.isEmpty() && printed("trade").bad.dates.isEmpty())
        // Box 53 is two columns: the Tiger, Dragon and Ox days and the trigram zon stand in the good one, for both halves.
        for (w in listOf("martial_skills", "games")) with(printed(w)) {
            assertTrue(good.animals.containsAll(setOf(Animal.TIGER, Animal.DRAGON, Animal.OX)) && Trigram.ZON in good.trigrams, w)
            assertTrue(bad.animals.isEmpty() && bad.trigrams.isEmpty(), w)
        }
        assertEquals(setOf(Link.FORMATION, Link.CONSCIOUSNESS, Link.CONTACT, Link.GRASPING), printed("martial_skills").good.links)
        assertTrue(printed("games").good.links.containsAll(setOf(Link.CRAVING, Link.BECOMING)))
        // O10f: box 18's bad khrums is the trigram khon.
        with(printed("planting_trees_and_flowers")) {
            assertTrue(Mansion.UTTARABHADRAPADA in good.mansions && Trigram.KHON in bad.trigrams)
        }
        // Box 21's dates, WB's work 9 (its doubtful numeral is KP's 9); its three bad dates are bad for breaking in oxen only.
        with(printed("manuring_and_breaking_in_oxen")) {
            assertEquals(setOf(1, 6, 16, 19, 25, 26), good.dates)
            assertTrue(bad.dates.isEmpty())
        }
        // The brackets: boxes 28, 30 and 54.
        assertEquals(setOf(Weekday.FRIDAY, Weekday.TUESDAY, Weekday.SUNDAY), printed("feeding_up_horses").good.weekdays)
        assertEquals(setOf(Weekday.TUESDAY, Weekday.WEDNESDAY, Weekday.THURSDAY), printed("saddling").good.weekdays)
        assertEquals(setOf(Weekday.THURSDAY, Weekday.WEDNESDAY), printed("composing_treatises_and_learning_poetics").good.weekdays)
        // Box 54's bad dates, read on the scan: the White Beryl's 6, 7, 12, 4, 15 in its order; the 9th on neither side.
        assertEquals(setOf(6, 7, 12, 4, 15), printed("composing_treatises_and_learning_poetics").bad.dates)
        assertEquals(setOf(1, 4, 6, 7, 12, 15), list("composing_treatises_and_learning_poetics").bad.dates)
        // Box 33: the 1st and the 6th, and the link formation.
        with(printed("learning_writing_and_astrology")) {
            assertEquals(setOf(1, 6), bad.dates)
            assertEquals(setOf(Link.FORMATION), good.links)
        }
        with(printed("bloodletting_and_moxibustion")) {
            assertTrue(Mansion.UTTARABHADRAPADA in bad.mansions && Trigram.LI in bad.trigrams)
            assertEquals(setOf(4, 8, 29, 30), bad.dates)
        }
        with(printed("bathing_and_washing_the_hair")) {
            assertEquals(setOf(Animal.DOG, Animal.TIGER), bad.animals)
            assertEquals(setOf(Trigram.LI, Trigram.KHEN), bad.trigrams)
        }
        // Box 48: sa ga good, lag bad.
        with(printed("fire_offerings")) { assertTrue(Mansion.VISHAKHA in good.mansions && Mansion.ARDRA in bad.mansions && Mansion.ARDRA !in good.mansions) }
        assertTrue(Mansion.JYESHTHA in printed("consecration").good.mansions)
        // Box 55's mansions, KP and KP2 alike.
        with(printed("bringing_rain")) {
            assertEquals(
                listOf(
                    Mansion.BHARANI, Mansion.MRIGASHIRAS, Mansion.ARDRA, Mansion.PUNARVASU, Mansion.PURVAPHALGUNI, Mansion.UTTARAPHALGUNI,
                    Mansion.CITRA, Mansion.VISHAKHA, Mansion.ANURADHA, Mansion.PURVASHADHA, Mansion.DHANISHTHA, Mansion.SHRAVANA,
                    Mansion.UTTARASHADHA, Mansion.SHATABHISHAJ, Mansion.PURVABHADRAPADA,
                ),
                good.mansions,
            )
            assertEquals(
                listOf(Mansion.KRITTIKA, Mansion.PUSHYA, Mansion.HASTA, Mansion.JYESHTHA, Mansion.MULA, Mansion.MAGHA, Mansion.REVATI, Mansion.ASHLESHA),
                bad.mansions,
            )
        }
        // Box 56: gro bzhin bad for Bon rites, so neither; the Tiger day bad.
        with(printed("thread_cross_and_torma_rites")) { assertTrue(Mansion.SHRAVANA !in good.mansions && Animal.TIGER in bad.animals) }
        // Box 56's bad dates on the scan: «༢ ༦», not 26; the 26th on neither side.
        assertEquals(setOf(2, 6, 12, 14), printed("thread_cross_and_torma_rites").bad.dates)
        // Box 60's bad date on the scan: «༢༢», the White Beryl's; the 12th on neither side.
        assertEquals(setOf(22), list("council").bad.dates)
    }

    /** ROADMAP T2.28: the White Beryl's chapter 34 beside the boxes (O2; docs/sources/white-beryl-ch34.md, *The chapter's lists whole*). */
    @Test
    fun `the chapter decides where the boxes are unread or read otherwise`() {
        fun good(w: String) = list(w).good
        fun bad(w: String) = list(w).bad
        // O2: marriage's «བཅོ་བརྒྱད་དགུ» names the 9th; learning's «ཚེས་གཅིག་དྲུག» the 1st and the 6th, not the 16th.
        assertTrue(listOf(9, 14, 18, 22).all { it in bad("marriage").dates })
        assertEquals(setOf(1, 6), bad("learning_writing_and_astrology").dates)
        assertEquals(setOf(18, 22), good("learning_writing_and_astrology").dates)
        // Work 3: the Mouse day good for cutting clothes.
        assertTrue(Animal.MOUSE in good("cutting_new_clothes").animals)
        // Work 4 settles box 5's bracket: craving middling.
        assertTrue(Link.CRAVING !in good("first_wearing_of_new_clothes").links && Link.CRAVING !in bad("first_wearing_of_new_clothes").links)
        // Work 9: the 19th where the box is carved 14; the dates bad for breaking in oxen only count for neither.
        assertEquals(setOf(1, 6, 16, 19, 25, 26), good("manuring_and_breaking_in_oxen").dates)
        assertTrue(bad("manuring_and_breaking_in_oxen").dates.isEmpty())
        // Work 14: the 19th for the box's 20th, the good run 21 to 25 for the box's 21st and 25th, and three dates of one
        // month each, for giving and for taking.
        for (w in listOf("receiving_wealth", "sending_out_wealth")) {
            assertTrue(19 in bad(w).dates && 20 !in bad(w).dates && 20 !in good(w).dates, w)
            assertTrue(good(w).dates.containsAll((21..25).toSet()) && (21..25).none { it in bad(w).dates }, w)
            assertEquals(setOf(11 to 27, 12 to 25, 1 to 23), bad(w).monthDates, w)
        }
        // Work 32: the green four for medicine, where the box prints 9.
        assertEquals(setOf(4), good("preparing_medicine").smeBa)
        // Work 38: bra nye good only for taking a widow into service; the link feeling good.
        assertTrue(Mansion.BHARANI !in good("taking_servants").mansions && Link.FEELING in good("taking_servants").links)
        // Work 45: contact good, plainly; work 23: the 14th bad for sowing.
        assertTrue(Link.CONTACT in good("fire_offerings").links)
        assertTrue(14 in bad("sowing").dates && 14 !in good("sowing").dates)
    }

    @Test
    fun `a date of one month only joins that month's date lists`() {
        // Work 14 (p. 395): the 27th of the 11th month avoids giving and taking goods; the 27th of another month does not.
        val eleventh = Texts.electionalDate(11, 27)
        assertTrue("receiving_wealth" in eleventh.avoidKeys && "sending_out_wealth" in eleventh.avoidKeys)
        assertTrue("receiving_wealth" !in Texts.electionalDate(10, 27).avoidKeys)
        assertEquals(Texts.ELECTIONAL_DATE.getValue(27), Texts.electionalDate(10, 27))
        // The month's own date outweighs the run: the 23rd is good for goods but the 1st month's 23rd and the 12th month's
        // 25th are bad, on one side only.
        for ((md, w) in listOf((1 to 23) to "receiving_wealth", (12 to 25) to "sending_out_wealth")) {
            val r = Texts.electionalDate(md.first, md.second)
            assertTrue(w in r.avoidKeys && w !in r.goodKeys, "$md $w")
            assertTrue(w in Texts.electionalDate(2, md.second).goodKeys, "$md $w")
        }
        assertEquals(setOf(11 to 27, 12 to 25, 1 to 23), Texts.ELECTIONAL_MONTH_DATE.keys)
    }
}
