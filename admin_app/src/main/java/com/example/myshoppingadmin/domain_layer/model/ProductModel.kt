package com.example.myshoppingadmin.domain_layer.model

import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class ProductModel(
    val title : String = "",
    val description : String = "",
    val price:String = "",
    val actualPrice : String = "",
    val category : String = "",
    val images : String = "",
    val availableUnits : Int =0,
    val isAvailable : Boolean = true,
    val date : String  = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yy"))
)

