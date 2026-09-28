/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.ui

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset

/**
 * Birth date for the personal days: Luck, Life and Anti days in the Tibetan
 * view, the three bad days of one's birth year in the 旧暦 view.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BirthDateDialog(initial: LocalDate?, onSave: (LocalDate?) -> Unit, onDismiss: () -> Unit) {
    val state = rememberDatePickerState(
        initialSelectedDateMillis = initial?.atStartOfDay(ZoneOffset.UTC)?.toInstant()?.toEpochMilli(),
        yearRange = 1900..LocalDate.now().year,
    )
    val colors = DatePickerDefaults.colors(containerColor = Palette.surface)
    DatePickerDialog(
        onDismissRequest = onDismiss,
        colors = colors,
        confirmButton = {
            TextButton(onClick = {
                onSave(state.selectedDateMillis?.let { Instant.ofEpochMilli(it).atZone(ZoneOffset.UTC).toLocalDate() })
            }) { Text("Save", color = Palette.saffron) }
        },
        dismissButton = {
            Row {
                if (initial != null) TextButton(onClick = { onSave(null) }) { Text("Clear", color = Palette.muted) }
                TextButton(onClick = onDismiss) { Text("Cancel", color = Palette.muted) }
            }
        },
    ) {
        DatePicker(state = state, colors = colors, title = { Text("Birth date", modifier = androidx.compose.ui.Modifier.padding(start = 24.dp, top = 16.dp)) })
    }
}
