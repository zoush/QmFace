package lib_http.model

/**
 * @author huangjinhui6
 * @describe:  摘要认证Nonce工具类
 * @date on 2022/8/4 9:43
 */
class Nonce {
    // 每个nonce的可用访问次数
    var nonce_left_time = 100
    // 随机数
    var nonce: String? = null
    constructor()
    constructor(nonce_left_time: Int, nonce: String?) {
        this.nonce_left_time = nonce_left_time
        this.nonce = nonce
    }
}