package ru.z13.imgtags.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import io.reactivex.Flowable
import ru.z13.imgtags.data.entity.database.ImageData
import ru.z13.imgtags.domain.DomainEvents
import ru.z13.imgtags.domain.DomainEvents.DomainEvent
import ru.z13.imgtags.domain.DomainState
import ru.z13.imgtags.domain.interactor.ImagesInteractor
import ru.z13.imgtags.mvp.utils.SchedulerProvider
import ru.z13.imgtags.mvp.views.HomeView
import ru.z13.imgtags.subnavigation.MainRouter
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Android Studio
 *
 * @author Yura Fedorchenko (www.android.z-13.ru)
 */
@InjectViewState
class HomePresenter @Inject constructor(private val domainState: DomainState,
                                        private val imagesInteractor: ImagesInteractor,
                                        private val schedulerProvider: SchedulerProvider,
                                        router: MainRouter,
                                        domainEvents: DomainEvents) : BasePresenter<HomeView>(router, domainEvents) {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        addSubscription(domainEvents.subscribeOnDomainEvent().subscribe{
            when(it){
                DomainEvent.UPDATED_IMAGES -> updateImages()
                DomainEvent.SELECTED_IMAGE_FROM_GALLERY -> viewState.setImagePath(domainState.getSelectedImagePath())
                null -> {}
            }
        })

        updateImages()
    }

    private fun updateImages() {
        viewState.setImages(domainState.getImages())
    }

    fun onClickAddBtn() {
        viewState.showAddDialog()
    }

    fun saveImage(imagePath: String, tags: String) {
        val imageData = ImageData(imagePath, tags)

        showMainProgressBar()

        addSubscription(imagesInteractor.saveImage(imageData)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe {
                    hideMainProgressBar()
                })
    }

    fun onClickImage(data: ImageData) {
        router.showSystemMessage("Image: " + data.toString())
    }

    fun setSearchViewFlowable(searchViewFlowable: Flowable<String>) {
        addSubscription(searchViewFlowable.debounce(400, TimeUnit.MILLISECONDS).subscribe{
            addSubscription(imagesInteractor.getAllImagesByTags(it)
                    .subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui())
                    .subscribe())
        })
    }
}