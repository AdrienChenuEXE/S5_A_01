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
import androidx.compose.runtime.mutableIntStateOf
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
        imageRes = R.drawable.iut_logo,
        title = "Bienvenue dans EduSense",
        description = "Découvrez le futur de l'éducation avec EduSense, une solution innovante utilisant les capteurs intelligents des salles de l'IUT pour surveiller la température, la concentration de CO2, l'intensité sonore et bien plus encore.",
        color = Color(0xFF1b3592)
    ), OnboardPage(
        imageRes = R.drawable.intelligence_artificielle,
        title = "Explorez les possibilités",
        description = "Plongez dans le futur avec notre intelligence artificielle avancée qui prédit les tendances et les évolutions des valeurs mesurées, ouvrant ainsi de nouvelles perspectives pour l'apprentissage et le confort des étudiants.",
        color = Color(0xFFb5121b)
    ), OnboardPage(
        imageRes = R.drawable.application_mobile,
        title = "Prêt à commencer",
        description = "Commencez dès maintenant en sélectionnant une salle de classe et en découvrant les données en temps réel.",
        color = Color(0xFFEE7332)
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
    val currentPage = remember { mutableIntStateOf(0) }

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
                imageRes = onboardPages[currentPage.intValue].imageRes,
                color = onboardPages[currentPage.intValue].color
            )

            OnBoardDetails(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp),
                currentPage = onboardPages[currentPage.intValue]
            )

            OnBoardNavButton(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp),
                currentPage = currentPage.intValue,
                noOfPages = onboardPages.size,
                onNextClicked = {
                    if (currentPage.intValue < onboardPages.size-1 ) {
                        currentPage.intValue++
                    } else {
                        doneOnboarding()
                    }
                })
        }
        TabSelector(
            onboardPages = onboardPages,
            currentPage = currentPage.intValue
        ) { index ->
            currentPage.intValue = index
        }
    }
}