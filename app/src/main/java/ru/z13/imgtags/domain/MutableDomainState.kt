package ru.z13.imgtags.domain

import ru.z13.imgtags.data.entity.database.ImageData

/**
 * @author Yura F (yura-f.github.io)
 */
interface MutableDomainState: DomainState {
    fun setImages(list: List<ImageData>)
    fun setSelectedImagePath(path: String)
}