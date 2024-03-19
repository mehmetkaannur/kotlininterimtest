package textfiles

import kotlin.reflect.KVisibility
import kotlin.test.Test
import kotlin.test.assertTrue

class GoodPracticesMultiStringTextFileTests {
    @Test
    fun testClassIsFinal() {
        assertTrue(MultiStringTextFile::class.isFinal)
    }

    @Test
    fun testClassIsPublic() {
        assertTrue(MultiStringTextFile::class.visibility!! == KVisibility.PUBLIC)
    }

    @Test
    fun testAllMembersFinal() {
        checkAllOtherMembersFinal(
            MultiStringTextFile::class,
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
            MultiStringTextFile::class,
            "length",
            "deleteText",
            "insertText",
            "toString",
            "compareTo",
            "equals",
            "hashCode",
            "numBlocks",
            "rebalance",
        )
    }
}
