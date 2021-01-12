package inventory.machtwatch.bamsppob.data

import androidx.lifecycle.LiveData
import inventory.machtwatch.bamsppob.ApiLiveService
import inventory.machtwatch.bamsppob.base.ApiResponse
import inventory.machtwatch.bamsppob.base.RemoteResource
import inventory.machtwatch.bamsppob.base.Resource
import inventory.machtwatch.bamsppob.feature.model.ResponseListDenomination
import inventory.machtwatch.bamsppob.feature.model.ResponseValidationNumber
import inventory.machtwatch.bamsppob.feature.model.TestResponse
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val liveService: ApiLiveService) {
    companion object {
        private const val LIMIT = "check_number"
    }
    fun getUser(number: String): LiveData<Resource<ResponseValidationNumber>>{
        return object : RemoteResource<ResponseValidationNumber>(){
            override fun createCall(): LiveData<ApiResponse<ResponseValidationNumber>> {
                return liveService.getValidateNumber(number)
            }
        }.asLiveData()
    }

    fun getKlasemen(): LiveData<Resource<TestResponse>>{
        return object : RemoteResource<TestResponse>(){
            override fun createCall(): LiveData<ApiResponse<TestResponse>> {
                return liveService.getKlasemen()
            }
        }.asLiveData()
    }


    fun getListPulsa(operator: String): LiveData<Resource<ResponseListDenomination>>{
        return object : RemoteResource<ResponseListDenomination>(){
            override fun createCall(): LiveData<ApiResponse<ResponseListDenomination>> {
                return liveService.getListPulsa(operator)
            }
        }.asLiveData()
    }

}