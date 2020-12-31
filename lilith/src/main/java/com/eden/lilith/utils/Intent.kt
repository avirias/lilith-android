package com.eden.lilith.utils

import com.eden.lilith.LilithActivity

fun LilithActivity.getString(
    key: String
) = intent.getStringExtra(key)

fun LilithActivity.getBoolean(
    key: String,
    defaultValue: Boolean = false
) = intent.getBooleanExtra(key, defaultValue)

fun LilithActivity.getLong(
    key: String,
    defaultValue: Long = 0L
) = intent.getLongExtra(key, defaultValue)

fun LilithActivity.getDouble(
    key: String,
    defaultValue: Double = 0.0
) = intent.getDoubleExtra(key, defaultValue)

fun LilithActivity.getInt(
    key: String,
    defaultValue: Int = 0
) = intent.getIntExtra(key, defaultValue)
