package base.ext

import androidx.lifecycle.LiveData


fun <T> LiveData<T>.asLiveData() = this