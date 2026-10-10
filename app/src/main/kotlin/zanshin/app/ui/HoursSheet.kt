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
import zanshin.core.texts.toneOf
import zanshin.core.tibetan.Ewts
import zanshin.core.tibetan.DayTimes
import zanshin.core.texts.Catalog
import zanshin.core.tibetan.SeasonReckoning
import zanshin.core.tibetan.Animal
import zanshin.core.tibetan.EarthLordCourses
import zanshin.core.tibetan.ChineseHours
import zanshin.core.astro.Place
import zanshin.core.texts.DaySummary
import zanshin.core.tibetan.Karana
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
 * pebbles of the hour's vitality and body against the birth year's; an arc
 * inside the rings marks Viṣṭi's span ([visti], SPEC §5.8). Tapping
 * an hour shows its rows, which open their readings through [onOpen]: among them the
 * hour's own readings (SPEC §5.13), with the rough times at [place] and the works they
 * turn against [summary]'s day animal. It opens at [initial], a two-hour period from
 * 05:00, or else at the present hour.
 */
/**
 * The present hour of the Tibetan day on [date] at [zone]: the two-hour period from 05:00 it falls
 * in, 0 to 11, and the minute of the clock day, both null when that day is not running now; kept
 * fresh every half minute while shown. The Tibetan day runs from 05:00 on its date to 05:00 the next
 * morning.
 */
@Composable
fun rememberCurrentHour(date: LocalDate, zone: ZoneId): Pair<Int?, Int?> {
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
    return current to (if (current != null) now.hour * 60 + now.minute else null)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HoursSheet(
    date: LocalDate,
    day: TibetanDay,
    birth: Sign?,
    signs: DaySigns,
    zone: ZoneId,
    summary: DaySummary,
    place: Place? = null,
    visti: DayTimes.Span? = null,
    initial: Int? = null,
    onOpen: (Annotation) -> Unit,
    onDismiss: () -> Unit,
) {
    val labels = LocalLabels.current
    val hours = remember(signs.date) { Forces.hours(signs.date) }
    val periods = remember(day.month) { (0 until 12).map { risingSign(day.month, it) } }
    val nectar = remember(day.weekday) { nectarHours(day.weekday) }
    val rough = remember(date, place) { place?.let { ChineseHours.roughTimes(date, it) }.orEmpty() }
    val (current, nowMinute) = rememberCurrentHour(date, zone)
    var selected by remember(date) { mutableIntStateOf(initial ?: current ?: 0) }

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
                    // Viṣṭi's span within the day, an unlucky arc inside the rings (WB vol. 1, p. 177).
                    visti?.let { v ->
                        val from = v.start.coerceAtLeast(0.0) * 24
                        val to = v.end.coerceAtMost(60.0) * 24
                        val radius = periodRadius - outerWidth / 2f - gap * 2.5f
                        drawArc(
                            color = toneColor(Tone.BAD),
                            startAngle = startAngle(5 * 60 + from.toInt()),
                            sweepAngle = ((to - from) / 1440 * 360).toFloat(),
                            useCenter = false,
                            topLeft = Offset(center.x - radius, center.y - radius),
                            size = Size(radius * 2, radius * 2),
                            style = Stroke(width = gap * 1.4f),
                        )
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
                // The period's general reading, without the works whose own hours put them on the other side (SPEC §5.13).
                val (periodTone, periodReading) = Texts.period(sign)
                val title = stringResource(R.string.hours_period_title, sign.english)
                val signDetail = stringResource(R.string.hours_period_sign) to Ewts.named(sign.english, sign.wylie)
                AnnotationRow(
                    Annotation(
                        title,
                        stringResource(if (periodTone == Tone.GOOD) R.string.hours_period_good else R.string.hours_period_bad),
                        periodTone,
                        periodReading,
                        titleIsKanji = false,
                        details = listOf(signDetail),
                    ),
                    onOpen,
                )
                // The works' own hours: what WB's chapter 34 names for this sign, each work's particular case.
                val works = Texts.WORKS_SIGN.getValue(sign)
                if (works.goodKeys.isNotEmpty() || works.avoidKeys.isNotEmpty()) {
                    AnnotationRow(
                        Annotation(
                            stringResource(R.string.hours_works_title),
                            stringResource(R.string.hours_works_subtitle, sign.english),
                            toneOf(works),
                            works,
                            titleIsKanji = false,
                            details = listOf(signDetail),
                        ),
                        onOpen,
                    )
                }
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
                            details = listOf(nectarTitle to Ewts.named(nectarTitle, "bdud rtsi thun mtshams")),
                        ),
                        onOpen,
                    )
                }
                // Viṣṭi in this hour, with its span (SPEC §5.8).
                visti?.takeIf { it.end * 24 > selected * 120 && it.start * 24 < selected * 120 + 120 }?.let { v ->
                    val vScript = Ewts.named(Karana.VISHTI.english, Karana.VISHTI.whiteBeryl)
                    AnnotationRow(
                        Annotation(
                            Karana.VISHTI.sanskrit,
                            labels.string(R.string.hours_visti_subtitle, clockOf(v.start, labels), clockOf(v.end, labels)),
                            Texts.KARANA_TONE.getValue(Karana.VISHTI),
                            Texts.KARANA[Karana.VISHTI],
                            titleIsKanji = false,
                            details = listOf(stringResource(R.string.row_tibetan) to vScript),
                        ),
                        onOpen,
                    )
                }
                // The earth lords of the hour on the hour's own place, and a black hour (WB vol. 2, pp. 235–236).
                val hourAnimal = hours[selected].sign.animal
                val black = EarthLordCourses.blackHour(day.lunarDayAnimal, hourAnimal)
                if (black) {
                    AnnotationRow(
                        Annotation(
                            stringResource(R.string.hours_black_title),
                            stringResource(R.string.hours_black_subtitle, gloss(day.lunarDayAnimal)),
                            Tone.BAD,
                            Texts.BLACK_HOUR,
                            titleIsKanji = false,
                            details = listOf(stringResource(R.string.row_tibetan) to Ewts.named(stringResource(R.string.hours_black_title), "dus tshod nag")),
                        ),
                        onOpen,
                    )
                }
                // The hour's own readings (WB vol. 2, p. 359): what it is for, a rough time in it, one's own year's hour.
                val worksTitle = stringResource(R.string.hours_works_hour_title)
                AnnotationRow(
                    Annotation(
                        worksTitle,
                        stringResource(R.string.hours_works_hour_subtitle),
                        Tone.GOOD,
                        Texts.HOUR_WORKS.getValue(hourAnimal),
                        titleIsKanji = false,
                        details = listOf(stringResource(R.string.row_tibetan) to Ewts.named(stringResource(R.string.hours_works_hour_subtitle), "rgya rtsis dus tshod bcu gnyis")),
                    ),
                    onOpen,
                )
                val roughHere = rough.filter { ChineseHours.hourOf(date, it.at) == selected }
                for (m in roughHere) {
                    AnnotationRow(
                        Annotation(
                            stringResource(R.string.hours_rough_title),
                            labels.string(R.string.hours_rough_subtitle, gloss(m.kind), m.at.format(labels.clock)),
                            Tone.BAD,
                            Texts.ROUGH_TIME,
                            titleIsKanji = false,
                            details = listOf(stringResource(R.string.row_tibetan) to Ewts.named(gloss(m.kind), m.kind.wylie)),
                        ),
                        onOpen,
                    )
                }
                val ownYear = birth != null && ChineseHours.ownYearHour(birth.animal, hourAnimal)
                if (ownYear) {
                    val ownTitle = stringResource(R.string.hours_own_year_title)
                    AnnotationRow(
                        Annotation(
                            ownTitle,
                            stringResource(R.string.hours_own_year_subtitle, gloss(birth!!.animal)),
                            Tone.BAD,
                            Texts.OWN_YEAR_HOUR,
                            titleIsKanji = false,
                            details = listOf(stringResource(R.string.row_tibetan) to Ewts.named(ownTitle, "rang nyid lo yi dus")),
                        ),
                        onOpen,
                    )
                }
                // KP's rule 2: the hour's own readings above the day's animal sign; the earth lords on their places stay out.
                val hourReadings = listOfNotNull(
                    Texts.HOUR_WORKS.getValue(hourAnimal),
                    Texts.BLACK_HOUR.takeIf { black },
                    Texts.ROUGH_TIME.takeIf { roughHere.isNotEmpty() },
                    Texts.OWN_YEAR_HOUR.takeIf { ownYear },
                )
                val turned = summary.overruledInHour(hourReadings)
                if (turned.isNotEmpty()) {
                    val good = turned.filter { it.second == Tone.GOOD }.map { it.first.activity.english }
                    val bad = turned.filter { it.second == Tone.BAD }.map { it.first.activity.english }
                    val goodLabel = stringResource(R.string.hours_over_day_good)
                    val badLabel = stringResource(R.string.hours_over_day_bad)
                    AnnotationRow(
                        Annotation(
                            stringResource(R.string.hours_over_day_title),
                            listOfNotNull(
                                good.takeIf { it.isNotEmpty() }?.let { "$goodLabel: ${it.joinToString()}" },
                                bad.takeIf { it.isNotEmpty() }?.let { "$badLabel: ${it.joinToString()}" },
                            ).joinToString(" · "),
                            if (bad.isEmpty()) Tone.GOOD else if (good.isEmpty()) Tone.BAD else Tone.NEUTRAL,
                            Texts.HOUR_OVER_DAY,
                            titleIsKanji = false,
                            details = listOfNotNull(
                                good.takeIf { it.isNotEmpty() }?.let { goodLabel to it.joinToString() },
                                bad.takeIf { it.isNotEmpty() }?.let { badLabel to it.joinToString() },
                                stringResource(R.string.row_tibetan) to labels.string(R.string.hours_over_day_sign, Ewts.named(stringResource(R.string.hours_over_day_sign_name), "nyi ma"), gloss(day.lunarDayAnimal)),
                            ),
                        ),
                        onOpen,
                    )
                }
                fun onPlace(a: Animal) = labels.string(R.string.hours_earth_lords_subtitle, gloss(a), gloss(a, "place"))
                AnnotationRow(
                    Annotation(
                        stringResource(R.string.hours_earth_lords_title),
                        onPlace(hourAnimal),
                        Tone.NEUTRAL,
                        Texts.HOUR_EARTH_LORDS,
                        titleIsKanji = false,
                        details = listOf(
                            stringResource(R.string.row_tibetan) to listOf(
                                "Yudzö Ngönmo" to "g.yu mdzod sngon mo", "Khangtsek" to "khang brtsegs", "Tsongön" to "mtsho sngon",
                            ).joinToString(" · ") { (n, w) -> Ewts.named(n, w) },
                        ),
                    ),
                    onOpen,
                )
                val blaMkhyenTitle = stringResource(R.string.hours_bla_mkhyen_title)
                AnnotationRow(
                    Annotation(
                        blaMkhyenTitle,
                        onPlace(EarthLordCourses.hourBlaMkhyen(hourAnimal)),
                        Tone.NEUTRAL,
                        Texts.HOUR_BLA_MKHYEN,
                        titleIsKanji = false,
                        details = listOf(stringResource(R.string.row_tibetan) to Ewts.named(blaMkhyenTitle, "dus tshod bla mkhyen")),
                    ),
                    onOpen,
                )
                val saRgyalTitle = stringResource(R.string.hours_sa_rgyal_title)
                AnnotationRow(
                    Annotation(
                        saRgyalTitle,
                        onPlace(EarthLordCourses.hourSaRgyal(hourAnimal)),
                        Tone.NEUTRAL,
                        Texts.HOUR_SA_RGYAL,
                        titleIsKanji = false,
                        details = listOf(stringResource(R.string.row_tibetan) to Ewts.named(saRgyalTitle, "dus tshod sa rgyal")),
                    ),
                    onOpen,
                )
                // The earth king the other way, Piling Parma's hour of the season-month (WB vol. 2, p. 236).
                EarthLordCourses.hourSaRgyalOther(SeasonReckoning.CHINESE.season(day.month), hourAnimal)?.let { place ->
                    val otherTitle = stringResource(R.string.hours_sa_rgyal_other_title)
                    AnnotationRow(
                        Annotation(
                            otherTitle,
                            onPlace(place),
                            Tone.NEUTRAL,
                            Texts.HOUR_SA_RGYAL_OTHER,
                            titleIsKanji = false,
                            details = listOf(stringResource(R.string.row_tibetan) to Ewts.named(stringResource(R.string.hours_sa_rgyal_title), "dus tshod sa rgyal")),
                        ),
                        onOpen,
                    )
                }
                // The hidden earth lord of the hour's animal on its place, and the sky dog over the places (WB vol. 2, pp. 221, 197, 236).
                val hiddenName = Catalog.text("reading.HourHidden.${hourAnimal.name}")
                AnnotationRow(
                    Annotation(
                        stringResource(R.string.hours_hidden_title),
                        labels.string(R.string.hours_hidden_subtitle, hiddenName, onPlace(hourAnimal)),
                        Tone.NEUTRAL,
                        Texts.HOUR_HIDDEN.getValue(hourAnimal),
                        titleIsKanji = false,
                        details = listOf(stringResource(R.string.row_tibetan) to Ewts.named(hiddenName, EarthLordCourses.HIDDEN_LORDS.getValue(hourAnimal))),
                    ),
                    onOpen,
                )
                val tail = Animal.entries[(hourAnimal.ordinal + 6) % 12]
                val dogTitle = stringResource(R.string.hours_gnam_khyi_title)
                AnnotationRow(
                    Annotation(
                        dogTitle,
                        labels.string(R.string.hours_gnam_khyi_subtitle, gloss(hourAnimal), gloss(tail)),
                        Tone.NEUTRAL,
                        Texts.HOUR_GNAM_KHYI,
                        titleIsKanji = false,
                        details = listOf(stringResource(R.string.row_tibetan) to Ewts.named(dogTitle, "dus tshod gnam khyi")),
                    ),
                    onOpen,
                )
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
