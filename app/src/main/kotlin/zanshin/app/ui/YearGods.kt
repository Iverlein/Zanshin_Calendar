/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.iverlein.zanshin.R
import zanshin.core.kyureki.BRANCHES
import zanshin.core.kyureki.HasshoGod
import zanshin.core.kyureki.Kanshi
import zanshin.core.kyureki.Rekichu
import zanshin.core.kyureki.RekichuDay
import zanshin.core.kyureki.Tone
import zanshin.core.kyureki.YearGod
import zanshin.core.kyureki.YearGods
import zanshin.core.texts.Catalog
import zanshin.core.texts.Texts
import zanshin.core.texts.gloss

/** A god's one-kanji mark on the compass: its name without 神, and the general's 将. */
private fun mark(g: HasshoGod): String = when (g) {
    HasshoGod.DAISHOGUN -> "将"
    else -> g.kanji.removeSuffix("神").last().toString()
}

/** A branch as a direction: its kanji and its compass name, "卯 · east (90°)". */
private fun direction(branch: Int): String = "${BRANCHES[branch]} · ${Catalog.text("BranchDirection.$branch")}"

/**
 * The year's directions (ROADMAP K5, D9), opened from the 恵方 row as the printed almanac opens with its 方位吉凶図:
 * the 恵方, then the eight gods in the almanac's order, each with its direction and its reading. The year is the
 * 恵方's, from 立春.
 */
@Composable
fun YearGodsSheet(rk: RekichuDay, ehou: Annotation, onDismiss: () -> Unit) {
    var open by remember { mutableStateOf<Annotation?>(null) }
    val gods = YearGods.of(rk.date)
    val konjin = YearGods.konjin(rk.date)
    val konjinName = Catalog.text("Konjin")
    val year = Kanshi.ofYear(Rekichu.setsuYearOf(rk.date))
    val detailDirection = stringResource(R.string.detail_direction)
    val detailQuarter = stringResource(R.string.detail_year_god_quarter)
    val detailWeight = stringResource(R.string.detail_saiha_weight)
    fun annotation(g: YearGod) = Annotation(
        g.god.kanji,
        "${g.god.reading} — ${g.god.english}",
        if (g.god == HasshoGod.TAISAI) Tone.GOOD else Tone.BAD,
        Texts.YEAR_GOD[g.god],
        subtitle = direction(g.branch),
        details = buildList {
            add(detailDirection to direction(g.branch))
            if (g.arc > 30) add(detailQuarter to (-1..1).joinToString(" ") { BRANCHES[Math.floorMod(g.branch + it, 12)].toString() })
            g.weight?.let { add(detailWeight to gloss(it)) }
        },
    )
    Sheet(onDismiss) {
        Column(
            Modifier.verticalScroll(rememberScrollState()).padding(bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            Column(Modifier.padding(horizontal = 24.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
                Text(stringResource(R.string.year_gods_title), style = body.copy(fontSize = 22.sp, fontWeight = FontWeight.SemiBold))
                Text(stringResource(R.string.year_gods_head, year.kanji), style = body.copy(fontSize = 16.sp))
                val caption = { b: Int ->
                    val here = (gods.filter { it.branch == b }.map { "${it.god.kanji} ${it.god.english}" } + listOfNotNull("金神 $konjinName".takeIf { b in konjin })).joinToString(", ")
                    if (here.isEmpty()) direction(b) else "${direction(b)}: $here"
                }
                DiagramFrame {
                    YearGodsCompass(
                        gods.map { Triple(mark(it.god), it.branch, it.god == HasshoGod.TAISAI) } + konjin.map { Triple("金", it, false) },
                        gods.first { it.god == HasshoGod.DAISHOGUN }.bearing,
                        rk.ehou.bearing,
                        240.dp,
                        stringResource(
                            R.string.desc_year_gods_compass,
                            (gods.map { "${it.god.english}: ${Catalog.text("BranchDirection.${it.branch}")}" } + "$konjinName: ${konjin.joinToString(", ") { Catalog.text("BranchDirection.$it") }}").joinToString("; "),
                        ),
                        start = gods.first { it.god == HasshoGod.TAISAI }.branch,
                        named = caption,
                    )
                }
                Text(stringResource(R.string.year_gods_note), style = body.copy(fontSize = 14.sp, lineHeight = 20.sp, color = Palette.muted))
                Column {
                    AnnotationRow(ehou) { open = it }
                    gods.forEach { g -> AnnotationRow(annotation(g)) { open = it } }
                    // 金神 after the eight, from the 簠簋内伝 (D9, the owner's choice of 2026-10-31).
                    AnnotationRow(
                        Annotation(
                            "金神",
                            "konjin — $konjinName",
                            Tone.BAD,
                            Texts.KONJIN,
                            subtitle = stringResource(R.string.year_gods_konjin, konjin.joinToString(" ") { BRANCHES[it].toString() }, year.stemKanji),
                            details = listOf(detailDirection to konjin.joinToString("\n") { direction(it) }),
                        ),
                    ) { open = it }
                }
            }
        }
    }
    open?.let { ReadingSheet(it) { open = null } }
}
