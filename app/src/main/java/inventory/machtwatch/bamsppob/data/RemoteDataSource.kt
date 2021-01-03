package inventory.machtwatch.bamsppob.data

import androidx.lifecycle.LiveData
import inventory.machtwatch.bamsppob.ApiLiveService
import inventory.machtwatch.bamsppob.base.ApiResponse
import inventory.machtwatch.bamsppob.base.RemoteResource
import inventory.machtwatch.bamsppob.base.Resource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val liveService: ApiLiveService) {

    companion object {
        private const val LIMIT = 10
    }

    fun getUser(user: String, limit: Int = LIMIT): LiveData<Resource<Any>>{
        return object : RemoteResource<Any>(){
            override fun createCall(): LiveData<ApiResponse<Any>> {
                return liveService.getUserResponse(user)
            }
        }.asLiveData()
    }

}