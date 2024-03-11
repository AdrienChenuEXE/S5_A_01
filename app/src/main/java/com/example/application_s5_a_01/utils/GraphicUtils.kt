package com.example.application_s5_a_01.utils

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

class GraphicUtils {
    companion object {
        fun getMainBrush(color1: Color, color2: Color): Brush {
            return Brush.linearGradient(colorStops = arrayOf(0.0f to color1, 1f to color2))
        }
    }
}