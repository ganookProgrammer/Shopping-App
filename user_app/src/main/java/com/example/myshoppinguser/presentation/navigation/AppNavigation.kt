package com.example.myshoppinguser.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.myshoppinguser.common.CustomAlertDialog
import com.example.myshoppinguser.presentation.pages.ProfilePage
import com.example.myshoppinguser.presentation.screen.HomeScreenUI
import com.example.myshoppinguser.presentation.screen.LoginScreen
import com.example.myshoppinguser.presentation.screen.ProductDetailsScreen
import com.example.myshoppinguser.presentation.screen.SignupScreenUI
import com.google.firebase.auth.FirebaseAuth

@Composable
fun AppNavigation(firebaseAuth: FirebaseAuth) {

    val navController = rememberNavController()

    val startScreen = if (firebaseAuth.currentUser !=null)
        SubNavigation.MainHomeScreen
    else
        SubNavigation.LoginSignupScreen

    NavHost(navController, startDestination = startScreen) {

        navigation<SubNavigation.LoginSignupScreen>(startDestination = Routes.LoginScreen) {
            composable<Routes.LoginScreen> {
                LoginScreen(navController = navController)
            }

            composable<Routes.SignUpScreen> {
                SignupScreenUI(navController = navController)
            }
        }

        navigation<SubNavigation.MainHomeScreen>(startDestination = Routes.HomeScreen) {
            composable<Routes.HomeScreen>{
                HomeScreenUI(navController = navController)
            }

            composable<Routes.ProfileScreen>{
                ProfilePage()
            }
        }

        composable<Routes.ProductDetailsScreen> {
            val data = it.toRoute<Routes.ProductDetailsScreen>()

            ProductDetailsScreen(
                productId = data.productId
            )
        }

        composable<Routes.RegisterAlertDialog> {
            CustomAlertDialog(navController)
        }
    }

}