package heop.data.request.json

import com.google.gson.annotations.SerializedName
import heop.data.constant.SearchResponseStatus
import heop.data.constant.UserType

/**
 * 添加人员
 * /ISAPI/AccessControl/UserInfo/Record?format=json
 */
data class UserInfoBean(
    @SerializedName("UserInfo")
    var userInfo: UserInfo? = null
)

/**
 * 人员信息
 */
data class UserInfo(
    /**
     * 工号（人员ID）, 必填
     */
    var employeeNo: String? = null,
    /**
     * 是否删除该人员
     * true - 是
     * （只有删除该人员时，才填写该字段；新增或修改人员时，不填写该字段）
     */
    var deleteUser: Boolean? = null,
    /**
     * 姓名, 可选
     */
    var name: String? = null,
    /**
     * 人员类型, 必填
     * normal - 普通人（主人）
     * visitor - 来宾（访客）
     * blackList - 黑名单人
     */
    @UserType
    var userType: String? = null,
    /**
     * 是否关门延迟, 可选
     * true - 是
     * false - 否
     */
    var closeDelayEnabled: Boolean? = null,
    /**
     * 有效期参数（enable不使能代表长期有效）, 必填
     * （有效时间跨度为1970年1月1日0点0分0秒~2037年12月31日23点59分59秒）
     */
    @SerializedName("Valid")
    var valid: Valid? = null,
    /**
     * 所属群组, 可选
     */
    var belongGroup: String? = null,
    /**
     * 密码, 可选
     */
    var password: String? = null,
    /**
     * 门权限（代表对门1、门3有权限）, 可选
     * （锁权限，此处为锁ID，可填写多个，代表对锁1、锁3有权限）
     */
    var doorRight: String? = null,
    @SerializedName("RightPlan")
    /**
     * 门权限计划（锁权限计划）, 可选
     */
    var rightPlan: List<RightPlan?>? = null,
    /**
     * 最大认证次数，0为无次数限制, 可选
     */
    var maxOpenDoorTime: Int? = null,
    /**
     * 已认证次数, 只读, 可选
     */
    var openDoorTime: Int? = null,
    /**
     * 房间号, 可选
     */
    var roomNumber: Int? = null,
    /**
     * 层号, 可选
     */
    var floorNumber: Int? = null,
    /**
     * 反锁开门权限, 可选
     * true - 有权限
     * false - 无权限
     */
    var doubleLockRight: Boolean? = null,
    /**
     * 是否具有设备本地UI访问权限, 可选
     * true - 有权限
     * false - 无权限
     */
    var localUIRight: Boolean? = null,
    /**
     * 人员验证方式（人员验证方式的优先级高于读卡器验证方式）, 可选
     * cardAndPw - 刷卡+密码
     * card - 刷卡
     * cardOrPw - 刷卡或密码
     * fp - 指纹
     * fpAndPw - 指纹+密码
     * fpOrCard - 指纹或刷卡
     * fpAndCard - 指纹+刷卡
     * fpAndCardAndPw - 指纹+刷卡+密码
     * faceOrFpOrCardOrPw - 人脸或指纹或刷卡或密码
     * faceAndFp - 人脸+指纹
     * faceAndPw - 人脸+密码
     * faceAndCard - 人脸+刷卡
     * face - 人脸
     * employeeNoAndPw - 工号+密码
     * fpOrPw - 指纹或密码
     * employeeNoAndFp - 工号+指纹
     * employeeNoAndFpAndPw - 工号+指纹+密码
     * faceAndFpAndCard - 人脸+指纹+刷卡
     * faceAndPwAndFp - 人脸+密码+指纹
     * employeeNoAndFace - 工号+人脸
     * faceOrfaceAndCard - 人脸或人脸+刷卡
     * fpOrface - 指纹或人脸
     * cardOrfaceOrPw - 刷卡或人脸或密码
     * cardOrFace - 刷卡或人脸
     * cardOrFaceOrFp - 刷卡或人脸或指纹
     * faceOrPw - 人脸或密码
     * employeeNoAndFaceAndPw - 工号+人脸+密码
     * faceOrfaceAndCard - 人脸或人脸+刷卡
     * fpOrface - 指纹或人脸
     * cardOrfaceOrPw - 刷卡或人脸或密码
     */
    var userVerifyMode: String? = null,
    /**
     * 设备是否进行人员重复添加校验, 可选
     * false - 不校验
     * true - 校验
     * （如果不配置该字段，则设备默认进行人员重复校验）
     * （如果确认设备端不存在任何人员信息，可将其置为false，则设备不进行重复校验，这样会加快下发速度；如果不确认，则不建议配置该字段）
     */
    var checkUser: Boolean? = null,
    /**
     * 关联人脸数量, 不返回不支持, 可选, 只读
     */
    var numOfFace: Int? = null,
    /**
     * 关联指纹数量, 不返回不支持, 可选, 只读
     */
    var numOfFP: Int? = null,
    /**
     * 关联卡数量, 不返回不支持, 可选, 只读
     */
    var numOfCard: Int? = null,
    /**
     * 人脸图片对应的人员性别, 可选
     * male - 男
     * female - 女
     * unknown - 未知
     */
    var gender: String? = null,
    @SerializedName("PersonInfoExtends")
    var personInfoExtends: List<PersonInfoExtend?>? = null,
    /**
     * 操作类型, 可选
     * byTerminal - 按终端操作
     */
    var operateType: String? = null,
    var terminalNoList: List<Int?>? = null,/*可选,array,type为byTerminal,byTerminalOrg时必填,终端ID列表（目前仅支持单个终端）*/
    /**
     * 动态权限码, 可选
     */
    var dynamicCode: String? = null,
    /**
     * 呼叫号码列表, 可选
     * 默认规则X-X-X-X，如1-1-1-401, roomNumber字段扩展，支持列表时，使用列表配置相关信息
     */
    var callNumbers: List<String?>? = null,
    /**
     * 层号列表, 可选
     * floorNumber扩展，支持列表时，可使用该字段配置层号
     */
    var floorNumbers: List<Int?>? = null,
) {
    data class Valid(
        /**
         * 使能有效期
         * false - 不使能
         * true - 使能
         */
        var enable: Boolean? = null,
        /**
         * 有效期起始时间, 必填
         * （timeType字段不存在或为local时，beginTime为设备本地时间，如：2017-08-01T17:30:08；
         * timeType字段为UTC时，beginTime为UTC时间，如：2017-08-01T17:30:08+08:00）
         */
        var beginTime: String? = null,
        /**
         * 有效期结束时间, 必填
         * （timeType字段不存在或为local时，endTime为设备本地时间，如：2017-08-01T17:30:08；
         * timeType字段为UTC时，endTime为UTC时间，如：2017-08-01T17:30:08+08:00）
         */
        var endTime: String? = null,
        /**
         * 时间类型, 可选
         * local - 设备本地时间
         * UTC - UTC时间
         */
        var timeType: String? = null
    )

    data class RightPlan(
        /**
         * 门编号（锁ID）, 可选
         */
        var doorNo: Int? = null,
        /**
         * 计划模板编号，同个门不同计划模板采用权限或的方式处理, 可选
         */
        var planTemplateNo: String? = null
    )

    data class PersonInfoExtend(
        /**
         * 属性内容序号, 可选
         * 与人员扩展信息名称/ISAPI/AccessControl/personInfoExtendName?format=json中的id对应
         */
        var id: Int? = null,
        /**
         * 人员信息扩展内容, 可选
         */
        var value: String? = null
    )
}

data class UserInfoSearchCond(
    /**
     * 搜索记录唯一标识, 必填
     * 用来确认上层客户端是否为同一个(倘若是同一个,设备记录内存,下次搜索加快速度)
     */
    var searchID: String? = null,
    /**
     * 查询结果在结果列表中的起始位置, 必填
     * 当记录条数很多时, 一次查询不能获取所有的记录, 下一次查询时指定位置可以查询后面的记录
     * （若设备支持的最大totalMatches为M个, 但是当前设备已存储的totalMatches为N个（N<=M）, 则该字段的合法范围为0~N-1）
     */
    var searchResultPosition: Int? = null,
    /**
     * 本次协议调用可获取的最大记录数, 必填
     * （如maxResults值大于设备能力集返回的范围，则设备按照能力集最大值返回，设备不进行报错）
     */
    var maxResults: Int? = null,
    /**
     * 人员ID列表, 可选
     */
    @SerializedName("EmployeeNoList")
    var employeeNoList: List<EmployeeNo?>? = null,
    /**
     * 模糊查询关键字, 可选
     */
    var fuzzySearch: String? = null,
    /**
     * 会议编号UUID
     */
    var meetingID: String? = null,
    var pageNum: Int? = null,
    var pageCapacity: Int? = null
) {
    data class EmployeeNo(
        /**
         * 工号（人员ID）, 可选
         */
        var employeeNo: String? = null
    )
}

/**
 * 查询人员条件
 * /ISAPI/AccessControl/UserInfo/Search?format=json
 */
data class UserInfoSearchCondBean(
    @SerializedName("UserInfoSearchCond")
    var userInfoSearchCond: UserInfoSearchCond? = null
)

data class UserInfoSearch(
    var searchID: String? = null,
    @SearchResponseStatus
    var responseStatusStrg: String? = null,
    var numOfMatches: Int? = null,
    var totalMatches: Int? = null,
    @SerializedName("UserInfo")
    var userInfo: List<UserInfo?>? = null
)

/**
 * 查询人员结果
 * /ISAPI/AccessControl/UserInfo/Search?format=json
 */
data class UserInfoSearchBean(
    @SerializedName("UserInfoSearch")
    var userInfoSearch: UserInfoSearch? = null
)

data class EmployeeNo(
    var employeeNo: String? = null
)

data class UserInfoDelCond(
    @SerializedName("EmployeeNoList")
    var employeeNoList: List<EmployeeNo?>? = null,
    var operateType: String? = null,
    var terminalNoList: List<Int?>? = null
)

/**
 * 删除人员
 */
data class UserInfoDelCondBean(
    @SerializedName("UserInfoDelCond")
    var userInfoDelCond: UserInfoDelCond? = null
)