package demo

import android.app.Application
import android.content.Intent
import android.os.Build
import android.util.Log
import base.ext.isDebug
import base.helper.CrashHandler
import com.hik.heop.demo.BuildConfig
import com.hik.vis.heop.v2.HeopHelper
import com.hik.vis.lib.log.interceptor.formatter.DefaultLogFileFormatter
import com.hik.vis.lib.log.interceptor.logFile.LogFileInterceptor
import com.hik.vis.lib.log.interceptor.logcat.LogcatLogInterceptor
import com.hik.vis.lib.log.logger.LogScheduler
import dagger.hilt.android.HiltAndroidApp
import log.loginterceptor.CommonLogcatFormatterLogInterceptor
import service.HeopService
import java.security.InvalidParameterException

@HiltAndroidApp
class InitApplication : Application() {
    companion object {
        private const val TAG = "heop"
    }

    override fun onCreate() {
        super.onCreate()
        // 初始化Crash
        initCrash()
        // 初始化Logs
        initLogs()
        // 初始化Heop
        initHeop()
        // 启动Heop服务
        startServices()
    }

    private fun initCrash() {
        Thread.setDefaultUncaughtExceptionHandler(
            CrashHandler(
                context = this.applicationContext,
                versionName = BuildConfig.VERSION_NAME,
                versionCode = BuildConfig.VERSION_CODE,
                buildTime = BuildConfig.BUILD_TIME,
                applicationStartTime = System.currentTimeMillis()
            )
        )
    }

    private fun initLogs() {
        LogScheduler.instance(
            tag = "heop", interceptorList = listOf(
                CommonLogcatFormatterLogInterceptor,
                LogcatLogInterceptor,
                DefaultLogFileFormatter,
                LogFileInterceptor
            )
        ).also {
            HeopHelper.setLogger(
                logV = if (isDebug()) it::v else null,
                logD = it::d,
                logI = it::i,
                logW = it::w,
                logE = it::e
            )
        }
    }

    private fun initHeop() {
        HeopHelper.init(
            defaultReqTimeOut = 5000,                   // 默认请求超时时间5秒
            defaultRespBuffSize = 100 * 1024,           // 默认请求响应接收缓存 100Kb
        )
        HeopHelper.setLogger(
            logV = { Log.v(TAG, it) },
            logD = { Log.d(TAG, it) },
            logI = { Log.i(TAG, it) },
            logW = { Log.w(TAG, it) },
            logE = { Log.e(TAG, it) }
        )

        HeopHelper.setEnableFilterSensitiveData(true)
        HeopHelper.setSensitiveFieldNames(
            HeopHelper.DEFAULT_SENSITIVE_FIELD_NAMES + arrayOf("wpaKeyLength")
        )
    }

    private fun startServices() {
        val heopService = Intent(this, HeopService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(heopService)
        } else {
            startService(heopService)
        }
    }
}