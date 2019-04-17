package atomspace.base

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals


class BaseLogicTest {

    @Test
    fun testSum() {

        val baseAtomSpace = BaseAtomSpace()
        baseAtomSpace.init()

        assertEquals(TrueNode, baseAtomSpace.execute(AndLink(TrueNode, TrueNode)))
        assertEquals(FalseNode, baseAtomSpace.execute(AndLink(TrueNode, FalseNode)))
        assertEquals(FalseNode, baseAtomSpace.execute(AndLink(FalseNode, TrueNode)))
        assertEquals(FalseNode, baseAtomSpace.execute(AndLink(FalseNode, FalseNode)))
    }

    @Test
    fun testEquals() {
        assertEquals(AndLink(TrueNode), AndLink(TrueNode))
        assertEquals(AndLink(FalseNode), AndLink(FalseNode))
        assertNotEquals(AndLink(TrueNode), AndLink(FalseNode))
        assertEquals(
                AndLink(TrueNode, FalseNode),
                AndLink(TrueNode, FalseNode)
        )
    }
}