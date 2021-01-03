package com.avirias.lilith_android

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.avirias.lilith_android.databinding.ActivityMainBinding
import com.eden.lilith.LilithActivity
import com.eden.lilith.utils.getBoolean
import com.eden.lilith.utils.getInt
import com.eden.lilith.utils.getString

class SecondActivity : LilithActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val hello = getString("Hello")
        val isLiked = getBoolean("isLiked")
        val number = getInt("New")

        findViewById<TextView>(R.id.text).text = "Hello from Second activity"

        Log.d(TAG, "onCreate: hello $hello")
        Log.d(TAG, "onCreate: isLiked $isLiked")
        Log.d(TAG, "onCreate: number $number")


    }

    companion object {
        private const val TAG = "SecondActivity"
    }
}