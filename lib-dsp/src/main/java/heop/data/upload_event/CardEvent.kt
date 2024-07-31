package heop.data.upload_event

import com.google.gson.annotations.SerializedName
import com.hik.vis.heop.v2.annotation.UploadEvent

/**
 * 刷卡事件
 */
data class CardEvent(
    var cardNo: String? = null,     // 卡号
    var cardType: String? = null,     //卡类型
    @SerializedName("IDCardInfo")
    var cardInfo: IDCardInfo? = null
)

@UploadEvent("CardEvent")
data class CardEventBean(
    var ipAddress: String? = null,
    var ipv6Address: String? = null,
    var portNo: Int? = null,
    var protocol: String? = null,
    var macAddress: String? = null,
    var channelID: Int? = null,
    var dateTime: String? = null,
    var activePostCount: Int? = null,
    var eventType: String? = null,
    var eventState: String? = null,
    var eventDescription: String? = null,
    var CardEvent: CardEvent? = null,

    )

