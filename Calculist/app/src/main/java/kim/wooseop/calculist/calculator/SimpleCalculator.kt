package kim.wooseop.calculist.calculator

import kim.wooseop.calculist.calculator.internal.Operation
import kim.wooseop.calculist.calculator.internal.Tree

class SimpleCalculator(initialInput: String? = null) : Calculator(initialInput) {

    private val tree = Tree(
        priorities = { it to Tree.DEFAULT_PRIORITY },
        eagerPruning = true
    )

    init {
        initialInput?.forEach(::concat)
    }

    override fun concat(c: Char) {
        if (c in Operation.symbolMap) {
            update()
            tree.add(c)
        } else {
            tree.add(c)
            update()
        }
    }

    override fun compute() {
        update()
    }

    override fun clear() {
        tree.clear()
        update()
    }

    private fun update() {
        update(tree.makeString())
    }

}
