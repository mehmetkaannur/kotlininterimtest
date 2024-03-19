package textfiles

import kotlin.reflect.KVisibility
import kotlin.test.Test
import kotlin.test.assertTrue

class GoodPracticesTextFileTests {
    @Test
    fun testClassIsPublic() {
        assertTrue(TextFile::class.visibility!! == KVisibility.PUBLIC)
    }

    @Test
    fun testAllMembersFinal() {
        checkAllOtherMembersFinal(
            TextFile::class,
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
            TextFile::class,
            "deleteText",
            "length",
            "insertText",
            "toString",
            "compareTo",
            "equals",
            "hashCode",
        )
    }
}
