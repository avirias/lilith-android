package com.eden.lilith.utils

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult


fun ComponentActivity.showToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}


fun ComponentActivity.openAppSettings() {
    val uri = Uri.fromParts("package", packageName, null)
    val intent = Intent().apply {
        action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        data = uri
    }
    startActivity(intent)
}


fun ComponentActivity.registerForResult(
    onResult: (ActivityResult) -> Unit
) = lazyOf(registerForActivityResult(StartActivityForResult()){
    onResult.invoke(it)
})

