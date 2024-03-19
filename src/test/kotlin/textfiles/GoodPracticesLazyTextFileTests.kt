package textfiles

import kotlin.reflect.KVisibility
import kotlin.test.Test
import kotlin.test.assertTrue

class GoodPracticesLazyTextFileTests {
    @Test
    fun testClassIsFinal() {
        assertTrue(LazyTextFile::class.isFinal)
    }

    @Test
    fun testClassIsPublic() {
        assertTrue(LazyTextFile::class.visibility!! == KVisibility.PUBLIC)
    }

    @Test
    fun testAllHelperMembersPrivate() {
        checkAllOtherMembersPrivate(
            LazyTextFile::class,
            "toString",
            "equals",
            "hashCode",
            "compareTo",
            "deleteText",
            "insertText",
            "length",
        )
    }
}
