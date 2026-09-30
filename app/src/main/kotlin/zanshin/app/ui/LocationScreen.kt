/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.location.LocationManagerCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import zanshin.app.Cities
import zanshin.app.City
import io.github.iverlein.zanshin.R
import zanshin.app.SavedPlace
import zanshin.app.formatCoordinates
import zanshin.core.astro.Place
import java.time.ZoneId

@Composable
fun LocationScreen(current: SavedPlace?, cities: Cities, onBack: () -> Unit, onChoose: (SavedPlace) -> Unit) {
    val context = LocalContext.current
    var query by remember { mutableStateOf("") }
    var results by remember { mutableStateOf(emptyList<City>()) }
    var lat by remember { mutableStateOf(current?.place?.latitude?.let { "%.4f".format(java.util.Locale.ROOT, it) } ?: "") }
    var lon by remember { mutableStateOf(current?.place?.longitude?.let { "%.4f".format(java.util.Locale.ROOT, it) } ?: "") }
    var status by remember { mutableStateOf<String?>(null) }

    var loaded by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        withContext(Dispatchers.Default) { cities.preload() }
        loaded = true
    }
    LaunchedEffect(query) {
        delay(120)
        results = withContext(Dispatchers.Default) { cities.search(query) }
        loaded = true
    }

    fun chooseDevice(location: Location) {
        onChoose(
            SavedPlace(
                label = context.getString(R.string.loc_my_location_label, formatCoordinates(location.latitude, location.longitude)),
                place = Place(location.latitude, location.longitude, ZoneId.systemDefault()),
            ),
        )
    }

    val permission = rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { granted ->
        if (granted.values.any { it }) {
            status = context.getString(R.string.loc_locating)
            currentLocation(context) { location ->
                if (location != null) chooseDevice(location) else status = context.getString(R.string.loc_unavailable)
            }
        } else {
            status = context.getString(R.string.loc_denied)
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(Palette.ink)
            .windowInsetsPadding(WindowInsets.safeDrawing),
    ) {
        Row(
            Modifier.fillMaxWidth().height(64.dp).padding(start = 6.dp, top = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = onBack) { Icon(Icons.ChevronLeft, contentDescription = stringResource(R.string.back), tint = Palette.text) }
            Text(stringResource(R.string.menu_location), style = body.copy(fontSize = 18.sp, fontWeight = FontWeight.SemiBold))
        }
        Column(
            Modifier.verticalScroll(rememberScrollState()).padding(horizontal = 24.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(22.dp),
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(stringResource(R.string.loc_current), style = body.copy(fontSize = 13.sp, color = Palette.muted))
                Text(current?.label ?: stringResource(R.string.not_set), style = body.copy(fontSize = 17.sp, fontWeight = FontWeight.Medium))
            }

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .border(1.dp, Palette.saffron, RoundedCornerShape(14.dp))
                        .clickable(role = Role.Button) {
                            permission.launch(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION))
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Icon(Icons.Crosshair, contentDescription = null, tint = Palette.saffron, modifier = Modifier.size(20.dp))
                    Text(
                        stringResource(R.string.loc_use_mine),
                        style = body.copy(fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Palette.saffron),
                        modifier = Modifier.padding(start = 10.dp),
                    )
                }
                status?.let { Text(it, style = body.copy(fontSize = 13.sp, color = Palette.muted)) }
            }

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(stringResource(R.string.loc_search), style = body.copy(fontSize = 13.sp, color = Palette.muted))
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .background(Palette.surface, RoundedCornerShape(12.dp))
                        .border(1.dp, Palette.line, RoundedCornerShape(12.dp))
                        .padding(horizontal = 14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Icon(Icons.Search, contentDescription = null, tint = Palette.muted, modifier = Modifier.size(18.dp))
                    Field(query, { query = it }, placeholder = stringResource(R.string.loc_type_city), modifier = Modifier.weight(1f))
                }
                if (!loaded && query.isNotBlank()) {
                    Text(stringResource(R.string.loc_loading), style = body.copy(fontSize = 13.sp, color = Palette.muted))
                }
                for (city in results) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .heightIn(min = 48.dp)
                            .clickable(role = Role.Button) { onChoose(city.toSavedPlace()) }
                            .drawBottomLine(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            "${city.name} · ${city.country}",
                            style = body,
                            modifier = Modifier.weight(1f),
                        )
                        Text(formatCoordinates(city.latitude, city.longitude), style = body.copy(fontSize = 13.sp, color = Palette.muted))
                    }
                }
            }

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(stringResource(R.string.loc_or_coordinates), style = body.copy(fontSize = 13.sp, color = Palette.muted))
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    CoordinateField(stringResource(R.string.loc_latitude), lat, { lat = it }, Modifier.weight(1f))
                    CoordinateField(stringResource(R.string.loc_longitude), lon, { lon = it }, Modifier.weight(1f))
                }
                val la = lat.replace(',', '.').toDoubleOrNull()
                val lo = lon.replace(',', '.').toDoubleOrNull()
                val valid = la != null && lo != null && la in -90.0..90.0 && lo in -180.0..180.0
                Text(
                    stringResource(R.string.loc_save),
                    style = body.copy(fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = if (valid) Palette.ink else Palette.faint),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .background(if (valid) Palette.saffron else Palette.surface, RoundedCornerShape(12.dp))
                        .clickable(enabled = valid, role = Role.Button) {
                            onChoose(SavedPlace(formatCoordinates(la!!, lo!!), Place(la, lo, ZoneId.systemDefault())))
                        }
                        .padding(vertical = 13.dp),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                )
                Text(
                    stringResource(R.string.loc_zone_note),
                    style = body.copy(fontSize = 12.sp, color = Palette.faint),
                )
            }
        }
    }
}

@Composable
private fun Field(value: String, onValue: (String) -> Unit, placeholder: String, modifier: Modifier = Modifier, numeric: Boolean = false) {
    BasicTextField(
        value = value,
        onValueChange = onValue,
        singleLine = true,
        textStyle = body.copy(fontSize = 16.sp),
        cursorBrush = SolidColor(Palette.text),
        keyboardOptions = if (numeric) KeyboardOptions(keyboardType = KeyboardType.Decimal) else KeyboardOptions.Default,
        modifier = modifier,
        decorationBox = { inner ->
            if (value.isEmpty()) Text(placeholder, style = body.copy(fontSize = 16.sp, color = Palette.faint))
            inner()
        },
    )
}

@Composable
private fun CoordinateField(label: String, value: String, onValue: (String) -> Unit, modifier: Modifier) {
    Column(modifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(label, style = body.copy(fontSize = 12.sp, color = Palette.faint))
        Row(
            Modifier
                .fillMaxWidth()
                .height(44.dp)
                .background(Palette.surface, RoundedCornerShape(10.dp))
                .border(1.dp, Palette.line, RoundedCornerShape(10.dp))
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Field(value, onValue, placeholder = "0.0000", modifier = Modifier.fillMaxWidth(), numeric = true)
        }
    }
}

private fun Modifier.drawBottomLine(): Modifier = drawBehind {
    drawLine(Palette.line, Offset(0f, size.height), Offset(size.width, size.height), 1.dp.toPx())
}

/** One reading from the best available provider; offline GPS works when fine location is granted. */
@SuppressLint("MissingPermission")
private fun currentLocation(context: Context, onResult: (Location?) -> Unit) {
    val manager = context.getSystemService(LocationManager::class.java)
    val fine = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    val providers = buildList {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && manager.isProviderEnabled(LocationManager.FUSED_PROVIDER)) {
            add(LocationManager.FUSED_PROVIDER)
        }
        if (fine && manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) add(LocationManager.GPS_PROVIDER)
        if (manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) add(LocationManager.NETWORK_PROVIDER)
    }
    val recent = providers.mapNotNull { runCatching { manager.getLastKnownLocation(it) }.getOrNull() }
        .filter { System.currentTimeMillis() - it.time < 60 * 60 * 1000 }
        .maxByOrNull { it.time }
    if (recent != null) return onResult(recent)
    val provider = providers.firstOrNull() ?: return onResult(null)
    LocationManagerCompat.getCurrentLocation(
        manager,
        provider,
        null as android.os.CancellationSignal?,
        ContextCompat.getMainExecutor(context),
        androidx.core.util.Consumer<Location?> { onResult(it) },
    )
}
