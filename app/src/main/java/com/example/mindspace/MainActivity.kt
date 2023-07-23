package com.example.mindspace

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
class MainActivity : AppCompatActivity() {
    private lateinit var articles :ImageView
    private lateinit var meditation :ImageView
    private lateinit var moodTracker :ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startWellnessButton = findViewById<Button>(R.id.startWellnessButton)


        startWellnessButton.setOnClickListener {
            setContentView(R.layout.activity_dashboard)
            articles = findViewById<ImageView>(R.id.acrticles)
            meditation = findViewById<ImageView>(R.id.medit)
            moodTracker = findViewById<ImageView>(R.id.mood)
        }


    }
}