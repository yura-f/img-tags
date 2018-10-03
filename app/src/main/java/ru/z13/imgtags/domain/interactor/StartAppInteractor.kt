package ru.z13.imgtags.domain.interactor

import io.reactivex.Observable
import ru.z13.imgtags.data.Repository
import javax.inject.Inject

/**
 * Android Studio
 *
 * @author Yura Fedorchenko (www.android.z-13.ru)
 */
class StartAppInteractor @Inject constructor(private val repository: Repository) {
    fun loadLocalData(): Observable<Void> {
        return repository.loadStartLocalData().toObservable()
    }
}