package heop.data.request.json

import com.google.gson.annotations.SerializedName

data class FDLib(
    /**
     * 人脸库ID
     */
    @SerializedName("FDID")
    val fdId: String? = null,
    /**
     * 人脸库类型
     * blackFD - 名单库
     * infraredFD - 静态库
     */
    val faceLibType: String? = null,
    /**
     * 人脸库的名称
     */
    val name: String? = null,
    /**
     * 自定义信息
     */
    val customInfo: String? = null,
)

data class FDLibBean(
    /**
     * 请求 URL
     */
    val requestURL: String? = null,
    /**
     * 状态码
     */
    val statusCode: Int? = null,
    /**
     * 状态描述
     */
    val statusString: String? = null,
    /**
     * 子状态码
     */
    val subStatusCode: String? = null,
    /**
     * 错误码,与 subStatusCode 对应
     * 当 statusCode 不为 1 时，必填
     */
    val errorCode: Int? = null,
    /**
     * 错误详细信息, 能具体到某一个参数的错误
     * 当 statusCode 不为 1 时，必填
     */
    val errorMsg: String? = null,
    /**
     * 人脸库信息
     */
    @SerializedName("FDLib")
    val fdLib: List<FDLib>? = null,
)
