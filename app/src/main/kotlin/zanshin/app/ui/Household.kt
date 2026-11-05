/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.iverlein.zanshin.R
import zanshin.app.HouseholdReading
import zanshin.app.Labels
import zanshin.app.LocalLabels
import zanshin.app.Person
import zanshin.core.kyureki.Tone
import zanshin.core.texts.Catalog
import zanshin.core.texts.Texts
import zanshin.core.tibetan.HouseholdMember
import zanshin.core.tibetan.HouseholdRound
import zanshin.core.tibetan.RoundColour
import zanshin.core.tibetan.RoundPebble
import zanshin.core.tibetan.RoundRead
import zanshin.core.tibetan.SME_BA_COLOURS

/**
 * The household round (SPEC §5.9.10, ROADMAP R9): who is counted, then, with four or more, the household's pebbles
 * together and each place by the colours that fall in it, every reading a row that opens through [onOpen].
 */
@Composable
fun HouseholdRoundView(person: Person, h: HouseholdReading, onOpen: (Annotation) -> Unit) {
    val labels = LocalLabels.current
    val muted = body.copy(fontSize = 13.sp, color = Palette.muted, lineHeight = 19.sp)
    val tree = person.relatives
    val self = personLabel(person)
    fun who(m: HouseholdMember): String {
        val r = m.tag?.let { id -> tree.list.firstOrNull { it.id == id } } ?: return self
        return r.name.ifBlank { relativeLabel(tree, r, labels) }
    }
    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        val round = h.round
        if (round == null) Text(stringResource(R.string.household_needed), style = muted)
        if (h.missing.isNotEmpty()) {
            val names = h.missing.joinToString(", ") { r ->
                if (r == null) labels.string(R.string.household_you) else r.name.ifBlank { relativeLabel(tree, r, labels) }
            }
            Text(labels.string(R.string.household_missing, names), style = muted)
        }
        if (round != null) {
            val rows = round.reads.map(Texts::householdRead)
            val (whites, blacks) = round.plain.count { it.colour == RoundColour.WHITE } to round.plain.count { it.colour == RoundColour.BLACK }
            Section(stringResource(R.string.match_section_together))
            AnnotationRow(
                Annotation(
                    title = stringResource(R.string.household_together_title),
                    english = "",
                    tone = if (round.blackTheMore) Tone.BAD else Tone.GOOD,
                    reading = Texts.householdTogether(round.blackTheMore, gatheredRemedies(rows)),
                    subtitle = labels.string(R.string.household_counts, whites, blacks),
                    titleIsKanji = false,
                ),
                onOpen,
            )
        }
        Section(stringResource(R.string.household_section_members))
        h.members.forEach { m ->
            val n = m.year.currentSmeBa
            val marks = HouseholdRound.column(m).dropLast(1).joinToString(" ") { mark(it.colour) }
            MemberRow(who(m), marks + " · " + labels.string(R.string.household_member_line, labels.ordinal(m.year.age), n, Catalog.text("Colour.${SME_BA_COLOURS[n - 1]}")))
        }
        if (round != null) {
            Place(stringResource(R.string.household_section_god), pebblesLine(round.god, true, labels), round.godReads + round.tailReads, onOpen)
            Place(stringResource(R.string.household_section_people), pebblesLine(round.people, true, labels), round.peopleReads, onOpen)
            Place(stringResource(R.string.household_section_house), pebblesLine(round.house, false, labels), round.houseReads, onOpen)
            Place(stringResource(R.string.household_section_enemy), pebblesLine(round.enemy, false, labels), round.enemyReads, onOpen)
            Place(stringResource(R.string.household_section_leftover), leftoverLine(round, labels), round.leftoverReads + round.omenReads, onOpen)
        }
    }
}

/** A place: its title, the pebbles that fell there, and a row for each reading. */
@Composable
private fun Place(title: String, line: String, reads: List<RoundRead>, onOpen: (Annotation) -> Unit) {
    Section(title)
    Text(line, style = body.copy(fontSize = 13.sp, color = Palette.muted, lineHeight = 19.sp))
    reads.forEach { r ->
        val reading = Texts.householdRead(r)
        AnnotationRow(
            Annotation(
                title = Catalog.text("HouseholdRow.${r.family}.${r.key}"),
                english = "",
                tone = when (r.good) {
                    true -> Tone.GOOD
                    false -> Tone.BAD
                    null -> Tone.MIXED
                },
                reading = reading,
                titleIsKanji = false,
            ),
            onOpen,
        )
    }
}

@Composable
private fun MemberRow(name: String, line: String) {
    Column {
        Text(name, style = body.copy(fontSize = 16.sp))
        Text(line, style = body.copy(fontSize = 13.sp, lineHeight = 18.sp, color = Palette.muted))
    }
}

/** A deciding pebble as the charts draw it: white ○, black ×, speckled ◐. */
private fun mark(c: RoundColour): String = when (c) {
    RoundColour.WHITE -> "○"
    RoundColour.BLACK -> "×"
    else -> "◐"
}

private fun word(p: RoundPebble, labels: Labels): String =
    if (p.colour == RoundColour.SPECKLED) labels.string(R.string.year_colour_speckled) else Catalog.text("Colour.${p.colour.name.lowercase()}")

private fun pebblesLine(row: List<RoundPebble>, tail: Boolean, labels: Labels): String {
    val (a, b, c) = row.take(3).map { word(it, labels) }
    val line = if (tail) labels.string(R.string.household_pebbles_tail, a, b, c, word(row.last(), labels)) else labels.string(R.string.household_pebbles, a, b, c)
    return if (row.take(3).any { it.colour == RoundColour.SPECKLED } || (tail && row.last().colour == RoundColour.SPECKLED)) line + " · " + labels.string(R.string.household_no_line) else line
}

private fun leftoverLine(r: HouseholdRound, labels: Labels): String = listOfNotNull(
    r.fold?.let { labels.string(R.string.household_leftover_fold, word(it, labels)) },
    r.refold.takeIf { it.size == 2 }?.let { labels.string(R.string.household_leftover_refold, word(it[0], labels), word(it[1], labels)) },
).joinToString(" · ").ifEmpty { labels.string(R.string.household_no_leftover) }
