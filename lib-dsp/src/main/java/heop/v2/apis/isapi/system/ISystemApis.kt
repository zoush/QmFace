package heop.v2.apis.isapi.system

import heop.data.request.json.AcsWorkStatus
import heop.data.request.xml.DeviceInfo
import heop.data.request.xml.IpAddress
import com.hik.vis.heop.data.request.xml.ResponseStatus
import com.hik.vis.heop.v2.annotation.*
import com.hik.vis.heop.v2.api.HeopResult
import heop.data.constant.FactoryResetMode

/**
 * /ISAPI/System/ 相关协议
 */
interface ISystemApis {
    /**
     *  获取设备信息
     */
    @Heop(
        method = HeopMethod.GET,
        respBodyType = BodyType.XML,
        path = "/ISAPI/System/deviceInfo"
    )
    suspend fun getDeviceInfo(): HeopResult<DeviceInfo>

    /**
     * 获取设备网络参数
     */
    @Heop(
        method = HeopMethod.GET,
        respBodyType = BodyType.XML,
        path = "/ISAPI/System/Network/interfaces/{interfaceId}/ipAddress"
    )
    suspend fun getIpAddress(@Path("interfaceId") interfaceId: String): HeopResult<IpAddress>

    /**
     * 设置设备网络参数
     */
    @Heop(
        method = HeopMethod.PUT,
        reqBodyType = BodyType.XML,
        respBodyType = BodyType.XML,
        path = "/ISAPI/System/Network/interfaces/{interfaceId}/ipAddress"
    )
    suspend fun putIpAddress(@Body bean: IpAddress, @Path("interfaceId") interfaceId: String): HeopResult<ResponseStatus>

    /**
     * 获取设备网络参数
     */
    @Heop(
        method = HeopMethod.GET,
        reqBodyType = BodyType.JSON,
        respBodyType = BodyType.JSON,
        path = "/ISAPI/AccessControl/AcsWorkStatus"
    )
    suspend fun getAcsWorkStatus(): HeopResult<AcsWorkStatus>

    /**
     *  恢复默认参数
     *
     *  @param mode 恢复默认参数模式
     */
    @Heop(
        method = HeopMethod.PUT,
        respBodyType = BodyType.XML,
        path = "/ISAPI/System/factoryReset?mode={mode}",
        timeout = 30_000
    )
    suspend fun putFactoryReset(@Path("mode") @FactoryResetMode mode: String): HeopResult<ResponseStatus>
}