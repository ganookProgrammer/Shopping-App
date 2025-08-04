package com.example.myshoppingadmin.domain_layer.repo

import android.net.Uri
import com.example.myshoppingadmin.common.ResultState
import com.example.myshoppingadmin.domain_layer.model.CategoryModel
import com.example.myshoppingadmin.domain_layer.model.ProductModel
import kotlinx.coroutines.flow.Flow


interface Repo  {
   suspend fun addCategory(category: CategoryModel) : Flow<ResultState<String>>

    suspend fun addProduct(product : ProductModel) : Flow<ResultState<String>>

    suspend fun getCategories() : Flow<ResultState<List<CategoryModel>>>

    suspend fun uploadImage(image : Uri) : Flow<ResultState<String>>

    suspend fun categoryImage(image: Uri) : Flow<ResultState<String>>
}