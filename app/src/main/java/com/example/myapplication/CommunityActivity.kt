package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityCommunityBinding

class CommunityActivity : AppCompatActivity() {

    val binding by lazy {ActivityCommunityBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val intent= Intent(this, Community_write::class.java)
        binding.btnAdd.setOnClickListener { startActivity(intent) }
    }
}