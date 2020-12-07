package kim.wooseop.calculist.calculator

import kim.wooseop.calculist.persistence.Preferences

object CalculatorFactory {

    fun fromPrefs(prefs: Preferences, type: CalculatorType): SimpleCalculator {
        val create = when (type) {
            CalculatorType.SIMPLE -> ::SimpleCalculator
        }
        return create(prefs.computed).apply {
            onUpdate { prefs.computed = it }
        }
    }

}
