/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.ui

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import zanshin.app.AppLanguage
import zanshin.app.CalendarKind
import zanshin.app.Cities
import zanshin.app.DayInfo
import zanshin.app.Days
import zanshin.app.LocalLabels
import zanshin.app.MenuGroup
import io.github.iverlein.zanshin.R
import zanshin.app.People
import zanshin.app.Person
import zanshin.app.SavedPlace
import zanshin.app.Settings
import zanshin.core.astro.SunTimes
import zanshin.core.kyureki.Kigaku
import java.time.LocalDate
import java.time.ZoneId

private enum class Screen { DAYS, LOCATION, ABOUT, ELECTION, MEDITATION, HEALTH }

/** The app; [meditationRequests] counts the timer notification's taps, each showing the meditation screen. */
@Composable
fun ZanshinApp(settings: Settings, cities: Cities, meditationRequests: Int = 0) {
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
    LaunchedEffect(meditationRequests) { if (meditationRequests > 0) screen = Screen.MEDITATION }
    var pickerOpen by remember { mutableStateOf(false) }
    // The election's work and the day it runs from (ROADMAP E2); a day it opens is shown when the pager is back.
    var electionWork by remember { mutableStateOf<zanshin.core.texts.Activity?>(null) }
    var electionFrom by remember { mutableStateOf(LocalDate.now()) }
    // Which calendar's election: the Tibetan weighing (E1–E3) or the 旧暦 listing (E5).
    var electionKyureki by remember { mutableStateOf(false) }
    var openDay by remember { mutableStateOf<LocalDate?>(null) }
    // The day the Health screen opens on (SPEC §10.10): the page shown when it was opened.
    var healthDay by remember { mutableStateOf(LocalDate.now()) }
    // The menu's groups, open or closed as each was last left (ROADMAP H3).
    var openGroups by remember { mutableStateOf(MenuGroup.entries.associateWith(settings::menuGroupOpen)) }
    val zone = place?.place?.zone ?: ZoneId.systemDefault()
    // Today's page (SPEC §4): the Tibetan day, which turns at 05:00, or the 旧暦's civil date. Saveable like the pager's
    // page, so that a task restored after its process was killed knows which day it took for today.
    var today by rememberSaveable { mutableStateOf(Days.today(calendar, zone)) }

    // The first-run line (ROADMAP U18): each page's first three opens show it, an open counted once a launch when the
    // page is first shown; a tap puts it away. Saveable, so that turning the phone is not another open.
    var countedOpens by rememberSaveable { mutableStateOf(listOf<String>()) }
    var firstRun by rememberSaveable { mutableStateOf(listOf<String>()) }
    LaunchedEffect(calendar) {
        if (calendar.name !in countedOpens) {
            countedOpens = countedOpens + calendar.name
            val opens = settings.firstRunOpens(calendar)
            if (opens < Settings.FIRST_RUN_OPENS) {
                settings.setFirstRunOpens(calendar, opens + 1)
                firstRun = firstRun + calendar.name
            }
        }
    }
    val dismissFirstRun = {
        settings.setFirstRunOpens(calendar, Settings.FIRST_RUN_OPENS)
        firstRun = firstRun - calendar.name
    }
    val onFirstRun = dismissFirstRun.takeIf { calendar.name in firstRun }

    val pager = rememberPagerState(initialPage = Days.pageOf(today)) { Days.count }
    val drawer = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    // Today turned, while the app was away or open, or with the calendar or the place: a page left on the old today
    // follows to the new one, a day browsed to stays. Between midnight and 05:00 the Tibetan page stays on the day
    // still running, whose hours hold the present one.
    fun followToday() {
        val now = Days.today(calendar, zone)
        if (now != today) {
            if (Days.dateOf(pager.currentPage) == today) scope.launch { pager.scrollToPage(Days.pageOf(now)) }
            today = now
        }
    }
    LifecycleEventEffect(Lifecycle.Event.ON_RESUME) { followToday() }
    LaunchedEffect(calendar, zone) {
        while (true) {
            followToday()
            delay(30_000)
        }
    }

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
        Screen.MEDITATION -> {
            BackHandler { screen = Screen.DAYS }
            MeditationScreen(onBack = { screen = Screen.DAYS })
            return
        }
        Screen.HEALTH -> {
            BackHandler { screen = Screen.DAYS }
            HealthScreen(
                from = healthDay,
                zone = zone,
                person = people.current,
                onPeople = ::openPeople,
                onBack = { screen = Screen.DAYS },
            )
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
                onHealth = {
                    scope.launch { drawer.close() }
                    healthDay = Days.dateOf(pager.currentPage)
                    screen = Screen.HEALTH
                },
                onMeditation = {
                    scope.launch { drawer.close() }
                    screen = Screen.MEDITATION
                },
                openGroups = openGroups,
                onGroup = { group, open ->
                    openGroups = openGroups + (group to open)
                    settings.setMenuGroupOpen(group, open)
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
                onChoose = ::chooseCalendar,
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
                    CalendarKind.TIBETAN -> TibetanPage(
                        info, zone, person = people.current, place = place?.place, onFirstRun = onFirstRun,
                        onHealth = {
                            healthDay = info.date
                            screen = Screen.HEALTH
                        },
                    ) { work ->
                        electionWork = work
                        electionFrom = info.date
                        electionKyureki = false
                        screen = Screen.ELECTION
                    }
                    CalendarKind.KYUREKI -> KyurekiPage(info, birthStar = if (kigaku) birth?.let(Kigaku::honmeiStar) else null, onFirstRun = onFirstRun) { work ->
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
    onChoose: (CalendarKind) -> Unit,
    person: Person?,
    showPerson: Boolean,
    onPerson: () -> Unit,
) {
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
        CalendarToggle(calendar, onChoose)
    }
}

/**
 * The calendars as two segments, "Tibetan | 旧暦" (SPEC §10.2, ROADMAP U15): the one shown filled in its accent,
 * the other outlined; a tap on the other switches. Screen readers get them as two tabs, the shown one selected.
 */
@Composable
private fun CalendarToggle(calendar: CalendarKind, onChoose: (CalendarKind) -> Unit) {
    Row(Modifier.height(44.dp).selectableGroup(), verticalAlignment = Alignment.CenterVertically) {
        for (kind in CalendarKind.entries) {
            val selected = kind == calendar
            val tibetan = kind == CalendarKind.TIBETAN
            val accent = if (tibetan) Palette.saffron else Palette.vermilion
            val shape = if (tibetan) RoundedCornerShape(topStart = 18.dp, bottomStart = 18.dp) else RoundedCornerShape(topEnd = 18.dp, bottomEnd = 18.dp)
            val name = if (tibetan) stringResource(R.string.calendar_tibetan) else "旧暦"
            val spoken = if (tibetan) name else stringResource(R.string.menu_kyureki)
            // The whole height takes the tap; the segment is drawn 36 dp high within it.
            Box(
                Modifier
                    .fillMaxHeight()
                    .selectable(selected = selected, role = Role.Tab) { if (!selected) onChoose(kind) }
                    .semantics { contentDescription = spoken },
                contentAlignment = Alignment.Center,
            ) {
                Box(
                    Modifier
                        .height(36.dp)
                        .then(if (selected) Modifier.background(accent, shape) else Modifier.border(1.dp, accent, shape))
                        // No wider than the pill it replaced, which the date beside it needs in Russian.
                        .padding(horizontal = 7.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        name,
                        style = body.copy(
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = if (selected) Palette.ink else accent,
                            fontFamily = if (tibetan) Figtree else Mincho,
                        ),
                        maxLines = 1,
                    )
                }
            }
        }
    }
}

/**
 * The sky line under both pages (SPEC §10.2, §10.5; ROADMAP U8): sunrise, noon with its altitude and sunset on
 * one line of 44 dp, read once a day; the place is in the balloon a tap on the line opens, with "Change location".
 */
@Composable
private fun SkyLine(date: LocalDate, place: SavedPlace?, onLocation: () -> Unit) {
    val line = Modifier
        .fillMaxWidth()
        .drawTopLine()
        .height(44.dp)
    if (place == null) {
        Box(line.clickable(role = Role.Button, onClick = onLocation).padding(horizontal = 24.dp), contentAlignment = Alignment.CenterStart) {
            Text(
                stringResource(R.string.sky_set_location),
                style = body.copy(fontSize = 14.sp, color = Palette.muted),
                maxLines = 1,
                modifier = Modifier.dottedUnderline(),
            )
        }
        return
    }
    val sun = remember(date, place) { SunTimes.of(date, place.place) }
    val labels = LocalLabels.current
    var open by remember { mutableStateOf(false) }
    Box {
        Row(
            line
                .clickable(role = Role.Button, onClickLabel = stringResource(R.string.sky_show_place)) { open = !open }
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SkyItem(Icons.Sunrise, sun.sunrise?.format(labels.clock) ?: "—", stringResource(R.string.sky_sunrise))
            SkyItem(
                Icons.Sun,
                sun.transit.format(labels.clock),
                stringResource(R.string.sky_solar_noon),
                suffix = stringResource(R.string.sky_altitude, sun.altitudeAtTransitDeg),
            )
            SkyItem(Icons.Sunset, sun.sunset?.format(labels.clock) ?: "—", stringResource(R.string.sky_sunset))
        }
        if (open) {
            Balloon(
                rows = listOf(BalloonRow(stringResource(R.string.sky_place), place.label)),
                preferAbove = true,
                actions = listOf(BalloonAction(stringResource(R.string.change_location), onLocation)),
                onDismiss = { open = false },
            )
        }
    }
}

@Composable
private fun SkyItem(icon: ImageVector, time: String, label: String, suffix: String? = null) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        Icon(icon, contentDescription = label, tint = Palette.muted, modifier = Modifier.size(20.dp))
        Text(time, style = body.copy(fontFeatureSettings = "tnum"), maxLines = 1)
        if (suffix != null) Text(suffix, style = body.copy(color = Palette.muted, fontFeatureSettings = "tnum"), maxLines = 1)
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
    onHealth: () -> Unit,
    onMeditation: () -> Unit,
    openGroups: Map<MenuGroup, Boolean>,
    onGroup: (MenuGroup, Boolean) -> Unit,
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
                .verticalScroll(rememberScrollState())
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
            // The groups fold (ROADMAP H3): each header opens and closes its own rows, closed on a fresh install.
            MenuGroupSection(MenuGroup.CALENDAR, stringResource(R.string.menu_calendar), openGroups, onGroup) {
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
                // Health, the Tibetan day's illness, the la and the person's element on a screen of their own (SPEC §10.10).
                MenuRow(Icons.Pulse, stringResource(R.string.menu_health), stringResource(R.string.menu_health_subtitle), onHealth)
            }
            MenuSeparator()
            // Practice: the meditation timer and the mindfulness bell, a screen of their own (SPEC §10.9).
            MenuGroupSection(MenuGroup.PRACTICE, stringResource(R.string.menu_practice), openGroups, onGroup) {
                MenuRow(Icons.Bowl, stringResource(R.string.menu_meditation), stringResource(R.string.menu_meditation_subtitle), onMeditation)
            }
            MenuSeparator()
            MenuGroupSection(MenuGroup.SETTINGS, stringResource(R.string.menu_settings), openGroups, onGroup) {
                MenuRow(Icons.Pin, stringResource(R.string.menu_location), placeLabel ?: stringResource(R.string.not_set), onLocation)
                MenuRow(Icons.Person, stringResource(R.string.menu_people), personLabel, onPeople)
                SwitchRow(Icons.Board, stringResource(R.string.menu_kigaku), stringResource(R.string.menu_kigaku_subtitle), kigaku, onKigaku)
                MenuRow(Icons.Globe, stringResource(R.string.menu_language), languageLabel, onLanguage)
            }
            MenuSeparator()
            MenuRow(Icons.Info, stringResource(R.string.menu_about), stringResource(R.string.menu_about_subtitle), onAbout)
        }
    }
}

/** The line between two groups of the menu. */
@Composable
private fun MenuSeparator() {
    Box(Modifier.padding(horizontal = 24.dp, vertical = 12.dp).fillMaxWidth().height(1.dp).background(Palette.line))
}

/**
 * A group of the menu (ROADMAP H3): its small-capital label as a header row with a chevron that turns to show
 * whether the group is open, and its rows folding in and out under it. Screen readers get the header as a
 * button with its state.
 */
@Composable
private fun MenuGroupSection(
    group: MenuGroup,
    label: String,
    openGroups: Map<MenuGroup, Boolean>,
    onGroup: (MenuGroup, Boolean) -> Unit,
    rows: @Composable () -> Unit,
) {
    val open = openGroups[group] == true
    val turn by animateFloatAsState(if (open) 180f else 0f, label = "chevron")
    val state = stringResource(if (open) R.string.menu_group_open else R.string.menu_group_closed)
    Column {
        Row(
            Modifier
                .padding(horizontal = 12.dp)
                .fillMaxWidth()
                .heightIn(min = 44.dp)
                .clickable(role = Role.Button, onClickLabel = stringResource(if (open) R.string.menu_group_close else R.string.menu_group_expand)) {
                    onGroup(group, !open)
                }
                .semantics { stateDescription = state }
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                label,
                style = body.copy(fontSize = 12.sp, fontWeight = FontWeight.SemiBold, letterSpacing = 1.2.sp, color = Palette.faint),
            )
            Icon(Icons.ChevronDown, contentDescription = null, tint = Palette.faint, modifier = Modifier.size(18.dp).graphicsLayer { rotationZ = turn })
        }
        AnimatedVisibility(visible = open, enter = expandVertically(), exit = shrinkVertically()) {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) { rows() }
        }
    }
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
