package com.cl.core.network

import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.lang.reflect.Type

class RxErrorHandlingCallAdapterFactory : CallAdapter.Factory() {

    private val rxJava2CallAdapterFactory: RxJava2CallAdapterFactory

    init {
        rxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()
    }

    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {
        return RxCallAdapterWrapper(rxJava2CallAdapterFactory.get(returnType, annotations, retrofit)!!)
    }
}
