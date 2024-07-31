package heop.v2.apis.isapi.heop.system

import heop.data.request.json.ActivateInfoBean
import heop.data.request.json.CameraInfoBean
import heop.data.request.json.SystemInitStatus
import com.hik.vis.heop.data.request.xml.ResponseStatus
import com.hik.vis.heop.v2.annotation.Body
import com.hik.vis.heop.v2.annotation.BodyType
import com.hik.vis.heop.v2.annotation.Heop
import com.hik.vis.heop.v2.annotation.HeopMethod
import com.hik.vis.heop.v2.api.HeopResult

/**
 * /ISAPI/HEOP/System/ 相关协议
 */
interface IHeopSystemApis {
    /**
     * 激活设备
     *
     * @param bean 激活信息
     */
    @Heop(
        method = HeopMethod.PUT,
        reqBodyType = BodyType.JSON,
        respBodyType = BodyType.JSON,
        path = "/ISAPI/HEOP/System/activate?format=json"
    )
    suspend fun putActivate(@Body bean: ActivateInfoBean): HeopResult<ResponseStatus>

    /**
     * 获取设备初始化状态
     */
    @Heop(
        method = HeopMethod.GET,
        respBodyType = BodyType.JSON,
        path = "/ISAPI/HEOP/System/Init/progress?format=json"
    )
    suspend fun getSystemInitStatus(): HeopResult<SystemInitStatus>

    /**
     * 摄像头信息
     */
    @Heop(
        method = HeopMethod.GET,
        respBodyType = BodyType.JSON,
        path = "/ISAPI/HEOP/System/cameraInfo?format=json"
    )
    suspend fun getCameraInfo(): HeopResult<CameraInfoBean>
}