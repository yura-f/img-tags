package ru.z13.imgtags.data.database.dao

import android.arch.persistence.room.*
import ru.z13.imgtags.data.entity.database.ImageData
import ru.z13.imgtags.data.entity.database.ImageTagJoinData
import ru.z13.imgtags.data.entity.database.TagData

/**
 * @author Yura F (yura-f.github.io)
 */
@Dao
interface ImageTagJoinDao: BaseDao<ImageTagJoinData> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(obj: ImageTagJoinData)

//    @Transaction
//    @Query("SELECT * FROM images INNER JOIN image_tag_join ON images.id=image_tag_join.imageId WHERE image_tag_join.tagId=:tagId")
//    fun getImagesByTag(tagId: String): List<ImageData>

    @Transaction
    @Query("SELECT * FROM images INNER JOIN image_tag_join ON images.id=image_tag_join.imageId WHERE image_tag_join.tagId IN(:tagIds)")
    fun getImagesByTags(tagIds: List<String>): List<ImageData>

    @Transaction
    @Query("SELECT * FROM images INNER JOIN image_tag_join ON images.id=image_tag_join.imageId WHERE image_tag_join.tagId LIKE '%' || :tag || '%'")
    fun getImagesByTag(tag: String): List<ImageData>

    @Transaction
    @Query("SELECT * FROM tags INNER JOIN image_tag_join ON tags.id=image_tag_join.tagId WHERE image_tag_join.imageId=:imageId")
    fun getTagsForImage(imageId: String): List<TagData>
}