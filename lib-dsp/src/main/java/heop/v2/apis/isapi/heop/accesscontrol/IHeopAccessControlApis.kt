package heop.v2.apis.isapi.heop.accesscontrol

import com.hik.heop.data.request.json.VerifyModeBean
import com.hik.vis.heop.data.request.xml.ResponseStatus
import com.hik.vis.heop.v2.annotation.Body
import com.hik.vis.heop.v2.annotation.BodyType
import com.hik.vis.heop.v2.annotation.Heop
import com.hik.vis.heop.v2.annotation.HeopMethod
import com.hik.vis.heop.v2.api.HeopResult

/**
 * /ISAPI/HEOP/AccessControl/ 相关协议
 */
interface IHeopAccessControlApis {
    /**
     * 设置认证模式
     *
     * @param bean 认证模式配置
     */
    @Heop(
        method = HeopMethod.PUT,
        reqBodyType = BodyType.JSON,
        respBodyType = BodyType.JSON,
        path = "/ISAPI/HEOP/AccessControl/verifyMode?format=json"
    )
    suspend fun putVerifyMode(@Body bean: VerifyModeBean): HeopResult<ResponseStatus>
}