package com.example.allmighty.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.allmighty.calculator.presentation.round_list.RoundListScreenRoot
import com.example.allmighty.calculator.presentation.round_list.RoundListViewModel
import com.example.allmighty.navigation.Navigation
import com.example.allmighty.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                val navHostController = rememberNavController()
                Navigation(navHostController)

                val rondListViewModel: RoundListViewModel = hiltViewModel()
                RoundListScreenRoot(rondListViewModel)
            }
        }
    }
}