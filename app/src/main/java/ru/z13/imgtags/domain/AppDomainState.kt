package ru.z13.imgtags.domain

import ru.z13.imgtags.data.entity.database.ImageData
import ru.z13.imgtags.domain.DomainEvents.DomainEvent

/**
 * Android Studio
 *
 * @author Yura Fedorchenko (www.android.z-13.ru)
 */
class AppDomainState(private val domainEvents: AppDomainEvents): DomainState {
    private var images: List<ImageData> = listOf()

    private var selectedImagePath: String = ""

    /**
     * IMAGES
     */
    fun setImages(list: List<ImageData>){
        images = list

        notifyDomainEvent(DomainEvent.UPDATED_IMAGES)
    }

    fun setSelectedImagePath(path: String) {
        selectedImagePath = path

        notifyDomainEvent(DomainEvent.SELECTED_IMAGE_FROM_GALLERY)
    }

    override fun getSelectedImagePath(): String {
        return selectedImagePath
    }

    override fun getImages(): List<ImageData> {
        return images
    }

    /**
     * NOTIFY
     */
    private fun notifyDomainEvent(event: DomainEvent){
        domainEvents.notifyDomainEvent(event)
    }
}