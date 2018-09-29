package ru.z13.imgtags.mvp.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import java.io.File

/**
 * Android Studio
 *
 * @author Yura Fedorchenko (www.android.z-13.ru)
 */
@StateStrategyType(AddToEndSingleStrategy::class)
interface MainActivityView :MvpView {
    fun showProgress(isShow: Boolean)

    @StateStrategyType(SkipStrategy::class)
    fun showImagePicker()
}