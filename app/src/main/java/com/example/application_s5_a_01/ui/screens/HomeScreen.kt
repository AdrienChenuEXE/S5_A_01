package com.example.application_s5_a_01.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
    onClassRoomListButtonClick: () -> Unit
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Home",
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp
        )
        Button(onClick = {
            onClassRoomListButtonClick()
        }
        ) {
            Text(text = "Go to classrooms")
        }
    }
}