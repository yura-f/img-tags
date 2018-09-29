package ru.z13.imgtags.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import io.reactivex.Flowable
import ru.z13.imgtags.App
import ru.z13.imgtags.data.entity.database.ImageData
import ru.z13.imgtags.domain.DomainEvents.DomainEvent
import ru.z13.imgtags.domain.DomainState
import ru.z13.imgtags.domain.interactor.SaveImageItemInteractor
import ru.z13.imgtags.domain.interactor.SetSearchTagsInteractor
import ru.z13.imgtags.mvp.views.HomeView
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Android Studio
 *
 * @author Yura Fedorchenko (www.android.z-13.ru)
 */
@InjectViewState
class HomePresenter: BasePresenter<HomeView>() {
    @Inject
    lateinit var domainState: DomainState

    @Inject
    lateinit var saveImageItemInteractor: SaveImageItemInteractor

    @Inject
    lateinit var setSearchTagsInteractor: SetSearchTagsInteractor

    init {
        App.appComponent.inject(this)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        addSubscription(domainEvents.subscribeOnDomainEvent().subscribe{
            when(it){
                DomainEvent.UPDATED_IMAGES -> updateImages()
                DomainEvent.SELECTED_IMAGE_FROM_GALLERY -> viewState.setImagePath(domainState.getSelectedImagePath())
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

        addSubscription(saveImageItemInteractor.prepare(imageData).subscribe({},{},{
            hideMainProgressBar()
        }))
    }

    fun onClickImage(data: ImageData) {
        router.showSystemMessage("Image: " + data.toString())
    }

    fun setSearchViewFlowable(searchViewFlowable: Flowable<String>) {
        addSubscription(searchViewFlowable.debounce(400, TimeUnit.MILLISECONDS).subscribe{
            addSubscription(setSearchTagsInteractor.prepare(it).subscribe())
        })
    }
}