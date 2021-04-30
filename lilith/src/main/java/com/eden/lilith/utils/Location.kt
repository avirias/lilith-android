package com.eden.lilith.utils

import android.Manifest
import android.location.Location
import android.location.LocationManager
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresPermission

@RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
fun ComponentActivity.getLocation(
    callback: (Location) -> Unit
) {

    val locationManager = this.getSystemService(LocationManager::class.java)

    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0L, 0F) {
        callback(it)
    }
}
