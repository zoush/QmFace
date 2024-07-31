package lib_http.controller.apis.isapi

import android.util.Log
import heop.v2.apis.HeopApis
import lib_http.model.AnsData
import com.hik.vis.heop.v2.api.HeopResult
import com.yanzhenjie.andserver.annotation.*
import com.yanzhenjie.andserver.http.HttpRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.CountDownLatch


/**
 * @author huangjinhui6
 * @describe:
 * @date on 2022/8/9 19:45
 **/


@RestController
class ISAPIController {


    //获取HEOP系统能力
    @RequestMapping(
        value = [
            "/ISAPI/{param1}",
            "/ISAPI/{param1}/{param2}",
            "/ISAPI/{param1}/{param2}/{param3}",
            "/ISAPI/{param1}/{param2}/{param3}/{param4}",
            "/ISAPI/{param1}/{param2}/{param3}/{param4}/{param5}",
            "/ISAPI/{param1}/{param2}/{param3}/{param4}/{param5}/{param6}",
            "/ISAPI/{param1}/{param2}/{param3}/{param4}/{param5}/{param6}/{param7}",
            "/ISAPI/{param1}/{param2}/{param3}/{param4}/{param5}/{param6}/{param7}/{param8}",
            "/ISAPI/{param1}/{param2}/{param3}/{param4}/{param5}/{param6}/{param7}/{param8}/{param9}",
            "/ISAPI/{param1}/{param2}/{param3}/{param4}/{param5}/{param6}/{param7}/{param8}/{param9}/{param10}",
        ],
        method = [RequestMethod.PUT, RequestMethod.POST, RequestMethod.DELETE]
    )
    fun httpOtherISAPIMethod(
        @PathVariable(value = "param1",required = false) param1 : String,
        @PathVariable(value = "param2",required = false) param2 : String,
        @PathVariable(value = "param3",required = false) param3 : String,
        @PathVariable(value = "param4",required = false) param4 : String,
        @PathVariable(value = "param5",required = false) param5 : String,
        @PathVariable(value = "param6",required = false) param6 : String,
        @PathVariable(value = "param7",required = false) param7 : String,
        @PathVariable(value = "param8",required = false) param8 : String,
        @PathVariable(value = "param9",required = false) param9 : String,
        @PathVariable(value = "param10",required = false) param10 : String,
        @RequestParam(value = "format",required = false) format : String,
        @RequestBody  body : String,
        request : HttpRequest
    ): Any {
        var ans: Any? = null
        var isSuccess = false
        var countDownLatch = CountDownLatch(1)
        val method : String = request.method.toString()
        val uri = request.uri
        uri.replaceFirst("scheme:","")
        val bodyByteArray = body.toByteArray()
        val coroutineScope = CoroutineScope(Dispatchers.Main)
        coroutineScope.launch {
            when (
                val result =    when(method){
                    "PUT" -> HeopApis.putHttpAccess(uri,bodyByteArray)
                    "POST" -> HeopApis.postHttpAccess(uri,bodyByteArray)
                    else -> HeopApis.deleteHttpAccess(uri,bodyByteArray)
                }
            ) {
                is HeopResult.Success -> {
                    ans = result
                    isSuccess = true
                    Log.d("suc", "ISAPIHtto success: " + String(result.data, charset("UTF-8")))
                    countDownLatch.countDown()
                }
                is HeopResult.Error -> {
                    ans = result
                    isSuccess = false
                    Log.d("err", "putFactoryReset failed: " + result.data)
                    countDownLatch.countDown()
                }
            }
        }
        countDownLatch.await()
        return AnsData(isSuccess, ans)
    }

    //获取HEOP系统能力
    @RequestMapping(
        value = [
            "/ISAPI/{param1}",
            "/ISAPI/{param1}/{param2}",
            "/ISAPI/{param1}/{param2}/{param3}",
            "/ISAPI/{param1}/{param2}/{param3}/{param4}",
            "/ISAPI/{param1}/{param2}/{param3}/{param4}/{param5}",
            "/ISAPI/{param1}/{param2}/{param3}/{param4}/{param5}/{param6}",
            "/ISAPI/{param1}/{param2}/{param3}/{param4}/{param5}/{param6}/{param7}",
            "/ISAPI/{param1}/{param2}/{param3}/{param4}/{param5}/{param6}/{param7}/{param8}",
            "/ISAPI/{param1}/{param2}/{param3}/{param4}/{param5}/{param6}/{param7}/{param8}/{param9}",
            "/ISAPI/{param1}/{param2}/{param3}/{param4}/{param5}/{param6}/{param7}/{param8}/{param9}/{param10}",
        ],
        method = [RequestMethod.GET]
    )
    fun httpGetISAPIMethod(
        @PathVariable(value = "param1",required = false) param1 : String,
        @PathVariable(value = "param2",required = false) param2 : String,
        @PathVariable(value = "param3",required = false) param3 : String,
        @PathVariable(value = "param4",required = false) param4 : String,
        @PathVariable(value = "param5",required = false) param5 : String,
        @PathVariable(value = "param6",required = false) param6 : String,
        @PathVariable(value = "param7",required = false) param7 : String,
        @PathVariable(value = "param8",required = false) param8 : String,
        @PathVariable(value = "param9",required = false) param9 : String,
        @PathVariable(value = "param10",required = false) param10 : String,
        @RequestParam(value = "format",required = false) format : String,
        request : HttpRequest
    ): Any {
        var ans: Any? = null
        var isSuccess = false
        var countDownLatch = CountDownLatch(1)
        val uri = request.uri
        uri.replaceFirst("scheme:","")
        val coroutineScope = CoroutineScope(Dispatchers.Main)
        coroutineScope.launch {
            when (
                val result = HeopApis.getHttpAccess(uri)
            ) {
                is HeopResult.Success -> {
                    ans = result
                    isSuccess = true
                    Log.d("suc", "putFactoryReset success: " + String(result.data, charset("UTF-8"))   )
                    countDownLatch.countDown()
                }
                is HeopResult.Error -> {
                    ans = result
                    isSuccess = false
                    Log.d("err", "putFactoryReset failed: " + result.data)
                    countDownLatch.countDown()
                }
            }
        }
        countDownLatch.await()
        return AnsData(isSuccess, ans)
    }

}






