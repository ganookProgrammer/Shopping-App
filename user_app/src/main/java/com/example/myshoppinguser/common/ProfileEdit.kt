package com.example.myshoppinguser.common

import android.R.attr.contentDescription
import android.R.attr.password
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.asLiveData
import androidx.navigation.NavController
import androidx.navigation.navArgument
import coil.compose.AsyncImage
import com.example.myshoppinguser.R
import com.example.myshoppinguser.domain.models.User
import com.example.myshoppinguser.presentation.navigation.Routes
import com.example.myshoppinguser.presentation.viewmodel.MyViewModel
import com.example.myshoppinguser.ui.theme.customBlack
import com.example.myshoppinguser.ui.theme.customPink

@Composable
fun ProfileEdit(navController: NavController,viewModel: MyViewModel = hiltViewModel()) {

    val state = viewModel.getUserInformationState.collectAsState().value


    LaunchedEffect(Unit) {
        viewModel.getUserInformation()
    }

    when {
        state.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        state.data != null -> {
            state.data.let { user ->
                ProfileCard(user = user, navController = navController)
            }
        }
    }



    val context = LocalContext.current

//    var firstName by remember { mutableStateOf("") }
//    var lastName by remember { mutableStateOf("") }
//    var email by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//    var address by remember { mutableStateOf("") }

}


@Composable
fun ProfileCard(modifier: Modifier = Modifier,user : User,navController: NavController) {
    var isEdit by remember { mutableStateOf(true) }


    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.corner),
            contentDescription = null,
            modifier = Modifier.align(Alignment.TopEnd)
        )

            Column(
                modifier = Modifier
                    .padding(
                        horizontal = 20.dp,
                        vertical = 20.dp
                    )
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start


            ) {

                Spacer(modifier = Modifier.height(30.dp))

                AsyncImage(
                    model = user?.userImage,
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(100.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    OutlinedTextField(
                        value = user.firstName.toString(),
                        onValueChange = { user.firstName = it },
                        label = { Text(text = "First Name") },
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = customPink,
                            unfocusedBorderColor = customPink
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        maxLines = 1,
                        readOnly = isEdit
                    )

                    OutlinedTextField(
                        value = user.lastName.toString(),
                        onValueChange = { user.lastName = it },
                        label = { Text(text = "Last Name") },
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = customPink,
                            unfocusedBorderColor = customPink
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        maxLines = 1,
                        readOnly = isEdit
                    )

                }
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = user.email,
                    onValueChange = { user.email = it },
                    label = { Text(text = "Email") },
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = customPink,
                        unfocusedBorderColor = customPink
                    ),
                    modifier = Modifier
                        .fillMaxWidth(),
                    maxLines = 1,
                    readOnly = isEdit
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = user.phoneNumber,
                    onValueChange = { user.phoneNumber = it },
                    label = { Text(text = "Phone number") },
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = customPink,
                        focusedBorderColor = customPink
                    ),
                    modifier = Modifier
                        .fillMaxWidth(),
                    maxLines = 1,
                    readOnly = isEdit
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = user.address,
                    onValueChange = { user.address = it },
                    label = { Text(text = "Address") },
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = customPink,
                        focusedBorderColor = customPink
                    ),
                    modifier = Modifier
                        .fillMaxWidth(),
                    maxLines = 1,
                    readOnly = isEdit

                )

                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = {
                        navController.navigate(Routes.AlertDialogLogout)
                    }, modifier = Modifier
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = customPink
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(text = "Log Out")
                }


                Spacer(modifier = Modifier.height(10.dp))
                OutlinedButton(
                    onClick = { isEdit = !isEdit },
                    modifier = Modifier.fillMaxWidth(),
                    border = BorderStroke(1.dp, customPink),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = customBlack,
                        containerColor = Color.Transparent
                    ),
                    contentPadding = PaddingValues(0.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {


                    // Text in center
                    Text(
                        text = if (isEdit) "Edit Profile" else "Update",
                        textAlign = TextAlign.Center
                    )
                }
            }

        Image(
            painter = painterResource(R.drawable.bottom_corner),
            contentDescription = null,
            modifier = Modifier.align(Alignment.BottomStart)
        )
    }
}
