package inventory.machtwatch.bamsppob.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import inventory.machtwatch.bamsppob.base.ProjectViewModelFactory
import inventory.machtwatch.bamsppob.base.ViewModelKey
import javax.inject.Singleton

@Module
abstract class BuilderModules {

    @Singleton
    @Binds
    abstract fun bindViewModelFactory(factory: ProjectViewModelFactory?): ViewModelProvider.Factory

//    @Binds
//    @IntoMap
//    @ViewModelKey(MainViewModel::class)
//    abstract fun bindMainViewModel(mainViewModel: MainViewModel) : ViewModel
//
//    @ContributesAndroidInjector
//    abstract fun contributeMainActivity(): MainActivity

}