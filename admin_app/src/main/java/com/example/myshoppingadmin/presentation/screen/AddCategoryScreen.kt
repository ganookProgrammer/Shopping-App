package com.example.myshoppingadmin.presentation.screen

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
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
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.myshoppingadmin.domain_layer.model.CategoryModel
import com.example.myshoppingadmin.presentation.viewmodel.MyViewModel

@Composable
fun AddCategoryScreenUI(viewModel: MyViewModel = hiltViewModel()) {

    val context = LocalContext.current
    val state = viewModel.addCategoryState.collectAsState().value
    val imgState = viewModel.uploadCategoryImageState.collectAsState().value

    var name by remember { mutableStateOf("") }

    var imageUri by remember { mutableStateOf<Uri?>(null) }

    var imageUrl by remember { mutableStateOf("") }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) {
        if (it != null) {
            viewModel.uploadCategoryImage(it)
            imageUri = it
        }
    }

    when {
        state.isLoading -> {
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        state.error.isNotEmpty() -> {
            Text("Something wrong")
        }

        state.data.isEmpty() || state.data.isNotEmpty() -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                when {
                    imgState.isLoading -> {
                        Box(
                            modifier = Modifier.size(90.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    imgState.data != null -> {
                        imageUrl = imgState.data

                        if (imageUri != null) {
                            AsyncImage(
                                model = imageUrl,
                                contentDescription = "image category",
                                modifier = Modifier
                                    .size(90.dp)
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )
                        } else {
                            Box(
                                modifier = Modifier
                                    .size(90.dp)
                                    .clip(CircleShape)
                                    .border(width = 1.dp, Color.DarkGray, shape = CircleShape)
                                    .clickable {
                                        launcher.launch(
                                            PickVisualMediaRequest(
                                                mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly
                                            )
                                        )
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Add,
                                    contentDescription = "Category profile"
                                )
                            }
                        }
                    }
                }

                Spacer(Modifier.height(15.dp))
                OutlinedTextField(
                    value = name,
                    onValueChange = {
                        name = it
                    },
                    label = { Text(text = "Name") },
                )



                Spacer(Modifier.height(10.dp))
                Button(
                    onClick = {
                        val category = CategoryModel(
                            name = name,
                            imgUri = imageUrl
                        )
                        if (imageUrl.isNotEmpty() && name.isNotEmpty()) {
                            viewModel.addCategory(category)

                            name = ""
                            imageUri = null
                            imageUrl = ""
                        } else {
                            Toast.makeText(context, "Please fill the fields", Toast.LENGTH_SHORT)
                                .show()
                        }

                    }, modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 50.dp), shape = RoundedCornerShape(8.dp)
                ) { Text(text = "Add Category") }


            }
        }


    }

}