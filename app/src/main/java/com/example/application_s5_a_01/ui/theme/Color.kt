package com.example.application_s5_a_01.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.ColorUtils

var Secondary = Color(0xffff0f7b)
var Tertiary = Color(0xfff89b29)
var Primary = Color(ColorUtils.blendARGB(Secondary.toArgb(), Tertiary.toArgb(), 0.5F))

