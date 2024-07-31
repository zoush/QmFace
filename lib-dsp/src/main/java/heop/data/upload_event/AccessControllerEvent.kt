package heop.data.upload_event

import com.google.gson.annotations.SerializedName
import com.hik.vis.heop.data.constant.*
import com.hik.vis.heop.v2.annotation.UploadEvent
import heop.data.constant.HelmetType
import heop.data.constant.MaskType
import heop.data.constant.UserType
import heop.data.constant.VerifyModeType

/**
 * 人脸温度坐标
 */
data class RegionCoordinates(
    var positionX: Int? = null,  //可选，integer，X坐标 归一化坐标0-1000
    var positionY: Int? = null   //可选，integer，Y坐标 归一化坐标0-1000
)

//健康码事件
data class HealthInfo(
    //[0#未请求,1#未申领,2#绿码,3#黄码,4#红码,5#无此人员,6#其他错误信息（如接口异常导致查询失败）,7#查询健康码超时]
    var healthCode: Int? = null,
    //[0#未查询到核酸检测结果,1#核酸检测阴性（代表正常）,2#核酸检测阳性（代表确诊）,3#核酸检测有效期已过,4#查询核酸结果失败]
    @SerializedName("NADCode")
    var nadCode: Int? = null,
    //[0#14天内一直在当地,1#14天内离开过当地,2#14天内到过疫区,3#其他,4#查询行程信息失败]
    var travelCode: Int? = null,
    //[0#未打疫苗,1#部分注射疫苗,2#已完成疫苗,3#查询疫苗信息失败,4#已完成加强针疫苗]
    var vaccineStatus: Int? = null,
    //打疫苗针数
    var vaccineNum: Int? = null,
    //抗原检测状态, subType:int, [0#未查询到抗原检测结果,1#抗原检测阴性,2#抗原检测阳性,3#抗原检测有效期已过,4#抗原检测无效]
    var ANTCode: Int? = null, // 1
    //抗原检测信息, range:[0,64], desc:空字符串表示查询到抗原检测信息失败
    var ANTMsg: String? = null, // test
    //打疫苗信息, range:[0,64], desc:空字符串表示查询到疫苗信息失败
    var NADMsg: String? = null, // test
    //核酸检测时间, subType:int, [1#24小时内,2#48小时内,3#48小时外]
    var NADTime: Int? = null, // 1
    //行程信息, desc:空字符串表示查询到行程信息失败
    var travelInfo: String? = null, // test
    //打疫苗信息, range:[0,64], desc:空字符串表示查询到疫苗信息失败
    var vaccineMsg: String? = null, // test
    @SerializedName("NADValidTime")
    var nadValidTime:Int?=null,//核酸检测有效时间, range:[1,744], step:1, unit:h, unitType:时间, desc:单位：小时
    var checkResult:String?=null,//*ro, opt, enum, 核验结果, subType:string, [success#核验成功,failed#核验失败], desc:若平台核验失败，则设备不开门，且上报认证失败事件
    var healthMessage:String?=null,//定义健康播报信息, range:[0,15], desc:平台下发该字段后，设备在认证后播报该字段，如“绿码请通行”，该字段优先级高于门禁的自定义语音播报功能*
    var selfDefineInfo:String?=null,//	/*ro, opt, string, 自定义信息, range:[0,128], desc:自定义信息，可填写为场所信息、省份信息等自定义内容*/
    @SerializedName("IDNum")
    var idNum:String?=null,//身份证号
    var name:String?=null//人员姓名
)

/**
 * 自定义工号
 */
data class PersonInfoExtend(
    var id: Int? = null,
    var value: String? = null
)

/**
 * 门禁上传事件
 */
data class AccessControllerEvent(
    var deviceName: String? = null,     //设备名称
    /**
     * @see [ACSEventMajor]
     */
    var majorEventType: Int? = null,        // 报警主类型，参考宏定义如传递1代表0x1（报警主类型）
    /**
     * @see [ACSEventMinor]
     */
    var subEventType: Int? = null,          // 报警次类型，参考宏定义 如传递1024代表0x400（防区短路报警次类型）
    var inductiveEventType: Int? = null,    // 归纳事件类型（后端设备定义，门禁设备暂时用不到）
    var netUser: String? = null,        // 网络操作的用户名
    var remoteHostAddr: String? = null, // 远程主机地址
    var cardNo: String? = null,         // 卡号
    /**
     * 卡类型：1-普通卡，2-残疾人卡，3-黑名单卡，4-巡更卡，5-胁迫卡，6-超级卡，7-来宾卡，8-解除卡
     */
    var cardType: Int? = null,
    var name: String? = null,           // 人员姓名
    var whiteListNo: Int? = null,       // 白名单单号，1-8
    var reportChannel: Int? = null,     // 报告上传通道：1-布防上传，2-中心组1上传，3-中心组2上传
    var cardReaderKind: Int? = null,    // 读卡器类型：1-IC读卡器，2-身份证读卡器，3-二维码读卡器，4-指纹头
    var cardReaderNo: Int? = null,      // 读卡器编号
    var doorNo: Int? = null,            // 门编号（楼层编号）
    var verifyNo: Int? = null,          // 多重卡认证序号
    var alarmInNo: Int? = null,         // 报警输入号
    var alarmOutNo: Int? = null,        // 报警输出号
    var caseSensorNo: Int? = null,      // 事件触发器编号
    var RS485No: Int? = null,           // RS485通道号
    var multiCardGroupNo: Int? = null,  // 群组编号
    var accessChannel: Int? = null,     // 人员通道号
    var deviceNo: Int? = null,          // 设备编号
    var distractControlNo: Int? = null, // 分控器编号
    var employeeNo: Int? = null,        // 工号（人员ID）
    /**
     * 工号（人员ID）对于设备来说，如果使用了工号（人员ID）字段，
     * employeeNoString一定要传递，如果employeeNoString可转换为employeeNo，那么该字段也要传递；
     * 对于上层平台或客户端来说，优先解析employeeNoString字段，如该字段为空，再考虑解析employeeNo字段
     */
    var employeeNoString: String? = null,
    /** 自定义 */
    @SerializedName("ExternInfoValue")
    var externInfoValue: String? = null,
    var employeeName: Int? = null,  // 人员名称,该字段仅信息发布项目使用,增加时报文上面的name字段丢失,导致多增加了人员名称字段
    var localControllerID: Int? = null, // 就地控制器编号：0-门禁主机，1-64代表就地控制器
    var InternetAccess: Int? = null,    // 网口ID：1-上行网口1，2-上行网口2，3-下行网口1
    /**
     * 防区类型：
     * 0-即时防区，1-24小时防区，2-延时防区，3-内部防区，4-钥匙防区，5-火警防区，6-周界防区，
     * 7-24小时无声防区，8-24小时辅助防区，9-24小时震动防区，10-门禁紧急开门防区，11-门禁紧急关门防区，255-无
     */
    var type: Int? = null,
    var MACAddr: Int? = null,                       // 物理地址
    var swipeCardType: Int? = null,                 // 刷卡类型：0-无效，1-二维码
    var serialNo: Int? = null,                      // 事件流水号
    var channelControllerID: Int? = null,           // 通道控制器ID：1-主通道控制器，2-从通道控制器
    var channelControllerLampID: Int? = null,       // 通道控制器灯板ID：1-255
    var channelControllerIRAdaptorID: Int? = null,  // 通道控制器红外转接板ID：1-255
    var channelControllerIREmitterID: Int? = null,  // 通道控制器红外对射ID：1-255
    /**
     *  人员类型，normal-普通人（主人），visitor-来宾（访客），blackList-黑名单人，administrators-管理员
     */
    @UserType
    var userType: String? = null,
    /**
     * 读卡器当前验证方式：
     * cardAndPw-刷卡+密码，card-刷卡，cardOrPw-刷卡或密码，fp-指纹，fpAndPw-指纹+密码，fpOrCard-指纹或刷卡，
     * fpAndCard-指纹+刷卡，fpAndCardAndPw-指纹+刷卡+密码，faceOrFpOrCardOrPw-人脸或指纹或刷卡或密码，
     * faceAndFp-人脸+指纹，faceAndPw-人脸+密码，faceAndCard-人脸+刷卡，face-人脸，employeeNoAndPw-工号+密码，
     * fpOrPw-指纹或密码，employeeNoAndFp-工号+指纹，employeeNoAndFpAndPw-工号+指纹+密码，
     * faceAndFpAndCard-人脸+指纹+刷卡，faceAndPwAndFp-人脸+密码+指纹，employeeNoAndFace-工号+人脸，
     * faceOrfaceAndCard-人脸或人脸+刷卡，fpOrface-指纹或人脸，cardOrfaceOrPw-刷卡或人脸或密码*/
    @VerifyModeType
    var currentVerifyMode: String? = null,
    var currentEvent: Boolean = false,      // 是否为实时事件，ture-是（实时事件），false-否（离线事件
    @SerializedName("QRCodeInfo")
    var qrCodeInfo: String? = null,         // 二维码信息
    //    /*enum, 测温结果, subType:string, [success#成功（默认）,fail#失败]*/
    var thermometryResult: String? = null,
    var thermometryUnit: String? = null,    // 测温单位（celsius-摄氏度（默认），fahrenheit-华氏度，kelvin-开尔文）
    var currTemperature: Float? = null,    // 人脸温度（精确到小数点后一位）
    var isAbnomalTemperature: Boolean? = null,           // 人脸测温是否温度异常（true-是，false-否）
    @SerializedName("RegionCoordinates")
    var regionCoordinates: RegionCoordinates? = null,   // 人脸温度坐标
    var remoteCheck: Boolean = false,   // 是否需要远程核验，true-需要，false-不需要（默认）
    @MaskType
    var mask: String? = null,           // 是否戴口罩：unknown-未知，yes-戴口罩，no-不戴口罩
    /**
     * 上一条事件流水号
     * （若设备没返回该字段，平台根据serialNo判断是否丢失事件；若设备返回该字段，
     * 平台根据该字段和serialNo字段共同判断是否丢失事件）
     * （主要用于解决报警订阅后导致serialNo不连续的情况）
     */
    var frontSerialNo: Int? = null,
    /**
     * 考勤状态：
     * checkIn-上班，checkOut-下班，breakOut-开始休息，breakIn-结束休息，overtimeIn-开始加班，overtimeOut-结束加班
     */
    var attendanceStatus: String? = null,
    var label:String?=null,//自定义考勤名称
    var mode:String?=null,//考勤模式
    var statusValue: Int? = null,   // 状态值
    var pictureURL: String? = null,         // 图片URL
    var visibleLightURL: String? = null,    // 热成像相机可见光图片URL
    var thermalURL: String? = null,         // 热成像图片URL
    var faceBasemapURL: String? = null,     //opt,人脸底图URL,desc:仅HEOP使用，用于人员认证成功后显示人脸图片。
    var picturesNumber: Int? = null,            // 图片数量
    /**
     * 对应次类型（MINOR_UNCLOCK_RECORD）时返回，
     * 开锁类型，string，password-密码开锁，hijcking-劫持开锁，card-刷卡开锁，householder-户主开锁，
     * centerplatform-中心平台开锁，bluetooth-蓝牙开锁，qrcode-二维码开锁，face-人脸开锁，fingerprint-指纹开锁
     */
    var unlockType: String? = null,
    var classroomId: String? = null,    // 教室UUID
    var classroomName: String? = null,  // 教室名称
    /**
     * 分析模块,电子班牌设备使用,signageApp-班牌APP,faceSDK-人脸SDK,本字段不返回默认采用班牌APP上报
     */
    var analysisModule: String? = null,
    var customInfo: String? = null,  // 自定义信息

    /*是否佩戴安全帽*/
    @HelmetType
    var helmet: String? = null,
    /*是否支持纯密码开门（人员信息中的-password字段）,
    desc:*纯密码方案：
    *①认证方式中的"或密码"为人员密码
    *②设备不对人员密码的重复性进行校验，需要上层平台自行保证密码的唯一性
    *③设备本地不能对人员密码进行操作（增删改查）*/
    var purePwdVerifyEnable: Boolean? = null,
    /*enum, 应用类型（信发产品使用）, subType:string, [attendance#考勤应用,signIn#签到应用]*/
    var appType: String? = null,
    // 健康码信息
    @SerializedName("HealthInfo")
    var healthInfo: HealthInfo? = null,
    /*enum, 图片url认证方式,
        subType:string, [no#无,digest#摘要认证],
        desc:no-无（这个是针对武汉云存储协议），
        digest-摘要认证（这个针对设备本地存储返回URL的方式，
        设备例如NVR/DVR）
    */
    @SerializedName("URLCertificationType")
    var urlCertificationType: String? = null,
    // 人脸认证模式
    @SerializedName("FaceAuthMode")
    var faceAuthMode: String? = null,
    // 人脸坐标X
    @SerializedName("FaceLocationX")
    var faceLocationX: Float? = null,
    // 人脸坐标Y
    @SerializedName("FaceLocationY")
    var faceLocationY: Float? = null,
    // 人脸宽度
    @SerializedName("FaceLocationW")
    var faceLocationW: Float? = null,
    // 人脸高度
    @SerializedName("FaceLocationH")
    var faceLocationH: Float? = null,
    // 自定义工号-字段
    @SerializedName("PersonInfoExtends")
    var personInfoExtends: List<PersonInfoExtend?>? = null,
    /*ro, opt, string, 自定义提示语, range:[1,128], desc:认证成功或失败或为陌生人时，上报自定义的显示提示语到UI上进行展示*/
    var customPrompt: String? = null,
    //显示倒计时
    var showDuration:Int?=null//认证结果显示的时间
)

/**
 * 门禁控制事件
 */
@UploadEvent("AccessControllerEvent")
data class AccessControllerEventBean(
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
    @SerializedName("AccessControllerEvent")
    var accessControllerEvent: AccessControllerEvent? = null, // 门禁主机事件
    /**
     * 可选, 图片url认证方式:
     * no-无（这个是针对武汉云存储协议），
     * digest-摘要认证（这个针对设备本地存储返回URL的方式，设备例如NVR/DVR）
     */
    @SerializedName("URLCertificationType")
    var urlCertificationType: String? = null,
)










