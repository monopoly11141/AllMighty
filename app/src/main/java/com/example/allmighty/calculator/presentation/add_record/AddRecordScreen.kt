package com.example.allmighty.calculator.presentation.add_record

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.allmighty.ui.theme.AllMightyTheme

@Composable
fun AddRecordScreenRoot(
    modifier : Modifier = Modifier,
    navController: NavController,
    viewModel: AddRecordViewModel = hiltViewModel()
) {
    AddRecordScreen(
        modifier = modifier,
        navController = navController,
        state = viewModel.state.collectAsStateWithLifecycle().value,
        onAction = { action ->
            viewModel.onAction(action)
        }
    )
}

@Composable
fun AddRecordScreen(
    modifier : Modifier = Modifier,
    navController: NavController,
    state: AddRecordState,
    onAction: (AddRecordAction) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Text(
            text = "hi",
            modifier = modifier
                .fillMaxWidth(),
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@PreviewLightDark
@Composable
private fun AddRecordScreenPreview() {
    AllMightyTheme {
        AddRecordScreen(
            navController = rememberNavController(),
            state = AddRecordState(
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