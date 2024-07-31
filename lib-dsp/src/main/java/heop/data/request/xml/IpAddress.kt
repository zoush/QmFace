package heop.data.request.xml

import com.thoughtworks.xstream.annotations.XStreamAlias

/**
 * 网络配置信息
 */
@XStreamAlias("IPAddress")
data class IpAddress(
    /**
     * IP 地址版本
     * v4 - IPv4
     * v6 - IPv6
     * dual - IPv4 和 IPv6 都支持
     */
    var ipVersion: String = "v4",
    /**
     * 地址类型
     * static - 静态
     * dynamic - 动态
     */
    var addressingType: String? = null,
    /**
     * IPv4 地址
     */
    var ipAddress: String? = null,
    /**
     * IPv4 子网掩码
     */
    var subnetMask: String? = null,
    /**
     * 默认网关
     */
    @XStreamAlias("DefaultGateway")
    var defaultGateway: IpAddressBean? = null,
    /**
     * 首选 DNS
     */
    @XStreamAlias("PrimaryDNS")
    var primaryDns: IpAddressBean? = null,
    /**
     * 备用 DNS
     */
    @XStreamAlias("SecondaryDNS")
    var secondaryDns: IpAddressBean? = null,
    /**
     * DNS 自动分配
     */
    @XStreamAlias("DNSEnable")
    var dnsEnable: Boolean? = false
)

data class IpAddressBean(
    /**
     * IPv4 地址
     */
    var ipAddress: String? = null
)
