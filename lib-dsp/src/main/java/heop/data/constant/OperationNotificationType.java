package heop.data.constant;

import androidx.annotation.StringDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 操作通知上报类型
 */
@Retention(RetentionPolicy.SOURCE)
@Target({
        ElementType.FIELD,
        ElementType.PARAMETER,
        ElementType.LOCAL_VARIABLE,
})
@StringDef({
        OperationNotificationType.INSTALL_APP,
        OperationNotificationType.UNINSTALL_APP,
        OperationNotificationType.CLEAR_APP,
        OperationNotificationType.START_APP,
        OperationNotificationType.STOP_APP,
        OperationNotificationType.UPGRADE,
        OperationNotificationType.PUBLISH_UPDATE,
        OperationNotificationType.HICORE_ON_LINE,
        OperationNotificationType.RESTORE,
        OperationNotificationType.FACTORY_RESET,
        OperationNotificationType.MESSAGE,
        OperationNotificationType.MESSAGE_TIMEOUT,
        OperationNotificationType.MOTION_DETECTED,
        OperationNotificationType.UPGRADING,
        OperationNotificationType.APP_UPDATE,
        OperationNotificationType.APP_CONFIG,
        OperationNotificationType.WORK_MODE_CHANGE,
        OperationNotificationType.IP_ADDRESS_CHANGE,
        OperationNotificationType.CONTACT_UPDATE,
        OperationNotificationType.TIME_TYPE,
        OperationNotificationType.CUSTOM_TEXT_UPDATE,
        OperationNotificationType.VOICE_NOTIFICATION,
        OperationNotificationType.MEETING_UPDATE,
        OperationNotificationType.SHOW_MODE_UPDATE,
        OperationNotificationType.MASK_DETECTION,
        OperationNotificationType.HELMET_DETECTION,
        OperationNotificationType.ACS_CFG,
        OperationNotificationType.PWD_LOCKED,
        OperationNotificationType.FACE_DETECTED,
        OperationNotificationType.SCREEN_ON,
        OperationNotificationType.ATTENDANCE_MODE_CHANGE,
        OperationNotificationType.FACE_RECOGNITION,
})
public @interface OperationNotificationType {
    String INSTALL_APP = "installApp";          //安装APP
    String UNINSTALL_APP = "uninstallApp";      //卸载APP
    String CLEAR_APP = "clearApp";              //清除APP数据
    String START_APP = "startApp";              //启动APP
    String STOP_APP = "stopApp";                //停止APP
    String UPGRADE = "upgrade";                 //远程升级
    String PUBLISH_UPDATE = "publishUpdate";    //信息发布内容更新
    String HICORE_ON_LINE = "hicoreOnline";     //hicore上线
    String RESTORE = "restore";                 //恢复默认参数
    String FACTORY_RESET = "factoryReset";      //恢复出厂设置
    String MESSAGE = "message";                 //留言
    String MESSAGE_TIMEOUT = "messageTimeout";  //留言超时
    String MOTION_DETECTED = "MotionDetected";  //移动侦测生效
    String UPGRADING = "upgrading";             //正在升级
    String APP_UPDATE = "appUpdate";            //app有版本更新
    String APP_CONFIG = "appConfig";            //三方app配置变更
    String WORK_MODE_CHANGE = "workModeChange"; //工作模式变化
    String IP_ADDRESS_CHANGE = "IPAddressChange"; //有线网络配置变化
    String CONTACT_UPDATE = "contactUpdate"; //通讯录更新
    String TIME_TYPE = "timeType"; //时间类型配置
    String CUSTOM_TEXT_UPDATE = "customTextUpdate"; //自定义文字更新
    String VOICE_NOTIFICATION = "voiceNotification";    // 语音控制
    String MEETING_UPDATE = "meetingUpdate";            // 会议更新
    String SHOW_MODE_UPDATE = "showModeUpdate";         // 主题更新
    String MASK_DETECTION = "maskDetection";            // 口罩检测参数
    String HELMET_DETECTION = "HelmetDetection";        // 安全帽检测规则
    String ACS_CFG = "AcsCfg";                          // 门禁参数
    String PWD_LOCKED = "pwdLocked";                    //密码已锁
    String FACE_DETECTED = "faceDetected";               // 人脸检测事件
    String SCREEN_ON = "screenOn";      //通知亮屏
    String HEALTHY_SHOW_PARAM_CHANGE = "HealthShowParamChange";     //健康码显示变化
    String ATTENDANCE_MODE_CHANGE = "attendanceModeChange";//考勤模式反生改变
    String FACE_RECOGNITION = "faceRecognition"; //人脸识别参数
}
