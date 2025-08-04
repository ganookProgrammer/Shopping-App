package com.example.myshoppinguser.presentation.screen

import android.R.attr.password
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults.contentPadding
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myshoppinguser.R
import com.example.myshoppinguser.domain.models.User
import com.example.myshoppinguser.presentation.viewmodel.MyViewModel
import com.example.myshoppinguser.ui.theme.customBlack
import com.example.myshoppinguser.ui.theme.customPink
import okhttp3.internal.connection.RouteDatabase
import kotlin.math.log


@Composable
fun LoginScreen(viewModel: MyViewModel = hiltViewModel(), navController: NavController) {
    val loginState = viewModel.loginState.collectAsState().value

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val context = LocalContext.current


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
                .padding(horizontal = 20.dp,
                    vertical = 20.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.Start


            ) {

            Text(
                text = "Login",
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold,
            )
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
                label = { Text(text = "Password") },
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = customPink,
                    focusedBorderColor = customPink
                ),
                modifier = Modifier
                    .fillMaxWidth()

            )

            Text(text = "Forget Password?",
                fontSize = 15.sp,
                color = customBlack,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End
            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    viewModel.loginWithEmailAndPass(
                        email = email,
                        password = password
                    )
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
                Text(text = "Don't have an account?",
                    fontSize = 15.sp,
                    color = customBlack)

                Text(text = "Sign Up",
                    fontSize = 15.sp,
                    color = customPink)
            }

            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically) {
                Divider(color = Color.Black , thickness = 1.dp,
                    modifier = Modifier.weight(1.0f)
                        .padding(start = 20.dp))
                Text(text = "OR",
                    fontSize = 15.sp,
                    color = customBlack,
                    modifier = Modifier.padding(horizontal = 10.dp))
                Divider(color = Color.Black , thickness = 1.dp,
                    modifier = Modifier.weight(1.0f)
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


    when {
        loginState.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        loginState.error != null -> {
            Toast.makeText(context, loginState.error.toString(), Toast.LENGTH_SHORT).show()
            viewModel.resetLoginState()
        }

        loginState.data != null -> {
            Toast.makeText(context, loginState.data.toString(), Toast.LENGTH_SHORT).show()
            viewModel.resetLoginState()

        }
    }

    if (!loginState.isLoading) {
//        Column(modifier = Modifier.fillMaxSize(),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally) {
//            OutlinedTextField(
//                value = email,
//                onValueChange = {email = it},
//                label = {Text(text = "Email")},
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 20.dp)
//            )
//
//            OutlinedTextField(
//                value = password,
//                onValueChange = {password = it},
//                label = {Text(text = "Password")},
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 20.dp)
//            )
//
//            Button(onClick = {
//                viewModel.loginWithEmailAndPass(
//                    email = email,
//                    password = password
//                )
//            }, modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 20.dp)) {
//                Text(text = "Login")
//            }
//        }
    }
}