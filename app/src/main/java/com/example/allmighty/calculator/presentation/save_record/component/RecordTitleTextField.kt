package com.example.allmighty.calculator.presentation.save_record.component

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.example.allmighty.ui.theme.AllMightyTheme

@Composable
fun RecordTitleTextField(
    modifier: Modifier = Modifier,
    titleText: String,
    labelText: String,
    onValueChange: (String) -> Unit,
) {
    TextField(
        value = titleText,
        onValueChange = { title -> onValueChange(title) },
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
private fun RecordTitleTextFieldPreview() {
    AllMightyTheme {
        RecordTitleTextField(
            titleText = "제목",
            labelText = "기록 제목을 입력하세요",
            onValueChange = {},
        )
    }
}