package heop.v2.apis.sdk

import heop.data.request.xml.ActivateStatus
import com.hik.vis.heop.v2.annotation.BodyType
import com.hik.vis.heop.v2.annotation.Heop
import com.hik.vis.heop.v2.annotation.HeopMethod
import com.hik.vis.heop.v2.api.HeopResult

/**
 * /SDK/ 相关协议
 */
interface ISDKApis {
    /**
     * 获取激活状态
     */
    @Heop(
        method = HeopMethod.GET,
        respBodyType = BodyType.XML,
        path = "/SDK/activateStatus"
    )
    suspend fun getActiveStatus(): HeopResult<ActivateStatus>
}