package ru.z13.imgtags.di.modules

import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.z13.imgtags.subnavigation.MainRouter
import javax.inject.Singleton

/**
 * Android Studio
 *
 * @author Yura Fedorchenko (www.android.z-13.ru)
 */
@Module
class NavigationModule {
    private val cicerone: Cicerone<MainRouter> = Cicerone.create(MainRouter())

    @Provides
    @Singleton
    internal fun provideRouter(): MainRouter {
        return cicerone.router
    }

    @Provides
    @Singleton
    internal fun provideNavigatorHolder(): NavigatorHolder {
        return cicerone.navigatorHolder
    }

}