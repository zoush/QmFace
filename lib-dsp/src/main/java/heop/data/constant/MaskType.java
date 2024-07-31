package heop.data.constant;

import androidx.annotation.StringDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 口罩类型
 */
@Retention(RetentionPolicy.SOURCE)
@Target({
    ElementType.FIELD,
    ElementType.PARAMETER,
    ElementType.LOCAL_VARIABLE,
})
@StringDef({
    MaskType.UNKNOWN,
    MaskType.YES,
    MaskType.NO,
})
public @interface MaskType {
    String UNKNOWN = "unknown";    // 未知
    String YES = "yes";  // 已佩戴
    String NO = "no";      // 未佩戴
}
