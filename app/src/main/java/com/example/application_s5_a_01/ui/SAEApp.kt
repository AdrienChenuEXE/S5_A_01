package com.example.application_s5_a_01.ui

import ClassRoomViewModel
import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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

    Scaffold(
        /*topBar = {
            SAEAppBar(
                isDarkMode = isDarkMode,
                switchDarkMode = switchDarkMode,
            )
        }*/
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
                ClassRoomDetailsScreen(
                    settings = classRoomViewModel.measureSettingsUiView,
                    measureUiState = classRoomViewModel.measureUiView,
                    retryAction = {
                        classRoomViewModel.getMeasures()
                    },
                    reload = {},
                    onSettingsChange = {
                        classRoomViewModel.setSettingsInterval(it.interval)
                    }
                )
            }
        }
    }

}

/*
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
*/


