package heop.data.upload_event

import com.google.gson.annotations.SerializedName
import heop.data.request.json.MaskDetection
import heop.data.request.json.SafetyHelmetDetection
import com.hik.vis.heop.v2.annotation.UploadEvent
import heop.data.constant.OperationNotificationType
import heop.data.constant.VirtualScreenStatusType

data class OperationNotificationEvent(
    @OperationNotificationType
    var operation: String? = null,  // 操作类型
    var parameters: String? = null, // 启动参数，startApp命令有效
    var packageName: String? = null,            // 包名
    var upgradePackagePath: String? = null,      // 升级包存放路径
    // 是否佩戴口罩
    var mask: String? = null,
    // 是否佩戴安全帽
    var helmet: String? = null,
    // 主题类型
    var showMode: String? = null,
    // 口罩佩戴策略
    @SerializedName("MaskDetection")
    var maskDetection: MaskDetection? = null,
    /*门禁参数信息，当operation为AcsCfg时有效*/
    @SerializedName("AcsCfg")
    var acsCfg: AcsCfgOperation? = null,
    // 人脸识别参数
    @SerializedName("FaceRecognition")
    var faceRecognition: FaceRecognition? = null,
    @SerializedName("HelmetDetection")
    // 安全检测策略
    var helmetDetection: SafetyHelmetDetection? = null,
    // 升级状态
    var upgradeStatus: Int? = null,
    //升级进度
    var upgradeProcess: Int? = null,
    //发起和关闭对讲时，通知UI开启和关闭虚拟屏幕(虚拟屏幕用于显示画面)
    @VirtualScreenStatusType
    var virtualScreenStatus:String?=null,

    var popUpPreviewWindow: Boolean? = null
)

// 人脸识别策略, 单人多人
data class FaceRecognition(
    var faceRecogizeEnable: Int? = null
)

/**
 * 操作通知事件
 */
@UploadEvent("OperationNotificationEvent")
data class OperationNotificationEventBean(
    var ipAddress: String? = null,              // 设备IPv4地址
    var ipv6Address: String? = null,            // 设备IPv6地址
    var portNo: Int? = null,                    // 设备端口号
    var protocol: String? = null,               // 协议类型: HTTP或HTTPS
    var macAddress: String? = null,             // 设备MAC地址
    var channelID: Int? = null,                 // 设备通道号
    var dateTime: String? = null,               // 触发事件时间（UTC时间）
    var activePostCount: Int? = null,           // 同一个事件已经上传的次数
    var eventType: String? = null,              // 触发的事件类型: OperationNotificationEvent–操作通知事件
    var eventState: String? = null,             // 事件触发状态: active-触发, inactive-未触发
    var eventDescription: String? = null,       // 事件描述: 操作通知事件(Operation Notification Event)
    @SerializedName("OperationNotificationEvent")
    var operationNotificationEvent: OperationNotificationEvent? = null,

    )

data class AcsCfgOperation(
    /*string, 工号显示类型：show-正常显示，hide-不显示，desensitise-脱敏显示*/
    var employeeNoShowType: String? = null,
    /*string, 姓名显示类型：show-正常显示，hide-不显示，desensitise-脱敏显示*/
    var nameShowType: String? = null,
    /*string, 图片显示类型：show-正常显示，hide-不显示，desensitise-脱敏显示*/
    var pictureShowType: String? = null,
    /*bool, 是否启用息屏*/
    var enabledScreenOff: Boolean? = null,
    /*int, 息屏时间，单位秒*/
    var screenOffTimeout: Int? = null,
    /*bool, 是否显示认证列表 */
    var showAuthenticationList: Boolean? = null,
    var saveVerificationPic: Boolean? = null, // true
    var showPicture: Boolean? = null, // true

    var combinationAuthenticationTimeout: Int? = null,
    var combinationAuthenticationLimitOrder: Boolean? = null
)
