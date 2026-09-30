/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zanshin.app.DayInfo
import zanshin.app.Labels
import zanshin.core.kyureki.Tone
import zanshin.core.texts.Texts
import zanshin.core.tibetan.Force
import zanshin.core.tibetan.ForceContrast
import zanshin.core.tibetan.Forces
import zanshin.core.tibetan.PersonalDay
import zanshin.core.tibetan.Repetition
import zanshin.core.tibetan.SME_BA_COLOURS
import zanshin.core.tibetan.Ewts
import zanshin.core.tibetan.Thl
import zanshin.core.tibetan.element

private enum class TibetanBalloon { MONTH, YEAR, DAY }

/** The Tibetan view of one day (SPEC §10.3). */
@Composable
fun TibetanPage(info: DayInfo, modifier: Modifier = Modifier) {
    val day = info.tibetan
    val accent = Palette.saffron
    var balloon by remember(day.jd) { mutableStateOf<TibetanBalloon?>(null) }
    var sheet by remember(day.jd) { mutableStateOf<Annotation?>(null) }
    fun toggle(b: TibetanBalloon) {
        balloon = if (balloon == b) null else b
    }

    val holiday = day.holiday
    val holidayAnnotation = holiday?.let {
        Annotation(it.festival.title, it.festival.english, Tone.GOOD, Texts.TIBETAN_FESTIVAL[it.festival], titleIsKanji = false)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 28.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp),
    ) {
        if (holiday != null && holidayAnnotation != null) {
            Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                FestivalGlyph(holiday.festival.name, accent, 72.dp)
                Text(
                    holiday.name,
                    style = TextStyle(fontFamily = Mincho, fontWeight = FontWeight.Bold, fontSize = 40.sp, color = accent).tight(1.1f),
                    modifier = Modifier.clickable(role = Role.Button) { sheet = holidayAnnotation },
                )
                Text(holiday.festival.english, style = body.copy(color = Palette.muted))
                holiday.movedFromDay?.let {
                    Text("moved from day $it, which is omitted", style = body.copy(fontSize = 14.sp, color = Palette.muted))
                }
                Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("${day.day}", style = TextStyle(fontFamily = Mincho, fontWeight = FontWeight.Medium, fontSize = 44.sp, color = Palette.text))
                    Text("from dawn", style = body.copy(fontSize = 13.sp, color = Palette.muted), modifier = Modifier.padding(bottom = 8.dp))
                }
            }
        } else {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    "${day.day}",
                    style = TextStyle(fontFamily = Mincho, fontWeight = FontWeight.Medium, fontSize = 168.sp, letterSpacing = (-6).sp, color = Palette.text),
                    modifier = Modifier.capBox(168.sp),
                )
                Text("from dawn", style = body.copy(fontSize = 13.sp, color = Palette.muted))
            }
        }

        when (day.repetition) {
            Repetition.FIRST_OF_TWO -> Chip("first of two")
            Repetition.SECOND_OF_TWO -> Chip("second of two")
            Repetition.NONE -> Unit
        }
        day.omittedBefore?.let { omitted ->
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Icon(Icons.Info, contentDescription = null, tint = Palette.muted)
                Text("Day $omitted is omitted this month", style = body.copy(fontSize = 14.sp, color = Palette.muted))
            }
        }

        Column {
            val monthWylie = day.monthNames.wylie
            val monthScript = remember(monthWylie) { Ewts.toTibetan(monthWylie) }
            val monthSaid = remember(monthWylie) { Thl.toPhonetic(monthWylie) }
            BalloonText(
                text = buildAnnotatedString {
                    append("${Labels.month(day.month, day.leapMonth)} · ")
                    if (monthScript != null) withStyle(SpanStyle(fontFamily = TibetanSerif)) { append(monthScript) } else append(monthWylie)
                },
                style = body.copy(fontSize = 22.sp, fontWeight = FontWeight.Medium),
                open = balloon == TibetanBalloon.MONTH,
                onToggle = { toggle(TibetanBalloon.MONTH) },
                underline = false,
                description = "${Labels.month(day.month, day.leapMonth)}, ${monthSaid ?: monthWylie}",
                rows = listOfNotNull(
                    monthScript?.let { BalloonRow("Tibetan", it, tibetanStyle(16.sp)) },
                    BalloonRow("Wylie", monthWylie),
                    monthSaid?.let { BalloonRow("Say", it) },
                    BalloonRow("Sanskrit", day.monthNames.sanskrit),
                    BalloonRow("Animal", "${Labels.enum(day.monthNames.animal)} month"),
                    BalloonRow("Season", day.monthNames.season),
                ),
            )
            BalloonText(
                text = "${Labels.enum(day.yearElement)} ${Labels.enum(day.yearAnimal)} year",
                style = body.copy(fontSize = 17.sp, color = Palette.muted),
                open = balloon == TibetanBalloon.YEAR,
                onToggle = { toggle(TibetanBalloon.YEAR) },
                rows = listOf(
                    BalloonRow("Year", "${Labels.enum(day.yearElement)} ${Labels.enum(day.yearGender).lowercase()} ${Labels.enum(day.yearAnimal)}"),
                    BalloonRow("Royal year", "${day.royalYear}"),
                    BalloonRow("Rabjung", "${Labels.ordinal(day.rabjungCycle)} cycle, year ${day.rabjungYear}"),
                ) + Forces.of(day.yearElement, day.yearAnimal).let { f ->
                    Force.entries.map { BalloonRow(it.english.replaceFirstChar(Char::uppercase), Labels.enum(f[it])) }
                },
            )
            BalloonText(
                text = "${day.weekday.english} · ${day.weekday.planet} · ${Labels.enum(day.dayElement)} ${Labels.enum(day.dayAnimal)}",
                style = body.copy(color = Palette.muted),
                open = balloon == TibetanBalloon.DAY,
                onToggle = { toggle(TibetanBalloon.DAY) },
                rows = listOf(
                    BalloonRow("Weekday", "${day.weekday.english} · gza’ ${day.weekday.wylie}"),
                    BalloonRow("Planet", day.weekday.planet),
                    BalloonRow("Element", Labels.enum(day.dayElement)),
                    BalloonRow("Animal", Labels.enum(day.dayAnimal)),
                    BalloonRow("Gender", day.dayGender.name.lowercase()),
                ),
            )
        }

        Spacer(Modifier.fillMaxWidth().height(1.dp).background(Palette.line))

        // Readings, each opening its sourced text.
        val annotations = buildList {
            holidayAnnotation?.let { add(it) }
            day.specialDay?.let { add(Annotation(it.english, "monthly observance", Tone.GOOD, Texts.SPECIAL_DAY[it], titleIsKanji = false)) }
            info.personalDay?.let {
                add(Annotation(it.english, "for your birth year", if (it == PersonalDay.ANTI) Tone.BAD else Tone.GOOD, Texts.PERSONAL_DAY[it], titleIsKanji = false))
            }
            val pair = day.elementPair
            add(
                Annotation(
                    "${day.weekday.element.english} – ${day.mansion.element.english}",
                    "${pair.english} (${pair.wylie})",
                    if (pair.auspicious) Tone.GOOD else Tone.BAD,
                    Texts.ELEMENT_PAIR[pair],
                    subtitle = "elements of weekday and mansion: ${pair.english}",
                    titleIsKanji = false,
                ),
            )
            val haircut = Texts.HAIRCUT[day.day - 1]
            add(
                Annotation(
                    "Haircut",
                    haircut.summary.removePrefix("Cutting hair today: ").removeSuffix("."),
                    if (day.day in Texts.HAIRCUT_GOOD) Tone.GOOD else Tone.BAD,
                    haircut,
                    titleIsKanji = false,
                ),
            )
        }
        SectionTitle("Almanac")
        Column { annotations.forEach { AnnotationRow(it) { a -> sheet = a } } }

        info.yearContrast?.let { contrasts ->
            SectionTitle("Your year")
            Column { contrasts.forEach { AnnotationRow(pebbleAnnotation(it)) { a -> sheet = a } } }
        }

        SectionTitle("Five components")
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            FactRow("Lunar mansion", day.mansion.wylie, "${day.mansion.sanskrit} — ${day.mansion.english} · ${day.mansion.element.english}")
            FactRow("Yoga", day.yoga.wylie, "${day.yoga.sanskrit} — ${day.yoga.english}")
            FactRow("Karaṇa", day.karana.wylie, "${day.karana.sanskrit} — ${day.karana.english}")
            FactRow("Weekday", "gza’ ${day.weekday.wylie}", "${day.weekday.english} · ${day.weekday.planet} · ${day.weekday.element.english}")
        }

        SectionTitle("Lunar day")
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            FactRow("Animal", Labels.enum(day.lunarDayAnimal), Labels.enum(day.lunarDayAnimal), tibetan = false)
            FactRow("Trigram", day.trigram.wylie, "${day.trigram.chinese} — ${day.trigram.english}")
            val colour = SME_BA_COLOURS[day.smeBa - 1]
            FactRow("Number", "${day.smeBa}", "${day.smeBa} · $colour", tibetan = false, swatch = smeBaSwatch(colour))
        }
    }

    sheet?.let { ReadingSheet(it) { sheet = null } }
}

/**
 * One aspect of the birth year against this year's, with its pebbles as the
 * charts write them: white noughts, black crosses.
 */
private fun pebbleAnnotation(c: ForceContrast): Annotation {
    val p = c.pebbles
    val aspect = c.force.english.replaceFirstChar(Char::uppercase)
    val spoken = listOfNotNull(
        if (p.white > 0) "${p.white} white" else null,
        if (p.black > 0) "${p.black} black" else null,
    ).joinToString(" and ") + if (p.white + p.black > 1) " pebbles" else " pebble"
    return Annotation(
        title = "$aspect  $p",
        english = c.kinship.english,
        tone = when {
            p.black == 0 -> Tone.GOOD
            p.white == 0 -> Tone.BAD
            else -> Tone.MIXED
        },
        reading = Texts.PEBBLES[c.kinship],
        subtitle = "${c.kinship.english}: the year's ${c.year.name.lowercase()} to your ${c.own.name.lowercase()}",
        titleIsKanji = false,
        spokenTitle = "$aspect, $spoken",
    )
}

/**
 * The colour a sme ba box is printed in (Berzin, Details of Tibetan Astrology 4),
 * by the colour name the app shows. Black gets a rim so it reads on the dark page.
 */
private fun smeBaSwatch(name: String): Color = when (name) {
    "white" -> Color(0xFFF2EFE8)
    "black" -> Color(0xFF000000)
    "blue" -> Color(0xFF2E4A8C)
    "green" -> Color(0xFF3F8A4E)
    "yellow" -> Color(0xFFE1B93A)
    "red" -> Color(0xFFB3322A)
    else -> Palette.faint
}

/** Tibetan script in the bundled font, sized to sit level with Latin text of [size]. */
fun tibetanStyle(size: TextUnit): TextStyle = body.copy(fontFamily = TibetanSerif, fontSize = size * 1.15f)

/**
 * A Tibetan term in Tibetan script, or in Wylie when the spelling is not one
 * the converter reads. Tapping it shows the Wylie, how it is said (THL
 * phonetics) and the English (SPEC §10.1); screen readers get the phonetics.
 */
@Composable
fun TibetanTerm(wylie: String, english: String, size: TextUnit = 16.sp, preferAbove: Boolean = false, modifier: Modifier = Modifier) {
    val script = remember(wylie) { Ewts.toTibetan(wylie) }
    val said = remember(wylie) { Thl.toPhonetic(wylie) }
    GlossText(
        text = script ?: wylie,
        english = english,
        style = if (script != null) tibetanStyle(size) else body.copy(fontSize = size),
        preferAbove = preferAbove,
        modifier = modifier,
        rows = listOfNotNull(BalloonRow("Wylie", wylie), said?.let { BalloonRow("Say", it) }, BalloonRow("English", english)),
        spoken = said ?: wylie,
    )
}

/**
 * A labelled fact whose value is a term: tapping the term shows its English.
 * [swatch] draws its colour before it. A Tibetan term sits on the label's
 * baseline: its script hangs from a head line, so centring the boxes would
 * lift it above the label.
 */
@Composable
fun FactRow(label: String, term: String, english: String, tibetan: Boolean = true, kanji: Boolean = false, swatch: Color? = null) {
    Row(
        Modifier.fillMaxWidth().heightIn(min = 40.dp),
        verticalAlignment = if (tibetan) Alignment.Top else Alignment.CenterVertically,
    ) {
        Text(
            label,
            style = body.copy(fontSize = 14.sp, color = Palette.muted),
            modifier = Modifier.width(120.dp).let { if (tibetan) it.alignByBaseline() else it },
        )
        if (swatch != null) {
            Spacer(
                Modifier
                    .padding(end = 10.dp)
                    .size(14.dp)
                    .background(swatch, CircleShape)
                    .border(1.dp, Palette.lineStrong, CircleShape),
            )
        }
        if (tibetan) {
            TibetanTerm(term, english, preferAbove = true, modifier = Modifier.alignByBaseline())
        } else if (kanji) {
            GlossText(
                term,
                english,
                style = body.copy(fontFamily = Mincho, fontWeight = FontWeight.Bold, fontSize = 17.sp),
                preferAbove = true,
            )
        } else {
            Text(english, style = body.copy(fontSize = 16.sp))
        }
    }
}
