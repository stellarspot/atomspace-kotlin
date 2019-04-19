package atomspace.base

import atomspace.core.Atom
import atomspace.core.AtomSpace
import atomspace.core.Link
import atomspace.core.Node
import java.lang.Exception

abstract class BooleanNode : Node()

object TrueNode : BooleanNode()
object FalseNode : BooleanNode()

class AndLink(override vararg val values: Atom) : Link()

fun Boolean.toNode() = when (this) {
    true -> TrueNode
    else -> FalseNode
}

fun andAction(atomspace: AtomSpace, atom: Atom): Atom = when (atom) {
    is AndLink -> atom
            .values
            .map { atomspace.execute(it) }
            .map {
                when (it) {
                    TrueNode -> true
                    FalseNode -> false
                    else -> throw Exception("Atom is not BooleanNode: $it")
                }
            }
            .reduce { acc, value -> acc && value }
            .toNode()
    else -> exception("Action And is not applicable for atom: $atom")
}