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
    ElementType.LOCAL_VARIABLE,
})
@StringDef({
    VoiceTalkCmdType.REQUEST,
    VoiceTalkCmdType.CANCEL,
    VoiceTalkCmdType.ANSWER,
    VoiceTalkCmdType.HANGUP,
    VoiceTalkCmdType.REJECT,
    VoiceTalkCmdType.CALL_FAILED,
    VoiceTalkCmdType.BELL_TIMEOUT,
    VoiceTalkCmdType.DEVICE_ON_CALL,
    VoiceTalkCmdType.CALL_TIMEOUT,
    VoiceTalkCmdType.START_INTERRUPT,
    VoiceTalkCmdType.END_INTERRUPT,
    VoiceTalkCmdType.CALL_INFO
})
public @interface VoiceTalkCmdType {
    String REQUEST = "request"; // 呼叫
    String CANCEL = "cancel";   // 取消呼叫接听
    String ANSWER = "answer";   // 接听
    String HANGUP = "hangUp";   // 挂断
    String REJECT = "reject";   // 拒接
    String CALL_FAILED = "callFailed";      // 呼叫失败
    String BELL_TIMEOUT = "bellTimeout";    // 被叫响铃超时
    String DEVICE_ON_CALL = "deviceOnCall"; // 设备正在通话中
    String CALL_TIMEOUT = "callTimeout"; // 被叫响铃超时
    String START_INTERRUPT = "startInterrupt";     //开始插话
    String END_INTERRUPT = "endInterrupt";    //结束插话
    String CALL_INFO = "callInfo";      //通话中信息传递,用于锁数量上报等
}