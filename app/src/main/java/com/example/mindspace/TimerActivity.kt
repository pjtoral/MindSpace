package com.example.mindspace

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TimerActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private lateinit var tvTimer: TextView
    private lateinit var btnPlayPause: Button
    private lateinit var btnReset: Button
    private var timerRunning: Boolean = false
    private var timerPaused: Boolean = false
    private lateinit var countDownTimer: CountDownTimer
    private var timeLeftInMillis: Long = 60000 // 1 minute in milliseconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        progressBar = findViewById(R.id.progressBar)
        tvTimer = findViewById(R.id.tvTimer)
        btnPlayPause = findViewById(R.id.btnPlayPause)
        btnReset = findViewById(R.id.btnReset)

        progressBar.max = 60000.toInt()

        val backBTN = findViewById<Button>(R.id.back_button)

        backBTN.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btnPlayPause.setOnClickListener {
            if (timerRunning) {
                pauseTimer()
            } else {
                startTimer()
            }
        }

        btnReset.setOnClickListener {
            resetTimer()
        }

        updateCountDownText()
    }

    private fun startTimer() {
        countDownTimer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateCountDownText()
                updateProgressBar()
            }

            override fun onFinish() {
                timerRunning = false
                timerPaused = false
                btnPlayPause.text = "Play"
            }
        }.start()

        timerRunning = true
        btnPlayPause.text = "Pause"
    }

    private fun pauseTimer() {
        countDownTimer.cancel()
        timerRunning = false
        timerPaused = true
        btnPlayPause.text = "Play"
    }

    private fun resetTimer() {
        countDownTimer.cancel()
        timeLeftInMillis = 60000
        timerRunning = false
        timerPaused = false
        btnPlayPause.text = "Play"
        updateCountDownText()
        updateProgressBar()
    }

    private fun updateCountDownText() {
        val minutes = (timeLeftInMillis / 1000) / 60
        val seconds = (timeLeftInMillis / 1000) % 60
        val timeLeftFormatted = String.format("%02d:%02d", minutes, seconds)
        tvTimer.text = timeLeftFormatted
    }

    private fun updateProgressBar() {
        progressBar.progress = timeLeftInMillis.toInt()
    }
}
