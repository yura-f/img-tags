package ru.z13.imgtags.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import ru.z13.imgtags.data.database.dao.*
import ru.z13.imgtags.data.entity.database.*

/**
 * Android Studio
 *
 * @author Yura Fedorchenko (www.android.z-13.ru)
 */
@Database(entities = [
    ImageData::class,
    TagData::class,
    ImageTagJoinData::class
],
        version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {
    abstract fun imageDao(): ImageDao
    abstract fun tagDao(): TagDao
    abstract fun imageTagJoinDao(): ImageTagJoinDao
}