/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.SystemClock
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import io.github.iverlein.zanshin.R
import kotlinx.coroutines.delay
import zanshin.app.LocalLabels
import zanshin.app.bell.BellPlayer
import zanshin.app.bell.BellSettings
import zanshin.app.bell.BellSound
import zanshin.app.bell.MeditationService
import zanshin.app.bell.MeditationSession
import zanshin.app.bell.MindfulnessBell
import zanshin.app.bell.Sitting
import java.time.DayOfWeek
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.TextStyle
import java.time.temporal.WeekFields
import java.util.Locale

/** The lengths of a sitting offered, in minutes. */
private val SIT_MINUTES = listOf(1, 2, 3, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 60, 75, 90, 120)
private val PREPARE_SECONDS = listOf(0, 5, 10, 15, 30, 60)
private val SIT_INTERVALS = listOf(0, 5, 10, 15, 20, 30)
private val BELL_INTERVALS = listOf(15, 20, 30, 45, 60, 90, 120)
private val SOUNDS = listOf(BellSound.BOWL to R.string.sound_bowl, BellSound.SMALL_BOWL to R.string.sound_small_bowl, BellSound.BELL to R.string.sound_bell)

/** The active hours move by half an hour. */
private const val HOUR_STEP = 30L

/**
 * The meditation timer and the mindfulness bell (SPEC §10.9), a screen of
 * their own from the menu: the dial and its settings, the bell through the
 * day, and the sound both ring with.
 */
@Composable
fun MeditationScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    val settings = remember { BellSettings(context) }
    val sitting by MeditationSession.state.collectAsState()
    val notifications = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { MeditationService.start(context) }

    fun begin() {
        if (Build.VERSION.SDK_INT >= 33 &&
            ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED
        ) {
            // The countdown and its Stop button live in the notification; the timer runs without it too.
            notifications.launch(Manifest.permission.POST_NOTIFICATIONS)
        } else {
            MeditationService.start(context)
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(Palette.ink)
            .windowInsetsPadding(WindowInsets.safeDrawing),
    ) {
        Row(Modifier.fillMaxWidth().height(64.dp).padding(start = 6.dp, top = 8.dp), verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onBack) { Icon(Icons.ChevronLeft, contentDescription = stringResource(R.string.back), tint = Palette.text) }
            Text(stringResource(R.string.meditation_title), style = body.copy(fontSize = 18.sp, fontWeight = FontWeight.SemiBold))
        }
        Column(
            Modifier.verticalScroll(rememberScrollState()).padding(bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            val current = sitting
            if (current != null) {
                Running(current, onStop = { MeditationService.stop(context) })
            } else {
                Timer(settings, onBegin = ::begin)
                Separator()
                PeriodicBell(settings)
                Separator()
                Sound(settings)
            }
        }
    }
}

/** The dial of a sitting under way, counting down its phase, and the Stop button. */
@Composable
private fun Running(sitting: Sitting, onStop: () -> Unit) {
    var now by remember { mutableLongStateOf(SystemClock.elapsedRealtime()) }
    LaunchedEffect(sitting) {
        while (true) {
            now = SystemClock.elapsedRealtime()
            delay(200)
        }
    }
    val phase = sitting.phase(now)
    val (whole, label) = when (phase) {
        Sitting.Phase.PREPARING -> sitting.prepareMillis to R.string.meditation_preparing
        Sitting.Phase.SITTING -> sitting.sitMillis to R.string.meditation_sitting
        Sitting.Phase.ENDED -> sitting.sitMillis to R.string.meditation_ended
    }
    val remaining = sitting.remaining(now)
    Column(Modifier.fillMaxWidth().padding(top = 24.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(28.dp)) {
        Dial(
            fraction = if (whole > 0) remaining.toFloat() / whole else 0f,
            time = clock(remaining),
            label = stringResource(label),
            faint = phase != Sitting.Phase.SITTING,
        )
        Pill(stringResource(R.string.meditation_stop), Palette.vermilion, onStop)
    }
}

/** The timer's settings under its dial, which shows the sitting's length, and the Begin button. */
@Composable
private fun Timer(settings: BellSettings, onBegin: () -> Unit) {
    var minutes by remember { mutableIntStateOf(settings.sitMinutes) }
    var prepare by remember { mutableIntStateOf(settings.prepareSeconds) }
    var interval by remember { mutableIntStateOf(settings.sitIntervalMinutes) }
    var strikes by remember { mutableIntStateOf(settings.endStrikes) }
    Column(Modifier.fillMaxWidth().padding(top = 16.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(24.dp)) {
        Dial(fraction = 1f, time = clock(minutes * 60_000L), label = stringResource(R.string.meditation_ready), faint = true)
        Pill(stringResource(R.string.meditation_begin), Palette.saffron, onBegin)
    }
    Column(Modifier.padding(horizontal = 24.dp, vertical = 16.dp), verticalArrangement = Arrangement.spacedBy(18.dp)) {
        val i = SIT_MINUTES.indexOf(minutes).takeIf { it >= 0 } ?: SIT_MINUTES.indexOf(20)
        Stepper(
            stringResource(R.string.meditation_length),
            stringResource(R.string.minutes, minutes),
            onMinus = if (i > 0) ({ minutes = SIT_MINUTES[i - 1]; settings.sitMinutes = minutes }) else null,
            onPlus = if (i < SIT_MINUTES.lastIndex) ({ minutes = SIT_MINUTES[i + 1]; settings.sitMinutes = minutes }) else null,
        )
        Choice(
            stringResource(R.string.meditation_prepare),
            PREPARE_SECONDS.map { it to if (it == 0) stringResource(R.string.none) else seconds(it) },
            prepare,
        ) { prepare = it; settings.prepareSeconds = it }
        Choice(
            stringResource(R.string.meditation_interval),
            SIT_INTERVALS.map { it to if (it == 0) stringResource(R.string.none) else stringResource(R.string.minutes, it) },
            interval,
        ) { interval = it; settings.sitIntervalMinutes = it }
        Choice(
            stringResource(R.string.meditation_end),
            listOf(1 to stringResource(R.string.meditation_end_one), 3 to stringResource(R.string.meditation_end_three)),
            strikes,
        ) { strikes = it; settings.endStrikes = it }
    }
}

/** The mindfulness bell: on or off with when it rings next, and when on its interval, hours and days. */
@Composable
private fun PeriodicBell(settings: BellSettings) {
    val context = LocalContext.current
    var on by remember { mutableStateOf(settings.periodic) }
    var minutes by remember { mutableIntStateOf(settings.periodicMinutes) }
    var random by remember { mutableStateOf(settings.periodicRandom) }
    var from by remember { mutableStateOf(settings.from) }
    var to by remember { mutableStateOf(settings.to) }
    var days by remember { mutableStateOf(settings.days) }
    var mute by remember { mutableStateOf(settings.muteWithPhone) }
    var next by remember { mutableLongStateOf(settings.nextBell) }

    fun reschedule() {
        MindfulnessBell.schedule(context)
        next = settings.nextBell
    }
    // A ring sets the next bell while the screen is away.
    LifecycleEventEffect(Lifecycle.Event.ON_RESUME) { next = settings.nextBell }

    val labels = LocalLabels.current
    val subtitle = when {
        !on -> stringResource(R.string.bell_off)
        next == 0L -> stringResource(R.string.bell_none)
        else -> {
            val at = Instant.ofEpochMilli(next).atZone(ZoneId.systemDefault())
            if (at.toLocalDate() == LocalDate.now()) {
                stringResource(R.string.bell_next_today, at.format(labels.clock))
            } else {
                stringResource(R.string.bell_next_day, at.dayOfWeek.getDisplayName(TextStyle.FULL_STANDALONE, labels.locale), at.format(labels.clock))
            }
        }
    }
    SectionLabel(stringResource(R.string.bell_section))
    Toggle(stringResource(R.string.bell_title), subtitle, on) {
        on = it
        settings.periodic = it
        reschedule()
    }
    if (!on) return
    Column(Modifier.padding(horizontal = 24.dp, vertical = 12.dp)) {
        Choice(stringResource(R.string.bell_every), BELL_INTERVALS.map { it to stringResource(R.string.minutes, it) }, minutes) {
            minutes = it
            settings.periodicMinutes = it
            reschedule()
        }
    }
    Toggle(stringResource(R.string.bell_random), stringResource(R.string.bell_random_subtitle), random) {
        random = it
        settings.periodicRandom = it
        reschedule()
    }
    Column(Modifier.padding(horizontal = 24.dp, vertical = 12.dp), verticalArrangement = Arrangement.spacedBy(18.dp)) {
        Stepper(
            stringResource(R.string.bell_from),
            from.format(labels.clock),
            onMinus = if (from >= LocalTime.of(0, HOUR_STEP.toInt())) ({ from = from.minusMinutes(HOUR_STEP); settings.from = from; reschedule() }) else null,
            onPlus = if (from.plusMinutes(HOUR_STEP) < to) ({ from = from.plusMinutes(HOUR_STEP); settings.from = from; reschedule() }) else null,
        )
        Stepper(
            stringResource(R.string.bell_to),
            to.format(labels.clock),
            onMinus = if (to.minusMinutes(HOUR_STEP) > from) ({ to = to.minusMinutes(HOUR_STEP); settings.to = to; reschedule() }) else null,
            onPlus = if (to < LocalTime.of(23, 30)) ({ to = to.plusMinutes(HOUR_STEP); settings.to = to; reschedule() }) else null,
        )
        Days(days) {
            days = it
            settings.days = it
            reschedule()
        }
    }
    Toggle(stringResource(R.string.bell_mute), stringResource(R.string.bell_mute_subtitle), mute) {
        mute = it
        settings.muteWithPhone = it
    }
}

/** The sound both ring with and its volume; a choice and the Listen button ring it once. */
@Composable
private fun Sound(settings: BellSettings) {
    val context = LocalContext.current
    var sound by remember { mutableStateOf(settings.sound) }
    var volume by remember { mutableFloatStateOf(settings.volume) }
    fun listen() = BellPlayer.ring(context, sound, 1, volume)
    SectionLabel(stringResource(R.string.sound_section))
    Column(Modifier.padding(horizontal = 24.dp, vertical = 8.dp), verticalArrangement = Arrangement.spacedBy(18.dp)) {
        Choice(null, SOUNDS.map { (s, name) -> s to stringResource(name) }, sound) {
            sound = it
            settings.sound = it
            listen()
        }
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(stringResource(R.string.sound_volume), style = label)
            Slider(
                value = volume,
                onValueChange = { volume = it },
                onValueChangeFinished = { settings.volume = volume },
                valueRange = 0.05f..1f,
                colors = SliderDefaults.colors(
                    thumbColor = Palette.saffron,
                    activeTrackColor = Palette.saffron,
                    inactiveTrackColor = Palette.raised,
                ),
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Pill(stringResource(R.string.sound_listen), Palette.saffron, ::listen)
            Text(stringResource(R.string.sound_note), style = body.copy(fontSize = 13.sp, lineHeight = 19.sp, color = Palette.faint), modifier = Modifier.weight(1f))
        }
    }
}

/** A ring that empties as the phase runs, the time left in its middle and what runs under it. */
@Composable
private fun Dial(fraction: Float, time: String, label: String, faint: Boolean) {
    Box(Modifier.size(240.dp), contentAlignment = Alignment.Center) {
        Canvas(Modifier.fillMaxSize()) {
            val stroke = 3.dp.toPx()
            val inset = stroke / 2
            val arc = Size(size.width - stroke, size.height - stroke)
            drawArc(Palette.line, 0f, 360f, false, Offset(inset, inset), arc, style = Stroke(stroke))
            drawArc(
                if (faint) Palette.muted else Palette.saffron,
                -90f,
                360f * fraction.coerceIn(0f, 1f),
                false,
                Offset(inset, inset),
                arc,
                style = Stroke(stroke, cap = StrokeCap.Round),
            )
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(time, style = body.copy(fontFamily = Mincho, fontSize = 56.sp, fontFeatureSettings = "tnum").tight())
            Text(label, style = body.copy(fontSize = 14.sp, color = Palette.muted))
        }
    }
}

/** An outlined pill button in [accent]. */
@Composable
private fun Pill(text: String, accent: androidx.compose.ui.graphics.Color, onClick: () -> Unit) {
    Text(
        text,
        style = body.copy(fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = accent),
        modifier = Modifier
            .heightIn(min = 48.dp)
            .border(1.dp, accent, RoundedCornerShape(24.dp))
            .clickable(role = Role.Button, onClick = onClick)
            .padding(horizontal = 28.dp, vertical = 13.dp),
    )
}

private val label get() = body.copy(fontSize = 14.sp, color = Palette.muted)

/** A value between − and + buttons; a null action greys its button out. */
@Composable
private fun Stepper(title: String, value: String, onMinus: (() -> Unit)?, onPlus: (() -> Unit)?) {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Text(title, style = label, modifier = Modifier.weight(1f))
        StepButton("−", onMinus)
        Text(
            value,
            style = body.copy(fontSize = 17.sp, fontWeight = FontWeight.SemiBold, fontFeatureSettings = "tnum"),
            modifier = Modifier.padding(horizontal = 14.dp),
        )
        StepButton("+", onPlus)
    }
}

@Composable
private fun StepButton(sign: String, onClick: (() -> Unit)?) {
    val color = if (onClick != null) Palette.text else Palette.off
    Box(
        Modifier
            .size(44.dp)
            .border(1.dp, if (onClick != null) Palette.lineStrong else Palette.line, CircleShape)
            .clickable(enabled = onClick != null, role = Role.Button) { onClick?.invoke() },
        contentAlignment = Alignment.Center,
    ) {
        Text(sign, style = body.copy(fontSize = 20.sp, color = color))
    }
}

/** A row of chips, one of them chosen, under an optional [title]. */
@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun <T> Choice(title: String?, options: List<Pair<T, String>>, selected: T, onSelect: (T) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        if (title != null) Text(title, style = label)
        FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            for ((value, text) in options) {
                val on = value == selected
                Text(
                    text,
                    style = body.copy(fontSize = 14.sp, fontWeight = if (on) FontWeight.SemiBold else FontWeight.Normal, color = if (on) Palette.saffron else Palette.muted),
                    modifier = Modifier
                        .heightIn(min = 40.dp)
                        .border(1.dp, if (on) Palette.saffron else Palette.lineStrong, RoundedCornerShape(20.dp))
                        .clickable(role = Role.RadioButton) { onSelect(value) }
                        .padding(horizontal = 14.dp, vertical = 10.dp),
                )
            }
        }
    }
}

/** The days of the week, from the language's first, each a round toggle. */
@Composable
private fun Days(days: Set<DayOfWeek>, onChange: (Set<DayOfWeek>) -> Unit) {
    val locale = LocalLabels.current.locale
    val first = WeekFields.of(locale).firstDayOfWeek
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(stringResource(R.string.bell_days), style = label)
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            for (k in 0L until 7L) {
                val day = first.plus(k)
                val on = day in days
                val name = day.getDisplayName(TextStyle.FULL_STANDALONE, locale)
                Box(
                    Modifier
                        .size(40.dp)
                        .background(if (on) Palette.selected else Palette.ink, CircleShape)
                        .border(1.dp, if (on) Palette.saffron else Palette.lineStrong, CircleShape)
                        .toggleable(value = on, role = Role.Checkbox) { onChange(if (it) days + day else days - day) }
                        .semantics { contentDescription = name },
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        day.getDisplayName(TextStyle.SHORT_STANDALONE, locale).take(2),
                        style = body.copy(fontSize = 13.sp, fontWeight = if (on) FontWeight.SemiBold else FontWeight.Normal, color = if (on) Palette.saffron else Palette.muted),
                    )
                }
            }
        }
    }
}

/** A title and subtitle with a switch, as in the menu. */
@Composable
private fun Toggle(title: String, subtitle: String, checked: Boolean, onChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 64.dp)
            .toggleable(value = checked, role = Role.Switch, onValueChange = onChange)
            .padding(horizontal = 24.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        Column(Modifier.weight(1f)) {
            Text(title, style = body.copy(fontSize = 16.sp))
            Text(subtitle, style = body.copy(fontSize = 13.sp, color = Palette.muted))
        }
        Switch(
            checked = checked,
            onCheckedChange = null,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Palette.ink,
                checkedTrackColor = Palette.saffron,
                uncheckedThumbColor = Palette.muted,
                uncheckedTrackColor = Palette.raised,
                uncheckedBorderColor = Palette.lineStrong,
            ),
        )
    }
}

@Composable
private fun SectionLabel(text: String) {
    Text(
        text,
        style = body.copy(fontSize = 12.sp, fontWeight = FontWeight.SemiBold, letterSpacing = 1.2.sp, color = Palette.faint),
        modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 8.dp, bottom = 4.dp),
    )
}

@Composable
private fun Separator() {
    Box(Modifier.padding(horizontal = 24.dp, vertical = 16.dp).fillMaxWidth().height(1.dp).background(Palette.line))
}

@Composable
private fun seconds(n: Int): String = if (n % 60 == 0) stringResource(R.string.minutes, n / 60) else stringResource(R.string.seconds, n)

/** Milliseconds as m:ss, or h:mm:ss from an hour, rounded up so that the dial reads 0:00 only at the end. */
private fun clock(millis: Long): String {
    val total = (millis + 999) / 1000
    val h = total / 3600
    val m = total / 60 % 60
    val s = total % 60
    return if (h > 0) String.format(Locale.ROOT, "%d:%02d:%02d", h, m, s) else String.format(Locale.ROOT, "%d:%02d", m, s)
}
