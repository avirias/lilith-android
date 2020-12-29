package com.eden.lilith.utils

import android.graphics.Bitmap
import android.widget.Toast
import com.eden.lilith.LilithActivity
import java.io.File
import java.io.FileOutputStream
import java.util.*

fun Bitmap.save(file: File): File {

    if (file.exists()) file.delete()

    return try {
        val outputStream = FileOutputStream(file)
        this.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        outputStream.flush()
        outputStream.close()
        file

    } catch (e: Exception) {
        throw e
    }
}


fun File.fromBitmap(bitmap: Bitmap, directory: File): File {

    val file = File(directory, "${Date().time}.png")
    return try {
        val outputStream = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        outputStream.flush()
        outputStream.close()
        file
    } catch (e: Exception) {
        throw e
    }
}

