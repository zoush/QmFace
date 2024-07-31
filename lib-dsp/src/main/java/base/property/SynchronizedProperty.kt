package base.property

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class SynchronizedProperty<T>(
    private var value: T
) : ReadWriteProperty<Any, T> {

    private var lock = Any()

    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        synchronized(lock) {
            return value
        }
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        synchronized(lock) {
            this.value = value
        }
    }
}

/**
 * Synchronized委托
 */
fun <T> syncProperty(value: T) = SynchronizedProperty<T>(value)