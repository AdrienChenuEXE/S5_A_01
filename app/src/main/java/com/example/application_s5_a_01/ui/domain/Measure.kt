package com.example.application_s5_a_01.ui.domain

import co.yml.charts.common.model.Point
import com.example.application_s5_a_01.model.Measure

fun getAllPoints(measureList: List<Measure>): List<Point> {
    val mockList = mutableListOf<Point>()
    for (i in 1..24) {
        mockList.add(Point(i.toFloat(), (Math.random()*30+30).toFloat()))
    }
    val moqpointsData = listOf(Point(0f, 40f), Point(1f, 90f), Point(2f, 0f), Point(3f, 60f), Point(4f, 10f))
    return moqpointsData
}