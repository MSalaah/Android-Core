package com.cl.core.feature.data

import com.cl.core.feature.data.AuthScope
import retrofit2.Retrofit
import com.cl.core.feature.data.AuthRepo
import com.cl.core.feature.data.AuthDataSource
import com.webkeyz.mvp.auth.usecase.LoginUseCase
import dagger.Module
import dagger.Provides

@Module
internal class AuthRepoModule {
    @Provides
    @AuthScope
    fun provideAuthRepo(retrofit: Retrofit?): AuthRepo {
        return AuthDataSource(retrofit!!)
    }

    @Provides
    @AuthScope
    fun provideLoginCase(authRepo: AuthRepo?): LoginUseCase {
        return LoginUseCase(authRepo!!)
    }
}