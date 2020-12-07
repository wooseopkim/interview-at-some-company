package kim.wooseop.calculist.persistence

import android.content.Context

class Preferences private constructor (context: Context) {

    object Keys {

        const val PREFS = "prefs"
        const val COMPUTED = "computed"

    }

    companion object {

        fun from(context: Context) = Preferences(context)

    }

    private val prefs = context.getSharedPreferences(Keys.PREFS, 0)

    var computed: String?
        get() = prefs.getString(Keys.COMPUTED, null)
        set(value) = prefs.edit().putString(Keys.COMPUTED, value).apply()

}
