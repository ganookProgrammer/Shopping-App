package com.example.myshoppinguser.domain.repo

import com.example.myshoppinguser.state.ResultState
import com.example.myshoppinguser.domain.models.Category
import com.example.myshoppinguser.domain.models.Product
import com.example.myshoppinguser.domain.models.User
import kotlinx.coroutines.flow.Flow

interface Repo {

//    Authentication
    fun registerUserWithEmailPass(user: User): Flow<ResultState<String>>
    fun loginUserWithEmailPass(email :String , password : String)  : Flow<ResultState<String>>

    fun getAllCategory(): Flow<ResultState<List<Category>>>


    fun getBannerImage() : Flow<ResultState<List<String>>>
//    fun getCategoriesInLimited(): Flow<ResultState<List<Category>>>

//    fun getProductsInLimited() : Flow<ResultState<List<>>>
    fun getAllProducts() : Flow<ResultState<List<Product>>>

    fun getProductById(productId : String) : Flow<ResultState<Product>>


}