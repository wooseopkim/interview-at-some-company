package kim.wooseop.calculist.helper

import android.content.Context
import android.content.SharedPreferences
import org.mockito.ArgumentMatchers
import org.mockito.Mockito

object Mocks {

    private val prefs = mutableMapOf<String, SharedPreferences>()

    fun context(): Context {
        return Mockito.mock(Context::class.java).apply {
            Mockito.`when`(getSharedPreferences(ArgumentMatchers.anyString(), ArgumentMatchers.anyInt())).then {
                val key = it.getArgument<String>(0)

                prefs.computeIfAbsent(key) { prefs() }
            }
        }
    }

    fun prefs(): SharedPreferences {
        return Mockito.mock(SharedPreferences::class.java).apply {
            val map = mutableMapOf<String, String?>()
            val editor = editor(map)

            Mockito.`when`(getString(ArgumentMatchers.anyString(), ArgumentMatchers.any())).then {
                val key = it.getArgument<String>(0)
                val default = it.getArgument<String?>(1)

                map.getOrDefault(key, default)
            }

            Mockito.`when`(edit()).thenReturn(editor)
        }
    }

    fun editor(map: MutableMap<String, String?>): SharedPreferences.Editor {
        return Mockito.mock(SharedPreferences.Editor::class.java).apply {
            val edit = mutableMapOf<String, String?>()

            Mockito.`when`(putString(ArgumentMatchers.anyString(), ArgumentMatchers.any())).then {
                val key = it.getArgument<String>(0)
                val value = it.getArgument<String?>(1)

                edit.put(key, value)
                this
            }

            Mockito.`when`(apply()).then { map.putAll(edit) }
        }
    }

}
