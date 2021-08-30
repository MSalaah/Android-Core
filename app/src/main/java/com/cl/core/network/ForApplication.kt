package com.cl.core.network


import javax.inject.Qualifier
import javax.inject.Singleton

@Singleton
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
internal annotation class ForApplication
