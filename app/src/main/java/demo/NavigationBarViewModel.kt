package demo

import android.content.Context
import android.content.Intent
import android.os.Process
import androidx.lifecycle.ViewModel

/**
 * 导航栏界面的 ViewModel
 */
class NavigationBarViewModel : ViewModel() {
    private val PACKAGE_SYSTEM_UI = "com.android.systemui"
    private val ACTION_SHOW_NAVI_BAR = "android.systemui.SHOW_NAVI_BAR"
    private val ACTION_HIDE_NAVI_BAR = "android.systemui.HIDE_NAVI_BAR"

    /**
     * 导航栏显示
     */
    fun showNavigationBar(context: Context) {
        val ctx = context.applicationContext
        ctx.sendBroadcast(Intent().apply {
            setPackage(PACKAGE_SYSTEM_UI)
            action = ACTION_SHOW_NAVI_BAR
        })
    }

    /**
     * 导航栏隐藏
     */
    fun hideNavigationBar(context: Context) {
        val ctx = context.applicationContext
        ctx.sendBroadcastAsUser(Intent().apply {
            setPackage(PACKAGE_SYSTEM_UI)
            action = ACTION_HIDE_NAVI_BAR
        }, Process.myUserHandle())
    }
}
