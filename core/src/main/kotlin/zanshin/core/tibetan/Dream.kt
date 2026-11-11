/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZonedDateTime
import zanshin.core.astro.Place
import zanshin.core.astro.SunTimes
import zanshin.core.kyureki.Tone
import zanshin.core.texts.gloss

/*
 * The dream reading (ROADMAP S2, block 73; SPEC §5.9.11): a reckoning with inputs, not an election. What was dreamt
 * (the images, ticked from the catalogue of docs/sources/claims/dream-catalogue.md) and when (the part of the night)
 * give each image's verdict by each text that reads it; nothing is summed, since no text sums images
 * (claims/dream-mixed-order.md), and no fruit date or span is given (the owner, 2026-11-10).
 */

/**
 * The part of the night a dream came in, the one time rule the sources give (ROADMAP S2): dusk (སྲོད, srod), whose
 * dream comes of habit; the middle of the night (ནམ་ཕྱེད, nam phyed), whose dream is the demons' display and is not
 * trusted (Marpa's song, rrd.23:p102-104, rrd.26:p88-90; Karma chags med, rrd.19:p271); and dawn (ཐོ་རངས, tho rangs),
 * the one dream read (the Mañjuśrī root tantra's fourth watch, mmk.1:p397-399; Lama Gongdü, lgd:p966-967; Klong
 * rdol's three thirds, rst:p1-8). Only the dawn dream counts (the owner, 2026-11-10).
 */
enum class DreamWatch {
    DUSK, MIDDLE, DAWN;

    val english: String get() = gloss(this)

    /** Whether a dream of this part of the night is read image by image. */
    val read: Boolean get() = this == DAWN
}

/**
 * The night from [sunset] to [sunrise], cut in four equal quarters (a stated choice: Shes rab 'od zer cuts the night
 * «in four equal parts», snz:p428-429, and no source bounds a watch by the clock, claims/night-watches.md): the first
 * quarter dusk, the second and third the middle of the night, the last dawn.
 */
data class DreamNight(val sunset: ZonedDateTime, val sunrise: ZonedDateTime) {
    init { require(sunrise > sunset) { "a night ends after it begins" } }

    private val length: Duration get() = Duration.between(sunset, sunrise)

    /**
     * The start of quarter [n], 0 to 4: sunset, the three inner bounds, sunrise. An inner bound is taken up to the next
     * whole minute, so that the clock time the screen shows for it is the first minute of its quarter.
     */
    fun quarter(n: Int): ZonedDateTime {
        require(n in 0..4)
        if (n == 0) return sunset
        if (n == 4) return sunrise
        val exact = sunset.plus(length.multipliedBy(n.toLong()).dividedBy(4))
        val minute = exact.withSecond(0).withNano(0)
        return if (minute == exact) minute else minute.plusMinutes(1)
    }

    /** The part of the night [time] falls in, or null before sunset or from sunrise on. */
    fun watch(time: ZonedDateTime): DreamWatch? = when {
        time < sunset || time >= sunrise -> null
        time < quarter(1) -> DreamWatch.DUSK
        time < quarter(3) -> DreamWatch.MIDDLE
        else -> DreamWatch.DAWN
    }

    /** The moment the clock reads [time] in this night: from sunset to midnight on the evening's date, after it on the morning's. */
    fun at(time: LocalTime): ZonedDateTime {
        val evening = sunset.withHour(time.hour).withMinute(time.minute).withSecond(0).withNano(0)
        return if (time >= sunset.toLocalTime().withSecond(0).withNano(0)) evening
        else sunrise.withHour(time.hour).withMinute(time.minute).withSecond(0).withNano(0)
    }

    companion object {
        /**
         * The night before [date] at [place]: from the sunset of the day before to the sunrise of [date], since the
         * Tibetan day turns at dawn and last night belongs to today (SPEC §3). Null where the sun does not set or
         * rise on those days (polar day or night).
         */
        fun before(date: LocalDate, place: Place): DreamNight? {
            val sunset = SunTimes.of(date.minusDays(1), place).sunset ?: return null
            val sunrise = SunTimes.of(date, place).sunrise ?: return null
            return if (sunrise > sunset) DreamNight(sunset, sunrise) else null
        }
    }
}

/**
 * The texts that read dream images, in the order the screen shows them (claims/dream-catalogue.md, *Order inside a
 * claim*; `.agents/rules/sources.md`): WB ch. 30 first, then the Four Tantras for a sick dreamer ([medical]: shown only
 * when the dreamer is ill; they stand above WB on medicine, and WB ch. 25's two readings of a sick person's dream are
 * the same dreams as theirs, so theirs alone are shown, claims/dream-st-above-wb25.md), then the rank-3 texts no source
 * outranks: the Lama Gongdü's signs (LGD), the Svapnohana (SVO), the Svapna-īkṣā (SVI), the method under Sakya
 * Paṇḍita's name (SKP), Klong rdol bla ma's verses (RST, with KBS's quotation of them), the 'Bri gung extended method
 * (RRT) and Karma chags med's Magic Mirror (KCM).
 */
enum class DreamText {
    WB, ST, LGD, SVO, SVI, SKP, KLONG, RRT, KCM;

    val medical: Boolean get() = this == ST
}

/** The groups the picker shows the images in, as the catalogue groups them (claims/dream-catalogue.md); [ILL] only when the dreamer is ill. */
enum class DreamGroup {
    SKY, DRESS, JOURNEY, WATER, WEALTH, PEOPLE, PLACES, BODY, HOLY, ILL;

    val english: String get() = gloss(this)
}

/** One text's verdict on one image: [wbSign] is WB's moment sign whose reading is shown (`MomentSign.DREAM.<sign>`), for [DreamText.WB] only. */
data class DreamVerdict(val image: DreamImage, val text: DreamText, val tone: Tone, val wbSign: String? = null) {
    /** The catalog key of the verdict's text: WB's moment sign's reading, reused, or the image's own row. */
    val key: String get() = if (wbSign != null) "reading.MomentSign.DREAM.$wbSign" else "DreamVerdict.${image.name}.${text.name}"
}

/**
 * The dream images (claims/dream-catalogue.md and the claim of each image, `claims/dream-<name>.md`; RD216, RD274),
 * each with the texts that read it: [spec] lists them as `<text><tone>`, the tone `+` good, `-` bad, `~` mixed or
 * turning, `.` a sign only, a text's several rows on one image read as one; [wb] the WB ch. 30 signs that hold the
 * image (`ObservedSigns`' DREAM group, never copied). Left out, as stated choices: an image or a text's row whose
 * verdict rests on an unsettled word (claims/dream-unsettled-words.md), as the moment's signs leave them out (SPEC
 * §5.9.5); the pregnant woman's dream of a man, a sign of a boy, since the screen asks nothing of a pregnancy; and two
 * rows of the sick that are rules rather than images (any dream of the sick as a distant sign of death, which is the
 * Four Tantras' remedy row; WB ch. 25's shapes poured into a skull, a rite of the illness reckoning).
 */
enum class DreamImage(val group: DreamGroup, private val spec: String, vararg val wb: String) {
    SUN_RISING(DreamGroup.SKY, "LGD+ KLONG+", "LIGHT"),
    SUN_MOON_SEEN(DreamGroup.SKY, "SVO+ SVI. SKP+ KLONG+"),
    SUN_MOON_DARKENED(DreamGroup.SKY, "ST- LGD- SKP-", "DARKENING"),
    DARKNESS_THUNDER_HAIL(DreamGroup.SKY, "ST- LGD- KLONG-", "DARKENING"),
    MIRROR_SNOW_CRYSTAL(DreamGroup.SKY, "SVI.", "LIGHT"),
    MIND_CLEAR_GLAD(DreamGroup.SKY, "KLONG~", "LIGHT"),
    ADORNED_FINE_CLOTHES(DreamGroup.DRESS, "LGD~ SVI. SKP+ KLONG+", "ADORNED"),
    BANNER_FORT_GODS_WORSHIPPED(DreamGroup.DRESS, "LGD+ SVI. SKP+", "ADORNED"),
    BANNER_ON_OWN_HOUSE(DreamGroup.DRESS, "LGD~"),
    HONOURED_BY_GODS_AND_PARENTS(DreamGroup.DRESS, "LGD+ SKP+", "ADORNED"),
    THRONE(DreamGroup.DRESS, "SVI. SKP+ KLONG+"),
    HAT_TURBAN(DreamGroup.DRESS, "LGD~ KLONG~"),
    ARMOUR(DreamGroup.DRESS, "SVI- SKP+ KLONG+"),
    RIDING_UPWARD(DreamGroup.JOURNEY, "LGD+ SVO+ SVI. SKP+ KLONG+", "RIDING"),
    RIDING_DONKEY_BARE(DreamGroup.JOURNEY, "LGD- SVO- KLONG-", "FALLING"),
    MUSIC_SONG_CONCH(DreamGroup.JOURNEY, "LGD+ SVI. SKP- KLONG+", "MUSIC"),
    PLEASANT_WORDS_RETINUE(DreamGroup.JOURNEY, "SVO+", "MUSIC"),
    ROAD_JOURNEY(DreamGroup.JOURNEY, "LGD~ SVO+ SVI- KLONG-"),
    WANDERING_ALONE_PLAIN(DreamGroup.JOURNEY, "LGD- KLONG-"),
    RIVER_CROSSED_BOAT_BRIDGE(DreamGroup.WATER, "LGD~ SVO+ SVI. SKP+ KLONG+"),
    WATER_RAIN_SWIMMING(DreamGroup.WATER, "ST- LGD~ SVI. KLONG+"),
    FLYING(DreamGroup.WATER, "LGD~ SVI. KLONG+"),
    BATHING(DreamGroup.WATER, "LGD+ SVI. RRT~"),
    TREASURE_WEALTH_FOUND(DreamGroup.WEALTH, "ST- LGD+ SVI. SKP+ KLONG~", "GAIN"),
    GRAIN_HARVEST_FRUIT(DreamGroup.WEALTH, "LGD~ SKP+ KLONG+", "HIGH_WORK"),
    EATING_FOOD(DreamGroup.WEALTH, "LGD~ SVO+"),
    MILK_CURD_SWEET_WATER(DreamGroup.WEALTH, "SVO+ KLONG+"),
    BEER_WINE_BLOOD_DRUNK(DreamGroup.WEALTH, "ST- SVO+ SKP+ KLONG-"),
    VESSELS_FULL_EMPTY(DreamGroup.WEALTH, "LGD+ KLONG-"),
    BUTTER_OIL_SMEARED(DreamGroup.WEALTH, "LGD~ SVO-"),
    EATING_FLESH_OWN_BODY(DreamGroup.WEALTH, "LGD~ SVO+ RRT~"),
    FRIEND_MET(DreamGroup.PEOPLE, "LGD+ SKP+", "GAIN"),
    CONTEST_DICE_DEBATE(DreamGroup.PEOPLE, "ST- LGD~", "GAIN", "DEFEAT"),
    ENEMIES_SUBDUED_WEAPON_IN_HAND(DreamGroup.PEOPLE, "LGD+ SVI. KLONG+", "GAIN"),
    STRUCK_BY_WEAPONS(DreamGroup.PEOPLE, "LGD- SVI- KLONG-", "DEFEAT"),
    LAWSUIT_CREDITORS_QUARREL(DreamGroup.PEOPLE, "LGD-", "DEFEAT"),
    IMPRISONED_BOUND(DreamGroup.PEOPLE, "LGD- SVO+ SVI- KLONG-"),
    LED_OFF_BY_THE_DISLIKED(DreamGroup.PEOPLE, "LGD-"),
    HIGH_PLACE_MOUNTAIN(DreamGroup.PLACES, "SVO+ SVI. SKP~ KLONG+", "HIGH_WORK"),
    TREES(DreamGroup.PLACES, "LGD~ SVO+ SKP+ KLONG+"),
    PIT_FALL_CHASM(DreamGroup.PLACES, "ST- LGD- SKP- KLONG-", "FALLING"),
    LANDSLIDE(DreamGroup.PLACES, "LGD-"),
    HOUSE_FALLEN(DreamGroup.PLACES, "ST- LGD- SKP-", "HOUSE"),
    HOUSE_BURNING(DreamGroup.PLACES, "SVO+ SKP-", "HOUSE"),
    FIRE_BLAZE(DreamGroup.PLACES, "LGD~ SVO- SVI. SKP- KLONG- RRT~"),
    STUPA_TEMPLE(DreamGroup.PLACES, "SVI. SKP~ KLONG-"),
    GROUND_SHAKING(DreamGroup.PLACES, "SVO-"),
    POND_CHANNEL(DreamGroup.PLACES, "LGD+"),
    RINGED_BY_IRON_MOUNTAIN(DreamGroup.PLACES, "LGD+"),
    NAKED(DreamGroup.BODY, "ST- SVI. SKP- KLONG- RRT~", "MOURNING", "FALLING"),
    BODY_FAILING(DreamGroup.BODY, "ST- LGD- SKP-", "BODY"),
    WEEPING_AND_SORROW(DreamGroup.BODY, "SVI- KLONG-", "MOURNING"),
    BLACK_AND_WHITE_THINGS(DreamGroup.BODY, "SVO- SKP-", "MOURNING"),
    MIND_SINKING_DEMON(DreamGroup.BODY, "LGD~ KLONG-", "MIND"),
    CREATURES_ON_BODY(DreamGroup.BODY, "ST- LGD- SVI- KLONG-"),
    BEASTS_CHASING(DreamGroup.BODY, "ST- LGD- SVO- SVI- SKP- KLONG-"),
    SNAKE_FROG_WATER_CREATURE(DreamGroup.BODY, "ST- SVI. SKP- KLONG."),
    BIRDS(DreamGroup.BODY, "SVO+ SKP+"),
    RED_CLOTHES_FLOWERS(DreamGroup.BODY, "ST- LGD~ SVO- KLONG-"),
    RED_WHIRLWIND(DreamGroup.BODY, "LGD-"),
    NET_ENTERED(DreamGroup.BODY, "LGD-"),
    THE_DEAD(DreamGroup.BODY, "ST- LGD- SVI- KLONG-"),
    BLACK_WOMAN(DreamGroup.BODY, "SKP- KLONG-"),
    WOMAN_IN_WHITE_EMBRACED(DreamGroup.BODY, "SVO+"),
    WOMAN_IN_BLACK_EMBRACED(DreamGroup.BODY, "SVO+"),
    WOMAN_IN_YELLOW_EMBRACED(DreamGroup.BODY, "SVO+"),
    DEATH_ANIMALS_AND_DIRECTIONS(DreamGroup.BODY, "ST- SVO- KLONG-"),
    BLOOD_FLOWING(DreamGroup.BODY, "LGD~ SVI. RRT~"),
    FILTH(DreamGroup.BODY, "ST- LGD- SVI- KLONG-"),
    BUDDHAS_GODS_GURU_SEEN(DreamGroup.HOLY, "SVO+ SVI~ SKP+"),
    PROPHECY_SIGN(DreamGroup.HOLY, "LGD. KCM."),
    LAMP_PARASOL_END_OF_SLEEP(DreamGroup.HOLY, "SVO+"),
    KING_ELEPHANT_HORSE_TRAVELLER(DreamGroup.HOLY, "SVO+"),
    DRAGON_VOICE_KINGS_WORD(DreamGroup.HOLY, "SKP+"),
    ROSARY_BROKEN(DreamGroup.HOLY, "SKP-"),
    LIGHTNING_ON_HEART(DreamGroup.HOLY, "SKP-"),
    RIVERS_SEA_DRY(DreamGroup.HOLY, "SKP-"),
    VOMITING_THIEVES(DreamGroup.HOLY, "SKP- RRT~"),
    CONFUSED_DREAMS(DreamGroup.ILL, "ST-"),
    NAGA_ILLNESS_DREAMS(DreamGroup.ILL, "ST-"),
    WOMEN_LEAD_AWAY(DreamGroup.ILL, "ST-"),
    CAT_MONKEY_TIGER_FOX(DreamGroup.ILL, "ST-"),
    RIDING_A_CORPSE(DreamGroup.ILL, "ST-"),
    SHOOT_AND_NEST_ON_THE_CROWN(DreamGroup.ILL, "ST-"),
    PALM_OR_THORN_TREE_IN_THE_HEART(DreamGroup.ILL, "ST-"),
    LOTUS_DRAWN_OUT(DreamGroup.ILL, "ST-"),
    LYING_IN_A_CHARNEL_GROUND(DreamGroup.ILL, "ST-"),
    HEAD_BROKEN(DreamGroup.ILL, "ST-"),
    RINGED_BY_CROWS(DreamGroup.ILL, "ST-"),
    ENTERING_THE_WOMB_AGAIN(DreamGroup.ILL, "ST-"),
    DEVOURED_BY_FISH(DreamGroup.ILL, "ST-"),
    TRADE(DreamGroup.ILL, "ST-"),
    LEVY_DEMANDED(DreamGroup.ILL, "ST-"),
    TAKING_A_BRIDE(DreamGroup.ILL, "ST-");

    /** The image's name in the picker. */
    val english: String get() = gloss(this)

    /** Every text's verdict on the image, in the texts' order, WB's signs first; the Four Tantras' among them. */
    val verdicts: List<DreamVerdict> by lazy {
        val signs = ObservedSigns.GROUPS.first { it.first == "DREAM" }.second.toMap()
        wb.map { DreamVerdict(this, DreamText.WB, signs.getValue(it), it) } +
            spec.split(' ').map { s ->
                val tone = when (s.last()) {
                    '+' -> Tone.GOOD
                    '-' -> Tone.BAD
                    '~' -> Tone.MIXED
                    '.' -> Tone.NEUTRAL
                    else -> error("tone of $s")
                }
                DreamVerdict(this, DreamText.valueOf(s.dropLast(1)), tone)
            }.sortedBy { it.text }
    }

    /** Whether the image is offered when the dreamer is [ill]: an image of the sick only then, any other always. */
    fun offered(ill: Boolean): Boolean = ill || group != DreamGroup.ILL

    companion object {
        /** The images the picker offers, by group in the groups' order. */
        fun offered(ill: Boolean): Map<DreamGroup, List<DreamImage>> = entries.filter { it.offered(ill) }.groupBy { it.group }
    }
}

/**
 * The remedies for a bad dream (claims/dream-remedies.md), one line per text, each once: WB's (its moment sign's own
 * remedy, reused), the Lama Gongdü's, the Svapnohana's and the Svapna-īkṣā's; for the sick, the Four Tantras' general
 * one (ch. 7: a dream is a distant, uncertain sign of death, turned back by merit, recitation and the yidam, and the
 * verse's rite of service) and the two their chapters give for one dream each ([image]). The method under Sakya
 * Paṇḍita's name, Klong rdol's verses and the Magic Mirror give none; the 'Bri gung method's is for a practitioner's
 * obstacle, not a bad dream, and is not shown.
 */
enum class DreamRemedy(val text: DreamText, val image: DreamImage? = null) {
    WB(DreamText.WB), ST(DreamText.ST), ST_CONFUSED(DreamText.ST, DreamImage.CONFUSED_DREAMS), ST_WOMEN(DreamText.ST, DreamImage.WOMEN_LEAD_AWAY),
    LGD(DreamText.LGD), SVO(DreamText.SVO), SVI(DreamText.SVI);

    /** The catalog key of the remedy's text: WB's is its moment signs' own. */
    val key: String get() = if (this == WB) "reading.MomentSign.DREAM.DARKENING.remedy" else "DreamRemedy.$name"
}

/**
 * A dream read (ROADMAP S2): its part of the night [watch], whether the dreamer [ill] is, and the [images] ticked.
 * Only a dawn dream is read: at dusk and in the middle of the night the screen shows the part's line and no rows.
 * When the dreamer is ill the Four Tantras' own rule of the night stands in place of the part's line, and their rows
 * join the images'.
 */
data class DreamReading(val watch: DreamWatch, val ill: Boolean, val images: List<DreamImage>) {
    /** The catalog key of the line for the night: the Four Tantras' rule when ill, else the part's own. */
    val lineKey: String get() = if (ill) "DreamNight.ILL" else "DreamNight.${watch.name}"

    /** The verdicts shown, by text in the texts' order and by image in the order ticked; none unless the dream is read. */
    val verdicts: List<DreamVerdict>
        get() = if (!watch.read) emptyList()
        else images.filter { it.offered(ill) }.flatMap { it.verdicts }.filter { ill || !it.text.medical }
            .sortedBy { it.text } // stable: images keep their order within a text

    /** The verdicts under each text's heading. */
    val byText: Map<DreamText, List<DreamVerdict>> get() = verdicts.groupBy { it.text }

    /** One Remedy row's lines: for each text that reads a shown image as bad or mixed, its remedy once. */
    val remedies: List<DreamRemedy>
        get() {
            val bad = verdicts.filter { it.tone == Tone.BAD || it.tone == Tone.MIXED }
            return DreamRemedy.entries.filter { r ->
                bad.any { it.text == r.text && (r.image == null || it.image == r.image) }
            }
        }
}
