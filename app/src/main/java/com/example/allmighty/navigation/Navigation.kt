package com.example.allmighty.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(
    navHostController: NavHostController
) {

    NavHost(
        navController = navHostController,
        startDestination = Screen.DisplayScreen.route
    ) {
        composable(route = Screen.DisplayScreen.route) {
            //RecordScreen(navController = navController)
        }
    }
}