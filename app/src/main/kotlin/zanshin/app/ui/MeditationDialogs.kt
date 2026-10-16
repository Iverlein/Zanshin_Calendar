/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.iverlein.zanshin.R
import zanshin.app.bell.BellSettings
import zanshin.app.bell.Preset
import zanshin.app.bell.SessionPlan
import java.time.LocalTime

/**
 * A length of any value, typed as minutes and seconds (SPEC §10.9): the
 * warm-up, which may be none, or a period, at least a second.
 */
@Composable
fun LengthDialog(title: String, seconds: Int, allowZero: Boolean, onSave: (Int) -> Unit, onDismiss: () -> Unit) {
    var minutes by remember { mutableStateOf((seconds / 60).toString()) }
    var rest by remember { mutableStateOf((seconds % 60).toString()) }
    val total = (minutes.toIntOrNull() ?: 0) * 60 + (rest.toIntOrNull() ?: 0)
    val valid = total in (if (allowZero) 0 else 1)..SessionPlan.MAX_SECONDS && (rest.toIntOrNull() ?: 0) < 60
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = Palette.surface,
        title = { Text(title, style = body.copy(fontSize = 20.sp, fontWeight = FontWeight.SemiBold)) },
        text = {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                NumberField(stringResource(R.string.length_minutes), minutes, 4, Modifier.weight(1f)) { minutes = it }
                NumberField(stringResource(R.string.length_seconds), rest, 2, Modifier.weight(1f)) { rest = it }
            }
        },
        confirmButton = {
            TextButton(onClick = { onSave(total) }, enabled = valid) {
                Text(stringResource(R.string.birth_save), color = if (valid) Palette.saffron else Palette.off)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text(stringResource(R.string.birth_cancel), color = Palette.muted) }
        },
    )
}

@Composable
private fun NumberField(title: String, value: String, digits: Int, modifier: Modifier, onChange: (String) -> Unit) {
    Column(modifier, verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(title, style = body.copy(fontSize = 13.sp, color = Palette.muted))
        BasicTextField(
            value = value,
            onValueChange = { v -> if (v.length <= digits && v.all { it.isDigit() }) onChange(v) },
            singleLine = true,
            textStyle = body.copy(fontSize = 22.sp, fontFeatureSettings = "tnum"),
            cursorBrush = SolidColor(Palette.text),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Palette.lineStrong, RoundedCornerShape(8.dp))
                .padding(horizontal = 12.dp, vertical = 10.dp)
                .semantics { contentDescription = title },
        )
    }
}

/** A name for the plan set; a name already taken replaces that preset, and the dialog says so. */
@Composable
fun PresetDialog(taken: Set<String>, onSave: (String) -> Unit, onDismiss: () -> Unit) {
    var name by remember { mutableStateOf("") }
    val clean = Preset.clean(name)
    val nameDescription = stringResource(R.string.preset_name)
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = Palette.surface,
        title = { Text(stringResource(R.string.preset_save), style = body.copy(fontSize = 20.sp, fontWeight = FontWeight.SemiBold)) },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(nameDescription, style = body.copy(fontSize = 13.sp, color = Palette.muted))
                BasicTextField(
                    value = name,
                    onValueChange = { if (it.length <= Preset.NAME_MAX) name = it.replace('\n', ' ') },
                    singleLine = true,
                    textStyle = body.copy(fontSize = 16.sp),
                    cursorBrush = SolidColor(Palette.text),
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Palette.lineStrong, RoundedCornerShape(8.dp))
                        .padding(horizontal = 12.dp, vertical = 12.dp)
                        .semantics { contentDescription = nameDescription },
                )
                if (clean in taken) Text(stringResource(R.string.preset_replaces), style = body.copy(fontSize = 13.sp, color = Palette.mixed))
            }
        },
        confirmButton = {
            TextButton(onClick = { onSave(clean) }, enabled = clean.isNotEmpty()) {
                Text(stringResource(R.string.birth_save), color = if (clean.isNotEmpty()) Palette.saffron else Palette.off)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text(stringResource(R.string.birth_cancel), color = Palette.muted) }
        },
    )
}

/** The mindfulness bell's message: any line, empty for the bell's name alone, Default for the mantra. */
@Composable
fun MessageDialog(message: String, onSave: (String) -> Unit, onDismiss: () -> Unit) {
    var text by remember { mutableStateOf(message) }
    val description = stringResource(R.string.bell_message)
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = Palette.surface,
        title = { Text(description, style = body.copy(fontSize = 20.sp, fontWeight = FontWeight.SemiBold)) },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(stringResource(R.string.bell_message_hint), style = body.copy(fontSize = 13.sp, color = Palette.muted))
                BasicTextField(
                    value = text,
                    onValueChange = { if (it.length <= BellSettings.MESSAGE_MAX) text = it.replace('\n', ' ') },
                    textStyle = body.copy(fontSize = 16.sp),
                    cursorBrush = SolidColor(Palette.text),
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Palette.lineStrong, RoundedCornerShape(8.dp))
                        .padding(horizontal = 12.dp, vertical = 12.dp)
                        .semantics { contentDescription = description },
                )
                if (text != BellSettings.MANTRA) {
                    TextButton(onClick = { text = BellSettings.MANTRA }) {
                        Text(stringResource(R.string.bell_message_default), color = Palette.saffron)
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = { onSave(text) }) { Text(stringResource(R.string.birth_save), color = Palette.saffron) }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text(stringResource(R.string.birth_cancel), color = Palette.muted) }
        },
    )
}

/**
 * A time for one end of the mindfulness bell's hours (ROADMAP U17): the
 * Material clock in the app's colours, 24-hour as the app's clock; the
 * screen rounds the time set to the half hour (`BellHours`). With its own
 * [note], the hour of a relative's birth or death (ROADMAP P1); with none,
 * the morning notice's time (ROADMAP N1).
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BellTimeDialog(
    title: String,
    time: LocalTime,
    onSet: (LocalTime) -> Unit,
    onDismiss: () -> Unit,
    note: String? = stringResource(R.string.bell_time_rounds),
) {
    val is24Hour = stringResource(R.string.pattern_clock).contains('H')
    val state = rememberTimePickerState(initialHour = time.hour, initialMinute = time.minute, is24Hour = is24Hour)
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = Palette.surface,
        title = { Text(title, style = body.copy(fontSize = 20.sp, fontWeight = FontWeight.SemiBold)) },
        text = {
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(12.dp)) {
                TimePicker(
                    state = state,
                    colors = TimePickerDefaults.colors(
                        containerColor = Palette.surface,
                        clockDialColor = Palette.raised,
                        clockDialSelectedContentColor = Palette.onAccent,
                        clockDialUnselectedContentColor = Palette.text,
                        selectorColor = Palette.saffron,
                        periodSelectorBorderColor = Palette.lineStrong,
                        periodSelectorSelectedContainerColor = Palette.saffron.copy(alpha = 0.22f),
                        periodSelectorUnselectedContainerColor = Color.Transparent,
                        periodSelectorSelectedContentColor = Palette.saffron,
                        periodSelectorUnselectedContentColor = Palette.muted,
                        timeSelectorSelectedContainerColor = Palette.saffron.copy(alpha = 0.22f),
                        timeSelectorUnselectedContainerColor = Palette.raised,
                        timeSelectorSelectedContentColor = Palette.saffron,
                        timeSelectorUnselectedContentColor = Palette.text,
                    ),
                )
                note?.let { Text(it, style = body.copy(fontSize = 13.sp, color = Palette.muted)) }
            }
        },
        confirmButton = {
            TextButton(onClick = { onSet(LocalTime.of(state.hour, state.minute)) }) {
                Text(stringResource(R.string.birth_save), color = Palette.saffron)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text(stringResource(R.string.birth_cancel), color = Palette.muted) }
        },
    )
}
