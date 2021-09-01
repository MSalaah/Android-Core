package com.cl.core.feature.data

import com.cl.core.feature.LoginViewModel
import com.cl.core.network.NetComponent
import dagger.Component

@AuthScope
@Component(dependencies = [NetComponent::class], modules = [AuthRepoModule::class])
interface AuthRepoComponent {

    fun inject(loginViewModel: LoginViewModel?)

    object Initializer {
        private var component: AuthRepoComponent? = null
        fun buildComponent(): AuthRepoComponent? {
            component = DaggerAuthRepoComponent.builder()
                .netComponent(NetComponent.Initializer.buildComponent())
                .authRepoModule(AuthRepoModule()).build()
            return component
        }
    }
}