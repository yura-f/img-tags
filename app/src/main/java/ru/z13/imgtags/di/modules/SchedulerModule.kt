package ru.z13.imgtags.di.modules

import dagger.Module
import dagger.Provides
import ru.z13.imgtags.mvp.utils.AppSchedulerProvider
import ru.z13.imgtags.mvp.utils.SchedulerProvider


/**
 * Android Studio
 *
 * @author Yura Fedorchenko (www.android.z-13.ru)
 */
@Module
class SchedulerModule {
    @Provides
    fun provideSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }
}