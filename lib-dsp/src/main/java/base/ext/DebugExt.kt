package base.ext

import android.content.Context
import android.content.pm.ApplicationInfo
import androidx.fragment.app.Fragment

/**
 * 检查当前是否为Debug模式
 */
fun Context.isDebug() = try {
    (applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE) != 0
} catch (e: Exception) {
    false
}

/**
 * 检查当前是否为Debug模式
 */
fun Fragment.isDebug(): Boolean = requireContext().isDebug()