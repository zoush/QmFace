package heop.data.request.xml

import com.thoughtworks.xstream.annotations.XStreamAlias

/**
 * 设备信息
 */
@XStreamAlias("DeviceInfo")
data class DeviceInfo(
    /**
     * 设备名称
     */
    var deviceName: String? = null,
    /**
     * 设备ID
     */
    var deviceID: String? = null,
    /**
     * 设备描述
     */
    var deviceDescription: String? = null,
    /**
     * 设备地区
     */
    var deviceLocation: String? = null,
    /**
     * 系统联系人
     */
    var systemContact: String? = null,
    /**
     * 设备型号
     */
    var model: String? = null,
    /**
     * 设备序列号
     */
    var serialNumber: String? = null,
    /**
     * 设备MAC地址
     */
    var macAddress: String? = null,
    /**
     * 设备固件版本号
     */
    var firmwareVersion: String? = null,
    /**
     * 设备固件版本编译日期
     */
    var firmwareReleasedDate: String? = null,
    /**
     * 开机BOOT版本号
     */
    var bootVersion: String? = null,
    /**
     * 开机BOOT版本编译日期
     */
    var bootReleasedDate: String? = null,
    /**
     * 设备硬件版本号
     */
    var hardwareVersion: String? = null,
    /**
     * 软件版本
     */
    var softwareVersion: String? = null,
    /**
     * 设备编码版本号
     */
    var encoderVersion: String? = null,
    /**
     * 设备编码版本编译日期
     */
    var encoderReleasedDate: String? = null,
    /**
     * 设备解码版本号
     */
    var decoderVersion: String? = null,
    /**
     * 设备解码版本编译日期
     */
    var decoderReleasedDate: String? = null,
    /**
     * 设备类型
     */
    var deviceType: String? = null,
    /**
     * 字符编码格式
     */
    var charEncodeFormat: String? = null,
    /**
     * 语言类型
     */
    var languageType: String? = null,
    /**
     * 厂商OME代码
     */
    var OEMCode: String? = null,
    /**
     * 设备屏幕个数
     */
    @XStreamAlias("dispalyNum")
    var displayNum: Int? = null,
    /**
     * BSP版本
     */
    var bspVersion: String? = null,
    /**
     * DSP版本
     */
    var dspVersion: String? = null,
    /**
     * 触摸屏版本
     */
    var touchScreenVersionInfo: String? = null,
    /**
     * 测温仪版本
     */
    var temperatureModuleVersionInfo: String? = null,
    /**
     * 定制信息
     */
    var customizedInfo: String? = null,
)