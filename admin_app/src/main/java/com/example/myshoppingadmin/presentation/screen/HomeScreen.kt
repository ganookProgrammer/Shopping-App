package com.example.myshoppingadmin.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEachIndexed
import com.example.myshoppingadmin.common.NavigationItems

@Composable
fun HomeScreen() {


    var selectedIndex by rememberSaveable {
        mutableStateOf(0)
    }

    val navItemsList = listOf<NavigationItems>(
        NavigationItems(name = "Add Product", Icons.Rounded.AddCircle),
        NavigationItems(name = "Add Category", Icons.Rounded.ShoppingCart)
    )


    Scaffold(
        bottomBar = {
            NavigationBar(
                modifier = Modifier
                    .padding(horizontal = 10.dp)

            ) {
                navItemsList.fastForEachIndexed { index, navItem ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = {
                            selectedIndex = index
                        },
                        icon = {
                            Icon(imageVector = navItem.icon, contentDescription = navItem.name)
                        },
                        label = {
                            Text(text = navItem.name)
                        },

                    )
                }
            }
        }
    ) { innerPadding ->


        Box(modifier = Modifier.padding(innerPadding))
        when (selectedIndex) {
            0 -> AddProductScreenUI()
            1 -> AddCategoryScreenUI()
        }
    }
}