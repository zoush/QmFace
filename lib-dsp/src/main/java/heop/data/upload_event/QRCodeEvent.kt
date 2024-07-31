package heop.data.upload_event

import com.google.gson.annotations.SerializedName
import com.hik.vis.heop.v2.annotation.UploadEvent
import heop.data.constant.HelmetType
import heop.data.constant.MaskType


@UploadEvent("QRCodeEvent")
data class QRCodeEventBean(
    var ipAddress: String? = null,              // 设备IPv4地址
    var ipv6Address: String? = null,            // 设备IPv6地址
    var portNo: Int? = null,                    // 设备端口号
    var protocol: String? = null,               // 协议类型: HTTP或HTTPS
    var macAddress: String? = null,             // 设备MAC地址
    var channelID: Int? = null,                 // 设备通道号
    var dateTime: String? = null,               // 触发事件时间（UTC时间）
    var activePostCount: Int? = null,           // 同一个事件已经上传的次数
    var eventType: String? = null,              // 触发的事件类型: AccessControllerEvent–门禁主机事件
    var eventState: String? = null,             // 事件触发状态: active-触发, inactive-未触发
    var deviceID: String? = null,               // 可选, 即PUID, 在ISUP协议接入透传ISAPI事件信息中必须返回
    var eventDescription: String? = null,       // 事件描述:  事件描述: 门禁主机事件(Access Controller Event)
    @SerializedName("QRCodeEvent")
    var qrCodeEvent: QRCodeEvent? = null, // 门禁主机事件
    /**
     * 可选, 图片url认证方式:
     * no-无（这个是针对武汉云存储协议），
     * digest-摘要认证（这个针对设备本地存储返回URL的方式，设备例如NVR/DVR）
     */
    @SerializedName("URLCertificationType")
    var urlCertificationType: String? = null,
)


data class QRCodeEvent(
    var deviceName: String? = null,     //设备名称
    var serialNo: String? = null,         //事件流水号
    var currentEvent: Boolean = false,   //是否为实时事件*/
    @SerializedName("QRCodeInfo")
    var qRCodeInfo: String? = null,  //二维码信息
    @SerializedName("QRCodeInfoBase64")
    var qRCodeInfoBase64: String? = null,// ro, opt, string, Base64编码后的二维码信息, range:[1,512], desc:1、对于不是字符串形式的二维码，直接扫码无法得到正常的二维码值，如十六进制的公交码。因此，设备在上传时需要先Base64编码，平台侧需Base64解码。2、老平台接新设备，若想实现解析本字段，需平台升级。
    var thermometryUnit: String? = null,// 		o, opt, enum, 测温单位, subType:string, [celsius#摄氏度（默认）,fahrenheit#华氏度,kelvin#开尔文]
    var currTemperature: Float? = null,// 人脸温度, desc:精确到小数点后一位
    var isAbnomalTemperature: Boolean = false,//人脸测温是否温度异常
    @SerializedName("RegionCoordinates")
    var regionCoordinates: RegionCoordinates? = null,   // 人脸温度坐标
    var remoteCheck: Boolean = false,   // 是否需要远程核验，true-需要，false-不需要（默认）
    @MaskType
    var mask: String? = null,           // 是否戴口罩：unknown-未知，yes-戴口罩，no-不戴口罩
    var pictureURL: String? = null,         // 图片URL
    var visibleLightURL: String? = null,    // 热成像相机可见光图片URL
    var thermalURL: String? = null,         // 热成像图片URL
    /*是否佩戴安全帽*/
    @HelmetType
    var helmet: String? = null,
    var picturesNumber: Int? = null,            // 图片数量
    @SerializedName("HealthInfo")
    var healthInfo: HealthInfo? = null,  // 健康码信息
    var employeeNo: String? = null,        // 工号（人员ID）
    var name: String? = null,//姓名
    @SerializedName("IDNum")
    var iDNum: String? = null,//证件号码
    var openDoor: Boolean = false //*ro, opt, bool, 是否开门, desc:当前本字段仅用于HEOP使用，当健康码平台核验的结果为成功，但是设备本地关联门处于常闭状态，Hicore依然提示UI不可开门。*/
)






