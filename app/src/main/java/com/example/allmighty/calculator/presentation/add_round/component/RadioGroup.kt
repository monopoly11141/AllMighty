package com.example.allmighty.calculator.presentation.add_round.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.allmighty.calculator.presentation.model.TrumpSuit
import com.example.allmighty.ui.theme.AllMightyTheme

@Composable
fun RadioGroup(
    modifier: Modifier = Modifier,
    text: String,
    optionList: List<String>,
    selectedIndex: Int,
    onclick: (Int) -> Unit
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text,
            modifier = modifier,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.primary
        )

        Row(
            modifier = modifier
        ) {
            optionList.forEachIndexed { index, playerName ->
                Column() {
                    RadioButton(
                        modifier = modifier,
                        option = playerName,
                        index = index,
                        selectedIndex = selectedIndex
                    ) {
                        onclick(index)
                    }
                }
            }
        }

    }

}

@Composable
private fun RadioButton(
    modifier: Modifier = Modifier,
    option: String,
    index: Int,
    selectedIndex: Int,
    onClick: (Int) -> Unit
) {
    RadioButton(
        modifier = modifier,
        selected = index == selectedIndex,
        onClick = { onClick(selectedIndex) },
    )
    Text(
        text = option,
        style = MaterialTheme.typography.bodyLarge,
        modifier = modifier
            .padding(horizontal = 4.dp),
        color = MaterialTheme.colorScheme.primary
    )
}

@PreviewLightDark
@Composable
private fun PlayerRadioGroupPreview() {
    AllMightyTheme {
        RadioGroup(
            text = "마이티 플레이어 :",
            optionList = playerNameList,
            selectedIndex = 0,
            onclick = {},
        )
    }
}

@PreviewLightDark
@Composable
private fun TrumpSuitRadioGroupPreview() {
    AllMightyTheme {
        RadioGroup(
            text = "기루다 :",
            optionList = TrumpSuit.entries.map { it.name },
            selectedIndex = 0,
            onclick = {},
        )
    }
}

internal val playerNameList = listOf(
        "Player 1",
        "Player 2",
        "Player 3",
        "Player 4",
        "Player 5"
)