package com.example.myshoppinguser.domain.use_case

import com.example.myshoppinguser.domain.repo.Repo
import javax.inject.Inject

class GetUserInformationUseCase @Inject constructor(private val  repo: Repo) {
    fun getUserInformationUseCase() = repo.getUserInformation()
}