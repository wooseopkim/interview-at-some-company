package kim.wooseop.calculist.calculator.internal

import org.junit.Assert.*
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.lang.IllegalStateException

@Suppress("UNUSED")
class NodeTest {

    class IntegerTest {

        @Test
        fun shouldEvaluateToGivenValue() {
            val value = 99
            val node = Node.Integer(value)

            val evaluated = node.evaluate()

            assertEquals(value, evaluated)
        }

        @Test
        fun shouldMakeStringOfGivenValue() {
            val value = 12
            val node = Node.Integer(value)

            val string = node.makeString()

            assertEquals(string, value.toString())
        }

    }

    class OperationTest {

        @Test
        fun shouldThrowExceptionWhenEvaluatedWithOperandMissing() {
            val op = mock(Operation::class.java)
            val node = Node.Operator(op).apply {
                left = Node.Integer(7)
            }

            var thrown: Exception? = null
            try {
                node.evaluate()
            } catch (e: IllegalStateException) {
                thrown = e
            } finally {
                assertNotNull(thrown)
            }
        }

        @Test
        fun shouldEvaluateToOperationResult() {
            val result = 42
            val op = mock(Operation::class.java).apply {
                `when`(consume(anyInt(), anyInt())).thenReturn(result)
            }
            val node = Node.Operator(op).apply {
                left = Node.Integer(0)
                right = Node.Integer(0)
            }

            val evaluated = node.evaluate()

            assertEquals(op.consume(1, 2), evaluated)
        }

        @Test
        fun shouldMakeStringOfOperatorSymbol() {
            val symbol = '!'
            val op = mock(Operation::class.java).apply {
                `when`(this.symbol).thenReturn(symbol)
            }
            val node = Node.Operator(op).apply {
                left = Node.Integer(0)
                right = Node.Integer(0)
            }

            val string = node.makeString()

            assertEquals(symbol.toString(), string)
        }

    }

}
