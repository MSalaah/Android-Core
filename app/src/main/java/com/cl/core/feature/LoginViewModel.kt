package com.cl.core.feature

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cl.core.R
import com.cl.core.annotation.LoadingType
import com.cl.core.annotation.UseCase
import com.cl.core.base.BaseFetchObserver
import com.cl.core.base.BaseViewModel
import com.cl.core.feature.data.AuthRepoComponent
import com.cl.core.helpers.LoadingIndicator
import com.cl.core.helpers.SingleLiveEvent
import com.cl.core.utils.Constants
import com.webkeyz.mvp.auth.model.LoginResponse
import com.webkeyz.mvp.auth.usecase.LoginUseCase
import javax.inject.Inject


class LoginViewModel : BaseViewModel() {

    private val loginLiveData = MutableLiveData<LoginResponse>()

    @UseCase(Constants.UseCase.loginUseCase)
    @Inject
    lateinit var loginUseCase: LoginUseCase

    private val loadingIndicator = LoadingIndicator(LoadingType.PROGRESS)

    private val _navigateToHome = SingleLiveEvent<Any>()
    val navigateToHome: LiveData<Any>
        get() = _navigateToHome

    init {
        AuthRepoComponent.Initializer.buildComponent()?.inject(this)
    }

    override fun start() {
        super.start()
        loginUseCase.setParams("email", "password")
        loginUseCase.execute(object :
            BaseFetchObserver<LoginResponse>(this, R.id.loginUseCase, loadingIndicator) {
            override fun onNext(loginResponse: LoginResponse) {
                loginLiveData.value = loginResponse
                print("Do Stuff")
            }
        })
    }
}