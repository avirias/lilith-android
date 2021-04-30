package com.eden.lilith.utils

import android.app.AlertDialog
import androidx.activity.ComponentActivity
import java.lang.Exception

fun ComponentActivity.showErrorDialog(
    message: String,
    buttonText: String,
    onClicked: () -> Unit
) {
    showMessageDialog(
        title = "Error",
        message, buttonText, onClicked
    )
}

fun ComponentActivity.showMessageDialog(
    title: String,
    message: String,
    buttonText: String,
    onClicked: () -> Unit
) {

    val alertDialog = AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(buttonText) { dialog, _ ->
            dialog.dismiss()
            onClicked.invoke()
        }.create()

    try {
        alertDialog.show()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}