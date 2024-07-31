package heop.data.request.json

import com.google.gson.annotations.SerializedName

data class OptList<T>(
    @SerializedName("@opt")
    var opt: List<T>? = null
)

data class OptString(
    @SerializedName("@opt")
    var opt: String? = null
)

data class MinMaxInt(
    @SerializedName("@min")
    var min: Int? = null,
    @SerializedName("@max")
    var max: Int? = null
)

data class MinMaxLong(
    @SerializedName("@min")
    var min: Long? = null,
    @SerializedName("@max")
    var max: Long? = null
)