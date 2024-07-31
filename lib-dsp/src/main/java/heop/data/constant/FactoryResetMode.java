package heop.data.constant;

import androidx.annotation.StringDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 恢复默认参数模式
 */
@Retention(RetentionPolicy.SOURCE)
@Target({
    ElementType.FIELD,
    ElementType.PARAMETER,
    ElementType.LOCAL_VARIABLE,
})
@StringDef({
    FactoryResetMode.FULL,
    FactoryResetMode.BASIC,
    FactoryResetMode.OTHER,
})
public @interface FactoryResetMode {
    String FULL = "full";    // 所有参数都恢复默认值（恢复出厂设置）
    String BASIC = "basic";  // 表示除了网络参数，其他参数都恢复默认
    String OTHER = "other";    // 表示除了网络参数和用户参数，其他参数都恢复默认
}