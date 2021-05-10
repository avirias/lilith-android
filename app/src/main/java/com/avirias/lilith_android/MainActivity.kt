package com.avirias.lilith_android

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.avirias.lilith_android.databinding.ActivityMainBinding
import com.eden.lilith.utils.locationFlow
import com.eden.lilith.utils.permissions
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private val locationProvider by lazy {
        LocationServices.getFusedLocationProviderClient(this)
    }

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val locationPermission by permissions {
        if (it.filterValues { e -> e.not() }.isEmpty()) {
            getLocation()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            locationPermission.launch(
                arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            )
        }

    }

    private fun getLocation() {

        locationProvider.locationFlow()
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach {
                Log.d(TAG, "onCreate: location => lat: ${it.latitude}, long: ${it.longitude}")
            }.launchIn(lifecycleScope)

    }

    companion object {
        private const val TAG = "MainActivity"
    }

}