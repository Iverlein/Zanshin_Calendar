/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.iverlein.zanshin.R
import zanshin.app.LocalLabels
import kotlinx.coroutines.delay
import zanshin.core.tibetan.DaySigns
import zanshin.core.tibetan.Force
import zanshin.core.tibetan.ForceContrast
import zanshin.core.tibetan.Forces
import zanshin.core.tibetan.Sign
import zanshin.core.tibetan.TibetanDay
import zanshin.core.texts.gloss
import zanshin.core.kyureki.Tone
import zanshin.core.texts.Texts
import zanshin.core.tibetan.Ewts
import zanshin.core.tibetan.nectarHours
import zanshin.core.tibetan.risingSign
import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin

/**
 * The hours of the Tibetan day (SPEC §10.3): a 24-hour dial, midnight at the
 * top, with the twelve two-hour periods from the hare hour at 05:00. The
 * inner ring is the combination period, the sign rising in each hour (SPEC
 * §5.13), coloured by the White Beryl's verdict on it, with dots on the
 * nectar periods. With a birth date, two outer rings are coloured by the
 * pebbles of the hour's vitality and body against the birth year's. Tapping
 * an hour shows its rows, which open their readings through [onOpen]. It opens
 * at [initial], a two-hour period from 05:00, or else at the present hour.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HoursSheet(date: LocalDate, day: TibetanDay, birth: Sign?, signs: DaySigns, zone: ZoneId, initial: Int? = null, onOpen: (Annotation) -> Unit, onDismiss: () -> Unit) {
    val labels = LocalLabels.current
    val hours = remember(signs.date) { Forces.hours(signs.date) }
    val periods = remember(day.month) { (0 until 12).map { risingSign(day.month, it) } }
    val nectar = remember(day.weekday) { nectarHours(day.weekday) }
    // The Tibetan day of this page runs from 05:00 on its date to 05:00 the next morning.
    val dayStart = remember(date, zone) { date.atTime(LocalTime.of(5, 0)).atZone(zone) }
    var now by remember { mutableStateOf(ZonedDateTime.now(zone)) }
    LaunchedEffect(zone) {
        while (true) {
            delay(30_000)
            now = ZonedDateTime.now(zone)
        }
    }
    val sinceStart = Duration.between(dayStart, now).toMinutes()
    val current = if (sinceStart in 0 until 24 * 60) (sinceStart / 120).toInt() else null
    var selected by remember(date) { mutableIntStateOf(initial ?: current ?: 0) }
    val nowMinute = if (current != null) now.hour * 60 + now.minute else null

    fun tone(i: Int, force: Force) = pebbleTone(ForceContrast(force, birth!!.forces[force], hours[i].sign.forces[force]).pebbles)

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
        containerColor = Palette.surface,
        scrimColor = Palette.scrim,
    ) {
        Column(
            Modifier
                .verticalScroll(rememberScrollState())
                .padding(start = 24.dp, end = 24.dp, bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            Text(stringResource(R.string.hours_title), style = body.copy(fontSize = 22.sp, fontWeight = FontWeight.SemiBold))

            val measurer = rememberTextMeasurer()
            val labelStyle = TextStyle(fontFamily = Figtree, fontSize = 12.sp, color = Palette.muted)
            val selectedLabelStyle = labelStyle.copy(color = Palette.text, fontWeight = FontWeight.SemiBold)
            val animalNames = hours.map { gloss(it.sign.animal) }
            val dialDescription = stringResource(if (birth != null) R.string.hours_dial_description else R.string.hours_dial_description_period)
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Canvas(
                    Modifier
                        .widthIn(max = 340.dp)
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .semantics { contentDescription = dialDescription }
                        .pointerInput(hours) {
                            detectTapGestures { tap ->
                                val c = Offset(size.width / 2f, size.height / 2f)
                                val angle = atan2(tap.y - c.y, tap.x - c.x) // 0 at 3 o'clock, clockwise
                                val minute = (((angle + PI / 2) / (2 * PI) * 1440).toInt() + 1440) % 1440
                                selected = ((minute - 5 * 60 + 1440) % 1440) / 120
                            }
                        },
                ) {
                    val outerWidth = size.minDimension * if (birth != null) 0.08f else 0.10f
                    val innerWidth = outerWidth
                    val gap = size.minDimension * 0.012f
                    val labelBand = size.minDimension * 0.11f
                    val outerRadius = size.minDimension / 2f - labelBand - outerWidth / 2f
                    val innerRadius = outerRadius - outerWidth / 2f - gap - innerWidth / 2f
                    // The combination period: the innermost ring, or the only one without a birth date.
                    val periodRadius = if (birth != null) innerRadius - innerWidth - gap else outerRadius
                    val center = Offset(size.width / 2f, size.height / 2f)

                    fun startAngle(minute: Int) = minute / 1440f * 360f - 90f
                    fun ring(radius: Float, width: Float, i: Int, tone: Tone) {
                        val alpha = if (i == selected) 1f else 0.55f
                        drawArc(
                            color = toneColor(tone).copy(alpha = alpha),
                            startAngle = startAngle(hours[i].startMinute) + 0.8f,
                            sweepAngle = 30f - 1.6f,
                            useCenter = false,
                            topLeft = Offset(center.x - radius, center.y - radius),
                            size = Size(radius * 2, radius * 2),
                            style = Stroke(width = width),
                        )
                    }
                    for (i in hours.indices) {
                        if (birth != null) {
                            ring(outerRadius, outerWidth, i, tone(i, Force.VITALITY))
                            ring(innerRadius, innerWidth, i, tone(i, Force.BODY))
                        }
                        ring(periodRadius, outerWidth, i, Texts.DUS_SBYOR.getValue(periods[i]).first)
                        // The animal's name outside the rings, at the middle of its hour.
                        val mid = Math.toRadians((startAngle(hours[i].startMinute) + 15f).toDouble())
                        val r = outerRadius + outerWidth / 2f + labelBand / 2f
                        val text = measurer.measure(animalNames[i], if (i == selected) selectedLabelStyle else labelStyle)
                        drawText(
                            text,
                            topLeft = Offset(
                                center.x + (r * cos(mid)).toFloat() - text.size.width / 2f,
                                center.y + (r * sin(mid)).toFloat() - text.size.height / 2f,
                            ),
                        )
                    }
                    // The nectar periods: a dot on the combination ring at the middle of each clock hour,
                    // on the band itself, so that none covers the selected hour's time in the middle.
                    for (h in nectar) {
                        val a = Math.toRadians((startAngle((5 * 60 + h * 60 + 30) % 1440)).toDouble())
                        val dot = Offset(center.x + (periodRadius * cos(a)).toFloat(), center.y + (periodRadius * sin(a)).toFloat())
                        drawCircle(Palette.surface, radius = gap * 2.3f, center = dot)
                        drawCircle(Palette.saffron, radius = gap * 1.6f, center = dot)
                    }
                    // The present moment, on today's page.
                    nowMinute?.let { m ->
                        val a = Math.toRadians(startAngle(m).toDouble())
                        val tip = outerRadius + outerWidth / 2f
                        val base = periodRadius - outerWidth / 2f
                        drawLine(
                            Palette.saffron,
                            start = Offset(center.x + (base * cos(a)).toFloat(), center.y + (base * sin(a)).toFloat()),
                            end = Offset(center.x + (tip * cos(a)).toFloat(), center.y + (tip * sin(a)).toFloat()),
                            strokeWidth = 2.dp.toPx(),
                        )
                    }
                }
                // The selected hour in the middle of the dial.
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(hourName(hours[selected], labels), style = body.copy(fontSize = 16.sp, fontWeight = FontWeight.SemiBold))
                    Text(hourSpan(hours[selected]), style = body.copy(fontSize = 13.sp, color = Palette.muted))
                    if (selected == current) Text(stringResource(R.string.hours_now), style = body.copy(fontSize = 12.sp, color = Palette.saffron))
                }
            }

            // Stepping through the hours, also for screen readers.
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { selected = (selected + 11) % 12 }) {
                    Icon(Icons.ChevronRight, contentDescription = stringResource(R.string.hours_previous), tint = Palette.muted, modifier = Modifier.graphicsLayer(scaleX = -1f))
                }
                Text(
                    labels.string(R.string.element_animal, gloss(hours[selected].sign.element), gloss(hours[selected].sign.animal)),
                    style = body.copy(fontSize = 14.sp, color = Palette.muted),
                    modifier = Modifier.weight(1f),
                )
                IconButton(onClick = { selected = (selected + 1) % 12 }) {
                    Icon(Icons.ChevronRight, contentDescription = stringResource(R.string.hours_next), tint = Palette.muted)
                }
            }

            Column {
                val sign = periods[selected]
                val (periodTone, periodReading) = Texts.DUS_SBYOR.getValue(sign)
                val title = stringResource(R.string.hours_period_title, sign.english)
                AnnotationRow(
                    Annotation(
                        title,
                        stringResource(if (periodTone == Tone.GOOD) R.string.hours_period_good else R.string.hours_period_bad),
                        periodTone,
                        periodReading,
                        titleIsKanji = false,
                        details = listOf(stringResource(R.string.hours_period_sign) to "${Ewts.toTibetan(sign.wylie)} (${sign.wylie})"),
                    ),
                    onOpen,
                )
                // A nectar period falls in the first or second clock hour of a two-hour period.
                nectar.filter { it / 2 == selected }.forEach { h ->
                    val nectarTitle = stringResource(R.string.tib_nectar_title)
                    AnnotationRow(
                        Annotation(
                            nectarTitle,
                            "%02d:00–%02d:00".format((5 + h) % 24, (6 + h) % 24),
                            Tone.GOOD,
                            Texts.NECTAR_PERIODS,
                            titleIsKanji = false,
                            details = listOf(nectarTitle to "${Ewts.toTibetan("bdud rtsi thun mtshams")} (bdud rtsi thun mtshams)"),
                        ),
                        onOpen,
                    )
                }
                if (birth != null) {
                    for (force in listOf(Force.VITALITY, Force.BODY)) {
                        AnnotationRow(pebbleAnnotation(force, birth, signs, day, labels, hours[selected]), onOpen)
                    }
                }
            }
            Text(withTibetan(stringResource(if (birth != null) R.string.hours_note else R.string.hours_note_period)), style = body.copy(fontSize = 12.sp, color = Palette.faint, lineHeight = 17.sp))
        }
    }
}
