/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.texts

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import zanshin.core.kyureki.Choku
import zanshin.core.kyureki.Ehou
import zanshin.core.kyureki.Gogyo
import zanshin.core.kyureki.KyuSei
import zanshin.core.kyureki.Rokuyo
import zanshin.core.kyureki.Senjitsu
import zanshin.core.kyureki.Shuku
import zanshin.core.kyureki.SolarTerm
import zanshin.core.kyureki.StarRelation
import zanshin.core.kyureki.Zassetsu
import zanshin.core.tibetan.HundredFeet
import zanshin.core.tibetan.CourseEvent
import zanshin.core.tibetan.EarthLordCourses
import zanshin.core.tibetan.Animal
import zanshin.core.tibetan.Care
import zanshin.core.tibetan.Element
import zanshin.core.tibetan.Sign
import zanshin.core.tibetan.ElectionalFactor
import zanshin.core.tibetan.ElementPair
import zanshin.core.tibetan.Gender
import zanshin.core.tibetan.Force
import zanshin.core.tibetan.IndianElement
import zanshin.core.tibetan.Karana
import zanshin.core.tibetan.Kinship
import zanshin.core.tibetan.LunarDayClass
import zanshin.core.tibetan.LetterElement
import zanshin.core.tibetan.LifeStage
import zanshin.core.tibetan.Link
import zanshin.core.tibetan.SenseObject
import zanshin.core.tibetan.Mansion
import zanshin.core.tibetan.PersonalDay
import zanshin.core.tibetan.PersonalMansion
import zanshin.core.tibetan.OwnDay
import zanshin.core.tibetan.SME_BA_COLOURS
import zanshin.core.tibetan.SpecialDay
import zanshin.core.tibetan.TibetanFestival
import zanshin.core.tibetan.CombinationDay
import zanshin.core.tibetan.GreatCombination
import zanshin.core.tibetan.Trigram
import zanshin.core.tibetan.Weekday
import zanshin.core.tibetan.Yoga
import zanshin.core.tibetan.BasicSign
import zanshin.core.tibetan.HarshYear
import zanshin.core.tibetan.KinObstacle
import zanshin.core.tibetan.GroupCut
import zanshin.core.tibetan.BirthReckoning
import zanshin.core.tibetan.YearPlace
import zanshin.core.tibetan.PowerYear
import zanshin.core.tibetan.ClanYear
import zanshin.core.tibetan.BirthOmen
import zanshin.core.tibetan.BirthChild
import zanshin.core.tibetan.BirthShare
import zanshin.core.tibetan.DpalByur
import zanshin.core.tibetan.LineGroup
import zanshin.core.tibetan.LineStage
import zanshin.core.tibetan.BadYear
import zanshin.core.tibetan.MatchMeeting
import zanshin.core.tibetan.MatchPlanet
import zanshin.core.tibetan.PairLine
import zanshin.core.tibetan.PairList
import zanshin.core.tibetan.YearSexes
import zanshin.core.tibetan.PebbleColour
import zanshin.core.tibetan.PreciousPebble
import zanshin.core.tibetan.RaisingDay
import zanshin.core.tibetan.RaisingMonth
import zanshin.core.tibetan.MatchReckoning
import zanshin.core.tibetan.NiSha
import zanshin.core.tibetan.LogMenPlace
import zanshin.core.tibetan.Sector
import zanshin.core.tibetan.SmeBaObstacle
import zanshin.core.tibetan.YearOfLife
import zanshin.core.tibetan.YearReckoning
import zanshin.core.tibetan.ElementQuarter
import zanshin.core.tibetan.GraveMansion
import zanshin.core.tibetan.Humour
import zanshin.core.tibetan.LaHour
import zanshin.core.tibetan.MedicalSeason
import java.io.File
import java.util.Locale
import javax.xml.parsers.DocumentBuilderFactory

/** SPEC §8.2: every text the engines show comes from the catalog, and a translation matches it. */
class CatalogTest {
    private val english = Catalog.entries("")

    private fun present(key: String) = assertNotNull(english[key], "missing from texts.properties: $key")

    @Test
    fun `every term has its English`() {
        val terms: List<Enum<*>> = listOf(
            IndianElement.entries, Weekday.entries, Mansion.entries, Yoga.entries, Karana.entries, ElementPair.entries,
            Trigram.entries, SpecialDay.entries, TibetanFestival.entries, PersonalDay.entries, Force.entries, Kinship.entries,
            Rokuyo.entries, SolarTerm.entries, Gogyo.entries, Choku.entries, Shuku.entries, KyuSei.entries,
            StarRelation.entries, Senjitsu.entries, Zassetsu.entries, Ehou.entries, Activity.entries, ActivityFamily.entries, License.entries, ElectionalFactor.entries, DayFactor.entries, HundredFeet.Holder.entries, GreatCombination.entries, CombinationDay.entries,
            LunarDayClass.entries, PersonalMansion.entries, OwnDay.entries, LetterElement.entries, SenseObject.entries, LifeStage.entries, Link.entries,
            BasicSign.entries, Sector.entries, HarshYear.entries, LogMenPlace.entries, SmeBaObstacle.entries, KinObstacle.entries, ReadingRow.entries,
            ElementQuarter.entries, GraveMansion.entries, Humour.entries, MedicalSeason.entries, LaHour.entries,
        ).flatten()
        terms.forEach { present(glossKey(it)) }
        Weekday.entries.forEach { present(glossKey(it, "planet")) }
        (Element.entries + Animal.entries + Gender.entries).forEach { present(glossKey(it)) }
        (Element.entries + Gender.entries).forEach { present(glossKey(it, "inText")) }
        TibetanFestival.entries.forEach { present(glossKey(it, "title")) }
        Trigram.entries.forEach { present(glossKey(it, "goddess")) }
        LaHour.entries.forEach { present(glossKey(it, "place")) }
        (1..30).forEach { present("LaPlace.$it") }
        (0..9).forEach { present("Stem.$it") }
        (0..11).forEach { present("Branch.$it") }
        (1..12).forEach { present("KyurekiMonth.$it"); present("Season.$it") }
        SME_BA_COLOURS.forEach { present("Colour.$it") }
        Texts.JAPANESE_FESTIVAL.keys.forEach { present("Festival.$it") }
        listOf("Kanshi", "StarAffinity.feeds", "StarAffinity.overcomes", "StarAffinity.same").forEach(::present)
        (listOf("one", "two", "few", "other").map { "DateOrdinal.$it" } + listOf("CourseFall.line", "CourseFall.dates")).forEach(::present)
        (0..3).forEach { present("CourseFall.season.$it") }
        (0..11).forEach { present("CourseFall.month.$it") }
        // The How it falls lines of the other courses (ROADMAP X7): their forms, what a line adds, and each animal as a line names it.
        listOf("once", "month", "every", "allBut", "animals", "seasonAnimals", "everyMonth", "at", "labelled", "and", "comma", "range").forEach { present("CourseFall.$it") }
        (listOf("hour") + EarthLordCourses.ZIN_PHUNG_TIMES + EarthLordCourses.GNAM_KHYI_TIMES + EarthLordCourses.NGAM_SHING_WAYS.values).forEach { present("CourseAt.$it") }
        Animal.entries.forEach { present(glossKey(it, "of")) }
        CourseEvent.entries.filter { it != CourseEvent.SITS }.forEach { present("CourseFall.event.${it.name}") }
    }

    @Test
    fun `every reading's text is in the catalog`() {
        val readings = listOf(
            Texts.ROKUYO, Texts.CHOKU, Texts.SHUKU, Texts.KYUSEI, Texts.KIGAKU, Texts.SENJITSU, Texts.ZASSETSU, Texts.SOLAR_TERM,
            Texts.EHOU, Texts.ELEMENT_PAIR, Texts.SPECIAL_DAY, Texts.TIBETAN_FESTIVAL, Texts.JAPANESE_FESTIVAL,
            Texts.PERSONAL_DAY, Texts.PEBBLES, Texts.MANSION, Texts.ELECTIONAL_WEEKDAY, Texts.ELECTIONAL_DATE,
            Texts.ELECTIONAL_ANIMAL, Texts.ELECTIONAL_TRIGRAM, Texts.YOGA, Texts.KARANA,
            Texts.PERSONAL_MANSION, Texts.WEEKDAY, Texts.TRIGRAM, Texts.GREAT_COMBINATION, Texts.COMBINATION_DAY, Texts.GTSUG_LAG_DAY, Texts.RAHU, Texts.RAHU_GENERAL, Texts.EARTH_LORD, Texts.BLA_MKHYEN,
            Texts.OWN_DAY, Texts.GREAT_BLACK_DAY, Texts.RAHU_SEASON, Texts.ELEMENT_QUARTER, Texts.GRAVE_MANSION, Texts.HUMOUR,
            Texts.MEDICAL_SEASON,
        ).flatMap { it.values } + Texts.LA_ELEMENT + Texts.LA_HOURS + Texts.SEVEN_RED_YEAR + Texts.HAIRCUT + Texts.LUNAR_DATE + Texts.BURNING_DATE +
        Texts.DATE_SME_BA.values + Texts.TWELVE_LINK.values + Texts.HUNDRED_FEET.values + Texts.SPEAR.values + Texts.GREAT_SPEAR.values + Texts.HOUR_EARTH_LORDS.values + Texts.BLACK_HOUR + Texts.HOUR_BLA_MKHYEN.values + Texts.HOUR_SA_RGYAL.values + Texts.HOUR_GNAM_KHYI.values + Texts.HOUR_HIDDEN.values +
        Texts.HOUR_WORKS.values + Texts.ROUGH_TIME + Texts.OWN_YEAR_HOUR + Texts.HOUR_OVER_DAY +
            // The earth lords that move by date: a reading for every course on every day it can fall.
            (1..12).flatMap { m -> (1..30).flatMap { d -> Animal.entries.flatMap { a -> EarthLordCourses.of(m, d, a).map(Texts::earthLord) } } }.distinct() +
            yearOfLifeReadings()
        (Texts.YOGA.values + Texts.LUNAR_DATE + Texts.PERSONAL_MANSION.values + Texts.WEEKDAY.values + Texts.TRIGRAM.values + Texts.GREAT_COMBINATION.values + Texts.ELEMENT_PAIR.values + Texts.COMBINATION_DAY.values + Texts.RAHU.values).forEach { present(it.key) }
        for (r in readings) {
            assertTrue(r.key.startsWith("reading."), "reading without a key from ${r.source.title}")
            r.arg?.let { present(r.key); present(it) }
            (r.goodKeys + r.avoidKeys).forEach { present("wording.$it") }
        }
    }

    /**
     * A translation has only English keys, and each text the same placeholders;
     * it may lack some, which then show in English. A language offered in the
     * app's menu must have them all, which the app's TranslationsTest checks.
     */
    /** Every reading a year of life can show: each birth sign over sixty years of age, for no gender, men and women. */
    private fun yearOfLifeReadings(): List<Reading> {
        val out = mutableSetOf<Reading>()
        for (n in 0 until 60) for (age in 1..100) for (gender in listOf(null, Gender.MALE, Gender.FEMALE)) {
            val birthYear = 1924 + n
            val year = birthYear + age - 1
            val r = YearReckoning(YearOfLife.signOf(n + 1924 - 1984), birthYear, YearOfLife.yearSign(year), year, gender, null)
            r.pebbles.forEach { out += Texts.yearPebble(it.force, it.pebbles) }
            r.sectors.forEach { out += Texts.yearSector(it.sector, it.force) }
            r.harsh.forEach { out += Texts.harsh(it) }
            r.smeBaObstacles.forEach { out += Texts.mewaObstacle(it) }
            r.trigram?.let { out += Texts.yearTrigram(it) }
            if (r.logMen != null) out += Texts.logMen(r.logMenPlace)
            if (r.nineMultiple) out += Texts.nineMultiple(gender!!, r.age)
            Texts.yearMewa(r.natalSmeBa, r.currentSmeBa)?.let { out += it }
        }
        for (f in Force.entries + listOf(null)) for (w in listOf(true, false)) out += Texts.yearPredictive(f, w)
        Trigram.entries.forEach { out += Texts.natalTrigram(it) }
        KinObstacle.entries.forEach { out += Texts.kinObstacle(it) }
        BirthChild.entries.forEach { c -> Element.entries.forEach { out += Texts.birthChild(c, it) } }
        BirthOmen.entries.forEach { out += Texts.birthOmen(it) }
        ClanYear.entries.forEach { out += Texts.clanYear(it) }
        Animal.entries.forEach { out += Texts.yearNature(it) }
        Element.entries.forEach { out += Texts.bodyNature(it) }
        (1..9).forEach { out += Texts.birthMewa(it) }
        RaisingMonth.entries.forEach { out += Texts.raisingMonth(it) }
        PreciousPebble.entries.forEach { p -> listOf(PebbleColour.WHITE, PebbleColour.BLACK).forEach { out += Texts.preciousPebble(p, it)!! } }
        RaisingDay.entries.forEach { out += Texts.raisingDay(it) }
        Force.entries.forEach { f -> Sector.entries.forEach { out += Texts.riseDecline(f, it) } }
        listOf(Force.VITALITY, Force.BODY, Force.DESTINY, Force.LUCK).forEach { f -> DpalByur.entries.forEach { r -> Element.entries.forEach { out += Texts.dpalByur(f, r, it) } } }
        LineStage.entries.forEach { s -> LineGroup.entries.forEach { out += Texts.birthLine(s, it) } }
        MatchReckoning.ASPECTS.forEach { f -> MatchMeeting.entries.forEach { out += Texts.matchAspect(f, it) } }
        out += Texts.matchKarma()
        Element.entries.forEach { a -> Element.entries.forEach { out += Texts.matchDestiny(MatchReckoning.destinyPair(a, it)) } }
        NiSha.entries.forEach { out += Texts.matchNiSha(it) }
        BadYear.entries.forEach { out += Texts.badYear(it) }
        MatchPlanet.entries.forEach { out += Texts.matchPlanet(it) }
        Animal.entries.filter { it != Animal.MONKEY }.forEach { out += Texts.matchSameAnimal(it) }
        PairList.entries.forEach { out += Texts.matchPairList(it) }
        PairLine.entries.forEach { out += Texts.matchPairLine(it) }
        Element.entries.forEach { e -> YearSexes.entries.forEach { s -> Texts.matchSameElement(e, s)?.let { out += it } } }
        PowerYear.entries.forEach { out += Texts.matchSameKind(it) }
        BirthShare.entries.forEach { s -> BirthChild.entries.forEach { k -> Element.entries.forEach { out += Texts.birthAspect(s, k, it) } } }
        (0 until 60 step 2).forEach { n -> out += Texts.khaSrog(Sign(Element.entries[n % 10 / 2], Animal.entries[n % 12])) }
        (0 until 60).forEach { n -> out += Texts.sixtyYear(Sign(Element.entries[n % 10 / 2], Animal.entries[n % 12])) }
        PowerYear.entries.forEach { y -> out += Texts.powerYear(y); Element.entries.forEach { out += Texts.powerYearOf(y, it) } }
        listOf(Element.WOOD, Element.IRON, Element.WATER, Element.FIRE).forEach { out += Texts.luckTriad(it) }
        YearPlace.entries.forEach { out += Texts.yearPlace(it); out += Texts.yearPlaceFull(it) }
        BirthReckoning.CUTS.forEach { (a, b) -> out += Texts.birthCut(a, b) }
        GroupCut.entries.forEach { out += Texts.groupCut(it) }
        Care.entries.forEach { out += Texts.yearCare(it) }
        return out.toList()
    }

    /** No text of the year of life is left that no reckoning shows. */
    @Test
    fun `every text of the year of life is shown`() {
        val shown = yearOfLifeReadings().flatMap { r ->
            listOfNotNull(r.key, r.arg) + r.rowKeys.flatMap { k -> ReadingRow.entries.map { "$k.${it.key}" } }
        }.toSet()
        val families = listOf(
            "YearCare", "YearPebble", "YearPredictive", "YearSector", "YearDecline", "LogMen", "Harsh", "NineMultiple", "MewaObstacle", "YearTrigram", "YearMewa",
            "NatalTrigram", "KinObstacle",
        )
        val written = english.keys.filter { k -> families.any { k == "reading.$it" || k.startsWith("reading.$it.") } }
        assertEquals(emptyList<String>(), written.filter { it !in shown }.sorted())
    }

    @Test
    fun `every language has English keys and placeholders`() {
        val dir = File("src/main/resources/texts")
        val languages = dir.listFiles().orEmpty().mapNotNull { Regex("texts(_.+)\\.properties").matchEntire(it.name)?.groupValues?.get(1) }
        val placeholders = Regex("\\{\\d}")
        for (suffix in languages) {
            val t = Catalog.entries(suffix)
            assertTrue(english.keys.containsAll(t.keys), "texts$suffix.properties: keys not in English: ${t.keys - english.keys}")
            for ((k, v) in t) {
                assertEquals(placeholders.findAll(english.getValue(k)).map { it.value }.toSet(), placeholders.findAll(v).map { it.value }.toSet(), "$suffix $k")
            }
        }
    }

    /**
     * The White Beryl (vol. 2, pp. 303–304) places the la on every date for people and for horses and other
     * livestock: in the reading, or in its *La today* row once the date has its rows (ROADMAP X3).
     */
    @Test
    fun `every lunar date gives the la's place in a person and in livestock`() {
        val livestock = mapOf("" to "in horses and other livestock", "_ru" to "у лошадей и другого скота")
        for ((suffix, words) in livestock) {
            val t = Catalog.entries(suffix)
            for (date in 1..30) {
                val reading = t.getValue("reading.LunarDate.$date") + " " + t["reading.LunarDate.$date.${ReadingRow.LA.key}"].orEmpty()
                assertTrue("(བླ, bla)" in reading && words in reading, "texts$suffix reading.LunarDate.$date")
            }
        }
    }

    /**
     * The names of the White Beryl's terms follow its words (ROADMAP T2.17), in every
     * language: terms it writes differently never share a name, and one word of its keeps
     * one word across the weekday-and-mansion reckonings that use it. Trigrams are left
     * out: they are named by their image (li, fire), not by a Tibetan word.
     */
    @Test
    fun `names follow the White Beryl's words`() {
        val terms: List<Pair<Enum<*>, String>> = Weekday.entries.map { it to it.wylie } +
            IndianElement.entries.map { it to it.wylie } + Mansion.entries.map { it to it.wylie } +
            Yoga.entries.map { it to it.wylie } + Karana.entries.map { it to it.wylie } +
            ElementPair.entries.map { it to it.wylie } + GreatCombination.entries.map { it to it.wylie } +
            CombinationDay.entries.map { it to it.wylie } + LunarDayClass.entries.map { it to it.wylie } +
            PersonalMansion.entries.map { it to it.wylie } + Force.entries.map { it to it.wylie } +
            OwnDay.entries.map { it to it.wylie }
        // The fire pair's 'phel 'gyur is 'phel, "growth", with 'gyur, "becoming": one word.
        val word = mapOf("'phel 'gyur" to "'phel")
        // Each WB word: its stem in English and Russian, and the names and readings that carry it.
        val oneWord = mapOf(
            "dngos grub" to Triple("attainment", "достижени", listOf("Yoga.SIDDHI", "ElementPair.EARTH_EARTH")),
            "grub" to Triple("accomplishment", "свершени", listOf("GreatCombination.GRUB", "CombinationDay.GRUB_NYI", "CombinationDay.GRUB_SBYOR")),
            "'phel" to Triple("growth", "возрастани", listOf("Yoga.VRIDDHI", "ElementPair.FIRE_FIRE", "GreatCombination.PHEL", "CombinationDay.PHEL_NYI")),
            "sreg" to Triple("burning", "сожжени", listOf("ElementPair.EARTH_FIRE", "BurningDate", "reading.CombinationDay.GTAN_SPANG.also_called")),
            "mi 'phrod" to Triple("incompatibility", "несовместимост", listOf("ElementPair.EARTH_WIND", "CombinationDay.MI_PHROD_NYI")),
            "mi mthun" to Triple("discord", "разлад", listOf("ElementPair.WATER_WIND", "CombinationDay.MI_MTHUN_NYI")),
            "bdud rtsi" to Triple("nectar", "нектар", listOf("ElementPair.WATER_WATER", "GreatCombination.BDUD_RTSI")),
            "'chi" to Triple("death", "смерт", listOf("ElementPair.FIRE_WATER", "CombinationDay.CHI_SBYOR", "GreatCombination.CHI_BDAG")),
            "dga' ba" to Triple("joy", "радост", listOf("Yoga.HARSHANA", "LunarDayClass.NANDA")),
        )
        for (suffix in listOf("", "_ru")) {
            val t = Catalog.entries(suffix)
            terms.groupBy { (e, _) -> t.getValue(glossKey(e)).lowercase() }.forEach { (name, same) ->
                assertEquals(1, same.map { word[it.second] ?: it.second }.toSet().size, "texts$suffix: «$name» names ${same.map { "${glossKey(it.first)} (${it.second})" }}")
            }
            for ((wylie, carried) in oneWord) {
                val (en, ru, keys) = carried
                val stem = if (suffix == "") en else ru
                keys.forEach { assertTrue(stem in t.getValue(it).lowercase(), "texts$suffix, $wylie: $it lacks «$stem»") }
            }
        }
    }

    /**
     * SPEC §8.1: every Tibetan word is named in English, then its Tibetan
     * script and Wylie in brackets, so that a reader can find it; several in
     * one bracket stand apart by semicolons, and a translation names the same
     * words as English.
     */
    @Test
    fun `Tibetan words are named in English with their Tibetan and Wylie`() {
        val dir = File("src/main/resources/texts")
        val suffixes = listOf("") + dir.listFiles().orEmpty().mapNotNull { Regex("texts(_.+)\\.properties").matchEntire(it.name)?.groupValues?.get(1) }
        val catalogs = suffixes.associateWith { Catalog.entries(it) }
        val sources = Sources::class.java.declaredMethods
            .filter { it.returnType == Source::class.java && it.parameterCount == 0 }
            .map { it.isAccessible = true; it.invoke(Sources) as Source }
        assertTrue(sources.size > 30)
        val known = TibetanNaming.engineWylie +
            (catalogs.values.flatMap { it.values } + sources.map { it.title }).flatMap { TibetanNaming.pairs(it).map { p -> p.second } } -
            ALSO_ENGLISH
        val wrong = mutableListOf<String>()
        for ((suffix, t) in catalogs) {
            for ((k, v) in t.toSortedMap()) {
                TibetanNaming.problems(v, known).forEach { wrong += "texts$suffix $k: $it" }
                if (suffix != "") {
                    val (en, here) = TibetanNaming.pairs(english.getValue(k)).toSet() to TibetanNaming.pairs(v).toSet()
                    if (en != here) wrong += "texts$suffix $k: names ${here - en}, English ${en - here}"
                }
            }
        }
        for (s in sources) TibetanNaming.problems(s.title, known).forEach { wrong += "source ${s.publisher}: $it" }
        assertEquals("", wrong.joinToString("\n"))
    }

    /**
     * SPEC §8.1: a reading names no source, no title, author, publisher,
     * page or chapter (owner, 2026-10-09); each reading's [Source] and the
     * repository's documentation keep them. The licence labels are the
     * catalog's record and are not shown.
     */
    @Test
    fun `readings name no source`() {
        val wrong = mutableListOf<String>()
        for (suffix in listOf("", "_ru")) {
            for ((k, v) in Catalog.entries(suffix).toSortedMap()) {
                if (k.startsWith("License.")) continue
                CITATION.find(v)?.let { wrong += "texts$suffix $k: ${it.value}" }
            }
        }
        assertEquals("", wrong.joinToString("\n"))
    }

    /** Every reading the engines can show, for the shape's checks: the readings above and those shown only by the page. */
    private val allReadings: List<Reading> by lazy {
        listOf(
            Texts.ROKUYO, Texts.CHOKU, Texts.SHUKU, Texts.KYUSEI, Texts.KIGAKU, Texts.SENJITSU, Texts.ZASSETSU, Texts.SOLAR_TERM,
            Texts.EHOU, Texts.ELEMENT_PAIR, Texts.SPECIAL_DAY, Texts.TIBETAN_FESTIVAL, Texts.JAPANESE_FESTIVAL,
            Texts.PERSONAL_DAY, Texts.PEBBLES, Texts.MANSION, Texts.ELECTIONAL_WEEKDAY, Texts.ELECTIONAL_DATE,
            Texts.ELECTIONAL_ANIMAL, Texts.ELECTIONAL_TRIGRAM, Texts.YOGA, Texts.KARANA, Texts.PERSONAL_MANSION, Texts.WEEKDAY,
            Texts.TRIGRAM, Texts.GREAT_COMBINATION, Texts.COMBINATION_DAY, Texts.GTSUG_LAG_DAY, Texts.RAHU, Texts.RAHU_GENERAL,
            Texts.EARTH_LORD, Texts.BLA_MKHYEN, Texts.OWN_DAY, Texts.GREAT_BLACK_DAY, Texts.RAHU_SEASON, Texts.RAHU_MONTH,
            Texts.WORKS_SIGN, Texts.SEASON_SIGN, Texts.DATE_SME_BA, Texts.TWELVE_LINK, Texts.HUNDRED_FEET, Texts.HOUR_HIDDEN,
            Texts.HOUR_WORKS, Texts.ELEMENT_QUARTER, Texts.GRAVE_MANSION, Texts.HUMOUR, Texts.MEDICAL_SEASON,
        ).flatMap { it.values } + Texts.LA_ELEMENT + Texts.LA_HOURS + Texts.SEVEN_RED_YEAR + Texts.DUS_SBYOR.values.map { it.second } + Texts.HAIRCUT + Texts.HAIRCUT_LIST +
            Texts.LUNAR_DATE + Texts.BURNING_DATE + Texts.SPEAR.values + Texts.GREAT_SPEAR.values + Texts.HOUR_EARTH_LORDS.values +
            Texts.BLACK_HOUR + Texts.HOUR_BLA_MKHYEN.values + Texts.HOUR_SA_RGYAL.values + Texts.HOUR_GNAM_KHYI.values +
            Texts.ROUGH_TIME + Texts.OWN_YEAR_HOUR + Texts.HOUR_OVER_DAY + Texts.NECTAR_PERIODS + Texts.COMET +
            Texts.LUNAR_ECLIPSE + Texts.SOLAR_ECLIPSE +
            // Every course on every day it can fall, in every year (the black day's other view goes by the year).
            (1..12).flatMap { m ->
                (1..30).flatMap { d -> Animal.entries.flatMap { a -> (Animal.entries + null).flatMap { y -> EarthLordCourses.of(m, d, a, y).map(Texts::earthLord) } } }
            }.distinct() +
            yearOfLifeReadings()
    }

    private val rowKeys = ReadingRow.entries.map { it.key }.toSet()

    private fun isRow(key: String) = key.startsWith("reading.") && key.substringAfterLast('.') in rowKeys

    /**
     * ROADMAP X2: a reading's rows are `<reading>.<row>` beside its own text, `<row>` one of
     * [ReadingRow]'s; a key in that place under another name is a row the sheet would not show. Its
     * variants (an earth lord's "other" view, a class's time) are texts a reading names and stay.
     */
    @Test
    fun `a reading's rows are the shape's rows`() {
        val named = allReadings.flatMap { listOfNotNull(it.key, it.arg) }.toSet()
        val entries = allReadings.flatMap { it.rowKeys }.toSet()
        val wrong = english.keys.filter { k ->
            k !in named && k.substringBeforeLast('.') in entries && k.substringAfterLast('.').matches(Regex("[a-z_]+")) && !isRow(k)
        }
        assertEquals(emptyList<String>(), wrong.sorted(), "rows other than ${rowKeys.sorted()}")
        val orphans = english.keys.filter { isRow(it) && it.substringBeforeLast('.') !in entries }
        assertEquals(emptyList<String>(), orphans.sorted(), "rows of no reading")
        assertEquals(
            listOf(ReadingRow.ALSO_CALLED, ReadingRow.ILLNESS, ReadingRow.LA, ReadingRow.BIRTH, ReadingRow.DEATH, ReadingRow.LIVESTOCK),
            Texts.LUNAR_DATE.first().rows.map { it.first },
        )
    }

    /** SPEC §8.1 rule 13: the Russian is the same shape, every row the English has. */
    @Test
    fun `the Russian has every row the English has`() {
        val russian = Catalog.entries("_ru")
        assertEquals(emptyList<String>(), english.keys.filter { isRow(it) && it !in russian }.sorted())
    }

    /**
     * SPEC §8.1, *The shape of a reading* (ROADMAP X2): no paragraph under 20 words or over 60 (rule 2), none of
     * the app's or an editor's words (rules 8 and 9), and every Tibetan word with one English name and
     * one bracket across the catalog, the interface and the store listing (rule 11, D7), in both
     * languages. The families not yet brought to the shape are allowed their breaks, item by item
     * ([ALLOWANCE]); every other family, and every text that is not a reading, holds the shape now.
     */
    @Test
    fun `readings keep the shape`() {
        val breaks = shapeBreaks()
        val families = breaks.mapNotNull { (where, _) -> family(where) }.toSet() +
            english.keys.filter { it.startsWith("reading.") }.map { it.split('.')[1] }
        assertEquals(emptySet<String>(), families - FAMILIES.keys - KYUREKI, "families no X item names (ROADMAP X3–X10)")
        val byItem = breaks.groupBy { (where, _) -> family(where)?.let { FAMILIES[it] } }
        val wrong = mutableListOf<String>()
        for ((item, found) in byItem) {
            val allowed = ALLOWANCE[item] ?: 0
            if (found.size > allowed) wrong += "${item ?: "not a reading"}: ${found.size} breaks, allowed $allowed\n" +
                found.joinToString("\n") { (where, what) -> "  $where: $what" }
        }
        for ((item, allowed) in ALLOWANCE) {
            val found = byItem[item].orEmpty().size
            if (found < allowed) wrong += "$item: $found breaks left, lower its allowance from $allowed to $found"
        }
        assertEquals("", wrong.joinToString("\n"))
    }

    /** Each break of the shape: where (`texts_ru:reading.LunarDate.3`, `strings:row_rabjung`) and what. */
    private fun shapeBreaks(): List<Pair<String, String>> {
        val out = mutableListOf<Pair<String, String>>()
        for (suffix in listOf("", "_ru")) {
            for ((k, v) in Catalog.entries(suffix).toSortedMap()) {
                if (!k.startsWith("reading.") || family("texts:$k") in KYUREKI) continue
                val where = "texts$suffix:$k"
                if (!isRow(k)) ReadingShape.words(v).let { if (it > 60) out += where to "paragraph of $it words" }
                ReadingShape.APP_OR_EDITOR.getValue(suffix).find(v)?.let { out += where to "«${it.value}»" }
            }
            out += shortParagraphs(suffix)
            val russian = suffix == "_ru"
            val namings = namedTexts(russian).flatMap { (where, text) -> ReadingShape.namings(where, text, russian) }
            ReadingShape.odd(namings, plain = { !it.contains(":reading.") }, exceptions = NAMED_TWO_WAYS, oneBracket = !russian)
                .forEach { (n, what) -> out += n.where to what }
        }
        return out
    }

    /**
     * Rule 2's lower bound: every paragraph a reading shows of 20 words at least, measured as the sheet
     * shows it, a family's note filled with the entry's own text; a reading with no text of its own (the
     * source gives only its lists) has no paragraph. The break stands at the entry's own text.
     */
    private fun shortParagraphs(suffix: String): List<Pair<String, String>> {
        val t = Catalog.entries(suffix)
        fun text(key: String) = t[key] ?: english[key]
        return allReadings.mapNotNull { r ->
            val where = "texts$suffix:${r.rowKey}"
            if (family(where) in KYUREKI) return@mapNotNull null
            val paragraph = text(r.key) ?: return@mapNotNull null
            val n = ReadingShape.words(r.arg?.let { Catalog.fill(paragraph, text(it) ?: it) } ?: paragraph)
            if (n < 20) where to "paragraph of $n words" else null
        }.distinctBy { it.first }
    }

    /** The catalog's texts, the interface strings and the store listing in one language; changelogs are past releases' and stay as written. */
    private fun namedTexts(russian: Boolean): Map<String, String> {
        val suffix = if (russian) "_ru" else ""
        val out = Catalog.entries(suffix).mapKeys { "texts$suffix:${it.key}" }.toMutableMap()
        val strings = File(if (russian) "../app/src/main/res/values-ru/strings.xml" else "../app/src/main/res/values/strings.xml")
        val nodes = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(strings).getElementsByTagName("string")
        for (i in 0 until nodes.length) {
            val e = nodes.item(i) as org.w3c.dom.Element
            out["strings$suffix:${e.getAttribute("name")}"] = e.textContent.replace("\\'", "'")
        }
        val listing = File("../fastlane/metadata/android/" + if (russian) "ru-RU" else "en-US")
        for (name in listOf("title.txt", "short_description.txt", "full_description.txt")) {
            File(listing, name).takeIf { it.exists() }?.let { out["listing$suffix:$name"] = it.readText().replace(Regex("<[^>]+>"), " ") }
        }
        return out
    }

    /** The reading family a break stands in: `reading.<Family>.…` of the catalog; none for a term, a wording, a string or the listing. */
    private fun family(where: String): String? =
        where.substringAfter(':').takeIf { where.startsWith("texts") && it.startsWith("reading.") }?.split('.')?.get(1)

    @Test
    fun `a language without a catalog falls back to English`() {
        assertEquals(english.getValue("Rokuyo.TAIAN"), Catalog.text("Rokuyo.TAIAN", Locale.forLanguageTag("xx")))
        assertEquals(listOf("_zh_Hant", "_zh_TW", "_zh", ""), Catalog.candidates(Locale.forLanguageTag("zh-Hant-TW")))
    }

    private companion object {
        /** What a citation looks like, in English and Russian: a source's name, a page or volume, the text speaking. */
        val CITATION = Regex(
            "White Beryl|All-Benefiting Mirror|Moonbeams|Henning|Berzin|Janson|FPMT|Hoki|Wikipedia|Kotobank|" +
                "(?<!\\p{L})(?:vol|pp?)\\. \\d|the text (?:says|names|adds|calls|gives)|in the text's|" +
                "[Бб]ерилл|[Зз]ерцал|Хоки|Википеди|(?<!\\p{L})(?:т|сс?)\\. \\d|[Тт]екст (?:говорит|называет|добавляет|даёт)|словами текста",
        )

        /** Wylie that is also an English word, left out of the check: the fire element's *me*. */
        val ALSO_ENGLISH = setOf("me")

        /**
         * D7's exceptions, the words the sources themselves name two ways: the la weekday, *bla gza'*,
         * both the birth animal's luck day and the element's own weekday.
         */
        val NAMED_TWO_WAYS = setOf("bla gza'")

        /** The 旧暦 page's readings, from other sources and not in ROADMAP X's review: outside the shape's checks. */
        val KYUREKI = setOf("Rokuyo", "Choku", "Shuku", "Senjitsu", "Zassetsu", "KyuSei", "StarRelation", "Ehou", "Festival")

        /** The Tibetan page's and the year sheet's reading families, by the ROADMAP item that brings each to the shape. */
        val FAMILIES: Map<String, String> = mapOf(
            "X3" to listOf("LunarDate"),
            "X4" to listOf("Weekday", "Trigram"),
            "X5" to listOf("GreatCombination", "ElementPair", "CombinationDay", "SpecialDay", "GtsugLagDay", "BurningDate", "TibetanFestival"),
            "X6" to listOf("Yoga", "Mansion", "Karana", "Electional"),
            "X7" to listOf("EarthLord", "Rahu", "RahuGeneral", "RahuMonth", "RahuSeason", "GreatBlackDay", "Eclipse", "SeasonSign", "Comet"),
            "X8" to listOf("PersonalDay", "OwnDay", "PersonalMansion", "Kinship", "HundredFeet", "Spear", "GreatSpear"),
            "X9" to listOf(
                "DusSbyor", "WorksSign", "HourWorks", "HourHidden", "HourEarthLords", "HourGnamKhyi", "HourBlaMkhyen", "HourSaRgyal",
                "BlackHour", "RoughTime", "OwnYearHour", "NectarPeriods", "HourOverDay",
            ),
            "X10" to listOf("BlaMkhyen", "TwelveLinks", "DateSmeBa", "Haircut"),
            // The year sheet, brought to the shape with its remedies as rows (Y1, block 57): no allowance.
            "Y1" to listOf(
                "YearCare", "YearPebble", "YearPredictive", "YearSector", "YearDecline", "LogMen", "Harsh", "NineMultiple", "MewaObstacle",
                "YearTrigram", "YearMewa",
            ),
            // The year of age's readings with relatives (block 29), written in the shape: no allowance.
            "P2" to listOf("NatalTrigram", "KinObstacle"),
            "R2" to listOf("BirthChild", "BirthOmen", "ClanYear", "PowerYear", "PowerYearOf", "LuckTriad", "YearPlace", "YearPlaceFull", "BirthCut", "GroupCut", "YearNature", "SixtyYear", "BodyNature", "KhaSrog", "BirthMewa", "BirthAspect", "DpalByur", "BirthLine", "RiseDecline", "RaisingMonth", "RaisingDay", "PreciousPebble"),
            // The marriage match (block 31), written in the shape: no allowance.
            "R3" to listOf("MatchAspect", "MatchDestiny", "MatchNiSha", "BadYear", "MatchSameKind", "MatchPlanet", "MatchSameAnimal", "MatchSameElement", "MatchPairList", "MatchPairLine"),
            // The 旧暦 page's solar terms (K6, block 56), the one 旧暦 family written in the shape: no allowance.
            "K6" to listOf("SolarTerm"),
            // The Health screen's own readings (block 26), written in the shape: no allowance.
            "H1" to listOf("ElementQuarter", "GraveMansion", "LaElement", "LaHours", "SevenRedYear", "Humour", "MedicalSeason"),
        ).flatMap { (item, families) -> families.map { it to item } }.toMap()

        /**
         * The breaks of the shape each ROADMAP item's families still hold (ROADMAP X2): paragraphs under
         * 20 words or over 60, the app's or an editor's words, a Tibetan word named or bracketed two ways,
         * counted in English and Russian together. The block that builds an item deletes its line, one
         * line, and the test then holds its families to the shape. A count may only fall: the test fails
         * on a break beyond it and asks for a lower count when breaks are mended, so nothing new enters
         * the allowance. The 20-word floor joined the checks on 2026-10-11 with the paragraphs already
         * under it counted in, the only time the counts rose. Every line is gone since block 43
         * (2026-10-18), the last: no reading is allowed a break.
         */
        val ALLOWANCE: Map<String, Int> = emptyMap()
    }
}
