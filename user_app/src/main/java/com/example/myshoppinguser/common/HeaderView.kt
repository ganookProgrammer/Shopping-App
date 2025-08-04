package com.example.myshoppinguser.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myshoppinguser.ui.theme.customPink
import com.example.myshoppinguser.R

@Composable
fun HeaderViewItems(modifier: Modifier = Modifier) {

    var search by remember {
        mutableStateOf("")
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Absolute.SpaceEvenly
    ) {
        
         OutlinedTextField(
            value = search,
            onValueChange = {
                search = it
            },
            placeholder = {
                Text(text = "Search")
            },
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
            },
            modifier = Modifier.weight(1f)

                ,
             shape = RoundedCornerShape(10.dp),
             colors = OutlinedTextFieldDefaults.colors(
                 focusedBorderColor = customPink,
                 unfocusedBorderColor = customPink
             )
        )

        Icon(painter = painterResource(R.drawable.mdi_bell_notification_outline), contentDescription = "bell-icon",
            modifier = Modifier.padding(start = 10.dp)
            )
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun PrieveScreen() {
//    HeaderViewItems()
//}