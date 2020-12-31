package com.avirias.lilith_android

import android.os.Bundle
import com.eden.lilith.LilithActivity

class SecondActivity: LilithActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val hello = intent.getStringExtra("Hello")
        val isLiked = intent.getBooleanExtra("isLiked", false)
    }
}