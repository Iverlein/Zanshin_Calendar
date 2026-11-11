/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.iverlein.zanshin.R
import java.time.LocalDate
import java.time.LocalTime
import zanshin.app.LocalLabels
import zanshin.core.astro.Place
import zanshin.core.texts.Catalog
import zanshin.core.tibetan.DreamGroup
import zanshin.core.tibetan.DreamImage
import zanshin.core.tibetan.DreamNight
import zanshin.core.tibetan.DreamReading
import zanshin.core.tibetan.DreamText
import zanshin.core.tibetan.DreamWatch

/** The heading of each text's verdicts (SPEC §5.9.11): the screen names the texts, their rows name none. */
private val TEXT_HEADING = mapOf(
    DreamText.WB to R.string.dream_text_wb,
    DreamText.ST to R.string.dream_text_st,
    DreamText.LGD to R.string.dream_text_lgd,
    DreamText.SVO to R.string.dream_text_svo,
    DreamText.SVI to R.string.dream_text_svi,
    DreamText.SKP to R.string.dream_text_skp,
    DreamText.KLONG to R.string.dream_text_klong,
    DreamText.RRT to R.string.dream_text_rrt,
    DreamText.KCM to R.string.dream_text_kcm,
)

/**
 * The dream reading (SPEC §5.9.11, ROADMAP S2): the night before [date] at [place], its part by a chip or a clock time
 * on the night's four equal quarters, whether the dreamer is ill, the images ticked in a picker, then the line of the
 * night and, for a dawn dream, each text's verdict on each image under the text's heading and one Remedy row. Nothing
 * is summed: the screen never calls a dream good or bad as a whole.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DreamReadingView(date: LocalDate, place: Place?) {
    val labels = LocalLabels.current
    val night = remember(date, place) { place?.let { DreamNight.before(date, it) } }
    var watch by rememberSaveable { mutableStateOf(DreamWatch.DAWN) }
    var time by rememberSaveable { mutableStateOf<LocalTime?>(null) }
    var outside by rememberSaveable { mutableStateOf<LocalTime?>(null) }
    var ill by rememberSaveable { mutableStateOf(false) }
    var chosen by rememberSaveable { mutableStateOf(listOf<String>()) }
    var clock by remember { mutableStateOf(false) }
    var picking by remember { mutableStateOf(false) }
    val images = chosen.map(DreamImage::valueOf)
    val reading = DreamReading(watch, ill, images)

    SectionTitle(stringResource(R.string.dream_section_when))
    val muted = body.copy(fontSize = 14.sp, lineHeight = 20.sp, color = Palette.muted)
    Text(
        if (night == null) stringResource(R.string.dream_no_place)
        else labels.string(R.string.dream_night, date.format(labels.headerDate), night.sunset.format(labels.clock), night.sunrise.format(labels.clock)),
        style = muted,
    )
    FlowRow(Modifier.padding(vertical = 6.dp), horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        for (w in DreamWatch.entries) {
            val on = w == watch
            Text(
                w.english,
                style = body.copy(fontSize = 14.sp, fontWeight = if (on) FontWeight.SemiBold else FontWeight.Normal, color = if (on) Palette.saffron else Palette.muted),
                modifier = Modifier
                    .heightIn(min = 40.dp)
                    .border(1.dp, if (on) Palette.saffron else Palette.lineStrong, RoundedCornerShape(20.dp))
                    .clickable(role = Role.RadioButton) { watch = w; time = null; outside = null }
                    .padding(horizontal = 14.dp, vertical = 10.dp),
            )
        }
    }
    if (night != null) {
        ScreenLinkRow(
            time?.let { labels.string(R.string.dream_time_set, it.format(labels.clock)) } ?: stringResource(R.string.dream_time_title),
            labels.string(R.string.dream_time_subtitle, night.quarter(1).format(labels.clock), night.quarter(2).format(labels.clock), night.quarter(3).format(labels.clock)),
            onClick = { clock = true },
            icon = Icons.Clock,
        )
        outside?.let { Text(labels.string(R.string.dream_time_outside, it.format(labels.clock)), style = muted) }
    }
    IllRow(ill) { ill = it }
    Text(withTibetan(Catalog.text(reading.lineKey)), style = body.copy(fontSize = 15.sp, lineHeight = 22.sp), modifier = Modifier.padding(top = 8.dp, bottom = 4.dp))

    if (watch.read) {
        SectionTitle(stringResource(R.string.dream_section_images))
        val shown = images.filter { it.offered(ill) }
        ScreenLinkRow(
            stringResource(R.string.dream_choose),
            if (shown.isEmpty()) stringResource(R.string.dream_choose_none) else labels.string(R.string.dream_chosen, shown.size),
            onClick = { picking = true },
            icon = Icons.Check,
        )
        if (shown.isEmpty()) {
            Text(stringResource(R.string.dream_no_images), style = muted)
        } else {
            SectionTitle(stringResource(R.string.dream_section_reading))
            for ((text, verdicts) in reading.byText) {
                Text(
                    withTibetan(stringResource(TEXT_HEADING.getValue(text))),
                    style = body.copy(fontSize = 15.sp, fontWeight = FontWeight.SemiBold),
                    modifier = Modifier.padding(top = 10.dp, bottom = 2.dp),
                )
                for (v in verdicts) VerdictRow(v.image.english, Catalog.text(v.key), v.tone)
            }
            if (reading.remedies.isNotEmpty()) {
                SectionTitle(Catalog.text("ReadingRow.REMEDY"))
                for (r in reading.remedies) {
                    val line = Catalog.text(r.key).replaceFirstChar(Char::uppercase).let { if (it.endsWith('.')) it else "$it." }
                    Text(withTibetan(line), style = body.copy(fontSize = 14.sp, lineHeight = 20.sp), modifier = Modifier.padding(vertical = 4.dp))
                }
            }
        }
    }

    if (clock && night != null) {
        BellTimeDialog(
            title = stringResource(R.string.dream_time_dialog),
            time = time ?: night.quarter(3).toLocalTime(),
            note = null,
            onSet = { t ->
                clock = false
                val w = night.watch(night.at(t))
                if (w == null) {
                    outside = t
                    time = null
                } else {
                    watch = w
                    time = t
                    outside = null
                }
            },
            onDismiss = { clock = false },
        )
    }
    if (picking) {
        DreamPickerSheet(ill, images.toSet(), onToggle = { i -> chosen = if (i.name in chosen) chosen - i.name else chosen + i.name }) { picking = false }
    }
}

/** One text's verdict on one image: its tone dot, the image, and the verdict under it. */
@Composable
private fun VerdictRow(image: String, verdict: String, tone: zanshin.core.kyureki.Tone) {
    val spoken = toneLabel(tone)
    Row(Modifier.fillMaxWidth().padding(vertical = 6.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        ToneDot(tone, modifier = Modifier.padding(top = 6.dp).semantics { contentDescription = spoken })
        Column(Modifier.weight(1f)) {
            Text(withTibetan(image), style = body.copy(fontSize = 15.sp, fontWeight = FontWeight.Medium))
            Text(withTibetan(verdict), style = body.copy(fontSize = 14.sp, lineHeight = 20.sp, color = Palette.muted))
        }
    }
}

/** The "dreamer is ill" switch: a checkbox among the inputs, not a section (the owner, 2026-11-09). */
@Composable
private fun IllRow(ill: Boolean, onChange: (Boolean) -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
            .toggleable(value = ill, role = Role.Switch, onValueChange = onChange),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Icon(Icons.Pulse, contentDescription = null, tint = Palette.muted, modifier = Modifier.size(20.dp))
        Column(Modifier.weight(1f)) {
            Text(stringResource(R.string.dream_ill), style = body.copy(fontSize = 16.sp, fontWeight = FontWeight.Medium))
            Text(stringResource(R.string.dream_ill_subtitle), style = body.copy(fontSize = 13.sp, lineHeight = 18.sp, color = Palette.muted))
        }
        Switch(
            checked = ill,
            onCheckedChange = null,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Palette.onAccent,
                checkedTrackColor = Palette.vermilion,
                uncheckedThumbColor = Palette.muted,
                uncheckedTrackColor = Palette.raised,
                uncheckedBorderColor = Palette.lineStrong,
            ),
        )
    }
}

/**
 * The images as a grouped, searchable multi-select (ROADMAP S2), the election's work picker generalised: the groups in
 * the catalogue's order, each opening its images, a search over their names in the app's language, each image a tick;
 * the images of the sick only when the dreamer is [ill].
 */
@Composable
private fun DreamPickerSheet(ill: Boolean, chosen: Set<DreamImage>, onToggle: (DreamImage) -> Unit, onDismiss: () -> Unit) {
    val offered = remember(ill) { DreamImage.offered(ill) }
    var query by rememberSaveable { mutableStateOf("") }
    var open by rememberSaveable { mutableStateOf<String?>(null) }
    val locale = LocalLabels.current.locale
    val matches = remember(query, locale, offered) {
        val q = query.trim().lowercase(locale)
        if (q.isEmpty()) emptyList() else offered.values.flatten().filter { q in it.english.lowercase(locale) }.sortedBy { it.english.lowercase(locale) }
    }
    Sheet(onDismiss) {
        Text(
            stringResource(R.string.dream_choose),
            style = body.copy(fontSize = 22.sp, fontWeight = FontWeight.SemiBold),
            modifier = Modifier.padding(start = 24.dp, end = 24.dp, bottom = 12.dp),
        )
        LazyColumn(Modifier.fillMaxSize(), contentPadding = androidx.compose.foundation.layout.PaddingValues(start = 24.dp, end = 24.dp, bottom = 32.dp)) {
            item {
                Column(Modifier.padding(bottom = 12.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(stringResource(R.string.dream_picker_note), style = body.copy(fontSize = 16.sp, color = Palette.muted))
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .background(Palette.surface, RoundedCornerShape(12.dp))
                            .border(1.dp, Palette.line, RoundedCornerShape(12.dp))
                            .padding(horizontal = 14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        Icon(Icons.Search, contentDescription = null, tint = Palette.faint, modifier = Modifier.size(18.dp))
                        BasicTextField(
                            value = query,
                            onValueChange = { query = it },
                            singleLine = true,
                            textStyle = body.copy(fontSize = 16.sp),
                            cursorBrush = SolidColor(Palette.text),
                            modifier = Modifier.weight(1f),
                            decorationBox = { inner ->
                                if (query.isEmpty()) Text(stringResource(R.string.dream_search), style = body.copy(fontSize = 16.sp, color = Palette.faint))
                                inner()
                            },
                        )
                    }
                }
            }
            if (query.isNotBlank()) {
                if (matches.isEmpty()) item { Text(stringResource(R.string.election_no_match), style = body.copy(color = Palette.faint)) }
                items(matches, key = { "match ${it.name}" }) { ImageRow(it, it in chosen, indent = false, onToggle) }
            } else {
                for ((group, list) in offered) {
                    item(key = "group ${group.name}") {
                        GroupRow(group, list.size, list.count { it in chosen }, open == group.name) { open = if (open == group.name) null else group.name }
                    }
                    if (open == group.name) items(list, key = { "image ${it.name}" }) { ImageRow(it, it in chosen, indent = true, onToggle) }
                }
            }
        }
    }
}

@Composable
private fun GroupRow(group: DreamGroup, count: Int, ticked: Int, open: Boolean, onToggle: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .heightIn(min = 52.dp)
            .clickable(role = Role.Button, onClick = onToggle),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        Text(group.english, style = body.copy(fontSize = 16.sp, fontWeight = if (ticked > 0) FontWeight.SemiBold else FontWeight.Normal), modifier = Modifier.weight(1f))
        Text(if (ticked > 0) "$ticked / $count" else "$count", style = body.copy(fontSize = 13.sp, color = if (ticked > 0) Palette.saffron else Palette.faint))
        Icon(if (open) Icons.ChevronDown else Icons.ChevronRight, contentDescription = null, tint = Palette.faint, modifier = Modifier.size(16.dp))
    }
}

@Composable
private fun ImageRow(image: DreamImage, checked: Boolean, indent: Boolean, onToggle: (DreamImage) -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .heightIn(min = 48.dp)
            .toggleable(checked, role = Role.Checkbox) { onToggle(image) }
            .padding(start = if (indent) 16.dp else 0.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(withTibetan(image.english), style = body.copy(fontSize = 15.sp), modifier = Modifier.weight(1f))
        if (checked) Icon(Icons.Check, contentDescription = null, tint = Palette.saffron, modifier = Modifier.size(20.dp))
    }
}
