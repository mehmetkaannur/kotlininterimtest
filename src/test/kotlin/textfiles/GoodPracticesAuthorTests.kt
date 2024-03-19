package textfiles

import kotlin.reflect.KVisibility
import kotlin.test.Test
import kotlin.test.assertTrue

class GoodPracticesAuthorTests {
    @Test
    fun testClassIsFinal() {
        assertTrue(Author::class.isFinal)
    }

    @Test
    fun testClassIsPublic() {
        assertTrue(Author::class.visibility!! == KVisibility.PUBLIC)
    }

    @Test
    fun testAllMembersFinal() {
        checkAllOtherMembersFinal(
            Author::class,
            "toString",
            "equals",
            "hashCode",
            "run",
        )
    }

    @Test
    fun testAllHelperMembersPrivate() {
        checkAllOtherMembersPrivate(
            Author::class,
            "toString",
            "equals",
            "hashCode",
            "run",
        )
    }
}
