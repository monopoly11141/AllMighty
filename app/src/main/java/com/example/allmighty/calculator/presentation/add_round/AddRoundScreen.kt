package com.example.allmighty.calculator.presentation.add_round

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.allmighty.calculator.presentation.add_round.component.PlayerRadioGroup
import com.example.allmighty.calculator.presentation.add_round.component.TrickNumberPicker
import com.example.allmighty.calculator.presentation.add_round.component.playerNameList
import com.example.allmighty.calculator.presentation.util.PledgeUtil.HIGH_BOUND
import com.example.allmighty.calculator.presentation.util.PledgeUtil.LOW_BOUND
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
        PlayerRadioGroup(
            text = "마이티 플레이어: ",
            playerNameList = state.playerNameList,
            selectedPlayerIndex = 0
        ) { index ->
            onAction(AddRoundAction.OnMightyPlayerIndexChange(index))
        }

        HorizontalDivider(
            color = MaterialTheme.colorScheme.primary
        )

        PlayerRadioGroup(
            text = "친구 플레이어: ",
            playerNameList = state.playerNameList,
            selectedPlayerIndex = 0
        ) { index ->
            onAction(AddRoundAction.OnFriendPlayerIndexChange(index))
        }

        HorizontalDivider(
            color = MaterialTheme.colorScheme.primary
        )

        TrickNumberPicker(
            text = "공약 수 :",
            defaultValue = state.pledgeNumber,
            lowBound = LOW_BOUND,
            highBound = HIGH_BOUND
        ) { pledgeNumber ->
            onAction(AddRoundAction.OnPledgeNumberChange(pledgeNumber))
        }

    }
}

@PreviewLightDark
@Composable
private fun AddRoundScreenPreview() {
    AllMightyTheme {
        AddRoundScreen(
            navController = rememberNavController(),
            state = AddRoundState(
                playerNameList = playerNameList
            ),
            onAction = {}
        )
    }
}