package com.eden.lilith.utils

import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_WEAK
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.eden.lilith.LilithActivity

fun LilithActivity.biometricAuth(
    title: String,
    subtitle: String,
    listener: BiometricPrompt.AuthenticationCallback
) {

    val build = BiometricPrompt.PromptInfo.Builder()
        .setTitle(title)
        .setSubtitle(subtitle)
        .setNegativeButtonText("Cancel")
        .setAllowedAuthenticators(BIOMETRIC_STRONG or BIOMETRIC_WEAK)
        .build()

    val executor = ContextCompat.getMainExecutor(this)

    val biometricPrompt = BiometricPrompt(this, executor, listener)
    biometricPrompt.authenticate(build)

}