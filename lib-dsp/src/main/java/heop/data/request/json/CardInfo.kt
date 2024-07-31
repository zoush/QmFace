package heop.data.request.json

import com.google.gson.annotations.SerializedName
import heop.data.constant.CardType

data class CardNo(
    var cardNo: String? = null
)

data class CardInfoSearchCond(
    var searchID: String? = null,
    var searchResultPosition: Int? = null,
    var maxResults: Int? = null,
    @SerializedName("EmployeeNoList")
    var employeeNoList: List<EmployeeNo?>? = null,
    @SerializedName("CardNoList")
    var cardNoList: List<CardNo?>? = null
)

/**
 * 查询卡
 * /ISAPI/AccessControl/CardInfo/Search?format=json
 */
data class CardInfoSearchCondBean(
    @SerializedName("CardInfoSearchCond")
    var cardInfoSearchCond: CardInfoSearchCond? = null
)

data class CardInfo(
    //是否删除该卡片， see [IAccessControlApis.setUpCardInfo]
    var deleteCard: Boolean? = null,
    var employeeNo: String? = null, //必填，string，工号（人员ID）
    var cardNo: String? = null, //必填，string，卡号
    @CardType
    var cardType: String? = null, //必填，string，卡类型，normalCard-普通卡，patrolCard-巡更卡，hijackCard-胁迫卡，superCard-超级卡，dismissingCard-解除卡，emergencyCard-应急管理卡（用于授权临时卡权限，本身不能开门）
    var leaderCard: String? = null //可选，string，是否有首次认证功能（表示该卡对于门1、门3、门5有首次认证功能）
)

/**
 * 添加卡
 * /ISAPI/AccessControl/CardInfo/Record?format=json
 */
data class CardInfoBean(
    var cardInfo: CardInfo? = null
)

data class CardInfoSearch(
    var searchID: String? = null,
    var responseStatusStrg: String? = null,
    var numOfMatches: Int? = null,
    var totalMatches: Int? = null,
    @SerializedName("CardInfo")
    var cardInfo: List<CardInfo?>? = null
)

/**
 * 查询卡
 * /ISAPI/AccessControl/CardInfo/Search?format=json
 */
data class CardInfoSearchBean(
    @SerializedName("CardInfoSearch")
    var cardInfoSearch: CardInfoSearch? = null
)

data class CardInfoDelCond(
    @SerializedName("EmployeeNoList")
    var employeeNoList: List<EmployeeNo?>? = null,
    @SerializedName("CardNoList")
    var cardNoList: List<CardNo?>? = null,
    var operateType: String? = null,
    var terminalNoList: List<Int?>? = null
)

/**
 * 删除卡
 * /ISAPI/AccessControl/CardInfo/Delete?format=json
 */
data class CardInfoDelCondBean(
    @SerializedName("CardInfoDelCond")
    var cardInfoDelCond: CardInfoDelCond? = null
)

data class CardInfoCapBean(
    @SerializedName("CardInfo")
    var cardInfo: CardInfoCap? = null
)

data class CardInfoCap(
    //支持卡的“增删改查、总数查询”功能：post-创建，delete-删除，put-修改，get-查询，setUp-设置（对应URL：/ISAPI/AccessControl/CardInfo/SetUp?format=json）
    var supportFunction: OptString? = null,
    //查找条件
    @SerializedName("CardInfoSearchCond")
    var cardInfoSearchCond: CardInfoSearchCond? = null,
    //删除条件
    @SerializedName("CardInfoDelCond")
    var cardInfoDelCond: CardInfoDelCond? = null,
    //string，卡号
    var cardNo: MinMaxInt? = null,
    //string，工号（人员ID）
    var employeeNo: MinMaxInt? = null,
    //// string，卡类型，normalCard-普通卡，patrolCard-巡更卡，hijackCard-胁迫卡，superCard-超级卡，dismissingCard-解除卡，emergencyCard-应急管理卡（用于授权临时卡权限，本身不能开门）
    var cardType: OptString? = null,
    // string，是否有首次认证功能
    var leaderCard: MinMaxInt? = null,
    // boolean，设备是否进行卡重复添加校验，false-不校验，true-校验（如果不配置该字段，则设备默认进行卡重复校验。注：该字段用于加快下发卡信息，一般不建议配置该字段）
    var checkCardNo: String? = null,
    // boolean，设备是否进行工号（人员ID）存在校验，false-不校验，true-校验（如果不配置该字段，则设备默认进行工号（人员ID）存在校验）（该字段配置为false时，设备不进行工号（人员ID）存在校验（可加快设备下发卡的速度）；该字段配置为true或不配置该字段时，设备进行工号（人员ID）存在校验，如不对处理速度有强烈要求，建议使用该方式）
    var checkEmployeeNo: String? = null,
    // integer，支持的最大记录条数（卡数量）（该字段对于平台集成十分重要，虽定义成可选，但建议设备实现）
    var maxRecordNum: Int? = null,
    // opt, enum integer，每人的卡数量限制，[255#无限制]，默认一人五张卡片
    var numberPerPerson: Int? = null,
) {
    /**
     * 查找条件
     */
    data class CardInfoSearchCond(
        //本次协议调用可获取的最大记录数,integer32类型
        var maxResults: MinMaxInt? = null,
        @SerializedName("EmployeeNoList")
        var employeeNoList: EmployeeNoList? = null,
        @SerializedName("CardNoList")
        var cardNoList: CardNoList? = null
    ) {
        /**
         * 人员ID列表
         */
        data class EmployeeNoList(
            //支持可配置的范围0-maxSize
            var maxSize: Int? = null,
            // string，工号（人员ID）
            var employeeNo: MinMaxInt? = null
        )

        /**
         * 卡号列表
         */
        data class CardNoList(
            //支持可配置的范围0-maxSize
            var maxSize: Int? = null,
            // string，卡号
            var cardNo: MinMaxInt? = null
        )
    }

    data class CardInfoDelCond(
        @SerializedName("EmployeeNoList")
        var employeeNoList: EmployeeNoList? = null,
        @SerializedName("CardNoList")
        var cardNoList: CardNoList? = null
    ) {
        /**
         * 人员ID列表
         */
        data class EmployeeNoList(
            //支持可配置的范围0-maxSize
            var maxSize: Int? = null,
            // string，工号（人员ID）
            var employeeNo: MinMaxInt? = null
        )

        /**
         * 卡号列表
         */
        data class CardNoList(
            //支持可配置的范围0-maxSize
            var maxSize: Int? = null,
            // string，卡号
            var cardNo: MinMaxInt? = null
        )
    }
}

/**
 * 所有卡数量
 * GET/ISAPI/AccessControl/CardInfo/Count?format=json
 */
data class CardInfoCountBean(
    @SerializedName("CardInfoCount")
    var cardInfoCount: CardInfoCount? = null
)

data class CardInfoCount(
    var cardNumber: Int? = null
)