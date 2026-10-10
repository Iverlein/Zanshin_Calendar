/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import zanshin.core.texts.gloss
import kotlin.math.floor

/** The five elements of the letters, in the order the Kālacakra's waxing half gives them (WB vol. 1, p. 17). */
enum class LetterElement(val wylie: String) {
    SPACE("nam mkha'"), WIND("rlung"), FIRE("me"), WATER("chu"), EARTH("sa");

    val english: String get() = gloss(this)
}

/** The five objects of the senses (*'dod yon*), in the same order (p. 17, «སྒྲ་རེག་རོ་གཟུགས་དྲི་སྣ་ལྔ»). */
enum class SenseObject(val wylie: String) {
    SOUND("sgra"), TOUCH("reg"), TASTE("ro"), FORM("gzugs"), SMELL("dri");

    val english: String get() = gloss(this)
}

/** The date's stage (*dar gud*), five in a round (p. 177, «བྱིས་གཞོན་ལང་ཚོ་རྒན་པོ་སྨིན»). */
enum class LifeStage(val wylie: String) {
    CHILD("byis pa"), YOUTH("gzhon nu"), PRIME("lang tsho"), OLD("rgan po"), RIPE("smin pa");

    val english: String get() = gloss(this)
}

/** The twelve links (*rten 'brel bcu gnyis*), in the order of p. 19 («མ་རིག་འདུ་བྱེད་རྣམ་པར་ཤེས། །མིང་གཟུགས་སྐྱེ་མཆེད་རེག་པ་དང་། །ཚོར་བ་སྲེད་པ་ལེན་པ་དང་། །སྲིད་པ་སྐྱེ་བ་རྒ་ཤིའོ»). */
enum class Link(val wylie: String) {
    IGNORANCE("ma rig pa"), FORMATION("'du byed"), CONSCIOUSNESS("rnam par shes pa"), NAME_AND_FORM("ming gzugs"),
    SOURCES("skye mched"), CONTACT("reg pa"), FEELING("tshor ba"), CRAVING("sred pa"), GRASPING("len pa"),
    BECOMING("srid pa"), BIRTH("skye ba"), AGEING_AND_DEATH("rga shi");

    val english: String get() = gloss(this)
}

/** A letter or syllable as WB prints it: [iast] its transliteration, [tibetan] its script. */
data class Letter(val iast: String, val tibetan: String)

/**
 * What the White Beryl's almanac writes of the letters on each day (vol. 1, pp. 177–178, entries 3,
 * 4, 12 and 17; ch. 2, pp. 16–19; docs/sources/day-letters.md): the vowels and consonants of the
 * Kālacakra and of the *dbyangs 'char* with their elements and sense objects, the date's stage, the
 * twelve links counted from the month's *sgang*, and the moon's foot of the "hundred feet". WB writes
 * them so that the reckoner sees the fine and the rough (p. 173); it reads none of them against the
 * day, so the app weighs none.
 */
object DayLetters {

    // The Kālacakra (p. 16, after its chapter on the world).

    /**
     * A vowel place of the Kālacakra's thirty dates: [sub] a letter joined under the consonant (the
     * semivowels, and the r and l of ṛ and ḷ), [vowel] the vowel in Extended Wylie, [suffix] an r or l
     * after it (ar, al).
     */
    private class Place(val iast: String, val sub: String?, val vowel: String, val suffix: String? = null)

    /**
     * The vowels of the thirty dates, the same in every month (p. 16): «གཅིག་ནས་ཨ་ཨི་རྀ་ཨུ་ལྀ། །དྲུག་ནས་ཨ་ཨེ་ཨར་ཨོ་ཨལ། །ཧ་ཡ་ར་བ་ལ་རིམ་བཞིན།»
     * in the waxing half, and from the 16th the same with long vowels, «རིམ་མིན་སྡུད་པས» in the reverse
     * order, gathering them in: lā vā rā yā hā, āl au ār ai ā, ḹ ū ṝ ī ā; these are the vowels of life
     * (*srog gi dbyangs*). The model almanac writes these in every month (pp. 154–171).
     */
    private val PLACES = listOf(
        Place("a", null, "a"), Place("i", null, "i"), Place("ṛ", "r", "-i"), Place("u", null, "u"), Place("ḷ", "l", "-i"),
        Place("a", null, "a"), Place("e", null, "e"), Place("ar", null, "a", "r"), Place("o", null, "o"), Place("al", null, "a", "l"),
        Place("ha", "h", "a"), Place("ya", "y", "a"), Place("ra", "r", "a"), Place("va", "w", "a"), Place("la", "l", "a"),
        Place("lā", "l", "A"), Place("vā", "w", "A"), Place("rā", "r", "A"), Place("yā", "y", "A"), Place("hā", "h", "A"),
        Place("āl", null, "A", "l"), Place("au", null, "au"), Place("ār", null, "A", "r"), Place("ai", null, "ai"), Place("ā", null, "A"),
        Place("ḹ", "l", "-I"), Place("ū", null, "U"), Place("ṝ", "r", "-I"), Place("ī", null, "I"), Place("ā", null, "A"),
    )

    /** A consonant: [iast] without its vowel, [ewts] its letters joined by "+". */
    private class Consonant(val iast: String, val ewts: String)

    private fun group(vararg pairs: String): List<Consonant> = pairs.toList().chunked(2).map { (i, e) -> Consonant(i, e) }

    /**
     * The six groups of five consonants, each rising in one sign-month in order and in the next in the
     * reverse order, six rounds to the month (p. 16): the *ka* group in Capricorn and back in Aquarius,
     * the *ca* group in Pisces and Aries, the *ṭa* group in Taurus and Gemini, the *pa* group in Cancer
     * and Leo, the *ta* group in Virgo and Libra, sa ha ṣa śa kṣa in Scorpio and Sagittarius.
     */
    private val GROUPS = listOf(
        group("k", "k", "kh", "kh", "g", "g", "gh", "gh", "ṅ", "ng"),
        group("c", "ts", "ch", "tsh", "j", "dz", "jh", "dz+h", "ñ", "ny"),
        group("ṭ", "T", "ṭh", "Th", "ḍ", "D", "ḍh", "Dh", "ṇ", "N"),
        group("p", "p", "ph", "ph", "b", "b", "bh", "bh", "m", "m"),
        group("t", "t", "th", "th", "d", "d", "dh", "dh", "n", "n"),
        group("s", "s", "h", "h", "ṣ", "Sh", "ś", "sh", "kṣ", "k+Sh"),
    )

    /**
     * The sign of Hor month [month], whose consonants it takes: the sign of its *sgang*, as the model
     * almanac heads each month (the 11th Sagittarius, «གཞུའི་ཁྱིམ»; the 3rd Aries). A leap month keeps its number's.
     */
    fun sign(month: Int): ZodiacSign = ZodiacSign.entries[Math.floorMod(month - 3, 12)]

    /**
     * The Kālacakra's line for a date; [arising] in the waxing half, where the elements arise from
     * space to earth, and not in the waning, where they are gathered back (p. 173, «འདོད་ཡོན་སྐྱེ་བསྡུའི་ཚུལ»).
     */
    data class Kalacakra(val vowel: Letter, val syllable: Letter, val element: LetterElement, val sense: SenseObject, val arising: Boolean)

    /**
     * The Kālacakra's vowel and syllable of [date] in Hor month [month], with its element and sense
     * object (p. 17, «ནམ་མཁའ་རླུང་མེ་ཆུ་དང་ས། །སྒྲ་རེག་རོ་གཟུགས་དྲི་སྣ་ལྔ། །དཀར་པོའི་ཕྱོགས་ལ་ལན་གསུམ་འཁོར། །ནག་ཕྱོགས་ས་ཆུ་མེ་རླུང་དང་། །ནམ་མཁའ་དྲི་གཟུགས་རོ་རེག་སྒྲ།»):
     * space, wind, fire, water and earth with sound, touch, taste, form and smell three rounds in the
     * waxing half, earth back to space with smell back to sound in the waning. The syllable is the
     * month's consonant with the date's vowel joined to it, as the model almanac writes it (the 11th
     * month's 1st «ཨ ཀྵ», its 12th's 3rd «རྀ གྲྀ»).
     */
    fun kalacakra(month: Int, date: Int): Kalacakra {
        val place = PLACES[date - 1]
        val k = Math.floorMod(sign(month).ordinal - ZodiacSign.CAPRICORN.ordinal, 12)
        val group = GROUPS[k / 2].let { if (k % 2 == 1) it.reversed() else it }
        val c = group[(date - 1) % 5]
        val waxing = date <= 15
        val i = (date - 1) % 5
        return Kalacakra(
            Letter(place.iast, standalone(place)),
            Letter(c.iast + place.iast, joined(c.ewts, place)),
            LetterElement.entries[if (waxing) i else 4 - i],
            SenseObject.entries[if (waxing) i else 4 - i],
            waxing,
        )
    }

    /** The vowel place alone: on the a-chen, or the semivowel or r or l itself. */
    private fun standalone(p: Place): String {
        val stack = p.sub ?: ""
        return Ewts.toTibetan(stack + p.vowel)!! + (p.suffix?.let { Ewts.toTibetan(it + "a")!! } ?: "")
    }

    /** The consonant [ewts] with the place joined: the semivowel, r or l under it, then the vowel and any r or l after. */
    private fun joined(ewts: String, p: Place): String {
        val stack = listOfNotNull(ewts, p.sub).joinToString("+")
        return Ewts.toTibetan(stack + p.vowel)!! + (p.suffix?.let { Ewts.toTibetan(it + "a")!! } ?: "")
    }

    // The dbyangs 'char (p. 17, after the gtsug na zla ba'i legs bshad).

    /** The *dbyangs 'char*'s line for a date. */
    data class Svarodaya(val vowel: Letter, val consonant: Letter, val element: LetterElement, val sense: SenseObject)

    /** The five vowels of the 1st to 15th, and the long ones of the 16th to 30th (the verse's waxing half, «ཨཱ་ཨཱི་ཨཱུ་ཨེ་ཨོ»). */
    private val SVARA_VOWELS = listOf("a" to "a", "i" to "i", "u" to "u", "e" to "e", "o" to "o")
    private val SVARA_LONG_VOWELS = listOf("ā" to "A", "ī" to "I", "ū" to "U", "e" to "e", "o" to "o")

    /**
     * The consonants of the thirty dates, one a date (p. 17, «གསལ་བྱེད་ང་ཉ་ཎ་གསུམ་པོ། །སྤངས་པའི་ཀ་ནས་དའི་བར་འཆར། …གསལ་བྱེད་དྷ་ནས་ཧའི་བར་དུ»):
     * from ka to da without ṅa, ña and ṇa, and from dha to ha, with the waxing and waning halves
     * reversed («དཀར་ནག་ཕྱོགས་ལྡོག་པའི»), so the 1st has dha, as the model almanac writes in every month
     * (the 11th month's 1st «ཨ དྷ», the 16th «ཨ ཀ»).
     */
    private val SVARA_CONSONANTS = group(
        "dha", "dha", "na", "na", "pa", "pa", "pha", "pha", "ba", "ba", "bha", "bha", "ma", "ma", "ya", "ya",
        "ra", "ra", "la", "la", "va", "wa", "śa", "sha", "ṣa", "Sha", "sa", "sa", "ha", "ha",
        "ka", "ka", "kha", "kha", "ga", "ga", "gha", "gha", "ca", "tsa", "cha", "tsha", "ja", "dza", "jha", "dz+ha",
        "ṭa", "Ta", "ṭha", "Tha", "ḍa", "Da", "ḍha", "Dha", "ta", "ta", "tha", "tha", "da", "da",
    )

    /**
     * The *dbyangs 'char*'s vowel, consonant, element and sense object of [date] (p. 17, «ཨཱ་ཨཱི་ཨཱུ་ཨེ་ཨོ་ལྔ། །འབྱུང་བ་ས་ཆུ་མེ་རླུང་མཁའ། །དྲི་རོ་རཱུ་པ་རེག་སྒྲ་རྣམས། །དཀར་པོའི་ཕྱོགས་ལ་ལན་གསུམ་འཁོར། …ནག་ཕྱོགས་ཨ་ཨི་ཨུ་ཨེ་ཨོ»):
     * ā ī ū e o with ka to da in the verse's waxing half, a i u e o with dha to ha in its waning, the
     * halves reversed, so the 1st to 15th have the short vowels and the 16th to 30th the long, as the
     * model almanac's ovals write them (the 21st «ཨཱ ཚ»); earth, water, fire, wind and space with smell,
     * taste, form, touch and sound, three rounds in each half; the same in every month.
     */
    fun svarodaya(date: Int): Svarodaya {
        val i = (date - 1) % 5
        val (iast, ewts) = (if (date <= 15) SVARA_VOWELS else SVARA_LONG_VOWELS)[i]
        val c = SVARA_CONSONANTS[date - 1]
        return Svarodaya(
            Letter(iast, Ewts.toTibetan(ewts)!!),
            Letter(c.iast, Ewts.toTibetan(c.ewts)!!),
            LetterElement.entries[4 - i],
            SVARA_SENSES[i],
        )
    }

    /** The *dbyangs 'char*'s objects, paired with its elements otherwise than the Kālacakra's (p. 17, «དྲི་རོ་གཟུགས་རེག་སྒྲ»; the ovals' «ཆུ་རོ», «མེ་གཟུགས»). */
    private val SVARA_SENSES = listOf(SenseObject.SMELL, SenseObject.TASTE, SenseObject.FORM, SenseObject.TOUCH, SenseObject.SOUND)

    /**
     * The date's stage (p. 177, «དུས་འཁོར་དབྱངས་འཆར་གཉིས་ཀའང་། །མཐུན་པའི་ཚེས་ནི་དར་གུད་ནི། །བྱིས་གཞོན་ལང་ཚོ་རྒན་པོ་སྨིན། །ཟླ་རེར་དྲུག་སྐོར་འདྲི་བར་བྱ»):
     * child, youth, prime, old and ripe, six rounds a month with the five-fold cycle, as the model
     * almanac writes them under each date's *dbyangs 'char* (the 1st *byis*, the 2nd *gzhon*, the 5th *smin*).
     */
    fun stage(date: Int): LifeStage = LifeStage.entries[(date - 1) % 5]

    // The twelve links (p. 177; the table pp. 150–153).

    /**
     * The link of the table's column [column] in the row of Hor month [month] (pp. 150–153, the table
     * the rule of p. 177 refers to, read cell by cell on the 1996 scans and the Zhol print, WBZ
     * img. 171–172). Each row starts from its own link, the 11th month's from ignorance and each later
     * month's from the next («མ་རིག་པ» heads the model almanac's 11th month), and runs forward for
     * columns 1–12, then 13–15 repeat the 3rd to 5th; columns 16–27, the second fifteen, run back
     * from the row's link, and 28–30 repeat the 18th to 20th; column 31 is the row's link less seven
     * and 32 less five. One cell breaks the rows' pattern and stands as both prints have it: the 10th
     * month's 28th, *rga shi* where the others give the link two before the row's.
     */
    fun linkAt(month: Int, column: Int): Link {
        require(column in 1..32)
        if (month == 10 && column == 28) return Link.AGEING_AND_DEATH
        val start = Math.floorMod(month + 1, 12)
        val step = when (column) {
            in 1..12 -> column - 1
            in 13..15 -> column - 11
            in 16..27 -> 16 - column
            in 28..30 -> 26 - column
            31 -> -7
            else -> -5
        }
        return Link.entries[Math.floorMod(start + step, 12)]
    }

    /**
     * The table's columns a stretch of [days] days from one *sgang* to the next uses, in order (p. 177,
     * «ཉེར་བརྒྱད་ཡོད་ཚེ་བཅོ་ལྔ་དང་། །སུམ་ཅུའི་འོག་མ་གཉིས་ཀ་དོར། །ཉེར་དགུ་ཡོད་ཚེ་སུམ་ཅུ་དོར། །སུམ་ཅུར་འགྲིག་ཚེ་བཅོ་ལྔ་པོ། །གཉིས་ཀ་དཀྱུས་བཞིན་འགོད་པ་དང་། །སོ་གཅིག་ཡོད་ཚེ་དེ་གྲངས་དང་། །སོ་གཉིས་ཚེ་ན་དེའི་གྲངས་ཀྱང་། །བསྣན་ཏེ»):
     * with 28 days both fifteenths are dropped, the 15th and the 30th; with 29 the 30th; with 30 both
     * stand; with 31 the 31st column is added, with 32 the 32nd too.
     */
    fun columns(days: Int): List<Int>? = when (days) {
        28 -> (1..30).filter { it != 15 && it != 30 }
        29 -> (1..29).toList()
        30 -> (1..30).toList()
        31 -> (1..31).toList()
        32 -> (1..32).toList()
        else -> null
    }

    /** A day's link: the [day]th of [days] days from the *sgang* of Hor month [month]. */
    data class LinkDay(val link: Link, val month: Int, val day: Int, val days: Int)

    /**
     * The link of [day] (p. 177, «དེ་ནས་རྟེན་འབྲེལ་བཅུ་གཉིས་ནི། །ཟླ་བའི་སྒང་ནས་སྒང་ཚད་བར།»): counted in days from the day
     * in which a month's *sgang* falls ([DayTimes.sgangDays]) to the day before the next, in the row of
     * that *sgang*'s month and its [columns]. Null in a stretch longer than the table's 32 days: the
     * almanac's true sun makes one of 33 in 2000–2049, from the 5th month's *sgang* of 2009 (4 July to
     * 5 August), which WB's rule does not provide for.
     */
    fun link(day: TibetanDay): LinkDay? {
        val sgangs = DayTimes.sgangDays(day.monthCount - 2..day.monthCount + 2)
        val i = sgangs.indexOfLast { it.first <= day.jd }
        val (from, month) = sgangs[i]
        val days = (sgangs[i + 1].first - from).toInt()
        val k = (day.jd - from).toInt() + 1
        val columns = columns(days) ?: return null
        return LinkDay(linkAt(month, columns[k - 1]), month, k, days)
    }

    // The hundred feet (rkang brgya, p. 178; the wheel ch. 12, p. 97).

    /**
     * The four feet of each mansion, its quarters, as ch. 12 sets them out on the wheel from Kṛttikā
     * (p. 97, «སྨིན་དྲུག་ཨ་དང་ཨི་ཨུ་ཨེ། །སྣར་མ་ཨོ་ཝ་ཝི་ཝུ་དང་། …བྲ་ཉེ་ལི་ལུ་ལེ་ལོའོ»), read on the scan. WB's
     * order puts Abhijit (*byi bzhin*) after Śravaṇa (*gro bzhin*), with khi khu khe kho; the moon's
     * twenty-seven mansions never reach those four.
     */
    private val FEET: Map<Mansion, List<Pair<String, String>>> = mapOf(
        Mansion.KRITTIKA to feet("a", "a", "i", "i", "u", "u", "e", "e"),
        Mansion.ROHINI to feet("o", "o", "va", "wa", "vi", "wi", "vu", "wu"),
        Mansion.MRIGASHIRAS to feet("ve", "we", "vo", "wo", "ka", "ka", "ki", "ki"),
        Mansion.ARDRA to feet("ku", "ku", "gha", "gha", "ṅa", "nga", "cha", "tsha"),
        Mansion.PUNARVASU to feet("ke", "ke", "ko", "ko", "ha", "ha", "hi", "hi"),
        Mansion.PUSHYA to feet("hu", "hu", "he", "he", "ho", "ho", "ḍa", "Da"),
        Mansion.ASHLESHA to feet("ḍi", "Di", "ḍu", "Du", "ḍe", "De", "ḍo", "Do"),
        Mansion.MAGHA to feet("ma", "ma", "mi", "mi", "mu", "mu", "me", "me"),
        Mansion.PURVAPHALGUNI to feet("mo", "mo", "ṭa", "Ta", "ṭi", "Ti", "ṭu", "Tu"),
        Mansion.UTTARAPHALGUNI to feet("ṭe", "Te", "ṭo", "To", "pa", "pa", "pi", "pi"),
        Mansion.HASTA to feet("pu", "pu", "ṣa", "Sha", "ṇa", "Na", "ṭha", "Tha"),
        Mansion.CITRA to feet("pe", "pe", "po", "po", "ra", "ra", "ri", "ri"),
        Mansion.SVATI to feet("ru", "ru", "re", "re", "ro", "ro", "ta", "ta"),
        Mansion.VISHAKHA to feet("ti", "ti", "tu", "tu", "te", "te", "to", "to"),
        Mansion.ANURADHA to feet("na", "na", "ni", "ni", "nu", "nu", "ne", "ne"),
        Mansion.JYESHTHA to feet("no", "no", "ya", "ya", "yi", "yi", "yu", "yu"),
        Mansion.MULA to feet("ye", "ye", "yo", "yo", "bha", "bha", "bhi", "bhi"),
        Mansion.PURVASHADHA to feet("bhu", "bhu", "dha", "dha", "pha", "pha", "ḍha", "Dha"),
        Mansion.UTTARASHADHA to feet("bhe", "bhe", "bho", "bho", "ja", "dza", "ji", "dzi"),
        Mansion.SHRAVANA to feet("ju", "dzu", "je", "dze", "jo", "dzo", "kha", "kha"),
        Mansion.DHANISHTHA to feet("ga", "ga", "gi", "gi", "gu", "gu", "ge", "ge"),
        Mansion.SHATABHISHAJ to feet("go", "go", "sa", "sa", "si", "si", "su", "su"),
        Mansion.PURVABHADRAPADA to feet("se", "se", "so", "so", "da", "da", "di", "di"),
        Mansion.UTTARABHADRAPADA to feet("du", "du", "tha", "tha", "jha", "dz+ha", "ña", "nya"),
        Mansion.REVATI to feet("de", "de", "do", "do", "ca", "tsa", "ci", "tsi"),
        Mansion.ASHVINI to feet("cu", "tsu", "ce", "tse", "co", "tso", "la", "la"),
        Mansion.BHARANI to feet("li", "li", "lu", "lu", "le", "le", "lo", "lo"),
    )

    private fun feet(vararg pairs: String): List<Pair<String, String>> = pairs.toList().chunked(2).map { (i, e) -> i to e }

    /** The moon's foot of [day]: the quarter, 1–4, of its mansion at daybreak, and that quarter's syllable. */
    data class Foot(val mansion: Mansion, val quarter: Int, val syllable: Letter)

    /**
     * The foot the moon walks in at daybreak (p. 178, «ཟླ་སྐར་སྤྱོད་ཚད་རྐང་བརྒྱའི»): the quarter of its mansion
     * that the day's figure of the moon has reached, and the syllable of that foot.
     */
    fun foot(day: TibetanDay): Foot {
        val inMansion = day.moon.toDouble() * 27 - day.mansion.ordinal
        val quarter = floor(inMansion * 4).toInt().coerceIn(0, 3) + 1
        val (iast, ewts) = FEET.getValue(day.mansion)[quarter - 1]
        return Foot(day.mansion, quarter, Letter(iast, Ewts.toTibetan(ewts)!!))
    }

    // The wheel of the hundred feet (ch. 12, pp. 96–97) and its fangs (p. 98).

    /** A cell of the wheel: a mansion (null for Abhijit, which no day has), a letter, a sign or a class of date. */
    sealed interface Cell {
        data class Star(val mansion: Mansion?) : Cell
        data class Sound(val letter: Letter) : Cell
        data class Sign(val sign: ZodiacSign) : Cell
        data class DateClass(val dateClass: LunarDayClass) : Cell
    }

    /**
     * The wheel of the hundred feet (*rkang pa brgya pa*, p. 96, drawn there, its rings p. 97): eighty-one
     * cells, nine to a side, east at the top. The vowels stand in the corners, ring by ring from the
     * outside, north-east, south-east, south-west, north-west (p. 96, «རིམ་པར་བྱང་ཤར་ཤར་ལྷོ་དང་། །ལྷོ་ནུབ་ནུབ་བྱང་ཕྱི་རིམ་བཞིན། །ཨ་ཨཱ་ཨི་ཨཱི་ཨུ་ཨཱུ་དང་། །རྀ་རཱྀ་ལྀ་ལཱྀ་ཨེ་ཨཻ་སྟེ། །ཨོ་ཨཽ་ཨཾ་ཨཿ»);
     * the mansions round the outer ring, seven to a side from Kṛttikā in the east, WB's twenty-eight with
     * Abhijit after Śravaṇa; the second ring the consonants, east a va ka ha ḍa, south ma ṭa pa ra ta,
     * west na ya bha ja kha, north ga sa da ca la; the third the signs, east Taurus to Cancer and so
     * round to Aries in the north; at the centre the five classes, *dga' ba* east, *bzang po* south,
     * *rgyal ba* west, *stong pa* north, *rdzogs pa* in the middle (p. 97). Each side is listed in the
     * order the mansions go round, east left to right, south top to bottom, west right to left, north
     * bottom to top.
     */
    val WHEEL: List<List<Cell>> by lazy {
        val grid = Array(9) { arrayOfNulls<Cell>(9) }
        fun side(ring: Int, cells: List<Cell>) {
            val n = 8 - 2 * ring - 1
            val q = cells.chunked(n)
            for (i in 0 until n) {
                grid[ring][ring + 1 + i] = q[0][i]
                grid[ring + 1 + i][8 - ring] = q[1][i]
                grid[8 - ring][8 - ring - 1 - i] = q[2][i]
                grid[8 - ring - 1 - i][ring] = q[3][i]
            }
        }
        val stars = listOf(
            Mansion.KRITTIKA, Mansion.ROHINI, Mansion.MRIGASHIRAS, Mansion.ARDRA, Mansion.PUNARVASU, Mansion.PUSHYA, Mansion.ASHLESHA,
            Mansion.MAGHA, Mansion.PURVAPHALGUNI, Mansion.UTTARAPHALGUNI, Mansion.HASTA, Mansion.CITRA, Mansion.SVATI, Mansion.VISHAKHA,
            Mansion.ANURADHA, Mansion.JYESHTHA, Mansion.MULA, Mansion.PURVASHADHA, Mansion.UTTARASHADHA, Mansion.SHRAVANA, null,
            Mansion.DHANISHTHA, Mansion.SHATABHISHAJ, Mansion.PURVABHADRAPADA, Mansion.UTTARABHADRAPADA, Mansion.REVATI, Mansion.ASHVINI, Mansion.BHARANI,
        )
        side(0, stars.map { Cell.Star(it) })
        val consonants = listOf(
            "a" to "a", "va" to "wa", "ka" to "ka", "ha" to "ha", "ḍa" to "Da",
            "ma" to "ma", "ṭa" to "Ta", "pa" to "pa", "ra" to "ra", "ta" to "ta",
            "na" to "na", "ya" to "ya", "bha" to "bha", "ja" to "dza", "kha" to "kha",
            "ga" to "ga", "sa" to "sa", "da" to "da", "ca" to "tsa", "la" to "la",
        )
        side(1, consonants.map { (i, e) -> Cell.Sound(Letter(i, Ewts.toTibetan(e)!!)) })
        side(2, (1..12).map { Cell.Sign(ZodiacSign.entries[it % 12]) })
        grid[3][4] = Cell.DateClass(LunarDayClass.NANDA)
        grid[4][5] = Cell.DateClass(LunarDayClass.BHADRA)
        grid[5][4] = Cell.DateClass(LunarDayClass.JAYA)
        grid[4][3] = Cell.DateClass(LunarDayClass.RIKTA)
        grid[4][4] = Cell.DateClass(LunarDayClass.PURNA)
        val vowels = listOf(
            "a" to "ཨ", "ā" to "ཨཱ", "i" to "ཨི", "ī" to "ཨཱི", "u" to "ཨུ", "ū" to "ཨཱུ", "ṛ" to "རྀ", "ṝ" to "རཱྀ",
            "ḷ" to "ལྀ", "ḹ" to "ལཱྀ", "e" to "ཨེ", "ai" to "ཨཻ", "o" to "ཨོ", "au" to "ཨཽ", "aṃ" to "ཨཾ", "aḥ" to "ཨཿ",
        ).map { (i, t) -> Cell.Sound(Letter(i, t)) }
        for (ring in 0..3) {
            grid[ring][ring] = vowels[4 * ring]
            grid[ring][8 - ring] = vowels[4 * ring + 1]
            grid[8 - ring][8 - ring] = vowels[4 * ring + 2]
            grid[8 - ring][ring] = vowels[4 * ring + 3]
        }
        grid.map { row -> row.map { it!! } }
    }

    /**
     * The face-on and the two fangs from a mansion, along the wheel's two diagonals (p. 98, a planet in
     * Kṛttikā: «གཟའ་གནས་གཡས་པ་བྲ་ཉེ་དང་། །གཡོན་པ་ཨ་ཡིག་གླང་ཁྱིམ་དང་། །དགའ་བཟང་སྲང་ཁྱིམ་ཏ་ཡིག་དང་། །ས་ག་མདུན་གྱི་བྱ་སྤོ་ལ། །གདོང་ཚུགས»): the right
     * fang the diagonal toward the mansions before, the left the one toward those after, each cell it
     * crosses; where the left reaches the outer ring again is the face-on, as Viśākhā is Kṛttikā's.
     */
    data class Fangs(val right: List<Cell>, val left: List<Cell>, val faceOn: Cell)

    /**
     * The fangs from [mansion] ([Fangs]). Over each mansion the almanac writes those of the bodies in it
     * (p. 178, «ལྔ་སྒྲ་ཉི་མ་བཅས་པ་ཡི། །གདོང་ཚུགས་གཡས་གཡོན་མཆེ་བ་བཅས», the five planets, Rāhu and the sun, and «ཟླ་སྐར་སྤྱོད་ཚད་རྐང་བརྒྱའི་མཆེ། །གཡས་པ་བཅས་པ»,
     * the moon's), the eight bodies whose strikes ch. 12 reckons (p. 97); the app has the moon's and the sun's.
     */
    fun fangs(mansion: Mansion): Fangs {
        val wheel = WHEEL
        val (r, c) = (0..8).flatMap { i -> (0..8).map { j -> i to j } }
            .first { (i, j) -> wheel[i][j] == Cell.Star(mansion) }
        // Toward the mansions before and after, as the side runs round (east left to right, …).
        val (right, left) = when {
            r == 0 -> (1 to -1) to (1 to 1)
            c == 8 -> (-1 to -1) to (1 to -1)
            r == 8 -> (-1 to 1) to (-1 to -1)
            else -> (1 to 1) to (-1 to 1)
        }
        fun line(d: Pair<Int, Int>): List<Cell> = generateSequence(r + d.first to c + d.second) { (i, j) -> i + d.first to j + d.second }
            .takeWhile { (i, j) -> i in 0..8 && j in 0..8 }
            .map { (i, j) -> wheel[i][j] }
            .toList()
        val l = line(left)
        return Fangs(line(right), l.dropLast(1), l.last())
    }

    /** The sun's mansion on [day]: the almanac's true sun at the end of the day's date. */
    fun sunMansion(day: TibetanDay): Mansion = Mansion.entries[(day.sun.toDouble() * 27).toInt().coerceIn(0, 26)]
}
