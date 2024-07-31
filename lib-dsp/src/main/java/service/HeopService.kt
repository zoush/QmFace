package service

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.lifecycleScope
import heop.v2.upload.HeopUploads
import log.Logs
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.ext.android.inject

/**
 *  Heop事件上报处理服务
 */
class HeopService : LifecycleService() {
    companion object {
        const val NOTIFICATION_CHANNEL_ID = "HEOP_SERVICE"
    }

    private val uploadCollector: IHeopUploadCollector by inject()

    override fun onCreate() {
        super.onCreate()

        startForeground()

        collectHeopEvents()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)

        return START_STICKY
    }

    override fun onDestroy() {
        stopForeground(true)

        super.onDestroy()
    }

    /**
     * Heop事件订阅
     */
    private fun collectHeopEvents() {
        with(HeopUploads) {
            // 操作通知事件监听
            operationNotificationEvent()
                .onEach(uploadCollector::operationNotificationEvent)
                .launchIn(lifecycleScope)


            // 门禁控制事件
            accessControllerEvent()
                .onEach(uploadCollector::accessControllerEvent)
                .launchIn(lifecycleScope)

            // 可视对讲事件监听
            voiceTalkEvent()
                .onEach(uploadCollector::voiceTalkEvent)
                .launchIn(lifecycleScope)

            //二维码事件
            qrCodeEvent()
                .onEach(uploadCollector::qrCodeEvent)
                .launchIn(lifecycleScope)

            //刷卡事件
            cardEvent()
                .onEach(uploadCollector::cardEvent)
                .launchIn(lifecycleScope)

            //身份证事件
            idCardInfoEvent()
                .onEach(uploadCollector::idCardInfoEvent)
                .launchIn(lifecycleScope)

        }
    }

    /**
     * 启动前台服务
     */
    @SuppressLint("WrongConstant")
    private fun startForeground() {
        Logs.common.d("startForeground")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManagerCompat.from(this).createNotificationChannel(
                NotificationChannel(
                    NOTIFICATION_CHANNEL_ID,
                    "heop服务",
                    NotificationManager.IMPORTANCE_LOW // 不弹出通知，只在通知栏有图标
                )
            )
        }

        val notification = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID).apply {
            //setSmallIcon(R.drawable.ic_service_foreground_24)
            setContentTitle("heop服务")
            setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            setAutoCancel(false)
            setOngoing(true)
        }.build()

        startForeground(hashCode(), notification)
    }
}