package kim.wooseop.calculist.calculator

import kim.wooseop.calculist.MainActivity
import kim.wooseop.calculist.calculator.helper.Scenario
import kim.wooseop.calculist.ui.CalculatorFragment
import kim.wooseop.calculist.ui.DisplayFragment
import org.junit.Assert.assertTrue
import org.junit.Test

class MainActivityTest {

    val scenario = Scenario.ofActivity<MainActivity>()

    @Test
    fun shouldRenderDisplayFragment() = scenario.play {
        val fragments = it.supportFragmentManager.fragments

        val instantiated = fragments.map { it::class }

        assertTrue(instantiated.any { it == DisplayFragment::class })
    }

    @Test
    fun shouldRenderCalculatorFragment() = scenario.play {
        val fragments = it.supportFragmentManager.fragments

        val instantiated = fragments.map { it::class }

        assertTrue(instantiated.any { it == CalculatorFragment::class })
    }

}
