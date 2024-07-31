package heop.data.constant;

import androidx.annotation.StringDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 多方对讲呼叫模式
 */
@Retention(RetentionPolicy.SOURCE)
@Target({
    ElementType.FIELD,
    ElementType.PARAMETER,
    ElementType.LOCAL_VARIABLE,
})
@StringDef({
    MutiCallMode.MEETING,
    MutiCallMode.COMMAND,
})
public @interface MutiCallMode {
    String MEETING = "meeting";   // 会议模式
    String COMMAND = "command";   // 指挥模式
}