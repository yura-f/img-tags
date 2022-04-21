package ru.z13.imgtags.domain

import ru.z13.imgtags.data.entity.database.ImageData
import ru.z13.imgtags.domain.DomainEvents.DomainEvent

/**
 * @author Yura F (yura-f.github.io)
 */
class AppDomainState(private val domainEvents: DomainEvents): MutableDomainState {
    private var images: List<ImageData> = listOf()

    private var selectedImagePath: String = ""

    /**
     * IMAGES
     */
    override fun setImages(list: List<ImageData>){
        images = list

        notifyDomainEvent(DomainEvent.UPDATED_IMAGES)
    }

    override fun setSelectedImagePath(path: String) {
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