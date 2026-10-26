/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.ui

import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.iverlein.zanshin.R
import zanshin.app.DayInfo
import zanshin.app.Labels
import zanshin.app.LocalLabels
import zanshin.app.Person
import zanshin.core.kyureki.Tone
import zanshin.core.texts.ActivityFamily
import zanshin.core.texts.Catalog
import zanshin.core.texts.DaySummary
import zanshin.core.texts.ReadingRow
import zanshin.core.texts.Texts
import zanshin.core.texts.family
import zanshin.core.texts.gloss
import zanshin.core.tibetan.DayLetters
import zanshin.core.tibetan.ElementQuarter
import zanshin.core.tibetan.Ewts
import zanshin.core.tibetan.Element
import zanshin.core.tibetan.Force
import zanshin.core.tibetan.Health
import zanshin.core.tibetan.Humour
import zanshin.core.tibetan.LaHour
import zanshin.core.tibetan.MedicalSeason
import zanshin.core.tibetan.Sign
import zanshin.core.tibetan.TibetanCalendar
import zanshin.core.tibetan.YearReckoning
import java.time.LocalDate
import java.time.ZoneId

/** The Health screen's tabs, by subject (ROADMAP H1). */
private enum class HealthTab(val label: Int) {
    TODAY(R.string.health_tab_today),
    YOU(R.string.health_tab_you),
    ILLNESS(R.string.health_tab_illness),
    TREATMENT(R.string.health_tab_treatment),
    MEDICINE(R.string.health_tab_medicine),
}

/**
 * The Health screen (SPEC §10.10, ROADMAP H1), opened from the menu or from Your day on [from], the day
 * shown there, and stepped day by day: the day's illness readings and the la's place (Today), the
 * person's element, the la's element, the vitality and body of the date and the year on the seven-red
 * (You), the medical works as the day's weighing gives them (Treatment), and the Four Tantras' humours
 * and seasons, which the White Beryl does not reckon, each with the grey dot (Medicine).
 */
@Composable
fun HealthScreen(from: LocalDate, zone: ZoneId, person: Person?, onPeople: () -> Unit, onBack: () -> Unit) {
    val labels = LocalLabels.current
    var date by rememberSaveable { mutableStateOf(from) }
    var tab by rememberSaveable { mutableStateOf(HealthTab.TODAY) }
    var sheet by remember { mutableStateOf<Annotation?>(null) }
    // The time of falling ill on the shown day, for the illness reckoning (H2b); the present time to begin with.
    var illTime by rememberSaveable { mutableStateOf(java.time.LocalTime.now(zone).withSecond(0).withNano(0)) }
    val info = remember(date, zone, person) { DayInfo.of(date, zone, person?.birth) }
    Column(
        Modifier
            .fillMaxSize()
            .background(Palette.ink)
            .windowInsetsPadding(WindowInsets.safeDrawing),
    ) {
        Row(Modifier.fillMaxWidth().heightIn(min = 64.dp).padding(start = 6.dp, end = 6.dp, top = 8.dp), verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onBack) { Icon(Icons.ChevronLeft, contentDescription = stringResource(R.string.back), tint = Palette.text) }
            Column(Modifier.weight(1f)) {
                Text(stringResource(R.string.menu_health), style = body.copy(fontSize = 18.sp, fontWeight = FontWeight.SemiBold))
                Text(date.format(labels.headerDate), style = body.copy(fontSize = 13.sp, color = Palette.muted))
            }
            IconButton(onClick = { date = date.minusDays(1) }) { Icon(Icons.ChevronLeft, contentDescription = stringResource(R.string.health_day_before), tint = Palette.muted) }
            IconButton(onClick = { date = date.plusDays(1) }) { Icon(Icons.ChevronRight, contentDescription = stringResource(R.string.health_day_after), tint = Palette.muted) }
        }
        Tabs(tab) { tab = it }
        Column(
            Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(start = 24.dp, end = 24.dp, top = 12.dp, bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(stringResource(R.string.health_not_advice), style = body.copy(fontSize = 13.sp, lineHeight = 18.sp, color = Palette.faint))
            val open: (Annotation) -> Unit = { sheet = it }
            when (tab) {
                HealthTab.TODAY -> TodayTab(info, labels, open)
                HealthTab.YOU -> YouTab(info, person, labels, onPeople, open)
                HealthTab.ILLNESS -> IllnessReckoningView(person, date, illTime, zone, { illTime = it }, onPeople, open)
                HealthTab.TREATMENT -> TreatmentTab(info, open)
                HealthTab.MEDICINE -> MedicineTab(open)
            }
        }
    }
    sheet?.let { ReadingSheet(it) { sheet = null } }
}

/**
 * The tabs as one row of segments, the chosen one filled; screen readers get them as tabs. Where the four do not
 * fit as equal segments (a large font, ROADMAP A2), each is as wide as its name and the row scrolls sideways.
 */
@Composable
private fun Tabs(tab: HealthTab, onTab: (HealthTab) -> Unit) {
    val style = body.copy(fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
    val names = HealthTab.entries.map { stringResource(it.label) }
    val measurer = rememberTextMeasurer()
    val density = LocalDensity.current
    BoxWithConstraints(Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
        val gap = 6.dp
        val widest = remember(names, density) { with(density) { names.maxOf { measurer.measure(it, style, maxLines = 1).size.width }.toDp() } }
        val equal = (widest + 24.dp) * names.size + gap * (names.size - 1) <= maxWidth
        Row(
            Modifier
                .fillMaxWidth()
                .then(if (equal) Modifier else Modifier.horizontalScroll(rememberScrollState()))
                .heightIn(min = 44.dp)
                .height(IntrinsicSize.Min)
                .selectableGroup(),
            horizontalArrangement = Arrangement.spacedBy(gap),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            HealthTab.entries.forEachIndexed { i, t ->
                val selected = t == tab
                val shape = RoundedCornerShape(18.dp)
                Box(
                    Modifier
                        .then(if (equal) Modifier.weight(1f) else Modifier)
                        .fillMaxHeight()
                        .selectable(selected = selected, role = Role.Tab) { onTab(t) },
                    contentAlignment = Alignment.Center,
                ) {
                    Box(
                        Modifier
                            .then(if (equal) Modifier.fillMaxWidth() else Modifier)
                            .heightIn(min = 34.dp)
                            .then(if (selected) Modifier.background(Palette.saffron, shape) else Modifier.border(1.dp, Palette.line, shape)),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            names[i],
                            style = style.copy(color = if (selected) Palette.onAccent else Palette.muted),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(horizontal = if (equal) 4.dp else 14.dp, vertical = 4.dp),
                        )
                    }
                }
            }
        }
    }
}

/**
 * The day's illness and the la (ROADMAP H1): what falling ill today means by the date's round of eight, the
 * weekday, the link and the goddess of the day; the la's place by the date, and its course within the day.
 */
@Composable
private fun TodayTab(info: DayInfo, labels: Labels, open: (Annotation) -> Unit) {
    val day = info.tibetan
    val illness = ReadingRow.ILLNESS.label
    val tibetanLabel = stringResource(R.string.row_tibetan)
    val perilous = day.day in Health.PERILOUS_DATES
    val round = Health.illnessDate(day.day)
    val dateTitle = labels.string(R.string.health_date_title, labels.ordinal(day.day))
    val parts = buildList {
        add(
            Annotation(
                dateTitle,
                if (round == day.day) labels.string(R.string.health_date_own) else labels.string(R.string.health_date_round, labels.ordinal(round)),
                if (perilous) Tone.BAD else Tone.NEUTRAL,
                null,
                titleIsKanji = false,
                details = listOf(illness to Catalog.text("reading.LunarDate.${day.day}.illness")),
            ),
        )
        add(
            Annotation(
                day.weekday.english,
                stringResource(R.string.health_weekday_note),
                Tone.NEUTRAL,
                null,
                titleIsKanji = false,
                details = listOf(illness to Catalog.text("reading.Weekday.${day.weekday.name}.illness")),
            ),
        )
        remember(day.jd) { DayLetters.link(day) }?.let { linkDay ->
            add(
                Annotation(
                    Ewts.named(linkDay.link.english.replaceFirstChar(Char::uppercase), linkDay.link.wylie),
                    stringResource(R.string.health_link_note),
                    Tone.NEUTRAL,
                    null,
                    titleIsKanji = false,
                    details = listOf(illness to Catalog.text("reading.TwelveLinks.${linkDay.link.name}.illness")),
                ),
            )
        }
        val goddess = Ewts.named(day.trigram.goddessName, day.trigram.goddess)
        add(
            Annotation(
                goddess,
                stringResource(R.string.tib_trigram_subtitle, day.trigram.english),
                Tone.NEUTRAL,
                Texts.TRIGRAM[day.trigram],
                titleIsKanji = false,
                details = listOf(tibetanLabel to goddess),
            ),
        )
    }
    SectionTitle(stringResource(R.string.health_section_illness))
    AnnotationRow(
        Annotation(
            stringResource(R.string.health_illness_title),
            stringResource(if (perilous) R.string.health_illness_note_perilous else R.string.health_illness_note),
            if (perilous) Tone.BAD else Tone.NEUTRAL,
            null,
            subtitle = parts.joinToString(" · ") { it.title.substringBefore(" (") } + if (perilous) " · " + stringResource(R.string.health_perilous) else "",
            titleIsKanji = false,
            parts = parts,
        ),
        open,
    )

    SectionTitle(stringResource(R.string.health_section_la))
    val place = Catalog.text("LaPlace.${day.day}")
    AnnotationRow(
        Annotation(
            stringResource(R.string.health_la_title),
            place,
            Tone.NEUTRAL,
            Texts.LA_HOURS,
            subtitle = labels.string(R.string.health_la_subtitle, place),
            titleIsKanji = false,
            details = listOf(labels.string(R.string.health_la_on_date, labels.ordinal(day.day)) to place) +
                LaHour.entries.map { it.english to it.place },
        ),
        open,
    )
}

/**
 * The person's own (ROADMAP H1): the day's mansion as a quarter of the life force's element, and a grave mansion
 * where it is one; the la's element; the vitality and body of the date against the birth year, moved here from
 * Your day; the year of age on the seven-red. Without a birth date, the way to choose a person.
 */
@Composable
private fun YouTab(info: DayInfo, person: Person?, labels: Labels, onPeople: () -> Unit, open: (Annotation) -> Unit) {
    val day = info.tibetan
    val element = info.ownElement
    val birth = info.birthSign
    if (person == null || element == null || birth == null) {
        Text(stringResource(R.string.health_no_person), style = body.copy(fontSize = 15.sp, lineHeight = 21.sp, color = Palette.muted), modifier = Modifier.padding(top = 12.dp))
        ScreenLinkRow(stringResource(R.string.menu_people), stringResource(R.string.health_choose_person), onPeople, Icons.Person)
        return
    }
    val tibetanLabel = stringResource(R.string.row_tibetan)
    val lifeForce = gloss(element, "inText")
    SectionTitle(stringResource(R.string.health_section_element))
    val quarter = Health.quarter(element, day.mansion)
    val mansionName = Ewts.named(day.mansion.sanskrit, day.mansion.wylie)
    val quarterElement = gloss(Health.quarterElement(day.mansion), "inText")
    AnnotationRow(
        Annotation(
            quarter.english,
            labels.string(R.string.health_quarter_subtitle, day.mansion.sanskrit, quarterElement),
            Texts.elementQuarterTone(quarter),
            Texts.ELEMENT_QUARTER.getValue(quarter),
            titleIsKanji = false,
            details = listOf(
                tibetanLabel to Ewts.named(quarter.english, quarter.wylie),
                stringResource(R.string.detail_moon_mansion) to mansionName,
                stringResource(R.string.health_detail_quarter) to quarterElement,
                stringResource(R.string.health_detail_life_force) to lifeForce,
            ),
        ),
        open,
    )
    Health.grave(element, day.mansion)?.let { g ->
        AnnotationRow(
            Annotation(
                g.english,
                labels.string(R.string.health_grave_subtitle, day.mansion.sanskrit),
                Tone.NEUTRAL,
                Texts.GRAVE_MANSION.getValue(g),
                titleIsKanji = false,
                details = listOf(
                    tibetanLabel to Ewts.named(g.english, g.wylie),
                    stringResource(R.string.health_detail_life_force) to lifeForce,
                ),
            ),
            open,
        )
    }
    val la = gloss(Health.laElement(element), "inText")
    AnnotationRow(
        Annotation(
            stringResource(R.string.health_la_element_title),
            labels.string(R.string.health_la_element_subtitle, la, lifeForce),
            Tone.NEUTRAL,
            Texts.LA_ELEMENT,
            titleIsKanji = false,
            details = listOf(
                stringResource(R.string.health_detail_life_force) to lifeForce,
                stringResource(R.string.health_detail_la) to la,
            ),
        ),
        open,
    )

    SectionTitle(stringResource(R.string.health_section_pebbles))
    for (force in listOf(Force.VITALITY, Force.BODY)) AnnotationRow(pebbleAnnotation(force, birth, info.signs, day, labels), open)

    // The year of age on the seven-red (WB vol. 1, pp. 403–404); by the natal mewa, the year sheet's own reading.
    val year = remember(person, day.year) {
        val born = TibetanCalendar.of(person.birth)
        YearReckoning(Sign(born.yearElement, born.yearAnimal), born.year, Sign(day.yearElement, day.yearAnimal), day.year, person.gender, null)
    }
    if (year.currentSmeBa == 7) {
        SectionTitle(stringResource(R.string.health_section_year))
        AnnotationRow(
            Annotation(
                stringResource(R.string.health_seven_red_title),
                labels.string(R.string.health_seven_red_subtitle, labels.ordinal(year.age)),
                Tone.BAD,
                Texts.SEVEN_RED_YEAR,
                titleIsKanji = false,
            ),
            open,
        )
    }
}

/**
 * The medical works (ROADMAP H1) as the day's weighing gives them, each with the voices on its side: the same
 * sides the day page and the election show (SPEC §5.12). On the person's day of every work avoided, that first, and
 * no work good.
 */
@Composable
private fun TreatmentTab(info: DayInfo, open: (Annotation) -> Unit) {
    val day = info.tibetan
    val locale = LocalLabels.current.locale
    val summary = remember(day.jd, locale, info.personalDay, info.personalMansions, info.ownDays) {
        DaySummary.of(day, info.personalDay, info.personalMansions, info.ownDays)
    }
    SectionTitle(stringResource(R.string.health_section_works))
    Text(stringResource(R.string.health_works_note), style = body.copy(fontSize = 13.sp, lineHeight = 18.sp, color = Palette.faint))
    for (e in summary.avoidAll) {
        AnnotationRow(Annotation(e.kanji, stringResource(R.string.health_avoid_all), Tone.BAD, e.reading, titleIsKanji = !e.latin), open)
    }
    // The person's lists, as the day page gives them: on a day of every work avoided, no good list (SPEC §5.12).
    val works = (summary.good + summary.avoid).distinct().filter { it.activity.family == ActivityFamily.MEDICINE }
    if (works.isEmpty()) {
        Text(stringResource(R.string.health_no_works), style = body.copy(color = Palette.muted), modifier = Modifier.padding(vertical = 8.dp))
    }
    for (n in works) {
        val good = n.good.isNotEmpty()
        val voices = if (good) n.good else n.avoid
        AnnotationRow(
            Annotation(
                n.activity.english.replaceFirstChar(Char::uppercase),
                stringResource(if (good) R.string.health_work_good else R.string.health_work_avoid, voices.joinToString(", ") { it.kanji }),
                if (good) Tone.GOOD else Tone.BAD,
                null,
                titleIsKanji = false,
                parts = voices.map { Annotation(it.kanji, it.english, it.tone, it.reading, titleIsKanji = !it.latin) },
            ),
            open,
        )
    }
}

/**
 * What the medical texts add (ROADMAP H1, *The grey dot*): the Root Tantra's three humours and the Explanatory
 * Tantra's six seasons, which give no works and no weight in the White Beryl's weighing, so each has the grey dot.
 */
@Composable
private fun MedicineTab(open: (Annotation) -> Unit) {
    val tibetanLabel = stringResource(R.string.row_tibetan)
    Text(stringResource(R.string.health_medicine_note), style = body.copy(fontSize = 13.sp, lineHeight = 18.sp, color = Palette.faint))
    SectionTitle(stringResource(R.string.health_section_humours))
    for (h in Humour.entries) {
        val named = Ewts.named(h.english, h.wylie)
        AnnotationRow(Annotation(h.english, named, Tone.NEUTRAL, Texts.HUMOUR.getValue(h), titleIsKanji = false, details = listOf(tibetanLabel to named)), open)
    }
    SectionTitle(stringResource(R.string.health_section_seasons))
    for (s in MedicalSeason.entries) {
        val named = Ewts.named(s.english, s.wylie)
        AnnotationRow(Annotation(s.english, named, Tone.NEUTRAL, Texts.MEDICAL_SEASON.getValue(s), titleIsKanji = false, details = listOf(tibetanLabel to named)), open)
    }
    // The White Beryl's illness chapter (ROADMAP H2a): each element's illness, its seat and door.
    SectionTitle(stringResource(R.string.health_section_element_illness))
    Text(stringResource(R.string.health_element_illness_note), style = body.copy(fontSize = 13.sp, lineHeight = 18.sp, color = Palette.faint))
    for (e in listOf(Element.WOOD, Element.FIRE, Element.EARTH, Element.IRON, Element.WATER)) {
        AnnotationRow(
            Annotation(
                stringResource(R.string.health_element_illness_title, gloss(e, "inText")),
                stringResource(ELEMENT_ILLNESS_SEAT.getValue(e)),
                Tone.NEUTRAL,
                Texts.ELEMENT_ILLNESS.getValue(e),
                titleIsKanji = false,
            ),
            open,
        )
    }
}

/** Each element's illness's seat and door in brief (WB vol. 1, p. 439), the row's second line. */
private val ELEMENT_ILLNESS_SEAT = mapOf(
    Element.WOOD to R.string.health_element_illness_wood,
    Element.FIRE to R.string.health_element_illness_fire,
    Element.EARTH to R.string.health_element_illness_earth,
    Element.IRON to R.string.health_element_illness_iron,
    Element.WATER to R.string.health_element_illness_water,
)

/** A row that leads to another screen: its title, a line under it, and a chevron. */
@Composable
fun ScreenLinkRow(title: String, subtitle: String, onClick: () -> Unit, icon: ImageVector = Icons.Pulse) {
    Row(
        Modifier
            .fillMaxWidth()
            .heightIn(min = 48.dp)
            .clickable(role = Role.Button, onClick = onClick)
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Icon(icon, contentDescription = null, tint = Palette.muted, modifier = Modifier.size(20.dp))
        Column(Modifier.weight(1f)) {
            Text(title, style = body.copy(fontSize = 16.sp, fontWeight = FontWeight.Medium))
            Text(subtitle, style = body.copy(fontSize = 13.sp, lineHeight = 18.sp, color = Palette.muted))
        }
        Icon(Icons.ChevronRight, contentDescription = null, tint = Palette.faint, modifier = Modifier.size(16.dp))
    }
}
