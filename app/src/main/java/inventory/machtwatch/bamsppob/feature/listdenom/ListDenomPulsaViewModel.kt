package inventory.machtwatch.bamsppob.feature.listdenom

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import inventory.machtwatch.bamsppob.base.BaseViewModel
import inventory.machtwatch.bamsppob.base.Resource
import inventory.machtwatch.bamsppob.data.RemoteDataSource
import inventory.machtwatch.bamsppob.feature.model.PaketDataResponse
import inventory.machtwatch.bamsppob.feature.model.ResponseListDenomination
import javax.inject.Inject

class ListDenomPulsaViewModel @Inject constructor(private val data: RemoteDataSource) :
    BaseViewModel() {

    val triggerListPulsa = MutableLiveData<String>()

    val triggerListPaketData = MutableLiveData<String>()

    val getListPulsa: LiveData<Resource<ResponseListDenomination>>
        get() = Transformations.switchMap(triggerListPulsa) {
            data.getListPulsa(it)
        }

    val getListPaketData: LiveData<Resource<PaketDataResponse>>
        get() = Transformations.switchMap(triggerListPaketData) {
            data.getListPaketData(it)
        }
}