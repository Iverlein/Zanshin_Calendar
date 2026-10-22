/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core

import java.io.File
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

/**
 * The knowledge pool (docs/sources/PLAN.md): every topic file is in the index, every reading the
 * roadmap names a topic file for points at one that exists, and a reading marked done has left its
 * text behind, its topic file naming it and holding the Tibetan it read.
 */
class SourcesTest {
    private val sources = File("../docs/sources")
    private val index = File(sources, "README.md").readText()
    private val roadmap = File("../docs/ROADMAP.md").readText()
    private val tibetan = Regex("[ༀ-࿿]")

    private data class Reading(val id: String, val done: Boolean, val files: List<Pair<String, Boolean>>)

    /** The rows of the roadmap's Readings table: id, whether done, and each topic file with whether it is new. */
    private val readings: List<Reading> = roadmap.lines().filter { it.startsWith("| <a id=\"rd") }.map { row ->
        val cells = row.split(" | ")
        val id = Regex("""RD\d+""").find(cells[0])!!.value
        val done = Regex("""— done \d{4}-\d{2}-\d{2}""").containsMatchIn(cells[1])
        val files = Regex("""([\w/-]+\.md)( \(new\))?""").findAll(cells[5]).map { it.groupValues[1].removePrefix("sources/") to it.groupValues[2].isNotEmpty() }.toList()
        Reading(id, done, files)
    }

    @Test
    fun `every topic file is in the index, and the index names no missing file`() {
        val files = sources.listFiles { f -> f.isFile && f.name.endsWith(".md") }!!.map { it.name }.filter { it != "README.md" }
        val unlisted = files.filter { "]($it)" !in index }
        assertTrue(unlisted.isEmpty(), "not in docs/sources/README.md: $unlisted")
        val linked = Regex("""\]\(([\w-]+\.md)\)""").findAll(index).map { it.groupValues[1] }.toSet()
        val missing = linked.filter { !File(sources, it).exists() }
        assertTrue(missing.isEmpty(), "README.md links missing files: $missing")
    }

    @Test
    fun `the readings name their topic files`() {
        assertTrue(readings.size >= 17, "the Readings table was not found in docs/ROADMAP.md")
        for (r in readings) {
            assertTrue(r.files.isNotEmpty(), "${r.id} names no topic file")
            for ((name, new) in r.files) {
                assertTrue(new || r.done || File(sources, name).exists(), "${r.id}: $name does not exist and is not marked (new)")
            }
        }
    }

    @Test
    fun `a reading marked done has left its text in its topic file`() {
        for (r in readings.filter { it.done }) {
            for ((name, _) in r.files) {
                val file = File(sources, name)
                assertTrue(file.exists(), "${r.id} is done, but $name does not exist")
                val text = file.readText()
                assertTrue(Regex("""\b${r.id}\b""").containsMatchIn(text), "${r.id} is done, but $name does not name it")
                assertTrue(tibetan.containsMatchIn(text), "${r.id} is done, but $name holds no Tibetan")
            }
        }
    }
}
