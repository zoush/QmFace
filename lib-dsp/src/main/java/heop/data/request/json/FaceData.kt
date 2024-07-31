package heop.data.request.json

import com.google.gson.annotations.SerializedName
import heop.data.AttributeMinMaxValueString
import com.thoughtworks.xstream.annotations.XStreamAlias
import heop.data.constant.FaceDataType
import heop.data.constant.FaceLibType

/**
 * 人脸采集条件
 */
@XStreamAlias("CaptureFaceDataCond")
data class CaptureFaceDataCond(
    var captureInfrared: Boolean? = null,   // true-同时采集红外图片 false-不采集红外图片
    @FaceDataType
    var dataType: String? = null            // "url,binary",默认url,采集图片数据类型
)

/**
 * 人脸采集结果
 */
@XStreamAlias("CaptureFaceData")
data class CaptureFaceData(
    var captureFaceDataCond: CaptureFaceDataCond? = null,           // 人脸采集条件
    var faceDataUrl: AttributeMinMaxValueString? = null,            // 人脸数据URL
    var captureProgress: AttributeMinMaxValueString? = null,           // 采集进度
    var infraredFaceDataUrl: AttributeMinMaxValueString? = null,    // 红外图片URL
    var modelData: AttributeMinMaxValueString? = null,              // 人脸建模数据（base64编码）
    var score: AttributeMinMaxValueString? = null                      // 人脸评分
)

/**
 * 设置人脸数据
 * /ISAPI/Intelligent/FDLib/FDSetUp?format=json
 */
data class FaceData(
    @FaceLibType
    var faceLibType: String? = null, /*必填, 人脸库类型: blackFD-名单库, staticFD-静态库, string类型, 最大长度为32*/
    @SerializedName("FDID")
    var fdId: String? = null, /*必填, 人脸库ID, string类型, 最大长度为63字节; */
    @SerializedName("FPID")
    var fpId: String? = null,/*可选, 人脸记录ID, string类型, 如果外部传入,最长63字节, 字母数字组合, 需要保证唯一性; 如果外部不传则由设备自动生成（与非视频工号（人员ID）字段一致）*/
    var faceURL: String? = null,  /*可选, 人脸图片URL方式上传时输入的图片存储URL, string类型, 最大长度为256字节*/
    var modelData: String? = null, /*opt, string, 目标模型数据, range:[,], desc:可选，目标模型数据，字符串类型，传输过程中针对二进制非建模数据进行base64的加密处理*/
    //是否删除该人脸, desc:可选，boolean，是否删除该人脸，true-是（只有删除该人脸时，才填写该字段；新增或修改人脸时，不填写该字段）
    var deleteFP: Boolean? = null,
    var saveFacePic: Boolean? = null,
)

/**
 * 查找人脸库中的人脸数据
 * /ISAPI/Intelligent/FDLib/FDSearch?format=json
 */
data class FDSearchCond(
    var searchResultPosition: Int? = null,
    var maxResults: Int? = null,
    var faceLibType: String? = null,
    @SerializedName("FDID")
    var fdId: String? = null,
    @SerializedName("FPID")
    var fpId: String? = null,
    @SerializedName("startTime ")
    var startTime: String? = null,
    var endTime: String? = null,
    var name: String? = null,
    var gender: String? = null,
    var city: String? = null,
    var certificateType: String? = null,
    var certificateNumber: String? = null,
    var isInLibrary: String? = null,
    var isDisplayCaptureNum: Boolean? = null,
    @SerializedName("rowKey ")
    var rowKey: String? = null,
    var transfer: Boolean? = null
)

/**
 * 查找人脸库中的人脸数据
 * /ISAPI/Intelligent/FDLib/FDSearch?format=json
 */
data class FDSearchResult(
    var requestURL: String? = null,
    var statusCode: Int? = null,
    var statusString: String? = null,
    var subStatusCode: String? = null,
    var errorCode: Int? = null,
    var errorMsg: String? = null,
    var responseStatusStrg: String? = null,
    var numOfMatches: Int? = null,
    var totalMatches: Int? = null,
    @SerializedName("MatchList")
    var matchList: List<Match?>? = null
) {
    data class Match(
        @SerializedName("FDID")
        var fDID: String? = null,
        @SerializedName("FDName")
        var fDName: String? = null,
        @SerializedName("FPID")
        var fPID: String? = null,
        var faceURL: String? = null,
        var name: String? = null,
        var gender: String? = null,
        @SerializedName("bornTime")
        var bornTime: String? = null,
        var city: String? = null,
        var certificateType: String? = null,
        @SerializedName("certificateNumber")
        var certificateNumber: String? = null,
        var caseInfo: String? = null,
        var tag: String? = null,
        var address: String? = null,
        var customInfo: String? = null,
        var isInLibrary: String? = null,
        var captureNum: Int? = null,
        var rowKey: String? = null,
        var modelData: String? = null
    )
}