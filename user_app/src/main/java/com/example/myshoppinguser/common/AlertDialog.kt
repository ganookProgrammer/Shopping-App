package com.example.myshoppinguser.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.myshoppinguser.R
import com.example.myshoppinguser.presentation.navigation.Routes
import com.example.myshoppinguser.ui.theme.customPink



@Composable
fun CustomAlertDialog(navController: NavController) {

    var openAlert = remember { mutableStateOf(true) }
    Dialog(onDismissRequest = {
        openAlert.value = false
    }) {
        CustomUI(openAlert , navController = navController)
    }
}

@Composable
private fun CustomUI(openAlert : MutableState<Boolean>,navController: NavController){

    Box(modifier = Modifier.fillMaxSize()
//        .background(brush = Brush.verticalGradient(
//            colors = listOf(
//                Color.Black.copy(alpha = 0.7f),
//                Color.Transparent
//        )))
        ,
        contentAlignment = Alignment.Center) {
        Card(
            modifier = Modifier.width(291.dp)
                .height(301.dp),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            )
        ) {

            Column(
                modifier = Modifier.fillMaxSize()
                    .background(Color.White)
                ,
                verticalArrangement = Arrangement.Center
            ) {


                Column(
                    modifier = Modifier
                        .padding(horizontal = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                    ) {
                        Image(
                            painter = painterResource(R.drawable.check_alert),
                            contentDescription = "check icon",
                        )
                        Image(
                            painter = painterResource(R.drawable.tick),
                            contentDescription = "tick icon",
                        )
                    }

                    Text(
                        text = "Success",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = customPink
                    )

                    Text(
                        text = "Congratulations, you have \ncompleted your registration!",
                        fontSize = 12.sp,

                    )

                    Button(
                        onClick = {
                            openAlert.value = false
                            navController.navigate(Routes.HomeScreen)

                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = customPink
                        )
                    ) {
                        Text(text = "Done")
                    }
                }
            }

        }
    }
}

