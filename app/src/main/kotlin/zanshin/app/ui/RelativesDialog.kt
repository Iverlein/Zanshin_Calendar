/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.iverlein.zanshin.R
import zanshin.app.Labels
import zanshin.app.LocalLabels
import zanshin.app.Moment
import zanshin.app.People
import zanshin.app.Relation
import zanshin.app.Relative
import zanshin.app.Relatives
import zanshin.core.texts.gloss
import zanshin.core.tibetan.Gender
import zanshin.core.tibetan.YearOfLife
import java.time.LocalDate
import java.time.LocalTime

/** The earliest year a relative's birth or death can be given in: forefathers reach back. */
private const val FIRST_YEAR = 1800

/** A relation's word for a gender, in the form of the one another is entered under when [of] is true. */
private fun word(labels: Labels, relation: Relation, gender: Gender?, of: Boolean): String {
    val id = when (relation) {
        Relation.MOTHER -> if (of) R.string.relation_mother_of else R.string.relation_mother
        Relation.FATHER -> if (of) R.string.relation_father_of else R.string.relation_father
        Relation.SPOUSE -> when (gender) {
            Gender.MALE -> if (of) R.string.relation_husband_of else R.string.relation_husband
            Gender.FEMALE -> if (of) R.string.relation_wife_of else R.string.relation_wife
            null -> if (of) R.string.relation_spouse_of else R.string.relation_spouse
        }
        Relation.CHILD -> when (gender) {
            Gender.MALE -> if (of) R.string.relation_son_of else R.string.relation_son
            Gender.FEMALE -> if (of) R.string.relation_daughter_of else R.string.relation_daughter
            null -> if (of) R.string.relation_child_of else R.string.relation_child
        }
        Relation.SIBLING -> when (gender) {
            Gender.MALE -> if (of) R.string.relation_brother_of else R.string.relation_brother
            Gender.FEMALE -> if (of) R.string.relation_sister_of else R.string.relation_sister
            null -> if (of) R.string.relation_sibling_of else R.string.relation_sibling
        }
        Relation.HOUSEHOLD -> if (of) R.string.relation_household_of else R.string.relation_household
    }
    return labels.string(id)
}

/** What [r] is to the person, capitalised: "Father's mother". */
internal fun relativeLabel(tree: Relatives, r: Relative, labels: Labels): String =
    tree.label(r, { rel, g, of -> word(labels, rel, g, of) }, { a, b -> labels.string(R.string.relative_label_of, a, b) })
        .replaceFirstChar { it.uppercase(labels.locale) }

/** A birth or death as the list shows it: the date with its hour, the date, or the year. */
private fun momentText(m: Moment, labels: Labels): String = when {
    m.time != null -> "${m.date!!.format(labels.shortDateYear)} ${m.time.format(labels.clock)}"
    m.date != null -> m.date.format(labels.shortDateYear)
    else -> "${m.year}"
}

/** A relative's line under their relation: the name, the birth and death, the household. */
private fun details(r: Relative, labels: Labels): String = listOfNotNull(
    r.name.takeIf { it.isNotBlank() },
    r.birth?.let { labels.string(R.string.relative_born_value, momentText(it, labels)) },
    if (r.died) r.death?.let { labels.string(R.string.relative_died_value, momentText(it, labels)) } ?: labels.string(R.string.relative_died_undated) else null,
    if (r.household && r.relation != Relation.HOUSEHOLD) labels.string(R.string.relative_in_household) else null,
).joinToString(" · ")

/**
 * The relatives of one person (SPEC §10.5, ROADMAP P1) as a tree: each with what they are to the person, their
 * details, a plus to enter a relative of theirs and a pencil to change them; at the foot, a relative of the person.
 * Changes go to [onChange] at once; the person's dialog saves them with the person.
 */
@Composable
fun RelativesDialog(owner: String, relatives: Relatives, onChange: (Relatives) -> Unit, onDismiss: () -> Unit) {
    val labels = LocalLabels.current
    // The relative being changed, or a new one's place (the one they are entered under, null for the person).
    var editing by remember { mutableStateOf<Relative?>(null) }
    var adding by remember { mutableStateOf<Int?>(null) }
    var addingOpen by remember { mutableStateOf(false) }
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = Palette.surface,
        title = {
            Column {
                Text(stringResource(R.string.relatives_title), style = body.copy(fontSize = 20.sp, fontWeight = FontWeight.SemiBold))
                Text(owner, style = body.copy(fontSize = 14.sp, color = Palette.muted))
            }
        },
        text = {
            Column(Modifier.verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(stringResource(R.string.relatives_note), style = body.copy(fontSize = 12.sp, color = Palette.faint, lineHeight = 17.sp), modifier = Modifier.padding(bottom = 8.dp))
                for ((r, depth) in relatives.walk()) {
                    val label = relativeLabel(relatives, r, labels)
                    Row(
                        Modifier.fillMaxWidth().heightIn(min = 52.dp).padding(start = (depth * 14).dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Column(Modifier.weight(1f).padding(vertical = 6.dp)) {
                            Text(label, style = body.copy(fontSize = 15.sp))
                            details(r, labels).takeIf { it.isNotEmpty() }?.let {
                                Text(it, style = body.copy(fontSize = 13.sp, color = Palette.muted, lineHeight = 17.sp))
                            }
                        }
                        if (r.relation != Relation.HOUSEHOLD && !relatives.full) {
                            IconButton(onClick = { adding = r.id; addingOpen = true }) {
                                Icon(Icons.Plus, contentDescription = stringResource(R.string.relatives_add_of, label), tint = Palette.muted, modifier = Modifier.size(18.dp))
                            }
                        }
                        IconButton(onClick = { editing = r }) {
                            Icon(Icons.Pencil, contentDescription = stringResource(R.string.relative_edit, label), tint = Palette.muted, modifier = Modifier.size(18.dp))
                        }
                    }
                }
                if (relatives.full) {
                    Text(stringResource(R.string.relatives_full, Relatives.MAX), style = body.copy(fontSize = 13.sp, color = Palette.muted), modifier = Modifier.padding(vertical = 12.dp))
                } else {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .heightIn(min = 48.dp)
                            .clickable(role = Role.Button) { adding = null; addingOpen = true },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        Icon(Icons.Plus, contentDescription = null, tint = Palette.saffron, modifier = Modifier.size(20.dp))
                        Text(stringResource(R.string.relatives_add), style = body.copy(fontSize = 16.sp, color = Palette.saffron))
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) { Text(stringResource(R.string.relatives_done), color = Palette.saffron) }
        },
    )
    if (addingOpen) {
        RelativeDialog(
            tree = relatives,
            owner = owner,
            initial = null,
            of = adding,
            onSave = { onChange(relatives.put(it)); addingOpen = false },
            onDelete = null,
            onDismiss = { addingOpen = false },
        )
    }
    editing?.let { r ->
        RelativeDialog(
            tree = relatives,
            owner = owner,
            initial = r,
            of = r.of,
            onSave = { onChange(relatives.put(it)); editing = null },
            onDelete = { onChange(relatives.remove(r.id)); editing = null },
            onDismiss = { editing = null },
        )
    }
}

/**
 * Enters a relative under [of] (null: the person [owner]) or changes [initial]: the relation among those still open
 * there, a name that may stay blank, the gender where the relation does not give it, the birth, living or died with
 * the death, and the household. A birth year or a year of death is needed to save.
 */
@Composable
private fun RelativeDialog(
    tree: Relatives,
    owner: String,
    initial: Relative?,
    of: Int?,
    onSave: (Relative) -> Unit,
    onDelete: (() -> Unit)?,
    onDismiss: () -> Unit,
) {
    val labels = LocalLabels.current
    val free = tree.relationsFree(of, keeping = initial)
    var relation by remember { mutableStateOf(initial?.relation ?: free.first()) }
    var name by remember { mutableStateOf(initial?.name ?: "") }
    var gender by remember { mutableStateOf(initial?.gender) }
    var birth by remember { mutableStateOf(initial?.birth) }
    var died by remember { mutableStateOf(initial?.died ?: false) }
    var death by remember { mutableStateOf(initial?.death) }
    var household by remember { mutableStateOf(initial?.household ?: false) }
    val ofLabel = of?.let(tree::byId)?.let { relativeLabel(tree, it, labels) } ?: owner
    val thisYear = LocalDate.now().year
    val datesFit = birth == null || death == null || death!!.year >= birth!!.year
    val ready = (birth != null || (died && death != null)) && datesFit

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = Palette.surface,
        title = {
            Text(
                stringResource(if (initial == null) R.string.relative_add_title else R.string.relative_edit_title),
                style = body.copy(fontSize = 20.sp, fontWeight = FontWeight.SemiBold),
            )
        },
        text = {
            Column(Modifier.verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Field(stringResource(R.string.relative_of_whom)) { Text(ofLabel, style = body.copy(fontSize = 16.sp)) }
                Field(stringResource(R.string.relative_relation)) {
                    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        for (r in free) {
                            Choice(word(labels, r, null, false).replaceFirstChar { it.uppercase(labels.locale) }, relation == r) {
                                relation = r
                                if (r == Relation.HOUSEHOLD) household = true
                            }
                        }
                    }
                }
                Field(stringResource(R.string.relative_name)) {
                    val description = stringResource(R.string.relative_name)
                    BasicTextField(
                        value = name,
                        onValueChange = { if (it.length <= People.NAME_MAX) name = it.replace('\n', ' ') },
                        singleLine = true,
                        textStyle = body.copy(fontSize = 16.sp),
                        cursorBrush = SolidColor(Palette.text),
                        keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words),
                        modifier = Modifier.fillMaxWidth().box().semantics { contentDescription = description },
                    )
                }
                if (relation != Relation.MOTHER && relation != Relation.FATHER) {
                    Field(stringResource(R.string.person_gender)) {
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            listOf(null to R.string.person_gender_none, Gender.MALE to R.string.person_gender_male, Gender.FEMALE to R.string.person_gender_female)
                                .forEach { (value, label) -> Choice(stringResource(label), gender == value, Modifier.weight(1f)) { gender = value } }
                        }
                    }
                }
                MomentField(stringResource(R.string.relative_born), birth, thisYear, labels) { birth = it }
                Field(stringResource(R.string.relative_life)) {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Choice(stringResource(R.string.relative_living), !died, Modifier.weight(1f)) { died = false; death = null }
                        Choice(stringResource(R.string.relative_dead), died, Modifier.weight(1f)) { died = true }
                    }
                }
                if (died) MomentField(stringResource(R.string.relative_died_when), death, thisYear, labels) { death = it }
                if (relation != Relation.HOUSEHOLD) {
                    Row(
                        Modifier.fillMaxWidth().heightIn(min = 48.dp).toggleable(value = household, role = Role.Switch) { household = it },
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(stringResource(R.string.relative_household), style = body.copy(fontSize = 15.sp), modifier = Modifier.weight(1f))
                        Switch(
                            checked = household,
                            onCheckedChange = null,
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Palette.ink,
                                checkedTrackColor = Palette.saffron,
                                uncheckedThumbColor = Palette.muted,
                                uncheckedTrackColor = Palette.raised,
                                uncheckedBorderColor = Palette.lineStrong,
                            ),
                        )
                    }
                }
                if (!ready) Text(stringResource(R.string.relative_needs_date), style = body.copy(fontSize = 13.sp, color = Palette.muted, lineHeight = 18.sp))
                if (initial != null && tree.branch(initial.id) > 0) {
                    Text(stringResource(R.string.relative_branch, tree.branch(initial.id)), style = body.copy(fontSize = 12.sp, color = Palette.faint, lineHeight = 17.sp))
                }
            }
        },
        confirmButton = {
            TextButton(
                enabled = ready,
                onClick = {
                    onSave(
                        Relative(
                            id = initial?.id ?: 0,
                            of = of,
                            relation = relation,
                            name = People.clean(name),
                            gender = if (relation == Relation.MOTHER || relation == Relation.FATHER) null else gender,
                            birth = birth,
                            died = died,
                            death = if (died) death else null,
                            household = relation == Relation.HOUSEHOLD || household,
                        ),
                    )
                },
            ) { Text(stringResource(R.string.birth_save), color = if (ready) Palette.saffron else Palette.faint) }
        },
        dismissButton = {
            Row {
                if (onDelete != null) TextButton(onClick = onDelete) { Text(stringResource(R.string.person_delete), color = Palette.vermilion) }
                TextButton(onClick = onDismiss) { Text(stringResource(R.string.birth_cancel), color = Palette.muted) }
            }
        },
    )
}

/** A labelled part of the dialog. */
@Composable
private fun Field(label: String, content: @Composable () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(label, style = body.copy(fontSize = 13.sp, color = Palette.muted))
        content()
    }
}

private fun Modifier.box(): Modifier = border(1.dp, Palette.lineStrong, RoundedCornerShape(8.dp)).padding(horizontal = 12.dp, vertical = 12.dp)

/** One of a set of choices, outlined, the chosen one in the accent. */
@Composable
private fun Choice(text: String, chosen: Boolean, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Text(
        text,
        style = body.copy(
            fontSize = 15.sp,
            color = if (chosen) Palette.saffron else Palette.text,
            fontWeight = if (chosen) FontWeight.SemiBold else FontWeight.Normal,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
        ),
        modifier = modifier
            .border(1.dp, if (chosen) Palette.saffron else Palette.lineStrong, RoundedCornerShape(8.dp))
            .clickable(role = Role.RadioButton, onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 12.dp),
    )
}

/**
 * A birth or a death: the year typed, with the Tibetan year it stands for (the date's, once a day is chosen), then, if known, the day from the
 * calendar and the hour from the clock; "Year only" and "No hour" take them back off.
 */
@Composable
private fun MomentField(label: String, moment: Moment?, thisYear: Int, labels: Labels, onChange: (Moment?) -> Unit) {
    var yearText by remember { mutableStateOf(moment?.year?.toString() ?: "") }
    var picking by remember { mutableStateOf(false) }
    var timing by remember { mutableStateOf(false) }
    Field(label) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            val description = "$label, ${stringResource(R.string.relative_year)}"
            BasicTextField(
                value = yearText,
                onValueChange = { text ->
                    val digits = text.filter(Char::isDigit).take(4)
                    yearText = digits
                    val year = digits.toIntOrNull()?.takeIf { digits.length == 4 && it in FIRST_YEAR..thisYear }
                    // A new year keeps the day only if it was in that year: a typed year is a year alone.
                    onChange(year?.let { y -> moment?.takeIf { it.year == y } ?: Moment(y) })
                },
                singleLine = true,
                textStyle = body.copy(fontSize = 16.sp),
                cursorBrush = SolidColor(Palette.text),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                decorationBox = { inner ->
                    if (yearText.isEmpty()) Text(stringResource(R.string.relative_year), style = body.copy(fontSize = 16.sp, color = Palette.faint))
                    inner()
                },
                modifier = Modifier.width(96.dp).box().semantics { contentDescription = description },
            )
            moment?.let { m ->
                val sign = YearOfLife.yearSign(m.tibetanYear)
                Text(
                    labels.string(R.string.relative_year_sign, labels.string(R.string.element_animal, gloss(sign.element), gloss(sign.animal))),
                    style = body.copy(fontSize = 14.sp, color = Palette.muted),
                )
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            val day = moment?.date
            Link(day?.format(labels.shortDateYear) ?: stringResource(R.string.relative_day)) { picking = true }
            if (day != null) {
                Link(moment.time?.format(labels.clock) ?: stringResource(R.string.relative_hour)) { timing = true }
                if (moment.time != null) Link(stringResource(R.string.relative_no_hour)) { onChange(moment.copy(time = null)) }
                else Link(stringResource(R.string.relative_year_only)) { onChange(Moment(moment.year)) }
            }
        }
        if (moment == null || moment.date == null) Text(stringResource(R.string.relative_year_note), style = body.copy(fontSize = 12.sp, color = Palette.faint, lineHeight = 17.sp))
    }
    if (picking) {
        BirthDateDialog(
            initial = moment?.date ?: moment?.let { LocalDate.of(it.year, 1, 1) },
            title = label,
            years = FIRST_YEAR..thisYear,
            onSave = { d ->
                onChange(Moment.of(d, moment?.time))
                yearText = d.year.toString()
                picking = false
            },
            onDismiss = { picking = false },
        )
    }
    if (timing && moment?.date != null) {
        BellTimeDialog(
            title = label,
            time = moment.time ?: LocalTime.NOON,
            note = stringResource(R.string.relative_hour_note),
            onSet = { onChange(Moment.of(moment.date, it)); timing = false },
            onDismiss = { timing = false },
        )
    }
}

/** A small action in the accent, 44 dp high. */
@Composable
private fun Link(text: String, onClick: () -> Unit) {
    Text(
        text,
        style = body.copy(fontSize = 14.sp, color = Palette.saffron),
        modifier = Modifier.heightIn(min = 44.dp).clickable(role = Role.Button, onClick = onClick).padding(horizontal = 4.dp, vertical = 12.dp),
    )
}
