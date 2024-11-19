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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.allmighty.calculator.presentation.record.RecordAction
import com.example.allmighty.calculator.presentation.record.RecordScreen
import com.example.allmighty.calculator.presentation.record.RecordState
import com.example.allmighty.calculator.presentation.record.RecordViewModel
import com.example.allmighty.calculator.presentation.record.component.AddRoundButton
import com.example.allmighty.calculator.presentation.record.previewRecordUi
import com.example.allmighty.calculator.presentation.record_list.component.RecordItem
import com.example.allmighty.ui.theme.AllMightyTheme

@Composable
fun RecordListScreenRoot(
    navController: NavController,
    viewModel: RecordListViewModel = hiltViewModel()
) {
    RecordListScreen(
        navController = navController,
        state = viewModel.state.collectAsStateWithLifecycle().value
    )
}

@Composable
fun RecordListScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: RecordListViewModel = hiltViewModel(),
    state: RecordListState
) {
    Scaffold(
        floatingActionButton = {
            AddRoundButton(
                onClick = {
                    viewModel.onAction(RecordListAction.OnCreateRecord)
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
                            onClick =  {}
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
            )
        )
    }
}