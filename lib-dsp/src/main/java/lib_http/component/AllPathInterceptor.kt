package lib_http.component

import lib_http.model.Nonce
import lib_http.model.RequireAuthTools
import com.yanzhenjie.andserver.annotation.Interceptor
import com.yanzhenjie.andserver.error.HttpException
import com.yanzhenjie.andserver.framework.HandlerInterceptor
import com.yanzhenjie.andserver.framework.handler.RequestHandler
import com.yanzhenjie.andserver.http.HttpRequest
import com.yanzhenjie.andserver.http.HttpResponse

/**
 * @author huangjinhui6
 * @describe:
 * @date on 2022/8/1 15:34
 **/

@Interceptor
class AllPathInterceptor : HandlerInterceptor {
    // 为了 测试Digest nc 值每次请求增加
    private  var nonce : Nonce = Nonce()
    @Throws(Exception::class)
    override fun onIntercept(
        request: HttpRequest,
        response: HttpResponse,
        handler: RequestHandler
    ): Boolean {
        // 摘要认证
        if (!RequireAuthTools.isAuth(request, response, nonce)) {
            // 验证不通过，拦截
            throw HttpException(response.status, "null")
        }
        return false
    }
}



