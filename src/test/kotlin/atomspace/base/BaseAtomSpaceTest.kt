package atomspace.base

import org.junit.Test
import kotlin.test.assertEquals


class BaseAtomSpaceTest {

    @Test
    fun test() {

        val baseAtomSpace = BaseAtomSpace()
        initBaseAtomSpace(baseAtomSpace)

        val sum = SumLink(listOf(NumberNode(1.0), NumberNode(2.0)))
        assertEquals(NumberNode(3.0), baseAtomSpace.execute(sum))
    }
}