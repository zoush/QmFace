package com.hik.heop.data.request.xml

import com.thoughtworks.xstream.annotations.XStreamAlias
import heop.data.constant.UnitType

/**
 * 设备编号
 */
@XStreamAlias("DeviceId")
data class DeviceId (
    /**
     * 设备类型
     */
    @UnitType var unitType: String? = null,
    /**
     * 期号
     */
    var periodNumber: Int = 0,
    /**
     * 楼号
     */
    var buildingNumber: Int = 0,
    /**
     * 单元号
     */
    var unitNumber: Int = 0,
    /**
     * 层号
     */
    var floorNumber: Int = 0,
    /**
     * 房间号
     */
    var roomNumber: Int = 0,
    /**
     * 设备序号
     */
    var deviceIndex: Int = 0,
    /**
     * 小区编号
     */
    var communityNumber: String? = null,
    /**
     * 自定义编号
     */
    var customDeviceId: String? = null,
    /**
     * 自定义层级名称
     */
    var periodNumberName: String? = null,
    /**
     * 自定义层级名称
     */
    var buildingNumberName: String? = null,
    /**
     * 自定义层级名称
     */
    var unitNumberName: String? = null,
    /**
     * 自定义层级名称
     */
    var floorNumberName: String? = null,
    /**
     * 自定义层级名称
     */
    var roomNumberName: String? = null,
    /**
     * 自定义层级名称
     */
    var deviceIndexName: String? = null,
    /**
     * 自定义层级名称
     */
    var communityNumberName: String? = null,
)