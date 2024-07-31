package heop.data.constant;

import androidx.annotation.StringDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * desc:发起和关闭对讲时，通知UI开启和关闭虚拟屏幕（虚拟屏幕用于显示画面
 */
@Retention(RetentionPolicy.SOURCE)
@Target({
    ElementType.FIELD,
    ElementType.PARAMETER,
    ElementType.LOCAL_VARIABLE,
})
@StringDef({
    VirtualScreenStatusType.OPEN,
    VirtualScreenStatusType.CLOSE,

})
public @interface VirtualScreenStatusType {
    String OPEN = "open";    // 开启
    String CLOSE = "close";  // 关闭

}
