package com.example.myapplication

import android.content.Intent
import android.media.session.PlaybackState
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityCommunityBinding
import com.example.myapplication.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_community.*

class CommunityActivity : AppCompatActivity() {

    val binding by lazy { ActivityCommunityBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val intent = Intent(this,Community_write::class.java)
        binding.writeBtn.setOnClickListener{ startActivity(intent) }

        //1. 데이터 로딩
        val data = loadData()
        //2. 어댑터 생성
        val adapter = CustomAdapter()
        //3. 어댑터에 데이터 전달
        adapter.listData = data
        //4. 화면에 있는 리사이클러뷰에 아답터 전달
        recycler.adapter = adapter
        //5. 레이아웃 매니저 연결
        recycler.layoutManager = LinearLayoutManager(this)
    }

    fun loadData() : MutableList<Memo> {
        val data:MutableList<Memo> = mutableListOf()

        for(no in 1..100) {
            val title = "이게 제목이양"
            val date = System.currentTimeMillis()
            val writer = "글쓴이얌"
            val hits = 10
            val memo = Memo(no, title, writer, date, hits)
            data.add(memo)
        }

        return data
    }


}