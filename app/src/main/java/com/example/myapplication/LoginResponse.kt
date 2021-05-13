package com.example.myapplication

data class LoginResponse(
    val email: Int,
    val id: Int,
    val name: String,
    val password: String,
    val phonenum: String,
    val seq: String,
    val success: Boolean
)

//Json파일 만들기
//{"success":true,"id":13,"password":"test1","name":"\ud14c\uc2a4\ud130","phonenum":"test@gmail.com","email":1012345678,"seq":"1234"}