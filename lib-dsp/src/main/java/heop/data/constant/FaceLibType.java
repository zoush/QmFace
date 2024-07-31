package heop.data.constant;

import androidx.annotation.StringDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 人脸库类型
 */
@Retention(RetentionPolicy.SOURCE)
@Target({
        ElementType.FIELD,
        ElementType.PARAMETER,
        ElementType.LOCAL_VARIABLE,
})
@StringDef({
        FaceLibType.BLACK_FD,
        FaceLibType.STATIC_FD,
        FaceLibType.INFRARED_FD,
})
public @interface FaceLibType {
    String BLACK_FD = "blackFD"; //名单库
    String STATIC_FD = "staticFD"; //静态库
    String INFRARED_FD =  "infraredFD"; //红外库
}