package ru.z13.imgtags.domain.interactor

import io.reactivex.Observable
import ru.z13.imgtags.data.Repository
import ru.z13.imgtags.data.entity.database.ImageData
import ru.z13.imgtags.domain.Interactor
import javax.inject.Inject

/**
 * Android Studio
 *
 * @author Yura Fedorchenko (www.android.z-13.ru)
 */
class SaveImageItemInteractor @Inject constructor(): Interactor<Void, ImageData>() {
    @Inject
    lateinit var repository: Repository

    override fun buildObservable(param: ImageData?): Observable<Void> {
        return repository.saveImage(param).toObservable()
    }
}