package demo

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import base.viewbinding.viewBinding
import com.hik.heop.demo.R
import com.hik.heop.demo.databinding.ActivityTestBinding
import dagger.hilt.android.AndroidEntryPoint
import heop.data.constant.VerifyType
import kotlinx.coroutines.launch
import log.Logs

/**
 * Create by zoush on 2024/7/25.
 */
@AndroidEntryPoint
class TestActivity : AppCompatActivity(R.layout.activity_test) {

    private val viewModel: HomeViewModel by viewModels()
    private val binding by viewBinding(ActivityTestBinding::bind)
    private val navViewModel: NavigationBarViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //隐藏状态栏
        navViewModel.hideNavigationBar(this)
        viewModel.getDeviceInfo()
        observeEvents()

        initView()
    }

    private fun initView() {
        binding.btnCamera.setOnClickListener {
            startActivity(Intent(this, CameraActivity::class.java))
        }

        with(binding) {
            viewModel.deviceInfo.observe(this@TestActivity) {
                var s = it?.serialNumber.toString().replace("-", "")
                binding.tvDevSerial.text = "设备序列号:" + s.substring(s.length - 32)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // 开启采集模式
        viewModel.setVerifyMode(
            mode = VerifyType.ENROLL,
            enableCard = true,    //身份证
            enableFingerPrint = true,
            enableFace = true,
            enableQRCode = true,  //二维码
            enablePassword = true
        )
    }

    override fun onPause() {
        super.onPause()
        // 关闭
        viewModel.setVerifyMode()
    }


    private fun observeEvents() {
        lifecycleScope.launch {
            launch {
                // 二维码事件
                viewModel.qrCodeEvent.collect {
                    Logs.common.d("qrCodeEvent, qrCodeInfo: ${it.qRCodeInfo}")
                    binding.dataTextView.text = "二维码数据:" + it.qRCodeInfo.toString()
                }
            }
            launch {
                // 刷卡事件
                viewModel.cardEvent.collect {
                    Logs.common.d("cardEvent, cardNo: ${it.cardNo}, cardType: ${it.cardType}")
                    binding.dataTextView.text = "身份证数据:" + it.toString()
                }
            }
        }
    }

}