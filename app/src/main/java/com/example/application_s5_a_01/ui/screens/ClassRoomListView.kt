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
import androidx.navigation.NavController

val classRoomList = listOf<String>("d251", "d351", "d360")

@Composable
fun ClassRoomListScreen(navController: NavController){
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
        ClassRoomButtonList(navController)
        Button(onClick = {
            navController.navigate("home")
        }
        ) {
            Text(text = "Go back to home")
        }
    }
}

@Composable
fun ClassRoomButtonList(navController: NavController){
    for (classRoom in classRoomList) {
        ClassRoomButton(classRoom, navController)
    }
}

@Composable
fun ClassRoomButton(classRoomName: String, navController: NavController){
    Button(onClick = {
        navController.navigate("classRoom/$classRoomName")
    }) {
        Text(text = classRoomName)
    }
}