package com.avirias.lilith_android

import android.os.Bundle
import android.util.Log
import com.avirias.lilith_android.databinding.ActivityMainBinding
import com.eden.lilith.LilithActivity
import com.eden.lilith.utils.*

class MainActivity : LilithActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val handler = object : PermissionHandler {
            override fun onPermissionDenied(permissionDenied: List<String>) {

            }
        }

        binding.button.setOnClickListener {
//            navigateTo(SecondActivity::class.java) {
//                it.apply {
//                    putExtra("Hello", "World")
//                    putExtra("New", 69)
//                    putExtra("isLiked", false)
//                }
//            }.go()



            ifOnline {
                Log.d(TAG, "onCreate: network connected")
            }.otherWise {
                Log.d(TAG, "onCreate: network not connected")
            }


        }





    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
