package com.eden.lilith.utils

import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.RequestMultiplePermissions
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission


fun ComponentActivity.permission(
    onResult: (Boolean) -> Unit
) = lazyOf(registerForActivityResult(RequestPermission()) {
    onResult.invoke(it)
})

fun ComponentActivity.permissions(
    onResult: (Map<String, Boolean>) -> Unit
) = lazyOf(registerForActivityResult(RequestMultiplePermissions()) {
    onResult.invoke(it)
})

fun Map<String, Boolean>.isAllGranted() = filterValues { it.not() }.isEmpty()

fun <I> ActivityResultLauncher<I>.request(input: I) = launch(input)
