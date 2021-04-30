package com.eden.lilith.utils

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresPermission


@RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
fun ComponentActivity.ifOnline(
    callback: () -> Unit
): LConnectivityManger {

    val result: Boolean?

    val connectivityManager = getSystemService(ConnectivityManager::class.java)
    val networkCapabilities = connectivityManager.activeNetwork
    val actNw =
        connectivityManager.getNetworkCapabilities(networkCapabilities)
    result = if (actNw == null) false
    else
        when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    result.run {
        if (this) callback.invoke()
    }
    return LConnectivityManger(result)
}

class LConnectivityManger(
    private val isNetworkAvailable: Boolean
) {
    fun otherWise(elseCallback: () -> Unit) {
        if (!isNetworkAvailable)
            elseCallback.invoke()
    }
}

