package com.example.allmighty.calculator.presentation.record.component

import androidx.compose.foundation.background
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.allmighty.calculator.presentation.model.PlayerUi
import com.example.allmighty.calculator.presentation.model.toDisplayableNumber
import com.example.allmighty.calculator.presentation.core.getContentColor
import com.example.allmighty.ui.theme.AllMightyTheme

@Composable
fun RoundSummary(
    playerUiList: List<PlayerUi>,
    modifier: Modifier = Modifier
) {

    val minScore = playerUiList.withIndex().minByOrNull { player -> player.value.score }?.value?.score
    val maxScore = playerUiList.withIndex().maxByOrNull { player -> player.value.score }?.value?.score

    Row(
        modifier = modifier
            .padding(4.dp)
            .background(MaterialTheme.colorScheme.background),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
        ) {

            playerUiList.forEach { player ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(40.dp)
                ) {
                    val fontColor = when (player.score) {
                        maxScore -> MaterialTheme.colorScheme.primary
                        minScore -> MaterialTheme.colorScheme.error
                        else -> getContentColor()
                    }

                    Text(
                        text = player.name,
                        fontWeight = FontWeight.Bold,
                        color = fontColor
                    )

                    Text(
                        text = player.score.toDisplayableNumber().formatted,
                        color = fontColor
                    )
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun RoundSummaryPreview() {
    AllMightyTheme {
        RoundSummary(
            playerUiList = listOf(
                PlayerUi("player 1", 2),
                PlayerUi("player 2", 100),
                PlayerUi("player 3", -24),
                PlayerUi("player 4", -24),
                PlayerUi("player 5", 100),
            ),
            modifier = Modifier

        )
    }
}