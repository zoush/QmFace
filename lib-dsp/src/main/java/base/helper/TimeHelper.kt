package base.helper

import log.Logs
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object TimeHelper {
    val SDF_YYYY get() = SimpleDateFormat("yyyy", Locale.getDefault())
    val SDF_MM_DD get() = SimpleDateFormat("MM-dd", Locale.getDefault())
    val SDF_YYYY_MM_DD get() = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val SDF_YYYY_MM_DD_HH_MM_SS get() = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val SDF_YYMMDD get() = SimpleDateFormat("yyMMdd", Locale.getDefault())
    val SDF_HH_MM_SS get() = SimpleDateFormat("HH:mm:ss", Locale.getDefault())

    /**
     * 时间戳转时间字符串
     */
    fun timeStamp2String(time: Long, sdf: SimpleDateFormat): String {
        return sdf.format(Date(time))
    }

    /**
     * yyyy-MM-dd HH:mm:ss时间字符串转ISO8601时间格式字符串：yyyy-MM-ddTHH:mm:ss
     */
    fun String.toISO8601Time(): String {
        return this.replace(" ", "T")
    }

    /**
     * 时间戳转ISO8601时间格式字符串：yyyy-MM-ddTHH:mm:ss
     */
    fun timeStamp2ISO8601Time(time: Long): String {
        return timeStamp2String(time, SDF_YYYY_MM_DD_HH_MM_SS).toISO8601Time()
    }

    /**
     * Date转时间字符串
     */
    fun date2String(time: Date, sdf: SimpleDateFormat): String {
        return sdf.format(time)
    }

    /**
     * Calendar转时间字符串
     */
    fun calendar2String(time: Calendar, sdf: SimpleDateFormat): String {
        return sdf.format(time.time)
    }

    /**
     * 时间字符串转时间戳
     */
    fun string2timeStamp(time: String, sdf: SimpleDateFormat): Long {
        return try {
            sdf.parse(time)?.time ?: 0L
        } catch (e: ParseException) {
            Logs.exception.e("string2timeStamp", e)
            0L
        }
    }

    /**
     * 获取当前时间戳
     */
    fun currentTimeMillis(): Long = System.currentTimeMillis()
}