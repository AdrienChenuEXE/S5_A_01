package com.example.application_s5_a_01.model

data class MeasureQuery (
    val start: Long,
    val end: Long,
    val measure: String,
    var salle: String,
    var bucket: String = "IUT_BUCKET",
    var interval: String = "1d",
    var discomfort: String = "None",
    var output: String = "None",
    var harmonize_data: Boolean = true
)