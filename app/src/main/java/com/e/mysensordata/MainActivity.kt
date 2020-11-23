package com.e.mysensordata

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var mSensorManager: SensorManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSensorManager =
                getSystemService(Context.SENSOR_SERVICE) as SensorManager?

        val sensorList: List<Sensor> = mSensorManager!!.getSensorList(Sensor.TYPE_ALL)

        val sensorText = StringBuilder()

        for (currentSensor in sensorList) {
            sensorText.append(currentSensor.name).append(
                    System.getProperty("line.separator"))
        }

        sensor_view.text = sensorText

        next_view.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        })
    }
}