package com.cl.core.network

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

@Module
@ForApplication
internal class NetModule(private val mBaseUrl: String) {

    @Provides
    fun provideOkHttp(): OkHttpClient {
        var httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor = httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val headers = HashMap<String, String>()

//        headers["Authorization"] ="Bearer"

        val headersInterceptor = HeadersInterceptor(headers)

        return OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(headersInterceptor)
            .addInterceptor(httpLoggingInterceptor).build()
    }

    @Provides
    fun provideRxErrorHandlerCallAdapter(): RxErrorHandlingCallAdapterFactory {
        return RxErrorHandlingCallAdapterFactory()
    }

    @Provides
    fun provideBaseRetrofit(okHttpClient: OkHttpClient, adapter: RxErrorHandlingCallAdapterFactory): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(mBaseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(adapter)
            .build()
    }
}
