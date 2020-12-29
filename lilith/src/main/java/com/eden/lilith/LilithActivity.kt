package com.eden.lilith

import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

open class LilithActivity : AppCompatActivity(), ActivityCompat.OnRequestPermissionsResultCallback {

    private lateinit var callback: ActivityCompat.OnRequestPermissionsResultCallback

    fun setCallback(mCallback: ActivityCompat.OnRequestPermissionsResultCallback) {
        callback = mCallback
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (this::callback.isInitialized) {
            callback.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }

    }
}