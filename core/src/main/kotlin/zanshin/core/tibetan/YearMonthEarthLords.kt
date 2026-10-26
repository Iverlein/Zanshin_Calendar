/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import java.time.LocalDate
import java.time.LocalTime
import zanshin.core.astro.Astro
import zanshin.core.tibetan.Animal.BIRD
import zanshin.core.tibetan.Animal.DOG
import zanshin.core.tibetan.Animal.DRAGON
import zanshin.core.tibetan.Animal.HORSE
import zanshin.core.tibetan.Animal.MONKEY
import zanshin.core.tibetan.Animal.MOUSE
import zanshin.core.tibetan.Animal.OX
import zanshin.core.tibetan.Animal.PIG
import zanshin.core.tibetan.Animal.RABBIT
import zanshin.core.tibetan.Animal.SHEEP
import zanshin.core.tibetan.Animal.SNAKE
import zanshin.core.tibetan.Animal.TIGER
import zanshin.core.tibetan.Direction.EAST
import zanshin.core.tibetan.Direction.NORTH
import zanshin.core.tibetan.Direction.NORTH_EAST
import zanshin.core.tibetan.Direction.NORTH_WEST
import zanshin.core.tibetan.Direction.SOUTH
import zanshin.core.tibetan.Direction.SOUTH_EAST
import zanshin.core.tibetan.Direction.SOUTH_WEST
import zanshin.core.tibetan.Direction.WEST

/** A place in or by the house where a month's earth lord stays, as ch. 31 names it. */
enum class HousePlace {
    CENTRE, DOOR, COURTYARD, HEARTH, HEARTHSTONES, UNDER_THE_THRESHOLD, UNDER_THE_HEARTH, UNDER_THE_HAND_MILL,
    AT_THE_EDGE_OF_THE_HOUSE, ROOF, PILLAR, DOOR_OF_THE_PROTECTOR_CHAPEL, OUTER_DOOR, INNER_DOOR, WALL_HOLE, WALL_AND_STONE,
    MAIN_STREET, BURIAL_GROUND, THE_LADDER_BEAM_AND_LANE,
}

/** Where a year's or month's earth lord stands. */
sealed interface LordPlace {
    /** The place of an animal of the twelve, a twelfth of the compass (`Animal.<A>.place`). */
    data class OnAnimal(val animal: Animal) : LordPlace

    /** One of the eight directions. */
    data class InDirection(val direction: Direction) : LordPlace

    /** A place in the house. */
    data class InHouse(val place: HousePlace) : LordPlace

    /** Moving from one direction to the other. */
    data class Along(val from: Direction, val to: Direction) : LordPlace

    /** No place: the whole year (the black years). */
    data object Whole : LordPlace
}

/** A lord's body part, where the source places the head, mouth or tail apart. */
enum class LordPart { HEAD, MOUTH, TAIL }

/**
 * One place a lord holds: [part] for a lord whose head and tail stand apart, [member] for a group whose members stand apart
 * (an index into the group's members, the catalog's `<Lord>.member.<n>`).
 */
data class LordSeat(val place: LordPlace, val part: LordPart? = null, val member: Int? = null)

/**
 * The year's earth lords the White Beryl's ch. 31 places by the year (vol. 2, pp. 177–197; docs/sources/year-month-earth-lords.md,
 * RD3), each with the WB inventory's id, its Wylie and its entry: the days after the winter solstice on which it takes the new
 * year's place (0 where WB names the solstice itself or no day).
 */
enum class YearLord(val id: String, val wylie: String, val entry: Int) {
    TESE("Y01", "the se rgyal po", 0), // The king, Tese, p. 177
    TEKHYIM("Y02", "the khyim", 5), // The great queen, Tekhyim, p. 178
    HARPHEN("Y03", "har phan", 0), // The king's consort, Harphen or Hangne, p. 178
    TESO("Y04", "te so", 4), // The king's son, Teso, p. 179
    TSANGKUN("Y05", "tsang kun", 12), // The king's minister, Tsangkun, p. 179
    SEBA_LAKHYEN("Y06", "se ba bla mkhyen", -12), // The reckoner, the life-spirit knower of the *se*, p. 180
    HANGPHEN("Y07", "hang phan ser po", -24), // The watchman, Hangphen the yellow, leader of the hal khyi, pp. 180–181
    SEBYI("Y08", "se byi", 8), // The king's treasure guardian, Sebyi, p. 181
    RANGTA("Y10", "se ba rang rta", -4), // The king's mount, Seba Rangta, p. 182
    TAKHRI("Y11", "se'u rta khrid", -4), // The earth lord Se'u who leads the horse, p. 182
    SEBYA("Y12", "se bya", -1), // The time-keeper, Sebya, pp. 182–183
    DENDING("Y13", "se ba gdan 'ding", -2), // The seat-spreader, Seba, p. 183
    KUNGNYUL("Y14", "se lo sa 'phyag kungs myul", -16), // The sweeper of the year, Kungnyul the inspector, p. 184
    SACHAK("Y15", "se lo sa 'phyag", -16), // The earth lord who sweeps the year, p. 184
    HAL_KHYI("Y16", "hal khyi nag po", 0), // The black hal khyi, the king's dog, p. 185
    KARSHAM("Y18", "dkar sham", 3), // The king's daughter, Karsham, p. 186
    BALTE("Y19", "sbal te", 0), // Her husband, Balte, p. 186
    LANGGING("Y20", "dmag gi lang ging dmar po", 0), // Langging the red, of war, p. 186
    TSANGKUN_GENERAL("Y21", "dmag dpon tsang kun bya ba", 16), // The general, Tsangkun the bird, pp. 186–187
    TSANGKUN_SPLITTER("Y22", "tsang kun 'phye bo", 16), // Tsangkun the splitter, the dark blue, p. 187
    TSANGKUN_DOG_HEADED("Y23", "tsang kun khyi mgo", 0), // Tsangkun the dog-headed, p. 187
    LAKHYEN_PEU("Y24", "bla mkhyen phe'u", 0), // The life-spirit knower, Phe'u, p. 187
    ZINPHUNG("Y25", "zin phung nag po", 0), // The earth lord Zinphung the black, p. 187
    EARTH_KINGS("Y27", "sa rgyal rje blon bcu gcig", 0), // The eleven earth kings, king and ministers, pp. 188–189
    BLACK_YEAR("Y28", "lo nag", 0), // The black year (the fanged black year; the earth lord Wanggi, pp. 189–191
    LESSER_BLACK_YEAR("Y29", "lo nag chung ngu", 0), // The lesser black year, p. 189
    NB_KING("Y31", "rgyal po mtshon thogs", 0), // The king who holds weapons, p. 191
    NB_KALA("Y32", "ka la ro zan", 124), // Kala the corpse-eater, p. 191
    NB_LAKHYEN("Y33", "sme ba bla mkhyen", -4), // The life-spirit knower of the sme ba, p. 191
    NB_ILLNESS_DEMON("Y36", "nad bdud mgo gcod", 16), // The illness demon who cuts heads, p. 191
    NB_TIGER_LEOPARD("Y37", "stag gzig sna 'dus", 8), // The tiger and leopard gathering at the nose, p. 191
    NB_LORD_OF_DEATH("Y38", "gshin rje zas 'phrog", 4), // The lord of death who steals food, p. 191
    NB_BLACK_ONE("Y39", "nag mo khyo med", 2), // The black one without a husband, pp. 191–192
    SME_1("Y40", "sa yi lha mo rab brtan ma", 0), // On the one-white: the earth goddess Rabtenma, p. 192
    SME_2("Y41", "bdud kyi rgyal po", 1), // On the two-black: the king of demons, p. 193
    SME_3("Y42", "sa bsen gdug byed", 2), // On the three-blue: Sasen the harmer, p. 193
    SME_4("Y43", "lha mo dal byed", 3), // On the four-green: the goddess Dalche, or the nāga king Varu, p. 193
    SME_5("Y44", "sa bdag rgyal po 'jig rten bdag", 4), // On the five-yellow: the earth lord king, lord of the world, p. 194
    SME_6("Y45", "rgyal po skye 'gro'i dpal", 5), // On the six-white: the king, glory of beings, p. 194
    SME_7("Y46", "btsan dmar chen po", 6), // On the seven-red: the great red btsan, p. 194
    SME_8("Y47", "lha chen dbang phyug che", 7), // On the eight-white: the great god, the great lord, p. 195
    SME_9("Y48", "ma mo dza mun ti", 8), // On the nine-red: the mother goddess Jamunti, p. 195
    HIDDEN("Y51", "gab pa'i sa bdag bcu gnyis", 0), // The hidden twelve, p. 196 (year), p. 221 (the month's)
    SKY_DOG("Y55", "gnam khyi", 0), // The sky dog's body, by the new Chinese reckoning (the year's, p. 197
    ;
}

/**
 * The month's earth lords of ch. 31 (vol. 2, pp. 199–223; RD4) that stand in a place for the whole month, each with its
 * inventory id and Wylie. They arise on the 1st of the month (p. 199).
 */
enum class MonthLord(val id: String, val wylie: String) {
    BAD_DIRECTION("M01", "ngan phyogs"), // The bad directions of each month, p. 199, img. 207
    DRAGON_GOD("M02", "dbus kyi 'brug lha ga pa"), // The dragon god of the centre, Gapa, p. 199, img. 207
    GRUMBU("M03", "the se grum bu"), // Grumbu of the the se spirits, god of the centre, pp. 199-200, img. 207-208
    MONTH_LUCK("M04", "zla klungs"), // The month's luck lord, p. 200, img. 208
    MONTH_ITSELF("M05", "zla dngos"), // The month itself, pp. 200-201, img. 208-209
    EARTH_KING("M06", "zla ba'i sa rgyal"), // The month's earth king, p. 201, img. 209
    RETINUE("M07", "lha bran"), // The earth king's retinue, the servant gods, pp. 201-202, img. 209-210
    SOUND_GOD("M09", "sgra lha phyir 'gro"), // The sound god who goes out, p. 202, img. 210
    EARTH_ARMY("M10", "sa dmag"), // The earth army, p. 202, img. 210
    EARTH_MEDICINE("M11", "sa sman"), // The earth medicine, pp. 202-203, img. 210-211
    TENMA("M12", "sa yi lha mo bstan ma"), // The earth goddess Tenma, p. 203, img. 211
    OLD_COUPLE("M13", "rgan rgon"), // The old couple, p. 203, img. 211
    DRAGON_LORD("M14", "'brug rje"), // The dragon lord, in Tibetan the inner god, pp. 204-205, img. 212-213
    PIG_HEADED_MOUSE_ROAD("M15", "byi lam phag mgo"), // The mouse road with the pig head, p. 205, img. 213
    BLUE_MOUSE_ROAD("M16", "byi lam sngon mo"), // The blue mouse road, pp. 205-206, img. 213-214
    DZA_ZOR("M17", "dza zor dmar po"), // Dza zor the red, p. 206, img. 214
    NINE_HEADED_WOLF("M18", "'phar ba mgo dgu"), // The nine-headed wolf, p. 206, img. 214
    SLAYER("M19", "srid gshed"), // The slayer of existence, p. 207, img. 215
    EARTH_DOGS("M20", "sa khyi dud kha pho"), // The two earth dogs: the smoke-coloured male, and the black f, p. 207, img. 215
    PILING("M21", "pi ling"), // Piling the poisonous, p. 207, img. 215
    ZINPHUNG("M22", "zin phung nag po"), // Zinphung the black, p. 207, img. 215
    NALBU("M24", "nal bu"), // Nalbu, pp. 207-208, img. 215-216
    EARTH_MOUSE_ROAD("M25", "sa yi byi lam"), // The earth's mouse road, p. 208, img. 216
    PILING_WOLF("M26", "pi ling 'phar ma"), // Piling the wolf, p. 208, img. 216
    GREAT_GOD_AND_GNYAN("M27", "lha chen"), // The great god and the great gnyan, p. 208, img. 216
    LEOPARD_TAIL("M28", "gzig mjug"), // The leopard's tail, p. 208, img. 216
    LEOPARD_SKIN("M29", "gzig slog"), // The leopard skin, p. 208, img. 216
    GUARD_DOG("M30", "sdom khyi"), // The guard dog, pp. 208-209, img. 216-217
    TSANGKUN("M31", "tsang kun"), // The month's minister Tsangkun, p. 209, img. 217
    NGAMPA("M32", "rngam pa phag mgo"), // Ngampa the pig-headed, p. 209, img. 217
    LAGCHEN("M33", "be sna lag chan"), // The calf-headed Lagchen, p. 209, img. 217
    FOUR_QUEENS("M34", "rgyal mo bzhi"), // The four queens, pp. 209-210, img. 217-218
    HEARTH_GOD("M35", "thab lha se shar"), // The month's hearth god, p. 210, img. 218
    UNCHANGING("M37", "sa bdag 'gyur med"), // The unchanging earth lord, the black belly-crawler, pp. 210-211, img. 218-219
    FIVE_GODS("M38", "grol bu'i lha lnga"), // The five gods of Grolbu, p. 211-212, img. 219-220 (month-entries.md, RD2; the rest year-mo
    NINE_GODS("M39", "lha dgu"), // The nine gods, pp. 211-212, img. 219-220
    EMPTY_VASE("M42", "bum stong"), // The empty vase, p. vol. 2, pp. 212-213, img. 220-221; vol. 1, p. 135, img. 145
    HAL_KHYI("M43", "zla ba'i hal khyi"), // The month's hal dog, pp. 213-214, img. 221-222
    BELLY_CRAWLER("M44", "lto 'phye"), // The belly-crawler, pp. 214-215, img. 222-223
    SKY_DOG("M45", "gnam khyi pho mo"), // The sky dogs, male and female, of the month, p. 215, img. 223 («གནམ(༦༠)ཁྱི(༦༡)གོང་བཤད་ཇི་བ
    NINE_BLACK("M46", "zla ba'i nag mo dgu skor"), // The month's nine black ones, p. 215, img. 223
    SE_DEMON("M48", "se bdud"), // The se demon, pp. 216-217, img. 224-225
    GRAVE_TAKER("M51", "dur len"), // The grave-taker, p. 217, img. 225
    SESHAR("M52", "thab lha se shar"), // The hearth god Seshar, pp. 217-218, img. 225-226
    KUKU("M55", "ku ku"), // Kuku, p. 218, img. 226
    BANNER("M56", "phyi skor ba dan khra bo"), // The speckled banner of the outer round, p. 219, img. 227
    HONGPHAN("M58", "gnam gyi bya khyung hong phan"), // The sky's garuḍa Hongphan, p. 219, img. 227
    SIX_GODS("M61", "gnam thig ser nag"), // The six gods of the yellow-black sky line (good), p. 220, img. 228
    SIX_BLACK("M62", "sribs kyi nag drug"), // The six black ones of the shadow (bad), pp. 220-221, img. 228-229
    HIDDEN("M63", "gab pa'i sa bdag bcu gnyis"), // The hidden twelve, p. 221, img. 229
    LHAGO("M64", "gnam gyi gza' chen lha rgod"), // The great planet Lhagö, pp. 221-222, img. 229-230
    STUMP_HAND("M68", "gcer bu lag rdum"), // The naked stump-hand, pp. 222-223, img. 230-231
    ;
}

/** A year's lord where it stands on a date: [year] is the Tibetan year (by its Losar's Gregorian year) whose place it holds, from [since]. */
data class YearStanding(val lord: YearLord, val year: Int, val since: LocalDate, val seats: List<LordSeat>)

/** A month's lord where it stands in the month. */
data class MonthStanding(val lord: MonthLord, val monthAnimal: Animal, val seats: List<LordSeat>)

/**
 * The earth lords of the year and the month (ROADMAP T2.26, SPEC §5.13): where each stands on a day. None of them is
 * weighed (D4): each bars its works in its place, with its remedy.
 */
object YearMonthEarthLords {
    private fun a(animal: Animal) = listOf(LordSeat(LordPlace.OnAnimal(animal)))
    private fun d(direction: Direction) = listOf(LordSeat(LordPlace.InDirection(direction)))
    private fun h(place: HousePlace) = listOf(LordSeat(LordPlace.InHouse(place)))
    private fun up(animal: Animal, n: Int) = Animal.entries[Math.floorMod(animal.ordinal + n, 12)]

    /** The animal of the Tibetan year whose Losar falls in Gregorian [year] (1984 the mouse). */
    fun yearAnimal(year: Int): Animal = Animal.entries[Math.floorMod(year - 1984, 12)]

    private val LHASA = java.time.ZoneOffset.ofHoursMinutesSeconds(6, 4, 24)
    private val solstices = java.util.concurrent.ConcurrentHashMap<Int, LocalDate>()

    /**
     * The day of the winter solstice of Gregorian [year] in Lhasa's mean solar time, the day at whose end the Sun has passed
     * 270°, as the day's sme ba reckons it ([DaySmeBa]).
     */
    fun winterSolstice(year: Int): LocalDate = solstices.getOrPut(year) {
        var d = LocalDate.of(year, 12, 15)
        while (Astro.sunApparentLongitudeDeg(Astro.jdeOf(d.plusDays(1).atTime(LocalTime.MIDNIGHT).toInstant(LHASA))) < 270.0) d = d.plusDays(1)
        d
    }

    /**
     * The Tibetan year whose place [lord] holds on [date]: the year that the last winter solstice opens, once [lord]'s entry
     * days after it have passed (question 34: the year's lords move at the solstice with the Chinese reckoning's year, not
     * at Losar; each on its own day).
     */
    fun lordYear(lord: YearLord, date: LocalDate): Pair<Int, LocalDate> {
        var k = date.year
        while (true) {
            val since = winterSolstice(k).plusDays(lord.entry.toLong())
            if (!date.isBefore(since)) return (k + 1) to since
            k--
        }
    }

    /** Where [lord] stands in Tibetan year [year], or an empty list where it does not stand that year (the black years). */
    fun seats(lord: YearLord, year: Int): List<LordSeat> {
        val y = yearAnimal(year)
        val mewa = YearOfLife.yearSmeBa(year)
        fun sme(n: Int) = d(DaySmeBa.directionOf(n, mewa))
        return when (lord) {
            YearLord.TESE, YearLord.HARPHEN, YearLord.DENDING, YearLord.NB_KING -> a(y)
            // p. 178: one year left between her and the king, behind him (WB's note on no. 12: "following" is the year behind).
            YearLord.TEKHYIM -> a(up(y, -2))
            YearLord.TESO, YearLord.NB_LORD_OF_DEATH -> a(EarthLordCourses.klungRta(y))
            YearLord.TSANGKUN -> when (y) {
                MOUSE -> a(BIRD)
                OX -> a(BIRD)
                TIGER -> a(MOUSE)
                RABBIT -> a(MOUSE)
                DRAGON -> a(MOUSE)
                SNAKE -> a(RABBIT)
                HORSE -> a(RABBIT)
                SHEEP -> a(RABBIT)
                MONKEY -> a(HORSE)
                BIRD -> a(HORSE)
                DOG -> a(HORSE)
                PIG -> a(BIRD)
            }
            // The outer place only (p. 180): the year's seventh; the inner and secret places are not reckoned from the text.
            YearLord.SEBA_LAKHYEN -> a(up(y, 6))
            YearLord.HANGPHEN, YearLord.NB_LAKHYEN -> when (y) {
                MOUSE -> a(DRAGON)
                OX -> a(OX)
                TIGER -> a(DOG)
                RABBIT -> a(SHEEP)
                DRAGON -> a(DRAGON)
                SNAKE -> a(OX)
                HORSE -> a(DOG)
                SHEEP -> a(SHEEP)
                MONKEY -> a(DRAGON)
                BIRD -> a(OX)
                DOG -> a(DOG)
                PIG -> a(SHEEP)
            }
            YearLord.SEBYI, YearLord.NB_TIGER_LEOPARD -> when (y) {
                MOUSE -> a(DOG)
                OX -> a(SHEEP)
                TIGER -> a(DRAGON)
                RABBIT -> a(OX)
                DRAGON -> a(DOG)
                SNAKE -> a(SHEEP)
                HORSE -> a(DRAGON)
                SHEEP -> a(OX)
                MONKEY -> a(DOG)
                BIRD -> a(SHEEP)
                DOG -> a(DRAGON)
                PIG -> a(OX)
            }
            YearLord.RANGTA, YearLord.TAKHRI -> a(up(y, 8))
            YearLord.SEBYA -> a(up(y, -1))
            YearLord.KUNGNYUL, YearLord.SACHAK -> a(up(y, -3))
            // On the previous year's wind-horse, with the king's daughter and her husband in one seat (pp. 185–186).
            YearLord.HAL_KHYI, YearLord.KARSHAM, YearLord.BALTE, YearLord.NB_KALA -> a(EarthLordCourses.klungRta(up(y, -1)))
            YearLord.LANGGING -> when (y) {
                MOUSE -> a(TIGER)
                OX -> a(SHEEP)
                TIGER -> a(RABBIT)
                RABBIT -> a(MOUSE)
                DRAGON -> a(MOUSE)
                SNAKE -> a(BIRD)
                HORSE -> a(MONKEY)
                SHEEP -> a(HORSE)
                MONKEY -> a(DRAGON)
                BIRD -> a(RABBIT)
                DOG -> a(OX)
                PIG -> a(SNAKE)
            }
            YearLord.TSANGKUN_GENERAL -> when (y) {
                MOUSE -> a(DOG)
                OX -> a(OX)
                TIGER -> a(OX)
                RABBIT -> a(OX)
                DRAGON -> a(DRAGON)
                SNAKE -> a(DRAGON)
                HORSE -> a(DRAGON)
                SHEEP -> a(SHEEP)
                MONKEY -> a(SHEEP)
                BIRD -> a(SHEEP)
                DOG -> a(DOG)
                PIG -> a(DOG)
            }
            YearLord.TSANGKUN_SPLITTER, YearLord.NB_ILLNESS_DEMON -> when (y) {
                MOUSE -> a(SHEEP)
                OX -> a(DOG)
                TIGER -> a(DOG)
                RABBIT -> a(DOG)
                DRAGON -> a(OX)
                SNAKE -> a(OX)
                HORSE -> a(OX)
                SHEEP -> a(DRAGON)
                MONKEY -> a(DRAGON)
                BIRD -> a(DRAGON)
                DOG -> a(SHEEP)
                PIG -> a(SHEEP)
            }
            YearLord.TSANGKUN_DOG_HEADED -> a(up(y, 3))
            // p. 187: by the year's group, the corner years two behind, the middle two ahead, the last on the seventh.
            YearLord.LAKHYEN_PEU -> when (y) {
                TIGER, MONKEY, PIG, SNAKE -> a(up(y, -2))
                MOUSE, HORSE, BIRD, RABBIT -> a(up(y, 2))
                OX, SHEEP, DOG, DRAGON -> a(up(y, 6))
            }
            YearLord.ZINPHUNG -> a(up(y, -5))
            YearLord.EARTH_KINGS -> when (y) {
                MOUSE -> a(MOUSE)
                OX -> a(RABBIT)
                TIGER -> a(SHEEP)
                RABBIT -> a(BIRD)
                DRAGON -> a(PIG)
                SNAKE -> a(DOG)
                HORSE -> a(TIGER)
                SHEEP -> a(MONKEY)
                MONKEY -> a(OX)
                BIRD -> a(HORSE)
                DOG -> a(DRAGON)
                PIG -> a(MOUSE)
            }
            // The black years are the years whose own sme ba is the two-black and the three-blue (pp. 189–191, question 40).
            YearLord.BLACK_YEAR -> if (mewa == 2) listOf(LordSeat(LordPlace.Whole)) else emptyList()
            YearLord.LESSER_BLACK_YEAR -> if (mewa == 3) listOf(LordSeat(LordPlace.Whole)) else emptyList()
            YearLord.NB_BLACK_ONE -> a(up(y, -2))
            YearLord.SME_1 -> sme(1)
            YearLord.SME_2 -> sme(2)
            YearLord.SME_3 -> sme(3)
            YearLord.SME_4 -> sme(4)
            YearLord.SME_5 -> sme(5)
            YearLord.SME_6 -> sme(6)
            YearLord.SME_7 -> sme(7)
            YearLord.SME_8 -> sme(8)
            YearLord.SME_9 -> sme(9)
            YearLord.HIDDEN -> a(y)
            // p. 197: the head on the year's place, the tail on the seventh.
            YearLord.SKY_DOG -> listOf(LordSeat(LordPlace.OnAnimal(y), LordPart.HEAD), LordSeat(LordPlace.OnAnimal(up(y, 6)), LordPart.TAIL))
        }
    }

    /** The year's lords as they stand on [date], each in the year its own entry has reached, in WB's order. */
    fun year(date: LocalDate): List<YearStanding> = YearLord.entries.mapNotNull { lord ->
        val (year, since) = lordYear(lord, date)
        seats(lord, year).takeIf { it.isNotEmpty() }?.let { YearStanding(lord, year, since, it) }
    }

    /** The members of each group lord, in WB's order: the catalog's `<Lord>.member.<n>`. */
    val MEMBERS: Map<MonthLord, Int> = mapOf(
        MonthLord.GREAT_GOD_AND_GNYAN to 2, MonthLord.NINE_BLACK to 9, MonthLord.SIX_GODS to 6, MonthLord.SIX_BLACK to 6,
    )

    private fun season(m: Animal): Int = Math.floorMod(m.ordinal - TIGER.ordinal, 12) / 3

    private fun bySeason(m: Animal, vararg dirs: Direction) = d(dirs[season(m)])

    /** The month's nine black ones (p. 215) by the month's animal: the members, in the year's order (`NineBlack.member.<n>`: the king, the corpse-eater, the *bla mkhyen*, the demon of births, the butcher, the demon of disease, the leopard, the lord of death, the black one), on their directions. */
    private val NINE_BLACK: Map<Animal, Map<Direction, List<Int>>> = mapOf(
        MOUSE to mapOf(EAST to listOf(0), SOUTH_EAST to listOf(8, 6), SOUTH to listOf(7), WEST to listOf(3, 4, 1), NORTH_WEST to listOf(5), NORTH_EAST to listOf(2)),
        OX to mapOf(EAST to listOf(8), SOUTH to listOf(2, 7), SOUTH_WEST to listOf(6), WEST to listOf(4, 3), NORTH to listOf(1), NORTH_EAST to listOf(0, 5)),
        TIGER to mapOf(EAST to listOf(1, 8, 7), SOUTH_EAST to listOf(5), SOUTH to listOf(3), WEST to listOf(2), NORTH_WEST to listOf(6), NORTH to listOf(0), NORTH_EAST to listOf(4)),
        RABBIT to mapOf(EAST to listOf(7, 2), SOUTH_EAST to listOf(4), SOUTH to listOf(1, 3, 5), NORTH_WEST to listOf(8, 0, 6)),
        DRAGON to mapOf(EAST to listOf(7), SOUTH_EAST to listOf(5, 6), SOUTH to listOf(3), SOUTH_WEST to listOf(4), WEST to listOf(1, 8), NORTH_WEST to listOf(0, 2)),
        SNAKE to mapOf(EAST to listOf(3), SOUTH to listOf(2), SOUTH_WEST to listOf(6, 5), WEST to listOf(0), NORTH_WEST to listOf(4), NORTH to listOf(1, 8, 7)),
        HORSE to mapOf(EAST to listOf(1, 3), SOUTH_EAST to listOf(5), SOUTH to listOf(2), WEST to listOf(0), NORTH_WEST to listOf(8, 6), NORTH to listOf(7), NORTH_EAST to listOf(4)),
        SHEEP to mapOf(EAST to listOf(3), SOUTH_EAST to listOf(4), SOUTH to listOf(1, 2), SOUTH_WEST to listOf(0), WEST to listOf(8, 5), NORTH to listOf(7), NORTH_EAST to listOf(6)),
        MONKEY to mapOf(SOUTH_EAST to listOf(6), SOUTH to listOf(0), SOUTH_WEST to listOf(4), WEST to listOf(1, 8), NORTH to listOf(3, 5, 2, 7)),
        BIRD to mapOf(EAST to listOf(5), SOUTH to listOf(0), SOUTH_WEST to listOf(8, 2, 6), WEST to listOf(7), NORTH_WEST to listOf(4), NORTH to listOf(1, 3)),
        DOG to mapOf(EAST to listOf(1), SOUTH to listOf(2, 8, 0), SOUTH_WEST to listOf(5), WEST to listOf(7), NORTH_WEST to listOf(6, 3), NORTH_EAST to listOf(4)),
        PIG to mapOf(EAST to listOf(0), SOUTH_EAST to listOf(4), SOUTH to listOf(1, 8, 2), SOUTH_WEST to listOf(5), WEST to listOf(3, 7), NORTH_EAST to listOf(6)),
    )

    /** The six good gods and the six black ones of the sky line (p. 220): by the pair of opposite months, each member on its animal. */
    private val SIX_GODS: Map<Set<Animal>, List<Animal>> = mapOf(
        setOf(MOUSE, HORSE) to listOf(BIRD, MOUSE, DRAGON, RABBIT, OX, HORSE),
        setOf(OX, SHEEP) to listOf(PIG, TIGER, HORSE, SNAKE, RABBIT, MONKEY),
        setOf(TIGER, MONKEY) to listOf(OX, DRAGON, MONKEY, SHEEP, SNAKE, DOG),
        setOf(BIRD, RABBIT) to listOf(RABBIT, HORSE, DOG, BIRD, SHEEP, MOUSE),
        setOf(DOG, DRAGON) to listOf(SNAKE, MONKEY, MOUSE, PIG, BIRD, TIGER),
        setOf(PIG, SNAKE) to listOf(SHEEP, DOG, TIGER, OX, PIG, DRAGON),
    )
    private val SIX_BLACK: Map<Set<Animal>, List<Animal>> = mapOf(
        setOf(MOUSE, HORSE) to listOf(MONKEY, DOG, PIG, TIGER, SNAKE, SHEEP),
        setOf(OX, SHEEP) to listOf(DOG, MOUSE, OX, DRAGON, SHEEP, BIRD),
        setOf(TIGER, MONKEY) to listOf(MOUSE, TIGER, RABBIT, HORSE, BIRD, PIG),
        setOf(BIRD, RABBIT) to listOf(TIGER, DRAGON, SNAKE, MONKEY, PIG, OX),
        setOf(DOG, DRAGON) to listOf(DRAGON, HORSE, SHEEP, DOG, OX, RABBIT),
        setOf(PIG, SNAKE) to listOf(HORSE, MONKEY, BIRD, MOUSE, RABBIT, SNAKE),
    )

    private fun members(table: Map<Set<Animal>, List<Animal>>, m: Animal) =
        table.entries.first { m in it.key }.value.mapIndexed { i, a -> LordSeat(LordPlace.OnAnimal(a), member = i) }

    /** The times of day and the line the se demon moves along in each month (pp. 216–217). */
    val SE_DEMON_LINE: Map<Animal, Pair<Direction, Direction>> = mapOf(
        TIGER to (SOUTH to NORTH), RABBIT to (SOUTH to NORTH), DRAGON to (SOUTH_WEST to NORTH_EAST), SNAKE to (WEST to NORTH_EAST),
        HORSE to (WEST to EAST), SHEEP to (NORTH_WEST to SOUTH_EAST), MONKEY to (NORTH to SOUTH), BIRD to (NORTH to SOUTH),
        DOG to (NORTH_EAST to SOUTH_WEST), PIG to (EAST to WEST), MOUSE to (WEST to EAST), OX to (SOUTH to NORTH),
    )

    /** Where [lord] stands in a month whose animal is [m], or an empty list where it has no seat that month. */
    fun seats(lord: MonthLord, m: Animal): List<LordSeat> = when (lord) {
        MonthLord.BAD_DIRECTION -> when (m) {
            TIGER -> d(NORTH_WEST)
            RABBIT -> d(SOUTH_EAST)
            DRAGON -> d(WEST)
            SNAKE -> d(SOUTH)
            HORSE -> d(NORTH_EAST)
            SHEEP -> d(EAST)
            MONKEY -> d(SOUTH_EAST)
            BIRD -> d(NORTH_WEST)
            DOG -> d(NORTH)
            PIG -> d(NORTH_WEST)
            MOUSE -> d(SOUTH_WEST)
            OX -> d(WEST)
        }
        // The house's quarters by the season (p. 199).
        MonthLord.DRAGON_GOD -> bySeason(m, EAST, SOUTH, WEST, NORTH)
        MonthLord.GRUMBU -> when (m) {
            TIGER -> d(EAST)
            RABBIT -> a(DRAGON)
            DRAGON -> h(HousePlace.UNDER_THE_THRESHOLD)
            SNAKE -> d(SOUTH)
            HORSE -> a(SHEEP)
            SHEEP -> h(HousePlace.UNDER_THE_HEARTH)
            MONKEY -> d(WEST)
            BIRD -> a(DOG)
            DOG -> h(HousePlace.UNDER_THE_HAND_MILL)
            PIG -> d(NORTH)
            MOUSE -> a(OX)
            OX -> h(HousePlace.AT_THE_EDGE_OF_THE_HOUSE)
        }
        MonthLord.MONTH_LUCK -> a(EarthLordCourses.klungRta(m))
        MonthLord.MONTH_ITSELF, MonthLord.EARTH_DOGS, MonthLord.LEOPARD_SKIN, MonthLord.NGAMPA, MonthLord.KUKU, MonthLord.HIDDEN -> a(m)
        MonthLord.EARTH_KING -> when (m) {
            TIGER -> d(NORTH)
            RABBIT -> d(NORTH_EAST)
            DRAGON -> d(EAST)
            SNAKE -> d(SOUTH_EAST)
            HORSE -> d(SOUTH)
            SHEEP -> d(SOUTH_WEST)
            MONKEY -> d(WEST)
            BIRD -> d(NORTH_WEST)
            DOG -> d(NORTH)
            PIG -> a(OX)
            MOUSE -> d(EAST)
            OX -> a(DRAGON)
        }
        MonthLord.RETINUE -> when (m) {
            TIGER -> d(EAST)
            RABBIT -> d(SOUTH)
            DRAGON -> d(WEST)
            SNAKE -> d(NORTH)
            HORSE -> d(EAST)
            SHEEP -> d(SOUTH)
            MONKEY -> d(WEST)
            BIRD -> d(NORTH)
            DOG -> d(EAST)
            PIG -> d(SOUTH)
            MOUSE -> d(WEST)
            OX -> d(NORTH)
        }
        MonthLord.SOUND_GOD -> when (m) {
            TIGER -> a(TIGER)
            RABBIT -> a(PIG)
            DRAGON -> a(MONKEY)
            SNAKE -> a(BIRD)
            HORSE -> a(DRAGON)
            SHEEP -> a(RABBIT)
            MONKEY -> a(MOUSE)
            BIRD -> a(HORSE)
            DOG -> a(DRAGON)
            PIG -> a(OX)
            MOUSE -> a(DOG)
            OX -> a(SHEEP)
        }
        MonthLord.EARTH_ARMY -> when (m) {
            TIGER -> a(OX)
            RABBIT -> a(DRAGON)
            DRAGON -> a(SHEEP)
            SNAKE -> a(DOG)
            HORSE -> a(HORSE) + a(SNAKE)
            SHEEP -> a(MOUSE) + a(PIG)
            MONKEY -> a(TIGER) + a(RABBIT)
            BIRD -> a(BIRD) + a(MONKEY)
            DOG -> a(DRAGON)
            PIG -> a(SHEEP)
            MOUSE -> a(DOG)
            OX -> a(OX)
        }
        MonthLord.EARTH_MEDICINE, MonthLord.PILING -> a(up(m, 6))
        MonthLord.TENMA -> when (m) {
            TIGER -> a(MOUSE)
            RABBIT -> a(BIRD)
            DRAGON -> a(SNAKE)
            SNAKE -> a(TIGER)
            HORSE -> a(HORSE)
            SHEEP -> a(MONKEY)
            MONKEY -> a(RABBIT)
            BIRD -> a(SHEEP)
            DOG -> a(PIG)
            PIG -> a(DRAGON)
            MOUSE -> a(DOG)
            OX -> a(OX)
        }
        MonthLord.OLD_COUPLE -> when (m) {
            TIGER -> d(WEST)
            RABBIT -> d(WEST)
            DRAGON -> d(EAST)
            SNAKE -> d(EAST)
            HORSE -> d(SOUTH)
            SHEEP -> d(SOUTH)
            MONKEY -> d(NORTH)
            BIRD -> d(NORTH)
            DOG -> d(WEST)
            PIG -> d(SOUTH)
            MOUSE -> d(EAST)
            OX -> d(NORTH)
        }
        MonthLord.DRAGON_LORD -> when (m) {
            TIGER -> h(HousePlace.CENTRE)
            RABBIT -> h(HousePlace.CENTRE)
            DRAGON -> h(HousePlace.DOOR)
            SNAKE -> h(HousePlace.DOOR)
            HORSE -> h(HousePlace.COURTYARD)
            SHEEP -> d(SOUTH_WEST)
            MONKEY -> h(HousePlace.COURTYARD)
            BIRD -> h(HousePlace.COURTYARD)
            DOG -> h(HousePlace.HEARTHSTONES)
            PIG -> h(HousePlace.HEARTHSTONES)
            MOUSE -> h(HousePlace.HEARTH)
            OX -> h(HousePlace.HEARTH)
        }
        MonthLord.PIG_HEADED_MOUSE_ROAD -> when (m) {
            TIGER -> d(NORTH)
            RABBIT -> d(SOUTH_EAST)
            DRAGON -> d(SOUTH_WEST)
            SNAKE -> d(SOUTH)
            HORSE -> d(NORTH)
            SHEEP -> d(NORTH_EAST)
            MONKEY -> d(SOUTH)
            BIRD -> d(NORTH)
            DOG -> d(EAST)
            PIG -> d(NORTH)
            MOUSE -> d(SOUTH_WEST)
            OX -> d(NORTH)
        }
        MonthLord.BLUE_MOUSE_ROAD -> when (m) {
            TIGER -> d(EAST)
            RABBIT -> d(EAST)
            DRAGON -> a(HORSE)
            SNAKE -> a(SHEEP)
            HORSE -> d(SOUTH)
            SHEEP -> a(RABBIT)
            MONKEY -> a(DRAGON)
            BIRD -> a(PIG)
            DOG -> a(MOUSE)
            PIG -> a(OX)
            MOUSE -> a(BIRD)
            OX -> a(BIRD)
        }
        MonthLord.DZA_ZOR -> when (m) {
            TIGER -> a(DOG)
            RABBIT -> a(SNAKE)
            DRAGON -> a(HORSE)
            SNAKE -> a(HORSE)
            HORSE -> a(TIGER)
            SHEEP -> a(OX)
            MONKEY -> a(OX)
            BIRD -> a(SHEEP)
            DOG -> a(SHEEP)
            PIG -> a(SHEEP)
            MOUSE -> a(MONKEY)
            OX -> a(RABBIT)
        }
        MonthLord.NINE_HEADED_WOLF -> when (m) {
            TIGER -> a(MONKEY)
            RABBIT -> a(SNAKE)
            DRAGON -> a(MONKEY)
            SNAKE -> a(TIGER)
            HORSE -> a(MONKEY)
            SHEEP -> a(PIG)
            MONKEY -> a(RABBIT)
            BIRD -> a(SHEEP)
            DOG -> a(DOG)
            PIG -> a(MONKEY)
            MOUSE -> a(DRAGON)
            OX -> a(BIRD)
        }
        // The four-slayer counted downward (p. 207).
        MonthLord.SLAYER -> a(up(m, 9))
        // The four-slayer counted upward (p. 207, p. 209).
        MonthLord.ZINPHUNG, MonthLord.TSANGKUN -> a(up(m, 3))
        MonthLord.NALBU -> when (m) {
            TIGER -> null
            RABBIT -> null
            DRAGON -> null
            SNAKE -> h(HousePlace.ROOF)
            HORSE -> h(HousePlace.PILLAR)
            SHEEP -> h(HousePlace.DOOR_OF_THE_PROTECTOR_CHAPEL)
            MONKEY -> h(HousePlace.OUTER_DOOR)
            BIRD -> h(HousePlace.INNER_DOOR)
            DOG -> h(HousePlace.WALL_HOLE)
            PIG -> h(HousePlace.PILLAR)
            MOUSE -> h(HousePlace.HEARTHSTONES)
            OX -> h(HousePlace.WALL_AND_STONE)
        } ?: emptyList()
        MonthLord.EARTH_MOUSE_ROAD -> when (m) {
            TIGER -> d(NORTH_WEST)
            RABBIT -> d(SOUTH_EAST)
            DRAGON -> d(SOUTH_WEST)
            SNAKE -> d(SOUTH)
            HORSE -> d(NORTH_EAST)
            SHEEP -> d(EAST)
            MONKEY -> d(SOUTH)
            BIRD -> d(NORTH_WEST)
            DOG -> d(NORTH)
            PIG -> d(NORTH_EAST)
            MOUSE -> d(SOUTH_WEST)
            OX -> d(WEST)
        }
        MonthLord.PILING_WOLF -> when (m) {
            TIGER -> a(DOG)
            RABBIT -> a(SNAKE)
            DRAGON -> a(HORSE)
            SNAKE -> a(SHEEP)
            HORSE -> a(TIGER)
            SHEEP -> a(RABBIT)
            MONKEY -> a(DRAGON)
            BIRD -> a(PIG)
            DOG -> a(MOUSE)
            PIG -> a(OX)
            MOUSE -> a(MONKEY)
            OX -> a(BIRD)
        }
        MonthLord.GREAT_GOD_AND_GNYAN -> listOf(
            LordSeat(LordPlace.InDirection(listOf(NORTH, EAST, SOUTH, WEST)[season(m)]), member = 0),
            LordSeat(LordPlace.InDirection(listOf(EAST, SOUTH, WEST, NORTH)[season(m)]), member = 1),
        )
        MonthLord.LEOPARD_TAIL -> when (m) {
            TIGER -> d(NORTH)
            RABBIT -> d(NORTH_EAST)
            DRAGON -> d(EAST)
            SNAKE -> d(SOUTH_EAST)
            HORSE -> d(SOUTH)
            SHEEP -> d(SOUTH_WEST)
            MONKEY -> d(WEST)
            BIRD -> d(NORTH_WEST)
            DOG -> d(NORTH)
            PIG -> h(HousePlace.DOOR)
            MOUSE -> h(HousePlace.MAIN_STREET)
            OX -> h(HousePlace.BURIAL_GROUND)
        }
        // In the lower quarter of the direction (p. 208), in four months only.
        MonthLord.GUARD_DOG -> when (m) {
            TIGER -> null
            RABBIT -> d(NORTH)
            DRAGON -> null
            SNAKE -> null
            HORSE -> d(EAST)
            SHEEP -> null
            MONKEY -> null
            BIRD -> d(SOUTH)
            DOG -> null
            PIG -> null
            MOUSE -> d(WEST)
            OX -> null
        } ?: emptyList()
        // No seat in the horse month, in both prints (p. 209).
        MonthLord.LAGCHEN -> when (m) {
            MOUSE, DRAGON, MONKEY -> a(RABBIT)
            TIGER, DOG -> a(PIG)
            HORSE -> null
            BIRD, OX, SNAKE -> a(SHEEP)
            PIG, SHEEP, RABBIT -> a(OX)
        } ?: emptyList()
        MonthLord.FOUR_QUEENS, MonthLord.HEARTH_GOD -> bySeason(m, EAST, SOUTH, WEST, NORTH)
        // Head and mouth by the season (pp. 210–211).
        MonthLord.UNCHANGING -> listOf(
            LordSeat(LordPlace.InDirection(listOf(SOUTH, WEST, NORTH, EAST)[season(m)]), LordPart.HEAD),
            LordSeat(LordPlace.InDirection(listOf(WEST, NORTH, EAST, SOUTH)[season(m)]), LordPart.MOUTH),
        )
        MonthLord.FIVE_GODS -> when (m) {
            TIGER, HORSE, DOG -> d(EAST)
            PIG, SHEEP, RABBIT -> d(SOUTH)
            MOUSE, DRAGON, MONKEY -> d(WEST)
            BIRD, OX, SNAKE -> d(NORTH)
        }
        MonthLord.NINE_GODS -> when (m) {
            TIGER, HORSE, DOG -> d(WEST)
            PIG, SHEEP, RABBIT -> d(SOUTH)
            MOUSE, DRAGON, MONKEY -> d(EAST)
            BIRD, OX, SNAKE -> d(NORTH)
        }
        // By the month's animal (p. 212, the way of the *gtsug lag*), one direction for the month, as the model almanac writes it.
        MonthLord.EMPTY_VASE -> when (m) {
            TIGER, HORSE, DOG -> d(EAST)
            PIG, SHEEP, RABBIT -> d(SOUTH)
            MOUSE, DRAGON, MONKEY -> d(WEST)
            BIRD, OX, SNAKE -> d(NORTH)
        }
        // On the wind-horse of the month before (p. 213), as the year's hal khyi on the year before's.
        MonthLord.HAL_KHYI -> a(EarthLordCourses.klungRta(up(m, -1)))
        // The tail on the month's place, the head on the seventh (p. 214).
        MonthLord.BELLY_CRAWLER -> listOf(LordSeat(LordPlace.OnAnimal(up(m, 6)), LordPart.HEAD), LordSeat(LordPlace.OnAnimal(m), LordPart.TAIL))
        // The head on the month's place, the tail on the seventh (p. 215, by the year's rule of p. 197).
        MonthLord.SKY_DOG -> listOf(LordSeat(LordPlace.OnAnimal(m), LordPart.HEAD), LordSeat(LordPlace.OnAnimal(up(m, 6)), LordPart.TAIL))
        MonthLord.NINE_BLACK -> NINE_BLACK.getValue(m).flatMap { (dir, ms) -> ms.map { LordSeat(LordPlace.InDirection(dir), member = it) } }
        MonthLord.SE_DEMON -> SE_DEMON_LINE.getValue(m).let { (from, to) -> listOf(LordSeat(LordPlace.Along(from, to))) }
        // The head, by the season of three counted from the horse (p. 217).
        MonthLord.GRAVE_TAKER -> when (m) {
            HORSE, SHEEP, MONKEY -> a(SHEEP)
            BIRD, DOG, PIG -> a(DOG)
            MOUSE, OX, TIGER -> a(OX)
            RABBIT, DRAGON, SNAKE -> a(DRAGON)
        }.map { it.copy(part = LordPart.HEAD) }
        MonthLord.SESHAR -> when (m) {
            TIGER -> d(SOUTH)
            RABBIT -> d(SOUTH_WEST)
            DRAGON -> d(WEST)
            SNAKE -> d(NORTH)
            HORSE -> d(EAST)
            SHEEP -> d(WEST)
            MONKEY -> d(NORTH_WEST)
            BIRD -> d(WEST)
            DOG -> d(NORTH)
            PIG -> d(SOUTH)
            MOUSE -> h(HousePlace.THE_LADDER_BEAM_AND_LANE)
            OX -> h(HousePlace.UNDER_THE_HEARTH)
        }
        MonthLord.BANNER -> when (m) {
            TIGER, HORSE, DOG -> a(SNAKE)
            PIG, SHEEP, RABBIT -> a(MONKEY)
            MOUSE, DRAGON, MONKEY -> a(PIG)
            BIRD, OX, SNAKE -> a(TIGER)
        }
        MonthLord.HONGPHAN -> when (m) {
            TIGER, HORSE, DOG -> a(PIG)
            PIG, SHEEP, RABBIT -> a(BIRD)
            MOUSE, DRAGON, MONKEY -> a(HORSE)
            BIRD, OX, SNAKE -> a(DOG)
        }
        MonthLord.SIX_GODS -> members(SIX_GODS, m)
        MonthLord.SIX_BLACK -> members(SIX_BLACK, m)
        MonthLord.LHAGO -> listOf(
            LordSeat(LordPlace.InDirection(listOf(WEST, NORTH, EAST, SOUTH)[season(m)]), LordPart.HEAD),
            LordSeat(LordPlace.InDirection(listOf(NORTH, EAST, SOUTH, WEST)[season(m)]), LordPart.MOUTH),
        )
        MonthLord.STUMP_HAND -> listOf(LordSeat(LordPlace.Along(listOf(EAST, SOUTH, WEST, NORTH)[season(m)], listOf(WEST, NORTH, EAST, SOUTH)[season(m)])))
    }

    /** The month's lords of [day]'s month, in WB's order. */
    fun month(day: TibetanDay): List<MonthStanding> = month(day.monthNames.animal)

    fun month(m: Animal): List<MonthStanding> = MonthLord.entries.mapNotNull { lord ->
        seats(lord, m).takeIf { it.isNotEmpty() }?.let { MonthStanding(lord, m, it) }
    }
}
