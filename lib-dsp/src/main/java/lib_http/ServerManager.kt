package lib_http

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import lib_http.util.NetUtils
import java.util.*

/**
 * @author huangjinhui6
 * @describe: HTTP API 控制类
 * @date on 2022/7/14 19:50
 */
class ServerManager(private val mActivity: AppCompatActivity) :
    BroadcastReceiver() {
    private val mService: Intent

    /**
     * Register broadcast.
     */
    fun register() {
        val filter = IntentFilter(ACTION)
        mActivity.registerReceiver(this, filter)
    }

    /**
     * UnRegister broadcast.
     */
    fun unRegister() {
        mActivity.unregisterReceiver(this)
    }

    fun startServer() {
        mActivity.startService(mService)
    }

    fun stopServer() {
        mActivity.stopService(mService)
    }

    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action
        if (ACTION == action) {
            val cmd = intent.getIntExtra(CMD_KEY, 0)
            when (cmd) {
                CMD_VALUE_START -> {
                    val ip = intent.getStringExtra(MESSAGE_KEY)
                    if (!TextUtils.isEmpty(ip)) {
                        val messageList: MutableList<String?> = LinkedList()
                        messageList.add("服务器已启动，访问地址如下：")
                        for ((_, value) in NetUtils.localIPAddressList.orEmpty()) {
                            val ipGet = value?.hostAddress
                            messageList.add("   http://$ipGet:8080/<请于此处填写对应协议字段>")
                            TextUtils.join("\n", messageList)

                        }
                        Toast.makeText(context, TextUtils.join("\n", messageList), Toast.LENGTH_LONG)
                            .show()
                    } else {
                        Toast.makeText(context, "没有获取到服务器IP地址", Toast.LENGTH_LONG).show()
                    }
                }
                CMD_VALUE_ERROR -> {
                    val error = intent.getStringExtra(MESSAGE_KEY)
                    Toast.makeText(context, error, Toast.LENGTH_LONG).show()
                }
                CMD_VALUE_STOP -> {
                    Toast.makeText(context, "服务器已停止", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    companion object {
        private const val ACTION = "com.yanzhenjie.andserver.receiver"
        private const val CMD_KEY = "CMD_KEY"
        private const val MESSAGE_KEY = "MESSAGE_KEY"
        private const val CMD_VALUE_START = 1
        private const val CMD_VALUE_ERROR = 2
        private const val CMD_VALUE_STOP = 4

        /**
         * Notify serverStart.
         *
         * @param context context.
         */
        fun onServerStart(context: Context, hostAddress: String?) {
            sendBroadcast(context, CMD_VALUE_START, hostAddress)
        }

        /**
         * Notify serverStop.
         *
         * @param context context.
         */
        fun onServerError(context: Context, error: String?) {
            sendBroadcast(context, CMD_VALUE_ERROR, error)
        }

        /**
         * Notify serverStop.
         *
         * @param context context.
         */
        fun onServerStop(context: Context) {
            sendBroadcast(context, CMD_VALUE_STOP)
        }

        private fun sendBroadcast(context: Context, cmd: Int, message: String? = null) {
            val broadcast = Intent(ACTION)
            broadcast.putExtra(CMD_KEY, cmd)
            broadcast.putExtra(MESSAGE_KEY, message)
            context.sendBroadcast(broadcast)
        }
    }

    init {
        mService = Intent(mActivity, CoreService::class.java)
    }
}