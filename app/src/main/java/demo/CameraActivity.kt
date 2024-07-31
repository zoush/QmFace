package demo

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import base.viewbinding.viewBinding
import com.hik.dsp.HiAcsSurfaceTextureListener
import com.hik.heop.demo.R
import com.hik.heop.demo.databinding.ActivityCameraBinding
import util.ImageUtil

/**
 * Create by zoush on 2024/7/25.
 * 756350775@qq.com
 */
class CameraActivity : AppCompatActivity(R.layout.activity_camera) {

    private val homeViewModel: HomeViewModel by viewModels()
    private val captureViewModel: CaptureViewModel by viewModels()
    private val binding by viewBinding(ActivityCameraBinding::bind)
    private var base64: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        observeLiveData()
    }

    private fun initView() {
        with(binding) {
            sfvFacePreview.surfaceTextureListener =
                HiAcsSurfaceTextureListener(isCameraPreview = true)

            // 2秒后自动抓拍，可根据自己需求调整，可点击触发
            Handler().postDelayed(Runnable { captureViewModel.postCaptureFace() }, 1000)

            binding.ivBack.setOnClickListener { finish() }
        }
    }

    private fun observeLiveData() {
        captureViewModel.timeout.observe(this) {
            Log.d("------", "拍照超时")
        }
        captureViewModel.picUrl.observe(this) {
            Log.d("---------",it)
            base64 = ImageUtil.imageToBase64(it)
            homeViewModel.TTStest("抓拍成功")
        }
    }
}