package com.eden.lilith.utils

import android.widget.Toast
import com.eden.lilith.LilithActivity
import android.net.Uri

import android.content.Intent
import android.provider.Settings


fun LilithActivity.showToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}


fun LilithActivity.openAppSettings() {
    val uri = Uri.fromParts("package", packageName, null)
    val intent = Intent().apply {
        action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        data = uri
    }
    startActivity(intent)
}

