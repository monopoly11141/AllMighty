package com.example.allmighty.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.allmighty.calculator.presentation.add_record.AddRecordScreenRoot
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
        composable(route = "${Screen.RecordScreen.route}/{recordUiId}",
            arguments = listOf(
                navArgument("recordUiId") {
                    type = NavType.StringType
                }
            )
        ) {
            RecordScreenRoot(
                navController = navHostController
            )
        }

        composable(route = Screen.RecordListScreen.route) {
            RecordListScreenRoot(navController = navHostController)
        }

        composable(route = Screen.AddRecordScreen.route) {
            AddRecordScreenRoot(navController = navHostController)
        }
    }
}