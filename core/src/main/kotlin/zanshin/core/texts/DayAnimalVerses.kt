/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.texts

import zanshin.core.tibetan.Animal
import zanshin.core.tibetan.Animal.*

/**
 * What the White Beryl's results of the twelve day animals, the *nyi ma* (vol. 2, pp. 356–359, read
 * on the scans in docs/sources/day-animals.md), name good and to avoid, as catalog wording keys in
 * the verse's order, with the six opposed pairs' avoidances at the end (SPEC §5.10). Left out:
 * words the reading leaves in doubt, what a verse calls middling (*'bring*) or acceptable (*rung
 * tsam*), and the earth lord's seat and its remedy, which are ch. 31's (`Texts.EARTH_LORD`).
 */
internal object DayAnimalVerses {
    private val VERSES: Map<Animal, Pair<List<String>, List<String>>> = mapOf(
        TIGER to Pair(
            listOf(
                "prosperity_rites", "building_forts", "setting_up_battlements", "leading_an_army", "killing_others",
                "fierce_and_harsh_work", "making_fierce_weapons", "suppressing", "lawsuits_in_the_east_and_west",
                "enthronement", "making_images", "burnt_offerings", "fire_offerings", "consecration",
            ),
            listOf(
                "offerings_to_deities", "robbery", "gifts", "marriage_alliances", "preparing_medicine", "empowerment",
                "bathing", "ransom_rites", "bon_rites", "thread_cross_and_torma_rites",
            ),
        ),
        RABBIT to Pair(
            listOf("funeral_rites", "work_with_oxen_and_sheep", "petitioning", "enthronement"),
            listOf(
                "war", "weapons", "building_forts", "marriage_alliances", "digging", "turning_stones",
                "repairing_ponds_and_canals", "feasts", "summoning_prosperity", "ritual_services", "funerals",
                "breaking_ground", "breaking_in_oxen", "sowing", "digging_springs", "ploughing", "breaking_in_horses",
                "building_dams", "gifts", "lawsuits_in_the_south", "suppressing",
            ),
        ),
        DRAGON to Pair(
            listOf(
                "enthronement", "virtuous_acts", "sending_hail", "making_images", "fierce_rites", "suppressing_sri",
                "offerings_to_deities", "subduing_enemies", "subduing_demons", "ordination", "practice_and_offering",
                "consecration", "empowerment", "taking_new_lands", "stretching_drum_skins",
            ),
            listOf(
                "repairing_boats_bridges_and_paths", "bloodletting", "moxibustion", "setting_up_a_water_mill", "digging",
                "turning_stones", "bringing_rain", "carrying_out_the_dead", "mourning",
            ),
        ),
        SNAKE to Pair(
            listOf("moving_the_sick", "seeing_off_and_welcoming", "lending", "sending_out_livestock", "naga_rites", "lawsuits_in_the_south"),
            listOf(
                "felling_trees", "funeral_rites", "medical_treatment", "burnt_offerings", "fierce_rites", "gifts",
                "paying_out_wealth", "feuds", "bloodletting_and_moxibustion", "building_a_hearth", "breaking_ground",
                "enthronement", "giving_and_taking_a_bride", "suppressing", "buying_and_trading", "ritual_services", "mourning",
            ),
        ),
        HORSE to Pair(
            listOf("offerings_to_deities", "secret_counsel", "trials_of_strength", "spectacles", "seeking_friends", "enthronement"),
            listOf(
                "building_forts", "building_a_house", "making_fierce_weapons", "funeral_rites", "fights_and_disputes",
                "judging_disputes", "lawsuits", "trimming_manes", "breaking_in_horses", "horse_racing", "bringing_rain",
                "trading_horses", "giving_and_taking_a_bride",
            ),
        ),
        SHEEP to Pair(
            listOf(
                "offerings_to_deities", "building_forts", "appointing_a_shepherd", "preparing_food", "a_bride",
                "sending_out_livestock", "lasting_work", "building_storehouses",
            ),
            listOf(
                "taking_vows", "bloodletting_and_moxibustion", "fierce_rites", "leading_an_army", "gifts",
                "preparing_medicine", "lawsuits", "bringing_rain",
            ),
        ),
        MONKEY to Pair(
            listOf(
                "giving_ornaments", "a_bride", "planting_trees", "dice", "board_games", "spectacles",
                "lawsuits_in_the_east_and_north", "bringing_rain", "suppressing", "funeral_rites", "making_gardens",
                "music_and_play",
            ),
            listOf(
                "new_clothes", "wearing_new_clothes", "weapons", "sewing_tents", "crafts", "councils", "robbery",
                "enthronement", "ceremonies_of_honour",
            ),
        ),
        BIRD to Pair(
            listOf(
                "trade", "treating_the_sick_and_children", "burnt_offerings", "fire_offerings", "planting_trees",
                "bringing_rain", "lawsuits",
            ),
            listOf(
                "secret_counsel", "feasts", "making_fierce_weapons", "suppressing", "virtuous_acts", "generosity",
                "spectacles",
            ),
        ),
        DOG to Pair(
            listOf(
                "crafts", "giving_a_daughter_away", "hunting", "binding_the_serak", "medicine", "judging_disputes",
                "petitioning", "prayer", "building_storehouses",
            ),
            listOf(
                "a_bride", "lawsuits", "gifts", "secret_counsel", "elopement", "washing_the_hair", "theft_and_raids",
                "fights", "building_forts", "bringing_rain", "fierce_rites",
            ),
        ),
        PIG to Pair(
            listOf("joyful_occasions", "inviting_a_teacher", "power_rites", "empowerment", "ordination", "suppressing_sri", "bringing_rain"),
            listOf(
                "burial", "turning_stones", "bon_rites", "building_forts", "breaking_ground", "suppressing",
                "learning_the_sciences", "painting", "funerals", "digging_wells",
            ),
        ),
        MOUSE to Pair(
            listOf(
                "setting_up_a_door", "theft_and_raids", "caring_for_children", "lawsuits", "elopement",
                "taking_in_a_child_born_out_of_wedlock", "building_storehouses", "abducting_women", "trade",
                "bringing_rain",
            ),
            listOf(
                "divination", "horse_racing", "making_fierce_weapons", "suppressing", "moxibustion",
                "slaughtering_livestock", "sending_away_a_son_or_sister",
            ),
        ),
        OX to Pair(
            listOf(
                "crafts", "leading_an_army", "making_weapons", "judging_disputes", "setting_up_a_forge",
                "buying_livestock", "building_forts", "laying_foundations",
            ),
            listOf(
                "quarrels", "trade", "learning_the_sciences", "ordination", "teaching_and_listening", "suppressing",
                "a_bride", "opening_grain_stores", "bringing_rain",
            ),
        ),
    )

    /** The six opposed pairs and what each avoids, after the twelve verses (p. 359); the bird's and hare's «གཏད་སྟོན» is left in doubt. */
    private val PAIRS: List<Pair<Set<Animal>, List<String>>> = listOf(
        setOf(TIGER, MONKEY) to listOf("councils_and_gatherings"),
        setOf(DOG, DRAGON) to listOf("war_and_raids"),
        setOf(PIG, SNAKE) to listOf("work_with_wood", "digging"),
        setOf(MOUSE, HORSE) to listOf("horse_racing", "slaughtering_livestock"),
        setOf(OX, SHEEP) to listOf("building_a_throne", "raising_flags"),
    )

    /** Each day animal's good and avoid lists, its verse's first, then its pair's avoidances. */
    val LISTS: Map<Animal, Pair<List<String>, List<String>>> = Animal.entries.associateWith { a ->
        val (good, avoid) = VERSES.getValue(a)
        good to (avoid + PAIRS.filter { a in it.first }.flatMap { it.second }).distinct()
    }
}
