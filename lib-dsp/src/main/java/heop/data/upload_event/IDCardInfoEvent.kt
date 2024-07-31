package heop.data.upload_event

import com.google.gson.annotations.SerializedName
import com.hik.vis.heop.v2.annotation.UploadEvent

@UploadEvent("IDCardInfoEvent")
data class IDCardInfoEventBean(
    var ipAddress: String? = null,
    var macAddress: String? = null,
    var channelID: String? = null,
    var dateTime: String? = null,
    var activePostCount: Int? = null,
    var eventType: String? = null,
    var eventState: String? = null,
    var eventDescription: String? = null,
    var IDCardInfoEvent: IDCardInfoEvent? = null,
    var pictureURL: String? = null,
    var IDCardPicURL: String? = null
)

data class IDCardInfoEvent(
    var deviceName: String? = null,
    var cardReaderNo: Int? = null,
    var majorEventType: Int? = null,
    var subEventType: Int? = null,
    var isAbnomalTemperature: Boolean? = null,
    var showDuration: Int? = null,
    var cardNo: String? = null,
    var name: String? = null,
    var employeeNoString: String? = null,
    var mask: String? = null,
    @SerializedName("IDCardInfo")
    var cardInfo: IDCardInfo? = null

)

data class IDCardInfo(
    var name: String? = null,
    var sex: String? = null,
    var birth: String? = null,
    var addr: String? = null,
    @SerializedName("IDCardNo")
    var id: String? = null,
    var startDate: String? = null,
    var endDate: String? = null,
    var nation: Int? = null,
    @SerializedName("IDCardPicURL")
    var cardPicUrl: String? = null
)

