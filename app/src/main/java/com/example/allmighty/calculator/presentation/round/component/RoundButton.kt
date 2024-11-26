package com.example.allmighty.calculator.presentation.round.component

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.example.allmighty.ui.theme.AllMightyTheme

@Composable
fun RoundButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = { onClick() }
    ) {
        Text(
            text = text
        )
    }
}

@PreviewLightDark
@Composable
private fun AddRoundButtonPreview() {
    AllMightyTheme {
        RoundButton(
            text = "라운드 저장",
            onClick = {}
        )
    }
}