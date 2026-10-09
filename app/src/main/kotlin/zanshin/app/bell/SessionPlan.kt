/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.bell

/**
 * How a sitting runs (SPEC §10.9): a silent warm-up of [warmUpSeconds],
 * then [periods] (seconds each) one after the other, the session's bell
 * at the start, [between] struck [betweenStrikes] times where one period
 * gives way to the next, and the bell [endStrikes] times at the end.
 */
data class SessionPlan(
    val warmUpSeconds: Int = 10,
    val periods: List<Int> = listOf(20 * 60),
    val between: BellSound = BellSound.WOOD,
    val betweenStrikes: Int = 2,
    val endStrikes: Int = 3,
) {
    init {
        require(warmUpSeconds in 0..MAX_SECONDS)
        require(periods.size in 1..MAX_PERIODS && periods.all { it in 1..MAX_SECONDS })
        require(betweenStrikes in 1..3 && endStrikes in 1..3)
    }

    val totalSeconds: Int get() = periods.sum()

    /** The plan as one line of text, for the settings: warm-up;period,period…;between;strikes;end. */
    fun encode(): String = listOf(warmUpSeconds, periods.joinToString(","), between.name, betweenStrikes, endStrikes).joinToString(";")

    companion object {
        /** Any length up to 24 hours, a warm-up or a period. */
        const val MAX_SECONDS = 24 * 3600
        const val MAX_PERIODS = 12

        /** A plan read back from [encode]; null when the text is not one. */
        fun decode(text: String): SessionPlan? = runCatching {
            val f = text.split(";")
            SessionPlan(
                warmUpSeconds = f[0].toInt(),
                periods = f[1].split(",").map { it.toInt() },
                between = BellSound.valueOf(f[2]),
                betweenStrikes = f[3].toInt(),
                endStrikes = f[4].toInt(),
            )
        }.getOrNull()
    }
}

/** A plan saved under a name, to be started again (SPEC §10.9). */
data class Preset(val name: String, val plan: SessionPlan) {
    companion object {
        const val NAME_MAX = 40

        /** Presets one per line, the name, a tab, the plan; lines that do not read are dropped. */
        fun decodeAll(text: String?): List<Preset> = text.orEmpty().lines().mapNotNull { line ->
            val tab = line.indexOf('\t')
            if (tab <= 0) return@mapNotNull null
            SessionPlan.decode(line.substring(tab + 1))?.let { Preset(line.substring(0, tab), it) }
        }

        fun encodeAll(presets: List<Preset>): String = presets.joinToString("\n") { "${clean(it.name)}\t${it.plan.encode()}" }

        /** A name as it can be stored: one line, no tab, trimmed, at most [NAME_MAX] characters. */
        fun clean(name: String): String = name.replace(Regex("[\\t\\r\\n]+"), " ").trim().take(NAME_MAX)

        /** [presets] with [preset] in place of the one of its name, or added at the end. */
        fun put(presets: List<Preset>, preset: Preset): List<Preset> {
            val p = preset.copy(name = clean(preset.name))
            val i = presets.indexOfFirst { it.name == p.name }
            return if (i >= 0) presets.toMutableList().also { it[i] = p } else presets + p
        }
    }
}
