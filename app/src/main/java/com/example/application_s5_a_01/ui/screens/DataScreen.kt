package com.example.application_s5_a_01.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application_s5_a_01.model.Days
import com.example.application_s5_a_01.model.MeasureSettings
import com.example.application_s5_a_01.model.MeasuresData
import com.example.application_s5_a_01.model.SingleMeasure
import com.example.application_s5_a_01.ui.composables.SAEChart
import com.example.application_s5_a_01.utils.GraphicUtils
import kotlin.math.roundToInt

@Composable
fun DataScreen(
    data: MeasuresData?,
    settings: MeasureSettings,
) {
    MeasureViewList(
        data = data
    )
    SAEChart(
        values = data?.getValues("Température"),
        settings = settings
    )

    var sliderPosition by remember { mutableFloatStateOf(0f) }

    /*Slider(
        value = sliderPosition,
        onValueChange = { sliderPosition = it },
        colors = SliderDefaults.colors(
            thumbColor = Color.White,
            activeTrackColor = Color.White,
            inactiveTrackColor = Color.White,
            disabledActiveTickColor = Color.Black
        ),
        steps = 3,
        valueRange = 0f..4f,
        modifier = Modifier
            .padding(5.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(
                GraphicUtils.getMainBrush(
                    Color(ColorUtils.blendARGB(MaterialTheme.colorScheme.primary.toArgb(), Color.White.toArgb(), 0.25f)),
                    Color(ColorUtils.blendARGB(MaterialTheme.colorScheme.secondary.toArgb(), Color.White.toArgb(), 0.25f)),
                    )
            )
            .padding(horizontal = 5.dp)

    )


    SAEDropDown(selected = settings.interval.text, entries = Interval.entries.map { it.text }) {
        settings.interval = Interval.entries.find { e -> e.text == it }!!
    }
    SAEDropDown(selected = settings.measure.text, entries = Measures.entries.map { it.text }) {
        settings.measure = Measures.entries.find { e -> e.text == it }!!
    }*/

}

@Composable
fun MeasureViewList(
    data: MeasuresData?
) {
    LazyRow(
    ) {
        if (data != null) {
            items(data.getCurrentValues()) {
                MeasureView(singleMeasure = it)
            }
        } else {
            items(5) {
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .width(130.dp)
                        .height(100.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(
                            GraphicUtils.getMainBrush(
                                MaterialTheme.colorScheme.secondary,
                                MaterialTheme.colorScheme.tertiary
                            )
                        )
                ) {

                }
            }
        }

    }
}

@Composable
fun MeasureView(
    singleMeasure: SingleMeasure
) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(5.dp)
            .width(130.dp)
            .height(100.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(
                GraphicUtils.getMainBrush(
                    MaterialTheme.colorScheme.secondary,
                    MaterialTheme.colorScheme.tertiary
                )
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                singleMeasure.measureValue.value, color = Color.White, fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Text(
                    singleMeasure.value.roundToInt().toString(),
                    fontSize = 30.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                singleMeasure.measureValue.unity?.let {
                    Text(
                        it, fontSize = 15.sp, color = Color.White
                    )
                }
            }
        }

    }
}
