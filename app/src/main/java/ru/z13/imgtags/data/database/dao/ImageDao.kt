package ru.z13.imgtags.data.database.dao

import android.arch.persistence.room.*
import ru.z13.imgtags.data.entity.database.ImageData

/**
 * Android Studio
 *
 * @author Yura Fedorchenko (www.android.z-13.ru)
 */
@Dao
interface ImageDao: BaseDao<ImageData> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(obj: List<ImageData>)

    @Transaction
    @Query("SELECT * FROM images")
    fun getAll(): List<ImageData>
}