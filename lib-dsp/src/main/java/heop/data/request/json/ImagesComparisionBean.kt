package heop.data.request.json

import com.google.gson.annotations.SerializedName


data class ImagesComparisionBean(
    var dataType: String? = null,
    @SerializedName("TargetImage")
    var targetImage: TargetImage? = null,
    @SerializedName("ContrastImage")
    var contrastImage: ContrastImage? = null
)
