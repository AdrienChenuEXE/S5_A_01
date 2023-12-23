package com.example.application_s5_a_01.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Router(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home" ){
        composable("home"){
            HomeScreen(navController = navController)
        }
        composable("classRoomList"){
            ClassRoomListScreen(navController = navController)
        }
        composable(
            route = "classRoom/{classRoomId}",
            arguments = listOf(navArgument("classRoomId") { type = NavType.StringType })
        ) { backStackEntry ->
            ClassRoomScreen(
                navController = navController,
                classRoomId = backStackEntry.arguments?.getString("classRoomId") ?: ""
            )
        }
    }
}



