package com.example.myshoppinguser.presentation.screen

import android.widget.Toast
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myshoppinguser.R
import com.example.myshoppinguser.common.CustomAlertDialog
import com.example.myshoppinguser.domain.models.User
import com.example.myshoppinguser.presentation.navigation.Routes
import com.example.myshoppinguser.presentation.viewmodel.MyViewModel
import com.example.myshoppinguser.ui.theme.customBlack
import com.example.myshoppinguser.ui.theme.customPink

@Composable
fun SignupScreenUI(viewModel: MyViewModel = hiltViewModel(),navController : NavController) {

    val state = viewModel.registerState.collectAsState().value

    val context = LocalContext.current

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
//        var address by remember { mutableStateOf("") }


    when {
        state.isLoading -> {
            Box(Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center){
                CircularProgressIndicator()

            }
        }

        state.error != null -> {
            Toast.makeText(context, state.error.toString(), Toast.LENGTH_SHORT).show()
            viewModel.resetRegisterState()
        }

        state.data != null -> {
            Toast.makeText(context, state.data.toString(), Toast.LENGTH_SHORT).show()
            viewModel.resetRegisterState()
            navController.navigate(Routes.RegisterAlertDialog)
            email = ""
            password = ""
            firstName = ""
            lastName = ""
            phoneNumber = ""


        }
    }

    if(!state.isLoading ) {
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
                    .align(Alignment.CenterStart)
                    .verticalScroll(rememberScrollState())
                    .padding(
                        horizontal = 20.dp,
                        vertical = 20.dp
                    )
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.Start


            ) {

                Text(
                    text = "Signup",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.SemiBold,
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    OutlinedTextField(
                        value = firstName,
                        onValueChange = { firstName = it },
                        label = { Text(text = "First Name") },
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = customPink,
                            unfocusedBorderColor = customPink
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )

                    OutlinedTextField(
                        value = lastName,
                        onValueChange = { lastName = it },
                        label = { Text(text = "Last Name") },
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = customPink,
                            unfocusedBorderColor = customPink
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )

                }
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text(text = "Email") },
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = customPink,
                        unfocusedBorderColor = customPink
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text(text = "Create Password") },
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = customPink,
                        focusedBorderColor = customPink
                    ),
                    modifier = Modifier
                        .fillMaxWidth()

                )

                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = { Text(text = "Confirm Password") },
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = customPink,
                        focusedBorderColor = customPink
                    ),
                    modifier = Modifier
                        .fillMaxWidth()

                )

                Button(
                    onClick = {
                        val data = User(
                            firstName = firstName,
                            lastName = lastName,
                            email = email,
                            password = password,
                             phoneNumber = phoneNumber
                        )

                        viewModel.registerUser(data)
//                        if (firstName.isNotBlank() && lastName.isNotBlank()
//                            && email.isNotBlank() && password.isNotBlank()
//                            && phoneNumber.isNotBlank() && confirmPassword.isNotBlank()
//                        ) {
//                            if (password == confirmPassword){
//                                viewModel.registerUser(data)
//                            }else{
//                                Toast.makeText(context, "password mismatch", Toast.LENGTH_SHORT).show()
//                            }
//
//
//                        }else {
//                            Toast.makeText(context, "something wrong", Toast.LENGTH_SHORT).show()
//                        }
                    }, modifier = Modifier
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = customPink
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(text = "Login")
                }
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Absolute.Center

                ){
                    Text(text = "Already have an account?",
                        fontSize = 15.sp,
                        color = customBlack)

                    Text(text = "Login",
                        fontSize = 15.sp,
                        color = customPink)
                }

                Row(modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically) {
                    Divider(color = Color.Black , thickness = 1.dp,
                        modifier = Modifier
                            .weight(1.0f)
                            .padding(start = 20.dp))
                    Text(text = "OR",
                        fontSize = 15.sp,
                        color = customBlack,
                        modifier = Modifier.padding(horizontal = 10.dp))
                    Divider(color = Color.Black , thickness = 1.dp,
                        modifier = Modifier
                            .weight(1.0f)
                            .padding(end = 20.dp))

                }

                OutlinedButton(onClick = {},
                    modifier = Modifier.fillMaxWidth()
                    ,
                    border = BorderStroke(1.dp , customPink),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = customBlack,
                        containerColor = Color.Transparent
                    ),
                    contentPadding = PaddingValues(0.dp),
                    shape = RoundedCornerShape(12.dp)
                ){

                    Box(modifier = Modifier.fillMaxWidth()) {
                        // Icon at start
                        Icon(
                            painter = painterResource(R.drawable.icons8_facebook_logo),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .padding(start = 16.dp)

                        )

                        // Text in center
                        Text(
                            text = "Log in with Facebook",
                            modifier = Modifier.align(Alignment.Center),
                            textAlign = TextAlign.Center
                        )
                    }
                }

                OutlinedButton(onClick = {},
                    modifier = Modifier.fillMaxWidth()
                    ,
                    border = BorderStroke(1.dp , customPink),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = customBlack,
                        containerColor = Color.Transparent
                    ),
                    contentPadding = PaddingValues(0.dp),
                    shape = RoundedCornerShape(12.dp)
                ){

                    Box(modifier = Modifier.fillMaxWidth()) {
                        // Icon at start
                        Icon(
                            painter = painterResource(R.drawable.icons8_google_48),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .padding(start = 16.dp)

                        )

                        // Text in center
                        Text(
                            text = "Log in with Google",
                            modifier = Modifier.align(Alignment.Center),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }



            Image(
                painter = painterResource(R.drawable.bottom_corner),
                contentDescription = null,
                modifier = Modifier.align(Alignment.BottomStart)
            )
        }

    }
}