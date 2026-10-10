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
import androidx.compose.ui.text.AnnotatedString
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
import zanshin.core.texts.DayFactor
import zanshin.core.texts.DaySummary
import zanshin.core.texts.Texts
import zanshin.core.texts.gloss
import zanshin.core.texts.toneOf
import zanshin.core.texts.sharedTone
import zanshin.core.astro.Place
import zanshin.core.tibetan.DaySigns
import zanshin.core.tibetan.Eclipses
import zanshin.core.tibetan.DaySmeBa
import zanshin.core.tibetan.Force
import zanshin.core.tibetan.GreatBlackDay
import zanshin.core.tibetan.Pebbles
import zanshin.core.tibetan.HourSign
import java.time.ZoneId
import zanshin.core.tibetan.ForceContrast
import zanshin.core.tibetan.Forces
import zanshin.core.tibetan.OwnDay
import zanshin.core.tibetan.PersonalDay
import zanshin.core.tibetan.RahuCourse
import zanshin.core.tibetan.RahuMove
import zanshin.core.tibetan.Repetition
import zanshin.core.tibetan.SME_BA_COLOURS
import zanshin.core.tibetan.Sign
import zanshin.core.tibetan.TibetanDay
import zanshin.core.tibetan.YearForces
import zanshin.core.tibetan.YearOfLife
import zanshin.core.tibetan.TibetanCalendar
import zanshin.app.Person
import zanshin.core.tibetan.Ewts
import zanshin.core.tibetan.RahuBySeason
import zanshin.core.tibetan.EarthLordCourses
import zanshin.core.tibetan.MonthEntries
import zanshin.core.tibetan.Weekday
import zanshin.core.tibetan.SeasonReckoning
import zanshin.core.tibetan.CourseEvent
import zanshin.core.tibetan.CourseDay
import zanshin.core.tibetan.EarthLordCourse
import zanshin.core.tibetan.LunarDayClass
import zanshin.core.tibetan.DayLetters
import zanshin.core.tibetan.Letter
import zanshin.core.tibetan.Thl
import zanshin.core.tibetan.DayTimes
import zanshin.core.tibetan.Karana
import zanshin.core.tibetan.element

private enum class TibetanBalloon { MONTH, YEAR }

/** The Tibetan view of one day (SPEC §10.3); a work in its brief or a reading's lists opens its election through [onElect]. */
@Composable
fun TibetanPage(
    info: DayInfo,
    zone: ZoneId,
    modifier: Modifier = Modifier,
    person: Person? = null,
    /** The place whose sun gives the hours' rough times; none when no place is set. */
    place: Place? = null,
    onElect: (Activity) -> Unit = {},
) {
    val day = info.tibetan
    val labels = LocalLabels.current
    val accent = Palette.saffron
    var balloon by remember(day.jd) { mutableStateOf<TibetanBalloon?>(null) }
    var sheet by remember(day.jd) { mutableStateOf<Annotation?>(null) }
    var summaryOpen by remember(day.jd) { mutableStateOf(false) }
    var hoursOpen by remember(day.jd) { mutableStateOf(false) }
    var yearOpen by remember(day.jd) { mutableStateOf(false) }
    // The hour the panel opens at when the brief opens it; the clock icon opens it at the present hour.
    var hoursFrom by remember(day.jd) { mutableStateOf<Int?>(null) }
    val summary = remember(day.jd, labels.locale, info.personalDay, info.personalMansions, info.ownDays) {
        DaySummary.of(day, info.personalDay, info.personalMansions, info.ownDays)
    }
    fun toggle(b: TibetanBalloon) {
        balloon = if (balloon == b) null else b
    }
    // The times within the day (SPEC §5.8): WB writes a second mansion that comes in daytime, and marks a burning
    // date begun in it, the day's length its own by the sun (vol. 1, p. 182).
    val next = remember(day.jd) { TibetanCalendar.of(day.jd + 1) }
    val nightfall = remember(day.jd) { DayTimes.dayLength(day) }
    val secondMansions = remember(day.jd, nightfall) { DayTimes.mansions(day, next).filter { it.at < nightfall } }
    val skippedYogas = remember(day.jd) { DayTimes.skippedYogas(day, next) }
    val visti = remember(day.jd) { DayTimes.visti(day) }
    val sunTerms = remember(day.jd) { DayTimes.sunTerms(day) }
    val burningFrom = remember(day.jd, nightfall) { DayTimes.burningFrom(day, nightfall) }

    val holiday = day.holiday
    val holidayAnnotation = holiday?.let {
        Annotation(it.festival.namedTitle, it.festival.english, Tone.GOOD, Texts.TIBETAN_FESTIVAL[it.festival], titleIsKanji = false)
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
                // A title in phonetics carries its Tibetan (SPEC §8.1), on its own line under the headline.
                holiday.festival.wylie?.let { w ->
                    Text(withTibetan(Ewts.named(holiday.name, w).removePrefix(holiday.name).trim()), style = body.copy(fontSize = 16.sp, color = Palette.muted))
                }
                Text(withTibetan(holiday.festival.english), style = body.copy(color = Palette.muted, lineHeight = 22.sp))
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
            // The month by its Sanskrit name, as English writes it, with its Tibetan (SPEC §8.1).
            val monthWylie = day.monthNames.wylie
            val monthSaid = remember(monthWylie) { Thl.toPhonetic(monthWylie) }
            val monthBracket = SpanStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal, color = Palette.muted)
            BalloonText(
                text = buildAnnotatedString {
                    append("${labels.month(day.month, day.leapMonth)} · ")
                    append(namedTerm(day.monthNames.sanskrit, monthWylie, monthBracket))
                },
                style = body.copy(fontSize = 22.sp, fontWeight = FontWeight.Medium),
                open = balloon == TibetanBalloon.MONTH,
                onToggle = { toggle(TibetanBalloon.MONTH) },
                underline = false,
                description = "${labels.month(day.month, day.leapMonth)}, ${monthSaid ?: monthWylie}",
                rows = listOfNotNull(
                    monthSaid?.let { BalloonRow(stringResource(R.string.row_say), it) },
                    BalloonRow(stringResource(R.string.row_animal), stringResource(R.string.tib_animal_month, gloss(day.monthNames.animal))),
                    BalloonRow(stringResource(R.string.row_season_kalacakra), day.monthNames.season),
                    BalloonRow(stringResource(R.string.row_season_chinese), day.monthNames.chineseSeason),
                    BalloonRow(stringResource(R.string.row_element), gloss(info.signs.month.element)),
                ) + monthEntryRows(day, labels) + aspectRows(info.signs.month.forces, info.birthSign?.forces, listOf(Force.VITALITY, Force.BODY)),
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
                actions = listOfNotNull(
                    person?.let { p ->
                        val age = YearOfLife.age(TibetanCalendar.of(p.birth).year, day.year)
                        if (age >= 1) BalloonAction(stringResource(R.string.year_action, labels.ordinal(age))) { yearOpen = true } else null
                    },
                ),
            )
            }
        }

        // The hours above the day (ROADMAP U6): the combination period, which outweighs every factor of the
        // day while it lasts (SPEC §5.12), stands before the day in brief.
        val (currentHour, nowMinute) = rememberCurrentHour(info.date, zone)
        val hourSigns = remember(info.signs.date) { Forces.hours(info.signs.date) }
        HoursRow(summary, day, hourSigns, currentHour, nowMinute) { hoursFrom = it; hoursOpen = true }

        BriefRow(summary) { summaryOpen = true }

        Spacer(Modifier.fillMaxWidth().height(1.dp).background(Palette.line))

        // Readings, each opening its sourced text.
        val observance = stringResource(R.string.tib_monthly_observance)
        val forBirthYear = stringResource(R.string.tib_for_birth_year)
        val pairSubtitle = stringResource(R.string.tib_element_pair_subtitle, day.elementPair.english)
        val haircutTitle = stringResource(R.string.tib_haircut)
        // The haircut as the brief weighs it (SPEC §5.12): the side, and the factors standing on it, strongest first.
        val haircutSide = summary.sideOf(Activity.HAIRCUTS)
        val haircutTone = haircutSide?.first
        val haircutStanding = haircutSide?.second.orEmpty()
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
        val forBirthDate = stringResource(R.string.tib_for_birth_date)
        val forLifeForce = info.ownElement?.let { stringResource(R.string.tib_for_life_force, gloss(it, "inText")) }.orEmpty()
        val birthMansionSubtitle = stringResource(R.string.tib_birth_mansion_subtitle, day.mansion.sanskrit)
        val yourWeekdayTitle = stringResource(R.string.tib_your_weekday_title)
        val yourWeekdayNote = stringResource(R.string.tib_your_weekday_note)
        val karanaSubtitle = stringResource(R.string.tib_karana_subtitle, day.karana.english)
        val secondMansionLabel = stringResource(R.string.detail_second_mansion)
        val skippedYogaLabel = stringResource(R.string.detail_skipped_yoga)
        val vistiLabel = stringResource(R.string.detail_visti)
        fun timeOf(chuTshod: Double) = clockOf(chuTshod, labels)
        fun mansionThen(name: String, time: String) = labels.string(R.string.tib_mansion_then, name, time)
        fun yogaSkipped(name: String, from: String, to: String) = labels.string(R.string.tib_yoga_skipped, name, from, to)
        fun vistiSpan(from: String, to: String) = labels.string(R.string.tib_visti_span, from, to)
        fun spanOf(from: String, to: String) = labels.string(R.string.hours_visti_subtitle, from, to)
        fun detailFrom(name: String, time: String) = labels.string(R.string.detail_from, name, time)
        fun detailSpan(name: String, from: String, to: String) = labels.string(R.string.detail_span, name, from, to)
        val weekdaySubtitle = stringResource(R.string.tib_weekday_subtitle, day.weekday.planet)
        val lunarDateTitle = stringResource(R.string.tib_lunar_date_title, day.day)
        val lunarDateSubtitle = stringResource(R.string.tib_lunar_date_subtitle, LunarDayClass.of(day.day).english)
        val tibetanLabel = stringResource(R.string.row_tibetan)
        val alsoCalledLabel = stringResource(R.string.detail_also_called)
        val hairWashingLabel = stringResource(R.string.detail_hair_washing)
        val rahuTitle = stringResource(R.string.tib_rahu_title)
        val rahuSubtitle = stringResource(R.string.tib_rahu_subtitle, day.day)
        val rahuGeneralSubtitle = stringResource(R.string.tib_rahu_general_subtitle, day.day)
        val rahuMonthSubtitle = stringResource(R.string.tib_rahu_month_subtitle, day.monthNames.chineseSeason)
        val rahuSeasonSubtitle = stringResource(R.string.tib_rahu_season_subtitle, day.monthNames.chineseSeason)
        // Rāhu's compass: the course its date's reading gives, the detailed where it names one (SPEC §10.7).
        val rahuMove = RahuCourse.of(day.day)
        val rahuCaption = when (rahuMove) {
            is RahuMove.Across -> stringResource(
                if (RahuCourse.isGeneral(day.day)) R.string.rahu_course_general else R.string.rahu_course_detailed,
                day.day,
                rahuMove.from.english,
                rahuMove.to.english,
            )
            RahuMove.IntoTheLake -> stringResource(R.string.rahu_course_lake, day.day)
            RahuMove.Everywhere -> stringResource(R.string.rahu_course_everywhere, day.day)
        }
        val rahuMark: @Composable () -> Unit = { RahuCompass(rahuMove, 24.dp, rahuCaption, small = true) }
        val rahuDiagram: @Composable () -> Unit = { RahuCompass(rahuMove, 200.dp, rahuCaption) }
        val daySmeBa = remember(info.date) { DaySmeBa.of(info.date) }
        val blaMkhyenTitle = stringResource(R.string.tib_bla_mkhyen_title)
        val blaMkhyenSubtitle = stringResource(R.string.tib_bla_mkhyen_subtitle, daySmeBa.sevenRed.english, daySmeBa.number)
        val blaMkhyenCaption = stringResource(R.string.bla_mkhyen_compass_caption, daySmeBa.sevenRed.english)
        val blaMkhyenDetails = listOf(
            stringResource(R.string.detail_day_sme_ba) to "${daySmeBa.number} · ${Catalog.text("Colour.${SME_BA_COLOURS[daySmeBa.number - 1]}")}",
            stringResource(R.string.detail_sme_ba_count) to stringResource(
                if (daySmeBa.forward) R.string.sme_ba_count_up else R.string.sme_ba_count_down,
                daySmeBa.woodMouse.format(labels.shortDate),
                daySmeBa.woodMouseNumber,
            ),
            stringResource(R.string.detail_seven_red) to daySmeBa.sevenRed.english,
        )
        val combinationTitle = stringResource(R.string.tib_combination_title)
        val combinationNote = stringResource(R.string.tib_combination_note)
        // The combination's row names both parts and whether they agree (ROADMAP U1, SPEC §5.12).
        val combinationSubtitle = stringResource(
            when {
                day.greatCombination.lucky != day.elementPair.auspicious -> R.string.tib_combination_disagree
                day.greatCombination.lucky -> R.string.tib_combination_both_lucky
                else -> R.string.tib_combination_both_unlucky
            },
            day.greatCombination.english.replaceFirstChar(Char::uppercase),
            "${day.weekday.element.english}–${day.mansion.element.english}",
        )
        val greatCombinationSubtitle = stringResource(R.string.tib_great_combination_subtitle, "${day.weekday.english} + ${day.mansion.sanskrit}")
        val combinationDaySubtitle = stringResource(R.string.tib_combination_day_subtitle, "${day.weekday.english} + ${day.mansion.sanskrit}")
        val gtsugLagDaySubtitle = stringResource(R.string.tib_gtsug_lag_day_subtitle, "${day.weekday.english} + ${day.mansion.sanskrit}")
        val specialDaysTitle = stringResource(R.string.tib_special_days_title)
        val specialDaysNote = stringResource(R.string.tib_special_days_note)
        val burningDateSubtitle = stringResource(R.string.tib_burning_date_subtitle, "${day.weekday.english} + $lunarDateTitle")
        val rahuCourses = stringResource(R.string.tib_rahu_courses)
        // The row of the factor that decided the day's tone carries a mark (ROADMAP U2).
        fun decides(factor: DayFactor) = summary.verdict?.factor == factor
        // Your day (ROADMAP U3): the person's own days and mansions, which the weighing shows but does not
        // weigh (SPEC §5.12), above the vitality and body of the date against the birth year.
        val yours = buildList {
            // The roles the weekday holds for the person, by the birth year's animal (p. 330), the weekday
            // of birth and the life force's element (p. 338): one row (ROADMAP T2.19).
            val weekdayRoles = listOfNotNull(
                info.personalDay?.let {
                    Annotation(it.english, forBirthYear, if (it == PersonalDay.ANTI) Tone.BAD else Tone.GOOD, Texts.PERSONAL_DAY[it], titleIsKanji = false)
                },
            ) + info.ownDays.filter { it.isWeekday }.map {
                Annotation(
                    it.english,
                    if (it == OwnDay.BIRTH_WEEKDAY) forBirthDate else forLifeForce,
                    Texts.ownDayTone(it),
                    Texts.OWN_DAY[it],
                    titleIsKanji = false,
                    details = listOf(tibetanLabel to Ewts.named(it.english, it.wylie)),
                )
            }
            when (weekdayRoles.size) {
                0 -> {}
                1 -> add(weekdayRoles.single())
                else -> add(
                    Annotation(
                        yourWeekdayTitle,
                        yourWeekdayNote,
                        sharedTone(weekdayRoles.map { it.tone }),
                        null,
                        subtitle = weekdayRoles.joinToString(" · ") { it.title },
                        titleIsKanji = false,
                        parts = weekdayRoles,
                    ),
                )
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
                        details = listOf(tibetanLabel to Ewts.named(it.english, it.wylie)),
                    ),
                )
            }
            info.ownDays.filter { !it.isWeekday }.forEach {
                add(
                    Annotation(
                        it.english,
                        forBirthDate,
                        Texts.ownDayTone(it),
                        Texts.OWN_DAY[it],
                        subtitle = birthMansionSubtitle,
                        titleIsKanji = false,
                        details = listOf(tibetanLabel to Ewts.named(it.english, it.wylie)),
                    ),
                )
            }
        }
        // The Almanac (ROADMAP U3): the monthly observance, the weighed voices in rank, then the haircut.
        val annotations = buildList {
            // The festival is the headline, which opens its reading; the Almanac does not repeat it.
            day.specialDay?.let { add(Annotation(it.english, observance, Tone.GOOD, Texts.SPECIAL_DAY[it], titleIsKanji = false)) }
            // An eclipse by WB's reckoning (vol. 1, ch. 9): a day of multiplied virtue, naming no works, so shown and not weighed.
            Eclipses.of(day)?.let { eclipse ->
                val moon = eclipse.body == Eclipses.Body.MOON
                add(
                    Annotation(
                        stringResource(if (moon) R.string.tib_lunar_eclipse else R.string.tib_solar_eclipse),
                        stringResource(if (moon) R.string.tib_lunar_eclipse_subtitle else R.string.tib_solar_eclipse_subtitle),
                        Tone.NEUTRAL,
                        if (moon) Texts.LUNAR_ECLIPSE else Texts.SOLAR_ECLIPSE,
                        titleIsKanji = false,
                    ),
                )
            }
            // The day's readings in the rank of the White Beryl and the kun phan me long (SPEC §5.12):
            // the combination, Rāhu, the weekday and the mansion, the special days, the date, karaṇa and yoga.
            val great = day.greatCombination
            val greatScript = Ewts.named(great.english, great.wylie)
            val pair = day.elementPair
            val pairElements: @Composable () -> Unit = {
                CueIcon(CueGlyphs.of(day.weekday.element), Palette.muted, 18.dp)
                Icon(Icons.ChevronRight, contentDescription = null, tint = Palette.faint, modifier = Modifier.size(12.dp))
                CueIcon(CueGlyphs.of(day.mansion.element), Palette.muted, 18.dp)
            }
            // The named combination and the element pair are one voice, so one row (ROADMAP U1): their dots side
            // by side, a tone only where they agree, and the sheet gives each reading in turn.
            val combination = listOf(
                Annotation(
                    great.english.replaceFirstChar(Char::uppercase),
                    greatScript,
                    if (great.lucky) Tone.GOOD else Tone.BAD,
                    Texts.GREAT_COMBINATION[great],
                    subtitle = greatCombinationSubtitle,
                    titleIsKanji = false,
                    details = listOf(tibetanLabel to greatScript),
                    diagram = { CombinationTable(day.weekday, day.mansion) },
                ),
                Annotation(
                    "${day.weekday.element.english} – ${day.mansion.element.english}",
                    Ewts.named(pair.english, pair.wylie),
                    if (pair.auspicious) Tone.GOOD else Tone.BAD,
                    Texts.ELEMENT_PAIR[pair],
                    subtitle = pairSubtitle,
                    titleIsKanji = false,
                    glyphs = pairElements,
                    diagram = { ElementPairGrid(day.weekday.element, day.mansion.element) },
                ),
            )
            add(
                Annotation(
                    combinationTitle,
                    combinationNote,
                    combination.map { it.tone }.distinct().singleOrNull() ?: Tone.MIXED,
                    null,
                    subtitle = combinationSubtitle,
                    titleIsKanji = false,
                    glyphs = pairElements,
                    parts = combination,
                    decides = decides(DayFactor.GREAT_COMBINATION),
                ),
            )
            // Rāhu's courses by date and by month, one row: the White Beryl's one Rāhu (SPEC §5.13).
            val rahu = listOfNotNull(
                Texts.RAHU[day.day]?.let { Annotation(rahuTitle, rahuSubtitle, Tone.NEUTRAL, it, subtitle = rahuSubtitle, titleIsKanji = false, glyphs = rahuMark, diagram = rahuDiagram) },
                Texts.RAHU_GENERAL[day.day]?.let { Annotation(rahuTitle, rahuGeneralSubtitle, Tone.NEUTRAL, it, subtitle = rahuGeneralSubtitle, titleIsKanji = false, glyphs = rahuMark, diagram = rahuDiagram) },
                Texts.RAHU_MONTH[day.month to day.day]?.let { Annotation(rahuTitle, rahuMonthSubtitle, Tone.NEUTRAL, it, subtitle = rahuMonthSubtitle, titleIsKanji = false) },
                RahuBySeason.of(day.month, day.day)?.let { Texts.RAHU_SEASON[it] }?.let { Annotation(rahuTitle, rahuSeasonSubtitle, Tone.NEUTRAL, it, subtitle = rahuSeasonSubtitle, titleIsKanji = false) },
            )
            if (rahu.size == 1) {
                add(rahu.single().copy(subtitle = null))
            } else if (rahu.size > 1) {
                add(Annotation(rahuTitle, rahuCourses, Tone.NEUTRAL, null, subtitle = rahu.joinToString(" · ") { it.english }, titleIsKanji = false, glyphs = rahuMark, parts = rahu))
            }
            add(
                Annotation(
                    day.weekday.english,
                    day.weekday.planet,
                    Texts.weekdayTone(day.weekday),
                    Texts.WEEKDAY[day.weekday],
                    subtitle = weekdaySubtitle,
                    titleIsKanji = false,
                    glyphs = { CueIcon(CueGlyphs.of(day.weekday.element), Palette.muted, 18.dp) },
                    tibetan = "gza' ${day.weekday.wylie}" to day.weekday.english,
                    decides = decides(DayFactor.WEEKDAY),
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
                    subtitle = (listOf(mansionSubtitle) + secondMansions.map { mansionThen(it.what.sanskrit, timeOf(it.at)) }).joinToString(" · "),
                    titleIsKanji = false,
                    tibetan = day.mansion.wylie to day.mansion.english,
                    details = secondMansions.map { secondMansionLabel to detailFrom(Ewts.named(it.what.english, it.what.wylie), timeOf(it.at)) },
                    glyphs = { MansionRing(day.mansion, 24.dp, small = true) },
                    diagram = { MansionRing(day.mansion, 280.dp) },
                ),
            )
            val specials = day.combinationDays.map { it to false }.plus(day.gtsugLagDays.map { it to true }).map { (c, gtsugLag) ->
                val cScript = Ewts.named(c.english, c.wylie)
                Annotation(
                    c.english.replaceFirstChar(Char::uppercase),
                    cScript,
                    if (c.lucky) Tone.GOOD else Tone.BAD,
                    if (gtsugLag) Texts.GTSUG_LAG_DAY[c] else Texts.COMBINATION_DAY[c],
                    subtitle = if (gtsugLag) gtsugLagDaySubtitle else combinationDaySubtitle,
                    titleIsKanji = false,
                    details = listOf(tibetanLabel to cScript),
                )
            } + listOfNotNull(
                // The burning date stands with them, as in the White Beryl's almanac (SPEC §5.12).
                if (day.burningDate) {
                    val bScript = Ewts.named(Catalog.text("BurningDate"), "bsreg tshes")
                    Annotation(
                        Catalog.text("BurningDate").replaceFirstChar(Char::uppercase),
                        bScript,
                        Tone.BAD,
                        Texts.BURNING_DATE,
                        subtitle = burningDateSubtitle,
                        titleIsKanji = false,
                        details = listOf(tibetanLabel to bScript),
                    )
                } else {
                    null
                },
                // The burning date of the day after, begun in daylight (WB vol. 1, p. 177, entry 11).
                burningFrom?.let { from ->
                    val bScript = Ewts.named(Catalog.text("BurningDate"), "bsreg tshes")
                    Annotation(
                        Catalog.text("BurningDate").replaceFirstChar(Char::uppercase),
                        bScript,
                        Tone.BAD,
                        Texts.BURNING_DATE,
                        subtitle = labels.string(R.string.tib_burning_from_subtitle, labels.ordinal(day.day % 30 + 1), clockOf(from, labels)),
                        titleIsKanji = false,
                        details = listOf(tibetanLabel to bScript, stringResource(R.string.detail_burning_hook) to stringResource(R.string.burning_hook)),
                    )
                },
            )
            // Several special days are one row, as they are one voice in the weighing (SPEC §5.12).
            if (specials.size == 1) {
                add(specials.single().copy(decides = decides(DayFactor.COMBINATION_DAY)))
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
                        decides = decides(DayFactor.COMBINATION_DAY),
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
                    // WB's own result of washing the hair on the date (p. 404, ROADMAP T2.15).
                    details = listOf(hairWashingLabel to Catalog.text(Texts.HAIR_DATE[day.day - 1].arg!!)),
                    glyphs = { LunarDateStrip(day.day) },
                    diagram = { LunarDateGrid(day.day) },
                    decides = decides(DayFactor.LUNAR_DATE),
                ),
            )
            add(
                Annotation(
                    day.karana.sanskrit,
                    day.karana.english,
                    Texts.KARANA_TONE.getValue(day.karana),
                    Texts.KARANA[day.karana],
                    subtitle = (listOf(karanaSubtitle) + listOfNotNull(visti?.let { if (day.karana == Karana.VISHTI) spanOf(timeOf(it.start), timeOf(it.end)) else vistiSpan(timeOf(it.start), timeOf(it.end)) })).joinToString(" · "),
                    titleIsKanji = false,
                    tibetan = day.karana.wylie to day.karana.english,
                    details = listOfNotNull(day.karana.takeIf { it.whiteBeryl != it.wylie }?.let { alsoCalledLabel to Ewts.named(it.english, it.whiteBeryl) }) +
                        listOfNotNull(visti?.let { vistiLabel to detailSpan(Ewts.named(Karana.VISHTI.english, Karana.VISHTI.whiteBeryl), timeOf(it.start), timeOf(it.end)) }),
                    glyphs = { KaranaRing(day.karana, 24.dp, small = true) },
                    diagram = { KaranaRing(day.karana, 280.dp) },
                    decides = decides(DayFactor.KARANA),
                ),
            )
            add(
                Annotation(
                    day.yoga.sanskrit,
                    day.yoga.english,
                    Texts.YOGA_TONE.getValue(day.yoga),
                    Texts.YOGA[day.yoga],
                    subtitle = (listOf(yogaSubtitle) + skippedYogas.map { yogaSkipped(it.what.sanskrit, timeOf(it.at), timeOf(it.until!!)) }).joinToString(" · "),
                    titleIsKanji = false,
                    tibetan = day.yoga.wylie to day.yoga.english,
                    details = listOfNotNull(day.yoga.takeIf { it.whiteBeryl != it.wylie }?.let { alsoCalledLabel to Ewts.named(it.english, it.whiteBeryl) }) +
                        skippedYogas.map { skippedYogaLabel to detailSpan(Ewts.named(it.what.english, it.what.wylie), timeOf(it.at), timeOf(it.until!!)) },
                    glyphs = { YogaRing(day.yoga, 24.dp, small = true) },
                    diagram = { YogaRing(day.yoga, 280.dp) },
                    decides = decides(DayFactor.YOGA),
                ),
            )
        }
        // Works (ROADMAP U6): a work's verdict, not a voice, so apart from the Almanac and before it. The haircut
        // weighed as the brief weighs it (ROADMAP T2.1, SPEC §10.3); FPMT's day for the date is one of the date's
        // lists, shown in the sheet as such, outweighed where it is (T2.15).
        val haircut = Texts.HAIRCUT[day.day - 1]
        val dateDay = Annotation(
            haircutDateTitle,
            haircutDateNote,
            haircutDateTone,
            haircut,
            titleIsKanji = false,
        )
        val haircutGrid: @Composable () -> Unit = { HaircutGrid(day, info.date) }
        val haircutRow = if (haircutTone == null || haircutBy == null) {
            dateDay.copy(title = haircutTitle, english = Catalog.text(haircut.arg!!), glyphs = { CueIcon(CueGlyphs.FAMILY.getValue(ActivityFamily.HAIRCUT), Palette.muted, 18.dp) }, diagram = haircutGrid)
        } else {
            Annotation(
                haircutTitle,
                haircutWhy,
                haircutTone,
                null,
                subtitle = haircutBy,
                titleIsKanji = false,
                lead = { ToneDot(haircutTone) },
                glyphs = { CueIcon(CueGlyphs.FAMILY.getValue(ActivityFamily.HAIRCUT), Palette.muted, 18.dp) },
                diagram = haircutGrid,
                parts = listOf(dateDay),
            )
        }
        SectionTitle(stringResource(R.string.section_works))
        AnnotationRow(haircutRow) { a -> sheet = a }

        // The Almanac's heading names what decides the day; among its rows only that voice's dot is solid,
        // the others' outlines (ROADMAP U6), so that the dots read as the voices' own tones, not as a count.
        Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            SectionTitle(stringResource(R.string.section_almanac))
            summary.verdict?.let { Text(byShort(it), style = body.copy(fontSize = 12.sp, color = Palette.faint)) }
        }
        Column { annotations.forEach { AnnotationRow(it.copy(outline = !it.decides)) { a -> sheet = a } } }

        val birth = info.birthSign
        if (yours.isNotEmpty() || birth != null) {
            SectionTitle(stringResource(R.string.section_your_day))
            Column {
                yours.forEach { AnnotationRow(it) { a -> sheet = a } }
                if (birth != null) {
                    for (force in listOf(Force.VITALITY, Force.BODY)) {
                        AnnotationRow(pebbleAnnotation(force, birth, info.signs, day, labels)) { a -> sheet = a }
                    }
                }
            }
        }

        // Also today (ROADMAP U3): what the day holds that the weighing does not count.
        SectionTitle(stringResource(R.string.section_also_today))
        AnnotationRow(
            Annotation(
                blaMkhyenTitle, blaMkhyenSubtitle, Tone.NEUTRAL, Texts.BLA_MKHYEN[daySmeBa.sevenRed], titleIsKanji = false,
                details = blaMkhyenDetails,
                glyphs = { BlaMkhyenCompass(daySmeBa.sevenRed, 24.dp, blaMkhyenCaption, small = true) },
                diagram = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        BlaMkhyenCompass(daySmeBa.sevenRed, 200.dp, blaMkhyenCaption)
                        Spacer(Modifier.height(20.dp))
                        MovedSmeBaSquare(
                            daySmeBa.number, 7, 200.dp,
                            stringResource(R.string.desc_moved_sme_ba, daySmeBa.number),
                            caption = stringResource(R.string.bla_mkhyen_square_caption, daySmeBa.number),
                        )
                    }
                },
            ),
        ) { a -> sheet = a }
        // The great black day, an earth lord of the date like the bla mkhyen: shown, not weighed (ROADMAP T2.14);
        // on 11/7 it is the meeting of the nine bad, which stands in the title.
        GreatBlackDay.of(day.month, day.day)?.let { season ->
            val nineBad = season == GreatBlackDay.NINE_BAD
            AnnotationRow(
                Annotation(
                    stringResource(if (nineBad) R.string.tib_nine_bad_title else R.string.tib_black_day_title),
                    if (nineBad) stringResource(R.string.tib_nine_bad_subtitle) else stringResource(R.string.tib_black_day_subtitle, day.monthNames.chineseSeason),
                    Tone.BAD,
                    Texts.GREAT_BLACK_DAY[season],
                    titleIsKanji = false,
                ),
            ) { a -> sheet = a }
        }
        // The earth lords that move by date (ROADMAP T2.13, SPEC §5.13): the day's courses as WB's almanac writes them, one row,
        // shown and not weighed, since WB's order of strength does not rank them; the sky door falls on every date.
        val courses = EarthLordCourses.of(day).map { c ->
            val name = gloss(c.course)
            Annotation(
                name,
                when {
                    c.course == EarthLordCourse.GNAM_SGO -> Catalog.text("SkyDoor.${c.variant!!.replace(' ', '_')}")
                    c.course == EarthLordCourse.ZLA_NAG -> MonthEntries.MonthPart.valueOf(c.variant!!).dates.let {
                        labels.string(R.string.black_dates, it.first, it.last)
                    }
                    c.course == EarthLordCourse.KI_KANG_ZLA_NAG -> Texts.earthLord(c).avoid.joinToString(", ")
                    c.otherView -> "${gloss(c.event)}, ${Catalog.text("CourseDay.otherView")}"
                    else -> gloss(c.event)
                },
                toneOf(Texts.earthLord(c)),
                Texts.earthLord(c),
                titleIsKanji = false,
                details = listOf(tibetanLabel to Ewts.named(name, c.course.wylie)),
            )
        }
        AnnotationRow(
            Annotation(
                stringResource(R.string.tib_earth_lords_title),
                stringResource(R.string.tib_earth_lords_note),
                Tone.NEUTRAL,
                null,
                subtitle = courses.joinToString(" · ") { "${it.title}: ${it.english}" },
                titleIsKanji = false,
                parts = courses,
            ),
        ) { a -> sheet = a }

        // The seasonal signs of the month heading that last seven days (ROADMAP T2.21): shown, not weighed.
        for (sign in MonthEntries.SeasonSign.entries) {
            MonthEntries.signDay(day, sign)?.let { k ->
                AnnotationRow(
                    Annotation(
                        stringResource(if (sign == MonthEntries.SeasonSign.RISHI) R.string.tib_rishi_title else R.string.tib_pig_title),
                        stringResource(R.string.sign_day, k),
                        if (sign == MonthEntries.SeasonSign.PIG) Tone.BAD else Tone.NEUTRAL,
                        Texts.SEASON_SIGN.getValue(sign),
                        titleIsKanji = false,
                    ),
                ) { a -> sheet = a }
            }
        }

        SectionTitle(stringResource(R.string.section_lunar_day))
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            // The labels are short English names on one line; a label's Tibetan is in the sheet or balloon its row opens.
            // The 60-day cycle's element and animal: weighed nowhere, so here rather than at the top, where the
            // weekday and planet it once stood with are the Almanac's weekday row (ROADMAP T3).
            val daySign = stringResource(R.string.element_animal, gloss(day.dayElement), gloss(day.dayAnimal))
            FactRow(
                stringResource(R.string.row_day_sign),
                daySign,
                daySign,
                tibetan = false,
                lead = {
                    CueIcon(CueGlyphs.of(day.dayElement), elementColour(day.dayElement), 22.dp)
                    CueIcon(CueGlyphs.ANIMAL.getValue(day.dayAnimal), Palette.text, 22.dp)
                },
                balloon = listOf(
                    BalloonRow(stringResource(R.string.row_element), gloss(day.dayElement), body.copy(fontSize = 14.sp, color = elementColour(day.dayElement))),
                    BalloonRow(stringResource(R.string.row_animal), gloss(day.dayAnimal)),
                    BalloonRow(stringResource(R.string.row_gender), gloss(day.dayGender, "inText")),
                ),
            )
            val animalAnnotation = Annotation(
                gloss(day.lunarDayAnimal),
                stringResource(R.string.tib_animal_subtitle),
                Tone.NEUTRAL,
                Texts.EARTH_LORD[day.lunarDayAnimal],
                titleIsKanji = false,
                tibetan = "nyi ma" to stringResource(R.string.row_date_animal),
            )
            FactRow(
                stringResource(R.string.row_date_animal),
                gloss(day.lunarDayAnimal),
                gloss(day.lunarDayAnimal),
                tibetan = false,
                lead = { CueIcon(CueGlyphs.ANIMAL.getValue(day.lunarDayAnimal), Palette.text, 22.dp) },
                onClick = { sheet = animalAnnotation },
            )
            val goddessScript = Ewts.named(day.trigram.goddessName, day.trigram.goddess)
            val trigramAnnotation = Annotation(
                goddessScript,
                stringResource(R.string.tib_trigram_subtitle, day.trigram.english),
                Tone.NEUTRAL,
                Texts.TRIGRAM[day.trigram],
                titleIsKanji = false,
                details = listOf(tibetanLabel to goddessScript),
            )
            FactRow(
                stringResource(R.string.row_trigram),
                day.trigram.wylie,
                day.trigram.english,
                gloss = "${day.trigram.chinese} — ${day.trigram.english}",
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
                balloon = listOf(BalloonRow(tibetanLabel, Ewts.named(stringResource(R.string.row_date_sme_ba), "sme ba"))),
            )
            // The letters of the day (WB vol. 1, pp. 16–19, 97, 150–153, 177–178; SPEC §5.11): written, not weighed.
            val kalacakra = DayLetters.kalacakra(day.month, day.day)
            val kalacakraValue = stringResource(
                R.string.letters_value, letter(kalacakra.syllable), kalacakra.element.english, kalacakra.sense.english,
            )
            val kalacakraAnnotation = Annotation(
                stringResource(R.string.row_kalacakra),
                kalacakraValue,
                Tone.NEUTRAL,
                Texts.KALACAKRA_LETTERS,
                titleIsKanji = false,
                details = listOf(
                    stringResource(R.string.detail_vowel) to letter(kalacakra.vowel),
                    stringResource(R.string.detail_syllable) to letter(kalacakra.syllable),
                    stringResource(R.string.detail_element) to Ewts.named(kalacakra.element.english, kalacakra.element.wylie),
                    stringResource(R.string.detail_sense) to Ewts.named(kalacakra.sense.english, kalacakra.sense.wylie),
                    stringResource(R.string.detail_half) to stringResource(if (kalacakra.arising) R.string.half_arising else R.string.half_gathered),
                    stringResource(R.string.detail_sign_month) to Ewts.named(DayLetters.sign(day.month).english, DayLetters.sign(day.month).wylie),
                ),
            )
            FactRow(
                stringResource(R.string.row_kalacakra),
                kalacakraValue,
                kalacakraValue,
                tibetan = false,
                onClick = { sheet = kalacakraAnnotation },
            )
            val svara = DayLetters.svarodaya(day.day)
            val stage = DayLetters.stage(day.day)
            val svaraValue = stringResource(
                R.string.letters_stage_value,
                "${svara.vowel.iast} ${svara.consonant.iast} (${svara.vowel.tibetan} ${svara.consonant.tibetan})",
                svara.element.english, svara.sense.english, stage.english,
            )
            val svaraAnnotation = Annotation(
                stringResource(R.string.row_svarodaya),
                svaraValue,
                Tone.NEUTRAL,
                Texts.SVARODAYA_LETTERS,
                titleIsKanji = false,
                details = listOf(
                    stringResource(R.string.detail_vowel) to letter(svara.vowel),
                    stringResource(R.string.detail_consonant) to letter(svara.consonant),
                    stringResource(R.string.detail_element) to Ewts.named(svara.element.english, svara.element.wylie),
                    stringResource(R.string.detail_sense) to Ewts.named(svara.sense.english, svara.sense.wylie),
                    stringResource(R.string.detail_stage) to Ewts.named(stage.english, stage.wylie),
                ),
            )
            FactRow(
                stringResource(R.string.row_svarodaya),
                svaraValue,
                svaraValue,
                tibetan = false,
                onClick = { sheet = svaraAnnotation },
            )
            remember(day.jd) { DayLetters.link(day) }?.let { linkDay ->
                val link = linkDay.link
                val count = stringResource(R.string.link_count, linkDay.day, linkDay.days, linkDay.month)
                val linkAnnotation = Annotation(
                    Ewts.named(link.english, link.wylie),
                    count,
                    Tone.NEUTRAL,
                    Texts.TWELVE_LINKS,
                    titleIsKanji = false,
                )
                FactRow(
                    stringResource(R.string.row_twelve_links),
                    link.wylie,
                    link.english,
                    onClick = { sheet = linkAnnotation },
                )
            }
            val foot = DayLetters.foot(day)
            val footValue = stringResource(R.string.foot_value, foot.quarter, letter(foot.syllable))
            val footAnnotation = Annotation(
                stringResource(R.string.row_hundred_feet),
                footValue,
                Tone.NEUTRAL,
                Texts.HUNDRED_FEET,
                titleIsKanji = false,
                details = listOf(
                    stringResource(R.string.detail_mansion) to Ewts.named(foot.mansion.english, foot.mansion.wylie),
                    stringResource(R.string.detail_quarter) to "${foot.quarter}",
                    stringResource(R.string.detail_syllable) to letter(foot.syllable),
                ) + run {
                    val abhijit = stringResource(R.string.abhijit)
                    val rahu = stringResource(R.string.body_rahu)
                    fun names(cells: List<DayLetters.Cell>) = cells.joinToString(", ") { wheelCell(it, abhijit) }
                    val fangsOf = remember(day.jd) { DayLetters.bodies(day) }
                    fangsOf.map { (body, mansion) ->
                        val f = DayLetters.fangs(mansion)
                        val bodyName = body.weekday?.planet ?: rahu
                        stringResource(R.string.body_in, bodyName, Ewts.named(mansion.english, mansion.wylie)) to
                            if (f.left.isEmpty()) stringResource(R.string.fangs_no_left, names(f.right), wheelCell(f.faceOn, abhijit))
                            else stringResource(R.string.fangs_value, names(f.right), names(f.left), wheelCell(f.faceOn, abhijit))
                    }
                },
            )
            FactRow(
                stringResource(R.string.row_hundred_feet),
                footValue,
                footValue,
                tibetan = false,
                onClick = { sheet = footAnnotation },
            )
            // The sun's terms that fall in the day (WB vol. 1, ch. 15; SPEC §5.8).
            for (term in sunTerms) {
                val t = term.what
                val name = sunTermName(t, labels)
                val at = clockOf(term.at, labels)
                val annotation = Annotation(
                    name,
                    at,
                    Tone.NEUTRAL,
                    Texts.SUN_TERM.getValue(t.kind),
                    titleIsKanji = false,
                    details = listOf(
                        stringResource(R.string.detail_sun_term) to Ewts.named(gloss(t.kind), t.kind.wylie),
                        stringResource(R.string.detail_sun_measure) to stringResource(R.string.sun_measure, t.mansion, t.chuTshod),
                    ),
                )
                FactRow(
                    stringResource(R.string.row_sun_term),
                    "$name · $at",
                    "$name · $at",
                    tibetan = false,
                    onClick = { sheet = annotation },
                )
            }
        }
    }

    if (summaryOpen) {
        DaySummarySheet(
            summary,
            festival = day.holiday != null || day.specialDay != null,
            onHour = { hoursFrom = it; hoursOpen = true },
            onElect = onElect,
        ) { summaryOpen = false }
    }
    if (hoursOpen) HoursSheet(info.date, day, info.birthSign, info.signs, zone, summary, place, visti = visti, initial = hoursFrom, onOpen = { sheet = it }) { hoursOpen = false }
    if (yearOpen && person != null) YearSheet(person, day, zone, onOpen = { sheet = it }) { yearOpen = false }
    sheet?.let { ReadingSheet(it, onElect = onElect) { sheet = null } }
}

/**
 * The aspects of a year or month in its balloon: each one's element, and with
 * a birth date set its pebbles and relation to the same aspect of the birth
 * year ("Fire · ○○○ mother"). The month and the year hold these rather than
 * the page, where they would repeat for weeks.
 */
/**
 * What WB's month heading writes that the app reckons (ROADMAP T2.21, SPEC §5.11): the month's days, long
 * or short; the weekday that rises, roughly by the month's animal and finely by its first date's weekday;
 * the year's black months, and this one's black third; the year's Kikang black month when it falls in
 * this month; the dated seasonal signs; the comet the count marks.
 */
private fun monthEntryRows(day: TibetanDay, labels: Labels): List<BalloonRow> = buildList {
    val days = MonthEntries.days(day.monthCount)
    add(BalloonRow(labels.string(R.string.row_month_days), labels.string(if (MonthEntries.isLong(days)) R.string.month_long else R.string.month_short, days)))
    add(BalloonRow(labels.string(R.string.row_rise_rough), MonthEntries.roughRise(day.monthNames.animal).planet))
    val lord = MonthEntries.lord(day.monthCount)
    val order = Weekday.entries.drop(1) + Weekday.SATURDAY
    fun planets(rise: MonthEntries.Rise) = order.filter { MonthEntries.rise(lord, it) == rise }.joinToString(", ") { it.planet }
    add(
        BalloonRow(
            labels.string(R.string.row_rise_fine),
            labels.string(
                R.string.rise_fine,
                planets(MonthEntries.Rise.RISES), planets(MonthEntries.Rise.WANES), planets(MonthEntries.Rise.DECLINES), planets(MonthEntries.Rise.MOVES),
            ),
        ),
    )
    val year = EarthLordCourses.chineseYear(day)
    val (first, second) = MonthEntries.blackMonths(year).sortedBy { a -> (1..12).first { TibetanCalendar.monthNames(it).animal == a } }
    val black = MonthEntries.blackMonth(day)
    add(
        BalloonRow(
            labels.string(R.string.row_black_months),
            if (black == null) {
                labels.string(R.string.black_months, gloss(first), gloss(second))
            } else {
                labels.string(R.string.black_months_this, gloss(first), gloss(second), black.dates.first, black.dates.last)
            },
        ),
    )
    MonthEntries.kiKang(day)?.let { k ->
        val time = when (k.time) {
            MonthEntries.DayTime.DAWN -> labels.string(R.string.kikang_dawn)
            MonthEntries.DayTime.SUNRISE -> labels.string(R.string.kikang_sunrise)
            MonthEntries.DayTime.DUSK -> labels.string(R.string.kikang_dusk)
            null -> ""
        }
        val works = Texts.earthLord(
            CourseDay(EarthLordCourse.KI_KANG_ZLA_NAG, CourseEvent.MOVES, SeasonReckoning.CHINESE.season(day.month), year.name),
        ).avoid.joinToString(", ")
        add(BalloonRow(labels.string(R.string.row_kikang_month), labels.string(R.string.kikang_month, k.date, time, works)))
    }
    for (sign in MonthEntries.SeasonSign.entries) {
        MonthEntries.signDate(day.monthCount, sign)?.let { d ->
            val label = if (sign == MonthEntries.SeasonSign.RISHI) R.string.row_rishi_days else R.string.row_pig_days
            add(BalloonRow(labels.string(label), labels.string(R.string.sign_from, d)))
        }
    }
    if (MonthEntries.cometMonth(day.year, day.month, day.leapMonth)) {
        add(BalloonRow(labels.string(R.string.row_comet), labels.string(R.string.comet_month)))
    }
}

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
internal fun hourSpan(h: HourSign): String = clockSpan(h.startMinute, 120)

/** "21:00–01:00": [minutes] from [start], clock time, past midnight as the clock reads it. */
/** A time [chuTshod] after the day's daybreak at 05:00 on the clock, marked when it lies before daybreak or in the next morning. */
internal fun clockOf(chuTshod: Double, labels: Labels): String {
    val m = DayTimes.clockMinute(chuTshod)
    val hhmm = "%02d:%02d".format(m / 60, m % 60)
    return when {
        chuTshod < 0 -> labels.string(R.string.time_before_daybreak, hhmm)
        chuTshod >= 60 -> labels.string(R.string.time_next_morning, hhmm)
        else -> hhmm
    }
}

/** The sun's term as a row names it: the month's breath or middle term, or the sign entered. */
/** A letter of the day as the app shows it: its transliteration, then its script in brackets. */
internal fun letter(l: Letter): String = "${l.iast} (${l.tibetan})"

/** A cell of the hundred feet's wheel, named as §8.1 names it: a mansion, letter, sign or class of date. */
internal fun wheelCell(c: DayLetters.Cell, abhijit: String): String = when (c) {
    is DayLetters.Cell.Star -> c.mansion?.let { Ewts.named(it.english, it.wylie) } ?: Ewts.named(abhijit, "byi bzhin")
    is DayLetters.Cell.Sound -> letter(c.letter)
    is DayLetters.Cell.Sign -> Ewts.named(c.sign.english, c.sign.wylie)
    is DayLetters.Cell.DateClass -> Ewts.named(c.dateClass.english, c.dateClass.wylie)
}

internal fun sunTermName(t: DayTimes.SunTerm, labels: Labels): String = when (t.kind) {
    DayTimes.SunTermKind.KHYIM_PHO -> labels.string(R.string.sun_term_sign, t.sign!!.english)
    else -> labels.string(R.string.sun_term_month, gloss(t.kind).replaceFirstChar(Char::uppercase), t.month!!)
}

internal fun clockSpan(start: Int, minutes: Int): String {
    fun hhmm(m: Int) = "%02d:%02d".format((m / 60) % 24, m % 60)
    return "${hhmm(start)}–${hhmm(start + minutes)}"
}

/** Tibetan script in the bundled font, sized to sit level with Latin text of [size]. */
fun tibetanStyle(size: TextUnit): TextStyle = body.copy(fontFamily = TibetanSerif, fontSize = size * 1.15f)

/**
 * A Tibetan term as SPEC §8.1 names it: its English [name], then its script
 * and Wylie in brackets. Tapping it shows how it is said (THL phonetics) and
 * the [gloss] (SPEC §10.1); screen readers get the name and the phonetics.
 */
@Composable
fun TibetanTerm(wylie: String, name: String, size: TextUnit = 16.sp, preferAbove: Boolean = false, modifier: Modifier = Modifier, gloss: String = name) {
    val text = remember(name, wylie) { namedTerm(name, wylie) }
    val said = remember(wylie) { Thl.toPhonetic(wylie) }
    GlossText(
        text = text,
        english = gloss,
        style = body.copy(fontSize = size),
        preferAbove = preferAbove,
        modifier = modifier,
        rows = listOfNotNull(
            said?.let { BalloonRow(stringResource(R.string.row_say), it) },
            BalloonRow(stringResource(R.string.row_english), gloss),
        ),
        spoken = "$name, ${said ?: wylie}",
    )
}

/**
 * A labelled fact whose value is a term: tapping the term shows its English;
 * a Tibetan [term] is its Wylie, shown with its [english] name (SPEC §8.1) and
 * its [gloss] on tap. [swatch] draws its colour before it. A Tibetan term
 * sits on the label's baseline: its script hangs from a head line, so
 * centring the boxes would lift it above the label.
 */
@Composable
fun FactRow(
    label: String,
    term: String,
    english: String,
    gloss: String = english,
    tibetan: Boolean = true,
    kanji: Boolean = false,
    swatch: Color? = null,
    /** Glyphs drawn between the label and the term (SPEC §10.4). */
    lead: (@Composable () -> Unit)? = null,
    /** Opens the term's reading, for the few facts that have one. */
    onClick: (() -> Unit)? = null,
    /** A balloon for a plain term, with more to say than its English. */
    balloon: List<BalloonRow>? = null,
) {
    // A bare term, or plain text that may hold Tibetan script, sits on the label's baseline: the script's tall line
    // would push a centred label below the text.
    val plain = !tibetan && !kanji && balloon == null
    val onBaseline = lead == null && (tibetan || plain && swatch == null)
    Row(
        Modifier.fillMaxWidth().heightIn(min = 40.dp).let { if (onClick != null) it.clickable(onClick = onClick) else it },
        verticalAlignment = if (onBaseline) Alignment.Top else Alignment.CenterVertically,
    ) {
        // A label naming a Tibetan word keeps its bracket whole on a line of its own (SPEC §8.1).
        val bracket = label.indexOf(" (").takeIf { it > 0 && label.substring(it).any { c -> c in '\u0F00'..'\u0FFF' } }
        Text(
            if (bracket == null) AnnotatedString(label) else withTibetan(label.substring(0, bracket) + "\n" + label.substring(bracket + 1)),
            style = body.copy(fontSize = 14.sp, color = Palette.muted),
            modifier = Modifier.width(120.dp).padding(end = 8.dp).let { if (onBaseline) it.alignByBaseline() else it },
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
            TibetanTerm(term, english, preferAbove = true, modifier = if (lead == null) Modifier.alignByBaseline() else Modifier.weight(1f), gloss = gloss)
        } else if (kanji) {
            GlossText(
                term,
                english,
                style = body.copy(fontFamily = Mincho, fontWeight = FontWeight.Bold, fontSize = 17.sp),
                preferAbove = true,
            )
        } else if (balloon != null) {
            // A screen reader gets the balloon's rows after the term, not the term twice.
            GlossText(english, balloon.joinToString { "${it.label} ${it.value}" }, body.copy(fontSize = 16.sp), preferAbove = true, rows = balloon)
        } else {
            Text(english, style = body.copy(fontSize = 16.sp), modifier = if (onBaseline) Modifier.alignByBaseline() else Modifier)
        }
    }
}
