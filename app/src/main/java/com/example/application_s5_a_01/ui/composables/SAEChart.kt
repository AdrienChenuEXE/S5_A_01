package com.example.application_s5_a_01.ui.composables

import android.graphics.Color
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import com.example.application_s5_a_01.data.enums.Interval
import com.example.application_s5_a_01.model.Days
import com.example.application_s5_a_01.model.MeasureSettings
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.column.columnChart
import com.patrykandpatrick.vico.core.axis.AxisItemPlacer
import com.patrykandpatrick.vico.core.axis.AxisPosition
import com.patrykandpatrick.vico.core.axis.vertical.VerticalAxis
import com.patrykandpatrick.vico.core.chart.draw.ChartDrawContext
import com.patrykandpatrick.vico.core.component.shape.LineComponent
import com.patrykandpatrick.vico.core.component.shape.Shapes
import com.patrykandpatrick.vico.core.context.MeasureContext
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.entryOf
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun SAEChart(
    settings: MeasureSettings,
    values: List<Double>?
) {
    val calendar: Calendar = Calendar.getInstance()

    fun getRandomEntries() = List(values?.size ?: 7) { entryOf(it, values?.get(it) ?: 0) }
    val chartEntryModelProducer = ChartEntryModelProducer(getRandomEntries())

    fun getFormat(pattern: String): Int {
        val date: Date = calendar.time
        return SimpleDateFormat(pattern, Locale.FRANCE).format(date.time).toInt()
    }

    fun getDayLabels(): List<String> {
        return List(12) {
            (it*2).toString()+"h"
        }
    }

    fun getHalfDayLabels(): List<String> {
        var currentHour = getFormat("k")
        if (currentHour % 2 == 1) currentHour += 1
        return List(7) {
            ((currentHour - 2 * (it-1) % 24).toString() + "h")
        }.reversed()
    }

    fun getHourLabels(): List<String> {
        return listOf("-30m", "-20m","-15m", "-10m", "-5m", "-0m")
    }

    fun getWeekLabels(): List<String> {
        val currentDay = getFormat("u")
        val labelList: ArrayList<String> = ArrayList()
        Days.forEachIndexed { index, _ ->
            labelList.add(Days[((currentDay + index) % 7)])
        }
        return labelList
    }

    fun getLabel(index: Int): String {
        return when(settings.interval) {
            Interval.day -> getDayLabels()
            Interval.halfday -> getHalfDayLabels()
            Interval.hour -> getHourLabels()
            Interval.week -> getHalfDayLabels()
        }[index]
    }

    Chart(
        chart = columnChart(
             columns = List(7) {
                 LineComponent(
                 color = ColorUtils.blendARGB(
                     MaterialTheme.colorScheme.secondary.toArgb(),
                     MaterialTheme.colorScheme.tertiary.toArgb(),
                     0.5f
                 ),
                 thicknessDp = 16f,
                 shape = Shapes.roundedCornerShape(allPercent = 100),
             ) }
        ),
        chartModelProducer = chartEntryModelProducer,
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .padding(top = 60.dp),
        startAxis = rememberStartAxis(
            itemPlacer = VerticalAxisItemPlacer(),
            axis = LineComponent(color = Color.TRANSPARENT),
        ),
        isZoomEnabled = true,
        bottomAxis = rememberBottomAxis(
            axis = LineComponent(color = Color.TRANSPARENT),
            valueFormatter = {
                value, _ ->
                getLabel(value.toInt())
            }
        )
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
        for (i in 0..6) {
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