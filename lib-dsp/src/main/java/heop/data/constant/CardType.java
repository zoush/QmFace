package heop.data.constant;

import androidx.annotation.StringDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 卡类型
 */
@Retention(RetentionPolicy.SOURCE)
@Target({
        ElementType.FIELD,
        ElementType.PARAMETER,
        ElementType.LOCAL_VARIABLE,
})
@StringDef({
        CardType.NORMAL_CARD,
        CardType.PATROL_CARD,
        CardType.HIJACK_CARD,
        CardType.SUPER_CARD,
        CardType.DISMISSING_CARD,
        CardType.EMERGENCY_CARD,
})
public @interface CardType {
    String NORMAL_CARD = "normalCard";         //普通卡
    String PATROL_CARD = "patrolCard";         //巡更卡
    String HIJACK_CARD = "hijackCard";         //胁迫卡
    String SUPER_CARD = "superCard";           //超级卡
    String DISMISSING_CARD = "dismissingCard"; //解除卡
    String EMERGENCY_CARD = "emergencyCard";   //应急管理
}