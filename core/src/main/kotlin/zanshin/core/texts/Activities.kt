/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.texts

import zanshin.core.texts.Activity.*

/**
 * What a reading's "good for" and "avoid" lists name, so that the same act
 * under different wordings ("weddings", "marriage", "taking a bride") can be
 * compared across annotations. The source wording stays in the catalog, one
 * `wording.<key>` entry each; only wordings that name the same act share an
 * entry here.
 */
enum class Activity {
    EVERYTHING,
    CELEBRATION,

    WEDDING,
    BETROTHAL,
    MARRIAGE_TALKS,

    JOURNEY,
    COMING_HOME,
    SEA_TRAVEL,
    MOVING_HOUSE,
    BRANCH_FAMILY,
    RETIRING,

    BUILDING,
    EXTENDING_HOUSE,
    HOUSE_REPAIRS,
    RAISING_PILLARS,
    RIDGEPOLE,
    ROOFING,
    BUILDING_GATES,
    OPENING_GATES,
    STONE_WALLS,
    BUILDING_STOREHOUSES,
    OPENING_STOREHOUSES,
    DRIVING_NAILS,
    CLIMBING_HIGH,

    MOVING_EARTH,
    DIGGING,
    BREAKING_GROUND,
    GROUND_BREAKING_RITES,
    LAYING_FOUNDATIONS,
    FILLING_HOLES,
    WELLS,

    SOWING,
    PLANTING,
    GRAFTING,
    HARVESTING,
    CUTTING_GRASS,
    FELLING_TREES,
    OPENING_RICE_BALES,

    OPENING_SHOP,
    BUYING,
    BUYING_LAND,
    ACQUIRING,
    RECEIVING_MONEY,
    MONEY_TALKS,
    LENDING_MONEY,
    STORING,
    COLLECTING,

    CONTRACTS,
    AGREEMENTS,
    PROMISES,
    NEGOTIATIONS,
    CONSULTATIONS,
    DISPUTES,
    FIXING_DECISIONS,
    QUICK_DECISIONS,

    BEGINNINGS,
    NEW_VENTURES,
    STARTING_SCHOOL,
    STARTING_LESSONS,
    ENTRANCE_EXAMS,
    TAKING_UP_OFFICE,
    ANNOUNCEMENTS,

    TAKING_MEDICINE,
    STARTING_MEDICINE,
    ACUPUNCTURE,
    VISITING_THE_SICK,

    FUNERALS,
    BURIAL,
    MEMORIAL_SERVICES,
    MOURNING,
    BUILDING_GRAVES,

    SHRINE_RITES,
    PRAYER,
    SHRINES_AND_ALTARS,
    DEVOTION,
    MAKING_WISHES,

    SEWING,
    NEW_CLOTHES,
    FIRST_WEARING,
    NAMING,
    HAIRCUTS,

    CLEARING_OUT,
    THROWING_AWAY,
    PUTTING_IN_ORDER,
    MAKING_THINGS,
    UNCLEAN_HOUSE,
    HELPING_OTHERS,

    BLADES,
    BLOODSHED,
    HUNTING,
    FIRE,
    HEAVY_EATING,
    CARELESS_WORDS,
    HASTE,
    CALM;

    val english: String get() = gloss(this)
}

/** The times of day the rokuyō name good or bad. They are hours, not acts. */
enum class DayTime { MORNING, NOON, AFTERNOON, EVENING }

object Activities {
    private fun of(vararg activities: Activity): Set<Activity> = activities.toSet()

    /** Every wording of the "good for" and "avoid" lists, by its catalog key, with the act it names. */
    val BY_WORDING: Map<String, Set<Activity>> = mapOf(
        "everything" to of(EVERYTHING),
        "celebrations" to of(CELEBRATION),

        "weddings" to of(WEDDING),
        "marriage" to of(WEDDING),
        "taking_a_bride" to of(WEDDING),
        "bringing_in_a_bride" to of(WEDDING),
        "betrothal_gifts" to of(BETROTHAL),
        "marriage_talks" to of(MARRIAGE_TALKS),

        "travel" to of(JOURNEY),
        "setting_out" to of(JOURNEY),
        "setting_out_on_journeys" to of(JOURNEY),
        "long_journeys" to of(JOURNEY),
        "journeys_in_any_direction" to of(JOURNEY),
        "coming_home" to of(COMING_HOME),
        "boarding_ships" to of(SEA_TRAVEL),
        "sea_travel" to of(SEA_TRAVEL),
        "sea_voyages" to of(SEA_TRAVEL),
        "moving_house" to of(MOVING_HOUSE),
        "setting_up_a_branch_family" to of(BRANCH_FAMILY),
        "retiring" to of(RETIRING),

        "building" to of(BUILDING),
        "building_a_house" to of(BUILDING),
        "any_building_work" to of(BUILDING),
        "extending_a_house" to of(EXTENDING_HOUSE),
        "house_repairs" to of(HOUSE_REPAIRS),
        "raising_pillars" to of(RAISING_PILLARS),
        "raising_the_ridgepole" to of(RIDGEPOLE),
        "roofing" to of(ROOFING),
        "building_gates" to of(BUILDING_GATES),
        "opening_gates" to of(OPENING_GATES),
        "building_stone_walls" to of(STONE_WALLS),
        "building_storehouses" to of(BUILDING_STOREHOUSES),
        "opening_a_shop_gate_or_storehouse" to of(OPENING_SHOP, OPENING_GATES, OPENING_STOREHOUSES),
        "driving_nails" to of(DRIVING_NAILS),
        "climbing_high" to of(CLIMBING_HIGH),

        "moving_earth" to of(MOVING_EARTH),
        "earthworks" to of(MOVING_EARTH),
        "turning_soil" to of(MOVING_EARTH),
        "digging" to of(DIGGING),
        "breaking_ground" to of(BREAKING_GROUND),
        "ground_breaking_rites" to of(GROUND_BREAKING_RITES),
        "laying_foundations" to of(LAYING_FOUNDATIONS),
        "filling_holes" to of(FILLING_HOLES),
        "digging_wells" to of(WELLS),
        "wells" to of(WELLS),

        "sowing" to of(SOWING),
        "planting" to of(PLANTING),
        "grafting" to of(GRAFTING),
        "harvesting" to of(HARVESTING),
        "cutting_grass" to of(CUTTING_GRASS),
        "felling_trees" to of(FELLING_TREES),
        "opening_rice_bales" to of(OPENING_RICE_BALES),

        "opening_a_shop" to of(OPENING_SHOP),
        "opening_a_business" to of(OPENING_SHOP),
        "buying" to of(BUYING),
        "buying_land" to of(BUYING_LAND),
        "acquiring_things" to of(ACQUIRING),
        "receiving_money" to of(RECEIVING_MONEY),
        "money_talks" to of(MONEY_TALKS),
        "lending_or_borrowing_money" to of(LENDING_MONEY),
        "storing" to of(STORING),
        "collecting" to of(COLLECTING),

        "contracts" to of(CONTRACTS),
        "agreements" to of(AGREEMENTS),
        "promises" to of(PROMISES),
        "negotiations" to of(NEGOTIATIONS),
        "consultations" to of(CONSULTATIONS),
        "disputes" to of(DISPUTES),
        "fixing_decisions" to of(FIXING_DECISIONS),
        "quick_decisions" to of(QUICK_DECISIONS),

        "beginnings" to of(BEGINNINGS),
        "new_beginnings" to of(BEGINNINGS),
        "new_ventures" to of(NEW_VENTURES),
        "starting_school" to of(STARTING_SCHOOL),
        "starting_lessons" to of(STARTING_LESSONS),
        "entrance_exams" to of(ENTRANCE_EXAMS),
        "taking_up_office" to of(TAKING_UP_OFFICE),
        "announcements" to of(ANNOUNCEMENTS),

        "taking_medicine" to of(TAKING_MEDICINE),
        "starting_medicine" to of(STARTING_MEDICINE),
        "acupuncture" to of(ACUPUNCTURE),
        "visiting_the_sick" to of(VISITING_THE_SICK),

        "funerals" to of(FUNERALS),
        "funerals_custom" to of(FUNERALS),
        "burial" to of(BURIAL),
        "interments" to of(BURIAL),
        "memorial_services" to of(MEMORIAL_SERVICES),
        "mourning" to of(MOURNING),
        "building_graves" to of(BUILDING_GRAVES),

        "shrine_rites" to of(SHRINE_RITES),
        "prayer" to of(PRAYER),
        "prayers_to_gods_and_buddhas" to of(PRAYER),
        "shrines_and_altars" to of(SHRINES_AND_ALTARS),
        "devotion" to of(DEVOTION),
        "making_wishes" to of(MAKING_WISHES),

        "sewing" to of(SEWING),
        "new_clothes" to of(NEW_CLOTHES),
        "first_wearing_of_new_clothes" to of(FIRST_WEARING),
        "naming" to of(NAMING),
        "naming_a_child" to of(NAMING),
        "haircuts" to of(HAIRCUTS),

        "clearing_out" to of(CLEARING_OUT),
        "throwing_things_away" to of(THROWING_AWAY),
        "putting_things_in_order" to of(PUTTING_IN_ORDER),
        "making_things" to of(MAKING_THINGS),
        "an_unclean_house" to of(UNCLEAN_HOUSE),
        "helping_others" to of(HELPING_OTHERS),

        "blades" to of(BLADES),
        "bloodshed" to of(BLOODSHED),
        "hunting" to of(HUNTING),
        "fire" to of(FIRE),
        "eating_and_drinking_heavily" to of(HEAVY_EATING),
        "careless_words" to of(CARELESS_WORDS),
        "haste" to of(HASTE),
        "calm" to of(CALM),
    )

    /** The rokuyō's hours, by wording key. */
    val TIMES: Map<String, Set<DayTime>> = mapOf(
        "the_morning" to setOf(DayTime.MORNING),
        "noon" to setOf(DayTime.NOON),
        "around_noon" to setOf(DayTime.NOON),
        "the_afternoon" to setOf(DayTime.AFTERNOON),
        "morning_and_evening" to setOf(DayTime.MORNING, DayTime.EVENING),
    )

    /** The activities a list of wording keys names; times of day are left out. */
    fun of(wordingKeys: List<String>): Set<Activity> = wordingKeys.flatMap { BY_WORDING[it].orEmpty() }.toSet()
}
