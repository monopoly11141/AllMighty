package com.example.allmighty.calculator.presentation.record.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.allmighty.calculator.presentation.model.RoundUi
import com.example.allmighty.calculator.presentation.model.TrumpSuit
import com.example.allmighty.calculator.presentation.model.toDisplayableNumber
import com.example.allmighty.calculator.presentation.core.getContentColor
import com.example.allmighty.ui.theme.AllMightyTheme

@Composable
fun RoundItem(
    roundNumber: Int,
    roundUi: RoundUi,
    onDeleteClick : () -> Unit,
    onEditClick : () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(2.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {

            IconButton(
                onClick = { onDeleteClick() }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = "delete",
                    tint = MaterialTheme.colorScheme.primary,
                )
            }
            
            Text(
                text = "$roundNumber 라운드",
                modifier = modifier
                    .weight(1f),
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.ExtraBold
            )

            IconButton(
                onClick = { onEditClick() }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = "edit",
                    tint = MaterialTheme.colorScheme.primary
                )
            }

        }


        Row(
            modifier = modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = "공약 : ${roundUi.pledgeNumber}",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "실제 : ${roundUi.actualNumber}",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "기루 : ${roundUi.trumpSuit}",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }

        HorizontalDivider(
            color = getContentColor(),
            modifier = Modifier
                .padding(horizontal = 4.dp)
        )

        roundUi.playerNameList.forEachIndexed { index, playerName ->

            val fontColor = when (index) {
                roundUi.mightyPlayerIndex -> MaterialTheme.colorScheme.primary
                roundUi.friendPlayerIndex -> MaterialTheme.colorScheme.primary
                else -> getContentColor()
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(40.dp)
            ) {
                Text(
                    text = playerName,
                    fontWeight = FontWeight.Bold,
                    color = fontColor
                )

                Text(
                    text = roundUi.scoreChange[index].formatted,
                    color = fontColor
                )

                if (index == roundUi.mightyPlayerIndex) {
                    Text(
                        text = "마이티",
                        color = fontColor
                    )
                }
                if (index == roundUi.friendPlayerIndex) {
                    Text(
                        text = "친구",
                        color = fontColor
                    )
                }

            }

        }
    }


}

@PreviewLightDark
@Composable
private fun RoundItemPreview() {
    AllMightyTheme {
        RoundItem(
            roundNumber = 1,
            roundUi = previewRoundUi,
            onDeleteClick = {},
            onEditClick = {},
            modifier = Modifier
        )
    }
}

internal val previewRoundUi = RoundUi(
    playerNameList = listOf(
        "Player 1",
        "Player 2",
        "Player 3",
        "Player 4",
        "Player 5"
    ),
    mightyPlayerIndex = 1,
    friendPlayerIndex = 3,
    trumpSuit = TrumpSuit.스페이드,
    pledgeNumber = 14,
    actualNumber = 16,
    scoreChange = listOf(-4, 4, -4, 2, -4).map { it -> it.toDisplayableNumber() }
)