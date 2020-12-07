package kim.wooseop.calculist.calculator

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

class BasicCalculatorTest {

    @Test
    fun shouldInitializeWithGivenValue() {
        val initialValue = "12341234"

        val calc = BasicCalculator(initialValue)

        assertEquals(initialValue, calc.representation)
    }

    @Test
    fun shouldHaveNullDefaultValue() {
        val calc = BasicCalculator()

        assertNull(calc.representation)
    }

    @Test
    fun shouldClearValueWhenRequested() {
        val initialValue = "42"
        val subject = BasicCalculator(initialValue)

        subject.clear()

        assertNull(subject.representation)
    }

    @RunWith(Parameterized::class)
    class InputTest {

        companion object {

            @Suppress("UNUSED")
            @JvmStatic
            @Parameterized.Parameters(name = "{2}")
            fun data() = arrayOf(
                testData(
                    '0',
                    expected = "0",
                    name = "shouldShowDigit"
                ),
                testData(
                    '1', '2',
                    expected = "12",
                    name = "shouldConcatDigit"
                ),
                testData(
                    '1', '+',
                    expected = "1+",
                    name = "shouldNotEvaluateUntilRequested"
                ),
                testData(
                    '1', '+', '1', '=',
                    expected = "2",
                    name = "shouldEvaluateWhenRequested"
                ),
                testData(
                    '1', '2', '+', '1', '-',
                    expected = "12+1-",
                    name = "shouldNotEvaluateUntilRequestedWithTwoOperandsOnOperatorInput"
                ),
                testData(
                    '1', '+', '2', '×', '2', '+', '1', '=',
                    expected = "6",
                    name = "shouldEvaluateByOperationPriorities"
                ),
                testData(
                    '1', '2', '+', '-', '1', '=',
                    expected = "11",
                    name = "shouldChooseLastOneOnMultipleOperatorInputs"
                ),
                testData(
                    '1', '2', '0', '+', '2', '=', 'C',
                    expected = null,
                    name = "shouldClearWhenRequested"
                )
            )


            private fun testData(vararg input: Char, expected: String?, name: String) =
                listOf(input).plus(expected).plus(name).toTypedArray()

        }

        @Parameterized.Parameter(0)
        lateinit var input: CharArray

        @Parameterized.Parameter(1)
        lateinit var expected: String

        @Suppress("UNUSED")
        @Parameterized.Parameter(2)
        lateinit var name: String

        @Test
        fun test() {
            val calc = BasicCalculator()

            input.forEach { calc.input(it) }

            if (::expected.isInitialized) {
                assertEquals(expected, calc.representation)
            } else {
                assertNull(calc.representation)
            }
        }

    }

}
