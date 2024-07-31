package com.hik.heop.data.constant

import androidx.annotation.IntDef

/**
 * 远程升级事件状态
 */

@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD, AnnotationTarget.FUNCTION)
@IntDef(
    RemoteUpgradeStatus.UPGRADING,
    RemoteUpgradeStatus.SUCCEED,
    RemoteUpgradeStatus.FAILED,
)
@Retention(AnnotationRetention.SOURCE)
annotation class RemoteUpgradeStatus {
    companion object {
        //升级中
        const val UPGRADING = 0
        //升级成功
        const val SUCCEED = 1
        //升级失败
        const val FAILED = 2
    }
}