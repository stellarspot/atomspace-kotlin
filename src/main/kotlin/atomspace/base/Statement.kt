package atomspace.base

import atomspace.core.Atom
import atomspace.core.AtomSpace
import atomspace.core.Link
import atomspace.core.atomType

class ConditionLink(override vararg val values: Atom) : Link()

class IfThenLink(val condition: Atom, val atom: Atom) : Link() {
    override val values = arrayOf(condition, atom)
}

class ElseLink(val atom: Atom) : Link() {
    override val values = arrayOf(atom)
}

fun BaseAtomSpace.initCondition() {
    this.addAction(atomType(ConditionLink::class), ::conditionAction)
}

fun conditionAction(atomspace: AtomSpace, atom: Atom): Atom = when (atom) {
    is ConditionLink -> {
        atom
                .values
                .forEach {
                    when (it) {
                        is IfThenLink -> {
                            val cond = atomspace.execute(it.condition)
                            if (cond is BooleanNode) {
                                if (cond.toBoolean()) {
                                    return atomspace.execute(it.atom)
                                }
                            } else {
                                exception("Given condition is not evaluated to BooleanNode: $cond")
                            }
                        }
                        is ElseLink -> return atomspace.execute(it.atom)
                        else -> throw Exception("Atom is not a IfThen or Else link: $it")
                    }
                }
        exception("Condition has not been found!")
    }
    else -> exception("Action Condition is not applicable for atom: $atom")
}