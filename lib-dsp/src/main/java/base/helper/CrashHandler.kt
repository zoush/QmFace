package base.helper

import android.content.Context
import android.os.Build
import android.os.Debug
import android.os.Environment
import android.os.Process
import log.Logs
import java.io.File
import java.io.FileOutputStream
import java.io.PrintWriter
import java.io.StringWriter
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class CrashHandler(
    context: Context,
    private val versionName: String,
    private val versionCode: Int,
    private val buildTime: String,
    private val applicationStartTime: Long
) : Thread.UncaughtExceptionHandler {

    companion object {
        private const val CRASH_DIR = "crash"
        private const val CRASH_HPROF = "hprof"
        private const val MAX_CRASH_FILE_SIZE = 30
        private const val MAX_HPROF_FILE_SIZE = 2
    }

    private val applicationContext: Context = context.applicationContext

    /**
     * 系统默认的UncaughtException处理类
     */
    private val defaultHandler: Thread.UncaughtExceptionHandler? = Thread.getDefaultUncaughtExceptionHandler()

    override fun uncaughtException(thread: Thread, ex: Throwable) {
        Logs.exception.e("uncaughtException: ", ex)
        if (!handleException(thread, ex) && defaultHandler != null) {
            // 如果自定义的没有处理则让系统默认的异常处理器来处理
            defaultHandler.uncaughtException(thread, ex)
        } else {
            Logs.exception.e("kill process")
            try {
                TimeUnit.SECONDS.sleep(2)
            } catch (ignore: InterruptedException) {
            }
            Process.killProcess(Process.myPid())
        }
    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     *
     * @param ex 异常信息
     * @return true 如果处理了该异常信息;否则返回false.
     */
    @Synchronized
    private fun handleException(thread: Thread, ex: Throwable): Boolean {
        val crashLog = collectDeviceInfo(thread, ex)
        Logs.exception.e("crash log:\n$crashLog")
        saveCrashInfo2File(crashLog)
        if (ex is OutOfMemoryError) {
            saveDumpInfo2File()
        }
        return true
    }

    /**
     * 收集设备参数信息
     *
     * @param throwable
     */
    private fun collectDeviceInfo(thread: Thread, throwable: Throwable): String {
        val sw = StringWriter()
        throwable.printStackTrace(PrintWriter(sw))
        val stackTrace = sw.buffer.toString()
        return StringBuilder().apply {
            listOf(
                "manufacturer" to Build.MANUFACTURER,
                "model" to Build.MODEL,
                "product" to Build.PRODUCT,
                "versionName" to versionName,
                "versionCode" to versionCode,
                "buildTime" to buildTime,
                "startTime" to TimeHelper.timeStamp2String(
                    applicationStartTime,
                    TimeHelper.SDF_YYYY_MM_DD_HH_MM_SS
                ),
                "progress" to (ProcessUtils.getProgressName(applicationContext) ?: ""),
                "thread" to thread.name,
                "crashContent" to "\n$stackTrace"
            ).forEach {
                append(it.first).append("=").append(it.second).append("\r\n")
            }
        }.toString()
    }

    private fun saveCrashInfo2File(crashLog: String) {
        val date = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault()).format(Date())
        val logFileName = "crash_$date.txt"
        val crashFile = getFile(CRASH_DIR, logFileName) ?: return
        Logs.exception.e("crash log writing, log file: " + crashFile.absolutePath)
        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            try {
                val listFile = getDir(CRASH_DIR)?.listFiles() ?: return
                if (listFile.size >= MAX_CRASH_FILE_SIZE) {
                    listFile[0].delete()
                    Logs.exception.i("crash log deleted, log file: " + listFile[0].name)
                }
                FileOutputStream(crashFile).use {
                    it.write(crashLog.toByteArray())
                }
                Logs.exception.e("crash log saved")
            } catch (e: Throwable) {
                Logs.exception.e("saveCrashInfo2File", e)
            }
        }
    }

    private fun saveDumpInfo2File() {
        try {
            System.gc()
            TimeUnit.SECONDS.sleep(2)
        } catch (e: Exception) {
            Logs.exception.e("saveDumpInfo2File", e)
        }
        val date = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault()).format(Date())
        val dumpFileName = "crash_$date.hprof"
        val dumpFile = getFile(CRASH_HPROF, dumpFileName) ?: return
        Logs.exception.e("hprof log writing, log file: " + dumpFile.absolutePath)
        try {
            val listFile = getDir(CRASH_HPROF)?.listFiles() ?: return
            if (listFile.size >= MAX_HPROF_FILE_SIZE) {
                listFile[0].delete()
                Logs.common.i("hprof log delete, log file: " + listFile[0].name)
            }
            Debug.dumpHprofData(dumpFile.absolutePath)
            Logs.exception.e("hprof log saved")
        } catch (e: Exception) {
            Logs.exception.e("saveDumpInfo2File", e)
        }
    }

    private fun getFile(dir: String, fileName: String): File? {
        val parent = getDir(dir) ?: return null
        return File(parent, fileName)
    }

    private fun getDir(dir: String): File? {
        val file = File(applicationContext.filesDir, dir)
        if (!file.exists()) {
            if (!file.mkdirs()) {
                Logs.exception.e("mkdirs failed")
                return null
            }
        }
        return file
    }
}