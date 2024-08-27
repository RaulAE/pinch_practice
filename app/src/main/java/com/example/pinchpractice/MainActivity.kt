package com.example.pinchpractice

import android.os.Bundle
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

lateinit var iv: ImageView
var scale = 1f

class MainActivity : AppCompatActivity() {

    var ourSD: ScaleGestureDetector? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        iv = findViewById(R.id.idImg)
        ourSD = ScaleGestureDetector(this, ScaleListener())


    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        ourSD!!.onTouchEvent(event!!)
        return super.onTouchEvent(event)
    }

    class ScaleListener: ScaleGestureDetector.SimpleOnScaleGestureListener(){
        override fun onScale(detector: ScaleGestureDetector): Boolean {
            scale *= detector.scaleFactor
            scale = Math.max(0.1f, Math.min(scale, 5.0f))
            iv.scaleX = scale
            iv.scaleY = scale

            return true
        }
    }
}