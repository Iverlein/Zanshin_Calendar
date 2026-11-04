/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.ui

import android.app.WallpaperManager
import android.content.ActivityNotFoundException
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.iverlein.zanshin.R
import zanshin.app.wheel.WheelForm
import zanshin.app.wheel.WheelGround
import zanshin.app.wheel.WheelLook
import zanshin.app.wheel.WheelMantra
import zanshin.app.wheel.WheelMotion
import zanshin.app.wheel.WheelPainter
import zanshin.app.wheel.WheelScript
import zanshin.app.wheel.WheelSettings
import zanshin.app.wheel.WheelWallpaper

private val MANTRA_NAMES = listOf(
    WheelMantra.MANI to R.string.wheel_mantra_mani,
    WheelMantra.VAJRA_GURU to R.string.wheel_mantra_vajra_guru,
    WheelMantra.TARA to R.string.wheel_mantra_tara,
    WheelMantra.MANJUSHRI to R.string.wheel_mantra_manjushri,
    WheelMantra.VAJRAPANI to R.string.wheel_mantra_vajrapani,
    WheelMantra.MEDICINE to R.string.wheel_mantra_medicine,
    WheelMantra.SHAKYAMUNI to R.string.wheel_mantra_shakyamuni,
    WheelMantra.OWN to R.string.wheel_mantra_own,
)
private val SCRIPT_NAMES = mapOf(
    WheelScript.GOLD to R.string.wheel_color_gold,
    WheelScript.WHITE to R.string.wheel_color_white,
    WheelScript.RED to R.string.wheel_color_red,
    WheelScript.GREEN to R.string.wheel_color_green,
    WheelScript.BLUE to R.string.wheel_color_blue,
    WheelScript.TURQUOISE to R.string.wheel_color_turquoise,
)
private val GROUND_NAMES = mapOf(
    WheelGround.BLACK to R.string.wheel_color_black,
    WheelGround.INK to R.string.wheel_color_ink,
    WheelGround.MAROON to R.string.wheel_color_maroon,
    WheelGround.INDIGO to R.string.wheel_color_indigo,
    WheelGround.FOREST to R.string.wheel_color_forest,
    WheelGround.UMBER to R.string.wheel_color_umber,
)

/**
 * The prayer wheel (SPEC §10.12), a screen of its own from the menu's Practice group: the wheel, turning, with the
 * mantra, its form and colours under it, or beside it where the screen is wider than tall, and the way to the wallpaper.
 */
@Composable
fun WheelScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    val settings = remember { WheelSettings(context) }
    var look by remember { mutableStateOf(settings.look) }
    Column(
        Modifier
            .fillMaxSize()
            .background(Palette.ink)
            .windowInsetsPadding(WindowInsets.safeDrawing),
    ) {
        Row(Modifier.fillMaxWidth().heightIn(min = 64.dp).padding(start = 6.dp, top = 8.dp), verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onBack) { Icon(Icons.ChevronLeft, contentDescription = stringResource(R.string.back), tint = Palette.text) }
            Text(stringResource(R.string.wheel_title), style = body.copy(fontSize = 18.sp, fontWeight = FontWeight.SemiBold))
        }
        BoxWithConstraints(Modifier.fillMaxSize()) {
            if (maxWidth > maxHeight) {
                Row(Modifier.fillMaxSize()) {
                    TurningWheel(look, Modifier.weight(1f).fillMaxHeight())
                    Options(settings, { look = settings.look }, Modifier.weight(1f).fillMaxHeight())
                }
            } else {
                Column(Modifier.fillMaxSize()) {
                    TurningWheel(look, Modifier.fillMaxWidth().weight(1f))
                    Options(settings, { look = settings.look }, Modifier.fillMaxWidth().weight(1f))
                }
            }
        }
    }
}

/** The wheel on its ground, turning while the screen is seen; a finger speeds or brakes it. */
@Composable
private fun TurningWheel(look: WheelLook, modifier: Modifier) {
    val context = LocalContext.current
    val painter = remember { WheelPainter(context) }
    val motion = remember { WheelMotion() }
    var frame by remember { mutableLongStateOf(0L) }
    DisposableEffect(painter) { onDispose { painter.release() } }
    LaunchedEffect(motion) {
        var last = 0L
        while (true) {
            withFrameNanos { now ->
                if (last != 0L) motion.step((now - last) / 1e9)
                last = now
                frame = now
            }
        }
    }
    val description = stringResource(R.string.desc_wheel)
    Canvas(
        modifier
            .clipToBounds()
            .semantics { contentDescription = description }
            .pointerInput(painter) {
                awaitEachGesture {
                    val down = awaitFirstDown(requireUnconsumed = false)
                    val tracker = VelocityTracker()
                    tracker.addPosition(down.uptimeMillis, down.position)
                    do {
                        val event = awaitPointerEvent()
                        val change = event.changes.firstOrNull { it.id == down.id } ?: break
                        tracker.addPosition(change.uptimeMillis, change.position)
                        val velocity = tracker.calculateVelocity()
                        motion.touch(painter.rate(change.position.x, change.position.y, velocity.x, velocity.y))
                        change.consume()
                    } while (change.pressed)
                }
            },
    ) {
        @Suppress("UNUSED_EXPRESSION") frame // read here, so that each frame draws anew
        drawIntoCanvas { painter.draw(it.nativeCanvas, size.width.toInt(), size.height.toInt(), motion.angle, look) }
    }
}

/** The mantra, the form, the two colours and the way to the wallpaper. */
@Composable
private fun Options(settings: WheelSettings, onChange: () -> Unit, modifier: Modifier) {
    val context = LocalContext.current
    var mantra by remember { mutableStateOf(settings.mantra) }
    var form by remember { mutableStateOf(settings.form) }
    var script by remember { mutableStateOf(settings.script) }
    var ground by remember { mutableStateOf(settings.ground) }
    var editingOwn by remember { mutableStateOf(false) }
    Column(
        modifier.verticalScroll(rememberScrollState()).padding(top = 12.dp, bottom = 32.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        SectionLabel(stringResource(R.string.wheel_section_mantra))
        Box(Modifier.padding(horizontal = 24.dp, vertical = 8.dp)) {
            Choice(null, MANTRA_NAMES.map { (m, name) -> m to stringResource(name) }, mantra) {
                if (it == WheelMantra.OWN) {
                    editingOwn = true
                } else {
                    mantra = it
                    settings.mantra = it
                    onChange()
                }
            }
        }
        Separator()
        SectionLabel(stringResource(R.string.wheel_section_look))
        Column(Modifier.padding(horizontal = 24.dp, vertical = 8.dp), verticalArrangement = Arrangement.spacedBy(18.dp)) {
            Choice(
                stringResource(R.string.wheel_form),
                listOf(WheelForm.DRUM to stringResource(R.string.wheel_form_drum), WheelForm.RING to stringResource(R.string.wheel_form_ring)),
                form,
            ) {
                form = it
                settings.form = it
                onChange()
            }
            Swatches(stringResource(R.string.wheel_script), WheelScript.entries, script, { it.argb }, { SCRIPT_NAMES.getValue(it) }) {
                script = it
                settings.script = it
                onChange()
            }
            Swatches(stringResource(R.string.wheel_ground), WheelGround.entries, ground, { it.argb }, { GROUND_NAMES.getValue(it) }) {
                ground = it
                settings.ground = it
                onChange()
            }
        }
        if (context.packageManager.hasSystemFeature(PackageManager.FEATURE_LIVE_WALLPAPER)) {
            Separator()
            Column(Modifier.padding(horizontal = 24.dp, vertical = 8.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Pill(stringResource(R.string.wheel_set_wallpaper), Palette.saffron) { openWallpaperPicker(context) }
                Text(stringResource(R.string.wheel_wallpaper_note), style = body.copy(fontSize = 13.sp, lineHeight = 19.sp, color = Palette.faint))
            }
        }
    }
    if (editingOwn) {
        MessageDialog(
            settings.ownLine,
            hint = stringResource(R.string.wheel_own_hint),
            default = "",
            title = stringResource(R.string.wheel_own_title),
            onSave = {
                settings.ownLine = it
                if (settings.ownLine.isNotEmpty()) {
                    mantra = WheelMantra.OWN
                    settings.mantra = WheelMantra.OWN
                } else if (mantra == WheelMantra.OWN) {
                    mantra = WheelMantra.MANI
                    settings.mantra = WheelMantra.MANI
                }
                onChange()
                editingOwn = false
            },
            onDismiss = { editingOwn = false },
        )
    }
}

/** A row of round colours under [title], the chosen one ringed. */
@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun <T> Swatches(title: String, options: List<T>, selected: T, argb: (T) -> Int, name: (T) -> Int, onSelect: (T) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(title, style = body.copy(fontSize = 14.sp, color = Palette.muted))
        FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            for (option in options) {
                val on = option == selected
                val description = stringResource(name(option))
                Box(
                    Modifier
                        .size(48.dp)
                        .clickable(role = Role.RadioButton) { onSelect(option) }
                        .semantics {
                            contentDescription = description
                            this.selected = on
                        },
                    contentAlignment = Alignment.Center,
                ) {
                    Box(
                        Modifier
                            .size(40.dp)
                            .border(2.dp, if (on) Palette.saffron else Color.Transparent, CircleShape)
                            .padding(5.dp)
                            .background(Color(argb(option)), CircleShape)
                            .border(1.dp, Palette.lineStrong, CircleShape),
                    )
                }
            }
        }
    }
}

/** Android's own page for this wallpaper; where a phone has none, its list of live wallpapers. */
private fun openWallpaperPicker(context: Context) {
    val own = Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER)
        .putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT, ComponentName(context, WheelWallpaper::class.java))
    try {
        context.startActivity(own)
    } catch (_: ActivityNotFoundException) {
        runCatching { context.startActivity(Intent(WallpaperManager.ACTION_LIVE_WALLPAPER_CHOOSER)) }
    }
}
