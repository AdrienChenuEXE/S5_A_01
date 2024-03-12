package com.example.application_s5_a_01.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application_s5_a_01.model.DiscomfortInfo
import com.example.application_s5_a_01.utils.GraphicUtils

@Composable
fun DiscomfortCard(
    discomfortInfo: DiscomfortInfo,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    GraphicUtils.getMainBrush(
                        MaterialTheme.colorScheme.secondary,
                        MaterialTheme.colorScheme.tertiary
                    )
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween // Aligne le texte à gauche et l'image à droite
        ) {
            Text(
                text = discomfortInfo.description,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier.padding(start = 8.dp)
            )

            Image(
                painter = painterResource(id = discomfortInfo.imageResId),
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .size(50.dp)
            )
        }
    }
}