/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.texts

import zanshin.core.tibetan.Mansion
import zanshin.core.tibetan.Mansion.*

/**
 * What the White Beryl's verse on each mansion names good and to avoid (vol. 2,
 * pp. 313–328, read on the scans in docs/sources/mansion-verses.md), as catalog
 * wording keys in the verse's order (SPEC §5.10). Left out: words the reading
 * leaves in doubt, what the verse calls middling or acceptable, and omens that
 * are no act (a birth, a death). Abhijit's verse is not here: the Phugpa
 * calendar does not count it among the day's mansions.
 */
internal object MansionVerses {
    val LISTS: Map<Mansion, Pair<List<String>, List<String>>> = mapOf(
        ASHVINI to Pair(
            listOf(
                "teaching_dharma", "ordination", "giving_vows", "prosperity_rites", "virtuous_acts", "new_clothes",
                "trade", "suppressing_sri", "war", "carrying_out_the_dead", "disputes", "building_a_hearth",
                "raising_banners", "funeral_rites", "moving_a_grave", "cutting_hair_and_nails", "washing_the_hair",
                "digging_ponds_canals_and_wells", "lawsuits", "bon_rites", "taming_livestock", "reconciliation",
                "making_offerings", "paying_back", "keeping_dogs", "setting_out", "subduing_enemies",
                "driving_out_thieves", "averting_rites", "ending_mourning", "rites_against_enemies", "stopping_rumours",
                "four_activities_but_special_cases", "ritual_services", "spectacles", "naming"
            ),
            listOf(
                "breaking_ground", "great_gatherings", "putting_on_ornaments", "preparing_medicine",
                "works_for_the_living", "marriage_alliances", "building", "bloodletting_and_moxibustion",
                "summoning_life", "dowries", "laying_foundations", "taking_a_bride", "sending_away_a_son_or_sister"
            ),
        ),
        BHARANI to Pair(
            listOf(
                "taking_attendants", "drawing_the_effigy", "suppressing_enemies", "washing_the_hair", "hurling_zor",
                "expelling_demons", "acquiring_goats", "planting_gardens", "selling_land", "suppressing_sri",
                "reciting_mantras", "funerals_of_the_low_born", "power_and_fierce_rites",
                "supporting_widows_and_widowers", "hunting", "setting_up_or_removing_a_gtad", "cremation",
                "empowerment", "naming"
            ),
            listOf(
                "offerings_to_deities", "building", "sowing", "temples", "making_images", "enthronement",
                "honouring_a_spiritual_friend", "new_clothes", "astrology_and_divination", "building_storehouses",
                "paying_back", "burial", "averting_rites", "judging_disputes", "leading_an_army", "paying_in_goats",
                "trade", "marriage_alliances", "setting_out", "pacifying_and_increasing_rites", "consecration",
                "prosperity_rites", "planting_trees", "preparing_medicine", "bloodletting_and_moxibustion",
                "fire_offerings", "raising_banners", "lawsuits", "rites_for_life_and_wealth", "white_work",
                "giving_or_taking_a_bride"
            ),
        ),
        KRITTIKA to Pair(
            listOf(
                "studying_the_dharma", "restoring_temples_and_stupas", "consecration", "prosperity_rites",
                "investiture", "fire_offerings", "giving_offerings", "preparing_medicine", "astrology",
                "putting_on_ornaments", "joyful_occasions", "enthronement", "building_a_hearth", "taking_things_in",
                "pitching_tents", "meeting_relatives", "horse_racing", "saddling", "spectacles", "washing_the_hair",
                "seizing_prisoners", "work_with_jewels", "consorting_with_women", "building_dams", "white_work"
            ),
            listOf(
                "building", "paying_out", "setting_out", "funerals", "raising_banners", "opening_storehouses",
                "digging_ponds_and_canals", "planting_fields", "opening_a_cellar", "new_clothes", "war_and_raids",
                "fierce_rites", "gifts", "funeral_rites", "funeral_feasts", "suppressing_sri", "taking_a_bride",
                "giving_a_child_away", "planting_flowers_and_trees"
            ),
        ),
        ROHINI to Pair(
            listOf(
                "taking_land_a_fort_or_a_dwelling", "enthronement", "ordination", "teaching", "consecration", "study",
                "offerings_to_deities", "protecting_life_and_prosperity", "preparing_medicine",
                "taking_in_wealth_and_livestock", "councils", "building_forts", "buying_and_trading",
                "bloodletting_and_moxibustion", "attacking_enemies", "sowing", "breaking_in_horses", "saddling",
                "horse_racing", "claiming_debts", "building_a_pleasure_garden", "pacifying_and_increasing_rites",
                "new_clothes", "washing_the_hair", "building_storehouses", "lawsuits_in_the_east", "breaking_ground",
                "sewing_and_pitching_tents"
            ),
            listOf(
                "setting_out", "feasts", "paying_out_livestock_and_goods", "war_and_raids", "moving_house", "gifts",
                "burying_bones", "sharing_out_prosperity", "marriage_alliances", "averting_rites",
                "cutting_hair_and_nails", "sending_away_a_son_or_sister", "funeral_rites", "giving_and_taking_a_bride"
            ),
        ),
        MRIGASHIRAS to Pair(
            listOf(
                "teaching_dharma", "ordination", "consecration", "marriage_alliances", "a_new_house",
                "setting_up_a_door", "offerings_to_deities", "prosperity_rites", "dharma_for_the_living",
                "giving_ornaments", "carpentry", "markets", "field_work", "works_for_the_living", "enthronement",
                "preparing_medicine", "counsel", "laying_foundations", "building", "temples", "making_images",
                "bathing", "war_and_raids", "subduing_enemies", "astrology_and_divination", "fierce_and_harsh_work",
                "building_a_hearth", "gatherings", "raising_victory_banners", "taking_wealth", "fierce_rites",
                "subduing_demons", "averting_rites", "suppressing_sri", "breaking_ground", "cutting_hair_and_nails",
                "horses_donkeys_and_mules", "feasts", "trade", "naming", "spectacles", "taming_livestock", "games",
                "gaining_what_one_wishes", "fierce_and_increasing_rites", "virtuous_acts", "planting_flowers_and_trees"
            ),
            listOf(
                "sending_away_people_or_wealth", "setting_out", "funerals", "funeral_rites", "burial", "quarrels",
                "gentle_work", "judging_disputes", "moving_house", "gifts", "new_clothes", "sending_away_a_bride"
            ),
        ),
        ARDRA to Pair(
            listOf(
                "prosperity_rites", "crafts_and_writing", "judging_disputes", "ordination", "consecration",
                "virtuous_acts", "giving_ornaments", "washing_the_hair", "sowing", "measuring_out_and_taking_in",
                "war_and_raids", "fierce_rites", "killing_and_cutting", "fights_and_disputes",
                "sri_rites_against_enemies", "summoning_life", "bon_rites", "suppressing_sri",
                "astrology_and_divination", "averting_rites", "repairing_ponds_and_canals", "petitioning_the_great",
                "planting_flowers_and_trees"
            ),
            listOf(
                "offerings_to_deities", "funeral_rites", "marriage_alliances", "measuring_livestock", "lawsuits",
                "gifts", "new_clothes", "setting_out", "enthronement", "reconciliation", "temples", "making_images",
                "raising_banners", "bloodletting_and_moxibustion", "sewing_tents_and_felt", "building_a_hearth",
                "building_dams", "pacifying_increasing_power_and_auspicious_rites", "fire_offerings", "taking_a_bride"
            ),
        ),
        PUNARVASU to Pair(
            listOf(
                "ordination", "consecration", "teaching", "empowerment", "offerings_to_deities", "summoning_prosperity",
                "fire_offerings", "practice_and_offering", "study", "drawing_mandalas", "sowing", "roofing",
                "laying_foundations", "planting_gardens", "paying_debts", "calling_back", "fights", "lawsuits",
                "giving_ornaments", "enthronement", "preparing_medicine", "new_clothes", "astrology_and_divination",
                "building", "taking_a_house", "building_towns", "temples", "making_images", "averting_rites", "the_zor",
                "taking_attendants", "beginning_small_tasks", "measuring_livestock", "funeral_rites",
                "building_storehouses", "planting_fields", "breaking_ground", "sewing_tents", "setting_out", "trade",
                "taking_sheep_in", "saddling_and_treating_horses_and_donkeys", "bloodletting_and_moxibustion",
                "petitioning", "naming", "power_increasing_and_pacifying_rites", "feasts", "works_for_the_living",
                "taking_in_a_bride"
            ),
            listOf(
                "war_and_raids", "paying_out_sheep", "fierce_rites", "giving_wealth", "taking_land",
                "pulling_down_temples", "building_a_hearth", "sinful_acts", "sending_away_a_bride"
            ),
        ),
        PUSHYA to Pair(
            listOf(
                "ordination", "empowerment", "consecration", "practice_and_offering", "taking_a_new_land", "teaching",
                "investiture", "giving_offerings", "giving_ornaments", "new_clothes", "enthronement",
                "prosperity_rites", "averting_rites", "drawing_mandalas", "preparing_medicine",
                "astrology_and_divination", "taking_attendants", "bloodletting", "setting_out",
                "taking_in_wealth_and_livestock", "temples", "making_images", "breaking_horses_and_oxen", "saddling",
                "building_a_hearth", "taking_over_a_treasury", "spectacles",
                "pacifying_increasing_power_and_auspicious_rites", "quick_accomplishment",
                "presenting_wealth_to_the_great", "naming", "building_dams", "raising_victory_banners",
                "works_for_the_living_and_the_dead", "lawsuits", "planting_trees", "taking_in_a_bride"
            ),
            listOf(
                "trade", "disputes", "paying_out", "gifts", "hunting_and_theft", "planting_fields", "building",
                "sending_away_prosperity", "feasts", "suppressing_sri", "washing_the_hair", "sending_away_a_bride"
            ),
        ),
        ASHLESHA to Pair(
            listOf(
                "war_and_raids", "killing_and_cutting", "destroying_towns", "petitioning", "suppressing_sri", "trade",
                "deceit_and_cheating", "paying_debts", "ponds_canals_and_dams", "planting_trees", "funeral_rites",
                "settling_deliberations", "fierce_rites", "turning_a_river"
            ),
            listOf(
                "offerings_to_deities", "consecration", "preparing_medicine", "gifts", "new_clothes",
                "giving_ornaments", "enthronement", "temples", "making_images", "astrology_and_divination", "building",
                "lawsuits", "a_new_house", "setting_out", "taming_livestock", "pressing_claims", "building_a_hearth",
                "raising_tents", "building_towns", "pacifying_increasing_and_power_rites",
                "auspicious_and_virtuous_acts", "saddling_and_treating_horses_and_donkeys", "sending_goats_away",
                "spectacles", "setting_up_a_loom", "ordination", "teaching", "drawing_mandalas", "study", "empowerment",
                "rites_for_life_and_wealth", "disputes", "virtuous_dharma", "lasting_work", "carrying_out_a_corpse",
                "giving_and_taking_a_bride"
            ),
        ),
        MAGHA to Pair(
            listOf(
                "ordination", "virtuous_practice", "teaching", "empowerment", "study", "practice_and_offering",
                "making_images", "astrology_and_divination", "learning_to_write", "drawing_mandalas", "averting_magic",
                "suppressing_sri", "fire_offerings", "the_zor", "attacking", "war_and_raids", "power_and_fierce_rites",
                "bloodletting_and_moxibustion", "increasing_rites", "a_new_house", "sowing", "taking_attendants",
                "taking_land", "giving_ornaments", "opening_storehouses", "taking_wealth", "prosperity_rites",
                "buying_horses_and_livestock", "crafts", "petitioning", "cutting_hair_and_nails",
                "beginning_white_work", "keeping_dogs"
            ),
            listOf(
                "gifts", "consecration", "enthronement", "building", "setting_out", "lawsuits", "offerings_to_deities",
                "feasts", "building_a_hearth", "washing_the_hair", "reconciliation", "new_clothes", "funerals",
                "dams_ponds_and_wells", "breaking_ground", "breaking_and_treating_horses_and_donkeys",
                "honouring_a_spiritual_friend", "sending_away_wealth_and_goods", "sewing_tents", "bon_rites",
                "planting_flowers_and_trees", "raising_banners", "a_bride_neither_given_nor_taken"
            ),
        ),
        PURVAPHALGUNI to Pair(
            listOf(
                "marriage_alliances", "taking_wealth", "mdos_for_life_and_prosperity", "meeting_relatives",
                "calling_back", "opening_storehouses", "trading_estates", "taking_livestock_and_wealth",
                "offerings_to_deities", "empowerment", "taking_land", "forts", "power_rites", "giving_ornaments",
                "astrology_and_divination", "paying_in_sheep", "travelling_south_west", "lasting_work",
                "building_a_hearth", "building_dams", "ordination", "teaching", "drawing_mandalas", "fire_offerings"
            ),
            listOf(
                "consecration", "funeral_rites", "feasts", "funerals", "subduing_enemies", "fierce_rites",
                "buying_horses", "setting_out", "paying_debts", "raids", "medicine", "weapons",
                "bloodletting_and_moxibustion", "donkeys", "giving_away_goods",
                "saddling_and_treating_horses_and_donkeys", "breaking_ground", "new_clothes", "washing_the_hair",
                "building_storehouses", "raising_banners", "ponds", "taming_livestock", "naming",
                "sewing_and_pitching_tents", "giving_or_taking_a_bride", "taking_attendants"
            ),
        ),
        UTTARAPHALGUNI to Pair(
            listOf(
                "prosperity_rites", "a_new_land", "taking_a_dwelling", "rites_for_long_life", "consecration",
                "hurling_zor", "teaching", "temples", "making_images", "averting_rites", "washing_the_hair",
                "giving_ornaments", "enthronement", "marriage_alliances", "astrology_and_divination", "sewing_tents",
                "dams", "building", "building_a_hearth", "planting_gardens", "giving_a_child_away", "moving_house",
                "gifts", "feasts", "naming", "raising_victory_banners", "saddling_horses_and_donkeys",
                "harsh_work_killing_and_cutting", "power_and_fierce_rites", "virtuous_and_auspicious_acts",
                "taking_attendants", "considered_and_lasting_work", "giving_and_taking_a_bride", "pacifying_rites",
                "sending_away_goods", "councils", "lawsuits", "new_clothes", "pitching_tents",
                "digging_ponds_canals_and_wells", "war_and_reconciliation", "breaking_horses_and_livestock_for_oneself",
                "setting_out_south_or_north"
            ),
            listOf(
                "field_work", "sowing", "breaking_ground", "planting_flowers_and_trees", "sending_away_a_son_or_sister",
                "sending_away_cattle", "crafts", "cutting_hair_and_nails"
            ),
        ),
        HASTA to Pair(
            listOf(
                "offerings", "ordination", "consecration", "empowerment", "teaching", "practice_and_offering",
                "drawing_mandalas", "study", "prosperity_rites", "giving_vows", "building", "a_new_house",
                "giving_ornaments", "crafts", "new_clothes", "preparing_medicine", "enthronement", "burial",
                "astrology_and_divination", "sowing", "field_work", "building_a_hearth", "building_towns",
                "rites_for_relatives", "joyful_occasions", "councils", "receiving_guests", "funeral_rites",
                "breaking_wild_livestock", "reconciliation", "shwa_rags", "breaking_ground", "virtuous_acts",
                "setting_up_cattle_and_sheep", "saddling_horses_and_donkeys", "trimming_manes", "lawsuits",
                "quick_accomplishment", "attacking", "war_and_raids", "sewing_tents", "suppressing",
                "cutting_hair_and_nails", "most_four_activities"
            ),
            listOf(
                "paying_out", "pitching_tents", "thieves", "setting_out", "funerals", "cremation",
                "carrying_out_a_corpse", "digging_ponds_and_wells", "temples", "making_images", "spectacles", "gifts",
                "sending_away_cattle", "taking_livestock_in", "roofing", "planting_flowers_and_trees", "trade",
                "a_bride"
            ),
        ),
        CITRA to Pair(
            listOf(
                "ordination", "making_images", "drawing_mandalas", "virtuous_acts", "listening_to_the_dharma",
                "quick_works", "practice_and_offering", "empowerment", "averting_rites", "life_and_prosperity_rites",
                "giving_ornaments", "cutting_hair_and_nails", "councils", "new_clothes",
                "taking_in_wealth_and_livestock", "preparing_medicine", "washing_the_hair", "painting", "field_work",
                "sowing", "taking_a_new_house", "horse_racing", "throwing_mdos_and_torma", "funerals",
                "taming_livestock", "sewing_tents", "taking_in_goats_and_cattle", "power_and_increasing_rites",
                "works_for_the_living", "white_work"
            ),
            listOf(
                "consecration", "offerings_to_deities", "enthronement", "long_life_rites_for_a_spiritual_friend",
                "petitioning", "paying_out_goats_and_livestock", "setting_out", "lawsuits", "astrology_and_divination",
                "pitching_tents_not_on_ones_own_estate", "burial", "opening_a_grave", "gifts", "theft_and_raids",
                "opening_ponds_canals_and_storehouses", "affairs_of_state", "petitioning_and_honouring",
                "fighting_enemies", "funeral_rites", "judging_disputes", "most_pacifying_and_fierce_rites", "dams",
                "spectacles", "naming", "a_bride"
            ),
        ),
        SVATI to Pair(
            listOf(
                "ordination", "consecration", "virtuous_acts", "drawing_mandalas", "study", "empowerment",
                "preparing_medicine", "deity_practice", "teaching", "prosperity_rites", "restoring_and_making_images",
                "field_work", "scattering_grain", "breaking_ground", "summoning_life", "averting_rites",
                "suppressing_sri", "reconciliation", "new_clothes", "enthronement", "taking_over_a_storehouse",
                "judging_disputes", "crossing_water", "pitching_tents", "marriage_alliances", "setting_out",
                "breaking_horses_and_oxen", "feasts", "the_brides_bath", "fire_offerings", "ponds_and_canals",
                "a_new_house", "sewing_tents", "saddling_and_treating_horses_donkeys_and_mules",
                "bloodletting_and_moxibustion", "dams", "laying_out_gardens", "taking_in_land_and_forts",
                "most_works_increasing_power_pacifying"
            ),
            listOf(
                "building", "giving_away_land_fields_or_forts", "paying_out", "lawsuits", "opening_grain_stores",
                "spectacles", "horse_racing", "burial", "gifts", "theft_and_raids", "fierce_rites"
            ),
        ),
        VISHAKHA to Pair(
            listOf(
                "drawing_mandalas", "ordination", "consecration", "empowerment", "practice", "fire_offerings",
                "teaching", "study", "marriage_alliances", "new_clothes", "paying_debts", "crafts", "a_new_house",
                "taking_over_a_storehouse", "suppressing_sri", "breaking_horses_and_oxen", "field_work", "sowing",
                "cutting_hair_and_nails", "building", "lawsuits", "power_and_fierce_rites", "dams", "subduing_enemies",
                "taking_in_a_bride"
            ),
            listOf(
                "burial", "selling_fields", "opening_storehouses", "building", "guests_going", "setting_out",
                "petitioning", "funerals", "trade", "enthronement", "temples", "making_images", "preparing_medicine",
                "pacifying_and_increasing_rites", "measuring_grain", "disputes", "lending", "gifts", "feasts",
                "washing_the_hair", "astrology_and_divination", "reconciliation", "planting_flowers_and_trees",
                "sending_away_a_bride"
            ),
        ),
        ANURADHA to Pair(
            listOf(
                "ordination", "consecration", "prosperity_rites", "medicine", "virtuous_acts", "teaching_dharma",
                "restoring_stupas", "new_clothes", "giving_ornaments", "enthronement", "washing_the_hair",
                "bloodletting_and_moxibustion", "painting", "war_and_raids", "attacking", "gtad_and_fierce_rites",
                "power_and_increasing_rites_in_general", "field_work", "sowing", "setting_out", "games", "quick_works",
                "saddling_horses_and_donkeys", "judging_disputes", "cutting_hair_and_nails", "travelling_far",
                "prosperity_rites_for_wool", "taking_in_gains", "lawsuits_in_the_south_and_west", "taking_land",
                "study", "taking_things_in"
            ),
            listOf(
                "pitching_tents", "funeral_rites", "paying_out", "councils", "building", "taking_a_new_house",
                "pacifying_rites", "feasts", "breaking_ground", "breaking_horses_donkeys_and_mules", "averting_rites",
                "suppressing_sri", "building_towns", "ploughing", "giving_a_child_away", "building_a_hearth",
                "a_bride_parts_from_her_husband"
            ),
        ),
        JYESHTHA to Pair(
            listOf(
                "harsh_speech", "bad_work", "arraying_for_battle", "suppressing_and_gtad", "destroying_and_robbing",
                "fierce_rites", "burying_the_phur_pa", "battle_and_killing", "theft_and_raids", "bad_fights",
                "petitioning", "ordination", "consecration", "summoning_life_and_prosperity", "offerings_to_deities",
                "auspicious_acts", "enthronement", "virtuous_acts", "study", "sowing", "giving_ornaments",
                "taking_wealth", "claiming_debts", "planting_trees", "building_bridges", "drawing_water_channels",
                "bloodletting_and_moxibustion", "sending_horses_to_the_herd", "lawsuits_in_the_south_and_west",
                "destroying_towns", "suppressing_sri", "sorcery", "works_for_the_living"
            ),
            listOf(
                "building", "new_clothes", "setting_out", "crafts", "preparing_medicine", "astrology_and_divination",
                "making_images", "reconciliation", "funeral_rites", "taking_servants", "giving_away_goats", "naming",
                "dams", "death_rites", "lawsuits", "pacifying_increasing_and_power_rites", "giving_and_taking_a_bride"
            ),
        ),
        MULA to Pair(
            listOf(
                "leading_an_army", "weapons_and_killing", "fierce_rites", "bad_means", "destroying_towns",
                "consecration", "life_and_prosperity_rites", "making_images", "trade", "offerings_to_deities",
                "restoring", "gifts", "building", "feasts", "sowing", "funeral_rites", "astrology_and_divination",
                "suppressing_sri", "averting_rites", "pacifying_rites", "setting_up_a_door", "building_storehouses",
                "giving_ornaments", "sending_messages", "taking_in_goods", "small_tasks",
                "power_and_fierce_works_for_the_living", "mdos_and_torma", "servants_work"
            ),
            listOf(
                "paying_out", "setting_out", "enthronement", "lawsuits", "new_clothes", "giving_meat_and_butter",
                "building_a_hearth", "raising_victory_banners", "burial", "naming", "dams", "taking_a_new_house",
                "fire_offerings", "travelling_abroad", "royal_affairs", "pacifying_and_increasing_rites_in_general",
                "councils", "auspicious_and_virtuous_acts", "giving_and_taking_a_bride"
            ),
        ),
        PURVASHADHA to Pair(
            listOf(
                "ordination", "consecration", "prosperity_rites", "empowerment", "marriage_alliances", "enthronement",
                "making_gardens", "restoring", "taking_attendants_and_women", "petitioning", "sowing",
                "taking_in_wealth_and_livestock", "giving_ornaments", "washing_the_hair", "breaking_ground",
                "spectacles", "buying_horses_livestock_fields_and_houses", "averting_rites", "fire_offerings",
                "building_storehouses", "building", "rites_for_long_life", "naming", "fierce_rites", "funerals",
                "setting_out", "sewing_tents", "trade", "auspicious_acts", "lawsuits_in_the_south_and_west",
                "ploughing_and_planting", "fighting_enemies", "appointing_stewards", "works_for_the_living"
            ),
            listOf(
                "sending_away_grain_and_wealth", "opening_storehouses", "new_clothes", "funeral_rites",
                "building_a_hearth", "moving_house", "gifts", "feasts", "horse_racing",
                "pacifying_and_increasing_rites", "building_dams", "a_bride"
            ),
        ),
        UTTARASHADHA to Pair(
            listOf(
                "ornaments", "consecration", "teaching_and_listening", "empowerment", "retreat_practice", "taking_vows",
                "taking_servants", "taking_in_grain_and_wealth", "planting_gardens", "making_images", "new_clothes",
                "enthronement", "building", "a_new_land_or_dwelling", "prosperity_and_long_life_rites",
                "astrology_and_divination", "planting_trees_and_flowers", "washing_the_hair", "bathing", "councils",
                "auspicious_acts", "funeral_rites", "sowing", "building_storehouses", "averting_rites",
                "treating_horses_donkeys_and_mules", "raising_banners", "repairing_ponds_and_canals",
                "preparing_medicine", "giving_and_taking_a_bride", "power_pacifying_and_increasing_rites_in_general",
                "naming", "everything_for_the_living"
            ),
            listOf(
                "slaughtering_livestock", "measuring_grain", "giving_butter_away", "cremation", "setting_out",
                "opening_storehouses", "cutting_hair_and_nails", "building_dams", "making_tombs_and_hiding_places",
                "fierce_rites", "sending_a_female_dzo_away", "pitching_tents", "lawsuits"
            ),
        ),
        SHRAVANA to Pair(
            listOf(
                "ponds_canals_and_water_works", "farming", "trade", "joyful_occasions", "virtuous_acts",
                "life_and_prosperity_rites", "marriage_alliances", "new_clothes", "giving_ornaments",
                "taking_a_new_house", "making_images", "averting_rites", "astrology_and_divination",
                "preparing_medicine", "bloodletting_and_moxibustion", "attacking", "reconciliation",
                "offerings_to_deities", "sowing_grain", "breaking_ground", "seeking_wealth_for_food",
                "opening_storehouses", "setting_out", "lawsuits", "breaking_horses_and_oxen", "building_storehouses",
                "paying_out_wealth", "spectacles", "summoning_human_prosperity", "games", "quick_works",
                "taking_a_bride_auspicious"
            ),
            listOf(
                "ordination", "consecration", "enthronement", "dharma_for_the_living", "bon_rites", "funeral_rites",
                "war_and_raids", "paying_debts", "horses_donkeys_and_gelding", "giving_offerings", "suppressing_sri",
                "feasts", "woodwork", "power_and_increasing_rites"
            ),
        ),
        DHANISHTHA to Pair(
            listOf(
                "consecration", "empowerment", "ordination", "summoning_prosperity", "preparing_medicine", "trade",
                "making_images", "building", "taking_a_new_house", "new_clothes", "giving_ornaments",
                "breaking_horses_and_oxen", "planting_fields", "breaking_ground", "setting_out", "lawsuits",
                "petitioning", "power_and_fierce_rites", "works_for_the_living", "war_and_raids", "inviting_a_teacher",
                "offerings_to_deities", "councils", "washing_the_hair", "treating_horses"
            ),
            listOf(
                "funeral_rites", "sending_away_wealth_goods_horses_and_livestock", "averting_rites", "suppressing_sri",
                "enthronement", "saddling_and_racing", "pacifying_and_increasing_rites", "moving_house", "gifts",
                "building_a_hearth", "marriage_alliances", "giving_and_taking_a_bride"
            ),
        ),
        SHATABHISHAJ to Pair(
            listOf(
                "teaching", "consecration", "ordination", "empowerment", "preparing_medicine", "sowing", "enthronement",
                "being_anothers_teacher", "taking_a_dwelling", "making_images", "building_a_treasury",
                "breaking_ground", "treating_and_breaking_horses", "buying_horses_and_livestock", "funeral_rites",
                "setting_out", "reconciliation", "buying_and_trading", "taking_land", "planting_trees", "virtuous_acts",
                "councils", "lasting_works_for_the_living", "lawsuits", "practice_and_offering", "prosperity_rites",
                "war_and_raids", "spectacles", "pacifying_increasing_power_but_special_cases"
            ),
            listOf(
                "giving_ornaments", "new_clothes", "digging_ponds_and_canals", "averting_rites", "crafts",
                "cutting_hair_and_nails", "giving_away_horses_donkeys_and_wealth", "opening_vessels", "building",
                "thieves", "funerals", "horse_racing", "sewing_tents", "giving_and_taking_a_bride"
            ),
        ),
        PURVABHADRAPADA to Pair(
            listOf(
                "empowerment", "consecration", "summoning_life", "marriage_alliances", "offerings_to_deities",
                "fire_offerings", "building", "reconciliation", "preparing_medicine", "works_for_the_living",
                "councils", "gifts", "buying_female_livestock", "breaking_in_horses_and_livestock", "taking_wealth",
                "building_a_hearth", "raising_victory_banners", "planting_trees", "taking_servants", "learning_crafts",
                "funeral_rites", "setting_up_a_sel", "trading_estates", "virtuous_acts", "keeping_dogs",
                "shwa_rags_against_rivers", "taking_land", "pitching_tents", "naming"
            ),
            listOf(
                "paying_out_wealth", "judging_disputes", "quarrels", "setting_out", "new_clothes", "washing_the_hair",
                "digging_ponds_and_canals", "lawsuits", "far_travel", "breaking_ground", "beginning_farming",
                "theft_and_raids", "ploughing", "field_work", "horse_racing", "giving_and_taking_a_bride",
                "pacifying_and_increasing_rites"
            ),
        ),
        UTTARABHADRAPADA to Pair(
            listOf(
                "giving_ornaments", "setting_out", "preparing_medicine", "painting", "consecration",
                "breeding_livestock", "measuring_out_and_taking_in", "enthronement", "teaching",
                "empowerment_and_taking_vows", "new_clothes", "councils", "gathering_grain_and_wealth",
                "ponds_and_canals", "field_work", "sowing", "making_images", "averting_rites", "suppressing_sri",
                "funeral_rites", "taking_servants", "washing_the_hair", "funerals", "power_and_fierce_rites",
                "quick_works", "war_theft_and_raids", "brewing_beer", "buying_and_trading", "breaking_ground",
                "planting_trees_and_flowers"
            ),
            listOf(
                "offerings_to_deities", "summoning_prosperity", "gatherings", "building", "taking_a_house",
                "building_towns", "bloodletting_and_moxibustion", "building_a_hearth", "sewing_tents",
                "works_for_the_living", "lawsuits", "feasts", "reconciliation", "dams", "cutting_hair_and_nails",
                "spectacles", "funerals", "pacifying_and_increasing_rites", "a_bride", "laying_poison"
            ),
        ),
        REVATI to Pair(
            listOf(
                "ordination", "consecration", "teaching", "empowerment", "deity_practice", "giving_vows",
                "wearing_new_clothes", "building", "judging_disputes", "enthronement", "prosperity_rites",
                "preparing_medicine", "virtuous_acts", "trade", "feasts", "distributing_livestock", "sowing",
                "taking_a_house", "washing_the_hair", "breaking_ground", "funeral_rites", "laying_out_a_corpse",
                "funerals", "gifts", "bloodletting_and_moxibustion", "naming", "cutting_hair_and_nails", "petitioning",
                "councils", "ones_own_affairs", "taming_livestock", "gelding_horses_and_donkeys",
                "works_for_the_living", "planting_trees", "power_and_pacifying_rites", "lawsuits_at_home"
            ),
            listOf(
                "cutting_clothes", "setting_out", "giving_a_child_away", "war", "fierce_work_robbing_and_destroying",
                "lasting_work", "averting_rites", "building_a_hearth", "sewing_tents", "reconciliation", "dams",
                "giving_a_daughter_away", "expelling", "sending_away_grain_and_wealth", "a_bride"
            ),
        ),
    )
}
