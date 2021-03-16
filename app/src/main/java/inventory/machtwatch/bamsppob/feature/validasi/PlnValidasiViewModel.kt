package inventory.machtwatch.bamsppob.feature.validasi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import inventory.machtwatch.bamsppob.base.BaseViewModel
import inventory.machtwatch.bamsppob.base.Resource
import inventory.machtwatch.bamsppob.data.RemoteDataSource
import inventory.machtwatch.bamsppob.feature.model.PlnPostPaidResponse
import inventory.machtwatch.bamsppob.feature.model.ResponseListDenomination
import javax.inject.Inject

class PlnValidasiViewModel @Inject constructor(private val data: RemoteDataSource) :
    BaseViewModel() {

    val triggerPLnList = MutableLiveData<Boolean>()

    val triggerValidationPlnAbudemen = MutableLiveData<String>()

    val getListPln: LiveData<Resource<ResponseListDenomination>>
        get() = Transformations.switchMap(triggerPLnList) {
            data.getPLNListPrice()
        }

    val getInquiryAbodemen: LiveData<Resource<PlnPostPaidResponse>>
        get() = Transformations.switchMap(triggerValidationPlnAbudemen) {
            data.getInquiryAbodemen(it)
        }
}