package service

import heop.data.upload_event.*

interface EventCollector<in T> {
    suspend operator fun invoke(value: T)
}

/**
 * Heop事件上报Flow
 */
class HeopUploadCollector(
    private val onOperationNotificationEvent: EventCollector<OperationNotificationEventBean>? = null,
    private val onAccessControllerEvent: EventCollector<AccessControllerEventBean>? = null,
    private val onVoiceTalkEventBean: EventCollector<VoiceTalkEventBean>? = null,
    private val onQRCodeEventBean: EventCollector<QRCodeEventBean>? = null,
    private val onCardEventBean: EventCollector<CardEventBean>? = null,
) : IHeopUploadCollector {
    override suspend fun operationNotificationEvent(event: OperationNotificationEventBean) {
        onOperationNotificationEvent?.invoke(event)
    }

    override suspend fun accessControllerEvent(event: AccessControllerEventBean) {
        onAccessControllerEvent?.invoke(event)
    }

    override suspend fun voiceTalkEvent(event: VoiceTalkEventBean) {
        onVoiceTalkEventBean?.invoke(event)
    }

    override suspend fun qrCodeEvent(event: QRCodeEventBean) {
        onQRCodeEventBean?.invoke(event)
    }

    override suspend fun cardEvent(event: CardEventBean) {
        onCardEventBean?.invoke(event)
    }

    override suspend fun idCardInfoEvent(event: IDCardInfoEventBean) {
        TODO("Not yet implemented")
    }

}