package ru.z13.imgtags.data.entity.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Android Studio
 *
 * @author Yura Fedorchenko (www.android.z-13.ru)
 */
@Entity(tableName = "images")
data class ImageData (
        @PrimaryKey
        @ColumnInfo(name = "id")
        var path: String,

        @ColumnInfo(name = "tags")
        var tags: String
){
    companion object {
        val DEFAULT: ImageData = ImageData( "", "")
    }

    override fun toString(): String {
        return path
    }
}