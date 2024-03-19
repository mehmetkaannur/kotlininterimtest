package textfiles

import kotlin.reflect.KVisibility
import kotlin.test.Test
import kotlin.test.assertTrue

class GoodPracticesFileMapTests {
    @Test
    fun testClassIsFinal() {
        assertTrue(FileMap::class.isFinal)
    }

    @Test
    fun testClassIsPublic() {
        assertTrue(FileMap::class.visibility!! == KVisibility.PUBLIC)
    }

    @Test
    fun testAllMembersFinal() {
        checkAllOtherMembersFinal(
            FileMap::class,
            "toString",
            "equals",
            "hashCode",
        )
    }

    @Test
    fun testAllHelperMembersPrivate() {
        checkAllOtherMembersPrivate(
            FileMap::class,
            "toString",
            "equals",
            "hashCode",
            "size",
            "get",
            "set",
            "iterator",
        )
    }
}
