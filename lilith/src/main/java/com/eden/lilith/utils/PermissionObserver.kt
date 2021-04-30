package com.eden.lilith.utils

import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts.RequestMultiplePermissions
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PermissionObserver(
    private val registry: ActivityResultRegistry
) : DefaultLifecycleObserver {

    private lateinit var getSinglePermission: ActivityResultLauncher<String>

    private val _isPermissionGranted: MutableStateFlow<Boolean?> = MutableStateFlow(null)
    val singlePermissionObserver: StateFlow<Boolean?> = _isPermissionGranted

    override fun onCreate(owner: LifecycleOwner) {
        getSinglePermission = registry.register("single", owner, RequestPermission()) {
            _isPermissionGranted.value = it
        }
    }


    fun requestPermission(permission: String) = getSinglePermission.launch(permission)

}

class MultiplePermissionObserver(
    private val registry: ActivityResultRegistry
) : DefaultLifecycleObserver {
    private lateinit var getMultiplePermissions: ActivityResultLauncher<Array<String>>

    private val _multiObserver: MutableStateFlow<Map<String, Boolean>> =
        MutableStateFlow(mapOf("" to false))

    val multiPermissionObserver: StateFlow<Map<String, Boolean>> = _multiObserver

    override fun onCreate(owner: LifecycleOwner) {
        getMultiplePermissions = registry.register("multi", owner, RequestMultiplePermissions()) {
            _multiObserver.value = it
        }
    }

    fun requestMultiplePermission(permissions: Array<String>) =
        getMultiplePermissions.launch(permissions)
}