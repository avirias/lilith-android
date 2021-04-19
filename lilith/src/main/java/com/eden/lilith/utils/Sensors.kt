package com.eden.lilith.utils

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.activity.OnBackPressedCallback
import com.eden.lilith.LilithActivity

fun LilithActivity.sensors(
    callback: (List<Sensor>) -> Unit
) {
    val sensorManager = this.getSystemService(SensorManager::class.java)

    // to get all sensors
    val deviceSensors: List<Sensor> = sensorManager.getSensorList(Sensor.TYPE_ALL)

    //println(deviceSensors)

    callback(deviceSensors)

}

fun LilithActivity.getSensor(
    sensorType: Int,
    sensorListener: SensorEventListener
) {
    val sensorManager = this.getSystemService(SensorManager::class.java)

    // get sensor data of sensorType

    // Success! There's a magnetometer.
    if (sensorManager.getDefaultSensor(sensorType) != null) {
        val mSensor = sensorManager.getDefaultSensor(sensorType)
        sensorManager.registerListener(sensorListener, mSensor, SensorManager.SENSOR_DELAY_NORMAL)
    } else throw Error("Sensor was not available, Try wrapping getSensor() with sensors().")


}