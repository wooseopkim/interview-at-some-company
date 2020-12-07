package kim.wooseop.calculist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kim.wooseop.calculist.calculator.CalculatorFactory
import kim.wooseop.calculist.calculator.CalculatorType
import kim.wooseop.calculist.databinding.DisplayFragmentBinding
import kim.wooseop.calculist.persistence.Preferences
import kim.wooseop.calculist.vm.CalculatorViewModel
import kim.wooseop.calculist.vm.TypedViewModelProvider


class DisplayFragment : Fragment() {

    companion object {

        private const val TYPE = "type"

        fun newInstance(type: CalculatorType) = DisplayFragment().apply {
            arguments = Bundle().apply {
                putSerializable(TYPE, type)
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DisplayFragmentBinding.inflate(inflater, container, false)
        binding.setLifecycleOwner(this)
        binding.vm = TypedViewModelProvider.of(activity!!, ::factory).get()
        return binding.root
    }

    private fun factory(): CalculatorViewModel {
        val prefs = Preferences.from(context!!)
        val type = arguments!!.getSerializable(TYPE) as CalculatorType
        val calc = CalculatorFactory.fromPrefs(prefs, type)
        return CalculatorViewModel(calc)
    }

}
