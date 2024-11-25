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
import com.example.allmighty.calculator.presentation.model.RoundUi
import com.example.allmighty.calculator.presentation.model.toDisplayableNumber
import com.example.allmighty.ui.theme.AllMightyTheme

@Composable
fun PlayerRadioGroup(
    modifier: Modifier = Modifier,
    text: String,
    playerNameList: List<String>,
    selectedPlayerIndex: Int,
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
            playerNameList.forEachIndexed { index, playerName ->
                Column() {
                    PlayerRadioButton(
                        modifier = modifier,
                        playerName = playerName,
                        playerIndex = index,
                        selectedPlayerIndex = selectedPlayerIndex
                    ) {
                        onclick(index)
                    }
                }
            }
        }

    }

}

@Composable
private fun PlayerRadioButton(
    modifier: Modifier = Modifier,
    playerName: String,
    playerIndex: Int,
    selectedPlayerIndex: Int,
    onClick: (Int) -> Unit
) {
    RadioButton(
        modifier = modifier,
        selected = playerIndex == selectedPlayerIndex,
        onClick = { onClick(selectedPlayerIndex) },
    )
    Text(
        text = playerName,
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
        PlayerRadioGroup(
            text = "마이티 플레이어 :",
            playerNameList = playerNameList,
            selectedPlayerIndex = 0,
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
