package heop.v2.apis.isapi.heop.videointercom

import heop.data.request.json.TTSBean
import com.hik.vis.heop.data.request.xml.ResponseStatus
import com.hik.vis.heop.v2.annotation.Body
import com.hik.vis.heop.v2.annotation.BodyType
import com.hik.vis.heop.v2.annotation.Heop
import com.hik.vis.heop.v2.annotation.HeopMethod
import com.hik.vis.heop.v2.api.HeopResult

/**
 * /ISAPI/HEOP/System/ 相关协议
 */
interface IHeopVideoIntercomApis {
    /**
     * 激活设备
     *
     * @param bean 激活信息
     */
    @Heop(
        method = HeopMethod.PUT,
        reqBodyType = BodyType.JSON,
        respBodyType = BodyType.JSON,
        path = "/ISAPI/HEOP/VideoIntercom/TTS?format=json"
    )
    suspend fun putTTS(@Body bean: TTSBean): HeopResult<ResponseStatus>

}