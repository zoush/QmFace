package heop.data.constant;

import androidx.annotation.StringDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 认证模式类型
 */
@Retention(RetentionPolicy.SOURCE)
@Target({
    ElementType.FIELD,
    ElementType.PARAMETER,
    ElementType.LOCAL_VARIABLE,
})
@StringDef({
    VerifyModeType.CARD_AND_PW,
    VerifyModeType.CARD,
    VerifyModeType.CARD_OR_FACE,
    VerifyModeType.CARD_OR_PW,
    VerifyModeType.FP,
    VerifyModeType.FP_AND_PW,
    VerifyModeType.FP_OR_CARD,
    VerifyModeType.FP_AND_CARD,
    VerifyModeType.FP_AND_CARD_AND_PW,
    VerifyModeType.FACE_OR_FPOR_CARD_OR_PW,
    VerifyModeType.FACE_AND_FP,
    VerifyModeType.FACE_AND_PW,
    VerifyModeType.FACE_NDC_ARD,
    VerifyModeType.FACE,
    VerifyModeType.EMPLOYEE_NO_AND_PW,
    VerifyModeType.FP_OR_PW,
    VerifyModeType.EMPLOYEE_NO_AND_FP,
    VerifyModeType.EMPLOYEE_NO_AND_FP_AND_PW,
    VerifyModeType.FACE_AND_FP_AND_CARD,
    VerifyModeType.FACE_AND_PW_AND_FP,
    VerifyModeType.EMPLOYEE_NO_AND_FACE,
    VerifyModeType.FACE_OR_FACE_AND_CARD,
    VerifyModeType.FP_OR_FACE,
    VerifyModeType.CARD_OR_FACE_OR_PW,
})
public @interface VerifyModeType {
    String CARD_AND_PW = "cardAndPw"; // 刷卡+密码
    String CARD = "card"; // 刷卡
    String CARD_OR_FACE = "cardOrFace"; //刷卡或人脸
    String CARD_OR_PW = "cardOrPw"; // 刷卡或密码
    String FP = "fp"; // 指纹
    String FP_AND_PW = "fpAndPw"; // 指纹+密码
    String FP_OR_CARD = "fpOrCard"; // 指纹或刷卡
    String FP_AND_CARD = "fpAndCard"; // 指纹+刷卡
    String FP_AND_CARD_AND_PW = "fpAndCardAndPw"; // 指纹+刷卡+密码
    String FACE_OR_FPOR_CARD_OR_PW = "faceOrFpOrCardOrPw"; // 人脸或指纹或刷卡或密码
    String FACE_AND_FP = "faceAndFp"; // 人脸+指纹
    String FACE_AND_PW = "faceAndPw"; // 人脸+密码
    String FACE_NDC_ARD = "faceAndCard"; // 人脸+刷卡
    String FACE = "face"; // 人脸
    String EMPLOYEE_NO_AND_PW = "employeeNoAndPw"; // 号+密码
    String FP_OR_PW = "fpOrPw"; // 指纹或密码
    String EMPLOYEE_NO_AND_FP = "employeeNoAndFp"; // 工号+指纹
    String EMPLOYEE_NO_AND_FP_AND_PW = "employeeNoAndFpAndPw"; // 工号+指纹+密码
    String FACE_AND_FP_AND_CARD = "faceAndFpAndCard"; // 人脸+指纹+刷卡
    String FACE_AND_PW_AND_FP = "faceAndPwAndFp"; // 人脸+密码+指纹
    String EMPLOYEE_NO_AND_FACE = "employeeNoAndFace"; // 工号+人脸
    String FACE_OR_FACE_AND_CARD = "faceOrfaceAndCard"; // 人脸或人脸+刷卡
    String FP_OR_FACE = "fpOrface"; // 指纹或人脸
    String CARD_OR_FACE_OR_PW = "cardOrfaceOrPw"; // 刷卡或人脸或密码
}