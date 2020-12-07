package kim.wooseop.calculist.vm

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

@Suppress("UNCHECKED_CAST")
class TypedViewModelProvider<VM : ViewModel> protected constructor(
    val activity: FragmentActivity,
    val factory: () -> VM,
    val clazz: Class<VM>
) {

    companion object {

        inline fun <reified VM : ViewModel> of(
            activity: FragmentActivity,
            noinline factory: () -> VM
        ) = TypedViewModelProvider(activity, factory, VM::class.java)

    }

    fun get(): VM {
        val factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>) = factory() as T
        }
        val provider =  ViewModelProviders.of(activity, factory)
        val vm = provider.get(clazz)
        return vm
    }

}
