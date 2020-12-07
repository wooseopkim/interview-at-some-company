package kim.wooseop.calculist.vm

import android.widget.Button
import kim.wooseop.calculist.calculator.Calculator
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.*

class CalculatorViewModelTest {

    @Test
    fun shouldDelegateInputToCalculator() {
        var actual: String? = null
        val mock = mock(Calculator::class.java).apply {
            `when`(input(anyChar())).then {
                val c = it.getArgument<Char>(0)

                actual = c.toString(); Unit
            }
        }
        val subject = CalculatorViewModel(mock)
        val expected = "9"
        val btn = Mockito.mock(Button::class.java).apply {
            `when`(text).thenReturn(expected)
        }

        subject.input(btn)

        assertEquals(expected, actual)
    }

}
