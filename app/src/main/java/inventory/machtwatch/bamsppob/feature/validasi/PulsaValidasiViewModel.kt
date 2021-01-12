package inventory.machtwatch.bamsppob.feature.validasi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import inventory.machtwatch.bamsppob.base.BaseViewModel
import inventory.machtwatch.bamsppob.base.Resource
import inventory.machtwatch.bamsppob.data.RemoteDataSource
import inventory.machtwatch.bamsppob.feature.model.ResponseValidationNumber
import inventory.machtwatch.bamsppob.feature.model.TestResponse
import javax.inject.Inject

class PulsaValidasiViewModel @Inject constructor(private val data: RemoteDataSource) :
    BaseViewModel() {

    val triggerValidation = MutableLiveData<String>()

    val triggerKlasemen = MutableLiveData<Boolean>()


    val getValidation: LiveData<Resource<ResponseValidationNumber>>
        get() = Transformations.switchMap(triggerValidation) {
            data.getUser(it)
        }

    val getKlasemen: LiveData<Resource<TestResponse>>
        get() = Transformations.switchMap(triggerKlasemen) {
            data.getKlasemen()
        }
}