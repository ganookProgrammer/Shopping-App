package com.example.myshoppinguser.domain.use_case

import com.example.myshoppinguser.domain.models.User
import com.example.myshoppinguser.domain.repo.Repo
import javax.inject.Inject

class RegisterUserWithEmailPassUseCase @Inject constructor(private val repo: Repo) {

    fun registerUserWithEmailPass(user: User) =
        repo.registerUserWithEmailPass(user)

}