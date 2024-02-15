package com.example.application_s5_a_01.ui

import ClassRoomViewModel
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.application_s5_a_01.model.MeasureSettings
import com.example.application_s5_a_01.ui.screens.ClassRoomDetailsScreen
import com.example.application_s5_a_01.ui.screens.ClassRoomListScreen

enum class Routes(val routeName: String) {
    CLassRoomList(routeName = "list"),
    ClassRoomDetails(routeName = "single"),
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SAEApp(
    navController: NavHostController = rememberNavController(),
    isDarkMode: Boolean,
    switchDarkMode: () -> Unit
    ){

    var settings: MeasureSettings by remember { mutableStateOf(MeasureSettings()) }

    Scaffold(
        topBar = {
            SAEAppBar(
                isDarkMode = isDarkMode,
                switchDarkMode = switchDarkMode,
            )
        }
    ) {
        NavHost(navController = navController, startDestination = Routes.CLassRoomList.routeName ){
            composable(Routes.CLassRoomList.routeName){
                ClassRoomListScreen(
                    onClassRoomClicked = {
                        navController.navigate(Routes.ClassRoomDetails.routeName)
                    }
                )
            }
            composable(Routes.ClassRoomDetails.routeName) {
                val classRoomViewModel: ClassRoomViewModel =
                    viewModel(factory = ClassRoomViewModel.Factory)
                classRoomViewModel.getMeasures(
                        settings.toMeasureQuery()
                )
                ClassRoomDetailsScreen(
                    settings = settings,
                    measureUiState = classRoomViewModel.measureUiView,
                    retryAction = {}
                ) {}
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SAEAppBar(
    isDarkMode: Boolean,
    switchDarkMode: () -> Unit
) {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Capteurs IUT",
                    style = MaterialTheme.typography.headlineLarge,
                )
                Switch(
                    checked = isDarkMode,
                    onCheckedChange = { switchDarkMode() },
                    modifier = Modifier.padding(end = 20.dp)
                )
            }
        }
    )
}



