package kim.wooseop.calculist.calculator.internal

enum class Operation(val symbol: Char) {

    ADD('+') {
        override fun consume(a: Int, b: Int) = a + b
    },
    SUBTRACT('-') {
        override fun consume(a: Int, b: Int) = a - b
    },
    MULTIPLY('×') {
        override fun consume(a: Int, b: Int) = a * b
    },
    DIVIDE('÷') {
        override fun consume(a: Int, b: Int) = a / b
    },
    ;

    companion object {

        val symbolMap = Operation.values()
            .map { it.symbol to it }
            .toMap()

    }

    abstract fun consume(a: Int, b: Int): Int

}