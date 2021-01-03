package inventory.machtwatch.bamsppob.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import inventory.machtwatch.bamsppob.base.App
import javax.inject.Singleton

@Singleton
@Component(modules = [
    Module::class,
    AndroidSupportInjectionModule::class,
    BuilderModules::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(app: App)

}