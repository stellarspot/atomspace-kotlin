package atomspace.base

import atomspace.core.Atom
import atomspace.core.AtomSpace
import atomspace.core.Link
import atomspace.core.ObjectNode

class NumberNode(val value: Double) : ObjectNode(value)

fun Double.toNode(): NumberNode = NumberNode(this)

class SumLink(override vararg val values: Atom) : Link()

fun sumAction(atomspace: AtomSpace, atom: Atom): Atom = when (atom) {
    is SumLink -> atom
            .values
            .map { atomspace.execute(it) }
            .map {
                when (it) {
                    is NumberNode -> it.value
                    else -> exception("Wrong NumberNodeType")
                }
            }.sum().toNode()
    else -> exception("Action Sum is not applicable for atom: $atom")
}