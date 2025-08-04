package com.example.myshoppinguser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.myshoppinguser.common.CustomAlertDialog
import com.example.myshoppinguser.common.custom
import com.example.myshoppinguser.presentation.navigation.AppNavigation
import com.example.myshoppinguser.presentation.screen.LoginScreen
import com.example.myshoppinguser.presentation.screen.ProductDetailsScreen
import com.example.myshoppinguser.ui.theme.MyShoppingUserTheme
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var firebaseAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


//        enableEdgeToEdge()
        setContent {
            MyShoppingUserTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
//                        HomeScreenUI()
//                        SignupScreenUI()
//                        LoginScreen()
//                        ProductDetailsScreen()
//                        AppNavigation(firebaseAuth)
                        custom()
                    }
                }
            }
        }
    }
}