package kim.wooseop.calculist

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import kim.wooseop.calculist.calculator.CalculatorType
import kim.wooseop.calculist.ui.CalculatorFragment
import kim.wooseop.calculist.ui.DisplayFragment

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val type = CalculatorType.SIMPLE

        supportFragmentManager.beginTransaction()
            .replace(R.id.display_placeholder, DisplayFragment.newInstance(type))
            .replace(R.id.calculator_placeholder, CalculatorFragment.newInstance(type))
            .commit()
    }
}
