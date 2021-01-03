package inventory.machtwatch.bamsppob.base

import androidx.lifecycle.LiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean

class LiveDataCallAdapter<R>(private val mResponseType: Type, private val mGson: Gson) : CallAdapter<R, LiveData<ApiResponse<R>>> {

    override fun responseType(): Type {
        return TypeToken.get(ResponseBody::class.java).type
    }

    override fun adapt(call: Call<R>): LiveData<ApiResponse<R>> {
        return object : LiveData<ApiResponse<R>>() {

            internal var mStarted = AtomicBoolean(false)

            override fun onActive() {
                super.onActive()
                if (mStarted.compareAndSet(false, true)) {
                    val apiResponse = ApiResponse<R>()
                    call.enqueue(object : Callback<R> {
                        override fun onResponse(call: Call<R>, response: Response<R>) {
                            if (response.isSuccessful) {
                                apiResponse.isSuccessful = true
                                try {
                                    val responseBody = response.body() as ResponseBody?
                                    val message = responseBody?.string()
                                    val messageAsObject: R

                                    messageAsObject = mGson.fromJson(message, mResponseType)

                                    apiResponse.body = messageAsObject

                                } catch (e: Exception) {
                                    apiResponse.isSuccessful = false
                                    e.printStackTrace()
                                }

                                postValue(apiResponse)
                            } else {
                                apiResponse.isSuccessful = false

                                try {
                                    val jsonObject = JSONObject(response.errorBody()!!.string())
                                    val meta = jsonObject.getJSONObject("meta")
                                    val code = meta.getInt("code")
                                    val message = meta.getString("message")

                                    apiResponse.errorMessage = message
                                    apiResponse.code = code

                                } catch (e: Exception) {
                                    apiResponse.errorMessage = response.message()
                                    e.printStackTrace()
                                }

                                apiResponse.code = response.code()
                                postValue(apiResponse)
                            }

                        }

                        override fun onFailure(call: Call<R>, t: Throwable) {
                            apiResponse.isSuccessful = false
                            apiResponse.errorMessage = t.toString()
                            postValue(apiResponse)
                        }
                    })
                }
            }
        }
    }
}
