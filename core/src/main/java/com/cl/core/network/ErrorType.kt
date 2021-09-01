package com.cl.core.network


import androidx.annotation.IntDef

@IntDef(ErrorType.NETWORK, ErrorType.HTTP, ErrorType.UNEXPECTED)
@Retention(AnnotationRetention.RUNTIME)
annotation class ErrorType {
    companion object {
        const val NETWORK = 1
        const val HTTP = 2
        const val UNEXPECTED = 3
    }
}
