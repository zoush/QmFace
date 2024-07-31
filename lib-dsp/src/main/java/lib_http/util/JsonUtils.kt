package lib_http.util

import com.google.gson.Gson
import com.google.gson.JsonParser
import lib_http.model.AnsData
import lib_http.model.ReturnData
import com.hik.vis.heop.data.request.xml.ResponseStatus
import com.hik.vis.heop.util.GsonUtil
import com.hik.vis.heop.util.XStreamUtil
import com.hik.vis.heop.v2.api.HeopResult
import java.lang.reflect.Type

/**
 * @author huangjinhui6
 * @describe: JSON 解析类
 * @date on 2022/7/14 19:32
 */
object JsonUtils {
    /**
     * Business is successful.
     *
     * @param data return data.
     *
     * @return json.
     */

    private val gson = Gson()

    fun successfulJson(data: Any?): String {
        val ans = data as AnsData?
        val returnData = ReturnData()
        if (ans != null) {
            if (ans.success) {
                val resultdata: HeopResult.Success<*> = ans.data as HeopResult.Success<*>
                returnData.success = ans.success
                returnData.msg = "request success"
                // 处理返回值
                val resultdataStr = String(resultdata.data as ByteArray, charset("UTF-8"))
                val bean : ResponseStatus?
                if (resultdataStr.trim { it <= ' ' }.startsWith("<")) {
                    returnData.data = resultdataStr
                    bean = XStreamUtil.toBeanOrNull(resultdataStr, ResponseStatus::class.java)
                } else {
                    returnData.data  = JsonParser.parseString(resultdataStr)
                    bean = GsonUtil.toBeanOrNull(resultdataStr, ResponseStatus::class.java)
                }
                if (bean != null && bean.statusCode !=0 && bean.statusCode !=1 ) {
                    returnData.success = false
                    returnData.msg = bean.errorMsg
                }

//                returnData.data = ans.data

                returnData.code = 200

            } else {
                if (ans.data ==null){
                    returnData.success = ans.success
                    returnData.data = "null"
                    returnData.code = 200
                    returnData.msg = ans.othermessage
                }
                else{
                    val errordata = ans.data as HeopResult.Error
                    returnData.success = ans.success
                    returnData.data = errordata.data
                    returnData.code = 200
                    returnData.msg = errordata.data.errorMsg
                }


            }
        }

        return gson.toJson(returnData)
    }

    /**
     * Business is failed.
     *
     * @param code error code.
     * @param message message.
     *
     * @return json.
     */
    fun failedJson(code: Int, message: String?): String {
        val returnData = ReturnData()
        returnData.success = false
        returnData.code = code
        returnData.msg = message
        return gson.toJson(returnData)
    }

    /**
     * Converter object to json string.
     *
     * @param data the object.
     *
     * @return json string.
     */
    fun toJsonString(data: Any?): String {
        return  gson.toJson(data)
    }

    /**
     * Parse json to object.
     *
     * @param json json string.
     * @param type the type of object.
     * @param <T> type.
     *
     * @return object.
    </T> */
    fun <T> parseJson(json: String?, type: Type?): T {
        return  gson.fromJson(json,type)
    }
}