package atomspace.base

import atomspace.core.Atom
import atomspace.core.AtomSpace
import atomspace.core.atomType

class BaseAtomSpace : AtomSpace {

    private val actionMap = mutableMapOf<String, (Atom) -> Atom>()

    fun addAction(atomType: String, action: (Atom) -> Atom) {
        actionMap[atomType] = action
    }

    override fun execute(atom: Atom): Atom {
        val action = actionMap.getOrElse(atom.type) { exception("No registered action") }
        return action(atom)
    }
}

fun exception(msg: String): Nothing = throw AtomSpaceException(msg)

class AtomSpaceException(msg: String) : java.lang.Exception(msg)


fun initBaseAtomSpace(baseAtomSpace: BaseAtomSpace) {

    val actions = mapOf<String, (Atom) -> Atom>(
            atomType(NumberNode::class) to { number -> number },
            atomType(SumLink::class) to { sum ->
                when (sum) {
                    is SumLink ->
                        NumberNode(sum
                                .values
                                .map { baseAtomSpace.execute(it) }
                                .map {
                                    when (it) {
                                        is NumberNode -> it.value
                                        else -> exception("Wrong NumberNodeType")
                                    }
                                }.sum())
                    else -> exception("Wrong Link Type")
                }
            }
    )

    actions.forEach { type, action -> baseAtomSpace.addAction(type, action) }
}