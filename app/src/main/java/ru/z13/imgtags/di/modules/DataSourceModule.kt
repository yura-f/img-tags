package ru.z13.imgtags.di.modules

import dagger.Module
import dagger.Provides
import ru.z13.imgtags.data.database.Database
import ru.z13.imgtags.data.datasource.RoomDataSource
import javax.inject.Singleton

/**
 * @author Yura F (yura-f.github.io)
 */
@Module
class DataSourceModule {
    @Provides
    @Singleton
    fun provideRoomDataSource(database: Database): RoomDataSource {
        return RoomDataSource(database)
    }
}