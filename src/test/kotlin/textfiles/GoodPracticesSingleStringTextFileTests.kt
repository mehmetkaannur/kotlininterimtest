package textfiles

import kotlin.reflect.KVisibility
import kotlin.test.Test
import kotlin.test.assertTrue

class GoodPracticesSingleStringTextFileTests {
    @Test
    fun testClassIsFinal() {
        assertTrue(SingleStringTextFile::class.isFinal)
    }

    @Test
    fun testClassIsPublic() {
        assertTrue(SingleStringTextFile::class.visibility!! == KVisibility.PUBLIC)
    }

    @Test
    fun testAllMembersFinal() {
        checkAllOtherMembersFinal(
            SingleStringTextFile::class,
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
            SingleStringTextFile::class,
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
