package ru.z13.imgtags.domain

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import ru.z13.imgtags.domain.DomainEvents.AppEvent
import ru.z13.imgtags.domain.DomainEvents.DomainEvent

/**
 * Android Studio
 *
 * @author Yura Fedorchenko (www.android.z-13.ru)
 */
class AppDomainEvents : DomainEvents {
    private val domainEventSubject: PublishSubject<DomainEvent> = PublishSubject.create<DomainEvent>()

    override fun subscribeOnDomainEvent(): Observable<DomainEvent> {
        return domainEventSubject
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun notifyDomainEvent(domainEvent: DomainEvent){
        domainEventSubject.onNext(domainEvent)
    }

    private val appEventSubject: PublishSubject<AppEvent> = PublishSubject.create<AppEvent>()

    override fun subscribeOnAppEvent(): Observable<DomainEvents.AppEvent> {
        return appEventSubject
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun notifyAppEvent(appEvent: DomainEvents.AppEvent) {
        appEventSubject.onNext(appEvent)
    }
}