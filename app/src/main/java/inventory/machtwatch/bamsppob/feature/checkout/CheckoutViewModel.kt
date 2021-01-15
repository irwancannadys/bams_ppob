package inventory.machtwatch.bamsppob.feature.checkout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import inventory.machtwatch.bamsppob.base.BaseViewModel
import inventory.machtwatch.bamsppob.base.Resource
import inventory.machtwatch.bamsppob.data.RemoteDataSource
import inventory.machtwatch.bamsppob.feature.model.CheckoutResponse
import javax.inject.Inject

class CheckoutViewModel @Inject constructor(private val data: RemoteDataSource) : BaseViewModel() {

    val triggerCheckout = MutableLiveData<Boolean>()

    fun getCheckout(pulsaCode: String, phoneNumber: String) : LiveData<Resource<CheckoutResponse>>{
        return Transformations.switchMap(triggerCheckout) {
            data.getCheckout(pulsaCode, phoneNumber)
        }
    }
}