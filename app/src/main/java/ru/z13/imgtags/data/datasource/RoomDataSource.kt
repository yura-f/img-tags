package ru.z13.imgtags.data.datasource

import ru.z13.imgtags.data.database.Database
import ru.z13.imgtags.data.entity.database.*
import javax.inject.Inject


/**
 * Android Studio
 *
 * @author Yura Fedorchenko (www.android.z-13.ru)
 */
class RoomDataSource @Inject constructor(private val database: Database) {
    companion object {
        const val DATABASE_NAME = "imgTags.db"
    }

    /**IMAGES**/
    fun insertImageData(data: ImageData){
        database.imageDao().insert(data)
    }

    fun getAllImageData(): List<ImageData>{
        return database.imageDao().getAll()
    }

    fun getAllImageDataByTags(tags: List<String>): List<ImageData>{
        return database.imageTagJoinDao().getImagesByTags(tags)
    }

    fun getAllImageDataByTag(tag: String): List<ImageData>{
        return database.imageTagJoinDao().getImagesByTag(tag)
    }

    /**TAGS**/
    fun insertTagData(data: TagData){
        database.tagDao().insert(data)
    }

    fun getAllTagData(): List<TagData>{
        return database.tagDao().getAll()
    }

    /**IMAGE TAG JOIN**/
    fun insertImageTagJoinData(data: ImageTagJoinData){
        database.imageTagJoinDao().insert(data)
    }
}