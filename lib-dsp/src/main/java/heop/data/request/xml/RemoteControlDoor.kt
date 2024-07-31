package heop.data.request.xml

import com.thoughtworks.xstream.annotations.XStreamAlias
import heop.data.constant.RemoteControlDoorCmd

/**
 * 远程控门
 */
@XStreamAlias("RemoteControlDoor")
data class RemoteControlDoor(
    /**
     * 控门指令
     * 取值范围参考[RemoteControlDoorCmd]
     */
    @RemoteControlDoorCmd val cmd: String,
)
