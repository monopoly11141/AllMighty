package com.example.allmighty.calculator.presentation.round_list.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.allmighty.calculator.presentation.model.Player
import com.example.allmighty.calculator.presentation.model.RoundUi
import com.example.allmighty.ui.theme.AppTheme

@Composable
fun RoundItem(
    roundUi: RoundUi,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val contentColor = if (isSystemInDarkTheme()) Color.White else Color.Black

    Row(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            roundUi.players.forEachIndexed { index, player ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(40.dp)
                ) {
                    Text(
                        text = player.name,
                        fontWeight = FontWeight.Bold,
                        color = contentColor
                    )
                    Text(
                        text = player.score.toString(),
                        color = contentColor
                    )
                    if(index == roundUi.mightyPlayerIndex) {
                        Text(
                            text = "마이티",
                            color = contentColor
                        )
                    }
                    if(index == roundUi.friendPlayerIndex) {
                        Text(
                            text ="친구",
                            color = contentColor
                        )
                    }
                }

            }
        }
    }

}

@PreviewLightDark
@Composable
private fun RoundItemPreview() {
    AppTheme {
        RoundItem(
            roundUi = previewRoundUi,
            onClick = {},
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
        )
    }
}

internal val previewRoundUi = RoundUi(
    players = listOf(
        Player("Player 1", 0),
        Player("Player 2", 0),
        Player("Player 3", 0),
        Player("Player 4", 0),
        Player("Player 5", 0)
    ),
    mightyPlayerIndex = 1,
    friendPlayerIndex = 3,
    trumpSuit = "Spades",
    pledgeNumber = 14,
    actualNumber = 16
)