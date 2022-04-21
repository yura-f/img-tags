package ru.z13.imgtags.data.database.dao

import android.arch.persistence.room.*
import ru.z13.imgtags.data.entity.database.TagData

/**
 * @author Yura F (yura-f.github.io)
 */
@Dao
interface TagDao: BaseDao<TagData> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(obj: List<TagData>)

    @Transaction
    @Query("SELECT * FROM tags")
    fun getAll(): List<TagData>
}