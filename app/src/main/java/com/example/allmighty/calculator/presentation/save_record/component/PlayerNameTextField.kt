package com.example.allmighty.calculator.presentation.save_record.component

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.example.allmighty.ui.theme.AllMightyTheme

@Composable
fun PlayerNameTextField(
    modifier: Modifier = Modifier,
    nameText: String,
    labelText: String,
    onValueChange: (String) -> Unit,
) {
    TextField(
        value = nameText,
        onValueChange = { name -> onValueChange(name) },
        modifier = modifier,
        label = {
            Text(
                text = labelText
            )
        }
    )
}


@PreviewLightDark
@Composable
private fun PlayerNameTextFieldPreview() {
    AllMightyTheme {
        PlayerNameTextField(
            nameText = "플레이어 이름",
            labelText = "플레이어 이름을 입력하세요.",
            onValueChange = {},
        )
    }
}