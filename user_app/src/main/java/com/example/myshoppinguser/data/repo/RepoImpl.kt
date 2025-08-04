package com.example.myshoppinguser.data.repo

import androidx.compose.runtime.mutableStateOf
import com.example.myshoppinguser.common.CATEGORY
import com.example.myshoppinguser.common.PRODUCT
import com.example.myshoppinguser.common.USERS
import com.example.myshoppinguser.state.ResultState
import com.example.myshoppinguser.domain.models.Category
import com.example.myshoppinguser.domain.models.Product
import com.example.myshoppinguser.domain.models.User
import com.example.myshoppinguser.domain.repo.Repo
import com.google.android.gms.auth.api.signin.internal.Storage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.plus
import javax.inject.Inject
import kotlin.collections.mutableListOf

class RepoImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth,
    private val storage: FirebaseStorage
) : Repo {
    override fun registerUserWithEmailPass(user: User): Flow<ResultState<String>> = callbackFlow {

        trySend(ResultState.Loading)

        auth.createUserWithEmailAndPassword(user.email, user.password).addOnSuccessListener {
            firestore.collection(USERS).document(it.user?.uid.toString()).set(user)
                .addOnSuccessListener {
                    trySend(ResultState.Success("User Registered Successfully"))
                }.addOnFailureListener {
                    trySend(ResultState.Error(it.toString()))
                }
        }.addOnFailureListener {
            trySend(ResultState.Error(it.toString()))
        }
        awaitClose {
            close()
        }
    }

    override fun loginUserWithEmailPass(
        email: String,
        password: String
    ): Flow<ResultState<String>> = callbackFlow {

        trySend(ResultState.Loading)

        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
            trySend(ResultState.Success("User Logged In Successfully"))
        }.addOnFailureListener {
            trySend(ResultState.Error(it.toString()))
        }
        awaitClose {
            close()
        }
    }

    override fun getAllCategory(): Flow<ResultState<List<Category>>> = callbackFlow {
        trySend(ResultState.Loading)

        val listener = firestore.collection(CATEGORY).addSnapshotListener { snapshot, error ->
            if (error != null) {
                trySend(ResultState.Error(error.message ?: "Unknown error"))
                return@addSnapshotListener
            }
            val list = snapshot?.documents?.mapNotNull {
                it.toObject(Category::class.java)
            } ?: emptyList()

            trySend(ResultState.Success(list))
        }
        awaitClose {
            listener.remove()
        }

    }

    override fun getBannerImage(): Flow<ResultState<List<String>>> = callbackFlow {
        trySend(ResultState.Loading)

        storage.reference.child("Banner/").listAll()
            .addOnSuccessListener {

                val imageUrls = mutableListOf<String>()

                val items = it.items

                if (items.isEmpty()) {
                    trySend(ResultState.Success(emptyList()))
                    close()
                    return@addOnSuccessListener
                }

                var completeCount = 0

                items.forEach { storageRef ->
                    storageRef.downloadUrl
                        .addOnSuccessListener {
                            imageUrls.add(it.toString())
                            completeCount++
                            if (completeCount == items.size) {
                                trySend(ResultState.Success(imageUrls))
                                close()
                            }
                        }
                        .addOnFailureListener { e ->
                            trySend(ResultState.Error(e.message ?: "Failed to get download URL"))
                            close()
                        }
                }
            }
            .addOnFailureListener { exception ->
                trySend(ResultState.Error(exception.message ?: "Failed to list banner images"))
                close()
            }

        awaitClose { /* No-op */ }
    }

    override fun getAllProducts(): Flow<ResultState<List<Product>>> = callbackFlow {
        trySend(ResultState.Loading)

        firestore.collection(PRODUCT).addSnapshotListener { snapshot, error ->

            val product = snapshot?.documents?.mapNotNull {
                val product = it.toObject(Product::class.java)
                product?.copy(productId = it.id)
            } ?: emptyList()

            if (error != null) {
                trySend(ResultState.Error(error.toString()))
            }
            trySend(ResultState.Success(product.plus(product)))

        }
        awaitClose {
            close()
        }
    }

    override fun getProductById(productId: String): Flow<ResultState<Product>> = callbackFlow {
        trySend(ResultState.Loading)

        firestore.collection(PRODUCT).document(productId).get().addOnSuccessListener {

            val product = it.toObject(Product::class.java)

            trySend(ResultState.Success(product!!))
        }.addOnFailureListener {
            trySend(ResultState.Error(it.toString()))
        }
        awaitClose{
            close()
        }
    }
}

