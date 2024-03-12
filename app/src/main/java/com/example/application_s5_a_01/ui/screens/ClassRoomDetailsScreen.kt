package com.example.application_s5_a_01.ui.screens

import MeasureUiState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application_s5_a_01.model.DiscomfortInfo
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
    onIntervalChange: (Interval) -> Unit
) {

    val isRefreshing by remember { mutableStateOf(false) }
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isRefreshing)

    SwipeRefresh(
        onRefresh = {
            retryAction()
        },
        state = swipeRefreshState,
    ) {
        when (measureUiState) {
            is MeasureUiState.Loading -> ClassRoomDetails(
                null, settings, {}
            )

            is MeasureUiState.Success -> ClassRoomDetails(
                measureUiState.measures,
                settings
            ) {
                onIntervalChange(it)
            }

            is MeasureUiState.Error -> ErrorScreen(retryAction = retryAction)
            else -> {}
        }
    }
}

@Composable
fun ClassRoomDetails(
    data: MeasuresData?,
    settings: MeasureSettings,
    onIntervalChange: (Interval) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
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
                .background(
                    GraphicUtils.getMainBrush(
                        MaterialTheme.colorScheme.secondary,
                        MaterialTheme.colorScheme.tertiary
                    )
                ),
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

        TextSwitch(
            selectedIndex = Interval.entries.indexOf(settings.interval),
            items = Interval.entries.map { it.text }
        ) {
            println("test")
            onIntervalChange(Interval.entries[it])
        }

        //Test
        DiscomfortInfo.getDiscomfortInfo("TG21")?.let { discomfortInfo ->
            DiscomfortCard(discomfortInfo = discomfortInfo)
        }
        DiscomfortInfo.getDiscomfortInfo("TL19")?.let { discomfortInfo ->
            DiscomfortCard(discomfortInfo = discomfortInfo)
        }
        DiscomfortInfo.getDiscomfortInfo("CG5k")?.let { discomfortInfo ->
            DiscomfortCard(discomfortInfo = discomfortInfo)
        }
        DiscomfortInfo.getDiscomfortInfo("DL10")?.let { discomfortInfo ->
            DiscomfortCard(discomfortInfo = discomfortInfo)
        }
        DiscomfortInfo.getDiscomfortInfo("HG50")?.let { discomfortInfo ->
            DiscomfortCard(discomfortInfo = discomfortInfo)
        }
        DiscomfortInfo.getDiscomfortInfo("PG10")?.let { discomfortInfo ->
            DiscomfortCard(discomfortInfo = discomfortInfo)
        }
        DiscomfortInfo.getDiscomfortInfo("BSHP")?.let { discomfortInfo ->
            DiscomfortCard(discomfortInfo = discomfortInfo)
        }
        DiscomfortInfo.getDiscomfortInfo("BSPD")?.let { discomfortInfo ->
            DiscomfortCard(discomfortInfo = discomfortInfo)
        }

        // Afficher les DiscomfortCards pour chaque élément de discomfortList
        data?.data?.get(0)?.values?.forEach { timePoint ->
            timePoint.values.forEach { measure ->
                measure.discomfortList.forEach { discomfortCode ->
                    DiscomfortInfo.getDiscomfortInfo(discomfortCode)?.let { discomfortInfo ->
                        DiscomfortCard(discomfortInfo = discomfortInfo)
                    }
                }
            }
        }
    }
}


@Composable
fun DiscomfortCard(
    discomfortInfo: DiscomfortInfo,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    GraphicUtils.getMainBrush(
                        MaterialTheme.colorScheme.secondary,
                        MaterialTheme.colorScheme.tertiary
                    )
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween // Aligne le texte à gauche et l'image à droite
        ) {
            Text(
                text = discomfortInfo.description,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier.padding(start = 8.dp)
            )

            Image(
                painter = painterResource(id = discomfortInfo.imageResId),
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .size(50.dp)
            )
        }
    }
}
