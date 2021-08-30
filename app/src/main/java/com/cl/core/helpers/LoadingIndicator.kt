package com.cl.core.helpers

import com.cl.core.annotation.LoadingType


class LoadingIndicator {

    var isLoading: Boolean = false

    @LoadingType
    private var loadingType: Int = 0

    constructor(@LoadingType loadingType: Int) {
        this.loadingType = loadingType
    }
}