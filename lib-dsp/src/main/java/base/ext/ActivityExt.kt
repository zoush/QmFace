package base.ext

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment

private var toast: Toast? = null

fun Activity.toast(message: CharSequence) {
    toast?.cancel()
    toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        .apply { show() }
}

/**
 * 启动Activity
 *
 * @param T      目的Activity类
 * @param bundle 启动所携带的信息
 */
inline fun <reified T : Activity> Context.startActivity(bundle: Bundle? = null) {
    startActivity(Intent(this, T::class.java).apply {
        bundle?.let { putExtras(bundle) }
    })
}

/**
 * 启动Activity
 *
 * @param T      目的Activity类
 * @param bundle 启动所携带的信息
 */
inline fun <reified T : Activity> Fragment.startActivity(bundle: Bundle? = null) {
    requireActivity().startActivity<T>(bundle)
}

/**
 * 获取Activity的根视图
 */
fun Activity.findRootView(): View =
    findViewById<ViewGroup>(android.R.id.content).getChildAt(0)

/**
 * 设置粘性沉浸模式
 */
fun Activity.hideSystemBars() {
    // Enables sticky immersive mode.
    // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
    // Or for "regular immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE
    window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            // Set the content to appear under the system bars so that the
            // content doesn't resize when the system bars hide and show.
            or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            // Hide the nav bar and status bar
            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_FULLSCREEN)
}