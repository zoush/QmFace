package heop.v2.apis.isapi.sdt

import heop.data.request.json.ImagesComparisionBean
import heop.data.request.json.PictureAnalysisBean
import heop.data.response.HeopFaceResponse
import com.hik.vis.heop.data.request.xml.ResponseStatus
import com.hik.vis.heop.v2.annotation.Body
import com.hik.vis.heop.v2.annotation.BodyType
import com.hik.vis.heop.v2.annotation.Heop
import com.hik.vis.heop.v2.annotation.HeopMethod
import com.hik.vis.heop.v2.api.HeopResult

/**
 * /ISAPI/SDT/ 相关协议
 */
interface ISDTApis {

    /**
     * 人脸建模
     */
    @Heop(
        method = HeopMethod.POST,
        reqBodyType = BodyType.JSON,
        respBodyType = BodyType.JSON,
        path = "/ISAPI/SDT/Face/pictureAnalysis?format=json"
    )
    suspend fun pictureAnalysis(@Body pictureAnalysisBean: PictureAnalysisBean): HeopResult<HeopFaceResponse>

    /**
     * 1v1人脸比对
     */
    @Heop(
        method = HeopMethod.POST,
        reqBodyType = BodyType.JSON,
        respBodyType = BodyType.JSON,
        path = "/ISAPI/Intelligent/imagesComparision/face?format=json"
    )
    suspend fun imagesComparision(@Body imagesComparisionBean: ImagesComparisionBean): HeopResult<ResponseStatus>
}