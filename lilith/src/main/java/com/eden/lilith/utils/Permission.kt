package com.eden.lilith.utils

import android.content.pm.PackageManager
import com.eden.lilith.LilithActivity

fun LilithActivity.getPermission(
    permissions: List<String>,
    permissionHandler: PermissionHandler,
    onAllPermissionGranted: () -> Unit
) {

    val rCode = 40122

    setCallback { requestCode, p, grantResults ->
        if (requestCode == requestCode) {
            val toSet = grantResults.toSet()
            if (toSet.size == 1 && toSet.contains(PackageManager.PERMISSION_GRANTED)) {
                // All granted
                onAllPermissionGranted()
            } else {
                // Some granted some denied // All denied
                val toList = grantResults.mapIndexed { index, it ->
                    if (PackageManager.PERMISSION_DENIED == it)
                        p[index]
                    else
                        "fuck"
                }.filter {
                    it != "fuck"
                }.toList()
                permissionHandler.onPermissionDenied(toList)

            }
        }
    }
    this.requestPermissions(permissions.toTypedArray(), rCode)
}


interface PermissionHandler {
    fun onPermissionDenied(permissionDenied: List<String>)
}