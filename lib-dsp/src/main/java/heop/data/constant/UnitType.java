package heop.data.constant;

import androidx.annotation.StringDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 设备类型
 */
@Retention(RetentionPolicy.SOURCE)
@Target({
    ElementType.FIELD,
    ElementType.PARAMETER,
    ElementType.LOCAL_VARIABLE
})
@StringDef({
    UnitType.INDOOR,
    UnitType.VILLA,
    UnitType.CONFIRM,
    UnitType.OUTDOOR,
    UnitType.FENCE,
    UnitType.ACS,
    UnitType.INTERACTIVE,
    UnitType.MANAGE,
    UnitType.SIP,
    UnitType.DOORBELL,
    UnitType.TERMINAL,
    UnitType.NET_AUDIO,
    UnitType.EZVIZ_GATEWAY
})
public @interface UnitType {
    String INDOOR = "indoor";// 室内机
    String VILLA = "villa";     //别墅门口机
    String CONFIRM = "confirm";   //二次确认机
    String OUTDOOR = "outdoor";// 门口机
    String FENCE = "fence"; // 围墙机
    String ACS = "acs";        //门禁设备
    String INTERACTIVE = "interactive"; //交互终端
    String MANAGE = "manage"; // 管理机
    String SIP = "sip";        //sip服务器
    String DOORBELL = "doorbell";   //门铃机
    String TERMINAL = "terminal"; // 终端设备
    String NET_AUDIO = "netAudio"; // 网络音响
    String EZVIZ_GATEWAY = "ezvizGateway"; //萤石网关设备
    String IVMS_4200 = "4200";  //4200客户端
    String DEVICE_8700 = "8700"; //8700服务平台
    String HIK_CLOUD = "hik_cloud"; //云眸设备
}