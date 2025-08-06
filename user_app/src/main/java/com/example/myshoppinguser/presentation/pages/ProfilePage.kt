package com.example.myshoppinguser.presentation.pages

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.myshoppinguser.common.ProfileEdit

@Composable
fun ProfilePage(modifier: Modifier = Modifier , navController: NavController) {
    ProfileEdit(navController = navController)
}
