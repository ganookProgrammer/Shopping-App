package com.example.myshoppingadmin.domain_layer.model

import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class CategoryModel(
    val name: String = "",
    val date: String = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyy")),
    val imgUri : String = ""
)
