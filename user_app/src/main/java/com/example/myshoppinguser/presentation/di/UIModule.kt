package com.example.myshoppinguser.presentation.di

import com.example.myshoppinguser.data.repo.RepoImpl
import com.example.myshoppinguser.domain.repo.Repo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UIModule {
    @Provides
    fun provideRepoImp(firestore : FirebaseFirestore,auth : FirebaseAuth,storage : FirebaseStorage) : Repo{
        return RepoImpl(firestore,auth,storage )
    }
}