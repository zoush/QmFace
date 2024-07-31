package heop.data.request.xml

import com.thoughtworks.xstream.annotations.XStreamAlias

/**
 * 配置人脸识别参数
 *
 * /ISAPI/AccessControl/FaceCompareCond
 */
@XStreamAlias("FaceCompareCond")
data class FaceCompareCond(
    var leftBorder: Int = 0, // 人脸左边界限 , range:[0,100]
    var rightBorder: Int = 0, // 人脸右边界限 , range:[0,100]
    var upBorder: Int = 0, // 人脸上边界限 , range:[0,100]
    var bottomBorder: Int = 0 // 人脸下边界限 , range:[0,100]
)