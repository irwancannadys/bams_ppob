package inventory.machtwatch.bamsppob

import androidx.lifecycle.LiveData
import inventory.machtwatch.bamsppob.base.ApiResponse
import inventory.machtwatch.bamsppob.feature.model.*
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiLiveService {

//    https://skripsi.paguponku.com/?api=check_number&phone_number=085642020047

    @GET("/check-number/")
    fun getValidateNumber(@Query("phone_number") query: String): LiveData<ApiResponse<ResponseValidationNumber>>

    @GET("leagues")
    fun getKlasemen(): LiveData<ApiResponse<TestResponse>>

    @GET("/operator-prices/")
    fun getListPulsa(@Query("operator") operator: String): LiveData<ApiResponse<ResponseListDenomination>>

    @GET("top-up/")
    fun getCheckout(
        @Query("pulsa_code") pulsaCode: String,
        @Query("phone_number") phoneNumber: String
    ): LiveData<ApiResponse<CheckoutResponse>>

    @GET("pln-prices/")
    fun getPricePLn(): LiveData<ApiResponse<ResponseListDenomination>>

    @GET("histories/")
    fun getHistory(): LiveData<ApiResponse<ResponseHistoryTransaction>>

    @GET("pln-postpaid/")
    fun getInquiryPlnPostPaid(@Query("no_meter") noMeter: String): LiveData<ApiResponse<PlnPostPaidResponse>>

    @GET("pln-postpaid-pay/")
    fun getCheckoutPLNPostPaid(
        @Query("ref_id") refId: String,
        @Query("tr_id") trId: String
    ): LiveData<ApiResponse<PlnPostPaidCheckout>>

    @GET("data-prices/")
    fun getListPaketData(@Query("operator_data") operatorData: String): LiveData<ApiResponse<PaketDataResponse>>

    @GET("balance")
    fun getBalance() : LiveData<ApiResponse<SaldoResponse>>
}