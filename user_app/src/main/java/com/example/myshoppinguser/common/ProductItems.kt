package com.example.myshoppinguser.common

import android.R.attr.text
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.myshoppinguser.R
import com.example.myshoppinguser.domain.models.Product
import com.example.myshoppinguser.presentation.viewmodel.MyViewModel
import com.example.myshoppinguser.ui.theme.customBlack
import com.example.myshoppinguser.ui.theme.customPink

@Composable
fun ProductItems(viewModel: MyViewModel = hiltViewModel()) {

    val productState = viewModel.getAllProductState.collectAsState().value

    LaunchedEffect(key1 = Unit) {
        viewModel.getAllProduct()
    }


    when {
        productState.isLoading -> {
            Box(modifier = Modifier.fillMaxWidth()) {
                CircularProgressIndicator()
            }
        }

        productState.data != null -> {
            LazyRow(modifier = Modifier.fillMaxWidth()) {
                items(productState.data) { product ->
                    ProductCard(product)
                }
            }
        }
    }

}

@Composable
fun ProductCard(product: Product) {

    val montserratFontFamily = FontFamily(
        Font(R.font.montserrat, FontWeight.Normal)
    )


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 20.dp)
    ) {
        Column {
            Card(
                onClick = {},
                modifier = Modifier
                    .width(120.dp)
                    .height(140.dp)
            ) {

                AsyncImage(
                    model = product.images,
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth
                )

            }

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedCard(
                onClick = {},
                modifier = Modifier
                    .width(120.dp)
                    .height(130.dp)
                    .border(width = 1.dp, customBlack, shape = RoundedCornerShape(8.dp)),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(8.dp)
            ) {

                Column(
                    modifier = Modifier
                        .padding(top = 4.dp, bottom = 8.dp, start = 6.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = product.title,
                        color = customBlack,
                        fontFamily = montserratFontFamily,
                        fontSize = 12.sp,
                        maxLines = 2,
                        modifier = Modifier.padding(end = 6.dp)
                    )
                    Text(
                        text = product.category,
                        color = customBlack,
                        fontSize = 12.sp
                    )

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Rs: ",
                            fontSize = 10.sp,
                            color = customPink
                        )
                        Text(
                            text = product.actualPrice,
                            fontWeight = FontWeight.Bold,
                            color = customPink,
                        )

                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Rs: ",
                            fontSize = 10.sp,

                            )
                        Text(
                            text = product.actualPrice,
                            textDecoration = TextDecoration.LineThrough

                        )


                        Text(
                            text = "20% off",
                            color = customPink,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(start = 2.dp)
                        )

                    }

                }
            }
        }
    }
}


//val listProduct = listOf<Product>(
//    Product(
//        productId = "sdfdfslfsfssff",
//        title = "One Shoulder Linen Dress",
//        description = "A dress is a one-piece garment typically worn by women, consisting of a bodice (top) and a skirt (bottom). Dresses come in a variety of styles, lengths, and designs,",
//        price = "7180",
//        actualPrice = "5740",
//        category = "dresses",
//        images = "",
//        availableUnits = 5,
//        isAvailable = true,
//        date = "01/04/2005"
//
//    )
//)

