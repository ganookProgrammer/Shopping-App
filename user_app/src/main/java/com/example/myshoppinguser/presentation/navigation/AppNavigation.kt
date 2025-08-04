package com.example.myshoppinguser.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.myshoppinguser.presentation.navigation.SubNavigation
import com.example.myshoppinguser.presentation.pages.ProfilePage
import com.example.myshoppinguser.presentation.screen.HomeScreenUI
import com.example.myshoppinguser.presentation.screen.LoginScreen
import com.example.myshoppinguser.presentation.screen.SignupScreenUI
import com.google.firebase.auth.FirebaseAuth
import okhttp3.Route

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
                HomeScreenUI()
            }

            composable<Routes.ProfileScreen>{
                ProfilePage()
            }
        }
    }

}