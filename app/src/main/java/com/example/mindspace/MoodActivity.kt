package com.example.mindspace


import android.content.Intent

import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class MoodActivity : AppCompatActivity() {
    private lateinit var entryTF : EditText
    private lateinit var spinner : Spinner
    private lateinit var dateToday : TextView
    private lateinit var sqliteHelper: DBHelper
    private lateinit var mood : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mood_tracker)

        val backBTN = findViewById<Button>(R.id.back_button)
        val cancelBTN = findViewById<Button>(R.id.rating_cancel_button)
        val confirmBTN = findViewById<Button>(R.id.rating_confirm_button)

        entryTF = findViewById<EditText>(R.id.text_box)
        sqliteHelper = DBHelper(this)
        dateToday = findViewById<TextView>(R.id.date_text)

        val calendar = Calendar.getInstance()
        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH] + 1 // Months are 0-based, so add 1
        val day = calendar[Calendar.DAY_OF_MONTH]

        val dateGet = Date(year, month, day)
        dateToday.text = dateGet.toString()

        spinner = findViewById<Spinner>(R.id.rating_rating_spinner)
        spinner.setSelection(2)

        backBTN.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        cancelBTN.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        confirmBTN.setOnClickListener{addEntry()}


        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                mood = spinner.getItemAtPosition(position).toString()

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                mood = spinner.getItemAtPosition(2).toString()
            }
        }

    }

    private fun addEntry(){
        val entry = entryTF.text.toString()
        val date = dateToday.text.toString()
        var num = 0
        if(mood.equals("Very Sad"))
            num = 1
        else if(mood.equals("Sad"))
            num = 2
        else if(mood.equals("Neutral"))
            num = 3
        else if(mood.equals("Happy"))
            num = 4
        else if(mood.equals("Very Happy"))
            num = 5
        val diary = DiaryEntry(entry, date, mood, num)
        val status = sqliteHelper.insertEntry(diary)
        if(status > -1){
            Toast.makeText(this, "Entry Added...", Toast.LENGTH_SHORT).show()
            clearEditText()
        }else{
            Toast.makeText(this, "Entry Not Added...", Toast.LENGTH_SHORT).show()
        }

    }

    private fun clearEditText()
    {
        entryTF.setText("")
    }

}

