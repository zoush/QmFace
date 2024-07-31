package base.ext

import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDestination
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import log.Logs

/**
 * 跳转Fragment
 *
 * @param resId  action id or a destination id to navigate
 * @param bundle arguments to pass to the destination
 */
fun Fragment.navigate(@IdRes resId: Int, bundle: Bundle? = null) {
    //解决navigation问题.参考链接：https://www.jianshu.com/p/3e4a25e4d7aa
    try {
        findNavController().navigate(resId, bundle)
    } catch (e: Exception) {
        Logs.exception.e("navigate", e)
    }
}

/**
 * Navigate via the given [NavDirections]
 *
 * @param directions directions that describe this navigation operation
 */
fun Fragment.navigate(directions: NavDirections) {
    navigate(directions.actionId, directions.arguments)
}

/**
 * navigate up
 */
fun Fragment.navigateUp() {
    try {
        findNavController().navigateUp()
    } catch (e: Exception) {
        Logs.exception.e("navigateUp", e)
    }
}

/**
 * popBackStack
 */
fun Fragment.popBackStack(): Boolean {
    return try {
        findNavController().popBackStack()
    } catch (e: Exception) {
        Logs.exception.e("popBackStack", e)
        false
    }
}

/**
 * popBackStack
 *
 * @param destinationId The topmost destination to retain
 * @param inclusive Whether the given destination should also be popped.
 */
fun Fragment.popBackStack(@IdRes destinationId: Int, inclusive: Boolean = false): Boolean {
    return try {
        findNavController().popBackStack(destinationId, inclusive)
    } catch (e: Exception) {
        Logs.exception.e("popBackStack", e)
        false
    }
}

/**
 * Gets the current destination.
 */
val Fragment.currentDestination: NavDestination?
    get() {
        return try {
            findNavController().currentDestination
        } catch (e: Exception) {
            Logs.exception.e("currentDestination", e)
            null
        }
    }

/**
 * 替换Fragment
 *
 * @param destinationId The topmost destination to retain
 */
fun Fragment.switchFragment(@IdRes destinationId: Int) {
    try {
        val navController = findNavController()
        if (navController.currentDestination?.id != destinationId) {
            if (!navController.popBackStack(destinationId, false)) {
                navController.navigate(destinationId)
            }
        }
    } catch (e: Exception) {
        Logs.exception.e("setStartDestination", e)
    }
}

/**
 * 跳转Fragment
 *
 * @param resId  所跳转的目的Fragment
 * @param bundle 跳转所携带的信息
 */
fun View.navigate(@IdRes resId: Int, bundle: Bundle? = null) {
    try {
        Navigation.findNavController(this).navigate(resId, bundle)
    } catch (e: Exception) {
        Logs.exception.e("navigate", e)
    }
}

/**
 * popBackStack
 */
fun View.popBackStack() {
    try {
        Navigation.findNavController(this).popBackStack()
    } catch (e: Exception) {
        Logs.exception.e("popBackStack", e)
    }
}

/**
 * popBackStack
 *
 * @param destinationId The topmost destination to retain
 * @param inclusive Whether the given destination should also be popped.
 */
fun View.popBackStack(@IdRes destinationId: Int, inclusive: Boolean = false) {
    try {
        Navigation.findNavController(this).popBackStack(destinationId, inclusive)
    } catch (e: Exception) {
        Logs.exception.e("popBackStack", e)
    }
}

/**
 * Sets the starting destination for this NavGraph.
 *
 * @param startDestId The id of the destination to be shown when navigating to this NavGraph.
 */
//fun View.setStartDestination(@IdRes startDestId: Int, bundle: Bundle? = null) {
//    try {
//        val navController = Navigation.findNavController(this)
//        val graph = navController.graph
//        if (graph.startDestinationId != startDestId) {
//            graph.startDestinationId = startDestId
//            navController.setGraph(graph, bundle)
//        }
//    } catch (e: Exception) {
//        Logs.exception.e("setStartDestination", e)
//    }
//}

/**
 * Gets the current destination.
 */
val View.currentDestination: NavDestination?
    get() {
        return try {
            Navigation.findNavController(this).currentDestination
        } catch (e: Exception) {
            Logs.exception.e("currentDestination", e)
            null
        }
    }

/**
 * 替换Fragment
 *
 * @param destinationId The topmost destination to retain
 */
fun View.switchFragment(@IdRes destinationId: Int) {
    try {
        val navController = Navigation.findNavController(this)
        if (navController.currentDestination?.id != destinationId) {
            if (!navController.popBackStack(destinationId, false)) {
                navController.navigate(destinationId)
            }
        }
    } catch (e: Exception) {
        Logs.exception.e("setStartDestination", e)
    }
}