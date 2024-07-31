package base.helper

import android.app.ActivityManager
import android.content.Context
import android.os.Process

object ProcessUtils {
    /**
     * 获取当前进程名
     *
     * @param context 上下文
     */
    fun getProgressName(context: Context): String? {
        val pid = Process.myPid()
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        activityManager.runningAppProcesses.forEach { process ->
            if (process.pid == pid) {
                return process.processName
            }
        }
        return null
    }

    /**
     * 获取主进程名
     *
     * @param context 上下文
     */
    fun getMainProcessName(context: Context): String? {
        return context.packageManager.getApplicationInfo(context.packageName, 0).processName
    }

    /**
     * 判断是否为主进程
     *
     * @param context 上下文
     * @return true是主进程
     */
    fun isMainProcess(context: Context): Boolean {
        return getProgressName(context) == getMainProcessName(context)
    }
}