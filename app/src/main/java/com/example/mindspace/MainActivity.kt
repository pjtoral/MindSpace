package com.example.mindspace

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.database.sqlite.SQLiteOpenHelper

class MainActivity : AppCompatActivity() {
    private lateinit var articles :ImageView
    private lateinit var meditation :ImageView
    private lateinit var moodTracker :ImageView
    private lateinit var moodText : TextView
    private lateinit var moodImage : ImageView
    private lateinit var sqliteHelper: DBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startWellnessButton = findViewById<Button>(R.id.startWellnessButton)
        sqliteHelper = DBHelper(this)

        startWellnessButton.setOnClickListener {
            setContentView(R.layout.activity_dashboard)
            articles = findViewById<ImageView>(R.id.acrticles)
            meditation = findViewById<ImageView>(R.id.medit)
            moodTracker = findViewById<ImageView>(R.id.mood)
            moodText = findViewById<TextView>(R.id.moodText)
            moodImage = findViewById<ImageView>(R.id.moodImage)
            moodTracker.setOnClickListener{
                val intent = Intent(this, MoodActivity::class.java)
                startActivity(intent)

            }

            articles.setOnClickListener{
                val intent = Intent(this, ArticleActivity::class.java)
                startActivity(intent)

            }
            meditation.setOnClickListener{
                val intent = Intent(this, TimerActivity::class.java)
                startActivity(intent)

            }
            moodTracker.setOnClickListener{
                val intent = Intent(this, MoodActivity::class.java)
                startActivity(intent)

            }
            getEntries()
        }


    }
    private fun getEntries(){
        val entriesListed = sqliteHelper.getAllEntries()
        if (entriesListed.isNotEmpty()) {
            var sum = 0
            for (entry in entriesListed) {
                sum += entry.num
            }
            val average = sum.toDouble() / entriesListed.size
            val category: String = if (average <= 1.0) {
                "category_1"
            } else if (average <= 2.0) {
                "category_2"
            } else if (average <= 3.0) {
                "category_3"
            } else if (average <= 4.0) {
                "category_4"
            } else {
                "category_5"
            }

            when (category) {
                "category_1" -> {moodImage.setImageResource(R.drawable.verysad)
                    moodText.text = "Very Sad"}
                "category_2" -> {moodImage.setImageResource(R.drawable.sad)
                    moodText.text = "Sad"}
                "category_3" -> {moodImage.setImageResource(R.drawable.neutral)
                    moodText.text = "Neutral"}
                "category_4" -> {moodImage.setImageResource(R.drawable.happy)
                    moodText.text = "Happy"}
                else -> {moodImage.setImageResource(R.drawable.veryhappy)
                    moodText.text = "Very Happy"}
            }
        } else {
            println("No entries found.")
        }
    }
}