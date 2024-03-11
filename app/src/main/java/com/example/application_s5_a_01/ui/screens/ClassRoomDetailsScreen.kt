package com.example.application_s5_a_01.ui.screens

import MeasureUiState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application_s5_a_01.data.enums.Interval
import com.example.application_s5_a_01.model.MeasureSettings
import com.example.application_s5_a_01.model.MeasuresData
import com.example.application_s5_a_01.ui.composables.ErrorScreen
import com.example.application_s5_a_01.ui.composables.TextSwitch
import com.example.application_s5_a_01.utils.GraphicUtils
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun ClassRoomDetailsScreen(
    settings: MeasureSettings,
    measureUiState: MeasureUiState,
    retryAction: () -> Unit,
    reload: () -> Unit,
    onSettingsChange: (MeasureSettings) -> Unit
) {

    val isRefreshing by remember { mutableStateOf(false) }
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isRefreshing)

    SwipeRefresh(
        onRefresh = {
            reload()
        },
        state = swipeRefreshState,
    ) {
        when (measureUiState) {
            is MeasureUiState.Loading -> ClassRoomDetails(
                null, settings, onSettingsChange = onSettingsChange
            )

            is MeasureUiState.Success -> ClassRoomDetails(
                measureUiState.measures,
                settings,
                onSettingsChange = { updatedSettings ->
                    settings.interval = updatedSettings.interval
                }
            )

            is MeasureUiState.Error -> ErrorScreen(retryAction = retryAction)
            else -> {}
        }
    }
}

@Composable
fun ClassRoomDetails(
    data: MeasuresData?,
    settings: MeasureSettings,
    onSettingsChange: (MeasureSettings) -> Unit
) {
    var isControl by remember { mutableIntStateOf(0) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()
    ) {

        Row(
            modifier = Modifier
                .height(150.dp)
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(
                        bottomEndPercent = 50, bottomStartPercent = 50
                    )
                )
                .background(GraphicUtils.getMainBrush(
                    MaterialTheme.colorScheme.secondary,
                    MaterialTheme.colorScheme.tertiary
                )),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = ("Classroom " + data?.data?.get(0)?.salle) ?: "",
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                fontSize = 40.sp
            )
        }

        Column {
            TextSwitch(selectedIndex = isControl, items = listOf("Data", "Control"), onSelectionChange = {
                isControl = it
            })
        }

        IntervalSwitch(
            currentInterval = settings.interval,
            onIntervalChange = { newInterval ->
                val updatedSettings = settings.copy(interval = newInterval)
                onSettingsChange(updatedSettings)
            }
        )

        if (isControl == 0) {
            DataScreen(data = data, settings = settings)
        }

    }
}

@Composable
fun IntervalSwitch(
    currentInterval: Interval,
    onIntervalChange: (Interval) -> Unit
) {
    TextSwitch(
        selectedIndex = Interval.entries.indexOf(currentInterval),
        items = Interval.entries.map { it.text }.reversed(),
        onSelectionChange = {
            onIntervalChange(Interval.entries[it])
    })
}
