package com.example.application_s5_a_01.ui.screens

import ClassRoomViewModel
import MeasureUiState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.application_s5_a_01.model.Measure
import com.example.application_s5_a_01.ui.domain.getAllPoints
import com.example.application_s5_a_01.ui.components.MeasureLineChart


@Composable
fun ClassRoomView(
    navController: NavController,
    classRoomId: String,
    measureUiState: MeasureUiState
){
    when (measureUiState) {
        is MeasureUiState.Loading -> LoadingScreen()
        is MeasureUiState.Success -> ClassRoomScreen(navController, classRoomId, measureUiState.measures)
        else -> ErrorScreen()
    }
}

@Composable
fun ClassRoomScreen(
    navController: NavController,
    classRoomId: String,
    measures: List<Measure>
    ) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Classroom $classRoomId",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp
        )
        MeasureViewList()
        MeasureLineChart(
            getAllPoints(emptyList()),
            40
        )
        Button(onClick = {
            navController.navigate("home")
        }
        ) {
            Text(text = "Go back to home")
        }
    }
}

@Composable
fun MeasureViewList() {
    Row {
        MeasureView("Temperature", "24°C")
        MeasureView("Humidité", "56%")
        MeasureView("Taux de CO2", "36ug/m3")
    }
}

@Composable
fun MeasureView(
    label: String,
    value: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(label)
        Text(value, fontSize = 30.sp)
    }
}

@Composable
fun ErrorScreen() {
    Text("ERROR")
}
@Composable
fun LoadingScreen() {
    Text("LOADING")
}

