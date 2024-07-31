package heop.data.request.json

import com.google.gson.annotations.SerializedName
import heop.data.constant.UnitType
import heop.data.constant.VoiceTalkCmdType

data class CallSignalBean(
    @SerializedName("CallSignal")
    val callSignal: CallSignal? = null,
)

data class CallSignal(
    @VoiceTalkCmdType
    val cmdType: String? = null,
    val src: DeviceInfo? = null,
    val target: DeviceInfo? = null,
)

data class DeviceInfo(
    val periodNumber: Int? = 0,
    val buildingNumber: Int? = 0,
    val unitNumber: Int? = 0,
    val floorNumber: Int? = 0,
    val roomNumber: String? = "",
    val devIndex: Int? = 0,
    val communityNumber: Int? = null,
    @UnitType
    val unitType: String? = null,
)
