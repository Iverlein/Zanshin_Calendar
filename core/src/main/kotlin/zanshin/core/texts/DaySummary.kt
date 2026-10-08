/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.texts

import zanshin.core.kyureki.DayMark
import zanshin.core.kyureki.Kigaku
import zanshin.core.kyureki.KyuSei
import zanshin.core.kyureki.KyurekiDay
import zanshin.core.kyureki.RekichuDay
import zanshin.core.kyureki.Rokuyo
import zanshin.core.kyureki.Senjitsu
import zanshin.core.kyureki.StarAffinity
import zanshin.core.kyureki.Tone
import zanshin.core.tibetan.PersonalDay
import zanshin.core.tibetan.PersonalMansion
import zanshin.core.tibetan.TibetanDay
import zanshin.core.tibetan.ZodiacSign
import zanshin.core.tibetan.element
import zanshin.core.tibetan.nectarHours
import zanshin.core.tibetan.risingSign

/** The app's lucky/unlucky dot for the rokuyō: an app convention, not a traditional mark. */
fun rokuyoTone(r: Rokuyo): Tone = when (r) {
    Rokuyo.TAIAN -> Tone.GOOD
    Rokuyo.BUTSUMETSU, Rokuyo.SHAKKO -> Tone.BAD
    else -> Tone.MIXED
}

/** The times of day a rokuyō's reading names good and to avoid (Todan), drawn as the day arc. */
fun rokuyoTimes(r: Rokuyo): Pair<Set<DayTime>, Set<DayTime>> {
    val reading = Texts.ROKUYO[r]
    fun times(keys: List<String>?) = keys.orEmpty().flatMap { Activities.TIMES[it].orEmpty() }.toSet()
    return times(reading?.goodKeys) to times(reading?.avoidKeys)
}

/** Lucky, unlucky or mixed from what a reading recommends and forbids. */
fun toneOf(r: Reading?): Tone = when {
    r == null -> Tone.NEUTRAL
    r.avoid.isEmpty() && r.good.isNotEmpty() -> Tone.GOOD
    r.good.isEmpty() && r.avoid.isNotEmpty() -> Tone.BAD
    else -> Tone.MIXED
}

/** One annotation of the day as the summary lists it; [latin] when its name is not kanji (the Tibetan day's factors). */
data class SummaryEntry(val kanji: String, val english: String, val tone: Tone, val reading: Reading?, val latin: Boolean = false)

/**
 * An activity and the annotations that name it good or to be avoided; on the 旧暦 page both sides
 * are kept. On the Tibetan page one side is, [outweighed] holds the factors that named it the
 * other way (SPEC §5.12), and [weight] is the sum of the weights of the voices standing on its side
 * ([DaySummary.row]); it orders the summary line and decides nothing.
 */
data class ActivityNote(
    val activity: Activity,
    val good: List<SummaryEntry>,
    val avoid: List<SummaryEntry>,
    val outweighed: List<SummaryEntry> = emptyList(),
    val weight: Int = 0,
) {
    val disputed: Boolean get() = good.isNotEmpty() && avoid.isNotEmpty()
}

/**
 * The factors of a Tibetan day in the rank of the White Beryl and the *kun
 * phan me long* (SPEC §5.12), strongest first: the two combinations of
 * weekday and mansion, which outweigh both (WB p. 333); Rāhu's course on
 * the dates it enters or turns, first of the *kun phan me long*'s seven; the weekday and the
 * mansion, whose own results come first (WB p. 337); the special days of
 * weekday and mansion, which "matter somewhat", with the burning date;
 * then the date, karaṇa, yoga and day animal (the lunar date's, the *nyi
 * ma* of WB's notes, vol. 2, p. 493) as the *kun phan me long* ranks them. The trigram is not
 * among them and counts last.
 */
enum class DayFactor {
    GREAT_COMBINATION,
    ELEMENT_PAIR,
    RAHU,
    WEEKDAY,
    MANSION,
    COMBINATION_DAY,
    LUNAR_DATE,
    KARANA,
    YOGA,
    DAY_ANIMAL,
    TRIGRAM;

    val english: String get() = gloss(this)
}

/** What decided the Tibetan day's tone, or an activity's side (SPEC §5.12). */
enum class VerdictBy {
    /** The two combinations of weekday and mansion agree: they outweigh every factor of the day (WB p. 333). */
    COMBINATION,

    /**
     * The combinations disagree: the strongest factor that takes a side. The White Beryl (vol. 2,
     * p. 376) weighs mixed factors by their order of strength, not by their number.
     */
    STRONGEST,
}

/**
 * Consecutive hours of the Tibetan day whose combination periods (SPEC §5.13) share the White
 * Beryl's verdict, GOOD to be accomplished and BAD to be avoided: [count] two-hour periods from
 * [first], counted from the hare hour at 05:00, with the signs rising in them. A run never crosses
 * the day's end at 05:00 the next morning.
 */
data class PeriodRun(val first: Int, val count: Int, val tone: Tone, val signs: List<ZodiacSign>)

/**
 * The hours above the Tibetan day (SPEC §5.13): its combination periods in [periods], which the
 * texts hold above every factor of the day (WB vol. 2, p. 376), and Jupiter's nectar periods in
 * [nectar], clock hours from 05:00 as [nectarHours] gives them. Neither is weighed into the day: they
 * are times within it.
 */
data class DayHours(val periods: List<PeriodRun>, val nectar: List<Int>) {
    companion object {
        fun of(day: TibetanDay): DayHours {
            val runs = mutableListOf<PeriodRun>()
            for (hour in 0 until 12) {
                val sign = risingSign(day.month, hour)
                val tone = Texts.DUS_SBYOR.getValue(sign).first
                val last = runs.lastOrNull()
                if (last?.tone == tone) {
                    runs[runs.lastIndex] = last.copy(count = last.count + 1, signs = last.signs + sign)
                } else {
                    runs += PeriodRun(hour, 1, tone, listOf(sign))
                }
            }
            return DayHours(runs, nectarHours(day.weekday))
        }
    }
}

/**
 * The Tibetan day's tone (SPEC §5.12) and what decided it: [by] how, [factor] the voice that
 * decided, and [deciding] its members on the day, the combination's two parts or the special days
 * that spoke as one.
 */
data class DayVerdict(val tone: Tone, val by: VerdictBy, val factor: DayFactor, val deciding: List<SummaryEntry>)

/**
 * The day in brief (ROADMAP R3), built only from what the sources state or
 * what can be counted. On the 旧暦 page there is no verdict or weighting:
 * annotations are grouped by tone and activities listed with who names them,
 * and where annotations disagree both sides stay; only the lower band's own
 * rules set some aside (SPEC §7.5). The Tibetan day is weighed
 * as the *kun phan me long* says (SPEC §5.12): [verdict] is its tone, and
 * outweighed factors are left out.
 */
data class DaySummary(
    val mark: DayMark?,
    val byTone: Map<Tone, List<SummaryEntry>>,
    val activities: List<ActivityNote>,
    val personal: List<SummaryEntry>,
    val affinity: StarAffinity?,
    val verdict: DayVerdict? = null,
    /** The 旧暦 annotations the almanac does not count today, with the one that sets them aside (SPEC §7.5). */
    val setAside: Map<SummaryEntry, List<SummaryEntry>> = emptyMap(),
    /** The Tibetan day's combination periods and nectar periods, shown and not weighed (SPEC §5.13). */
    val hours: DayHours? = null,
) {
    /**
     * The side the weighing gives [activity] on the day, GOOD or BAD, with the
     * voices standing on it, strongest first; null when no voice names it.
     */
    fun sideOf(activity: Activity): Pair<Tone, List<SummaryEntry>>? =
        activities.firstOrNull { it.activity == activity }?.let { if (it.good.isNotEmpty()) Tone.GOOD to it.good else Tone.BAD to it.avoid }

    val good: List<ActivityNote> get() = activities.filter { it.good.isNotEmpty() }
    val avoid: List<ActivityNote> get() = activities.filter { it.avoid.isNotEmpty() }

    /** The families of the activities named good, in family order (the summary line's glyphs). */
    val goodFamilies: List<ActivityFamily> get() = good.map { it.activity.family }.distinct().sorted()

    /** The families of the activities named to avoid, in family order. */
    val avoidFamilies: List<ActivityFamily> get() = avoid.map { it.activity.family }.distinct().sorted()

    /** Families holding an activity named both good and to avoid. */
    val disputedFamilies: Set<ActivityFamily> get() = activities.filter { it.disputed }.map { it.activity.family }.toSet()

    /**
     * The Tibetan summary line (SPEC §10.7): one row of at most [slots] families, those of the
     * heaviest works named good, then those of the heaviest named to avoid, half the places each and
     * a side's spare places to the other. Each family is drawn by its heaviest work on that side, and
     * appears once: where it would stand on both, it keeps the side where its work is heavier and the
     * next family takes the other place. Equal weights keep the breakdown's order (more voices first,
     * then the stronger). The weights pick the glyphs only and change no side.
     */
    fun row(slots: Int = ROW_SLOTS): Pair<List<ActivityNote>, List<ActivityNote>> {
        val place = activities.withIndex().associate { it.value.activity to it.index }
        val order = compareByDescending<ActivityNote> { it.weight }.thenBy { place.getValue(it.activity) }
        fun heaviest(notes: List<ActivityNote>) =
            notes.groupBy { it.activity.family }.values.map { it.sortedWith(order).first() }.sortedWith(order).toMutableList()
        val good = heaviest(this.good)
        val avoid = heaviest(this.avoid)
        while (true) {
            val g = minOf(good.size, maxOf(slots / 2, slots - avoid.size))
            val shownGood = good.take(g)
            val shownAvoid = avoid.take(minOf(avoid.size, slots - g))
            val twice = shownGood.filter { n -> shownAvoid.any { it.activity.family == n.activity.family } }
            if (twice.isEmpty()) return shownGood to shownAvoid
            for (n in twice) {
                val other = shownAvoid.first { it.activity.family == n.activity.family }
                if (order.compare(n, other) <= 0) avoid -= other else good -= n
            }
        }
    }

    companion object {
        /** The places of the Tibetan summary line: eight glyphs fit a phone's width at 22 dp. */
        const val ROW_SLOTS = 8

        /**
         * A voice's weight in [row]: ten for the combination down to one for the trigram, by the rank
         * of SPEC §5.12. An app convention that orders the line by the Phugpa order of strength; the
         * only numbers in the texts (the date one, the planet four, the mansion eight) are the
         * Kashmiri paṇḍita's, which the White Beryl sets aside for that order (vol. 2, p. 376).
         */
        fun weight(rank: Int): Int = 10 - rank

        private val PERSONAL = setOf(Senjitsu.TAIKA, Senjitsu.ROSHAKU, Senjitsu.METSUMON)

        fun of(day: KyurekiDay, rk: RekichuDay, birthStar: KyuSei? = null): DaySummary {
            val shukuReading = Texts.SHUKU[rk.shuku]
            fun entry(s: Senjitsu) = SummaryEntry(s.kanji, s.english, s.tone, Texts.SENJITSU[s])
            val setAside = rk.setAside
            val entries = buildList {
                add(SummaryEntry(day.rokuyo.kanji, day.rokuyo.english, rokuyoTone(day.rokuyo), Texts.ROKUYO[day.rokuyo]))
                add(SummaryEntry(rk.choku.kanji, rk.choku.english, rk.choku.tone, Texts.CHOKU[rk.choku]))
                add(SummaryEntry("${rk.shuku.kanji}宿", rk.shuku.english, toneOf(shukuReading), shukuReading))
                rk.senjitsu.filter { it !in setAside }.forEach { add(entry(it)) }
            }

            return DaySummary(
                mark = rk.mark,
                byTone = entries.filter { it.tone != Tone.NEUTRAL }.groupBy { it.tone },
                activities = activities(entries),
                personal = entries.filter { e -> PERSONAL.any { it.kanji == e.kanji } },
                affinity = birthStar?.let { Kigaku.affinity(it, rk.dayStar) },
                setAside = setAside.entries.groupBy({ entry(it.value) }, { entry(it.key) }),
            )
        }

        /**
         * The Tibetan day in brief, weighed as SPEC §5.12 says. One weighing answers both
         * questions, what the day is and what each work is on it: the combination of weekday and
         * mansion is the result where it speaks, on the day where its two parts agree and on a work
         * where its element pair names it, and the factors that say otherwise are outweighed (WB
         * p. 333); elsewhere the strongest factor that takes a side, the factors whose lists name
         * a work deciding it and those with a tone of their own the day (the White Beryl, vol. 2,
         * p. 376, which the *kun phan me long*'s rules 2–4 digest).
         *
         * [personalDay] and [personalMansions], the birth year's own weekday and mansions on the day,
         * are listed as [personal] and not weighed: WB calls them "of particular importance" (vol. 2,
         * p. 338), and a later reader counts them as a particular case (*dmigs bsal*), but no text
         * found places them against the combination (SPEC §5.12).
         */
        fun of(day: TibetanDay, personalDay: PersonalDay? = null, personalMansions: List<PersonalMansion> = emptyList()): DaySummary {
            fun entry(name: String, factor: DayFactor, tone: Tone, reading: Reading?) =
                SummaryEntry(name, factor.english, tone, reading, latin = true)
            fun lucky(b: Boolean) = if (b) Tone.GOOD else Tone.BAD
            fun one(rank: Int, factor: DayFactor, e: SummaryEntry, vararg readings: Reading?) =
                Voice(rank, factor, listOf(Member(e, readings.filterNotNull())))
            val pair = day.elementPair
            val mansion = Texts.MANSION.getValue(day.mansion)
            val animal = Texts.ELECTIONAL_ANIMAL.getValue(day.lunarDayAnimal)
            val trigram = Texts.ELECTIONAL_TRIGRAM.getValue(day.trigram)

            // The named combination and the element pair speak as one: the combination ('phrod) of weekday and mansion.
            val combination = Voice(
                COMBINATION_RANK,
                DayFactor.GREAT_COMBINATION,
                listOf(
                    Member(
                        entry(day.greatCombination.english.replaceFirstChar(Char::uppercase), DayFactor.GREAT_COMBINATION, lucky(day.greatCombination.lucky), Texts.GREAT_COMBINATION[day.greatCombination]),
                        listOfNotNull(Texts.GREAT_COMBINATION[day.greatCombination]),
                    ),
                    Member(
                        entry("${day.weekday.element.english} – ${day.mansion.element.english}", DayFactor.ELEMENT_PAIR, lucky(pair.auspicious), Texts.ELEMENT_PAIR[pair]),
                        listOfNotNull(Texts.ELEMENT_PAIR[pair]),
                    ),
                ),
            )
            // Rāhu is reckoned by direction: it names works but takes no side on the day as a whole.
            val rahu = Voice(
                1,
                DayFactor.RAHU,
                listOfNotNull(Texts.RAHU[day.day], Texts.RAHU_MONTH[day.month to day.day]).map {
                    Member(entry(Catalog.text("DayFactor.RAHU"), DayFactor.RAHU, Tone.NEUTRAL, it), listOf(it))
                },
            )
            // The special days speak as one, and only when they agree; the burning date stands with them,
            // as in WB's almanac (vol. 1, p. 177).
            val special = Voice(
                4,
                DayFactor.COMBINATION_DAY,
                day.combinationDays.map {
                    Member(entry(it.english.replaceFirstChar(Char::uppercase), DayFactor.COMBINATION_DAY, lucky(it.lucky), Texts.COMBINATION_DAY[it]), listOfNotNull(Texts.COMBINATION_DAY[it]))
                } + day.gtsugLagDays.map {
                    Member(entry(it.english.replaceFirstChar(Char::uppercase), DayFactor.COMBINATION_DAY, lucky(it.lucky), Texts.GTSUG_LAG_DAY[it]), listOfNotNull(Texts.GTSUG_LAG_DAY[it]))
                } + listOfNotNull(
                    Texts.BURNING_DATE.takeIf { day.burningDate }?.let {
                        Member(entry(Catalog.text("BurningDate").replaceFirstChar(Char::uppercase), DayFactor.COMBINATION_DAY, Tone.BAD, it), listOf(it))
                    },
                ),
            )
            val voices = listOf(
                combination,
                rahu,
                one(2, DayFactor.WEEKDAY, entry(day.weekday.english, DayFactor.WEEKDAY, Texts.weekdayTone(day.weekday), Texts.WEEKDAY[day.weekday]), Texts.WEEKDAY[day.weekday], Texts.ELECTIONAL_WEEKDAY.getValue(day.weekday)),
                // The mansion and the nyi ma have no tone of their own: their lists name works both ways.
                one(3, DayFactor.MANSION, entry(day.mansion.sanskrit, DayFactor.MANSION, Tone.NEUTRAL, mansion), mansion),
                special,
                one(
                    5,
                    DayFactor.LUNAR_DATE,
                    entry(Catalog.format("ElectionalFactor.LUNAR_DATE.title", day.day.toString()), DayFactor.LUNAR_DATE, Texts.lunarDateTone(day.day), Texts.LUNAR_DATE[day.day - 1]),
                    Texts.LUNAR_DATE[day.day - 1], Texts.ELECTIONAL_DATE.getValue(day.day), Texts.HAIRCUT_LIST[day.day - 1],
                ),
                one(6, DayFactor.KARANA, entry(day.karana.sanskrit, DayFactor.KARANA, Texts.KARANA_TONE.getValue(day.karana), Texts.KARANA[day.karana]), Texts.KARANA[day.karana]),
                one(7, DayFactor.YOGA, entry(day.yoga.sanskrit, DayFactor.YOGA, Texts.YOGA_TONE.getValue(day.yoga), Texts.YOGA[day.yoga]), Texts.YOGA[day.yoga]),
                one(8, DayFactor.DAY_ANIMAL, entry(gloss(day.lunarDayAnimal), DayFactor.DAY_ANIMAL, Tone.NEUTRAL, animal), animal),
                // Not among the kun phan me long's seven, so the weakest: its lists decide only what no other factor names.
                one(9, DayFactor.TRIGRAM, entry(day.trigram.wylie.replaceFirstChar(Char::uppercase), DayFactor.TRIGRAM, Tone.NEUTRAL, trigram), trigram),
            ).filter { it.members.isNotEmpty() }

            val dayDecision = weigh(voices.mapNotNull { v -> v.tone?.let { Vote(v, v.members, it) } }, combination.tone)!!
            // The day's deciding voice is the strongest standing on its side: the combination where it speaks.
            val decider = dayDecision.standing.first()
            val verdict = DayVerdict(dayDecision.tone, dayDecision.by, decider.voice.factor, decider.members.map { it.entry })

            val order = LinkedHashSet<Activity>()
            voices.forEach { v -> v.members.forEach { order += it.good + it.avoid } }
            // Works on which more of the day's voices agree come first, then those a stronger one decides.
            val decided = order.mapNotNull { a ->
                weigh(voices.mapNotNull { it.vote(a) })?.let { a to it }
            }.sortedWith(compareBy({ -it.second.standing.size }, { it.second.standing.first().voice.rank }))
            fun note(a: Activity, d: Decision, named: List<Vote>): ActivityNote {
                val entries = named.flatMap { v -> v.members.map { it.entry } }
                return ActivityNote(a, good = if (d.tone == Tone.GOOD) entries else emptyList(), avoid = if (d.tone == Tone.BAD) entries else emptyList())
            }
            return DaySummary(
                mark = null,
                byTone = mapOf(verdict.tone to dayDecision.standing.flatMap { v -> v.members.map { it.entry } }),
                activities = decided.map { (a, d) ->
                    note(a, d, d.standing).copy(
                        outweighed = d.outweighed.flatMap { v -> v.members.map { it.entry } },
                        weight = d.standing.sumOf { weight(it.voice.rank) },
                    )
                },
                personal = listOfNotNull(
                    personalDay?.let {
                        SummaryEntry(it.english, day.weekday.english, if (it == PersonalDay.ANTI) Tone.BAD else Tone.GOOD, Texts.PERSONAL_DAY[it], latin = true)
                    },
                ) + personalMansions.map {
                    SummaryEntry(it.english, day.mansion.sanskrit, Texts.personalMansionTone(it), Texts.PERSONAL_MANSION[it], latin = true)
                },
                affinity = null,
                verdict = verdict,
                hours = DayHours.of(day),
            )
        }

        private const val COMBINATION_RANK = 0

        /** A factor's readings, with the activities they name good and to avoid; those they name both ways it is silent on. */
        private class Member(val entry: SummaryEntry, readings: List<Reading>) {
            private val named = Activities.of(readings.flatMap { it.goodKeys }) to Activities.of(readings.flatMap { it.avoidKeys })
            val good: Set<Activity> = named.first - named.second
            val avoid: Set<Activity> = named.second - named.first
        }

        /**
         * One voice in the weighing, [rank] 0 strongest: a factor, or a group that speaks as one (the
         * two combinations; the special days; Rāhu's courses), named by its [factor].
         */
        private class Voice(val rank: Int, val factor: DayFactor, val members: List<Member>) {
            /** Its tone on the day as a whole, where all its members have the same, lucky or unlucky. */
            val tone: Tone? = members.map { it.entry.tone }.distinct().singleOrNull()?.takeIf { it == Tone.GOOD || it == Tone.BAD }

            /** Its side on [a], with the members that name it so; none where its members name it both ways. */
            fun vote(a: Activity): Vote? {
                val good = members.filter { a in it.good }
                val avoid = members.filter { a in it.avoid }
                return when {
                    good.isNotEmpty() && avoid.isEmpty() -> Vote(this, good, Tone.GOOD)
                    avoid.isNotEmpty() && good.isEmpty() -> Vote(this, avoid, Tone.BAD)
                    else -> null
                }
            }
        }

        private class Vote(val voice: Voice, val members: List<Member>, val side: Tone)

        /** The side taken, how, the votes that stand on it and those outweighed. */
        private class Decision(val tone: Tone, val by: VerdictBy, val standing: List<Vote>, val outweighed: List<Vote>)

        /**
         * The weighing of SPEC §5.12, for the day or for one activity. Where the combination speaks, its
         * side is the result, as WB p. 333 says even of a good planet and mansion, and the other side is
         * outweighed: on a work when its element pair names it, on the day when its two parts agree
         * ([dayResult]). Its tone on the day does not decide a work it does not name: a lucky day does
         * not lift the date's or the weekday's prohibitions. Otherwise the strongest vote's side: the
         * White Beryl's «མང་ཉུང་སྟོབས་ཀྱི་ཁྱད་པར་བརྩི» weighs by strength, and its *phyogs sdebs* is a
         * factor's general good or bad, not a count of sides (docs/sources/weighing.md).
         */
        private fun weigh(votes: List<Vote>, dayResult: Tone? = null): Decision? {
            if (votes.isEmpty()) return null
            val (side, by) = votes.firstOrNull { it.voice.rank == COMBINATION_RANK }?.let { it.side to VerdictBy.COMBINATION }
                ?: dayResult?.let { it to VerdictBy.COMBINATION }
                ?: (votes.minBy { it.voice.rank }.side to VerdictBy.STRONGEST)
            val (standing, outweighed) = votes.sortedBy { it.voice.rank }.partition { it.side == side }
            return Decision(side, by, standing, outweighed)
        }

        /** Activities with the entries that name them, in order of first mention, so the list follows the almanac's own order. */
        private fun activities(entries: List<SummaryEntry>): List<ActivityNote> {
            val order = LinkedHashSet<Activity>()
            entries.forEach { e -> e.reading?.let { order += Activities.of(it.goodKeys) + Activities.of(it.avoidKeys) } }
            return order.map { a ->
                ActivityNote(
                    a,
                    good = entries.filter { e -> e.reading != null && a in Activities.of(e.reading.goodKeys) },
                    avoid = entries.filter { e -> e.reading != null && a in Activities.of(e.reading.avoidKeys) },
                )
            }
        }
    }
}
