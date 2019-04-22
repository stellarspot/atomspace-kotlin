package atomspace.base

import org.junit.Test
import kotlin.test.assertEquals

class ConditionStatementTest {


    @Test
    fun test() {

        val baseAtomSpace = BaseAtomSpace()
        baseAtomSpace.init()

        assertEquals(
                NumberNode(1.0),
                baseAtomSpace.execute(
                        ConditionLink(
                                ElseLink(NumberNode(1.0))
                        )
                )
        )

        assertEquals(
                NumberNode(2.0),
                baseAtomSpace.execute(
                        ConditionLink(
                                IfThenLink(AndLink(TrueNode, TrueNode), NumberNode(2.0)),
                                ElseLink(NumberNode(3.0))
                        )
                )
        )

        assertEquals(
                NumberNode(3.0),
                baseAtomSpace.execute(
                        ConditionLink(
                                IfThenLink(AndLink(TrueNode, FalseNode), NumberNode(2.0)),
                                ElseLink(NumberNode(3.0))
                        )
                )
        )
    }
}