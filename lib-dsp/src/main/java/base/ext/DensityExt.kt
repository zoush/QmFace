package base.ext

import android.content.Context

/** dp和px转换 **/
fun Context.dp2px(dpValue: Float): Int {
    return (dpValue * resources.displayMetrics.density + 0.5f).toInt()
}

fun Context.px2dp(pxValue: Float): Int {
    return (pxValue / resources.displayMetrics.density + 0.5f).toInt()
}

fun Context.sp2px(spValue: Float): Int {
    return (spValue * resources.displayMetrics.scaledDensity + 0.5f).toInt()
}

fun Context.px2sp(pxValue: Float): Int {
    return (pxValue / resources.displayMetrics.scaledDensity + 0.5f).toInt()
}