package com.eden.lilith.utils

import android.content.pm.PackageManager
import com.eden.lilith.LilithActivity

fun LilithActivity.getPermission(
    permissions: List<String>,
    handler: Handler,
    onAllPermissionGranted: () -> Unit
) {

    val REQUEST_CODE = 40122

    setCallback { requestCode, p, grantResults ->
        if (requestCode == REQUEST_CODE) {
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
                handler.onPermissionDenied(toList)

            }
        }
    }
    this.requestPermissions(permissions.toTypedArray(), REQUEST_CODE)
}


interface Handler {
    fun onPermissionDenied(permissionDenied: List<String>)
}