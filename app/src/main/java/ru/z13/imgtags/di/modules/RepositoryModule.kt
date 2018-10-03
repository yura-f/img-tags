package ru.z13.imgtags.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.z13.imgtags.data.AppRepository
import ru.z13.imgtags.data.Repository
import ru.z13.imgtags.data.datasource.RoomDataSource
import ru.z13.imgtags.domain.*
import ru.z13.imgtags.mvp.utils.SchedulerProvider
import javax.inject.Singleton

/**
 * Android Studio
 *
 * @author Yura Fedorchenko (www.android.z-13.ru)
 */
@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideDomainEvents(schedulerProvider: SchedulerProvider):DomainEvents{
        return AppDomainEvents(schedulerProvider)
    }

    @Provides
    @Singleton
    fun provideAppDomainState(domainEvents: DomainEvents): AppDomainState {
        return AppDomainState(domainEvents)
    }

    @Provides
    @Singleton
    fun bindDomainState(appDomainState: AppDomainState): DomainState = appDomainState

    @Provides
    @Singleton
    fun bindMutableDomainState(appDomainState: AppDomainState): MutableDomainState = appDomainState

    @Provides
    @Singleton
    fun provideRepository(context: Context,
                          roomDataSource: RoomDataSource,
                          domainState: MutableDomainState,
                          domainEvents: DomainEvents):Repository{
        return AppRepository(context,
                roomDataSource,
                domainState,
                domainEvents)
    }
}