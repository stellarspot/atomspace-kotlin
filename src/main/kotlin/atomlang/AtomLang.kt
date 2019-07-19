package atomlang

import atomspace.base.BaseAtomSpace
import atomspace.base.exception
import atomspace.core.*

class VoidNode(override val value: String = "") : ObjectNode()
class DoubleNode(override val value: Double) : ObjectNode()
class StringNode(override val value: String) : ObjectNode()

class PrintlnLink(override vararg val values: Atom) : Link()

class LangAtomSpace : BaseAtomSpace()

fun getLangAtomSpace(): LangAtomSpace {
    val atomSpace = LangAtomSpace()
    atomSpace.init()
    return atomSpace
}

fun LangAtomSpace.init() {

    this.initPrintln()
}


fun LangAtomSpace.initPrintln() {
    this.addAction(atomType(PrintlnLink::class), ::printlnAction)
}

fun printlnAction(atomspace: AtomSpace, atom: Atom) = when (atom) {
    is PrintlnLink -> {
        atom
                .values
                .map {
                    atomspace.execute(it)
                }
                .forEach {
                    val str = when (it) {
                        is ObjectNode -> it.value.toString()
                        else -> it.toString()
                    }
                    print(str)
                }
        println()
        VoidNode()
    }
    else -> exception("Action Println is not applicable for atom: $atom")
}
