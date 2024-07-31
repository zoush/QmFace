package heop.data.response


data class HeopFaceResponse(
    var statusCode: Int? = null,
    var statusString: String? = null,
    var subStatusCode: String? = null,
    var targets: List<Target>? = null
)

data class Target(
    var id: Int? = null,
    var targetModelData: String? = null
)