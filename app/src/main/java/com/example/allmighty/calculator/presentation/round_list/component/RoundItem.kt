package com.example.allmighty.calculator.presentation.round_list.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
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
import com.example.allmighty.calculator.presentation.model.toDisplayableNumber
import com.example.allmighty.core.presentation.util.getContentColor
import com.example.allmighty.ui.theme.AppTheme

@Composable
fun RoundItem(
    roundUi: RoundUi,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = "공약 : ${roundUi.pledgeNumber}",
                    fontWeight = FontWeight.Bold,
                    color = getContentColor()
                )

                Text(
                    text = "실제 : ${roundUi.actualNumber}",
                    fontWeight = FontWeight.Bold,
                    color = getContentColor()
                )

                Text(
                    text = "기루 : ${roundUi.trumpSuit}",
                    fontWeight = FontWeight.Bold,
                    color = getContentColor()
                )
            }

            HorizontalDivider(
                color = getContentColor()
            )

            roundUi.players.forEachIndexed { index, player ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(40.dp)
                ) {
                    Text(
                        text = player.name,
                        fontWeight = FontWeight.Bold,
                        color = getContentColor()
                    )

                    Text(
                        text = roundUi.scoreChange[index].formatted,
                        color = getContentColor()
                    )

                    if (index == roundUi.mightyPlayerIndex) {
                        Text(
                            text = "마이티",
                            color = getContentColor()
                        )
                    }
                    if (index == roundUi.friendPlayerIndex) {
                        Text(
                            text = "친구",
                            color = getContentColor()
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
        Player("Player 1", 2),
        Player("Player 2", 100),
        Player("Player 3", -24),
        Player("Player 4", -24),
        Player("Player 5", 100)
    ),
    mightyPlayerIndex = 1,
    friendPlayerIndex = 3,
    trumpSuit = "Spades",
    pledgeNumber = 14,
    actualNumber = 16,
    scoreChange = listOf(-4, 4, -4, 2, -4).map { it -> it.toDisplayableNumber() }
)