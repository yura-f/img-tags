package ru.z13.imgtags.domain

import ru.z13.imgtags.data.entity.database.ImageData

/**
 * @author Yura F (yura-f.github.io)
 */
interface DomainState {
    fun getImages(): List<ImageData>
    fun getSelectedImagePath(): String
}