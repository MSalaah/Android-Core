package com.cl.core.network

import dagger.Component
import retrofit2.Retrofit


@ForApplication
@Component(modules = [NetModule::class])
interface NetComponent {

    companion object {
        val BaseUrl = "https://api.github.com/"
    }

    val retrofit: Retrofit

    object Initializer {
        private var component: NetComponent? = null
        fun buildComponent(): NetComponent? {
            component = DaggerNetComponent.builder()
                .netModule(NetModule(BaseUrl))
                .build()
            return component
        }
    }
}
