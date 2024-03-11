package com.example.application_s5_a_01.ui

import ClassRoomViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.application_s5_a_01.ui.screens.ClassRoomDetailsScreen
import com.example.application_s5_a_01.ui.screens.ClassRoomListScreen
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.application_s5_a_01.utils.GraphicUtils


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
                    }
                ) {}
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
@Composable
fun BottomMenu(
    onHomeClicked: () -> Unit,
    onFavoriteClicked: () -> Unit,
    onSettingsClicked: () -> Unit
) {
        BottomAppBar(
            content = {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    IconButton(
                        onClick = onHomeClicked
                    ) {
                        Icon(Icons.Default.Home, contentDescription = "Home")
                    }
                    IconButton(
                        onClick = onFavoriteClicked
                    ) {
                        Icon(Icons.Default.Favorite, contentDescription = "Favorite")
                    }
                    IconButton(
                        onClick = onSettingsClicked
                    ) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings")
                    }
                }
            }
        )
    }








