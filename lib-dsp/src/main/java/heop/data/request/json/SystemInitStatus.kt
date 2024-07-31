package heop.data.request.json

import com.thoughtworks.xstream.annotations.XStreamAlias

@XStreamAlias("SystemInitSataus")
data class SystemInitStatus(
    val Progress: InitProgressBean? = null
)

data class InitProgressBean(
    val percent: Int? = null,
    val status: String? = null
)