@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.application_s5_a_01.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.application_s5_a_01.ui.theme.Application_S5_A_01Theme
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Application_S5_A_01Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //DropdownMenu()
                    Router()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenu() {
    var isExpended by remember {
        mutableStateOf(false)
    }

    var gender by remember {
        mutableStateOf("")
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        ExposedDropdownMenuBox(
            expanded = isExpended,
            onExpandedChange = { isExpended = it}
        ) {
            TextField(
                value = gender,
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpended)
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier.menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = isExpended,
                onDismissRequest = { isExpended = false }
            ) {
                DropdownMenuItem(
                    text = {
                        Text(text = "Male")
                    },
                    onClick = {
                        gender = "Male"
                        isExpended = false
                    }
                )

                DropdownMenuItem(
                    text = {
                        Text(text = "Female")
                    },
                    onClick = {
                        gender = "Female"
                        isExpended = false
                    }
                )
            }
        }
    }
}




