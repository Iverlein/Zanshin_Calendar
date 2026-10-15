/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.texts

import zanshin.core.tibetan.Link
import zanshin.core.tibetan.Link.*

/**
 * What the White Beryl's days of the twelve links name good and to avoid (vol. 2, pp. 351–356, read on
 * the scans in docs/sources/day-letters.md, *WB's days of the twelve links*), as catalog wording keys in
 * the verse's order: the works, then washing the head, washing the body and new clothes, which every
 * verse names one way or the other. Left out: the words the reading leaves unidentified (name and
 * form's «འཇལ་ལེན» and «འཇིགས་ལ་ཁྲོས་པ», craving's «སྨྱུར་སྤང»), what a verse names without a side (the
 * sense sources' entering a house and coming out again), a king's ablution on the sense sources' day,
 * where washing the body is bad, and the sweeping lines "every other work" and "whatever else is
 * begun", which stand in the readings. The directions, the birth and the remedy are the readings'
 * rows; the loss stays in the topic file and the illness waits for the health screen (ROADMAP H1).
 */
internal object LinkVerses {
    val LISTS: Map<Link, Pair<List<String>, List<String>>> = mapOf(
        IGNORANCE to Pair(
            listOf(
                "stupas", "building_forts", "offerings", "gifts", "seeking_land", "washing_the_hair", "bathing",
                "making_clothes", "seeking_ones_aims", "gatherings",
            ),
            listOf("meeting_great_people", "divination", "wearing_new_clothes"),
        ),
        FORMATION to Pair(
            listOf("study", "learning_to_write", "giving_ornaments", "a_bride", "virtuous_work", "bathing", "building_a_house", "subduing_enemies"),
            listOf("washing_the_hair", "wearing_new_clothes"),
        ),
        CONSCIOUSNESS to Pair(
            listOf(
                "setting_up_supports", "empowerment", "practice_and_offering", "medical_treatment", "offerings", "gifts",
                "subduing_enemies", "divination", "destroying_towns", "wearing_new_clothes", "gatherings", "a_bride",
                "enthronement", "washing_the_hair",
            ),
            listOf("bathing"),
        ),
        NAME_AND_FORM to Pair(
            listOf("prayers_for_good_fortune", "cutting_hair_and_nails", "white_work", "meeting_the_lama", "preparing_medicine"),
            listOf("washing_the_hair", "bathing", "wearing_new_clothes"),
        ),
        SOURCES to Pair(
            listOf(
                "enthronement", "a_bride", "averting_rites", "setting_up_supports", "taking_a_town", "virtuous_work",
                "hiding_treasure", "washing_the_hair", "wearing_new_clothes",
            ),
            listOf("prostrations", "sinful_acts", "bathing"),
        ),
        CONTACT to Pair(
            listOf("robbery", "killing_others", "punishing", "suppressing", "fierce_and_harsh_work", "washing_the_hair"),
            listOf("crafts", "bathing", "wearing_new_clothes"),
        ),
        FEELING to Pair(
            listOf(
                "meeting_friends", "setting_out", "trade", "crossing_water", "pacifying_rites", "taking_a_retinue",
                "drawing_mandalas", "gathering_food_and_wealth", "virtuous_work",
            ),
            listOf("washing_the_hair", "shaving", "bathing", "wearing_new_clothes"),
        ),
        CRAVING to Pair(
            listOf("a_bride", "gathering_wealth_and_goods", "trade", "gatherings", "virtuous_work", "divination"),
            listOf("wearing_new_clothes", "washing_the_hair", "shaving", "bathing"),
        ),
        GRASPING to Pair(
            listOf(
                "preparing_medicine", "fighting_enemies", "sending_messages", "going_to_anothers_wife", "cutting_the_beard",
                "keeping_silence", "washing_the_hair", "bathing",
            ),
            listOf("wearing_new_clothes"),
        ),
        BECOMING to Pair(
            listOf("welcoming_women_and_friends", "washing_the_hair", "bathing"),
            listOf("wearing_new_clothes", "fights_and_disputes"),
        ),
        BIRTH to Pair(
            listOf("averting_rites", "house_repairs", "taking_a_house", "the_works_of_a_house", "affairs_of_state", "washing_the_hair", "bathing"),
            listOf("wearing_new_clothes"),
        ),
        AGEING_AND_DEATH to Pair(
            listOf("robbery", "taking_things_in", "washing_the_hair", "offerings", "gifts"),
            listOf("crafts", "divination", "fights_and_disputes", "bathing", "wearing_new_clothes"),
        ),
    )
}
