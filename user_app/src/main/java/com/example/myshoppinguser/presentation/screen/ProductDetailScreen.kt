package com.example.myshoppinguser.presentation.screen

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
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
fun ProductDetailsScreen(viewModel: MyViewModel = hiltViewModel()) {

    val productState = viewModel.getProductById.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.getProductById("oBMh2pcBPuq5uTSIZDRP")
    }

    when {
        productState.isLoading -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }

        productState.data != null -> {
            ProductDetailsCard(productState.data)
        }
    }
}


@Composable
fun ProductDetailsCard(product: Product) {

    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {

        // 🖼️ IMAGE + OVERLAY TITLE + PRICE
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) { // Fixed height


            AsyncImage(
                model = product.images,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )

            // Gradient Blur at Bottom
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.White.copy(alpha = 0.8f)
                            )
                        )
                    )
            )

            // 🔽 Overlay Text Content
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 16.dp, bottom = 16.dp)
            ) {
                Text(
                    text = product.title,
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Row {
                    repeat(5) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = Color.Yellow,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Rs: ",
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                    Text(
                        text = product.actualPrice,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }
        }

        // ⬇️ Rest of Details Content (OUTSIDE of Box)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Size",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "See more",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = customPink
                )

            }
            Spacer(Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        contentAlignment = Alignment.Center, modifier = Modifier
                            .size(30.dp)
                            .border(1.dp, customPink, RoundedCornerShape(5.dp))
                    ) {
                        Text(
                            "UK 8",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Box(
                        contentAlignment = Alignment.Center, modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .size(30.dp)
                            .border(1.dp, customPink, RoundedCornerShape(5.dp))
                    ) {
                        Text(
                            "UK 10",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Box(
                        contentAlignment = Alignment.Center, modifier = Modifier
                            .size(30.dp)
                            .border(1.dp, customPink, RoundedCornerShape(5.dp))
                    ) {
                        Text(
                            "UK 12",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "+",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold

                    )
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .size(30.dp)
                            .border(1.dp, customPink, RoundedCornerShape(5.dp))
                    ) {
                        Text(
                            text = "1",
                            fontSize = 15.sp
                        )
                    }

                    Text(
                        text = "-",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

            }

            Spacer(Modifier.height(20.dp))
            Column {
                Text(
                    text = "Color",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Row(Modifier.padding(top = 10.dp)) {
                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .background(customPink, RoundedCornerShape(5.dp))
                    )

                    Box(
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .size(30.dp)
                            .background(Color(0xff3DC6AD), RoundedCornerShape(5.dp))
                    )

                    Box(
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .size(30.dp)
                            .background(Color(0xFF27A624), RoundedCornerShape(5.dp))
                    )

                    Box(
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .size(30.dp)
                            .background(Color(0xffFBE417), RoundedCornerShape(5.dp))
                    )

                }
            }
            Spacer(Modifier.height(20.dp))

            Column(verticalArrangement = Arrangement.SpaceEvenly) {
                Text(
                    "Specification",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )


                Spacer(Modifier.height(10.dp))
                Text(
                    text = product.category,
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat))
                )

                Spacer(Modifier.height(10.dp))

                Text(
                    text = product.description,
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat))
                )

                Spacer(Modifier.height(10.dp))

                Button(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = customPink
                    )
                ) {
                    Text("Buy now")
                }

                Spacer(Modifier.height(10.dp))

                Button(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = customBlack
                    )
                ) {
                    Text("Add to Cart")
                }

                Spacer(Modifier.height(10.dp))

                TextButton(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = customPink
                    )
                ) {

                    Icon(Icons.Filled.FavoriteBorder, contentDescription = null)

                    Spacer(Modifier.width(6.dp))
                    Text(
                        "Add to Wishlist",
                        fontWeight = FontWeight.Bold
                    )

                }

            }

        }
        // Rest of your code...
        // ⬇️ you can paste the same size/color/button rows here
    }
}


//val listProduct =
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
//
////@Preview(showSystemUi = true)
//@Composable
//fun customCompose() {
//    ProductDetailsCard(product = listProduct)
//}
