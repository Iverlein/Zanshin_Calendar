/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

/*
 * The reckoning of the moment (ROADMAP R2o; WB vol. 2, ch. 30; docs/sources/reckonings.md, RD17 and O16, question
 * 164): the trigram and the mewa of a moment. The date's trigram and mewa stand at dawn, the hare hour; the trigram
 * of a month's first date is li for the tiger, horse and dog months, zin for the pig, sheep and hare, dwa for the
 * bird, ox and snake, kham for the mouse, dragon and monkey, and runs forward one a date; the mewa is 1 white for the
 * first month of each season, 4 green for the middle, 7 red for the last, and runs up one a date (the notes, vol. 2,
 * p. 535; the Moonbeams). Through the day they run on by WB's rough spans (p. 162): three quarters of an hour for a
 * trigram, two thirds for a mewa, eight and nine by day, and the same again from the date's own by night. The day is
 * twelve hours from dawn at 05:00, the night twelve from 17:00.
 */
/** The times of the day the moment's readings are headed by (p. 156), from dawn, one to each hour of the hare to the bird. */
enum class MomentPeriod { DAWN, SUNRISE, WARMTH, NOON, PAST_NOON, LATE, SUNSET; val english: String get() = zanshin.core.texts.gloss(this) }

object MomentReckoning {
    /** The period of the day [minutes] after dawn: the hare's hour dawn, the dragon's sunrise, on to the bird's sunset; null by night. */
    fun period(minutes: Int): MomentPeriod? = MomentPeriod.entries.getOrNull(minutes / 120)

    /** The trigram that dawns on a month's first date, by the month's animal. */
    fun monthTrigram(month: Animal): Trigram = when (month) {
        Animal.TIGER, Animal.HORSE, Animal.DOG -> Trigram.LI
        Animal.PIG, Animal.SHEEP, Animal.RABBIT -> Trigram.ZIN
        Animal.BIRD, Animal.OX, Animal.SNAKE -> Trigram.DWA
        Animal.MOUSE, Animal.DRAGON, Animal.MONKEY -> Trigram.KHAM
    }

    /** The mewa that dawns on a month's first date: 1 for a season's first month, 4 for its middle, 7 for its last. */
    fun monthMewa(month: Animal): Int = when (month) {
        Animal.TIGER, Animal.SNAKE, Animal.MONKEY, Animal.PIG -> 1
        Animal.RABBIT, Animal.HORSE, Animal.BIRD, Animal.MOUSE -> 4
        Animal.DRAGON, Animal.SHEEP, Animal.DOG, Animal.OX -> 7
    }

    /** The trigram of the lunar date [date] (1–30) in a month of [month]: forward one a date in WB's order. */
    fun dateTrigram(month: Animal, date: Int): Trigram = Trigram.entries[(monthTrigram(month).ordinal + date - 1) % 8]

    /** The mewa of the lunar date [date] in a month of [month]: up one a date, round the nine. */
    fun dateMewa(month: Animal, date: Int): Int = (monthMewa(month) + date - 2) % 9 + 1

    /** The moment's trigram, [minutes] after dawn (0–1439) on the lunar date [date] of a month of [month]. */
    fun trigram(month: Animal, date: Int, minutes: Int): Trigram =
        Trigram.entries[(dateTrigram(month, date).ordinal + (minutes % 720) / 90) % 8]

    /** The moment's mewa, [minutes] after dawn on the lunar date [date] of a month of [month]. */
    fun mewa(month: Animal, date: Int, minutes: Int): Int = (dateMewa(month, date) - 1 + (minutes % 720) / 80) % 9 + 1
}
