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
class LoadStartLocalDataInteractor @Inject constructor(): Interactor<Void, Void>() {
    @Inject
    lateinit var repository: Repository

    override fun buildObservable(param: Void?): Observable<Void> {
        return repository.loadStartLocalData().toObservable()
    }
}