/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import zanshin.core.tibetan.Animal.*
import zanshin.core.tibetan.Mansion.*
import zanshin.core.tibetan.Trigram.*
import zanshin.core.tibetan.Weekday.*

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

    /** The print's boxes with the chapter joined to each, then the chapter's own works. */
    fun join(boxes: List<ActivityList>): List<ActivityList> {
        val byWording = TO_BOXES.associateBy { it.wording }
        require(byWording.keys.all { w -> boxes.any { it.wording == w } }) { "a work joined to no box" }
        return boxes.map { box -> byWording[box.wording]?.let { box.joined(it) } ?: box } + OWN
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
