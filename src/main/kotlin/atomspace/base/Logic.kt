package atomspace.base

import atomspace.core.*

abstract class BooleanNode : Node()

object TrueNode : BooleanNode()
object FalseNode : BooleanNode()

class AndLink(override vararg val values: Atom) : Link()

fun Boolean.toBooleanNode() = when (this) {
    true -> TrueNode
    else -> FalseNode
}


fun BooleanNode.toBoolean(): Boolean = this == TrueNode

class FuzzyBooleanNode(override val value: Double) : ObjectNode()

val TrueFuzzyBooleanNode = FuzzyBooleanNode(1.0)
val FalseFuzzyBooleanNode = FuzzyBooleanNode(0.0)

fun Boolean.toFuzzyBooleanNode(): FuzzyBooleanNode = when (this) {
    true -> TrueFuzzyBooleanNode
    else -> FalseFuzzyBooleanNode
}

fun Double.toFuzzyBooleanNode(): FuzzyBooleanNode =
        if (this == 1.0)
            TrueFuzzyBooleanNode
        else if (this == 0.0)
            FalseFuzzyBooleanNode
        else if (this in 0.0..1.0)
            FuzzyBooleanNode(this)
        else
            throw Exception("Value is out of range: $this")


fun BaseAtomSpace.initLogic() {
    this.addAction(atomType(AndLink::class), ::andAction)
}

fun BaseAtomSpace.initFuzzyLogic() {
    this.addAction(atomType(AndLink::class), ::andFuzzyAction)
}

fun andAction(atomspace: AtomSpace, atom: Atom): Atom = when (atom) {
    is AndLink -> {
        var containsTrueNode = false
        atom
                .values
                .forEach {
                    val node = atomspace.execute(it)
                    when (node) {
                        TrueNode -> containsTrueNode = true
                        FalseNode -> return FalseNode
                        else -> throw Exception("Atom is not BooleanNode: $it")
                    }
                }
        containsTrueNode.toBooleanNode()
    }
    else -> exception("Action And is not applicable for atom: $atom")
}

fun andFuzzyAction(atomspace: AtomSpace, atom: Atom): Atom = when (atom) {
    is AndLink -> atom
            .values
            .map {
                val node = atomspace.execute(it)
                when (node) {
                    is FuzzyBooleanNode -> {
                        val value = node.value
                        if (value == 0.0) {
                            return FalseFuzzyBooleanNode
                        }
                        value
                    }
                    else -> throw Exception("Atom is not FuzzyBooleanNode: $node")
                }
            }
            .reduce { acc, value -> Math.min(acc, value) }
            .toFuzzyBooleanNode()
    else -> exception("Action Fuzzy And is not applicable for atom: $atom")
}