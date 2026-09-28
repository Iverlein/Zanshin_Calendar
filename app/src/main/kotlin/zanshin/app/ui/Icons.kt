/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.ui

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.unit.dp

/** The stroke icons of the design, 24×24, drawn from SVG path data; tinted by the Icon that shows them. */
object Icons {
    val Menu = stroke("M4 7h16M4 12h16M4 17h16")
    val ChevronDown = stroke("M6 9l6 6 6-6", 2f)
    val ChevronLeft = stroke("M15 5l-7 7 7 7", 2f)
    val ChevronRight = stroke("M9 5l7 7-7 7", 2f)
    val Swap = stroke("M7 7h12l-3-3M17 17H5l3 3", 2f)
    val Close = stroke("M6 6l12 12M18 6 6 18")
    val Check = stroke("M5 12l5 5 9-10", 2.2f)
    val Info = stroke("M12 3a9 9 0 1 0 0 18a9 9 0 1 0 0-18M12 11v6M12 7.5v.5")
    val Pin = stroke("M12 21s-7-6.2-7-11.5a7 7 0 0 1 14 0C19 14.8 12 21 12 21zM12 7a2.5 2.5 0 1 0 0 5a2.5 2.5 0 1 0 0-5")
    val Crosshair = stroke("M12 5a7 7 0 1 0 0 14a7 7 0 1 0 0-14M12 10a2 2 0 1 0 0 4a2 2 0 1 0 0-4M12 2v3M12 19v3M2 12h3M19 12h3")
    val Search = stroke("M11 5a6 6 0 1 0 0 12a6 6 0 1 0 0-12M20 20l-4.5-4.5", 2f)
    val Sunrise = stroke("M3 19h18M7 19a5 5 0 0 1 10 0M12 4v6M9.5 6.5 12 4l2.5 2.5", 1.7f)
    val Sunset = stroke("M3 19h18M7 19a5 5 0 0 1 10 0M12 10V4M9.5 7.5 12 10l2.5-2.5", 1.7f)
    val Sun = stroke(
        "M12 8a4 4 0 1 0 0 8a4 4 0 1 0 0-8M12 2.5v2M12 19.5v2M2.5 12h2M19.5 12h2" +
            "M5.3 5.3l1.4 1.4M17.3 17.3l1.4 1.4M5.3 18.7l1.4-1.4M17.3 6.7l1.4-1.4",
        1.7f,
    )

    private fun stroke(path: String, width: Float = 1.8f): ImageVector =
        ImageVector.Builder(defaultWidth = 24.dp, defaultHeight = 24.dp, viewportWidth = 24f, viewportHeight = 24f)
            .addPath(
                pathData = PathParser().parsePathString(path).toNodes(),
                fill = null,
                stroke = SolidColor(Color.Black),
                strokeLineWidth = width,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
            )
            .build()
}
