package com.example.application_s5_a_01.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application_s5_a_01.R
import com.example.application_s5_a_01.model.ClassRooms
import com.example.application_s5_a_01.model.SallesList
import com.example.application_s5_a_01.ui.composables.MeasureLineChart
import com.example.application_s5_a_01.ui.composables.SAEDropDown
import com.example.application_s5_a_01.ui.domain.getAllPoints
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

enum class Interval (val text: String) {
    day(text = "Dernier jour"), // 24h
    halfday(text = "Dernières 12h"), // 12h
    hour(text = "Dernière heure"), // 1h
}

enum class Measure (val text: String) {
    co2(text = "CO2"),
    humidity(text = "Humidité"),
    uv(text = "UV"),
    db(text = "dB"),
    temperature(text = "Temperature")
}

@Composable
fun ClassRoomDetailsScreen(
    classRoom: ClassRooms,
    measureUiState: SallesList,
    retryAction: () -> Unit,
    reload: () -> Unit
    ){

    val isRefreshing by remember { mutableStateOf(false) }
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isRefreshing)

    SwipeRefresh(
        onRefresh = {
            reload()
        },
        state = swipeRefreshState,
    ) {
        ClassRoomDetails()
        /*when (measureUiState) {
            is MeasureUiState.Loading -> LoadingScreen()
            is MeasureUiState.Success -> ClassRoomDetails(
                classRoom,
                listOf()
            )
            is MeasureUiState.Error -> ErrorScreen(retryAction = retryAction)
            else -> {}
        }*/
    }
}

@Composable
fun ClassRoomDetails(
    ) {
    var interval by remember {
        mutableStateOf(Interval.day)
    }
    var measure by remember {
        mutableStateOf(Measure.temperature)
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Classroom",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp
        )
        MeasureViewList()
        MeasureLineChart(
            getAllPoints(emptyList()),
            40
        )
        SAEDropDown(
            selected = interval.text,
            entries = Interval.entries.map { it.text }
        ) {
            interval = Interval.entries.find { e -> e.text == it }!!
        }
        SAEDropDown(
            selected = measure.text,
            entries = Measure.entries.map { it.text }
        ) {
            measure = Measure.entries.find { e -> e.text == it }!!
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
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}
@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}



