package ru.z13.imgtags.domain

import io.reactivex.Observable

/**
 * @author Yura F (yura-f.github.io)
 */
interface DomainEvents {
    fun subscribeOnDomainEvent(): Observable<DomainEvent>
    fun notifyDomainEvent(domainEvent: DomainEvent)

    fun subscribeOnAppEvent(): Observable<AppEvent>
    fun notifyAppEvent(appEvent: AppEvent)

    enum class DomainEvent {
        UPDATED_IMAGES,
        SELECTED_IMAGE_FROM_GALLERY
    }

    enum class AppEvent {
        SHOW_PROGRESS,
        HIDE_PROGRESS,
        SHOW_IMAGE_PICKER
    }
}