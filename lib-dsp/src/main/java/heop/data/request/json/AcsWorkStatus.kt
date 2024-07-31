package heop.data.request.json

import com.google.gson.annotations.SerializedName

data class AcsWorkStatus(
        /**
         * AcsWorkStatus : {"netStatus":"ipConflict","sipStatus":"disconnect","ezvizStatus":"notAdd","voipStatus":"unregistered"}
         */
        var AcsWorkStatus: AcsWorkStatusBean? = null
)

enum class DoorStatusType(val value: Int) {
    // 休眠
    dormant(1),

    // 常开
    passMode(2),

    // 常闭
    always_close(3),

    // 普通状态
    accessControlMode(4)
}


class AcsWorkStatusBean {
    /**
     * netStatus : ipConflict
     * sipStatus : disconnect
     * ezvizStatus : notAdd
     * voipStatus : unregistered
     */
    // array，门状态（楼层状态），1-休眠，2-常开状态（自由），3-常闭状态（禁用），4-普通状态（受控）（[1,2,1,2]代表：门1休眠，门2常开状态，门3休眠，门4常开状态）
    var doorStatus: List<Int>? = null

    // array，门锁状态（继电器开合状态），0-正常关，1-正常开，2-短路报警，3-断路报警，4-异常报警（[1,2,1,2]代表：门锁1正常开，门锁2短路报警，门锁3正常开，门锁4短路报警）
    var doorLockStatus: List<Int>? = null

    // array，门磁状态，0-正常关，1-正常开，2-短路报警，3-断路报警，4-异常报警（[1,2,1,2]代表：门磁1正常开，门磁2短路报警，门磁3正常开，门磁4短路报警）
    var magneticStatus: List<Int>? = null

    // array，事件触发器状态（[1,3,5]代表：事件触发器1、事件触发器3、事件触发器5有输入）
    var caseStatus: List<Int>? = null

    // string，多门互锁状态，close-关闭，open-开启
    var multiDoorInterlockStatus: String? = null

    // string，反潜回状态，close-关闭，open-开启
    var antiSneakStatus: String? = null

    // string，主机防拆状态，close-关闭，open-开启
    var hostAntiDismantleStatus: String? = null

    // array，读卡器在线状态（[1,3,5]代表：读卡器1、读卡器3、读卡器5在线）
    var cardReaderOnlineStatus: List<Int>? = null

    // array，读卡器防拆状态（[1,3,5]代表：读卡器1、读卡器3、读卡器5防拆功能开启）
    var cardReaderAntiDismantleStatus: List<Int>? = null

    // array，读卡器当前验证方式，1-休眠，2-刷卡+密码，3-刷卡，4-刷卡或密码，
    //      5-指纹，6-指纹+密码，7-指纹或刷卡，8-指纹+刷卡，9-指纹+刷卡+密码，
    //      10-人脸或指纹或刷卡或密码，11-人脸+指纹，12-人脸+密码，13-人脸+刷卡，
    //      14-人脸，15-工号+密码，16-指纹或密码，17-工号+指纹，18-工号+指纹+密码，
    //      19-人脸+指纹+刷卡，20-人脸+密码+指纹，21-工号+人脸，22-人脸或人脸+刷卡，
    //      23-指纹或人脸，24-刷卡或人脸或密码（[3,5,3,5]代表：读卡器1当前验证方式为刷卡，
    //      读卡器2当前验证方式为指纹，读卡器3当前验证方式为刷卡，读卡器4当前验证方式为指纹）
    var cardReaderVerifyMode: List<Int>? = null

    // array，报警输入口布防状态（[1,3,5]代表：报警输入口1、报警输入口3、报警输入口5处于布防状态）
    var setupAlarmStatus: List<Int>? = null

    // array，报警输入口报警状态（[1,3,5]代表：报警输入口1、报警输入口3、报警输入口5有报警）
    var alarmInStatus: List<Int>? = null

    // array，报警输出口状态（[1,3,5]代表：报警输出口1、报警输出口3、报警输出口5有报警）
    var alarmOutStatus: List<Int>? = null

    // integer，已添加的卡数量
    var cardNum: Int? = null

    // string，反潜回服务器状态，disable-未启用，normal-正常，disconnect-断开
    var antiSneakServerStatus: String? = null

    // string，网络状态（connect-网络正常连接，disconnect-网络断开连接，ipConflict-网络IP冲突）
    var netStatus: String? = null

    // string，SIP状态（connect-SIP正常连接，disconnect-SIP断开连接，unregistered-SIP未注册）
    var sipStatus: String? = null

    // string，信号状态（noSignal-无信号，2G-2G信号，3G-3G信号，4G-4G信号）
    var signalStatus: String? = null

    // string，EZVIZ状态（unregistered-设备未注册EZVIZ，notAdd-设备未添加到EZVIZ，connect-设备正常连接到EZVIZ）
    var ezvizStatus: String? = null

    // string，VOIP状态（unregistered-VOIP未注册，connect-VOIP正常连接）
    var voipStatus: String? = null

    // string，DHCP状态(success-成功 failure-失败)
    var dhcpStatus: String? = null

    // string，测温状态(connect, disconnect)
    var tempStatus: String? = null

    // string，人脸下载状态(downloading, over)
    var faceDownStatus: String? = null

    // List，多网口状态
    @SerializedName("InterfaceStatusList")
    var interfaceStatusList: List<NetStatusBean?>? = null

    override fun toString(): String {
        return "AcsWorkStatusBean(doorStatus=$doorStatus, doorLockStatus=$doorLockStatus, magneticStatus=$magneticStatus, caseStatus=$caseStatus, multiDoorInterlockStatus=$multiDoorInterlockStatus, antiSneakStatus=$antiSneakStatus, hostAntiDismantleStatus=$hostAntiDismantleStatus, cardReaderOnlineStatus=$cardReaderOnlineStatus, cardReaderAntiDismantleStatus=$cardReaderAntiDismantleStatus, cardReaderVerifyMode=$cardReaderVerifyMode, setupAlarmStatus=$setupAlarmStatus, alarmInStatus=$alarmInStatus, alarmOutStatus=$alarmOutStatus, cardNum=$cardNum, antiSneakServerStatus=$antiSneakServerStatus, netStatus=$netStatus, sipStatus=$sipStatus, signalStatus=$signalStatus, ezvizStatus=$ezvizStatus, voipStatus=$voipStatus, dhcpStatus=$dhcpStatus, tempStatus=$tempStatus, faceDownStatus=$faceDownStatus, interfaceStatusList=$interfaceStatusList)"
    }


}

/**
 * 多网口网络状态
 */
data class NetStatusBean(
    var id: Int? = null,
    var netStatus: String? = null
)