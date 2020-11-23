package com.e.mysensordata

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() , SensorEventListener {

    private var mSensorManager: SensorManager? = null

    // Individual light and proximity sensors.
    private var mSensorProximity: Sensor? = null
    private var mSensorLight: Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        mSensorManager =
            getSystemService(Context.SENSOR_SERVICE) as SensorManager?

        mSensorProximity = mSensorManager?.getDefaultSensor(Sensor.TYPE_PROXIMITY)

        mSensorLight = mSensorManager?.getDefaultSensor(Sensor.TYPE_LIGHT)


    }

    override fun onStart() {
        super.onStart()

        if (mSensorProximity != null) {
            mSensorManager!!.registerListener(
                this, mSensorProximity,
                SensorManager.SENSOR_DELAY_NORMAL
            );
        }
        if (mSensorLight != null) {
            mSensorManager!!.registerListener(
                this, mSensorLight,
                SensorManager.SENSOR_DELAY_NORMAL
            );
        }
    }

    override fun onStop() {
        super.onStop()

        mSensorManager!!.unregisterListener(this);
    }

    override fun onSensorChanged(event: SensorEvent?) {
        var sensorType = event!!.sensor.type
        var currentValue = event.values[0]

        when (sensorType) {
            Sensor.TYPE_LIGHT -> {
                light_view.text = "light $currentValue"
            }

            Sensor.TYPE_PROXIMITY -> {
                proximity_view.text = "proximity $currentValue"
            }
            else -> {


            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }
}