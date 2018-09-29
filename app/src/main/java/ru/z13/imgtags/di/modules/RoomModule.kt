package ru.z13.imgtags.di.modules

import android.app.Application
import android.arch.persistence.room.Room
import dagger.Module
import dagger.Provides
import ru.z13.imgtags.data.database.Database
import ru.z13.imgtags.data.datasource.RoomDataSource
import javax.inject.Singleton

/**
 * Android Studio
 *
 * @author Yura Fedorchenko (www.android.z-13.ru)
 */
@Module
class RoomModule {

    @Provides
    @Singleton
    fun provideRoom(application: Application): Database {
        /**
         * The fallbackToDestructiveMigration says that if there is no migration class provided
         * with an incrementation of the db version, it will drop any table and recreate the database.
         * It allows to avoid the crash while incrementing the database version without migration strategy
         * (otherwise than be ok to loose all the data).
         */
        return Room.databaseBuilder(application, Database::class.java, RoomDataSource.DATABASE_NAME)
                //.fallbackToDestructiveMigration()
                .build()
    }
}