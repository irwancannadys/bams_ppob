package inventory.machtwatch.bamsppob.feature.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import inventory.machtwatch.bamsppob.base.BaseViewModel
import inventory.machtwatch.bamsppob.base.Resource
import inventory.machtwatch.bamsppob.data.RemoteDataSource
import inventory.machtwatch.bamsppob.feature.model.ResponseHistoryTransaction
import inventory.machtwatch.bamsppob.feature.model.SaldoResponse
import javax.inject.Inject

class HistoryViewModel @Inject constructor(val data: RemoteDataSource) : BaseViewModel() {

    val triggerHistoryList = MutableLiveData<Boolean>()

    val triggerSaldo = MutableLiveData<Boolean>()

    val getListHistory: LiveData<Resource<ResponseHistoryTransaction>>
        get() = Transformations.switchMap(triggerHistoryList) {
            data.getHistories()
        }

    val getSaldo: LiveData<Resource<SaldoResponse>>
        get() = Transformations.switchMap(triggerSaldo) {
            data.getSaldo()
        }
}