package com.example.myshoppinguser.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myshoppinguser.state.ResultState
import com.example.myshoppinguser.domain.models.Category
import com.example.myshoppinguser.domain.models.Product
import com.example.myshoppinguser.domain.models.User
import com.example.myshoppinguser.domain.use_case.GetAllCategoryUseCase
import com.example.myshoppinguser.domain.use_case.GetAllProductUseCase
import com.example.myshoppinguser.domain.use_case.GetBannerImageUseCase
import com.example.myshoppinguser.domain.use_case.GetProductByIdUseCase
import com.example.myshoppinguser.domain.use_case.GetUserInformationUseCase
import com.example.myshoppinguser.domain.use_case.LoginWithEmailAndPassUseCase
import com.example.myshoppinguser.domain.use_case.RegisterUserWithEmailPassUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val repo: GetAllCategoryUseCase,
    private val registerUserWithEmailPassUseCase: RegisterUserWithEmailPassUseCase,
    private val loginWithEmailAndPassUseCase: LoginWithEmailAndPassUseCase,
    private val getBannerImageUseCase: GetBannerImageUseCase,
    private val getAllProductUseCase: GetAllProductUseCase,
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val getUserInformationUseCase: GetUserInformationUseCase
) : ViewModel() {

    private val _getCategoryState = MutableStateFlow(GetCategoryState())
    val getCategoryState = _getCategoryState.asStateFlow()

    private val _registerState = MutableStateFlow(RegisterState())
    val registerState = _registerState.asStateFlow()

    private val _loginState = MutableStateFlow(LoginState())
    val loginState = _loginState.asStateFlow()

    private val _getBannerState = MutableStateFlow(GetBannerImageState())
    val getBannerState = _getBannerState.asStateFlow()

    private val _getAllProductState = MutableStateFlow(GetAllProductState())
    val getAllProductState = _getAllProductState.asStateFlow()

    private val _getProductById = MutableStateFlow(GetProductByIdState())
    val getProductById = _getProductById.asStateFlow()

    private val _getUserInformationState = MutableStateFlow(GetUserInformationState())
    val getUserInformationState = _getUserInformationState.asStateFlow()

    fun restUserInformation() {
        _getUserInformationState.value = GetUserInformationState()
    }

    fun resetProductById() {
        _getProductById.value = GetProductByIdState()
    }

    fun resetProductState() {
        _getAllProductState.value = GetAllProductState()
    }

    fun resetBannerState() {
        _getBannerState.value = GetBannerImageState()
    }

    fun resetRegisterState() {
        _registerState.value = RegisterState()
    }

    fun resetLoginState() {
        _loginState.value = LoginState()
    }

    fun getUserInformation() {
        viewModelScope.launch {
            getUserInformationUseCase.getUserInformationUseCase().collectLatest {
                when (it) {
                    is ResultState.Loading -> _getUserInformationState.value =
                        GetUserInformationState(isLoading = true)
                    is ResultState.Error -> _getUserInformationState.value =
                        GetUserInformationState(isLoading = false , error = it.error)
                   is ResultState.Success -> _getUserInformationState.value =
                       GetUserInformationState(data = it.data)
                }
            }
        }
    }

    fun getProductById(productId: String) {
        viewModelScope.launch {
            getProductByIdUseCase.getProductByIdUseCase(productId).collectLatest {
                when (it) {
                    is ResultState.Error -> _getProductById.value =
                        GetProductByIdState(isLoading = false, error = it.error)

                    is ResultState.Loading -> _getProductById.value =
                        GetProductByIdState(isLoading = true)

                    is ResultState.Success -> _getProductById.value =
                        GetProductByIdState(isLoading = false, data = it.data)
                }
            }
        }
    }

    fun getAllProduct() {
        viewModelScope.launch {
            getAllProductUseCase.getAllProductUseCase().collectLatest {
                when (it) {
                    is ResultState.Error -> _getAllProductState.value =
                        GetAllProductState(isLoading = false, error = it.error)

                    is ResultState.Loading -> _getAllProductState.value =
                        GetAllProductState(isLoading = true)

                    is ResultState.Success -> _getAllProductState.value =
                        GetAllProductState(isLoading = false, data = it.data)
                }
            }
        }
    }

    fun getBannerImages() {
        viewModelScope.launch {
            getBannerImageUseCase.getBannerImageUseCase().collectLatest {
                when (it) {
                    is ResultState.Error -> _getBannerState.value =
                        GetBannerImageState(isLoading = false, error = it.error)

                    is ResultState.Loading -> _getBannerState.value =
                        GetBannerImageState(isLoading = true)

                    is ResultState.Success -> _getBannerState.value =
                        GetBannerImageState(data = it.data)
                }
            }
        }
    }

    fun loginWithEmailAndPass(
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            loginWithEmailAndPassUseCase.loginWithEmailAndPassUseCase(
                email = email,
                password = password
            ).collectLatest {
                when (it) {
                    is ResultState.Loading -> _loginState.value = LoginState(isLoading = true)
                    is ResultState.Error -> _loginState.value =
                        LoginState(isLoading = false, error = it.error)

                    is ResultState.Success -> _loginState.value =
                        LoginState(isLoading = false, data = it.data)
                }
            }
        }
    }

    fun registerUser(user: User) {
        viewModelScope.launch {
            registerUserWithEmailPassUseCase.registerUserWithEmailPass(user).collectLatest {
                when (it) {
                    is ResultState.Error -> _registerState.value =
                        RegisterState(isLoading = false, error = it.error)

                    is ResultState.Success -> _registerState.value =
                        RegisterState(isLoading = false, data = it.data)

                    is ResultState.Loading -> _registerState.value = RegisterState(isLoading = true)
                }
            }
        }
    }


    fun getAllCategory() {
        viewModelScope.launch {
            repo.getCategoryUseCase().collectLatest { state ->
                when (state) {
                    is ResultState.Loading -> _getCategoryState.value =
                        GetCategoryState(isLoading = true)

                    is ResultState.Error -> _getCategoryState.value =
                        GetCategoryState(error = state.error, isLoading = false)

                    is ResultState.Success -> _getCategoryState.value =
                        GetCategoryState(data = state.data, isLoading = false)
                }
            }
        }
    }

}

data class LoginState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val data: String? = null
)

data class GetCategoryState(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: List<Category?> = emptyList()
)

data class RegisterState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val data: String? = null
)

data class GetBannerImageState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val data: List<String> = emptyList()
)

data class GetAllProductState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val data: List<Product>? = emptyList()
)

data class GetProductByIdState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val data: Product? = null
)

data class GetUserInformationState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val data: User? = null
)


//data class HomeScreenState(
//    val isLoading: Boolean = true,
//    val error: String? = null,
//    val categories : List<Category>? =null,
//    val products: List<Product>? = null
//)