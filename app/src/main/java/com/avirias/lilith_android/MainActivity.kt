package com.avirias.lilith_android

import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import com.avirias.lilith_android.databinding.ActivityMainBinding
import com.eden.lilith.utils.permission
import com.eden.lilith.utils.permissions
import com.eden.lilith.utils.registerForResult

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val cameraPermission by permission {
       if (it) takePicture.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
    }

    private val locationPermission by permissions {

    }

    private val takePicture by registerForResult {
        if (it.resultCode == RESULT_OK) {
            val bitmap = intent?.extras?.get("data") as Bitmap
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cameraPermission.launch(Manifest.permission.CAMERA)

    }

}
