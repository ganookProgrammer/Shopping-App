package com.example.myshoppinguser.presentation.navigation

import kotlinx.serialization.Serializable

sealed class SubNavigation{

    @Serializable
    object MainHomeScreen : SubNavigation()

    @Serializable
    object LoginSignupScreen : SubNavigation()
}

sealed class Routes{

    @Serializable
    object LoginScreen

    @Serializable
    object SignUpScreen

    @Serializable
    object HomeScreen

    @Serializable
    object ProfileScreen

    @Serializable
    object WishListScreen

    @Serializable
    object CartScreen

    @Serializable
    object CheckoutScreen

    @Serializable
    object PaymentScreen

    @Serializable
    object SeeAllProductScreen

    @Serializable
    data class ProductDetailsScreen(val productId : String)

    @Serializable
    object AllCategoryScreen

    @Serializable
    object CategoryItemsScreen

    @Serializable
    object RegisterAlertDialog
}