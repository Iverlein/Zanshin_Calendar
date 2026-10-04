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
    CALM,

    // The Tibetan activity lists (SPEC §5.10)
    OFFERINGS,
    SACRED_SUPPORTS,
    DIVINATION,
    LEARNING_ASTROLOGY,
    STUDYING_SCRIPTURE,
    LEARNING_ARTS,
    MEDICAL_TREATMENT,
    SURGERY,
    STUDYING_MEDICINE,
    BUYING_HOME,
    MAKING_A_WILL,
    MAKING_WEAPONS,
    HEALTH_AND_WEALTH,
    PACIFYING,
    INCREASING,
    CONTROLLING,
    DESTROYING,

    // The White Beryl's karaṇas and lunar dates (SPEC §5.11)
    LAWSUITS,
    OATHS,
    STATECRAFT,
    WAR,
    KILLING,
    ROBBERY,
    POISON,
    BUYING_LIVESTOCK,
    TRADING,
    GIVING_OUT,
    MOVING_GOODS,
    TAMING_ANIMALS,
    CATTLE_WORK,
    FIELD_WORK,
    TEACHING_DHARMA,
    HEARING_DHARMA,
    DHARMA_PRACTICE,
    VIRTUE,
    ORDINATION,
    PROSTRATIONS,
    MANTRAS,
    AMULETS,
    SERVING_TEACHER,
    BRAHMINS,
    CONSECRATION,
    WASHING_HAIR,
    AVERTING_RITES,
    SUPPRESSING_SRI,
    FIRE_OFFERINGS,
    PROSPERITY_RITES,
    EMPOWERMENT,
    CURSING,
    HEARTH,
    DAMS,
    SERVANTS,
    BATHING,
    BREWING,
    PARENTS,
    GIVING_A_CHILD,
    BLOODLETTING,
    MAKING_MEDICINE,
    LASTING_WORK,
    MOVING_WORK,
    FRIENDSHIP,
    GOOD_FORTUNE,

    // The White Beryl's weekdays
    MEETING_THE_GREAT,
    PETITIONS,
    RECONCILIATION,
    BANNERS,
    HORSE_RACING,
    SPECTACLES,
    FEASTS,
    GAMES,
    SADDLING,
    DAIRY,
    RAISING_DOGS,
    SMOKE_OFFERINGS,
    RECITATION,
    VOWS,
    STUDY,
    PERFUME,
    ORNAMENTS,
    WATER_WORK,
    LOVE;

    val english: String get() = gloss(this)
}

/**
 * The families the activities are drawn by, one glyph each (SPEC §10.4): the
 * day's summary line shows which families its annotations name good and to
 * avoid. Declared in the order that line shows them.
 */
enum class ActivityFamily {
    EVERYTHING,
    CELEBRATION,
    WEDDING,
    JOURNEY,
    SEA,
    MOVING_HOUSE,
    BUILDING,
    EARTH,
    WELL,
    FIELD,
    TRADE,
    AGREEMENT,
    BEGINNING,
    LEARNING,
    MEDICINE,
    FUNERAL,
    SHRINE,
    PRAYER,
    SACRED,
    RITE,
    CLOTHES,
    HAIRCUT,
    NAME,
    HOUSEHOLD,
    BLADE,
    FIRE,
    CONDUCT;

    val english: String get() = gloss(this)
}

/** The family an activity is drawn with. Every activity has one: the `when` is exhaustive. */
val Activity.family: ActivityFamily
    get() = when (this) {
        EVERYTHING -> ActivityFamily.EVERYTHING
        CELEBRATION -> ActivityFamily.CELEBRATION
        WEDDING, BETROTHAL, MARRIAGE_TALKS -> ActivityFamily.WEDDING
        JOURNEY, COMING_HOME -> ActivityFamily.JOURNEY
        SEA_TRAVEL -> ActivityFamily.SEA
        MOVING_HOUSE, BRANCH_FAMILY, RETIRING, BUYING_HOME -> ActivityFamily.MOVING_HOUSE
        BUILDING, EXTENDING_HOUSE, HOUSE_REPAIRS, RAISING_PILLARS, RIDGEPOLE, ROOFING, BUILDING_GATES, OPENING_GATES,
        STONE_WALLS, BUILDING_STOREHOUSES, OPENING_STOREHOUSES, DRIVING_NAILS, CLIMBING_HIGH, MAKING_THINGS -> ActivityFamily.BUILDING
        MOVING_EARTH, DIGGING, BREAKING_GROUND, GROUND_BREAKING_RITES, LAYING_FOUNDATIONS, FILLING_HOLES -> ActivityFamily.EARTH
        WELLS -> ActivityFamily.WELL
        SOWING, PLANTING, GRAFTING, HARVESTING, CUTTING_GRASS, FELLING_TREES, OPENING_RICE_BALES -> ActivityFamily.FIELD
        OPENING_SHOP, BUYING, BUYING_LAND, ACQUIRING, RECEIVING_MONEY, MONEY_TALKS, LENDING_MONEY, STORING, COLLECTING -> ActivityFamily.TRADE
        CONTRACTS, AGREEMENTS, PROMISES, NEGOTIATIONS, CONSULTATIONS, DISPUTES, FIXING_DECISIONS, QUICK_DECISIONS, MAKING_A_WILL -> ActivityFamily.AGREEMENT
        BEGINNINGS, NEW_VENTURES, TAKING_UP_OFFICE, ANNOUNCEMENTS -> ActivityFamily.BEGINNING
        STARTING_SCHOOL, STARTING_LESSONS, ENTRANCE_EXAMS, STUDYING_SCRIPTURE, LEARNING_ARTS, LEARNING_ASTROLOGY,
        DIVINATION, STUDYING_MEDICINE -> ActivityFamily.LEARNING
        TAKING_MEDICINE, STARTING_MEDICINE, ACUPUNCTURE, VISITING_THE_SICK, MEDICAL_TREATMENT, SURGERY -> ActivityFamily.MEDICINE
        FUNERALS, BURIAL, MEMORIAL_SERVICES, MOURNING, BUILDING_GRAVES -> ActivityFamily.FUNERAL
        SHRINE_RITES, SHRINES_AND_ALTARS -> ActivityFamily.SHRINE
        PRAYER, DEVOTION, MAKING_WISHES, OFFERINGS -> ActivityFamily.PRAYER
        SACRED_SUPPORTS -> ActivityFamily.SACRED
        PACIFYING, INCREASING, CONTROLLING, DESTROYING, HEALTH_AND_WEALTH -> ActivityFamily.RITE
        SEWING, NEW_CLOTHES, FIRST_WEARING -> ActivityFamily.CLOTHES
        HAIRCUTS -> ActivityFamily.HAIRCUT
        NAMING -> ActivityFamily.NAME
        CLEARING_OUT, THROWING_AWAY, PUTTING_IN_ORDER, UNCLEAN_HOUSE -> ActivityFamily.HOUSEHOLD
        BLADES, BLOODSHED, HUNTING, MAKING_WEAPONS -> ActivityFamily.BLADE
        FIRE -> ActivityFamily.FIRE
        CALM, HASTE, CARELESS_WORDS, HEAVY_EATING, HELPING_OTHERS -> ActivityFamily.CONDUCT
        LAWSUITS, OATHS, STATECRAFT -> ActivityFamily.AGREEMENT
        WAR, KILLING, ROBBERY, POISON -> ActivityFamily.BLADE
        BUYING_LIVESTOCK, TRADING, GIVING_OUT, MOVING_GOODS -> ActivityFamily.TRADE
        TAMING_ANIMALS, CATTLE_WORK, FIELD_WORK -> ActivityFamily.FIELD
        TEACHING_DHARMA, HEARING_DHARMA -> ActivityFamily.LEARNING
        DHARMA_PRACTICE, VIRTUE, ORDINATION, PROSTRATIONS, MANTRAS, AMULETS, SERVING_TEACHER, BRAHMINS -> ActivityFamily.PRAYER
        CONSECRATION -> ActivityFamily.SACRED
        WASHING_HAIR -> ActivityFamily.HAIRCUT
        AVERTING_RITES, SUPPRESSING_SRI, FIRE_OFFERINGS, PROSPERITY_RITES, EMPOWERMENT, CURSING -> ActivityFamily.RITE
        HEARTH -> ActivityFamily.BUILDING
        DAMS -> ActivityFamily.EARTH
        SERVANTS, BATHING, BREWING, PARENTS, GIVING_A_CHILD -> ActivityFamily.HOUSEHOLD
        BLOODLETTING, MAKING_MEDICINE -> ActivityFamily.MEDICINE
        LASTING_WORK, MOVING_WORK, FRIENDSHIP -> ActivityFamily.CONDUCT
        GOOD_FORTUNE -> ActivityFamily.CELEBRATION
        MEETING_THE_GREAT, PETITIONS, RECONCILIATION -> ActivityFamily.AGREEMENT
        BANNERS -> ActivityFamily.SACRED
        HORSE_RACING, SPECTACLES, FEASTS, GAMES -> ActivityFamily.CELEBRATION
        SADDLING, DAIRY, RAISING_DOGS -> ActivityFamily.FIELD
        SMOKE_OFFERINGS, RECITATION, VOWS -> ActivityFamily.PRAYER
        STUDY -> ActivityFamily.LEARNING
        PERFUME -> ActivityFamily.HOUSEHOLD
        ORNAMENTS -> ActivityFamily.CLOTHES
        WATER_WORK -> ActivityFamily.WELL
        LOVE -> ActivityFamily.WEDDING
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

        "offerings_to_deities" to of(OFFERINGS),
        "taking_a_new_home" to of(MOVING_HOUSE),
        "setting_up_supports" to of(SACRED_SUPPORTS),
        "installing_a_deity" to of(SACRED_SUPPORTS),
        "astrology_and_divination" to of(DIVINATION),
        "learning_astrology" to of(LEARNING_ASTROLOGY),
        "studying_scripture" to of(STUDYING_SCRIPTURE),
        "learning_music_or_dance" to of(LEARNING_ARTS),
        "planting_and_sowing" to of(PLANTING, SOWING),
        "laying_the_foundation_of_a_home" to of(LAYING_FOUNDATIONS),
        "medical_treatment" to of(MEDICAL_TREATMENT),
        "surgical_treatment" to of(SURGERY),
        "studying_medicine" to of(STUDYING_MEDICINE),
        "buying_a_home" to of(BUYING_HOME),
        "making_a_will" to of(MAKING_A_WILL),
        "making_weapons" to of(MAKING_WEAPONS),
        "health_and_wealth" to of(HEALTH_AND_WEALTH),
        "pacifying_activity" to of(PACIFYING),
        "increasing_activity" to of(INCREASING),
        "controlling_activity" to of(CONTROLLING),
        "destructive_activity" to of(DESTROYING),

        "lawsuits" to of(LAWSUITS),
        "pacifying_rites" to of(PACIFYING),
        "increasing_rites" to of(INCREASING),
        "power_rites" to of(CONTROLLING),
        "fierce_rites" to of(DESTROYING),
        "buying_livestock" to of(BUYING_LIVESTOCK),
        "teaching_dharma" to of(TEACHING_DHARMA),
        "hearing_dharma" to of(HEARING_DHARMA),
        "dharma_practice" to of(DHARMA_PRACTICE),
        "washing_the_hair" to of(WASHING_HAIR),
        "funeral_rites" to of(FUNERALS),
        "giving_anything_out" to of(GIVING_OUT),
        "worship_of_deities" to of(OFFERINGS),
        "averting_rites" to of(AVERTING_RITES),
        "suppressing_sri" to of(SUPPRESSING_SRI),
        "gtad_and_sri_rites" to of(SUPPRESSING_SRI),
        "ordination" to of(ORDINATION),
        "consecration" to of(CONSECRATION),
        "enthronement" to of(TAKING_UP_OFFICE),
        "building_a_hearth" to of(HEARTH),
        "digging_ponds_and_wells" to of(DIGGING, WELLS),
        "digging_ponds_canals_and_wells" to of(DIGGING, WELLS),
        "digging_ponds_and_canals" to of(DIGGING),
        "building_dams" to of(DAMS),
        "building_flood_dikes" to of(DAMS),
        "fire_offerings" to of(FIRE_OFFERINGS),
        "retinue_and_marriage" to of(SERVANTS, WEDDING),
        "trade" to of(TRADING),
        "taking_servants" to of(SERVANTS),
        "prostrations" to of(PROSTRATIONS),
        "bloodletting_and_moxibustion" to of(BLOODLETTING),
        "bloodletting" to of(BLOODLETTING),
        "leading_an_army" to of(WAR),
        "war_not_east" to of(WAR),
        "war_not_south" to of(WAR),
        "war_not_south_or_west" to of(WAR),
        "war_south_or_west" to of(WAR),
        "attacking_enemies" to of(WAR),
        "subduing_enemies" to of(WAR),
        "judging_disputes" to of(DISPUTES),
        "quarrels" to of(DISPUTES),
        "oaths" to of(OATHS),
        "making_images" to of(SACRED_SUPPORTS),
        "building_temples_and_stupas" to of(SACRED_SUPPORTS),
        "building_temples" to of(SACRED_SUPPORTS),
        "bathing" to of(BATHING),
        "tying_on_amulets" to of(AMULETS),
        "breaking_in_livestock" to of(TAMING_ANIMALS),
        "prosperity_rites" to of(PROSPERITY_RITES),
        "empowerment" to of(EMPOWERMENT),
        "gaining_wealth" to of(HEALTH_AND_WEALTH),
        "giving_a_child_away" to of(GIVING_A_CHILD),
        "paying_debts" to of(LENDING_MONEY),
        "buying_goods" to of(BUYING),
        "buying_servants_and_goods" to of(SERVANTS, BUYING),
        "buying_fields" to of(BUYING_LAND),
        "robbery" to of(ROBBERY),
        "hanging_doors" to of(BUILDING_GATES),
        "virtuous_work" to of(VIRTUE),
        "learning_writing_astrology_and_crafts" to of(LEARNING_ASTROLOGY, LEARNING_ARTS),
        "work_with_rock" to of(BUILDING),
        "council" to of(CONSULTATIONS),
        "brewing_beer" to of(BREWING),
        "field_work" to of(FIELD_WORK),
        "virtue" to of(VIRTUE),
        "lasting_work" to of(LASTING_WORK),
        "work_on_the_move" to of(MOVING_WORK),
        "preparing_medicine" to of(MAKING_MEDICINE),
        "reciting_mantras" to of(MANTRAS),
        "mantras" to of(MANTRAS),
        "serving_the_teacher" to of(SERVING_TEACHER),
        "the_brahmins_affairs" to of(BRAHMINS),
        "the_parents_affairs" to of(PARENTS),
        "honouring_and_service" to of(SERVING_TEACHER),
        "work_with_cattle" to of(CATTLE_WORK),
        "gathering_merit" to of(VIRTUE),
        "unsteady_and_moving_work" to of(MOVING_WORK),
        "moving_goods" to of(MOVING_GOODS),
        "gathering_power" to of(CONTROLLING),
        "prayers_for_good_fortune" to of(PRAYER),
        "making_friends" to of(FRIENDSHIP),
        "joyful_occasions" to of(CELEBRATION),
        "buying_and_trading" to of(BUYING, TRADING),
        "killing_others" to of(KILLING),
        "preparing_poison" to of(POISON),
        "harsh_work" to of(DESTROYING),
        "cursing" to of(CURSING),
        "worship_of_brahmins" to of(BRAHMINS),
        "affairs_of_state" to of(STATECRAFT),
        "weapon_tormas" to of(DESTROYING),
        "killing_and_robbing" to of(KILLING, ROBBERY),
        "works_of_good_fortune" to of(GOOD_FORTUNE),
        "killing_people" to of(KILLING),
        "robbing" to of(ROBBERY),
        "wished_for_increase" to of(INCREASING),

        "founding_a_palace" to of(BUILDING),
        "meeting_great_people" to of(MEETING_THE_GREAT),
        "planting_trees" to of(PLANTING),
        "planting_flowers" to of(PLANTING),
        "raising_banners" to of(BANNERS),
        "averting_thieves" to of(AVERTING_RITES),
        "horse_racing" to of(HORSE_RACING),
        "breaking_in_horses_and_livestock" to of(TAMING_ANIMALS),
        "breaking_in_oxen" to of(TAMING_ANIMALS),
        "breaking_in_horses" to of(TAMING_ANIMALS),
        "spectacles" to of(SPECTACLES),
        "magic_shows" to of(SPECTACLES),
        "saddling" to of(SADDLING),
        "crafts_in_gold_wood_leather_and_bone" to of(MAKING_THINGS),
        "work_with_fire" to of(FIRE),
        "smoke_offerings" to of(SMOKE_OFFERINGS),
        "reciting_scripture" to of(RECITATION),
        "presenting_petitions" to of(PETITIONS),
        "study" to of(STUDY),
        "going_into_the_forest" to of(JOURNEY),
        "feasts" to of(FEASTS),
        "games" to of(GAMES),
        "making_perfumes" to of(PERFUME),
        "auspicious_work" to of(GOOD_FORTUNE),
        "carrying_out_the_dead" to of(FUNERALS),
        "harsh_words" to of(CARELESS_WORDS),
        "temple_foundations" to of(SACRED_SUPPORTS, LAYING_FOUNDATIONS),
        "cutting_hair_and_nails" to of(HAIRCUTS),
        "sewing_tents" to of(SEWING),
        "building_towns" to of(BUILDING),
        "sending_out_wealth" to of(GIVING_OUT),
        "sending_out_livestock" to of(GIVING_OUT),
        "reconciliation" to of(RECONCILIATION),
        "lawsuits_and_disputes" to of(LAWSUITS, DISPUTES),
        "work_with_water" to of(WATER_WORK),
        "offerings_to_nagas" to of(OFFERINGS),
        "taking_elixirs" to of(TAKING_MEDICINE),
        "putting_on_ornaments" to of(ORNAMENTS),
        "matchmaking" to of(MARRIAGE_TALKS),
        "dairy_work" to of(DAIRY),
        "grain_work" to of(FIELD_WORK),
        "offerings_to_the_lama" to of(OFFERINGS),
        "receiving_wealth" to of(RECEIVING_MONEY),
        "war" to of(WAR),
        "martial_skills" to of(WAR),
        "appointing_generals" to of(TAKING_UP_OFFICE),
        "fighting_enemies" to of(WAR),
        "hurling_zor" to of(DESTROYING),
        "hurling_mdos_torma_and_zor" to of(DESTROYING),
        "directing_magic" to of(DESTROYING),
        "black_rites" to of(DESTROYING),
        "collecting_debts" to of(LENDING_MONEY),
        "taking_new_lands" to of(WAR),
        "destroying_forts" to of(WAR),
        "raising_dogs" to of(RAISING_DOGS),
        "moxibustion" to of(BLOODLETTING),
        "work_with_gold_coral_and_swords" to of(MAKING_THINGS, MAKING_WEAPONS),
        "divination" to of(DIVINATION),
        "astrology" to of(DIVINATION),
        "crafts" to of(MAKING_THINGS),
        "woodwork" to of(MAKING_THINGS),
        "ironwork" to of(MAKING_THINGS),
        "work_with_jewels" to of(MAKING_THINGS),
        "metal_and_jewel_work" to of(MAKING_THINGS),
        "gatherings" to of(CELEBRATION),
        "poetry_and_grammar" to of(STUDY),
        "writing_treatises" to of(STUDY),
        "learning_the_sciences" to of(STUDY),
        "learning_writing_and_astrology" to of(STUDY, LEARNING_ASTROLOGY),
        "studying_the_dharma" to of(STUDYING_SCRIPTURE),
        "teaching_and_hearing_the_dharma" to of(TEACHING_DHARMA, HEARING_DHARMA),
        "mandala_rites" to of(DHARMA_PRACTICE),
        "taking_vows" to of(VOWS),
        "restoring_vows" to of(VOWS),
        "seeking_connections" to of(FRIENDSHIP),
        "seeking_friends" to of(FRIENDSHIP),
        "taking_a_retinue" to of(SERVANTS),
        "presenting_offerings" to of(OFFERINGS),
        "farming" to of(FIELD_WORK),
        "building_new_houses" to of(BUILDING),
        "consorting_with_women" to of(LOVE),
        "feuds" to of(DISPUTES),
        "long_life_and_prosperity_rites" to of(PROSPERITY_RITES),
        "acquiring_goods_and_livestock" to of(ACQUIRING, BUYING_LIVESTOCK),
        "trading_land_and_houses" to of(BUYING_LAND),
        "serving_the_king" to of(STATECRAFT),
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
