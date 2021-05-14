package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_login_result.*

class LoginResult : AppCompatActivity() {

    lateinit var CategoryGo: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_result)

        CategoryGo=findViewById(R.id.CategoryGo)

        if(intent.hasExtra("msg")){
            gettextID.text=intent.getStringExtra("msg")
        }

        CategoryGo.setOnClickListener {
            Intent(this, CategoryActivity::class.java).run {
                startActivity(this)
            }

    }
}}