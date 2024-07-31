package com.hik.dsp

import android.graphics.SurfaceTexture
import android.view.Surface
import android.view.SurfaceHolder
import android.view.TextureView
import androidx.annotation.CallSuper
import log.Logs


/**
 * HiAcs surface texture listener
 */
open class HiAcsSurfaceTextureListener(
    private val isCameraPreview: Boolean = true, // 是否为摄像头预览
    private val mirrorMode: HiAcsProxy.MirrorMode? = null, // 镜像模式
    private val screenID: Int = 0, // 显示屏id号
    private val channel: Int = 0, // 流通道号
    private val surfaceID: Int = 0,// 显示窗口号
    private val isDrawFaceFrame: Boolean = true, // 是否显示人脸框
    private val setFaceRule: (x: Int, y: Int, w: Int, h: Int) -> Unit = { _, _, _, _ -> }, // 下发人脸识别区域
) : TextureView.SurfaceTextureListener,SurfaceHolder.Callback {
    private var surface: Surface? = null

    @CallSuper
    override fun onSurfaceTextureAvailable(surface: SurfaceTexture, width: Int, height: Int) {
        HiAcsProxy.hiAcsHandler.post {
            Logs.dsp.w(
                "onSurfaceTextureAvailable width = $width, height = $height" +
                        "channel=$channel screenID=$screenID surfaceID=$surfaceID"
            )
            val newSurface = Surface(surface)
            this.surface = newSurface
            try {
                HiAcsProxy.startPreviewSurface(screenID, surfaceID, width, height, newSurface)
                setSurfaceCrop(width, height)
                if (isCameraPreview) {
                    HiAcsProxy.setSurfaceCameraCaptureData(
                        channel,
                        screenID,
                        surfaceID,
                        drawFaceFrame = isDrawFaceFrame
                    )
                } else {
                    HiAcsProxy.setSurfaceVideoDecodeData(channel, screenID, surfaceID)
                }

                HiAcsProxy.setSurfaceMirrorMode(
                    screenID, surfaceID, mirrorMode ?: if (isCameraPreview) {
                        HiAcsProxy.MirrorMode.HORIZONTAL
                    } else {
                        HiAcsProxy.MirrorMode.NONE
                    }
                )
            } catch (e: Throwable) {
                Logs.dsp.e("dsp error", e)
            }
        }
    }

    @CallSuper
    override fun onSurfaceTextureSizeChanged(surface: SurfaceTexture, width: Int, height: Int) {
        Logs.dsp.w("onSurfaceTextureSizeChanged width = $width, height = $height")
    }

    @CallSuper
    override fun onSurfaceTextureDestroyed(surface: SurfaceTexture): Boolean {
        HiAcsProxy.hiAcsHandler.post {
            Logs.dsp.w("onSurfaceTextureDestroyed channel=$channel screenID=$screenID surfaceID=$surfaceID")
            try {
                HiAcsProxy.stopPreviewSurface(screenID, surfaceID, this.surface)
            } catch (e: Throwable) {
                Logs.dsp.e("dsp error", e)
            }

            this.surface?.release()
            this.surface = null
        }
        return false
    }

    @CallSuper
    override fun onSurfaceTextureUpdated(surface: SurfaceTexture) {
    }

    // 剪裁图像
    private fun setSurfaceCrop(width: Int, height: Int) {
        val cameraInfo = HiAcsProxy.cameraInfo
        Logs.dsp.d("cameraInfo=$cameraInfo")
        // 预览宽高
        val previewWidth: Int
        val previewHeight: Int

        // 裁剪区域宽高
        val cropWidth: Int
        val cropHeight: Int

        when (cameraInfo.rotationAngle) {
            HiAcsProxy.RotationAngle.ANGLE_90,
            HiAcsProxy.RotationAngle.ANGLE_270 -> {
                previewWidth = cameraInfo.cameraHeight
                previewHeight = cameraInfo.cameraWidth
            }
            else -> {
                previewWidth = cameraInfo.cameraWidth
                previewHeight = cameraInfo.cameraHeight
            }
        }
        Logs.dsp.d("previewWidth=$previewWidth, previewHeight=$previewHeight")
        if (width * previewHeight >= height * previewWidth) {
            cropWidth = previewWidth
            cropHeight = ((height * previewWidth).toDouble() / width).toInt()
        } else {
            cropWidth = ((width * previewHeight).toDouble() / height).toInt()
            cropHeight = previewHeight
        }
        Logs.dsp.d("cropWidth=$cropWidth, cropHeight=$cropHeight")
        val mX: Int
        val mY: Int
        val mWidth: Int
        val mHeight: Int
        when (cameraInfo.rotationAngle) {
            HiAcsProxy.RotationAngle.ANGLE_90,
            HiAcsProxy.RotationAngle.ANGLE_270 -> {
                mX = (previewHeight - cropHeight) / 2
                mY = (previewWidth - cropWidth) / 2
                mWidth = cropHeight
                mHeight = cropWidth
            }
            else -> {
                mX = (previewWidth - cropWidth) / 2
                mY = (previewHeight - cropHeight) / 2
                mWidth = cropWidth
                mHeight = cropHeight
            }
        }
        setFaceRule(mX, mY, mWidth, mHeight)
        HiAcsProxy.setSurfaceCrop(screenID, surfaceID, mX, mY, mWidth, mHeight)
    }

    override fun surfaceCreated(holder: SurfaceHolder) {

    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        HiAcsProxy.hiAcsHandler.post {
            Logs.dsp.w(
                "onSurfaceTextureAvailable width = $width, height = $height" +
                        "channel=$channel screenID=$screenID surfaceID=$surfaceID"
            )
            try {
                HiAcsProxy.startPreviewSurface(screenID, surfaceID, width, height, holder.surface)
                setSurfaceCrop(width, height)
                if (isCameraPreview) {
                    HiAcsProxy.setSurfaceCameraCaptureData(
                        channel,
                        screenID,
                        surfaceID,
                        drawFaceFrame = isDrawFaceFrame
                    )
                } else {
                    HiAcsProxy.setSurfaceVideoDecodeData(channel, screenID, surfaceID)
                }

                HiAcsProxy.setSurfaceMirrorMode(
                    screenID, surfaceID, mirrorMode ?: if (isCameraPreview) {
                        HiAcsProxy.MirrorMode.HORIZONTAL
                    } else {
                        HiAcsProxy.MirrorMode.NONE
                    }
                )

            } catch (e: Throwable) {
                Logs.dsp.e("dsp error", e)
            }
        }
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        HiAcsProxy.hiAcsHandler.post {
            Logs.dsp.w("onSurfaceTextureDestroyed channel=$channel screenID=$screenID surfaceID=$surfaceID")
            try {
                HiAcsProxy.stopPreviewSurface(screenID, surfaceID, this.surface)
            } catch (e: Throwable) {
                Logs.dsp.e("dsp error", e)
            }


        }

    }
}