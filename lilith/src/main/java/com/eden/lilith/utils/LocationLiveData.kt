package com.eden.lilith.utils

import android.content.Context
import android.location.Location
import android.os.Looper
import androidx.lifecycle.LiveData
import com.google.android.gms.location.*

class LocationLiveData(context: Context) : LiveData<Location>() {

    private val locationProvider = LocationServices.getFusedLocationProviderClient(context)

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            locationResult ?: return
            for (location in locationResult.locations) {
                value = location
            }
        }
    }

    private fun startUpdates() {
        locationProvider.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
    }

    override fun onActive() {
        super.onActive()
        locationProvider.removeLocationUpdates(locationCallback)
    }

    override fun onInactive() {
        super.onInactive()
        locationProvider.lastLocation
            .addOnSuccessListener {
                it?.let {
                    value = it
                }
            }
        startUpdates()
    }

    companion object {
        private const val FASTEST_INTERVAL = 500L
        private const val INTERVAL = 1000L
        private val locationRequest: LocationRequest = LocationRequest.create()
            .apply {
                interval = INTERVAL
                fastestInterval = FASTEST_INTERVAL
                priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            }
    }
}