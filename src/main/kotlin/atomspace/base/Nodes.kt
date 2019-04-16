package atomspace.base

import atomspace.core.Node

class NumberNode(val value: Double) : Node() {
    override val name = value.toString()
}