package heop.data.constant;

import androidx.annotation.StringDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 未戴口罩是否开门策略
 */
@Retention(RetentionPolicy.SOURCE)
@Target({
    ElementType.FIELD,
    ElementType.PARAMETER,
    ElementType.LOCAL_VARIABLE,
})
@StringDef({
    NoMaskStrategy.TIPS_AND_OPEN_DOOR,
    NoMaskStrategy.NO_TIPS_AND_OPEN_DOOR,
    NoMaskStrategy.TIPS_AND_NOT_OPEN_DOOR,
    NoMaskStrategy.NO_TIPS_AND_CONSUME,
    NoMaskStrategy.TIPS_AND_CONSUME,
    NoMaskStrategy.TIPS_AND_NOT_CONSUME,
})
public @interface NoMaskStrategy {
    String TIPS_AND_OPEN_DOOR = "tipsAndOpenDoor"; //提示且开门
    String NO_TIPS_AND_OPEN_DOOR = "noTipsAndOpenDoor"; //不提示且开门
    String TIPS_AND_NOT_OPEN_DOOR = "tipsAndNotOpenDoor"; //提示且不开门

    String NO_TIPS_AND_CONSUME = "noTipsAndConsume";//不提示且允许消费
    String TIPS_AND_CONSUME = "tipsAndConsume";//提示且允许消费
    String TIPS_AND_NOT_CONSUME = "tipsAndNotConsume";//提示且不允许消费
}