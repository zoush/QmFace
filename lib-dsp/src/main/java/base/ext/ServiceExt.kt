package base.ext

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment

/**
 * 启动Service
 *
 * @param T      目的Service类
 * @param bundle 启动所携带的信息
 */
inline fun <reified T : Service> Context.startService(bundle: Bundle? = null) {
    startService(Intent(this, T::class.java).apply {
        bundle?.let { putExtras(bundle) }
    })
}

/**
 * 启动Service
 *
 * @param T      目的Service类
 * @param bundle 启动所携带的信息
 */
inline fun <reified T : Service> Fragment.startService(bundle: Bundle? = null) {
    requireActivity().startService<T>(bundle)
}