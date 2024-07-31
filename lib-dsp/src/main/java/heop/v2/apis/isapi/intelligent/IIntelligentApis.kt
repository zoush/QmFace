package heop.v2.apis.isapi.intelligent

import com.hik.heop.data.request.json.*
import heop.data.request.xml.FaceRule
import com.hik.vis.heop.data.request.xml.ResponseStatus
import com.hik.vis.heop.v2.annotation.*
import com.hik.vis.heop.v2.api.HeopResult
import heop.data.request.json.FDLibBean
import heop.data.request.json.FDSearchCond
import heop.data.request.json.FDSearchResult
import heop.data.request.json.FaceData

/**
 * /ISAPI/Intelligent/ 相关协议
 */
interface IIntelligentApis {
    /**
     * 设置人脸识别区域
     *
     * @param faceRule 人脸识别区域
     */
    @Heop(
        method = HeopMethod.PUT,
        reqBodyType = BodyType.XML,
        respBodyType = BodyType.XML,
        path = "/ISAPI/Intelligent/channels/{id}/faceRule"
    )
    suspend fun putFaceRule(@Path("id") id: Int, @Body faceRule: FaceRule): HeopResult<ResponseStatus>

    /**
     * 获取所有人脸库信息
     */
    @Heop(
        method = HeopMethod.GET,
        respBodyType = BodyType.JSON,
        path = "/ISAPI/Intelligent/FDLib?format=json"
    )
    suspend fun getFDLib(): HeopResult<FDLibBean>

    /**
     * 查询所有人脸库人脸记录总数
     *//*
    @Heop(
        method = HeopMethod.GET,
        respBodyType = BodyType.JSON,
        path = "/ISAPI/Intelligent/FDLib/Count?format=json"
    )
    suspend fun getFDLibCount(): HeopResult<FDRecordDataInfoBean>*/

    /**
     * 人脸设置
     */
    @Heop(
        method = HeopMethod.PUT,
        reqBodyType = BodyType.JSON,
        respBodyType = BodyType.JSON,
        path = "/ISAPI/Intelligent/FDLib/FDSetUp?format=json"
    )
    suspend fun putFDSetUp(@Body bean: FaceData): HeopResult<ResponseStatus>

    /**
     * 人脸查询
     */
    @Heop(
        method = HeopMethod.POST,
        reqBodyType = BodyType.JSON,
        respBodyType = BodyType.JSON,
        path = "/ISAPI/Intelligent/FDLib/FDSearch?format=json"
    )
    suspend fun postFDSearch(@Body bean: FDSearchCond): HeopResult<FDSearchResult>
}