package com.example.allmighty.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.allmighty.calculator.presentation.record.RecordScreenRoot
import com.example.allmighty.calculator.presentation.record_list.RecordListScreenRoot

@Composable
fun Navigation(
    navHostController: NavHostController
) {

    NavHost(
        navController = navHostController,
        startDestination = Screen.RecordListScreen.route
    ) {
        composable(route = Screen.RecordScreen.route) {
            RecordScreenRoot(navController = navHostController)
        }

        composable(route = Screen.RecordListScreen.route) {
            RecordListScreenRoot(navController = navHostController)
        }
    }
}