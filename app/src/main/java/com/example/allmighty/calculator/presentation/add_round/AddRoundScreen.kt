package com.example.allmighty.calculator.presentation.add_round

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.allmighty.ui.theme.AllMightyTheme

@Composable
fun AddRoundScreenRoot(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: AddRoundViewModel = hiltViewModel()
) {
    AddRoundScreen(
        modifier = modifier,
        navController = navController,
        state = viewModel.state.collectAsStateWithLifecycle().value,
        onAction = { action ->
            viewModel.onAction(action)
        }
    )
}

@Composable
fun AddRoundScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    state: AddRoundState,
    onAction: (AddRoundAction) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

    }
}

@PreviewLightDark
@Composable
private fun AddRoundScreenPreview() {
    AllMightyTheme {
        AddRoundScreen(
            navController = rememberNavController(),
            state = AddRoundState(),
            onAction = {}
        )
    }
}