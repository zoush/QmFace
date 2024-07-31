package heop.data.constant;

import androidx.annotation.StringDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 激活类型
 */
@Retention(RetentionPolicy.SOURCE)
@Target({
        ElementType.FIELD,
        ElementType.PARAMETER,
        ElementType.LOCAL_VARIABLE,
})
@StringDef({
        ActivateType.LOCAL,
        ActivateType.REMOTE,
})
public @interface ActivateType {
    String LOCAL = "local";
    String REMOTE = "remote";
}
