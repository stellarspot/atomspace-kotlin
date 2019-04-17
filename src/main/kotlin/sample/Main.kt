package sample

import atomspace.base.BaseAtomSpace
import atomspace.base.NumberNode
import atomspace.base.SumLink
import atomspace.base.initBaseAtomSpace

fun main(args: Array<String>) {

    val baseAtomSpace = BaseAtomSpace()
    initBaseAtomSpace(baseAtomSpace)


    val sum = SumLink(NumberNode(1.0), NumberNode(2.0))
    val result = baseAtomSpace.execute(sum)

    println("execute: $sum = $result")
}