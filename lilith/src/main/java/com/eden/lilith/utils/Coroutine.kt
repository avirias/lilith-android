package com.eden.lilith.utils

import androidx.activity.ComponentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

fun ComponentActivity.launchRepeating(
    state: Lifecycle.State,
    block: suspend CoroutineScope.() -> Unit
): Job {
    return lifecycle.coroutineScope.launch {
        lifecycle.repeatOnLifecycle(state, block)
    }
}
