package heop.data.request.json

import com.google.gson.annotations.SerializedName

/**
 * 摄像头信息
 *
 * /ISAPI/HEOP/System/cameraInfo?format=json
 */
data class CameraInfo(
    @SerializedName("List")
    var list: List<Info?>? = null
) {
    data class Info(
        var id: Int? = null,
        var videoResolutionWidth: Int? = null,
        var videoResolutionHeight: Int? = null,
        var rotationAngle: Int? = null, // 旋转角度, subType:int, [0#0度,90#90度,180#180度,270#270度]
    )
}

data class CameraInfoBean(
    @SerializedName("CameraInfo")
    var cameraInfo: CameraInfo? = null
)