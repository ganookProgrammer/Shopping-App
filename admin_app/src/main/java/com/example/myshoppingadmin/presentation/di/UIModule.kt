package com.example.myshoppingadmin.presentation.di

import com.example.myshoppingadmin.data_layer.repoImpl.RepoImpl
import com.example.myshoppingadmin.domain_layer.repo.Repo
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
    fun providesRepoImpl(firestore : FirebaseFirestore , storage : FirebaseStorage) : Repo{
        return RepoImpl(firestore,storage)
    }
}