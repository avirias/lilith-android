package com.eden.lilith.utils

import android.content.pm.PackageManager
import android.provider.FontsContract
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import com.eden.lilith.LilithActivity
import kotlinx.coroutines.flow.collect

fun LilithActivity.requestPermission(
    permission: String,
    rationalRequestCallback: () -> Unit = { showRequestPermissionRationale() },
    onPermissionGranted: () -> Unit
) {


    when {
        ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED ->
            onPermissionGranted()
        shouldShowRequestPermissionRationale(permission) -> rationalRequestCallback()
        else -> {
            observer.requestPermission(permission)
            lifecycleScope.launchWhenCreated {
                observer.singlePermissionObserver.collect {
                    if (it) onPermissionGranted()
                }
            }
        }
    }

}


fun LilithActivity.requestMultiplePermissions(
    permissions: Array<String>,
    rationalRequestCallback: () -> Unit = { showRequestPermissionRationale() },
    onAllPermissionsGranted: () -> Unit
) {
    var haveAllPermission = true
    var shouldShowRational = false

    permissions.forEach {
        if (haveAllPermission)
            if (ActivityCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED) {
                haveAllPermission = false
            }
        if (!shouldShowRational)
            shouldShowRational = shouldShowRequestPermissionRationale(it)
    }

    if (!haveAllPermission && !shouldShowRational) {
        multipleObserver.requestMultiplePermission(permissions)
        lifecycleScope.launchWhenCreated {
            multipleObserver.multiPermissionObserver.collect {
                if (it.filterValues { e -> e.not() }.isEmpty()) {
                    onAllPermissionsGranted()
                }
            }
        }
    }

    if (shouldShowRational) rationalRequestCallback()

}
