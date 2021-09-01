package com.webkeyz.mvp.auth.usecase

import com.cl.core.base.BaseUseCase
import com.cl.core.feature.data.AuthRepo
import com.webkeyz.mvp.auth.model.LoginResponse
import io.reactivex.Observable


class LoginUseCase : BaseUseCase<LoginResponse> {

    private val authRepo: AuthRepo

    private lateinit var email: String

    private lateinit var password: String


    constructor(authRepo: AuthRepo) {
        this.authRepo = authRepo
    }

    fun setParams(email: String, password: String) {
        this.email = email
        this.password = password
    }

    override fun buildUseCaseObservable(): Observable<LoginResponse> {
        return authRepo.doLogin(email, password)
    }
}