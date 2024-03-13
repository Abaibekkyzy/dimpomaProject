package com.example.myapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class NewActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)

        val imageResource = intent.getIntExtra("imageResource", 0)
        val data = intent.getStringExtra("data")
        val time = intent.getStringExtra("time")
        val description = intent.getStringExtra("description")


        val imageView = findViewById<ImageView>(R.id.imageView)
        val dataTextView = findViewById<TextView>(R.id.dataTextView)
        val timeTextView = findViewById<TextView>(R.id.timeTextView)
        val descriptionTextView = findViewById<TextView>(R.id.descriptionTextView)

        imageView.setImageResource(imageResource)
        dataTextView.text = data
        timeTextView.text = time
        descriptionTextView.text = description
    }
}
