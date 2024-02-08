
package com.example.compase

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity(),SensorEventListener {

    var sensor:Sensor? = null
    var sensorManager:SensorManager?=null
    lateinit var compaseImage: ImageView
    lateinit var rotationalTV:TextView

    //To keep track of rotation of the sensor
    var currentdegree = 0f




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager=getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_ORIENTATION)


        compaseImage = findViewById(R.id.comp)
        rotationalTV = findViewById(R.id.text)

    }

    override fun onSensorChanged(event: SensorEvent?) {

     //Most importent funtion

        val degrre = Math.round(event!!.values[0])
        rotationalTV.text= degrre.toString() + "Love Angle"
        val rotateAnimation= RotateAnimation(currentdegree, (-degrre).toFloat(),Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f)
        rotateAnimation.duration = 210
        rotateAnimation.fillAfter = true
        compaseImage.startAnimation(rotateAnimation)
        currentdegree= (-degrre).toFloat()



    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

//Register a listner for the sensor
    override fun onResume() {
        super.onResume()
        sensorManager?.registerListener(this, sensor,SensorManager.SENSOR_DELAY_GAME)
    }

    override fun onPause() {
        super.onPause()
        sensorManager?.unregisterListener(this)
    }

}