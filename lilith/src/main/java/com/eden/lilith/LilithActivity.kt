package com.eden.lilith

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.eden.lilith.utils.MultiplePermissionObserver
import com.eden.lilith.utils.PermissionObserver

open class LilithActivity : AppCompatActivity() {

    lateinit var observer: PermissionObserver
    lateinit var multipleObserver: MultiplePermissionObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observer = PermissionObserver(activityResultRegistry)
        multipleObserver = MultiplePermissionObserver(activityResultRegistry)
        lifecycle.addObserver(observer)
        lifecycle.addObserver(multipleObserver)
    }

}