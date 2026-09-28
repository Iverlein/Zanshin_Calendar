/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.ui

import androidx.activity.compose.BackHandler
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
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
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
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import kotlinx.coroutines.launch
import zanshin.app.CalendarKind
import zanshin.app.Cities
import zanshin.app.DayInfo
import zanshin.app.Days
import zanshin.app.Labels
import zanshin.app.SavedPlace
import zanshin.app.Settings
import zanshin.core.astro.SunTimes
import java.time.LocalDate
import java.time.ZoneId
import java.util.Locale

private enum class Screen { DAYS, LOCATION, ABOUT }

@Composable
fun ZanshinApp(settings: Settings, cities: Cities) {
    var calendar by remember { mutableStateOf(settings.calendar) }
    var place by remember { mutableStateOf(settings.place) }
    var birth by remember { mutableStateOf(settings.birthDate) }
    var birthDialog by remember { mutableStateOf(false) }
    var screen by remember { mutableStateOf(Screen.DAYS) }
    var pickerOpen by remember { mutableStateOf(false) }
    var today by remember { mutableStateOf(LocalDate.now()) }
    LifecycleEventEffect(Lifecycle.Event.ON_RESUME) { today = LocalDate.now() }

    val pager = rememberPagerState(initialPage = Days.pageOf(today)) { Days.count }
    val drawer = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    fun chooseCalendar(kind: CalendarKind) {
        calendar = kind
        settings.calendar = kind
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
        Screen.DAYS -> Unit
    }

    ModalNavigationDrawer(
        drawerState = drawer,
        drawerContent = {
            SideMenu(
                calendar = calendar,
                placeLabel = place?.label,
                birthLabel = birth?.format(Labels.headerDate),
                onBirth = {
                    scope.launch { drawer.close() }
                    birthDialog = true
                },
                onCalendar = {
                    chooseCalendar(it)
                    scope.launch { drawer.close() }
                },
                onLocation = {
                    scope.launch { drawer.close() }
                    screen = Screen.LOCATION
                },
                onAbout = {
                    scope.launch { drawer.close() }
                    screen = Screen.ABOUT
                },
                onClose = { scope.launch { drawer.close() } },
            )
        },
    ) {
        val date = Days.dateOf(pager.currentPage)
        val zone = place?.place?.zone ?: ZoneId.systemDefault()
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
            )
            HorizontalPager(
                state = pager,
                modifier = Modifier.weight(1f),
                beyondViewportPageCount = 1,
                key = { it },
            ) { page ->
                val info = remember(page, zone, birth) { DayInfo.of(Days.dateOf(page), zone, birth) }
                when (calendar) {
                    CalendarKind.TIBETAN -> TibetanPage(info)
                    CalendarKind.KYUREKI -> KyurekiPage(info)
                }
            }
            SkyLine(date = date, place = place, onLocation = { screen = Screen.LOCATION })
        }

        if (birthDialog) {
            BirthDateDialog(
                initial = birth,
                onSave = {
                    birth = it
                    settings.birthDate = it
                    birthDialog = false
                },
                onDismiss = { birthDialog = false },
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
            Icon(Icons.Menu, contentDescription = "Open menu", tint = Palette.text)
        }
        Row(
            modifier = Modifier
                .weight(1f)
                .heightIn(min = 44.dp)
                .clickable(role = Role.Button, onClickLabel = "Choose date", onClick = onDate)
                .padding(horizontal = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Text(date.format(Labels.headerDate), style = body.copy(fontSize = 16.sp, fontWeight = FontWeight.SemiBold))
            Icon(Icons.ChevronDown, contentDescription = null, tint = Palette.muted, modifier = Modifier.size(16.dp))
        }
        if (!isToday) {
            Text(
                "Today",
                style = body.copy(fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Palette.muted),
                modifier = Modifier
                    .heightIn(min = 44.dp)
                    .clickable(role = Role.Button, onClick = onToday)
                    .padding(horizontal = 8.dp, vertical = 12.dp),
            )
        }
        Row(
            modifier = Modifier
                .height(44.dp)
                .border(1.dp, accent, RoundedCornerShape(22.dp))
                .clickable(role = Role.Button, onClickLabel = "Switch calendar", onClick = onSwitch)
                .padding(horizontal = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            val name = if (calendar == CalendarKind.TIBETAN) "Tibetan" else "旧暦"
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
                "Set a location for sunrise and sunset",
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
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            SkyItem(Icons.Sunrise, sun.sunrise?.format(Labels.clock) ?: "—", "Sunrise")
            SkyItem(
                Icons.Sun,
                sun.transit.format(Labels.clock),
                "Solar noon",
                suffix = String.format(Locale.ROOT, "· %.1f°", sun.altitudeAtTransitDeg),
            )
            SkyItem(Icons.Sunset, sun.sunset?.format(Labels.clock) ?: "—", "Sunset")
        }
        Row(
            modifier = Modifier
                .heightIn(min = 36.dp)
                .clickable(role = Role.Button, onClickLabel = "Change location", onClick = onLocation),
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
    birthLabel: String?,
    onBirth: () -> Unit,
    onCalendar: (CalendarKind) -> Unit,
    onLocation: () -> Unit,
    onAbout: () -> Unit,
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
                Text("Zanshin", style = body.copy(fontFamily = Mincho, fontWeight = FontWeight.Bold, fontSize = 24.sp))
                IconButton(onClick = onClose) { Icon(Icons.Close, contentDescription = "Close menu", tint = Palette.muted) }
            }
            SectionLabel("CALENDAR")
            CalendarRow("Tibetan", "Phugpa", calendar == CalendarKind.TIBETAN, Palette.saffron) { onCalendar(CalendarKind.TIBETAN) }
            CalendarRow("旧暦 Kyūreki", "Tenpō rules, rokuyō", calendar == CalendarKind.KYUREKI, Palette.vermilion) {
                onCalendar(CalendarKind.KYUREKI)
            }
            Box(Modifier.padding(horizontal = 24.dp, vertical = 12.dp).fillMaxWidth().height(1.dp).background(Palette.line))
            SectionLabel("SETTINGS")
            MenuRow(Icons.Pin, "Location", placeLabel ?: "Not set", onLocation)
            MenuRow(Icons.Sun, "Birth date", birthLabel ?: "Not set — for personal days", onBirth)
            MenuRow(Icons.Info, "About & sources", "Formulas, fonts, GeoNames", onAbout)
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
        if (selected) Icon(Icons.Check, contentDescription = "Selected", tint = accent, modifier = Modifier.size(20.dp))
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
