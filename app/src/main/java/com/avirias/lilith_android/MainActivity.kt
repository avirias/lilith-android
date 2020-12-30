package com.avirias.lilith_android

import android.Manifest
import android.os.Bundle
import android.util.Log
import com.avirias.lilith_android.databinding.ActivityMainBinding
import com.eden.lilith.LilithActivity
import com.eden.lilith.utils.Handler
import com.eden.lilith.utils.getCallLogs
import com.eden.lilith.utils.getLocation
import com.eden.lilith.utils.getPermission

class MainActivity : LilithActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val handler = object : Handler {
            override fun onPermissionDenied(permissionDenied: List<String>) {

            }
        }

        binding.button.setOnClickListener {
            getPermission(listOf(Manifest.permission.READ_CALL_LOG), handler) {
                getCallLogs {
                    it.forEach { call ->
                        println(call)
                    }
                }
            }
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}