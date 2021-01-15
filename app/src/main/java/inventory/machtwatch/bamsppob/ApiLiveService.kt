package inventory.machtwatch.bamsppob

import androidx.lifecycle.LiveData
import inventory.machtwatch.bamsppob.base.ApiResponse
import inventory.machtwatch.bamsppob.feature.model.CheckoutResponse
import inventory.machtwatch.bamsppob.feature.model.ResponseListDenomination
import inventory.machtwatch.bamsppob.feature.model.ResponseValidationNumber
import inventory.machtwatch.bamsppob.feature.model.TestResponse
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
    fun getPricePLn() : LiveData<ApiResponse<ResponseListDenomination>>
}