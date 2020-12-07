package kim.wooseop.calculist.calculator

import kim.wooseop.calculist.calculator.internal.Node
import kim.wooseop.calculist.calculator.internal.Operation
import kim.wooseop.calculist.calculator.internal.Tree
import kim.wooseop.calculist.calculator.internal.Tree.Companion.DEFAULT_PRIORITY
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

class BasicCalculator(initialInput: String? = null) : Calculator(initialInput) {

    private val tree = Tree(
        priorities = {
            it to when (it) {
                Operation.MULTIPLY, Operation.DIVIDE -> 1
                else -> 0
            }
        },
        eagerPruning = false
    )

    init {
        initialInput?.forEach(::concat)
    }

    override fun concat(c: Char) {
        tree.add(c)
        update()
    }

    override fun compute() {
        tree.prune()
        update()
    }

    override fun clear() {
        tree.clear()
        update()
    }

    private fun update() {
        update(tree.makeString())
    }

    private data class Input(val type: Input.Type, val value: String) {

        enum class Type {
            OPERATOR,
            INTEGER,
        }

    }

}
