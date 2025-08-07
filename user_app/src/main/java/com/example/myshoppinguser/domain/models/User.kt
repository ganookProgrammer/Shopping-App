package com.example.myshoppinguser.domain.models

data class User(
    var firstName : String = "",
    var lastName : String = "",
    var email : String = "",
    val password : String = "",
    var phoneNumber : String = "",
    var address : String = "",
    val userImage : String = ""
)
