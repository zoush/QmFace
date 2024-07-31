package heop.data.request.json

import com.google.gson.annotations.SerializedName

/**
 * 设备显示区域配置
 * /ISAPI/VideoIntercom/displayRegion?format=json
 */
data class DisplayRegion(
    var x: Double? = null, // 左上角点x坐标,范围为0~1
    var y: Double? = null, // 左上角点y坐标,范围为0~1
    var width: Double? = null, // 矩形的宽度,范围为0~1
    var height: Double? = null // 矩形的宽度,范围为0~1
)

data class DisplayRegionBean(
    @SerializedName("DisplayRegion")
    var displayRegion: DisplayRegion? = null
)