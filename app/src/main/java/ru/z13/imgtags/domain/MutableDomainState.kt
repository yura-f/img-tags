package ru.z13.imgtags.domain

import ru.z13.imgtags.data.entity.database.ImageData

/**
 * Android Studio
 *
 * @author Yura Fedorchenko (www.android.z-13.ru)
 */
interface MutableDomainState: DomainState {
    fun setImages(list: List<ImageData>)
    fun setSelectedImagePath(path: String)
}