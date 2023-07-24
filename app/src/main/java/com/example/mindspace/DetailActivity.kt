package com.example.mindspace

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val images = findViewById<ImageView>(R.id.images)

        // Retrieve the item data passed from MainActivity
        val item = intent.getParcelableExtra<Item>("item")
        item?.let {
            images.setImageResource(it.imageResId)
        }
    }
}