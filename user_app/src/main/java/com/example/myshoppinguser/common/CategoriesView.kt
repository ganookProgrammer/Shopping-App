package com.example.myshoppinguser.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myshoppinguser.ui.theme.customPink

@Composable
fun CategoriesView(modifier: Modifier = Modifier) {

    Row(modifier = Modifier.fillMaxWidth()
        ,
        horizontalArrangement = Arrangement.Absolute.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {



        Text(text = "Categories",
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold
            ))

        Text(text = "See more",
            style = TextStyle(
                fontSize = 13.sp,
                color = customPink,
                fontWeight = FontWeight.Bold
            ))
    }
}
