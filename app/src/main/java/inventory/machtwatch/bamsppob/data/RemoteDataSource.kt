package inventory.machtwatch.bamsppob.data

import androidx.lifecycle.LiveData
import inventory.machtwatch.bamsppob.ApiLiveService
import inventory.machtwatch.bamsppob.base.ApiResponse
import inventory.machtwatch.bamsppob.base.RemoteResource
import inventory.machtwatch.bamsppob.base.Resource
import inventory.machtwatch.bamsppob.feature.model.*
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

    fun getListPulsa(operator: String): LiveData<Resource<ResponseListDenomination>>{
        return object : RemoteResource<ResponseListDenomination>(){
            override fun createCall(): LiveData<ApiResponse<ResponseListDenomination>> {
                return liveService.getListPulsa(operator)
            }
        }.asLiveData()
    }

    fun getCheckout(operator: String, phoneNumber: String): LiveData<Resource<CheckoutResponse>>{
        return object : RemoteResource<CheckoutResponse>(){
            override fun createCall(): LiveData<ApiResponse<CheckoutResponse>> {
                return liveService.getCheckout(operator, phoneNumber)
            }
        }.asLiveData()
    }

    fun getPLNListPrice(): LiveData<Resource<ResponseListDenomination>>{
        return object : RemoteResource<ResponseListDenomination>(){
            override fun createCall(): LiveData<ApiResponse<ResponseListDenomination>> {
                return liveService.getPricePLn()
            }
        }.asLiveData()
    }

    fun getHistories(): LiveData<Resource<ResponseHistoryTransaction>>{
        return object : RemoteResource<ResponseHistoryTransaction>(){
            override fun createCall(): LiveData<ApiResponse<ResponseHistoryTransaction>> {
                return liveService.getHistory()
            }
        }.asLiveData()
    }

    fun getListPaketData(operator_data: String): LiveData<Resource<PaketDataResponse>>{
        return object : RemoteResource<PaketDataResponse>(){
            override fun createCall(): LiveData<ApiResponse<PaketDataResponse>> {
                return liveService.getListPaketData(operator_data)
            }
        }.asLiveData()
    }

    fun getSaldo(): LiveData<Resource<SaldoResponse>>{
        return object : RemoteResource<SaldoResponse>(){
            override fun createCall(): LiveData<ApiResponse<SaldoResponse>> {
                return liveService.getBalance()
            }
        }.asLiveData()
    }

    fun getInquiryAbodemen(noMeter: String): LiveData<Resource<PlnPostPaidResponse>>{
        return object : RemoteResource<PlnPostPaidResponse>(){
            override fun createCall(): LiveData<ApiResponse<PlnPostPaidResponse>> {
                return liveService.getInquiryPlnPostPaid(noMeter)
            }
        }.asLiveData()
    }

    fun getInquiryCheckout(refId: String, trId: String): LiveData<Resource<PlnPostPaidCheckout>>{
        return object : RemoteResource<PlnPostPaidCheckout>(){
            override fun createCall(): LiveData<ApiResponse<PlnPostPaidCheckout>> {
                return liveService.getCheckoutPLNPostPaid(refId, trId)
            }
        }.asLiveData()
    }
}