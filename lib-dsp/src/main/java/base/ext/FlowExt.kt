package base.ext

import android.util.SparseArray
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

inline fun <T> Flow<T>.collectIn(owner: LifecycleOwner, repeatOnLifecycle: Boolean = true, crossinline block: suspend (T) -> Unit) {
    owner.lifecycleScope.launch {
        if (repeatOnLifecycle) {
            owner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                collect { block.invoke(it) }
            }
        }else {
            collect { block.invoke(it) }
        }
    }
}

inline fun <T> Flow<T>.collectIn(scope: CoroutineScope, crossinline block: suspend (T) -> Unit) {
    scope.launch {
        collect { block.invoke(it) }
    }
}

fun<T> SparseArray<Pair<T, String>>.getIndex(cfg: T): Int {
    var i = 0
    for (j in 0 until size()) {
        if (get(j).first == cfg) {
            i = j
            break
        }
    }
    return i
}

