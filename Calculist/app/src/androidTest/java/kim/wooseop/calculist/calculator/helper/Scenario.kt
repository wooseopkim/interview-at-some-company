package kim.wooseop.calculist.calculator.helper

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.FragmentScenario
import androidx.test.core.app.ActivityScenario

sealed class Scenario<T> {

    companion object {

        inline fun <reified T : Activity> ofActivity() = OfActivity(T::class.java)

        inline fun <reified T : Fragment> ofFragment() = OfFragment(T::class.java)

    }

    abstract fun play(block: (T) -> Unit)

    class OfActivity<T: Activity>(private val clazz: Class<T>) : Scenario<T>() {

        override fun play(block: (T) -> Unit) {
            ActivityScenario.launch(clazz).onActivity(block)
        }

    }

    class OfFragment<T: Fragment>(private val clazz: Class<T>) : Scenario<T>() {

        override fun play(block: (T) -> Unit) = play(null, block)

        fun play(args: Bundle?, block: (T) -> Unit) {
            FragmentScenario.launchInContainer(clazz, args).onFragment(block)
        }

    }

}
