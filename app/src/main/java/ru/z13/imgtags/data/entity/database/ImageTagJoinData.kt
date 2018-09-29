package ru.z13.imgtags.data.entity.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey

/**
 * Android Studio
 *
 * @author Yura Fedorchenko (www.android.z-13.ru)
 */
@Entity(tableName = "image_tag_join",
        primaryKeys = ["imageId", "tagId"],
        foreignKeys = [
            ForeignKey(entity = ImageData::class, parentColumns = ["id"], childColumns = ["imageId"]),
            ForeignKey(entity = TagData::class, parentColumns = ["id"], childColumns = ["tagId"])
        ])
data class ImageTagJoinData (
        @ColumnInfo(name = "imageId")
        var imageId: String,

        @ColumnInfo(name = "tagId")
        var tagId: String
){

    companion object {
        val DEFAULT: ImageTagJoinData = ImageTagJoinData("", "")
    }

    override fun toString(): String {
        return "$tagId | $imageId"
    }
}