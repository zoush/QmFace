package heop.data.constant;

import androidx.annotation.StringDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 认证类型
 * 除了管理员和采集 其他都是认证开门
 * 管理员：仅认证
 * 采集：不认证不开门
 * 普通认证：人脸识别、指纹识别、卡片识别开，二维码识别关
 * 关闭认证：全关
 * 二维码认证：人脸识别、指纹识别、卡片识别关，二维码识别开
 * 管理员认证：人脸识别、指纹识别、卡片识别开，二维码识别关
 * 低功耗模式：人脸识别关，指纹识别、卡片识别开，二维码识别关
 */
@Retention(RetentionPolicy.SOURCE)
@Target({
    ElementType.FIELD,
    ElementType.PARAMETER,
    ElementType.LOCAL_VARIABLE
})
@StringDef({
        VerifyType.VERIFY,
        VerifyType.VERIFY_AND_OPEN_DOOR,
        VerifyType.NONE,
        VerifyType.LOW_POWER,
        VerifyType.ENROLL,
})
public @interface VerifyType {
    String VERIFY = "verify";                               //仅认证（用于数据采集）
    String VERIFY_AND_OPEN_DOOR = "verifyAndOpenDoor";      //认证开门（用于权限认证）
    String NONE = "None";                                   //不认证不开门 （透明模式）
    String LOW_POWER = "lowPower";                          //低功耗模式
    String ENROLL = "enroll";                               //录入
}