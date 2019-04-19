package atomspace.base

import atomspace.core.Atom
import atomspace.core.AtomSpace
import atomspace.core.Node
import atomspace.core.atomType

class BaseAtomSpace : AtomSpace {

    private val actionMap = mutableMapOf<String, (AtomSpace, Atom) -> Atom>()

    fun addAction(atomType: String, action: (AtomSpace, Atom) -> Atom) {
        actionMap[atomType] = action
    }

    override fun execute(atom: Atom): Atom {

        if (atom is Node) {
            return atom
        }

        val action = actionMap.getOrElse(atom.type) {
            exception("No registered action for atom: $atom")
        }

        return action(this, atom)
    }
}

fun exception(msg: String): Nothing = throw AtomSpaceException(msg)

class AtomSpaceException(msg: String) : java.lang.Exception(msg)

fun BaseAtomSpace.init() {

    val actions = mapOf<String, (AtomSpace, Atom) -> Atom>(
            atomType(SumLink::class) to ::sumAction,
            atomType(AndLink::class) to ::andAction
    )

    actions.forEach { type, action -> this.addAction(type, action) }
}