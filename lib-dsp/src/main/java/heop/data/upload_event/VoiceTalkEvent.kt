package heop.data.upload_event

import com.google.gson.annotations.SerializedName
import com.hik.vis.heop.v2.annotation.UploadEvent
import heop.data.constant.IndustryType
import heop.data.constant.MutiCallMode
import heop.data.constant.UnitType
import heop.data.constant.VoiceTalkCmdType

data class CallSrc(
    var periodNumber: Int? = null,
    var buildingNumber: Int? = null,
    var unitNumber: Int? = null,
    var floorNumber: Int? = null,
    var roomNumber: Int? = null,
    var devIndex: Int? = null,
    var callNumber: String? = null,
    @UnitType
    var unitType: String? = null
)

data class CallTarget(
    var periodNumber: Int? = null,
    var buildingNumber: Int? = null,
    var unitNumber: Int? = null,
    var floorNumber: Int? = null,
    var roomNumber: Int? = null,
    var callNumber: String? = null,
    var devIndex: Int? = null,
    @UnitType
    var unitType: String? = null,
    /**
     * 行业(场景)类型,[builidings#楼宇,prison#监所,medicalTreatment#医疗,broadcasting#广播]
     */
    @IndustryType
    var industryType: String? = null,
    /**
     * opt, string, 映射房间号, range:[1,5], desc:海外用户的隐私需求，为了不暴露实际房间号，通过映射房号进行呼叫，映射房间号具有全局唯一性
     */
    var mappingRoomNumber: String? = null
)

data class VoiceTalkEvent(
    var deviceName: String? = null,
    var netUser: String? = null,
    var deviceNo: Int? = null,
    var deviceId: Int? = null,
    var remoteHostAddr: String? = null,
    @VoiceTalkCmdType
    var cmdType: String? = null,
    var src: CallSrc? = null,
    var target: CallTarget? = null,
    var serialNo: Int? = null,
    var currentEvent: Boolean? = null,
    var frontSerialNo: Int? = null,
    var pictureURL: String? = null,
    var picturesNumber: Int? = null,
    @MutiCallMode
    var callMode: String? = null,
    @SerializedName("Info")
    var info: Info? = null
) {
    data class Info(
        var lockNum: Int? = null
    )
}

/**
 * 可视对讲事件
 */
@UploadEvent("voiceTalkEvent")
data class VoiceTalkEventBean(
    var ipAddress: String? = null,              // 设备IPv4地址
    var ipv6Address: String? = null,            // 设备IPv6地址
    var portNo: Int? = null,                    // 设备端口号
    var protocol: String? = null,               // 协议类型: HTTP或HTTPS
    var macAddress: String? = null,             // 设备MAC地址
    var channelID: Int? = null,                 // 设备通道号
    var dateTime: String? = null,               // 触发事件时间（UTC时间）
    var activePostCount: Int? = null,           // 同一个事件已经上传的次数
    var eventType: String? = null,              // 触发的事件类型: voiceTalkEvent-对讲事件
    var eventState: String? = null,             // 事件触发状态: active-触发, inactive-未触发
    var eventDescription: String? = null,       // 事件描述: 对讲事件(Voice Talk Interactive Event)
    @SerializedName("VoiceTalkEvent")
    var voiceTalkEvent: VoiceTalkEvent? = null  // 对讲事件
)