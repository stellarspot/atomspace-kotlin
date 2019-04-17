package sample

import atomspace.base.*

fun main(args: Array<String>) {

    val baseAtomSpace = BaseAtomSpace()
    baseAtomSpace.init()


    val sum = SumLink(NumberNode(1.0), NumberNode(2.0))
    val result = baseAtomSpace.execute(sum)

    println("execute: $sum = $result")
}