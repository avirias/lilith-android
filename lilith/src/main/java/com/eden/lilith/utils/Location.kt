package com.eden.lilith.utils

import android.Manifest
import android.annotation.SuppressLint
import android.location.Location
import android.location.LocationManager
import androidx.annotation.RequiresPermission
import com.eden.lilith.LilithActivity

@RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
fun LilithActivity.getLocation(
    callback: (Location) -> Unit
) {

    val locationManager = this.getSystemService(LocationManager::class.java)

    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0L, 0F) {
        callback(it)
    }
}

// TODO: 19/12/20  
fun LilithActivity.getLastLocation(
    callback: (Location) -> Unit
) {

}