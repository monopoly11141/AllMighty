package com.example.allmighty.calculator.presentation.core

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun getContentColor() : Color{
    return MaterialTheme.colorScheme.onBackground
}