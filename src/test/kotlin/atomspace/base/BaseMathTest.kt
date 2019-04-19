package atomspace.base

import org.junit.Test
import kotlin.test.assertEquals


class BaseMathTest {

    @Test
    fun testSum() {

        val baseAtomSpace = BaseAtomSpace()
        baseAtomSpace.init()

        assertEquals(
                NumberNode(3.0),
                baseAtomSpace.execute(
                        SumLink(
                                NumberNode(1.0),
                                NumberNode(2.0))))

        assertEquals(
                NumberNode(6.0),
                baseAtomSpace.execute(
                        SumLink(
                                NumberNode(1.0),
                                SumLink(
                                        NumberNode(2.0),
                                        NumberNode(3.0)))))

    }

    @Test
    fun testEquals() {
        assertEquals(SumLink(NumberNode(1.0)), SumLink(NumberNode(1.0)))
        assertEquals(
                SumLink(NumberNode(1.0), NumberNode(2.0)),
                SumLink(NumberNode(1.0), NumberNode(2.0)))
    }
}