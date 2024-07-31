package com.hik.heop.v2.apis

import com.hik.vis.heop.v2.annotation.*
import com.hik.vis.heop.v2.api.HeopResult

/**
 * @author huangjinhui6
 * @describe:
 * @date on 2022/8/9 19:16
 **/
interface IHttpAccessApis {

    /**
     * 获取门禁总能力
     */
    @Heop(
        method = HeopMethod.GET,
        respBodyType = BodyType.BINARY,
        path = "{uri}"
    )
    suspend fun getHttpAccess(@Path("uri") uri: String): HeopResult<ByteArray>



    /**
     * Put方法
     */
    @Heop(
        method = HeopMethod.PUT,
        reqBodyType = BodyType.BINARY,
        respBodyType = BodyType.BINARY,
        path = "{uri}"
    )
    suspend fun putHttpAccess(@Path("uri") uri: String, @Body bean: ByteArray): HeopResult<ByteArray>



    /**
     * Put方法
     */
    @Heop(
        method = HeopMethod.POST,
        reqBodyType = BodyType.BINARY,
        respBodyType = BodyType.BINARY,
        path = "{uri}"
    )
    suspend fun postHttpAccess(@Path("uri") uri: String, @Body bean: ByteArray): HeopResult<ByteArray>


    /**
     * Put方法
     */
    @Heop(
        method = HeopMethod.DELETE,
        reqBodyType = BodyType.BINARY,
        respBodyType = BodyType.BINARY,
        path = "{uri}"
    )
    suspend fun deleteHttpAccess(@Path("uri") uri: String, @Body bean: ByteArray): HeopResult<ByteArray>

}