package atomspace.base

import atomspace.core.*

class NumberNode(override val value: Double) : ObjectNode()

fun Double.toNumberNode(): NumberNode = NumberNode(this)

class SumLink(override vararg val values: Atom) : Link()


fun BaseAtomSpace.initMath() {
    this.addAction(atomType(SumLink::class), ::sumAction)
}


fun sumAction(atomspace: AtomSpace, atom: Atom): Atom = when (atom) {
    is SumLink -> atom
            .values
            .map { atomspace.execute(it) }
            .map {
                when (it) {
                    is NumberNode -> it.value
                    else -> exception("Wrong NumberNodeType")
                }
            }.sum().toNumberNode()
    else -> exception("Action Sum is not applicable for atom: $atom")
}