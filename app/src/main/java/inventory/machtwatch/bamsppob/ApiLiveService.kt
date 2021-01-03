package inventory.machtwatch.bamsppob

import androidx.lifecycle.LiveData
import inventory.machtwatch.bamsppob.base.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiLiveService {

    @GET("search/users")
    fun getUserResponse(@Query("q") query: String) : LiveData<ApiResponse<Any>>
}