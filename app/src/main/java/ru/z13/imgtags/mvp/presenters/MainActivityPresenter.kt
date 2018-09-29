package ru.z13.imgtags.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import ru.z13.imgtags.App
import ru.z13.imgtags.domain.DomainEvents.AppEvent
import ru.z13.imgtags.domain.interactor.LoadStartLocalDataInteractor
import ru.z13.imgtags.domain.interactor.SetSelectedImagePathInteractor
import ru.z13.imgtags.enums.FragmentEnums
import ru.z13.imgtags.mvp.views.MainActivityView
import javax.inject.Inject

/**
 * Android Studio
 *
 * @author Yura Fedorchenko (www.android.z-13.ru)
 */
@InjectViewState
class MainActivityPresenter: BasePresenter<MainActivityView>() {
    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var setSelectedImagePathInteractor: SetSelectedImagePathInteractor

    @Inject
    lateinit var loadStartLocalDataInteractor: LoadStartLocalDataInteractor


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        addSubscription(domainEvents.subscribeOnAppEvent().subscribe{
            when(it){
                AppEvent.SHOW_PROGRESS -> viewState.showProgress(true)
                AppEvent.HIDE_PROGRESS -> viewState.showProgress(false)
                AppEvent.SHOW_IMAGE_PICKER -> viewState.showImagePicker()
                else -> {}
            }
        })

        addSubscription(loadStartLocalDataInteractor.prepare().subscribe())

        router.newRootScreen(FragmentEnums.HOME_FRAGMENT)
    }

    fun onFirstStart(){

    }

    fun onPause(){

    }

    fun onResume(){

    }

    fun selectedImageItem(path: String) {
        addSubscription(setSelectedImagePathInteractor.prepare(path).subscribe())
    }
}