package inventory.machtwatch.bamsppob.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import inventory.machtwatch.bamsppob.base.ProjectViewModelFactory
import inventory.machtwatch.bamsppob.base.ViewModelKey
import inventory.machtwatch.bamsppob.feature.checkout.CheckoutActivity
import inventory.machtwatch.bamsppob.feature.checkout.CheckoutViewModel
import inventory.machtwatch.bamsppob.feature.listdenom.ListDenomPulsaViewModel
import inventory.machtwatch.bamsppob.feature.listdenom.ListDenominationPulsa
import inventory.machtwatch.bamsppob.feature.validasi.PlnActivity
import inventory.machtwatch.bamsppob.feature.validasi.PlnValidasiViewModel
import inventory.machtwatch.bamsppob.feature.validasi.PulsaActivity
import inventory.machtwatch.bamsppob.feature.validasi.PulsaValidasiViewModel
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

    @Binds
    @IntoMap
    @ViewModelKey(PulsaValidasiViewModel::class)
    abstract fun bindMainViewModel(pulsaValidasiViewModel: PulsaValidasiViewModel) : ViewModel

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): PulsaActivity

    @Binds
    @IntoMap
    @ViewModelKey(ListDenomPulsaViewModel::class)
    abstract fun bindListDenomPulsaViewModel(pulsaValidasiViewModel: ListDenomPulsaViewModel) : ViewModel

    @ContributesAndroidInjector
    abstract fun contributeListDenominationPulsa(): ListDenominationPulsa

    @Binds
    @IntoMap
    @ViewModelKey(PlnValidasiViewModel::class)
    abstract fun bindPlnValidasiViewModel(pulsaValidasiViewModel: PlnValidasiViewModel) : ViewModel

    @ContributesAndroidInjector
    abstract fun contributePlnActivity(): PlnActivity

    @Binds
    @IntoMap
    @ViewModelKey(CheckoutViewModel::class)
    abstract fun bindCheckoutViewModel(checkoutViewModel: CheckoutViewModel) : ViewModel

    @ContributesAndroidInjector
    abstract fun contributeCheckoutActivity(): CheckoutActivity

}