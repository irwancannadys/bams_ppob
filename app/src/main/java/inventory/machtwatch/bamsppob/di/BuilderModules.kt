package inventory.machtwatch.bamsppob.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import inventory.machtwatch.bamsppob.MainActivity
import inventory.machtwatch.bamsppob.base.ProjectViewModelFactory
import inventory.machtwatch.bamsppob.base.ViewModelKey
import inventory.machtwatch.bamsppob.feature.checkout.CheckoutActivity
import inventory.machtwatch.bamsppob.feature.checkout.CheckoutViewModel
import inventory.machtwatch.bamsppob.feature.history.HistoryActivity
import inventory.machtwatch.bamsppob.feature.history.HistoryViewModel
import inventory.machtwatch.bamsppob.feature.listdenom.ListDenomPaketData
import inventory.machtwatch.bamsppob.feature.listdenom.ListDenomPulsaViewModel
import inventory.machtwatch.bamsppob.feature.listdenom.ListDenominationPulsa
import inventory.machtwatch.bamsppob.feature.validasi.*
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
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @Binds
    @IntoMap
    @ViewModelKey(PulsaValidasiViewModel::class)
    abstract fun bindMainViewModel(pulsaValidasiViewModel: PulsaValidasiViewModel) : ViewModel

    @ContributesAndroidInjector
    abstract fun contributePulsaActivity(): PulsaActivity

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

    @Binds
    @IntoMap
    @ViewModelKey(HistoryViewModel::class)
    abstract fun bindHistoryViewModel(historyViewModel: HistoryViewModel) : ViewModel

    @ContributesAndroidInjector
    abstract fun contributeHistoryActivity(): HistoryActivity

    @ContributesAndroidInjector
    abstract fun contributePaketDataActivity(): PaketDataActivity

    @ContributesAndroidInjector
    abstract fun contributePlnPostPaid(): PlnPostPaidActivity

    @ContributesAndroidInjector
    abstract fun contributeListDenomPaketData(): ListDenomPaketData

    @ContributesAndroidInjector
    abstract fun contributePlnPostPaidInquiryActivity(): PlnPostPaidInquiryActivity

}