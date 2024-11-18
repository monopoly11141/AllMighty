package com.example.allmighty.calculator.presentation.round_list.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.example.allmighty.calculator.presentation.model.RoundUi
import com.example.allmighty.calculator.presentation.model.toDisplayableNumber
import com.example.allmighty.core.presentation.util.getContentColor
import com.example.allmighty.ui.theme.AppTheme

@Composable
fun RoundSummary(
    roundUi: RoundUi,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val minScore = roundUi.players.withIndex().minByOrNull { player -> player.value.score }?.value?.score
    val maxScore = roundUi.players.withIndex().maxByOrNull { player -> player.value.score }?.value?.score

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

            roundUi.players.forEach { player ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(40.dp)
                ) {
                    val fontColor = when (player.score) {
                        maxScore -> Color.Blue
                        minScore -> Color.Red
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
    AppTheme {
        RoundSummary(
            roundUi = previewRoundUi,
            onClick = {},
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
        )
    }
}