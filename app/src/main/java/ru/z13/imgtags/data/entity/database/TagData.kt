package ru.z13.imgtags.data.entity.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Android Studio
 *
 * @author Yura Fedorchenko (www.android.z-13.ru)
 */
@Entity(tableName = "tags")
data class TagData (
        @PrimaryKey
        @ColumnInfo(name = "id")
        var name: String
){

    companion object {
        val DEFAULT: TagData = TagData("")
    }

    override fun toString(): String {
        return name
    }
}