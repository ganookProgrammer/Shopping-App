package com.example.myshoppinguser.domain.models

import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class Product(
    val productId : String = "",
    val title : String = "",
    val description : String = "",
    val price:String = "",
    val actualPrice : String = "",
    val category : String = "",
    val images : String = "",
    val availableUnits : Int =0,
    val isAvailable : Boolean = true,
    val date : String  = ""
)
