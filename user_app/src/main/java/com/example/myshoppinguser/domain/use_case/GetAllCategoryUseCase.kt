package com.example.myshoppinguser.domain.use_case

import com.example.myshoppinguser.domain.repo.Repo
import javax.inject.Inject

class GetAllCategoryUseCase @Inject constructor(private val repo: Repo)  {
    fun getCategoryUseCase() = repo.getAllCategory()
}