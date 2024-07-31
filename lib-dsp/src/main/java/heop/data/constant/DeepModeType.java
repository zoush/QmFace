package heop.data.constant;

import androidx.annotation.StringDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 人脸模式
 */
@Retention(RetentionPolicy.SOURCE)
@Target({
        ElementType.FIELD,
        ElementType.PARAMETER,
        ElementType.LOCAL_VARIABLE,
})
@StringDef({
        DeepModeType.INFRARED_FD_TYPE,
        DeepModeType.INFRARED_FD_CODE,
        DeepModeType.BLACK_FD_TYPE,
        DeepModeType.BLACK_FD_CODE
})
public @interface DeepModeType {
    String INFRARED_FD_TYPE = "infraredFD";
    String INFRARED_FD_CODE = "2";
    String BLACK_FD_TYPE = "blackFD";
    String BLACK_FD_CODE = "1";
}