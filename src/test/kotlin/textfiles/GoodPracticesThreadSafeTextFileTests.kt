package textfiles

import kotlin.reflect.KVisibility
import kotlin.test.Test
import kotlin.test.assertTrue

class GoodPracticesThreadSafeTextFileTests {
    @Test
    fun testClassIsFinal() {
        assertTrue(ThreadSafeTextFile::class.isFinal)
    }

    @Test
    fun testClassIsPublic() {
        assertTrue(ThreadSafeTextFile::class.visibility!! == KVisibility.PUBLIC)
    }

    @Test
    fun testAllMembersFinal() {
        checkAllOtherMembersFinal(
            ThreadSafeTextFile::class,
            "length",
            "deleteText",
            "insertText",
            "toString",
            "compareTo",
            "equals",
            "hashCode",
        )
    }

    @Test
    fun testAllHelperMembersPrivate() {
        checkAllOtherMembersPrivate(
            ThreadSafeTextFile::class,
            "length",
            "deleteText",
            "insertText",
            "toString",
            "compareTo",
            "equals",
            "hashCode",
        )
    }
}
