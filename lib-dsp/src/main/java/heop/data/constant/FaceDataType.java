package heop.data.constant;

import androidx.annotation.StringDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 人脸图片数据类型
 */
@Retention(RetentionPolicy.SOURCE)
@Target({
    ElementType.FIELD,
    ElementType.PARAMETER,
    ElementType.LOCAL_VARIABLE,
})
@StringDef({
    FaceDataType.URL,
    FaceDataType.BINARY,
})
public @interface FaceDataType {
    String URL = "url";   //响应数据为纯json格式（图片url）
    String BINARY = "binary";//响应数据为boundary格式（二进制图片）
}