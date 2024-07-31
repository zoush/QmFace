package heop.data.constant;

import androidx.annotation.StringDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 控门指令
 */
@Retention(RetentionPolicy.SOURCE)
@Target({
        ElementType.FIELD,
        ElementType.PARAMETER,
        ElementType.LOCAL_VARIABLE
})
@StringDef({
        RemoteControlDoorCmd.OPEN,
        RemoteControlDoorCmd.CLOSE,
        RemoteControlDoorCmd.ALWAYSOPEN,
        RemoteControlDoorCmd.ALWAYSCLOSE,
})
public @interface RemoteControlDoorCmd {
    String OPEN = "open";               // 开门
    String CLOSE = "close";             // 关闭
    String ALWAYSOPEN = "alwaysOpen";   // 常开
    String ALWAYSCLOSE = "alwaysClose"; // 常关
}
