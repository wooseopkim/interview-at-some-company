package kim.wooseop.calculist.vm

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import kim.wooseop.calculist.helper.Mocks
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class TypedViewModelProviderTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    lateinit var activity: FragmentActivity

    @Before
    fun initialize() {
        activity = Mockito.mock(FragmentActivity::class.java).apply {
            val app = Mockito.mock(Application::class.java)
            val prefs = Mocks.prefs()

            Mockito.`when`(application).thenReturn(app)
            Mockito.`when`(viewModelStore).thenReturn(ViewModelStore())
            Mockito.`when`(getSharedPreferences(Mockito.anyString(), Mockito.anyInt())).thenReturn(prefs)
        }
    }

    @Test
    fun shouldInstantiateProvider() {
        val subject =
            TypedViewModelProvider.of(activity) { ViewModelStub() }

        assertEquals(subject::class, TypedViewModelProvider::class)
    }

    @Test
    fun shouldProvideViewModel() {
        val subject =
            TypedViewModelProvider.of(activity) { ViewModelStub() }

        val provided = subject.get()

        assertEquals(provided::class, ViewModelStub::class)
    }

    @Test
    fun shouldProvideViewModelWithGivenFactory() {
        val vm = ViewModelStub()
        val subject = TypedViewModelProvider.of(activity) { vm }

        val provided = subject.get()

        assertEquals(provided, vm)
    }

    class ViewModelStub : ViewModel()

}
