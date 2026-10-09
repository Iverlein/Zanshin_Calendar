/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import zanshin.core.tibetan.Animal.*
import zanshin.core.tibetan.Mansion.*
import zanshin.core.tibetan.Trigram.*
import zanshin.core.tibetan.Weekday.*
import zanshin.core.tibetan.ZodiacSign.*

/**
 * The White Beryl's chapter 34, "the results of the important works one by
 * one" (vol. 2, pp. 378–428), whose lists the *kun phan me long*'s activity
 * boxes digest, in the same order (docs/sources/white-beryl-ch34.md). Each
 * entry was read on the scan of the 1996 edition. A work's list holds only
 * what the chapter names plainly on one side: an entry qualified by a kind
 * of the work, named on both sides, called middling or acceptable, or left
 * in doubt by the print is not here (SPEC §5.10).
 *
 * The chapter is the source the boxes digest, so it [join]s them: what it
 * names good is added to the box's good and taken from its bad, and the
 * other way round; what the box names and the chapter does not keeps the
 * box's reading. Works the print has no box for are lists of their own.
 */
object WhiteBerylWorks {
    private fun m(vararg x: Mansion) = x.toList()
    private fun waning(vararg except: Int) = (16..30).toSet() - except.toSet()

    /** For each of the print's boxes, by its wording, what the chapter adds to it or decides against it. */
    val TO_BOXES: List<ActivityList> = listOf(
        // 2. Naming, p. 385: «ཁྲུམས་སྟོད(༢༤)», where the box has khrums smad.
        ActivityList("naming", good = Factors(mansions = m(PURVABHADRAPADA)), bad = Factors()),
        // 5. Offerings, p. 386: «ཁྲུམས་སྟོད(༢༤) དབྱུག(༠)» good, «ཁྲུམས་སྨད(༢༥)» bad (open question 6).
        ActivityList(
            "offerings_to_deities",
            good = Factors(mansions = m(PURVABHADRAPADA, ASHVINI)),
            bad = Factors(mansions = m(UTTARABHADRAPADA)),
        ),
        // 6. Building, p. 388: Mūla good, and good for gates as well.
        ActivityList("building_walls", good = Factors(mansions = m(MULA)), bad = Factors()),
        // 7. A new home, p. 389: the 13th among the good dates.
        ActivityList("taking_a_new_home", good = Factors(dates = setOf(13)), bad = Factors()),
        // 8. The hearth, pp. 389–390: Anurādhā bad; «ཉི་མ་ཁྱི་ངན», the Ox day good only for a smith's hearth.
        ActivityList("setting_up_hearth_and_pillars", good = Factors(), bad = Factors(mansions = m(ANURADHA), animals = setOf(DOG))),
        // 9. Opening the soil, taming oxen, p. 390: the dates read «དྲུག» where the box has ༤.
        ActivityList(
            "manuring_and_breaking_in_oxen",
            good = Factors(dates = setOf(6, 16, 26), mansions = m(SHRAVANA)),
            bad = Factors(dates = setOf(29)),
        ),
        // 11. Feasts, p. 391: «ཉི་མ་ཁྱི་ཕག་བཟང».
        ActivityList("feasts", good = Factors(animals = setOf(PIG)), bad = Factors()),
        // 12. Journeys, p. 393: dates to avoid in every direction; the Dragon day, setting out.
        ActivityList(
            "setting_out_on_journeys",
            good = Factors(),
            bad = Factors(dates = setOf(2, 8, 14, 20, 26, 4), animals = setOf(DRAGON)),
        ),
        // 13. Trade, p. 394–395: Pūrvāṣāḍhā and Uttarabhādrapadā good; «གླང་སྦྲུལ་ཉི་ནག་ངན», the Ox also good for buying.
        ActivityList(
            "trade",
            good = Factors(mansions = m(PURVASHADHA, UTTARABHADRAPADA)),
            bad = Factors(animals = setOf(SNAKE)),
        ),
        // 15. Planting, p. 396: both khrums good; the khon trigram avoided.
        ActivityList("planting_trees_and_flowers", good = Factors(mansions = m(UTTARABHADRAPADA)), bad = Factors(trigrams = setOf(KHON))),
        // 17. Tents and felt, p. 396: Tuesday bad; «སྒྲོག(༢༣)» where the box has mon gre.
        ActivityList("sewing_tents", good = Factors(), bad = Factors(weekdays = setOf(TUESDAY), mansions = m(SHATABHISHAJ))),
        // 18. Storehouses, p. 396: Kṛttikā and Bharaṇī very bad (Pūrvaphalgunī also good for opening one).
        ActivityList("building_storehouses", good = Factors(), bad = Factors(mansions = m(KRITTIKA, BHARANI))),
        // 19. Banners, p. 397: Tuesday bad.
        ActivityList("raising_banners", good = Factors(), bad = Factors(weekdays = setOf(TUESDAY))),
        // 23. Sowing, p. 400: «བཅོ་བརྒྱད་བཅུ་དགུ» good, «ཉེར་བརྒྱད» bad.
        ActivityList("sowing", good = Factors(dates = setOf(18, 19)), bad = Factors(dates = setOf(28))),
        // 24. Gifts and dowries, p. 401: «སྤར་ཁ་ཁེན» avoided.
        ActivityList("giving_gifts_and_dowries", good = Factors(), bad = Factors(trigrams = setOf(KHEN))),
        // 26. Treating horses, p. 401.
        ActivityList(
            "treating_horses_mules_and_donkeys",
            good = Factors(mansions = m(MRIGASHIRAS, DHANISHTHA)),
            bad = Factors(weekdays = setOf(SUNDAY, SATURDAY), mansions = m(MAGHA, PURVAPHALGUNI, ANURADHA)),
        ),
        // 30. Dogs, p. 402: khrums smad among the best.
        ActivityList("raising_dogs", good = Factors(mansions = m(UTTARABHADRAPADA)), bad = Factors()),
        // 31. Writing and astrology, p. 402: the 22nd good with the 18th.
        ActivityList("learning_writing_and_astrology", good = Factors(dates = setOf(22)), bad = Factors()),
        // 33. Bloodletting and moxa, pp. 403–404.
        ActivityList(
            "bloodletting_and_moxibustion",
            good = Factors(dates = setOf(11)),
            bad = Factors(weekdays = setOf(SUNDAY, SATURDAY), dates = setOf(4), mansions = m(UTTARABHADRAPADA)),
        ),
        // 34. Bathing and washing the hair, p. 404.
        ActivityList("bathing_and_washing_the_hair", good = Factors(mansions = m(ANURADHA)), bad = Factors(animals = setOf(DOG, TIGER))),
        // 36. Enthronement, p. 405: the box names no dates. 8–10 are good, and banned by the Chinese reckoning.
        ActivityList(
            "enthronement",
            good = Factors(dates = setOf(1, 2, 11, 12, 13, 15, 18)),
            bad = Factors(dates = setOf(6, 7, 16) + (19..30)),
        ),
        // 38. Servants, p. 406: the box has the 4th bad and the 14th good, the chapter the reverse.
        ActivityList(
            "taking_servants",
            good = Factors(dates = setOf(4, 5, 16, 24)),
            bad = Factors(dates = setOf(3, 6, 7, 9, 10, 11, 12, 13, 14, 21, 22, 23, 25, 26, 27, 28, 29, 30), trigrams = setOf(KHON)),
        ),
        // 39. Marriage, p. 412: Svātī good (the box names it twice); 14 and 7, good by another system, banned here.
        ActivityList("marriage", good = Factors(mansions = m(SVATI)), bad = Factors(dates = setOf(14, 18, 22))),
        // 40. Ornaments, p. 413: the zin trigram good.
        ActivityList("putting_on_ornaments", good = Factors(trigrams = setOf(ZIN)), bad = Factors()),
        // 41. Funerals, p. 414: the 22nd among the bad dates.
        ActivityList("funerals", good = Factors(), bad = Factors(dates = setOf(22))),
        // 42. Supports and temples, p. 414.
        ActivityList("setting_up_supports", good = Factors(mansions = m(UTTARABHADRAPADA)), bad = Factors()),
        // 45. Fire offerings, pp. 417–418: Wednesday only acceptable.
        ActivityList(
            "fire_offerings",
            good = Factors(dates = setOf(3, 13, 23, 18, 28, 29), mansions = m(VISHAKHA), animals = setOf(TIGER, BIRD)),
            bad = Factors(weekdays = setOf(MONDAY), dates = setOf(2, 12, 22, 17, 27), mansions = m(ARDRA)),
        ),
        // 46. Suppressing sri, p. 418: the box names no dates.
        ActivityList(
            "suppressing_sri",
            good = Factors(dates = setOf(4, 8, 9, 19, 29, 18, 22, 25), trigrams = setOf(GIN)),
            bad = Factors(dates = setOf(1)),
        ),
        // 47. Ordination, teaching, empowerment, p. 418.
        ActivityList(
            "ordination_teaching_and_empowerment",
            good = Factors(
                dates = setOf(3, 5, 10, 14, 11, 23),
                mansions = m(PURVABHADRAPADA, UTTARASHADHA, ANURADHA, REVATI, JYESHTHA, ASHVINI),
            ),
            bad = Factors(dates = setOf(1, 4, 6, 7, 8, 12, 15), mansions = m(SHRAVANA, ASHLESHA)),
        ),
        // 48. Consecration, p. 419: the waxing dates but the 4th and 14th; the 17th and 18th neutral.
        ActivityList(
            "consecration",
            good = Factors(dates = (1..15).toSet() - setOf(4, 14), mansions = m(HASTA, SHATABHISHAJ, REVATI, VISHAKHA, SVATI, JYESHTHA)),
            bad = Factors(dates = waning(17, 18), mansions = m(SHRAVANA, ASHLESHA)),
        ),
        // 49. Dikes, p. 420: the box names no dates.
        ActivityList("building_flood_dikes", good = Factors(dates = setOf(13, 18, 23, 28)), bad = Factors(dates = setOf(2, 17, 22, 27))),
        // 50. Crafts, p. 420: Wednesday and Friday good; five more mansions.
        ActivityList(
            "crafts",
            good = Factors(weekdays = setOf(WEDNESDAY), mansions = m(HASTA, REVATI, ASHVINI, ARDRA, PURVABHADRAPADA)),
            bad = Factors(),
        ),
        // 50. Cutting hair and nails: «ཁྲུམས་སྨད(༢༥)ནི། །བཟོ་བཟང་སྐྲ་སེན་འབྲེག་པ་ངན».
        ActivityList("cutting_hair_and_nails", good = Factors(mansions = m(PURVABHADRAPADA)), bad = Factors(mansions = m(UTTARABHADRAPADA))),
        // 51. War, contests, dice, pp. 420–421: the day animals as the chapter gives them, the box's reversed.
        ActivityList(
            "martial_skills",
            good = Factors(weekdays = setOf(TUESDAY, SATURDAY), dates = setOf(8, 11, 29), mansions = m(UTTARABHADRAPADA), animals = setOf(TIGER, DRAGON)),
            bad = Factors(
                dates = setOf(4, 14, 24),
                mansions = m(PUNARVASU, CITRA, REVATI, PURVAPHALGUNI, BHARANI, KRITTIKA),
                animals = setOf(RABBIT, SNAKE, SHEEP, DOG, MONKEY),
            ),
        ),
        // 51. The same for games; the Monkey day is good for dice.
        ActivityList(
            "games",
            good = Factors(dates = setOf(8, 11, 29), mansions = m(UTTARABHADRAPADA), animals = setOf(TIGER, DRAGON)),
            bad = Factors(
                dates = setOf(4, 14, 24),
                mansions = m(PUNARVASU, CITRA, REVATI, PURVAPHALGUNI, BHARANI, KRITTIKA),
                animals = setOf(RABBIT, SNAKE, SHEEP, DOG),
            ),
        ),
        // 52. Poetics and grammar, p. 421: «སྒྲོན(༡༧)» where the box has snrubs; the 1st, the time to beware for learning.
        ActivityList("composing_treatises_and_learning_poetics", good = Factors(mansions = m(JYESHTHA)), bad = Factors(dates = setOf(1, 6))),
        // 53. Thread-cross and torma, p. 422.
        ActivityList(
            "thread_cross_and_torma_rites",
            good = Factors(dates = setOf(29), mansions = m(PURVASHADHA, CITRA, ASHVINI), animals = setOf(DRAGON)),
            bad = Factors(dates = setOf(2, 6), animals = setOf(TIGER)),
        ),
        // 55. Rain, p. 423: the mansions with water in them good, these bad, where the box has them good.
        ActivityList(
            "bringing_rain",
            good = Factors(mansions = m(PUNARVASU, VISHAKHA)),
            bad = Factors(mansions = m(MAGHA, JYESHTHA, MULA, REVATI)),
        ),
        // 57. Councils, pp. 424–425.
        ActivityList("council", good = Factors(), bad = Factors(dates = setOf(22), trigrams = setOf(KHON))),
        // 65. Increasing, p. 427: «ནམ་གྲུ(༢༦) མོན་གྲུ(༢༣) བཟང».
        ActivityList("increasing_activity", good = Factors(mansions = m(SHATABHISHAJ)), bad = Factors()),
    )

    /** The chapter's works the print has no box for, as lists of their own. */
    val OWN: List<ActivityList> = listOf(
        // 10. Shows, p. 390: Sunday acceptable, Wednesday neutral.
        ActivityList(
            "spectacles",
            good = Factors(
                weekdays = setOf(FRIDAY, MONDAY, THURSDAY),
                mansions = m(KRITTIKA, ASHVINI, MRIGASHIRAS, PUSHYA, PURVASHADHA, SHRAVANA, SHATABHISHAJ),
                animals = setOf(DOG, HORSE, MONKEY),
            ),
            bad = Factors(
                weekdays = setOf(TUESDAY, SATURDAY),
                mansions = m(BHARANI, ASHLESHA, HASTA, SVATI, UTTARABHADRAPADA, CITRA),
                animals = setOf(TIGER, RABBIT, BIRD),
            ),
        ),
        // 20. Hunting and theft, pp. 397–398: Thursday middling; mansions named for theft alone left out.
        ActivityList(
            "hunting_and_theft",
            good = Factors(
                weekdays = setOf(SUNDAY, TUESDAY, SATURDAY),
                dates = setOf(8, 29),
                mansions = m(UTTARABHADRAPADA, MRIGASHIRAS, ARDRA, MULA, ANURADHA, MAGHA, UTTARAPHALGUNI, JYESHTHA, ASHVINI, DHANISHTHA),
                animals = setOf(DRAGON, DOG, MOUSE),
            ),
            bad = Factors(
                weekdays = setOf(MONDAY, WEDNESDAY, FRIDAY),
                dates = setOf(16),
                mansions = m(KRITTIKA, ROHINI, PUSHYA, PURVAPHALGUNI, CITRA, PUNARVASU, SVATI, SHRAVANA, PURVABHADRAPADA),
                animals = setOf(RABBIT, SNAKE, MONKEY),
                trigrams = setOf(KHAM, LI),
            ),
        ),
        // 28. Taming horses, p. 401: «ཁྱིམ་ཉི་རྒྱུག་དང་མཚུངས», otherwise as horse racing (the print's box 28).
        ActivityList(
            "breaking_in_horses",
            good = Factors(
                weekdays = setOf(SUNDAY, TUESDAY, THURSDAY, MONDAY),
                mansions = m(
                    KRITTIKA, SHATABHISHAJ, PUSHYA, PUNARVASU, SHRAVANA, DHANISHTHA, MRIGASHIRAS,
                    ROHINI, SVATI, VISHAKHA, PURVABHADRAPADA, UTTARAPHALGUNI, ASHVINI,
                ),
                animals = setOf(RABBIT),
                trigrams = setOf(GIN),
            ),
            bad = Factors(weekdays = setOf(SATURDAY), dates = setOf(11, 22, 29), animals = setOf(MOUSE, HORSE)),
        ),
        // 44. Averting rites, p. 417: the box names a kind of rite in every entry; the chapter has these plainly.
        ActivityList(
            "averting_rites",
            good = Factors(
                weekdays = setOf(SUNDAY),
                dates = setOf(15, 22, 29),
                mansions = m(
                    ARDRA, PUNARVASU, PUSHYA, JYESHTHA, MULA, PURVASHADHA, UTTARASHADHA,
                    UTTARABHADRAPADA, CITRA, MAGHA, UTTARAPHALGUNI, SVATI, ASHVINI,
                ),
                animals = setOf(TIGER, DRAGON),
                trigrams = setOf(GIN, KHAM, ZON),
            ),
            bad = Factors(
                weekdays = setOf(SATURDAY),
                dates = setOf(1),
                mansions = m(BHARANI, DHANISHTHA, SHATABHISHAJ, REVATI, ANURADHA, ROHINI),
                animals = setOf(RABBIT, SNAKE),
            ),
        ),
        // 54. Sorcery, p. 422: fifteen mansions only acceptable, «གཞན་རྣམས་སྤང».
        ActivityList(
            "sorcery",
            good = Factors(weekdays = setOf(TUESDAY, THURSDAY), mansions = m(ANURADHA, JYESHTHA), animals = setOf(TIGER, DRAGON)),
            bad = Factors(
                weekdays = setOf(WEDNESDAY, FRIDAY),
                mansions = m(KRITTIKA, ROHINI, PUNARVASU, PUSHYA, PURVAPHALGUNI, CITRA, SVATI, UTTARASHADHA, REVATI),
                animals = setOf(SNAKE, RABBIT),
            ),
        ),
    )

    private fun z(vararg x: ZodiacSign) = x.toSet()
    private fun others(vararg x: ZodiacSign) = ZodiacSign.entries.toSet() - x.toSet()

    /**
     * Each work's own rising signs, good and bad, by its wording, read on the scans
     * (docs/sources/white-beryl-ch34.md, *Rising signs*): the times of the day, counted from the
     * combination period's table, that the chapter names for the work. A sign it calls middling or
     * acceptable, names for a kind of the work only, or names on both sides is in neither; "the
     * others bad" is applied as stated. The print's boxes name signs too and are not read for them.
     */
    val SIGNS: Map<String, Pair<Set<ZodiacSign>, Set<ZodiacSign>>> = mapOf(
        // 2, p. 385: «འཁྲིག་གཞུ་བུམ་ཉ་བཟང་། །སྲང་དང་སྡིག་པ་གཉིས་ལ་ངན།།»
        "naming" to (z(GEMINI, SAGITTARIUS, AQUARIUS, PISCES) to z(LIBRA, SCORPIO)),
        // 3, p. 385: «…བུ་མོ་ཉ་བཟང་ཀར་སྡིག་པ། །ཆུ་སྲིན་ངན་ལ་གཞན་བཏང་སྙོམས།།»
        "new_clothes" to (z(TAURUS, GEMINI, LEO, VIRGO, PISCES) to z(CANCER, SCORPIO, CAPRICORN)),
        // 4, p. 386.
        "first_wearing_of_new_clothes" to (z(TAURUS, GEMINI, LEO, VIRGO, SAGITTARIUS) to z(CANCER, SCORPIO)),
        // 5, p. 386: «དུས་སྦྱོར་གླང་འཁྲིག་སེང་གེ་བཟང་། །ལུག་གཞུ་ཆུ་སྲིན་ཀར་སྡིག་ངན།།»
        "offerings_to_deities" to (z(TAURUS, GEMINI, LEO) to z(ARIES, SAGITTARIUS, CAPRICORN, CANCER, SCORPIO)),
        // 6, p. 388: Gemini bad for forts and good for temples, Pisces the other way: neither.
        "building_walls" to (z(TAURUS, LEO, SAGITTARIUS, AQUARIUS, VIRGO) to z(ARIES, SCORPIO, CANCER, CAPRICORN, LIBRA)),
        // 7, p. 389: «དུས་སྦྱོར་བུམ་གླང་བཟང་བ་དང་།»
        "taking_a_new_home" to (z(AQUARIUS, TAURUS) to z()),
        // 8, p. 390.
        "setting_up_hearth_and_pillars" to (z(LEO, AQUARIUS, PISCES) to z()),
        // 9, p. 390: «དུས་སྦྱོར་སྲང་གཞུ་བུམ་ཉ་བཟང་།»
        "manuring_and_breaking_in_oxen" to (z(LIBRA, SAGITTARIUS, AQUARIUS, PISCES) to z()),
        // 10, p. 390.
        "spectacles" to (z(TAURUS, GEMINI, PISCES, SAGITTARIUS, LEO) to z()),
        // 12, p. 393: «…ཉ་བཟང་གཞན་རྣམས་ངན་པ་ཡིན།»
        "setting_out_on_journeys" to (z(CAPRICORN, VIRGO, SAGITTARIUS, PISCES) to others(CAPRICORN, VIRGO, SAGITTARIUS, PISCES)),
        // 13, p. 394: «སྲང་སྡིག་བུ་མོ་གླང་དང་འཁྲིག །སེང་གེ་བཟང་ལ་ལུག་ལ་སྤང་།»
        "trade" to (z(LIBRA, SCORPIO, VIRGO, TAURUS, GEMINI, LEO) to z(ARIES)),
        // 15, p. 396.
        "planting_trees_and_flowers" to (z(TAURUS, GEMINI, CAPRICORN, AQUARIUS, PISCES) to z(ARIES, LEO, LIBRA, SCORPIO, VIRGO)),
        // 16, p. 396.
        "reconciliation" to (z(ARIES, LEO, VIRGO, PISCES) to z(CANCER, SCORPIO, CAPRICORN)),
        // 17, p. 396.
        "sewing_tents" to (z(LEO, SAGITTARIUS, AQUARIUS, PISCES, TAURUS) to z(CAPRICORN, SCORPIO)),
        // 18, pp. 396–397.
        "building_storehouses" to (z(TAURUS, AQUARIUS, PISCES) to z(CANCER, SCORPIO)),
        // 19, p. 397: the bad ones avoided with the black day and the like.
        "raising_banners" to (z(TAURUS, GEMINI, LEO, SAGITTARIUS) to z(CAPRICORN, SCORPIO, LIBRA, CANCER)),
        // 20, p. 398: two lists; what the second leaves uncertain and the first names (Cancer, Libra, Scorpio) is in neither.
        "hunting_and_theft" to (z(ARIES, SAGITTARIUS, CAPRICORN, AQUARIUS) to z(VIRGO, PISCES)),
        // 22, p. 399.
        "digging_ponds_canals_and_wells" to (z(GEMINI, AQUARIUS, CAPRICORN, SAGITTARIUS, TAURUS, CANCER) to z(ARIES, LEO)),
        // 23, p. 400.
        "sowing" to (z(PISCES, AQUARIUS, LIBRA, TAURUS, CAPRICORN) to z(ARIES, CANCER, SCORPIO)),
        // 25, p. 401; 26–28 take its signs: «ཁྱིམ་ཉི་ལ་སོགས་རྒྱུག་དང་མཚུངས།»
        "feeding_up_horses" to (z(TAURUS, LEO, SAGITTARIUS) to z(CANCER, AQUARIUS)),
        "treating_horses_mules_and_donkeys" to (z(TAURUS, LEO, SAGITTARIUS) to z(CANCER, AQUARIUS)),
        "saddling" to (z(TAURUS, LEO, SAGITTARIUS) to z(CANCER, AQUARIUS)),
        "breaking_in_horses" to (z(TAURUS, LEO, SAGITTARIUS) to z(CANCER, AQUARIUS)),
        // 31, p. 402: Gemini good for learning the arts only.
        "learning_writing_and_astrology" to (z(PISCES, AQUARIUS, TAURUS, LEO, LIBRA, VIRGO, SAGITTARIUS) to z(SCORPIO)),
        // 32, p. 403: «དུས་སྦྱོར་བུ་མོ་མཆོག་ཡིན་ལ།»
        "preparing_medicine" to (z(VIRGO, GEMINI, SAGITTARIUS, PISCES) to z(CAPRICORN, SCORPIO, CANCER)),
        // 33, p. 404.
        "bloodletting_and_moxibustion" to (z(TAURUS, GEMINI, VIRGO, PISCES) to z(ARIES, SCORPIO, CAPRICORN, AQUARIUS)),
        // 34, p. 404.
        "bathing_and_washing_the_hair" to (z(PISCES, AQUARIUS, CAPRICORN, VIRGO) to z(LIBRA, CANCER)),
        // 35, p. 405: «གཞུ་ཉ་སེང་གེ་མཆོག་ཡིན་ཏེ། །གླང་དང་བུ་མོ་སྲང་ཡང་བཟང་།» — the best, then "also good".
        "astrology_and_divination" to (z(SAGITTARIUS, PISCES, LEO, TAURUS, VIRGO, LIBRA) to z()),
        // 36, p. 405: Sagittarius, Pisces and Aquarius only acceptable.
        "enthronement" to (z(LEO) to z(CAPRICORN, SCORPIO)),
        // 37, p. 405.
        "making_weapons" to (z(SCORPIO, ARIES, TAURUS, LEO, PISCES) to z(LIBRA, CAPRICORN, CANCER)),
        // 38, p. 406.
        "taking_servants" to (z(ARIES, SAGITTARIUS, LEO, CAPRICORN, TAURUS) to z(CANCER, LIBRA)),
        // 39, p. 412: «…ཉ་བཟང་ལུག་སྲང་སྡིག་ཆུ་ངན།», chu for chu srin.
        "marriage" to (z(TAURUS, GEMINI, LEO, SAGITTARIUS, VIRGO, PISCES) to z(ARIES, LIBRA, SCORPIO, CAPRICORN)),
        // 40, p. 413.
        "putting_on_ornaments" to (z(TAURUS, GEMINI, VIRGO, SAGITTARIUS, PISCES, LEO) to z()),
        // 42, p. 414.
        "setting_up_supports" to (z(TAURUS, GEMINI, VIRGO, AQUARIUS, SAGITTARIUS, LEO) to z(ARIES, LIBRA, CANCER, SCORPIO, CAPRICORN, PISCES)),
        // 45, p. 418.
        "fire_offerings" to (z(TAURUS, ARIES, LEO) to z(CAPRICORN, PISCES)),
        // 46, p. 418.
        "suppressing_sri" to (z(LEO, TAURUS, SAGITTARIUS, CANCER, SCORPIO, CAPRICORN) to z()),
        // 47, p. 418.
        "ordination_teaching_and_empowerment" to (z(ARIES, TAURUS, CAPRICORN, LEO, AQUARIUS, PISCES) to z(GEMINI, VIRGO, SAGITTARIUS)),
        // 48, p. 419.
        "consecration" to (z(LEO, GEMINI, SCORPIO, AQUARIUS) to z(ARIES, LIBRA, CANCER, CAPRICORN)),
        // 49, p. 420: «…ཆུ་བོ་བསྲུང་བའི་ལས་ལ་བཟང་།», the work itself.
        "building_flood_dikes" to (z(CANCER, ARIES, LIBRA, VIRGO, LEO) to z(AQUARIUS, SAGITTARIUS, PISCES)),
        // 50, p. 420, for both halves.
        "crafts" to (z(ARIES, TAURUS, GEMINI, LEO, VIRGO, PISCES) to z(SCORPIO, LIBRA, CAPRICORN, CANCER)),
        "cutting_hair_and_nails" to (z(ARIES, TAURUS, GEMINI, LEO, VIRGO, PISCES) to z(SCORPIO, LIBRA, CAPRICORN, CANCER)),
        // 51, p. 421, for both halves.
        "martial_skills" to (z(SAGITTARIUS, CANCER, ARIES, LEO) to z(VIRGO, PISCES, SCORPIO, AQUARIUS)),
        "games" to (z(SAGITTARIUS, CANCER, ARIES, LEO) to z(VIRGO, PISCES, SCORPIO, AQUARIUS)),
        // 52, p. 421.
        "composing_treatises_and_learning_poetics" to (z(PISCES, TAURUS, GEMINI, LEO) to z()),
        // 53, p. 422.
        "thread_cross_and_torma_rites" to (z(SCORPIO, CAPRICORN, ARIES, LEO, CANCER, PISCES) to z()),
        // 54, p. 422: Libra and Cancer middling.
        "sorcery" to (z(LEO, SCORPIO, SAGITTARIUS, ARIES, TAURUS, CAPRICORN) to z()),
        // 55, p. 423.
        "bringing_rain" to (z(CANCER, AQUARIUS, PISCES, CAPRICORN) to z(SCORPIO, ARIES, LIBRA)),
        // 57, p. 425.
        "council" to (z(TAURUS, LEO, VIRGO, SAGITTARIUS, AQUARIUS) to z(ARIES, CANCER, LIBRA, SCORPIO, CAPRICORN)),
        // 59, pp. 425–426: Libra, Scorpio and Capricorn bad, and good for lasting works: neither.
        "virtuous_acts_for_the_living" to (z(LEO, TAURUS, AQUARIUS, VIRGO, SAGITTARIUS) to z(ARIES)),
        // 60, p. 426.
        "auspicious_work" to (z(TAURUS, LEO, SAGITTARIUS, AQUARIUS, VIRGO) to z(CANCER, SCORPIO, CAPRICORN, LIBRA, ARIES)),
        // 61, p. 426.
        "health_and_wealth" to (z(GEMINI, TAURUS) to z(ARIES, CANCER, CAPRICORN, SCORPIO)),
        // 62, p. 427: «…འབྲིང་ལ་གཞན་རྣམས་སྤང་བ་ཡིན།»
        "controlling_activity" to (z(TAURUS, LIBRA, LEO) to z(ARIES, GEMINI, CANCER, SCORPIO, CAPRICORN)),
        // 63, p. 427.
        "destructive_activity" to (z(LEO, SCORPIO, TAURUS, CAPRICORN, SAGITTARIUS, ARIES) to z()),
        // 64, p. 427.
        "pacifying_activity" to (z(AQUARIUS, LEO, SAGITTARIUS, PISCES, VIRGO) to z()),
        // 65, p. 428: «…སེང་གླང་འབྲིང་ཡིན་གཞན་རྣམས་ངན།»
        "increasing_activity" to (z(GEMINI, VIRGO, SAGITTARIUS, CANCER, AQUARIUS) to others(GEMINI, VIRGO, SAGITTARIUS, CANCER, AQUARIUS, LEO, TAURUS)),
    )

    /** The print's boxes with the chapter joined to each, then the chapter's own works, each with its rising signs. */
    fun join(boxes: List<ActivityList>): List<ActivityList> {
        val byWording = TO_BOXES.associateBy { it.wording }
        require(byWording.keys.all { w -> boxes.any { it.wording == w } }) { "a work joined to no box" }
        val joined = boxes.map { box -> byWording[box.wording]?.let { box.joined(it) } ?: box } + OWN
        require(SIGNS.keys.all { w -> joined.any { it.wording == w } }) { "rising signs for no work" }
        return joined.map { a -> SIGNS[a.wording]?.let { (g, b) -> ActivityList(a.wording, a.good.copy(signs = g), a.bad.copy(signs = b)) } ?: a }
    }

    private fun ActivityList.joined(wb: ActivityList) =
        ActivityList(wording, good = good.joined(wb.good, wb.bad), bad = bad.joined(wb.bad, wb.good))

    private fun Factors.joined(add: Factors, take: Factors) = Factors(
        weekdays = weekdays - take.weekdays + add.weekdays,
        dates = dates - take.dates + add.dates,
        mansions = (mansions - take.mansions.toSet() + add.mansions).distinct(),
        animals = animals - take.animals + add.animals,
        trigrams = trigrams - take.trigrams + add.trigrams,
    )
}
