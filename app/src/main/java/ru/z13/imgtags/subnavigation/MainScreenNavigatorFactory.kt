package ru.z13.imgtags.subnavigation

import android.support.annotation.IdRes
import android.support.v4.app.FragmentManager
import ru.terrakok.cicerone.Navigator

/**
 * Android Studio
 *
 * @author Yura Fedorchenko (www.android.z-13.ru)
 */
class MainScreenNavigatorFactory {
    companion object {
        fun createNavigator(fragmentManager: FragmentManager, @IdRes containerId: Int, callback: NavigatorCallback):Navigator{
            return MainScreenNavigator(fragmentManager, containerId, callback)
        }
    }
}