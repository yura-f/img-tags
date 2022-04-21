package ru.z13.imgtags.di.modules

import dagger.Module
import dagger.Provides
import ru.z13.imgtags.mvp.utils.AppSchedulerProvider
import ru.z13.imgtags.mvp.utils.SchedulerProvider


/**
 * @author Yura F (yura-f.github.io)
 */
@Module
class SchedulerModule {
    @Provides
    fun provideSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }
}