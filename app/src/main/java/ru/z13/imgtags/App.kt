package ru.z13.imgtags

import android.support.multidex.MultiDexApplication
import com.facebook.stetho.Stetho
import com.squareup.leakcanary.LeakCanary
import ru.z13.imgtags.di.components.ApplicationComponent
import ru.z13.imgtags.di.components.DaggerApplicationComponent
import ru.z13.imgtags.di.modules.ApplicationModule
import timber.log.Timber

class App : MultiDexApplication() {

    companion object {
        @JvmStatic lateinit var appComponent: ApplicationComponent

//        fun getAppComponent(): ApplicationComponent {
//            return appComponent
//        }
//
//        @VisibleForTesting
//        fun setAppComponent(@NonNull component: ApplicationComponent) {
//            appComponent = component
//        }
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Stetho.initializeWithDefaults(this)
            LeakCanary.install(this)
        }

        initDi()
    }

    private fun initDi() {
        appComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }


}
