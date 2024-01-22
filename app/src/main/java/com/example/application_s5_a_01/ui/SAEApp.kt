package com.example.application_s5_a_01.ui

import ClassRoomViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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

@Composable
fun SAEApp(
    navController: NavHostController = rememberNavController(),
    ){

    var currentClassRoom: ClassRooms by remember { mutableStateOf(ClassRooms.default) }

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



