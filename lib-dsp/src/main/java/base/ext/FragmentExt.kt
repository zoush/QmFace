package base.ext

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

private var toast: Toast? = null

fun Fragment.toast(message: CharSequence) {
    toast?.cancel()
    toast = Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT)
        .apply { show() }
}

/**
 * Fragment中获取具体类型的Activity
 */
inline fun <reified T : FragmentActivity> Fragment.typedActivity(): T? = activity as? T

/**
 * Fragment中获取具体类型的Activity
 */
inline fun <reified T : FragmentActivity> Fragment.requireTypedActivity(): T = requireActivity() as T