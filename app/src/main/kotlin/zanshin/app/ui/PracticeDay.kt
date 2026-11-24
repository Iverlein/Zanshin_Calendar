/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.iverlein.zanshin.R
import zanshin.core.texts.Texts
import zanshin.core.tibetan.SpecialDay
import zanshin.core.tibetan.TibetanFestival
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt

/*
 * The practice days of the month and the festivals on the Tibetan page (SPEC §10.3): the date stands in an aureole,
 * as a buddha's image does, on a lotus seat, with an emblem at its crown, painted in the manner of a Central Tibetan
 * thangka (ROADMAP T6.1, T6.2; the pictures cut by tools/practice_frames.py), and the day's name under it opens a
 * balloon with what is practised.
 */

/** The picture of a day, [w] × [h] dp, with the numeral's window from ([x0], [y0]) to ([x1], [y1]). */
class Frame internal constructor(@DrawableRes val res: Int, val w: Float, val h: Float, val x0: Float, val y0: Float, val x1: Float, val y1: Float)

// The large paintings, the practice days' and the festivals', share one geometry, the numeral's window inside the
// plain disc (tools/practice_frames.py).
private fun large(res: Int) = Frame(res, 250f, 258f, 54f, 72f, 196f, 186f)

/**
 * The frame of a festival, which on a practice day stands in place of the day's own (T6.2); null for a festival not
 * yet painted (the Nyingma days of T6.4), which keeps the practice day's frame or none.
 */
fun frameOf(festival: TibetanFestival): Frame? = when (festival) {
    TibetanFestival.LOSAR -> R.drawable.festival_losar
    TibetanFestival.CHOTRUL_DUCHEN -> R.drawable.festival_chotrul_duchen
    TibetanFestival.BIRTH -> R.drawable.festival_birth
    TibetanFestival.SAGA_DAWA_DUCHEN -> R.drawable.festival_saga_dawa_duchen
    TibetanFestival.ZAMLING_CHISANG -> R.drawable.festival_zamling_chisang
    TibetanFestival.CHOKHOR_DUCHEN -> R.drawable.festival_chokhor_duchen
    TibetanFestival.ENTRY_INTO_WOMB -> R.drawable.festival_entry_into_womb
    TibetanFestival.LHABAB_DUCHEN -> R.drawable.festival_lhabab_duchen
    TibetanFestival.PROTECTORS -> R.drawable.festival_protectors
    TibetanFestival.MIPHAM, TibetanFestival.GURU_BIRTH, TibetanFestival.JIGME_LINGPA, TibetanFestival.DUDJOM,
    TibetanFestival.LONGCHENPA -> null
}?.let(::large)

/** The frame of a practice day. */
fun frameOf(day: SpecialDay): Frame {
    return when (day) {
        SpecialDay.EIGHTH -> large(R.drawable.practice_8)
        SpecialDay.TENTH -> large(R.drawable.practice_10)
        SpecialDay.FULL_MOON -> large(R.drawable.practice_15)
        SpecialDay.TWENTY_FIFTH -> large(R.drawable.practice_25)
        SpecialDay.TWENTY_NINTH -> large(R.drawable.practice_29)
        SpecialDay.NEW_MOON -> large(R.drawable.practice_30)
        SpecialDay.NINTH, SpecialDay.TWENTY_FOURTH -> Frame(R.drawable.practice_small, 170f, 172f, 26f, 41f, 144f, 137f)
    }
}

/** The accent of a practice day: saffron for the buddhas and the guru, vermilion for the ḍākinīs and the protectors. */
fun practiceColor(day: SpecialDay): Color = when (day) {
    SpecialDay.TWENTY_FIFTH, SpecialDay.TWENTY_NINTH, SpecialDay.NINTH, SpecialDay.TWENTY_FOURTH -> Palette.vermilion
    else -> Palette.saffron
}

/**
 * [content], the day's numeral, in the painted [frame]: the picture is scaled so that its window holds the
 * numeral, whatever the font scale, and the numeral stands at the window's centre. Where the width is short of
 * that, the picture is made smaller to fit and the numeral with it.
 */
@Composable
fun PracticeOrnament(frame: Frame, content: @Composable () -> Unit) {
    val f = frame
    val painter = painterResource(f.res)
    Layout(content = {
        Image(painter, contentDescription = null, contentScale = ContentScale.FillBounds)
        content()
    }) { measurables, constraints ->
        val numeral = measurables[1].measure(Constraints())
        // Pixels per dp of the picture: by the numeral's height, or its width when it is wider than the window.
        val wanted = max(numeral.height / (f.y1 - f.y0), numeral.width / (f.x1 - f.x0))
        val s = if (constraints.hasBoundedWidth) min(wanted, constraints.maxWidth / f.w) else wanted
        val width = (f.w * s).roundToInt()
        val height = (f.h * s).roundToInt()
        val picture = measurables[0].measure(Constraints.fixed(width, height))
        layout(width, height) {
            picture.place(0, 0)
            val x = ((f.x0 + f.x1) / 2 * s - numeral.width / 2f).roundToInt()
            val y = ((f.y0 + f.y1) / 2 * s - numeral.height / 2f).roundToInt()
            if (s < wanted) numeral.placeWithLayer(x, y) { scaleX = s / wanted; scaleY = s / wanted } else numeral.place(x, y)
        }
    }
}

/**
 * The day's numeral in [frame], a practice day's or a festival's, with [label] ("from dawn") beside it: the label keeps the width of its
 * longest word, so that it never breaks inside one, and the frame takes the rest.
 */
@Composable
fun PracticeHeadline(frame: Frame, numeral: @Composable () -> Unit, label: @Composable () -> Unit) {
    Layout(content = {
        PracticeOrnament(frame, numeral)
        label()
    }) { (frame, text), constraints ->
        val gap = 12.dp.roundToPx()
        val least = text.minIntrinsicWidth(Constraints.Infinity)
        val frameMax = if (constraints.hasBoundedWidth) (constraints.maxWidth - gap - least).coerceAtLeast(0) else Constraints.Infinity
        val placedFrame = frame.measure(Constraints(maxWidth = frameMax))
        val textMax = if (constraints.hasBoundedWidth) (constraints.maxWidth - placedFrame.width - gap).coerceAtLeast(0) else Constraints.Infinity
        val placedText = text.measure(Constraints(maxWidth = textMax))
        layout(placedFrame.width + gap + placedText.width, max(placedFrame.height, placedText.height)) {
            placedFrame.place(0, 0)
            placedText.place(placedFrame.width + gap, 0)
        }
    }
}

/**
 * The practice day's name under the numeral, opening a balloon with the day's reading and its rows (the practice,
 * when it is strong); a skipped date's day says where it comes from.
 */
@Composable
fun PracticeLine(day: SpecialDay, movedFrom: Int?, open: Boolean, onToggle: () -> Unit) {
    val reading = Texts.SPECIAL_DAY.getValue(day)
    Column {
        BalloonText(
            text = withTibetan(day.english),
            style = body.copy(fontSize = 19.sp, fontWeight = FontWeight.Medium, color = practiceColor(day), lineHeight = 25.sp),
            open = open,
            onToggle = onToggle,
            lead = reading.summary,
            rows = reading.rows.map { (row, text) -> BalloonRow(row.label, text, stacked = true) },
        )
        movedFrom?.let { Text(stringResource(R.string.tib_moved_from_day, it), style = body.copy(fontSize = 14.sp, color = Palette.muted)) }
    }
}
