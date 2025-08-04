package com.example.myshoppingadmin.data_layer.repoImpl


import android.net.Uri
import com.example.myshoppingadmin.common.CATEGORY
import com.example.myshoppingadmin.common.PRODUCT
import com.example.myshoppingadmin.common.ResultState
import com.example.myshoppingadmin.domain_layer.model.CategoryModel
import com.example.myshoppingadmin.domain_layer.model.ProductModel
import com.example.myshoppingadmin.domain_layer.repo.Repo
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class RepoImpl @Inject constructor(
    val firestore: FirebaseFirestore,
    val storage : FirebaseStorage
) : Repo {
    override suspend fun addCategory(category: CategoryModel): Flow<ResultState<String>> =
        callbackFlow {
            trySend(ResultState.Loading)

            firestore.collection(CATEGORY).add(category).addOnSuccessListener {

                trySend(ResultState.Success("Category Added Successfully"))
            }.addOnFailureListener {
                trySend(ResultState.Error(it.toString()))
            }
            awaitClose {
                close()
            }
        }

    override suspend fun addProduct(product: ProductModel): Flow<ResultState<String>> = callbackFlow {
        trySend(ResultState.Loading)

        firestore.collection(PRODUCT).add(product).addOnSuccessListener {
            trySend(ResultState.Success("Product Add Successfully"))
        }.addOnFailureListener {
            trySend(ResultState.Error(it.toString()))
        }
        awaitClose {
            close()
        }
    }

    override suspend fun getCategories(): Flow<ResultState<List<CategoryModel>>> = callbackFlow {

        trySend(ResultState.Loading)

        firestore.collection(CATEGORY).get().addOnSuccessListener {

            val categories = it.documents.mapNotNull {
                it.toObject(CategoryModel::class.java)
            }

            trySend(ResultState.Success(categories))
        }.addOnFailureListener {
            trySend(ResultState.Error(it.toString()))

        }
        awaitClose{
            close()
        }
    }

    override suspend fun uploadImage(image: Uri): Flow<ResultState<String>> = callbackFlow {

        trySend(ResultState.Loading)

        storage.reference.child("Products/${System.currentTimeMillis()}")
            .putFile(image).addOnSuccessListener {
                it.storage.downloadUrl.addOnSuccessListener {
                    trySend(ResultState.Success(it.toString()))
                }
                if (it.error !=null){
                    trySend(ResultState.Error(it.error!!.localizedMessage))
                }
            }
        awaitClose{
            close()
        }
    }

    override suspend fun categoryImage(image: Uri): Flow<ResultState<String>> = callbackFlow{
        trySend(ResultState.Loading)

        storage.reference.child("Category/${System.currentTimeMillis()}")
            .putFile(image).addOnSuccessListener {
                it.storage.downloadUrl.addOnSuccessListener {
                    trySend(ResultState.Success(it.toString()))
                }
                if (it.error !=null){
                    trySend(ResultState.Error(it.error!!.localizedMessage))
                }
            }
        awaitClose{
            close()
        }
    }

}