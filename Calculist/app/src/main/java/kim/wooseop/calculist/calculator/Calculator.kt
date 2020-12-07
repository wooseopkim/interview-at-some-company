package kim.wooseop.calculist.calculator

abstract class Calculator(
    @Suppress("UNUSED_PARAMETER")
    initialInput: String?
) {

    private val listeners = mutableListOf<(String?) -> Unit>()

    private var _representation: String? = null
    val representation
        get() = _representation

    fun input(c: Char) {
        when (c) {
            'C' -> clear()
            '=' -> compute()
            else -> concat(c)
        }
    }

    fun onUpdate(listener: (String?) -> Unit) {
        listeners.add(listener)
    }

    fun sync(listener: (String?) -> Unit) {
        notify(listener)
        onUpdate(listener)
    }

    protected fun update(representation: String?) {
        this._representation = representation
        listeners.forEach { notify(it) }
    }

    private fun notify(listener: (String?) -> Unit) {
        listener(representation)
    }

    abstract fun clear()

    protected abstract fun concat(c: Char)

    protected abstract fun compute()

}
