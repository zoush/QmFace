package lib_http.model

/**
 * @author huangjinhui6
 * @describe: 摘要认证Message工具类
 * @date on 2022/8/1 11:09
 */
class DigestAuthInfo {
    var username: String? = null
    var realm: String? = null
    var nonce: String? = null
    var uri: String? = null
    var response: String? = null
    var qop: String? = null
    var nc: String? = null
    var cnonce: String? = null
    override fun toString(): String {
        return ("DigestAuthInfo [username=" + username + ", realm=" + realm + ", nonce=" + nonce + ", uri=" + uri
                + ", response=" + response + ", qop=" + qop + ", nc=" + nc + ", cnonce=" + cnonce + "]")
    }
}