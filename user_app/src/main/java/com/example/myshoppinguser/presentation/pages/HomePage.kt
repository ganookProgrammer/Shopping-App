package com.example.myshoppinguser.presentation.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myshoppinguser.common.BannerViewItems
import com.example.myshoppinguser.common.CategoriesItem
import com.example.myshoppinguser.common.CategoriesView
import com.example.myshoppinguser.common.HeaderViewItems
import com.example.myshoppinguser.common.ProductItems
import com.example.myshoppinguser.common.ProductView
import com.example.myshoppinguser.presentation.viewmodel.MyViewModel

@Composable
fun HomePage(modifier: Modifier = Modifier , viewModel: MyViewModel = hiltViewModel(),navController: NavController) {

            Column(modifier = Modifier.fillMaxSize()
                .padding(horizontal = 20.dp)){

                HeaderViewItems()

                Spacer(Modifier.height(20.dp))
                CategoriesView()

                Spacer(Modifier.height(20.dp))
                CategoriesItem()

                Spacer(Modifier.height(10.dp))
                BannerViewItems()

                Spacer(Modifier.height(20.dp))
                ProductView()

                Spacer(Modifier.height(20.dp))
                ProductItems(navController = navController)

            }
        }
