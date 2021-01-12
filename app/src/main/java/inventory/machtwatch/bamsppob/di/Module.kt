package inventory.machtwatch.bamsppob.di

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import dagger.Provides
import inventory.machtwatch.bamsppob.ApiLiveService
import inventory.machtwatch.bamsppob.base.App
import inventory.machtwatch.bamsppob.base.LiveDataCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [BuilderModules::class])
class Module {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    internal fun provideApp(application: Application): App {
        return application as App
    }

    @Provides
    fun provideLiveRest(
        callAdapterFactory: LiveDataCallAdapterFactory
    ): ApiLiveService {
        return Retrofit.Builder()
            .baseUrl("https://skripsi.paguponku.com")
            .client(provideOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(callAdapterFactory)
            .build()
            .create(ApiLiveService::class.java)
    }

    private fun provideOkHttpClient() : OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.writeTimeout(1, TimeUnit.MINUTES)
        builder.readTimeout(1, TimeUnit.MINUTES)
        builder.connectTimeout(1, TimeUnit.MINUTES)

        return builder.build()
    }

    @Singleton
    @Provides
    internal fun provideLiveDataCallAdapterFactory(gson: Gson): LiveDataCallAdapterFactory {
        return LiveDataCallAdapterFactory(gson)
    }

    @Provides
    fun provideGson(): Gson = Gson()

}