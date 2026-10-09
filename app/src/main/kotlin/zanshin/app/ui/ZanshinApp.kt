/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.ui

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.selection.toggleable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import kotlinx.coroutines.launch
import zanshin.app.AppLanguage
import zanshin.app.CalendarKind
import zanshin.app.Cities
import zanshin.app.DayInfo
import zanshin.app.Days
import zanshin.app.LocalLabels
import io.github.iverlein.zanshin.R
import zanshin.app.People
import zanshin.app.Person
import zanshin.app.SavedPlace
import zanshin.app.Settings
import zanshin.core.astro.SunTimes
import zanshin.core.kyureki.Kigaku
import java.time.LocalDate
import java.time.ZoneId

private enum class Screen { DAYS, LOCATION, ABOUT, ELECTION }

@Composable
fun ZanshinApp(settings: Settings, cities: Cities) {
    var calendar by remember { mutableStateOf(settings.calendar) }
    var place by remember { mutableStateOf(settings.place) }
    var people by remember { mutableStateOf(settings.people) }
    val birth = people.current?.birth
    var peopleDialog by remember { mutableStateOf(false) }
    // The person being changed in PersonDialog: an index, or -1 for a new one; null while it is closed.
    var editing by remember { mutableStateOf<Int?>(null) }
    var kigaku by remember { mutableStateOf(settings.kigaku) }
    val activity = LocalContext.current as Activity
    val language = remember { AppLanguage.current(activity) }
    var languageDialog by remember { mutableStateOf(false) }
    // Set while the people dialogs were opened by switching 九星気学 on.
    var kigakuPending by remember { mutableStateOf(false) }
    var screen by remember { mutableStateOf(Screen.DAYS) }
    var pickerOpen by remember { mutableStateOf(false) }
    // The election's work and the day it runs from (ROADMAP E2); a day it opens is shown when the pager is back.
    var electionWork by remember { mutableStateOf<zanshin.core.texts.Activity?>(null) }
    var electionFrom by remember { mutableStateOf(LocalDate.now()) }
    // Which calendar's election: the Tibetan weighing (E1–E3) or the 旧暦 listing (E5).
    var electionKyureki by remember { mutableStateOf(false) }
    var openDay by remember { mutableStateOf<LocalDate?>(null) }
    var today by remember { mutableStateOf(LocalDate.now()) }
    LifecycleEventEffect(Lifecycle.Event.ON_RESUME) { today = LocalDate.now() }

    val pager = rememberPagerState(initialPage = Days.pageOf(today)) { Days.count }
    val drawer = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    fun chooseCalendar(kind: CalendarKind) {
        calendar = kind
        settings.calendar = kind
    }

    fun savePeople(p: People) {
        people = p
        settings.people = p
        if (kigakuPending && p.current != null) {
            kigaku = true
            settings.kigaku = true
            kigakuPending = false
        }
    }

    // Choosing the person from the menu or the header: with no one saved yet, straight to adding one.
    fun openPeople() {
        if (people.list.isEmpty()) editing = -1 else peopleDialog = true
    }

    fun savePlace(p: SavedPlace) {
        place = p
        settings.place = p
        screen = Screen.DAYS
    }

    when (screen) {
        Screen.LOCATION -> {
            BackHandler { screen = Screen.DAYS }
            LocationScreen(current = place, cities = cities, onBack = { screen = Screen.DAYS }, onChoose = ::savePlace)
            return
        }
        Screen.ABOUT -> {
            BackHandler { screen = Screen.DAYS }
            AboutScreen(onBack = { screen = Screen.DAYS })
            return
        }
        Screen.ELECTION -> {
            BackHandler { screen = Screen.DAYS }
            ElectionScreen(
                from = electionFrom,
                birth = birth,
                kyureki = electionKyureki,
                work = electionWork,
                onWork = { electionWork = it },
                onOpenDay = {
                    chooseCalendar(if (electionKyureki) CalendarKind.KYUREKI else CalendarKind.TIBETAN)
                    openDay = it
                    screen = Screen.DAYS
                },
                onBack = { screen = Screen.DAYS },
            )
            return
        }
        Screen.DAYS -> Unit
    }

    ModalNavigationDrawer(
        drawerState = drawer,
        drawerContent = {
            SideMenu(
                calendar = calendar,
                placeLabel = place?.label,
                personLabel = people.current?.let { p ->
                    if (p.name.isBlank()) personLabel(p) else stringResource(R.string.menu_person_value, p.name, p.birth.format(LocalLabels.current.headerDate))
                } ?: stringResource(if (people.list.isEmpty()) R.string.menu_birth_not_set else R.string.menu_no_one_chosen),
                onPeople = {
                    scope.launch { drawer.close() }
                    openPeople()
                },
                kigaku = kigaku,
                onKigaku = { on ->
                    if (on && birth == null) {
                        scope.launch { drawer.close() }
                        kigakuPending = true
                        openPeople()
                    } else {
                        kigaku = on
                        settings.kigaku = on
                    }
                },
                onCalendar = {
                    chooseCalendar(it)
                    scope.launch { drawer.close() }
                },
                onElection = {
                    scope.launch { drawer.close() }
                    electionWork = null
                    electionFrom = Days.dateOf(pager.currentPage)
                    electionKyureki = calendar == CalendarKind.KYUREKI
                    screen = Screen.ELECTION
                },
                onLocation = {
                    scope.launch { drawer.close() }
                    screen = Screen.LOCATION
                },
                onAbout = {
                    scope.launch { drawer.close() }
                    screen = Screen.ABOUT
                },
                languageLabel = language?.let(AppLanguage::nativeName) ?: stringResource(R.string.language_system),
                onLanguage = {
                    scope.launch { drawer.close() }
                    languageDialog = true
                },
                onClose = { scope.launch { drawer.close() } },
            )
        },
    ) {
        val date = Days.dateOf(pager.currentPage)
        val zone = place?.place?.zone ?: ZoneId.systemDefault()
        LaunchedEffect(openDay) {
            openDay?.let { pager.scrollToPage(Days.pageOf(it)) }
            openDay = null
        }
        Column(
            Modifier
                .fillMaxSize()
                .background(Palette.ink)
                .windowInsetsPadding(WindowInsets.safeDrawing),
        ) {
            Header(
                date = date,
                isToday = date == today,
                calendar = calendar,
                onMenu = { scope.launch { drawer.open() } },
                onDate = { pickerOpen = true },
                onToday = { scope.launch { pager.animateScrollToPage(Days.pageOf(today)) } },
                onSwitch = { chooseCalendar(if (calendar == CalendarKind.TIBETAN) CalendarKind.KYUREKI else CalendarKind.TIBETAN) },
                person = people.current,
                showPerson = people.list.size >= 2,
                onPerson = { peopleDialog = true },
            )
            HorizontalPager(
                state = pager,
                modifier = Modifier.weight(1f),
                beyondViewportPageCount = 1,
                key = { it },
            ) { page ->
                val info = remember(page, zone, birth) { DayInfo.of(Days.dateOf(page), zone, birth) }
                when (calendar) {
                    CalendarKind.TIBETAN -> TibetanPage(info, zone) { work ->
                        electionWork = work
                        electionFrom = info.date
                        electionKyureki = false
                        screen = Screen.ELECTION
                    }
                    CalendarKind.KYUREKI -> KyurekiPage(info, birthStar = if (kigaku) birth?.let(Kigaku::honmeiStar) else null) { work ->
                        electionWork = work
                        electionFrom = info.date
                        electionKyureki = true
                        screen = Screen.ELECTION
                    }
                }
            }
            SkyLine(date = date, place = place, onLocation = { screen = Screen.LOCATION })
        }

        if (peopleDialog && editing == null) {
            PeopleDialog(
                people = people,
                onChoose = {
                    savePeople(people.choose(it))
                    peopleDialog = false
                    kigakuPending = false
                },
                onEdit = { editing = it ?: -1 },
                onDismiss = {
                    peopleDialog = false
                    kigakuPending = false
                },
            )
        }

        editing?.let { index ->
            val initial = people.list.getOrNull(index)
            PersonDialog(
                initial = initial,
                onSave = {
                    // A new person is chosen at once; a changed one keeps whoever was chosen.
                    savePeople(if (initial == null) people.add(it) else people.replace(index, it))
                    editing = null
                    if (initial == null) peopleDialog = false
                },
                onDelete = if (initial == null) null else {
                    {
                        savePeople(people.remove(index))
                        editing = null
                        if (people.list.isEmpty()) peopleDialog = false
                    }
                },
                onDismiss = {
                    editing = null
                    if (!peopleDialog) kigakuPending = false
                },
            )
        }

        if (languageDialog) {
            LanguageDialog(
                current = language,
                onChoose = {
                    languageDialog = false
                    if (it != language) AppLanguage.set(activity, it)
                },
                onDismiss = { languageDialog = false },
            )
        }

        if (pickerOpen) {
            DatePickerSheet(
                selected = date,
                today = today,
                calendar = calendar,
                zone = zone,
                onPick = {
                    pickerOpen = false
                    scope.launch { pager.scrollToPage(Days.pageOf(it)) }
                },
                onDismiss = { pickerOpen = false },
            )
        }
    }
}

@Composable
private fun Header(
    date: LocalDate,
    isToday: Boolean,
    calendar: CalendarKind,
    onMenu: () -> Unit,
    onDate: () -> Unit,
    onToday: () -> Unit,
    onSwitch: () -> Unit,
    person: Person?,
    showPerson: Boolean,
    onPerson: () -> Unit,
) {
    val accent = if (calendar == CalendarKind.TIBETAN) Palette.saffron else Palette.vermilion
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .padding(start = 6.dp, end = 10.dp, top = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        IconButton(onClick = onMenu) {
            Icon(Icons.Menu, contentDescription = stringResource(R.string.menu_open), tint = Palette.text)
        }
        Row(
            modifier = Modifier
                .weight(1f)
                .heightIn(min = 44.dp)
                .clickable(role = Role.Button, onClickLabel = stringResource(R.string.choose_date), onClick = onDate)
                .padding(horizontal = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Text(date.format(LocalLabels.current.headerDate), style = body.copy(fontSize = 16.sp, fontWeight = FontWeight.SemiBold))
            Icon(Icons.ChevronDown, contentDescription = null, tint = Palette.muted, modifier = Modifier.size(16.dp))
        }
        if (!isToday) {
            Text(
                stringResource(R.string.today),
                style = body.copy(fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Palette.muted),
                modifier = Modifier
                    .heightIn(min = 44.dp)
                    .clickable(role = Role.Button, onClick = onToday)
                    .padding(horizontal = 8.dp, vertical = 12.dp),
            )
        }
        if (showPerson) PersonChip(person, onPerson)
        Row(
            modifier = Modifier
                .height(44.dp)
                .border(1.dp, accent, RoundedCornerShape(22.dp))
                .clickable(role = Role.Button, onClickLabel = stringResource(R.string.switch_calendar), onClick = onSwitch)
                .padding(horizontal = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            val name = if (calendar == CalendarKind.TIBETAN) stringResource(R.string.calendar_tibetan) else "旧暦"
            Text(
                name,
                style = body.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = accent,
                    fontFamily = if (calendar == CalendarKind.TIBETAN) Figtree else Mincho,
                ),
            )
            Icon(Icons.Swap, contentDescription = null, tint = accent, modifier = Modifier.size(16.dp))
        }
    }
}

@Composable
private fun SkyLine(date: LocalDate, place: SavedPlace?, onLocation: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .drawTopLine()
            .padding(start = 24.dp, end = 24.dp, top = 12.dp, bottom = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        if (place == null) {
            Text(
                stringResource(R.string.sky_set_location),
                style = body.copy(fontSize = 14.sp, color = Palette.muted),
                modifier = Modifier
                    .heightIn(min = 44.dp)
                    .clickable(role = Role.Button, onClick = onLocation)
                    .padding(vertical = 12.dp)
                    .dottedUnderline(),
            )
            return@Column
        }
        val sun = remember(date, place) { SunTimes.of(date, place.place) }
        val labels = LocalLabels.current
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            SkyItem(Icons.Sunrise, sun.sunrise?.format(labels.clock) ?: "—", stringResource(R.string.sky_sunrise))
            SkyItem(
                Icons.Sun,
                sun.transit.format(labels.clock),
                stringResource(R.string.sky_solar_noon),
                suffix = stringResource(R.string.sky_altitude, sun.altitudeAtTransitDeg),
            )
            SkyItem(Icons.Sunset, sun.sunset?.format(labels.clock) ?: "—", stringResource(R.string.sky_sunset))
        }
        Row(
            modifier = Modifier
                .heightIn(min = 36.dp)
                .clickable(role = Role.Button, onClickLabel = stringResource(R.string.change_location), onClick = onLocation),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Icon(Icons.Pin, contentDescription = null, tint = Palette.faint, modifier = Modifier.size(14.dp))
            Text(place.label, style = body.copy(fontSize = 13.sp, color = Palette.faint))
        }
    }
}

@Composable
private fun SkyItem(icon: ImageVector, time: String, label: String, suffix: String? = null) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        Icon(icon, contentDescription = label, tint = Palette.muted, modifier = Modifier.size(20.dp))
        Text(time, style = body.copy(fontFeatureSettings = "tnum"))
        if (suffix != null) Text(suffix, style = body.copy(color = Palette.muted, fontFeatureSettings = "tnum"))
    }
}

private fun Modifier.drawTopLine(): Modifier = drawBehind {
    drawLine(Palette.line, Offset(0f, 0f), Offset(size.width, 0f), 1.dp.toPx())
}

@Composable
private fun SideMenu(
    calendar: CalendarKind,
    placeLabel: String?,
    personLabel: String,
    onPeople: () -> Unit,
    kigaku: Boolean,
    onKigaku: (Boolean) -> Unit,
    onCalendar: (CalendarKind) -> Unit,
    onElection: () -> Unit,
    onLocation: () -> Unit,
    onAbout: () -> Unit,
    languageLabel: String,
    onLanguage: () -> Unit,
    onClose: () -> Unit,
) {
    ModalDrawerSheet(
        drawerContainerColor = Palette.surface,
        drawerShape = RoundedCornerShape(topEnd = 0.dp, bottomEnd = 0.dp),
        modifier = Modifier.width(304.dp),
    ) {
        Column(
            Modifier
                .fillMaxHeight()
                .windowInsetsPadding(WindowInsets.safeDrawing)
                .padding(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Row(
                Modifier.fillMaxWidth().padding(start = 24.dp, end = 12.dp, bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(stringResource(R.string.menu_title), style = body.copy(fontFamily = Mincho, fontWeight = FontWeight.Bold, fontSize = 24.sp))
                IconButton(onClick = onClose) { Icon(Icons.Close, contentDescription = stringResource(R.string.menu_close), tint = Palette.muted) }
            }
            SectionLabel(stringResource(R.string.menu_calendar))
            CalendarRow(
                stringResource(R.string.calendar_tibetan),
                stringResource(R.string.menu_tibetan_subtitle),
                calendar == CalendarKind.TIBETAN,
                Palette.saffron,
            ) { onCalendar(CalendarKind.TIBETAN) }
            CalendarRow(
                stringResource(R.string.menu_kyureki),
                stringResource(R.string.menu_kyureki_subtitle),
                calendar == CalendarKind.KYUREKI,
                Palette.vermilion,
            ) {
                onCalendar(CalendarKind.KYUREKI)
            }
            // The election, under the two calendars, for the one shown: the Tibetan day's weighing read across
            // days (ROADMAP E2), or the 旧暦 annotations listed across days (E5).
            MenuRow(Icons.Search, stringResource(R.string.menu_election), stringResource(R.string.menu_election_subtitle), onElection)
            Box(Modifier.padding(horizontal = 24.dp, vertical = 12.dp).fillMaxWidth().height(1.dp).background(Palette.line))
            SectionLabel(stringResource(R.string.menu_settings))
            MenuRow(Icons.Pin, stringResource(R.string.menu_location), placeLabel ?: stringResource(R.string.not_set), onLocation)
            MenuRow(Icons.Person, stringResource(R.string.menu_people), personLabel, onPeople)
            SwitchRow(Icons.Board, stringResource(R.string.menu_kigaku), stringResource(R.string.menu_kigaku_subtitle), kigaku, onKigaku)
            MenuRow(Icons.Globe, stringResource(R.string.menu_language), languageLabel, onLanguage)
            MenuRow(Icons.Info, stringResource(R.string.menu_about), stringResource(R.string.menu_about_subtitle), onAbout)
        }
    }
}

@Composable
private fun SectionLabel(text: String) {
    Text(
        text,
        style = body.copy(fontSize = 12.sp, fontWeight = FontWeight.SemiBold, letterSpacing = 1.2.sp, color = Palette.faint),
        modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp),
    )
}

@Composable
private fun CalendarRow(title: String, subtitle: String, selected: Boolean, accent: Color, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .fillMaxWidth()
            .heightIn(min = 56.dp)
            .background(if (selected) Palette.selected else Palette.surface, RoundedCornerShape(12.dp))
            .clickable(role = Role.RadioButton, onClick = onClick)
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column {
            Text(title, style = body.copy(fontSize = 16.sp, fontWeight = FontWeight.SemiBold))
            Text(subtitle, style = body.copy(fontSize = 13.sp, color = Palette.muted))
        }
        if (selected) Icon(Icons.Check, contentDescription = stringResource(R.string.selected), tint = accent, modifier = Modifier.size(20.dp))
    }
}

@Composable
private fun SwitchRow(icon: ImageVector, title: String, subtitle: String, checked: Boolean, onChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .fillMaxWidth()
            .heightIn(min = 56.dp)
            .toggleable(value = checked, role = Role.Switch, onValueChange = onChange)
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        Icon(icon, contentDescription = null, tint = Palette.muted, modifier = Modifier.size(22.dp))
        Column(Modifier.weight(1f)) {
            Text(title, style = body.copy(fontSize = 16.sp))
            Text(subtitle, style = body.copy(fontSize = 13.sp, color = Palette.muted))
        }
        Switch(
            checked = checked,
            onCheckedChange = null,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Palette.ink,
                checkedTrackColor = Palette.vermilion,
                uncheckedThumbColor = Palette.muted,
                uncheckedTrackColor = Palette.raised,
                uncheckedBorderColor = Palette.lineStrong,
            ),
        )
    }
}

@Composable
private fun MenuRow(icon: ImageVector, title: String, subtitle: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .fillMaxWidth()
            .heightIn(min = 56.dp)
            .clickable(role = Role.Button, onClick = onClick)
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        Icon(icon, contentDescription = null, tint = Palette.muted, modifier = Modifier.size(22.dp))
        Column {
            Text(title, style = body.copy(fontSize = 16.sp))
            Text(subtitle, style = body.copy(fontSize = 13.sp, color = Palette.muted))
        }
    }
}
