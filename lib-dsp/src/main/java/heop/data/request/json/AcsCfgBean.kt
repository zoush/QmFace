package heop.data.request.json

import com.google.gson.annotations.SerializedName

data class AcsCfgBean(
    @SerializedName("AcsCfg")
    val acsCfg: AcsCfg,
)

data class AcsCfg(
    /**
     * 测温开关使能
     */
    var thermalEnabled: Boolean? = null,
    /**
     * 是否开启仅测温模式
     */
    var thermalMode: Boolean? = null,
    /**
     * 仅测温模式可见光图片上传使能
     */
    var thermalPictureEnabled: Boolean? = null,
    /**
     * 测温单位配置
     * celsius - 摄氏度
     * fahrenheit - 华氏度
     */
    var thermalUnit: String? = null,
    /**
     * 温度阈值上限
     * 精度：小数点后一位
     */
    var highestThermalThreshold: Float? = null,
    /**
     * 温度阈值下限
     * 精度：小数点后一位
     */
    var lowestThermalThreshold: Float? = null,
    /**
     * 华氏度温度阈值上限
     * 精度：小数点后一位
     */
    var highestThermalThresholdF: Float? = null,
    /**
     * 华氏度温度阈值下限
     * 精度：小数点后一位
     */
    var lowestThermalThresholdF: Float? = null,
    /**
     * 温度补偿
     * 单位根据 thermalUnit 字段确定，若 thermalUnit 字段不存在认为单位为摄氏度
     */
    var thermalCompensation: Float? = null,
    /**
     * 温度阈值开门使能
     * 超过温度阈值上限（highestThermalThreshold）或低于温度阈值下限（lowestThermalThreshold）后是否开门
     */
    var thermalDoorEnabled: Boolean? = null,
)