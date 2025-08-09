package com.example.myshoppinguser.domain.use_case

import android.net.Uri
import com.example.myshoppinguser.domain.repo.Repo
import javax.inject.Inject

class UploadUserImageUseCase @Inject constructor(private val repo : Repo) {
    fun uploadUserImageUseCase(imageUri : Uri) = repo.uploadUserImage(imageUri)
}