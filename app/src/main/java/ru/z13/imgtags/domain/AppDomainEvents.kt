package ru.z13.imgtags.domain

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import ru.z13.imgtags.domain.DomainEvents.AppEvent
import ru.z13.imgtags.domain.DomainEvents.DomainEvent
import ru.z13.imgtags.mvp.utils.SchedulerProvider

/**
 * @author Yura F (yura-f.github.io)
 */
class AppDomainEvents(private val schedulerProvider: SchedulerProvider) : DomainEvents {
    private val domainEventSubject: PublishSubject<DomainEvent> = PublishSubject.create<DomainEvent>()
    private val appEventSubject: PublishSubject<AppEvent> = PublishSubject.create<AppEvent>()

    /**
     * Domain Event
     */
    override fun subscribeOnDomainEvent(): Observable<DomainEvent> {
        return domainEventSubject
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
    }

    override fun notifyDomainEvent(domainEvent: DomainEvent){
        domainEventSubject.onNext(domainEvent)
    }

    /**
     * App Event
     */
    override fun subscribeOnAppEvent(): Observable<DomainEvents.AppEvent> {
        return appEventSubject
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
    }

    override fun notifyAppEvent(appEvent: DomainEvents.AppEvent) {
        appEventSubject.onNext(appEvent)
    }
}