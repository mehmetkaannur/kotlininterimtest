package textfiles

private const val BLOCK_SIZE: Int = 8

class MultiStringTextFile(initialContents: String) : TextFile {

    private var blocks: MutableList<StringBuilder> = mutableListOf(StringBuilder(initialContents))

    /**
     * Yields the number of blocks that are currently used to represent the file.
     *
     * @return The number of blocks used to represent this file.
     */
    val numBlocks: Int
        get() = blocks.size

    override val length: Int
        get() = blocks.sumOf { it.length }

    init {
        rebalance()
    }

    /**
     * Reorganises the internal representation of the text file so that the content is partitioned
     * into blocks of equal size, except that the final block may be shorter.
     */
    fun rebalance() {
        val newBlocks: MutableList<StringBuilder> = mutableListOf()
        var oldEntries = ""
        blocks.forEach { oldEntries += it.toString() }
        for (i in (0..<(length / BLOCK_SIZE))) {
            val newBuilder = StringBuilder()
            newBuilder.append(oldEntries.substring(0, BLOCK_SIZE))
            oldEntries = oldEntries.substring(BLOCK_SIZE)
            newBlocks.add(newBuilder)
        }
        if (oldEntries.isNotEmpty()) {
            val newBuilder = StringBuilder()
            newBuilder.append(oldEntries)
            newBlocks.add(newBuilder)
        }
        blocks = newBlocks
    }

    override fun insertText(offset: Int, toInsert: String) {
        if (offset > length || offset < 0) {
            throw FileIndexOutOfBoundsException()
        }
        val listOfLengths = blocks.map { it.length }
        var targetBlock = 0
        var targetIndex: Int = offset
        for (i in listOfLengths) {
            if (targetIndex < i) {
                break
            } else {
                targetIndex -= i
                targetBlock += 1
            }
        }
        if (offset == length) {
            val newBuilder: StringBuilder = StringBuilder()
            newBuilder.append(toInsert)
            blocks.add(newBuilder)
        } else {
            blocks[targetBlock].insert(targetIndex, toInsert)
        }
    }

    override fun deleteText(offset: Int, size: Int) {
        if (offset < 0 || size < 0) {
            throw FileIndexOutOfBoundsException()
        }
        val newBlocks: MutableList<StringBuilder> = mutableListOf()
        var index = 0
        var block = 0
        while (block < blocks.size) {
            if (index < offset + size && offset < index + blocks[block].length) {
                if (size == 0) {
                    return
                }
                // Add the prefix
                val prefixSize: Int = offset - index
                if (prefixSize > 0) {
                    newBlocks.add(StringBuilder(blocks[block].substring(0, prefixSize)))
                }
                index += prefixSize
                // If the suffix is in the same block, add it. Otherwise, skip over blocks.
                if (prefixSize + size < blocks[block].length) {
                    newBlocks.add(StringBuilder(blocks[block].substring(prefixSize + size)))
                } else {
                    var deleted: Int = blocks[block].length - prefixSize
                    block++
                    while (block < blocks.size && size - deleted >= blocks[block].length) {
                        deleted += blocks[block].length
                        block++
                    }
                    if (deleted < size) {
                        if (block >= blocks.size) {
                            throw FileIndexOutOfBoundsException()
                        }
                        assert(blocks[block].length > size - deleted)
                        newBlocks.add(StringBuilder(blocks[block].substring(size - deleted)))
                    }
                }
                index += size
            } else {
                newBlocks.add(blocks[block])
                index += blocks[block].length
            }
            block++
        }
        if (offset >= index) {
            if (offset > index || size > 0) {
                throw FileIndexOutOfBoundsException()
            }
        }
        blocks = newBlocks
    }

    override fun toString(): String {
        var entries = ""
        blocks.forEach { entries += it.toString() }
        return entries
    }
}
