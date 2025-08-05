package com.example.myshoppinguser.common

import android.R.attr.onClick
import android.app.AlertDialog
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.myshoppinguser.R
import com.example.myshoppinguser.ui.theme.customPink



@Composable
fun AlertDialogLogout(modifier: Modifier = Modifier) {

    var openAlert =  remember { mutableStateOf(false) }
//    Button(onClick = {
//        openAlert.value = true
//    }) { Text("show alert box")}
//
//    if (openAlert.value){
//       AlertDialog(openAlert)
//    }
    AlertDialog(openAlert)
}

@Composable
fun AlertDialog(openAlert : MutableState<Boolean> ) {

    Dialog(onDismissRequest = {
        openAlert.value = false
    }) {
        CustomUI(openAlert)
    }
}

@Composable
private fun CustomUI(openAlert : MutableState<Boolean>){

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
                            painter = painterResource(R.drawable.profile),
                            contentDescription = "Profile",
                            modifier = Modifier.size(64.dp)
                        )
                    }

                    Text(
                        text = "LOG OUT",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = customPink
                    )

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Do you Really",
                            fontSize = 12.sp,

                            )

                        Text(
                            text = "Want To Logout",
                            fontSize = 12.sp
                        )
                    }

                    Row {

                        OutlinedButton(onClick = {},
                            modifier = Modifier.fillMaxWidth()
                                .weight(1f),
                            colors = ButtonDefaults.buttonColors(
                                contentColor = Color.Red,
                                containerColor = Color.Transparent
                            ),
                            shape = RoundedCornerShape(12.dp),
                           border = BorderStroke(1.dp , Color.Red)) {
                            Text(
                                text = "Cancel"
                            )
                        }

                        Spacer(modifier = Modifier.width(15.dp))
                        Button(
                            onClick = {
                                openAlert.value = false
                            },
                            modifier = Modifier.fillMaxWidth()
                                .weight(1f),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = customPink
                            )
                        ) {
                            Text(text = "Log Out")
                        }
                    }
                }
            }

        }
    }
}
