package com.example.myshoppinguser.presentation.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.fastForEachIndexed
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myshoppinguser.presentation.pages.CartPage
import com.example.myshoppinguser.presentation.pages.FavoritePage
import com.example.myshoppinguser.presentation.pages.HomePage
import com.example.myshoppinguser.presentation.pages.ProfilePage
import com.example.myshoppinguser.presentation.viewmodel.MyViewModel

@Composable
fun HomeScreenUI(viewModel: MyViewModel = hiltViewModel()) {


    var selectedIndex by remember {
        mutableStateOf(0)
    }

    val naviItems = listOf<NavigationItems>(
        NavigationItems(name = "Home", Icons.Rounded.Home),
        NavigationItems(name = "Favourite", Icons.Rounded.FavoriteBorder),
        NavigationItems(name = "Cart", Icons.Rounded.ShoppingCart),
        NavigationItems(name = "Profile", Icons.Filled.Person),

        )
    Scaffold(
        bottomBar = {
            NavigationBar (
                modifier = Modifier.height(56.dp)
            ){

                naviItems.fastForEachIndexed { index, navItem ->

                        NavigationBarItem(
                            selected = selectedIndex == index,
                            onClick = {
                                selectedIndex = index
                            },
                            icon = {
                                Icon(
                                    imageVector = navItem.icon, contentDescription = navItem.name,
                                    modifier = Modifier.size(24.dp)
                                )
                            },

                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color.Black,        // Icon color when selected

                            ),
                        )
                }
            }
        }

    ) {
        it

        Items(modifier = Modifier, selectedIndex)


    }
    }

@Composable
fun Items(modifier: Modifier = Modifier , selectedIndex : Int) {
    when(selectedIndex){

        0 -> HomePage(modifier)
        1 -> FavoritePage(modifier)
        2 -> CartPage(modifier)
        3 -> ProfilePage(modifier)
    }
}
data class NavigationItems(
    val name : String,
    val icon : ImageVector
)
