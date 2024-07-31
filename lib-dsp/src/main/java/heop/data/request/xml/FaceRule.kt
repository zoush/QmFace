package heop.data.request.xml

import com.thoughtworks.xstream.annotations.XStreamAlias
import com.thoughtworks.xstream.annotations.XStreamImplicit

/**
 * 人脸识别区域
 * /ISAPI/Intelligent/channels/1/faceRule
 */
@XStreamAlias("FaceRule")
data class FaceRule(
    var enabled: Boolean? = null,

    @XStreamAlias("FaceDetectionRegion")
    var faceDetectionRegion: FaceDetectionRegion? = null
) {
    data class FaceDetectionRegion(
        @XStreamAlias("RegionCoordinatesList")
        var regionCoordinatesList: RegionCoordinatesList? = null
    )

    data class RegionCoordinatesList(
        @XStreamImplicit(itemFieldName = "RegionCoordinates")
        var regionCoordinates: List<RegionCoordinates?>? = null
    )

    data class RegionCoordinates(
        var positionX: Int? = null,
        var positionY: Int? = null
    )
}