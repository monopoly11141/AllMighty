package com.example.allmighty.calculator.presentation.record

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.allmighty.calculator.presentation.model.DisplayableTime
import com.example.allmighty.calculator.presentation.model.PlayerUi
import com.example.allmighty.calculator.presentation.model.RecordUi
import com.example.allmighty.calculator.presentation.record.component.AddRoundButton
import com.example.allmighty.calculator.presentation.record.component.RoundItem
import com.example.allmighty.calculator.presentation.record.component.RoundSummary
import com.example.allmighty.calculator.presentation.record.component.previewRoundUi
import com.example.allmighty.core.presentation.util.getContentColor
import com.example.allmighty.navigation.Screen
import com.example.allmighty.ui.theme.AllMightyTheme

@Composable
fun RecordScreenRoot(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: RecordViewModel = hiltViewModel(),
) {
    RecordScreen(
        modifier = modifier,
        navController = navController,
        state = viewModel.state.collectAsStateWithLifecycle().value,
        onAction = { action ->
            viewModel.onAction(action)
        }
    )

}

@Composable
fun RecordScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    state: RecordState,
    onAction: (RecordAction) -> Unit
) {
    LaunchedEffect(true) {
        onAction(RecordAction.UpdateRecord)
    }
    
    Scaffold(
        floatingActionButton = {
            AddRoundButton(
                onClick = {
                    navController.navigate("${Screen.AddRoundScreen.route}/${state.recordUi.id}")
                }
            )
        }
    ) { contentPaddings ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(contentPaddings)
        ) {
            Text(
                text = state.recordUi.title,
                modifier = modifier
                    .fillMaxWidth(),
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary
            )

            HorizontalDivider(
                color = getContentColor()
            )

            RoundSummary(
                playerUiList = state.recordUi.playerUiList,
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
                itemsIndexed(
                    items = state.recordUi.roundUiList
                ) { index, roundUi ->

                    Text(
                        text = "${index + 1} 라운드",
                        modifier = modifier
                            .fillMaxWidth(),
                        color = MaterialTheme.colorScheme.primary,
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


}

@PreviewLightDark
@Composable
private fun RecordScreenPreview() {
    AllMightyTheme {
        RecordScreen(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background),
            rememberNavController(),
            state = RecordState(
                recordUi = previewRecordUi
            ),
            onAction = {}
        )
    }
}

internal val previewRecordUi = RecordUi(
    playerUiList = (1..5).map { it ->
        PlayerUi(
            name = "Player $it",
            score = 0
        )
    },
    roundUiList = (1..100).map {
        previewRoundUi
    },
    title = "마이티",
    createdTime = DisplayableTime()
)