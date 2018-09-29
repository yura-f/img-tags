package ru.z13.imgtags.ui.fragments

import android.content.Context
import com.arellomobile.mvp.MvpAppCompatFragment

/**
 * Android Studio
 *
 * @author Yura Fedorchenko (www.android.z-13.ru)
 */
abstract class BaseFragment: MvpAppCompatFragment() {
    override fun onAttach(context: Context?) {
        super.onAttach(context)

        mvpDelegate.onCreate()
    }

    override fun onDetach() {
        super.onDetach()

        mvpDelegate.onDestroy()
    }
}