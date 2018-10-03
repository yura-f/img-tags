package ru.z13.imgtags.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import ru.z13.imgtags.domain.DomainEvents
import ru.z13.imgtags.domain.DomainEvents.AppEvent
import ru.z13.imgtags.domain.interactor.ImagesInteractor
import ru.z13.imgtags.domain.interactor.StartAppInteractor
import ru.z13.imgtags.enums.FragmentEnums
import ru.z13.imgtags.mvp.utils.SchedulerProvider
import ru.z13.imgtags.mvp.views.MainActivityView
import ru.z13.imgtags.subnavigation.MainRouter
import javax.inject.Inject

/**
 * Android Studio
 *
 * @author Yura Fedorchenko (www.android.z-13.ru)
 */
@InjectViewState
class MainActivityPresenter @Inject constructor(private val imagesInteractor: ImagesInteractor,
                                                private val startAppInteractor: StartAppInteractor,
                                                private val schedulerProvider: SchedulerProvider,
                                                router: MainRouter,
                                                domainEvents: DomainEvents): BasePresenter<MainActivityView>(router, domainEvents) {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        addSubscription(domainEvents.subscribeOnAppEvent().subscribe{
            when(it){
                AppEvent.SHOW_PROGRESS -> viewState.showProgress(true)
                AppEvent.HIDE_PROGRESS -> viewState.showProgress(false)
                AppEvent.SHOW_IMAGE_PICKER -> viewState.showImagePicker()
                null -> {}
            }
        })

        addSubscription(startAppInteractor.loadLocalData()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe())

        router.newRootScreen(FragmentEnums.HOME_FRAGMENT)
    }

    fun onFirstStart(){

    }

    fun onPause(){

    }

    fun onResume(){

    }

    fun selectedImageItem(path: String) {
        addSubscription(imagesInteractor.setSelectedImagePath(path)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe())
    }
}