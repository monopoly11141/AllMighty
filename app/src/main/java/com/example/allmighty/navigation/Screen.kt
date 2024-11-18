package com.example.allmighty.navigation

sealed class Screen(val route: String) {

    data object DisplayScreen : Screen(route = "display_screen")
}