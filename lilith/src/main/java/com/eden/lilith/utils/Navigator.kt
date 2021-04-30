package com.eden.lilith.utils

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity

fun ComponentActivity.navigateTo(
    to: Class<out Activity>,
    intent: Intent = Intent(this, to)
) = startActivity(intent)

fun ComponentActivity.navigateTo(
    to: Class<out Activity>,
    extras: Bundle
) {
    val intent = Intent(this, to).apply {
        putExtras(extras)
    }
    startActivity(intent)
}

fun ComponentActivity.navigateTo(
    to: Class<out Activity>,
    intent: (Intent) -> Intent
): NavigationBuilder {
    val newIntent = Intent(this, to)
    val returnedIntent = intent.invoke(newIntent)
    return NavigationBuilder(
        from = this,
        intent = returnedIntent
    )
}


class NavigationBuilder(
    private val from: ComponentActivity,
    private val intent: Intent
) {
    fun go() = from.startActivity(intent)
}

