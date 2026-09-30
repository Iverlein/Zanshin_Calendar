/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.kyureki

import zanshin.core.texts.Catalog
import zanshin.core.texts.gloss
import java.time.LocalDate

/** The five elements (五行), in the order of the generating cycle: 木生火, 火生土, 土生金, 金生水, 水生木. */
enum class Gogyo(val kanji: String) {
    WOOD("木"),
    FIRE("火"),
    EARTH("土"),
    METAL("金"),
    WATER("水");

    val english: String get() = gloss(this)


    /** The element this one feeds (相生). */
    val feeds: Gogyo get() = entries[(ordinal + 1) % 5]

    /** The element this one overcomes (相剋): 木剋土, 土剋水, 水剋火, 火剋金, 金剋木. */
    val overcomes: Gogyo get() = entries[(ordinal + 2) % 5]
}

/** Element of each star (Japanese Wikipedia 九星). */
val KyuSei.element: Gogyo
    get() = when (this) {
        KyuSei.IPPAKU -> Gogyo.WATER
        KyuSei.SANPEKI, KyuSei.SHIROKU -> Gogyo.WOOD
        KyuSei.JIKOKU, KyuSei.GOO, KyuSei.HAPPAKU -> Gogyo.EARTH
        KyuSei.ROPPAKU, KyuSei.SHICHISEKI -> Gogyo.METAL
        KyuSei.KYUSHI -> Gogyo.FIRE
    }

/**
 * How two stars stand by their elements (Japanese Wikipedia 九星, 九星の関係):
 * one feeds the other, both share an element, or one overcomes the other.
 * For a person's years, months and days the article reads 相生 and 比和 as
 * good and 相剋 as bad.
 */
enum class StarRelation(override val kanji: String, override val reading: String, val tone: Tone) : Term {
    SOSHO("相生", "sōshō", Tone.GOOD),
    HIWA("比和", "hiwa", Tone.GOOD),
    SOKOKU("相剋", "sōkoku", Tone.BAD);

    override val english: String get() = gloss(this)
}

/** The relation of one's birth star to another star, with the cycle that links them, e.g. 金生水. */
data class StarAffinity(val own: KyuSei, val other: KyuSei, val relation: StarRelation, val cycle: String, val cycleEnglish: String)

/** 九星気学: one's own star against the stars of the calendar. */
object Kigaku {
    /** 本命星, the year star of the birth date, the year reckoned from 立春. */
    fun honmeiStar(birth: LocalDate): KyuSei = Rekichu.yearStar(Rekichu.setsuYearOf(birth))

    fun affinity(own: KyuSei, other: KyuSei): StarAffinity {
        val a = own.element
        val b = other.element
        fun link(from: Gogyo, verb: String, to: Gogyo, key: String, relation: StarRelation) =
            StarAffinity(own, other, relation, "${from.kanji}$verb${to.kanji}", Catalog.format(key, from.english, to.english))
        return when {
            a == b -> StarAffinity(own, other, StarRelation.HIWA, StarRelation.HIWA.kanji, Catalog.format("StarAffinity.same", a.english))
            b.feeds == a -> link(b, "生", a, "StarAffinity.feeds", StarRelation.SOSHO)
            a.feeds == b -> link(a, "生", b, "StarAffinity.feeds", StarRelation.SOSHO)
            b.overcomes == a -> link(b, "剋", a, "StarAffinity.overcomes", StarRelation.SOKOKU)
            else -> link(a, "剋", b, "StarAffinity.overcomes", StarRelation.SOKOKU)
        }
    }
}
