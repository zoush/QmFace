package heop.data.constant;

import androidx.annotation.StringDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 行业(场景)类型
 */
@Retention(RetentionPolicy.SOURCE)
@Target({
    ElementType.FIELD,
    ElementType.PARAMETER,
    ElementType.LOCAL_VARIABLE,
})
@StringDef({
    IndustryType.BUILDINGS,
    IndustryType.PRISON,
    IndustryType.MEDICAL_TREATMENT,
    IndustryType.BROADCASTING,
})
public @interface IndustryType {
    String BUILDINGS = "builidings";// 楼宇
    String PRISON = "prison";     //监所
    String MEDICAL_TREATMENT = "medicalTreatment";   //医疗
    String BROADCASTING = "broadcasting";// 广播
}