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
import androidx.compose.ui.res.stringResource
import zanshin.app.DayInfo
import zanshin.app.Labels
import zanshin.app.LocalLabels
import io.github.iverlein.zanshin.R
import zanshin.core.kyureki.Tone
import zanshin.core.texts.Catalog
import zanshin.core.texts.Texts
import zanshin.core.texts.gloss
import zanshin.core.tibetan.DaySigns
import zanshin.core.tibetan.Force
import zanshin.core.tibetan.ForceContrast
import zanshin.core.tibetan.Forces
import zanshin.core.tibetan.PersonalDay
import zanshin.core.tibetan.Repetition
import zanshin.core.tibetan.SME_BA_COLOURS
import zanshin.core.tibetan.Sign
import zanshin.core.tibetan.TibetanDay
import zanshin.core.tibetan.YearForces
import zanshin.core.tibetan.Ewts
import zanshin.core.tibetan.Thl
import zanshin.core.tibetan.element

private enum class TibetanBalloon { MONTH, YEAR, DAY }

/** The Tibetan view of one day (SPEC §10.3). */
@Composable
fun TibetanPage(info: DayInfo, modifier: Modifier = Modifier) {
    val day = info.tibetan
    val labels = LocalLabels.current
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
                    Text(stringResource(R.string.tib_moved_from_day, it), style = body.copy(fontSize = 14.sp, color = Palette.muted))
                }
                Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("${day.day}", style = TextStyle(fontFamily = Mincho, fontWeight = FontWeight.Medium, fontSize = 44.sp, color = Palette.text))
                    Text(stringResource(R.string.tib_from_dawn), style = body.copy(fontSize = 13.sp, color = Palette.muted), modifier = Modifier.padding(bottom = 8.dp))
                }
            }
        } else {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    "${day.day}",
                    style = TextStyle(fontFamily = Mincho, fontWeight = FontWeight.Medium, fontSize = 168.sp, letterSpacing = (-6).sp, color = Palette.text),
                    modifier = Modifier.capBox(168.sp),
                )
                Text(stringResource(R.string.tib_from_dawn), style = body.copy(fontSize = 13.sp, color = Palette.muted))
            }
        }

        when (day.repetition) {
            Repetition.FIRST_OF_TWO -> Chip(stringResource(R.string.tib_first_of_two))
            Repetition.SECOND_OF_TWO -> Chip(stringResource(R.string.tib_second_of_two))
            Repetition.NONE -> Unit
        }
        day.omittedBefore?.let { omitted ->
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Icon(Icons.Info, contentDescription = null, tint = Palette.muted)
                Text(stringResource(R.string.tib_day_omitted, omitted), style = body.copy(fontSize = 14.sp, color = Palette.muted))
            }
        }

        Column {
            val monthWylie = day.monthNames.wylie
            val monthScript = remember(monthWylie) { Ewts.toTibetan(monthWylie) }
            val monthSaid = remember(monthWylie) { Thl.toPhonetic(monthWylie) }
            BalloonText(
                text = buildAnnotatedString {
                    append("${labels.month(day.month, day.leapMonth)} · ")
                    if (monthScript != null) withStyle(SpanStyle(fontFamily = TibetanSerif)) { append(monthScript) } else append(monthWylie)
                },
                style = body.copy(fontSize = 22.sp, fontWeight = FontWeight.Medium),
                open = balloon == TibetanBalloon.MONTH,
                onToggle = { toggle(TibetanBalloon.MONTH) },
                underline = false,
                description = "${labels.month(day.month, day.leapMonth)}, ${monthSaid ?: monthWylie}",
                rows = listOfNotNull(
                    monthScript?.let { BalloonRow(stringResource(R.string.row_tibetan), it, tibetanStyle(16.sp)) },
                    BalloonRow(stringResource(R.string.row_wylie), monthWylie),
                    monthSaid?.let { BalloonRow(stringResource(R.string.row_say), it) },
                    BalloonRow(stringResource(R.string.row_sanskrit), day.monthNames.sanskrit),
                    BalloonRow(stringResource(R.string.row_animal), stringResource(R.string.tib_animal_month, gloss(day.monthNames.animal))),
                    BalloonRow(stringResource(R.string.row_season), day.monthNames.season),
                    BalloonRow(stringResource(R.string.row_element), gloss(info.signs.month.element)),
                ) + aspectRows(info.signs.month.forces, info.birthSign?.forces, listOf(Force.VITALITY, Force.BODY)),
            )
            BalloonText(
                text = stringResource(R.string.tib_year_line, gloss(day.yearElement), gloss(day.yearAnimal)),
                style = body.copy(fontSize = 17.sp, color = Palette.muted),
                open = balloon == TibetanBalloon.YEAR,
                onToggle = { toggle(TibetanBalloon.YEAR) },
                rows = listOf(
                    BalloonRow(
                        stringResource(R.string.row_year),
                        stringResource(R.string.tib_year_full, gloss(day.yearElement), gloss(day.yearGender, "inText"), gloss(day.yearAnimal)),
                    ),
                    BalloonRow(stringResource(R.string.row_royal_year), "${day.royalYear}"),
                    BalloonRow(stringResource(R.string.row_rabjung), stringResource(R.string.tib_rabjung, labels.ordinal(day.rabjungCycle), day.rabjungYear)),
                ) + aspectRows(Forces.of(day.yearElement, day.yearAnimal), info.birthSign?.forces, Force.entries),
            )
            BalloonText(
                text = "${day.weekday.english} · ${day.weekday.planet} · ${stringResource(R.string.element_animal, gloss(day.dayElement), gloss(day.dayAnimal))}",
                style = body.copy(color = Palette.muted),
                open = balloon == TibetanBalloon.DAY,
                onToggle = { toggle(TibetanBalloon.DAY) },
                rows = listOf(
                    BalloonRow(stringResource(R.string.row_weekday), "${day.weekday.english} · gza’ ${day.weekday.wylie}"),
                    BalloonRow(stringResource(R.string.row_planet), day.weekday.planet),
                    BalloonRow(stringResource(R.string.row_element), gloss(day.dayElement)),
                    BalloonRow(stringResource(R.string.row_animal), gloss(day.dayAnimal)),
                    BalloonRow(stringResource(R.string.row_gender), gloss(day.dayGender, "inText")),
                ),
            )
        }

        Spacer(Modifier.fillMaxWidth().height(1.dp).background(Palette.line))

        // Readings, each opening its sourced text.
        val observance = stringResource(R.string.tib_monthly_observance)
        val forBirthYear = stringResource(R.string.tib_for_birth_year)
        val pairSubtitle = stringResource(R.string.tib_element_pair_subtitle, day.elementPair.english)
        val haircutTitle = stringResource(R.string.tib_haircut)
        val annotations = buildList {
            holidayAnnotation?.let { add(it) }
            day.specialDay?.let { add(Annotation(it.english, observance, Tone.GOOD, Texts.SPECIAL_DAY[it], titleIsKanji = false)) }
            info.personalDay?.let {
                add(Annotation(it.english, forBirthYear, if (it == PersonalDay.ANTI) Tone.BAD else Tone.GOOD, Texts.PERSONAL_DAY[it], titleIsKanji = false))
            }
            val pair = day.elementPair
            add(
                Annotation(
                    "${day.weekday.element.english} – ${day.mansion.element.english}",
                    "${pair.english} (${pair.wylie})",
                    if (pair.auspicious) Tone.GOOD else Tone.BAD,
                    Texts.ELEMENT_PAIR[pair],
                    subtitle = pairSubtitle,
                    titleIsKanji = false,
                ),
            )
            val haircut = Texts.HAIRCUT[day.day - 1]
            add(
                Annotation(
                    haircutTitle,
                    Catalog.text(haircut.arg!!),
                    if (day.day in Texts.HAIRCUT_GOOD) Tone.GOOD else Tone.BAD,
                    haircut,
                    titleIsKanji = false,
                ),
            )
        }
        SectionTitle(stringResource(R.string.section_almanac))
        Column { annotations.forEach { AnnotationRow(it) { a -> sheet = a } } }

        info.birthSign?.let { birth ->
            val signs = info.signs
            SectionTitle(stringResource(R.string.section_your_day))
            Column {
                for (force in listOf(Force.VITALITY, Force.BODY)) {
                    AnnotationRow(pebbleAnnotation(force, birth, signs, day, labels)) { a -> sheet = a }
                }
            }
        }

        SectionTitle(stringResource(R.string.section_five_components))
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            FactRow(stringResource(R.string.row_lunar_mansion), day.mansion.wylie, "${day.mansion.sanskrit} — ${day.mansion.english} · ${day.mansion.element.english}")
            FactRow(stringResource(R.string.row_yoga), day.yoga.wylie, "${day.yoga.sanskrit} — ${day.yoga.english}")
            FactRow(stringResource(R.string.row_karana), day.karana.wylie, "${day.karana.sanskrit} — ${day.karana.english}")
            FactRow(stringResource(R.string.row_weekday), "gza’ ${day.weekday.wylie}", "${day.weekday.english} · ${day.weekday.planet} · ${day.weekday.element.english}")
        }

        SectionTitle(stringResource(R.string.section_lunar_day))
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            FactRow(stringResource(R.string.row_animal), gloss(day.lunarDayAnimal), gloss(day.lunarDayAnimal), tibetan = false)
            FactRow(stringResource(R.string.row_trigram), day.trigram.wylie, "${day.trigram.chinese} — ${day.trigram.english}")
            val colour = SME_BA_COLOURS[day.smeBa - 1]
            FactRow(stringResource(R.string.row_number), "${day.smeBa}", "${day.smeBa} · ${Catalog.text("Colour.$colour")}", tibetan = false, swatch = smeBaSwatch(colour))
        }
    }

    sheet?.let { ReadingSheet(it) { sheet = null } }
}

/**
 * The aspects of a year or month in its balloon: each one's element, and with
 * a birth date set its pebbles and relation to the same aspect of the birth
 * year ("Fire · ○○○ mother"). The month and the year hold these rather than
 * the page, where they would repeat for weeks.
 */
@Composable
private fun aspectRows(its: YearForces, own: YearForces?, forces: List<Force>): List<BalloonRow> = forces.map { force ->
    val value = if (own == null) {
        gloss(its[force])
    } else {
        val c = ForceContrast(force, own[force], its[force])
        stringResource(R.string.aspect_for_you, gloss(its[force]), c.pebbles.toString(), c.kinship.english)
    }
    BalloonRow(force.english.replaceFirstChar(Char::uppercase), value)
}

/**
 * One aspect of the birth year against the same aspect of the lunar date (the
 * White Beryl's divination of health, which reads vitality and body): its
 * pebbles as the charts write them, white noughts and black crosses, and on
 * tap how the date's element was worked out.
 */
private fun pebbleAnnotation(force: Force, birth: Sign, signs: DaySigns, day: TibetanDay, labels: Labels): Annotation {
    val sign = signs.date
    val c = ForceContrast(force, birth.forces[force], sign.forces[force])
    val p = c.pebbles
    val aspect = force.english.replaceFirstChar(Char::uppercase)
    val spoken = when {
        p.black == 0 -> labels.plural(R.plurals.pebbles_white, p.white)
        p.white == 0 -> labels.plural(R.plurals.pebbles_black, p.black)
        else -> labels.string(R.string.pebbles_mixed, p.white, p.black)
    }
    fun name(s: Sign) = labels.string(R.string.element_animal, gloss(s.element), gloss(s.animal))
    val details = listOf(
        labels.string(R.string.detail_date) to labels.string(R.string.detail_date_value, labels.ordinal(day.day), name(signs.date)),
        labels.string(R.string.detail_month) to labels.string(R.string.detail_month_value, labels.month(day.month, day.leapMonth), name(signs.month)),
        labels.string(R.string.row_year) to name(signs.year),
        labels.string(R.string.detail_counted) to labels.string(
            R.string.detail_counted_day,
            gloss(signs.year.element, "inText"),
            gloss(signs.month.element, "inText"),
        ),
        labels.string(R.string.detail_its_day, force.english) to gloss(sign.forces[force]),
        labels.string(R.string.detail_yours) to labels.string(R.string.detail_yours_value, gloss(birth.forces[force]), name(birth)),
        labels.string(R.string.detail_relation) to "${c.kinship.english} · $p",
    )
    return Annotation(
        title = labels.string(R.string.pebble_row_title, aspect, p.toString()),
        english = c.kinship.english,
        tone = when {
            p.black == 0 -> Tone.GOOD
            p.white == 0 -> Tone.BAD
            else -> Tone.MIXED
        },
        reading = Texts.PEBBLES[c.kinship],
        subtitle = labels.string(R.string.pebble_subtitle, c.kinship.english, gloss(c.other, "inText"), gloss(c.own, "inText")),
        titleIsKanji = false,
        spokenTitle = "$aspect, $spoken",
        details = details,
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
        rows = listOfNotNull(
            BalloonRow(stringResource(R.string.row_wylie), wylie),
            said?.let { BalloonRow(stringResource(R.string.row_say), it) },
            BalloonRow(stringResource(R.string.row_english), english),
        ),
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
