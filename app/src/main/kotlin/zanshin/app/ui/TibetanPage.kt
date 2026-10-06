/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Box
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
import zanshin.core.texts.Activity
import zanshin.core.texts.ActivityFamily
import zanshin.core.texts.Catalog
import zanshin.core.texts.DaySummary
import zanshin.core.texts.Texts
import zanshin.core.texts.gloss
import zanshin.core.tibetan.DaySigns
import zanshin.core.tibetan.DaySmeBa
import zanshin.core.tibetan.Force
import androidx.compose.material3.IconButton
import zanshin.core.tibetan.Pebbles
import zanshin.core.tibetan.HourSign
import java.time.ZoneId
import zanshin.core.tibetan.ForceContrast
import zanshin.core.tibetan.Forces
import zanshin.core.tibetan.PersonalDay
import zanshin.core.tibetan.Repetition
import zanshin.core.tibetan.SME_BA_COLOURS
import zanshin.core.tibetan.Sign
import zanshin.core.tibetan.TibetanDay
import zanshin.core.tibetan.YearForces
import zanshin.core.tibetan.Ewts
import zanshin.core.tibetan.nectarHours
import zanshin.core.tibetan.LunarDayClass
import zanshin.core.tibetan.Thl
import zanshin.core.tibetan.element

private enum class TibetanBalloon { MONTH, YEAR, DAY }

/** The Tibetan view of one day (SPEC §10.3). */
@Composable
fun TibetanPage(info: DayInfo, zone: ZoneId, modifier: Modifier = Modifier) {
    val day = info.tibetan
    val labels = LocalLabels.current
    val accent = Palette.saffron
    var balloon by remember(day.jd) { mutableStateOf<TibetanBalloon?>(null) }
    var sheet by remember(day.jd) { mutableStateOf<Annotation?>(null) }
    var summaryOpen by remember(day.jd) { mutableStateOf(false) }
    var hoursOpen by remember(day.jd) { mutableStateOf(false) }
    val summary = remember(day.jd, labels.locale) { DaySummary.of(day) }
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
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            CueIcon(CueGlyphs.of(day.yearElement), Palette.muted, 20.dp)
            CueIcon(CueGlyphs.ANIMAL.getValue(day.yearAnimal), Palette.muted, 20.dp, modifier = Modifier.padding(end = 6.dp))
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
            }
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            CueIcon(CueGlyphs.of(day.dayElement), Palette.muted, 20.dp)
            CueIcon(CueGlyphs.ANIMAL.getValue(day.dayAnimal), Palette.muted, 20.dp, modifier = Modifier.padding(end = 6.dp))
            BalloonText(
                text = "${day.weekday.english} · ${day.weekday.planet} · ${stringResource(R.string.element_animal, gloss(day.dayElement), gloss(day.dayAnimal))}",
                style = body.copy(color = Palette.muted),
                open = balloon == TibetanBalloon.DAY,
                onToggle = { toggle(TibetanBalloon.DAY) },
                rows = listOf(
                    BalloonRow(stringResource(R.string.row_weekday), "${day.weekday.english} · gza’ ${day.weekday.wylie}"),
                    BalloonRow(stringResource(R.string.row_planet), day.weekday.planet),
                    BalloonRow(stringResource(R.string.row_element), gloss(day.dayElement)),
                    BalloonRow(stringResource(R.string.row_day_sign), gloss(day.dayAnimal)),
                    BalloonRow(stringResource(R.string.row_gender), gloss(day.dayGender, "inText")),
                ),
            )
            }
        }

        BriefRow(summary) { summaryOpen = true }

        Spacer(Modifier.fillMaxWidth().height(1.dp).background(Palette.line))

        // Readings, each opening its sourced text.
        val observance = stringResource(R.string.tib_monthly_observance)
        val forBirthYear = stringResource(R.string.tib_for_birth_year)
        val pairSubtitle = stringResource(R.string.tib_element_pair_subtitle, day.elementPair.english)
        val haircutTitle = stringResource(R.string.tib_haircut)
        // The haircut as the brief weighs it (SPEC §5.12): the side, and the factors standing on it, strongest first.
        val haircutNote = summary.activities.firstOrNull { it.activity == Activity.HAIRCUTS }
        val haircutTone = haircutNote?.let { if (it.good.isNotEmpty()) Tone.GOOD else Tone.BAD }
        val haircutStanding = haircutNote?.let { it.good + it.avoid }.orEmpty()
        val haircutBy = haircutStanding.firstOrNull()?.let { by ->
            stringResource(R.string.haircut_by, stringResource(if (haircutTone == Tone.GOOD) R.string.brief_good else R.string.brief_avoid), by.kanji)
        }
        val haircutWhy = stringResource(R.string.haircut_note, haircutStanding.joinToString(" · ") { it.kanji })
        val haircutDateTone = if (day.day in Texts.HAIRCUT_GOOD) Tone.GOOD else Tone.BAD
        val haircutDateTitle = stringResource(R.string.haircut_day, day.day, Catalog.text(Texts.HAIRCUT[day.day - 1].arg!!))
        val haircutDateNote = when {
            haircutTone == null || haircutTone == haircutDateTone -> stringResource(R.string.haircut_date_agrees)
            else -> stringResource(R.string.haircut_date_outweighed, haircutStanding.first().kanji)
        }
        val mansionSubtitle = stringResource(R.string.tib_mansion_subtitle, day.mansion.english)
        val yogaSubtitle = stringResource(R.string.tib_yoga_subtitle, day.yoga.english)
        val personalMansionSubtitle = stringResource(R.string.tib_personal_mansion_subtitle, day.mansion.sanskrit)
        val karanaSubtitle = stringResource(R.string.tib_karana_subtitle, day.karana.english)
        val weekdaySubtitle = stringResource(R.string.tib_weekday_subtitle, day.weekday.planet)
        val lunarDateTitle = stringResource(R.string.tib_lunar_date_title, day.day)
        val lunarDateSubtitle = stringResource(R.string.tib_lunar_date_subtitle, LunarDayClass.of(day.day).english)
        val whiteBerylLabel = stringResource(R.string.detail_white_beryl)
        val rahuTitle = stringResource(R.string.tib_rahu_title)
        val rahuSubtitle = stringResource(R.string.tib_rahu_subtitle, day.day)
        val rahuGeneralSubtitle = stringResource(R.string.tib_rahu_general_subtitle, day.day)
        val rahuMonthSubtitle = stringResource(R.string.tib_rahu_month_subtitle, day.monthNames.season)
        val daySmeBa = remember(info.date) { DaySmeBa.of(info.date) }
        val blaMkhyenTitle = stringResource(R.string.tib_bla_mkhyen_title)
        val blaMkhyenSubtitle = stringResource(R.string.tib_bla_mkhyen_subtitle, daySmeBa.sevenRed.english, daySmeBa.number)
        val blaMkhyenDetails = listOf(
            stringResource(R.string.detail_day_sme_ba) to "${daySmeBa.number} · ${Catalog.text("Colour.${SME_BA_COLOURS[daySmeBa.number - 1]}")}",
            stringResource(R.string.detail_sme_ba_count) to stringResource(
                if (daySmeBa.forward) R.string.sme_ba_count_up else R.string.sme_ba_count_down,
                daySmeBa.woodMouse.format(labels.shortDate),
                daySmeBa.woodMouseNumber,
            ),
            stringResource(R.string.detail_seven_red) to daySmeBa.sevenRed.english,
        )
        val nectarTitle = stringResource(R.string.tib_nectar_title)
        val nectarSubtitle = stringResource(
            R.string.tib_nectar_subtitle,
            nectarHours(day.weekday).joinToString(" · ") { h -> "%02d:00–%02d:00".format((5 + h) % 24, (6 + h) % 24) },
        )
        val greatCombinationSubtitle = stringResource(R.string.tib_great_combination_subtitle, "${day.weekday.english} + ${day.mansion.sanskrit}")
        val combinationDaySubtitle = stringResource(R.string.tib_combination_day_subtitle, "${day.weekday.english} + ${day.mansion.sanskrit}")
        val gtsugLagDaySubtitle = stringResource(R.string.tib_gtsug_lag_day_subtitle, "${day.weekday.english} + ${day.mansion.sanskrit}")
        val specialDaysTitle = stringResource(R.string.tib_special_days_title)
        val specialDaysNote = stringResource(R.string.tib_special_days_note)
        val rahuCourses = stringResource(R.string.tib_rahu_courses)
        val annotations = buildList {
            // The festival is the headline, which opens its reading; the Almanac does not repeat it.
            if (holiday == null || holidayAnnotation == null) holidayAnnotation?.let { add(it) }
            day.specialDay?.let { add(Annotation(it.english, observance, Tone.GOOD, Texts.SPECIAL_DAY[it], titleIsKanji = false)) }
            info.personalDay?.let {
                add(Annotation(it.english, forBirthYear, if (it == PersonalDay.ANTI) Tone.BAD else Tone.GOOD, Texts.PERSONAL_DAY[it], titleIsKanji = false))
            }
            info.personalMansions.forEach {
                add(
                    Annotation(
                        it.english,
                        forBirthYear,
                        Texts.personalMansionTone(it),
                        Texts.PERSONAL_MANSION[it],
                        subtitle = personalMansionSubtitle,
                        titleIsKanji = false,
                        details = listOf(whiteBerylLabel to it.wylie),
                    ),
                )
            }
            // The day's readings in the rank of the White Beryl and the kun phan me long (SPEC §5.12):
            // the combinations, the weekday and the mansion, the special days, the date, karaṇa and yoga.
            val great = day.greatCombination
            val greatScript = "${Ewts.toTibetan(great.wylie)} (${great.wylie})"
            add(
                Annotation(
                    great.english.replaceFirstChar(Char::uppercase),
                    greatScript,
                    if (great.lucky) Tone.GOOD else Tone.BAD,
                    Texts.GREAT_COMBINATION[great],
                    subtitle = greatCombinationSubtitle,
                    titleIsKanji = false,
                    details = listOf(whiteBerylLabel to greatScript),
                    diagram = { CombinationTable(day.weekday, day.mansion) },
                ),
            )
            val pair = day.elementPair
            add(
                Annotation(
                    "${day.weekday.element.english} – ${day.mansion.element.english}",
                    "${pair.english} (${pair.wylie})",
                    if (pair.auspicious) Tone.GOOD else Tone.BAD,
                    Texts.ELEMENT_PAIR[pair],
                    subtitle = pairSubtitle,
                    titleIsKanji = false,
                    glyphs = {
                        CueIcon(CueGlyphs.of(day.weekday.element), Palette.muted, 18.dp)
                        Icon(Icons.ChevronRight, contentDescription = null, tint = Palette.faint, modifier = Modifier.size(12.dp))
                        CueIcon(CueGlyphs.of(day.mansion.element), Palette.muted, 18.dp)
                    },
                    diagram = { ElementPairGrid(day.weekday.element, day.mansion.element) },
                ),
            )
            // Rāhu's courses by date and by month, one row: the White Beryl's one Rāhu (SPEC §5.13).
            val rahu = listOfNotNull(
                Texts.RAHU[day.day]?.let { Annotation(rahuTitle, rahuSubtitle, Tone.NEUTRAL, it, subtitle = rahuSubtitle, titleIsKanji = false) },
                Texts.RAHU_GENERAL[day.day]?.let { Annotation(rahuTitle, rahuGeneralSubtitle, Tone.NEUTRAL, it, subtitle = rahuGeneralSubtitle, titleIsKanji = false) },
                Texts.RAHU_MONTH[day.month to day.day]?.let { Annotation(rahuTitle, rahuMonthSubtitle, Tone.NEUTRAL, it, subtitle = rahuMonthSubtitle, titleIsKanji = false) },
            )
            if (rahu.size == 1) {
                add(rahu.single().copy(subtitle = null))
            } else if (rahu.size > 1) {
                add(Annotation(rahuTitle, rahuCourses, Tone.NEUTRAL, null, subtitle = rahu.joinToString(" · ") { it.english }, titleIsKanji = false, parts = rahu))
            }
            add(
                Annotation(
                    blaMkhyenTitle, blaMkhyenSubtitle, Tone.NEUTRAL, Texts.BLA_MKHYEN[daySmeBa.sevenRed], titleIsKanji = false,
                    details = blaMkhyenDetails,
                    diagram = { MovedSmeBaSquare(daySmeBa.number, 7, 200.dp, stringResource(R.string.desc_moved_sme_ba, daySmeBa.number)) },
                ),
            )
            add(
                Annotation(
                    nectarTitle, nectarSubtitle, Tone.GOOD, Texts.NECTAR_PERIODS, titleIsKanji = false,
                    details = listOf(nectarTitle to "${Ewts.toTibetan("bdud rtsi thun mtshams")} (bdud rtsi thun mtshams)"),
                    glyphs = { NectarDial(nectarHours(day.weekday), 24.dp, small = true) },
                    diagram = { NectarDial(nectarHours(day.weekday), 240.dp) },
                ),
            )
            add(
                Annotation(
                    day.weekday.english,
                    day.weekday.planet,
                    Texts.weekdayTone(day.weekday),
                    Texts.WEEKDAY[day.weekday],
                    subtitle = weekdaySubtitle,
                    titleIsKanji = false,
                    glyphs = { CueIcon(CueGlyphs.of(day.weekday.element), Palette.muted, 18.dp) },
                ),
            )
            val mansionReading = Texts.MANSION.getValue(day.mansion)
            add(
                Annotation(
                    day.mansion.sanskrit,
                    day.mansion.english,
                    // The mansion has no tone of its own: its lists name works both ways (SPEC §5.12).
                    Tone.NEUTRAL,
                    mansionReading,
                    subtitle = mansionSubtitle,
                    titleIsKanji = false,
                    glyphs = { MansionRing(day.mansion, 24.dp, small = true) },
                    diagram = { MansionRing(day.mansion, 280.dp) },
                ),
            )
            val specials = day.combinationDays.map { it to false }.plus(day.gtsugLagDays.map { it to true }).map { (c, gtsugLag) ->
                val cScript = "${Ewts.toTibetan(c.wylie)} (${c.wylie})"
                Annotation(
                    c.english.replaceFirstChar(Char::uppercase),
                    cScript,
                    if (c.lucky) Tone.GOOD else Tone.BAD,
                    if (gtsugLag) Texts.GTSUG_LAG_DAY[c] else Texts.COMBINATION_DAY[c],
                    subtitle = if (gtsugLag) gtsugLagDaySubtitle else combinationDaySubtitle,
                    titleIsKanji = false,
                    details = listOf(whiteBerylLabel to cScript),
                )
            }
            // Several special days are one row, as they are one voice in the weighing (SPEC §5.12).
            if (specials.size == 1) {
                add(specials.single())
            } else if (specials.size > 1) {
                add(
                    Annotation(
                        specialDaysTitle,
                        specialDaysNote,
                        specials.map { it.tone }.distinct().singleOrNull() ?: Tone.MIXED,
                        null,
                        subtitle = specials.joinToString(" · ") { it.title },
                        titleIsKanji = false,
                        parts = specials,
                    ),
                )
            }
            add(
                Annotation(
                    lunarDateTitle,
                    LunarDayClass.of(day.day).english,
                    Texts.lunarDateTone(day.day),
                    Texts.LUNAR_DATE[day.day - 1],
                    subtitle = lunarDateSubtitle,
                    titleIsKanji = false,
                    glyphs = { LunarDateStrip(day.day) },
                    diagram = { LunarDateGrid(day.day) },
                ),
            )
            add(
                Annotation(
                    day.karana.sanskrit,
                    day.karana.english,
                    Texts.KARANA_TONE.getValue(day.karana),
                    Texts.KARANA[day.karana],
                    subtitle = karanaSubtitle,
                    titleIsKanji = false,
                    details = listOf(whiteBerylLabel to day.karana.whiteBeryl),
                    glyphs = { KaranaRing(day.karana, 24.dp, small = true) },
                    diagram = { KaranaRing(day.karana, 280.dp) },
                ),
            )
            add(
                Annotation(
                    day.yoga.sanskrit,
                    day.yoga.english,
                    Texts.YOGA_TONE.getValue(day.yoga),
                    Texts.YOGA[day.yoga],
                    subtitle = yogaSubtitle,
                    titleIsKanji = false,
                    details = listOf(whiteBerylLabel to day.yoga.whiteBeryl),
                    glyphs = { YogaRing(day.yoga, 24.dp, small = true) },
                    diagram = { YogaRing(day.yoga, 280.dp) },
                ),
            )
            // The row is the haircut weighed as the brief weighs it (ROADMAP T2.1, SPEC §10.3); FPMT's day for the
            // date is one of the date's lists, shown in the sheet as such, outweighed where it is.
            val haircut = Texts.HAIRCUT[day.day - 1]
            val dateDay = Annotation(
                haircutDateTitle,
                haircutDateNote,
                haircutDateTone,
                haircut,
                titleIsKanji = false,
                diagram = { HaircutGrid(day.day) },
            )
            add(
                if (haircutTone == null || haircutBy == null) {
                    dateDay.copy(title = haircutTitle, english = Catalog.text(haircut.arg!!), glyphs = { CueIcon(CueGlyphs.FAMILY.getValue(ActivityFamily.HAIRCUT), Palette.muted, 18.dp) })
                } else {
                    Annotation(
                        haircutTitle,
                        haircutWhy,
                        haircutTone,
                        null,
                        subtitle = haircutBy,
                        titleIsKanji = false,
                        lead = { Box(Modifier.size(8.dp).background(toneColor(haircutTone), CircleShape)) },
                        glyphs = { CueIcon(CueGlyphs.FAMILY.getValue(ActivityFamily.HAIRCUT), Palette.muted, 18.dp) },
                        parts = listOf(dateDay),
                    )
                },
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.weight(1f)) { SectionTitle(stringResource(R.string.section_almanac)) }
            IconButton(onClick = { hoursOpen = true }) {
                Icon(Icons.Clock, contentDescription = stringResource(R.string.hours_open), tint = Palette.muted)
            }
        }
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
            FactRow(
                stringResource(R.string.row_weekday),
                "gza’ ${day.weekday.wylie}",
                "${day.weekday.english} · ${day.weekday.planet} · ${day.weekday.element.english}",
                lead = { CueIcon(CueGlyphs.of(day.weekday.element), Palette.muted, 20.dp) },
            )
            FactRow(
                stringResource(R.string.row_lunar_mansion),
                day.mansion.wylie,
                "${day.mansion.sanskrit} — ${day.mansion.english} · ${day.mansion.element.english}",
                lead = { CueIcon(CueGlyphs.of(day.mansion.element), Palette.muted, 20.dp) },
            )
            FactRow(stringResource(R.string.row_karana), day.karana.wylie, "${day.karana.sanskrit} — ${day.karana.english}")
            FactRow(stringResource(R.string.row_yoga), day.yoga.wylie, "${day.yoga.sanskrit} — ${day.yoga.english}")
        }

        SectionTitle(stringResource(R.string.section_lunar_day))
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            val animalAnnotation = Annotation(
                gloss(day.lunarDayAnimal),
                stringResource(R.string.tib_animal_subtitle),
                Tone.NEUTRAL,
                Texts.EARTH_LORD[day.lunarDayAnimal],
                titleIsKanji = false,
            )
            FactRow(
                stringResource(R.string.row_date_animal),
                gloss(day.lunarDayAnimal),
                gloss(day.lunarDayAnimal),
                tibetan = false,
                lead = { CueIcon(CueGlyphs.ANIMAL.getValue(day.lunarDayAnimal), Palette.text, 22.dp) },
                onClick = { sheet = animalAnnotation },
            )
            val goddessScript = "${Ewts.toTibetan(day.trigram.goddess)} (${day.trigram.goddess})"
            val trigramAnnotation = Annotation(
                goddessScript,
                stringResource(R.string.tib_trigram_subtitle, day.trigram.wylie),
                Tone.NEUTRAL,
                Texts.TRIGRAM[day.trigram],
                titleIsKanji = false,
                details = listOf(whiteBerylLabel to goddessScript),
            )
            FactRow(
                stringResource(R.string.row_trigram),
                day.trigram.wylie,
                "${day.trigram.chinese} — ${day.trigram.english}",
                lead = { TrigramBars(day.trigram, 22.dp) },
                onClick = { sheet = trigramAnnotation },
            )
            val colour = SME_BA_COLOURS[day.smeBa - 1]
            val number = "${day.smeBa} · ${Catalog.text("Colour.$colour")}"
            FactRow(
                stringResource(R.string.row_date_sme_ba),
                "${day.smeBa}",
                number,
                tibetan = false,
                lead = { SmeBaSquare(day.smeBa, 30.dp, stringResource(R.string.desc_sme_ba, day.smeBa)) },
            )
        }
    }

    if (summaryOpen) DaySummarySheet(summary, festival = day.holiday != null || day.specialDay != null) { summaryOpen = false }
    if (hoursOpen) HoursSheet(info.date, day, info.birthSign, info.signs, zone, onOpen = { sheet = it }) { hoursOpen = false }
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
 * One aspect of the birth year against the same aspect of the lunar date, or
 * of one of its hours (the White Beryl's divination of health, which reads
 * vitality and body): its pebbles as the charts write them, white noughts and
 * black crosses, and on tap how the date's or hour's element was worked out.
 */
internal fun pebbleAnnotation(force: Force, birth: Sign, signs: DaySigns, day: TibetanDay, labels: Labels, hour: HourSign? = null): Annotation {
    val sign = hour?.sign ?: signs.date
    val c = ForceContrast(force, birth.forces[force], sign.forces[force])
    val p = c.pebbles
    val aspect = force.english.replaceFirstChar(Char::uppercase)
    val spoken = when {
        p.black == 0 -> labels.plural(R.plurals.pebbles_white, p.white)
        p.white == 0 -> labels.plural(R.plurals.pebbles_black, p.black)
        else -> labels.string(R.string.pebbles_mixed, p.white, p.black)
    }
    fun name(s: Sign) = labels.string(R.string.element_animal, gloss(s.element), gloss(s.animal))
    val details = buildList {
        hour?.let { h -> add(labels.string(R.string.detail_hour) to labels.string(R.string.detail_hour_value, hourName(h, labels), hourSpan(h), name(h.sign))) }
        add(labels.string(R.string.detail_date) to labels.string(R.string.detail_date_value, labels.ordinal(day.day), name(signs.date)))
        add(labels.string(R.string.detail_month) to labels.string(R.string.detail_month_value, labels.month(day.month, day.leapMonth), name(signs.month)))
        add(labels.string(R.string.row_year) to name(signs.year))
        add(
            labels.string(R.string.detail_counted) to if (hour == null) {
                labels.string(R.string.detail_counted_day, gloss(signs.year.element, "inText"), gloss(signs.month.element, "inText"))
            } else {
                labels.string(R.string.detail_counted_hour, gloss(signs.date.element, "inText"))
            },
        )
        add(labels.string(if (hour == null) R.string.detail_its_day else R.string.detail_its_hour, force.english).replaceFirstChar(Char::uppercase) to gloss(sign.forces[force]))
        add(labels.string(R.string.detail_yours) to labels.string(R.string.detail_yours_value, gloss(birth.forces[force]), name(birth)))
        add(labels.string(R.string.detail_relation) to "${c.kinship.english} · $p")
    }
    return Annotation(
        title = labels.string(R.string.pebble_row_title, aspect, p.toString()),
        english = c.kinship.english,
        tone = pebbleTone(p),
        reading = Texts.PEBBLES[c.kinship],
        subtitle = labels.string(
            if (hour == null) R.string.pebble_subtitle else R.string.pebble_subtitle_hour,
            c.kinship.english,
            gloss(c.other, "inText"),
            gloss(c.own, "inText"),
        ),
        titleIsKanji = false,
        spokenTitle = "$aspect, $spoken",
        details = details,
    )
}

/** Lucky for white pebbles only, unlucky for black only, mixed for both. */
internal fun pebbleTone(p: Pebbles): Tone = when {
    p.black == 0 -> Tone.GOOD
    p.white == 0 -> Tone.BAD
    else -> Tone.MIXED
}

/** "Bird hour". */
internal fun hourName(h: HourSign, labels: Labels): String = labels.string(R.string.hour_name, gloss(h.sign.animal))

/** "17:00–19:00", clock time. */
internal fun hourSpan(h: HourSign): String {
    fun hhmm(m: Int) = "%02d:%02d".format((m / 60) % 24, m % 60)
    return "${hhmm(h.startMinute)}–${hhmm(h.startMinute + 120)}"
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
fun FactRow(
    label: String,
    term: String,
    english: String,
    tibetan: Boolean = true,
    kanji: Boolean = false,
    swatch: Color? = null,
    /** Glyphs drawn between the label and the term (SPEC §10.4). */
    lead: (@Composable () -> Unit)? = null,
    /** Opens the term's reading, for the few facts that have one. */
    onClick: (() -> Unit)? = null,
) {
    Row(
        Modifier.fillMaxWidth().heightIn(min = 40.dp).let { if (onClick != null) it.clickable(onClick = onClick) else it },
        verticalAlignment = if (tibetan && lead == null) Alignment.Top else Alignment.CenterVertically,
    ) {
        Text(
            label,
            style = body.copy(fontSize = 14.sp, color = Palette.muted),
            modifier = Modifier.width(120.dp).padding(end = 8.dp).let { if (tibetan && lead == null) it.alignByBaseline() else it },
        )
        if (lead != null) {
            Row(Modifier.padding(end = 10.dp), horizontalArrangement = Arrangement.spacedBy(4.dp), verticalAlignment = Alignment.CenterVertically) { lead() }
        }
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
            TibetanTerm(term, english, preferAbove = true, modifier = if (lead == null) Modifier.alignByBaseline() else Modifier)
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
