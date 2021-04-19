package com.avirias.lilith_android

import android.Manifest
import android.os.Bundle
import android.util.Log
import com.avirias.lilith_android.databinding.ActivityMainBinding
import com.eden.lilith.LilithActivity
import com.eden.lilith.utils.requestMultiplePermissions
import kotlinx.coroutines.flow.collect

class MainActivity : LilithActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.button.setOnClickListener {
            requestMultiplePermissions(arrayOf(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO)) {
                Log.d(TAG, "onCreate: all permissions granted")
            }
        }


    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
