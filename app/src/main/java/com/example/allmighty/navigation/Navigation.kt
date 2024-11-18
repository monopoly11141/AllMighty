package com.example.allmighty.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.allmighty.calculator.presentation.record.RecordScreenRoot

@Composable
fun Navigation(
    navHostController: NavHostController
) {

    NavHost(
        navController = navHostController,
        startDestination = Screen.RecordScreen.route
    ) {
        composable(route = Screen.RecordScreen.route) {
            RecordScreenRoot(navController = navHostController)
        }
    }
}