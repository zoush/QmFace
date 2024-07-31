package base.lifecycle

import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import log.Logs

internal object UninitializedValue

class AutoClearProperty<T>(
    private val lifecycleOwnerProvider: () -> LifecycleOwner?,
    private val initializer: () -> T
) : Lazy<T> {
    private var _value: Any? = UninitializedValue

    override val value: T
        get() {
            if (!isInitialized()) {
                try {
                    lifecycleOwnerProvider()
                } catch (e: Exception) {
                    Logs.common.e("call lifecycleOwnerProvider() failed!", e)
                    throw e
                }?.lifecycle?.let { lifecycle ->
                    if (!lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
                        throw IllegalStateException(
                            "value cannot be accessed before ${Lifecycle.State.INITIALIZED} " +
                                "or after ${Lifecycle.State.DESTROYED}, " +
                                "currentState is ${lifecycle.currentState}."
                        )
                    }

                    lifecycle.addObserver(object : LifecycleObserver {
                        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
                        fun onDestroy() {
                            lifecycle.removeObserver(this)
                            _value = UninitializedValue
                        }
                    })
                }

                _value = try {
                    initializer()
                } catch (e: Exception) {
                    Logs.common.e("call initializer() failed!", e)
                    throw e
                }
            }

            @Suppress("UNCHECKED_CAST")
            return _value as T
        }

    override fun isInitialized(): Boolean = _value !== UninitializedValue
}

/**
 * Creates a new instance,
 * auto cleared onDestroy from lifecycleOwner
 */
inline fun <T> autoClear(
    crossinline lifecycleOwnerProvider: () -> LifecycleOwner,
    crossinline initializer: () -> T
): Lazy<T> = AutoClearProperty<T>(
    lifecycleOwnerProvider = { lifecycleOwnerProvider() },
    initializer = { initializer() }
)

/**
 * Creates a new instance in Fragment,
 * auto cleared onDestroyView
 */
inline fun <T> Fragment.autoClear(
    crossinline initializer: () -> T
): Lazy<T> = autoClear(
    lifecycleOwnerProvider = { viewLifecycleOwner },
    initializer = initializer
)

/**
 * Creates a new instance in Activity,
 * auto cleared onDestroy
 */
inline fun <T> ComponentActivity.autoClear(
    crossinline initializer: () -> T
): Lazy<T> = autoClear(
    lifecycleOwnerProvider = { this },
    initializer = initializer
)