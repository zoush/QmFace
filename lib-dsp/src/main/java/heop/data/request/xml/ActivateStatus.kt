package heop.data.request.xml

import com.thoughtworks.xstream.annotations.XStreamAlias

/**
 * 设备激活状态
 *
 * /SDK/activateStatus
 */
@XStreamAlias("ActivateStatus")
data class ActivateStatus(
    @XStreamAlias("Activated")
    var activated: Boolean? = null  // 设备是否已经激活
)