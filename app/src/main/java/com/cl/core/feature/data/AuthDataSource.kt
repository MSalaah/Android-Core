package com.cl.core.feature.data

import com.webkeyz.mvp.auth.model.LoginResponse
import io.reactivex.Observable
import retrofit2.Retrofit

class AuthDataSource internal constructor(retrofit: Retrofit) : AuthRepo {

    private val authAPI: AuthAPI

    override fun doLogin(email: String, password: String): Observable<LoginResponse?>? {
        return authAPI.doLogin(email, password)
    }

    init {
        authAPI = retrofit.create(AuthAPI::class.java)
    }
}