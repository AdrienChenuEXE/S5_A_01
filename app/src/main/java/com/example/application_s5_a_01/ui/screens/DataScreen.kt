package com.example.application_s5_a_01.ui.screens

import androidx.compose.runtime.Composable
import com.example.application_s5_a_01.data.enums.Measures
import com.example.application_s5_a_01.model.MeasureSettings
import com.example.application_s5_a_01.model.MeasuresData
import com.example.application_s5_a_01.ui.composables.MeasureViewList
import com.example.application_s5_a_01.ui.composables.SAEChart

@Composable
fun DataScreen(
    data: MeasuresData?,
    settings: MeasureSettings,
    setCurrentMeasure: (Measures) -> Unit
) {
    MeasureViewList(
        data = data
    ) { setCurrentMeasure(it) }
    SAEChart(
        values = data?.getValues(settings.measure.value),
        settings = settings
    )
}