package heop.v2.upload

import heop.data.upload_event.*
import kotlinx.coroutines.flow.Flow

/**
 * Heop事件上报Flow
 */
interface IHeopUploads {
    /**
     * 操作通知事件
     */
    fun operationNotificationEvent(): Flow<OperationNotificationEventBean>

    /**
     * 门禁控制事件
     */
    fun accessControllerEvent(): Flow<AccessControllerEventBean>

    /**
     * 可视对讲上报事件
     */
    fun voiceTalkEvent(): Flow<VoiceTalkEventBean>

    /**
     * 二维码事件
     */
    fun qrCodeEvent(): Flow<QRCodeEventBean>

    /**
     * 刷卡事件
     */
    fun cardEvent(): Flow<CardEventBean>

    /**
     * 身份证事件
     */
    fun idCardInfoEvent(): Flow<IDCardInfoEventBean>
}