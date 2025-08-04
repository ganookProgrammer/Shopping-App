package com.example.myshoppingadmin.common

import android.R.attr.label
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myshoppingadmin.presentation.viewmodel.MyViewModel
import java.nio.file.WatchEvent


@Composable
fun DropDownMenu(viewModel: MyViewModel = hiltViewModel(),
                 onCategorySelected :(String) -> Unit) {

    LaunchedEffect(key1 = true) {
        viewModel.getCategories()
    }

    val getCategoriesState = viewModel.getCategoriesState.collectAsState().value

    var mExpanded by remember { mutableStateOf(false) }

    var mSelectedText by remember { mutableStateOf("") }

    var mTextFieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(Modifier.padding()
        ) {
        OutlinedTextField(
            value = mSelectedText,
            onValueChange = {mSelectedText = it},
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    mTextFieldSize = coordinates.size.toSize()
                },
            label = { Text("Categories")},
            trailingIcon = {
                Icon(icon,"contentDescription",
                    Modifier.clickable{mExpanded = !mExpanded})
            },

            readOnly = true
        )
        DropdownMenu(
            expanded = mExpanded,
            onDismissRequest = {mExpanded = false},
            modifier = Modifier
                .width(with(LocalDensity.current){mTextFieldSize.width.toDp()})

        ) {

                Log.d("TAG", "DropDownMenu: ${getCategoriesState.data}")
                getCategoriesState.data.forEach {
                    DropdownMenuItem(
                        text = { Text(text = it!!.name) },
                        onClick = {
                            mSelectedText = it!!.name
                            mExpanded = false
                            onCategorySelected(it.name)
                        }
                    )
                }
            }
                }
            }
