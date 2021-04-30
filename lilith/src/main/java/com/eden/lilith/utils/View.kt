package com.eden.lilith.utils

import androidx.activity.ComponentActivity

fun ComponentActivity.showRequestPermissionRationale() {

    showErrorDialog(
        "Need this permission, Please allow in app settings",
        "SETTINGS"
    ) {
        openAppSettings()
    }

}