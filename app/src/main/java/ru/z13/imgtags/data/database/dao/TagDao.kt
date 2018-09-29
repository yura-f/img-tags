package ru.z13.imgtags.data.database.dao

import android.arch.persistence.room.*
import ru.z13.imgtags.data.entity.database.TagData

/**
 * Android Studio
 *
 * @author Yura Fedorchenko (www.android.z-13.ru)
 */
@Dao
interface TagDao: BaseDao<TagData> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(obj: List<TagData>)

    @Transaction
    @Query("SELECT * FROM tags")
    fun getAll(): List<TagData>
}