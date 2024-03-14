package com.example.application_s5_a_01.ui.composables

import android.graphics.Color
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.application_s5_a_01.model.MeasureSettings
import com.example.application_s5_a_01.ui.theme.Primary
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.compose.chart.line.lineSpec
import com.patrykandpatrick.vico.core.component.shape.LineComponent
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.entryOf
import kotlin.math.roundToInt

@Composable
fun SAEChart(
    settings: MeasureSettings,
    values: List<Double>?
) {

    fun getValues() = List(values?.size ?: settings.interval.getLabels.size) { entryOf(it, values?.get(it) ?: 0) }
    val chartEntryModelProducer = ChartEntryModelProducer(getValues())

    Chart(
        chart = lineChart(
            remember {
                listOf(lineSpec(
                    Primary
                )) }
        ),
        chartModelProducer = chartEntryModelProducer,
        modifier = Modifier
            .padding(10.dp),
        startAxis = rememberStartAxis(
            axis = LineComponent(color = Color.WHITE),
            valueFormatter = {
                value, _ ->
                value.roundToInt().toString()
            }
        ),
        isZoomEnabled = true,
        bottomAxis = rememberBottomAxis(
            axis = LineComponent(color = Color.TRANSPARENT),
            valueFormatter = {
                value, _ ->
                if (settings.interval.getLabels.size > value.toInt()) settings.interval.getLabels[value.toInt()] else ""
            }
        ),

    )
}
