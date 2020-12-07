package kim.wooseop.calculist.vm

import android.view.View
import android.widget.Button
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kim.wooseop.calculist.calculator.Calculator

class CalculatorViewModel(private val calculator: Calculator) : ViewModel() {

    val text: MutableLiveData<String> = MutableLiveData()

    init {
        calculator.sync { text.postValue(it) }
    }

    fun input(v: View) {
        val c = (v as Button).text[0]
        calculator.input(c)
    }

}
