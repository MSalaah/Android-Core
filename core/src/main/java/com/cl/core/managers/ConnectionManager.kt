package com.cl.core.managers

import android.content.Context
import android.net.ConnectivityManager

object ConnectionManager {
    private var connectivityManager: ConnectivityManager? = null

    fun initialize(context: Context) {
        if (connectivityManager == null) {
            connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        }
    }

    fun isConnected(): Boolean {
        val ni = connectivityManager!!.activeNetworkInfo
        return ni != null && ni.isConnected
    }
}
