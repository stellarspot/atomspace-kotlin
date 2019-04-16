package atomspace.core

import kotlin.reflect.KClass

open class Atom {
    val type: String = javaClass.canonicalName
}

abstract class Node : Atom() {

    abstract val name: String

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        if (other !is Node) {
            return false
        }

        return this.name == other.name
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }

    override fun toString() = buildString {
        append(type)
        append("(")
        append(name)
        append(")")
    }
}

abstract class Link : Atom() {

    abstract val values: List<Atom>

    override fun toString() = buildString {
        append(type)
        append("(")
        append(values.joinToString(","))
        append(")")
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        if (other !is Link) {
            return false
        }

        return this.values == other.values
    }

    override fun hashCode(): Int {
        return values.hashCode()
    }
}

interface AtomSpace {
    fun execute(atom: Atom): Atom
}


fun atomType(kclass: KClass<out Atom>): String = kclass.qualifiedName!!