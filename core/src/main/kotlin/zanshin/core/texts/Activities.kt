/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.texts

import zanshin.core.texts.Activity.*

/**
 * What a reading's "good for" and "avoid" lists name, so that the same act
 * under different wordings ("weddings", "marriage", "taking a bride") can be
 * compared across annotations. The source wording stays in the reading; only
 * wordings that name the same act share an entry.
 */
enum class Activity(val english: String) {
    EVERYTHING("everything"),
    CELEBRATION("celebrations"),

    WEDDING("weddings"),
    BETROTHAL("betrothal gifts"),
    MARRIAGE_TALKS("marriage talks"),

    JOURNEY("journeys"),
    COMING_HOME("coming home"),
    SEA_TRAVEL("sea travel"),
    MOVING_HOUSE("moving house"),
    BRANCH_FAMILY("setting up a branch family"),
    RETIRING("retiring"),

    BUILDING("building"),
    EXTENDING_HOUSE("extending a house"),
    HOUSE_REPAIRS("house repairs"),
    RAISING_PILLARS("raising pillars"),
    RIDGEPOLE("raising the ridgepole"),
    ROOFING("roofing"),
    BUILDING_GATES("building gates"),
    OPENING_GATES("opening gates"),
    STONE_WALLS("building stone walls"),
    BUILDING_STOREHOUSES("building storehouses"),
    OPENING_STOREHOUSES("opening storehouses"),
    DRIVING_NAILS("driving nails"),
    CLIMBING_HIGH("climbing high"),

    MOVING_EARTH("moving earth"),
    DIGGING("digging"),
    BREAKING_GROUND("breaking ground"),
    GROUND_BREAKING_RITES("ground-breaking rites"),
    LAYING_FOUNDATIONS("laying foundations"),
    FILLING_HOLES("filling holes"),
    WELLS("digging wells"),

    SOWING("sowing"),
    PLANTING("planting"),
    GRAFTING("grafting"),
    HARVESTING("harvesting"),
    CUTTING_GRASS("cutting grass"),
    FELLING_TREES("felling trees"),
    OPENING_RICE_BALES("opening rice bales"),

    OPENING_SHOP("opening a shop or business"),
    BUYING("buying"),
    BUYING_LAND("buying land"),
    ACQUIRING("acquiring things"),
    RECEIVING_MONEY("receiving money"),
    MONEY_TALKS("money talks"),
    LENDING_MONEY("lending or borrowing money"),
    STORING("storing"),
    COLLECTING("collecting"),

    CONTRACTS("contracts"),
    AGREEMENTS("agreements"),
    PROMISES("promises"),
    NEGOTIATIONS("negotiations"),
    CONSULTATIONS("consultations"),
    DISPUTES("disputes"),
    FIXING_DECISIONS("fixing decisions"),
    QUICK_DECISIONS("quick decisions"),

    BEGINNINGS("beginnings"),
    NEW_VENTURES("new ventures"),
    STARTING_SCHOOL("starting school"),
    STARTING_LESSONS("starting lessons"),
    ENTRANCE_EXAMS("entrance exams"),
    TAKING_UP_OFFICE("taking up office"),
    ANNOUNCEMENTS("announcements"),

    TAKING_MEDICINE("taking medicine"),
    STARTING_MEDICINE("starting medicine"),
    ACUPUNCTURE("acupuncture"),
    VISITING_THE_SICK("visiting the sick"),

    FUNERALS("funerals"),
    BURIAL("burial"),
    MEMORIAL_SERVICES("memorial services"),
    MOURNING("mourning"),
    BUILDING_GRAVES("building graves"),

    SHRINE_RITES("shrine rites"),
    PRAYER("prayer"),
    SHRINES_AND_ALTARS("shrines and altars"),
    DEVOTION("devotion"),
    MAKING_WISHES("making wishes"),

    SEWING("sewing"),
    NEW_CLOTHES("new clothes"),
    FIRST_WEARING("first wearing of new clothes"),
    NAMING("naming"),
    HAIRCUTS("haircuts"),

    CLEARING_OUT("clearing out"),
    THROWING_AWAY("throwing things away"),
    PUTTING_IN_ORDER("putting things in order"),
    MAKING_THINGS("making things"),
    UNCLEAN_HOUSE("an unclean house"),
    HELPING_OTHERS("helping others"),

    BLADES("blades"),
    BLOODSHED("bloodshed"),
    HUNTING("hunting"),
    FIRE("fire"),
    HEAVY_EATING("eating and drinking heavily"),
    CARELESS_WORDS("careless words"),
    HASTE("haste"),
    CALM("calm"),
}

/** The times of day the rokuyō name good or bad. They are hours, not acts. */
enum class DayTime { MORNING, NOON, AFTERNOON, EVENING }

object Activities {
    private fun of(vararg activities: Activity): Set<Activity> = activities.toSet()

    /** Every wording of the "good for" and "avoid" lists, by the act it names. */
    val BY_WORDING: Map<String, Set<Activity>> = mapOf(
        "everything" to of(EVERYTHING),
        "celebrations" to of(CELEBRATION),

        "weddings" to of(WEDDING),
        "marriage" to of(WEDDING),
        "taking a bride" to of(WEDDING),
        "bringing in a bride" to of(WEDDING),
        "betrothal gifts" to of(BETROTHAL),
        "marriage talks" to of(MARRIAGE_TALKS),

        "travel" to of(JOURNEY),
        "setting out" to of(JOURNEY),
        "setting out on journeys" to of(JOURNEY),
        "long journeys" to of(JOURNEY),
        "journeys in any direction" to of(JOURNEY),
        "coming home" to of(COMING_HOME),
        "boarding ships" to of(SEA_TRAVEL),
        "sea travel" to of(SEA_TRAVEL),
        "sea voyages" to of(SEA_TRAVEL),
        "moving house" to of(MOVING_HOUSE),
        "setting up a branch family" to of(BRANCH_FAMILY),
        "retiring" to of(RETIRING),

        "building" to of(BUILDING),
        "building a house" to of(BUILDING),
        "any building work" to of(BUILDING),
        "extending a house" to of(EXTENDING_HOUSE),
        "house repairs" to of(HOUSE_REPAIRS),
        "raising pillars" to of(RAISING_PILLARS),
        "raising the ridgepole" to of(RIDGEPOLE),
        "roofing" to of(ROOFING),
        "building gates" to of(BUILDING_GATES),
        "opening gates" to of(OPENING_GATES),
        "building stone walls" to of(STONE_WALLS),
        "building storehouses" to of(BUILDING_STOREHOUSES),
        "opening a shop, gate or storehouse" to of(OPENING_SHOP, OPENING_GATES, OPENING_STOREHOUSES),
        "driving nails" to of(DRIVING_NAILS),
        "climbing high" to of(CLIMBING_HIGH),

        "moving earth" to of(MOVING_EARTH),
        "earthworks" to of(MOVING_EARTH),
        "turning soil" to of(MOVING_EARTH),
        "digging" to of(DIGGING),
        "breaking ground" to of(BREAKING_GROUND),
        "ground-breaking rites" to of(GROUND_BREAKING_RITES),
        "laying foundations" to of(LAYING_FOUNDATIONS),
        "filling holes" to of(FILLING_HOLES),
        "digging wells" to of(WELLS),
        "wells" to of(WELLS),

        "sowing" to of(SOWING),
        "planting" to of(PLANTING),
        "grafting" to of(GRAFTING),
        "harvesting" to of(HARVESTING),
        "cutting grass" to of(CUTTING_GRASS),
        "felling trees" to of(FELLING_TREES),
        "opening rice bales" to of(OPENING_RICE_BALES),

        "opening a shop" to of(OPENING_SHOP),
        "opening a business" to of(OPENING_SHOP),
        "buying" to of(BUYING),
        "buying land" to of(BUYING_LAND),
        "acquiring things" to of(ACQUIRING),
        "receiving money" to of(RECEIVING_MONEY),
        "money talks" to of(MONEY_TALKS),
        "lending or borrowing money" to of(LENDING_MONEY),
        "storing" to of(STORING),
        "collecting" to of(COLLECTING),

        "contracts" to of(CONTRACTS),
        "agreements" to of(AGREEMENTS),
        "promises" to of(PROMISES),
        "negotiations" to of(NEGOTIATIONS),
        "consultations" to of(CONSULTATIONS),
        "disputes" to of(DISPUTES),
        "fixing decisions" to of(FIXING_DECISIONS),
        "quick decisions" to of(QUICK_DECISIONS),

        "beginnings" to of(BEGINNINGS),
        "new beginnings" to of(BEGINNINGS),
        "new ventures" to of(NEW_VENTURES),
        "starting school" to of(STARTING_SCHOOL),
        "starting lessons" to of(STARTING_LESSONS),
        "entrance exams" to of(ENTRANCE_EXAMS),
        "taking up office" to of(TAKING_UP_OFFICE),
        "announcements" to of(ANNOUNCEMENTS),

        "taking medicine" to of(TAKING_MEDICINE),
        "starting medicine" to of(STARTING_MEDICINE),
        "acupuncture" to of(ACUPUNCTURE),
        "visiting the sick" to of(VISITING_THE_SICK),

        "funerals" to of(FUNERALS),
        "funerals (custom)" to of(FUNERALS),
        "burial" to of(BURIAL),
        "interments" to of(BURIAL),
        "memorial services" to of(MEMORIAL_SERVICES),
        "mourning" to of(MOURNING),
        "building graves" to of(BUILDING_GRAVES),

        "shrine rites" to of(SHRINE_RITES),
        "prayer" to of(PRAYER),
        "prayers to gods and buddhas" to of(PRAYER),
        "shrines and altars" to of(SHRINES_AND_ALTARS),
        "devotion" to of(DEVOTION),
        "making wishes" to of(MAKING_WISHES),

        "sewing" to of(SEWING),
        "new clothes" to of(NEW_CLOTHES),
        "first wearing of new clothes" to of(FIRST_WEARING),
        "naming" to of(NAMING),
        "naming a child" to of(NAMING),
        "haircuts" to of(HAIRCUTS),

        "clearing out" to of(CLEARING_OUT),
        "throwing things away" to of(THROWING_AWAY),
        "putting things in order" to of(PUTTING_IN_ORDER),
        "making things" to of(MAKING_THINGS),
        "an unclean house" to of(UNCLEAN_HOUSE),
        "helping others" to of(HELPING_OTHERS),

        "blades" to of(BLADES),
        "bloodshed" to of(BLOODSHED),
        "hunting" to of(HUNTING),
        "fire" to of(FIRE),
        "eating and drinking heavily" to of(HEAVY_EATING),
        "careless words" to of(CARELESS_WORDS),
        "haste" to of(HASTE),
        "calm" to of(CALM),
    )

    /** The rokuyō's hours, by wording. */
    val TIMES: Map<String, Set<DayTime>> = mapOf(
        "the morning" to setOf(DayTime.MORNING),
        "noon" to setOf(DayTime.NOON),
        "around noon" to setOf(DayTime.NOON),
        "the afternoon" to setOf(DayTime.AFTERNOON),
        "morning and evening" to setOf(DayTime.MORNING, DayTime.EVENING),
    )

    /** The activities a list names; times of day are left out. */
    fun of(wordings: List<String>): Set<Activity> = wordings.flatMap { BY_WORDING[it].orEmpty() }.toSet()
}
