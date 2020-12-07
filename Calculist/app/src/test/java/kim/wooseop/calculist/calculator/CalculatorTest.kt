package kim.wooseop.calculist.calculator

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CalculatorTest {

    @Test
    fun shouldConcatOnDigitInput() {
        Array(10) { it.toString()[0] }.forEach { c ->
            val calc = CalculatorStub

            calc.input(c)

            assertEquals(Call("concat", listOf(c)), calc.lastCall)
        }
    }

    @Test
    fun shouldConcatOnMathSymbolInput() {
        arrayOf('+', '-', '×', '÷').forEach { c ->
            val calc = CalculatorStub

            calc.input(c)

            assertEquals(Call("concat", listOf(c)), calc.lastCall)
        }
    }

    @Test
    fun shouldComputeOnEqualSignInput() {
        val calc = CalculatorStub
        val c = '='

        calc.input(c)

        assertEquals(Call("compute"), calc.lastCall)
    }

    @Test
    fun shouldClearOnLetterCInput() {
        val calc = CalculatorStub
        val c = 'C'

        calc.input(c)

        assertEquals(Call("clear"), calc.lastCall)
    }

    @Test
    fun shouldSetRepresentationValueOnUpdate() {
        val representation = "what"
        val calc = CalculatorStub

        calc.triggerUpdate(representation)

        assertEquals(representation, calc.representation)
    }

    @Test
    fun shouldNotifyListenersOnUpdate() {
        var notified = false
        val calc = CalculatorStub

        calc.onUpdate { notified = true }
        calc.triggerUpdate("hey")

        assertTrue(notified)
    }

    @Test
    fun shouldNotifyListenerWhenSyncedFirst() {
        var notified = false
        val calc = CalculatorStub

        calc.sync { notified = true }

        assertTrue(notified)
    }


    @Test
    fun shouldNotifyListenersOnUpdateIfSynced() {
        var synced = false
        var notified = false
        val calc = CalculatorStub

        calc.sync {
            notified = synced and true
            synced = true
        }
        calc.triggerUpdate(":)")

        assertTrue(notified)
    }

    object CalculatorStub : Calculator(null) {

        lateinit var lastCall: Call

        override fun concat(c: Char) {
            lastCall = Call("concat", listOf(c))
        }

        override fun compute() {
            lastCall = Call("compute")
        }

        override fun clear() {
            lastCall = Call("clear")
        }

        fun triggerUpdate(s: String) {
            super.update(s)
        }

    }

    data class Call(val name: String, val argument: List<Any>? = null)

}
