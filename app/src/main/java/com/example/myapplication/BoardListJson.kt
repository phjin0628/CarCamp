package com.example.myapplication

import com.example.myapplication.BoardData.BoardList
import com.example.myapplication.mapdata.CarMapJson
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

class BoardListJson {
    companion object{
        val DOMAIN="http://3.34.141.115/boardlist3.php/"
    }
}

interface BoardListOpenService{
    @GET
    fun getCar(@Url url:String): Call<BoardList>
}