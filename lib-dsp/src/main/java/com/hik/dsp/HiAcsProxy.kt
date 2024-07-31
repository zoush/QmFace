package com.hik.dsp

import android.graphics.RectF
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.view.Surface
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import heop.v2.apis.HeopApis
import com.hik.vis.heop.v2.isSuccess
import kotlinx.coroutines.runBlocking
import java.lang.reflect.Proxy
import com.hik.common.HiAcs
import com.hik.common.HiAcsCommon

const val FACE_DETECTION_TIMEOUT = 300L

object HiAcsProxy {
    enum class MirrorMode {
        NONE,
        HORIZONTAL,
        VERTICAL,
        CENTER
    }

    enum class RotationAngle {
        ANGLE_0,
        ANGLE_90,
        ANGLE_180,
        ANGLE_270,
    }

    data class CameraInfo(
        var cameraWidth: Int,
        var cameraHeight: Int,
        var rotationAngle: RotationAngle,
    )

    private val cancelPreviewSurfaceMethod1 = try {
        HiAcs::class.java.getDeclaredMethod(
            "cancelPreviewSurface",
            Int::class.javaPrimitiveType,
            Int::class.javaPrimitiveType
        )
    } catch (e: NoSuchMethodException) {
        null
    }

    private val cancelPreviewSurfaceMethod2 = try {
        HiAcs::class.java.getDeclaredMethod(
            "cancelPreviewSurface",
            Int::class.javaPrimitiveType,
            Int::class.javaPrimitiveType,
            Surface::class.java
        )
    } catch (e: NoSuchMethodException) {
        null
    }

    private val setSurfaceCaptDataMethod1 = try {
        HiAcs::class.java.getDeclaredMethod(
            "setSurfaceCaptData",
            Int::class.javaPrimitiveType,
            Int::class.javaPrimitiveType,
            Int::class.javaPrimitiveType,
        )
    } catch (e: NoSuchMethodException) {
        null
    }

    private val setSurfaceCaptDataMethod2 = try {
        HiAcs::class.java.getDeclaredMethod(
            "setSurfaceCaptData",
            Int::class.javaPrimitiveType,
            Int::class.javaPrimitiveType,
            Int::class.javaPrimitiveType,
            Int::class.javaPrimitiveType,
        )
    } catch (e: NoSuchMethodException) {
        null
    }

    private val classFaceDetectionListener = try {
        Class.forName("com.hik.common.HiAcs\$FaceDetectionListener")
    } catch (e: ClassNotFoundException) {
        null
    }
    private val setFaceDetectionListenerMethod = classFaceDetectionListener?.let {
        try {
            HiAcs::class.java.getDeclaredMethod("setFaceDetectionListener", it)
        } catch (e: NoSuchMethodException) {
            null
        }
    }

    private val handlerThreadHandlerThread by lazy {
        HandlerThread("HandlerThread-HiAcsProxy").apply {
            start()
        }
    }
    val hiAcsHandler = Handler(handlerThreadHandlerThread.looper)
    private val mainHandler = Handler(Looper.getMainLooper())
    @Volatile
    private var hiAcsInstance: HiAcs? = null
    private val hiAcs
        get() = hiAcsInstance ?: synchronized(this) {
            hiAcsInstance ?: HiAcs.getInstance().also {
                hiAcsInstance = it
            }
        }

    @Volatile
    private var cameraInfoInstance: CameraInfo? = null
    val cameraInfo
        @WorkerThread
        get() = cameraInfoInstance ?: synchronized(this) {
            cameraInfoInstance ?: getCameraInfo().also {
                cameraInfoInstance = it
            }
        }

    private val faceDetectionListeners = mutableListOf<(CameraInfo, List<RectF>) -> Unit>()

    init {
        hiAcsHandler.post {
            val info = cameraInfo
            val faceDetectionEmptyRunnable = Runnable {
                faceDetectionListeners.forEach { listener ->
                    listener.invoke(info, emptyList())
                }
            }

            classFaceDetectionListener?.let { clsFaceDetectionListener ->
                val faceDetectionListener = Proxy.newProxyInstance(
                    javaClass.classLoader,
                    arrayOf(clsFaceDetectionListener)
                ) { _, method, args ->
                    if (method.name == "onFaceDetection") {
                        @Suppress("unchecked_cast")
                        val list = try {
                            (args?.firstOrNull() as? Array<*>)?.toList() as List<RectF>
                        } catch (e: Throwable) {
                            null
                        }
                        mainHandler.removeCallbacksAndMessages(null)
                        if (list.isNullOrEmpty()) {
                            mainHandler.post(faceDetectionEmptyRunnable)
                            return@newProxyInstance Unit
                        }
                        mainHandler.post {
                            faceDetectionListeners.forEach { listener ->
                                listener.invoke(info, list)
                            }
                        }
                        mainHandler.postDelayed(faceDetectionEmptyRunnable, FACE_DETECTION_TIMEOUT)
                    }
                }

                setFaceDetectionListenerMethod?.invoke(hiAcs, faceDetectionListener)
            }
        }
    }

    /**
     * 增加人脸检测监听
     */
    @MainThread
    fun addFaceDetectionListener(listener: (CameraInfo, List<RectF>) -> Unit) {
        faceDetectionListeners.add(listener)
    }

    /**
     * 移除人脸检测监听
     */
    @MainThread
    fun removeFaceDetectionListener(listener: (CameraInfo, List<RectF>) -> Unit) {
        faceDetectionListeners.remove(listener)
    }

    /**
     * 开启预览
     */
    @WorkerThread
    fun startPreviewSurface(screenID: Int, surfaceID: Int, width: Int, height: Int, surface: Surface): Boolean {
        // DSP要求 宽是8的倍数，高是2的倍数
        val w = if (width / 8 * 8 != width) {
            width / 8 * 8 + 8
        } else {
            width
        }
        val h = if (height / 2 * 2 != height) {
            height / 2 * 2 + 2
        } else {
            height
        }
        return hiAcs.setPreviewSurface(screenID, surfaceID, w, h, surface)
    }

    /**
     * 关闭预览
     */
    @WorkerThread
    fun stopPreviewSurface(screenID: Int, surfaceID: Int, surface: Surface?): Boolean {
        if (cancelPreviewSurfaceMethod2 != null) {
            return cancelPreviewSurfaceMethod2.invoke(hiAcs, screenID, surfaceID, surface) as Boolean
        }
        if (cancelPreviewSurfaceMethod1 != null) {
            return cancelPreviewSurfaceMethod1.invoke(hiAcs, screenID, surfaceID) as Boolean
        }
        return false
    }

    /**
     * 设置为显示摄像头预览数据
     */
    @WorkerThread
    fun setSurfaceCameraCaptureData(
        channel: Int,
        screenID: Int,
        surfaceID: Int,
        drawFaceFrame: Boolean = true
    ): Boolean {
        if (setSurfaceCaptDataMethod2 != null) {
            return setSurfaceCaptDataMethod2.invoke(
                hiAcs,
                channel,
                screenID,
                surfaceID,
                if (drawFaceFrame) 1 else 0
            ) as Boolean
        }
        if (setSurfaceCaptDataMethod1 != null) {
            return setSurfaceCaptDataMethod1.invoke(hiAcs, channel, screenID, surfaceID) as Boolean
        }
        return false
    }

    /**
     * 设置为显示视频解码数据
     */
    @WorkerThread
    fun setSurfaceVideoDecodeData(channel: Int, screenID: Int, surfaceID: Int): Boolean {
        return hiAcs.setSurfaceVdecData(channel, screenID, surfaceID)
    }

    /**
     * 设置镜像模式
     */
    @WorkerThread
    fun setSurfaceMirrorMode(screenID: Int, surfaceID: Int, mirrorMode: MirrorMode): Boolean {
        return hiAcs.setSurfaceMirror(
            screenID, surfaceID, when (mirrorMode) {
                MirrorMode.NONE -> HiAcsCommon.MirrorMode.MIRROR_MODE_NONE
                MirrorMode.HORIZONTAL -> HiAcsCommon.MirrorMode.MIRROR_MODE_HORIZONTAL
                MirrorMode.VERTICAL -> HiAcsCommon.MirrorMode.MIRROR_MODE_VERTICAL
                MirrorMode.CENTER -> HiAcsCommon.MirrorMode.MIRROR_MODE_CENTER
            }
        )
    }

    /**
     * 裁剪显示图像
     */
    @WorkerThread
    fun setSurfaceCrop(screenID: Int, surfaceID: Int, x: Int, y: Int, width: Int, height: Int): Boolean {
        // DSP要求 宽是8的倍数，高是2的倍数
        val w = if (width / 8 * 8 != width) {
            width / 8 * 8 + 8
        } else {
            width
        }
        val h = if (height / 2 * 2 != height) {
            height / 2 * 2 + 2
        } else {
            height
        }
        return hiAcs.setSurfaceCrop(screenID, surfaceID, x / 8 * 8, y / 2 * 2, w, h)
    }

    /**
     * 获取摄像头信息
     */
    @WorkerThread
    @JvmName("jvmGetCameraInfo")
    private fun getCameraInfo(): CameraInfo = runBlocking {
        // 获取摄像头宽高
        HeopApis.getCameraInfo().isSuccess { bean ->
            val info = bean.cameraInfo?.list?.firstOrNull() ?: return@isSuccess
            // 摄像头始终是宽大于高
            var cameraWidth = info.videoResolutionWidth ?: 1920
            var cameraHeight = info.videoResolutionHeight ?: 1080
            if (cameraWidth < cameraHeight) {
                cameraWidth = info.videoResolutionHeight ?: 1920
                cameraHeight = info.videoResolutionWidth ?: 1080
            }
            return@runBlocking CameraInfo(
                cameraWidth = cameraWidth,
                cameraHeight = cameraHeight,
                rotationAngle = when (info.rotationAngle) {
                    0 -> RotationAngle.ANGLE_0
                    90 -> RotationAngle.ANGLE_90
                    180 -> RotationAngle.ANGLE_180
                    270 -> RotationAngle.ANGLE_270
                    else -> RotationAngle.ANGLE_90
                }
            )
        }

        CameraInfo(
            cameraWidth = 1920,
            cameraHeight = 1080,
            rotationAngle = RotationAngle.ANGLE_90
        )
    }
}