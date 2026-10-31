/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.iverlein.zanshin.R
import java.time.LocalDate
import java.time.LocalTime
import zanshin.app.LocalLabels
import zanshin.app.Person
import zanshin.app.Reckonings
import zanshin.core.kyureki.Tone
import zanshin.core.texts.Catalog
import zanshin.core.texts.Texts
import zanshin.core.texts.gloss
import zanshin.core.tibetan.Ewts
import zanshin.core.tibetan.Element
import zanshin.core.tibetan.Force
import zanshin.core.tibetan.IllnessBoard
import zanshin.core.tibetan.IllnessTreeVerdict
import zanshin.core.tibetan.PebbleColour
import zanshin.core.tibetan.Sign
import zanshin.core.tibetan.Forces
import zanshin.core.tibetan.IllnessHelper
import zanshin.core.tibetan.IllnessPebble
import zanshin.core.tibetan.IllnessReckoning
import zanshin.core.tibetan.IllnessYearPair
import zanshin.core.tibetan.SME_BA_COLOURS
import zanshin.core.tibetan.YearOfLife

/**
 * WB's illness reckoning (SPEC §10.10, ROADMAP H2b) for an illness that came on at [time] on [date]: the trigram, the
 * day animal and the mewa of the moment of falling ill, each opening its reading, and, for [person] when one is
 * chosen, the kind of harmful spirit by their birth mewa against the mewa they fell ill under. The time opens a
 * clock to set it ([onTime]). The hearth-keeper's stage is at the present hour in [zone].
 */
@Composable
fun IllnessReckoningView(person: Person?, date: LocalDate, time: LocalTime, zone: java.time.ZoneId, onTime: (LocalTime) -> Unit, onPeople: () -> Unit, onOpen: (Annotation) -> Unit) {
    val labels = LocalLabels.current
    val onset = remember(date, time) { IllnessReckoning.onset(date, time) }
    val day = onset.day
    var clock by remember { mutableStateOf(false) }
    // The hour of reckoning, for the hearth-keeper's stage (p. 442: the hour the question is asked).
    val now = remember(zone) { java.time.LocalDateTime.now(zone).let { IllnessReckoning.onset(it.toLocalDate(), it.toLocalTime()) } }
    ScreenLinkRow(
        stringResource(R.string.illness_time_title, time.format(labels.clock)),
        labels.string(
            R.string.illness_time_subtitle,
            day.weekday.english.replaceFirstChar(Char::uppercase),
            labels.ordinal(day.day),
            labels.ordinal(day.monthNames.number),
        ),
        onClick = { clock = true },
        icon = Icons.Clock,
    )
    IllnessTogether(onset, person, now, onOpen)
    SectionTitle(stringResource(R.string.illness_section_moment))
    val trigram = onset.trigram
    AnnotationRow(
        Annotation(
            title = labels.string(R.string.illness_trigram_title, Ewts.named(trigram.english, trigram.wylie)),
            english = "",
            tone = Tone.NEUTRAL,
            reading = Texts.illnessTrigram(trigram),
            titleIsKanji = false,
        ),
        onOpen,
    )
    AnnotationRow(
        Annotation(
            title = labels.string(R.string.illness_trigram_cause_title, Ewts.named(trigram.english, trigram.wylie)),
            english = "",
            tone = Tone.NEUTRAL,
            reading = Texts.illnessTrigramCause(trigram),
            titleIsKanji = false,
        ),
        onOpen,
    )
    AnnotationRow(
        Annotation(
            title = labels.string(R.string.illness_trigram_ransom_title, Ewts.named(trigram.english, trigram.wylie)),
            english = "",
            tone = Tone.NEUTRAL,
            reading = Texts.illnessTrigramRansom(trigram),
            titleIsKanji = false,
        ),
        onOpen,
    )
    AnnotationRow(
        Annotation(
            title = labels.string(R.string.illness_flee_title, Ewts.named(trigram.english, trigram.wylie)),
            english = "",
            tone = Tone.NEUTRAL,
            reading = Texts.illnessFlee(trigram),
            titleIsKanji = false,
        ),
        onOpen,
    )
    val animal = onset.dayAnimal
    AnnotationRow(
        Annotation(
            title = labels.string(R.string.illness_day_title, gloss(animal).lowercase()),
            english = "",
            tone = Tone.NEUTRAL,
            reading = Texts.illnessDay(animal),
            titleIsKanji = false,
        ),
        onOpen,
    )
    val mewa = onset.mewa
    val colour = Catalog.text("Colour.${SME_BA_COLOURS[mewa - 1]}")
    AnnotationRow(
        Annotation(
            title = labels.string(R.string.illness_mewa_title, mewa, colour),
            english = "",
            tone = Tone.NEUTRAL,
            reading = Texts.illnessMewa(mewa),
            titleIsKanji = false,
        ),
        onOpen,
    )
    AnnotationRow(
        Annotation(
            title = labels.string(R.string.illness_planet_title, day.weekday.english.replaceFirstChar(Char::uppercase)),
            english = "",
            tone = Tone.NEUTRAL,
            reading = Texts.illnessPlanet(day.weekday),
            titleIsKanji = false,
        ),
        onOpen,
    )
    FivePebbles(onset.pebbles, onOpen)
    SectionTitle(stringResource(R.string.illness_section_spirit))
    if (person == null) {
        Text(stringResource(R.string.illness_no_person), style = body.copy(fontSize = 15.sp, lineHeight = 21.sp, color = Palette.muted), modifier = Modifier.padding(top = 4.dp))
        ScreenLinkRow(stringResource(R.string.menu_people), stringResource(R.string.health_choose_person), onPeople, Icons.Person)
    } else {
        val natal = YearOfLife.yearSmeBa(Reckonings.birthYear(person))
        val kind = IllnessReckoning.spiritKind(natal, mewa)
        AnnotationRow(
            Annotation(
                title = kind.english.replaceFirstChar(Char::uppercase),
                english = labels.string(R.string.illness_spirit_subtitle, natal, Catalog.text("Colour.${SME_BA_COLOURS[natal - 1]}"), mewa, colour),
                tone = Tone.NEUTRAL,
                reading = Texts.illnessSpirit(mewa, kind),
                titleIsKanji = false,
            ),
            onOpen,
        )
        val birth = Reckonings.birthSign(person)
        val birthYear = Reckonings.birthYear(person)
        person.gender?.let { g ->
            val reach = IllnessReckoning.reach(birth, birthYear, g, day.year)
            AnnotationRow(
                Annotation(
                    title = labels.string(R.string.illness_reach_title, reach.english),
                    english = "",
                    tone = Tone.NEUTRAL,
                    reading = Texts.illnessReach(reach),
                    titleIsKanji = false,
                ),
                onOpen,
            )
        }
        val pair = IllnessYearPair.of(day.yearAnimal)
        AnnotationRow(
            Annotation(
                title = labels.string(R.string.illness_year_title, gloss(birth.element, "inText"), gloss(day.yearAnimal).lowercase()),
                english = "",
                tone = Tone.NEUTRAL,
                reading = Texts.illnessYear(birth.element, pair),
                titleIsKanji = false,
            ),
            onOpen,
        )
        AnnotationRow(
            Annotation(
                title = labels.string(R.string.illness_ransom_title, gloss(birth.animal).lowercase()),
                english = "",
                tone = Tone.NEUTRAL,
                reading = Texts.illnessRansom(birth.animal),
                titleIsKanji = false,
            ),
            onOpen,
        )
        Nineteen(IllnessReckoning.nineteen(birth, onset), onOpen)
        Board(IllnessReckoning.board(birth, birthYear, day.year), onOpen)
        SectionTitle(stringResource(R.string.illness_section_people))
        val vitality = birth.forces.vitality
        IllnessHelper.entries.forEach { h ->
            AnnotationRow(
                Annotation(
                    title = labels.string(R.string.illness_helper_title, h.english.replaceFirstChar(Char::uppercase), gloss(IllnessReckoning.helper(h, vitality), "inText")),
                    english = "",
                    tone = Tone.NEUTRAL,
                    reading = Texts.illnessHelper(h),
                    titleIsKanji = false,
                ),
                onOpen,
            )
        }
        val hour = Forces.hours(Forces.signs(now.day).date)[now.minutes / 120].sign.animal
        val stage = IllnessReckoning.stage(birth, hour)
        AnnotationRow(
            Annotation(
                title = labels.string(R.string.illness_stage_title, stage.english),
                english = labels.string(R.string.illness_stage_subtitle, gloss(hour).lowercase()),
                tone = Tone.NEUTRAL,
                reading = Texts.illnessStage(stage),
                titleIsKanji = false,
            ),
            onOpen,
        )
    }
    Text(stringResource(R.string.illness_note), style = body.copy(fontSize = 13.sp, color = Palette.muted, lineHeight = 19.sp), modifier = Modifier.padding(top = 10.dp))
    if (clock) {
        BellTimeDialog(
            stringResource(R.string.illness_time_dialog),
            time,
            onSet = { onTime(it); clock = false },
            onDismiss = { clock = false },
            note = stringResource(R.string.illness_time_dialog_note),
        )
    }
}

/**
 * The illness named by five element pebbles (ROADMAP H2c): the life force of the year, month, day and hour of falling
 * ill and the element most among their trigrams; the element most among the five opens its illness, two sharing the
 * most both, and a split of two elements that p. 436 names opens its own.
 */
@Composable
private fun FivePebbles(pebbles: List<Element>, onOpen: (Annotation) -> Unit) {
    val labels = LocalLabels.current
    SectionTitle(stringResource(R.string.illness_section_five))
    Text(
        labels.string(R.string.illness_five_line, pebbles.joinToString(", ") { gloss(it, "inText") }),
        style = body.copy(fontSize = 14.sp, color = Palette.muted, lineHeight = 20.sp),
        modifier = Modifier.padding(bottom = 4.dp),
    )
    IllnessReckoning.split(pebbles)?.takeIf { it in IllnessReckoning.SPLITS }?.let { split ->
        AnnotationRow(
            Annotation(
                title = labels.string(R.string.illness_split_title, split.split('_').joinToString(" · ") { s -> "${s.last()} ${gloss(Element.valueOf(s.dropLast(1)), "inText")}" }),
                english = "",
                tone = Tone.NEUTRAL,
                reading = Texts.illnessSplit(split),
                titleIsKanji = false,
            ),
            onOpen,
        )
    }
    IllnessReckoning.chief(pebbles).forEach { AnnotationRow(chiefAnnotation(it), onOpen) }
}

/** The illness of the element [e] that is most among the five pebbles (p. 435). */
@Composable
private fun chiefAnnotation(e: Element): Annotation = Annotation(
    title = LocalLabels.current.string(R.string.illness_chief_title, gloss(e, "inText")),
    english = "",
    tone = Tone.NEUTRAL,
    reading = Texts.ELEMENT_ILLNESS.getValue(e),
    titleIsKanji = false,
)

/** [f]'s plank-tree on [board] with its verdict (pp. 422–423), its pebbles drawn by the three five-rows. */
@Composable
private fun treeAnnotation(board: IllnessBoard, f: Force): Annotation {
    val labels = LocalLabels.current
    val tree = board.tree(f)
    val verdict = board.verdict(f)
    return Annotation(
        title = labels.string(R.string.illness_tree_title, gloss(f).replaceFirstChar(Char::uppercase), stringResource(VERDICT.getValue(verdict))),
        english = "",
        tone = toneOf(tree),
        reading = Texts.illnessTree(f, verdict),
        subtitle = labels.string(R.string.illness_tree_drawn, drawn(tree.take(5)), drawn(tree.subList(5, 10)), drawn(tree.drop(10))),
        titleIsKanji = false,
    )
}

/** The spirit the body's [n] black pebbles name (p. 425). */
@Composable
private fun blackAnnotation(n: Int): Annotation =
    Annotation(title = LocalLabels.current.string(R.string.illness_black_title, n), english = "", tone = Tone.BAD, reading = Texts.illnessBlack(n), titleIsKanji = false)

/** The blocked pebble doors of [board] (p. 419): one row each, or one for all four. */
@Composable
private fun doorAnnotations(board: IllnessBoard): List<Annotation> {
    val labels = LocalLabels.current
    val blocked = Force.entries.filter { board.door[it.ordinal] == PebbleColour.BLACK }
    return (if (blocked.size == 4) listOf(null) else blocked).map { f ->
        Annotation(
            title = if (f == null) stringResource(R.string.illness_door_all) else labels.string(R.string.illness_door_title, gloss(f)),
            english = "",
            tone = Tone.BAD,
            reading = Texts.illnessDoor(f),
            titleIsKanji = false,
        )
    }
}

/**
 * The illness together (ROADMAP R5; docs/sources/verdicts.md, RD58), under the time of falling ill: the illness named
 * by the element that is most (p. 435); with the sick person chosen, the spirit the body's black pebbles name (p. 425),
 * the four plank-trees' verdicts (pp. 422–423), the blocked pebble doors (p. 419) and the class of the body's fifteen
 * (p. 425; the notes, vol. 2, p. 496), each as the sections below reckon it; then the Remedy rows of the readings
 * shown, gathered into one (a starting point mostly white left out, its rite answering a point lost).
 */
@Composable
private fun IllnessTogether(onset: zanshin.core.tibetan.IllnessOnset, person: Person?, now: zanshin.core.tibetan.IllnessOnset, onOpen: (Annotation) -> Unit) {
    val labels = LocalLabels.current
    val muted = body.copy(fontSize = 13.sp, color = Palette.muted, lineHeight = 19.sp)
    SectionTitle(stringResource(R.string.match_section_together))
    IllnessReckoning.chief(onset.pebbles).forEach { AnnotationRow(chiefAnnotation(it), onOpen) }
    val shown = mutableListOf<zanshin.core.texts.Reading>()
    if (person == null) {
        Text(stringResource(R.string.illness_together_needed), style = muted)
    } else {
        val birth = Reckonings.birthSign(person)
        val board = IllnessReckoning.board(birth, Reckonings.birthYear(person), onset.day.year)
        board.bodyBlack.takeIf { it > 0 }?.let { AnnotationRow(blackAnnotation(it), onOpen) }
        val trees = Force.entries.map { treeAnnotation(board, it) }
        AnnotationRow(
            Annotation(
                title = stringResource(R.string.illness_trees_title),
                english = Force.entries.map { labels.string(R.string.illness_trees_one, gloss(it), stringResource(VERDICT.getValue(board.verdict(it)))) }.joinToString(" · "),
                tone = Tone.NEUTRAL, reading = null, titleIsKanji = false, parts = trees,
            ),
            onOpen,
        )
        doorAnnotations(board).forEach { AnnotationRow(it, onOpen) }
        val fifteen = board.tree(Force.BODY)
        val white = fifteen.count { it == PebbleColour.WHITE }
        val black = fifteen.count { it == PebbleColour.BLACK }
        val c = board.bodyClass
        if (c == null) {
            Text(labels.string(R.string.illness_class_none, white, black), style = muted)
        } else {
            Text(labels.string(R.string.illness_class_counts, white, black), style = muted)
            AnnotationRow(
                Annotation(
                    title = labels.string(R.string.illness_class_title, c.english),
                    english = "",
                    tone = when (c.white) { true -> Tone.GOOD; false -> Tone.BAD; null -> Tone.MIXED },
                    reading = Texts.illnessClass(c),
                    titleIsKanji = false,
                ),
                onOpen,
            )
            shown += Texts.illnessClass(c)
        }
        shown += personReadings(person, onset, now, board)
    }
    val remedies = gatheredRemedies(onsetReadings(onset) + shown)
    if (remedies.isNotEmpty()) {
        AnnotationRow(
            Annotation(title = stringResource(R.string.together_remedies), english = "", tone = Tone.NEUTRAL, reading = Texts.illnessTogether(remedies), titleIsKanji = false),
            onOpen,
        )
    }
}

/** The readings of the moment of falling ill and of the five pebbles, as the view below shows them. */
private fun onsetReadings(onset: zanshin.core.tibetan.IllnessOnset): List<zanshin.core.texts.Reading> = buildList {
    val t = onset.trigram
    add(Texts.illnessTrigram(t)); add(Texts.illnessTrigramCause(t)); add(Texts.illnessTrigramRansom(t)); add(Texts.illnessFlee(t))
    add(Texts.illnessDay(onset.dayAnimal)); add(Texts.illnessMewa(onset.mewa)); add(Texts.illnessPlanet(onset.day.weekday))
    IllnessReckoning.split(onset.pebbles)?.takeIf { it in IllnessReckoning.SPLITS }?.let { add(Texts.illnessSplit(it)) }
    IllnessReckoning.chief(onset.pebbles).forEach { add(Texts.ELEMENT_ILLNESS.getValue(it)) }
}

/**
 * The readings the view shows for [person] as the sick one, in its order, the starting points mostly white left out;
 * [now] the moment of asking, for the hearth-keeper's stage.
 */
private fun personReadings(person: Person, onset: zanshin.core.tibetan.IllnessOnset, now: zanshin.core.tibetan.IllnessOnset, board: IllnessBoard): List<zanshin.core.texts.Reading> = buildList {
    val day = onset.day
    val birth = Reckonings.birthSign(person)
    val birthYear = Reckonings.birthYear(person)
    add(Texts.illnessSpirit(onset.mewa, IllnessReckoning.spiritKind(YearOfLife.yearSmeBa(birthYear), onset.mewa)))
    person.gender?.let { add(Texts.illnessReach(IllnessReckoning.reach(birth, birthYear, it, day.year))) }
    add(Texts.illnessYear(birth.element, IllnessYearPair.of(day.yearAnimal)))
    add(Texts.illnessRansom(birth.animal))
    IllnessReckoning.nineteen(birth, onset).forEach { add(Texts.illnessPebble(it)) }
    Force.entries.forEach { add(Texts.illnessTree(it, board.verdict(it))) }
    board.bodyBlack.takeIf { it > 0 }?.let { add(Texts.illnessBlack(it)) }
    Force.entries.filter { board.door[it.ordinal] == PebbleColour.BLACK }.let { b -> (if (b.size == 4) listOf(null) else b).forEach { add(Texts.illnessDoor(it)) } }
    board.points.filter { toneOf(it.pebbles) != Tone.GOOD }.forEach { add(Texts.illnessPoint(it.n)) }
    IllnessHelper.entries.forEach { add(Texts.illnessHelper(it)) }
    val hour = Forces.hours(Forces.signs(now.day).date)[now.minutes / 120].sign.animal
    add(Texts.illnessStage(IllnessReckoning.stage(birth, hour)))
}

/** The sixteen fought pebbles of the nineteen (ROADMAP H2d), with the count of white and black, each opening its reading. */
@Composable
private fun Nineteen(pebbles: List<IllnessPebble>, onOpen: (Annotation) -> Unit) {
    val labels = LocalLabels.current
    SectionTitle(stringResource(R.string.illness_section_nineteen))
    Text(
        labels.string(R.string.illness_nineteen_line, pebbles.count { it.white }, pebbles.count { !it.white }),
        style = body.copy(fontSize = 14.sp, color = Palette.muted, lineHeight = 20.sp),
        modifier = Modifier.padding(bottom = 4.dp),
    )
    pebbles.forEach { p ->
        AnnotationRow(
            Annotation(
                title = labels.string(R.string.illness_pebble_title, p.aspect.english.replaceFirstChar(Char::uppercase), p.time.english, stringResource(if (p.white) R.string.illness_white else R.string.illness_black)),
                english = "",
                tone = if (p.white) Tone.GOOD else Tone.BAD,
                reading = Texts.illnessPebble(p),
                titleIsKanji = false,
            ),
            onOpen,
        )
    }
}

/** A pebble drawn: ○ white, × black, ◐ speckled. */
private fun drawn(p: List<PebbleColour>) = p.joinToString("") { when (it) { PebbleColour.WHITE -> "○"; PebbleColour.BLACK -> "×"; PebbleColour.SPECKLED -> "◐" } }

/** More white than black good, more black bad, else mixed. */
private fun toneOf(p: List<PebbleColour>): Tone = p.count { it == PebbleColour.WHITE }.compareTo(p.count { it == PebbleColour.BLACK }).let {
    if (it > 0) Tone.GOOD else if (it < 0) Tone.BAD else Tone.MIXED
}

/**
 * The board of the fifteen starting points (ROADMAP H2g): each aspect's plank-tree with its verdict, the spirit the
 * body's black pebbles name, the pebble door's blocked doors, and the fifteen points, each with the year reached and its
 * four pebbles, opening its reading.
 */
@Composable
private fun Board(board: IllnessBoard, onOpen: (Annotation) -> Unit) {
    val labels = LocalLabels.current
    fun name(s: Sign) = labels.string(R.string.element_animal, gloss(s.element), gloss(s.animal))
    SectionTitle(stringResource(R.string.illness_section_board))
    Force.entries.forEach { AnnotationRow(treeAnnotation(board, it), onOpen) }
    board.bodyBlack.takeIf { it > 0 }?.let { AnnotationRow(blackAnnotation(it), onOpen) }
    val blocked = Force.entries.filter { board.door[it.ordinal] == PebbleColour.BLACK }
    doorAnnotations(board).forEach { AnnotationRow(it, onOpen) }
    if (blocked.isEmpty()) Text(stringResource(R.string.illness_door_open), style = body.copy(fontSize = 14.sp, color = Palette.muted, lineHeight = 20.sp))
    board.points.forEach { p ->
        AnnotationRow(
            Annotation(
                title = labels.string(R.string.illness_point_title, p.n, drawn(p.pebbles)),
                english = labels.string(R.string.illness_point_reached, name(p.reached)),
                tone = toneOf(p.pebbles),
                reading = Texts.illnessPoint(p.n),
                titleIsKanji = false,
                spokenTitle = labels.string(R.string.illness_point_spoken, p.n, p.pebbles.count { it == PebbleColour.WHITE }, p.pebbles.count { it == PebbleColour.BLACK }),
            ),
            onOpen,
        )
    }
}

private val VERDICT = mapOf(
    IllnessTreeVerdict.STANDS to R.string.illness_tree_stands,
    IllnessTreeVerdict.MOSTLY_WHITE to R.string.illness_tree_mostly_white,
    IllnessTreeVerdict.SPECKLED to R.string.illness_tree_speckled,
    IllnessTreeVerdict.MOSTLY_BLACK to R.string.illness_tree_mostly_black,
    IllnessTreeVerdict.BROKEN to R.string.illness_tree_broken,
)
