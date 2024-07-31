package heop.v2.apis

import com.hik.heop.v2.apis.IHttpAccessApis
import heop.v2.apis.isapi.accesscontrol.IAccessControlApis
import heop.v2.apis.isapi.heop.system.IHeopSystemApis
import heop.v2.apis.isapi.heop.accesscontrol.IHeopAccessControlApis
import heop.v2.apis.isapi.heop.videointercom.IHeopVideoIntercomApis
import heop.v2.apis.isapi.intelligent.IIntelligentApis
import heop.v2.apis.isapi.sdt.ISDTApis
import heop.v2.apis.isapi.system.ISystemApis
import heop.v2.apis.isapi.videointercom.IVideoIntercomApis
import heop.v2.apis.sdk.ISDKApis
import com.hik.vis.heop.v2.api.HeopApi

/**
 * Heop请求协议接口类
 */
object HeopApis :
    ISDKApis by HeopApi.create(ISDKApis::class.java),
    IHeopSystemApis by HeopApi.create(IHeopSystemApis::class.java),
    IVideoIntercomApis by HeopApi.create(IVideoIntercomApis::class.java),
    IIntelligentApis by HeopApi.create(IIntelligentApis::class.java),
    IAccessControlApis by HeopApi.create(IAccessControlApis::class.java),
    ISystemApis by HeopApi.create(ISystemApis::class.java),
    IHeopVideoIntercomApis by HeopApi.create(IHeopVideoIntercomApis::class.java),
    IHeopAccessControlApis by HeopApi.create(IHeopAccessControlApis::class.java),
    IHttpAccessApis by HeopApi.create(IHttpAccessApis::class.java),
    ISDTApis by HeopApi.create(ISDTApis::class.java)
