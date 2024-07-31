package base.viewbinding

import android.view.View
import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import base.ext.findRootView
import base.lifecycle.AutoClearProperty

/**
 * 创建视图绑定
 *
 * @param initializer   视图绑定初始化函数
 */
inline fun <VB : ViewBinding> viewBinding(
    crossinline lifecycleOwnerProvider: (() -> LifecycleOwner?),
    crossinline initializer: () -> VB,
): Lazy<VB> = AutoClearProperty(
    lifecycleOwnerProvider = { lifecycleOwnerProvider() },
    initializer = { initializer() }
)

/**
 * 创建视图绑定
 *
 * @param bindProvider   从View创建视图绑定的函数(ViewBinding::bind)
 * @param viewProvider      提供视图绑定的根视图的函数
 */
inline fun <VB : ViewBinding> viewBinding(
    crossinline lifecycleOwnerProvider: (() -> LifecycleOwner?),
    crossinline bindProvider: (View) -> VB,
    crossinline viewProvider: () -> View
): Lazy<VB> = viewBinding(
    lifecycleOwnerProvider = lifecycleOwnerProvider,
    initializer = { bindProvider(viewProvider()) }
)

/**
 * Activity中创建视图绑定
 *
 * @param initializer   视图绑定初始化函数
 */
inline fun <VB : ViewBinding> ComponentActivity.viewBinding(
    crossinline initializer: () -> VB,
): Lazy<VB> = viewBinding(
    lifecycleOwnerProvider = { this },
    initializer = initializer
)

/**
 * Activity中创建视图绑定
 *
 * 注意，要使用 { findRootView() } 为 viewProvider 需要使用
 * ComponentActivity的(@LayoutRes int contentLayoutId)构造方法
 *
 * @param bindProvider   从View创建视图绑定的函数(ViewBinding::bind）
 * @param viewProvider      返回根视图的函数
 */
inline fun <VB : ViewBinding> ComponentActivity.viewBinding(
    crossinline bindProvider: (View) -> VB,
    crossinline viewProvider: () -> View = { findRootView() }
): Lazy<VB> = viewBinding(
    lifecycleOwnerProvider = { this },
    bindProvider = bindProvider,
    viewProvider = viewProvider
)

/**
 * Fragment中创建视图绑定
 *
 * @param initializer   视图绑定初始化函数
 */
inline fun <VB : ViewBinding> Fragment.viewBinding(
    crossinline initializer: () -> VB,
): Lazy<VB> = viewBinding(
    lifecycleOwnerProvider = { viewLifecycleOwner },
    initializer = initializer
)

/**
 * Fragment中创建视图绑定
 *
 * 注意，要使用 { requireView() } 为 viewProvider 需要使用
 * Fragment的(@LayoutRes int contentLayoutId)构造方法
 *
 * @param bindProvider   从View创建视图绑定的函数(ViewBinding::bind）
 * @param viewProvider      返回根视图的函数 [Fragment.requireView]
 */
inline fun <VB : ViewBinding> Fragment.viewBinding(
    crossinline bindProvider: (View) -> VB,
    crossinline viewProvider: () -> View = { requireView() }
): Lazy<VB> = viewBinding(
    lifecycleOwnerProvider = { viewLifecycleOwner },
    bindProvider = bindProvider,
    viewProvider = viewProvider
)