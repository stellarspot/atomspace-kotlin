package atomspace.base

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals


class BaseLogicTest {

    @Test
    fun testAnd() {

        val baseAtomSpace = BaseAtomSpace()
        baseAtomSpace.init()

        assertEquals(TrueNode, baseAtomSpace.execute(AndLink(TrueNode, TrueNode)))
        assertEquals(FalseNode, baseAtomSpace.execute(AndLink(TrueNode, FalseNode)))
        assertEquals(FalseNode, baseAtomSpace.execute(AndLink(FalseNode, TrueNode)))
        assertEquals(FalseNode, baseAtomSpace.execute(AndLink(FalseNode, FalseNode)))

        assertEquals(TrueNode, baseAtomSpace.execute(AndLink(TrueNode, AndLink(TrueNode, TrueNode))))
        assertEquals(FalseNode, baseAtomSpace.execute(AndLink(TrueNode, AndLink(TrueNode, FalseNode))))
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

    @Test
    fun testFuzzyAnd() {

        val baseAtomSpace = BaseAtomSpace()
        baseAtomSpace.init()
        baseAtomSpace.initFuzzyLogic()

        assertEquals(
                FuzzyBooleanNode(0.3),
                baseAtomSpace.execute(
                        AndLink(
                                FuzzyBooleanNode(0.3),
                                FuzzyBooleanNode(0.4))
                )
        )

        assertEquals(
                true.toFuzzyBooleanNode(),
                baseAtomSpace.execute(
                        AndLink(
                                true.toFuzzyBooleanNode(),
                                true.toFuzzyBooleanNode())
                )
        )

        assertEquals(
                false.toFuzzyBooleanNode(),
                baseAtomSpace.execute(
                        AndLink(
                                true.toFuzzyBooleanNode(),
                                false.toFuzzyBooleanNode())
                )
        )
    }

    @Test
    fun testFuzzyEquals() {

        assertEquals(
                AndLink(FuzzyBooleanNode(0.3)),
                AndLink(FuzzyBooleanNode(0.3))
        )

        assertNotEquals(
                AndLink(FuzzyBooleanNode(0.3)),
                AndLink(FuzzyBooleanNode(0.4))
        )

        assertEquals(
                AndLink(FuzzyBooleanNode(0.3), FuzzyBooleanNode(0.4)),
                AndLink(FuzzyBooleanNode(0.3), FuzzyBooleanNode(0.4))
        )

        assertEquals(
                AndLink(TrueFuzzyBooleanNode),
                AndLink(FuzzyBooleanNode(1.0))
        )

        assertEquals(
                AndLink(FalseFuzzyBooleanNode),
                AndLink(FuzzyBooleanNode(0.0))
        )
    }
}