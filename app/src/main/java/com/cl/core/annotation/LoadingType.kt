package com.cl.core.annotation

import androidx.annotation.IntDef


@IntDef(
    LoadingType.SHIMMER,
    LoadingType.PROGRESS
)
@Retention(AnnotationRetention.RUNTIME)
annotation class LoadingType {
    companion object {
        const val SHIMMER = 1
        const val PROGRESS = 2
    }
}
