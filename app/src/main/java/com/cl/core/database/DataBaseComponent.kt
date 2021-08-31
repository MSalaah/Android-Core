//package com.cl.core.database
//
//import androidx.room.Database
//import com.cl.core.network.DaggerNetComponent
//import com.cl.core.network.ForApplication
//import com.cl.core.network.NetComponent
//import com.cl.core.network.NetModule
//import dagger.Component
//import retrofit2.Retrofit
//
//
//@ForApplication
//@Component(modules = [DataBaseModule::class])
//interface DataBaseComponent {
//
//    companion object {
//        val BaseUrl = "https://api.github.com/"
//    }
//
//    val retrofit: Retrofit
//
////    object Initializer {
////        private var component: DataBaseComponent? = null
////        fun buildComponent(): DataBaseComponent? {
////            component = DaggerNetComponent.builder()
////                .data(NetModule(BaseUrl))
////                .build()
////            return component
////        }
////    }
//}