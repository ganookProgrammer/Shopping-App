package com.example.myshoppingadmin.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myshoppingadmin.presentation.screen.AddCategoryScreenUI
import com.example.myshoppingadmin.presentation.screen.AddProductScreenUI
import com.example.myshoppingadmin.presentation.screen.HomeScreen

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {

    val navController = rememberNavController()

    NavHost(navController = navController ,startDestination = HomeScreen){
      composable<HomeScreen>{
          HomeScreen()
      }
      composable<AddProductScreen> {
          AddProductScreenUI()
      }
        composable<AddCategoryScreen> {
            AddCategoryScreenUI()
        }
    }
}