package inventory.machtwatch.bamsppob.feature.checkout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import inventory.machtwatch.bamsppob.base.BaseViewModel
import inventory.machtwatch.bamsppob.base.Resource
import inventory.machtwatch.bamsppob.data.RemoteDataSource
import inventory.machtwatch.bamsppob.feature.model.CheckoutResponse
import inventory.machtwatch.bamsppob.feature.model.PlnPostPaidCheckout
import javax.inject.Inject

class CheckoutViewModel @Inject constructor(private val data: RemoteDataSource) : BaseViewModel() {

    val triggerCheckout = MutableLiveData<Boolean>()

    val triggerCheckoutInquiry = MutableLiveData<Boolean>()


    fun getCheckout(pulsaCode: String, phoneNumber: String) : LiveData<Resource<CheckoutResponse>>{
        return Transformations.switchMap(triggerCheckout) {
            data.getCheckout(pulsaCode, phoneNumber)
        }
    }

    fun checkoutPostPaid(refId: String, trId: String) : LiveData<Resource<PlnPostPaidCheckout>>{
        return Transformations.switchMap(triggerCheckoutInquiry) {
            data.getInquiryCheckout(refId, trId)
        }
    }
}