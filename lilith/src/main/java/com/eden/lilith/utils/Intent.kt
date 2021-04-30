package com.eden.lilith.utils

import androidx.activity.ComponentActivity


fun ComponentActivity.getString(
    key: String
) = intent.getStringExtra(key)

fun ComponentActivity.getBoolean(
    key: String,
    defaultValue: Boolean = false
) = intent.getBooleanExtra(key, defaultValue)

fun ComponentActivity.getLong(
    key: String,
    defaultValue: Long = 0L
) = intent.getLongExtra(key, defaultValue)

fun ComponentActivity.getDouble(
    key: String,
    defaultValue: Double = 0.0
) = intent.getDoubleExtra(key, defaultValue)

fun ComponentActivity.getInt(
    key: String,
    defaultValue: Int = 0
) = intent.getIntExtra(key, defaultValue)
