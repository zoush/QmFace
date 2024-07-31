package heop.data.request.json

import com.google.gson.annotations.SerializedName
import heop.data.constant.NoMaskStrategy

/**
 * 口罩检测配置
 *
 * /ISAPI/AccessControl/maskDetection?format=json
 */
data class MaskDetectionBean(
    @SerializedName("MaskDetection")
    var maskDetection: MaskDetection? = null
)

data class MaskDetection(
    var enable: Boolean? = null,  // 是否开启口罩检测
    @NoMaskStrategy
    var noMaskStrategy: String? = null // 口罩检测策略
)

/**
 * 口罩检测配置能力集
 *
 * /ISAPI/AccessControl/maskDetection/capabilities?format=json
 */
data class MaskDetectionCapBean(
    @SerializedName("MaskDetectionCap")
    val maskDetectionCap: MaskDetectionCap? = null
)

data class MaskDetectionCap(
    val enable: OptList<String?>? = null,
    val noMaskStrategy: OptList<String?>? = null,
)