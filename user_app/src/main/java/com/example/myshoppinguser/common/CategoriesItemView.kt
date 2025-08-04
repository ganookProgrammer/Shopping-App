package com.example.myshoppinguser.common

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.myshoppinguser.domain.models.Category
import com.example.myshoppinguser.R
import com.example.myshoppinguser.presentation.viewmodel.MyViewModel
import com.example.myshoppinguser.ui.theme.customBlack
import com.example.myshoppinguser.ui.theme.customPink

@Composable
fun CategoriesItem(modifier: Modifier = Modifier, viewModel: MyViewModel = hiltViewModel()) {

    val categoriesState = viewModel.getCategoryState.collectAsState().value

    LaunchedEffect(key1 = Unit) {
        viewModel.getAllCategory()
    }


    Log.d("TAG", "CategoriesItem: ${categoriesState.data}")
    when {
        categoriesState.isLoading -> {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        categoriesState.error.isNotEmpty() -> {

        }

        categoriesState.data != null -> {

            LazyRow(
                modifier = Modifier.fillMaxWidth()
                ,
                horizontalArrangement = Arrangement.Absolute.SpaceBetween
            ) {
                items(categoriesState.data) {

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .clip(CircleShape)

                                .border(width = 1.dp, color = customBlack, shape = CircleShape),

                            contentAlignment = Alignment.Center
                        ) {
                            AsyncImage(
                                model = it!!.imgUri,
                                contentDescription = it.name,
                                modifier = Modifier.size(30.dp)
                            )
                        }

                        Text(
                            text = it!!.name,
                            color = customBlack
                        )

                    }
                }
            }
        }
    }

}

