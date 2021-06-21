package com.example.myapplication

import android.media.session.PlaybackState
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_community.*

class CommunityActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community)

        val data = loadData()
        val adapter = CustomAdapter()
        adapter.listData = data
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(this)
    }

    fun loadData() : MutableList<Memo> {
        val data:MutableList<Memo> = mutableListOf()

        for(no in 1..100) {
            val title = "이게 제목이양"
            val date = System.currentTimeMillis()
            val memo = Memo(no, title, date)
            data.add(memo)
        }

        return data
    }


}