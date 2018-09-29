package ru.z13.imgtags.domain

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Android Studio
 *
 * @author Yura Fedorchenko (www.android.z-13.ru)
 */
abstract class Interactor<Response, Request> {
    protected abstract fun buildObservable(param: Request?): Observable<Response>

    fun prepare(param: Request?): Observable<Response>{
        return buildObservable(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun prepare(): Observable<Response>{
        return  prepare(null)
    }
}