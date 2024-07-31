package heop.v2.apis.isapi.accesscontrol

import com.hik.heop.data.request.json.*
import com.hik.heop.data.request.xml.*
import com.hik.vis.heop.data.request.xml.ResponseStatus
import com.hik.vis.heop.v2.annotation.*
import com.hik.vis.heop.v2.api.HeopResult
import heop.data.request.json.*
import heop.data.request.xml.*
import heop.data.request.xml.CaptureFaceData
import heop.data.request.xml.CaptureFaceDataCond

/**
 * /ISAPI/AccessControl/ 相关协议
 */
interface IAccessControlApis {
    /**
     * 获取门禁总能力
     */
    @Heop(
        method = HeopMethod.GET,
        respBodyType = BodyType.XML,
        path = "/ISAPI/AccessControl/capabilities"
    )
    suspend fun getAccessControlCapability(): HeopResult<AccessControlCapability>

    /**
     *  配置人脸识别参数
     *
     *  @param param 人脸识别参数
     */
    @Heop(
        method = HeopMethod.PUT,
        reqBodyType = BodyType.XML,
        respBodyType = BodyType.XML,
        path = "/ISAPI/AccessControl/FaceCompareCond"
    )
    suspend fun putFaceCompareCond(@Body param: FaceCompareCond): HeopResult<ResponseStatus>

    /**
     * 远程控门
     *
     * @param bean 远程控门参数
     */
    @Heop(
        method = HeopMethod.PUT,
        reqBodyType = BodyType.XML,
        respBodyType = BodyType.XML,
        path = "/ISAPI/AccessControl/RemoteControl/door/1"
    )
    suspend fun remoteControlDoor(@Body bean: RemoteControlDoor): HeopResult<ResponseStatus>

    /**
     * 人脸采集
     *
     * @param bean 人脸采集条件
     */
    @Heop(
        method = HeopMethod.POST,
        reqBodyType = BodyType.XML,
        respBodyType = BodyType.XML,
        path = "/ISAPI/AccessControl/CaptureFaceData"
    )
    suspend fun postCaptureFace(@Body bean: CaptureFaceDataCond): HeopResult<CaptureFaceData>

    /**
     * 获取门禁参数
     */
    @Heop(
        method = HeopMethod.GET,
        respBodyType = BodyType.JSON,
        path = "/ISAPI/AccessControl/AcsCfg?format=json"
    )
    suspend fun getAcsCfg(): HeopResult<AcsCfgBean>

    /**
     * 配置门禁参数
     */
    @Heop(
        method = HeopMethod.PUT,
        reqBodyType = BodyType.JSON,
        respBodyType = BodyType.JSON,
        path = "/ISAPI/AccessControl/AcsCfg?format=json"
    )
    suspend fun putAcsCfg(@Body bean: AcsCfgBean): HeopResult<ResponseStatus>

    /**
     * 人员设置
     *
     * @param bean 人员信息
     */
    @Heop(
        method = HeopMethod.PUT,
        reqBodyType = BodyType.JSON,
        respBodyType = BodyType.JSON,
        path = "/ISAPI/AccessControl/UserInfo/SetUp?format=json"
    )
    suspend fun putUserInfoSetUp(@Body bean: UserInfoBean): HeopResult<ResponseStatus>

    /**
     * 人员查询
     *
     * @param bean 人员查询条件
     */
    @Heop(
        method = HeopMethod.POST,
        reqBodyType = BodyType.JSON,
        respBodyType = BodyType.JSON,
        path = "/ISAPI/AccessControl/UserInfo/Search?format=json"
    )
    suspend fun postUserInfoSearch(@Body bean: UserInfoSearchCondBean): HeopResult<UserInfoSearchBean>

    /**
     * 人员删除
     *
     * @param bean 人员删除条件
     */
    @Heop(
        method = HeopMethod.PUT,
        reqBodyType = BodyType.JSON,
        respBodyType = BodyType.JSON,
        path = "/ISAPI/AccessControl/UserInfo/Delete?format=json"
    )
    suspend fun putUserInfoDelete(@Body bean: UserInfoDelCondBean): HeopResult<ResponseStatus>

    /**
     * 查询所有卡数量
     */
    @Heop(
        method = HeopMethod.GET,
        respBodyType = BodyType.JSON,
        path = "/ISAPI/AccessControl/CardInfo/Count?format=json"
    )
    suspend fun getCardInfoCount(): HeopResult<CardInfoCountBean>

    /**
     * 查询指定工号所有卡数量
     */
    @Heop(
        method = HeopMethod.GET,
        respBodyType = BodyType.JSON,
        path = "/ISAPI/AccessControl/CardInfo/Count?format=json&employeeNo={employeeNo}"
    )
    suspend fun getCardInfoCount(@Path("employeeNo") employeeNo: String): HeopResult<CardInfoCountBean>

    /**
     * 卡片设置
     */
    @Heop(
        method = HeopMethod.PUT,
        reqBodyType = BodyType.JSON,
        respBodyType = BodyType.JSON,
        path = "/ISAPI/AccessControl/CardInfo/SetUp?format=json"
    )
    suspend fun putCardInfoSetUp(@Body bean: CardInfoBean): HeopResult<ResponseStatus>

    /**
     * 卡片查询
     */
    @Heop(
        method = HeopMethod.POST,
        reqBodyType = BodyType.JSON,
        respBodyType = BodyType.JSON,
        path = "/ISAPI/AccessControl/CardInfo/Search?format=json"
    )
    suspend fun postCardInfoSearch(@Body bean: CardInfoSearchCondBean): HeopResult<CardInfoSearchBean>

    /**
     * 卡片删除
     */
    @Heop(
        method = HeopMethod.PUT,
        reqBodyType = BodyType.JSON,
        respBodyType = BodyType.JSON,
        path = "/ISAPI/AccessControl/CardInfo/Delete?format=json"
    )
    suspend fun putCardInfoDelete(@Body bean: CardInfoDelCondBean): HeopResult<ResponseStatus>
}