package com.example.application_s5_a_01.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.application_s5_a_01.R

data class OnboardPage(
    val imageRes: Int,
    val title: String,
    val description: String,
    val color: Color
)

val onboardPagesList = listOf(
    OnboardPage(
        imageRes = R.drawable.thermometer,
        title = "Bienvenue dans <nom>",
        description = "Nous utilisons les différents capteurs placés dans les salles de l'IUT :" +
                "température, concentration de CO2, intensité sonore...",
        color = Color(0xFF3498db)
    ), OnboardPage(
        imageRes = R.drawable.brain,
        title = "Voyagez dans le futur",
        description = "Nous utilisons l'intelligence artificielle afin de déterminer des " +
                "prédictions sur les futures valeurs mesurées.",
        color = Color(0xFF9b59b6)
    ), OnboardPage(
        imageRes = R.drawable.thumb_up,
        title = "Démarrez dès maintenant",
        description = "Cliquez sur démarrer et choississez une salle de classe pour démarrer!" +
                "Si vous souhaitez revoir ce menu à chaque lancement de l'application, allez dans" +
                " les paramètres",
        color = Color(0xFFf1c40f)
    )
)

@Composable
fun OnBoardImageView(
    modifier: Modifier = Modifier,
    imageRes: Int,
    color: Color
) {
    Box(modifier = modifier
        .padding(30.dp)
    ) {
        Icon(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(CircleShape)
                .background(color)
                .padding(vertical = 30.dp ),
            tint = Color.White
        )
    }
}

@Composable
fun OnBoardDetails(
    modifier: Modifier = Modifier, currentPage: OnboardPage
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = currentPage.title,
            style = MaterialTheme.typography.displaySmall,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = currentPage.description,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun OnBoardNavButton(
    modifier: Modifier = Modifier, currentPage: Int, noOfPages: Int, onNextClicked: () -> Unit,
) {
    Button(
        onClick = {
            onNextClicked()
        }, modifier = modifier
    ) {
        Text(text = if (currentPage < noOfPages - 1) "Suivant" else "Commencer")
    }
}

@Composable
fun TabSelector(onboardPages: List<OnboardPage>, currentPage: Int, onTabSelected: (Int) -> Unit) {
    TabRow(
        selectedTabIndex = currentPage,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        onboardPages.forEachIndexed { index, _ ->
            Tab(selected = index == currentPage, onClick = {
                onTabSelected(index)
            }, modifier = Modifier.padding(16.dp), content = {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(
                            color = if (index == currentPage) MaterialTheme.colorScheme.onPrimary
                            else Color.LightGray, shape = RoundedCornerShape(4.dp)
                        )
                )
            })
        }
    }
}

@Composable
fun OnboardScreen(
    doneOnboarding: () -> Unit
) {

    val onboardPages = onboardPagesList

    val currentPage = remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxHeight()
    ) {
        Column(
            modifier = Modifier.padding(50.dp)
        ) {
            OnBoardImageView(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                imageRes = onboardPages[currentPage.value].imageRes,
                color = onboardPages[currentPage.value].color
            )

            OnBoardDetails(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp),
                currentPage = onboardPages[currentPage.value]
            )

            OnBoardNavButton(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp),
                currentPage = currentPage.value,
                noOfPages = onboardPages.size,
                onNextClicked = {
                    if (currentPage.value < onboardPages.size-1 ) {
                        currentPage.value++
                    } else {
                        doneOnboarding()
                    }
                })
        }
        TabSelector(
            onboardPages = onboardPages,
            currentPage = currentPage.value
        ) { index ->
            currentPage.value = index
        }
    }
}