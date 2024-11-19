package com.example.allmighty.calculator.presentation.record.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.example.allmighty.ui.theme.AllMightyTheme

@Composable
fun AddRoundButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FloatingActionButton(
        onClick = { onClick() }
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "라운드 추가"
        )
    }
}

@PreviewLightDark
@Composable
private fun AddRoundButtonPreview() {
    AllMightyTheme {
        AddRoundButton(
            onClick = {},
        )
    }
}