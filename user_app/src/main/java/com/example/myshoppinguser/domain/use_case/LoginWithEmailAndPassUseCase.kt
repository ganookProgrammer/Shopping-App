package com.example.myshoppinguser.domain.use_case

import com.example.myshoppinguser.domain.repo.Repo
import javax.inject.Inject

class LoginWithEmailAndPassUseCase @Inject  constructor(private val repo : Repo) {
    fun loginWithEmailAndPassUseCase(
        email : String,
        password : String
    ) = repo.loginUserWithEmailPass(email = email , password = password)
}