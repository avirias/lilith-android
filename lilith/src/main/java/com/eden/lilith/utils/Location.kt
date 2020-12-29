package com.eden.lilith.utils

import android.Manifest
import android.annotation.SuppressLint
import android.location.Location
import android.location.LocationManager
import com.eden.lilith.LilithActivity

@SuppressLint("MissingPermission")
fun LilithActivity.getLocation(
    callback: (Location) -> Unit
) {

    val locationManager = this.getSystemService(LocationManager::class.java)

    val p = listOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
    val handler: Handler = object : Handler {
        override fun onPermissionDenied(permissionDenied: List<String>) {
            println("Permissions denied were : $permissionDenied")
        }

    }
    
    getPermission(p, handler) {
        locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER, 0L, 0F
        ) {
            callback(it)
        }
    }
}

// TODO: 19/12/20  
fun LilithActivity.getLastLocation(
    callback: (Location) -> Unit
) {

}