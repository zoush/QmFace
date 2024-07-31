package log

import log.loginterceptor.CommonLogcatFormatterLogInterceptor
import com.hik.vis.lib.log.interceptor.formatter.DefaultLogFileFormatter
import com.hik.vis.lib.log.interceptor.logFile.LogFileInterceptor
import com.hik.vis.lib.log.interceptor.logcat.LogcatLogInterceptor
import com.hik.vis.lib.log.logger.LogScheduler.Companion.instance

object Logs {
    private val interceptors = listOf(
        CommonLogcatFormatterLogInterceptor,
        LogcatLogInterceptor,
        DefaultLogFileFormatter,
        LogFileInterceptor
    )

    // 公共
    @JvmField
    val common = instance("common", interceptors)

    // 设置
    @JvmField
    val setting = instance("setting", interceptors)

    // DSP
    @JvmField
    val dsp = instance("dsp", interceptors)

    // 生命周期
    @JvmField
    val lifecycle = instance("lifecycle", interceptors)

    // 异常
    @JvmField
    val exception = instance("exception", interceptors)
}