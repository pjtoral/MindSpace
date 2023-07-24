package com.example.mindspace

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ArticleActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var listAdapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.articles_activity)

        val items = getSampleItems()

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        // Create the ListAdapter and pass onItemClickListener lambda
        val listAdapter = ListAdapter(items) { item ->
            showDetailDialog(item)
        }


        recyclerView.adapter = listAdapter
    }

    private fun getSampleItems(): List<Item> {
        val sampleItems = mutableListOf<Item>()

        sampleItems.add(Item(R.drawable.reminder_1, "Mental Health Matters"))
        sampleItems.add(Item(R.drawable.verysad, "Mental Health Matters"))
        sampleItems.add(Item(R.drawable.sad, "Mental Health Matters"))
        sampleItems.add(Item(R.drawable.neutral, "Mental Health Matters"))
        sampleItems.add(Item(R.drawable.happy, "Mental Health Matters"))
        sampleItems.add(Item(R.drawable.veryhappy, "Mental Health Matters"))



        return sampleItems
    }

    private fun showDetailDialog(item: Item) {
        // Navigate to the detail activity and pass the item's data
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("item", item)
        startActivity(intent)
    }
}