package heop.data.request.json

import com.google.gson.annotations.SerializedName

/**
 * 安全帽检测
 */
data class SafetyHelmetDetectionBean(
    @SerializedName("SafetyHelmetDetection")
    var safetyHelmetDetection: SafetyHelmetDetection? = null
)

data class SafetyHelmetDetection(
    var enable: Boolean? = null, //是否开启安全帽检测
    var noHelmetStrategy: String? = null //未戴安全帽是否开门，normal-开门通行、forbidden-禁止通行
)