package ru.z13.imgtags.mvp.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import java.io.File

/**
 * @author Yura F (yura-f.github.io)
 */
@StateStrategyType(AddToEndSingleStrategy::class)
interface MainActivityView :MvpView {
    fun showProgress(isShow: Boolean)

    @StateStrategyType(SkipStrategy::class)
    fun showImagePicker()
}