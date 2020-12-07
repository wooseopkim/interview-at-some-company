package kim.wooseop.calculist.calculator

import kim.wooseop.calculist.helper.Mocks
import kim.wooseop.calculist.persistence.Preferences
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CalculatorFactoryTest {

    private lateinit var prefs: Preferences

    val initialValue = "3141592"

    @Before
    fun initialize() {
        prefs = Preferences.from(Mocks.context())
    }

    @Test
    fun shouldCreateSimpleCalculatorWithInitialValueFromPrefs() {
        prefs.computed = initialValue

        val calc = CalculatorFactory.fromPrefs(prefs, CalculatorType.SIMPLE)

        assertEquals(initialValue, calc.representation)
    }

    @Test
    fun shouldCreateSimpleCalculatorThatUpdatesPrefs() {
        prefs.computed = initialValue
        val input = '6'

        val calc = CalculatorFactory.fromPrefs(prefs, CalculatorType.SIMPLE).apply { input(input) }

        assertEquals(calc.representation, prefs.computed)
    }

}
