package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_community__content.*

class Community_Content : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community__content)

        val no =intent.getStringExtra("no")
        val title =intent.getStringExtra("title")
        val date =intent.getStringExtra("date")

        textNo.text = no
        textTitle.text = title
        textDate.text = date
    }
}