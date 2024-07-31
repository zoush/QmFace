package heop.v2.apis.isapi.videointercom

import heop.data.request.json.CallSignalBean
import heop.data.request.json.DisplayRegionBean
import com.hik.heop.data.request.xml.DeviceId
import com.hik.vis.heop.data.request.xml.ResponseStatus
import com.hik.vis.heop.v2.annotation.Body
import com.hik.vis.heop.v2.annotation.BodyType
import com.hik.vis.heop.v2.annotation.Heop
import com.hik.vis.heop.v2.annotation.HeopMethod
import com.hik.vis.heop.v2.api.HeopResult

/**
 * /ISAPI/VideoIntercom/ 相关协议
 */
interface IVideoIntercomApis {
    /**
     * 设备显示区域配置
     *
     * @param displayRegionBean 显示区域配置
     */
    @Heop(
        method = HeopMethod.PUT,
        reqBodyType = BodyType.JSON,
        respBodyType = BodyType.JSON,
        path = "/ISAPI/VideoIntercom/displayRegion?format=json"
    )
    suspend fun putDisplayRegion(@Body displayRegionBean: DisplayRegionBean): HeopResult<ResponseStatus>

    /**
     * 设备编号获取
     */
    @Heop(
        method = HeopMethod.GET,
        respBodyType = BodyType.XML,
        path = "/ISAPI/VideoIntercom/deviceId"
    )
    suspend fun getDeviceId(): HeopResult<DeviceId>

    /**
     * 设备编号配置
     */
    @Heop(
        method = HeopMethod.PUT,
        reqBodyType = BodyType.XML,
        respBodyType = BodyType.XML,
        path = "/ISAPI/VideoIntercom/deviceId"
    )
    suspend fun putDeviceId(@Body bean: DeviceId): HeopResult<ResponseStatus>

    /**
     * 通话信令交互
     */
    @Heop(
        method = HeopMethod.PUT,
        reqBodyType = BodyType.JSON,
        respBodyType = BodyType.JSON,
        path = "/ISAPI/VideoIntercom/callSignal?format=json"
    )
    suspend fun putCallSignal(@Body bean: CallSignalBean): HeopResult<ResponseStatus>
}