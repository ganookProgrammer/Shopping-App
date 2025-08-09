package com.example.myshoppinguser.domain.use_case

import com.example.myshoppinguser.domain.models.User
import com.example.myshoppinguser.domain.repo.Repo
import javax.inject.Inject

class UpdateUserDataUserCase @Inject constructor(private val repo : Repo) {
    fun updateUserDataUseCase(user : User) = repo.updateUserData(user)
}