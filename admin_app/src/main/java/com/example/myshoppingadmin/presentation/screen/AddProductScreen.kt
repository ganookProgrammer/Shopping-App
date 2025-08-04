package com.example.myshoppingadmin.presentation.screen

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.myshoppingadmin.common.DropDownMenu
import com.example.myshoppingadmin.domain_layer.model.ProductModel
import com.example.myshoppingadmin.presentation.viewmodel.MyViewModel

@Composable
fun AddProductScreenUI(viewModel: MyViewModel = hiltViewModel()) {

    val addProductState = viewModel.addProductState.collectAsState().value
    val uploadImageState = viewModel.uploadImageState.collectAsState().value


    val context = LocalContext.current

    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var imageUrl by remember { mutableStateOf("") }

    val launch = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) {
        if (it != null) {
            viewModel.uploadProductImage(it)
            imageUri = it
        }
    }


    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var actualPrice by remember { mutableStateOf("") }

    var category by remember { mutableStateOf("") }
//        var image by remember { mutableStateOf("") }
    var availableUnits by remember { mutableStateOf(0) }
    var isAvailable = remember { mutableStateOf(true) }


    when {
        addProductState.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        addProductState.error.isNotEmpty() -> {
            Text(text = addProductState.error)
        }

        addProductState.data.isNotEmpty() -> {
            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top,

                    ) {


                    when {
                        uploadImageState.isLoading -> {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                                    .padding(10.dp)
                                    .clip(RoundedCornerShape(8.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }

                        uploadImageState.data != null -> {
                            imageUrl = uploadImageState.data

                            if (imageUri != null) {
                                AsyncImage(
                                    model = imageUrl,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(200.dp)
                                        .padding(10.dp)
                                        .border(width = 1.dp, Color.DarkGray)
                                        .clip(RoundedCornerShape(8.dp)),
                                    contentScale = ContentScale.Crop
                                )
                            } else {

                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(200.dp)
                                        .padding(10.dp)
                                        .border(width = 1.dp, Color.DarkGray)
                                        .clip(RoundedCornerShape(8.dp)),
                                    contentAlignment = Alignment.Center
                                ) {

                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center,
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .clickable {
                                                launch.launch(
                                                    PickVisualMediaRequest(
                                                        mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly
                                                    )
                                                )
                                            }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Rounded.Add,
                                            contentDescription = null,
                                            modifier = Modifier.clickable {

                                            })

                                        Text(text = "Add Image")
                                    }
                                }
                            }


                        }
                    }



                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {


                        Text(
                            text = "Add Products",
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.ExtraBold
                            )
                        )

                        Spacer(Modifier.height(10.dp))

                        OutlinedTextField(
                            value = title,
                            onValueChange = {
                                title = it
                            },
                            label = { Text(text = "Title") },
                            maxLines = 1,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Row(modifier = Modifier.fillMaxWidth()) {


                            OutlinedTextField(
                                value = price,
                                onValueChange = {
                                    price = it
                                },
                                label = { Text(text = "Price") },
                                maxLines = 1,
                                modifier = Modifier.weight(1f)
                            )
                            Spacer(Modifier.width(10.dp))
                            OutlinedTextField(
                                value = actualPrice,
                                onValueChange = {
                                    actualPrice = it
                                },
                                label = { Text(text = "ActualPrice") },
                                maxLines = 1,
                                modifier = Modifier.weight(1f)
                            )
                        }

                        OutlinedTextField(
                            value = description,
                            onValueChange = {
                                description = it
                            },
                            label = { Text(text = "Description") },
                            maxLines = 1,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                        )




                        DropDownMenu(onCategorySelected = {
                            category = it
                        })


                        OutlinedTextField(
                            value = availableUnits.toString(),
                            onValueChange = {
                                availableUnits = it.toIntOrNull() ?: 0
                            },
                            label = { Text(text = "Available Units") },
                            maxLines = 1,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(10.dp))



                        Button(
                            onClick = {
                                val addProduct = ProductModel(
                                    title = title,
                                    description = description,
                                    price = price,
                                    actualPrice = actualPrice,
                                    category = category,
                                    images = imageUrl,
                                    availableUnits = availableUnits.toInt(),
                                    isAvailable = isAvailable.value
                                )

                                if (title.isNotEmpty() && description.isNotEmpty()
                                    && price.isNotEmpty() && actualPrice.isNotEmpty()
                                    && category.isNotEmpty() && imageUrl.isNotEmpty()
                                    && availableUnits > 0
                                ) {
                                    viewModel.addProduct(addProduct)

                                    title = ""
                                    description = ""
                                    price = ""
                                    actualPrice = ""
                                    category = ""
                                    imageUrl = ""
                                    imageUri = null
                                    availableUnits = 0
                                    isAvailable.value = true
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Please fill the fields",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                            }, modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp)
                        ) { Text(text = "ADD PRODUCT") }
                    }
                }
            }
        }
    }
}