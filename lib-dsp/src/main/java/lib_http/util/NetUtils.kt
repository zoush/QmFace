package lib_http.util


import java.net.InetAddress
import java.net.NetworkInterface
import java.net.SocketException
import java.util.*
import java.util.regex.Pattern
/**
 * @author huangjinhui6
 * @describe: 网络工具类
 * @date on 2022/7/14 19:32
 */
object NetUtils {
    /**
     * Ipv4 address check.
     */
    private val IPV4_PATTERN = Pattern.compile(
        "^(" + "([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}" +
                "([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$"
    )

    /**
     * Check if valid IPV4 address.
     *
     * @param input the address string to check for validity.
     *
     * @return True if the input parameter is a valid IPv4 address.
     */
    fun isIPv4Address(input: String?): Boolean {

        return IPV4_PATTERN.matcher(input as CharSequence).matches()
    }

    /**
     * Get local Ip address.
     */
    val localIPAddress: InetAddress?
        get() {
            var enumeration: Enumeration<NetworkInterface>? = null
            try {
                enumeration = NetworkInterface.getNetworkInterfaces()
            } catch (e: SocketException) {
                e.printStackTrace()
            }
            if (enumeration != null) {
                while (enumeration.hasMoreElements()) {
                    val nif = enumeration.nextElement()
                    val inetAddresses = nif.inetAddresses
                    if (inetAddresses != null) {
                        while (inetAddresses.hasMoreElements()) {
                            val inetAddress = inetAddresses.nextElement()
                            if (!inetAddress.isLoopbackAddress && isIPv4Address(inetAddress.hostAddress)) {
                                return inetAddress
                            }
                        }
                    }
                }
            }
            return null
        }

    /**
     * Get local Ip address.
     */
    val localIPAddressList: HashMap<String?, InetAddress?>?
        get() {
            var enumeration: Enumeration<NetworkInterface>? = null
            try {
                enumeration = NetworkInterface.getNetworkInterfaces()
            } catch (e: SocketException) {
                e.printStackTrace()
            }
            if (enumeration != null) {
                val returnInetAddresses = HashMap<String?, InetAddress?>()
                while (enumeration.hasMoreElements()) {
                    val nif = enumeration.nextElement()
                    if (nif.name.matches(Regex("eth."))) {
                        val inetAddresses = nif.inetAddresses
                        if (inetAddresses != null) {
                            while (inetAddresses.hasMoreElements()) {
                                val inetAddress = inetAddresses.nextElement()
                                if (!inetAddress.isLoopbackAddress && isIPv4Address(inetAddress.hostAddress)) {
                                    returnInetAddresses[nif.name] = inetAddress
                                }
                            }
                        }
                    }
                }
                return returnInetAddresses
            }
            return null
        }
}