package inventory.machtwatch.bamsppob.feature.validasi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import inventory.machtwatch.bamsppob.base.BaseViewModel
import inventory.machtwatch.bamsppob.base.Resource
import inventory.machtwatch.bamsppob.data.RemoteDataSource
import inventory.machtwatch.bamsppob.feature.model.ResponseListDenomination
import javax.inject.Inject

class PlnValidasiViewModel @Inject constructor(private val data: RemoteDataSource) :
    BaseViewModel() {

    val triggerPLnList = MutableLiveData<Boolean>()

    val getListPln: LiveData<Resource<ResponseListDenomination>>
        get() = Transformations.switchMap(triggerPLnList) {
            data.getPLNListPrice()
        }
}