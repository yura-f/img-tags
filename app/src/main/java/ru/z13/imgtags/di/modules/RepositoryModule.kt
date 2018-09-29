package ru.z13.imgtags.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.z13.imgtags.data.AppRepository
import ru.z13.imgtags.data.Repository
import ru.z13.imgtags.data.datasource.RoomDataSource
import ru.z13.imgtags.domain.AppDomainEvents
import ru.z13.imgtags.domain.AppDomainState
import ru.z13.imgtags.domain.DomainEvents
import ru.z13.imgtags.domain.DomainState
import javax.inject.Singleton

/**
 * Android Studio
 *
 * @author Yura Fedorchenko (www.android.z-13.ru)
 */
@Module
class RepositoryModule {

    private val domainEvents: AppDomainEvents
    private val domainState: AppDomainState

    @Provides
    @Singleton
    fun provideRepository(context: Context,
                          roomDataSource: RoomDataSource):Repository{
        return AppRepository(context,
                roomDataSource,
                domainState,
                domainEvents)
    }

    @Provides
    @Singleton
    fun provideDomainState():DomainState{
        return domainState
    }

    @Provides
    @Singleton
    fun provideDomainEvents():DomainEvents{
        return domainEvents
    }

    init {
        domainEvents = AppDomainEvents()
        domainState = AppDomainState(domainEvents)
    }
}