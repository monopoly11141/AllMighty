package com.example.allmighty.calculator.presentation.record_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.allmighty.calculator.presentation.record.component.AddRoundButton
import com.example.allmighty.calculator.presentation.record.previewRecordUi
import com.example.allmighty.calculator.presentation.record_list.component.RecordItem
import com.example.allmighty.navigation.Screen
import com.example.allmighty.ui.theme.AllMightyTheme

@Composable
fun RecordListScreenRoot(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: RecordListViewModel = hiltViewModel()
) {
    RecordListScreen(
        modifier = modifier,
        navController = navController,
        state = viewModel.state.collectAsStateWithLifecycle().value,
        onAction = { action ->
            viewModel.onAction(action)
        }
    )
}

@Composable
fun RecordListScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    state: RecordListState,
    onAction: (RecordListAction) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            AddRoundButton(
                onClick = {
                    navController.navigate(Screen.AddRecordScreen.route)
                }
            )
        }
    ) { contentPaddings ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(contentPaddings)
        ) {
            LazyColumn(
                modifier = modifier
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                items(state.recordUiList) { recordUi ->
                    RecordItem(
                        recordUi = recordUi,
                        onItemClick = {
                            navController.navigate("${Screen.RecordScreen.route}/${recordUi.id}")
                        },
                        onDeleteClick = {
                            onAction(RecordListAction.OnDeleteRecord(recordUi.id.toString()))
                        }
                    )
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun RecordListScreenPreview() {
    AllMightyTheme {
        RecordListScreen(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background),
            rememberNavController(),
            state = RecordListState(
                (1..100).map { previewRecordUi }
            ),
            onAction = {}
        )
    }
}