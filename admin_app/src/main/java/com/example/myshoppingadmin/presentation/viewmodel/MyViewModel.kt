package com.example.myshoppingadmin.presentation.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myshoppingadmin.common.ResultState
import com.example.myshoppingadmin.domain_layer.model.CategoryModel
import com.example.myshoppingadmin.domain_layer.model.ProductModel
import com.example.myshoppingadmin.domain_layer.repo.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(val repo: Repo) : ViewModel() {

    private val _addCategoryState = MutableStateFlow(AddCategoryState())
    val addCategoryState = _addCategoryState.asStateFlow()

    private val _addProductState = MutableStateFlow(AddProductState())
    val addProductState = _addProductState.asStateFlow()

    private val _getCategories = MutableStateFlow(GetCategoriesState())
    val getCategoriesState = _getCategories.asStateFlow()

    private val _uploadImage = MutableStateFlow(UploadProductImageState())
    val uploadImageState = _uploadImage.asStateFlow()

    private val _uploadCategoryImageState = MutableStateFlow(UploadCategoryImageState())
    val uploadCategoryImageState = _uploadCategoryImageState.asStateFlow()

    fun uploadCategoryImage(image:Uri){
        viewModelScope.launch {
            repo.categoryImage(image).collectLatest {
                when(it){
                    is ResultState.Loading -> _uploadCategoryImageState.value =
                        UploadCategoryImageState(isLoading = true)

                    is ResultState.Error -> _uploadCategoryImageState.value =
                        UploadCategoryImageState(isLoading = false , error = it.error)

                    is ResultState.Success -> _uploadCategoryImageState.value =
                        UploadCategoryImageState(isLoading = false , data = it.data)
                }
            }
        }
    }

    fun uploadProductImage(image: Uri) {
        viewModelScope.launch {
            repo.uploadImage(image).collectLatest {
                when (it) {
                    is ResultState.Loading -> _uploadImage.value =
                        UploadProductImageState(isLoading = true)

                    is ResultState.Error -> _uploadImage.value =
                        UploadProductImageState(isLoading = false, error = it.error)

                    is ResultState.Success<*> -> _uploadImage.value =
                        UploadProductImageState(isLoading = false , data = it.data.toString())
                }

            }
        }
    }


    fun getCategories() {
        viewModelScope.launch {
            repo.getCategories().collectLatest {
                when (it) {
                    is ResultState.Loading -> _getCategories.value =
                        GetCategoriesState(isLoading = true)

                    is ResultState.Error -> _getCategories.value =
                        GetCategoriesState(isLoading = false, error = it.error)

                    is ResultState.Success -> _getCategories.value =
                        GetCategoriesState(isLoading = false, data = it.data)
                }
            }
        }
    }

    fun addCategory(category: CategoryModel) {
        viewModelScope.launch {
            repo.addCategory(category).collectLatest {
                when (it) {
                    is ResultState.Loading -> _addCategoryState.value =
                        AddCategoryState(isLoading = true)

                    is ResultState.Error -> _addCategoryState.value =
                        AddCategoryState(error = it.error, isLoading = false)

                    is ResultState.Success -> _addCategoryState.value =
                        AddCategoryState(data = it.data, isLoading = false)
                }
            }
        }
    }


    fun addProduct(product: ProductModel) {
        viewModelScope.launch {
            repo.addProduct(product).collectLatest { state ->
                when (state) {
                    is ResultState.Loading -> _addProductState.value =
                        AddProductState(isLoading = true)

                    is ResultState.Error -> _addProductState.value =
                        AddProductState(isLoading = false, error = state.error)

                    is ResultState.Success -> _addProductState.value =
                        AddProductState(isLoading = false, data = state.data)
                }
            }
        }
    }
}

data class AddCategoryState(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: String = ""

)

data class AddProductState(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: String = " "

)

data class GetCategoriesState(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: List<CategoryModel?> = emptyList<CategoryModel>()
)

data class UploadProductImageState(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: String = ""
)

data class UploadCategoryImageState(
    val isLoading: Boolean = false,
    val error : String = "",
    val data : String = ""
)
