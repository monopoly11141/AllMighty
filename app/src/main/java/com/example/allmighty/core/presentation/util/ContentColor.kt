package com.example.allmighty.core.presentation.util

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun getContentColor() : Color{
    return if (isSystemInDarkTheme()) Color.White else Color.Black
}