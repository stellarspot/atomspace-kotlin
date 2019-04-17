package atomspace.base

import org.junit.Test
import kotlin.test.assertEquals


class BaseAtomSpaceTest {

    @Test
    fun testSum() {

        val baseAtomSpace = BaseAtomSpace()
        initBaseAtomSpace(baseAtomSpace)

        val sum = SumLink(NumberNode(1.0), NumberNode(2.0))
        assertEquals(NumberNode(3.0), baseAtomSpace.execute(sum))
    }

    @Test
    fun testEquals() {
        assertEquals(SumLink(NumberNode(1.0)), SumLink(NumberNode(1.0)))
        assertEquals(
                SumLink(NumberNode(1.0), NumberNode(2.0)),
                SumLink(NumberNode(1.0), NumberNode(2.0))
        )
    }
}