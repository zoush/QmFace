package lib_http.component


import lib_http.util.JsonUtils
import com.yanzhenjie.andserver.annotation.Converter
import com.yanzhenjie.andserver.framework.MessageConverter
import com.yanzhenjie.andserver.framework.body.JsonBody
import com.yanzhenjie.andserver.http.ResponseBody
import com.yanzhenjie.andserver.util.IOUtils
import com.yanzhenjie.andserver.util.MediaType
import java.io.IOException
import java.io.InputStream
import java.lang.reflect.Type

/**
 * response 类型转换
 */
@Converter
class AppMessageConverter : MessageConverter {
    override fun convert(output: Any?, mediaType: MediaType?): ResponseBody {
        return JsonBody(JsonUtils.successfulJson(output))
    }

    @Throws(IOException::class)
    override fun <T> convert(stream: InputStream, mediaType: MediaType?, type: Type): T? {
        val charset = mediaType?.charset
            ?: return JsonUtils.parseJson(
                IOUtils.toString(stream),
                type
            )
        return JsonUtils.parseJson(IOUtils.toString(stream, charset), type)
    }
}