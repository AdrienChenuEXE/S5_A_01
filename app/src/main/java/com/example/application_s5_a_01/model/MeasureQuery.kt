package com.example.application_s5_a_01.model

enum class Output {
    mean,
    median,
    last
}

data class MeasureQuery (
    val start:Int,
    val end: Int,
    var bucket: String? = "IUT_BUCKET",
    var interval: String? = "1d",
    var discomfort: String = "None",
    var output: String? = "None"
)