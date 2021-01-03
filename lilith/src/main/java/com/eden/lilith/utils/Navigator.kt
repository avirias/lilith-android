package com.eden.lilith.utils

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.eden.lilith.LilithActivity

fun LilithActivity.navigateTo(
    to: Class<out Activity>,
    intent: Intent = Intent(this, to)
) = startActivity(intent)

fun LilithActivity.navigateTo(
    to: Class<out Activity>,
    extras: Bundle
) {
    val intent = Intent(this, to).apply {
        putExtras(extras)
    }
    startActivity(intent)
}

fun LilithActivity.navigateTo(
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
    private val from: LilithActivity,
    private val intent: Intent
) {
    fun go() {
        from.startActivity(intent)
    }
}

