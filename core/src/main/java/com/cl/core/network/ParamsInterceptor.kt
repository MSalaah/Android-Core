package com.cl.core.network

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

internal class ParamsInterceptor(private val params: Map<String, String>) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val newUrlBuilder = original.url().newBuilder()
        for ((key, value) in params) {
            newUrlBuilder.addQueryParameter(key, value)
        }

        return chain.proceed(original.newBuilder().url(newUrlBuilder.build()).build())
    }

}
