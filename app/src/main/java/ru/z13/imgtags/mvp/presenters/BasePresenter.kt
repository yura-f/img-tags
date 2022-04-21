package ru.z13.imgtags.mvp.presenters

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.jetbrains.annotations.NotNull
import ru.z13.imgtags.domain.DomainEvents
import ru.z13.imgtags.subnavigation.MainRouter
import javax.inject.Inject

/**
 * @author Yura F (yura-f.github.io)
 */
abstract class BasePresenter<View:MvpView> constructor(val router: MainRouter, val domainEvents: DomainEvents): MvpPresenter<View>() {
    private var compositeSubscription: CompositeDisposable = CompositeDisposable()

    protected fun addSubscription(@NotNull subscription: Disposable){
        compositeSubscription.add(subscription)
    }

    protected fun showMainProgressBar(){
        domainEvents.notifyAppEvent(DomainEvents.AppEvent.SHOW_PROGRESS)
    }

    protected fun hideMainProgressBar(){
        domainEvents.notifyAppEvent(DomainEvents.AppEvent.HIDE_PROGRESS)
    }

    fun showImagePicker(){
        domainEvents.notifyAppEvent(DomainEvents.AppEvent.SHOW_IMAGE_PICKER)
    }

    open fun onBackPressed() {
        router.exit()
    }

    override fun onDestroy() {
        super.onDestroy()

        compositeSubscription.clear()
    }
}