package ru.z13.imgtags.subnavigation

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import ru.terrakok.cicerone.android.SupportFragmentNavigator
import ru.z13.imgtags.factories.FragmentFactory

/**
 * Android Studio
 *
 * @author Yura Fedorchenko (www.android.z-13.ru)
 */
class MainScreenNavigator(fragmentManager: FragmentManager,
                          @IdRes private val containerId: Int,
                          private val callback: NavigatorCallback): SupportFragmentNavigator(fragmentManager, containerId) {

    override fun createFragment(screenKey: String, data: Any?): Fragment {
        return FragmentFactory.getFragment(screenKey, data)
    }

    override fun showSystemMessage(message: String) {
        callback.showSystemMessage(message)
    }

    override fun exit() {
        callback.exit()
    }
}