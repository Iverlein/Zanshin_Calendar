/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalDensity

/**
 * The app's bottom sheet, always fully open: [ModalBottomSheet] with its top inset taken out of its
 * hands. Material's own sheet pads its content by the part of the status bar its offset does not
 * cover, so a sheet tall enough to reach the status bar changes height as it moves, which moves its
 * open anchor, which moves it again: after an upward swipe it jumped up and down without end and
 * kept the processor busy. Here the content is padded at the bottom only and kept below the status
 * bar by a height cap, so the sheet's height no longer depends on where it is.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Sheet(
    onDismiss: () -> Unit,
    dragHandle: @Composable (() -> Unit)? = { BottomSheetDefaults.DragHandle() },
    content: @Composable ColumnScope.() -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
        containerColor = Palette.surface,
        scrimColor = Palette.scrim,
        dragHandle = dragHandle,
        contentWindowInsets = { WindowInsets.safeDrawing.only(WindowInsetsSides.Bottom) },
    ) {
        // The window's own top inset, not what the sheet's offset leaves of it.
        val top = WindowInsets.safeDrawing.getTop(LocalDensity.current)
        Column(
            Modifier.layout { measurable, constraints ->
                val placeable = measurable.measure(
                    constraints.copy(maxHeight = (constraints.maxHeight - top).coerceAtLeast(constraints.minHeight)),
                )
                layout(placeable.width, placeable.height) { placeable.place(0, 0) }
            },
            content = content,
        )
    }
}
