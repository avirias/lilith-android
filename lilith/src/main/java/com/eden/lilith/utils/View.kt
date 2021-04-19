package com.eden.lilith.utils

import com.eden.lilith.LilithActivity

fun LilithActivity.showRequestPermissionRationale() {

    showErrorDialog(
        "Need this permission, Please allow in app settings",
        "SETTINGS"
    ) {
        openAppSettings()
    }

}