package log.loginterceptor

import com.hik.vis.lib.log.event.LogEvent
import com.hik.vis.lib.log.interceptor.LogInterceptor

object CommonLogcatFormatterLogInterceptor : LogInterceptor {
    override fun intercept(logEvent: LogEvent): Boolean {
        val stackTrace1 = LogFormatterUtils.stackTrace(6)
        val currentThreadName = logEvent.currentThreadName
        val content = "[$currentThreadName]-$stackTrace1: ${logEvent.content}"
        logEvent.content = content
        return false
    }
}