package demo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import base.ext.asLiveData
import com.hik.vis.heop.v2.api.HeopResult
import heop.data.constant.FaceDataType
import heop.data.request.xml.CaptureFaceDataCond
import heop.v2.apis.HeopApis
import kotlinx.coroutines.launch
import log.Logs

/**
 * 人脸抓拍界面的 ViewModel
 */
class CaptureViewModel : ViewModel() {

    private val _picUrl = MutableLiveData<String>()
    val picUrl = _picUrl.asLiveData()
    var count = 1;

    private val _timeOut = MutableLiveData<Boolean>()
    val timeout = _timeOut.asLiveData()

    /**
     * 人脸抓拍
     */
    fun postCaptureFace() {
        viewModelScope.launch {
            when (val result =
                HeopApis.postCaptureFace(CaptureFaceDataCond(false, FaceDataType.URL))) {
                is HeopResult.Success -> {
                    Logs.setting.d("postCaptureFace success: ${result.data}")
                    if (result.data.faceDataUrl != null) {
                        _picUrl.value = result.data.faceDataUrl!!.value!!
                    }
                }
                is HeopResult.Error -> {
                    Logs.setting.e("postCaptureFace failed: ${result.data}")
                    count++
                    if (count == 2) {
                        //抓拍超时 1次5秒 可根据自己需求调整
                        _timeOut.value = true
                    } else {
                        postCaptureFace()
                    }
                }
            }
        }
    }
}