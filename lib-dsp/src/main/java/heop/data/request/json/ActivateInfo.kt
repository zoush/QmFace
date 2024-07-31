package heop.data.request.json

import com.google.gson.annotations.SerializedName

data class ActivateInfo(
    var password: String? = null,
    //mac地址，远程激活 SADP 搜索设备时填写，本地激活不填写
    var macAddress: String? = null,
    var activeType: String? = null
)

/**
 * 激活设备
 */
data class ActivateInfoBean(
    @SerializedName("ActivateInfo")
    var activateInfo: ActivateInfo? = null
)