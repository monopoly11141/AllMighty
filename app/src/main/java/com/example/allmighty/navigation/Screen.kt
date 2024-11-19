package com.example.allmighty.navigation

sealed class Screen(val route: String) {

    data object RecordScreen : Screen(route = "record_screen")
    data object RecordListScreen : Screen(route = "record_list_screen")
}