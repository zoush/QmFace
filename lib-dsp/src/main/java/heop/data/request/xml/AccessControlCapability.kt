package heop.data.request.xml

import com.thoughtworks.xstream.annotations.XStreamAlias

@XStreamAlias("AccessControl")
data class AccessControlCapability(
    // 是否支持测温配置, desc:支持时：返回true,不支持时：不返回该字段
    val isSupportTemperatureMeasureCfg: Boolean? = null
)