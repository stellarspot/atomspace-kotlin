package sample

import atomlang.LangAtomSpace
import atomlang.PrintlnLink
import atomlang.StringNode
import atomlang.getLangAtomSpace


fun main() {
    val program = PrintlnLink(
            StringNode("Hello,"),
            StringNode(" World!")
    )

    val langAtomSpace = getLangAtomSpace()

    langAtomSpace.execute(program)
}