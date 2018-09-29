package ru.z13.imgtags.di.components

import dagger.Component
import ru.z13.imgtags.di.modules.*
import ru.z13.imgtags.mvp.presenters.*
import ru.z13.imgtags.ui.activities.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApplicationModule::class,
    NavigationModule::class,
    RepositoryModule::class,
    DataSourceModule::class,
    RoomModule::class
])
interface ApplicationComponent {
    fun inject(activity: MainActivity)
    fun inject(presenter: MainActivityPresenter)
    fun inject(presenter: HomePresenter)
}
