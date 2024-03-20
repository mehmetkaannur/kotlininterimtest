package textfiles

import java.util.random.RandomGenerator

class Author(
    private val strings: List<String>,
    private val target: TextFile,
    private val random: RandomGenerator
) : Runnable {
    override fun run() {
        for (string in strings) {
            target.insertText(random.nextInt(target.length + 1), string)
        }
    }
}