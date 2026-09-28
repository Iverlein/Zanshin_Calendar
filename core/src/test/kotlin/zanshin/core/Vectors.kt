/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core

import org.junit.jupiter.api.Assumptions.assumeTrue

/**
 * Rows of a tab-separated vector file under test resources, comments and blank lines skipped.
 * The vectors are third-party tables kept out of the public repository, so a test that needs
 * a missing file is skipped rather than failed.
 */
fun vectors(name: String): List<List<String>> {
    val stream = object {}.javaClass.getResourceAsStream("/vectors/$name")
    assumeTrue(stream != null) { "vector file $name is not present (not published)" }
    return stream!!.bufferedReader().readLines()
        .filter { it.isNotBlank() && !it.startsWith("#") }
        .map { it.split('\t') }
}
