package com.example.myshoppinguser.domain.use_case

import com.example.myshoppinguser.domain.repo.Repo
import javax.inject.Inject

class GetProductByIdUseCase @Inject constructor(private val repo : Repo) {
    fun getProductByIdUseCase(productId : String) = repo.getProductById(productId)
}