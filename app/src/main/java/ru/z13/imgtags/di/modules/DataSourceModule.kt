package ru.z13.imgtags.di.modules

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
class DataSourceModule {
    @Provides
    @Singleton
    fun provideRoomDataSource(database: Database): RoomDataSource {
        return RoomDataSource(database)
    }
}