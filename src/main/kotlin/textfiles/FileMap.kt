package textfiles

//Key is File Name
//Value is Text File
typealias Bucket = MutableList<Pair<String, TextFile>>

const val INITIAL_MAP_SIZE = 4
const val RESIZE_CONSTANT = 0.75

class FileMap {

    private var buckets: ArrayList<Bucket> = ArrayList(INITIAL_MAP_SIZE)



    var size: Int = INITIAL_MAP_SIZE
        private set(value) {
            field = value
            if (value > buckets.size * RESIZE_CONSTANT) {
                this.resize()
            }
        }

    private fun getBucketIndex(fileName: String): Int {
        return fileName.hashCode() % size
    }

    private fun resize() {
        val oldValues = buckets.flatten()
        size *= 2
        buckets = ArrayList(size)
        oldValues.forEach { this[it.first] = it.second }
    }

    operator fun get(fileName: String): TextFile? {
        val targetBucket = buckets[getBucketIndex(fileName)]
        for (pair in targetBucket) {
            if (pair.first == fileName) {
                return pair.second
            }
        }
        return null
    }

    operator fun set(fileName: String, textFile: TextFile) {
        val targetBucket = buckets[getBucketIndex(fileName)]
        for (pair in targetBucket) {
            if (pair.first == fileName) {
                targetBucket.remove(pair)
                targetBucket.add(Pair(fileName, textFile))
                return
            }
        }
        targetBucket.add(Pair(fileName, textFile))
        size++
    }

    operator fun iterator(): Iterator<Pair<String, TextFile>> {
        var bucketIndex = 0
        var index = 0
        return object : Iterator<Pair<String, TextFile>> {
            override fun hasNext(): Boolean {
                return bucketIndex < size
            }

            override fun next(): Pair<String, TextFile> {
                if (buckets[bucketIndex].size <= index) {
                    index++
                    return buckets[bucketIndex][index - 1]
                } else {
                    while (buckets[bucketIndex].isEmpty()) {
                        bucketIndex++
                    }
                    index = 1
                    return buckets[bucketIndex][index]
                }
            }
        }
    }
}
