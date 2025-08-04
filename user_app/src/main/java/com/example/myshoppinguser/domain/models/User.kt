package com.example.myshoppinguser.domain.models

data class User(
    val firstName : String = "",
    val lastName : String = "",
    val email : String = "",
    val password : String = "",
    val phoneNumber : String = "",
    val address : String = "",
    val userImage : String = ""
)
