package lib_http.model

import android.os.Parcel
import android.os.Parcelable


/**
 * @author huangjinhui6
 * @describe:  HTTP透传返回消息类
 * @date on 2022/7/26 17:08
 */
class ReturnData : Parcelable {
    var success = false
    var code = 0
    var msg: String? = null
    var data: Any? = null

    constructor()
    protected constructor(`in`: Parcel) {
        success = `in`.readByte().toInt() != 0
        code = `in`.readInt()
        msg = `in`.readString()
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeByte((if (success) 1 else 0).toByte())
        dest.writeInt(code)
        dest.writeString(msg)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ReturnData?> = object : Parcelable.Creator<ReturnData?> {
            override fun createFromParcel(`in`: Parcel): ReturnData? {
                return ReturnData(`in`)
            }

            override fun newArray(size: Int): Array<ReturnData?> {
                return arrayOfNulls(size)
            }
        }
    }
}