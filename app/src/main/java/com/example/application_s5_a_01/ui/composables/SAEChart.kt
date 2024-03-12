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
import com.patrykandpatrick.vico.core.axis.AxisItemPlacer
import com.patrykandpatrick.vico.core.axis.AxisPosition
import com.patrykandpatrick.vico.core.axis.vertical.VerticalAxis
import com.patrykandpatrick.vico.core.chart.draw.ChartDrawContext
import com.patrykandpatrick.vico.core.component.shape.LineComponent
import com.patrykandpatrick.vico.core.context.MeasureContext
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.entryOf

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
            .padding(horizontal = 10.dp)
            .padding(top = 60.dp),
        startAxis = rememberStartAxis(
            itemPlacer = VerticalAxisItemPlacer(),
            axis = LineComponent(color = Color.WHITE),
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

class VerticalAxisItemPlacer : AxisItemPlacer.Vertical {
    override fun getLabelValues(
        context: ChartDrawContext,
        axisHeight: Float,
        maxLabelHeight: Float,
        position: AxisPosition.Vertical
    ): List<Float> {
        val labelList = mutableListOf<Float>()
        for (i in 0..5) {
            labelList.add((i * 5F))
        }
        return labelList.toList()
    }

    override fun getTopVerticalAxisInset(
        verticalLabelPosition: VerticalAxis.VerticalLabelPosition,
        maxLabelHeight: Float,
        maxLineThickness: Float
    ): Float {
        return 1f
    }

    override fun getWidthMeasurementLabelValues(
        context: MeasureContext,
        axisHeight: Float,
        maxLabelHeight: Float,
        position: AxisPosition.Vertical
    ): List<Float> {
        return listOf(10f)
    }

    // Return the default implementation for the other required functions
    override fun getBottomVerticalAxisInset(
        verticalLabelPosition: VerticalAxis.VerticalLabelPosition,
        maxLabelHeight: Float,
        maxLineThickness: Float
    ): Float {
        return AxisItemPlacer.Vertical.default().getBottomVerticalAxisInset(
            verticalLabelPosition,
            maxLabelHeight,
            maxLineThickness
        )
    }

    override fun getHeightMeasurementLabelValues(
        context: MeasureContext,
        position: AxisPosition.Vertical
    ): List<Float> {
        return listOf()
    }
}