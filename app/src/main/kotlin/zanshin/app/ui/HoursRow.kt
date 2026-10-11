/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.CustomAccessibilityAction
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.customActions
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.iverlein.zanshin.R
import zanshin.app.LocalLabels
import zanshin.core.kyureki.Tone
import zanshin.core.texts.DaySummary
import zanshin.core.texts.HoursShortForm
import zanshin.core.texts.Texts
import zanshin.core.tibetan.HourSign
import zanshin.core.tibetan.TibetanDay
import zanshin.core.tibetan.risingSign

/**
 * The hours above the day (ROADMAP U6, SPEC §10.3): the combination period, the one factor the
 * texts put above every factor of the day (WB vol. 2, p. 376), as a row before the day in brief. A
 * strip of the twelve two-hour periods from 05:00, each coloured by the White Beryl's verdict on the
 * sign rising in it, with the present moment marked on today's page; under it, today, the present
 * hour with its sign and verdict, and the periods that run against the day's tone
 * ([DaySummary.hoursAgainst]) on one line in short form ([HoursShortForm], ROADMAP U11), "hours to
 * avoid 05–09 · 11–13". A tap on a run opens the hours panel at its hour through [onHour], a tap
 * elsewhere on the row, the strip included, at the present one. Nothing is weighed anew. Screen
 * readers get the present hour and every run's full times, with an action for each.
 */
@Composable
fun HoursRow(summary: DaySummary, day: TibetanDay, hourSigns: List<HourSign>, current: Int?, nowMinute: Int?, onHour: (Int?) -> Unit) {
    val hours = summary.hours ?: return
    val labels = LocalLabels.current
    val periods = remember(day.month) { (0 until 12).map { risingSign(day.month, it) } }
    val tones = remember(periods) { periods.map { Texts.DUS_SBYOR.getValue(it).first } }
    val label = stringResource(R.string.hours_row_label)
    val openHour = stringResource(R.string.hours_open)
    val nowText = current?.let { i ->
        val sign = periods[i]
        stringResource(
            R.string.hours_row_now,
            hourName(hourSigns[i], labels),
            hourSpan(hourSigns[i]),
            sign.english,
            stringResource(if (tones[i] == Tone.GOOD) R.string.hours_period_good else R.string.hours_period_bad),
        )
    }
    val against = summary.hoursAgainst.map { HoursShortForm.span(it) to it.first }
    // Every run's times for screen readers, each with an action that opens its hour.
    val runs = hours.periods.map { clockSpan(5 * 60 + it.first * 120, it.count * 120) to it }
    val runLabels = mapOf(Tone.GOOD to stringResource(R.string.brief_hours_good), Tone.BAD to stringResource(R.string.brief_hours_bad))
    val spoken = label + ": " + (nowText?.let { "$it; " } ?: "") + runLabels.entries.joinToString("; ") { (tone, name) ->
        name + " " + runs.filter { it.second.tone == tone }.joinToString { it.first }
    }
    Row(
        Modifier
            .fillMaxWidth()
            .heightIn(min = 44.dp)
            .clickable(role = Role.Button, onClickLabel = openHour) { onHour(current) }
            .clearAndSetSemantics {
                contentDescription = spoken
                customActions = runs.map { (time, run) -> CustomAccessibilityAction("$openHour, $time") { onHour(run.first); true } }
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                Text(label, style = body.copy(fontSize = 14.sp, fontWeight = FontWeight.SemiBold))
                HourStrip(tones, nowMinute, Modifier.weight(1f))
            }
            nowText?.let {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    ToneDot(tones[current!!])
                    Text(it, style = body.copy(fontSize = 13.sp, lineHeight = 18.sp, color = Palette.muted))
                }
            }
            // The hours against the day's tone in short form (ROADMAP U11), one line: on an unlucky day those to be
            // accomplished, on a lucky day those to avoid, under the In brief line's words. The full times are in the
            // brief's "By the hour" and in what a screen reader hears.
            if (against.isNotEmpty()) {
                val tone = if (summary.verdict!!.tone == Tone.GOOD) Tone.BAD else Tone.GOOD
                ShortHours(stringResource(if (tone == Tone.BAD) R.string.brief_avoid else R.string.brief_good), toneColor(tone), against, openHour, onHour)
            }
        }
        Icon(Icons.ChevronRight, contentDescription = null, tint = Palette.faint, modifier = Modifier.size(18.dp))
    }
}

/**
 * The runs of [against], each its short span and the period it begins, after [label] in [colour], on one line
 * (ROADMAP U11): "avoid 05–09 · 11–13 · 17–19 · 23–01", each span a tap that opens its hour. Four runs fit at
 * font scale 1.3 in English and Russian; a day with five, which some months have, is set smaller rather than
 * broken over two lines.
 */
@Composable
private fun ShortHours(label: String, colour: Color, against: List<Pair<String, Int>>, openHour: String, onHour: (Int?) -> Unit) {
    val measurer = rememberTextMeasurer()
    val labelStyle = body.copy(fontSize = 13.sp, color = colour)
    val spanStyle = body.copy(fontSize = 14.sp)
    val sepStyle = body.copy(fontSize = 13.sp, color = Palette.faint)
    val gap = 8.dp
    BoxWithConstraints(Modifier.fillMaxWidth()) {
        val density = LocalDensity.current
        val natural = remember(label, against, density) {
            fun w(text: String, style: TextStyle) = measurer.measure(text, style, maxLines = 1, softWrap = false).size.width
            w(label, labelStyle) + with(density) { gap.toPx() } +
                against.sumOf { w(it.first, spanStyle) } + (against.size - 1) * w(HoursShortForm.SEPARATOR, sepStyle)
        }
        val available = with(density) { maxWidth.toPx() }
        val k = if (natural > available) available / natural * 0.98f else 1f
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(label, style = labelStyle.copy(fontSize = labelStyle.fontSize * k), maxLines = 1, softWrap = false)
            Spacer(Modifier.width(gap * k))
            against.forEachIndexed { i, (span, hour) ->
                if (i > 0) Text(HoursShortForm.SEPARATOR, style = sepStyle.copy(fontSize = sepStyle.fontSize * k), maxLines = 1, softWrap = false)
                Text(
                    span,
                    style = spanStyle.copy(fontSize = spanStyle.fontSize * k),
                    maxLines = 1,
                    softWrap = false,
                    modifier = Modifier.clickable(role = Role.Button, onClickLabel = openHour) { onHour(hour) }.padding(vertical = 4.dp),
                )
            }
        }
    }
}

/**
 * The twelve two-hour periods from 05:00 as a strip, each coloured by its combination period's tone,
 * the clock hour under every third, and the present minute as a mark. A tap on it is the row's, which
 * opens the panel at the present hour: a tap by period opened whatever hour the finger fell on.
 */
@Composable
private fun HourStrip(tones: List<Tone>, nowMinute: Int?, modifier: Modifier = Modifier) {
    val measurer = rememberTextMeasurer()
    val labelStyle = TextStyle(fontFamily = Figtree, fontSize = 10.sp, color = Palette.faint)
    val description = stringResource(R.string.hours_strip_description)
    Canvas(
        modifier
            .height(26.dp)
            .clearAndSetSemantics { contentDescription = description },
    ) {
        val gap = 2.dp.toPx()
        val bar = 10.dp.toPx()
        val cell = (size.width - gap * 11) / 12
        for (i in 0 until 12) {
            drawRoundRect(
                color = toneColor(tones[i]).copy(alpha = 0.8f),
                topLeft = Offset(i * (cell + gap), 0f),
                size = Size(cell, bar),
                cornerRadius = CornerRadius(2.dp.toPx()),
            )
            if (i % 3 == 0) {
                val text = measurer.measure("%02d".format((5 + i * 2) % 24), labelStyle)
                drawText(text, topLeft = Offset(i * (cell + gap), bar + 2.dp.toPx()))
            }
        }
        nowMinute?.let { m ->
            val x = ((m - 5 * 60 + 1440) % 1440) / 1440f * size.width
            drawLine(Palette.saffron, Offset(x, -2.dp.toPx()), Offset(x, bar + 2.dp.toPx()), strokeWidth = 2.dp.toPx())
        }
    }
}
