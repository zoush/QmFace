package lib_http.model

import com.yanzhenjie.andserver.http.HttpRequest
import com.yanzhenjie.andserver.http.HttpResponse
import com.yanzhenjie.andserver.util.DigestUtils
import java.util.*

/**
 * @author huangjinhui6
 * @describe:  摘要认证工具类
 * @date on 2022/8/4 9:43
 */
class RequireAuthTools {
    companion object {

        // 每个nonce的可用次数
        val NONCE_AVAIBLE_TIMES = 100

        /**
         *
         * @author huangjinhui6
         * @describe: 摘要认证
         * @date 2022/8/11 15:15
         */
        fun isAuth(req: HttpRequest, res: HttpResponse, nonce: Nonce?): Boolean {
            val authStr = req.getHeader("Authorization")
            if (authStr == null || authStr.length <= 7) {
                // 没有 Authorization 请求头，开启质询
                return challenge(res, nonce, NONCE_AVAIBLE_TIMES, false)
            }
            if (nonce == null || nonce.nonce_left_time <= 0) {
                return challenge(res, nonce, NONCE_AVAIBLE_TIMES, true)
            }
            val authObject = getAuthInfoObject(authStr)
            /*
             * 生成 response 的算法：
             *  response = MD5(MD5(username:realm:password):nonce:nc:cnonce:qop:MD5(<request-method>:url))
             */
            val HA1 = DigestUtils.md5DigestAsHex("admin" + ":" + authObject!!.realm + ":hik12345")
            val HD = (nonce.nonce + ":" + authObject.nc + ":" + authObject.cnonce + ":"
                    + authObject.qop)
            val HA2 = DigestUtils.md5DigestAsHex(req.method.toString() + ":" + authObject.uri)
            val responseValid = DigestUtils.md5DigestAsHex("$HA1:$HD:$HA2")
            // 如果 Authorization 中的 response（浏览器生成的） 与期望的 response（服务器计算的） 相同，则验证通过
            if (responseValid == authObject.response) {
                nonce.nonce_left_time = nonce.nonce_left_time - 1
                return true
            }
            // 验证不通过，重复质询
            return challenge(res, nonce, NONCE_AVAIBLE_TIMES, false)
        }

        /**
         * 质询：返回状态码 401 和 WWW-Authenticate 响应头
         *
         * @param res 返回false，则表示拦截器拦截请求
         */
        fun challenge(
            res: HttpResponse,
            nonce: Nonce?,
            nonceAvaiableTimes: Int?,
            stale: Boolean
        ): Boolean {
            // 质询前，重置或删除保存的与该用户关联的 nc 值（nc：nonce计数器，是一个16进制的数值，表示同一nonce下客户端发送出请求的数量）
            // 将 nc 置为初始值 0， 这里代码省略
            // 测试代码 start
            nonce!!.nonce = UUID.randomUUID().toString()
            nonce.nonce_left_time = nonceAvaiableTimes!!
            // 测试代码 end
            res.status = 401
            val authHeader = ("Digest realm=\"DIGEST tiemao\""
                    + ",nonce=\"" + nonce.nonce + "\""
                    + ",algorithm=MD5"
                    + ",qop=\"" + "auth" + "\""
                    + ",stale=\"" + stale + "\"") //noce替换标识
            res.addHeader("WWW-Authenticate", authHeader)
            return false
        }

        /**
         * 该方法用于将 Authorization 请求头的内容封装成一个 DigestAuthInfo  对象。
         *
         */
        fun getAuthInfoObject(authStrGet: String?): DigestAuthInfo? {
            var authStr = authStrGet
            if (authStr == null || authStr.length <= 7) return null
            if (authStr.lowercase(Locale.getDefault()).indexOf("digest") >= 0) {
                // 截掉前缀 Digest
                authStr = authStr.substring(6)
            }
            // 将双引号去掉
            authStr = authStr.replace("\"".toRegex(), "")
            val digestAuthObject = DigestAuthInfo()
            var authArray: Array<String?>
            authArray = authStr.split(",").toTypedArray()
            // System.out.println(java.util.Arrays.toString(authArray));
            var i = 0
            val len = authArray.size
            while (i < len) {
                val auth = authArray[i]
                val key = auth!!.substring(0, auth.indexOf("=")).trim { it <= ' ' }
                val value = auth.substring(auth.indexOf("=") + 1).trim { it <= ' ' }
                when (key) {
                    "username" -> digestAuthObject.username = value
                    "realm" -> digestAuthObject.realm = value
                    "nonce" -> digestAuthObject.nonce = value
                    "uri" -> digestAuthObject.uri = value
                    "response" -> digestAuthObject.response = value
                    "qop" -> digestAuthObject.qop = value
                    "nc" -> digestAuthObject.nc = value
                    "cnonce" -> digestAuthObject.cnonce = value
                }
                i++
            }
            return digestAuthObject
        }
    }


}