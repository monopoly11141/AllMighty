package com.example.allmighty.calculator.presentation.round_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.allmighty.calculator.presentation.round_list.component.RoundItem
import com.example.allmighty.calculator.presentation.round_list.component.RoundSummary
import com.example.allmighty.calculator.presentation.round_list.component.previewRoundUi
import com.example.allmighty.core.presentation.util.getContentColor
import com.example.allmighty.ui.theme.AppTheme

@Composable
fun RoundListScreenRoot(
    viewModel: RoundListViewModel = hiltViewModel()
) {
    RoundListScreen(viewModel.state)
}

@Composable
fun RoundListScreen(
    state: RoundListState,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Text(
            text = "Record",
            modifier = modifier
                .fillMaxWidth(),
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center,
            color = getContentColor()
        )

        HorizontalDivider(
            color = getContentColor()
        )

        RoundSummary(
            roundUi = state.roundList.last(),
            onClick = {},
            modifier = modifier
                .fillMaxWidth()
        )

        HorizontalDivider(
            color = getContentColor()
        )

        LazyColumn(
            modifier = modifier
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            itemsIndexed(state.roundList) { index, roundUi ->

                Text(
                    text = "${index + 1} 라운드",
                    modifier = modifier
                        .fillMaxWidth(),
                    color = getContentColor(),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.ExtraBold
                )

                RoundItem(
                    roundUi = roundUi,
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                HorizontalDivider(
                    color = getContentColor()
                )

            }
        }
    }

}

@PreviewLightDark
@Composable
private fun RoundListScreenPreview() {
    AppTheme {
        RoundListScreen(
            state = RoundListState(
                roundList = (1..100).map {
                    previewRoundUi
                }
            ),
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
        )
    }
}