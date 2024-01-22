package com.example.application_s5_a_01.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.application_s5_a_01.model.ClassRoom
import com.example.application_s5_a_01.model.ClassRooms

@Composable
fun ClassRoomListScreen(
    onClassRoomClicked: (classRoom: ClassRooms) -> Unit,
    onBackButtonClicked: () -> Unit
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(
            text = "Class rooms",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp
        )
        ClassRoomButtonList(onClassRoomClicked)
        Button(onClick = {
            onBackButtonClicked()
        }
        ) {
            Text(text = "Go back to home")
        }
    }
}

@Composable
fun ClassRoomButtonList(
    onClassRoomClicked: (classRoom: ClassRooms) -> Unit,
){
    for (classRoom in ClassRoom.getClassRooms()) {
        Button(onClick = {
            onClassRoomClicked(classRoom)
        }) {
            Text(text = classRoom.name)
        }
    }
}