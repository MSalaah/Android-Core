package com.cl.core.network

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

internal class HeadersInterceptor(private val headers: Map<String, String>?) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val requestBuilder = original.newBuilder()
        if (headers != null) {
            for ((key, value) in headers) {
                requestBuilder.addHeader(key, value)
            }
        }
        return chain.proceed(requestBuilder.build())
    }
}
