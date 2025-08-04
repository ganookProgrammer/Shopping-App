package com.example.myshoppingadmin.data_layer.di

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModules {

    @Provides
    fun provideFirestoreInstance() : FirebaseFirestore{
        return FirebaseFirestore.getInstance()
    }

    @Provides
    fun provideStorageInstance() : FirebaseStorage{
        return FirebaseStorage.getInstance()
    }
}