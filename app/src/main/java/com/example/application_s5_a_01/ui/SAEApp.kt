package com.example.application_s5_a_01.ui

import ClassRoomViewModel
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.application_s5_a_01.model.ClassRooms
import com.example.application_s5_a_01.ui.screens.ClassRoomDetailsScreen
import com.example.application_s5_a_01.ui.screens.ClassRoomListScreen
import com.example.application_s5_a_01.ui.screens.HomeScreen

enum class Routes(val routeName: String) {
    Home(routeName = "home"),
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

    var currentClassRoom: ClassRooms by remember { mutableStateOf(ClassRooms.D251) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    Scaffold(
        topBar = {
            SAEAppBar(
                navController = navController,
                isDarkMode = isDarkMode,
                switchDarkMode = switchDarkMode,
                showButton = navBackStackEntry?.destination?.route != Routes.Home.routeName
            )
        }
    ) {
        NavHost(navController = navController, startDestination = "home" ){
            composable(Routes.Home.routeName){
                HomeScreen(
                    onClassRoomListButtonClick = {
                        navController.navigate(Routes.CLassRoomList.routeName)
                    }
                )
            }
            composable(Routes.CLassRoomList.routeName){
                ClassRoomListScreen(
                    onClassRoomClicked = {
                        currentClassRoom = it;
                        navController.navigate(Routes.ClassRoomDetails.routeName)
                    },
                    onBackButtonClicked = {
                        navController.navigate(Routes.Home.routeName)
                    }
                )
            }
            composable(Routes.ClassRoomDetails.routeName) { backStackEntry ->
                val classRoomViewModel: ClassRoomViewModel =
                    viewModel(factory = ClassRoomViewModel.Factory)
                ClassRoomDetailsScreen(
                    currentClassRoom,
                    measureUiState = classRoomViewModel.measureUiView,
                    retryAction = {},
                    reload = {}
                )
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SAEAppBar(
    navController: NavHostController,
    showButton: Boolean,
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
        },
        navigationIcon = {
                if (showButton) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                }
            }
        },
    )
}



