/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.iverlein.zanshin.R
import zanshin.app.LocalLabels
import zanshin.app.Person
import zanshin.app.Reckoning
import zanshin.app.Reckonings
import zanshin.core.texts.gloss
import zanshin.core.tibetan.Ewts
import zanshin.core.tibetan.YearOfLife
import java.time.ZoneId

/**
 * The Reckonings section (SPEC §10.11, ROADMAP R1): the element reckonings built, listed for [person], each
 * opening on a view of its own. [year] is the Tibetan year of the day shown when the section was opened; [opened]
 * is the reckoning it opens straight on, from the year's balloon, or null for the list. Back leads the way it came:
 * from a reckoning to the list, unless the section was opened on it, and from the list out through [onBack].
 */
@Composable
fun ReckoningsScreen(year: Int, opened: Reckoning?, zone: ZoneId, person: Person?, onPeople: () -> Unit, onBack: () -> Unit) {
    var shown by rememberSaveable { mutableStateOf(opened) }
    var sheet by remember { mutableStateOf<Annotation?>(null) }
    // The year of age's year, stepped on its own view; never before the person's first year.
    var shownYear by rememberSaveable(person) { mutableStateOf(person?.let { maxOf(year, Reckonings.birthYear(it)) } ?: year) }
    val back = { if (shown != null && opened == null) shown = null else onBack() }
    BackHandler(onBack = back)

    Column(
        Modifier
            .fillMaxSize()
            .background(Palette.ink)
            .windowInsetsPadding(WindowInsets.safeDrawing),
    ) {
        val open: (Annotation) -> Unit = { sheet = it }
        when (shown) {
            null -> ReckoningList(person, shownYear, onPeople, back) { shown = it }
            Reckoning.BIRTH -> BirthView(person, onPeople, back, open)
            Reckoning.YEAR_OF_AGE -> YearOfAgeView(person, shownYear, zone, onYear = { shownYear = it }, onPeople, back, open)
        }
    }
    sheet?.let { ReadingSheet(it) { sheet = null } }
}

/** The screen's header: Back, the title with a line under it, and what [trailing] adds at the end. */
@Composable
private fun Header(title: String, subtitle: String?, onBack: () -> Unit, trailing: @Composable () -> Unit = {}) {
    Row(Modifier.fillMaxWidth().heightIn(min = 64.dp).padding(start = 6.dp, end = 6.dp, top = 8.dp), verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = onBack) { Icon(Icons.ChevronLeft, contentDescription = stringResource(R.string.back), tint = Palette.text) }
        Column(Modifier.weight(1f)) {
            Text(title, style = body.copy(fontSize = 18.sp, fontWeight = FontWeight.SemiBold), maxLines = 1)
            if (subtitle != null) Text(subtitle, style = body.copy(fontSize = 13.sp, color = Palette.muted), maxLines = 1)
        }
        trailing()
    }
}

/** The body under a header, scrolling. */
@Composable
private fun Body(content: @Composable () -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(start = 24.dp, end = 24.dp, top = 12.dp, bottom = 32.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) { content() }
}

/** Without a person chosen: what the reckonings need, and the way to People. */
@Composable
private fun NoPerson(onPeople: () -> Unit) {
    Text(stringResource(R.string.reckonings_no_person), style = body.copy(fontSize = 15.sp, lineHeight = 21.sp, color = Palette.muted), modifier = Modifier.padding(top = 12.dp))
    ScreenLinkRow(stringResource(R.string.menu_people), stringResource(R.string.health_choose_person), onPeople, Icons.Person)
}

/** The list of the reckonings built, each a row leading to its own view, with the person's value for [year]. */
@Composable
private fun ReckoningList(person: Person?, year: Int, onPeople: () -> Unit, onBack: () -> Unit, onOpen: (Reckoning) -> Unit) {
    val labels = LocalLabels.current
    Header(stringResource(R.string.menu_reckonings), person?.let { personLabel(it) } ?: stringResource(R.string.menu_no_one_chosen), onBack)
    Body {
        Text(stringResource(R.string.reckonings_note), style = body.copy(fontSize = 14.sp, lineHeight = 20.sp, color = Palette.muted))
        if (person == null) {
            NoPerson(onPeople)
            return@Body
        }
        SectionTitle(stringResource(R.string.reckonings_section_built))
        for (r in Reckonings.built(person)) when (r) {
            Reckoning.BIRTH -> {
                val b = Reckonings.birth(person)
                ScreenLinkRow(
                    stringResource(R.string.birth_title),
                    if (b == null) stringResource(R.string.reckonings_birth_needed)
                    else labels.string(R.string.reckonings_birth_subtitle, Ewts.named(b.natal.english, b.natal.wylie), b.child.english),
                    onClick = { onOpen(r) },
                    icon = Icons.Pebbles,
                )
            }
            Reckoning.YEAR_OF_AGE -> {
                val sign = YearOfLife.yearSign(year)
                ScreenLinkRow(
                    stringResource(R.string.year_title),
                    labels.string(
                        R.string.reckonings_year_subtitle,
                        labels.ordinal(YearOfLife.age(Reckonings.birthYear(person), year)),
                        labels.string(R.string.element_animal, gloss(sign.element), gloss(sign.animal)),
                    ),
                    onClick = { onOpen(r) },
                    icon = Icons.Pebbles,
                )
            }
        }
    }
}

/** The reckoning of [person]'s birth (SPEC §5.9.2, ROADMAP R2), or what it needs. */
@Composable
private fun BirthView(person: Person?, onPeople: () -> Unit, onBack: () -> Unit, onOpen: (Annotation) -> Unit) {
    val b = remember(person) { person?.let { Reckonings.birth(it) } }
    Header(stringResource(R.string.birth_title), person?.let { personLabel(it) }, onBack)
    Body {
        when {
            person == null -> NoPerson(onPeople)
            b == null -> {
                Text(stringResource(R.string.birth_natal_needed), style = body.copy(fontSize = 15.sp, lineHeight = 21.sp, color = Palette.muted), modifier = Modifier.padding(top = 12.dp))
                ScreenLinkRow(stringResource(R.string.menu_people), stringResource(R.string.health_choose_person), onPeople, Icons.Person)
                ClanSection(person, onOpen)
            }
            else -> BirthReckoningView(person, b, onOpen)
        }
    }
}

/**
 * The year of age (SPEC §5.9.1) of [person] in the Tibetan year [year], stepped a year at a time from their first;
 * the hour of reckoning is the present one at [zone], whatever the year.
 */
@Composable
private fun YearOfAgeView(
    person: Person?,
    year: Int,
    zone: ZoneId,
    onYear: (Int) -> Unit,
    onPeople: () -> Unit,
    onBack: () -> Unit,
    onOpen: (Annotation) -> Unit,
) {
    val labels = LocalLabels.current
    val sign = YearOfLife.yearSign(year)
    val r = remember(person, year) { person?.let { Reckonings.yearOfAge(it, year, hourOfReckoning(zone)) } }
    Header(
        stringResource(R.string.year_title),
        labels.string(R.string.reckonings_year_header, labels.string(R.string.element_animal, gloss(sign.element), gloss(sign.animal)), year, year + 1),
        onBack,
    ) {
        if (person != null) {
            val first = Reckonings.birthYear(person)
            IconButton(onClick = { onYear(year - 1) }, enabled = year > first) {
                Icon(Icons.ChevronLeft, contentDescription = stringResource(R.string.reckonings_year_before), tint = if (year > first) Palette.muted else Palette.faint)
            }
            IconButton(onClick = { onYear(year + 1) }) {
                Icon(Icons.ChevronRight, contentDescription = stringResource(R.string.reckonings_year_after), tint = Palette.muted)
            }
        }
    }
    Body {
        if (person == null || r == null) NoPerson(onPeople) else YearOfAge(person, r, onOpen)
    }
}
