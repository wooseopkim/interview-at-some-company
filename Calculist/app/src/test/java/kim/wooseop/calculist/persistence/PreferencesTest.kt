package kim.wooseop.calculist.persistence

import android.content.Context
import kim.wooseop.calculist.helper.Mocks
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class PreferencesTest {

    private lateinit var ctx: Context
    private lateinit var subject: Preferences

    @Before
    fun initialize() {
        ctx = Mocks.context()
        subject = Preferences.from(ctx)
    }

    @Test
    fun shouldStoreComputedToSharedPreferences() {
        val computed = "foobar"

        subject.computed = computed

        val prefs = ctx.getSharedPreferences(Preferences.Keys.PREFS, 0)
        val stored = prefs.getString(Preferences.Keys.COMPUTED, null)
        Assert.assertEquals(computed, stored)
    }

    @Test
    fun shouldRetrieveComputedFromSharedPreferences() {
        val computed = "foobar"
        val prefs = ctx.getSharedPreferences(Preferences.Keys.PREFS, 0)
        prefs.edit().putString(Preferences.Keys.COMPUTED, computed).apply()

        val restored = subject.computed

        Assert.assertEquals(computed, restored)
    }

}