package atomspace.core

import kotlin.reflect.KClass

open class Atom {
    val type: String = javaClass.canonicalName
}

abstract class Node : Atom() {
    override fun toString() = atomType(this.javaClass.kotlin)
}

abstract class ObjectNode : Node() {

    abstract val value: Any

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        if (other !is ObjectNode) {
            return false
        }

        return this.value == other.value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    override fun toString() = buildString {
        append(type)
        append("(")
        append(value)
        append(")")
    }
}

abstract class Link : Atom() {

    abstract val values: Array<out Atom>

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

        return this.values.contentDeepEquals(other.values)
    }

    override fun hashCode(): Int {
        return values.contentDeepHashCode()
    }
}

interface AtomSpace {
    fun execute(atom: Atom): Atom
}


fun atomType(kclass: KClass<out Atom>): String = kclass.qualifiedName!!