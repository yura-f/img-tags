package ru.z13.imgtags.domain.interactor

import io.reactivex.Completable
import ru.z13.imgtags.data.Repository
import ru.z13.imgtags.data.entity.database.ImageData
import javax.inject.Inject

/**
 * Android Studio
 *
 * @author Yura Fedorchenko (www.android.z-13.ru)
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