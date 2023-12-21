package com.example.application_s5_a_01

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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeScreen(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "first_screen" ){
        composable("first_screen"){
            Screen1(navController = navController)
        }
        composable("second_screen"){
            Screen2(navController = navController)
        }
    }
}

@Composable
fun Screen1(navController: NavController){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Screen 1",
            color = Color.Blue,
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp
        )
        Button(onClick = {
            navController.navigate("second_screen")
        }
        ) {
            Text(text = "Change to Screen 2")
        }
    }
}

@Composable
fun Screen2(navController: NavController){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)
    ) {
        Text(
            text = "Screen 2",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp
        )
        Button(onClick = {
            navController.popBackStack()
        }
        ) {
            Text(text = "Change to Screen 2")
        }
    }
}