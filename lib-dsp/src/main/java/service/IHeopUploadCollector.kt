package service

import heop.data.upload_event.*

/**
 * Heop事件上报Flow
 */
interface IHeopUploadCollector {
    /**
     * 操作通知事件
     */
    suspend fun operationNotificationEvent(event: OperationNotificationEventBean)

    /**
     * 门禁控制事件
     */
    suspend fun accessControllerEvent(event: AccessControllerEventBean)

    /**
     * 可视对讲上报事件
     */
    suspend fun voiceTalkEvent(event: VoiceTalkEventBean)

    /**
     * 二维码事件
     */
    suspend fun qrCodeEvent(event: QRCodeEventBean)

    /**
     * 刷卡事件
     */
    suspend fun cardEvent(event: CardEventBean)

    /**
     * 刷身份证事件
     */
    suspend fun idCardInfoEvent(event: IDCardInfoEventBean)

}