package ru.z13.imgtags.domain.interactor

import io.reactivex.Completable
import ru.z13.imgtags.data.Repository
import ru.z13.imgtags.data.entity.database.ImageData
import javax.inject.Inject

/**
 * @author Yura F (yura-f.github.io)
 */
class ImagesInteractor @Inject constructor(private val repository: Repository) {
    fun saveImage(param: ImageData): Completable {
        return repository.saveImage(param)
    }

    fun getAllImagesByTags(param: String): Completable {
        return repository.getAllImagesByTags(param)
    }

    fun setSelectedImagePath(path: String): Completable{
        return repository.setSelectedImagePath(path)
    }
}