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
import zanshin.core.tibetan.TibetanDay
import zanshin.core.tibetan.element

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

/** An activity and the annotations that name it good or to be avoided; both sides are kept. */
data class ActivityNote(val activity: Activity, val good: List<SummaryEntry>, val avoid: List<SummaryEntry>) {
    val disputed: Boolean get() = good.isNotEmpty() && avoid.isNotEmpty()
}

/**
 * The factors of a Tibetan day in the rank of the White Beryl and the *kun
 * phan me long* (SPEC §5.12), strongest first: the two combinations of
 * weekday and mansion, which outweigh both (WB p. 333); Rāhu's course on
 * the dates it enters or turns, first of the *kun phan me long*'s seven; the weekday and the
 * mansion, whose own results come first (WB p. 337); the special days of
 * weekday and mansion, which "matter somewhat"; then the date, karaṇa, yoga
 * and day animal (the lunar date's, the *nyi ma* of WB's notes, vol. 2,
 * p. 493) as the *kun phan me long* ranks them. The trigram is not
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

/** What decided the Tibetan day's tone (SPEC §5.12). */
enum class VerdictBy {
    /** The two combinations of weekday and mansion, which agree. */
    COMBINATION,

    /** The combinations disagree: the special days, which agree (the text's special case). */
    COMBINATION_DAY,

    /** The combinations disagree and no special day settles it: the side more factors take. */
    SIDES,

    /** As many factors on each side: the strongest of them. */
    STRONGEST,
}

/** The Tibetan day's tone (SPEC §5.12) and what decided it. */
data class DayVerdict(val tone: Tone, val by: VerdictBy)

/**
 * The day in brief (ROADMAP R3), built only from what the sources state or
 * what can be counted. On the 旧暦 page there is no verdict or weighting:
 * annotations are grouped by tone and activities listed with who names them,
 * and where annotations disagree both sides stay. The Tibetan day is weighed
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
) {
    val good: List<ActivityNote> get() = activities.filter { it.good.isNotEmpty() }
    val avoid: List<ActivityNote> get() = activities.filter { it.avoid.isNotEmpty() }

    /** The families of the activities named good, in family order (the summary line's glyphs). */
    val goodFamilies: List<ActivityFamily> get() = good.map { it.activity.family }.distinct().sorted()

    /** The families of the activities named to avoid, in family order. */
    val avoidFamilies: List<ActivityFamily> get() = avoid.map { it.activity.family }.distinct().sorted()

    /** Families holding an activity named both good and to avoid. */
    val disputedFamilies: Set<ActivityFamily> get() = activities.filter { it.disputed }.map { it.activity.family }.toSet()

    companion object {
        private val PERSONAL = setOf(Senjitsu.TAIKA, Senjitsu.ROSHAKU, Senjitsu.METSUMON)

        fun of(day: KyurekiDay, rk: RekichuDay, birthStar: KyuSei? = null): DaySummary {
            val shukuReading = Texts.SHUKU[rk.shuku]
            val entries = buildList {
                add(SummaryEntry(day.rokuyo.kanji, day.rokuyo.english, rokuyoTone(day.rokuyo), Texts.ROKUYO[day.rokuyo]))
                add(SummaryEntry(rk.choku.kanji, rk.choku.english, rk.choku.tone, Texts.CHOKU[rk.choku]))
                add(SummaryEntry("${rk.shuku.kanji}宿", rk.shuku.english, toneOf(shukuReading), shukuReading))
                rk.senjitsu.forEach { add(SummaryEntry(it.kanji, it.english, it.tone, Texts.SENJITSU[it])) }
            }

            return DaySummary(
                mark = rk.mark,
                byTone = entries.filter { it.tone != Tone.NEUTRAL }.groupBy { it.tone },
                activities = activities(entries),
                personal = entries.filter { e -> PERSONAL.any { it.kanji == e.kanji } },
                affinity = birthStar?.let { Kigaku.affinity(it, rk.dayStar) },
            )
        }

        /**
         * The Tibetan day in brief, weighed as SPEC §5.12 says: each activity
         * decided by the strongest tier of factors that names it with one voice,
         * the day's tone by the combinations where they agree, else by the
         * special days, else by the side with more factors, else by the
         * strongest; outweighed factors are left out.
         */
        fun of(day: TibetanDay): DaySummary {
            fun entry(name: String, factor: DayFactor, tone: Tone, reading: Reading?) =
                SummaryEntry(name, factor.english, tone, reading, latin = true)
            fun lucky(b: Boolean) = if (b) Tone.GOOD else Tone.BAD
            val pair = day.elementPair
            val mansion = Texts.MANSION.getValue(day.mansion)
            val animal = Texts.ELECTIONAL_ANIMAL.getValue(day.lunarDayAnimal)
            val trigram = Texts.ELECTIONAL_TRIGRAM.getValue(day.trigram)
            val great = Ranked(
                entry(day.greatCombination.english.replaceFirstChar(Char::uppercase), DayFactor.GREAT_COMBINATION, lucky(day.greatCombination.lucky), Texts.GREAT_COMBINATION[day.greatCombination]),
                listOfNotNull(Texts.GREAT_COMBINATION[day.greatCombination]), tier = 0,
            )
            val elements = Ranked(
                entry("${day.weekday.element.english} – ${day.mansion.element.english}", DayFactor.ELEMENT_PAIR, lucky(pair.auspicious), Texts.ELEMENT_PAIR[pair]),
                listOfNotNull(Texts.ELEMENT_PAIR[pair]), tier = 0,
            )
            val special = day.combinationDays.map {
                Ranked(entry(it.english.replaceFirstChar(Char::uppercase), DayFactor.COMBINATION_DAY, lucky(it.lucky), Texts.COMBINATION_DAY[it]), listOfNotNull(Texts.COMBINATION_DAY[it]), tier = 4)
            }
            val planetAndMansion = listOf(
                Ranked(
                    entry(day.weekday.english, DayFactor.WEEKDAY, Texts.weekdayTone(day.weekday), Texts.WEEKDAY[day.weekday]),
                    listOfNotNull(Texts.WEEKDAY[day.weekday], Texts.ELECTIONAL_WEEKDAY.getValue(day.weekday)), tier = 2,
                ),
                Ranked(entry(day.mansion.sanskrit, DayFactor.MANSION, toneOf(mansion), mansion), listOf(mansion), tier = 3),
            )
            val single = listOf(
                Ranked(
                    entry(Catalog.format("ElectionalFactor.LUNAR_DATE.title", day.day.toString()), DayFactor.LUNAR_DATE, Texts.lunarDateTone(day.day), Texts.LUNAR_DATE[day.day - 1]),
                    listOf(Texts.LUNAR_DATE[day.day - 1], Texts.ELECTIONAL_DATE.getValue(day.day)), tier = 5,
                ),
                Ranked(entry(day.karana.sanskrit, DayFactor.KARANA, Texts.KARANA_TONE.getValue(day.karana), Texts.KARANA[day.karana]), listOfNotNull(Texts.KARANA[day.karana]), tier = 6),
                Ranked(entry(day.yoga.sanskrit, DayFactor.YOGA, Texts.YOGA_TONE.getValue(day.yoga), Texts.YOGA[day.yoga]), listOfNotNull(Texts.YOGA[day.yoga]), tier = 7),
                Ranked(entry(gloss(day.lunarDayAnimal), DayFactor.DAY_ANIMAL, toneOf(animal), animal), listOf(animal), tier = 8),
                Ranked(entry(day.trigram.wylie.replaceFirstChar(Char::uppercase), DayFactor.TRIGRAM, toneOf(trigram), trigram), listOf(trigram), tier = 9),
            )
            val rahu = Texts.RAHU[day.day]?.let {
                // Rāhu is reckoned by direction; it decides activities but takes no side on the day's tone.
                listOf(Ranked(entry(Catalog.text("DayFactor.RAHU"), DayFactor.RAHU, Tone.NEUTRAL, it), listOf(it), tier = 1))
            }.orEmpty()
            val ranked = listOf(great, elements) + rahu + planetAndMansion + special + single

            fun toned(fs: List<Ranked>) = fs.map { it.entry.tone }.filter { it == Tone.GOOD || it == Tone.BAD }
            val specialTones = toned(special).distinct()
            val combinationTones = toned(listOf(great, elements)).distinct()
            val verdict = when {
                combinationTones.size == 1 -> DayVerdict(combinationTones.single(), VerdictBy.COMBINATION)
                specialTones.size == 1 -> DayVerdict(specialTones.single(), VerdictBy.COMBINATION_DAY)
                else -> {
                    val sides = toned(ranked)
                    val good = sides.count { it == Tone.GOOD }
                    val bad = sides.count { it == Tone.BAD }
                    when {
                        good > bad -> DayVerdict(Tone.GOOD, VerdictBy.SIDES)
                        bad > good -> DayVerdict(Tone.BAD, VerdictBy.SIDES)
                        else -> DayVerdict(ranked.first { it.entry.tone == Tone.GOOD || it.entry.tone == Tone.BAD }.entry.tone, VerdictBy.STRONGEST)
                    }
                }
            }

            val order = LinkedHashSet<Activity>()
            ranked.forEach { order += it.good + it.avoid }
            val activities = order.mapNotNull { a ->
                // The strongest tier that names the activity with one voice decides; a split tier is passed over.
                val deciding = ranked.groupBy { it.tier }.toSortedMap().values.firstOrNull { tier ->
                    val named = tier.filter { a in it.good || a in it.avoid }
                    named.isNotEmpty() && (named.all { a in it.good } || named.all { a in it.avoid })
                } ?: return@mapNotNull null
                val good = deciding.any { a in it.good }
                val side = ranked.filter { it.tier >= deciding.first().tier && if (good) a in it.good else a in it.avoid }.map { it.entry }
                ActivityNote(a, good = if (good) side else emptyList(), avoid = if (good) emptyList() else side)
            }
            return DaySummary(
                mark = null,
                byTone = ranked.map { it.entry }.filter { it.tone == verdict.tone }.groupBy { it.tone },
                activities = activities,
                personal = emptyList(),
                affinity = null,
                verdict = verdict,
            )
        }

        /** A factor in its tier (0 strongest) with the activities its lists name good and to avoid; those it names both ways it is silent on. */
        private class Ranked(val entry: SummaryEntry, readings: List<Reading>, val tier: Int) {
            private val named = Activities.of(readings.flatMap { it.goodKeys }) to Activities.of(readings.flatMap { it.avoidKeys })
            val good: Set<Activity> = named.first - named.second
            val avoid: Set<Activity> = named.second - named.first
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
