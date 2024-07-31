package demo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hik.heop.data.request.json.VerifyMode
import com.hik.heop.data.request.json.VerifyModeBean
import com.hik.vis.heop.v2.api.HeopResult
import dagger.hilt.android.lifecycle.HiltViewModel
import heop.data.constant.VerifyType
import heop.data.request.json.TTS
import heop.data.request.json.TTSBean
import heop.data.request.xml.DeviceInfo
import heop.data.upload_event.CardEvent
import heop.data.upload_event.QRCodeEvent
import heop.v2.apis.HeopApis
import heop.v2.upload.HeopUploads
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import log.Logs
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    val qrCodeEvent: Flow<QRCodeEvent> =
        HeopUploads.qrCodeEvent().map { it.qrCodeEvent }.filterNotNull()

    val cardEvent: Flow<CardEvent> =
        HeopUploads.cardEvent().map { it.CardEvent }.filterNotNull()

    private val _deviceInfo = MutableLiveData<DeviceInfo?>()
    val deviceInfo: LiveData<DeviceInfo?> = _deviceInfo

    /**
     * 设置认证模式
     * @param openCard 是否开启卡片认证
     * @param openFingerPrint 是否开启指纹认证
     * @param openFace 是否开启人脸认证
     * @param openQrCode 是否开启二维码认证
     * @param openPassword 是否开启密码认证
     */
    fun setVerifyMode(
        mode: String = VerifyType.NONE,
        enableCard: Boolean = false,
        enableFingerPrint: Boolean = false,
        enableFace: Boolean = false,
        enableQRCode: Boolean = false,
        enablePassword: Boolean = false
    ) {
        viewModelScope.launch {
            when (val result = HeopApis.putVerifyMode(
                VerifyModeBean(
                    VerifyMode(
                        mode = mode,
                        card = enableCard,
                        fingerPrint = enableFingerPrint,
                        face = enableFace,
                        QRCode = enableQRCode,
                        password = enablePassword
                    )
                )
            )) {
                is HeopResult.Error -> {
                    Logs.common.e("setVerifyMode failed: ${result.data}")
                }
                is HeopResult.Success -> {
                    Logs.common.i("setVerifyMode success: ${result.data}")
                }
            }
        }
    }

    /**
     * TTS
     */
    fun TTStest(str:String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = HeopApis.putTTS(
                TTSBean(
                    tts = listOf(
                        TTS(
                            text = str,
                            language = "SimChinese"
                        )
                    ))

            )) {
                is HeopResult.Success -> {
                    Logs.setting.d("userDeleteAll success: ${result.data}")
                }
                is HeopResult.Error -> {
                    Logs.setting.d("userDeleteAll error: ${result.data}")
                }
            }
        }
    }

    /**
     * 获取设备信息
     */
    fun getDeviceInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = HeopApis.getDeviceInfo()) {
                is HeopResult.Success -> {
                    Logs.common.d("setVerifyMode success: ${result.data}")
                    _deviceInfo.postValue(result.data)
                }
                is HeopResult.Error -> {
                    Logs.common.e("setVerifyMode failed: ${result.data}")
                }
            }
        }
    }


}