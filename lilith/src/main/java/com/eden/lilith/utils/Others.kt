package com.eden.lilith.utils

import android.widget.Toast
import com.eden.lilith.LilithActivity

fun LilithActivity.showToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}

