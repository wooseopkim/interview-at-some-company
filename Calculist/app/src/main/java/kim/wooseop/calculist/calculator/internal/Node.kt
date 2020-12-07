package kim.wooseop.calculist.calculator.internal

sealed class Node {

    var left: Node? = null
    var right: Node? = null

    abstract fun evaluate(): Int

    abstract fun makeString(): String

    data class Integer(var value: Int) : Node() {

        override fun evaluate() = value

        override fun makeString() = value.toString()

    }

    data class Operator(var operation: Operation) : Node() {

        override fun evaluate(): Int {
            val a = left?.evaluate()
            val b = right?.evaluate()
            if (a == null || b == null) {
                throw IllegalStateException()
            }
            return operation.consume(a, b)
        }

        override fun makeString() = operation.symbol.toString()

    }

}