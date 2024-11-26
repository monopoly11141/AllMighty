package com.example.allmighty.calculator.presentation.add_round

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.example.allmighty.calculator.presentation.round.RoundAction
import com.example.allmighty.calculator.presentation.core.RadioGroup
import com.example.allmighty.calculator.presentation.round.RoundState
import com.example.allmighty.calculator.presentation.core.playerNameList
import com.example.allmighty.calculator.presentation.core.util.PledgeUtil.HIGH_BOUND
import com.example.allmighty.calculator.presentation.core.util.PledgeUtil.LOW_BOUND
import com.example.allmighty.calculator.presentation.model.TrumpSuit
import com.example.allmighty.calculator.presentation.round.AddRoundViewModel
import com.example.allmighty.calculator.presentation.round.component.RoundButton
import com.example.allmighty.core.presentation.component.TrickNumberPicker
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
    state: RoundState,
    onAction: (RoundAction) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RadioGroup(
            text = "마이티 플레이어: ",
            optionList = state.playerNameList,
            selectedIndex = state.mightyPlayerIndex
        ) { index ->
            onAction(RoundAction.OnMightyPlayerIndexChange(index))
        }

        HorizontalDivider(
            color = MaterialTheme.colorScheme.primary
        )

        RadioGroup(
            text = "친구 플레이어: ",
            optionList = state.playerNameList,
            selectedIndex = state.friendPlayerIndex
        ) { index ->
            onAction(RoundAction.OnFriendPlayerIndexChange(index))
        }

        HorizontalDivider(
            color = MaterialTheme.colorScheme.primary
        )

        RadioGroup(
            text = "기루다: ",
            optionList = TrumpSuit.entries.map { it.name },
            selectedIndex = state.trumpSuitIndex
        ) { index ->
            onAction(RoundAction.OnTrumpSuitChange(index))
        }

        HorizontalDivider(
            color = MaterialTheme.colorScheme.primary
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TrickNumberPicker(
                text = "공약 수",
                defaultValue = state.pledgeNumber,
                lowBound = LOW_BOUND,
                highBound = HIGH_BOUND
            ) { pledgeNumber ->
                onAction(RoundAction.OnPledgeNumberChange(pledgeNumber))
            }

            TrickNumberPicker(
                text = "여당 득점",
                defaultValue = state.actualNumber,
                lowBound = LOW_BOUND,
                highBound = HIGH_BOUND
            ) { actualNumber ->
                onAction(RoundAction.OnActualNumberChange(actualNumber))
            }
        }

        RoundButton(
            text = "라운드 저장",
            onClick = {
                onAction(RoundAction.OnRoundButtonClick)
                navController.popBackStack()
            }
        )

    }
}

@PreviewLightDark
@Composable
private fun AddRoundScreenPreview() {
    AllMightyTheme {
        AddRoundScreen(
            navController = rememberNavController(),
            state = RoundState(
                playerNameList = playerNameList
            ),
            onAction = {}
        )
    }
}