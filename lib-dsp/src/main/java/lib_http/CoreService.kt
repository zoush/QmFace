package lib_http

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

import com.yanzhenjie.andserver.Server
import com.yanzhenjie.andserver.AndServer
import com.yanzhenjie.andserver.Server.ServerListener
import lib_http.util.NetUtils.localIPAddressList
import java.lang.Exception
import java.util.concurrent.TimeUnit

/**
 * @author huangjinhui6
 * @describe: HTTP API 核心类
 * @date on 2022/7/14 19:32
 */
class CoreService : Service() {
    private var mServer: Server? = null
    override fun onCreate() {
        //注册AndServer
        mServer = AndServer.webServer(this)
            .port(8080)
            .timeout(10, TimeUnit.SECONDS)
            .listener(object : ServerListener {
                override fun onStarted() {
//                        InetAddress address = NetUtils.getLocalIPAddress();
                    //690设备外部通过eth1网卡访问
                    val address = localIPAddressList?.get("eth0")
                    ServerManager.onServerStart(this@CoreService, address!!.hostAddress)
                    // TODO The server started successfully.
                    //Log显示所有可访问IP
                    for ((key, value) in localIPAddressList.orEmpty()) {
                        Log.e(TAG, "onStarted: 服务器启动 $key  Ip: $value")
                    }
                }

                override fun onStopped() {
                    ServerManager.onServerStop(this@CoreService)
                    // TODO The server has stopped.
                    Log.e(TAG, "onStarted: " + "服务器停止")
                }

                override fun onException(e: Exception) {
                    e.printStackTrace()
                    ServerManager.onServerError(this@CoreService, e.message)
                    // TODO An exception occurred while the server was starting.
                    Log.e(TAG, "onStarted: " + "服务器异常" + e.message)
                }
            })
            .build()
        //注册完以后调用启动Server的方法
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        startServer()
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        stopServer()
        super.onDestroy()
    }

    /**
     * 启动 server.
     */
    fun startServer() {
        if (mServer!!.isRunning) {
            // TODO The server is already up.
        } else {
            mServer!!.startup()
        }
    }

    /**
     * 停止server.
     */
    fun stopServer() {
        if (mServer!!.isRunning) {
            mServer!!.shutdown()
        } else {
            Log.w("AndServer", "The server has not started yet.")
        }
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    companion object {
        const val TAG = "CoreService"
    }
}