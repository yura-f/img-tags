package ru.z13.imgtags.mvp.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.z13.imgtags.data.entity.database.ImageData

/**
 * @author Yura F (yura-f.github.io)
 */
@StateStrategyType(AddToEndSingleStrategy::class)
interface HomeView: MvpView {
    fun setImages(list: List<ImageData>)
    fun setImagePath(path: String)
    fun showAddDialog()
}