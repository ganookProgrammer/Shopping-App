package com.example.myshoppinguser.domain.use_case

import com.example.myshoppinguser.domain.repo.Repo
import javax.inject.Inject

class GetBannerImageUseCase @Inject constructor(private val repo: Repo) {

    fun getBannerImageUseCase() = repo.getBannerImage()
}