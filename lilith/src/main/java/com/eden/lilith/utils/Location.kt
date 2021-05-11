package com.eden.lilith.utils

import android.Manifest
import android.location.Location
import android.location.LocationManager
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresPermission
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resumeWithException

@RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
fun ComponentActivity.getLocation(
    callback: (Location) -> Unit
) {

    val locationManager = this.getSystemService(LocationManager::class.java)

    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0L, 0F) {
        callback(it)
    }
}


@ExperimentalCoroutinesApi
fun FusedLocationProviderClient.locationFlow(
    locationRequest: LocationRequest = LocationRequest.create()
        .apply {
            interval = 500L
            fastestInterval = 1000L
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
) = callbackFlow<Location> {
    val callback = object : LocationCallback() {
        override fun onLocationResult(result: LocationResult?) {
            result ?: return
            try {
                result.locations.forEach {
                    offer(it)
                }
            } catch (e: Exception) {
            }
        }
    }
    requestLocationUpdates(locationRequest, callback, Looper.getMainLooper())
        .addOnFailureListener { e ->
            close(e)
        }
    awaitClose {
        removeLocationUpdates(callback)
    }
}

@ExperimentalCoroutinesApi
suspend fun FusedLocationProviderClient.currentLocation() =
    suspendCancellableCoroutine<Location> { cont ->

        val cancellationToken = CancellationTokenSource()

        getCurrentLocation(
            LocationRequest.PRIORITY_HIGH_ACCURACY,
            cancellationToken.token
        ).addOnCompleteListener { task ->
            if (task.isSuccessful)
                cont.resume(task.result, null)
            task.exception?.let { cont.resumeWithException(it) }
        }

        cont.invokeOnCancellation {
            cancellationToken.cancel()
        }
    }