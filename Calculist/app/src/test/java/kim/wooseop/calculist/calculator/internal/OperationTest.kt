package kim.wooseop.calculist.calculator.internal

import org.junit.Assert.assertEquals
import org.junit.Test

class OperationTest {

    @Test
    fun shouldEvaluateToResultValue() {
        val a = 3
        val b = 5
        Operation.values().forEach {
            val result = when (it) {
                Operation.ADD -> a + b
                Operation.SUBTRACT -> a - b
                Operation.MULTIPLY -> a * b
                Operation.DIVIDE -> a / b
            }

            assertEquals(result, it.consume(a, b))
        }
    }

}
