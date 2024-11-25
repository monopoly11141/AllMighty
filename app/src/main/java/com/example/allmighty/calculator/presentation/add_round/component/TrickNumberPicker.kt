package com.example.allmighty.calculator.presentation.add_round.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.chargemap.compose.numberpicker.NumberPicker
import com.example.allmighty.ui.theme.AllMightyTheme

@Composable
fun TrickNumberPicker(
    text: String,
    modifier: Modifier = Modifier,
    defaultValue: Int,
    lowBound: Int,
    highBound: Int,
    onNumberChange: (Int) -> Unit,
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(4.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.primary
        )

        NumberPicker(
            modifier = modifier,
            value = defaultValue,
            onValueChange = { number ->
                onNumberChange(number)
            },
            range = (lowBound..highBound),
            textStyle = TextStyle(
                color = MaterialTheme.colorScheme.primary
            ),
            dividersColor = MaterialTheme.colorScheme.primary
        )
    }
}

@PreviewLightDark
@Composable
private fun TrickNumberPickerPreview() {
    AllMightyTheme {
        TrickNumberPicker(
            text = "공약 수: ",
            defaultValue = 12,
            lowBound = 0,
            highBound = 20
        ) {

        }
    }
}