package com.example.application_s5_a_01.ui.composables

import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine

@Composable
fun MeasureLineChart(
    pointsData: List<Point>,
    stepsize: Int,
                     ) {
    val xAxisData = AxisData.Builder()
        .axisStepSize(stepsize.dp)
        .backgroundColor(Color.White)
        .steps(24)
        .labelData { i -> "${i.toString()}h" }
        .labelAndAxisLinePadding(15.dp)
        .axisOffset(50.dp)
        .build()

    val steps: Int = 8
    val yAxisData = AxisData.Builder()
        .steps(steps)
        .backgroundColor(Color.Red)
        .labelAndAxisLinePadding(20.dp)
        .build()

    val lineChartData = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = pointsData,
                    LineStyle(),
                    IntersectionPoint(),
                    SelectionHighlightPoint(),
                    ShadowUnderLine(),
                    SelectionHighlightPopUp()
                )
            ),
        ),
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines(),
        backgroundColor = Color.White
    )

    LineChart(
        modifier = Modifier
            .height(300.dp),
        lineChartData = lineChartData
    )
}