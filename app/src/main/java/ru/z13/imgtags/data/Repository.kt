package ru.z13.imgtags.data

import io.reactivex.Completable
import ru.z13.imgtags.data.entity.database.ImageData

/**
 * @author Yura F (yura-f.github.io)
 */
interface Repository {
    fun loadStartLocalData(): Completable
    fun setSelectedImagePath(path: String?): Completable
    fun saveImage(imageData: ImageData?): Completable
    fun getAllImagesByTags(tagsStr: String?): Completable
}