package ru.z13.imgtags.di.components

import dagger.Component
import ru.z13.imgtags.di.modules.*
import ru.z13.imgtags.ui.activities.MainActivity
import ru.z13.imgtags.ui.fragments.HomeFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApplicationModule::class,
    NavigationModule::class,
    RepositoryModule::class,
    DataSourceModule::class,
    RoomModule::class,
    SchedulerModule::class
])
interface ApplicationComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: HomeFragment)
}
