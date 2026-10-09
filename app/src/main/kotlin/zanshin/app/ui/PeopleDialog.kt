/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.iverlein.zanshin.R
import zanshin.app.LocalLabels
import zanshin.app.People
import zanshin.app.Person
import zanshin.app.initials
import zanshin.core.tibetan.Gender
import java.time.LocalDate

/** How a person is listed: the name, or the birth date while the name is blank (a birth date saved before names were). */
@Composable
fun personLabel(person: Person): String = person.name.ifBlank { person.birth.format(LocalLabels.current.headerDate) }

/**
 * The saved people (SPEC §10.5): tapping one reads the pages for them and
 * closes the dialog; "No one" leaves the personal readings out. Each row
 * has its pencil to change or delete the person; a new one can be added
 * while fewer than [People.MAX] are saved.
 */
@Composable
fun PeopleDialog(people: People, onChoose: (Int?) -> Unit, onEdit: (Int?) -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = Palette.surface,
        title = { Text(stringResource(R.string.menu_people), style = body.copy(fontSize = 20.sp, fontWeight = FontWeight.SemiBold)) },
        text = {
            Column(Modifier.verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.spacedBy(2.dp)) {
                people.list.forEachIndexed { i, person ->
                    val label = personLabel(person)
                    PersonRow(
                        title = label,
                        subtitle = if (person.name.isBlank()) null else person.birth.format(LocalLabels.current.headerDate),
                        selected = people.active == i,
                        onClick = { onChoose(i) },
                        editDescription = stringResource(R.string.person_edit, label),
                        onEdit = { onEdit(i) },
                    )
                }
                PersonRow(
                    title = stringResource(R.string.people_no_one),
                    subtitle = stringResource(R.string.people_no_one_subtitle),
                    selected = people.active == null,
                    onClick = { onChoose(null) },
                )
                if (people.full) {
                    Text(
                        stringResource(R.string.people_full, People.MAX),
                        style = body.copy(fontSize = 13.sp, color = Palette.muted),
                        modifier = Modifier.padding(horizontal = 4.dp, vertical = 12.dp),
                    )
                } else {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .heightIn(min = 48.dp)
                            .clickable(role = Role.Button) { onEdit(null) }
                            .padding(horizontal = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        Icon(Icons.Plus, contentDescription = null, tint = Palette.saffron, modifier = Modifier.size(20.dp))
                        Text(stringResource(R.string.people_add), style = body.copy(fontSize = 16.sp, color = Palette.saffron))
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) { Text(stringResource(R.string.people_close), color = Palette.muted) }
        },
    )
}

@Composable
private fun PersonRow(
    title: String,
    subtitle: String?,
    selected: Boolean,
    onClick: () -> Unit,
    editDescription: String? = null,
    onEdit: (() -> Unit)? = null,
) {
    Row(
        Modifier
            .fillMaxWidth()
            .heightIn(min = 52.dp)
            .clickable(role = Role.RadioButton, onClick = onClick)
            .padding(start = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Box(Modifier.size(20.dp)) {
            if (selected) Icon(Icons.Check, contentDescription = stringResource(R.string.selected), tint = Palette.saffron, modifier = Modifier.size(20.dp))
        }
        Column(Modifier.weight(1f).padding(vertical = 6.dp)) {
            Text(title, style = body.copy(fontSize = 16.sp, fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal))
            if (subtitle != null) Text(subtitle, style = body.copy(fontSize = 13.sp, color = Palette.muted))
        }
        if (onEdit != null) {
            IconButton(onClick = onEdit) {
                Icon(Icons.Pencil, contentDescription = editDescription, tint = Palette.muted, modifier = Modifier.size(20.dp))
            }
        }
    }
}

/**
 * Adds a person ([initial] null) or changes one: a name and a birth date,
 * both needed to save, and the gender, which may stay not set. [onDelete]
 * is offered for a saved person.
 */
@Composable
fun PersonDialog(initial: Person?, onSave: (Person) -> Unit, onDelete: (() -> Unit)?, onDismiss: () -> Unit) {
    var name by remember { mutableStateOf(initial?.name ?: "") }
    var birth by remember { mutableStateOf<LocalDate?>(initial?.birth) }
    var gender by remember { mutableStateOf(initial?.gender) }
    var picking by remember { mutableStateOf(false) }
    val cleaned = People.clean(name)
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = Palette.surface,
        title = {
            Text(
                stringResource(if (initial == null) R.string.people_add else R.string.person_edit_title),
                style = body.copy(fontSize = 20.sp, fontWeight = FontWeight.SemiBold),
            )
        },
        text = {
            Column(Modifier.verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(stringResource(R.string.person_name), style = body.copy(fontSize = 13.sp, color = Palette.muted))
                    val nameDescription = stringResource(R.string.person_name)
                    BasicTextField(
                        value = name,
                        onValueChange = { if (it.length <= People.NAME_MAX) name = it.replace('\n', ' ') },
                        singleLine = true,
                        textStyle = body.copy(fontSize = 16.sp),
                        cursorBrush = SolidColor(Palette.text),
                        keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words),
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, Palette.lineStrong, RoundedCornerShape(8.dp))
                            .padding(horizontal = 12.dp, vertical = 12.dp)
                            .semantics { contentDescription = nameDescription },
                    )
                }
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(stringResource(R.string.menu_birth_date), style = body.copy(fontSize = 13.sp, color = Palette.muted))
                    Text(
                        birth?.format(LocalLabels.current.headerDate) ?: stringResource(R.string.person_choose_date),
                        style = body.copy(fontSize = 16.sp, color = if (birth == null) Palette.saffron else Palette.text),
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, Palette.lineStrong, RoundedCornerShape(8.dp))
                            .clickable(role = Role.Button) { picking = true }
                            .padding(horizontal = 12.dp, vertical = 12.dp),
                    )
                }
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(stringResource(R.string.person_gender), style = body.copy(fontSize = 13.sp, color = Palette.muted))
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        listOf(null to R.string.person_gender_none, Gender.MALE to R.string.person_gender_male, Gender.FEMALE to R.string.person_gender_female)
                            .forEach { (value, label) ->
                                val chosen = gender == value
                                Text(
                                    stringResource(label),
                                    style = body.copy(
                                        fontSize = 15.sp,
                                        color = if (chosen) Palette.saffron else Palette.text,
                                        fontWeight = if (chosen) FontWeight.SemiBold else FontWeight.Normal,
                                        textAlign = TextAlign.Center,
                                    ),
                                    modifier = Modifier
                                        .weight(1f)
                                        .border(1.dp, if (chosen) Palette.saffron else Palette.lineStrong, RoundedCornerShape(8.dp))
                                        .clickable(role = Role.RadioButton) { gender = value }
                                        .padding(horizontal = 8.dp, vertical = 12.dp),
                                )
                            }
                    }
                    Text(stringResource(R.string.person_gender_note), style = body.copy(fontSize = 12.sp, color = Palette.faint, lineHeight = 17.sp))
                }
                Text(stringResource(R.string.person_note), style = body.copy(fontSize = 12.sp, color = Palette.faint, lineHeight = 17.sp))
            }
        },
        confirmButton = {
            val ready = cleaned.isNotEmpty() && birth != null
            TextButton(onClick = { onSave(Person(cleaned, birth!!, gender)) }, enabled = ready) {
                Text(stringResource(R.string.birth_save), color = if (ready) Palette.saffron else Palette.faint)
            }
        },
        dismissButton = {
            Row {
                if (onDelete != null) TextButton(onClick = onDelete) { Text(stringResource(R.string.person_delete), color = Palette.vermilion) }
                TextButton(onClick = onDismiss) { Text(stringResource(R.string.birth_cancel), color = Palette.muted) }
            }
        },
    )
    if (picking) {
        BirthDateDialog(
            initial = birth,
            onSave = {
                birth = it
                picking = false
            },
            onDismiss = { picking = false },
        )
    }
}

/**
 * The header's switcher, shown once two people are saved: the chosen
 * person's initials in a ring, or the person glyph for a blank name or for
 * no one; tapping it opens [PeopleDialog].
 */
@Composable
fun PersonChip(person: Person?, onClick: () -> Unit) {
    val description = if (person == null) stringResource(R.string.person_chip_none) else stringResource(R.string.person_chip, personLabel(person))
    val letters = person?.let { initials(it.name) }.orEmpty()
    Box(
        Modifier
            .size(44.dp)
            .clickable(role = Role.Button, onClick = onClick)
            .semantics(mergeDescendants = true) { contentDescription = description },
        contentAlignment = Alignment.Center,
    ) {
        Box(
            Modifier
                .size(34.dp)
                .border(1.dp, if (person == null) Palette.lineStrong else Palette.muted, CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            if (letters.isNotEmpty()) {
                Text(letters, style = body.copy(fontSize = 13.sp, fontWeight = FontWeight.SemiBold))
            } else {
                Icon(Icons.Person, contentDescription = null, tint = if (person == null) Palette.faint else Palette.muted, modifier = Modifier.size(18.dp))
            }
        }
    }
}
