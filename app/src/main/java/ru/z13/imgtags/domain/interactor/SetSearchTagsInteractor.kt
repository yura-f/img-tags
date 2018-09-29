package ru.z13.imgtags.domain.interactor

import io.reactivex.Observable
import ru.z13.imgtags.data.Repository
import ru.z13.imgtags.domain.Interactor
import javax.inject.Inject

/**
 * Android Studio
 *
 * @author Yura Fedorchenko (www.android.z-13.ru)
 */
class SetSearchTagsInteractor @Inject constructor(): Interactor<Void, String>() {
    @Inject
    lateinit var repository: Repository

    override fun buildObservable(param: String?): Observable<Void> {
        return repository.getAllImagesByTags(param).toObservable()
    }
}