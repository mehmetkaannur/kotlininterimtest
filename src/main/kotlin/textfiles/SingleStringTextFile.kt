package textfiles

class SingleStringTextFile(initialText: String) : TextFile {

    private val stringBuilder: StringBuilder = StringBuilder()

    init {
        stringBuilder.append(initialText)
    }

    override val length: Int
        get() = stringBuilder.length

    override fun insertText(offset: Int, toInsert: String) {
        if (offset < 0 || offset > length) {
            throw FileIndexOutOfBoundsException()
        }
        stringBuilder.insert(offset, toInsert)
    }

    override fun deleteText(offset: Int, size: Int) {
        if (offset < 0 || size < 0 || (offset + size) > length) {
            throw FileIndexOutOfBoundsException()
        }
        stringBuilder.deleteRange(offset, offset + size)
    }

    override fun toString(): String {
        return stringBuilder.toString()
    }
}
