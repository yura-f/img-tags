package ru.z13.imgtags.domain.interactor

import io.reactivex.Observable
import ru.z13.imgtags.data.Repository
import javax.inject.Inject

/**
 * @author Yura F (yura-f.github.io)
 */
class StartAppInteractor @Inject constructor(private val repository: Repository) {
    fun loadLocalData(): Observable<Void> {
        return repository.loadStartLocalData().toObservable()
    }
}