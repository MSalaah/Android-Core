package com.cl.core.feature.data

import com.webkeyz.mvp.auth.model.LoginResponse
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthAPI {

    @POST("login")
    @FormUrlEncoded
    fun doLogin(@Field("email") email: String?, @Field("password") password: String? ): Observable<LoginResponse?>?
}