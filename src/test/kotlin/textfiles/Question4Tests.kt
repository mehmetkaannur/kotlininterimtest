package textfiles

import java.util.Random
import kotlin.test.Test
import kotlin.test.assertEquals

class Question4Tests {
    @Test
    fun concurrencyTest() {
        for (repeat in 1..20) {
            println("Repeat run $repeat")
            val initialText = "initialtext"
            val authorStrings: List<List<String>> = (0..<8).map {
                (0..<1000).map {
                    (0..it % 10).map { number -> number.toString() }.joinToString(separator = "")
                }
            }
            val expectedOutput: String =
                (initialText + authorStrings.flatten().joinToString(separator = ""))
                    .toCharArray().sortedArray().joinToString(separator = "")
            val textFile: TextFile = ThreadSafeTextFile(SingleStringTextFile(initialText))
            val authors = (0..<8).map {
                Author(authorStrings[it], textFile, Random())
            }
            val threads = authors.map {
                Thread(it)
            }
            threads.forEach {
                it.start()
            }
            threads.forEach {
                it.join()
            }
            assertEquals(
                expectedOutput,
                textFile.toString().toCharArray().sortedArray().joinToString(separator = ""),
            )
        }
    }
}
