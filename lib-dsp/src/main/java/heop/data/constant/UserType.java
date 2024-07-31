package heop.data.constant;

import androidx.annotation.StringDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用户类别
 */
@Retention(RetentionPolicy.SOURCE)
@Target({
        ElementType.FIELD,
        ElementType.PARAMETER,
        ElementType.LOCAL_VARIABLE,
})
@StringDef({
        UserType.NORMAL,
        UserType.VISITOR,
        UserType.BLACK_LIST,
        UserType.ADMINISTRATORS,
        UserType.OPERATOR,
})
public @interface UserType {
    String NORMAL = "normal"; //普通人（主人）
    String VISITOR = "visitor"; //来宾（访客）
    String BLACK_LIST = "blackList"; //黑名单人
    String ADMINISTRATORS= "administrators"; // 管理员
    String OPERATOR = "operator"; // 操作员
}