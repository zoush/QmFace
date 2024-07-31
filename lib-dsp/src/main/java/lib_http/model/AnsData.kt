package lib_http.model

import android.util.Log

/**
 * @author huangjinhui6
 * @describe:  HTTP透传Message工具类
 * @date on 2022/7/26 17:08
 */
class AnsData(var success: Boolean, var data: Any?) {
    var othermessage :String = "Err: Early return"
    init {
        Log.d("ANSresult:", success.toString() + " " + "data:" + data.toString())
    }
}