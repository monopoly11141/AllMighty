package com.example.allmighty.calculator.presentation.save_record

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.allmighty.calculator.presentation.save_record.component.AddRecordButton
import com.example.allmighty.calculator.presentation.save_record.component.PlayerNameTextField
import com.example.allmighty.calculator.presentation.save_record.component.RecordTitleTextField
import com.example.allmighty.navigation.Screen
import com.example.allmighty.ui.theme.AllMightyTheme

@Composable
fun EditRecordScreenRoot(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: EditRecordViewModel = hiltViewModel()
) {
    EditRecordScreen(
        modifier = modifier,
        navController = navController,
        state = viewModel.state.collectAsStateWithLifecycle().value,
        onAction = { action ->
            viewModel.onAction(action)
        }
    )
}

@Composable
fun EditRecordScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    state: SaveRecordState,
    onAction: (SaveRecordAction) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(
            modifier = Modifier
                .fillMaxHeight(0.1f)
        )

        RecordTitleTextField(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background),
            titleText = state.recordTitle,
            labelText = "기록 제목을 입력하세요."
        ) { recordTitle ->
            onAction(SaveRecordAction.OnRecordTitleChange(recordTitle))
        }

        Spacer(
            modifier = Modifier
                .fillMaxHeight(0.1f)
        )

        LazyColumn {
            items(state.playerNameList.size) { i ->

                PlayerNameTextField(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .padding(4.dp),
                    nameText = state.playerNameList[i],
                    labelText = "플레이어 ${i + 1}의 이름을 입력하세요. "
                ) { name ->
                    onAction(SaveRecordAction.OnPlayerNameChange(i, name))
                }

            }
        }

        Spacer(
            modifier = Modifier
                .fillMaxHeight(0.2f)
        )

        AddRecordButton(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background),
            text = "기록 수정"
        ) {
            onAction(SaveRecordAction.OnSaveRecord)
            navController.navigate(Screen.RecordListScreen.route)
        }

    }

}


@PreviewLightDark
@Composable
private fun EditRecordScreenPreview() {
    AllMightyTheme {
        AddRecordScreen(
            navController = rememberNavController(),
            state = SaveRecordState(
                playerNameList = listOf(
                    "player 1",
                    "player 2",
                    "player 3",
                    "player 4",
                    "player 5",
                ),
                recordTitle = "Record"
            ),
            onAction = {}
        )
    }

}