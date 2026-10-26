/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.texts

import zanshin.core.tibetan.Animal
import zanshin.core.tibetan.HourFall
import zanshin.core.tibetan.LordPlace
import zanshin.core.tibetan.LordSeat
import zanshin.core.tibetan.MonthLord
import zanshin.core.tibetan.MonthStanding
import zanshin.core.tibetan.YearLord
import zanshin.core.tibetan.YearStanding

/**
 * The readings of the year's and the month's earth lords (ROADMAP T2.26, SPEC §5.13; WB vol. 2, ch. 31, pp. 174–223,
 * docs/sources/year-month-earth-lords.md). Each lord's paragraph is `reading.YearLord.<LORD>` or `reading.MonthLord.<LORD>`,
 * its rows beside it, its *Avoid* list the wordings below, and its *How it falls* the place it holds on the shown day.
 * None of them is weighed (D4): a lord bars the works it names in its place, with its remedy.
 */
object LordTexts {
    /** The works each year's lord bars in its place, as the wordings of the catalog (`wording.<key>`). */
    val YEAR_AVOID: Map<YearLord, List<String>> by lazy { YEAR_AVOID_A + YEAR_AVOID_B }

    /** The works each month's lord bars in its place. */
    val MONTH_AVOID: Map<MonthLord, List<String>> by lazy { MONTH_AVOID_C + MONTH_AVOID_D + MONTH_AVOID_E }

    /** The works that go well towards a month's lord: the six gods of the sky line (p. 220). */
    val MONTH_GOOD: Map<MonthLord, List<String>> by lazy { MONTH_GOOD_E }

    fun year(s: YearStanding): Reading = Reading(
        avoidKeys = YEAR_AVOID[s.lord].orEmpty(),
        source = Sources.WHITE_BERYL_YEAR_EARTH_LORDS,
        key = "reading.YearLord.${s.lord.name}",
        falls = listOf(HourFall("yearLord", listOf(yearAnimal(s.year), entry(s.lord.entry), place(s.seats, "YearLord.${s.lord.name}")))),
    )

    fun month(s: MonthStanding): Reading = Reading(
        goodKeys = MONTH_GOOD[s.lord].orEmpty(),
        avoidKeys = MONTH_AVOID[s.lord].orEmpty(),
        source = Sources.WHITE_BERYL_MONTH_EARTH_LORDS,
        also = listOf(Sources.WHITE_BERYL_MODEL_ALMANAC),
        key = "reading.MonthLord.${s.lord.name}",
        falls = listOf(HourFall("monthLord", listOf(s.monthAnimal, place(s.seats, "MonthLord.${s.lord.name}")))),
    )

    /** The year's animal as a *How it falls* line names it: the Tibetan year whose Losar falls in Gregorian [year]. */
    private fun yearAnimal(year: Int): Animal = zanshin.core.tibetan.YearMonthEarthLords.yearAnimal(year)

    /** When a year's lord takes the year's place, counted from the winter solstice. */
    fun entry(days: Int): String = when {
        days == 0 -> Catalog.text("LordEntry.solstice")
        days > 0 -> Catalog.format("LordEntry.after", days.toString())
        else -> Catalog.format("LordEntry.before", (-days).toString())
    }

    /** One place in running text: an animal's place with its name, a direction, a place in the house, a line, the whole year. */
    fun place(p: LordPlace): String = when (p) {
        is LordPlace.OnAnimal -> Catalog.format("LordPlace.animal", Catalog.text(glossKey(p.animal, "of")), Catalog.text(glossKey(p.animal, "place")))
        is LordPlace.InDirection -> Catalog.text(glossKey(p.direction))
        is LordPlace.InHouse -> Catalog.text(glossKey(p.place))
        is LordPlace.Along -> Catalog.format("LordPlace.along", Catalog.text(glossKey(p.from)), Catalog.text(glossKey(p.to)))
        LordPlace.Whole -> Catalog.text("LordPlace.whole")
    }

    /** The places a lord holds, each under its part or member where it has several: "head: …; tail: …". */
    fun place(seats: List<LordSeat>, lordKey: String): String = seats.joinToString("; ") { s ->
        val label = s.part?.let { Catalog.text(glossKey(it)) } ?: s.member?.let { Catalog.text("$lordKey.member.$it") }
        label?.let { Catalog.format("LordPlace.labelled", it, place(s.place)) } ?: place(s.place)
    }
}

// The lists below are written one block to a part of the chapter; each block keeps to its own lords.

// ---------------------------------------------------------------- the year's lords, from the king to the hal khyi (pp. 177–185)

private val YEAR_AVOID_A: Map<YearLord, List<String>> = mapOf(
    YearLord.TESE to listOf(
        "founding_a_palace", "building_a_house", "promotions", "war", "raising_banners", "feasts", "building_a_hearth",
        "building_a_throne", "moving_into_a_fort", "a_bride", "taking_out_a_corpse", "the_kings_affairs", "setting_out", "moving_a_grave",
    ),
    YearLord.TEKHYIM to listOf("taking_out_a_corpse", "building_forts", "funeral_rites", "a_bride", "oaths"),
    YearLord.HARPHEN to listOf(
        "building_a_hearth", "moving_house", "a_bride", "taking_out_a_corpse", "funeral_rites", "spectacles", "contests_of_skill",
        "founding_a_palace", "war", "building_forts",
    ),
    YearLord.TESO to listOf("moving_house", "rites_of_increase", "taking_a_child_out", "building_forts", "feasts", "harmful_works"),
    YearLord.TSANGKUN to listOf(
        "raising_banners", "councils", "carrying_out_a_corpse", "mediating_in_fights", "funerals", "building_forts", "moving_house", "feasts",
    ),
    // The astrologer spirit's own works (p. 180), as the day's and the hour's give them (Texts.BLA_MKHYEN).
    YearLord.SEBA_LAKHYEN to listOf(
        "markets", "any_building_work", "seeking_land", "taking_out_a_corpse", "building_graves", "founding_a_palace",
        "funeral_rites", "taking_in_a_dog", "setting_up_a_gate", "building_a_hearth", "war", "robbery",
    ),
    YearLord.HANGPHEN to listOf(
        "building_forts", "taking_in_a_dog", "feasts", "taking_out_a_corpse", "funerals", "war", "robbery", "moving_house", "a_bride",
        "matchmaking",
    ),
    YearLord.SEBYI to listOf(
        "giving_and_taking_loans", "taking_out_a_corpse", "war", "funerals", "building_forts", "feasts", "a_bride", "trade", "councils",
        "judging_disputes", "robbery", "giving_and_taking_dogs", "giving_and_taking_cats",
    ),
    YearLord.RANGTA to listOf(
        "buying_horses", "horse_racing", "a_bride", "building_horse_pens_and_stables", "trading_horses", "spectacles", "funerals",
        "feasts", "war",
    ),
    YearLord.TAKHRI to listOf("taking_out_a_corpse", "funerals", "horse_works"),
    YearLord.SEBYA to listOf(
        "taking_servants", "funerals", "building_forts", "taking_out_a_corpse", "hurling_zor", "fire_offerings", "a_bride", "war", "burial",
    ),
    YearLord.DENDING to listOf("taking_out_a_corpse", "war", "feasts", "a_bride", "markets", "spreading_seats", "building_a_throne"),
    YearLord.KUNGNYUL to listOf(
        "taking_out_a_corpse", "war", "hurling_zor", "setting_out", "building_a_hearth", "a_bride", "games", "contests_of_skill",
        "horse_racing", "mandala_rites", "building_forts", "founding_a_palace", "setting_boundary_stakes", "spectacles",
    ),
    YearLord.SACHAK to listOf(
        "war", "robbery", "founding_a_palace", "taking_out_a_corpse", "games", "contests_of_skill", "horse_racing", "building_forts",
        "setting_boundary_stakes",
    ),
    YearLord.HAL_KHYI to listOf("taking_out_a_corpse", "a_bride", "leading_an_army", "building_forts", "moving_house", "taking_in_cats"),
)

// ---------------------------------------------------------------- the year's lords, from Karsham to the sky dog (pp. 186–197)
//
//
//

/** The black years' works (p. 190): the great and the lesser bar the same, the lesser a little less. */
private val BLACK_YEAR_AVOID = listOf(
    "laying_foundations", "pulling_down_an_old_fort", "raising_great_supports", "drawing_water_channels", "felling_trees", "work_with_rock",
    "levelling_hills", "giving_or_taking_a_bride", "war", "robbery", "gatherings", "great_displays",
)

private val YEAR_AVOID_B: Map<YearLord, List<String>> = mapOf(
    YearLord.KARSHAM to listOf("a_bride", "taking_out_a_corpse", "building_forts", "funeral_rites", "marriage_talks", "digging", "feasts"),
    YearLord.BALTE to listOf("a_bride", "taking_out_a_corpse", "building_forts", "funeral_rites", "marriage_talks", "digging", "feasts"),
    YearLord.LANGGING to listOf("war", "robbery", "disputes", "mustering_troops", "martial_skills"),
    YearLord.TSANGKUN_GENERAL to listOf("war", "taking_out_a_corpse", "thread_cross_and_torma_rites", "hurling_zor"),
    YearLord.TSANGKUN_SPLITTER to listOf("a_bride", "secret_counsel"),
    YearLord.TSANGKUN_DOG_HEADED to listOf("building_forts", "taking_out_a_corpse", "moving_house"),
    YearLord.LAKHYEN_PEU to listOf("building_forts", "taking_out_a_corpse", "a_bride"),
    YearLord.ZINPHUNG to listOf("every_work", "a_bride", "building_forts", "works_of_a_grave"),
    YearLord.EARTH_KINGS to listOf("markets", "funeral_rites", "shows", "quarrels", "a_bride", "councils", "marriage_talks"),
    YearLord.BLACK_YEAR to BLACK_YEAR_AVOID,
    YearLord.LESSER_BLACK_YEAR to BLACK_YEAR_AVOID,
    YearLord.NB_KING to listOf("sharpening_weapons", "war", "robbery", "mustering_troops", "violent_deeds"),
    YearLord.NB_KALA to listOf("taking_out_a_corpse"),
    YearLord.NB_LAKHYEN to listOf("digging", "burial"),
    YearLord.NB_ILLNESS_DEMON to listOf("taking_out_a_corpse", "moving_the_sick"),
    YearLord.NB_TIGER_LEOPARD to listOf("moving_house"),
    YearLord.NB_LORD_OF_DEATH to listOf("funeral_feasts", "burial"),
    YearLord.NB_BLACK_ONE to listOf("a_bride", "taking_out_a_corpse"),
    YearLord.SME_1 to listOf("a_young_persons_corpse", "a_womans_corpse", "a_bride", "offerings_to_deities", "leading_an_army", "childrens_feasts", "taking_a_child_out"),
    YearLord.SME_2 to listOf("every_work", "funeral_rites", "building_forts", "taking_out_a_corpse", "a_bride", "markets", "moving_house", "sounding_music"),
    YearLord.SME_3 to listOf("taking_out_a_corpse", "digging", "moving_house", "building_forts", "digging_ponds_and_canals", "felling_nyen_trees"),
    YearLord.SME_4 to listOf("corpse_of_an_epidemic_or_naga_illness", "a_womans_corpse", "a_bride", "digging_springs"),
    YearLord.SME_5 to listOf("building_a_mandala_or_a_palace", "fierce_fire_offerings", "corpse_of_bile_disorder_or_dropsy", "setting_up_a_door", "a_monks_corpse"),
    YearLord.SME_6 to listOf("a_bon_priests_corpse", "cremating_after_smallpox", "councils", "judging_disputes"),
    YearLord.SME_7 to listOf("fights", "war", "red_work", "a_corpse_killed_by_a_blade", "contests_of_skill"),
    YearLord.SME_8 to listOf("a_stake_for_a_lepers_corpse", "a_widows_or_criminals_corpse"),
    YearLord.SME_9 to listOf("lending", "paying_back", "corpse_of_the_auspicious_or_prosperous"),
    YearLord.HIDDEN to listOf("taking_out_a_corpse"),
    YearLord.SKY_DOG to listOf("building_forts", "funeral_rites", "taking_out_a_corpse", "a_bride"),
)

// ---------------------------------------------------------------- the month's lords, from the bad directions to Zinphung (pp. 199–207)
//
//
//

private val MONTH_AVOID_C: Map<MonthLord, List<String>> = mapOf(
    MonthLord.BAD_DIRECTION to listOf("every_work", "hiding_a_corpse", "digging", "carrying_out_a_corpse"),
    MonthLord.DRAGON_GOD to listOf("moving_the_storeroom_or_hearth", "building", "corpse_work", "using_the_hand_mill"),
    MonthLord.GRUMBU to listOf("work_on_the_storeroom", "work_on_the_hearth", "using_the_hand_mill", "feasts", "building", "corpse_work", "slaughtering_livestock"),
    MonthLord.MONTH_LUCK to listOf("sending_messages", "giving_wealth_and_food", "war", "theft", "burial", "raising_flags", "setting_up_a_sel", "feasts"),
    MonthLord.MONTH_ITSELF to listOf("corpse_work", "funeral_rites", "building", "war", "raising_banners", "a_bride", "moving_house", "building_a_hearth"),
    MonthLord.EARTH_KING to listOf("spectacles", "feasts", "markets", "trade", "drawing_mandalas", "setting_out_offerings", "corpse_work", "digging", "moving_house", "giving_or_taking_a_bride"),
    MonthLord.RETINUE to listOf("spectacles", "feasts", "markets", "trade", "drawing_mandalas", "setting_out_offerings", "corpse_work", "digging", "moving_house", "giving_or_taking_a_bride"),
    MonthLord.SOUND_GOD to listOf("dividing_a_brides_goods", "trade", "giving_or_taking_cattle"),
    MonthLord.EARTH_ARMY to listOf("corpse_work", "a_bride"),
    MonthLord.EARTH_MEDICINE to listOf("moving_a_great_fire", "cremation", "fire_offerings", "corpse_work", "war"),
    MonthLord.TENMA to listOf("corpse_work", "a_bride", "work_on_the_hearth", "slaughtering_livestock", "breaking_ground", "digging", "burying_a_woman", "taking_a_small_child_out_of_the_door"),
    MonthLord.OLD_COUPLE to listOf("carrying_out_a_corpse", "the_corpse_road", "laying_down_a_corpse"),
    MonthLord.DRAGON_LORD to listOf("corpse_work", "a_bride", "moving_or_setting_up_a_hearth", "raising_pillars", "setting_up_a_door", "slaughter_and_cooking_meat"),
    MonthLord.PIG_HEADED_MOUSE_ROAD to listOf("carrying_out_a_corpse", "work_with_rock", "work_with_wood", "building", "moving_house"),
    MonthLord.BLUE_MOUSE_ROAD to listOf("building", "war", "moving_house", "funeral_rites", "digging", "the_corpse_road"),
    MonthLord.DZA_ZOR to listOf("corpse_work", "funeral_rites", "carrying_out_a_corpse"),
    MonthLord.NINE_HEADED_WOLF to listOf("carrying_out_a_corpse", "burial", "funeral_rites"),
    MonthLord.SLAYER to listOf("taking_a_small_child_out_of_the_door", "a_childs_feast", "giving_birth", "burying_a_small_child"),
    MonthLord.EARTH_DOGS to listOf("war", "the_corpse_road"),
    MonthLord.PILING to listOf("corpse_work", "war", "a_bride", "building", "cremation", "digging"),
    MonthLord.ZINPHUNG to listOf("every_work"),
)

// ---------------------------------------------------------------- the month's lords, from Nalbu to the nine black ones (pp. 207–215)
// In the source's order; "corpse" (རོ) is carrying out a corpse and fort work (མཁར་ལས) building forts, as for the date's lords.
// The four queens and the unchanging earth lord bar different works in each season (or at the head and the mouth); the list
// joins them. The five and the nine follow month-entries.md's reading of p. 211.

private val MONTH_AVOID_D: Map<MonthLord, List<String>> = mapOf(
    MonthLord.NALBU to listOf("carrying_out_a_corpse", "taking_a_bride", "bad_work"),
    MonthLord.EARTH_MOUSE_ROAD to listOf("earthworks", "carrying_out_a_corpse"),
    MonthLord.PILING_WOLF to listOf("earthworks", "carrying_out_a_corpse"),
    MonthLord.GREAT_GOD_AND_GNYAN to listOf("digging_a_grave", "earthworks"),
    MonthLord.LEOPARD_TAIL to listOf("carrying_out_a_corpse", "building_forts"),
    MonthLord.LEOPARD_SKIN to listOf("showing_weapons", "carrying_out_a_corpse"),
    MonthLord.GUARD_DOG to listOf("carrying_out_a_corpse"),
    MonthLord.TSANGKUN to listOf("carrying_out_a_corpse"),
    MonthLord.NGAMPA to listOf("carrying_out_a_corpse", "funeral_rites"),
    MonthLord.LAGCHEN to listOf("setting_up_a_mortar_or_hand_mill"),
    MonthLord.FOUR_QUEENS to listOf("taking_a_bride", "war", "feasts", "funeral_rites", "bon_thread_crosses", "moving_house", "building_forts"),
    MonthLord.HEARTH_GOD to listOf("hiding_a_corpse", "hearth_and_hand_mill"),
    MonthLord.UNCHANGING to listOf("building_forts", "pointing_a_ladle", "taking_a_bride", "feasts"),
    MonthLord.FIVE_GODS to listOf(
        "carrying_out_a_corpse", "building_forts", "markets", "funeral_rites", "seeing_off_and_welcoming", "spectacles", "gatherings", "war",
        "robbery", "showing_weapons", "moving_house", "taking_a_bride",
    ),
    MonthLord.NINE_GODS to listOf(
        "carrying_out_a_corpse", "burial", "building_forts", "markets", "funeral_rites", "seeing_off_and_welcoming", "spectacles", "gatherings",
        "war", "every_work",
    ),
    MonthLord.EMPTY_VASE to listOf("most_works"),
    MonthLord.HAL_KHYI to listOf("building_forts", "carrying_out_a_corpse", "funeral_rites"),
    MonthLord.BELLY_CRAWLER to listOf(
        "mandala_rites", "building_forts", "digging", "suppressing", "planting", "carrying_out_a_corpse", "funeral_rites", "war", "taking_a_bride",
        "moving_house", "spectacles",
    ),
    MonthLord.SKY_DOG to listOf(
        "building_forts", "funerals", "carrying_out_a_corpse", "taking_a_bride", "building_graves", "leading_an_army", "war", "robbery",
    ),
    MonthLord.NINE_BLACK to listOf("carrying_out_a_corpse", "war", "taking_a_bride", "medical_treatment", "death_rites", "slaughtering_livestock"),
)

// ---------------------------------------------------------------- the month's lords, from the se demon to the stump-hand (pp. 216–223)
//
//
//

private val MONTH_AVOID_E: Map<MonthLord, List<String>> = mapOf(
    MonthLord.SE_DEMON to listOf("carrying_out_the_dead", "a_bride", "building", "war", "feasts", "funeral_rites", "setting_up_a_gate"),
    MonthLord.GRAVE_TAKER to listOf("setting_up_a_gate", "funeral_rites", "burial"),
    MonthLord.SESHAR to listOf("earthworks", "building"),
    MonthLord.KUKU to listOf("a_corpses_road", "a_bride"),
    MonthLord.BANNER to listOf("digging", "earthworks"),
    MonthLord.HONGPHAN to listOf("cooking_on_the_hearth"),
    MonthLord.SIX_BLACK to listOf("a_bride", "building", "works_for_the_living_and_the_dead"),
    MonthLord.HIDDEN to listOf("a_corpses_road"),
    MonthLord.LHAGO to listOf("a_bride", "carrying_out_the_dead", "funeral_rites", "burial", "great_works"),
    MonthLord.STUMP_HAND to listOf("a_bride", "carrying_out_the_dead", "funeral_rites", "burial", "great_works"),
)

private val MONTH_GOOD_E: Map<MonthLord, List<String>> = mapOf(
    MonthLord.SIX_GODS to listOf("a_corpses_road", "a_bride", "building", "works_for_the_living_and_the_dead"),
)
