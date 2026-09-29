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

/** The app's lucky/unlucky dot for the rokuyō: an app convention, not a traditional mark. */
fun rokuyoTone(r: Rokuyo): Tone = when (r) {
    Rokuyo.TAIAN -> Tone.GOOD
    Rokuyo.BUTSUMETSU, Rokuyo.SHAKKO -> Tone.BAD
    else -> Tone.MIXED
}

/** Lucky, unlucky or mixed from what a reading recommends and forbids. */
fun toneOf(r: Reading?): Tone = when {
    r == null -> Tone.NEUTRAL
    r.avoid.isEmpty() && r.good.isNotEmpty() -> Tone.GOOD
    r.good.isEmpty() && r.avoid.isNotEmpty() -> Tone.BAD
    else -> Tone.MIXED
}

/** One annotation of the day as the summary lists it. */
data class SummaryEntry(val kanji: String, val english: String, val tone: Tone, val reading: Reading?)

/** An activity and the annotations that name it good or to be avoided; both sides are kept. */
data class ActivityNote(val activity: Activity, val good: List<SummaryEntry>, val avoid: List<SummaryEntry>) {
    val disputed: Boolean get() = good.isNotEmpty() && avoid.isNotEmpty()
}

/**
 * The day in brief (ROADMAP R3), built only from what the sources state or
 * what can be counted: no verdict, score or weighting. Annotations are grouped
 * by tone and activities listed with who names them; where annotations
 * disagree both sides stay.
 */
data class DaySummary(
    val mark: DayMark?,
    val byTone: Map<Tone, List<SummaryEntry>>,
    val activities: List<ActivityNote>,
    val personal: List<SummaryEntry>,
    val affinity: StarAffinity?,
) {
    val good: List<ActivityNote> get() = activities.filter { it.good.isNotEmpty() }
    val avoid: List<ActivityNote> get() = activities.filter { it.avoid.isNotEmpty() }

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

            // In order of first mention, so the list follows the almanac's own order.
            val order = LinkedHashSet<Activity>()
            entries.forEach { e -> e.reading?.let { order += Activities.of(it.good) + Activities.of(it.avoid) } }
            val activities = order.map { a ->
                ActivityNote(
                    a,
                    good = entries.filter { e -> e.reading != null && a in Activities.of(e.reading.good) },
                    avoid = entries.filter { e -> e.reading != null && a in Activities.of(e.reading.avoid) },
                )
            }

            return DaySummary(
                mark = rk.mark,
                byTone = entries.filter { it.tone != Tone.NEUTRAL }.groupBy { it.tone },
                activities = activities,
                personal = entries.filter { e -> PERSONAL.any { it.kanji == e.kanji } },
                affinity = birthStar?.let { Kigaku.affinity(it, rk.dayStar) },
            )
        }
    }
}
