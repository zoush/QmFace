package heop.data.request.json

import com.google.gson.annotations.SerializedName

data class TTSBean(
    @SerializedName("TTS")
    var tts: List<TTS>? = null
)

data class TTS(
    var text: String? = null, // 语音播报文本信息
    var language: String? = null // 语言类型
)